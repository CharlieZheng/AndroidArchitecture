package com.ljp.androidarchitecture.backend;

import android.arch.lifecycle.LiveData;

import com.ljp.androidarchitecture.api.ApiResponse;
import com.ljp.androidarchitecture.pojo.Book;

import retrofit2.http.GET;

public interface Webservice {
    @GET("/v2/book/1220562")
    LiveData<ApiResponse<Book>> loadConfig(
    );
   /* @GET("/v2/book/search")
    LiveData<ApiResponse<BookList>> bookSearch(
            @Query("q") String q,
            @Query("start") int start,
            @Query("count") int count
    );*/


}