package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText createFullName;
    EditText createEmail;
    EditText createPassword;
    Button registerBtn;
    TextView registeredUser;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createFullName = findViewById(R.id.createFullName);
        createEmail = findViewById(R.id.createEmail);
        createPassword = findViewById(R.id.createPassword);
        registerBtn = findViewById(R.id.registerBtn);
        registeredUser = findViewById(R.id.registeredUser);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = createEmail.getText().toString().trim();
                String password = createPassword.getText().toString().trim();

                // Checks to see if fields are empty
                if (TextUtils.isEmpty(email)) {
                    createEmail.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    createPassword.setError("Password is required.");
                }

                progressBar.setVisibility(View.VISIBLE);

                // Register the user in firebase

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Register.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });

        registeredUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


    }
}