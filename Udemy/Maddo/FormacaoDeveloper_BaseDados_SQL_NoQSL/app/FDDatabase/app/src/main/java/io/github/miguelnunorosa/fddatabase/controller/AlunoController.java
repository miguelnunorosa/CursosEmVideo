package io.github.miguelnunorosa.fddatabase.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import io.github.miguelnunorosa.fddatabase.database.AppDatabase;
import io.github.miguelnunorosa.fddatabase.model.Aluno;

public class AlunoController extends AppDatabase {

    ContentValues data;

    public AlunoController(Context context) {
        super(context);
    }


    //generic methods for CRUD (Create/Read/Update/Delete)

    public boolean save(Aluno obj){
        data = new ContentValues();

        data.put("nome", obj.getNome());
        data.put("email", obj.getEmail());
        data.put("status", obj.isStatus());

        Log.i("FD-LOG", "(AlunoController) -> Nome: " + obj.getNome() + " (" + obj.getEmail() + " )" + "Status: " + obj.isStatus());

        return insertData("aluno", data);
    }



    public void delete(Aluno obj){}


    public void update(Aluno obj){}


    public void read(Aluno obj){}


    public void filter(Aluno obj){}


    public void changeStatus(Aluno obj){}


}
