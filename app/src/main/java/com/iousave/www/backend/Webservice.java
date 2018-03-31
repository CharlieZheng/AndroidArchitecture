package com.iousave.www.backend;

import android.arch.lifecycle.LiveData;

import com.iousave.www.api.ApiResponse;
import com.iousave.www.pojo.Book;
import com.iousave.www.pojo.BookList;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Webservice {
    @GET("/v2/book/1220562")
    LiveData<ApiResponse<Book>> loadConfig(
    );

    @GET("/v2/book/search")
    LiveData<ApiResponse<BookList>> bookSearch(
            @Query("q") String q,
            @Query("start") int start,
            @Query("count") int count
    );


}