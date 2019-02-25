package com.crisantru.yoveterinario;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import data.ClinicDbHelper;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Context context;
    private ClinicDbHelper db;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((UserActivity)getActivity()).setActionBarTitle("Clínicas Xalapa");

        //save fragment on variable for set intent
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //equal to this in the Activity
        context = view.getContext();

        final ListView listView = (ListView) view.findViewById(R.id.ListView_clinic);
        listView.setClickable(true);

        db = new ClinicDbHelper(context);
        ArrayList<String> listClinic = new ArrayList<>();
        Cursor data = db.getListContent();

        if(data.getCount() == 0){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "No hay Clínicas Registradas que Mostrar.", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            while(data.moveToNext()){
                listClinic.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,listClinic);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String clinic = adapterView.getItemAtPosition(i).toString();

                Intent intent = new Intent(context, ClinicDetail.class);
                intent.putExtra("clinic", clinic);
                startActivity(intent);

            }
        });



        return view;
    }

}
