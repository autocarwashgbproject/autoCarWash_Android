package com.example.myapplication.views;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.myapplication.App;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.RegisterPresenter;

import static com.example.myapplication.Const.CODE_ERROR;
import static com.example.myapplication.Const.PHONE_ERROR;
import static com.example.myapplication.Const.POLICY_ERROR;


public class RegisterFragment extends MvpAppCompatFragment implements RegisterIF {

    @InjectPresenter
    RegisterPresenter registerPresenter;

    private EditText enteredPhone;

    private EditText codeNum1;
    private EditText codeNum2;
    private EditText codeNum3;
    private EditText codeNum4;

    private CheckBox agreement;

    private Button getCodeBtn;
    private Button registerBtn;

    @ProvidePresenter
    public RegisterPresenter providePresenter() {
        final RegisterPresenter registerPresenter = new RegisterPresenter();
        App.getInstance().getAppComponent().inject(registerPresenter);
        return registerPresenter;
    }

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register, container, false);

        initPhoneField(view);
        initCodeFields(view);
        initButtons(view);

        return view;
    }

    private void initButtons(View view) {
        agreement = view.findViewById(R.id.user_agreement_checkbox_id);

        getCodeBtn = view.findViewById(R.id.get_code_btn_id);
        getCodeBtn.setOnClickListener(v -> {
                    registerPresenter.getSmsCode(enteredPhone.getText().toString().trim());
                }
        );

        registerBtn = view.findViewById(R.id.register_btn_id);
        registerBtn.setOnClickListener(v -> {
            String code = codeNum1.getText().toString() +
                    codeNum2.getText().toString() +
                    codeNum3.getText().toString() +
                    codeNum4.getText().toString();

            registerPresenter.register(
                    code,
                    agreement.isChecked()
            );
        });
    }

    private void initCodeFields(View view) {
        codeNum1 = view.findViewById(R.id.enter_digit_1_edit_txt_id);
        codeNum1.addTextChangedListener(codeNumsWatcher);
        codeNum2 = view.findViewById(R.id.enter_digit_2_edit_txt_id);
        codeNum2.addTextChangedListener(codeNumsWatcher);
        codeNum3 = view.findViewById(R.id.enter_digit_3_edit_txt_id);
        codeNum3.addTextChangedListener(codeNumsWatcher);
        codeNum4 = view.findViewById(R.id.enter_digit_4_edit_txt_id);
        codeNum4.addTextChangedListener(codeNumsWatcher);

    }

    private void initPhoneField(View view) {
        enteredPhone = view.findViewById(R.id.enter_phone_edit_txt_id);
        enteredPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("+7")) {
                    if (s.toString().startsWith("+")) {
                        s.insert(1, "7");
                    } else {
                        s.insert(0, "+7");
                    }
                }
            }
        });
    }

    private TextWatcher codeNumsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            MainActivity activity = ((MainActivity) getActivity());
            TextView text = null;
            if (activity != null) {
                text = (TextView) getActivity().getCurrentFocus();
            }

            if (text != null && text.length() > 0) {
                View next = text.focusSearch(View.FOCUS_RIGHT);
                if (next != null)
                    next.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void showErrorMessage(int code) {
        String message = getString(R.string.code_check);
        switch (code) {
            case POLICY_ERROR:
                message = getString(R.string.policy_check);
                break;
            case CODE_ERROR:
                message = getString(R.string.code_check);
                break;
            case PHONE_ERROR:
                message = getString(R.string.phone_check);
                break;
        }

        showAlertDialog(message);
    }

    private void showAlertDialog(String message) {
        Context context = getContext();
        AlertDialog.Builder dialog;
        if (context != null) {
            dialog = new AlertDialog.Builder(context);
            dialog.setTitle(message);
            dialog.setPositiveButton(getString(R.string.positive_button_text), null);
            dialog.show();
        }
    }

    @Override
    public void loadMain() {
        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.loadFragment(WashFragment.newInstance());
        }
    }

    @Override
    public void loadProfile() {
        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.loadFragment(FillProfileFragment.newInstance(true));
        }
    }

    // Заполнение полей для кода из смс, только для тестов.
    @Override
    public void fillCodeFields(String smsForTests) {
        if (smsForTests != null) {
            codeNum1.getText().insert(0, String.valueOf(smsForTests.charAt(0)));
            codeNum2.getText().insert(0, String.valueOf(smsForTests.charAt(1)));
            codeNum3.getText().insert(0, String.valueOf(smsForTests.charAt(2)));
            codeNum4.getText().insert(0, String.valueOf(smsForTests.charAt(3)));
        }
    }

    @Override
    public void saveRegistrationStatus() {
        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.changeAuthorizationStatus(true);
        }
    }
}
