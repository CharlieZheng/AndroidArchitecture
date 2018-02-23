package com.ljp.androidarchitecture.backend;

import com.ljp.androidarchitecture.pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Webservice {
    @GET("/new/apiOfCommon/api/config")
    Call<User> loadConfig(
    );
}