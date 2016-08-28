package com.tomaschlapek.portfolio.core.components;

import com.tomaschlapek.portfolio.core.modules.CvInfoModule;
import com.tomaschlapek.portfolio.core.scopes.PerActivity;

import dagger.Subcomponent;

/**
 * Created by tomaschlapek on 6/7/16.
 */
@PerActivity
@Subcomponent(modules = CvInfoModule.class)
public interface CvInfoComponent {

//  CvInfoRepository provideCvInfoRepository();
//
//  CvInfoRepository provideCvInfoRepository();

}
