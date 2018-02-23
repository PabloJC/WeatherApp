package com.pabji.wheatherapp.presentation.base;

import android.support.v7.app.AppCompatActivity;


public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public AppCompatActivity getActivity() {
        return (AppCompatActivity) mView;
    }
}
