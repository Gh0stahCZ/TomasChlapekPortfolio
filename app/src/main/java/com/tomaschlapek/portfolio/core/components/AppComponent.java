package com.tomaschlapek.portfolio.core.components;

import android.app.Application;
import android.content.Context;

import com.tomaschlapek.portfolio.core.executor.PostExecutionThread;
import com.tomaschlapek.portfolio.core.modules.AppModule;
import com.tomaschlapek.portfolio.core.modules.CustomModule;
import com.tomaschlapek.portfolio.core.modules.MainActivityModule;
import com.tomaschlapek.portfolio.core.modules.NetModule;
import com.tomaschlapek.portfolio.core.modules.PortfolioRepositoryModule;
import com.tomaschlapek.portfolio.navigation.Navigator;
import com.tomaschlapek.portfolio.presentation.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by tomaschlapek on 4/7/16.
 */

@Singleton
@Component(modules = { AppModule.class, NetModule.class, MainActivityModule.class })
public interface AppComponent {

  void inject(MainActivity activity);

  Application provideApplication();

  Context provideContext();

  Navigator provideNavigator();

  CustomComponent plusCustom(CustomModule module);

  PortfolioRepositoryComponent plusPortfolioRepositoryComponent(PortfolioRepositoryModule module);

  Retrofit provideRetrofit();

  OkHttpClient provideApi();

//  MainActivity provideMainActivity();

//  ThreadExecutor provideThreadExecutor();

  PostExecutionThread providePostExecutionThread();

}
