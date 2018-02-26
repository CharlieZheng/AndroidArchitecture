package com.ljp.androidarchitecture.component;

import com.ljp.androidarchitecture.viewModel.UserProfileViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author hanrong
 */
@Component
@Singleton
public interface UserProfileViewModelComponent {
    void inject(UserProfileViewModel userProfileViewModel);
}
