package com.example.multigamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeActivity extends AppCompatActivity {
    private String currentPlayer;
    private Button[][] buttons = new Button[3][3];
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        status = findViewById(R.id.status);
        Button playAgainButton = findViewById(R.id.play_again_button);
        Button backButton = findViewById(R.id.back_button);

        currentPlayer = "X";

        // Initialize buttons and set click listeners
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new GameClickListener(i, j));
            }
        }

        playAgainButton.setOnClickListener(v -> resetGame());
        backButton.setOnClickListener(v -> finish());
    }

    private class GameClickListener implements View.OnClickListener {
        private final int row;
        private final int col;

        GameClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            if (buttons[row][col].getText().toString().equals("")) {
                buttons[row][col].setText(currentPlayer);

                // Check for a winner or draw after each move
                if (checkForWinner()) {
                    status.setText("Player " + currentPlayer + " wins!");
                    disableButtons(); // Disable buttons after a win
                } else if (isBoardFull()) {
                    status.setText("It's a draw!");
                } else {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X"; // Switch player
                    status.setText("Player " + currentPlayer + "'s turn");
                }
            }
        }
    }

    private boolean checkForWinner() {
        // Check rows, columns and diagonals for a winner
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().toString().equals(currentPlayer) &&
                    buttons[i][1].getText().toString().equals(currentPlayer) &&
                    buttons[i][2].getText().toString().equals(currentPlayer)) {
                return true;
            }
            if (buttons[0][i].getText().toString().equals(currentPlayer) &&
                    buttons[1][i].getText().toString().equals(currentPlayer) &&
                    buttons[2][i].getText().toString().equals(currentPlayer)) {
                return true;
            }
        }

        // Check diagonals
        return (buttons[0][0].getText().toString().equals(currentPlayer) &&
                buttons[1][1].getText().toString().equals(currentPlayer) &&
                buttons[2][2].getText().toString().equals(currentPlayer)) ||
                (buttons[0][2].getText().toString().equals(currentPlayer) &&
                        buttons[1][1].getText().toString().equals(currentPlayer) &&
                        buttons[2][0].getText().toString().equals(currentPlayer));
    }

    private boolean isBoardFull() {
        // Check if the board is full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().equals("")) {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // No empty cells, board is full
    }

    private void disableButtons() {
        // Disable all buttons after a win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        currentPlayer = "X"; // Reset to player X
        status.setText("Player X's turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear button text
                buttons[i][j].setEnabled(true); // Re-enable buttons
            }
        }
    }
}
