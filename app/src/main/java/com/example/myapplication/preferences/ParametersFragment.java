package com.example.myapplication.preferences;


import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.utils.OnBackPressedListener;
import com.example.myapplication.views.MenuFragment;

public class ParametersFragment extends PreferenceFragmentCompat implements OnBackPressedListener {

    private SharedPreferences preferences;
    private AppLangPreference lang;
    private AppRegionsPreference regions;
    private AppGeolocationPreference geo;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.parameters, rootKey);

        lang = findPreference("language");

        if (lang != null) {
            lang.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String value = (String) newValue;
                    SharedPreferences.Editor e = preferences.edit();
                    e.putString(preference.getKey(), value);
                    e.apply();
                    preference.setSummary(value);
                    return true;
                }
            });
        }

        regions = findPreference("regions");

        if (regions != null) {
            regions.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String value = (String) newValue;
                    SharedPreferences.Editor e = preferences.edit();
                    e.putString(preference.getKey(), value);
                    e.apply();
                    preference.setSummary(value);
                    return true;
                }
            });
        }

        geo = findPreference("geolocation");

        if (geo != null) {

            geo.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean value = (boolean) newValue;
                    SharedPreferences.Editor e = preferences.edit();
                    e.putBoolean(preference.getKey(), value);
                    e.apply();
                    ((AppGeolocationPreference) preference).setChecked(value);
                    return true;
                }
            });
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ((MainActivity) getActivity()).getBottomNavigationView().setVisibility(View.GONE);
    }

    private void setViews() {

        final Toolbar toolbar = getActivity().findViewById(R.id.include);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle("Параметры");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.GONE);
                ((MainActivity) getActivity()).loadFragment(MenuFragment.newInstance());
            }
        });

        ImageView img = toolbar.findViewById(R.id.toolbar_title_txt_id);
        img.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        if (v != null) {
            v.setBackground(getResources().getDrawable(R.drawable.background));
        }
        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        setViews();
    }

    @Override
    public boolean onBackPressed() {
        final Toolbar toolbar = getActivity().findViewById(R.id.include);
        toolbar.setVisibility(View.GONE);
        return false;
    }
}
