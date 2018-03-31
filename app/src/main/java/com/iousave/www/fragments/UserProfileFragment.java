package com.iousave.www.fragments;

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

import com.iousave.www.R;
import com.iousave.www.pojo.Book;
import com.iousave.www.viewModel.UserProfileViewModel;
import com.iousave.www.vo.Resource;

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
            if (msg.obj instanceof Resource) {
                Resource resource = (Resource) msg.obj;
                if (resource.data instanceof Book[]) {

                    Book[] book = (Book[]) resource.data;
                    if (book.length > 0) ref.get().appName.setText(book[0].getImages().getLarge());
                }
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
        viewModel.init(userId);
        viewModel.getBook().observe(UserProfileFragment.this, book -> {
            Message msg = new Message();
            msg.obj = book;
            myHandler.sendMessage(msg);
        });

    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) return null;
        View root = inflater.inflate(R.layout.user_profile_layout, container, false);
        appName = (TextView) root.findViewById(R.id.appName);
        root.findViewById(R.id.changeDataInDatabase).setOnClickListener(v -> viewModel.changeDataInDatabase());
        return root;
    }
}
