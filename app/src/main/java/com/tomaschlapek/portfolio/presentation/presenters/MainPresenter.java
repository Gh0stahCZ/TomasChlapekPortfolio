package com.tomaschlapek.portfolio.presentation.presenters;

import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;
import com.tomaschlapek.portfolio.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void clickOnGetData();

         void showPortfolios(Portfolio portfolio);
    }

    void retrofitClick();

}
