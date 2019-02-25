package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DoctorDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "doctor.db";
    public static final int DATABASE_VERSION =1;

    public static final String CREATE_TABLE = "create table " + DoctorContract.DoctorEntry.TABLE_NAME + "("
            + DoctorContract.DoctorEntry.DOCTOR_ID + " integer primary key autoincrement, "
            + DoctorContract.DoctorEntry.NAME + " text, "
            + DoctorContract.DoctorEntry.LASTNAME + " text, "
            + DoctorContract.DoctorEntry.CORREO + " text, "
            + DoctorContract.DoctorEntry.CEDPROF + " text, "
            + DoctorContract.DoctorEntry.ESPECIALIDAD + " text, "
            + DoctorContract.DoctorEntry.VETERINARIA + " text);";;

    public static final String DROP_TABLE = "drop table if exist " + DoctorContract.DoctorEntry.TABLE_NAME;

    public DoctorDbHelper(Context context){
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

    public void save(String name, String lastname, String correo, String cedprof, String especialidad, String veterinaria, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DoctorContract.DoctorEntry.NAME, name);
        contentValues.put(DoctorContract.DoctorEntry.LASTNAME, lastname);
        contentValues.put(DoctorContract.DoctorEntry.CORREO, correo);
        contentValues.put(DoctorContract.DoctorEntry.CEDPROF, cedprof);
        contentValues.put(DoctorContract.DoctorEntry.ESPECIALIDAD, especialidad);
        contentValues.put(DoctorContract.DoctorEntry.VETERINARIA, especialidad);


        db.insert(DoctorContract.DoctorEntry.TABLE_NAME, null, contentValues);
        Log.d("Database Operations", "One Raw Inserted...");

    }

    public Cursor getListContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + DoctorContract.DoctorEntry.TABLE_NAME, null );

        return data;
    }

    public Cursor getDoctor(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery( "SELECT * FROM " + DoctorContract.DoctorEntry.TABLE_NAME + " WHERE " +
                DoctorContract.DoctorEntry.NAME+"="+ "'" +name + "'", null);

        return data;
    }

}
