package com.tomaschlapek.portfolio.navigation;

import javax.inject.Inject;

/**
 * Created by tomaschlapek on 4/7/16.
 */
public class Navigator {

  long time;

  @Inject
  public Navigator() {
    this.time = System.currentTimeMillis();
  }
}
