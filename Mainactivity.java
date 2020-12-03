package com.example.seminarkp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import com.example.seminarkp1.R;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class MainActivity extends Activity {

    private LinearLayout viewScientistsCard;
    private LinearLayout addScientistsCard;
    private LinearLayout third;
    private LinearLayout closeCard;
    private LinearLayout keluar;
    private LinearLayout scanbarcode;
    MediaPlayer audio;

    public void initializeWidgets(){
        viewScientistsCard=findViewById(R.id.ViewScientistCard);
        addScientistsCard=findViewById(R.id.addScientistCard);
        third=findViewById(R.id.third);
        closeCard=findViewById(R.id.closeCard);
        keluar=findViewById(R.id.keluar);
        scanbarcode=findViewById(R.id.scanbarcode);


        viewScientistsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FirebaseDBReadActivity.getActIntent(MainActivity.this));

            }
        });
        addScientistsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FirebaseDBCreateActivity.getActIntent(MainActivity.this));

            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,ProfilActivity.class);
                startActivity(i);

            }
        });
        closeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent a=new Intent(MainActivity.this,PdfActivity.class);
            startActivity(a);
            }
        });
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();

            }
        });

        scanbarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahscan=new Intent(MainActivity.this,QrScan.class);
                startActivity(pindahscan);
            }
        });

        //aslinya
        /*
addScientistsCard.setOnClickListener(v->Utils.openActivity(DashboardActivity.this,
                ScientistsActivity.class));

third.setOnClickListener(v -> Utils.showInfoDialog(DashboardActivity.this,"Yes", "you can click"));*/
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        audio =  MediaPlayer.create(this,R.raw.harikemerdekaankita);
        audio.setLooping(true);
        audio.setVolume(1,1);
        audio.start();

        this.initializeWidgets();
    }

    public void onToogleButton(View view) {
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            audio.setVolume(0,0);
        }
        else {
            audio.setVolume(1,1);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        audio.isPlaying();
        MainActivity.this.onResume();
    }

}

