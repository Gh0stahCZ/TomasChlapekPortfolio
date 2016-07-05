package com.tomaschlapek.portfolio;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.tomaschlapek.portfolio.components.AppComponent;
import com.tomaschlapek.portfolio.components.DaggerAppComponent;
import com.tomaschlapek.portfolio.modules.AppModule;
import com.tomaschlapek.portfolio.modules.StorageModule;
import com.tomaschlapek.portfolio.utils.ReleaseTree;

import io.fabric.sdk.android.Fabric;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class AndroidApplication extends Application {

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    Timber.plant(new ReleaseTree());
    Fabric.with(this, new Crashlytics());

    LeakCanary.install(this);

    appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
      .storageModule(new StorageModule(this)).build();
  }

  public AppComponent getAppComponent() {
    return this.appComponent;
  }
}
