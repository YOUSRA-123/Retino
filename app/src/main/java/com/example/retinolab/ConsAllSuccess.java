package com.example.retinolab;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.retinolab.R.id.recyclerView;


public class ConsAllSuccess  extends AppCompatActivity {

    private static final String TAG = "RequestList";

    public RecyclerView rvrequest;
    private static List<DemandeConsNew> listeCons;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_all_success);
        listeCons = new ArrayList<>();
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        rvrequest = findViewById(recyclerView);

        getRequest();



    }

    private void getRequest() {


        fStore.collection("Patient").get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            Log.d("If success", task.getResult().size() +"");

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                patientID = document.getId();
                                Log.d("Patient ID", patientID+"");


                                fStore.collection("DemandeConsultation").document(patientID).collection("ConsultationsP").get()

                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                    for (DocumentSnapshot document : task.getResult()){

                                                        DemandeConsNew consObject = new DemandeConsNew();

                                                        Map<String,Object> cons = document.getData();
                                                        consObject.setName((String) cons.get("NomPatient"));
                                                        consObject.setEmail((String) cons.get("EmailPatient"));
                                                        consObject.setPhone((String) cons.get("Phone"));
                                                        consObject.setDate((String) cons.get("DateCons"));

                                                        listeCons.add(consObject);
                                                        Log.d(TAG, "Name: " + cons.get("NomPatient"));

                                                    }

                                                Log.d("Liste Size", listeCons.size()+"");
                                                ConsAllAdapter adapter = new ConsAllAdapter(ConsAllSuccess.this, listeCons);

                                                rvrequest.setAdapter(adapter);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ConsAllSuccess.this);
                                                rvrequest.setLayoutManager(linearLayoutManager);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.toString());
                                    }
                                });

                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }


                });

    }
}
