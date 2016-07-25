package com.tomaschlapek.portfolio.presentation.presenters.impl;

import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.domain.interactors.ShowPortfolioInteractor;
import com.tomaschlapek.portfolio.domain.interactors.impl.ShowPortfolioInteractorImpl;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.interactor.DefaultSubscriber;
import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.base.AbstractPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
  ShowPortfolioInteractor.Callback {

  private MainPresenter.View mView;
  private PortfolioRepository mPortfolioRepo;

  public MainPresenterImpl(ThreadExecutor executor,
    MainThread mainThread,
    View view, PortfolioRepository repository) {
    super(executor, mainThread);
    mView = view;
    mPortfolioRepo = repository;
  }

  @Override
  public void resume() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void stop() {

  }

  @Override
  public void destroy() {

  }

  @Override
  public void onError(String message) {

  }

  @Override
  public void retrofitClick() {

    //    Snackbar.make(scroll, "PortfoliosLoaded", Snackbar.LENGTH_SHORT).show();

    //    ShowPortfolioInteractor showAllProjectsInteractor =
    //      new ShowPortfolioInteractorImpl(mExecutor, mMainThread,
    //        mPortfolioRepo, this);

    ShowPortfolioInteractor showAllProjectsInteractor =
      new ShowPortfolioInteractorImpl(mExecutor, mMainThread,
        mPortfolioRepo, new PortfolioSubscriber());
    showAllProjectsInteractor.execute();
  }

  @Override
  public void showPortfolios(Portfolio portfolio) {
    mView.showPortfolios(portfolio);
  }

  private final class PortfolioSubscriber extends DefaultSubscriber<Portfolio> {
    @Override
    public void onCompleted() {
      super.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
      super.onError(e);
    }

    @Override
    public void onNext(Portfolio portfolio) {
      super.onNext(portfolio);
      mView.showPortfolios(portfolio);
    }
  }

}
