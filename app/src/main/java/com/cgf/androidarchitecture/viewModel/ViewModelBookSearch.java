package com.cgf.androidarchitecture.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cgf.androidarchitecture.pojo.Book;
import com.cgf.androidarchitecture.repository.RepositoryBook;
import com.cgf.androidarchitecture.vo.Resource;

import javax.inject.Inject;

public class ViewModelBookSearch extends ViewModel {


    private LiveData<Resource<Book[]>> bookList;
    private RepositoryBook repositoryBook;


    @Inject
    public ViewModelBookSearch(RepositoryBook repositoryBook) {
        this.repositoryBook = repositoryBook;
    }

    public void init(String q, int start, int count) {
        if (this.bookList != null) return;
        bookList = repositoryBook.getBookList(q, start, count);
    }

    public LiveData<Resource<Book[]>> getBook() {
        return bookList;
    }

}