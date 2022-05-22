package com.example.redweekraces.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridMapAdapter extends ArrayAdapter<TextView> {

    public GridMapAdapter(Context context, ArrayList<TextView> resource){
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView currItem = getItem(position);

        if (currItem == null) {
            currItem = new TextView(getContext());
            currItem.setText("i love u");
        }

        return currItem;
    }
}
