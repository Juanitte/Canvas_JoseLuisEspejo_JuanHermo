package com.juanite.canvas_joseluisespejo_juanhermo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.juanite.canvas_joseluisespejo_juanhermo.components.StarsRating;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StarsRating starsRating = findViewById(R.id.starsRating);
    }
}