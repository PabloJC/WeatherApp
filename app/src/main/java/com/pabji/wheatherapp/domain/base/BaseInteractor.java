package com.pabji.wheatherapp.domain.base;

import com.pabji.wheatherapp.domain.executor.Executor;
import com.pabji.wheatherapp.domain.executor.MainThread;
import com.pabji.wheatherapp.domain.executor.MainThreadImpl;
import com.pabji.wheatherapp.domain.executor.ThreadExecutor;


public abstract class BaseInteractor implements Interactor {

        protected Executor mThreadExecutor;
        protected MainThread mMainThread;

        protected volatile boolean mIsCanceled;
        protected volatile boolean mIsRunning;

        public BaseInteractor() {
            mThreadExecutor = ThreadExecutor.getInstance();
            mMainThread = MainThreadImpl.getInstance();
        }

        public abstract void run();

        public void cancel() {
            mIsCanceled = true;
            mIsRunning = false;
        }

        public boolean isRunning() {
            return mIsRunning;
        }

        public void onFinished() {
            mIsRunning = false;
            mIsCanceled = false;
        }



        public void execute() {
            this.mIsRunning = true;
            mThreadExecutor.execute(this);
        }

}
