package com.example.readapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import com.bumptech.glide.Glide;


import com.example.readapplication.R;


public class Splash_Activity extends AppCompatActivity {

    private ImageView splash_IMG_imageDisplay;
    private TextView splash_LBL_title;
    private Animation splash_top_animation;
    private Animation splash_bottom_Animation;
    private final int SPLASH_SCREEN = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splash_top_animation = AnimationUtils.loadAnimation(this, R.anim.splash_top_animation);
        splash_bottom_Animation = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_animation);

        findView();
        initAnimation();

        splash_IMG_imageDisplay.setImageResource(R.drawable.spy);

        handlerSplashScreen();
        setImage(R.drawable.spy,splash_IMG_imageDisplay);
    }

    private void handlerSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Activity.this, Main_Activity.class);

                Pair pair = new Pair<View,String>(splash_LBL_title, "logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions.makeSceneTransitionAnimation(Splash_Activity.this, pair);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_SCREEN);
    }


    private void initAnimation() {
        splash_IMG_imageDisplay.startAnimation(splash_top_animation);
        splash_LBL_title.startAnimation(splash_bottom_Animation);
    }

    private void findView() {
        splash_IMG_imageDisplay = findViewById(R.id.splash_IMG_imageDisplay);
        splash_LBL_title = findViewById(R.id.splash_LBL_title);
    }

    private void setImage(int id, ImageView imageView) {
        Glide
                .with(this)
                .load(id)
                .into(imageView);
    }
}

