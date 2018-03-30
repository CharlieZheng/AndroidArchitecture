package com.cgf.androidarchitecture.module;

import com.cgf.androidarchitecture.fragments.FragmentBookSearch;
import com.cgf.androidarchitecture.fragments.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentModule {
    @ContributesAndroidInjector
    abstract UserProfileFragment f1();

    @ContributesAndroidInjector
    abstract FragmentBookSearch f2();

}
