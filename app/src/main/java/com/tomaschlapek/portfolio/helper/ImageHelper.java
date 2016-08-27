package com.tomaschlapek.portfolio.helper;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tomaschlapek.portfolio.R;

/**
 * Created by tomaschlapek on 7/8/16.
 */
public class ImageHelper {

  /**
   * Loads image from url (program approach).
   *
   * @param view Vista to put image.
   * @param url URL of image.
   * @param errorImageResId Resource ID of error image.
   */
  public static void processLogoIcon(ImageView view, String url, int errorImageResId) {

    Picasso.with(view.getContext())
      .load(url)
      .resize(((int) view.getContext().getResources().getDimension(R.dimen.icon_width)),
        ((int) view.getContext().getResources().getDimension(R.dimen.icon_height)))
      .centerCrop()
      .error(errorImageResId)
      .into(view);
  }

  /**
   * Loads image from url (data binding approach).
   *
   * @param view Vista to put image.
   * @param url URL of image.
   * @param errorImage Error image drawable.
   */
  @BindingAdapter({ "bind:logoUrl", "bind:errorImage" })
  public static void processLogoIcon(ImageView view, String url, Drawable errorImage) {

    Picasso.with(view.getContext())
      .load(url)
      .resize(((int) view.getContext().getResources().getDimension(R.dimen.icon_width)),
        ((int) view.getContext().getResources().getDimension(R.dimen.icon_height)))
      .centerCrop()
      .error(errorImage)
      .into(view);
  }
}
