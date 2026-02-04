package ma.projet.lifebalance.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ma.projet.lifebalance.R;
import ma.projet.lifebalance.database.TacheDAO;
import ma.projet.lifebalance.models.Tache;

public class AddTacheActivity extends AppCompatActivity {
    private EditText etTitre, etDescription, etDate;
    private Button btnAjouter;
    private TacheDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tache);

        etTitre = findViewById(R.id.etTitre);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        btnAjouter = findViewById(R.id.btnAjouter);

        dao = new TacheDAO(this);
        dao.open();

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = etTitre.getText().toString();
                String description = etDescription.getText().toString();
                String date = etDate.getText().toString();

                if (titre.isEmpty()) {
                    Toast.makeText(AddTacheActivity.this, R.string.title_is_required, Toast.LENGTH_SHORT).show();
                    return;
                }

                Tache tache = new Tache(titre, description, date, "À faire");
                dao.insertTache(tache);

                Toast.makeText(AddTacheActivity.this, R.string.task_added_successfully, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }
}
