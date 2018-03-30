package com.ljp.androidarchitecture.module;

import com.ljp.androidarchitecture.fragments.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentModule {
    @ContributesAndroidInjector
    abstract UserProfileFragment f1();
    /*@ContributesAndroidInjector
    abstract FragmentBookSearch f2();*/

}
