package com.example.geonullos.Activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.geonullos.Data.Country;
import com.example.geonullos.Fragment.CountryDetails;
import com.example.geonullos.R;

public class SecondActivity extends AppCompatActivity {
    Country country;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Définissez votre vue, rien de plus. Tout sera pris en charge par le fragment qui affiche les données
        Bundle extras = getIntent().getExtras();
        country = (Country)extras.getSerializable("chosenCountry");
        setTitle(country.getName());
        setContentView(R.layout.secondactivity);


        //Retrouver votre fragment en utilisant son identifiant (si besoin)
        //CountryList mainFragment=findViewById(R.id.fragmentlist);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof CountryDetails) {
            Bundle args = new Bundle();
            args.putSerializable("country", country);
            fragment.setArguments(args);
        }
    }

}
