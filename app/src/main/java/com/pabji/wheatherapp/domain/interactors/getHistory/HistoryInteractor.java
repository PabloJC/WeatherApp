package com.pabji.wheatherapp.domain.interactors.getHistory;

import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.domain.base.Interactor;

import java.util.List;


public interface HistoryInteractor extends Interactor {
    void build(ResponseCallback<List<CityModel>> callback);
}
