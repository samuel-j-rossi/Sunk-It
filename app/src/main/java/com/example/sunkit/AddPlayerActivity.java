package com.example.sunkit;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPlayerActivity extends AppCompatActivity {

    EditText pas, usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        usr = (EditText) findViewById(R.id.username);
        pas = (EditText) findViewById(R.id.password);
        // setting up Home Button
        Button addHome = findViewById(R.id.homeButton);
        // adding clicklistener
        addHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

        // setting up Add player

        public void loginBtn(View view) {


            String user = usr.getText().toString();
            String pass = pas.getText().toString();

            background bg = new background(this);
            bg.execute(user,pass);


        }

        public void loginBtn2(View view){

            String user = usr.getText().toString();
            String pass = pas.getText().toString();

            backgroundWin bg = new backgroundWin(this);
            bg.execute(user,pass);
        }

        public void addLossBtn(View view){

            String user = usr.getText().toString();
            String pass = pas.getText().toString();

            backgroundLoss bg = new backgroundLoss(this);
            bg.execute(user, pass);
        }

        public void addSinkBtn(View view){

            String user = usr.getText().toString();
            String pass = pas.getText().toString();

            backgroundSink bg = new backgroundSink(this);
            bg.execute(user, pass);
    }






}

