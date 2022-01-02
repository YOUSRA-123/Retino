package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListeAide extends AppCompatActivity {

    Button ListeAide, Demande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_aide);

        ListeAide = findViewById(R.id.laide);
        Demande = findViewById(R.id.daide);


        ListeAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AideSuccess.class));

            }
        });

        Demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandeAide.class));

            }
        });
    }
}