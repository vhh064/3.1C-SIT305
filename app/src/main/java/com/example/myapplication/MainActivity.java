package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button startQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        nameEditText = findViewById(R.id.name_edit_text);
        startQuizButton = findViewById(R.id.start_quiz_button);

        // Set up click listeners for the start quiz button
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        // Trim the input to remove any leading or trailing whitespace
        String name = nameEditText.getText().toString().trim();

        if (!name.isEmpty()) {
            // Intent to start the Question1 Activity and include the user's name
            Intent intent = new Intent(MainActivity.this, Question1.class);
            intent.putExtra("USER_NAME", name); // Pass the name to Question1 Activity
            startActivity(intent);
        } else {
            // Prompt the user to enter their name if the input is empty
            Toast.makeText(MainActivity.this, "Please enter your name to start the quiz.", Toast.LENGTH_SHORT).show();
        }
    }
}

