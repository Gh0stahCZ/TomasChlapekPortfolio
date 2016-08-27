package com.tomaschlapek.portfolio.presentation.ui.adapters.viewholder;

import android.databinding.ViewDataBinding;
import android.widget.ImageView;

import com.google.auto.factory.AutoFactory;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.databinding.ItemPortfolioListAdapterExtendedBinding;
import com.tomaschlapek.portfolio.factory.portfolio.PortfolioListViewHolderFactory;
import com.tomaschlapek.portfolio.helper.ImageHelper;
import com.tomaschlapek.portfolio.network.model.Project;

import butterknife.OnClick;

/**
 * Created by tomaschlapek on 4/8/16.
 */
@AutoFactory(implementing = PortfolioListViewHolderFactory.class)
public class ProjectViewHolderExtended extends ProjectViewHolder {

  private boolean isFavourite;
  private ItemPortfolioListAdapterExtendedBinding mBinding;

  @OnClick(R.id.item_favourite_icon)
  public void onFavouriteIconClick(ImageView view) {
    int favouriteIconResId =
      (isFavourite) ? R.drawable.ic_favorite_border_black_48dp : R.drawable.ic_favorite_black_48dp;
    view.setImageResource(favouriteIconResId);
    isFavourite = !isFavourite;
  }

  public ProjectViewHolderExtended(ViewDataBinding binding) {
    super(binding.getRoot());
    mBinding = (ItemPortfolioListAdapterExtendedBinding) binding;
    isFavourite = false;
  }

  @Override
  public void bind(Project project) {
    //    mBinding.itemProjectNameTextView.setText(project.getProjectTitle());
    //    mBinding.itemProjectDescTextView.setText(project.getProjectDescription());

    mBinding.setDinosaur(project);
  }

  public static void processLogoIcon(ImageView view, String logoUrl) {
    ImageHelper.processLogoIcon(view, logoUrl, -1);
  }
}
