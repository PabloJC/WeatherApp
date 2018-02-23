package com.pabji.wheatherapp.data.net.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SearchEntity {

    @SerializedName("geonames")
    @Expose
    public List<GeonameEntity> geonames;

}
