package com.example.myapplication.views;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.myapplication.presenters.WelcomePresenter;

import static com.example.myapplication.Const.CODE_ERROR;
import static com.example.myapplication.Const.PHONE_ERROR;

public class WelcomeFragment extends MvpAppCompatFragment implements WelcomeIF {
    private EditText phoneEdit;
    private EditText codeEdit;
    private Button codeButton;
    private Button nextButton;
    private TextView requestCodeText;

    @InjectPresenter
    WelcomePresenter welcomePresenter;

    @ProvidePresenter
    public WelcomePresenter providePresenter() {
        final WelcomePresenter welcomePresenter = new WelcomePresenter();
        App.getInstance().getAppComponent().inject(welcomePresenter);
        return welcomePresenter;
    }

    public WelcomeFragment() {
    }

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment, container, false);
        initUI(view);
        initPhoneField();
        onButtonClick();
        return view;
    }

    private void initUI(View view) {
        phoneEdit = view.findViewById(R.id.welcome_phone);
        codeEdit = view.findViewById(R.id.welcome_code);
        codeButton = view.findViewById(R.id.welcome_code_btn);
        nextButton = view.findViewById(R.id.welcome_next_btn);
        requestCodeText = view.findViewById(R.id.repeat_request_text);
    }

    private void onButtonClick() {
        codeButton.setOnClickListener(v -> {
            startRequestTimer();
            codeButton.setClickable(false);
            welcomePresenter.getSmsCode(phoneEdit.getText().toString());
        });
        nextButton.setOnClickListener(v -> {
            String code = codeEdit.getText().toString();
            welcomePresenter.register(code);
        });
    }

    private void startRequestTimer() {
        requestCodeText.setVisibility(View.VISIBLE);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                requestCodeText.setText("Запросить код повторно через " + millisUntilFinished / 1000 + " c");
            }

            public void onFinish() {
                requestCodeText.setVisibility(View.GONE);
                codeButton.setClickable(true);
            }
        }.start();
    }


    private void initPhoneField() {
        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("+7")) {
                    s.insert(0, "+7");
                }
            }
        });
    }

    @Override
    public void showErrorMessage(int code) {
        String message = getString(R.string.code_check);
        switch (code) {
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
            activity.loadFragment(RegisterFragment.newInstance());
        }
    }

    // Заполнение полей для кода из смс, только для тестов.
    @Override
    public void fillCodeFields(String smsForTests) {
        if (smsForTests != null) {
            codeEdit.getText().insert(0, smsForTests);
        }
    }
}