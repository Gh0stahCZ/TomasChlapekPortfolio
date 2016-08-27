package com.tomaschlapek.portfolio.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.network.model.Project;
import com.tomaschlapek.portfolio.presentation.ui.activities.BaseActivity;
import com.tomaschlapek.portfolio.presentation.ui.fragments.BlogEntryFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.BlogListFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.ContactInfoFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.CvInfoFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.PortfolioDetailFragment;
import com.tomaschlapek.portfolio.presentation.ui.fragments.PortfolioListFragment;

import timber.log.Timber;

/**
 * Supports navigation between activites and fragments.
 */
public class Navigator {

  /**
   * Time of manager initialization
   */
  private long mTime;

  private FragmentManager mFragmentManager;

  private NavigationListener mNavigationListener;

  Context mContext;

  /* Private Static Attributes ********************************************************************/

  /**
   * Holds reference to currently displayed activity.
   */
  private static BaseActivity sCurrentActivity;

  /**
   * Holds name of previous activity's title.
   */
  private static String sPreviousActivityTitle = "First run";

  /**
   * Holds reference to currently displayed portfolio list fragment.
   */
  private static PortfolioListFragment sCurrentPortfolioListFragment;

  /**
   * Holds reference to currently displayed portfolio detail fragment.
   */
  private static PortfolioDetailFragment sCurrentPortfolioDetailFragment;

  /**
   * Holds reference to currently displayed blog list detail fragment.
   */
  private static BlogListFragment sCurrentBlogListFragment;

  /**
   * Holds reference to currently displayed blog detail fragment.
   */
  private static BlogEntryFragment sCurrentBlogEntryFragment;

  /**
   * Holds reference to currently displayed contact fragment.
   */
  private static ContactInfoFragment sCurrentContactInfoFragment;

  /**
   * Holds reference to currently displayed CV fragment.
   */
  private static CvInfoFragment sCurrentCvInfoFragment;

  /**
   * Indicates if the back call is blocked.
   */
  private static boolean sBackBlocked;

  /* Constructor *********************************************************************************/

  public Navigator(Context context) {
    this.mTime = System.currentTimeMillis();
    mContext = context;
  }

  /* Public methods ******************************************************************************/

  public void init(FragmentManager fragmentManager) {
    mFragmentManager = fragmentManager;
    mFragmentManager.addOnBackStackChangedListener(
      () -> {
        if (mNavigationListener != null) {
          mNavigationListener.onBackstackChanged();
        }
      }
    );
  }

  public void back() {
    // We pressed the back button, show the logout dialog.
    if (mFragmentManager.getBackStackEntryCount() == 1) {
      // We have only one fragment left so we would close the application with this back
      showExitDialog();
    } else {
      AndroidApplication.getAppComponent().provideNavigator().navigateBack(sCurrentActivity);
    }
  }

  public PortfolioListFragment createAndAddPortfolioListFragment(FragmentActivity activity) {
    Timber.d("createAndAddPortfolioListFragment()");

    sCurrentPortfolioListFragment = PortfolioListFragment.newInstance();

    replaceFragment(beginTransaction(activity), R.id.master_fragment_container,
      sCurrentPortfolioListFragment, true);

    return sCurrentPortfolioListFragment;
  }

  public PortfolioDetailFragment createAndAddPortfolioDetailFragment(FragmentActivity activity,
    Project project, boolean addToBackStack) {
    Timber.d("createAndAddPortfolioDetailFragment()");

    sCurrentPortfolioDetailFragment = PortfolioDetailFragment.newInstance(project);

    replaceFragment(beginTransaction(activity), R.id.detail_fragment_container,
      sCurrentPortfolioDetailFragment, addToBackStack, R.anim.slide_in_right,
      R.anim.slide_out_right);

    return sCurrentPortfolioDetailFragment;
  }

  public BlogListFragment createAndAddBlogListFragment(FragmentActivity activity) {
    Timber.d("createAndAddBlogListFragment()");

    sCurrentBlogListFragment = BlogListFragment.newInstance();

    replaceFragment(beginTransaction(activity), R.id.master_fragment_container,
      sCurrentBlogListFragment, true);

    return sCurrentBlogListFragment;
  }

  public BlogEntryFragment createAndAddBlogDetailFragment(FragmentActivity activity,
    boolean addToBackStack) {
    Timber.d("createAndAddPortfolioDetailFragment()");

    sCurrentBlogEntryFragment = BlogEntryFragment.newInstance();

    replaceFragment(beginTransaction(activity), R.id.detail_fragment_container,
      sCurrentBlogEntryFragment, addToBackStack, R.anim.slide_in_right, R.anim.slide_out_right);

    return sCurrentBlogEntryFragment;
  }

  public CvInfoFragment createAndAddCvInfoFragment(FragmentActivity activity) {
    Timber.d("createAndAddCvInfoFragment()");

    sCurrentCvInfoFragment = CvInfoFragment.newInstance();

    replaceFragment(beginTransaction(activity), R.id.master_fragment_container,
      sCurrentCvInfoFragment, true);

    return sCurrentCvInfoFragment;
  }

  public ContactInfoFragment createAndAddContactInfoFragment(FragmentActivity activity) {
    Timber.d("createAndAddContactInfoFragment()");

    sCurrentContactInfoFragment = ContactInfoFragment.newInstance();

    replaceFragment(beginTransaction(activity), R.id.master_fragment_container,
      sCurrentContactInfoFragment, true);

    return sCurrentContactInfoFragment;
  }


  /**
   * Pops all the queued fragments.
   */
  private void popEveryFragment() {
    // Clear all back stack.
    int backStackCount = mFragmentManager.getBackStackEntryCount();
    for (int i = 0; i < backStackCount; i++) {

      // Get the back stack fragment id.
      int backStackId = mFragmentManager.getBackStackEntryAt(i).getId();

      mFragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  /**
   * Navigates back by popping teh back stack. If there is no more items left we finish the current
   * activity.
   *
   * @param baseActivity
   */
  public void navigateBack(Activity baseActivity) {

    if (mFragmentManager.getBackStackEntryCount() == 0) {
      // We can finish the base activity since we have no other fragments.
      baseActivity.finish();
    } else {
      mFragmentManager.popBackStackImmediate();
    }
  }

  /**
   * Shows the logout dialog. Stops the service and finishes the application.
   */
  protected void showExitDialog() {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(sCurrentActivity);
    alertDialogBuilder.setMessage(R.string.exit_message).setCancelable(false)
      .setPositiveButton(android.R.string.yes,
        (dialog, id) -> sCurrentActivity.finish())
      .setNegativeButton(android.R.string.cancel, null);
    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }

  /* Getters / Setters ***************************************************************************/

  public NavigationListener getNavigationListener() {
    return mNavigationListener;
  }

  public void setNavigationListener(
    NavigationListener navigationListener) {
    mNavigationListener = navigationListener;
  }

  /**
   * Returns current activity context.
   *
   * @return Currently displayed activity.
   */
  public static BaseActivity getCurrentActivity() {
    return sCurrentActivity;
  }

  /**
   * Sets current activity context.
   *
   * @param currentActivity Currently displayed activity.
   */
  public static void setCurrentActivity(
    BaseActivity currentActivity) {
    sCurrentActivity = currentActivity;
  }

  /**
   * Returns previous activity's title.
   *
   * @return Previously displayed activity.
   */
  public static String getPreviousActivityTitle() {
    return sPreviousActivityTitle;
  }

  /**
   * Sets previous activity's title.
   *
   * @param previousActivityTitle Previously displayed activity.
   */
  public static void setPreviousActivityTitle(String previousActivityTitle) {
    sPreviousActivityTitle = previousActivityTitle;
  }

  /**
   * Informs navigation manager that new activity has been opened.
   */
  public static void onNewActivity() {
    if (sCurrentActivity != null) {
      sPreviousActivityTitle = sCurrentActivity.getPageTitle();
    }
  }

  /**
   * Clears references to opened fragments and dialogs.
   * Should be called on application reinitialization.
   */
  public void clear() {
    Timber.d("clear()");

    sCurrentActivity = null;
    sCurrentPortfolioListFragment = null;
    sCurrentPortfolioDetailFragment = null;
    sCurrentCvInfoFragment = null;
    sCurrentBlogEntryFragment = null;
    sCurrentBlogListFragment = null;
    sCurrentContactInfoFragment = null;
  }

  /* Private Static Methods ***********************************************************************/

  /**
   * Pops fragment from back stack of current activity.
   *
   * @return True if fragment has been removed.
   */
  private boolean popFragmentFromBackStack() {
    Timber.d("popFragmentFromBackStack()");

    if (sCurrentActivity == null) {
      return false;
    }

    // Attempt to remove fragment from back stack.
    FragmentManager manager = sCurrentActivity.getSupportFragmentManager();
    manager.executePendingTransactions();
    return !manager.isDestroyed() && manager.popBackStackImmediate();
  }

  /**
   * Replaces fragment in certain container.
   */
  private void replaceFragment(FragmentTransaction transaction,
    int containerId, Fragment fragment, boolean addToBackStack) {

    replaceFragment(transaction, containerId, fragment, addToBackStack, -1, -1);
  }

  /**
   * Replaces fragment in certain container with custom animation.
   */
  private void replaceFragment(FragmentTransaction transaction,
    int containerId, Fragment fragment, boolean addToBackStack, int enterAnim, int exitAnim) {

    // Set transaction animations if passed.
    if ((enterAnim >= 0) && (exitAnim >= 0)) {
      transaction.setCustomAnimations(enterAnim, R.anim.no_anim, R.anim.no_anim, exitAnim);
    }

    // Replace fragment in container.
    transaction.replace(containerId, fragment);

    // Add to back-stack.
    if (addToBackStack) {
      transaction.addToBackStack(null);
    }

    transaction.commitAllowingStateLoss();
  }

  @SuppressLint("CommitTransaction")
  private FragmentTransaction beginTransaction(FragmentActivity activity) {
    return activity.getSupportFragmentManager().beginTransaction();
  }

  @SuppressLint("CommitTransaction")
  private FragmentTransaction beginTransaction(Fragment fragment) {
    return fragment.getFragmentManager().beginTransaction();
  }

  /* Public types ********************************************************************************/

  /**
   * Listener interface for navigation events.
   */
  public interface NavigationListener {

    /**
     * Callback on backstack changed.
     */
    void onBackstackChanged();
  }

}
