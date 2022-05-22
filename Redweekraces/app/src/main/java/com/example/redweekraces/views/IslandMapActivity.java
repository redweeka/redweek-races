package com.example.redweekraces.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redweekraces.R;
import com.example.redweekraces.adapters.GridMapAdapter;

import java.util.ArrayList;

public class IslandMapActivity extends AppCompatActivity {

    private GridView gridMap;
    private int gridMapColumns = 66;
    private int gridMapSquareScale = 116;
    private int gridMapItems = 1166;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island_map);

        this.gridMap = findViewById(R.id.grid_map);
        this.gridMap.setNumColumns(this.gridMapColumns);

        ViewGroup.LayoutParams layoutParams = this.gridMap.getLayoutParams();
        layoutParams.width = this.gridMapColumns * this.gridMapSquareScale;

        this.gridMap.setLayoutParams(layoutParams);

        fillGridMap();
    }

    private void fillGridMap() {
        ArrayList<TextView> demoList = new ArrayList<>();

        for (int i = 0; i < this.gridMapItems; i++) {
            TextView currItem = new TextView(this);
            currItem.setWidth(this.gridMapSquareScale);
            currItem.setHeight(this.gridMapSquareScale);
            currItem.setText("r" + i);

            if (i % 2 == 0){
                currItem.setBackgroundColor(0xFF0000F6);
            }
            if (i % 3 == 0){
                currItem.setBackgroundColor(0xFF00006F);
            }
            if (i % 5 == 0){
                currItem.setBackgroundColor(0xFF0006FF);
            }
            if (i % 7 == 0){
                currItem.setBackgroundColor(0xFF0060FF);
            }
            if (i % 11 == 0){
                currItem.setBackgroundColor(0xFF0600FF);
            }
            if (i % 13 == 0){
                currItem.setBackgroundColor(0xFF6000FF);
            }
            if (i % 17 == 0){
                currItem.setBackgroundColor(0xF60000FF);
            }
            if (i % 23 == 0){
                currItem.setBackgroundColor(0x6F0000FF);
            }

            demoList.add(currItem);
        }

        GridMapAdapter gridMapAdapter = new GridMapAdapter(this, demoList);
        this.gridMap.setAdapter(gridMapAdapter);
    }
}
