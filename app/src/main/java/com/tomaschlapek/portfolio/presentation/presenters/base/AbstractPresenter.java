package com.tomaschlapek.portfolio.presentation.presenters.base;

import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;

/**
 * This is a base class for all presenters which are communicating with interactors. This base class will hold a
 * reference to the Executor and MainThread objects that are needed for running interactors in a background thread.
 */
public abstract class AbstractPresenter {
    protected ThreadExecutor mExecutor;
    protected MainThread mMainThread;

//    protected ThreadExecutor threadExecutor;
//    protected PostExecutionThread postExecutionThread;

    public AbstractPresenter(ThreadExecutor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }

//    public AbstractPresenter(
//      PostExecutionThread postExecutionThread,
//      ThreadExecutor threadExecutor) {
//        this.postExecutionThread = postExecutionThread;
//        this.threadExecutor = threadExecutor;
//    }
}
