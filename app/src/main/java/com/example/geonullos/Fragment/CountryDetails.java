package com.example.geonullos.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geonullos.Component.CustomeListElement;
import com.example.geonullos.Data.CountriesService;
import com.example.geonullos.Data.Country;
import com.example.geonullos.Data.Utils;
import com.example.geonullos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
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

    List<Country> favCountries;

    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentdetails, container, false);

        country = (Country)this.getArguments().getSerializable("country");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        sharedPreferences = view.getContext().getSharedPreferences("favCountry",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("listCountries", "");
        favCountries = gson.fromJson(json, new TypeToken<List<Country>>(){}.getType());
        if(favCountries == null){
            favCountries = new ArrayList<Country>();
        }

        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utils.exists(country, favCountries)){
                    fab.setImageResource(R.drawable.notfav);
                    favCountries = Utils.remove(country, favCountries);
                    Snackbar.make(view, country.getName() + " removed from favorite.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    fab.setImageResource(R.drawable.fav);
                    favCountries.add(country);
                    Snackbar.make(view, country.getName() + " add to favorite.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                Gson gson = new Gson();
                String json = gson.toJson(favCountries);
                sharedPreferences
                        .edit()
                        .putString("listCountries", json)
                        .apply();

            }
        });

        if(Utils.exists(country, favCountries)){
            fab.setImageResource(R.drawable.fav);
        }

        String pattern = "###,###.##";
        DecimalFormat df = new DecimalFormat(pattern);

        ImageView flag = view.findViewById(R.id.flag);
        Utils.fetchSvg(view.getContext(), country.getFlag(), flag);

        TextView capital = view.findViewById(R.id.capitalTxt);
        TextView demonym = view.findViewById(R.id.demonymTxt);
        TextView population = view.findViewById(R.id.populationTxt);
        TextView density = view.findViewById(R.id.densityTxt);
        TextView area = view.findViewById(R.id.areaTxt);
        TextView timezones = view.findViewById(R.id.timezonesTxt);

        capital.setText(capital.getText() + country.getCapital());
        demonym.setText(demonym.getText() + country.getDemonym());
        population.setText(population.getText() + df.format(country.getPopulation()) + " habitants");
        density.setText(density.getText() + df.format(country.getPopulation()/country.getArea()) + " habitants/km2");
        area.setText(area.getText() + df.format(country.getArea()) + " km2");
        String txt = "";
        int i = 0;
        for(String e : country.getTimezones()){
            if(i++ == 0){
                txt+=e;
            }else{
                txt+=" ; " + e;
            }
        }
        timezones.setText(timezones.getText() + txt);



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
