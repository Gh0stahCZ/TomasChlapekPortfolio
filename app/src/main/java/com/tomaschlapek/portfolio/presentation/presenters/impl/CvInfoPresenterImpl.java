package com.tomaschlapek.portfolio.presentation.presenters.impl;

import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.presentation.presenters.CvInfoPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.base.AbstractPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class CvInfoPresenterImpl extends AbstractPresenter
  implements CvInfoPresenter {

  private Vista mView;

  public CvInfoPresenterImpl(ThreadExecutor executor,
    MainThread mainThread) {
    super(executor, mainThread);
  }

  @Override
  public void onViewAttached(Vista view) {
    mView = view;
  }

  @Override
  public void onViewDetached() {
    mView = null;
  }

  @Override
  public void onDestroyed() {

  }
}