package com.example.multigamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RockPaperScissorsActivity extends AppCompatActivity {

    private TextView status;
    private TextView result;
    private String currentPlayer;
    private String playerXChoice;
    private String playerOChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rockpaperscissors);

        status = findViewById(R.id.status);
        result = findViewById(R.id.result);
        Button buttonRock = findViewById(R.id.button_player_x);
        Button buttonPaper = findViewById(R.id.button_player_y);
        Button buttonScissors = findViewById(R.id.button_player_z);
        Button buttonPlayAgain = findViewById(R.id.button_play_again);
        Button buttonBack = findViewById(R.id.button_back);

        currentPlayer = "X";

        buttonRock.setOnClickListener(v -> makeChoice("Rock"));
        buttonPaper.setOnClickListener(v -> makeChoice("Paper"));
        buttonScissors.setOnClickListener(v -> makeChoice("Scissors"));

        buttonPlayAgain.setOnClickListener(v -> resetGame());
        buttonBack.setOnClickListener(v -> finish()); // Go back to main menu
    }

    private void makeChoice(String choice) {
        if (currentPlayer.equals("X")) {
            playerXChoice = choice;
            currentPlayer = "O";
            status.setText("Player O's turn");
        } else {
            playerOChoice = choice;
            determineWinner();
        }
    }

    private void determineWinner() {
        String winner;
        if (playerXChoice.equals(playerOChoice)) {
            winner = "It's a tie!";
        } else if ((playerXChoice.equals("Rock") && playerOChoice.equals("Scissors")) ||
                (playerXChoice.equals("Paper") && playerOChoice.equals("Rock")) ||
                (playerXChoice.equals("Scissors") && playerOChoice.equals("Paper"))) {
            winner = "Player X wins!";
        } else {
            winner = "Player O wins!";
        }
        result.setText(winner);
        status.setText(""); // Clear the status message
    }

    private void resetGame() {
        playerXChoice = null;
        playerOChoice = null;
        currentPlayer = "X";
        status.setText("Player X's turn");
        result.setText("");
    }
}
