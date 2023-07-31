package io.github.miguelnunorosa.fddatabase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.github.miguelnunorosa.fddatabase.R;
import io.github.miguelnunorosa.fddatabase.controller.AlunoController;
import io.github.miguelnunorosa.fddatabase.model.Aluno;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlunoController alunoController = new AlunoController(getApplicationContext());
        Aluno objAluno = new Aluno();

        objAluno.setNome("Mike");
        objAluno.setEmail("asd@asd.asd");
        objAluno.setStatus(true);

        if(alunoController.insert(objAluno)){
            Toast.makeText(getApplicationContext(), "aluno " + objAluno.getNome() + " Saved.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "ERROR on save data from aluno " + objAluno.getNome(), Toast.LENGTH_LONG).show();
        }

    }


}