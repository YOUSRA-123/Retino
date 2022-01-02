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

public class DemandeConsultation extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText NomP, EmailP, GsmP, date;
    Button SubmitBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_consultation);


        NomP = findViewById(R.id.nomP);
        EmailP = findViewById(R.id.emailP);
        GsmP = findViewById(R.id.gsmP);
        date = findViewById(R.id.date_picker);
        SubmitBtn = findViewById(R.id.submit);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);


        date.setInputType(InputType.TYPE_NULL);

        date.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                showDateDialog(date);
            }
        });

        SubmitBtn.setOnClickListener((v)-> {

            final String nomP = NomP.getText().toString();
            final String emailP = EmailP.getText().toString().trim();
            String gsmP = GsmP.getText().toString().trim();
            final String date_picker = date.getText().toString();

            if (TextUtils.isEmpty(nomP)) {
                NomP.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(emailP)) {
                EmailP.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(gsmP)) {
                GsmP.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(date_picker)) {
                date.setError("Ce champ est obligatoire");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

                    Toast.makeText(DemandeConsultation.this, "Demande faite avec succès", Toast.LENGTH_SHORT).show();
                    patientID = fAuth.getCurrentUser().getUid();// id of the patient
                    DocumentReference documentReference = fStore.collection("DemandeConsultation").document(patientID).collection("ConsultationsP").document();

                    Map<String,Object> patient = new HashMap<>();
                    patient.put("NomPatient",nomP);
                    patient.put("EmailPatient",emailP);
                    patient.put("Phone",gsmP);
                    patient.put("DateCons",date_picker);

                    documentReference.set(patient).addOnSuccessListener( (OnSuccessListener) (aVoid) -> {
                        Log.d(TAG, "onSuccess:  Patient is created for: "+ patientID);
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());

                        }
                    });
                    startActivity(new Intent(getApplicationContext(), DemandeConsSuccess.class));
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));


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

        new DatePickerDialog( DemandeConsultation.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }



    }
