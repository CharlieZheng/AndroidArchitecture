package com.iousave.www.module;

import com.iousave.www.fragments.FragmentBookSearch;
import com.iousave.www.fragments.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentModule {
    @ContributesAndroidInjector
    abstract UserProfileFragment f1();

    @ContributesAndroidInjector
    abstract FragmentBookSearch f2();

}
