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
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.presenters.FillProfilePresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.IMG_URI;
import static com.example.myapplication.Const.LOAD_PROFILE_PICTURE_CODE;
import static com.example.myapplication.Const.PICTURE_PREFS;
import static com.example.myapplication.Const.PROFILE_PIC;


public class FillProfileFragment extends MvpAppCompatFragment implements FillProfileIF {

    private ImageView avatar;
    private EditText profileName;
    private EditText profileLastName;
    private EditText profileFatherName;
    private EditText profileBirthDate;
    private EditText profileEmail;
    private EditText profilePhone;

    @InjectPresenter
    FillProfilePresenter presenter;

    @ProvidePresenter
    public FillProfilePresenter providePresenter() {
        final FillProfilePresenter presenter = new FillProfilePresenter();
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    public FillProfileFragment() {
    }

    public static FillProfileFragment newInstance(String uri) {
        FillProfileFragment pf = new FillProfileFragment();
        if (!uri.isEmpty()) {
            Bundle args = new Bundle();
            args.putString(IMG_URI, uri);
            pf.setArguments(args);
        }
        return pf;
    }

    public static FillProfileFragment newInstance(Boolean newReg) {
        FillProfileFragment pf = new FillProfileFragment();
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
        View view = inflater.inflate(R.layout.fill_profile_fragment, container, false);

        initViews(view);

        final MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
            activity.findViewById(R.id.include).setVisibility(View.GONE);
        }

        initButtons(view);
        loadCurrentAvatarImg();
        presenter.getClientFromApi();

        return view;
    }

    private void getDataFromFields() {
        String firstName = profileName.getText().toString().trim();
        String lastName = profileLastName.getText().toString().trim();
        String fathersName = profileFatherName.getText().toString().trim();
        // int birthdate... формат даты?
        String mail = profileEmail.getText().toString().trim();
        String phoneNr = profilePhone.getText().toString().trim();

        ApiClient client = new ApiClient();
        if (!firstName.isEmpty()) {
            client.setName(firstName);
        }

        if (!lastName.isEmpty()) {
            client.setSurname(lastName);
        }

        if (!fathersName.isEmpty()) {
            client.setPatronymic(fathersName);
        }

        if (!mail.isEmpty()) {
            client.setEmail(mail);
        }

        if (!phoneNr.isEmpty()) {
            client.setPhone(phoneNr);
        }

        presenter.fillProfileData(client);
    }

    private void initViews(View view) {
        avatar = view.findViewById(R.id.profile_avatar_img_id);
        profileName = view.findViewById(R.id.name_etxt_id);
        profileLastName = view.findViewById(R.id.last_name_etxt_id);
        profileFatherName = view.findViewById(R.id.father_name_etxt_id);
        profileBirthDate = view.findViewById(R.id.birth_date_etxt_id);
        profileEmail = view.findViewById(R.id.email_etxt_id);
        profilePhone = view.findViewById(R.id.phone_etxt_id);
    }

    private void initButtons(View view) {
        MainActivity activity = (MainActivity) getActivity();

        ImageView addPhoto = view.findViewById(R.id.add_photo_btn_img_id);
        addPhoto.setOnClickListener(v -> {
            if (activity != null) {
                activity.pickFromGallery(LOAD_PROFILE_PICTURE_CODE);
            }
        });

        Button save = view.findViewById(R.id.save_profile_btn_id);
        save.setOnClickListener(v -> {
            getDataFromFields();
            if (activity != null) {
                if (checkNew()) {
                    activity.loadFragment(CarProfileFragment.newInstance(true));
                } else {
                    activity.loadFragment(ProfileFragment.newInstance());
                }
            }
        });

        TextView deleteProfile = view.findViewById(R.id.delete_profile_txt_id);
        deleteProfile.setOnClickListener(v -> {
            presenter.deleteProfile();
            if (activity != null) {
                activity.changeAuthorizationStatus(false);
                activity.loadFragment(RegisterFragment.newInstance());
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

    private void loadCurrentAvatarImg() {
        MainActivity activity = (MainActivity) getActivity();
        String uri = null;
        if (getArguments() != null && getArguments().containsKey(IMG_URI)) {
            uri = getArguments().getString(IMG_URI);
            if (activity != null) {
                activity.savePicture(PICTURE_PREFS, PROFILE_PIC, uri);
            }
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(avatar);
        } else if (activity != null) {
            uri = activity.loadPicture(PICTURE_PREFS, PROFILE_PIC);
        }
        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(avatar);
        }
    }

    @Override
    public void updateData(ApiClient client) {
        String mName = client.getName();
        String mSurname = client.getSurname();
        String mFatherName = client.getPatronymic();
        int mBirthDate = client.getBirthday();
        String mEmail = client.getEmail();
        String mPhone = client.getPhone();

        profileName.setText(mName);
        profileLastName.setText(mSurname);
        profileFatherName.setText(mFatherName);
        if (mBirthDate != 0) profileBirthDate.setText(String.valueOf(mBirthDate));
        profileEmail.setText(mEmail);
        profilePhone.setText("+");
        profilePhone.append(mPhone == null ? "Телефон" : mPhone);
    }
}
