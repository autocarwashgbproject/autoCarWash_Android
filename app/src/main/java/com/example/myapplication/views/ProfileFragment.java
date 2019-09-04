package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.myapplication.App;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.ProfilePresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.CAR_PIC;
import static com.example.myapplication.Const.PICTURE_PREFS;
import static com.example.myapplication.Const.PROFILE_PIC;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileIF {

    private ImageView avatar;
    private ImageView car;

    @InjectPresenter
    ProfilePresenter profilePresenter;

    private Button updateClientBtn;
    private Button deleteClientBtn;
    private Button logoutClientBtn;
    private Button updateCarBtn;
    private Button deleteCarBtn;
    private Button createCarBtn;
    private EditText carNumber;

    @ProvidePresenter
    public ProfilePresenter providePresenter() {
        final ProfilePresenter profilePresenter = new ProfilePresenter();
        App.getInstance().getAppComponent().inject(profilePresenter);
        return profilePresenter;
    }

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

        initButtons(view);

        avatar = view.findViewById(R.id.profile_img_id);
        car = view.findViewById(R.id.profile_car_img_id);
        loadCurrentImgs();


//         carNumber = view.findViewById(R.id.profile_fragment_car_number_text);
//         updateClientBtn = view.findViewById(R.id.profile_fragment_button);
//         deleteClientBtn = view.findViewById(R.id.profile_fragment_button_delete);
//         logoutClientBtn = view.findViewById(R.id.profile_fragment_button_logout);
//         updateCarBtn = view.findViewById(R.id.profile_fragment_button_car_update);
//         deleteCarBtn = view.findViewById(R.id.profile_fragment_button_car_delete);
//         createCarBtn = view.findViewById(R.id.profile_fragment_button_car_create);
//         updateClientBtn.setOnClickListener(v -> profilePresenter.updateClient());
//         deleteClientBtn.setOnClickListener(v -> profilePresenter.deleteClient());
//         logoutClientBtn.setOnClickListener(v -> profilePresenter.logout());
//         updateCarBtn.setOnClickListener(v -> profilePresenter.updateCar(carNumber.getText().toString()));
//         deleteCarBtn.setOnClickListener(v -> profilePresenter.deleteCar(carNumber.getText().toString()));
//         createCarBtn.setOnClickListener(v -> profilePresenter.createCar(carNumber.getText().toString()));

        profilePresenter.getClientFromApi();

        return view;
    }

    private void initButtons(View view) {
        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.VISIBLE);
        }

        Button editProfile = view.findViewById(R.id.edit_profile_btn_id);
        editProfile.setOnClickListener(v -> {
            if (activity != null) {
                activity.loadFragment(FillProfileFragment.newInstance(null));
            }
        });

        Button addCar = view.findViewById(R.id.add_car_btn_id);
        addCar.setOnClickListener(v -> {
            if (activity != null) {
                activity.loadFragment(CarProfileFragment.newInstance(null));
            }
        });
    }

    private void loadCurrentImgs() {
        MainActivity activity = (MainActivity) getActivity();
        String uri;
        if (activity != null) {
            uri = activity.loadPicture(PICTURE_PREFS, PROFILE_PIC);
            if (uri != null) {
                Picasso.get()
                        .load(uri)
                        .fit()
                        .transform(new CropCircleTransformation())
                        .into(avatar);
            }
            uri = activity.loadPicture(PICTURE_PREFS, CAR_PIC);
            if (uri != null) {
                Picasso.get()
                        .load(uri)
                        .fit()
                        .transform(new CropCircleTransformation())
                        .into(car);
            }
        }
    }
}
