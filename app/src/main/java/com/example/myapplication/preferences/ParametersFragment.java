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

        lang = findPreference(getString(R.string.language_preference_text));

        if (lang != null) {
            lang.setOnPreferenceChangeListener((preference, newValue) -> {
                String value = (String) newValue;
                SharedPreferences.Editor e = preferences.edit();
                e.putString(preference.getKey(), value);
                e.apply();
                preference.setSummary(value);
                return true;
            });
        }

        regions = findPreference(getString(R.string.regions_preference_text));

        if (regions != null) {
            regions.setOnPreferenceChangeListener((preference, newValue) -> {
                String value = (String) newValue;
                SharedPreferences.Editor e = preferences.edit();
                e.putString(preference.getKey(), value);
                e.apply();
                preference.setSummary(value);
                return true;
            });
        }

        geo = findPreference(getString(R.string.geolocation_preference_text));

        if (geo != null) {
            geo.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean value = (boolean) newValue;
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(preference.getKey(), value);
                e.apply();
                ((AppGeolocationPreference) preference).setChecked(value);
                return true;
            });
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(activity);
            activity.getBottomNavigationView().setVisibility(View.GONE);
        }
    }

    private void setViews() {

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            final Toolbar toolbar = activity.findViewById(R.id.include);
            toolbar.setVisibility(View.VISIBLE);
            toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            toolbar.setTitle(getString(R.string.parameters_prefs_text));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(v -> {
                toolbar.setVisibility(View.GONE);
                activity.loadFragment(MenuFragment.newInstance());
            });

            ImageView img = toolbar.findViewById(R.id.toolbar_title_txt_id);
            img.setVisibility(View.GONE);
        }
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
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            final Toolbar toolbar = activity.findViewById(R.id.include);
            toolbar.setVisibility(View.GONE);
        }
        return false;
    }
}
