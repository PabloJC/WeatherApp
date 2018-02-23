package com.pabji.wheatherapp.data.bbdd.History;

import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;

import java.util.List;


public interface HistoryDB {

    void getHistory(ResponseCallback<List<CityModel>> callback);
    void saveSearch(CityModel cityModel, ResponseCallback<Boolean> callback);
}
