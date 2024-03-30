package com.example.notepadproject;

import android.content.Context;
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
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    //Меню навигации
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    NotesAdapter adapter;

    // Кнопка в навигации
    FloatingActionButton fabBtn;

    ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); //Отображение по всему экрану
        setContentView(R.layout.activity_second);

        // Инициализация
        init();
        // Меню навигации
        setUpBottomNavBar();

        setUpRecyclerView();

    }

    // Метод инициализации
    private void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fabBtn = findViewById(R.id.fab);
    }

    // Метод навигации
    private void setUpBottomNavBar(){
        bottomNavigationView.setBackground(null); // Убираем фон нижнего меню
        bottomNavigationView.setSelectedItemId(R.id.miHome); // Начальный выбор кнопки
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Обработчик нажатий кнопок


                if (menuItem.getItemId() == R.id.miSettings){

                    startActivity(new Intent( SecondActivity.this,SettingsActivity.class));  // Переход на окно настроек
                    overridePendingTransition(0, 0); // Убирает анимки
                    finish();
                } else {

                }
                return true;

            }
        });

        fabBtn.setOnClickListener((v -> showDialog()));


        }


    private void showDialog() {
        // Создаем диалоговое окно
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        alertDialog.setTitle("Введите название");

        // Создаем поле ввода
        EditText input = new EditText(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(lp);
        input.setHint("Введите название");
        alertDialog.setView(input);


        // Кнопка "Создать"
        alertDialog.setPositiveButton("Создать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                addToRecyclerView(new Note(1, input.getText().toString() ) );
            }
        });
        // Кнопка "Отмена"
        alertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        // Показываем диалоговое окно
        alertDialog.show();
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.rcView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NotesAdapter( getBaseContext(), notes );
        recyclerView.setAdapter(adapter);
    }

    private void addToRecyclerView(Note note) {
        adapter.addElement(note);
    }

}



