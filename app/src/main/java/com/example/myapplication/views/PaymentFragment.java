package com.example.myapplication.views;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.presenters.PaymentPresenter;

public class PaymentFragment extends MvpAppCompatFragment implements PaymentIF {

    @InjectPresenter
    PaymentPresenter presenter;

    public PaymentFragment() {
        // Required empty public constructor
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

        ((MainActivity) getActivity()).getBottomNavigationView().setVisibility(View.GONE);
        getActivity().findViewById(R.id.include).setVisibility(View.GONE);

        final Toolbar toolbar = view.findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(MenuFragment.newInstance());
            }
        });

        EditText cardDate = view.findViewById(R.id.card_date_etxt_id);
        cardDate.addTextChangedListener(cardDateWatcher);

        Spinner cardTypes = view.findViewById(R.id.choose_card_spinner_id);
        ArrayAdapter<CharSequence> cardAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.card_types, R.layout.spinner_item);
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardTypes.setAdapter(cardAdapter);
        cardTypes.setOnItemSelectedListener(new CardTypeSpinnerListener());

        Spinner payment = view.findViewById(R.id.choose_payment_amount_spinner_id);
        ArrayAdapter<CharSequence> paymentAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.payment_amount, R.layout.spinner_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment.setAdapter(paymentAdapter);
        payment.setOnItemSelectedListener(new PaymentSpinnerListener());

        return view;
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
