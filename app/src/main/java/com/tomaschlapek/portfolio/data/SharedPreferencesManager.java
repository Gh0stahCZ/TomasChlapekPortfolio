package com.tomaschlapek.portfolio.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by tomaschlapek on 4/7/16.
 */
public class SharedPreferencesManager {

  SharedPreferences sharedPreferences;

  public SharedPreferencesManager(Application application) {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
  }

  public void saveStringValue(String key, String value) {
    sharedPreferences.edit().putString(key, value).commit();
  }

  public String loadStringValue(String key) {
    return sharedPreferences.getString(key, null);
  }
}
