package io.github.miguelnunorosa.appgaseta.controller;

import android.content.SharedPreferences;

import io.github.miguelnunorosa.appgaseta.model.Combustivel;
import io.github.miguelnunorosa.appgaseta.view.MainActivity;

public class CombustivelController {

    SharedPreferences preferences;
    SharedPreferences.Editor preferencesData;
    public static final String PREFERENCES_NAME = "pref_gaseta";


    public CombustivelController(MainActivity mainActivity) {
        preferences = mainActivity.getSharedPreferences(PREFERENCES_NAME, 0);

        preferencesData = preferences.edit();
    }


    public void saveData(Combustivel combustivel){
        preferencesData.putString("fuelType", combustivel.getFuelType());
        preferencesData.putFloat("fuelPrice", (float) combustivel.getFuelPrice());
        preferencesData.putString("suggestion", combustivel.getSuggestion());

        preferencesData.apply();
    }

    public void clearData(){
        preferencesData.clear();
        preferencesData.apply();
    }

}
