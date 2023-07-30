package io.github.miguelnunorosa.fddatabase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.github.miguelnunorosa.fddatabase.R;
import io.github.miguelnunorosa.fddatabase.controller.AlunoController;
import io.github.miguelnunorosa.fddatabase.model.Aluno;

public class MainActivity extends AppCompatActivity {

    Aluno obj;
    AlunoController alunoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obj = new Aluno();
        alunoController = new AlunoController( getApplicationContext() );

        //obj.setNome("Aluno x");
        //obj.setEmail("asd@ads.asd");
        //alunoController.save(obj);
    }


}