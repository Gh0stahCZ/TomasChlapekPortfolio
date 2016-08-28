package com.tomaschlapek.portfolio.core.modules;

import com.tomaschlapek.portfolio.core.scopes.PerActivity;
import com.tomaschlapek.portfolio.presentation.ui.fragments.ContactInfoFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 4/7/16.
 */

/**
 * This module will be created newly for every call (it has own defined scope)
 */
@Module
public class ContactInfoModule {

  ContactInfoFragment mContactInfoFragment;

  /* Contact Info Fragment ********************************************************************/

  public ContactInfoModule(ContactInfoFragment fragment) {
    mContactInfoFragment = fragment;
  }

  @Provides
  @PerActivity
  public ContactInfoFragment provideContactInfoFragment() {
    return mContactInfoFragment;
  }
}
