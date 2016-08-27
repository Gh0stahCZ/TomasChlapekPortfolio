package com.tomaschlapek.portfolio.factory.portfolio;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tomaschlapek on 4/8/16.
 */
public interface PortfolioDetailViewHolderFactory {
  RecyclerView.ViewHolder createViewHolder(ViewDataBinding parent);
}
