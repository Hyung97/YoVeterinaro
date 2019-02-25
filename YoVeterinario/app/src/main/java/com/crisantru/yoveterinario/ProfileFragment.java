package com.crisantru.yoveterinario;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import data.DoctorDbHelper;
import data.UserDbHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private UserDbHelper db;
    private Context context;

    public ProfileFragment() {
        // Required empty public constructor
    }

    private TextView txt_name;
    //private TextView txt_g;
    private TextView txt_email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((UserActivity)getActivity()).setActionBarTitle("Perfil");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txt_name = view.findViewById(R.id.txt_name);
        //txt_g = view.findViewById(R.id.txt_g);
        txt_email = view.findViewById(R.id.txt_email);
        context = view.getContext();
        Boolean s;

        db = new UserDbHelper(context);
        Cursor data = db.getUserContent();

        while (data.moveToNext()){
            txt_name.setText(data.getString(1) + " " + data.getString(2));
            //txt_g.setText(data.getString(3));
            txt_email.setText(data.getString(4));
        }



        return view;
    }

}
