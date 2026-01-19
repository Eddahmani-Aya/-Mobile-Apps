package com.example.tp_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

        Spinner spinnerSalle ;
        Button btnManage ;
         String typeSalle = "";
         @Override
         protected void onCreate ( Bundle savedInstanceState ) {
            super . onCreate ( savedInstanceState ) ;
            setContentView (R.layout.activity_dashboard ) ;
            spinnerSalle = findViewById (R.id.spinnerSalle ) ;
            btnManage = findViewById (R.id.btnManage );
            //      D o n n e s du Spinner
            String [] salles = {"TD", "TP", " Amphi "};
            //       Adapter
             ArrayAdapter< String > adapter = new ArrayAdapter < >(
                     this ,
                     android . R . layout . simple_spinner_item ,
                     salles
             ) ;
             adapter.setDropDownViewResource (
                     android . R . layout . simple_spinner_dropdown_item
             ) ;
             //      Lier Spinner Adapter
             spinnerSalle.setAdapter ( adapter ) ;
             //      Listener
             spinnerSalle . setOnItemSelectedListener (
                      new AdapterView.OnItemSelectedListener () {
                      @Override
                      public void onItemSelected (
                            AdapterView<? > parent ,
                            View view ,
                            int position ,
                            long id ) {
                        typeSalle = parent
                        . getItemAtPosition ( position )
                        . toString () ;
                      }
                      @Override
                      public void onNothingSelected (
                              AdapterView <? > parent ) {
                      }
             }) ;
             btnManage . setOnClickListener ( v -> {
                  Intent i = new Intent (
                         DashboardActivity .this ,
                          SalleActivity.class
                  ) ;
                  i.putExtra (" typeSalle ", typeSalle ) ;
                  startActivity ( i ) ;
             }) ;

         }
}