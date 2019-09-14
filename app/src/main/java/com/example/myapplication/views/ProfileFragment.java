package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.myapplication.App;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.api.parsingJson.ApiCar;
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.presenters.ProfilePresenter;
import com.squareup.picasso.Picasso;

import java.util.Map;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.CAR_PIC;
import static com.example.myapplication.Const.PICTURE_PREFS;
import static com.example.myapplication.Const.PROFILE_PIC;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileIF {

    private ImageView avatar;
    private ImageView car;

    @InjectPresenter
    ProfilePresenter profilePresenter;

    private TextView profileName;
    private TextView profilePhone;
    private TextView profileCarNumber;
    private TextView profileCarDescription;

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

        initViews(view);

        loadCurrentImgs();

        profilePresenter.getClientFromApi();
        profilePresenter.getCarData();

        return view;
    }

    private void initViews(View view) {
        profileName = view.findViewById(R.id.profile_name_txt_id);
        profilePhone = view.findViewById(R.id.profile_phone_txt_id);
        profileCarNumber = view.findViewById(R.id.profile_car_data_txt_id);
        profileCarDescription = view.findViewById(R.id.profile_car_text_txt_id);
        avatar = view.findViewById(R.id.profile_img_id);
        car = view.findViewById(R.id.profile_car_img_id);
    }

    private void initButtons(View view) {
        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.VISIBLE);
        }

        Button editProfile = view.findViewById(R.id.edit_profile_btn_id);
        editProfile.setOnClickListener(v -> {
            if (activity != null) {
                activity.loadFragment(FillProfileFragment.newInstance());
            }
        });

        Button addCar = view.findViewById(R.id.add_car_btn_id);
        addCar.setOnClickListener(v -> {
            if (activity != null) {
                activity.loadFragment(CarProfileFragment.newInstance());
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

    @Override
    public void updateData(ApiClient client) {
        String firsName = client.getName();
        String lastName = client.getSurname();

        profileName.setText(
                String.format("%s %s",
                        firsName == null ? "Имя" : firsName,
                        lastName == null ? "Фамилия" : lastName
                )
        );

        String phone = client.getPhone();

        profilePhone.setText("+7");
        profilePhone.append(phone == null ? "Телефон" : phone);
    }

    @Override
    public void updateCarsData(Map<String, ApiCar> cars) {
        // Пока одна машина, если больше нужно передалать View что бы показывал список.
        for (Map.Entry<String, ApiCar> entry : cars.entrySet()) {
            String carNumber = entry.getValue().getRegNum();
            profileCarNumber.setText(carNumber == null ? "Номер авто" : carNumber);
            profileCarDescription.setText("");
            break;
        }
    }
}
