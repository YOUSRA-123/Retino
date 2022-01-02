package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceuilMedecin extends AppCompatActivity {

    Button Emploi, Consultation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_medecin);

        Consultation = findViewById(R.id.mCons);
        Emploi = findViewById(R.id.mEmploi);


        Consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ConsAllSuccess.class));
            }
        });

        Emploi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EmploiMed.class));
            }
        });

    }
}