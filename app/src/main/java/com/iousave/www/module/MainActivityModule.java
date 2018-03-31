package com.iousave.www.module;

import com.iousave.www.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = MainActivityFragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
