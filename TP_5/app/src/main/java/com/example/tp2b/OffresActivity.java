package com.example.tp2b;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class OffresActivity extends AppCompatActivity {

    private ListView listViewOffres;
    private Button btnVoirCandidats;
    private TextView tvEntreprise;
    private ArrayList<Stage> offres;
    private Stage offreSelectionnee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offres);

        listViewOffres = findViewById(R.id.listViewOffres);
        btnVoirCandidats = findViewById(R.id.btnVoirCandidats);
        tvEntreprise = findViewById(R.id.tvEntreprise);

        // Récupérer l'entreprise du Spinner
        String entreprise = getIntent().getStringExtra("entreprise");
        tvEntreprise.setText("Offres chez " + entreprise);

        // Initialiser les offres
        offres = new ArrayList<>();
        if ("Google".equals(entreprise)) {
            offres.add(new Stage("Google", "Développeur Android", "6 mois", "Paris", R.drawable.morocco));
            offres.add(new Stage("Google", "Data Scientist", "4 mois", "Lyon", R.drawable.egypt));
        } else if ("Microsoft".equals(entreprise)) {
            offres.add(new Stage("Microsoft", "Ingénieur Cloud", "5 mois", "Paris", R.drawable.algeria));
            offres.add(new Stage("Microsoft", "Développeur C#", "6 mois", "Toulouse", R.drawable.tunisia));
        }

        // ArrayAdapter pour ListView
        ArrayAdapter<Stage> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                offres
        );
        listViewOffres.setAdapter(adapter);

        // Sélection dans ListView
        listViewOffres.setOnItemClickListener((parent, view, position, id) -> {
            offreSelectionnee = offres.get(position);
            btnVoirCandidats.setEnabled(true);
        });

        // Bouton pour passer au GridView
        btnVoirCandidats.setOnClickListener(v -> {
            if (offreSelectionnee != null) {
                Intent intent = new Intent(OffresActivity.this, CandidatsActivity.class);
                intent.putExtra("offre", offreSelectionnee.getPoste());
                startActivity(intent);
            }
        });
        btnVoirCandidats.setEnabled(false);
    }
}