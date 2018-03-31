package com.iousave.www.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.iousave.www.BuildConfig;
import com.iousave.www.api.ApiResponse;
import com.iousave.www.backend.Webservice;
import com.iousave.www.pojo.Book;
import com.iousave.www.pojo.BookCache;
import com.iousave.www.pojo.BookList;
import com.iousave.www.repository.dataSource.db.AppDatabase;
import com.iousave.www.util.LiveDataCallAdapterFactory;
import com.iousave.www.vo.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RepositoryBook {
    private Webservice webservice;

    private static class MyLogger1 implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            if (BuildConfig.DEBUG) Log.i("HTTP_LOG", message);
        }
    }

    private BookCache bookCache;

    private AppDatabase db;

    private AppExecutors appExecutors;

    @Inject
    public RepositoryBook(AppDatabase db, AppExecutors appExecutors) {
        this.db = db;
        this.appExecutors = appExecutors;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new MyLogger1());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com")
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        webservice = retrofit.create(Webservice.class);
        bookCache = new BookCache();
    }

    public LiveData<Resource<Book[]>> getBook(String bookId) {

        return new NetworkBoundResource<Book[], Book>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull Book item) {
                db.daoBook().insertBooks(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Book[] data) {
//                return data == null;
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Book[]> loadFromDb() {
                return db.daoBook().loadAllBooks();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Book>> createCall() {
                return webservice.loadConfig();
            }
        }.asLiveData();
    }

    public LiveData<Resource<Book[]>> getBookList(String q, int start, int count) {

        return new NetworkBoundResource<Book[], BookList>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull BookList item) {
                db.daoBook().insertBooks(item.getBooks());
            }

            @Override
            protected boolean shouldFetch(@Nullable Book[] data) {
                //                return data == null;
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Book[]> loadFromDb() {
                return db.daoBook().bookSearch();
//                 return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<BookList>> createCall() {
                return webservice.bookSearch(q, start, count);
            }
        }.asLiveData();
    }

    public void changeDataInDatabase() {
        LiveData<Book[]> liveData = db.daoBook().loadAllBooks();
        liveData.observeForever(new Observer<Book[]>() {
            @Override
            public void onChanged(@Nullable Book[] books) {
                liveData.removeObserver(this);
                if (books != null && books.length > 0) {
                    Book book = books[0];
                    book.getImages().setLarge(book.getImages().getLarge() + Math.random());
                    appExecutors.diskIO().execute(() -> db.daoBook().insertBooks(book));
                }
            }
        });
    }
}