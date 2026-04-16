package com.example.tp_11;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTitre, editDescription, editDate;
    Switch switchDark;
    Button btnSave;

    SharedPreferences prefs;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);

        // appliquer Dark Mode si déjà sauvegardé
        boolean darkMode = prefs.getBoolean("darkMode", false);
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        setContentView(R.layout.activity_main);

        // récupérer les views
        editTitre = findViewById(R.id.editTitre);
        editDescription = findViewById(R.id.editDescription);
        editDate = findViewById(R.id.editDate);
        switchDark = findViewById(R.id.switchDark);
        btnSave = findViewById(R.id.btnSave);

        // référence Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("notes");

        // restaurer les données sauvegardées
        restoreData();

        // première ouverture
        checkFirstTime();

        // configurer DatePicker
        setupDatePicker();

        // validation dynamique
        setupValidation();

        // Dark Mode
        setupDarkMode();

        // bouton Enregistrer
        setupSaveButton();
    }

    // RESTAURATION DES DONNEES
    private void restoreData() {
        editTitre.setText(prefs.getString("titre",""));
        editDescription.setText(prefs.getString("description",""));
        editDate.setText(prefs.getString("date",""));
        switchDark.setChecked(prefs.getBoolean("darkMode", false));
        validateFields();
    }

    // PREMIERE OUVERTURE
    private void checkFirstTime() {
        boolean firstTime = prefs.getBoolean("firstTime", true);
        if(firstTime){
            Toast.makeText(this,"Bienvenue dans SmartNotes !", Toast.LENGTH_LONG).show();
            prefs.edit().putBoolean("firstTime", false).apply();
        }
    }

    // DATE PICKER
    private void setupDatePicker() {
        editDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        editDate.setText(date);
                        saveFormData();
                        validateFields();
                    }, year, month, day
            );
            datePicker.show();
        });
    }

    // VALIDATION DYNAMIQUE
    private void setupValidation() {
        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFields();
                saveFormData();
            }
            @Override public void afterTextChanged(Editable s) {}
        };
        editTitre.addTextChangedListener(watcher);
        editDescription.addTextChangedListener(watcher);
    }
    //verifier si les champs n'ont pas vide
    private void validateFields() {
        boolean valid = !editTitre.getText().toString().isEmpty() &&
                !editDescription.getText().toString().isEmpty() &&
                !editDate.getText().toString().isEmpty();
        btnSave.setEnabled(valid);
    }

    // SAUVEGARDE AUTOMATIQUE
    private void saveFormData() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("titre", editTitre.getText().toString());
        editor.putString("description", editDescription.getText().toString());
        editor.putString("date", editDate.getText().toString());
        editor.apply();
    }

    // DARK MODE
    private void setupDarkMode() {
        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("darkMode", isChecked).apply();
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    // ENREGISTREMENT SUR FIREBASE
    private void setupSaveButton() {
        btnSave.setOnClickListener(v -> {
            String titre = editTitre.getText().toString();
            String description = editDescription.getText().toString();
            String date = editDate.getText().toString();

            Note note = new Note(titre, description, date);
            databaseReference.push().setValue(note);

            Toast.makeText(this,"Note enregistrée!", Toast.LENGTH_SHORT).show();
            clearForm();
        });
    }

    private void clearForm() {
        editTitre.setText("");
        editDescription.setText("");
        editDate.setText("");
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("titre");
        editor.remove("description");
        editor.remove("date");
        editor.apply();
        validateFields();
    }
}