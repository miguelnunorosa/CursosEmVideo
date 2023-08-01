package io.github.miguelnunorosa.applistavip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import io.github.miguelnunorosa.applistavip.R;

public class MainActivity extends AppCompatActivity {

    EditText  edtxt_nome, edtxt_sobrenome, edtxt_courseName, edtxt_phone;
    Button btnSave, btnLimpar, btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupScreen();
    }


    private void setupScreen(){
        EditText edtxt_nome = findViewById(R.id.edtxt_nome);
        EditText edtxt_sobrenome = findViewById(R.id.edtxt_sobrenome);
        EditText edtxt_courseName = findViewById(R.id.edtxt_courseName);
        EditText edtxt_phone = findViewById(R.id.edtxt_phone);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnLimpar = findViewById(R.id.btnLimpar);
        Button btnFinalizar = findViewById(R.id.btnFinalizar);
    }



}