package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListeConsPatient extends AppCompatActivity {

    Button ListeCon, Demande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_cons_patient);

        ListeCon = findViewById(R.id.lCons);
        Demande = findViewById(R.id.dCons);


        ListeCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandeConsSuccess.class));

            }
        });

        Demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandeConsultation.class));

            }
        });

    }
}