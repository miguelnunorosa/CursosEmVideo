package io.github.miguelnunorosa.applistavip.controller;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import io.github.miguelnunorosa.applistavip.model.Pessoa;
import io.github.miguelnunorosa.applistavip.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String PREFERENCES_NAME = "pref_listavip";



    public PessoaController(MainActivity mainActivity) {
        preferences = mainActivity.getSharedPreferences(PREFERENCES_NAME, 0);

        listaVip = preferences.edit();
    }


    public void saveData(Pessoa pessoa) {
        listaVip.putString("nome", pessoa.getNome());
        listaVip.putString("apelido", pessoa.getApelido());
        listaVip.putString("curso", pessoa.getCurso());
        listaVip.putString("telefone", pessoa.getTelefone());

        listaVip.apply(); //save to SharedPreferences

        Log.d("AppListaVIP", "Saved " + pessoa.toString());
    }


    public Pessoa getData(Pessoa pessoa){
        pessoa.setNome(preferences.getString("nome", ""));
        pessoa.setApelido(preferences.getString("apelido", ""));
        pessoa.setCurso(preferences.getString("curso", ""));
        pessoa.setTelefone(preferences.getString("telefone", ""));

        return pessoa;
    }


    public void clearData(){
        listaVip.clear();
        listaVip.apply();
    }



    @NonNull
    @Override
    public String toString() {
        Log.d("AppListaVIP", "Controller Pessoa started");
        return super.toString();
    }

}
