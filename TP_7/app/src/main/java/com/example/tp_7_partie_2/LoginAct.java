package com.example.tp_7_partie_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginAct extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.equals("Neeko") && password.equals("1234")) {

                // Intent + passage du paramètre
                Intent intent = new Intent(LoginAct.this, MainActivity.class);
                intent.putExtra("USER_NAME", username);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(LoginAct.this,
                        "Identifiants incorrects",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}