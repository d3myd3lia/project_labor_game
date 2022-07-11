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

public class game2 extends AppCompatActivity {

    String name;
    int min = 1;
    int max = 20;
    int random1 = 0;
    int random2 = 0;
    int progressState = 0;
    int progressValue = 10;
    boolean osszkiv;
    boolean szoroszt;
    Integer game2;

    public Integer getGame2() {
        return game2;
    }

    public void setGame2(Integer game2) {
        this.game2 = game2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        name = getIntent().getStringExtra("Name");
        TextView szam1Tv = findViewById(R.id.szam1Tv);
        random1 = new Random().nextInt((max - min) + 1) + min;
        szam1Tv.setText("" + random1);

        TextView szam2Tv = findViewById(R.id.szam2Tv);
        random2 = new Random().nextInt((max - min) + 1) + min;
        szam2Tv.setText("" + random2);

        TextView eredmenyTv = findViewById(R.id.eredmenyTv);

        if(random1 > random2){
            eredmenyTv.setText("" + (random1 - random2));
        }
        else if(random1 < random2){
            eredmenyTv.setText("" + (random1 + random2));
        }
        else if(random1 == random2){
            eredmenyTv.setText("" + (random1 + random2));
        }
        else if(random1 +1 < random2){
            eredmenyTv.setText("" + (random1 * random2));
        }
        else if (random1 % random2 == 0 ){
            eredmenyTv.setText("" + (random1 / random2));
        }

        configurebackButton();
        configureBplusz();
        configureBminusz();
        configureBszorzas();
        configureBosztas();
        configureCBtn();
        configureBOK();

    }

    public void Update(String n){

        DocumentReference segedValt;
        segedValt = FirebaseFirestore.getInstance().collection("Player").document(n);

        segedValt.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()){
                Integer s = Integer.valueOf(String.valueOf(documentSnapshot.getString("game2")));
                setGame2(s);
            }
        });

        segedValt.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Integer ponto = getGame2() + 1;
                segedValt.update("game2",String.valueOf(ponto));

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

    public void configureBplusz(){
        Button Bplusz = (Button) findViewById(R.id.bplusz);
        Bplusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText("+");
                osszkiv = true;
            }
        });
    }

    public void configureBminusz(){
        Button Bminusz = (Button) findViewById(R.id.bminusz);
        Bminusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText("-");
                osszkiv = false;
            }
        });
    }

    public void configureBszorzas(){
        Button Bszorzas = (Button) findViewById(R.id.bszorzas);
        Bszorzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText("*");
                szoroszt = true;
            }
        });
    }

    public void configureBosztas(){
        Button Bosztas = (Button) findViewById(R.id.bosztas);
        Bosztas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muveletTv = findViewById(R.id.muveletTv);
                muveletTv.setText("/");
                szoroszt = false;
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

    public void configureBOK(){
        Button BOK = (Button) findViewById(R.id.bOK);
        BOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                TextView szam1Tv = findViewById(R.id.szam1Tv);
                TextView szam2Tv = findViewById(R.id.szam2Tv);
                TextView eredmenyTv = findViewById(R.id.eredmenyTv);
                ProgressBar progressBar = findViewById(R.id.progressBar);

                if (random1 > random2 && osszkiv == false) {
                    Toast.makeText(getBaseContext(), "Helyes válasz!", Toast.LENGTH_LONG).show();
                    random1 = new Random().nextInt((max - min) + 1) + min;
                    szam1Tv.setText("" + random1);
                    random2 = new Random().nextInt((max - min) + 1) + min;
                    szam2Tv.setText("" + random2);

                    progressBar.setProgress(progressState += progressValue);
                    if(progressState == 100){

                        AlertDialog.Builder alertD = new AlertDialog.Builder(game2.this);
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

                    if(random1 > random2) {
                        eredmenyTv.setText("" + (random1 - random2));
                    }
                    else if (random1 < random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if (random1 == random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if(random1 > random2 +1){
                        eredmenyTv.setText("" + (random1 * random2));
                    }
                    else if (random1 % random2 == 0 ){
                        eredmenyTv.setText("" + (random1 / random2));
                    }
                }
                else if (random1 > random2 && osszkiv == true) {
                    Toast.makeText(getBaseContext(), "Helytelen válasz!", Toast.LENGTH_LONG).show();
                }
                else if (random1 < random2 && osszkiv == true) {
                    Toast.makeText(getBaseContext(), "Helyes válasz!", Toast.LENGTH_LONG).show();
                    random1 = new Random().nextInt((max - min) + 1) + min;
                    szam1Tv.setText("" + random1);
                    random2 = new Random().nextInt((max - min) + 1) + min;
                    szam2Tv.setText("" + random2);
                    progressBar.setProgress(progressState += progressValue);
                    if(progressState == 100){

                        AlertDialog.Builder alertD = new AlertDialog.Builder(game2.this);
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

                    if(random1 > random2) {
                        eredmenyTv.setText("" + (random1 - random2));
                    }
                    else if (random1 < random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if (random1 == random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if(random1 > random2 +1){
                        eredmenyTv.setText("" + (random1 * random2));
                    }
                    else if (random1 % random2 == 0 ){
                        eredmenyTv.setText("" + (random1 / random2));
                    }
                }
                else if (random1 < random2 && osszkiv == false) {
                    Toast.makeText(getBaseContext(), "Helytelen válasz!",
                            Toast.LENGTH_LONG).show();
                }
                else if (random1 == random2 && osszkiv == true) {
                    Toast.makeText(getBaseContext(), "Helyes válasz!", Toast.LENGTH_LONG).show();
                    random1 = new Random().nextInt((max - min) + 1) + min;
                    szam1Tv.setText("" + random1);
                    random2 = new Random().nextInt((max - min) + 1) + min;
                    szam2Tv.setText("" + random2);
                    progressBar.setProgress(progressState += progressValue);
                    if(progressState == 100){

                        AlertDialog.Builder alertD = new AlertDialog.Builder(game2.this);
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

                    if(random1 > random2) {
                        eredmenyTv.setText("" + (random1 - random2));
                    }
                    else if (random1 < random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if (random1 == random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if(random1 > random2 +1){
                        eredmenyTv.setText("" + (random1 * random2));
                    }
                    else if (random1 % random2 == 0 ){
                        eredmenyTv.setText("" + (random1 / random2));
                    }
                }
                else if (random1 == random2 && osszkiv == false) {
                    Toast.makeText(getBaseContext(), "Helytelen válasz!",
                            Toast.LENGTH_LONG).show();
                }
                else if(random1 > random2 +1 && szoroszt == true){
                    Toast.makeText(getBaseContext(), "Helyes válasz!",
                            Toast.LENGTH_LONG).show();
                    random1 = new Random().nextInt((max - min) + 1) + min;
                    szam1Tv.setText("" + random1);
                    random2 = new Random().nextInt((max - min) + 1) + min;
                    szam2Tv.setText("" + random2);
                    progressBar.setProgress(progressState += progressValue);
                    if(progressState == 100){

                        AlertDialog.Builder alertD = new AlertDialog.Builder(game2.this);
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

                    if(random1 > random2) {
                        eredmenyTv.setText("" + (random1 - random2));
                    }
                    else if (random1 < random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if (random1 == random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if(random1 > random2 +1){
                        eredmenyTv.setText("" + (random1 * random2));
                    }
                    else if (random1 % random2 == 0 ){
                        eredmenyTv.setText("" + (random1 / random2));
                    }
                }

                else if (random1 > random2 +1 && szoroszt == false) {
                    Toast.makeText(getBaseContext(), "Helytelen válasz!",
                            Toast.LENGTH_LONG).show();
                }

                else if (random1 % random2 == 0 && szoroszt == false){
                    Toast.makeText(getBaseContext(), "Helyes válasz!",
                            Toast.LENGTH_LONG).show();
                    random1 = new Random().nextInt((max - min) + 1) + min;
                    szam1Tv.setText("" + random1);
                    random2 = new Random().nextInt((max - min) + 1) + min;
                    szam2Tv.setText("" + random2);
                    progressBar.setProgress(progressState += progressValue);
                    if(progressState == 100){

                        AlertDialog.Builder alertD = new AlertDialog.Builder(game2.this);
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

                    if(random1 > random2) {
                        eredmenyTv.setText("" + (random1 - random2));
                    }
                    else if (random1 < random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if (random1 == random2){
                        eredmenyTv.setText("" + (random1 + random2));
                    }
                    else if(random1 > random2 +1){
                        eredmenyTv.setText("" + (random1 * random2));
                    }
                    else if (random1 % random2 == 0 ){
                        eredmenyTv.setText("" + (random1 / random2));
                    }
                }

                else if (random1 > random2 +1 && szoroszt == true) {
                    Toast.makeText(getBaseContext(), "Helytelen válasz!",
                            Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(getBaseContext(), "Helytelen válasz!",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}