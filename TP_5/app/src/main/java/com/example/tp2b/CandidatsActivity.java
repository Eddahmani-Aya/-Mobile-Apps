package com.example.tp2b;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CandidatsActivity extends AppCompatActivity {

    private GridView gridViewCandidats;
    private TextView tvOffre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidats);

        gridViewCandidats = findViewById(R.id.gridViewCandidats);
        tvOffre = findViewById(R.id.tvOffre);

        // Récupérer l'offre sélectionnée
        String offre = getIntent().getStringExtra("offre");
        tvOffre.setText("Candidats pour : " + offre);

        // Données des candidats
        ArrayList<Candidat> candidats = new ArrayList<>();
        candidats.add(new Candidat("Marie", "Informatique", R.drawable.algeria));
        candidats.add(new Candidat("Jean", "Data Science", R.drawable.egypt));
        candidats.add(new Candidat("Sophie", "Design", R.drawable.morocco));
        candidats.add(new Candidat("Thomas", "Marketing", R.drawable.tunisia));

        // Adaptateur personnalisé pour GridView
        CandidatAdapter adapter = new CandidatAdapter(this, candidats);
        gridViewCandidats.setAdapter(adapter);
    }
}