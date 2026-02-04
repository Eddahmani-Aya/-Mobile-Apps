package ma.projet.lifebalance.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ma.projet.lifebalance.R;
import ma.projet.lifebalance.adapters.TacheAdapter;
import ma.projet.lifebalance.database.TacheDAO;
import ma.projet.lifebalance.models.Tache;

public class ListeTachesActivity extends AppCompatActivity implements TacheAdapter.OnTacheListener {

    private TacheAdapter adapter;
    private TacheDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_taches);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dao = new TacheDAO(this);
        adapter = new TacheAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao.open();
        ArrayList<Tache> taches = dao.getAllTaches();
        adapter.setListe(taches);
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

    @Override
    public void onTacheClick(Tache tache) {
        Intent intent = new Intent(this, EditTacheActivity.class);
        intent.putExtra("id", tache.getId());
        intent.putExtra("titre", tache.getTitre());
        intent.putExtra("description", tache.getDescription());
        intent.putExtra("date", tache.getDate());
        intent.putExtra("statut", tache.getStatut());
        startActivity(intent);
    }

    @Override
    public void onTacheLongClick(Tache tache) {
        dao.deleteTache(tache.getId());
        ArrayList<Tache> taches = dao.getAllTaches();
        adapter.setListe(taches);
    }
}
