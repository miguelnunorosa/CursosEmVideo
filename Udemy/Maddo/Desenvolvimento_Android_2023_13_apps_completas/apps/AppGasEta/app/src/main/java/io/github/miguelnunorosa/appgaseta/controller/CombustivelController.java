package io.github.miguelnunorosa.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import java.util.List;

import io.github.miguelnunorosa.appgaseta.database.GasEtaDB;
import io.github.miguelnunorosa.appgaseta.model.Combustivel;
import io.github.miguelnunorosa.appgaseta.view.MainActivity;

public class CombustivelController extends GasEtaDB {

    SharedPreferences preferences;
    SharedPreferences.Editor preferencesData;
    public static final String PREFERENCES_NAME = "pref_gaseta";


    public CombustivelController(MainActivity mainActivity) {
        super(mainActivity);
        preferences = mainActivity.getSharedPreferences(PREFERENCES_NAME, 0);

        preferencesData = preferences.edit();
    }


    public void saveData(Combustivel combustivel){
        ContentValues data = new ContentValues();

        //save on SharedPreferences
        preferencesData.putString("fuelType", combustivel.getFuelType());
        preferencesData.putFloat("fuelPrice", (float) combustivel.getFuelPrice());
        preferencesData.putString("suggestion", combustivel.getSuggestion());
        preferencesData.apply();

        //save to database
        data.put("fuelType", combustivel.getFuelType() );
        data.put("fuelPrice", (float) combustivel.getFuelPrice() );
        data.put("suggestion", combustivel.getSuggestion() );

        saveObj("Combustivel", data);
        int pra=0;
    }


    public List<Combustivel> getAllData(){
        return listData();
    }


    public void clearData(){
        preferencesData.clear();
        preferencesData.apply();
    }

}
