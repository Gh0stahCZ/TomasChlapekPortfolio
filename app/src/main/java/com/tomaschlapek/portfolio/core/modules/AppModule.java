package com.tomaschlapek.portfolio.core.modules;

import android.app.Application;
import android.content.Context;

import com.tomaschlapek.portfolio.core.executor.PostExecutionThread;
import com.tomaschlapek.portfolio.core.executor.UIThread;
import com.tomaschlapek.portfolio.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 4/7/16.
 */
@Module(
  includes = {
    UiModule.class
  }
)
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

//  @Provides
//  @Singleton
//  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
//    return jobExecutor;
//  }

  @Provides
  @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }
}

