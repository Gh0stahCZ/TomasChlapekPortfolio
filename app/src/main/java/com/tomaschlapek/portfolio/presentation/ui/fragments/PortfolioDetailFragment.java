package com.tomaschlapek.portfolio.presentation.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.annotation.Nullable;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.core.modules.PortfolioModule;
import com.tomaschlapek.portfolio.databinding.FragmentPortfolioDetailBinding;
import com.tomaschlapek.portfolio.domain.model.Argument;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.factory.portfolio.PortfolioDetailPresenterFactory;
import com.tomaschlapek.portfolio.network.model.Project;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioDetailPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.PortfolioDetailPresenter.Vista;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public class PortfolioDetailFragment extends BaseFragment implements Vista {

  @InjectExtra(Argument.EXTRA_PROJECT)
  @Nullable
  Project mProject;

  @BindView(R.id.portfolio_detail_name_text_view)
  TextView portfolioNameTextView;

  PortfolioDetailPresenter mPortfolioDetailPresenter;

  @Override
  protected PresenterFactory getPresenterFactory() {

    PortfolioRepository mPortfolioRepository =
      AndroidApplication.getAppComponent()
        .plusPortfolioComponent(new PortfolioModule(this)).providePortfolioRepository();

    return new PortfolioDetailPresenterFactory(mPortfolioRepository);
  }

  @Override
  protected void onPresenterPrepared(BasePresenter presenter) {
    mPortfolioDetailPresenter = (PortfolioDetailPresenter) presenter;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this, getArguments()); // Workaround -> Dart does not support SupportFragment
  }

  @Override
  public String getToolbarTitle() {
    return getPageTitle();
  }

  public static PortfolioDetailFragment newInstance(Project project) {

    Bundle args = new Bundle();
    args.putParcelable(Argument.EXTRA_PROJECT, Parcels.wrap(project));

    PortfolioDetailFragment fragment = new PortfolioDetailFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
//    View view = inflater.inflate(R.layout.fragment_portfolio_detail, container, false);

    FragmentPortfolioDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio_detail, container, false);
    View view = binding.getRoot();
    binding.setProjectDetail(mProject);


    ButterKnife.bind(this, view);

    init();

    return view;
  }

  /**
   * First initViews.
   */
  private void init() {
    portfolioNameTextView.setText(mProject.getProjectTitle());
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
