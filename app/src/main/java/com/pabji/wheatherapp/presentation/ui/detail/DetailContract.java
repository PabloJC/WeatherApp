package com.pabji.wheatherapp.presentation.ui.detail;

import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.presentation.base.BasePresenter;
import com.pabji.wheatherapp.presentation.base.BaseView;

import java.util.List;


public interface DetailContract {

    interface View extends BaseView{
        void showNoData();
        void showData(WeatherObservation result);
    }

    interface Presenter extends BasePresenter<View>{
        void getCityData(CityModel cityModel);
    }
}
