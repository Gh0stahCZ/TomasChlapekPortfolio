package com.tomaschlapek.portfolio.presentation.presenters.impl;

import com.tomaschlapek.portfolio.domain.executor.MainThread;
import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.presentation.presenters.ContactInfoPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.base.AbstractPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ContactInfoPresenterImpl extends AbstractPresenter
  implements ContactInfoPresenter {

  private Vista mView;
//  private ContactInfoRepository mContactInfoRepository;

  public ContactInfoPresenterImpl(ThreadExecutor executor,
    MainThread mainThread/*,
    ContactInfoRepository repository*/) {
    super(executor, mainThread);
//    mContactInfoRepository = repository;
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
