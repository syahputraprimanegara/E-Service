package com.example.seminarkp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import io.github.inflationx.viewpump.ViewPumpContextWrapper;



public class SplashActivity extends Activity {

    private ImageView mLogo;
    private TextView mainTittle,subTittle;

    private void initializeWidget(){
        mLogo=findViewById(R.id.mLogo);
        mainTittle=findViewById(R.id.mainTittle);
        subTittle=findViewById(R.id.subTittle);

    }

    private void showSplashAnimation (){
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.top_to_bottom);
        mLogo.startAnimation(animation);

        Animation fadein =AnimationUtils.loadAnimation(this,R.anim.fade_in);
        mainTittle.startAnimation(fadein);
        subTittle.startAnimation(fadein);
    }


    @Override
    protected void attachBaseContext (Context newBase){
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            public void run () {
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);

                    startActivity(intent);

                    finish();
                }
            }
        };
        thread.start();


        this.initializeWidget();
        this.showSplashAnimation();
    }
}
