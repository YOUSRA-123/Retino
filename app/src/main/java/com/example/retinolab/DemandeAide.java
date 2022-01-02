package com.example.retinolab;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DemandeAide extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText NomEnf, NomTut, EmailTut, GsmTut, Msg;
    Button SubmitBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String patientID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_aide);


        NomEnf = findViewById(R.id.nomEnf);
        EmailTut = findViewById(R.id.emailTut);
        GsmTut = findViewById(R.id.gsmTut);
        NomTut = findViewById(R.id.nomTut);
        Msg = findViewById(R.id.msg);
        SubmitBtn = findViewById(R.id.submit);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);


        SubmitBtn.setOnClickListener((v)-> {

            final String nomEnf = NomEnf.getText().toString();
            final String nomTut = NomTut.getText().toString();
            final String emailTut = EmailTut.getText().toString().trim();
            String gsmTut = GsmTut.getText().toString().trim();
            String msg = Msg.getText().toString().trim();

            if (TextUtils.isEmpty(nomEnf)) {
                NomEnf.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(nomTut)) {
                NomTut.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(emailTut)) {
                EmailTut.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(gsmTut)) {
                GsmTut.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(msg)) {
                Msg.setError("Ce champ est obligatoire");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            Toast.makeText(DemandeAide.this, "Demande faite avec succès", Toast.LENGTH_SHORT).show();
            patientID = fAuth.getCurrentUser().getUid();// id of the patient
            DocumentReference documentReference = fStore.collection("DemandeAide").document(patientID).collection("AideP").document();


            Map<String,Object> patient = new HashMap<>();
            patient.put("Nom de l'enfant",nomEnf);
            patient.put("Nom du tuteur",nomTut);
            patient.put("Email",emailTut);
            patient.put("GSM",gsmTut);
            patient.put("Msg",msg);

            documentReference.set(patient).addOnSuccessListener( (OnSuccessListener) (aVoid) -> {
                Log.d(TAG, "onSuccess:  Patient is created for: "+ patientID);
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());

                }
            });
            startActivity(new Intent(getApplicationContext(), AideSuccess.class));

        });
    }

}
