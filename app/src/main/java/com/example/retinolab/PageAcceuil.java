package com.example.retinolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageAcceuil extends AppCompatActivity {

    Button mChat, mFours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil);

        mChat = findViewById(R.id.chatbotBtn);
        mFours = findViewById(R.id.foursBtn);


        mChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FourActors.class));

            }
        });

        mFours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FourActors.class));

            }
        });


    }
}