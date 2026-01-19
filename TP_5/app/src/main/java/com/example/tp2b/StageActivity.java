package com.example.tp2b;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class StageActivity extends AppCompatActivity {

    private Spinner spinnerEntreprises;
    private Button btnVoirOffres;
    private String entrepriseSelectionnee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        spinnerEntreprises = findViewById(R.id.spinnerEntreprises);
        btnVoirOffres = findViewById(R.id.btnVoirOffres);

        // Données pour le Spinner
        String[] entreprises = {"Google", "Microsoft", "Amazon", "Facebook", "Tesla"};

        // ArrayAdapter pour Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                entreprises
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntreprises.setAdapter(adapter);

        // Sélection dans Spinner
        spinnerEntreprises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entrepriseSelectionnee = entreprises[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Bouton pour passer à la ListView
        btnVoirOffres.setOnClickListener(v -> {
            Intent intent = new Intent(StageActivity.this, OffresActivity.class);
            intent.putExtra("entreprise", entrepriseSelectionnee);
            startActivity(intent);
        });
    }
}