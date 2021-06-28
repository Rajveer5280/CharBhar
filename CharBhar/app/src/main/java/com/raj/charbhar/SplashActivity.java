package com.raj.charbhar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    ConstraintLayout constraintLayout;
    TextView textView;
    ImageView imageView;
    Animation topAnim;
    Animation bottomAnim;
    Animation rightAnim;
    Animation leftAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        textView =findViewById(R.id.wc_txt);
        imageView=findViewById(R.id.logo);
        relativeLayout=findViewById(R.id.rl);
        constraintLayout=findViewById(R.id.cl);

        //Animation
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        leftAnim = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        rightAnim = AnimationUtils.loadAnimation(this,R.anim.right_animation);

        //set Animation
        imageView.setAnimation(topAnim);
        textView.setAnimation(bottomAnim);
        //constraintLayout.setAnimation(leftAnim);
        relativeLayout.setAnimation(rightAnim);

        /*** create Thread that will sleep for 4 seconds
         *
         */
        Thread background =new Thread(){
            public void run(){
                try{
                    //Thread will sleep for 4 seconds
                    sleep(4*1000);

                    //After 4 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),HomeActivity.class);
                    startActivity(i);
                    finish();

                }catch (Exception e){

                }
            }
        };

        background.start();


    }
}