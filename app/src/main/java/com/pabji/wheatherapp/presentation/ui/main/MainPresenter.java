package com.pabji.wheatherapp.presentation.ui.main;

import android.content.Context;

import com.pabji.wheatherapp.R;
import com.pabji.wheatherapp.data.constants.ResponseCodes;
import com.pabji.wheatherapp.domain.interactors.searchCities.SearchCitiesInteractor;
import com.pabji.wheatherapp.domain.interactors.searchCities.SearchCitiesInteractorImpl;
import com.pabji.wheatherapp.presentation.base.BasePresenterImpl;
import com.pabji.wheatherapp.data.bbdd.History.HistoryDBManager;
import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.net.requestsModels.SearchRequest;
import com.pabji.wheatherapp.domain.interactors.getHistory.HistoryInteractor;
import com.pabji.wheatherapp.domain.interactors.getHistory.HistoryInteractorImpl;
import com.pabji.wheatherapp.domain.interactors.saveCity.SaveCityInteractorImpl;
import com.pabji.wheatherapp.presentation.navigation.Router;

import java.util.List;


public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{

    private final Router mRouter;
    private final Context context;
    private SaveCityInteractorImpl mSaveSearchInteractor;
    private HistoryInteractor mHistoryInteractor;
    private SearchCitiesInteractor mSearchCitiesInteractor;

    public MainPresenter(MainContract.View view) {
        context = (MainActivity)view;
        attachView(view);

        HistoryDBManager historyDBManager = HistoryDBManager.getInstance(context);

        mSearchCitiesInteractor = new SearchCitiesInteractorImpl();
        mHistoryInteractor = new HistoryInteractorImpl(historyDBManager);
        mSaveSearchInteractor = new SaveCityInteractorImpl(historyDBManager);

        mRouter = new Router(context);
    }


    @Override
    public void searchCity(String cityName) {
        mSearchCitiesInteractor.build(new SearchRequest(cityName), new ResponseCallback<List<CityModel>>() {
            @Override
            public void success(List<CityModel> result) {
                mView.showSearchList(result);
            }

            @Override
            public void error(int error) {
                if(error == ResponseCodes.CODE_EMPTY) {
                    mView.showEmptySearch(context.getString(R.string.empty_search));
                }else if(error == ResponseCodes.CODE_NETWORK) {
                    mView.showError(context.getString(R.string.network_error));
                }else {
                    mView.showError(context.getString(R.string.default_error));
                }
            }
        });
        mSearchCitiesInteractor.execute();
    }

    @Override
    public void loadHistory() {
        mHistoryInteractor.build(new ResponseCallback<List<CityModel>>() {
            @Override
            public void success(List<CityModel> result) {
                mView.showHistory(result);
            }

            @Override
            public void error(int error) {
                if(error == ResponseCodes.CODE_EMPTY){
                    mView.showEmptyHistory(context.getString(R.string.empty_history));
                }
            }
        });
        mHistoryInteractor.execute();

    }

    @Override
    public void goToDetail(CityModel model) {
        saveCityInHistory(model);
        mRouter.goToDetail(model);
    }

    private void saveCityInHistory(CityModel model) {
        mSaveSearchInteractor.build(model, new ResponseCallback<Boolean>() {
            @Override
            public void success(Boolean result) {

            }

            @Override
            public void error(int error) {

            }
        });
        mSaveSearchInteractor.execute();
    }
}
