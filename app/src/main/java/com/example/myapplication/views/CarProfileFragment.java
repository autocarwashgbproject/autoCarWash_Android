package com.example.myapplication.views;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.CarProfilePresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class CarProfileFragment extends MvpAppCompatFragment implements CarProfileIF {

    @InjectPresenter
    CarProfilePresenter presenter;

    public CarProfileFragment() {
        // Required empty public constructor
    }

    public static CarProfileFragment newInstance(String uri) {
        CarProfileFragment pf = new CarProfileFragment();
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
        View view = inflater.inflate(R.layout.car_profile_fragment, container, false);

        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
            activity.findViewById(R.id.include).setVisibility(View.GONE);
        }
        EditText carNumber = view.findViewById(R.id.car_number_etxt_id);

        ImageView autoImg = view.findViewById(R.id.auto_profile_img_id);
        ImageView addImgBtn = view.findViewById(R.id.add_btn_img_id);

        if (getArguments() != null) {
            Uri uri = Uri.parse(getArguments().getString("imgURI"));

            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(autoImg);
        }

        addImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.pickFromGallery(MainActivity.LOAD_CAR_PICTURE_CODE);
                }
            }
        });

        return view;
    }
}
