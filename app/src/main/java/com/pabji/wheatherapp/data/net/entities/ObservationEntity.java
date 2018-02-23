package com.pabji.wheatherapp.data.net.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ObservationEntity {

    @SerializedName("temperature")
    @Expose
    public String temperature;

    @SerializedName("clouds")
    @Expose
    public String clouds;

    @SerializedName("humidity")
    @Expose
    public Integer humidity;
}
