package com.pabji.wheatherapp.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.presentation.ui.constants.IntentConstants;
import com.pabji.wheatherapp.presentation.ui.detail.DetailActivity;

public class Router {

    private final Context mContext;

    public Router(Context context){
        mContext = context;
    }

    public void goToDetail(CityModel model){
        if (mContext != null) {
            Intent intent = DetailActivity.getCallingIntent(mContext);
            intent.putExtra(IntentConstants.CITY, model);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
