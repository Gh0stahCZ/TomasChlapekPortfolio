
package com.tomaschlapek.portfolio.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
@Parcel(Serialization.BEAN)
public class Project {


  @SerializedName("project_title")
  @Expose
  private String projectTitle;
  @SerializedName("project_description")
  @Expose
  private String projectDescription;
  @SerializedName("project_info")
  @Expose
  private String projectInfo;
  @SerializedName("project_coop")
  @Expose
  private ProjectCoop projectCoop;
  @SerializedName("project_image_url")
  @Expose
  private String projectImageUrl;
  @SerializedName("logo_url")
  @Expose
  private String logoUrl;
  @SerializedName("google_play_url")
  @Expose
  private String googlePlayUrl;
  @SerializedName("status")
  @Expose
  private String status;
  @SerializedName("category")
  @Expose
  private String category;
  @SerializedName("position")
  @Expose
  private String position;
  @SerializedName("start_time")
  @Expose
  private String startTime;
  @SerializedName("end_time")
  @Expose
  private String endTime;
  @SerializedName("in_progress")
  @Expose
  private Boolean inProgress;
  @SerializedName("screenshots")
  @Expose
  private List<Screenshot> screenshots = new ArrayList<Screenshot>();
  @SerializedName("videos")
  @Expose
  private List<Video> videos = new ArrayList<Video>();
  @SerializedName("last_update")
  @Expose
  private String lastUpdate;

  @ParcelConstructor
  public Project(String category, String endTime, String googlePlayUrl, Boolean inProgress,
    String lastUpdate, String logoUrl, String position,
    ProjectCoop projectCoop, String projectDescription, String projectImageUrl,
    String projectInfo, String projectTitle,
    List<Screenshot> screenshots, String startTime, String status,
    List<Video> videos) {
    this.category = category;
    this.endTime = endTime;
    this.googlePlayUrl = googlePlayUrl;
    this.inProgress = inProgress;
    this.lastUpdate = lastUpdate;
    this.logoUrl = logoUrl;
    this.position = position;
    this.projectCoop = projectCoop;
    this.projectDescription = projectDescription;
    this.projectImageUrl = projectImageUrl;
    this.projectInfo = projectInfo;
    this.projectTitle = projectTitle;
    this.screenshots = screenshots;
    this.startTime = startTime;
    this.status = status;
    this.videos = videos;
  }

  /**
   * @return The projectTitle
   */
  public String getProjectTitle() {
    return projectTitle;
  }

  /**
   * @param projectTitle The project_title
   */
  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }

  /**
   * @return The projectDescription
   */
  public String getProjectDescription() {
    return projectDescription;
  }

  /**
   * @param projectDescription The project_description
   */
  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  /**
   * @return The projectInfo
   */
  public String getProjectInfo() {
    return projectInfo;
  }

  /**
   * @param projectInfo The project_info
   */
  public void setProjectInfo(String projectInfo) {
    this.projectInfo = projectInfo;
  }

  /**
   * @return The projectCoop
   */
  public ProjectCoop getProjectCoop() {
    return projectCoop;
  }

  /**
   * @param projectCoop The project_coop
   */
  public void setProjectCoop(ProjectCoop projectCoop) {
    this.projectCoop = projectCoop;
  }

  /**
   * @return The projectImageUrl
   */
  public String getProjectImageUrl() {
    return projectImageUrl;
  }

  /**
   * @param projectImageUrl The project_image_url
   */
  public void setProjectImageUrl(String projectImageUrl) {
    this.projectImageUrl = projectImageUrl;
  }

  /**
   * @return The logoUrl
   */
  public String getLogoUrl() {
    return logoUrl;
  }

  /**
   * @param logoUrl The logo_url
   */
  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  /**
   * @return The googlePlayUrl
   */
  public String getGooglePlayUrl() {
    return googlePlayUrl;
  }

  /**
   * @param googlePlayUrl The google_play_url
   */
  public void setGooglePlayUrl(String googlePlayUrl) {
    this.googlePlayUrl = googlePlayUrl;
  }

  /**
   * @return The status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status The status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return The category
   */
  public String getCategory() {
    return category;
  }

  /**
   * @param category The category
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * @return The position
   */
  public String getPosition() {
    return position;
  }

  /**
   * @param position The position
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * @return The startTime
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * @param startTime The start_time
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * @return The endTime
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * @param endTime The end_time
   */
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  /**
   * @return The inProgress
   */
  public Boolean getInProgress() {
    return inProgress;
  }

  /**
   * @param inProgress The in_progress
   */
  public void setInProgress(Boolean inProgress) {
    this.inProgress = inProgress;
  }

  /**
   * @return The screenshots
   */
  public List<Screenshot> getScreenshots() {
    return screenshots;
  }

  /**
   * @param screenshots The screenshots
   */
  public void setScreenshots(List<Screenshot> screenshots) {
    this.screenshots = screenshots;
  }

  /**
   * @return The videos
   */
  public List<Video> getVideos() {
    return videos;
  }

  /**
   * @param videos The videos
   */
  public void setVideos(List<Video> videos) {
    this.videos = videos;
  }

  /**
   * @return The lastUpdate
   */
  public String getLastUpdate() {
    return lastUpdate;
  }

  /**
   * @param lastUpdate The last_update
   */
  public void setLastUpdate(String lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  @Override
  public String toString() {
    return "Project{" +
      "category='" + category + '\'' +
      ", projectTitle='" + projectTitle + '\'' +
      ", projectDescription='" + projectDescription + '\'' +
      ", projectInfo='" + projectInfo + '\'' +
      ", projectCoop=" + projectCoop +
      ", projectImageUrl='" + projectImageUrl + '\'' +
      ", logoUrl='" + logoUrl + '\'' +
      ", googlePlayUrl='" + googlePlayUrl + '\'' +
      ", status='" + status + '\'' +
      ", position='" + position + '\'' +
      ", startTime='" + startTime + '\'' +
      ", endTime=" + endTime +
      ", inProgress=" + inProgress +
      ", screenshots=" + screenshots +
      ", videos=" + videos +
      ", lastUpdate='" + lastUpdate + '\'' +
      '}';
  }
}
