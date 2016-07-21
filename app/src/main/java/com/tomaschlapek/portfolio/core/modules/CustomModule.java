package com.tomaschlapek.portfolio.core.modules;

import com.tomaschlapek.portfolio.core.scopes.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 4/7/16.
 */

/**
 * This module will be created newly for every call (it has own defined scope)
 */
@Module
public class CustomModule {

  @Provides
  @CustomScope
  public Object provideCustomObject() {
    return new Object();
  }
}
