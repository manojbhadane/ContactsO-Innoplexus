package com.manojbhadane.innoplexus.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by lenovo on 27-Jan-18.
 */

public class ResponseModel implements Parcelable, Comparable<ResponseModel>
{
    private int id;
    private String name;
    private String email;
    private String phone;
    private String website;
    private String username;
    private Address address;
    private Company company;

    public ResponseModel()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }


    protected ResponseModel(Parcel in)
    {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        website = in.readString();
        username = in.readString();
        address = (Address) in.readValue(Address.class.getClassLoader());
        company = (Company) in.readValue(Company.class.getClassLoader());
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeString(username);
        dest.writeValue(address);
        dest.writeValue(company);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResponseModel> CREATOR = new Parcelable.Creator<ResponseModel>()
    {
        @Override
        public ResponseModel createFromParcel(Parcel in)
        {
            return new ResponseModel(in);
        }

        @Override
        public ResponseModel[] newArray(int size)
        {
            return new ResponseModel[size];
        }
    };

    @Override public int compareTo(@NonNull ResponseModel responseModel)
    {
        int firstCmp = this.getName().compareTo(responseModel.getName());
        return firstCmp != 0 ? firstCmp : this.getName().compareTo(responseModel.getName());
    }
}