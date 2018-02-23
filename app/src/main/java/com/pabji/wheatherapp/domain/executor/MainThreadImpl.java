package com.pabji.wheatherapp.domain.executor;

import android.os.Handler;
import android.os.Looper;


public class MainThreadImpl implements MainThread {

    private static MainThread instance;

    private final Handler mHandler;

    public MainThreadImpl(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static synchronized MainThread getInstance(){
        if(instance == null){
            instance = new MainThreadImpl();
        }
        return instance;
    }
}
