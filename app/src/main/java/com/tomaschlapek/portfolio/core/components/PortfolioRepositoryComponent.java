package com.tomaschlapek.portfolio.core.components;

import com.tomaschlapek.portfolio.core.modules.PortfolioRepositoryModule;
import com.tomaschlapek.portfolio.core.scopes.CustomScope;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.presentation.ui.activities.MainActivity;

import dagger.Subcomponent;

/**
 * Created by tomaschlapek on 6/7/16.
 */
@CustomScope
@Subcomponent(modules = PortfolioRepositoryModule.class)
public interface PortfolioRepositoryComponent {

  void inject(MainActivity activity);

  PortfolioRepository providePortfolioRepository();

}
