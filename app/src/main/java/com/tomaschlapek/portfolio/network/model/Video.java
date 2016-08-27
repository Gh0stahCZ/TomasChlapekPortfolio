
package com.tomaschlapek.portfolio.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
@Parcel(Serialization.BEAN)
public class Video {

  @SerializedName("video_title")
  @Expose
  private String videoTitle;
  @SerializedName("video_url")
  @Expose
  private String videoUrl;

  /**
   * @return The videoTitle
   */
  public String getVideoTitle() {
    return videoTitle;
  }

  /**
   * @param videoTitle The video_title
   */
  public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
  }

  /**
   * @return The videoUrl
   */
  public String getVideoUrl() {
    return videoUrl;
  }

  /**
   * @param videoUrl The video_url
   */
  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }
}
