package com.pabji.wheatherapp.domain.interactors.saveCity;

import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.domain.base.Interactor;


public interface SaveCityInteractor extends Interactor {
    void build(CityModel model, ResponseCallback<Boolean> callback);
}
