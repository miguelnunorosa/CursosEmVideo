package io.github.miguelnunorosa.appgaseta.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE_FUEL = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT,  plate TEXT)";
    private static final String CREATE_TABLE_VEHICLE = "CREATE TABLE Vehicle (id INTEGER PRIMARY KEY AUTOINCREMENT,  fuelType TEXT,  fuelPrice REAL,  suggestion TEXT)";

    Cursor cursor;
    SQLiteDatabase db;




    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FUEL);
        db.execSQL(CREATE_TABLE_VEHICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }
}
