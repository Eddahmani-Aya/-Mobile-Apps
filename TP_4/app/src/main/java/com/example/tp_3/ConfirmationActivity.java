package com.example.tp_3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmationActivity extends AppCompatActivity {
     TextView tvResume = findViewById ( R.id.tvResume );
     @Override
protected void onCreate ( Bundle savedInstanceState ) {
        super . onCreate ( savedInstanceState ) ;
        setContentView ( R . layout . activity_confirmation ) ;

         boolean video = getIntent () . getBooleanExtra (" video ", false ) ;
        boolean clim = getIntent () . getBooleanExtra (" clim ", false ) ;
        boolean wifi = getIntent () . getBooleanExtra (" wifi ", false ) ;

        tvResume . setText (
                " V i d o p r o j e c t e u r : " + video +
                        "\nClimatisation : " + clim +
                        "\nWiFi : " + wifi
                ) ;
        }
}
