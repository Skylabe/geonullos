package com.example.geonullos.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountriesService {

    public static final String ENDPOINT = "https://restcountries.eu/rest/v2/";

    @GET("region/{continent}")
    Call<List<Country>> listCountry(@Path("continent") String continent);
}