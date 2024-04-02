package com.example.notepadproject;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class SettingsActivity extends AppCompatActivity {

    //Меню навигации
    BottomNavigationView bottomNavigationView;
    // Кнопка в навигации


    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Инициализация
        init();
        // Меню навигации
        setUpBottomNavBar();

         setOnClickListeners();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btnLogOut = findViewById(R.id.btnLogOut);
    }

   private void setOnClickListeners() {
        btnLogOut.setOnClickListener((v -> {
            //todo log out from firebase account
        }));
    }


    // Метод навигации
    private void setUpBottomNavBar() {
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.miSettings); //  выбор кнопки
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Обработчик нажатий кнопок

                if (menuItem.getItemId() == R.id.miHome) {
                    startActivity(new Intent(SettingsActivity.this, SecondActivity.class));
                    overridePendingTransition(0, 0); // Убирает анимки
                    finish();
                }

                if (menuItem.getItemId() == R.id.miGroup) {
                }
                return true;

            }
        });


    }
}