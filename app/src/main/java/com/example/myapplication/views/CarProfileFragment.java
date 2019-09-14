package com.example.myapplication.views;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.myapplication.presenters.CarProfilePresenter;
import com.squareup.picasso.Picasso;

import java.util.Map;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.CAR_PIC;
import static com.example.myapplication.Const.LOAD_CAR_PICTURE_CODE;
import static com.example.myapplication.Const.PICTURE_PREFS;

public class CarProfileFragment extends MvpAppCompatFragment implements CarProfileIF {

    @InjectPresenter
    CarProfilePresenter presenter;

    private ImageView autoImg;
    private ImageView addImgBtn;
    private EditText carChar1;
    private EditText carChar2;
    private EditText carChar3;
    private EditText carChar4;
    private EditText carChar5;
    private EditText carChar6;
    private EditText carChar7;
    private Button addCar;
    private TextView deleteData;


    @ProvidePresenter
    public CarProfilePresenter providePresenter() {
        final CarProfilePresenter presenter = new CarProfilePresenter();
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    public CarProfileFragment() {
    }

    public static CarProfileFragment newInstance() {
        return new CarProfileFragment();
    }

    public static CarProfileFragment newInstance(Boolean newReg) {
        CarProfileFragment pf = new CarProfileFragment();
        Bundle args = new Bundle();
        args.putBoolean("NewRegistration", newReg);
        pf.setArguments(args);
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

        initViews(view);
        loadCurrentCarImg();

        presenter.getCarData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCurrentCarImg();
    }

    private TextWatcher codeNumsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            MainActivity activity = ((MainActivity) getActivity());
            TextView text = null;
            if (activity != null) {
                text = (TextView) getActivity().getCurrentFocus();
            }

            if (text != null && text.length() > 0) {
                View next = text.focusSearch(View.FOCUS_RIGHT);
                if (next != null)
                    next.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void initViews(View view) {
        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
            activity.findViewById(R.id.include).setVisibility(View.GONE);
        }
        carChar1 = view.findViewById(R.id.car_number_1);
        carChar1.addTextChangedListener(codeNumsWatcher);
        carChar2 = view.findViewById(R.id.car_number_2);
        carChar2.addTextChangedListener(codeNumsWatcher);
        carChar3 = view.findViewById(R.id.car_number_3);
        carChar3.addTextChangedListener(codeNumsWatcher);
        carChar4 = view.findViewById(R.id.car_number_4);
        carChar4.addTextChangedListener(codeNumsWatcher);
        carChar5 = view.findViewById(R.id.car_number_5);
        carChar5.addTextChangedListener(codeNumsWatcher);
        carChar6 = view.findViewById(R.id.car_number_6);
        carChar6.addTextChangedListener(codeNumsWatcher);
        carChar7 = view.findViewById(R.id.car_number_7);
        addCar = view.findViewById(R.id.add_auto_btn_id);
        addCar.setOnClickListener(v -> {
            presenter.createCar(getCarNumber());
            if (activity != null) {
                if (checkNew()) {
                    activity.loadFragment(WashFragment.newInstance());
                } else {
                    activity.loadFragment(ProfileFragment.newInstance());
                }
            }
        });
        deleteData = view.findViewById(R.id.delete_data_txt_id);
        deleteData.setOnClickListener(v -> {
            presenter.deleteCar(getCarNumber());
            if (activity != null) {
                activity.loadFragment(ProfileFragment.newInstance());
            }
        });
        autoImg = view.findViewById(R.id.auto_profile_img_id);
        addImgBtn = view.findViewById(R.id.add_btn_img_id);
        addImgBtn.setOnClickListener(v -> {
            if (activity != null) {
                activity.pickFromGallery(LOAD_CAR_PICTURE_CODE, this);
            }
        });
    }

    private Boolean checkNew() {
        if (getArguments() == null) {
            return false;
        }
        if (!getArguments().containsKey("NewRegistration")) {
            return false;
        }
        return getArguments().getBoolean("NewRegistration");
    }

    private String getCarNumber() {
        return carChar1.getText().toString()
                + carChar2.getText().toString()
                + carChar3.getText().toString()
                + carChar4.getText().toString()
                + carChar5.getText().toString()
                + carChar6.getText().toString()
                + carChar7.getText().toString();
    }

    private void loadCurrentCarImg() {
        MainActivity activity = (MainActivity) getActivity();
        String uri = activity.loadPicture(PICTURE_PREFS, CAR_PIC);
        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(autoImg);
        }
    }


    @Override
    public void updateCarsData(Map<String, ApiCar> cars) {
        for (Map.Entry<String, ApiCar> entry : cars.entrySet()) {
            String mCarNumber = entry.getValue().getRegNum();
            setCharsOnView(mCarNumber);
            break;
        }
    }

    public void setCharsOnView(String mCarNumber) {
        if (mCarNumber != null) {

            carChar1.getText().insert(0, String.valueOf(mCarNumber.charAt(0)));
            carChar2.getText().insert(0, String.valueOf(mCarNumber.charAt(1)));
            carChar3.getText().insert(0, String.valueOf(mCarNumber.charAt(2)));
            carChar4.getText().insert(0, String.valueOf(mCarNumber.charAt(3)));
            carChar5.getText().insert(0, String.valueOf(mCarNumber.charAt(4)));
            carChar6.getText().insert(0, String.valueOf(mCarNumber.charAt(5)));
            carChar7.getText().insert(0, mCarNumber, 6, mCarNumber.length());
        }
    }
}
