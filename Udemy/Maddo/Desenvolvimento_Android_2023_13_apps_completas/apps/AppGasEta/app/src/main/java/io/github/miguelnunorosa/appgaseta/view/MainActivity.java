package io.github.miguelnunorosa.appgaseta.view;

import io.github.miguelnunorosa.appgaseta.R;
import io.github.miguelnunorosa.appgaseta.util.UtilGasEta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;


public class MainActivity extends AppCompatActivity {

    EditText edtxt_gasolina, edtxt_etanol;
    BootstrapButton btnSave, btnClean, btnCalculate, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupScreen();
        clearEditTexts();
        actionsForButtons();

        //example
        Toast.makeText(this, UtilGasEta.calculateBestOption(5.12, 2.99), Toast.LENGTH_LONG).show();
    }


    private void setupScreen() {
        edtxt_gasolina = findViewById(R.id.edtxt_gasolina);
        edtxt_etanol = findViewById(R.id.edtxt_etanol);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClean = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
    }

    private void clearEditTexts(){
        edtxt_gasolina.setText("");
        edtxt_etanol.setText("");
    }

    private void actionsForButtons(){
        btnClean.setOnClickListener(view -> {
            clearEditTexts();

            //clear sharedPreferences
            //controller.clearData();
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte sempre!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}