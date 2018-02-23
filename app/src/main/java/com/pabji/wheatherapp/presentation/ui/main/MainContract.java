package com.pabji.wheatherapp.presentation.ui.main;

import com.pabji.wheatherapp.presentation.base.BasePresenter;
import com.pabji.wheatherapp.presentation.base.BaseView;
import com.pabji.wheatherapp.data.models.CityModel;

import java.util.List;


public interface MainContract {

    interface View extends BaseView{

        void showSearchList(List<CityModel> result);
        void showHistory(List<CityModel> result);
        void showEmptySearch(String message);
        void showEmptyHistory(String message);
    }

    interface Presenter extends BasePresenter<View>{

        void searchCity(String cityName);
        void loadHistory();
        void goToDetail(CityModel model);
    }
}
