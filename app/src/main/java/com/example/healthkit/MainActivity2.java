package com.example.healthkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button backButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.bakButton);
        progressBar = findViewById(R.id.progressBar);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String username = usernameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate input fields
                if (username.isEmpty()) {
                    // Display an error message for empty username
                    Toast.makeText(MainActivity2.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    // Display an error message for empty email
                    Toast.makeText(MainActivity2.this, "Please enter an email address", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    // Display an error message for empty password
                    Toast.makeText(MainActivity2.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                } else {
                    // Show progress bar
                    progressBar.setVisibility(View.VISIBLE);

                    // Create a new user with email and password
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Hide progress bar
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        // Registration success
                                        Toast.makeText(MainActivity2.this, "Registration successful", Toast.LENGTH_SHORT).show();

                                        // Pass the username to MainActivity3\
                                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                        intent.putExtra("username", username);
                                        startActivity(intent);
                                        finish();

                                        Toast.makeText(MainActivity2.this, "Thank You for choosing us!", Toast.LENGTH_SHORT).show();
                                        // Additional logic after successful registration
                                    } else {
                                        // Registration failed
                                        Toast.makeText(MainActivity2.this, "Registration failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


        // Set click listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the main activity
                finish();
            }
        });
    }
}
