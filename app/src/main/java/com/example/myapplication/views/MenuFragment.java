package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.myapplication.App;
import com.example.myapplication.MainActivity;
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.preferences.ParametersFragment;
import com.example.myapplication.R;
import com.example.myapplication.presenters.MenuPresenter;


public class MenuFragment extends MvpAppCompatFragment implements MenuIF, View.OnClickListener {

    @InjectPresenter
    MenuPresenter presenter;

    @ProvidePresenter
    public MenuPresenter providePresenter() {
        final MenuPresenter presenter = new MenuPresenter();
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    private TextView profileName;
    private TextView profilePhone;

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initButtons(View view) {
        Button payment = view.findViewById(R.id.payment_btn_id);
        payment.setOnClickListener(this);
        Button history = view.findViewById(R.id.history_btn_id);
        history.setOnClickListener(this);
        Button settings = view.findViewById(R.id.settings_btn_id);
        settings.setOnClickListener(this);
        Button help = view.findViewById(R.id.help_btn_id);
        help.setOnClickListener(this);
        Button about = view.findViewById(R.id.about_btn_id);
        about.setOnClickListener(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        profileName = view.findViewById(R.id.account_name_txt_id);
        profilePhone = view.findViewById(R.id.account_phone_txt_id);

        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.VISIBLE);
        }

        initButtons(view);

        presenter.updateProfile();
        return view;
    }

    @Override
    public void updateProfile(ApiClient client) {
        String firsName = client.getName();
        String patronymic = client.getPatronymic();
        String lastName = client.getSurname();
        profileName.setText(
                String.format(
                        "%s %s %s",
                        firsName == null ? "Имя" : firsName,
                        patronymic == null ? "Отчество" : patronymic,
                        lastName == null ? "Фамилия" : lastName
                )
        );
        String phone = client.getPhone();
        profilePhone.setText(String.format("+ %s", phone == null ? "Телефон" : phone));
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            switch (viewId) {
                case R.id.payment_btn_id:
                    activity.loadFragment(PaymentFragment.newInstance());
                    break;
                case R.id.history_btn_id:
                    activity.loadFragment(HistoryFragment.newInstance());
                    break;
                case R.id.settings_btn_id:
                    activity.loadFragment(new ParametersFragment());
                    break;
                case R.id.help_btn_id:
                    /*activity.loadFragment(HelpFragment.newInstance());*/
                    break;
                case R.id.about_btn_id:
                    activity.loadFragment(AboutFragment.newInstance());
                    break;
            }
        }
    }
}
