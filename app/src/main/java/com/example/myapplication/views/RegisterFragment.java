package com.example.myapplication.views;


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
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.RegisterPresenter;


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

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register, container, false);

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
                    s.insert(0, "+7");
                }
            }
        });

        codeNum1 = view.findViewById(R.id.enter_digit_1_edit_txt_id);
        codeNum1.addTextChangedListener(codeNumsWatcher);
        codeNum2 = view.findViewById(R.id.enter_digit_2_edit_txt_id);
        codeNum2.addTextChangedListener(codeNumsWatcher);
        codeNum3 = view.findViewById(R.id.enter_digit_3_edit_txt_id);
        codeNum3.addTextChangedListener(codeNumsWatcher);
        codeNum4 = view.findViewById(R.id.enter_digit_4_edit_txt_id);
        codeNum4.addTextChangedListener(codeNumsWatcher);

        agreement = view.findViewById(R.id.user_agreement_checkbox_id);

        getCodeBtn = view.findViewById(R.id.get_code_btn_id);
        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.getSmsCode(enteredPhone.getText().toString());
            }
        });

        registerBtn = view.findViewById(R.id.register_btn_id);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeNum1.getText().toString() +
                        codeNum2.getText().toString() +
                        codeNum3.getText().toString() +
                        codeNum4.getText().toString();

                registerPresenter.register(
                        code,
                        agreement.isChecked()
                );
            }
        });

        return view;
    }

    private TextWatcher codeNumsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            TextView text = (TextView) getActivity().getCurrentFocus();

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
    public void showErrorMessage(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(message);
        dialog.setPositiveButton("Хорошо", null);
        dialog.show();
    }

    @Override
    public void loadMain() {
        ((MainActivity) getActivity()).loadFragment(WashFragment.newInstance());
    }
}
