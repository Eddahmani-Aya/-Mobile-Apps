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

    EditText Username , Password ;
    Button btnLogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);


        Username = findViewById ( R.id.edTxtUser ) ;
        Password = findViewById ( R.id.edTxtPassword ) ;
        btnLogin = findViewById ( R.id.btnLogin ) ;
        btnLogin . setOnClickListener ( v -> {
            String username =Username.getText().toString().trim() ;
            String password = Password.getText().toString().trim() ;
            if (TextUtils.isEmpty(username) ) {
                Username.setError(" Username ␣ requis ") ;
                Username.requestFocus() ;
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
        }) ;

    }
}