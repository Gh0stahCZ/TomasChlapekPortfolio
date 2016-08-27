package com.tomaschlapek.portfolio.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.navigation.Navigator;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;
import com.tomaschlapek.portfolio.util.ToolbarDelegate;

import javax.inject.Inject;

/**
 * Created by tomaschlapek on 21/7/16.
 */
public class DrawerActivity extends BaseActivity {

  /* Private Attributes ***************************************************************************/

  @Inject
  ToolbarDelegate mToolbarDelegate;

  Toolbar mToolbar;

  @Inject
  Navigator mNavigator;

  protected DrawerLayout mDrawerLayout;

  protected NavigationView mNavigationView;


  /**
   * Indicates if the navigation drawer is opened.
   */
  private boolean mDrawerOpened;

  /**
   * Indicates if the navigation drawer is locked.
   */
  private boolean mDrawerLocked;

  /**
   * Extra identifiers.
   */
  public static class Extra {
    public static final String DRAWER_OPENED = "drawer_opened";
    public static final String DRAWER_LOCKED = "drawer_locked";
  }

    /* Protected Methods ***************************************************************************/

  @Override
  protected PresenterFactory getPresenterFactory() {
    return null;
  }

  @Override
  protected void onPresenterPrepared(BasePresenter presenter) {

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    // It can not be instanced by Butter Knife
    mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
    mNavigationView = (NavigationView) findViewById(R.id.main_navigation);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);

    // Load or initialize attributes.
    if (savedInstanceState != null) {
      mDrawerOpened = savedInstanceState.getBoolean(Extra.DRAWER_OPENED, false);
      mDrawerLocked = savedInstanceState.getBoolean(Extra.DRAWER_LOCKED, false);
    }

    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

    ((AndroidApplication) getApplication()).getAppComponent().inject(this);

    setupToolbar();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    // Setup drawer views.
    setupViews();

    // Refresh with current data.
    setDrawerOpened(mDrawerOpened);
    setDrawerLocked(mDrawerLocked);
  }

  /**
   * @see FragmentActivity#onSaveInstanceState(Bundle)
   */
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putBoolean(Extra.DRAWER_OPENED, mDrawerOpened);
    outState.putBoolean(Extra.DRAWER_LOCKED, mDrawerLocked);
  }

    /* Public Methods ***************************************************************************/

  /**
   * Indicates whether this activity has drawer.
   */
  public boolean hasDrawer() {
    return (mDrawerLayout != null);
  }

  /**
   * Indicates whether the drawer is opened.
   */
  public boolean isDrawerOpened() {
    return mDrawerOpened;
  }

  /**
   * Opens or closes the navigation drawer.
   *
   * @param opened Whether the drawer should be opened.
   */
  public void setDrawerOpened(boolean opened) {
    mDrawerOpened = opened;
    if (hasDrawer() && (mDrawerOpened != mDrawerLayout.isDrawerOpen(GravityCompat.END))) {
      if (mDrawerOpened) {
        mDrawerLayout.openDrawer(GravityCompat.END);
      } else {
        mDrawerLayout.closeDrawer(GravityCompat.END);
      }
    }
  }

  /**
   * Indicates whether the drawer is locked.
   */
  public boolean isDrawerLocked() {
    return mDrawerLocked;
  }

  /**
   * Locks or unlocks the navigation drawer.
   *
   * @param locked Whether the drawer should be locked.
   */
  public void setDrawerLocked(boolean locked) {
    mDrawerLocked = locked;
    if (hasDrawer()) {
      mDrawerLayout.setDrawerLockMode(mDrawerLocked
        ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        : DrawerLayout.LOCK_MODE_UNLOCKED);
    }
  }

  /* Private Methods ******************************************************************************/

  /**
   * Sets up drawer views and their listeners.
   */
  private void setupViews() {

    // This activity has no drawer, get out!
    if (!hasDrawer()) {
      return;
    }

    mNavigationView.setNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.menu_item_portfolio:
          mToolbarDelegate.setTitle(R.string.drawer_item_portfolio);
          mNavigator.createAndAddPortfolioListFragment(this);
          break;
        case R.id.menu_item_cv:
          mToolbarDelegate.setTitle(R.string.drawer_item_cv);
          mNavigator.createAndAddCvInfoFragment(this);
          break;
        case R.id.menu_item_blog:
          mToolbarDelegate.setTitle(R.string.drawer_item_blog);
          mNavigator.createAndAddBlogListFragment(this);
          break;
        case R.id.menu_item_contact:
          mToolbarDelegate.setTitle(R.string.drawer_item_contact);
          mNavigator.createAndAddContactInfoFragment(this);
          break;
        default:
          throw new IllegalStateException("Unknown navigation item: " + item.getTitle());
      }

      item.setChecked(true);
      mDrawerLayout.closeDrawers();
      return true;
    });
  }

  /**
   * Setup mToolbar delegate.
   */
  private void setupToolbar() {
    mToolbarDelegate.setToolbar(mToolbar);
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }
  }

  public void setToolbarTitle(String title) {
    mToolbarDelegate.setTitle(title);
  }
}
