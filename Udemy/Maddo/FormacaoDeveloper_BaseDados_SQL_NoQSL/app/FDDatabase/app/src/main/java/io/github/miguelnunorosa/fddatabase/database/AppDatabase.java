package io.github.miguelnunorosa.fddatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Calendar;


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


    private String dataHora;
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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }


    public boolean insert(String tabela, ContentValues data){
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


    public boolean update(String tabela, ContentValues data){
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
