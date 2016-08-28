package com.tomaschlapek.portfolio.core.components;

import com.tomaschlapek.portfolio.core.modules.ContactInfoModule;
import com.tomaschlapek.portfolio.core.scopes.PerActivity;

import dagger.Subcomponent;

/**
 * Created by tomaschlapek on 6/7/16.
 */
@PerActivity
@Subcomponent(modules = ContactInfoModule.class)
public interface ContactInfoComponent {

//  ContactInfoRepository provideContactInfoRepository();

}
