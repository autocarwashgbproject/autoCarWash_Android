package com.example.myapplication.views;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

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
    private EditText name;
    private EditText surname;
    private EditText patronymic;
    private EditText email;
    private EditText phonenumber;

    private CheckBox checkBox;


    @InjectPresenter
    RegisterPresenter registerPresenter;


    @ProvidePresenter
    public RegisterPresenter providePresenter() {
        final RegisterPresenter registerPresenter = new RegisterPresenter();
        App.getInstance().getAppComponent().inject(registerPresenter);
        return registerPresenter;
    }

    public RegisterFragment() {
    }

    //TODO REGISTER FRAGMENT

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
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        initUI(view);
        return view;
    }

    private void initUI(View view) {
        name = view.findViewById(R.id.register_name);
        surname = view.findViewById(R.id.register_surname);
        patronymic = view.findViewById(R.id.register_patronymic);
        email = view.findViewById(R.id.register_email);
        phonenumber = view.findViewById(R.id.register_phone);
    }

}
