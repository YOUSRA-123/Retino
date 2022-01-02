package com.example.retinolab;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.retinolab.R.id.recyclerView;

import androidx.recyclerview.widget.RecyclerView;


public class AideSuccess extends AppCompatActivity {

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

        patientID = fAuth.getCurrentUser().getUid();// id of the patient
        fStore.collection("DemandeAide").document(patientID).collection("AideP").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {

                                AideNew aideObject = new AideNew();

                                Map<String,Object> aide = document.getData();
                                aideObject.setNameEnf((String) aide.get("Nom de l'enfant"));
                                aideObject.setNameTut((String) aide.get("Nom du tuteur"));
                                aideObject.setEmail((String) aide.get("Email"));
                                aideObject.setPhone((String) aide.get("GSM"));
                                aideObject.setMsg((String) aide.get("Msg"));


                                listeAide.add(aideObject);

                            }

                            AideAdapter adapter = new AideAdapter(AideSuccess.this, listeAide);

                            rvrequest.setAdapter(adapter);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AideSuccess.this);
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