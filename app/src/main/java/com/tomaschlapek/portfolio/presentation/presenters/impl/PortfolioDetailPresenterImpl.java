package com.tomaschlapek.portfolio.presentation.presenters.impl;

import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioDetailPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.base.AbstractPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class PortfolioDetailPresenterImpl extends AbstractPresenter
  implements PortfolioDetailPresenter {

  private Vista mView;
  private PortfolioRepository mPortfolioRepo;

  public PortfolioDetailPresenterImpl(ThreadExecutor executor,
    MainThread mainThread,
    PortfolioRepository repository) {
    super(executor, mainThread);
    mPortfolioRepo = repository;
  }

   @Override
  public void onViewAttached(PortfolioDetailPresenter.Vista view) {
    mView = view;
  }

//  @Override
//  public void onViewAttached(PortfolioListPresenter.Vista view) {
//
//  }

  @Override
  public void onViewDetached() {
    mView = null;
  }

  @Override
  public void onDestroyed() {

  }


  private void requestPortfolioDetailData() {
//    mView.showProgress();
//    ShowPortfolioInteractor showAllProjectsInteractor =
//      new ShowPortfolioInteractorImpl(mExecutor, mMainThread,
//        mPortfolioRepo, new PortfolioSubscriber());
//    showAllProjectsInteractor.execute();
  }

/*  private final class PortfolioSubscriber extends DefaultSubscriber<Portfolio> {
    @Override
    public void onCompleted() {
      super.onCompleted();
      mView.hideProgress();
    }

    @Override
    public void onError(Throwable e) {
      super.onError(e);
      mView
        .showError(R.string.error_unexpect, R.drawable.ic_sentiment_very_dissatisfied_black_48dp);
      mView.hideProgress();
    }

    @Override
    public void onNext(Portfolio portfolio) {
      super.onNext(portfolio);
      mView.showPortfolios(portfolio);
    }
  }*/
}
