package com.tomaschlapek.portfolio.factory;

import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.impl.MainPresenterImpl;
import com.tomaschlapek.portfolio.threading.MainThreadImpl;

/**
 * Created by tomaschlapek on 13/8/16.
 */
public class MainPresenterFactory implements PresenterFactory<MainPresenter> {

  @Override
  public MainPresenter create() {
    return new MainPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance());
  }
}
