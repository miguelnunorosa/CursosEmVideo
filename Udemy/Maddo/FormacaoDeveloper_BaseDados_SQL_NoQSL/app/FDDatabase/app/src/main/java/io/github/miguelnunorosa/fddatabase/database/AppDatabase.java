package io.github.miguelnunorosa.fddatabase.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "fd_db.sqlite";
    private static final int DB_VERSION = 1;

    Cursor cursor; //manipulate objects
    SQLiteDatabase db; //implement crud methods


    public AppDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
        Log.i("FD-LOG (AppDatabase) -> ", "Database created.");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }


    //
}
