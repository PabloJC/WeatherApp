package com.pabji.wheatherapp.domain.interactors.saveCity;

import com.pabji.wheatherapp.data.bbdd.History.HistoryDBManager;
import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.domain.base.BaseInteractor;


public class SaveCityInteractorImpl extends BaseInteractor implements SaveCityInteractor {


    private final HistoryDBManager dbHelper;
    private ResponseCallback<Boolean> mCallback;
    private CityModel mModel;

    public SaveCityInteractorImpl(HistoryDBManager historyDBManager) {
            dbHelper = historyDBManager;
        }

    @Override
    public void build(CityModel model, ResponseCallback<Boolean> callback) {
        mModel = model;
        mCallback = callback;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                dbHelper.saveSearch(mModel,mCallback);
            }
        });
    }
}
