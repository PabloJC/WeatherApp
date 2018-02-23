package com.pabji.wheatherapp.data.net.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherObservationEntity {

    @SerializedName("weatherObservations")
    @Expose
    public List<ObservationEntity> weatherObservations;
}
