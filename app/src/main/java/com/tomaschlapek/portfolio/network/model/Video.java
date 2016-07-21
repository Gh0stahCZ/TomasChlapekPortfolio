
package com.tomaschlapek.portfolio.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Video {

  @SerializedName("video_title")
  @Expose
  private Object videoTitle;
  @SerializedName("video_url")
  @Expose
  private Object videoUrl;

  /**
   * @return The videoTitle
   */
  public Object getVideoTitle() {
    return videoTitle;
  }

  /**
   * @param videoTitle The video_title
   */
  public void setVideoTitle(Object videoTitle) {
    this.videoTitle = videoTitle;
  }

  /**
   * @return The videoUrl
   */
  public Object getVideoUrl() {
    return videoUrl;
  }

  /**
   * @param videoUrl The video_url
   */
  public void setVideoUrl(Object videoUrl) {
    this.videoUrl = videoUrl;
  }
}
