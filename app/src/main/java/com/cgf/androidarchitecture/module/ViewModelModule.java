package com.cgf.androidarchitecture.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cgf.androidarchitecture.viewModel.GithubViewModelFactory;
import com.cgf.androidarchitecture.viewModel.UserProfileViewModel;
import com.cgf.androidarchitecture.viewModel.ViewModelBookSearch;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    abstract ViewModel f1(UserProfileViewModel searchViewModel);

    @Binds
    abstract ViewModelProvider.Factory f2(GithubViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelBookSearch.class)
    abstract ViewModel f3(ViewModelBookSearch viewModel);
}
