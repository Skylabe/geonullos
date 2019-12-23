package com.example.geonullos.Component;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geonullos.Data.Country;
import com.example.geonullos.Data.Utils;
import com.example.geonullos.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class CustomeListElement extends BaseAdapter {
    Context context;
    List<Country> countryList;
    LayoutInflater inflter;

    public CustomeListElement(Context applicationContext, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.list_element, null);
        TextView country = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        country.setText(countryList.get(i).getName());
        String[] parts = countryList.get(i).getFlag().split("[.]");
        String extension = parts[parts.length-1];

        if(extension.equals("svg")){
            Utils.fetchSvg(view.getContext(), countryList.get(i).getFlag(), icon);
        }

        return view;
    }
}