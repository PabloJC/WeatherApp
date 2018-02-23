package com.pabji.wheatherapp.data.callbacks;


public interface ResponseCallback<T> {

    void success(T result);
    void error(int codeError);
}
