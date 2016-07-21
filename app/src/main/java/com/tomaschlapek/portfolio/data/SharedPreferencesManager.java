package com.tomaschlapek.portfolio.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.tomaschlapek.portfolio.R;

/**
 * Created by tomaschlapek on 4/7/16.
 */
public class SharedPreferencesManager {

  SharedPreferences sharedPreferences;

  public SharedPreferencesManager(Application application) {
    sharedPreferences =
      application.getSharedPreferences(application.getString(R.string.shared_preference_name),
        Context.MODE_PRIVATE);
  }

  public void saveStringValue(String key, String value) {
    sharedPreferences.edit().putString(key, value).commit();
  }

  public String loadStringValue(String key) {
    return sharedPreferences.getString(key, null);
  }
}
