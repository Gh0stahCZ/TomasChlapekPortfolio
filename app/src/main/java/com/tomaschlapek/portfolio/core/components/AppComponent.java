package com.tomaschlapek.portfolio.core.components;

import android.app.Application;
import android.content.Context;

import com.tomaschlapek.portfolio.core.executor.PostExecutionThread;
import com.tomaschlapek.portfolio.core.modules.AppModule;
import com.tomaschlapek.portfolio.core.modules.ContactInfoModule;
import com.tomaschlapek.portfolio.core.modules.CustomModule;
import com.tomaschlapek.portfolio.core.modules.CvInfoModule;
import com.tomaschlapek.portfolio.core.modules.MainActivityModule;
import com.tomaschlapek.portfolio.core.modules.NetModule;
import com.tomaschlapek.portfolio.core.modules.PortfolioModule;
import com.tomaschlapek.portfolio.navigation.Navigator;
import com.tomaschlapek.portfolio.presentation.ui.activities.DrawerActivity;
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

  void inject(DrawerActivity activity);

  Application provideApplication();

  Context provideContext();

  Navigator provideNavigator();

  Navigator provideToolbar();

  CustomComponent plusCustom(CustomModule module);

  PortfolioComponent plusPortfolioComponent(PortfolioModule module);

  CvInfoComponent plusCvInfoComponent(CvInfoModule module);

  ContactInfoComponent plusContactInfoComponent(ContactInfoModule module);

  Retrofit provideRetrofit();

  OkHttpClient provideApi();

//  MainActivity provideMainActivity();

//  ThreadExecutor provideThreadExecutor();

  PostExecutionThread providePostExecutionThread();

}
