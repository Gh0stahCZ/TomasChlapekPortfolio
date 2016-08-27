package com.tomaschlapek.portfolio.presentation.presenters;

import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.network.model.Project;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioListPresenter.Vista;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;
import com.tomaschlapek.portfolio.presentation.ui.BaseVista;

public interface PortfolioListPresenter extends BasePresenter<Vista> {

  interface Vista extends BaseVista {

    void showPortfolios(Portfolio portfolio);

    void onProjectClick(Project project);

  }

  void retrofitClick();

  void swipeToRefresh();
}
