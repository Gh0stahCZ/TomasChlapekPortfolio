package com.tomaschlapek.portfolio.presentation.ui.activities;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;
import com.tomaschlapek.portfolio.util.MiscMethods;
import com.tomaschlapek.portfolio.util.PresenterLoader;

import timber.log.Timber;

public abstract class BaseActivity<P extends BasePresenter<V>, V> extends AppCompatActivity {

  private BasePresenter<V> presenter;

  /**
   * Monitors memory callbacks.
   */
  private MemoryBoss mMemoryBoss = new MemoryBoss();

  /**
   * Gets newly created presenter factory for making new presenter.
   *
   * @return Presenter factory.
   */
  protected abstract PresenterFactory<P> getPresenterFactory();

  /**
   * Notifies about status of prepared presenter.
   *
   * @param presenter Presenter.
   */
  protected abstract void onPresenterPrepared(P presenter);

  /**
   * Notifies about status of destroyed presenter.
   */
  protected void onPresenterDestroyed() {
    // hook for subclasses
  }

  // Override in case of Activity not implementing Presenter<Vista> interface
  protected V getPresenterView() {
    return (V) this;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Lock to portrait if not dual-pane.
    //    if (!AndroidApplication.isDualPane()) {
    //      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    //    }

    // Register memory callbacks.
    registerComponentCallbacks(mMemoryBoss);

    getSupportLoaderManager().initLoader(getPageId(), null, new LoaderManager.LoaderCallbacks<P>() {
      @Override
      public final Loader<P> onCreateLoader(int id, Bundle args) {
        Timber.i("onCreateLoader");
        return new PresenterLoader<>(BaseActivity.this, getPresenterFactory(), getPageTitle());
      }

      @Override
      public final void onLoadFinished(Loader<P> loader, P presenter) {
        Timber.i("onLoadFinished");
        BaseActivity.this.presenter = presenter;
        onPresenterPrepared(presenter);
      }

      @Override
      public final void onLoaderReset(Loader<P> loader) {
        Timber.i("onLoaderReset");
        BaseActivity.this.presenter = null;
        onPresenterDestroyed();
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    presenter.onViewAttached(getPresenterView());
  }

  @Override
  protected void onStop() {
    presenter.onViewDetached();
    super.onStop();
  }

  @Override
  protected void onPause() {
    super.onPause();

    // Hide soft keyboard.
    hideKeyboard();

    // TODO Cancel all ongoing image requests.
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    // Unregister memory callbacks.
    unregisterComponentCallbacks(mMemoryBoss);
  }

  /**
   * @see FragmentActivity#onBackPressed()
   */
  @Override
  public void onBackPressed() {
    AndroidApplication.getAppComponent().provideNavigator().back();
  }

  @Override
  protected void onResume() {
    super.onResume();

    // Detect application coming from background.
    if (AndroidApplication.isInBackground()) {
      AndroidApplication.setInBackground(false);
      onApplicationForeground();
    }

    // Change context in navigation manager.
    AndroidApplication.getAppComponent().provideNavigator().setCurrentActivity(this);
  }

  /**
   * @see FragmentActivity#onLowMemory()
   */
  @Override
  public void onLowMemory() {
    super.onLowMemory();

    Timber.i("onLowMemory()");
    // TODO Stop loading images.
  }

  /**
   * Shows the keyboard.
   */
  public void showKeyboard(final View view) {
    InputMethodManager manager = (InputMethodManager)
      getSystemService(Context.INPUT_METHOD_SERVICE);
    manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
  }

  /**
   * Hides the keyboard, if shown.
   */
  public void hideKeyboard() {
    InputMethodManager manager = (InputMethodManager)
      getSystemService(Context.INPUT_METHOD_SERVICE);
    View currentFocus = getCurrentFocus();
    if (currentFocus != null) {
      manager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }
  }

  /**
   * Gets title of page according to class name.
   */
  public String getPageTitle() {
    return getClass().getName();
  }

  /**
   * Gets ID of page according to class name.
   *
   * @return Page ID.
   */
  public int getPageId() {
    return MiscMethods.stringToInt(getPageTitle());
  }

  /**
   * Method called when whole application is going to foreground.
   */
  protected void onApplicationForeground() {
    Timber.d("onApplicationForeground()");

    // TODO Do some logging stuff
  }

  /**
   * Method called when whole application is going to background.
   */
  protected void onApplicationBackground() {
    Timber.d("onApplicationBackground()");

    // TODO Do some logging stuff
  }


    /* Nested Classes *******************************************************************************/

  /**
   * Listens to message about UI no longer being visible.
   */
  private class MemoryBoss implements ComponentCallbacks2 {
    @Override
    public void onTrimMemory(int level) {
      if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
        AndroidApplication.setInBackground(true);
        onApplicationBackground();
      }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    @Override
    public void onLowMemory() {
    }
  }
}
