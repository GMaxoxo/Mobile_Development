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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView inputLoginAndPasswordTV;

    private EditText inputLoginET;

    private EditText inputPassET;

    private Button registrationBtn;

    private Button authorizationBtn;
    private TextView passVerification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация
        init();
        saveData();
        setOnClickListeners();

        // Добавляем обработчик для проверки пароля
        inputPassET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Proverka();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }





    // Метод Инициализации
    private void init() {
        inputLoginAndPasswordTV = findViewById(R.id.inputLoginAndPasswordTV);
        inputLoginET = findViewById(R.id.inputLoginET);
        inputPassET = findViewById(R.id.inputPassET);
        registrationBtn = findViewById(R.id.registrationBtn);
        authorizationBtn = findViewById(R.id.authorizationBtn);
        passVerification = findViewById(R.id.passVerification);
    }

    // Метод обработчика нажатий
    private void setOnClickListeners() {

        String loginNotEmpty = inputLoginET.getText().toString();
        String passwordNotEmpty = inputPassET.getText().toString();

        // Проверяем, что и логин, и пароль не пустые
        if (loginNotEmpty.isEmpty() || passwordNotEmpty.isEmpty()) {
            return;
        } else {
            // Обработчк регистрации
            registrationBtn.setOnClickListener((v -> {
                saveData();
            })
            );
        }


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
            overridePendingTransition(0, 0); // Убирает анимки
        } else {        // выдаёт ошибку
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
        //  inputLoginAndPasswordTV.setText(login + " " + password);
        // как-будто можно убрать уже + в инициализации
    }

    private void Proverka() {



        // Получаем пароль
        String password = inputPassET.getText().toString();

        // Флаги для проверки условий
        boolean passCapital = false; // Заглавная буква
        boolean passDigit = false; // Цифра
        boolean passLength = false;  // Длинна
        String passReport = "";

        // Проверяем символы
        for (int i = 0; i < password.length(); i++) {
            char massivPassword = password.charAt(i);
            if (Character.isUpperCase(massivPassword)) {
                passCapital = true;
            }

            if (Character.isDigit(massivPassword)) {
                passDigit = true;
            }

        }
        // Проверяем длину пароля
        if (password.length() > 7) {
            passLength = true;
        }

        // Считаем пароль правильным
        if (passDigit && passCapital && passLength) {
            passVerification.setText("Правильный пароль");
            passReport += "Хороший пароль";
            saveData();
            setOnClickListeners();
        } else {

            // Отчёт об ошибке
            passReport += "Пароль должен содержать минимум 8 символов. Также он должен содержать как минимум одну цифру и заглавную букву";
                failregistration();
            }


            passVerification.setText(passReport);
    }



    // Показ неудачной регистрации
    private void failregistration() {
        registrationBtn.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Пароль не введён по требованиям", Toast.LENGTH_SHORT).show();
        });
    }

}




