package com.example.redweekraces.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redweekraces.R;
import com.example.redweekraces.adapters.CurrRacersRecyclerViewAdapter;
import com.example.redweekraces.services.RaceFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView currRacersRecyclerView;
    CurrRacersRecyclerViewAdapter currRacersRecyclerViewAdapter;
    TextView raceResultTextView;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.currRacersRecyclerView = findViewById(R.id.curr_racers);
        initCurrRacersRecyclerView();
        this.raceResultTextView = findViewById(R.id.race_result);
        this.playButton = findViewById(R.id.play);

        setPlayOnClickListener();
    }

    private void initCurrRacersRecyclerView() {
        // Make recycler horizontal
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);

        // Line between columns
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this.currRacersRecyclerView.getContext(), DividerItemDecoration.HORIZONTAL);

        // Register adapter
        this.currRacersRecyclerViewAdapter = new CurrRacersRecyclerViewAdapter(this);

        this.currRacersRecyclerView.setLayoutManager(linearLayoutManager);
        this.currRacersRecyclerView.addItemDecoration(dividerItemDecoration);
        this.currRacersRecyclerView.setAdapter(this.currRacersRecyclerViewAdapter);
    }

    private void updateRacerList() {
        // TODO: 10/31/2021 actual prepare racers separate from race
        // Demo racers
        List<String> racersList = new ArrayList<>();
        racersList.add("1");
        racersList.add("16");
        racersList.add("6");
        racersList.add("36");
        racersList.add("46");

        this.currRacersRecyclerViewAdapter.setRacersNames(racersList);
    }

    private void setPlayOnClickListener() {
        playButton.setOnClickListener(v -> {
            updateRacerList();
            String winnerName = RaceFactory.getInstance().race();
            this.raceResultTextView.setText(winnerName);
        });
    }
}