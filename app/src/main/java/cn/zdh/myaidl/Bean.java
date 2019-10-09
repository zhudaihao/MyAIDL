package cn.zdh.myaidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Bean implements Parcelable {

    private String name;

    private String password;

    public Bean() {
    }

    public Bean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.password);
    }


    protected Bean(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<Bean> CREATOR = new Parcelable.Creator<Bean>() {
        @Override
        public Bean createFromParcel(Parcel source) {
            return new Bean(source);
        }

        @Override
        public Bean[] newArray(int size) {
            return new Bean[size];
        }
    };


    /**
     * adil 使用out 或者inout 需要写这个方法
     */

    public void readFromParcel(Parcel reply) {
        this.name = reply.readString();
        this.password = reply.readString();

    }
}
