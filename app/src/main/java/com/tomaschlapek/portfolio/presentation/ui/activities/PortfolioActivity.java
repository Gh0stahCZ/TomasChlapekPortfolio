package com.tomaschlapek.portfolio.presentation.ui.activities;

import android.os.Bundle;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.core.executor.PostExecutionThread;
import com.tomaschlapek.portfolio.domain.enumeration.InitPageType;
import com.tomaschlapek.portfolio.factory.MainPresenterFactory;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.navigation.Navigator;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter.Vista;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;
import com.tomaschlapek.portfolio.presentation.ui.fragments.BaseFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.BlogEntryFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.BlogListFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.ContactInfoFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.CvInfoFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.PortfolioDetailFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.PortfolioListFragment;

import javax.inject.Inject;

public class PortfolioActivity extends DrawerActivity implements Vista {

    public static class Extra {
        public static final String PORTFOLIO_LIST_FRAGMENT = "portfolio_list_fragment";
        public static final String PORTFOLIO_DETAIL_FRAGMENT = "portfolio_detail_fragment";

        public static final String BLOG_LIST_FRAGMENT = "blog_list_fragment";
        public static final String BLOG_ENTRY_FRAGMENT = "blog_entry_fragment";

        public static final String CV_INFO_FRAGMENT = "cv_info_fragment";

        public static final String CONTACT_INFO_FRAGMENT = "contact_info_fragment";
    }

    @Inject
    PostExecutionThread mPostExecutionThread;

    @Inject
    Navigator mNavigator;

    BaseFragment mPrimarySelectedFragment;
    BaseFragment mSecondarySelectedFragment;

    MainPresenter mMainPresenter;

    /**
     * Initial page to be displayed.
     */
    InitPageType mInitPageType = InitPageType.PORTFOLIO;

    @Override
    protected PresenterFactory getPresenterFactory() {
        return new MainPresenterFactory();
    }

    @Override
    protected void onPresenterPrepared(BasePresenter presenter) {
        mMainPresenter = (MainPresenter) presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    /*
        // inflate the custom activity layout
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //        android.view.Vista portfolioView =
        layoutInflater.inflate(R.layout.portfolio_view, mMainContainer, true);
    */

        ((AndroidApplication) getApplication()).getAppComponent().inject(this);
        mNavigator.init(getSupportFragmentManager());

        if (savedInstanceState != null) {

            switch (mInitPageType) {

                case CV:
                    if (savedInstanceState.containsKey(Extra.CV_INFO_FRAGMENT)) {
                        mPrimarySelectedFragment = (CvInfoFragment) getSupportFragmentManager().
                          getFragment(savedInstanceState, Extra.CV_INFO_FRAGMENT);
                    }
                    break;
                case BLOG:
                    if (savedInstanceState.containsKey(Extra.BLOG_LIST_FRAGMENT)) {
                        mPrimarySelectedFragment = (BlogListFragment) getSupportFragmentManager().
                          getFragment(savedInstanceState, Extra.BLOG_LIST_FRAGMENT);
                    }
                    if (savedInstanceState.containsKey(Extra.BLOG_ENTRY_FRAGMENT)) {
                        mSecondarySelectedFragment = (BlogEntryFragment) getSupportFragmentManager().
                          getFragment(savedInstanceState, Extra.BLOG_ENTRY_FRAGMENT);
                    }
                    break;
                case CONTACT:
                    if (savedInstanceState.containsKey(Extra.CONTACT_INFO_FRAGMENT)) {
                        mPrimarySelectedFragment = (ContactInfoFragment) getSupportFragmentManager().
                          getFragment(savedInstanceState, Extra.CONTACT_INFO_FRAGMENT);
                    }
                    break;
                case PORTFOLIO:
                    if (savedInstanceState.containsKey(Extra.PORTFOLIO_LIST_FRAGMENT)) {
                        mPrimarySelectedFragment = (PortfolioListFragment) getSupportFragmentManager().
                          getFragment(savedInstanceState, Extra.PORTFOLIO_LIST_FRAGMENT);
                    }
                    if (savedInstanceState.containsKey(Extra.PORTFOLIO_DETAIL_FRAGMENT)) {
                        mSecondarySelectedFragment = (PortfolioDetailFragment) getSupportFragmentManager().
                          getFragment(savedInstanceState, Extra.PORTFOLIO_DETAIL_FRAGMENT);
                    }
                default:
                    break;
            }
        }

        if (mPrimarySelectedFragment == null || (AndroidApplication.isDualPane()
          && mSecondarySelectedFragment == null)) {
            switch (mInitPageType) {

                case CV:
                    mPrimarySelectedFragment = mNavigator.createAndAddCvInfoFragment(this);
                    //                mNavigator.createAndAddCvInfoFragment(this);
                    break;
                case BLOG:
                    mPrimarySelectedFragment = mNavigator.createAndAddBlogListFragment(this);
                    //                mNavigator.createAndAddBlogListFragment(this);
                    //                if (mCurrentDetailFragment == null && AndroidApplication.isDualPane()) {
                    //                    mNavigator.createAndAddBlogDetailFragment(this);
                    //                }
                    break;
                case CONTACT:
                    mPrimarySelectedFragment = mNavigator.createAndAddContactInfoFragment(this);
                    //                mNavigator.createAndAddContactInfoFragment(this);
                    break;
                case PORTFOLIO:
                    mPrimarySelectedFragment = mNavigator.createAndAddPortfolioListFragment(this);
                    //                mNavigator.createAndAddPortfolioListFragment(this);
                    //                if (mCurrentDetailFragment == null && AndroidApplication.isDualPane()) {
                    //                    mNavigator.createAndAddPortfolioDetailFragment(this);
                    //                }
                default:
                    break;
            }
            //        initViews();
        }
    }

    //    public void initViews() {
    //
    //        mMainPresenter = new MainPresenterImpl(
    //          ThreadExecutor.getInstance(),
    //          MainThreadImpl.getInstance());
    //    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save fragment states.
        switch (mInitPageType) {

            case CV:
                if (mPrimarySelectedFragment != null) {
                    getSupportFragmentManager().putFragment(outState,
                      Extra.CV_INFO_FRAGMENT, mPrimarySelectedFragment);
                }
                break;
            case BLOG:
                if (mPrimarySelectedFragment != null) {
                    getSupportFragmentManager().putFragment(outState,
                      Extra.BLOG_LIST_FRAGMENT, mPrimarySelectedFragment);
                }
                if (mSecondarySelectedFragment != null) {
                    getSupportFragmentManager().putFragment(outState,
                      Extra.BLOG_ENTRY_FRAGMENT, mSecondarySelectedFragment);
                }
                break;
            case CONTACT:
                if (mPrimarySelectedFragment != null) {
                    getSupportFragmentManager().putFragment(outState,
                      Extra.CONTACT_INFO_FRAGMENT, mPrimarySelectedFragment);
                }
                break;
            case PORTFOLIO:
                if (mPrimarySelectedFragment != null) {
                    getSupportFragmentManager().putFragment(outState,
                      Extra.PORTFOLIO_LIST_FRAGMENT, mPrimarySelectedFragment);
                }
                if (mSecondarySelectedFragment != null) {
                    getSupportFragmentManager().putFragment(outState,
                      Extra.PORTFOLIO_DETAIL_FRAGMENT, mSecondarySelectedFragment);
                }
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void showError(String message) {
    }

    @Override
    public void showError(int messageResId, int iconResId) {
    }
}
