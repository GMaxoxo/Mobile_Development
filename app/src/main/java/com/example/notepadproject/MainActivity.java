package com.example.notepadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText inputLoginET;

    private EditText inputPassET;

    private Button registrationBtn;

    private Button authorizationBtn;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Инициализация
        init();

        // Обработчики нажатий
        setOnClickListeners();

    }



    // Метод Инициализации
    private void init() {
        inputLoginET = findViewById(R.id.inputLoginET);
        inputPassET = findViewById(R.id.inputPassET);
        registrationBtn = findViewById(R.id.registrationBtn);
        authorizationBtn = findViewById(R.id.authorizationBtn);
        mAuth = FirebaseAuth.getInstance();

    }

    // Метод обработчика нажатий
    private void setOnClickListeners(){
        // Обработчк регистрации
        registrationBtn.setOnClickListener((v -> {
            String email = inputLoginET.getText().toString();
            String pass = inputPassET.getText().toString();
            if (!email.isEmpty() && !pass.isEmpty()) {
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener((it -> {
                    if (it.isSuccessful()) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e("11", "ошибка");

                    }
                }));
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show();
            }
                })
        );
        // Обработчк авторизации
        authorizationBtn.setOnClickListener((v -> {
            String email = inputLoginET.getText().toString();
            String pass = inputPassET.getText().toString();
            if (!email.isEmpty() && !pass.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener((it -> {
                    if (it.isSuccessful()) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e("11", "ошибка");

                    }
                }));
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show();
            }
                })
        );

    }


    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        }
    }

}