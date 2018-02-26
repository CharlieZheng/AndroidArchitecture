package com.ljp.androidarchitecture.component;

import com.ljp.androidarchitecture.viewModel.UserProfileViewModel;

import dagger.Component;

/**
 * @author hanrong
 */
@Component
public interface UserProfileViewModelComponent {
    void inject(UserProfileViewModel userProfileViewModel);
}
