package com.pabji.wheatherapp.domain.interactors.searchCities;

import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.net.requestsModels.SearchRequest;
import com.pabji.wheatherapp.domain.base.Interactor;

import java.util.List;


public interface SearchCitiesInteractor extends Interactor {
    void build(SearchRequest request, ResponseCallback<List<CityModel>> callback);
}
