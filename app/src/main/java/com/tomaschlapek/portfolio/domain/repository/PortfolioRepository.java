package com.tomaschlapek.portfolio.domain.repository;

import com.tomaschlapek.portfolio.domain.interactors.impl.ShowAllProjectsInteractorImpl.PortfolioCallback;
import com.tomaschlapek.portfolio.network.model.Project;

import java.util.List;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface PortfolioRepository {

  List<Project> getProjects();

  Project getProject(PortfolioCallback portfolioCallback);
}
