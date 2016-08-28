package com.tomaschlapek.portfolio.factory.cvinfo;

import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.CvInfoPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.impl.CvInfoPresenterImpl;
import com.tomaschlapek.portfolio.threading.MainThreadImpl;

/**
 * Created by tomaschlapek on 14/8/16.
 */
public class CvInfoPresenterFactory implements PresenterFactory<CvInfoPresenter> {

  /*CvInfoRepository mRepository;*/

  public CvInfoPresenterFactory(/*CvInfoRepository repository*/) {
   /* mRepository = repository;*/
  }

  @Override
  public CvInfoPresenter create() {
    return new CvInfoPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance());
  }
}
