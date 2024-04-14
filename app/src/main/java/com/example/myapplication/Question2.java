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

public class Question2 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button submitButton;
    private Button nextQuestionButton;
    private String userName;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        // Initialize components from the layout
        radioGroup = findViewById(R.id.answers_group);
        submitButton = findViewById(R.id.submit_button2); // Using consistent IDs across activities
        nextQuestionButton = findViewById(R.id.next_question_button2); // Using consistent IDs across activities

        // Retrieve the user's name and the score from the intent
        userName = getIntent().getStringExtra("USER_NAME");
        score = getIntent().getIntExtra("SCORE", 0); // Correctly moved inside onCreate

        final int correctAnswerId = R.id.answer2; // Assume R.id.answer2 is the ID of the correct answer RadioButton

        // Setting up the submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // Check if an answer has been selected
                if (selectedId == -1) {
                    Toast.makeText(Question2.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Disable all RadioButtons to prevent changing the answer after submission
                disableRadioButtons();

                // Check if the selected answer is correct
                if (selectedId == correctAnswerId) {
                    score++;
                    showToastAndHighlightAnswer("Correct answer!", Color.GREEN, selectedId);
                } else {
                    showToastAndHighlightAnswer("Incorrect answer!", Color.RED, selectedId);
                }

                // Make the next question button visible
                nextQuestionButton.setVisibility(View.VISIBLE);
            }
        });

        // Setting up the next question button click listener
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start Question3 and pass along the user's name and current score
                Intent intent = new Intent(Question2.this, Question3.class); // Assuming Question3 exists
                intent.putExtra("USER_NAME", userName);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        });
    }

    private void showToastAndHighlightAnswer(String message, int color, int selectedId) {
        Toast.makeText(Question2.this, message, Toast.LENGTH_SHORT).show();
        RadioButton selectedRadioButton = findViewById(selectedId);
        selectedRadioButton.setTextColor(color); // Highlight the selected RadioButton
    }

    private void disableRadioButtons() {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
    }
}
