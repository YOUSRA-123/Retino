package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceuilAdmin extends AppCompatActivity {

    Button Aide, Emploi, Cons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_admin);

        Aide = findViewById(R.id.aAide);
        Emploi = findViewById(R.id.aEmploi);
        Cons = findViewById(R.id.aCons);


        Aide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AideAllSuccess.class));
            }
        });

        Emploi.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmploiAdmin.class));
            }
        }));

        Cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ConsAllSuccess.class));
            }
        });


    }
}