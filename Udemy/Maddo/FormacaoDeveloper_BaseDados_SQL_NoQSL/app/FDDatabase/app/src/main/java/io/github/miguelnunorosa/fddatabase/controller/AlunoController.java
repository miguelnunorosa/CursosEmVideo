package io.github.miguelnunorosa.fddatabase.controller;

import android.content.Context;
import android.util.Log;

import java.net.ContentHandler;

import io.github.miguelnunorosa.fddatabase.database.AppDatabase;
import io.github.miguelnunorosa.fddatabase.model.Aluno;

public class AlunoController extends AppDatabase {

    public AlunoController(Context context) {
        super(context);
    }


    //generic methods for CRUD (Create/Read/Update/Delete)

    public void save(Aluno obj){
        Log.i("FD-LOG", "(AlunoController) -> Nome: " + obj.getNome() + " (" + obj.getEmail() + " )");
    }


    public void delete(Aluno obj){}


    public void update(Aluno obj){}


    public void read(Aluno obj){}


    public void filter(Aluno obj){}


    public void changeStatus(Aluno obj){}


}
