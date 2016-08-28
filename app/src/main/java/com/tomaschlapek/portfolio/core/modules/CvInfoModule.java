package com.tomaschlapek.portfolio.core.modules;

import com.tomaschlapek.portfolio.core.scopes.PerActivity;
import com.tomaschlapek.portfolio.presentation.ui.fragments.CvInfoFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 4/7/16.
 */

/**
 * This module will be created newly for every call (it has own defined scope)
 */
@Module
public class CvInfoModule {

  CvInfoFragment mCvInfoFragment;

  /* Contact Info Fragment ********************************************************************/

  public CvInfoModule(CvInfoFragment fragment) {
    mCvInfoFragment = fragment;
  }

  @Provides
  @PerActivity
  public CvInfoFragment provideCvInfoFragment() {
    return mCvInfoFragment;
  }
}
