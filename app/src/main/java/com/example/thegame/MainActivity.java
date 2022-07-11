package com.example.thegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //The Firebase Firestore data save file has been deleted
    String nev;
    TextView nev_helye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nev_helye = findViewById(R.id.nev_helye);
        nev = getIntent().getStringExtra("Name");
        //Toast.makeText(this, nev , Toast.LENGTH_SHORT).show();
        nev_helye.setText(nev);

        configurenewgameCW();
        configureScoresCw();
        configureExitCw();
    }

    private void configurenewgameCW(){
        CardView newgameCW = (CardView) findViewById(R.id.newgameCw);
        newgameCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), games.class);
                i.putExtra("Name", nev);
                startActivity(i);
            }
        });
    }

    public void configureScoresCw(){
        CardView scoresCw = findViewById(R.id.scoresCw);
        scoresCw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), scores.class);
                i.putExtra("Name", nev);
                startActivity(i);
            }
        });
    }

    public void configureExitCw(){
        CardView exitCw = findViewById(R.id.exitCw);
        exitCw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });
    }
}