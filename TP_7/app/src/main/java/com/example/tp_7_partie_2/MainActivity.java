package com.example.tp_7_partie_2;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    Button  btnClose;
    String[] subjects = {
            "Dentiste", "Vétérinaire","Coiffeur / Barbier",
            "Psychologue / Psychiatre", "Maquilleur(se) ",
            "Consultant","Awvocat","Coach "
            ,"Mécanicien automobile","Prothésiste ongulaire" ,
            "Plombier","Électricien","Nettoyage à domicile"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("USER_NAME");
        Toast.makeText(this, "Bienvenue " + username, Toast.LENGTH_LONG).show();

        gridView = findViewById(R.id.gridView);
        btnClose = findViewById(R.id.btnClose);
        SubjectAdapter adapter = new SubjectAdapter(this, subjects);
        gridView.setAdapter(adapter);
        btnClose.setOnClickListener(v -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Application visible", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Application prête", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CYCLE", "Application en pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        new AlertDialog.Builder(this)
                .setTitle("Information")
                .setMessage("Application en arrière-plan")
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new AlertDialog.Builder(this)
                .setTitle("Reprise")
                .setMessage("Voulez-vous continuer ?")
                .setPositiveButton("Oui", null)
                .setNegativeButton("Non", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CYCLE", "Application fermée");
    }
}
