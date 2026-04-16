package com.example.tp_12;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import android.widget.*;
public class MainActivity extends AppCompatActivity {

    GridView gridView;
    NoteAdapter adapter;
    FloatingActionButton btnAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView =findViewById(R.id.gridView);
        btnAdd = findViewById(R.id.btnAdd);
        loadData();

        btnAdd.setOnClickListener(v -> startActivity(new Intent(this, AddActivity.class)));
    }

    private void loadData() {
        // Lire toutes les notes depuis le fichier
        List<DataClass> list = FileManager.readNotes(this);

        // Créer l’adapter
        adapter = new NoteAdapter(this, list);

        // Associer l’adapter au GridView
        gridView.setAdapter(adapter);

        // Long click pour modifier
        gridView.setOnItemLongClickListener((parent, view, position, id) -> {
            DataClass note = (DataClass) adapter.getItem(position);

            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra("key", note.getKey());
            intent.putExtra("title", note.getDataTitle());
            intent.putExtra("desc", note.getDataDesc());
            intent.putExtra("lang", note.getDataLang());
            startActivity(intent);

            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}