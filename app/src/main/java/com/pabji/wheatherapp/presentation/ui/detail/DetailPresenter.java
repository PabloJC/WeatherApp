package com.pabji.wheatherapp.presentation.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.pabji.wheatherapp.R;
import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.constants.ResponseCodes;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.data.net.requestsModels.CityDataRequest;
import com.pabji.wheatherapp.domain.interactors.getCityData.CityDataInteractor;
import com.pabji.wheatherapp.domain.interactors.getCityData.CityDataInteractorImpl;
import com.pabji.wheatherapp.presentation.base.BasePresenterImpl;
import com.pabji.wheatherapp.presentation.ui.constants.IntentConstants;
import com.pabji.wheatherapp.presentation.ui.main.MainActivity;


public class DetailPresenter extends BasePresenterImpl<DetailContract.View> implements DetailContract.Presenter{

    private Context context;
    private CityModel city;
    private CityDataInteractor mDataInteractor;

    public DetailPresenter(DetailContract.View view) {
        context = (DetailActivity)view;
        attachView(view);

        mDataInteractor = new CityDataInteractorImpl();
    }

    @Override
    public void getCityData(CityModel city) {

        mDataInteractor.build(new CityDataRequest(city), new ResponseCallback<WeatherObservation>() {
            @Override
            public void success(WeatherObservation result) {
                mView.showData(result);
            }

            @Override
            public void error(int error) {
                if(error == ResponseCodes.CODE_EMPTY){
                    mView.showNoData();
                }else if(error == ResponseCodes.CODE_NETWORK) {
                    mView.showError(context.getString(R.string.network_error));
                }else {
                    mView.showError(context.getString(R.string.default_error));
                }
            }
        });
        mDataInteractor.execute();
    }
}
