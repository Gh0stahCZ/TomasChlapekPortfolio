
package com.tomaschlapek.portfolio.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Screenshot {

  @SerializedName("screen_title")
  @Expose
  private String screenTitle;
  @SerializedName("screen_url")
  @Expose
  private String screenUrl;

  /**
   * @return The screenTitle
   */
  public String getScreenTitle() {
    return screenTitle;
  }

  /**
   * @param screenTitle The screen_title
   */
  public void setScreenTitle(String screenTitle) {
    this.screenTitle = screenTitle;
  }

  /**
   * @return The screenUrl
   */
  public String getScreenUrl() {
    return screenUrl;
  }

  /**
   * @param screenUrl The screen_url
   */
  public void setScreenUrl(String screenUrl) {
    this.screenUrl = screenUrl;
  }
}
