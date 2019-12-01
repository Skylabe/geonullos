package com.example.geonullos.Activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.geonullos.Fragment.CountryList;
import com.example.geonullos.R;

public class MainActivity extends AppCompatActivity {
    String continent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Définissez votre vue, rien de plus. Tout sera pris en charge par le fragment qui affiche les données
        Bundle extras = getIntent().getExtras();
        Log.w("INFO", extras.toString());
        continent = extras.getString("chosenContinent");
        String title = "";
        switch(continent){
            case "afrique":
                continent = "africa";
                title = "Africa";
                break;
            case "amerique":
                title = "Americas";
                break;
            case "asie":
                title = "Asia";
                break;
            case "europe":
                title = "Europe";
                break;
            case "oceanie":
                title = "Oceania";
                break;
        }
        setTitle(title);
        setContentView(R.layout.mainactivity);


        //Retrouver votre fragment en utilisant son identifiant (si besoin)
        //CountryList mainFragment=findViewById(R.id.fragmentlist);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof CountryList) {
            Bundle args = new Bundle();
            args.putString("continent", continent);
            fragment.setArguments(args);
        }
    }

}
