package com.example.sunkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);


        // setting up Home Button
        Button homeButton = findViewById(R.id.buttonHomeLead);
        // adding clicklistener
        homeButton.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v)
        {
            Intent intent = new Intent(RulesActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });
    }
}




