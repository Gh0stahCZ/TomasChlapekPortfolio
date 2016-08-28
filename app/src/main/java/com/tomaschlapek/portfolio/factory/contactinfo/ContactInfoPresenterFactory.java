package com.tomaschlapek.portfolio.factory.contactinfo;

import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.ContactInfoPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.impl.ContactInfoPresenterImpl;
import com.tomaschlapek.portfolio.threading.MainThreadImpl;

/**
 * Created by tomaschlapek on 14/8/16.
 */
public class ContactInfoPresenterFactory implements PresenterFactory<ContactInfoPresenter> {

//  ContactInfoRepository mRepository;

  public ContactInfoPresenterFactory(/*ContactInfoRepository repository*/) {
//    mRepository = repository;
  }

  @Override
  public ContactInfoPresenter create() {
    return new ContactInfoPresenterImpl(
      ThreadExecutor.getInstance(),
      MainThreadImpl.getInstance()/*,

      mRepository*/);
  }
}
