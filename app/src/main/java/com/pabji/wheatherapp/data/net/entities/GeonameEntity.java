package com.pabji.wheatherapp.data.net.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GeonameEntity {

    @SerializedName("bbox")
    @Expose
    public GeolocationEntity bbox;

    @SerializedName("lat")
    @Expose
    public String lat;

    @SerializedName("lng")
    @Expose
    public String lng;

    @SerializedName("alternateNames")
    @Expose
    public List<AlternateNameEntity> alternateNameEntities;
}
