package com.example.redweekraces.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redweekraces.R;

public class MainActivity extends AppCompatActivity {
    private Button horseRaces;
    private Button islandRaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.horseRaces = findViewById(R.id.horse_races);
        this.islandRaces = findViewById(R.id.island_races);

        setButtonsClickListeners();
    }

    private void setButtonsClickListeners() {
        this.horseRaces.setOnClickListener(v -> {
            Intent horseRacesIntent = new Intent(this, HorseRacesActivity.class);
            startActivity(horseRacesIntent);
        });

        this.islandRaces.setOnClickListener(v -> {
            Intent islandRacesIntent = new Intent(this, IslandRacesActivity.class);
            startActivity(islandRacesIntent);
        });
    }
}