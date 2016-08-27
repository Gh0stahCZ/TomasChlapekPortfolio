package com.tomaschlapek.portfolio.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public class MiscMethods {

  /**
   * Converts PX to DP on primary screen.
   *
   * @param px PX to convert.
   *
   * @return Resulting DP.
   */
  public static int pxToDp(Context context, int px) {
    return (int) ((px / context.getResources().getDisplayMetrics().density) + 0.5);
  }

  /**
   * Converts DP to PX on primary screen.
   *
   * @param dp DP to convert.
   *
   * @return Resulting PX.
   */
  public static int dpToPx(Context context, int dp) {
    return (int) ((dp * context.getResources().getDisplayMetrics().density) + 0.5);
  }

  /**
   * Returns country code of the current locale.
   */
  public static String getCountryCode(Context context) {
    return context.getResources().getConfiguration().locale.getCountry();
  }

  /**
   * @see Resources#getString(int)
   */
  public static String getResourceString(Context context, int id) {
    return context.getResources().getString(id);
  }

  /**
   * @see Resources#getString(int, Object...)
   */
  public static String getResourceString(Context context, int resourceId, Object... formatArgs) {
    return context.getResources().getString(resourceId, formatArgs);
  }

  /**
   * @see Resources#getBoolean(int)
   */
  public static boolean getResourceBoolean(Context context, int id) {
    return context.getResources().getBoolean(id);
  }

  /**
   * @see Resources#getDimensionPixelSize(int)
   */
  public static int getResourceDimension(Context context, int id) {
    return context.getResources().getDimensionPixelSize(id);
  }

  /**
   * Opens stream of specified asset file.
   *
   * @param assetFileName Asset file name.
   *
   * @return Opened stream of asset file.
   */
  public static InputStream getAssetFileStream(Context context, String assetFileName) {
    InputStream result = null;
    try {
      result = context.getAssets().open(assetFileName);
    } catch (IOException exception) {
      Timber.e(exception.getMessage(), exception);
    }
    return result;
  }

  /**
   * Converts string to integer.
   *
   * @param text Input text.
   *
   * @return Numeric value of string.
   */
  public static int stringToInt(String text) {
    int sum = 0;
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      sum += Character.getNumericValue(c);
    }
    return sum;
  }
}
