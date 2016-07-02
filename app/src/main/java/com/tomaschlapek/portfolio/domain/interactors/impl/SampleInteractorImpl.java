package com.tomaschlapek.portfolio.domain.interactors.impl;

import com.tomaschlapek.portfolio.domain.executor.Executor;
import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.interactors.SampleInteractor;
import com.tomaschlapek.portfolio.domain.interactors.base.AbstractInteractor;
import com.tomaschlapek.portfolio.domain.repository.Repository;

/**
 * This is an interactor portfolio with a reference to a model repository.
 * <p/>
 */
public class SampleInteractorImpl extends AbstractInteractor implements SampleInteractor {

    private SampleInteractor.Callback mCallback;
    private Repository                mRepository;

    public SampleInteractorImpl(Executor threadExecutor,
                                MainThread mainThread,
                                Callback callback, Repository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {
        // TODO: Implement this with your business logic
    }
}
