package com.jd.common.entity;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * 猜数字的控制跳转的bean
 *
 */

public class GuessNumCtrlBean implements Parcelable {


    private String guessType;
    private String name;
    private String gender;
    private String birthday;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGuessType() {
        return guessType;
    }

    public void setGuessType(String guessType) {
        this.guessType = guessType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.guessType);
        dest.writeString(this.name);
        dest.writeString(this.gender);
        dest.writeString(this.birthday);
    }

    public GuessNumCtrlBean() {
    }

    protected GuessNumCtrlBean(Parcel in) {
        this.guessType = in.readString();
        this.name = in.readString();
        this.gender = in.readString();
        this.birthday = in.readString();
    }

    public static final Parcelable.Creator<GuessNumCtrlBean> CREATOR = new Parcelable.Creator<GuessNumCtrlBean>() {
        @Override
        public GuessNumCtrlBean createFromParcel(Parcel source) {
            return new GuessNumCtrlBean(source);
        }

        @Override
        public GuessNumCtrlBean[] newArray(int size) {
            return new GuessNumCtrlBean[size];
        }
    };
}
