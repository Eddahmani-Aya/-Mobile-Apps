package com.example.tp_firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddMessage extends AppCompatActivity {

    EditText text, text2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        text = findViewById(R.id.editTextText);
        text2 = findViewById(R.id.editTextText2);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(v -> {

            String msg = text.getText().toString().trim();
            String author = text2.getText().toString().trim();

            if (msg.isEmpty()) {
                text.setError("Champ vide !");
                return;
            }

            if (author.isEmpty()) {
                text2.setError("Champ vide !");
                return;
            }

            addMsgDB(msg, author);
        });
    }

    private void addMsgDB(String msg, String author) {

        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("msg");

        String key = ref.push().getKey();

        Message message = new Message(key, msg, author);

        ref.child(key).setValue(message);

        Toast.makeText(this, "Enregistré", Toast.LENGTH_LONG).show();

        text.getText().clear();
        text2.getText().clear();
    }
}