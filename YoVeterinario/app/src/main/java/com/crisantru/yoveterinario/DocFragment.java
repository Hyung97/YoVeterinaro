package com.crisantru.yoveterinario;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

;
import data.DoctorDbHelper;


public class DocFragment extends Fragment {
    private DoctorDbHelper db;
    private Context context;

    public DocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((UserActivity)getActivity()).setActionBarTitle("Médicos Veterinarios");
        View view = inflater.inflate(R.layout.fragment_doc, container, false);

        context = view.getContext();

        final ListView listView = (ListView) view.findViewById(R.id.ListView_doctor);
        listView.setClickable(true);

        db = new DoctorDbHelper(context);
        ArrayList<String> listDoctor = new ArrayList<>();
        Cursor data = db.getListContent();

        if(data.getCount() == 0){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "No hay Clínicas Registradas que Mostrar.", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            while(data.moveToNext()){
                listDoctor.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,listDoctor);
                listView.setAdapter(listAdapter);
            }
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String doctor = adapterView.getItemAtPosition(i).toString();
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(), a, Toast.LENGTH_SHORT);
                //toast.show();
                Intent intent = new Intent(context, DoctorDetail.class);
                intent.putExtra("doctor", doctor);
                startActivity(intent);



            }
        });



        return view;
    }


}
