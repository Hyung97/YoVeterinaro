package com.crisantru.yoveterinario;

import android.content.Intent;
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

import data.DoctorDbHelper;

public class DoctorActivity extends AppCompatActivity {

    private DoctorDbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        final ListView listView = (ListView) findViewById(R.id.ListView_doctor);
        listView.setClickable(true);

        db = new DoctorDbHelper(this);
        ArrayList<String> listDoctor = new ArrayList<>();
        Cursor data = db.getListContent();

        if(data.getCount() == 0){
            Toast toast = Toast.makeText(this, "No hay Clínicas Registradas que Mostrar.", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            while(data.moveToNext()){
                listDoctor.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listDoctor);
                listView.setAdapter(listAdapter);
            }
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String doctor = adapterView.getItemAtPosition(i).toString();
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(), a, Toast.LENGTH_SHORT);
                //toast.show();
                Intent intent = new Intent(DoctorActivity.this, DoctorDetail.class);
                intent.putExtra("doctor", doctor);
                startActivity(intent);



            }
        });
    }
}
