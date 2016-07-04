package com.tomaschlapek.portfolio.modules;

import android.app.Application;
import android.content.Context;

import com.tomaschlapek.portfolio.data.SharedPreferencesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 4/7/16.
 */

@Module
public class StorageModule {

  Context context;

  public StorageModule(Context context) {
    this.context = context;
  }

  @Provides
  @Singleton
  public SharedPreferencesManager provideSharedPreferencesManager(Application application) {
    return new SharedPreferencesManager(application);
  }
}
