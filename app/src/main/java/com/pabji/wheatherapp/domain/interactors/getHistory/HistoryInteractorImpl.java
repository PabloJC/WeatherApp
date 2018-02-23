package com.pabji.wheatherapp.domain.interactors.getHistory;

import com.pabji.wheatherapp.data.bbdd.History.HistoryDBManager;
import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.domain.base.BaseInteractor;

import java.util.List;


public class HistoryInteractorImpl extends BaseInteractor implements HistoryInteractor {


    private final HistoryDBManager dbHelper;
    private ResponseCallback<List<CityModel>> mCallback;

    public HistoryInteractorImpl(HistoryDBManager historyDBManager) {
        dbHelper = historyDBManager;
    }

    @Override
    public void build(ResponseCallback<List<CityModel>> callback) {
        mCallback = callback;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                dbHelper.getHistory(mCallback);
            }
        });
    }
}
