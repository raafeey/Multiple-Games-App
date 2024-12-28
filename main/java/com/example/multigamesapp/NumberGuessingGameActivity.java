package com.example.multigamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class NumberGuessingGameActivity extends AppCompatActivity {

    private int targetNumber;
    private int attempts;
    private static final int MAX_ATTEMPTS = 8;

    private TextView resultTextView;
    private EditText inputGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberguessing);

        resultTextView = findViewById(R.id.resultTextView);
        inputGuess = findViewById(R.id.inputGuess);
        Button guessButton = findViewById(R.id.guessButton);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);

        resetGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NumberGuessingGameActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void resetGame() {
        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        resultTextView.setText("Guess a number between 1 and 100.");
        inputGuess.setText("");
    }

    private void checkGuess() {
        if (attempts < MAX_ATTEMPTS) {
            String guessStr = inputGuess.getText().toString();
            if (!guessStr.isEmpty()) {
                int guess = Integer.parseInt(guessStr);
                attempts++;

                if (guess < targetNumber) {
                    resultTextView.setText("Too low! Attempts left: " + (MAX_ATTEMPTS - attempts));
                } else if (guess > targetNumber) {
                    resultTextView.setText("Too high! Attempts left: " + (MAX_ATTEMPTS - attempts));
                } else {
                    resultTextView.setText("Congratulations! You guessed it in " + attempts + " attempts.");
                }
            }
        } else {
            resultTextView.setText("You've used all attempts! The correct number was " + targetNumber);
        }
        inputGuess.setText("");
    }
}
