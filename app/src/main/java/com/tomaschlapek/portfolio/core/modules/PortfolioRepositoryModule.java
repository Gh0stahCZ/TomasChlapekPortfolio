package com.tomaschlapek.portfolio.core.modules;

import android.content.Context;

import com.tomaschlapek.portfolio.core.scopes.CustomScope;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.storage.PortfolioRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by tomaschlapek on 4/7/16.
 */

/**
 * This module will be created newly for every call (it has own defined scope)
 */
@Module
public class PortfolioRepositoryModule {

  @Provides
  @CustomScope
  public PortfolioRepository providePortfolioRepository(Context context, Retrofit retrofit) {
    return new PortfolioRepositoryImpl(context, retrofit);
  }
}
