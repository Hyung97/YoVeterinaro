package com.crisantru.yoveterinario;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.UUID;

import data.UserDbHelper;

public class RegisterUser extends AppCompatActivity {
    private Context thiscont = this;
    private EditText txt_name;
    private EditText txt_apellido;
    private EditText txt_email;
    private EditText txt_pass1;
    private EditText txt_pass2;
    private RadioButton rbtn_h;
    private RadioButton rbtn_m;
    private Button btn_guardar;
    public String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        txt_name = (EditText)findViewById(R.id.txt_name);
        txt_apellido = (EditText)findViewById(R.id.txt_apellido);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_pass1 = (EditText)findViewById(R.id.txt_password1);
        txt_pass2 = (EditText)findViewById(R.id.txt_password2);
        rbtn_h = (RadioButton)findViewById(R.id.rbtn_h);
        rbtn_m = (RadioButton)findViewById(R.id.rbtn_m);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);

        if(rbtn_h.isChecked()== true){
            gender = "Masculino";
            Toast toast = Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT);
            toast.show();

        }

        if(rbtn_m.isChecked()){
            gender = "Femenino";
            Toast toast = Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT);
            toast.show();
        }


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validacion de campos vacios
                if(txt_name.getText().toString().isEmpty() || txt_apellido.getText().toString().isEmpty() ||
                        rbtn_h.getText().toString().isEmpty() || rbtn_m.getText().toString().isEmpty() ||
                        txt_email.getText().toString().isEmpty() || txt_pass1.getText().toString().isEmpty() ||
                        txt_pass2.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Faltan campos de llenar", Toast.LENGTH_SHORT);
                    toast.show();

                    return;
                }


                final String email = txt_email.getText().toString().trim();
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                //validacion email
               if(! email.matches(emailPattern) && email.length() > 0){
                   Toast toast = Toast.makeText(getApplicationContext(), "Correo Invalido", Toast.LENGTH_SHORT);
                   toast.show();
                   return;
               }

               //validacion longitud de contraseña
                if(txt_pass1.getText().toString().length() <= 7 ){
                    Toast toast = Toast.makeText(getApplicationContext(), "La Contraseña debe tener\n al menos 8 caracteres", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                //validacion de password match
                if(!txt_pass1.getText().toString().equals(txt_pass2.getText().toString())){
                    Toast toast = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                    toast.show();
                    txt_pass1.setText("");
                    txt_pass2.setText("");

                    return;
                }

                //Integer user_id = UUID.randomUUID();
                String name = txt_name.getText().toString();
                Log.d("Name User Database", name);
                String sex = gender;
                String lastname = txt_apellido.getText().toString();
                String email2 = txt_email.getText().toString();
                String password = txt_pass2.getText().toString();

                UserDbHelper userDbHelper = new UserDbHelper(thiscont);
                SQLiteDatabase db = userDbHelper.getWritableDatabase();

                userDbHelper.save( name, lastname, sex, email2, password, db);
                userDbHelper.close();

                txt_name.setText("");
                txt_apellido.setText("");
                txt_email.setText("");
                txt_pass1.setText("");
                txt_pass2.setText("");

                Toast toast = Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT);
                toast.show();

                finish();


            }
        });



    }
}
