package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.ProfilePresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.CAR_PIC;
import static com.example.myapplication.Const.PICTURE_PREFS;
import static com.example.myapplication.Const.PROFILE_PIC;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileIF {

    @InjectPresenter
    ProfilePresenter presenter;

    private ImageView avatar;
    private ImageView car;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.VISIBLE);
        }

        Button editProfile = view.findViewById(R.id.edit_profile_btn_id);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.loadFragment(FillProfileFragment.newInstance(null));
                }
            }
        });

        Button addCar = view.findViewById(R.id.add_car_btn_id);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.loadFragment(CarProfileFragment.newInstance(null));
                }
            }
        });

        avatar = view.findViewById(R.id.profile_img_id);
        car = view.findViewById(R.id.profile_car_img_id);
        loadCurrentImgs();

        return view;
    }

    private void loadCurrentImgs() {

        MainActivity activity = (MainActivity) getActivity();
        String uri = null;

        if (activity != null) {
            uri = activity.loadPicture(
                    PICTURE_PREFS,
                    PROFILE_PIC
            );
        }

        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(avatar);
        }

        if (activity != null) {
            uri = activity.loadPicture(
                    PICTURE_PREFS,
                    CAR_PIC
            );
        }

        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(car);
        }
    }
}
