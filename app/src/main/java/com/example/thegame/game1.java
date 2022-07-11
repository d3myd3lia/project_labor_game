package com.example.thegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class game1 extends AppCompatActivity {

    String name;
    int min = 1;
    int max = 20;
    int random1 = 0, random2 = 0, eredm = 0;
    int RandOperation;
    int rand_min=0, rand_max=3;
    int progressState = 0;
    int progressValue = 10;
    Integer game1;

    public Integer getGame1() {
        return game1;
    }

    public void setGame1(Integer game1) {
        this.game1 = game1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        name = getIntent().getStringExtra("Name");
        //Toast.makeText(this, name , Toast.LENGTH_SHORT).show();
        Szamolas();
        configurebackButton();
        configureB1();
        configureB2();
        configureB3();
        configureB4();
        configureB5();
        configureB6();
        configureB7();
        configureB8();
        configureB9();
        configureB0();
        configureCButton();
        configureBOK();
    }

    public void Szamolas(){

        TextView muveletTv = findViewById(R.id.muveletTv);

        TextView szam1Tv = findViewById(R.id.szam1Tv);
        random1 = new Random().nextInt((max - min) + 1) + min;
        szam1Tv.setText("" + random1);

        TextView szam2Tv = findViewById(R.id.szam2Tv);
        random2 = new Random().nextInt((max - min) + 1) + min;
        szam2Tv.setText("" + random2);

        RandOperation = new Random().nextInt((rand_max - rand_min) + 1) + rand_min;

        if(RandOperation == 0) {         //összeadás  ha = 0
            muveletTv.setText("+");
            eredm = random1 + random2;

        }
        else if(RandOperation == 1){   //kivonás ha = 1
            if(random1 < random2){
                int seged = random1;
                random1 = random2;
                random2 = seged;
            }
            szam1Tv.setText("" + random1);
            szam2Tv.setText("" + random2);
            muveletTv.setText("-");
            eredm = random1 - random2;

        }
        else if(RandOperation == 2){   //szorzás ha = 2
            muveletTv.setText("*");
            eredm = random1 * random2;
        }
        else if(RandOperation == 3){   //osztás ha = 3
            while(random1 % random2 != 0 && random2 % random1 != 0){
                random1 = new Random().nextInt((max - min) + 1) + min;
                szam1Tv.setText("" + random1);
                random2 = new Random().nextInt((max - min) + 1) + min;
                szam2Tv.setText("" + random2);
            }

            if(random1 < random2){
                int seged = random1;    //csere
                random1 = random2;
                random2 = seged;
            }
            eredm = random1 / random2;

            szam1Tv.setText("" + random1);
            szam2Tv.setText("" + random2);
            muveletTv.setText("/");
            /*if(random1 % random2 == 0) {
                eredm = random1 / random2;
            }
            else{
                random1 = new Random().nextInt((max - min) + 1) + min;
                szam1Tv.setText("" + random1);
                random2 = new Random().nextInt((max - min) + 1) + min;
                szam2Tv.setText("" + random2);
            }*/
        }
        else{ muveletTv.setText("HIBA"); }
    }

    public void Update(String n){

        DocumentReference segedValt;
        segedValt = FirebaseFirestore.getInstance().collection("Player").document(n);

        segedValt.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()){
                //setGame1(Integer.valueOf(documentSnapshot.getString("game1")));
                Integer s = Integer.valueOf(String.valueOf(documentSnapshot.getString("game1")));
                setGame1(s);
            }
        });

        segedValt.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                    Integer ponto = getGame1() + 1;
                    segedValt.update("game1",String.valueOf(ponto));


            } else {
                Log.d("TAG", "Failed with: ", task.getException());
            }
        });

    }

    public void configurebackButton(){
        Button backButton = (Button) findViewById(R.id.kilepesBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });
    }

    public void configureB1(){
        Button B1Button = (Button) findViewById(R.id.b1);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "1" );

            }
        });
    }

    public void configureB2(){
        Button B1Button = (Button) findViewById(R.id.b2);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "2" );

            }
        });
    }

    public void configureB3(){
        Button B1Button = (Button) findViewById(R.id.b3);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "3" );

            }
        });
    }

    public void configureB4(){
        Button B1Button = (Button) findViewById(R.id.b4);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "4" );

            }
        });
    }

    public void configureB5(){
        Button B1Button = (Button) findViewById(R.id.b5);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "5" );

            }
        });
    }

    public void configureB6(){
        Button B1Button = (Button) findViewById(R.id.b6);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "6" );

            }
        });
    }

    public void configureB7(){
        Button B1Button = (Button) findViewById(R.id.b7);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "7" );

            }
        });
    }

    public void configureB8(){
        Button B1Button = (Button) findViewById(R.id.b8);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "8" );

            }
        });
    }

    public void configureB9(){
        Button B1Button = (Button) findViewById(R.id.b9);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "9" );

            }
        });
    }

    public void configureB0(){
        Button B1Button = (Button) findViewById(R.id.b0);
        B1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                eredmenyTv.setText(eredmenyTv.getText() + "0" );

            }
        });
    }

    public void configureCButton(){
        Button CButton = (Button) findViewById(R.id.bC);
        CButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                //String s = eredmenyTv.getText().toString();
                //s = s.substring(0, s.length() - 1);
                //eredmenyTv.setText(s);
                eredmenyTv.setText("");
            }
        });
    }

    public void configureBOK(){
        Button BOK = (Button) findViewById(R.id.bOK);
        BOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                ProgressBar progressBar = findViewById(R.id.progressBar);
                int seged2 = Integer.parseInt(String.valueOf(eredmenyTv.getText()));

                if(seged2 == eredm){
                    //ekkor menjen feljebb a progressBar
                    //ekkor válaszolt JÓL
                    Toast.makeText(getBaseContext(), "Helyes válasz!", Toast.LENGTH_LONG).show();

                    progressBar.setProgress(progressState += progressValue);

                    if(progressState == 100){
                        AlertDialog.Builder alertD = new AlertDialog.Builder(game1.this);
                        alertD.setCancelable(false);
                        alertD.setTitle("Gratulálunk!");
                        alertD.setIcon(R.drawable.ic_baseline_check_circle_24);
                        alertD.setMessage("Sikeresen megcsináltad a feladatot!");
                        alertD.setPositiveButton("Köszönöm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { finish(); }
                        });
                        alertD.show();
                        Update(name);
                    }
                    Szamolas();

                }else if(seged2 != eredm){
                    //ekkor volt ROSSZ a válasz
                    Toast.makeText(getBaseContext(), "Helytelen válasz!", Toast.LENGTH_LONG).show();
                }
                eredmenyTv.setText("");
            }
        });
    }

}