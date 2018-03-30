package com.cgf.androidarchitecture.module;

import com.cgf.androidarchitecture.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = MainActivityFragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
