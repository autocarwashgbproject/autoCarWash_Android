package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ProfileFragment extends MvpAppCompatFragment implements ProfileIF {

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
        // Required empty public constructor
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
        ((MainActivity)getActivity()).getBottomNavigationView().setVisibility(View.VISIBLE);

        carNumber = view.findViewById(R.id.profile_fragment_car_number_text);
        updateClientBtn = view.findViewById(R.id.profile_fragment_button);
        deleteClientBtn = view.findViewById(R.id.profile_fragment_button_delete);
        logoutClientBtn = view.findViewById(R.id.profile_fragment_button_logout);
        updateCarBtn = view.findViewById(R.id.profile_fragment_button_car_update);
        deleteCarBtn = view.findViewById(R.id.profile_fragment_button_car_delete);
        createCarBtn = view.findViewById(R.id.profile_fragment_button_car_create);
        updateClientBtn.setOnClickListener(v -> profilePresenter.updateClient());
        deleteClientBtn.setOnClickListener(v -> profilePresenter.deleteClient());
        logoutClientBtn.setOnClickListener(v -> profilePresenter.logout());
        updateCarBtn.setOnClickListener(v -> profilePresenter.updateCar(carNumber.getText().toString()));
        deleteCarBtn.setOnClickListener(v -> profilePresenter.deleteCar(carNumber.getText().toString()));
        createCarBtn.setOnClickListener(v -> profilePresenter.createCar(carNumber.getText().toString()));

        profilePresenter.getClientFromApi();

        return view;
    }
}
