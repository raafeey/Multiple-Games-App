package com.example.multigamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ticTacToeButton = findViewById(R.id.button_tic_tac_toe);
        Button rockPaperScissorsButton = findViewById(R.id.button_rock_paper_scissors);
        Button numberGuessingButton = findViewById(R.id.button_number_guessing);
        Button hangmanButton = findViewById(R.id.button_hangman); // New button for Hangman game

        ticTacToeButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TicTacToeActivity.class)));
        rockPaperScissorsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RockPaperScissorsActivity.class)));
        numberGuessingButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NumberGuessingGameActivity.class)));
        hangmanButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HangmanActivity.class))); // Launch Hangman
    }
}
