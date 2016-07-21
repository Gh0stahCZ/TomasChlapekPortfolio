package com.tomaschlapek.portfolio.network.services;

import com.tomaschlapek.portfolio.network.model.Portfolio;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tomaschlapek on 6/7/16.
 */
public interface PortfolioService {
  @GET("portfolios") //
  Call<Portfolio> getPortfolios();

}
