package com.example.retinolab;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Paiement extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText NomT, NumC, NumCvv, SomP, Date;
    Button SubmitBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement);


        NomT = findViewById(R.id.nomT);
        NumC = findViewById(R.id.numC);
        NumCvv = findViewById(R.id.numCvv);
        SomP= findViewById(R.id.somP);
        Date = findViewById(R.id.date_picker);
        SubmitBtn = findViewById(R.id.submit);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);


        Date.setInputType(InputType.TYPE_NULL);

        Date.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                showDateDialog(Date);
            }
        });

        SubmitBtn.setOnClickListener((v)-> {

            final String nomT = NomT.getText().toString();
            final String numC = NumC.getText().toString().trim();
            String numCvv = NumCvv.getText().toString().trim();
            String somP = SomP.getText().toString().trim();
            final String date_picker = Date.getText().toString();

            if (TextUtils.isEmpty(nomT)) {
                NomT.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(numC)) {
                NumC.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(numCvv)) {
                NumCvv.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(somP)) {
                SomP.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(date_picker)) {
                Date.setError("Ce champ est obligatoire");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            Toast.makeText(Paiement.this, "Paiement fait avec succès", Toast.LENGTH_SHORT).show();
            patientID = fAuth.getCurrentUser().getUid();// id of the patient
            DocumentReference documentReference = fStore.collection("Paiement").document(patientID);

            Map<String,Object> patient = new HashMap<>();
            patient.put("Nom du Tuteur",nomT);
            patient.put("Numéro de la carte",numC);
            patient.put("Numéro Cvv",numCvv);
            patient.put("Somme",somP);
            patient.put("Date d'expiration",date_picker);

            documentReference.set(patient).addOnSuccessListener( (OnSuccessListener) (aVoid) -> {
                Log.d(TAG, "onSuccess:  Paiement fait pour: "+ patientID);
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());

                }
            });
            startActivity(new Intent(getApplicationContext(), PaiementSuccess.class));

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDateDialog(EditText date) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener =  new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
                date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog( Paiement.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }



}
