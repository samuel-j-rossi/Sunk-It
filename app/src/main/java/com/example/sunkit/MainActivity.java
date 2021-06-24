package com.example.sunkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    Button barBTN, pieBTN;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //setting up buttons
        // rules button
        Button rulesBTN = findViewById(R.id.rulesButton);
        rulesBTN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentRules = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intentRules);
            }
        });
        // leaderboard button
        Button leadBTN = findViewById(R.id.leaderButton);
        leadBTN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentLead = new Intent(MainActivity.this, LeaderBoardActivity.class);
                startActivity(intentLead);
            }
        });

        // Play Button
        Button playBTN = findViewById(R.id.playButton);
        playBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intentPlay = new Intent(MainActivity.this, AddPlayerActivity.class);
                startActivity(intentPlay);
            }
        });

        // Pie Chart
        pieBTN = findViewById(R.id.pieButton);
        // adding clicklistener
        pieBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intentPie = new Intent(MainActivity.this, pieActivity.class);
                startActivity(intentPie);
            }
        });

        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        barBTN = findViewById(R.id.barBTN);


        barBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intentBar = new Intent(MainActivity.this, BarGraphActivity.class);
                startActivity(intentBar);
            }
        });

        Button lossBTN = findViewById(R.id.buttonBarLoss);
        // adding clicklistener
        lossBTN.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v)
                {
                    Intent intentLoss = new Intent(MainActivity.this, LossGraphActivity.class);
                    startActivity(intentLoss);
                }

        });


    }
}




