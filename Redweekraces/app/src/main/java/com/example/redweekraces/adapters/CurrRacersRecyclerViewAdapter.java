package com.example.redweekraces.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.redweekraces.R;

import java.util.ArrayList;
import java.util.List;

public class CurrRacersRecyclerViewAdapter extends Adapter<CurrRacersRecyclerViewAdapter.currRacerItem> {

    private List<String> racersNames;
    private LayoutInflater layoutInflater;

    public CurrRacersRecyclerViewAdapter(Context context){
        this.layoutInflater = LayoutInflater.from(context);
        this.racersNames = new ArrayList<>();
    }

    public void setRacersNames(List<String> racersNames) {
        this.racersNames = racersNames;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public currRacerItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.curr_racers_item, parent, false);
        return new currRacerItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull currRacerItem holder, int position) {
        String currRacerName = this.racersNames.get(position);
        holder.racerName.setText(currRacerName);
    }

    @Override
    public int getItemCount() {
        return this.racersNames.size();
    }

    public class currRacerItem extends ViewHolder {
        TextView racerName;

        public currRacerItem(@NonNull View itemView) {
            super(itemView);
            racerName = itemView.findViewById(R.id.curr_racer_name);
        }
    }
}
