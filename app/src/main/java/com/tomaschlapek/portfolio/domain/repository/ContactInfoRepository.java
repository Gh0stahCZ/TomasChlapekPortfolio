package com.tomaschlapek.portfolio.domain.repository;

import com.tomaschlapek.portfolio.network.model.Portfolio;

import rx.Observable;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface ContactInfoRepository {

  Observable<Portfolio> getContactInfo();
}
