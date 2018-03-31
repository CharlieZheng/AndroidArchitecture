package com.iousave.www.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.iousave.www.pojo.Book;
import com.iousave.www.repository.RepositoryBook;
import com.iousave.www.vo.Resource;

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