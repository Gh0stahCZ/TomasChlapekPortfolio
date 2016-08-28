package com.tomaschlapek.portfolio.presentation.ui.adapters;

import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.databinding.ItemPortfolioListAdapterExtendedBinding;
import com.tomaschlapek.portfolio.domain.model.ProjectType;
import com.tomaschlapek.portfolio.network.model.Project;
import com.tomaschlapek.portfolio.presentation.ui.adapters.viewholder.ProjectViewHolder;
import com.tomaschlapek.portfolio.presentation.ui.adapters.viewholder.ProjectViewHolderExtended;
import com.tomaschlapek.portfolio.presentation.ui.adapters.viewholder.ProjectViewHolderExtendedFactory;
import com.tomaschlapek.portfolio.presentation.ui.fragments.PortfolioListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public class PortfolioListAdapter extends RecyclerView.Adapter {

  private PortfolioListFragment mPortfolioListFragment;

  private Map<Integer, ProjectViewHolderExtendedFactory> mViewHolderFactories;

  private final List<Project> mProjectList = new ArrayList<>();

  public PortfolioListAdapter(PortfolioListFragment portfolioFragment,
    Map<Integer, ProjectViewHolderExtendedFactory> viewHolderFactories) {
    mPortfolioListFragment = portfolioFragment;
    mViewHolderFactories = viewHolderFactories;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final RecyclerView.ViewHolder viewHolder;

    ViewDataBinding binding;

    if (viewType == ProjectType.NORMAL) {
      binding = ItemPortfolioListAdapterExtendedBinding
        .inflate(LayoutInflater.from(parent.getContext()), parent, false);

      viewHolder = mViewHolderFactories.get(viewType).createViewHolder(binding);
      viewHolder.itemView
        .setOnClickListener((v) -> onRepositoryItemClicked(viewHolder.getAdapterPosition(),
          ((ItemPortfolioListAdapterExtendedBinding) binding).itemProjectIcon));
    } else if (viewType == ProjectType.SMALL) {
      //      binding = ItemPortfolioListAdapterExtendedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
      return null;
    } else if (viewType == ProjectType.BIG) {
      //      binding = ItemPortfolioListAdapterExtendedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
      return null;
    } else {
      return null;
    }



    return viewHolder;
  }

  private void onRepositoryItemClicked(int adapterPosition, View viewForTransition) {
    mPortfolioListFragment.onProjectClick(mProjectList.get(adapterPosition), viewForTransition);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((ProjectViewHolder) holder).bind(mProjectList.get(position));

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      ((ProjectViewHolderExtended) holder).getBinding().itemProjectIcon.setTransitionName(
        AndroidApplication.getAppComponent().provideContext().getResources().getString(
          R.string.transition_project_logo) + "_" + position );
    }
  }

  @Override
  public int getItemCount() {
    return mProjectList.size();
  }

  @Override
  public int getItemViewType(int position) {
    Project project = mProjectList.get(position);
    return ProjectType.NORMAL;
    //    if (repository.stargazers_count > 500) {
    //      if (repository.forks_count > 100) {
    //        return Repository.TYPE_FEATURED;
    //      }
    //      return Repository.TYPE_BIG;
    //    }
    //    return Repository.TYPE_NORMAL;
  }

  public void updatePortfolioList(List<Project> projectList) {
    this.mProjectList.clear();
    this.mProjectList.addAll(projectList);
    notifyDataSetChanged();
  }

  public void clearPortfolioList() {
    this.mProjectList.clear();
    notifyDataSetChanged();
  }
}
