package com.example.geonullos.Component;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geonullos.R;

public class ContinentCell extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private ImageView imageView;

    public ContinentCell(View itemView) {
        super(itemView);

        textViewView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    public void bind(com.example.geonullos.Data.Continent continent){
        textViewView.setText(continent.getText());
        switch(continent.getImageName()){
            case "europe":
                imageView.setImageResource(R.drawable.europe);
                break;

            case "asie":
                imageView.setImageResource(R.drawable.asie);
                break;

            case "afrique":
                imageView.setImageResource(R.drawable.afrique);
                break;

            case "adn":
                imageView.setImageResource(R.drawable.adn);
                break;

            case "ads":
                imageView.setImageResource(R.drawable.ads);
                break;

        }
    }
}