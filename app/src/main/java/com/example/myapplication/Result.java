package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Retrieve the user's name and score from the intent
        String userName = getIntent().getStringExtra("USER_NAME");
        int score = getIntent().getIntExtra("SCORE", 0);

        // Initialize the views
        TextView congratulationsTextView = findViewById(R.id.congratulations_text);
        TextView scoreTextView = findViewById(R.id.score_text);
        Button takeNewQuizButton = findViewById(R.id.take_new_quiz_button);
        Button finishButton = findViewById(R.id.finish_button);

        // Display the results

        congratulationsTextView.setText(getString(R.string.congratulations_message, userName));
        scoreTextView.setText(getString(R.string.score_message, score));

        // Set the "Take New Quiz" button to restart the quiz
        takeNewQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(Result.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        finishButton.setOnClickListener(v -> {
            finishAffinity(); 
        });


    }
}

