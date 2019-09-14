package com.example.myapplication.views;

import android.app.DatePickerDialog;
import android.content.Context;
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
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.presenters.FillProfilePresenter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());


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

    public static FillProfileFragment newInstance() {
        return new FillProfileFragment();
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
        pickDate();
        presenter.getClientFromApi();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCurrentAvatarImg();
    }

    private void getDataFromFields() {
        String firstName = profileName.getText().toString().trim();
        String lastName = profileLastName.getText().toString().trim();
        String fathersName = profileFatherName.getText().toString().trim();
        String birthDate = profileBirthDate.getText().toString().trim();
        String mail = profileEmail.getText().toString().trim();
        String phoneNr = profilePhone.getText().toString().trim().substring(2);

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
        if (!birthDate.isEmpty()) {
            try {
                client.setBirthday(sdf.parse(birthDate).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        initPhoneField(view);
    }

    private void initButtons(View view) {
        MainActivity activity = (MainActivity) getActivity();

        ImageView addPhoto = view.findViewById(R.id.add_photo_btn_img_id);
        addPhoto.setOnClickListener(v -> {
            if (activity != null) {
                activity.pickFromGallery(LOAD_PROFILE_PICTURE_CODE, this);
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
        String uri = activity.loadPicture(PICTURE_PREFS, PROFILE_PIC);
        if (uri != null) {
            Picasso.get()
                    .load(uri)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .into(avatar);
        }
    }

    private void initPhoneField(View view) {
        profilePhone = view.findViewById(R.id.phone_etxt_id);
        profilePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("+7")) {
                    if (s.toString().startsWith("+")) {
                        s.insert(1, "7");
                    } else {
                        s.insert(0, "+7");
                    }
                }
            }
        });
    }

    private void pickDate() {
        final Calendar myCalendar = Calendar.getInstance();
        Context context = getContext();
        profileBirthDate.setOnClickListener(v -> {
            DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                profileBirthDate.setText(sdf.format(myCalendar.getTime()));
            };
            new DatePickerDialog(context, date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH))
                    .show();
        });
    }

    @Override
    public void updateData(ApiClient client) {
        String mName = client.getName();
        String mSurname = client.getSurname();
        String mFatherName = client.getPatronymic();
        long mBirthDate = client.getBirthday();
        String mEmail = client.getEmail();
        String mPhone = client.getPhone();

        profileName.setText(mName);
        profileLastName.setText(mSurname);
        profileFatherName.setText(mFatherName);
        if (mBirthDate != 0) profileBirthDate.setText(sdf.format(mBirthDate));
        profileEmail.setText(mEmail);
        profilePhone.setText("+7");
        profilePhone.append(mPhone == null ? "Телефон" : mPhone);
    }

}
