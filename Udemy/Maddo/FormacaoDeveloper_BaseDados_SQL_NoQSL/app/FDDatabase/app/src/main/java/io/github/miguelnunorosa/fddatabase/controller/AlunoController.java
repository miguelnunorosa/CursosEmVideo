package io.github.miguelnunorosa.fddatabase.controller;

import android.util.Log;

import io.github.miguelnunorosa.fddatabase.model.Aluno;

public class AlunoController {

    public AlunoController() {}


    //generic methods for CRUD (Create/Read/Update/Delete)

    public void save(Aluno obj){
        Log.i("FD-LOG (AlunoController) -> ", "Nome: " + obj.getNome() + " (" + obj.getEmail() + " )");
    }


    public void delete(Aluno obj){}


    public void update(Aluno obj){}


    public void read(Aluno obj){}


    public void filter(Aluno obj){}


    public void changeStatus(Aluno obj){}


}
