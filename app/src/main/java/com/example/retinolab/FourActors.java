package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FourActors extends AppCompatActivity {

    Button mPatientBtn, mBenevoleBtn, mAdminBtn, mMedecinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_actors);

        mPatientBtn = findViewById(R.id.patientBtn);
        mBenevoleBtn = findViewById(R.id.benevoleBtn);
        mAdminBtn = findViewById(R.id.adminBtn);
        mMedecinBtn = findViewById(R.id.medecinBtn);


        mPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));

            }
        });

        mAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginAdmin.class));

            }
        });

        mMedecinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginMedecin.class));

            }
        });

        mBenevoleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginBenevole.class));

            }
        });



    }
}