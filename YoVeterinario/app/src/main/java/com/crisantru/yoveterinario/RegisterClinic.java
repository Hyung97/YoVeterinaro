package com.crisantru.yoveterinario;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.ClinicDbHelper;
import data.UserDbHelper;

public class RegisterClinic extends AppCompatActivity {

    private Context thiscont = this;
    private EditText txt_title;
    private EditText txt_snipped;
    private EditText txt_position;
    private Button btn_save;
    private Button btn_medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_clinic);

        txt_title = findViewById(R.id.txt_title);
        txt_snipped = findViewById(R.id.txt_snipped);
        txt_position = findViewById(R.id.txt_position);
        btn_save = findViewById(R.id.btn_save_clinic);
        btn_medico = findViewById(R.id.btn_medico);

        btn_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_title.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Introduce el Nombre de la Clínica", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    //Intent intent = new Intent(RegisterClinic.this, RegisterMedic.class);
                    //intent.putExtra("clinica", txt_title.getText());
                    //startActivity(intent);
                    Intent intent = new Intent(RegisterClinic.this, RegisterDoctor.class);
                    intent.putExtra("clinica", txt_title.getText().toString());
                    startActivity(intent);

                }



            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validar campos
                if(txt_title.getText().toString().isEmpty() || txt_snipped.getText().toString().isEmpty() ||
                        txt_position.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Faltan campos de llenar", Toast.LENGTH_SHORT);
                    toast.show();

                    return;

                }

                //var for save data on DB
                String title = txt_title.getText().toString();
                String snipped = txt_snipped.getText().toString();
                String position = txt_position.getText().toString();

                ClinicDbHelper clinicDbHelper = new ClinicDbHelper(thiscont);
                SQLiteDatabase db = clinicDbHelper.getWritableDatabase();

                clinicDbHelper.save(title, snipped, position, db);
                clinicDbHelper.close();

                txt_title.setText("");
                txt_snipped.setText("");
                txt_position.setText("");

                Toast toast = Toast.makeText(getApplicationContext(), "Clínica Registrada", Toast.LENGTH_SHORT);
                toast.show();

                finish();
            }

        });


    }
}
