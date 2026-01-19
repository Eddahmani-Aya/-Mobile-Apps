package com.example.tp_6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextVille;
    private ImageButton buttonOK;
    private ListView listViewMeteo;

    private List<MeteoItem> data;
    private MeteoListModel adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app);

        // Liaison avec XML
        editTextVille = findViewById(R.id.editTextVille);
        buttonOK = findViewById(R.id.buttonOK);
        listViewMeteo = findViewById(R.id.listViewMeteo);

        // Initialisation des données
        data = new ArrayList<>();

        // Initialisation de l'adapter personnalisé
        adapter = new MeteoListModel(
                MainActivity.this,
                R.layout.list_item_layout,
                data
        );

        listViewMeteo.setAdapter(adapter);

        // Click sur le bouton
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ville = editTextVille.getText().toString().trim();

                if (ville.isEmpty()) {
                    editTextVille.setError("Entrer une ville");
                    return;
                }

                data.clear();
                adapter.notifyDataSetChanged();


                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                String url = "https://api.openweathermap.org/data/2.5/forecast?q="
                        + ville
                        + "&appid=a4578e39643716894ec78b28a71c7110";

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray list = jsonObject.getJSONArray("list");

                                    for (int i = 0; i < list.length(); i++) {

                                        JSONObject item = list.getJSONObject(i);
                                        JSONObject main = item.getJSONObject("main");
                                        JSONArray weather = item.getJSONArray("weather");

                                        // Date
                                        long dt = item.getLong("dt") * 1000;
                                        Date date = new Date(dt);
                                        SimpleDateFormat sdf = new SimpleDateFormat(
                                                "dd-MMM-yyyy HH:mm",
                                                Locale.getDefault()
                                        );
                                        String dateStr = sdf.format(date);

                                        // Températures (Kelvin -> Celsius)
                                        int tempMin = (int) (main.getDouble("temp_min") - 273.15);
                                        int tempMax = (int) (main.getDouble("temp_max") - 273.15);

                                        int pression = main.getInt("pressure");
                                        int humidite = main.getInt("humidity");

                                        String image = weather
                                                .getJSONObject(0)
                                                .getString("main");

                                        // Création objet MeteoItem
                                        MeteoItem meteo = new MeteoItem();
                                        meteo.tempMin = tempMin;
                                        meteo.tempMax = tempMax;
                                        meteo.pression = pression;
                                        meteo.humidite = humidite;
                                        meteo.date = dateStr;
                                        meteo.image = image;

                                        data.add(meteo);
                                    }

                                    adapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley", "Erreur réseau");
                                Toast.makeText(
                                        MainActivity.this,
                                        "Problème de connexion",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                );

                queue.add(request);
            }
        });
    }

    // ===== Cycle de vie =====

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
        Log.i("LifeCycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage("Application en arrière-plan")
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new AlertDialog.Builder(this)
                .setTitle("Retour")
                .setMessage("Voulez-vous continuer ?")
                .setPositiveButton("Oui", null)
                .setNegativeButton("Non", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LifeCycle", "Application fermée");
    }
}