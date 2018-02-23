package com.pabji.wheatherapp.data.repositories.weather;

import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.data.net.requestsModels.CityDataRequest;
import com.pabji.wheatherapp.data.net.requestsModels.SearchRequest;

import java.util.List;


public interface WeatherRepository {

    void searchCity(SearchRequest request, ResponseCallback<List<CityModel>> callback);

    void getCityData(CityDataRequest request, ResponseCallback<WeatherObservation> callback);
}
