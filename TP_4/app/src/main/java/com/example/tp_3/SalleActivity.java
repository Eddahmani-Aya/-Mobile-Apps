package com.example.tp_3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SalleActivity extends AppCompatActivity {
     EditText etNom ;
     Switch switchVideo ;
     CheckBox cbClim , cbWifi ;
     Button btnValider = findViewById (R.id.btnValider );
    boolean video = false , clim = false , wifi = false ;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super . onCreate ( savedInstanceState ) ;
        setContentView ( R . layout . activity_salle ) ;

        etNom = findViewById ( R.id.etNomSalle ) ;
        switchVideo = findViewById ( R . id . switchVideo ) ;
        cbClim = findViewById ( R . id . cbClim ) ;
        cbWifi = findViewById ( R . id . cbWifi ) ;

        etNom . addTextChangedListener ( new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged (
                CharSequence s , int start , int before , int count ) {
            }
        }) ;
        switchVideo . setOnCheckedChangeListener (
                ( buttonView , isChecked ) -> video = isChecked ) ;
        cbClim . setOnCheckedChangeListener (
                ( buttonView , isChecked ) -> clim = isChecked ) ;
         cbWifi . setOnCheckedChangeListener (
                ( buttonView , isChecked ) -> wifi = isChecked ) ;
        btnValider . setOnClickListener ( v -> showPopup () ) ;
    }
    private void showPopup () {
        AlertDialog. Builder builder =
                new AlertDialog . Builder ( this ) ;

        builder . setTitle (" Confirmation ") ;
        builder . setMessage (" Confirmer la r s e r v a t i o n ?") ;

         builder . setPositiveButton (" Oui ", (d , w ) -> {
             Intent i = new Intent (
                SalleActivity .this ,
                     ConfirmationActivity.class
             ) ;
             i . putExtra (" video ", video );
             i . putExtra (" clim ", clim ) ;
             i . putExtra (" wifi ", wifi ) ;
             startActivity ( i ) ;
         }) ;

         builder . setNegativeButton (" Annuler ", null ) ;
         builder . show () ;
    }
}