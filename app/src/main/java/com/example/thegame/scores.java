package com.example.thegame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class scores extends AppCompatActivity {
    //The Firebase Firestore data save file has been deleted
    public ImageView g3cs1, g3cs2, g3cs3, g3cs4, g3cs5, g2cs1, g2cs2, g2cs3, g2cs4, g2cs5, g4cs1, g4cs2, g4cs3, g4cs4, g4cs5, g1cs1, g1cs2, g1cs3, g1cs4, g1cs5;
    public TextView nev_helye;
    public String nev, game3, game1, game2, game4;
    public Integer g3, g1, g2, g4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        g1cs1 = findViewById(R.id.g1CS1);
        g1cs2 = findViewById(R.id.g1CS2);
        g1cs3 = findViewById(R.id.g1CS3);
        g1cs4 = findViewById(R.id.g1CS4);
        g1cs5 = findViewById(R.id.g1CS5);
        g2cs1 = findViewById(R.id.g2CS1);
        g2cs2 = findViewById(R.id.g2CS2);
        g2cs3 = findViewById(R.id.g2CS3);
        g2cs4 = findViewById(R.id.g2CS4);
        g2cs5 = findViewById(R.id.g2CS5);
        g3cs1 = findViewById(R.id.g3CS1);
        g3cs2 = findViewById(R.id.g3CS2);
        g3cs3 = findViewById(R.id.g3CS3);
        g3cs4 = findViewById(R.id.g3CS4);
        g3cs5 = findViewById(R.id.g3CS5);
        g4cs1 = findViewById(R.id.g4CS1);
        g4cs2 = findViewById(R.id.g4CS2);
        g4cs3 = findViewById(R.id.g4CS3);
        g4cs4 = findViewById(R.id.g4CS4);
        g4cs5 = findViewById(R.id.g4CS5);
        //game1 = findViewById(R.id.game1C);
        //game2 = findViewById(R.id.game2C);
        //game3 = findViewById(R.id.game3C);
        nev_helye = findViewById(R.id.nevTv);
        nev = getIntent().getStringExtra("Name");
        nev_helye.setText(nev);
        AdatLekér(nev);
        Vissza();
    }

    public void Vizsgalas(Integer i, ImageView cs1, ImageView cs2, ImageView cs3, ImageView cs4, ImageView cs5){
        if(i == 0){
            Toast.makeText(getBaseContext(), "Jelenleg még nincsen csillagod", Toast.LENGTH_LONG).show();
        }else if (i == 1){
            cs1.setVisibility(View.VISIBLE);
        }else if(i == 2){
            cs1.setVisibility(View.VISIBLE);
            cs2.setVisibility(View.VISIBLE);
        }else if(i == 3){
            cs1.setVisibility(View.VISIBLE);
            cs2.setVisibility(View.VISIBLE);
            cs3.setVisibility(View.VISIBLE);
        }else if(i == 4){
            cs1.setVisibility(View.VISIBLE);
            cs2.setVisibility(View.VISIBLE);
            cs3.setVisibility(View.VISIBLE);
            cs4.setVisibility(View.VISIBLE);
        }else if (i == 5){
            cs1.setVisibility(View.VISIBLE);
            cs2.setVisibility(View.VISIBLE);
            cs3.setVisibility(View.VISIBLE);
            cs4.setVisibility(View.VISIBLE);
            cs5.setVisibility(View.VISIBLE);
        }
    }

    public void AdatLekér(String n){
        DocumentReference segedValt;
        segedValt = FirebaseFirestore.getInstance().collection("Player").document(n);

        segedValt.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                //game1.setText(value.getString("game1"));
                //game2.setText(value.getString("game2"));
                //game3.setText(value.getString("game3"));
                game1 = value.getString("game1");
                g1 = Integer.valueOf(game1);
                game2 = value.getString("game2");
                g2 = Integer.valueOf(game2);
                game3 = value.getString("game3");
                g3 = Integer.valueOf(game3);
                game4 = value.getString("game4");
                g4 = Integer.valueOf(game4);

                Vizsgalas(g1, g1cs1, g1cs2, g1cs3, g1cs4, g1cs5);
                Vizsgalas(g2, g2cs1, g2cs2, g2cs3, g2cs4, g2cs5);
                Vizsgalas(g3, g3cs1, g3cs2, g3cs3, g3cs4, g3cs5);
                Vizsgalas(g4, g4cs1, g4cs2, g4cs3, g4cs4, g4cs5);
            }
        });
    }

    public void Vissza(){
        Button backBtn = (Button) findViewById(R.id.VisszaBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}