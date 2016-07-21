package com.tomaschlapek.portfolio.domain.interactors.impl;

import com.tomaschlapek.portfolio.domain.executor.Executor;
import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.interactors.ShowAllProjectsInteractor;
import com.tomaschlapek.portfolio.domain.interactors.base.AbstractInteractor;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.network.model.Portfolio;

/**
 * This is an interactor portfolio with a reference to a model repository.
 * <p/>
 */
public class ShowAllProjectsInteractorImpl extends AbstractInteractor implements
  ShowAllProjectsInteractor {

  private Callback mCallback;
  private PortfolioRepository mRepository;

  public ShowAllProjectsInteractorImpl(Executor threadExecutor,
    MainThread mainThread, PortfolioRepository repository, Callback callback) {
    super(threadExecutor, mainThread);
    mCallback = callback;
    mRepository = repository;
  }

  @Override
  public void run() {

    PortfolioCallback portfolioCallback = new PortfolioCallback() {
      @Override
      public void response(final Portfolio response) {

        mMainThread.post(new Runnable() {
          @Override
          public void run() {
            mCallback.showPortfolios(response);
          }
        });
      }
    };

    mRepository.getProject(portfolioCallback);
  }

  public interface PortfolioCallback {
    void response(Portfolio project);
  }
}
