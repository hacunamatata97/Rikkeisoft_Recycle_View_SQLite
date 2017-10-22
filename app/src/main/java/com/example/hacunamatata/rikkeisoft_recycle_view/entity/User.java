package com.example.hacunamatata.rikkeisoft_recycle_view.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hacunamatata on 10/21/2017.
 */

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private int id;
    private String name;
    private String phone;

    public User(String name, String phone) {

        this.name = name;
        this.phone = phone;
    }

    public User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    protected User(Parcel in) {
        name = in.readString();
        phone = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" + "NAME: " + name + "\n" + "PHONE: " + phone + "\n\n";
    }
}
