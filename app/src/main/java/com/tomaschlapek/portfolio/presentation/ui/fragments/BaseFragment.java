package com.tomaschlapek.portfolio.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;
import com.tomaschlapek.portfolio.presentation.ui.activities.DrawerActivity;
import com.tomaschlapek.portfolio.util.MiscMethods;
import com.tomaschlapek.portfolio.util.PresenterLoader;

import java.lang.reflect.Field;

import timber.log.Timber;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public abstract class BaseFragment<P extends BasePresenter<V>, V> extends Fragment {

  //  private static final int LOADER_ID = 101;

  // boolean flag to avoid delivering the result twice. Calling initLoader in onActivityCreated makes
  // onLoadFinished will be called twice during configuration change.
  private boolean delivered = false;
  private BasePresenter<V> presenter;

  /* Public Methods *******************************************************************************/

  protected abstract PresenterFactory<P> getPresenterFactory();

  protected abstract void onPresenterPrepared(P presenter);

  protected void onPresenterDestroyed() {
    // hook for subclasses
  }

  // Override in case of fragment not implementing Presenter<Vista> interface
  protected V getPresenterView() {
    return (V) this;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Gets page name according to class name.
   */
  public String getPageTitle() {
    return getClass().getName();
  }

  /**
   * Gets ID of page according to class name.
   *
   * @return
   */
  public int getPageId() {
    return MiscMethods.stringToInt(getPageTitle());
  }

  @Override
  public void onPause() {
    presenter.onViewDetached();
    super.onPause();
  }

  @Override
  public void onResume() {
    super.onResume();
    if (getActivity() instanceof DrawerActivity) {
      ((DrawerActivity) getActivity()).setToolbarTitle(getToolbarTitle());
    }
    presenter.onViewAttached(getPresenterView());
  }

  /**
   * @see Fragment#onDetach()
   */
  @Override
  public void onDetach() {
    super.onDetach();

    // Set the child fragment manager to null.
    // Needs to be done manually on Android 4 devices.
    try {
      Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
      childFragmentManager.setAccessible(true);
      childFragmentManager.set(this, null);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    getLoaderManager().initLoader(getPageId(), null, new LoaderManager.LoaderCallbacks<P>() {
      @Override
      public final Loader<P> onCreateLoader(int id, Bundle args) {
        Timber.i("onCreateLoader-" + getPageTitle());
        return new PresenterLoader<>(getContext(), getPresenterFactory(), getPageTitle());
      }

      @Override
      public final void onLoadFinished(Loader<P> loader, P presenter) {
        Timber.i("onLoadFinished-" + getPageTitle());
        if (!delivered) {
          BaseFragment.this.presenter = presenter;
          delivered = true;
          onPresenterPrepared(presenter);
        }
      }

      @Override
      public final void onLoaderReset(Loader<P> loader) {
        Timber.i("onLoaderReset-" + getPageTitle());
        BaseFragment.this.presenter = null;
        onPresenterDestroyed();
      }
    });
  }

  public void showKeyboard(View view) {
    Timber.d("showKeyboard()");

    // Force the keyboard to show.
    FragmentActivity activity = getActivity();
    if (activity != null) {
      InputMethodManager manager = (InputMethodManager) activity.
        getSystemService(Context.INPUT_METHOD_SERVICE);
      manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
  }

  public void hideKeyboard() {
    Timber.d("hideKeyboard()");

    // Hide keyboard associated with current focus.
    FragmentActivity activity = getActivity();
    if (activity != null) {
      InputMethodManager manager = (InputMethodManager) activity.
        getSystemService(Context.INPUT_METHOD_SERVICE);
      View currentFocus = activity.getCurrentFocus();
      if (currentFocus != null) {
        manager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
      }
    }
  }

  public abstract String getToolbarTitle();
}
