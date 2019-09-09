package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.myapplication.utils.OnBackPressedListener;
import com.example.myapplication.views.CarProfileFragment;
import com.example.myapplication.views.FillProfileFragment;
import com.example.myapplication.views.HistoryFragment;
import com.example.myapplication.views.MenuFragment;
import com.example.myapplication.views.ProfileFragment;
import com.example.myapplication.views.RegisterFragment;
import com.example.myapplication.views.WashFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.myapplication.Const.LOAD_CAR_PICTURE_CODE;
import static com.example.myapplication.Const.LOAD_PROFILE_PICTURE_CODE;

public class MainActivity extends MvpAppCompatActivity /*implements MainView*/ {

    private static final int PERMISSION_REQUEST_CODE = 111;
    public static final String LOGIN_DATA_PREFS = "login_data";
    public static final String AUTHORIZATION_STATUS = "isAuthorized";

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_menu_id);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (isLoggedIn()) {
            // Загрузить фрагмент главного экрана
            loadFragment(WashFragment.newInstance());
            bottomNavigationView.setVisibility(View.VISIBLE);
            bottomNavigationView.setSelectedItemId(R.id.wash);
        } else {
            bottomNavigationView.setVisibility(View.GONE);
            loadFragment(RegisterFragment.newInstance());
        }
    }

    // проверяем в SharedPreferences, если есть данные о логине,
    // возвращяем true, если нет false.
    public boolean isLoggedIn() {
        return getSharedPreferences(LOGIN_DATA_PREFS, MODE_PRIVATE)
                .getBoolean(AUTHORIZATION_STATUS, false);
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_id, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.wash:
                fragment = WashFragment.newInstance();
                break;
            case R.id.history:
                fragment = HistoryFragment.newInstance();
                break;
            case R.id.profile:
                fragment = ProfileFragment.newInstance();
                break;
            case R.id.menu:
                fragment = MenuFragment.newInstance();
                break;
        }
        return MainActivity.this.loadFragment(fragment);
    };

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_id);
        if (!(fragment instanceof OnBackPressedListener)
                || !((OnBackPressedListener) fragment).onBackPressed()) {
            super.onBackPressed();
        } else {
            ((OnBackPressedListener) fragment).onBackPressed();
        }
    }

    public void savePicture(String name, String key, String uri) {
        SharedPreferences pref = getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, uri);
        editor.apply();
    }

    public String loadPicture(String name, String key) {
        SharedPreferences pref = getSharedPreferences(name, MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public void pickFromGallery(int code) {
        requestPermissions();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri;
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case LOAD_PROFILE_PICTURE_CODE:
                    uri = data.getData();
                    loadFragment(FillProfileFragment.newInstance(String.valueOf(uri)));
                    break;
                case LOAD_CAR_PICTURE_CODE:
                    uri = data.getData();
                    loadFragment(CarProfileFragment.newInstance(String.valueOf(uri)));
                    break;
            }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 2 && (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                requestPermissions();
            }
        }
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },
                        PERMISSION_REQUEST_CODE
                );
            }
        }
    }

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    public void changeAuthorizationStatus(boolean status) {
        SharedPreferences pref = getSharedPreferences(LOGIN_DATA_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(AUTHORIZATION_STATUS, status);
        editor.apply();
    }
}
