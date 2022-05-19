package com.example.redweekraces.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redweekraces.R;

public class MainActivity extends AppCompatActivity {
    Button prepareRaceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonsClickListeners();
    }

    private void setButtonsClickListeners() {
        this.prepareRaceButton.setOnClickListener(v -> {
            Intent horseRacesIntent = new Intent(this, HorseRacesActivity.class);
            startActivity(horseRacesIntent);
        });
    }
}