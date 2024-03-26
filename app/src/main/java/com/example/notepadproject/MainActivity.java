package com.example.notepadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView inputLoginAndPasswordTV;
    private EditText inputLoginET;
    private EditText inputPassET;
    private Button registrationBtn;
    private Button authorizationBtn;
    private ArrayList<String> authorizedUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setOnClickListener();
        authorizedUsers = new ArrayList<>();


    }


    private void init() {
        inputLoginAndPasswordTV = findViewById(R.id.inputLoginAndPasswordTV);
        inputLoginET = findViewById(R.id.inputLoginET);
        inputPassET = findViewById(R.id.inputPassET);
        registrationBtn = findViewById(R.id.registrationBtn);
        authorizationBtn = findViewById(R.id.authorizationBtn);
    }

    private void setOnClickListener() {
        authorizationBtn.setOnClickListener(v -> {
            String login = inputLoginET.getText().toString();
            String password = inputPassET.getText().toString();

            if (userIsAuthorized(login, password)) {
                authorizedUsers.add(login);
                startActivity(new Intent(this, SecondActivity.class));
            } else {
                inputLoginAndPasswordTV.setText("Данные введены неверно");
            }
        });

    }
    private boolean userIsAuthorized(String login, String password){

        return login.equals("GMaxoxo") && password.equals("235");
    }


}

