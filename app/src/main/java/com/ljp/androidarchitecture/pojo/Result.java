package com.ljp.androidarchitecture.pojo;

import android.os.Parcelable;

/**
 * @author hanrong
 */

public class Result<T extends Parcelable> {
    String reqId;
    public T data;
}
