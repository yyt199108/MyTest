package com.test.mytest.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017-12-06.
 */

public class AccountBean implements Parcelable {
    public String petName;
    public String petType;
    public String petGender;
    public String petAge;
    public String location;

    public AccountBean() {

    }

    protected AccountBean(Parcel in) {
        petName=in.readString();
        petType=in.readString();
        petGender =in.readString();
        petAge =in.readString();
        location=in.readString();
    }

    public static final Creator<AccountBean> CREATOR = new Creator<AccountBean>() {
        @Override
        public AccountBean createFromParcel(Parcel in) {
            return new AccountBean(in);
        }

        @Override
        public AccountBean[] newArray(int size) {
            return new AccountBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(petName);
        dest.writeString(petType);
        dest.writeString(petGender);
        dest.writeString(petAge);
        dest.writeString(location);
    }
}
