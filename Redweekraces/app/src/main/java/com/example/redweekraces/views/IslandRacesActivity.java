package com.example.redweekraces.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redweekraces.R;

public class IslandRacesActivity extends AppCompatActivity {
    private Button islandMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island_races);
        this.islandMapButton = findViewById(R.id.islandMap);

        setButtonsClickListeners();
    }

    private void setButtonsClickListeners() {
        this.islandMapButton.setOnClickListener(view -> {
            Intent islandMapIntent = new Intent(this, IslandMapActivity.class);
            startActivity(islandMapIntent);
        });
    }
}
