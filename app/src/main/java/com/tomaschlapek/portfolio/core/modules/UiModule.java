package com.tomaschlapek.portfolio.core.modules;

import com.tomaschlapek.portfolio.presentation.ui.ViewContainer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 21/7/16.
 */
@Module
public final class UiModule {
  @Provides
  @Singleton
  ViewContainer provideViewContainer() {
    return ViewContainer.DEFAULT;
  }
}
