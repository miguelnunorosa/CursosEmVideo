package io.github.miguelnunorosa.applistavip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;

import io.github.miguelnunorosa.applistavip.R;
import io.github.miguelnunorosa.applistavip.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    EditText  edtxt_nome, edtxt_sobrenome, edtxt_courseName, edtxt_phone;
    BootstrapButton btnSave, btnLimpar, btnFinalizar;
    Pessoa pessoa;
    Pessoa outraPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupScreen();

        pessoa = new Pessoa();
        pessoa.setNome("nome da pessoa");
        pessoa.setApelido("apelido da pessoa");
        pessoa.setCurso("curso da pessoa");
        pessoa.setTelefone("teelfone da pessoa");
        Log.i("AppListaVIP", "Nome: " + pessoa.getNome() + " | Apelido: " + pessoa.getApelido() + " | Curso: " + pessoa.getCurso() + " Contato: " + pessoa.getTelefone());


        outraPessoa = new Pessoa();
        outraPessoa.setNome("Oscar");
        outraPessoa.setApelido("Alho");
        outraPessoa.setCurso("Android");
        outraPessoa.setTelefone("123456789");
        Log.i("AppListaVIP", "Nome: " + outraPessoa.getNome() + " | Apelido: " + outraPessoa.getApelido() + " | Curso: " + outraPessoa.getCurso() + " Contato: " + outraPessoa.getTelefone());

    }


    private void setupScreen(){
        edtxt_nome = findViewById(R.id.edtxt_nome);
        edtxt_sobrenome = findViewById(R.id.edtxt_sobrenome);
        edtxt_courseName = findViewById(R.id.edtxt_courseName);
        edtxt_phone = findViewById(R.id.edtxt_phone);

        btnSave = findViewById(R.id.btnSave);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
    }



}