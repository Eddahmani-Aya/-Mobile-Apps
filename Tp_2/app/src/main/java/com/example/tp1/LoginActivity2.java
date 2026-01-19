package com.example.tp1;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity2 extends AppCompatActivity {

    EditText etUsername , etPassword ;
    private static final String LOG_TAG =
            AdminActivity.class.getSimpleName();
    Button btnLogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);


        etUsername = findViewById ( R.id.edTxtUser ) ;
        etPassword = findViewById ( R.id.edTxtPassword ) ;
        btnLogin = findViewById ( R.id.btnLogin ) ;
        btnLogin . setOnClickListener ( v -> {
            String username = etUsername.getText().toString().trim() ;
            String password = etPassword.getText().toString().trim() ;
            if (TextUtils.isEmpty(username) ) {
                etUsername.setError(" Username ␣ requis ") ;
                etUsername.requestFocus() ;
                return ;
            }
            if (username.equals("Neeko") && password.equals("1234") ) {
                Log. d ("LoginActivity" , " Login ␣ reussi ");
                Toast.makeText(this , " Welcome ␣ admin ␣ ", Toast.LENGTH_SHORT ).show() ;
                Intent intent =new Intent ( this, MainActivity.class ) ;
                intent.putExtra("username",username);
                startActivity(intent);
            }

            else {
                Toast.makeText(this , " Wrong ␣ credentials ␣ ", Toast.LENGTH_SHORT ).show();
            }

            if (username.equals("Neeko") && password.equals("1234")) {
                Log.d(LOG_TAG, "Login réussi");
                if (BuildConfig.FLAVOR.equals("student")) {
                    Log.d(LOG_TAG, "Mode STUDENT actif");
                    Intent intent =
                            new Intent(LoginActivity2.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Log.d(LOG_TAG, "Mode ADMIN actif");
                    Intent intent =
                            new Intent(LoginActivity2.this, AdminActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            } else {
                Log.d(LOG_TAG, "Login échoué");
                Toast.makeText(this,
                        "Wrong credentials ",
                        Toast.LENGTH_SHORT).show();
            }
        }) ;

    }
}