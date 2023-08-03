package io.github.miguelnunorosa.applistavip.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import io.github.miguelnunorosa.applistavip.model.Pessoa;

public class PessoaController {

    public void saveData(Pessoa pessoa) {
        Log.d("AppListaVIP", "Saved " + pessoa.toString());
    }







    @NonNull
    @Override
    public String toString() {
        Log.d("AppListaVIP", "Controller Pessoa started");
        return super.toString();
    }

}
