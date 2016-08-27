package com.tomaschlapek.portfolio.factory;

import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;

/**
 * Created by tomaschlapek on 13/8/16.
 */
public interface PresenterFactory<T extends BasePresenter> {
  T create();
}
