package com.tomaschlapek.portfolio.core.modules;

import com.tomaschlapek.portfolio.presentation.ui.activities.MainActivity;

import dagger.Module;

/**
 * Created by tomaschlapek on 21/7/16.
 */
@Module
public final class MainActivityModule {

  private final MainActivity mMainActivity;

  public MainActivityModule(MainActivity mainActivity) {
    mMainActivity = mainActivity;
  }

//  @Provides
//  @Singleton
//  DrawerLayout provideDrawerLayout() {
////    return mMainActivity.drawerLayout;
//  }
}
