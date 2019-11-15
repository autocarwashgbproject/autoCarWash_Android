package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.presenters.WashPresenter;
import com.squareup.picasso.Picasso;

import java.util.Map;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.PICTURE_PREFS;
import static com.example.myapplication.Const.PROFILE_PIC;

public class WashFragment extends MvpAppCompatFragment implements WashIF {

    @InjectPresenter
    public WashPresenter presenter;

    private ImageView avatar;
    private TextView profileName;
    private TextView profilePhone;
    private TextView address;
    private TextView paidToDate;
    private TextView registrationNr;

    @ProvidePresenter
    public WashPresenter providePresenter() {
        final WashPresenter presenter = new WashPresenter();
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

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

        initViews(view);
        loadCurrentProfilePicture();

        presenter.getClientFromApi();
        presenter.getCarData();

        return view;
    }

    private void initViews(View view) {
        avatar = view.findViewById(R.id.user_avatar_img_id);

        profileName = view.findViewById(R.id.user_name_txt_id);
        profilePhone = view.findViewById(R.id.user_phone_txt_id);
        address = view.findViewById(R.id.address_txt_id);
        paidToDate = view.findViewById(R.id.paid_to_date_txt_id);
        registrationNr = view.findViewById(R.id.registration_nr_txt_id);
    }

    private void loadCurrentProfilePicture() {
        MainActivity activity = (MainActivity) getActivity();
        String uri;
        if (activity != null) {
            uri = activity.loadPicture(PICTURE_PREFS, PROFILE_PIC);
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

    @Override
    public void updateClientData(ApiClient client) {
        String firsName = client.getName();
        String lastName = client.getSurname();

        profileName.setText(
                String.format("%s %s",
                        firsName == null ? "Имя" : firsName,
                        lastName == null ? "Фамилия" : lastName
                )
        );

        String phone = client.getPhone();

        profilePhone.setText("+ " );
        profilePhone.append(phone == null ? "Телефон" : phone);
    }

    @Override
    public void updateCarsData(Map<String, ApiCar> cars) {
        // Пока одна машина, если больше нужно передалать View что бы показывал список.
        for (Map.Entry<String, ApiCar> entry : cars.entrySet()) {
            String carNumber = entry.getValue().getRegNum();
            registrationNr.setText(carNumber == null ? "Номер авто" : carNumber);
        }
    }
}
