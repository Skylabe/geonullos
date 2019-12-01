package com.example.geonullos.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CountryList extends Fragment {
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentlist, container, false);

        String continent = this.getArguments().getString("continent");

        list = view.findViewById(R.id.countryList);
        arrayList = new ArrayList<String>();



        CountriesService countriesService = new Retrofit.Builder()
                .baseUrl(CountriesService.ENDPOINT)

                //convertie le json automatiquement
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountriesService.class);
        Log.w("INFO", "Continent demandé : " + continent);
        countriesService.listCountry(continent).enqueue(new Callback<List<Country>>() {

            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> listCountry = response.body();
                fillList(listCountry);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
            }
        });

        return view;
    }

    public void fillList(List<Country> countries) {
        CustomeListElement customAdapter = new CustomeListElement(view.getContext(), countries);
        list.setAdapter(customAdapter);

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data

        // Here, you set the data in your ListView
        //list.setAdapter(adapter);
        for(Country country : countries){
            arrayList.add(country.getName());
            // next thing you have to do is check if your adapter has changed
           // adapter.notifyDataSetChanged();
        }
    }
}
