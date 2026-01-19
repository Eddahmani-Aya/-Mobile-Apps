package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    CheckBox cbA, cbB, cbC;
    TextView tvTotal, tvWelcome;
    Button btnCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer le username depuis LoginActivity
        String username = getIntent().getStringExtra("username");
        if(username == null) username = "Utilisateur";

        // Initialisation des vues
        cbA = findViewById(R.id.cbOptionA);
        cbB = findViewById(R.id.cbOptionB);
        cbC = findViewById(R.id.cbOptionC);
        tvTotal = findViewById(R.id.tvTotal);
        tvWelcome = findViewById(R.id.tvWelcome);
        btnCalculer = findViewById(R.id.btnCalculer);

        Log.d(LOG_TAG, "Vérification des IDs : cbA=" + cbA + ", cbB=" + cbB + ", cbC=" + cbC + ", tvTotal=" + tvTotal + ", tvWelcome=" + tvWelcome + ", btnCalculer=" + btnCalculer);

        // Affichage du message de bienvenue
        tvWelcome.setText("Welcome " + username);
        Log.d(LOG_TAG, "MainActivity ouverte");

        // Déclenchement automatique du calcul
        if(cbA != null) cbA.setOnCheckedChangeListener((buttonView, isChecked) -> calculerTotal());
        if(cbB != null) cbB.setOnCheckedChangeListener((buttonView, isChecked) -> calculerTotal());
        if(cbC != null) cbC.setOnCheckedChangeListener((buttonView, isChecked) -> calculerTotal());

        // Calcul manuel via bouton
        if(btnCalculer != null) btnCalculer.setOnClickListener(v -> calculerTotal());
        String flavor = BuildConfig.FLAVOR;
        if ("admin".equals(flavor)) {
            Log.d(LOG_TAG, "Mode ADMIN actif");
            tvWelcome.setText("Bienvenue Admin");
        } else if ("student".equals(flavor)) {
            Log.d(LOG_TAG, "Mode STUDENT actif");
            tvWelcome.setText("Bienvenue Étudiant");
        } else {
            Log.d(LOG_TAG, "Flavor inconnu : " + flavor);
        }

    }

    private void calculerTotal() {
        int total = 0;
        if(cbA != null && cbA.isChecked()) total += 50;
        if(cbB != null && cbB.isChecked()) total += 30;
        if(cbC != null && cbC.isChecked()) total += 20;

        if(tvTotal != null) tvTotal.setText("Total : " + total + " DH");
        Log.d(LOG_TAG, "Total calculé : " + total);
    }
}
