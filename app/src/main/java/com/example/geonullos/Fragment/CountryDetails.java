package com.example.geonullos.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geonullos.Component.CustomeListElement;
import com.example.geonullos.Data.CountriesService;
import com.example.geonullos.Data.Country;
import com.example.geonullos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CountryDetails extends Fragment implements OnMapReadyCallback  {
    private Country country;

    View view;
    private GoogleMap mMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentdetails, container, false);

        country = (Country)this.getArguments().getSerializable("country");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(R.drawable.fav);
                Snackbar.make(view, country.getName() + " add to favorite.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        Double[] coord = country.getLatlng();
        LatLng marker = new LatLng(coord[0], coord[1]);
        mMap.addMarker(new MarkerOptions().position(marker).title("Marker of " + country.getName()));
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(marker).
                tilt(60).
                zoom(5).
                bearing(0).
                build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}
