package com.tomaschlapek.portfolio.core.modules;

import android.content.Context;

import com.tomaschlapek.portfolio.core.scopes.PerActivity;
import com.tomaschlapek.portfolio.domain.model.ProjectType;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.presentation.ui.adapters.PortfolioListAdapter;
import com.tomaschlapek.portfolio.presentation.ui.adapters.viewholder.ProjectViewHolderExtendedFactory;
import com.tomaschlapek.portfolio.presentation.ui.fragments.PortfolioDetailFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.PortfolioListFragment;
import com.tomaschlapek.portfolio.storage.PortfolioRepositoryImpl;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import retrofit2.Retrofit;

/**
 * Created by tomaschlapek on 4/7/16.
 */

/**
 * This module will be created newly for every call (it has own defined scope)
 */
@Module
public class PortfolioModule {

  PortfolioListFragment mPortfolioListFragment;

  /* Portfolio Detail Fragment ********************************************************************/

  public PortfolioModule(PortfolioListFragment fragment) {
    mPortfolioListFragment = fragment;
  }

  @Provides
  @PerActivity
  public PortfolioListFragment providePortfolioListFragment() {
    return mPortfolioListFragment;
  }

  @Provides
  @PerActivity
  public PortfolioRepository providePortfolioRepository(Context context, Retrofit retrofit) {
    return new PortfolioRepositoryImpl(context, retrofit);
  }

  @Provides
  @PerActivity
  public PortfolioListAdapter providePortfolioListAdapter(PortfolioListFragment fragment,
    Map<Integer, ProjectViewHolderExtendedFactory> viewHolderExtendedFactoryMap) {
    //    Map<Integer, ProjectViewHolderExtendedFactory> viewHolderExtendedFactoryMap = new HashMap<>();
    //    viewHolderExtendedFactoryMap.put(ProjectType.NORMAL, new ProjectViewHolderExtendedFactory());
    return new PortfolioListAdapter(fragment, viewHolderExtendedFactoryMap);
  }

  @Provides
  @IntoMap
  @IntKey(ProjectType.NORMAL)
  ProjectViewHolderExtendedFactory provideViewHolderExtended() {
    return new ProjectViewHolderExtendedFactory();
  }

  /* Portfolio Detail Fragment ********************************************************************/

  PortfolioDetailFragment mPortfolioDetailFragment;

  public PortfolioModule(PortfolioDetailFragment fragment) {
    mPortfolioDetailFragment = fragment;
  }

  @Provides
  @PerActivity
  public PortfolioDetailFragment providePortfolioDetailFragment() {
    return mPortfolioDetailFragment;
  }

  //  @Provides
  //  @PerActivity
  //  public PortfolioRepository providePortfolioDetailRepository(Context context, Retrofit retrofit) {
  //    return new PortfolioRepositoryImpl(context, retrofit);
  //  }

  //  @Provides
  //  @PerActivity
  //  public PortfolioListAdapter providePortfolioDetailAdapter(PortfolioListFragment fragment, Map<Integer, ProjectViewHolderExtendedFactory> viewHolderExtendedFactoryMap) {
  ////    Map<Integer, ProjectViewHolderExtendedFactory> viewHolderExtendedFactoryMap = new HashMap<>();
  ////    viewHolderExtendedFactoryMap.put(ProjectType.NORMAL, new ProjectViewHolderExtendedFactory());
  //    return new PortfolioListAdapter(fragment, viewHolderExtendedFactoryMap);
  //  }

  //  @Provides
  //  @IntoMap
  //  @IntKey(ProjectType.NORMAL)
  //  ProjectViewHolderExtendedFactory provideViewHolderExtended() {
  //    return new ProjectViewHolderExtendedFactory();
  //  }


}
