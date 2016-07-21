package com.tomaschlapek.portfolio;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.tomaschlapek.portfolio.core.components.AppComponent;
import com.tomaschlapek.portfolio.core.components.DaggerAppComponent;
import com.tomaschlapek.portfolio.core.modules.AppModule;
import com.tomaschlapek.portfolio.core.modules.NetModule;
import com.tomaschlapek.portfolio.utils.ReleaseTree;

import io.fabric.sdk.android.Fabric;

import timber.log.Timber;

public class AndroidApplication extends MultiDexApplication {

  private AppComponent appComponent;

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(newBase);
    MultiDex.install(this);
  }

  @Override
  public void onCreate() {
    super.onCreate();

    String baseUrl = getString(R.string.base_url_release);

    Timber.plant(new ReleaseTree());
    Fabric.with(this, new Crashlytics());

    LeakCanary.install(this);

    appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).netModule(new NetModule()).build();  }

  public AppComponent getAppComponent() {
    return this.appComponent;
  }
}
