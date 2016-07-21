
package com.tomaschlapek.portfolio.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ProjectCoop {

  @SerializedName("coop_company")
  @Expose
  private String coopCompany;
  @SerializedName("coop_url")
  @Expose
  private String coopUrl;

  /**
   * @return The coopCompany
   */
  public String getCoopCompany() {
    return coopCompany;
  }

  /**
   * @param coopCompany The coop_company
   */
  public void setCoopCompany(String coopCompany) {
    this.coopCompany = coopCompany;
  }

  /**
   * @return The coopUrl
   */
  public String getCoopUrl() {
    return coopUrl;
  }

  /**
   * @param coopUrl The coop_url
   */
  public void setCoopUrl(String coopUrl) {
    this.coopUrl = coopUrl;
  }
}
