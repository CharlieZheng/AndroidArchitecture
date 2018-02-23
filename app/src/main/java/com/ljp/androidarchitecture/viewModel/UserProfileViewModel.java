package com.ljp.androidarchitecture.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ljp.androidarchitecture.pojo.User;
import com.ljp.androidarchitecture.repository.UserRepository;

import javax.inject.Inject;

public class UserProfileViewModel extends ViewModel {


    private LiveData<User> user;
    private UserRepository userRepository;

    @Inject
    public UserProfileViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init(String userId) {
        if (this.user != null) return;
        user = userRepository.getUser(userId);
    }

    public LiveData<User> getUser() {
        return user;
    }
}