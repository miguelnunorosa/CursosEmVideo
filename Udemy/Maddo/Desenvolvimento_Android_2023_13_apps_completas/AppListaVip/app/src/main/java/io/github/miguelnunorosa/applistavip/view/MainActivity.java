package io.github.miguelnunorosa.applistavip.view;

import io.github.miguelnunorosa.applistavip.R;
import io.github.miguelnunorosa.applistavip.controller.PessoaController;
import io.github.miguelnunorosa.applistavip.model.Pessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;


public class MainActivity extends AppCompatActivity {

    EditText edtxt_name, edtxt_lastname, edtxt_courseName, edtxt_phone;
    BootstrapButton btnSave, btnLimpar, btnFinalizar;
    Pessoa pessoa, outraPessoa;
    PessoaController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new PessoaController(MainActivity.this);

        setupScreen();
        actionsForButtons();

        //initialData();
        tempData();
        clearEditTexts();

        //load data from SharedPreferences
        sharedPreferencesData();
    }



    private void setupScreen() {
        edtxt_name = findViewById(R.id.edtxt_firstname);
        edtxt_lastname = findViewById(R.id.edtxt_lastname);
        edtxt_courseName = findViewById(R.id.edtxt_courseName);
        edtxt_phone = findViewById(R.id.edtxt_phone);

        btnSave = findViewById(R.id.btnSave);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
    }

    private void tempData() {

        pessoa = new Pessoa();
        pessoa.setNome("Oscar");
        pessoa.setApelido("Alho");
        pessoa.setCurso("Android");
        pessoa.setTelefone("123456789");
        Log.i("AppListaVIP", "Nome: " + pessoa.getNome() + " | Apelido: " + pessoa.getApelido() + " | Curso: " + pessoa.getCurso() + " Contato: " + pessoa.getTelefone());
        Log.i("AppListaVIP", "Using toString: " + pessoa.toString());


        outraPessoa = new Pessoa();
        outraPessoa.setNome("Oscar");
        outraPessoa.setApelido("Alho");
        outraPessoa.setCurso("Android");
        outraPessoa.setTelefone("123456789");
        Log.i("AppListaVIP", "Nome: " + outraPessoa.getNome() + " | Apelido: " + outraPessoa.getApelido() + " | Curso: " + outraPessoa.getCurso() + " Contato: " + outraPessoa.getTelefone());
        Log.i("AppListaVIP", "Using toString: " + outraPessoa.toString());
    }

    private void initialData(){
        //initial data demo
        edtxt_name.setText(pessoa.getNome());
        edtxt_lastname.setText(pessoa.getApelido());
        edtxt_courseName.setText(pessoa.getCurso());
        edtxt_phone.setText(pessoa.getTelefone());

        //initial data are from static data. After first save, all data come from SharedPreferences
    }

    private void sharedPreferencesData() {
        //data from PessoaController
        controller.getData(pessoa);

        //add data to fields
        edtxt_name.setText(pessoa.getNome());
        edtxt_lastname.setText(pessoa.getApelido());
        edtxt_courseName.setText(pessoa.getCurso());
        edtxt_phone.setText(pessoa.getTelefone());
    }

    private void clearEditTexts(){
        edtxt_name.setText("");
        edtxt_lastname.setText("");
        edtxt_courseName.setText("");
        edtxt_phone.setText("");
    }

    private void actionsForButtons() {

        btnLimpar.setOnClickListener(view -> {
            clearEditTexts();

            //clear sharedPreferences
            controller.clearData();
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte sempre!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setNome(edtxt_name.getText().toString());
                pessoa.setApelido(edtxt_lastname.getText().toString());
                pessoa.setCurso(edtxt_courseName.getText().toString());
                pessoa.setTelefone(edtxt_phone.getText().toString());

                controller.saveData(pessoa);

                clearEditTexts();

                Toast.makeText(MainActivity.this, "Guardado! " + pessoa.toString(), Toast.LENGTH_LONG).show();
                Log.i("AppListaVIP", "Using toString: " + pessoa.toString());
            }
        });

    }


}