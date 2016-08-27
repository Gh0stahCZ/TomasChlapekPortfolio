package com.tomaschlapek.portfolio.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;

import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public class BlogEntryFragment extends BaseFragment {

  /* Public Attributes ****************************************************************************/


  /* Public Methods ****************************************************************************/

  @Override
  protected PresenterFactory getPresenterFactory() {
    return null;
  }

  @Override
  protected void onPresenterPrepared(BasePresenter presenter) {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public String getToolbarTitle() {
    return getPageTitle();
  }

  public static BlogEntryFragment newInstance() {

    Bundle args = new Bundle();

    BlogEntryFragment fragment = new BlogEntryFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_blog_entry, container, false);
    ButterKnife.bind(this, view);

    return view;
  }
}
