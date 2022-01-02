package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceuilBenevole extends AppCompatActivity {

    Button Aide, Don;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_benevole);

        Aide = findViewById(R.id.bAide);
        Don = findViewById(R.id.bDon);


        Aide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AideAllSuccess.class));
            }
        });

        Don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PaypalTest.class));
            }
        });

    }
}