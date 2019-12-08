package com.example.geonullos.Data;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Country implements Serializable {
    private String name;
    private String capital;
    private String demonym;
    private String flag;
    private Double[] latlng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Double[] getLatlng(){
        return latlng;
    }

    public void setLatlng(Double[] latlng){
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", demonym='" + demonym + '\'' +
                ", flag='" + flag + '\'' +
                ", latlng='" + latlng + '\'' +
                '}';
    }
}
