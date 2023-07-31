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

        //insert data
        objAluno.setNome("Mike");
        objAluno.setEmail("asd@asd.asd");
        objAluno.setStatus(true);
        if(alunoController.insert(objAluno)){
            Toast.makeText(getApplicationContext(), "aluno " + objAluno.getNome() + " SAVED.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "ERROR on save data from aluno " + objAluno.getNome(), Toast.LENGTH_LONG).show();
        }

        //insert 10 records:
        for (int i = 0; i < 5; i++) {
            objAluno.setNome("NOME " + i);
            objAluno.setNome("EMAIL" + i + "@asdasd.co " + i);
            if(i % 2 == 0)
                objAluno.setStatus(true);
            else
                objAluno.setStatus(false);

            alunoController.insert(objAluno);
            Toast.makeText(getApplicationContext(), "aluno " + objAluno.getNome() + " SAVED.", Toast.LENGTH_LONG).show();
            Log.i("FD-LOG", "(MainActivity) -> CREATE ALUNO: " + "ID " + i + " Nome: " + objAluno.getNome()+i + "Email: " + objAluno.getEmail() + "Status: " + objAluno.isStatus());
        }


        //update data id=2
        objAluno.setId(1);
        objAluno.setNome("Mike");
        objAluno.setEmail("asd@asd.asd");
        objAluno.setStatus(true);

        if(alunoController.update(objAluno)){
            Toast.makeText(getApplicationContext(), "aluno " + objAluno.getNome() + " UPDATED.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "ERROR when update data from aluno " + objAluno.getNome(), Toast.LENGTH_LONG).show();
        }


        //delete data
        objAluno.setId(1);
        objAluno.setNome("Mike");
        objAluno.setEmail("asd@asd.asd");
        objAluno.setStatus(true);

        if(alunoController.delete(objAluno)){
            Toast.makeText(getApplicationContext(), "aluno " + objAluno.getNome() + " DELETED.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "ERROR when delete data from aluno " + objAluno.getNome(), Toast.LENGTH_LONG).show();
        }

    }


}