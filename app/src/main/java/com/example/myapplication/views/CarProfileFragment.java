package com.example.myapplication.views;


import android.os.Bundle;
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
import com.example.myapplication.presenters.CarProfilePresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.CAR_PIC;
import static com.example.myapplication.Const.IMG_URI;
import static com.example.myapplication.Const.LOAD_CAR_PICTURE_CODE;
import static com.example.myapplication.Const.PICTURE_PREFS;

public class CarProfileFragment extends MvpAppCompatFragment implements CarProfileIF {

    @InjectPresenter
    CarProfilePresenter presenter;

    private ImageView autoImg;
    private ImageView addImgBtn;
    private EditText carNumber;
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

    public static CarProfileFragment newInstance(String uri) {
        CarProfileFragment pf = new CarProfileFragment();
        if (uri != null) {
            Bundle args = new Bundle();
            args.putString(IMG_URI, uri);
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

        initViews(view);

        loadCurrentCarImg();

        return view;
    }

    private void initViews(View view) {
        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
            activity.findViewById(R.id.include).setVisibility(View.GONE);
        }
        carNumber = view.findViewById(R.id.car_nr_etxt_id);
        addCar = view.findViewById(R.id.add_auto_btn_id);
        addCar.setOnClickListener(v -> {
            presenter.createCar("a777aa77"/*carNumber.getText().toString()*/);
        });
        deleteData = view.findViewById(R.id.delete_data_txt_id);
        deleteData.setOnClickListener(v -> {

        });
        autoImg = view.findViewById(R.id.auto_profile_img_id);
        addImgBtn = view.findViewById(R.id.add_btn_img_id);
        addImgBtn.setOnClickListener(v -> {
            if (activity != null) {
                activity.pickFromGallery(LOAD_CAR_PICTURE_CODE);
            }
        });
    }

    private void loadCurrentCarImg() {
        MainActivity activity = (MainActivity) getActivity();
        String uri = null;
        if (getArguments() != null) {
            uri = getArguments().getString(IMG_URI);
            if (activity != null) {
                activity.savePicture(PICTURE_PREFS, CAR_PIC, uri);
            }
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(autoImg);
        } else if (activity != null) {
            uri = activity.loadPicture(PICTURE_PREFS, CAR_PIC);
        }
        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(autoImg);
        }
    }
}
