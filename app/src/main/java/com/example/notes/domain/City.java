package com.example.notes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class City implements Parcelable {
    @StringRes
    private final int cityName;

    @DrawableRes
    private final int coatOfArms;

    public City(int cityName, int coatOfArms) {
        this.cityName = cityName;
        this.coatOfArms = coatOfArms;
    }

    protected City(Parcel in) {
        cityName = in.readInt();
        coatOfArms = in.readInt();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getCityName() {
        return cityName;
    }

    public int getCoatOfArms() {
        return coatOfArms;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cityName);
        dest.writeInt(coatOfArms);
    }
}

