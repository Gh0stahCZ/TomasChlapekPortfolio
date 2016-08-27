package com.tomaschlapek.portfolio.util;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.tomaschlapek.portfolio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tomaschlapek on 4/8/16.
 */
public class ToolbarDelegate {

  @Nullable
  @BindView(R.id.title)
  TextView mTitle;
  @Nullable
  @BindView(R.id.icon)
  ImageView mIcon;

  @Nullable
  private Toolbar mToolbar;

  public void setToolbar(@Nullable Toolbar toolbar) {
    mToolbar = toolbar;

    if (mToolbar != null) {
      ButterKnife.bind(this, mToolbar);
    }
  }

  public void setTitle(@Nullable String title) {
    setTitle(title, R.color.white);
  }

  @SuppressWarnings("ConstantConditions")
  public void setTitle(@Nullable String title, int colorResId) {
    checkToolbarNotNull();
    checkTitleViewNotNull();
    if (title == null) {
      mTitle.setText("");
    } else {
      mTitle.setText(title);
      mTitle.setTextColor(ContextCompat.getColor(mToolbar.getContext(), colorResId));
    }
  }

  public void setTitle(int titleResId) {
    setTitle(titleResId, R.color.white);
  }

  @SuppressWarnings("ConstantConditions")
  public void setTitle(int titleResId, int colorResId) {
    checkToolbarNotNull();
    setTitle(mToolbar.getContext().getString(titleResId), colorResId);
  }

  @SuppressWarnings("ConstantConditions")
  public void setIcon(@DrawableRes int drawableResId) {
    checkToolbarNotNull();
    checkImageViewNotNull();
    mIcon.setImageResource(drawableResId);
  }

  private void checkImageViewNotNull() {
    if (mIcon == null) {
      throw new NullPointerException(
        "Trying to set the Toolbar icon but no ImageView could be found. "
          + "Make sure to include an ImageView in your toolbar layout with the id R.id.icon");
    }
  }

  private void checkTitleViewNotNull() {
    if (mTitle == null) {
      throw new NullPointerException(
        "Trying to set the title of the Toolbar but no TextView could be found. "
          + "Make sure to include a TextView in your toolbar layout with the id R.id.title");
    }
  }

  private void checkToolbarNotNull() {
    if (mToolbar == null) {
      throw new NullPointerException(
        "Trying to set attributes in the Toolbar when it was never defined. "
          + "Make sure to include a Toolbar in your layout with the id R.id.toolbar");
    }
  }
}
