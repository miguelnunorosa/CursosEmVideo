package io.github.miguelnunorosa.appgaseta.view;

import io.github.miguelnunorosa.appgaseta.R;
import io.github.miguelnunorosa.appgaseta.model.Combustivel;
import io.github.miguelnunorosa.appgaseta.util.UtilGasEta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;


public class MainActivity extends AppCompatActivity {

    EditText edtxt_gasolina, edtxt_etanol;
    TextView txtResult;
    BootstrapButton btnSave, btnClear, btnCalculate, btnExit;

    String resultBestChoice;
    double precoGasolina, precoEtanol;
    Combustivel combustivelGasolina, combustivelEtanol;


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
        txtResult = findViewById(R.id.txtResult);
        btnClear = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
    }

    private void clearEditTexts(){
        edtxt_gasolina.setText("");
        edtxt_etanol.setText("");
    }

    private void actionsForButtons(){

        btnCalculate.setOnClickListener(view -> {

            boolean isDadosOK = true;

            if(TextUtils.isEmpty(edtxt_gasolina.getText())){
                edtxt_gasolina.setError("Obrigatório");
                edtxt_gasolina.requestFocus();
                isDadosOK = false;
            }
            if(TextUtils.isEmpty(edtxt_etanol.getText())){
                edtxt_etanol.setError("Obrigatório");
                edtxt_etanol.requestFocus();
                isDadosOK = false;
            }

            if(isDadosOK){
                resultBestChoice = UtilGasEta.calculateBestOption(Double.parseDouble(edtxt_gasolina.getText().toString()), Double.parseDouble(edtxt_etanol.getText().toString()));
                txtResult.setText(resultBestChoice);
            }else{
                Toast.makeText(MainActivity.this, "Insira os dados obrigatórios!", Toast.LENGTH_LONG).show();
                Log.e("AppGasEta", "Dados inseridos incorretos.");
            }

        });

        btnSave.setOnClickListener(view -> {
            combustivelGasolina = new Combustivel();
            combustivelEtanol = new Combustivel();

            combustivelGasolina.setFuelTipe("Gasolina");
            combustivelGasolina.setFuelPrice(precoGasolina);
            combustivelEtanol.setFuelTipe("Etanol");
            combustivelEtanol.setFuelPrice(precoEtanol);

            combustivelGasolina.setSuggestion(UtilGasEta.calculateBestOption(precoGasolina, precoEtanol));
            combustivelEtanol.setSuggestion(UtilGasEta.calculateBestOption(precoGasolina, precoEtanol));

        });

        btnClear.setOnClickListener(view -> {
            clearEditTexts();
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