package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.myapplication.views.HistoryFragment;
import com.example.myapplication.views.MenuFragment;
import com.example.myapplication.views.ProfileFragment;
import com.example.myapplication.views.RegisterFragment;
import com.example.myapplication.views.WashFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends MvpAppCompatActivity /*implements MainView*/ {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_menu_id);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setSelectedItemId(R.id.profile);

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

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }
}
