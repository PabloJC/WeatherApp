package com.pabji.wheatherapp.data.net.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AlternateNameEntity {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("lang")
    @Expose
    public String lang;
}
