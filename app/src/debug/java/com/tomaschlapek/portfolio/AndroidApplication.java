package com.tomaschlapek.portfolio;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.tomaschlapek.portfolio.core.components.AppComponent;
import com.tomaschlapek.portfolio.core.components.DaggerAppComponent;
import com.tomaschlapek.portfolio.core.modules.AppModule;
import com.tomaschlapek.portfolio.core.modules.NetModule;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class AndroidApplication extends MultiDexApplication {

   /* Private Static Attributes *******************************************************************/

    private static AppComponent appComponent;

    /**
     * Indicates that the application is initializes.
     */
    private static boolean sIsInitialized;

    /**
     * Indicates that the application went to background.
     */
    private static boolean sIsInBackground;

    /**
     * Indicates that the application is in dual-pane mode.
     */
    private static boolean sIsDualPane;

    /**
     * Indicates that the application displays two columns in portrait.
     */
    private static boolean sHasTwoColumnsInPortrait;

    /* Public Static Methods **********************************************************************/

    /**
     * Indicates that the application is initializes.
     */
    public static boolean isInitialized() {
        return sIsInitialized;
    }

    /**
     * Marks the application as initialized.
     */
    public static void setInitialized(boolean isInitialized) {
        sIsInitialized = isInitialized;
    }

    /**
     * Indicates that the application went to background.
     */
    public static boolean isInBackground() {
        return sIsInBackground;
    }

    /**
     * Marks if the application went to background.
     */
    public static void setInBackground(boolean isInBackground) {
        sIsInBackground = isInBackground;
    }

    /**
     * Indicates that the application is in dual-pane mode.
     */
    public static boolean isDualPane() {
        return sIsDualPane;
    }

    /**
     * Indicates that the application displays two columns in portrait.
     */
    public static boolean hasTwoColumnsInPortrait() {
        return sHasTwoColumnsInPortrait;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    /* Public Methods *****************************************************************************/

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

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
          .netModule(new NetModule()).build();

        //        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
        //          .storageModule(new CustomModule(this)).build();

        init();
    }

    public void init() {
        sIsInitialized = false;
        sIsInBackground = true;
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
}
