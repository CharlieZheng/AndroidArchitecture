package com.cgf.androidarchitecture.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgf.androidarchitecture.R;
import com.cgf.androidarchitecture.adapter.AdapterBook;
import com.cgf.androidarchitecture.pojo.Book;
import com.cgf.androidarchitecture.viewModel.ViewModelBookSearch;
import com.cgf.androidarchitecture.vo.Resource;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class FragmentBookSearch extends Fragment implements Injectable {
    private List<Book> bookList = new ArrayList<>();
    private AdapterBook<Book> adapter;

    private static class MyHandler extends Handler {
        private WeakReference<FragmentBookSearch> ref;

        private MyHandler(FragmentBookSearch fragment) {
            ref = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.obj instanceof Resource) {
                Resource resource = (Resource) msg.obj;
                if (resource.data instanceof Book[]) {
                    ref.get().bookList.clear();

                    List<? extends Book> bookCollection = Arrays.asList((Book[]) resource.data);
                    ref.get().bookList.addAll(bookCollection);

//                    resource.data
                    ref.get().adapter.notifyDataSetChanged();
                }
            }
        }
    }

    public static final String UID_KEY = "uid";
    private ViewModelBookSearch viewModel;

    private MyHandler myHandler = new MyHandler(this);


    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        String tag = Thread.currentThread().getStackTrace()[2].getMethodName();
        Log.v(tag, tag);
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewModelBookSearch.class);
        viewModel.init("yellow", 0, 20);
        viewModel.getBook().observe(FragmentBookSearch.this, book -> {
            Message msg = new Message();
            msg.obj = book;
            myHandler.sendMessage(msg);
        });

    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) return null;
        View root = inflater.inflate(R.layout.fragment_book_search, container, false);
        adapter = new AdapterBook<Book>(getContext(), bookList);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}
