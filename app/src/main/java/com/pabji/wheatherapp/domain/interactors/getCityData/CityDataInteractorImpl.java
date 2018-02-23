package com.pabji.wheatherapp.domain.interactors.getCityData;

import com.pabji.wheatherapp.data.bbdd.History.HistoryDBManager;
import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.data.net.requestsModels.CityDataRequest;
import com.pabji.wheatherapp.data.repositories.weather.WeatherRepository;
import com.pabji.wheatherapp.data.repositories.weather.WeatherRepositoryImpl;
import com.pabji.wheatherapp.domain.base.BaseInteractor;

import java.util.List;


public class CityDataInteractorImpl extends BaseInteractor implements CityDataInteractor {

    private final WeatherRepository mRepository;
    private ResponseCallback<WeatherObservation> mCallback;
    private CityDataRequest mRequest;

    public CityDataInteractorImpl() {
        mRepository = WeatherRepositoryImpl.getInstance();
    }

    @Override
    public void build(CityDataRequest request, ResponseCallback<WeatherObservation> callback) {
        mCallback = callback;
        mRequest = request;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mRepository.getCityData(mRequest,mCallback);
            }
        });
    }
}
