package com.pabji.wheatherapp.domain.executor;

import com.pabji.wheatherapp.domain.base.BaseInteractor;
import com.pabji.wheatherapp.domain.base.Interactor;


public interface Executor {

    void execute(final BaseInteractor interactor);
}
