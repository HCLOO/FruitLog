package com.example.think.fruitlog.activtiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.think.fruitlog.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView IVsplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        IVsplash=(ImageView) findViewById(R.id.iv_splash);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splash_animation);
        animation.setDuration(1500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(SplashActivity.this,LoginAndSignUpActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        IVsplash.startAnimation(animation);
    }
}
