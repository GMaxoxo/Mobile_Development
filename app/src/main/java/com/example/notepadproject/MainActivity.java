package com.example.notepadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputLoginET;

    private EditText inputPassET;

    private Button registrationBtn;

    private Button authorizationBtn;
    private TextView inputLoginAndPasswordTV;

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
        inputLoginAndPasswordTV = findViewById(R.id.inputLoginAndPasswordTV);
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
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener((it -> {
                    if (it.isSuccessful()) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, it.getException().toString(), Toast.LENGTH_SHORT).show();

                    }
                }));
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show();
            }
                })
        );
        // Обработчк авторизации
        authorizationBtn.setOnClickListener((v -> {
                    loadData();
                })
        );

    }
    // Метод сохранения
    private void saveData() {
        String login = inputLoginET.getText().toString();
        String password = inputPassET.getText().toString();

        Log.e("11", login + password);

        // Настройка хранения данных
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", login);
        editor.putString("password", password);
        editor.apply();
    }

    // Метод проверки
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("login", null);
        String password = sharedPreferences.getString("password", null);

        // Проверка данных
        if (inputLoginET.getText().toString().equals(login)  // перекидывает на новое окно
                && inputPassET.getText().toString().equals(password)) {
            startActivity(new Intent(this, SecondActivity.class));
        } else {        // выдаёт ошибку
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
        }
        //  inputLoginAndPasswordTV.setText(login + " " + password);  // как-будто можно убрать уже
    }



}