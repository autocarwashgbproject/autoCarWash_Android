package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.preferences.ParametersFragment;
import com.example.myapplication.R;
import com.example.myapplication.adapters.BottomMenuItemsAdapter;
import com.example.myapplication.adapters.Menu;

import java.util.Arrays;
import java.util.List;

public class MenuFragment extends ListFragment {

    private final List<Menu> menuList = Arrays.asList(
            new Menu(R.drawable.ic_payment, getString(R.string.payment_menu_text)),
            new Menu(R.drawable.menu_bubbles_item, getString(R.string.history_menu_text)),
            new Menu(R.drawable.menu_settings_item, getString(R.string.parameters_menu_text)),
            new Menu(R.drawable.menu_help_item, getString(R.string.help_menu_text)),
            new Menu(R.drawable.menu_about_item, getString(R.string.about_menu_text))
    );

    private BottomMenuItemsAdapter adapter;

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            adapter = new BottomMenuItemsAdapter(activity, menuList);
        }
        setListAdapter(adapter);

        getListView().setDivider(null);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        int img = ((Menu) l.getAdapter().getItem(position)).getImg();
        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            switch (img) {
                case R.drawable.menu_about_item:
                    activity.loadFragment(AboutFragment.newInstance());
                    break;
                case R.drawable.menu_settings_item:
                    activity.loadFragment(new ParametersFragment());
                    break;
                case R.drawable.ic_payment:
                    activity.loadFragment(PaymentFragment.newInstance());
                    break;
                case R.drawable.menu_bubbles_item:
                    activity.loadFragment(HistoryFragment.newInstance());
                    break;
            }
        }
    }
}
