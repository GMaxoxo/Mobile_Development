package com.example.notepadproject;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class SettingsActivity extends AppCompatActivity {

    //Меню навигации
    BottomNavigationView bottomNavigationView;
    // Кнопка в навигации
    FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); //Отображение по всему экрану
        setContentView(R.layout.activity_settings);

        // Инициализация
        init();
        // Меню навигации
        setUpBottomNavBar();
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fabBtn = findViewById(R.id.fab);
    }

    // Метод навигации
    private void setUpBottomNavBar(){
        bottomNavigationView.setBackground(null); // Убираем фон нижнего меню
        bottomNavigationView.setSelectedItemId(R.id.miSettings); // Начальный выбор кнопки
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Обработчик нажатий кнопок

                if (menuItem.getItemId() == R.id.miHome){
                    startActivity(new Intent( SettingsActivity.this,SecondActivity.class));
                    overridePendingTransition(0, 0); // Убирает анимки
                    finish();
                } else {

                }
                return true;


            }
        });

        fabBtn.setOnClickListener((v -> { }));


    }
}