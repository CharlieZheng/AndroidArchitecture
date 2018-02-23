package com.ljp.androidarchitecture.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljp.androidarchitecture.R;
import com.ljp.androidarchitecture.pojo.User;
import com.ljp.androidarchitecture.viewModel.UserProfileViewModel;

/**
 * @author hanrong
 */

public class UserProfileFragment extends Fragment {

    public static final String UID_KEY = "uid";
    private UserProfileViewModel viewModel;
    private View root;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String userId = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.init(userId);
        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                TextView appName = (TextView) root.findViewById(R.id.appName);
                appName.setText(user.toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.user_profile_layout, container, false);
        return root;
    }
}
