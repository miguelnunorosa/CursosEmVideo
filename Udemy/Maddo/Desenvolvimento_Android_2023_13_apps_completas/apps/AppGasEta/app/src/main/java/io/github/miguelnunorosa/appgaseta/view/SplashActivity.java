package io.github.miguelnunorosa.appgaseta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import io.github.miguelnunorosa.appgaseta.R;

public class SplashActivity extends AppCompatActivity {

    private static final int TIMEOUT_SPLASH = 2500; //time in ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        changeSplashScreen();
    }

    private void changeSplashScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { //process in TIMEOUT time
                Intent mainActivityScreen = new Intent(SplashActivity.this, MainActivity.class);

                //Note:
                //Intent mainActivityScreen = new Intent(from screen, to screen);

                startActivity(mainActivityScreen); //open Screen
                finish(); //don't allow to go back (previous screen)
            }
        }, TIMEOUT_SPLASH);

    }


}