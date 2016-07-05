package com.tomaschlapek.portfolio.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * Created by tomaschlapek on 5/7/16.
 */
public class ReleaseTree extends Timber.Tree {

  @Override
  protected boolean isLoggable(int priority) {

    if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
      return false;
    }
    return true;
  }

  @Override
  protected void log(int priority, String tag, String message, Throwable t) {
    if (isLoggable(priority)) {

      if (priority == Log.ERROR && t != null) {
        Crashlytics.log(t.getLocalizedMessage());
      }
    }
  }
}
