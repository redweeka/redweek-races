package com.example.redweekraces.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redweekraces.R;
import com.example.redweekraces.adapters.CurrRacersRecyclerViewAdapter;
import com.example.redweekraces.services.RaceFactory;

import java.util.Arrays;
import java.util.List;

public class HorseRacesActivity extends AppCompatActivity {
    RecyclerView currRacersRecyclerView;
    CurrRacersRecyclerViewAdapter currRacersRecyclerViewAdapter;
    TextView raceResultTextView;
    Button prepareRaceButton;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.currRacersRecyclerView = findViewById(R.id.curr_racers);
        initCurrRacersRecyclerView();
        this.raceResultTextView = findViewById(R.id.race_result);
        this.prepareRaceButton = findViewById(R.id.prepare_new_race);
        this.playButton = findViewById(R.id.play);

        setButtonsClickListeners();
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

    private void updateRacerList(List<String> racersList) {
        this.currRacersRecyclerViewAdapter.setRacersNames(racersList);
    }

    private void setButtonsClickListeners() {
        this.prepareRaceButton.setOnClickListener(v -> {
            raceResultTextView.setText(getString(R.string.race_result));

            String[] racers = RaceFactory.getInstance().prepareRaceRiders();
            updateRacerList(Arrays.asList(racers));

            prepareRaceButton.setVisibility(View.GONE);
            playButton.setVisibility(View.VISIBLE);
        });

        this.playButton.setOnClickListener(v -> {
            String winnerName = RaceFactory.getInstance().race();
            this.raceResultTextView.setText(winnerName);

            playButton.setVisibility(View.GONE);
            prepareRaceButton.setVisibility(View.VISIBLE);
        });
    }
}
