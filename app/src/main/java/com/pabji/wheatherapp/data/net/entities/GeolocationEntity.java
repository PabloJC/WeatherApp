package com.pabji.wheatherapp.data.net.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GeolocationEntity {

    @SerializedName("east")
    @Expose
    public Double east;

    @SerializedName("south")
    @Expose
    public Double south;

    @SerializedName("north")
    @Expose
    public Double north;

    @SerializedName("west")
    @Expose
    public Double west;
}
