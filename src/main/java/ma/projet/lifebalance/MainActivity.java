package ma.projet.lifebalance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ma.projet.lifebalance.activities.AddTacheActivity;
import ma.projet.lifebalance.activities.ListeTachesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btnAddTache);
        Button btnList = findViewById(R.id.btnListeTaches);

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddTacheActivity.class)));

        btnList.setOnClickListener(v ->
                startActivity(new Intent(this, ListeTachesActivity.class)));
    }
}
