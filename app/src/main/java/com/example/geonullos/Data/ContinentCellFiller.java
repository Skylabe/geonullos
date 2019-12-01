package com.example.geonullos.Data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geonullos.Component.ContinentCell;
import com.example.geonullos.R;

import java.util.List;

public class ContinentCellFiller extends RecyclerView.Adapter<ContinentCell> {

    List<Continent> list;

    //ajouter un constructeur prenant en entrée une liste
    public ContinentCellFiller(List<Continent> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public ContinentCell onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
        return new ContinentCell(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(ContinentCell continentCell, int position) {
        Continent continent = list.get(position);
        continentCell.bind(continent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}