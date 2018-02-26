package com.ljp.androidarchitecture.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author hanrong
 */

public class AppConfig implements Parcelable {
    public String changelog;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.changelog);
    }

    public AppConfig() {
    }

    protected AppConfig(Parcel in) {
        this.changelog = in.readString();
    }

    public static final Parcelable.Creator<AppConfig> CREATOR = new Parcelable.Creator<AppConfig>() {
        @Override
        public AppConfig createFromParcel(Parcel source) {
            return new AppConfig(source);
        }

        @Override
        public AppConfig[] newArray(int size) {
            return new AppConfig[size];
        }
    };
}
