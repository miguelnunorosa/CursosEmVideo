package primeiro.cliente.appprimeirocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class TelaSplashActivity extends AppCompatActivity {

    private final static String TAG = "APP_PRIMEIRO_CLIENTE";
    private final static int TEMPO_ESPERA = 15000; //time in milliseconds (ms) 15000ms = 15 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        Log.d(TAG, "onCreate: Splash criada...");
        trocarTela();
    }


    private void trocarTela(){
        //                                           De onde?              Para onde?
        Intent trocarTela = new Intent(TelaSplashActivity.this, MainActivity.class);

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