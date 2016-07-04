package com.tomaschlapek.portfolio;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tomaschlapek.portfolio.components.AppComponent;
import com.tomaschlapek.portfolio.components.DaggerAppComponent;
import com.tomaschlapek.portfolio.modules.AppModule;
import com.tomaschlapek.portfolio.modules.StorageModule;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class AndroidApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // initiate Timber
        Timber.plant(new DebugTree());
        LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
          .storageModule(new StorageModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
