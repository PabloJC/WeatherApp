package com.pabji.wheatherapp.data.net.retrofit;

import com.pabji.wheatherapp.data.net.entities.SearchEntity;
import com.pabji.wheatherapp.data.net.entities.WeatherObservationEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface ApiInterface {

    @GET("searchJSON")
    Call<SearchEntity> searchCity(@QueryMap Map<String, String> options);

    @GET("weatherJSON")
    Call<WeatherObservationEntity> getWeather(@QueryMap Map<String, String> options);
}
