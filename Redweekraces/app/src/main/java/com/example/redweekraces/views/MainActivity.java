package com.example.redweekraces.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redweekraces.R;
import com.example.redweekraces.services.RaceFactory;

public class MainActivity extends AppCompatActivity {

    TextView raceResult;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raceResult = findViewById(R.id.race_result);
        play = findViewById(R.id.play);

        setPlayOnClickListener();
    }

    private void setPlayOnClickListener() {
        play.setOnClickListener(v -> {
            String winnerName = RaceFactory.getInstance().race();
            raceResult.setText(winnerName);
        });
    }
}