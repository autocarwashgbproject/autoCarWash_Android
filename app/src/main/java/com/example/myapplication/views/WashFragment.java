package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.WashPresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class WashFragment extends MvpAppCompatFragment implements WashIF {

    @InjectPresenter
    public WashPresenter presenter;

    private ImageView avatar;

    public WashFragment() {
    }

    public static WashFragment newInstance() {
        return new WashFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wash_layout_fragment, container, false);

        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.VISIBLE);
        }

        avatar = view.findViewById(R.id.user_avatar_img_id);
        loadCurrentProfilePicture();

        return view;
    }

    private void loadCurrentProfilePicture() {

        MainActivity activity = (MainActivity) getActivity();
        String uri = null;

        if (activity != null) {
            uri = activity.loadPicture(
                    MainActivity.PICTURE_PREFS,
                    MainActivity.PROFILE_PIC
            );
        }
        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .placeholder(R.drawable.user)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(avatar);
        }
    }
}
