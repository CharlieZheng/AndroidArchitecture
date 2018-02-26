package com.ljp.androidarchitecture.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author hanrong
 */

public class User implements Parcelable {
    public AppConfig appConfig;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.appConfig, flags);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.appConfig = in.readParcelable(AppConfig.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
