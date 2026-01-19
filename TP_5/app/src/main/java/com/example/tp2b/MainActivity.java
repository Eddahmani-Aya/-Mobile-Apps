package com.example.tp2b;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2b.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        String flavor = BuildConfig.FLAVOR;
        String username = getIntent().getStringExtra("username");

        switch (flavor) {
            case "admin":
                setContentView(R.layout.activity_admin);
                break;

            case "student":
                setContentView(R.layout.activity_student);
                break;

            case "company":
                setContentView(R.layout.activity_company);
                break;

            case "professor":
                setContentView(R.layout.activity_professor);
                break;

            default:
                setContentView(R.layout.activity_login);
                Log.e(LOG_TAG, "Flavor inconnu : " + flavor);
                return;
        }

        // Ajouter dans MainActivity.onCreate() après le switch(flavor)
        Button btnStages = findViewById(R.id.btnStages);
        if (btnStages != null) {
            btnStages.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, StageActivity.class);
                startActivity(intent);
            });
        }

        tvWelcome = findViewById(R.id.tvWelcome);
        if (tvWelcome != null) {
            tvWelcome.setText(tvWelcome.getText().toString() +(username != null ? username : flavor));
        }

        Log.d(LOG_TAG, "Flavor actif : " + flavor);
    }
}
