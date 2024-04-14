package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Question1 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button submitButton;
    private Button nextQuestionButton;
    private String userName;
    private int score = 0; // Initialize score to 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        // Retrieve the user's name from the intent
        userName = getIntent().getStringExtra("USER_NAME");

        radioGroup = findViewById(R.id.answers_group);
        submitButton = findViewById(R.id.submit_button);
        nextQuestionButton = findViewById(R.id.next_question_button);

        final int correctAnswerId = R.id.answer1; // Correct answer ID

        submitButton.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(Question1.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            // Immediately disable all RadioButtons to prevent changing the answer
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                radioGroup.getChildAt(i).setEnabled(false);
            }

            // Check if the selected answer is correct and increment score if it is
            if (selectedId == correctAnswerId) {
                score++;
                showToastAndHighlightAnswer("Correct answer!", Color.GREEN, selectedId);
            } else {
                showToastAndHighlightAnswer("Incorrect answer!", Color.RED, selectedId);
            }

            nextQuestionButton.setVisibility(View.VISIBLE); // Show the next question button
        });

        nextQuestionButton.setOnClickListener(v -> {
            // Create an intent for Question2 and pass the user's name and score
            Intent intent = new Intent(Question1.this, Question2.class);
            intent.putExtra("USER_NAME", userName);
            intent.putExtra("SCORE", score); // Pass the current score to the next question
            startActivity(intent);
        });
    }

    private void showToastAndHighlightAnswer(String message, int color, int selectedId) {
        Toast.makeText(Question1.this, message, Toast.LENGTH_SHORT).show();
        ((RadioButton) findViewById(selectedId)).setTextColor(color); // Set text color of the RadioButton
    }
}




