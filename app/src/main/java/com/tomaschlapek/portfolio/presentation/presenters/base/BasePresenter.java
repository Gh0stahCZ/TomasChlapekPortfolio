package com.tomaschlapek.portfolio.presentation.presenters.base;

public interface BasePresenter<V> {


    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();

}
