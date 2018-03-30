package com.cgf.androidarchitecture.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cgf.androidarchitecture.pojo.Book;
import com.cgf.androidarchitecture.repository.RepositoryBook;
import com.cgf.androidarchitecture.vo.Resource;

import javax.inject.Inject;

public class UserProfileViewModel extends ViewModel {


    private LiveData<Resource<Book[]>> book;
    private RepositoryBook repositoryBook;


    @Inject
    public UserProfileViewModel(RepositoryBook repositoryBook) {
        this.repositoryBook = repositoryBook;
    }

    public void init(String bookId) {
        if (this.book != null) return;
        book = repositoryBook.getBook("");
    }

    public LiveData<Resource<Book[]>> getBook() {
        return book;
    }

    public void changeDataInDatabase() {
        repositoryBook.changeDataInDatabase();
    }
}