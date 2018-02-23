package com.pabji.wheatherapp.domain.interactors.getCityData;

import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.data.net.requestsModels.CityDataRequest;
import com.pabji.wheatherapp.domain.base.Interactor;

import java.util.List;


public interface CityDataInteractor extends Interactor {
    void build(CityDataRequest request, ResponseCallback<WeatherObservation> callback);
}
