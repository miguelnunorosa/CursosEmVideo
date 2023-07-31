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


    public boolean insert(Aluno objAluno) {

        data = new ContentValues();

        data.put("nome", objAluno.getNome());
        data.put("email", objAluno.getEmail());
        data.put("status", objAluno.isStatus());

        Log.e("FD-LOG", "(AlunoController) -> Nome: " + objAluno.getNome() + " | Email: " + objAluno.getEmail() + " | Status: " + objAluno.isStatus());

        return insert("aluno", data); //insert data on database
    }



}
