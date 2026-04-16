package com.example.tp_12;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
public class UpdateActivity extends AppCompatActivity {

    EditText uploadTopic, uploadDesc, uploadLang;
    Button btnSave;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        uploadTopic = findViewById(R.id.uploadTopic);
        uploadDesc = findViewById(R.id.uploadDesc);
        uploadLang = findViewById(R.id.uploadLang);
        btnSave = findViewById(R.id.saveButton);

        key = getIntent().getStringExtra("key");
        uploadTopic.setText(getIntent().getStringExtra("title"));
        uploadDesc.setText(getIntent().getStringExtra("desc"));
        uploadLang.setText(getIntent().getStringExtra("lang"));
        btnSave.setText("Update");

        btnSave.setOnClickListener(v -> {
            String newTitle = uploadTopic.getText().toString();
            String newDesc = uploadDesc.getText().toString();
            String newLang = uploadLang.getText().toString();

            if(newTitle.isEmpty() || newDesc.isEmpty() || newLang.isEmpty()) {
                Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
            } else {
                FileManager.updateNote(this, key, newTitle, newDesc, newLang);
                Toast.makeText(this, "Note mise à jour", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}