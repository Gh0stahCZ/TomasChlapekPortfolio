package com.tomaschlapek.portfolio.domain.interactors.impl;

import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.domain.interactors.ShowPortfolioInteractor;
import com.tomaschlapek.portfolio.domain.interactors.base.AbstractInteractor;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.network.model.Portfolio;

import rx.Observable;
import rx.Observable.Transformer;
import rx.Subscriber;
import timber.log.Timber;

/**
 * This is an interactor portfolio with a reference to a model repository.
 * <p/>
 */
public class ShowPortfolioInteractorImpl extends AbstractInteractor implements
  ShowPortfolioInteractor {

  private PortfolioRepository mRepository;
  private Subscriber mSubscriber;

  public ShowPortfolioInteractorImpl(ThreadExecutor threadExecutor,
    MainThread mainThread, PortfolioRepository repository, Subscriber subscriber) {
    super(threadExecutor, mainThread);
    mRepository = repository;
    mThreadExecutor = threadExecutor;
    mSubscriber = subscriber;
  }

  @Override
  public void run() {
    Timber.d(Thread.currentThread().getName());

    Observable<Portfolio> portfolioObservable = mRepository.getPortfolio();

    portfolioObservable
      //      .compose(this.<Portfolio>applySchedulers())
      .compose(applySchedulers())
      .subscribe(mSubscriber);

  }


  /**
   * Apply schedulers for setting observable on working thread from Thread pool and setting
   * subscriber on MainUiThread
   *
   * @param <T>
   *
   * @return Transformer with set observable.
   */
  private <T> Observable.Transformer<T, T> applySchedulers() {
    return (Transformer<T, T>) mSchedulersTransformer;
  }
}
