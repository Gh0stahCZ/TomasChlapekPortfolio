package com.tomaschlapek.portfolio.domain.interactors;

import com.tomaschlapek.portfolio.domain.interactors.base.Interactor;
import com.tomaschlapek.portfolio.network.model.Portfolio;

public interface ShowAllProjectsInteractor extends Interactor {

  interface Callback {
    void showPortfolios(Portfolio portfolio);
  }

  // TODO: Add interactor methods here
}
