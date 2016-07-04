package com.tomaschlapek.portfolio.components;

import android.app.Application;
import android.content.Context;

import com.tomaschlapek.portfolio.data.SharedPreferencesManager;
import com.tomaschlapek.portfolio.modules.AppModule;
import com.tomaschlapek.portfolio.modules.StorageModule;
import com.tomaschlapek.portfolio.presentation.ui.activities.BaseActivity;
import com.tomaschlapek.portfolio.presentation.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tomaschlapek on 4/7/16.
 */

@Singleton
@Component(modules = { AppModule.class, StorageModule.class })
public interface AppComponent {

  void inject(BaseActivity activity);

  Application providesApplication();
  Context providesContext();
  SharedPreferencesManager provideSharedPreferenceManager();
}
