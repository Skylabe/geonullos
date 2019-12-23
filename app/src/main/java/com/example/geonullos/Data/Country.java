package com.example.geonullos.Data;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Country implements Serializable {
    private String name;
    private String capital;
    private String demonym;
    private String flag;
    private Double[] latlng;
    private String subregion;
    private long population;
    private double area;
    private String[] timezones;

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

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
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
