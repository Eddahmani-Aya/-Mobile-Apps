package com.example.tp_3;

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

public class MainActivity extends AppCompatActivity {


    EditText etEmail = findViewById ( R.id.edTxtEmail ), etPassword = findViewById ( R.id.edTxtPassword );
    Button btnLogin = findViewById ( R.id.btnLogin );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        btnLogin . setOnClickListener ( v -> {
            String username = etEmail.getText().toString().trim() ;
            String password = etPassword.getText().toString().trim() ;
            if (TextUtils.isEmpty(username) ) {
                etEmail.setError(" Username ␣ requis ") ;
                etEmail.requestFocus() ;
                return ;
            }
            if (username.equals("Neeko") && password.equals("1234") ) {
                Log. d ("MainActivity" , " Login ␣ reussi ");
                Toast.makeText(this , " Welcome ␣ admin ␣ ", Toast.LENGTH_SHORT ).show() ;
                Intent intent =new Intent ( this, DashboardActivity.class ) ;
                intent.putExtra("username",username);
                startActivity(intent);
            }
            else {
                Toast.makeText(this , " Wrong ␣ credentials ␣ ", Toast.LENGTH_SHORT ).show();
            }
        }) ;

    }
}