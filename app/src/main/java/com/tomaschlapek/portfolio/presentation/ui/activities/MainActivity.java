package com.tomaschlapek.portfolio.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v4.widget.NestedScrollView;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.core.components.PortfolioRepositoryComponent;
import com.tomaschlapek.portfolio.core.executor.PostExecutionThread;
import com.tomaschlapek.portfolio.core.modules.PortfolioRepositoryModule;
import com.tomaschlapek.portfolio.domain.executor.impl.ThreadExecutor;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter.View;
import com.tomaschlapek.portfolio.presentation.presenters.impl.MainPresenterImpl;
import com.tomaschlapek.portfolio.threading.MainThreadImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends DrawerActivity implements View {

    @BindView(R.id.retrofit_output)
    TextView retrofitTextVIew;

    @BindView(R.id.retrofit_button)
    TextView retrofitButton;

    @BindView(R.id.retrofit_scrollview)
    NestedScrollView scroll;

    @BindView(R.id.first)
    LinearLayout first;

    @BindView(R.id.second)
    LinearLayout second;

    @BindView(R.id.third)
    LinearLayout third;

    MainPresenter mMainPresenter;

    @Inject
    PostExecutionThread mPostExecutionThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate the custom activity layout
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        android.view.View portfolioView =
          layoutInflater.inflate(R.layout.portfolio_view, mMainContainer, true);
        // add the custom layout of this activity to frame layout.
//        mMainContainer.addView(portfolioView);

        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        ((AndroidApplication) getApplication()).getAppComponent().inject(this);

        init();

    }

    public void init() {

        PortfolioRepositoryComponent portfolioRepositoryComponent =
          ((AndroidApplication) getApplication()).getAppComponent()
            .plusPortfolioRepositoryComponent(new PortfolioRepositoryModule());

        PortfolioRepository portfolioRepository =
          portfolioRepositoryComponent.providePortfolioRepository();

        Timber.d("mPortfolioRepository: " + portfolioRepository);

        mMainPresenter = new MainPresenterImpl(
          ThreadExecutor.getInstance(),
          MainThreadImpl.getInstance(),
          this,
          portfolioRepository);
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    @OnClick(R.id.retrofit_button)
    public void clickOnGetData() {
        mMainPresenter.retrofitClick();
    }

    @Override
    public void showPortfolios(Portfolio portfolio) {
        retrofitTextVIew.setText(portfolio.getPortfolio().toString());

        ((TextView) first.findViewById(R.id.project_name))
          .setText(portfolio.getPortfolio().get(0).getProjectTitle());
        ((TextView) first.findViewById(R.id.project_desc))
          .setText(portfolio.getPortfolio().get(0).getProjectDescription());
        ((TextView) first.findViewById(R.id.project_info))
          .setText(portfolio.getPortfolio().get(0).getProjectInfo());

        ((TextView) second.findViewById(R.id.project_name))
          .setText(portfolio.getPortfolio().get(1).getProjectTitle());
        ((TextView) second.findViewById(R.id.project_desc))
          .setText(portfolio.getPortfolio().get(1).getProjectDescription());
        ((TextView) second.findViewById(R.id.project_info))
          .setText(portfolio.getPortfolio().get(1).getProjectInfo());
    }
}
