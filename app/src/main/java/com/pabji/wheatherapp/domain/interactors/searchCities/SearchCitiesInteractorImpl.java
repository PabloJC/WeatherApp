package com.pabji.wheatherapp.domain.interactors.searchCities;

import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.net.requestsModels.SearchRequest;
import com.pabji.wheatherapp.data.repositories.weather.WeatherRepository;
import com.pabji.wheatherapp.data.repositories.weather.WeatherRepositoryImpl;
import com.pabji.wheatherapp.domain.base.BaseInteractor;

import java.util.List;


public class SearchCitiesInteractorImpl extends BaseInteractor implements SearchCitiesInteractor {

    private final WeatherRepository mRepository;
    private ResponseCallback<List<CityModel>> mCallback;
    private SearchRequest mRequest;

    public SearchCitiesInteractorImpl() {
        mRepository = WeatherRepositoryImpl.getInstance();
    }

    @Override
    public void build(SearchRequest request, ResponseCallback<List<CityModel>> callback) {
        mCallback = callback;
        mRequest = request;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mRepository.searchCity(mRequest,mCallback);
            }
        });
    }
}
