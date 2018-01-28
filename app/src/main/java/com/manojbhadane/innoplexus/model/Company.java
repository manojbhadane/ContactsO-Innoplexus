package com.manojbhadane.innoplexus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 28-Jan-18.
 */

public class Company implements Parcelable
{
    private String name;
    private String catchPhrase;
    private String bs;

    public Company()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCatchPhrase()
    {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase)
    {
        this.catchPhrase = catchPhrase;
    }

    public String getBs()
    {
        return bs;
    }

    public void setBs(String bs)
    {
        this.bs = bs;
    }

    protected Company(Parcel in)
    {
        name = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(catchPhrase);
        dest.writeString(bs);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>()
    {
        @Override
        public Company createFromParcel(Parcel in)
        {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size)
        {
            return new Company[size];
        }
    };
}