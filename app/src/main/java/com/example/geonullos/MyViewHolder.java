package com.example.geonullos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geonullos.R;
import com.squareup.picasso.Picasso;

public class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(MyObject myObject){
        textViewView.setText(myObject.getText());
        Picasso.get().load(myObject.getImageUrl()).centerCrop().fit().into(imageView);
    }
}