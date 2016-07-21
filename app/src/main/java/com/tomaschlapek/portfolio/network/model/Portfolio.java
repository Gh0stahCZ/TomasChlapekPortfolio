package com.tomaschlapek.portfolio.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tomaschlapek on 8/7/16.
 */
public class Portfolio {

  @SerializedName("portfolio")
  private List<Project> portfolio;

  public List<Project> getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(
    List<Project> portfolio) {
    this.portfolio = portfolio;
  }
}
