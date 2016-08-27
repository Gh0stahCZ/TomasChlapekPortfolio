package com.tomaschlapek.portfolio.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.core.modules.PortfolioModule;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.factory.portfolio.PortfolioListPresenterFactory;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.network.model.Project;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioListPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioListPresenter.Vista;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;
import com.tomaschlapek.portfolio.presentation.ui.adapters.PortfolioListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public class PortfolioListFragment extends BaseFragment implements Vista {

  @BindView(R.id.retrofit_output)
  TextView retrofitTextVIew;

  @BindView(R.id.retrofit_button)
  TextView retrofitButton;

  @BindView(R.id.portfolio_list_recycler_view)
  RecyclerView portfolioListRecyclerView;

  @BindView(R.id.portfolio_list_swipe_refresh)
  SwipeRefreshLayout swipeRefreshLayout;

  @BindView(R.id.error_container)
  ViewGroup errorContainer;

  @BindView(R.id.error_text_view)
  TextView errorTextView;

  @BindView(R.id.error_icon)
  ImageView errorIcon;

  PortfolioListAdapter mPortfolioListAdapter;

  PortfolioListPresenter mPortfolioListPresenter;

  @Override
  protected PresenterFactory getPresenterFactory() {

    PortfolioRepository mPortfolioRepository =
      AndroidApplication.getAppComponent()
        .plusPortfolioComponent(new PortfolioModule(this)).providePortfolioRepository();

    return new PortfolioListPresenterFactory(mPortfolioRepository);
  }

  @Override
  protected void onPresenterPrepared(BasePresenter presenter) {
    mPortfolioListPresenter = (PortfolioListPresenter) presenter;
  }

  /**
   * Entire communication with presenter must be called after onResume(), when view is attached.
   */
  @Override
  public void onResume() {
    super.onResume();
    requestPortfolio();
    setupSwipeRefresh();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public String getToolbarTitle() {
    return getPageTitle();
  }

  public static PortfolioListFragment newInstance() {

    Bundle args = new Bundle();

    PortfolioListFragment fragment = new PortfolioListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_portfolio_list, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    initViews();
  }

  /**
   * First initialization.
   */
  public void initViews() {

    initPortfolioRecyclerView();
  }

  /**
   * Sets adapter and layout manager for list of projects (portfolio).
   */
  private void initPortfolioRecyclerView() {
    mPortfolioListAdapter = AndroidApplication.getAppComponent()
      .plusPortfolioComponent(new PortfolioModule(this)).providePortfolioListAdapter();
    portfolioListRecyclerView.setAdapter(mPortfolioListAdapter);
    portfolioListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
  }

  /**
   * Sets behavior of swipe to refresh layout.
   */
  private void setupSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> requestPortfolio());
  }

  private void requestPortfolio() {
    hideError();
    mPortfolioListPresenter.swipeToRefresh();
  }

  @OnClick(R.id.retrofit_button)
  public void clickOnGetData() {
    requestPortfolio();
  }

  @OnClick(R.id.error_retry_button)
  public void onErrorRetrClick() {

    requestPortfolio();
  }

  @Override
  public void showPortfolios(Portfolio portfolio) {
    mPortfolioListAdapter.updatePortfolioList(portfolio.getPortfolio());
    retrofitTextVIew.setText(portfolio.getPortfolio().toString());
  }

  @Override
  public void onProjectClick(Project project) {
    AndroidApplication.getAppComponent().provideNavigator()
      .createAndAddPortfolioDetailFragment(getActivity(), project,
        AndroidApplication.isDualPane() ? false : true);
  }

  @Override
  public void showProgress() {

  }

  @Override
  public void hideProgress() {
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void showError(String message) {

  }

  @Override
  public void showError(int messageResId, int iconResId) {
    mPortfolioListAdapter.clearPortfolioList();
    errorContainer.setVisibility(View.VISIBLE);
    errorTextView.setText(getString(messageResId));
    errorIcon.setImageResource(iconResId);
  }

  public void hideError() {
    errorContainer.setVisibility(View.GONE);
  }
}
