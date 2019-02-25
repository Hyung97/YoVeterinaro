package com.crisantru.yoveterinario;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.UserDbHelper;

public class MainActivity extends AppCompatActivity {

    private Context thiscont = this;
    private EditText txt_user;
    private EditText txt_password;
    private Button btn_iniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_user = (EditText)findViewById(R.id.txt_user);
        txt_password = (EditText)findViewById(R.id.txt_password1);
        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);

    }

    public void ingresar(View view){

        if(txt_user.getText().toString().isEmpty() || txt_password.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Campos Vacios!!!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Cursor cursor;

        UserDbHelper userDbHelper = new UserDbHelper(thiscont);
        SQLiteDatabase db = userDbHelper.getReadableDatabase();

        String username = txt_user.getText().toString();
        String password = txt_password.getText().toString();

        cursor = db.rawQuery("select email, password from user_info where email='"+username+"' and password='"+password+"'",null);

        if(cursor.moveToFirst()==true){
            String user = cursor.getString(0);
            String pass = cursor.getString(1);

            if(username.equals(user) && password.equals(pass)){
                Intent userHome = new Intent(this, UserActivity.class);
                startActivity(userHome);

                txt_user.setText("");
                txt_password.setText("");
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña incorrecta", Toast.LENGTH_SHORT);
            toast.show();
            txt_password.setText("");
        }
    }

    public void new_register(View view){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);

    }
}
