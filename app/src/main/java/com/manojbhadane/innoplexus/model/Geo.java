package com.manojbhadane.innoplexus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 28-Jan-18.
 */

public class Geo implements Parcelable
{
    private String lat;
    private String lng;

    public Geo()
    {
    }

    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLng()
    {
        return lng;
    }

    public void setLng(String lng)
    {
        this.lng = lng;
    }

    protected Geo(Parcel in)
    {
        lat = in.readString();
        lng = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(lat);
        dest.writeString(lng);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Geo> CREATOR = new Parcelable.Creator<Geo>()
    {
        @Override
        public Geo createFromParcel(Parcel in)
        {
            return new Geo(in);
        }

        @Override
        public Geo[] newArray(int size)
        {
            return new Geo[size];
        }
    };
}