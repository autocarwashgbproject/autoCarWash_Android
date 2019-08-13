package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.AboutPresenter;

public class AboutFragment extends MvpAppCompatFragment implements AboutIF {

    @InjectPresenter
    AboutPresenter aboutPresenter;

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_app, container, false);

        ((MainActivity) getActivity()).getBottomNavigationView().setVisibility(View.GONE);

        Toolbar toolbar = view.findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(MenuFragment.newInstance());
            }
        });

        TextView agreement = view.findViewById(R.id.service_agreement_txt_id);
        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutPresenter.showAgreement();
            }
        });

        TextView policy = view.findViewById(R.id.privacy_policy_txt_id);
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutPresenter.showPrivacyPolicy();
            }
        });

        return view;
    }
}