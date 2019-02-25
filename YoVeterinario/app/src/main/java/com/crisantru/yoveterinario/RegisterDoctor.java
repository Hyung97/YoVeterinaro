package com.crisantru.yoveterinario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import data.DoctorDbHelper;

public class RegisterDoctor extends AppCompatActivity {

    private Context thiscont = this;
    private TextView txt_clinica;
    private Button btn_save;
    private EditText txt_name;
    private EditText txt_lastname;
    private EditText txt_email;
    private EditText txt_cedprof;
    private EditText txt_espe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);

        final String clinic = getIntent().getStringExtra("clinica");
        txt_clinica = findViewById(R.id.txt_clinica);
        txt_name = findViewById(R.id.txt_name);
        txt_lastname = findViewById(R.id.txt_lastname);
        txt_email = findViewById(R.id.txt_email);
        txt_cedprof = findViewById(R.id.txt_ced);
        txt_espe = findViewById(R.id.txt_esp);

        btn_save = findViewById(R.id.btn_save_doctor);

        txt_clinica.setText(clinic);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_name.getText().toString().isEmpty() || txt_lastname.getText().toString().isEmpty() ||
                        txt_email.getText().toString().isEmpty() || txt_cedprof.getText().toString().isEmpty() ||
                        txt_espe.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Faltan campos de llenar", Toast.LENGTH_SHORT);
                    toast.show();

                    return;
                }

                //var for save data on DB
                String name = txt_name.getText().toString();
                String lastname = txt_lastname.getText().toString();
                String email = txt_email.getText().toString();
                String cedprof = txt_cedprof.getText().toString();
                String espe = txt_espe.getText().toString();


                DoctorDbHelper doctorDbHelper = new DoctorDbHelper(thiscont);
                SQLiteDatabase db = doctorDbHelper.getWritableDatabase();

                doctorDbHelper.save(name, lastname, email, cedprof, espe, clinic, db);
                doctorDbHelper.close();

                txt_name.setText("");
                txt_lastname.setText("");
                txt_email.setText("");
                txt_cedprof.setText("");
                txt_espe.setText("");

                Toast toast = Toast.makeText(getApplicationContext(), "Doctor Registrado a la Clínica", Toast.LENGTH_SHORT);
                toast.show();

                finish();
            }
        });
    }
}
