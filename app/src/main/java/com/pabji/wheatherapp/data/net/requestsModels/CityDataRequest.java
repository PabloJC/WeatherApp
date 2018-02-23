package com.pabji.wheatherapp.data.net.requestsModels;

import com.pabji.wheatherapp.data.models.CityModel;

import java.util.HashMap;
import java.util.Map;

public class CityDataRequest extends BaseRequest {

    private Double mNorth;
    private Double mSouth;
    private Double mWest;
    private Double mEast;

    public CityDataRequest(CityModel model) {
        mNorth = model.getNorth();
        mSouth = model.getSouth();
        mWest = model.getWest();
        mEast = model.getEast();
    }

    @Override
    public Map<String, String> toQueryMap() {
        Map<String, String> data = new HashMap<>();
        data.put("north", String.valueOf(mNorth));
        data.put("south", String.valueOf(mSouth));
        data.put("east", String.valueOf(mEast));
        data.put("west", String.valueOf(mWest));
        data.put("username", getUsername());

        return data;
    }
}
