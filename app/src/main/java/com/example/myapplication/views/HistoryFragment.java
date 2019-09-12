package com.example.myapplication.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.myapplication.App;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.HistoryAdapter;
import com.example.myapplication.model.cache.Wash;
import com.example.myapplication.presenters.HistoryPresenter;

import java.util.List;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryIF {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    @InjectPresenter
    public HistoryPresenter historyPresenter;
    private Button addBtn;

    @ProvidePresenter
    public HistoryPresenter providePresenter() {
        final HistoryPresenter historyPresenter = new HistoryPresenter();
        App.getInstance().getAppComponent().inject(historyPresenter);
        return historyPresenter;
    }


    public HistoryFragment() {
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        historyPresenter.getHistoryList();
        addBtn = view.findViewById(R.id.history_button_add);
        addBtn.setOnClickListener(v -> {
            historyPresenter.addWash();
        });

        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.getBottomNavigationView().setVisibility(View.VISIBLE);
        }
        recyclerView = view.findViewById(R.id.list_history);
        adapter = new HistoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void updateList(List<Wash> washes) {
        adapter.getHistories().clear();
        adapter.getHistories().addAll(washes);
        adapter.notifyDataSetChanged();
    }
}
