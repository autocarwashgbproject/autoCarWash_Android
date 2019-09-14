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

        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
        }

        initToolbar(view);

        TextView agreement = view.findViewById(R.id.service_agreement_txt_id);
        agreement.setOnClickListener(v -> aboutPresenter.showAgreement());

        TextView policy = view.findViewById(R.id.privacy_policy_txt_id);
        policy.setOnClickListener(v -> aboutPresenter.showPrivacyPolicy());

        return view;
    }

    private void initToolbar(View view) {
        final Toolbar toolbar = view.findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> {
            MainActivity activity = ((MainActivity) getActivity());
            if (activity != null) {
                activity.loadFragment(MenuFragment.newInstance());
            }
        });
    }
}