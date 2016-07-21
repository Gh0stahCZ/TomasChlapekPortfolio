package com.tomaschlapek.portfolio.presentation.presenters.impl;

import com.tomaschlapek.portfolio.domain.executor.Executor;
import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.interactors.ShowAllProjectsInteractor;
import com.tomaschlapek.portfolio.domain.interactors.impl.ShowAllProjectsInteractorImpl;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.base.AbstractPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
  ShowAllProjectsInteractor.Callback {

  private MainPresenter.View mView;
  private PortfolioRepository mPortfolioRepo;

  public MainPresenterImpl(Executor executor,
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

    ShowAllProjectsInteractor showAllProjectsInteractor =
      new ShowAllProjectsInteractorImpl(mExecutor, mMainThread,
        mPortfolioRepo, this);
    showAllProjectsInteractor.execute();
  }

  @Override
  public void showPortfolios(Portfolio portfolio) {
    mView.showPortfolios(portfolio);
  }
}
