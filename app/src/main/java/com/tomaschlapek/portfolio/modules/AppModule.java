package com.tomaschlapek.portfolio.modules;

import android.app.Application;

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
  Application providesContext() {
    return this.mApplication;
  }
}

