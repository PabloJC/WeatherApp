package com.pabji.wheatherapp.data.repositories.weather;

import android.support.annotation.NonNull;
import android.util.Log;

import com.pabji.wheatherapp.data.constants.LogConstants;
import com.pabji.wheatherapp.data.constants.ResponseCodes;
import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.data.net.entities.WeatherObservationEntity;
import com.pabji.wheatherapp.data.net.requestsModels.CityDataRequest;
import com.pabji.wheatherapp.data.net.retrofit.ApiClient;
import com.pabji.wheatherapp.data.net.retrofit.ApiInterface;
import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.net.entities.SearchEntity;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.net.requestsModels.SearchRequest;
import com.pabji.wheatherapp.data.parsers.ModelParsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherRepositoryImpl implements WeatherRepository {

    private static final String TAG = WeatherRepositoryImpl.class.getName();

    private static WeatherRepository instance;
    private final ApiInterface mApiInterface;

    public WeatherRepositoryImpl(){
        this.mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public void searchCity(final SearchRequest request, final ResponseCallback<List<CityModel>> callback) {

        doSearch(request, new ResponseCallback<List<CityModel>>() {
            @Override
            public void success(List<CityModel> result) {
                callback.success(result);
            }

            @Override
            public void error(int error) {
                request.setUsername(SearchRequest.DEFAULT_USERNAMES[1]);
                doSearch(request,callback);
            }
        });
    }

    @Override
    public void getCityData(final CityDataRequest request, final ResponseCallback<WeatherObservation> callback) {
        doRequest(request, new ResponseCallback<WeatherObservation>() {
            @Override
            public void success(WeatherObservation result) {
                callback.success(result);
            }

            @Override
            public void error(int error) {
                request.setUsername(SearchRequest.DEFAULT_USERNAMES[1]);
                doRequest(request,callback);
            }
        });
    }

    private void doSearch(final SearchRequest request, final ResponseCallback<List<CityModel>> callback){

        if(request != null){
            Call<SearchEntity> call = this.mApiInterface.searchCity(request.toQueryMap());

            call.enqueue(new Callback<SearchEntity>() {
                @Override
                public void onResponse(Call<SearchEntity> call, @NonNull Response<SearchEntity> response) {
                    if(response.isSuccessful()){
                        SearchEntity entity = response.body();
                        if(entity != null && entity.geonames != null){
                            List<CityModel> listModel = ModelParsers.toListModel(entity.geonames, SearchRequest.LANGUAGE);

                            if (listModel.isEmpty()){
                                callback.error(ResponseCodes.CODE_EMPTY);
                                Log.d(TAG, String.format(LogConstants.ERROR_EMPTY_LANGUAGE,SearchRequest.LANGUAGE));
                            }else{
                                callback.success(listModel);
                                Log.d(TAG, entity.toString());
                            }
                        }else{
                            callback.error(ResponseCodes.CODE_EMPTY);
                            Log.d(TAG, LogConstants.ERROR_EMPTY_SEARCH);
                        }
                    }else{
                        callback.error(response.code());
                        Log.d(TAG, response.message());
                    }
                }

                @Override
                public void onFailure(Call<SearchEntity> call, Throwable t) {
                    callback.error(ResponseCodes.CODE_NETWORK);
                    Log.d(TAG, LogConstants.ERROR_NETWORK,t);
                }
            });
        }

    }

    private void doRequest(final CityDataRequest request, final ResponseCallback<WeatherObservation> callback){

        if(request != null){
            Call<WeatherObservationEntity> call = this.mApiInterface.getWeather(request.toQueryMap());

            call.enqueue(new Callback<WeatherObservationEntity>() {
                @Override
                public void onResponse(Call<WeatherObservationEntity> call, @NonNull Response<WeatherObservationEntity> response) {

                    if(response.isSuccessful()){
                        WeatherObservationEntity entity = response.body();
                        if(entity != null && entity.weatherObservations != null && !entity.weatherObservations.isEmpty()){
                            WeatherObservation model = ModelParsers.toModel(entity.weatherObservations.get(0));
                            callback.success(model);
                            Log.d(TAG, entity.toString());
                        }else{
                            callback.error(ResponseCodes.CODE_EMPTY);
                            Log.d(TAG, LogConstants.ERROR_EMPTY_RESPONSE);
                        }
                    }else{
                        callback.error(response.code());
                        Log.d(TAG, response.message());
                    }
                }

                @Override
                public void onFailure(Call<WeatherObservationEntity> call, Throwable t) {
                    callback.error(ResponseCodes.CODE_NETWORK);
                    Log.d(TAG, LogConstants.ERROR_NETWORK,t);
                }
            });
        }
    }

    public static synchronized WeatherRepository getInstance(){
        if(instance == null){
            instance = new WeatherRepositoryImpl();
        }
        return instance;
    }


}
