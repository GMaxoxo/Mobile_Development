package com.example.notepadproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class SecondActivity extends AppCompatActivity {

    //Меню навигации
    BottomNavigationView bottomNavigationView;
    // Кнопка в навигации
    FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); //Отображение по всему экрану
        setContentView(R.layout.activity_second);

        // Инициализация
        init();
        // Меню навигации
        setUpBottomNavBar();
    }

    // Метод инициализации
    private void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
       fabBtn = findViewById(R.id.fab);
    }

    // Метод навигации
    private void setUpBottomNavBar(){
        bottomNavigationView.setBackground(null); // Убираем фон нижнего меню
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Обработчик нажатий кнопок
                if (menuItem.getItemId() == R.id.miHome) {
                    showDialog();
                }

                if (menuItem.getItemId() == R.id.fab) {
                    showDialog();
                }

                if (menuItem.getItemId() == R.id.miSettings){
                    startActivity(new Intent( SecondActivity.this,SettingsActivity.class));
                    overridePendingTransition(0, 0);
                } else {

                }
                return true;




            }
        });

        fabBtn.setOnClickListener((v -> { }));


        }


        private void showDialog() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());

            alertDialog.setTitle("Введите название");
            alertDialog.setMessage("Введите название заметки");

            EditText input = new EditText(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);

           alertDialog.setPositiveButton("Создать", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   //todo create note
               }
                   });

            alertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which) {
                    //todo cancel dialog
                }
            });

            alertDialog.show();
      }
}