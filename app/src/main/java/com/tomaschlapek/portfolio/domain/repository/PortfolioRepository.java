package com.tomaschlapek.portfolio.domain.repository;

import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.network.model.Project;

import java.util.List;

import rx.Observable;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface PortfolioRepository {

  List<Project> getProjects();

  Observable<Portfolio> getPortfolio();
}
