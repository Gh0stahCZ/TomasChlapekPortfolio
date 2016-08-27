package com.tomaschlapek.portfolio.factory.portfolio;

import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioListPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.impl.PortfolioListPresenterImpl;
import com.tomaschlapek.portfolio.threading.MainThreadImpl;

/**
 * Created by tomaschlapek on 14/8/16.
 */
public class PortfolioListPresenterFactory implements PresenterFactory<PortfolioListPresenter> {

  PortfolioRepository mRepository;

  public PortfolioListPresenterFactory(PortfolioRepository repository) {
    mRepository = repository;
  }

  @Override
  public PortfolioListPresenter create() {
    return new PortfolioListPresenterImpl(
      ThreadExecutor.getInstance(),
      MainThreadImpl.getInstance(),

      mRepository);
  }
}
