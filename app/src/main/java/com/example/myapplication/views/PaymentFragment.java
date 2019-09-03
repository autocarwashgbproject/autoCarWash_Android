package com.example.myapplication.views;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.PaymentPresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.myapplication.Const.PICTURE_PREFS;
import static com.example.myapplication.Const.PROFILE_PIC;

public class PaymentFragment extends MvpAppCompatFragment implements PaymentIF {

    @InjectPresenter
    PaymentPresenter presenter;

    private ImageView avatar;

    public PaymentFragment() {
    }

    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_fragment, container, false);

        final MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.GONE);
            activity.findViewById(R.id.include).setVisibility(View.GONE);
        }

        initToolbar(view);
        initSpinners(view);

        EditText cardDate = view.findViewById(R.id.card_date_etxt_id);
        cardDate.addTextChangedListener(cardDateWatcher);

        avatar = view.findViewById(R.id.user_img_id);

        loadCurrentAvatarImg();

        return view;
    }

    private void initSpinners(View view) {
        Context context = getContext();
        if (context != null) {
            Spinner cardTypes = view.findViewById(R.id.choose_card_spinner_id);

            ArrayAdapter<CharSequence> cardAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.card_types,
                    R.layout.spinner_item
            );
            cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cardTypes.setAdapter(cardAdapter);
            cardTypes.setOnItemSelectedListener(new CardTypeSpinnerListener());

            Spinner payment = view.findViewById(R.id.choose_payment_amount_spinner_id);

            ArrayAdapter<CharSequence> paymentAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.payment_amount,
                    R.layout.spinner_item
            );
            paymentAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            payment.setAdapter(paymentAdapter);
            payment.setOnItemSelectedListener(new PaymentSpinnerListener());
        }
    }

    private void initToolbar(View view) {
        final Toolbar toolbar = view.findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> {
            MainActivity activity1 = ((MainActivity) getActivity());
            if (activity1 != null) {
                activity1.loadFragment(MenuFragment.newInstance());
            }
        });
    }

    private TextWatcher cardDateWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 2) {
                s.append("/");
            }
        }
    };

    private void loadCurrentAvatarImg() {
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
        }
    }

    private class CardTypeSpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class PaymentSpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
