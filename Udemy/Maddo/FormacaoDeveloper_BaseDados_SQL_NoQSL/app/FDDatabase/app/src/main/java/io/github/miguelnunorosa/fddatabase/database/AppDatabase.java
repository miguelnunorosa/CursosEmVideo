package io.github.miguelnunorosa.fddatabase.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.miguelnunorosa.fddatabase.model.Aluno;


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

    private static final String LIST_ALL_DATA = "SELECT * FROM aluno ORDER BY nome";
    private String SQL;
    private String dataHora;
    Cursor cursor;
    SQLiteDatabase db;


    public AppDatabase(Context ctx){ //CREATE DATABASE
        super(ctx,DB_NAME,null,DB_VERSION);

        db = getWritableDatabase();

        Log.i("FD_LOG","(AppDatabase) -> Conected to "+DB_NAME+" Ver. "+DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) { //CREATE TABLE

        try {
            db.execSQL(TABELA_ALUNO);
            Log.i("FD_LOG","(AppDatabase) -> Created table Aluno.");

        }catch (SQLException e){
            Log.e("FD-LOG", "(AppDatabase) -> Error on create aluno table. " + e.getMessage());
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //ALTER TABLE
    }


    public boolean insert(String tabela, ContentValues data){ //INSERT DATA
        boolean retorno = true;
        dataHora = getDateTime();

        try{
            data.put("dataInc", dataHora);
            data.put("dataAlt", dataHora);

            retorno = db.insert(tabela, null, data) > 0; //if retorno > 0 means that we can save data on database
        }catch (SQLException e){
            Log.e("FD-LOG", "(AppDatabase) -> Error inserted data on aluno table. " + e.getMessage());
            return false;
        }

        return retorno;
    }


    public boolean update(String tabela, ContentValues data){ //UPDATE DATA
        boolean retorno = true;
        dataHora = getDateTime();

        try{
            data.put("dataAlt", dataHora);
            int id = data.getAsInteger("id");

            retorno = db.update(tabela, data, "id=?", new String[]{Integer.toString(id)}) > 0; //if retorno > 0 means that we can save data on database
        }catch (SQLException e){
            Log.e("FD-LOG", "(AppDatabase) -> Error when update aluno " + e.getMessage());
            return false;
        }

        return retorno;
    }


    public boolean delete(String tabela, ContentValues data){ //DELETE DATA
        boolean retorno = true;

        try{
            int id = data.getAsInteger("id");

            retorno = db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0; //if retorno > 0 means that we can save data on database
        }catch (SQLException e){
            Log.e("FD-LOG", "(AppDatabase) -> Error when delete aluno " + e.getMessage());
            return false;
        }

        return retorno;
    }


    @SuppressLint("Range")
    public List<Aluno> getAllAlunos(){ //SELECT (ALL) DATA

        List<Aluno> list = new ArrayList<>();
        Aluno obj;
        boolean status = false;

        try{
            cursor = db.rawQuery(LIST_ALL_DATA, null);

            if(cursor.moveToFirst()){
                do {
                    obj = new Aluno();

                    obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    obj.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                    obj.setEmail(cursor.getString(cursor.getColumnIndex("email")));

                    status = cursor.getInt(cursor.getColumnIndex("status")) != 0;

                    obj.setStatus(status);

                    list.add(obj);
                }while (cursor.moveToNext());
            }

        }catch (SQLException e){
            Log.e("FD-LOG", "(AppDatabase) -> Error when listing data from aluno " + e.getMessage());
        }

        return list;
    }




    private String getDateTime() {

        String day, month, year;
        String hour, minute, second;

        try {
            Calendar calendar = Calendar.getInstance();

            int calDia = calendar.get(Calendar.DAY_OF_MONTH);
            int calMes = calendar.get(Calendar.MONTH) + 1;

            day = (calDia <= 9) ? "0" + calendar.get(Calendar.DAY_OF_MONTH) : Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            month = (calMes <= 9) ? "0" + calMes : Integer.toString(calMes);
            year = Integer.toString(calendar.get(Calendar.YEAR));

            int iHour = calendar.get(Calendar.HOUR_OF_DAY);
            int iMinute = calendar.get(Calendar.MINUTE);
            int iSecond = calendar.get(Calendar.SECOND);

            hour = (iHour <= 9) ? "0" + calendar.get(Calendar.HOUR_OF_DAY) : Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            minute = (iMinute <= 9) ? "0" + calendar.get(Calendar.MINUTE) : Integer.toString(calendar.get(Calendar.MINUTE));
            second = (iSecond <= 9) ? "0" + calendar.get(Calendar.SECOND) : Integer.toString(calendar.get(Calendar.SECOND));

            return day + "/" + month + "/" + year + " - " + hour + ":" + minute + ":" + second;
        }catch (Exception e){
            return "00/00/0000 - 00:00:00";
        }

    }

}
