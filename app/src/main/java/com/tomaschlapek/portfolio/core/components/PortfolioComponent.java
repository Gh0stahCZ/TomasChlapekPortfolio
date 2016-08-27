package com.tomaschlapek.portfolio.core.components;

import com.tomaschlapek.portfolio.core.modules.PortfolioModule;
import com.tomaschlapek.portfolio.core.scopes.PerActivity;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.presentation.ui.adapters.PortfolioListAdapter;

import dagger.Subcomponent;

/**
 * Created by tomaschlapek on 6/7/16.
 */
@PerActivity
@Subcomponent(modules = PortfolioModule.class)
public interface PortfolioComponent {

  PortfolioRepository providePortfolioRepository();

  PortfolioListAdapter providePortfolioListAdapter();

}
