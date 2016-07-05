package com.tomaschlapek.portfolio.modules;

import android.app.Application;
import android.content.Context;

import com.tomaschlapek.portfolio.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 4/7/16.
 */
@Module
public class AppModule {

  private Application mApplication;

  public AppModule(Application application) {
    this.mApplication = application;
  }

  @Provides
  @Singleton
  Application providesApplication() {
    return this.mApplication;
  }

  @Provides
  @Singleton
  Context provideContext() {
    return this.mApplication;
  }

  @Provides
  @Singleton
  Navigator provideNavigator() {
    return new Navigator();
  }
}

