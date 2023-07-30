package io.github.miguelnunorosa.fddatabase.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "fd_db.sqlite";
    private static final int DB_VERSION = 1;
    private static final String TABELA_ALUNO = "CREATE TABLE aluno\n" +
            " ( id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
            " nome TEXT, \n" +
            " email TEXT, \n" +
            " status INTEGER,\n" +
            " dataInc TEXT,\n" +
            " dataAlt TEXT )";


    Cursor cursor;
    SQLiteDatabase db;


    public AppDatabase(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);

        db = getWritableDatabase();

        Log.i("FD_LOG","(AppDatabase) -> Conected to "+DB_NAME+" Ver. "+DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(TABELA_ALUNO);
            Log.i("FD_LOG","(AppDatabase) -> Created table Aluno.");

        }catch (SQLException e){
            Log.e("FD-LOG", "(AppDatabase) -> Error on create aluno table. " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }


    //
}
