package com.tomaschlapek.portfolio.network.services;

import com.tomaschlapek.portfolio.network.model.Portfolio;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by tomaschlapek on 6/7/16.
 */
public interface PortfolioService {
  @GET("portfolios")
  Observable<Portfolio> getPortfolios();

}
