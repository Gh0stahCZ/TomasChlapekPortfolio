package com.tomaschlapek.portfolio.presentation.ui.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tomaschlapek.portfolio.network.model.Project;

/**
 * Created by tomaschlapek on 3/8/16.
 */
public abstract class ProjectViewHolder extends RecyclerView.ViewHolder {
  public ProjectViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(Project repository);
}
