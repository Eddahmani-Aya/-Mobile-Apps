package ma.projet.lifebalance.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ma.projet.lifebalance.R;
import ma.projet.lifebalance.database.TacheDAO;
import ma.projet.lifebalance.models.Tache;

public class EditTacheActivity extends AppCompatActivity {
    private EditText etTitre, etDescription, etDate;
    private Button btnModifier;
    private int id;
    private TacheDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tache);

        etTitre = findViewById(R.id.etTitre);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        btnModifier = findViewById(R.id.btnModifier);

        dao = new TacheDAO(this);
        dao.open();

        id = getIntent().getIntExtra("id", -1);
        etTitre.setText(getIntent().getStringExtra("titre"));
        etDescription.setText(getIntent().getStringExtra("description"));
        etDate.setText(getIntent().getStringExtra("date"));

        btnModifier.setOnClickListener(v -> {
            Tache tache = new Tache(
                    etTitre.getText().toString(),
                    etDescription.getText().toString(),
                    etDate.getText().toString(),
                    "À faire"
            );
            tache.setId(id);

            dao.updateTache(tache);

            Toast.makeText(this, R.string.task_updated_successfully, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }
}
