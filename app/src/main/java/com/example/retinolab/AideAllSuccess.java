package com.example.retinolab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.retinolab.R.id.recyclerView;

public class AideAllSuccess  extends AppCompatActivity {

    private static final String TAG = "RequestList";

    public RecyclerView rvrequest;
    private List<AideNew> listeAide;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String patientID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide_success);
        listeAide = new ArrayList<>();
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


                                fStore.collection("DemandeAide").document(patientID).collection("AideP").get()

                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                for (DocumentSnapshot document : task.getResult()){

                                                    AideNew aideObject = new AideNew();

                                                    Map<String,Object> aide = document.getData();
                                                    aideObject.setNameEnf((String) aide.get("Nom de l'enfant"));
                                                    aideObject.setNameTut((String) aide.get("Nom du tuteur"));
                                                    aideObject.setEmail((String) aide.get("Email"));
                                                    aideObject.setPhone((String) aide.get("GSM"));
                                                    aideObject.setMsg((String) aide.get("Msg"));

                                                    listeAide.add(aideObject);


                                                }

                                                Log.d("Liste Size", listeAide.size()+"");
                                                AideAdapter adapter = new AideAdapter(AideAllSuccess.this, listeAide);

                                                rvrequest.setAdapter(adapter);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AideAllSuccess.this);
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
