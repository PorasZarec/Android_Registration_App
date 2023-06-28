package com.example.e_jeeptracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    ImageView Roadmap,JeepImage;
    TextView txtV1,txtV2;
    Animation Top_anim,Bottom_anim,Left_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // SETTING A DELAY TRANSITION FROM 'SplashScreen.java' to 'MainActivity.java'
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

        Roadmap=findViewById(R.id.phone_roadmap);
        JeepImage=findViewById(R.id.jeep);
        txtV1=findViewById(R.id.tv1_sp_screen);
        txtV2=findViewById(R.id.tv2_sp_screen);

        // DECLARING ANIMATION FOR EACH OF THEM
        Top_anim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        Bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        Left_anim = AnimationUtils.loadAnimation(this,R.anim.left_animation);

        Roadmap.setAnimation(Top_anim);
        JeepImage.setAnimation(Left_anim);
        txtV1.setAnimation(Bottom_anim);
        txtV2.setAnimation(Bottom_anim);
    }
}