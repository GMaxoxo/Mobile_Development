package com.example.notepadproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    //Меню навигации
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    NotesAdapter adapter;

    ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Инициализация
        init();
        // Меню навигации
        setUpBottomNavBar();

        setUpRecyclerView();

    }

    // Метод инициализации
    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    // Метод навигации
    private void setUpBottomNavBar() {
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Обработчик нажатий кнопок

                if (menuItem.getItemId() == R.id.miGroup) {
                }

                if (menuItem.getItemId() == R.id.miSettings) {
                    Intent intent = new Intent(SecondActivity.this, SettingsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                } else {

                }
                return true;

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.miAdd) {
            startActivity(new Intent(this, NoteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.rcView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NotesAdapter(getBaseContext(), notes);
        recyclerView.setAdapter(adapter);
    }

    private void addToRecyclerView(Note note) {
        adapter.addItem(note);
    }
}



