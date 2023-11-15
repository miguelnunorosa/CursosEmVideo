package pt.miguelrosa.appminhaideia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private final static String TAG = "APP_MINHA_IDEIA";

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

        Log.d(TAG, "A trocar de tela...");

        startActivity(trocarTela);
        finish();
    }

}