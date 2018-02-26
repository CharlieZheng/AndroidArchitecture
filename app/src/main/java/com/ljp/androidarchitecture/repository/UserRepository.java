package com.ljp.androidarchitecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ljp.androidarchitecture.BuildConfig;
import com.ljp.androidarchitecture.backend.Webservice;
import com.ljp.androidarchitecture.pojo.Result;
import com.ljp.androidarchitecture.pojo.User;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserRepository {
    private Webservice webservice;

    private static class MyLogger1 implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            if (BuildConfig.DEBUG) Log.i("HTTP_LOG", message);
        }
    }

    @Inject
    public UserRepository() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new MyLogger1());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ftx1.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();
        webservice = retrofit.create(Webservice.class);
    }

    // ...
    public LiveData<User> getUser(String userId) {
        // This is not an optimal implementation, we'll fix it below
        final MutableLiveData<User> data = new MutableLiveData<>();
        webservice.loadConfig().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                Gson gson = new GsonBuilder().create();
                Result<User> result = gson.fromJson(body, new TypeToken<Result<User>>() {
                }.getType());
                if (result != null) data.setValue(result.data);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return data;
    }
}