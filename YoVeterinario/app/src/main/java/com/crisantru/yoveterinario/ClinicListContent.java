package com.crisantru.yoveterinario;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import data.ClinicDbHelper;

public class ClinicListContent extends AppCompatActivity {

    private ClinicDbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_list_content);

        final ListView listView = (ListView) findViewById(R.id.ListView_clinic);
        listView.setClickable(true);

        db = new ClinicDbHelper(this);

        ArrayList<String> listClinic = new ArrayList<>();
        Cursor data = db.getListContent();

        if(data.getCount() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "No hay Clínicas Registradas que Mostrar.", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            while(data.moveToNext()){
                listClinic.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listClinic);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer id = i;
                String a = adapterView.getItemAtPosition(i).toString();
                Toast toast = Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT);
                toast.show();



            }
        });
    }
}
