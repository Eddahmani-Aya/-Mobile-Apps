package com.example.tp_12;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;

public class AddActivity extends AppCompatActivity {

    EditText uploadTopic, uploadDesc, uploadLang;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        uploadTopic = findViewById(R.id.uploadTopic);
        uploadDesc = findViewById(R.id.uploadDesc);
        uploadLang = findViewById(R.id.uploadLang);
        btnSave = findViewById(R.id.saveButton);

        btnSave.setOnClickListener(v -> {
            String title = uploadTopic.getText().toString();
            String desc = uploadDesc.getText().toString();
            String lang = uploadLang.getText().toString();

            if(title.isEmpty() || desc.isEmpty() || lang.isEmpty()) {
                Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
            } else {
                FileManager.saveNote(this, title, desc, lang);
                Toast.makeText(this, "Note ajoutée", Toast.LENGTH_SHORT).show();
                finish(); // retourne à MainActivity
            }
        });
    }
}