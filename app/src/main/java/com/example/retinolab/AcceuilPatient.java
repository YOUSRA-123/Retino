package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AcceuilPatient extends AppCompatActivity {


    Button ConsBtn, PmtBtn, AideBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_patient);

        ConsBtn = findViewById(R.id.pCons);
        PmtBtn = findViewById(R.id.pPaiement);
        AideBtn = findViewById(R.id.pAide);

        ConsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListeConsPatient.class));

            }
        });

        PmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PaypalTest.class));

            }
        });

        AideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListeAide.class));

            }
        });


    }
}