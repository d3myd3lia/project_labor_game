package com.example.thegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class register extends AppCompatActivity {
    //The Firebase Firestore data save file has been deleted
    Button enaFolyt;
    EditText editTextName;
    CollectionReference reff;
    DocumentReference segedValt;

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    String nev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = (EditText) findViewById(R.id.editTextName);

        //player = new Player(nev, 0, 0, 0, 0);
        reff = FirebaseFirestore.getInstance().collection("Player");
        configureOKButton();
        configureNextButton();
        configurebackButton();

    }

    private void configureOKButton(){
       Button OKButton = (Button)  findViewById(R.id.OKButton);
       enaFolyt = (Button) findViewById(R.id.nextButton);
       OKButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TextView textHello = findViewById(R.id.textHello);
               editTextName = (EditText) findViewById(R.id.editTextName);
               nev = editTextName.getText().toString();
               setNev(nev);

               textHello.setText("Szia " + editTextName.getText().toString() + "!");

               TextView textWelcome = findViewById(R.id.textWelcome);
               textWelcome.setText("Üdvözlünk a játékunkban!\nKérlek nyomd meg a folytatás gombot, hogy átjuthass a főmenübe! :)");

               enaFolyt.setEnabled(true);

               Map<String, String> datatosave = new HashMap<>();

               segedValt = reff.document(nev);
               segedValt.get().addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       DocumentSnapshot document = task.getResult();
                       if (document.exists()) {
                           Log.d("VMI", "Dokumentum létezik!");
                       } else {
                           Log.d("VMI", "Dokumentum nem létezik!");
                           datatosave.put("game1", "0");
                           datatosave.put("game2", "0");
                           datatosave.put("game3", "0");
                           datatosave.put("game4", "0");
                           segedValt.set(datatosave);

                       }
                   } else {
                       Log.d("TAG", "Failed with: ", task.getException());
                   }
               });

           }
       });
    }


    private void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("Name", getNev());
                startActivity(i);
            }
        });
    }



    private void configurebackButton(){
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}