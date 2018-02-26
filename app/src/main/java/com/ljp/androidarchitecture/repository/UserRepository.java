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
import com.ljp.androidarchitecture.pojo.UserCache;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Singleton
public class UserRepository {
    private Webservice webservice;

    private static class MyLogger1 implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            if (BuildConfig.DEBUG) Log.i("HTTP_LOG", message);
        }
    }

    private UserCache userCache;

    @Inject
    public UserRepository() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        Log.v(methodName, "UserRepository construction");
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
        userCache = new UserCache();
    }

    public LiveData<User> getUser(String userId) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        LiveData<User> temp = userCache.get();
        if (temp != null) {
            Log.v(methodName, "temp is not null");

            User user = temp.getValue();
            if (user != null && user.appConfig != null && user.appConfig.changelog != null)
                Log.v(methodName, user.appConfig.changelog);
            return temp;
        } else {
            Log.v(methodName, "temp is null");

        }
        final MutableLiveData<User> data = new MutableLiveData<>();
        userCache.put(data);
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