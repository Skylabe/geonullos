package com.example.geonullos.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.geonullos.Data.Continent;
import com.example.geonullos.Data.ContinentCellFiller;
import com.example.geonullos.Data.RecyclerItemClickListener;
import com.example.geonullos.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Continent> continents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //remplir la ville
        ajouterVilles();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setAdapter(new ContinentCellFiller(continents));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //start new activity here
                String param = "";
                switch(position){
                    case 0:
                        param = "afrique";
                        break;
                    case 1:
                        param = "adn";
                        break;
                    case 2:
                        param = "ads";
                        break;
                    case 3:
                        param = "asie";
                        break;
                    case 4:
                        param = "europe";
                        break;
                }
                Intent mainActivity = new Intent(Home.this, MainActivity.class);
                mainActivity.putExtra("chosenContinent", param);
                startActivity(mainActivity);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    private void ajouterVilles() {
        continents.add(new Continent("Afrique", "afrique"));
        continents.add(new Continent("Amérique du nord", "adn"));
        continents.add(new Continent("Amérique du sud", "ads"));
        continents.add(new Continent("Asie", "asie"));
        continents.add(new Continent("Europe", "europe"));
    }

}