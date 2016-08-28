package com.tomaschlapek.portfolio.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;

import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.factory.PresenterFactory;
import com.tomaschlapek.portfolio.factory.contactinfo.ContactInfoPresenterFactory;
import com.tomaschlapek.portfolio.presentation.presenters.ContactInfoPresenter;
import com.tomaschlapek.portfolio.presentation.presenters.ContactInfoPresenter.Vista;
import com.tomaschlapek.portfolio.presentation.presenters.base.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public class ContactInfoFragment extends BaseFragment implements Vista{

  ContactInfoPresenter mContactInfoPresenter;

  @Override
  protected PresenterFactory getPresenterFactory() {
    return new ContactInfoPresenterFactory();
  }

  @Override
  protected void onPresenterPrepared(BasePresenter presenter) {
    mContactInfoPresenter = (ContactInfoPresenter) presenter;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public String getToolbarTitle() {
    return getPageTitle();
  }

  public static ContactInfoFragment newInstance() {
    
    Bundle args = new Bundle();
    
    ContactInfoFragment fragment = new ContactInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
    ButterKnife.bind(this, view);

    return view;
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
  public void showError(int messageResId, int iconResId) {

  }
}
