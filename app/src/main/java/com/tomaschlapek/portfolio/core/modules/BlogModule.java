package com.tomaschlapek.portfolio.core.modules;

import com.tomaschlapek.portfolio.core.scopes.PerActivity;
import com.tomaschlapek.portfolio.presentation.ui.fragments.BlogEntryFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.BlogListFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomaschlapek on 4/7/16.
 */

/**
 * This module will be created newly for every call (it has own defined scope)
 */
@Module
public class BlogModule {

  /* Portfolio Detail Fragment ********************************************************************/

  BlogListFragment mBlogListFragment;

  public BlogModule(BlogListFragment fragment) {
    mBlogListFragment = fragment;
  }

  @Provides
  @PerActivity
  public BlogListFragment providePortfolioListFragment() {
    return mBlogListFragment;
  }



  /* Portfolio Detail Fragment ********************************************************************/

  BlogEntryFragment mBlogEntryFragment;

  public BlogModule(BlogEntryFragment fragment) {
    mBlogEntryFragment = fragment;
  }

  @Provides
  @PerActivity
  public BlogEntryFragment providePortfolioDetailFragment() {
    return mBlogEntryFragment;
  }

}
