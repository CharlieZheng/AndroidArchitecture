package com.ljp.androidarchitecture.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.ljp.androidarchitecture.viewModel.GithubViewModelFactory;
import com.ljp.androidarchitecture.viewModel.UserProfileViewModel;

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
    /*@Binds
    @IntoMap
    @ViewModelKey(ViewModelBookSearch.class)
    abstract ViewModel f3(ViewModelBookSearch viewModel);*/
}
