package com.pabji.wheatherapp.domain.executor;


public interface MainThread {

    void post(final Runnable runnable);
}
