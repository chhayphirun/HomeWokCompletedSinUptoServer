package com.example.lier.homewok;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kshrd on 5/24/17.
 */

public class UserOld implements Parcelable {
    private int id;
    private String username;
    private String password;

    public UserOld(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserOld(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserOld(int id, String username, String password) {

        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserOld(int id, String username, String password, String email) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserOld() {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.email);
    }

    protected UserOld(Parcel in) {
        this.id = in.readInt();
        this.username = in.readString();
        this.password = in.readString();
        this.email = in.readString();
    }

    public static final Creator<UserOld> CREATOR = new Creator<UserOld>() {
        @Override
        public UserOld createFromParcel(Parcel source) {
            return new UserOld(source);
        }

        @Override
        public UserOld[] newArray(int size) {
            return new UserOld[size];
        }
    };
}
