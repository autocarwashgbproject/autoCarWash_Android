package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
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

public class MainActivity extends MvpAppCompatActivity /*implements MainView*/ {

    public static final int LOAD_PROFILE_PICTURE_CODE = 101;
    public static final int LOAD_CAR_PICTURE_CODE = 110;

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
        return getSharedPreferences("login_data", MODE_PRIVATE).getBoolean("isAuthorized", true); // izmenit' na false
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

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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
        }
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

    public void pickFromGallery(int code) {
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

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }
}
