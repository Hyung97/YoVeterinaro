package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by crisantru on 17/04/18.
 */

public class UserDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";
    public static final int DATABASE_VERSION =1;

    public static final String CREATE_TABLE = "create table " + UserContract.UserEntry.TABLE_NAME + "("
            + UserContract.UserEntry.USER_ID + " integer primary key autoincrement, "
            + UserContract.UserEntry.NAME + " text, "
            + UserContract.UserEntry.LASTNAME + " text, "
            + UserContract.UserEntry.GENDER + " text, "
            + UserContract.UserEntry.EMAIL + " text, "
            + UserContract.UserEntry.PASSWORD + " text);";

    public static final String DROP_TABLE = "drop table if exist " + UserContract.UserEntry.TABLE_NAME;

    public UserDbHelper(Context context){
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

    public void save(String name, String lastname, String gender, String email, String password, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();

        //contentValues.put(UserContract.UserEntry.USER_ID, user_id);
        contentValues.put(UserContract.UserEntry.NAME, name);
        contentValues.put(UserContract.UserEntry.LASTNAME, lastname);
        contentValues.put(UserContract.UserEntry.GENDER, gender);
        contentValues.put(UserContract.UserEntry.EMAIL, email);
        contentValues.put(UserContract.UserEntry.PASSWORD, password);

        db.insert(UserContract.UserEntry.TABLE_NAME, null, contentValues);
        Log.d("Database Operations", "One Raw Inserted...");

    }

    public Cursor getUserContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME, null );

        return data;
    }


}
