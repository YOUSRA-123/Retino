package com.example.retinolab;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.retinolab.R.id.emaiil;
import static com.example.retinolab.R.id.recyclerView;

import androidx.recyclerview.widget.RecyclerView;

public class DemandeConsSuccess extends AppCompatActivity {

    private static final String TAG = "RequestList";

    public RecyclerView rvrequest;
    private List<DemandeConsNew> listeCons;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_cons_success);
        listeCons = new ArrayList<>();
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        rvrequest = findViewById(recyclerView);
        getRequest();

    }

    private void getRequest() {

        patientID = fAuth.getCurrentUser().getUid();// id of the patient
        fStore.collection("DemandeConsultation").document(patientID).collection("ConsultationsP").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {

                                DemandeConsNew consObject = new DemandeConsNew();

                                Map<String,Object> cons = document.getData();
                                consObject.setName((String) cons.get("NomPatient"));
                                consObject.setEmail((String) cons.get("EmailPatient"));
                                consObject.setPhone((String) cons.get("Phone"));
                                consObject.setDate((String) cons.get("DateCons"));

                                listeCons.add(consObject);

                            }

                            ConsAdapter adapter = new ConsAdapter(DemandeConsSuccess.this, listeCons);

                            rvrequest.setAdapter(adapter);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DemandeConsSuccess.this);
                            rvrequest.setLayoutManager(linearLayoutManager);
                        }
                        else
                        {
                            Log.d(TAG, "Erreur! ", task.getException());
                        }

                    }

                });
    }
}