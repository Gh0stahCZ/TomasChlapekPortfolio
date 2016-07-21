package com.tomaschlapek.portfolio.presentation.ui.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.tomaschlapek.portfolio.R;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by tomaschlapek on 21/7/16.
 */
public class DrawerActivity extends BaseActivity {

  /* Private Attributes ***************************************************************************/

  protected DrawerLayout mDrawerLayout;

  protected NavigationView mNavigationView;

  protected ViewGroup mMainContainer;

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
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.drawer_activity_layout);

    mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
    mNavigationView = (NavigationView) findViewById(R.id.main_navigation);
    mMainContainer = (ViewGroup) findViewById(R.id.main_content);

    // Load or initialize attributes.
    if (savedInstanceState != null) {
      mDrawerOpened = savedInstanceState.getBoolean(Extra.DRAWER_OPENED, false);
      mDrawerLocked = savedInstanceState.getBoolean(Extra.DRAWER_LOCKED, false);
    }

    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
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
          Toast.makeText(DrawerActivity.this, "Portfolio!", LENGTH_SHORT).show();
          break;
        case R.id.menu_item_cv:
          Toast.makeText(DrawerActivity.this, "CV!", LENGTH_SHORT).show();
          break;
        case R.id.menu_item_blog:
          Toast.makeText(DrawerActivity.this, "Blog!", LENGTH_SHORT).show();
          break;
        case R.id.menu_item_contact:
          Toast.makeText(DrawerActivity.this, "Contact!", LENGTH_SHORT).show();
          break;
        default:
          throw new IllegalStateException("Unknown navigation item: " + item.getTitle());
      }

      item.setChecked(true);
      mDrawerLayout.closeDrawers();
      return true;
    });
  }
}
