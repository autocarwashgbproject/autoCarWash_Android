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
import com.example.myapplication.presenters.FillProfilePresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class FillProfileFragment extends MvpAppCompatFragment implements FillProfileIF {

    private ImageView avatar;

    @InjectPresenter
    FillProfilePresenter presenter;

    public FillProfileFragment() {
    }

    public static FillProfileFragment newInstance(String uri) {
        FillProfileFragment pf = new FillProfileFragment();
        if (uri != null) {
            Bundle args = new Bundle();
            args.putString("imgURI", uri);
            pf.setArguments(args);
        }
        return pf;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fill_profile_fragment, container, false);

        ImageView addPhoto = view.findViewById(R.id.add_photo_btn_img_id);
        avatar = view.findViewById(R.id.profile_avatar_img_id);

        final MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
            activity.findViewById(R.id.include).setVisibility(View.GONE);
        }

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.pickFromGallery(MainActivity.LOAD_PROFILE_PICTURE_CODE);
                }
            }
        });

        Button save = view.findViewById(R.id.save_profile_btn_id);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.loadFragment(ProfileFragment.newInstance());
                }
            }
        });

        loadCurrentAvatarImg();

        return view;
    }

    private void loadCurrentAvatarImg() {

        MainActivity activity = (MainActivity) getActivity();
        String uri = null;

        if (getArguments() != null) {
            uri = getArguments().getString("imgURI");

            if (activity != null) {
                activity.savePicture(
                        MainActivity.PICTURE_PREFS,
                        MainActivity.PROFILE_PIC,
                        uri
                );
            }

            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(avatar);

        } else if (activity != null) {
            uri = activity.loadPicture(
                    MainActivity.PICTURE_PREFS,
                    MainActivity.PROFILE_PIC
            );
        }

        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(avatar);
        }
    }
}
