package com.ljp.androidarchitecture.pojo;

import android.arch.lifecycle.LiveData;
import android.support.v4.util.LruCache;

/**
 * @author hanrong
 */

public class UserCache {
    private static final String KEY = UserCache.class.getName();
    public LruCache<String, LiveData<User>> cache;

    public LiveData<User> get() {
        return cache.get(KEY);
    }

    public void put(LiveData<User> data) {
        cache.put(KEY, data);
    }
}
