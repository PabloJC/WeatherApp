package com.pabji.wheatherapp.presentation.base;

import android.support.v7.app.AppCompatActivity;


public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void detachView();

    AppCompatActivity getActivity();
}
