package com.example.geonullos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CountryList extends Fragment {
    private TextView title;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlist, container, false);
        //Instancier vos composants graphique ici (faîtes vos findViewById)
        title = view.findViewById(R.id.title);
        String continent = this.getArguments().getString("continent");
        title.setText(continent);

        list = view.findViewById(R.id.countryList);
        arrayList = new ArrayList<String>();

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, arrayList);

        // Here, you set the data in your ListView
        list.setAdapter(adapter);
        for(int i = 0 ; i < 10 ; i++){
            arrayList.add(""+i);
            // next thing you have to do is check if your adapter has changed
            adapter.notifyDataSetChanged();
        }

        return view;
    }
}
