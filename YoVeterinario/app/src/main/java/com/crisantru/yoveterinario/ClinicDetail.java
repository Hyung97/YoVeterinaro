package com.crisantru.yoveterinario;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import data.ClinicDbHelper;

public class ClinicDetail extends AppCompatActivity {

    private TextView lbl_clinic;
    private TextView lbl_dir;
    private ClinicDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detail);

        String clinic = getIntent().getStringExtra("clinic");

        lbl_dir = findViewById(R.id.lbl_dir);
        lbl_clinic = findViewById(R.id.lbl_clinic);
        lbl_clinic.setText(clinic);

        db = new ClinicDbHelper(this);
        Cursor data = db.getClinic(clinic);

        while (data.moveToNext()){
            lbl_dir.setText(data.getString(2));
        }

    }
}
