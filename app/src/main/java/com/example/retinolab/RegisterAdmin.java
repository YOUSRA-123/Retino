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
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterAdmin extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String AdminID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener((v)-> {

            final String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            final String fullName = mFullName.getText().toString();
            final String phone = mPhone.getText().toString();

            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email is required");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password is required");
                return;
            }

            if (password.length() < 6) {
                mPassword.setError("Password must be >=6 Characters");
            }

            progressBar.setVisibility(View.VISIBLE);

            //register the user in firebase

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task)-> {

                if(task.isSuccessful()){

                    Toast.makeText(RegisterAdmin.this, "User Created.", Toast.LENGTH_SHORT).show();
                    AdminID = fAuth.getCurrentUser().getUid();// id of the admin
                    DocumentReference documentReference = fStore.collection("Admin").document(AdminID);

                    Map<String,Object> admin = new HashMap<>();
                    admin.put("fName",fullName);
                    admin.put("email",email);
                    admin.put("phone",phone);
                    documentReference.set(admin).addOnSuccessListener( (OnSuccessListener) (aVoid) -> {
                        Log.d(TAG, "onSuccess:  Admin is created for: "+ AdminID);
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), LoginAdmin.class));

                }else{
                    Toast.makeText(RegisterAdmin.this, "Error !" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        });

        mLoginBtn.setOnClickListener((v) -> {

            startActivity(new Intent(getApplicationContext(), LoginAdmin.class));
        });

    }
}