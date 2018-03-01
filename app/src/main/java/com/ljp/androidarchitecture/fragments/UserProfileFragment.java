package com.ljp.androidarchitecture.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljp.androidarchitecture.R;
import com.ljp.androidarchitecture.pojo.User;
import com.ljp.androidarchitecture.viewModel.UserProfileViewModel;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class UserProfileFragment extends Fragment implements Injectable {
    private static class MyHandler extends Handler {
        private WeakReference<UserProfileFragment> ref;

        private MyHandler(UserProfileFragment fragment) {
            ref = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.obj instanceof User) {
                User user = (User) msg.obj;
                if (user.appConfig != null && user.appConfig.changelog != null)
                    ref.get().appName.setText(user.appConfig.changelog);
            }
        }
    }

    public static final String UID_KEY = "uid";
    private UserProfileViewModel viewModel;

    TextView appName;
    private MyHandler myHandler = new MyHandler(this);

    @Override
    public void onAttach(Context context) {
        //AndroidSupportInjection.inject(this);
        super.onAttach(context);

    }

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        String tag = Thread.currentThread().getStackTrace()[2].getMethodName();
        Log.v(tag, tag);
        super.onActivityCreated(savedInstanceState);
        final String userId = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserProfileViewModel.class);
        //viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.init(userId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                viewModel.getUser().observe(UserProfileFragment.this, new Observer<User>() {
                    @Override
                    public void onChanged(@Nullable User user) {
                        Message msg = new Message();
                        msg.obj = user;
                        myHandler.sendMessage(msg);
                    }
                });
            }
        }).start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_profile_layout, container, false);
        appName = (TextView) root.findViewById(R.id.appName);
        return root;
    }
}
