package com.tomaschlapek.portfolio.factory.portfolio;

import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioDetailPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.impl.PortfolioDetailPresenterImpl;
import com.tomaschlapek.portfolio.threading.MainThreadImpl;

/**
 * Created by tomaschlapek on 14/8/16.
 */
public class PortfolioDetailPresenterFactory implements PresenterFactory<PortfolioDetailPresenter> {

  PortfolioRepository mRepository;

  public PortfolioDetailPresenterFactory(PortfolioRepository repository) {
    mRepository = repository;
  }

  @Override
  public PortfolioDetailPresenter create() {
    return new PortfolioDetailPresenterImpl(
      ThreadExecutor.getInstance(),
      MainThreadImpl.getInstance(),

      mRepository);
  }
}
