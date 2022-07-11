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

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class game3 extends AppCompatActivity {

    String name;
    int min = 1;
    int max = 10;
    int random1 = 0;
    int random2 = 0;
    int progressState = 0;
    int progressValue = 10;
    boolean kacsa;
    boolean egyenlo = false;
    Integer game3;

    public Integer getGame3() {
        return game3;
    }

    public void setGame3(Integer game3) {
        this.game3 = game3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        name = getIntent().getStringExtra("Name");
        TextView szam1Tv = findViewById(R.id.szam1Tv);
        random1 = new Random().nextInt((max - min) + 1) + min;
        szam1Tv.setText("" + random1);
        TextView szam2Tv = findViewById(R.id.szam2Tv);
        random2 = new Random().nextInt((max - min) + 1) + min;
        szam2Tv.setText("" + random2);


        configurebackButton();
        configurebalrakacsaBtn();
        configurejobbrakacsaBtn();
        configureegyenloBtn();
        configureCBtn();
        configureOKBtn();

    }

    public void Update(String n){

        DocumentReference segedValt;
        segedValt = FirebaseFirestore.getInstance().collection("Player").document(n);

        segedValt.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()){
                Integer s = Integer.valueOf(String.valueOf(documentSnapshot.getString("game3")));
                setGame3(s);
            }
        });

        segedValt.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Integer ponto = getGame3() + 1;
                segedValt.update("game3",String.valueOf(ponto));

            } else {
                Log.d("TAG", "Failed with: ", task.getException());
            }
        });

    }

    public void GameOverPop(){
        AlertDialog.Builder alertD = new AlertDialog.Builder(game3.this);
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

    private void configurebackButton(){
        Button backButton = (Button) findViewById(R.id.kilepesBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });
    }

    private void configurebalrakacsaBtn(){
        Button balrakacsaBtn = (Button) findViewById(R.id.balrakacsaBtn);
        balrakacsaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText(">");
                kacsa = true;
            }
        });
    }

    private void configurejobbrakacsaBtn(){
        Button jobbrakacsaBtn = (Button) findViewById(R.id.jobbrakacsaBtn);
        jobbrakacsaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText("<");
                kacsa = false;
            }
        });
    }

    private void configureegyenloBtn(){
        Button egyenloBtn = (Button) findViewById(R.id.egyenloBtn);
        egyenloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText("=");
            }
        });
    }

    private void configureCBtn(){
        Button bC = (Button) findViewById(R.id.bC);
        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText("");
            }
        });
    }

    private void configureOKBtn(){
        Button bOK = (Button) findViewById(R.id.bOK);
        bOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView szam1Tv = findViewById(R.id.szam1Tv);
                TextView szam2Tv = findViewById(R.id.szam2Tv);
                TextView muveletTv = findViewById(R.id.muveletTv);
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);

                if (muveletTv == null){
                    Toast.makeText(getBaseContext(), "Üres a mező!",
                            Toast.LENGTH_LONG).show();

                }
                else{
                    if (random1 > random2 && kacsa == true) {
                        Toast.makeText(getBaseContext(), "Helyes válasz!",
                                Toast.LENGTH_LONG).show();
                        random1 = new Random().nextInt((max - min) + 1) + min;
                        szam1Tv.setText("" + random1);
                        random2 = new Random().nextInt((max - min) + 1) + min;
                        szam2Tv.setText("" + random2);
                        progressBar.setProgress(progressState += progressValue);
                        if(progressState == 100){
                            GameOverPop();
                        }
                    }
                    else if(random1 > random2 && kacsa == false){
                        Toast.makeText(getBaseContext(), "Helytelen válasz!",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(random1 < random2 && kacsa == false){
                        Toast.makeText(getBaseContext(), "Helyes válasz!",
                                Toast.LENGTH_LONG).show();
                        random1 = new Random().nextInt((max - min) + 1) + min;
                        szam1Tv.setText("" + random1);
                        random2 = new Random().nextInt((max - min) + 1) + min;
                        szam2Tv.setText("" + random2);
                        progressBar.setProgress(progressState += progressValue);
                        if(progressState == 100){
                            GameOverPop();
                        }
                    }
                    else if(random1 < random2 && kacsa == true){
                        Toast.makeText(getBaseContext(), "Helytelen válasz!",
                                Toast.LENGTH_LONG).show();

                    }
                    else if(random1 == random2){
                        egyenlo = true;
                        Toast.makeText(getBaseContext(), "Helyes válasz!",
                                Toast.LENGTH_LONG).show();
                        random1 = new Random().nextInt((max - min) + 1) + min;
                        szam1Tv.setText("" + random1);
                        random2 = new Random().nextInt((max - min) + 1) + min;
                        szam2Tv.setText("" + random2);
                        progressBar.setProgress(progressState += progressValue);
                        if(progressState == 100){
                            GameOverPop();
                        }
                    }

                    else{
                        Toast.makeText(getBaseContext(), "Helytelen válasz!",
                                Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
}