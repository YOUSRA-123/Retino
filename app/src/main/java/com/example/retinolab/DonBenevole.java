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

    public class DonBenevole extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText NomB, PrenB, NomP, MontB;
    Button SubmitBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String benevoleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_benevole);

        NomB = findViewById(R.id.nomB);
        NomP = findViewById(R.id.nomP);
        PrenB = findViewById(R.id.prenB);
        MontB = findViewById(R.id.montantB);

        SubmitBtn = findViewById(R.id.submit);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);




        SubmitBtn.setOnClickListener((v)-> {

            final String nomP = NomP.getText().toString();
            final String nomB = NomB.getText().toString().trim();
            String prenB = PrenB.getText().toString().trim();
            final String montantB = MontB.getText().toString();

            if (TextUtils.isEmpty(nomP)) {
                NomP.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(prenB)) {
                PrenB.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(montantB)) {
                MontB.setError("Ce champ est obligatoire");
                return;
            }

            if (TextUtils.isEmpty(nomB)) {
                NomB.setError("Ce champ est obligatoire");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            Toast.makeText(DonBenevole.this, "Don fait avec succès", Toast.LENGTH_SHORT).show();
            benevoleID = fAuth.getCurrentUser().getUid();// id of the patient
            DocumentReference documentReference = fStore.collection("Don").document(benevoleID);

            Map<String,Object> benevole = new HashMap<>();
            benevole.put("NomBenevole",nomB);
            benevole.put("PrenomBenevole",prenB);
            benevole.put("NomPatient",nomP);
            benevole.put("Montant",montantB);

            documentReference.set(benevole).addOnSuccessListener( (OnSuccessListener) (aVoid) -> {
                Log.d(TAG, "onSuccess:  Don fait pour: "+ benevoleID);
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());

                }
            });
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });

    }

}
