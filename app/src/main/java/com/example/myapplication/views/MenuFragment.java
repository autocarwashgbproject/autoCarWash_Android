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

public class MenuFragment extends ListFragment {

    // TODO: 2019-08-08 Presenter
    private BottomMenuItemsAdapter adapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new BottomMenuItemsAdapter(getActivity(), Menu.menuList);
        setListAdapter(adapter);

        getListView().setDivider(null);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        ((MainActivity) getActivity()).getBottomNavigationView().setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        int img = ((Menu) l.getAdapter().getItem(position)).getImg();

        switch (img) {
            case R.drawable.menu_about_item:
                ((MainActivity) getActivity()).loadFragment(AboutFragment.newInstance());
                break;
            case R.drawable.menu_settings_item:
                ((MainActivity) getActivity()).loadFragment(new ParametersFragment());
                break;
            case R.drawable.ic_payment:
                ((MainActivity) getActivity()).loadFragment(PaymentFragment.newInstance());
                break;
        }
    }
}
