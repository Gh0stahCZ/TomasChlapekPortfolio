package com.tomaschlapek.portfolio;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.tomaschlapek.portfolio.components.AppComponent;
import com.tomaschlapek.portfolio.components.DaggerAppComponent;
import com.tomaschlapek.portfolio.modules.AppModule;
import com.tomaschlapek.portfolio.modules.StorageModule;

import okhttp3.OkHttpClient;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class AndroidApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                return super.createStackElementTag(element) + ":" + element.getLineNumber();
            }
        });

        LeakCanary.install(this);

        initStetho();

        Timber.d("Stetho is ready.");


        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
          .storageModule(new StorageModule(this)).build();
    }

    public void initStetho() {
        Stetho.InitializerBuilder initializerBuilder =
          Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
          Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
          Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
