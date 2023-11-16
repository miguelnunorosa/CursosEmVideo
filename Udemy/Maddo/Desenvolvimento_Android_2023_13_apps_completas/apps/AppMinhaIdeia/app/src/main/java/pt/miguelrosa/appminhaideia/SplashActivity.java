package pt.miguelrosa.appminhaideia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private final static String TAG = "APP_MINHA_IDEIA";
    private final static int TEMPO_ESPERA = 2000; //time in milliseconds (ms)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d(TAG, "onCreate: Splash criada...");
        trocarTela();
    }


    private void trocarTela(){
        //                                           De onde?              Para onde?
        Intent trocarTela = new Intent(SplashActivity.this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "A trocar de tela...");
                startActivity(trocarTela);
                finish();
            }
        }, TEMPO_ESPERA);

    }

}