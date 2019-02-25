package com.crisantru.yoveterinario;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import data.ClinicContract;
import data.ClinicDbHelper;


public class GmapFragment extends Fragment implements OnMapReadyCallback {

    public TextView lbl_title;
    public TextView lbl_description;
    public Button btn_doctor;
    Context context;
    ClinicDbHelper db;

    public GmapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((UserActivity) getActivity()).setActionBarTitle("Yo-Veterinario");

        View view = inflater.inflate(R.layout.fragment_gmap, container, false);
        context = view.getContext();
        lbl_title = (TextView) view.findViewById(R.id.lbl_titleM);
        lbl_description = (TextView) view.findViewById(R.id.lbl_descriptionM);
        btn_doctor = (Button) view.findViewById(R.id.btn_doc);

        btn_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast toast1 = Toast.makeText(getActivity().getApplicationContext(), "Entra listener", Toast.LENGTH_SHORT);
                //toast1.show();
                //lbl_title.setText(lbl_description.getText());
                if(lbl_title.getText().toString().isEmpty() || lbl_description.toString().isEmpty()){
                    Toast toast = Toast.makeText(getActivity(), "Selecciona una Clínica", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(context, DoctorActivity.class);
                    intent.putExtra("clinic", lbl_title.getText().toString());
                    startActivity(intent);
                }

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment ft = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        ft.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //googleMap = googleMap;
        db = new ClinicDbHelper(getActivity().getApplicationContext());
        Cursor data = db.getListContent();
        
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setAllGesturesEnabled(true);

        while (data.moveToNext()){
            String[] slatlng = data.getString(3).split(" ");
            LatLng lat = new LatLng(Double.valueOf(slatlng[0]), Double.valueOf(slatlng[1]));

            googleMap.addMarker(new MarkerOptions()
                .title(data.getString(1))
                .snippet(data.getString(2))
                .position(lat)
            );

        }


        // Add a marker
        LatLng home = new LatLng(19.418101, -96.926964);
        googleMap.addMarker(new MarkerOptions()
                .position(home)
                .title("Marker in Home")
                .snippet("Hacienda Los Cafetales #85, Coatepec, Ver. "));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(home));


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {


            @Override
            public boolean onMarkerClick(Marker marker) {


                marker.showInfoWindow();
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Clínica Seleccionada", Toast.LENGTH_SHORT);
                toast.show();

                lbl_title.setText(marker.getTitle());
                lbl_description.setText(marker.getSnippet());

                return true;
            }


        });


    }

}
