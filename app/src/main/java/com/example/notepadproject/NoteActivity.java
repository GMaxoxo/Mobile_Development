package com.example.notepadproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.UUID;

public class NoteActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private RadioButton rbPublic;
    private RadioButton rbPrivate;

    private DatabaseReference rootDatabaseRef;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(R.color.BackgroundElements)));

        init();
        loadData();
    }

    private void init() {
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        rbPublic = findViewById(R.id.rbPublic);
        rbPrivate = findViewById(R.id.rbPrivate);

         rootDatabaseRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.miPermit) {
        String noteId = UUID.randomUUID().toString();
        Note note = new Note(
                noteId,
                etTitle.getText().toString(),
                etDescription.getText().toString()
        );

            rootDatabaseRef.child(userId).child(noteId).setValue(note);
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", null);
        Log.e("11", userId);
    }

}