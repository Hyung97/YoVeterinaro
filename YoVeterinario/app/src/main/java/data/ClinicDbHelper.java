package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ClinicDbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "clinic.db";
    public static final int DATABASE_VERSION =1;

    public static final String CREATE_TABLE = "create table " + ClinicContract.ClinicEntry.TABLE_NAME + "("
            + ClinicContract.ClinicEntry.CLINIC_ID + " integer primary key autoincrement, "
            + ClinicContract.ClinicEntry.TITLE + " text, "
            + ClinicContract.ClinicEntry.SNIPPED + " text, "
            + ClinicContract.ClinicEntry.POSITION + " text);";;

    public static final String DROP_TABLE = "drop table if exist " + ClinicContract.ClinicEntry.TABLE_NAME;

    public ClinicDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations", "Database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("Database Operations", "Table created...");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(CREATE_TABLE);
        onCreate(db);

    }

    public void save(String title, String snipped, String position, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ClinicContract.ClinicEntry.TITLE, title);
        contentValues.put(ClinicContract.ClinicEntry.SNIPPED, snipped);
        contentValues.put(ClinicContract.ClinicEntry.POSITION, position);

        db.insert(ClinicContract.ClinicEntry.TABLE_NAME, null, contentValues);
        Log.d("Database Operations", "One Raw Inserted...");

    }

    public boolean delete(String title){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ClinicContract.ClinicEntry.TABLE_NAME, ClinicContract.ClinicEntry.TITLE+"="+ "'" +title + "'", null);

        return true;
    }

    public Cursor getListContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + ClinicContract.ClinicEntry.TABLE_NAME, null );

        return data;
    }

    public Cursor getClinic(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery( "SELECT * FROM " + ClinicContract.ClinicEntry.TABLE_NAME + " WHERE " +
                ClinicContract.ClinicEntry.TITLE +"="+ "'" +name + "'", null);

        return data;
    }


}
