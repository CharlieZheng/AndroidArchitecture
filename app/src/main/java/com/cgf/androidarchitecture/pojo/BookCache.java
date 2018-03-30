package com.cgf.androidarchitecture.pojo;

import android.arch.lifecycle.LiveData;
import android.support.v4.util.LruCache;

/**
 * @author hanrong
 */

public class BookCache {
    private static final String KEY = BookCache.class.getName();
    public LruCache<String, LiveData<Book>> cache = new LruCache<>(1);

    public LiveData<Book> get() {
        return cache.get(KEY);
    }

    public void put(LiveData<Book> data) {
        cache.put(KEY, data);
    }
}
