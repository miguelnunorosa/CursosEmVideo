package io.github.miguelnunorosa.appgaseta.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.miguelnunorosa.appgaseta.model.Combustivel;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE_FUEL = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT,  fuelType TEXT,  fuelPrice REAL,  suggestion TEXT)";
    private static final String READ_QUERY = "SELECT * FROM Combustivel";

    Cursor cursor;
    SQLiteDatabase db;




    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FUEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }


    public void saveObj(String tableName, ContentValues data){
        db.insert(tableName, null, data);
    }

    public List<Combustivel> listData(){

        List<Combustivel> lista = new ArrayList<>();
        Combustivel dbRegister; //get row from database

        cursor = db.rawQuery(READ_QUERY, null);

        if(cursor.moveToFirst()){
            do{
                dbRegister = new Combustivel();

                dbRegister.setId(cursor.getInt(0));
                dbRegister.setFuelType(cursor.getString(1));
                dbRegister.setFuelPrice(cursor.getDouble(2));
                dbRegister.setSuggestion(cursor.getString(3));

                lista.add(dbRegister);
            }while(cursor.moveToNext());
        }else{
            //
        }


        return lista;
    }


}
