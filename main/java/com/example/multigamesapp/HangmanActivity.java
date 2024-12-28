package com.example.multigamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class HangmanActivity extends AppCompatActivity {

    private String[] words = {
            "apple", "banana", "orange", "grape", "peach", "cherry", "melon", "berry", "mango", "kiwi",
            "computer", "laptop", "tablet", "monitor", "mouse", "keyboard", "printer", "internet", "software", "hardware",
            "house", "kitchen", "window", "door", "garden", "roof", "floor", "ceiling", "table", "chair",
            "school", "teacher", "student", "homework", "classroom", "library", "book", "pencil", "desk", "blackboard",
            "car", "engine", "tire", "brake", "steering", "mirror", "seatbelt", "dashboard", "horn", "headlight",
            "water", "river", "ocean", "lake", "beach", "island", "boat", "fish", "wave", "sand",
            "music", "guitar", "piano", "drum", "violin", "singer", "song", "dance", "concert", "microphone",
            "city", "street", "building", "park", "shop", "office", "hospital", "police", "station", "hotel",
            "friend", "family", "brother", "sister", "mother", "father", "uncle", "aunt", "cousin", "neighbor",
            "animal", "dog", "cat", "bird", "fish", "tiger", "lion", "elephant", "horse", "rabbit"
    };

    private String currentWord;
    private StringBuilder hiddenWord;
    private int attempts;
    private static final int MAX_ATTEMPTS = 8;

    private TextView wordTextView;
    private TextView attemptsTextView;
    private TextView messageTextView;
    private EditText guessEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        wordTextView = findViewById(R.id.wordTextView);
        attemptsTextView = findViewById(R.id.attemptsTextView);
        messageTextView = findViewById(R.id.messageTextView);
        guessEditText = findViewById(R.id.guessEditText);
        Button guessButton = findViewById(R.id.guessButton);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);

        startNewGame();

        // Set listeners for buttons
        guessButton.setOnClickListener(v -> checkGuess());
        playAgainButton.setOnClickListener(v -> startNewGame());
        backToHomeButton.setOnClickListener(v -> {
            startActivity(new Intent(HangmanActivity.this, MainActivity.class));
            finish();
        });
    }

    private void startNewGame() {
        // Select a random word from the list
        currentWord = words[new Random().nextInt(words.length)];
        hiddenWord = new StringBuilder("_".repeat(currentWord.length()));
        attempts = MAX_ATTEMPTS;
        updateUI();
        messageTextView.setText("");
    }

    private void updateUI() {
        // Display hidden word with attempts remaining
        wordTextView.setText(hiddenWord.toString());
        attemptsTextView.setText("Attempts left: " + attempts);
    }

    private void checkGuess() {
        String guess = guessEditText.getText().toString().toLowerCase().trim();
        guessEditText.setText("");

        // Validate the guess input
        if (guess.isEmpty() || guess.length() > 1) {
            messageTextView.setText("Please enter a single letter.");
            return;
        }

        // Check if the guessed letter is in the current word
        if (currentWord.contains(guess)) {
            // Update the hidden word to reveal the guessed letter
            for (int i = 0; i < currentWord.length(); i++) {
                if (currentWord.charAt(i) == guess.charAt(0)) {
                    hiddenWord.setCharAt(i, guess.charAt(0));
                }
            }
            messageTextView.setText("Correct guess!");
        } else {
            attempts--; // Reduce attempts for an incorrect guess
            messageTextView.setText("Wrong guess! Try again.");
        }

        // Check for win or lose condition
        if (hiddenWord.toString().equals(currentWord)) {
            messageTextView.setText("Congratulations! You've won! The word was: " + currentWord);
            disableInput(); // Stop the game if the player wins
        } else if (attempts <= 0) {
            messageTextView.setText("Game Over! The word was: " + currentWord);
            disableInput(); // Stop the game if the player loses
        }

        updateUI();
    }

    private void disableInput() {
        // Disable input and buttons after the game ends
        guessEditText.setEnabled(false);
        findViewById(R.id.guessButton).setEnabled(false);
    }
}
