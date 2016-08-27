package com.tomaschlapek.portfolio.util;

import android.content.Context;

import android.support.v4.content.Loader;

import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;

import timber.log.Timber;

/**
 * Created by tomaschlapek on 13/8/16.
 */
public class PresenterLoader<T extends BasePresenter> extends Loader<T> {

  private final PresenterFactory<T> factory;
  private T presenter;
  private final String tag;

  /**
   * Stores away the application context associated with context.
   * Since Loaders can be used across multiple activities it's dangerous to
   * store the context directly; always use {@link #getContext()} to retrieve
   * the Loader's Context, don't use the constructor argument directly.
   * The Context returned by {@link #getContext} is safe to use across
   * Activity instances.
   *
   * @param context used to retrieve the application context.
   */
  public PresenterLoader(Context context, PresenterFactory<T> factory, String tag) {
    super(context);
    this.factory = factory;
    this.tag = tag;
  }

  @Override
  protected void onStartLoading() {
    Timber.i("onStartLoading() - " + tag);

    // If we already own an instance, simply deliver it.
    if (presenter != null) {
      deliverResult(presenter);
      return;
    }

    // Otherwise, force a load
    forceLoad();
  }

  @Override
  protected void onStopLoading() {
    super.onStopLoading();

    Timber.i("onStopLoading() - " + tag);
  }

  @Override
  protected void onForceLoad() {
    Timber.i("onForceLoad() - " + tag);
    // Create the Presenter using the Factory
    presenter = factory.create();

    // Deliver the result
    deliverResult(presenter);
  }

  @Override
  protected void onReset() {
    Timber.i("onReset() - " + tag);
    if (presenter != null) {
      presenter.onDestroyed();
      presenter = null;
    }
  }
}
