package com.crisantru.yoveterinario;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import data.ClinicDbHelper;
import data.DoctorDbHelper;

public class DoctorDetail extends AppCompatActivity {

    private DoctorDbHelper db;
    private TextView txt_name;
    private TextView txt_email;
    private TextView txt_espec;
    private TextView txt_clinic;
    private TextView txt_cedprof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        String doctor = getIntent().getStringExtra("doctor");
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        txt_clinic = findViewById(R.id.txt_clinic);
        txt_espec = findViewById(R.id.txt_espe);
        txt_cedprof = findViewById(R.id.txt_cedprof);

        db = new DoctorDbHelper(this);
        Cursor data = db.getDoctor(doctor);

        while (data.moveToNext()){
            txt_name.setText(data.getString(1) + " " + data.getString(2));
            txt_email.setText(data.getString(3));
            txt_clinic.setText(data.getString(6));
            txt_cedprof.setText(data.getString(4));
            txt_espec.setText(data.getString(5));
        }

    }
}
