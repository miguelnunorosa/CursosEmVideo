package io.github.miguelnunorosa.fddatabase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.github.miguelnunorosa.fddatabase.R;
import io.github.miguelnunorosa.fddatabase.controller.AlunoController;
import io.github.miguelnunorosa.fddatabase.model.Aluno;

public class MainActivity extends AppCompatActivity {

    Aluno objAluno;
    AlunoController alunoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objAluno = new Aluno();
        alunoController = new AlunoController( getApplicationContext() );

        objAluno.setNome("TESTE");
        objAluno.setEmail("asd@ad.asd");
        objAluno.setStatus(true);

        if( alunoController.save(objAluno) ){
            Log.i("FD-LOG", "(AlunoController) -> Nome: " + objAluno.getNome() + " / " + objAluno.getEmail() + " / Status: " + objAluno.isStatus());
            Toast.makeText(getApplicationContext(), "(AlunoController) -> Nome: " + objAluno.getNome() + " / " + objAluno.getEmail() + "  / Status: " + objAluno.isStatus(), Toast.LENGTH_SHORT).show();
        }else{
            Log.i("FD-LOG", "(AlunoController) -> Erro ao tentar salvar aluno> Nome: " + objAluno.getNome() + " (" + objAluno.getEmail() + " )" + "Status: " + objAluno.isStatus());
        }

    }


}