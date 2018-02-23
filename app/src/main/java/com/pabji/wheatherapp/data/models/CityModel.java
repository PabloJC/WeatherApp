package com.pabji.wheatherapp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CityModel implements Parcelable{

    private Double east;
    private Double south;
    private Double north;
    private Double west;

    private Double latitude;
    private Double longitude;
    private String name;

    public CityModel(){

    }

    protected CityModel(Parcel in) {
        if (in.readByte() == 0) {
            east = null;
        } else {
            east = in.readDouble();
        }
        if (in.readByte() == 0) {
            south = null;
        } else {
            south = in.readDouble();
        }
        if (in.readByte() == 0) {
            north = null;
        } else {
            north = in.readDouble();
        }
        if (in.readByte() == 0) {
            west = null;
        } else {
            west = in.readDouble();
        }
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (east == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(east);
        }
        if (south == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(south);
        }
        if (north == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(north);
        }
        if (west == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(west);
        }
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CityModel> CREATOR = new Creator<CityModel>() {
        @Override
        public CityModel createFromParcel(Parcel in) {
            return new CityModel(in);
        }

        @Override
        public CityModel[] newArray(int size) {
            return new CityModel[size];
        }
    };

    public Double getEast() {
        return east;
    }

    public void setEast(Double east) {
        this.east = east;
    }

    public Double getSouth() {
        return south;
    }

    public void setSouth(Double south) {
        this.south = south;
    }

    public Double getNorth() {
        return north;
    }

    public void setNorth(Double north) {
        this.north = north;
    }

    public Double getWest() {
        return west;
    }

    public void setWest(Double west) {
        this.west = west;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean hasBounds(){
        return west != null && north != null && east != null && south != null;
    }

    public Boolean hasLocation(){
        return latitude != null && longitude != null;
    }

    @Override
    public boolean equals(Object obj) {
        CityModel model = (CityModel) obj;
        return model.getName().equals(name);
    }
}
