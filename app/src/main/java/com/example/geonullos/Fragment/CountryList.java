package com.example.geonullos.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geonullos.Activity.Home;
import com.example.geonullos.Activity.MainActivity;
import com.example.geonullos.Activity.SecondActivity;
import com.example.geonullos.Component.CustomeListElement;
import com.example.geonullos.Data.CountriesService;
import com.example.geonullos.Data.Country;
import com.example.geonullos.Data.Utils;
import com.example.geonullos.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CountryList extends Fragment {
    private ListView list;
    private ArrayList<String> arrayList;
    private ArrayList<Country> arrayCountry = new ArrayList<>();
    private ArrayList<Country> baseCountry = new ArrayList<>();

    String continent;

    View view;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentlist, container, false);

        continent = this.getArguments().getString("continent");

        list = view.findViewById(R.id.countryList);
        arrayList = new ArrayList<String>();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Country selectedItem = arrayCountry.get(position);

                Intent secondActivity = new Intent(view.getContext(), SecondActivity.class);
                secondActivity.putExtra("chosenCountry", selectedItem);
                startActivity(secondActivity);
            }
        });


        if(continent != "favorites"){
            CountriesService countriesService = new Retrofit.Builder()
                    .baseUrl(CountriesService.ENDPOINT)

                    //convertie le json automatiquement
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CountriesService.class);
            countriesService.listCountry(continent).enqueue(new Callback<List<Country>>() {

                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    Log.w("DEBUG", response.body().toString());
                    List<Country> listCountry = response.body();
                    baseCountry = (ArrayList<Country>)listCountry;
                    fillList(listCountry);
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {
                    Log.w("DEBUG", t.getMessage());
                }
            });
        } else{
            sharedPreferences = view.getContext().getSharedPreferences("favCountry",Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("listCountries", "");
            List<Country> favCountries = gson.fromJson(json, new TypeToken<List<Country>>(){}.getType());
            if(favCountries == null){
                favCountries = new ArrayList<Country>();
            }
            arrayCountry = (ArrayList<Country>)favCountries;
            baseCountry = (ArrayList<Country>)favCountries;
            fillList(arrayCountry);
        }

        SearchView simpleSearchView = (SearchView) view.findViewById(R.id.simpleSearchView); // inititate a search view

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*Log.w("DEBUG", query);
                if(query.length() == 0){
                    arrayCountry = baseCountry;
                    fillList(arrayCountry);
                    return false;
                }
                fillList(Utils.search(query, baseCountry));*/
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() == 0){
                    fillList(baseCountry);
                } else {
                    fillList(Utils.search(newText, baseCountry));
                }
                return false;
            }
        });

        return view;
    }

    public void fillList(List<Country> countries) {
        CustomeListElement customAdapter = new CustomeListElement(view.getContext(), countries);
        list.setAdapter(customAdapter);
        arrayCountry = new ArrayList<>();
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data

        // Here, you set the data in your ListView
        //list.setAdapter(adapter);
        for(Country country : countries){
            arrayList.add(country.getName());
            arrayCountry.add(country);
            // next thing you have to do is check if your adapter has changed
           // adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(continent == "favorites"){
            sharedPreferences = view.getContext().getSharedPreferences("favCountry",Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("listCountries", "");
            List<Country> favCountries = gson.fromJson(json, new TypeToken<List<Country>>(){}.getType());
            if(favCountries == null){
                favCountries = new ArrayList<Country>();
            }
            fillList(favCountries);
        }
    }
}
