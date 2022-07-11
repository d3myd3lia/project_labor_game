package com.example.thegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class games extends AppCompatActivity {
    //The Firebase Firestore data save file has been deleted
    TextView nev_helye;
    String nev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        nev_helye = findViewById(R.id.nev_helye);
        nev = getIntent().getStringExtra("Name");
        nev_helye.setText(nev);
        configureGameCw1();
        configureGameCw2();
        configureGameCw3();
        configureGameCw4();
        configurebackButton2();
    }

    public void configureGameCw1(){
        CardView gameCw = findViewById(R.id.game1Cw);
        gameCw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), game1.class);
                i.putExtra("Name", nev);
                startActivity(i);
            }
        });
    }

    public void configureGameCw2(){
        CardView gameCw = findViewById(R.id.game2Cw);
        gameCw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), game2.class);
                i.putExtra("Name", nev);
                startActivity(i);
            }
        });
    }

    public void configureGameCw3(){
        CardView gameCw = findViewById(R.id.game3Cw);
        gameCw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), game3.class);
                i.putExtra("Name", nev);
                startActivity(i);

            }
        });
    }

    public void configureGameCw4(){
        CardView gameCw = findViewById(R.id.game4Cw);
        gameCw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), game4.class);
                i.putExtra("Name", nev);
                startActivity(i);
            }
        });
    }

    private void configurebackButton2(){
        Button backButton2 = (Button) findViewById(R.id.backButton2);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}