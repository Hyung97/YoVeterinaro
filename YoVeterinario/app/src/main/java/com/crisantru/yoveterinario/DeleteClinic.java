package com.crisantru.yoveterinario;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import data.ClinicDbHelper;

public class DeleteClinic extends AppCompatActivity {

    private EditText txt_title;
    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_clinic);

        txt_title = (EditText)findViewById(R.id.txt_title);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean res = false;

                if(txt_title.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(DeleteClinic.this, "Introduce el Nombre de la Clínica", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    ClinicDbHelper db;
                    db = new ClinicDbHelper(DeleteClinic.this);
                    res = db.delete(txt_title.getText().toString());

                    if(res){
                        Toast toast = Toast.makeText(DeleteClinic.this, "Clínica Eliminada", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(DeleteClinic.this, "No se pudo Elimimar la Clínica que Introduciste", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }
}
