package com.example.thegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class game4 extends AppCompatActivity {


    LinearLayout target1, target2, target3, target4, target5, box1, box2, box3, box4, box5;
    Button szam1, szam2, szam3, szam4, szam5, drop1, drop2, drop3, drop4, drop5, vissza;
    List<Integer> sortList = new ArrayList<Integer>();
    Button[] gombok;
    int progress = 0;
    String name;
    Integer game4;

    public Integer getGame4() {
        return game4;
    }

    public void setGame4(Integer game4) {
        this.game4 = game4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = getIntent().getStringExtra("Name");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_game4);

        target1 = (LinearLayout) findViewById(R.id.target1);
        target2 = (LinearLayout) findViewById(R.id.target2);
        target3 = (LinearLayout) findViewById(R.id.target3);
        target4 = (LinearLayout) findViewById(R.id.target4);
        target5 = (LinearLayout) findViewById(R.id.target5);
        box1 = (LinearLayout) findViewById(R.id.box1);
        box2 = (LinearLayout) findViewById(R.id.box2);
        box3 = (LinearLayout) findViewById(R.id.box3);
        box4 = (LinearLayout) findViewById(R.id.box4);
        box5 = (LinearLayout) findViewById(R.id.box5);
        szam1 = (Button) findViewById(R.id.szam1Btn);
        szam2 = (Button) findViewById(R.id.szam2Btn);
        szam3 = (Button) findViewById(R.id.szam3Btn);
        szam4 = (Button) findViewById(R.id.szam4Btn);
        szam5 = (Button) findViewById(R.id.szam5Btn);

        gombok = new Button[]{szam1, szam2, szam3, szam4, szam5};

        drop1 = findViewById(R.id.target1Btn);
        drop2 = findViewById(R.id.target2Btn);
        drop3 = findViewById(R.id.target3Btn);
        drop4 = findViewById(R.id.target4Btn);
        drop5 = findViewById(R.id.target5Btn);

        dLSetting();

        target1.setOnDragListener(dL);
        target2.setOnDragListener(dL);
        target3.setOnDragListener(dL);
        target4.setOnDragListener(dL);
        target5.setOnDragListener(dL);
        szam1.setOnLongClickListener(lcL);
        szam2.setOnLongClickListener(lcL);
        szam3.setOnLongClickListener(lcL);
        szam4.setOnLongClickListener(lcL);
        szam5.setOnLongClickListener(lcL);

        setNumbers();
        Collections.sort(sortList);
        sortGombok();
        visszaGomb();
    }

    /*public void gameOverDialog(){

            AlertDialog.Builder alertD = new AlertDialog.Builder(this);
            alertD.setCancelable(false);
            alertD.setTitle("Gratulálunk!");
            alertD.setIcon(R.drawable.ic_baseline_check_circle_24);
            alertD.setMessage("Sikeresen megcsináltad a feladatot!");
            alertD.setPositiveButton("Köszönöm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) { finish(); }
            });
            alertD.show();

    }*/

    public void Update(String n){

        DocumentReference segedValt;
        segedValt = FirebaseFirestore.getInstance().collection("Player").document(n);

        segedValt.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()){
                Integer s = Integer.valueOf(String.valueOf(documentSnapshot.getString("game4")));
                setGame4(s);
            }
        });

        segedValt.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Integer ponto = getGame4() + 1;
                segedValt.update("game4",String.valueOf(ponto));

            } else {
                Log.d("TAG", "Failed with: ", task.getException());
            }
        });

    }

    public void visszaGomb() {
        vissza = (Button) findViewById(R.id.visszaBtn);
        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int getRandomNum(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }

    public void setNumbers() {
        /*r1 = getRandomNum(0, 100);
        r2 = getRandomNum(0, 100);
        r3 = getRandomNum(0, 100);
        r4 = getRandomNum(0, 100);
        r5 = getRandomNum(0, 100);*/
        szam1.setText(String.valueOf(getRandomNum(0, 100)));
        szam2.setText(String.valueOf(getRandomNum(0, 100)));
        szam3.setText(String.valueOf(getRandomNum(0, 100)));
        szam4.setText(String.valueOf(getRandomNum(0, 100)));
        szam5.setText(String.valueOf(getRandomNum(0, 100)));

        sortList.add(Integer.parseInt(szam1.getText().toString()));
        sortList.add(Integer.parseInt(szam2.getText().toString()));
        sortList.add(Integer.parseInt(szam3.getText().toString()));
        sortList.add(Integer.parseInt(szam4.getText().toString()));
        sortList.add(Integer.parseInt(szam5.getText().toString()));
    }

    public void sortGombok() {
        for (int i = 0; i < gombok.length - 1; i++) {
            for (int j = i; j < gombok.length; j++) {
                Button csere;
                if (Integer.parseInt(gombok[i].getText().toString()) > Integer.parseInt(gombok[j].getText().toString())) {
                    csere = gombok[i];
                    gombok[i] = gombok[j];
                    gombok[j] = csere;
                }
            }
        }
    }

    public void dLSetting() {

        dL = new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {

                int dragEvent = event.getAction();
                final View view = (View) event.getLocalState();

                switch (dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:

                        break;
                    case DragEvent.ACTION_DROP:

                        if (view.getId() == gombok[0].getId() && v.getId() == R.id.target1) {
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newparent = (LinearLayout) v;
                            drop1.setVisibility(View.GONE);
                            newparent.addView(view);
                            progress++;
                        } else if (view.getId() == gombok[1].getId() && v.getId() == R.id.target2) {
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newparent = (LinearLayout) v;
                            drop2.setVisibility(View.GONE);
                            newparent.addView(view);
                            progress++;
                        } else if (view.getId() == gombok[2].getId() && v.getId() == R.id.target3) {
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newparent = (LinearLayout) v;
                            drop3.setVisibility(View.GONE);
                            newparent.addView(view);
                            progress++;
                        } else if (view.getId() == gombok[3].getId() && v.getId() == R.id.target4) {
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newparent = (LinearLayout) v;
                            drop4.setVisibility(View.GONE);
                            newparent.addView(view);
                            progress++;
                        } else if (view.getId() == gombok[4].getId() && v.getId() == R.id.target5) {
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newparent = (LinearLayout) v;
                            drop5.setVisibility(View.GONE);
                            newparent.addView(view);

                            AlertDialog.Builder alertD = new AlertDialog.Builder(game4.this);
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
                        break;
                }
                return true;
            }
        };
    }

    View.OnLongClickListener lcL = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadow, v, 0);
            return true;
        }
    };

    View.OnDragListener dL;
}