package com.ljp.androidarchitecture.backend;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Webservice {
    @GET("/new/apiOfCommon/api/config")
    Call<String> loadConfig(
    );
}