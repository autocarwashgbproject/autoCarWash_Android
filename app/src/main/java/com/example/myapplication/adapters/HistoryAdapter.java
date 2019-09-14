package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.cache.Wash;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<Wash> histories = new ArrayList<>();

    public List<Wash> getHistories() {
        return histories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        //holder.image.setImageResource(histories.get(position).getImageResRef());
        holder.description.setText("" + histories.get(position).getCar());
        holder.date.setText(new SimpleDateFormat("DD.MMM.YYYY HH:mm", Locale.getDefault()).format(new Date(histories.get(position).getTimestamp())));
        holder.price.setText("" + histories.get(position).getWash());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView description;
        private TextView date;
        private TextView price;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.history_item_image);
            description = itemView.findViewById(R.id.history_item_description);
            date = itemView.findViewById(R.id.history_item_date);
            price = itemView.findViewById(R.id.history_item_price);
        }
    }
}
