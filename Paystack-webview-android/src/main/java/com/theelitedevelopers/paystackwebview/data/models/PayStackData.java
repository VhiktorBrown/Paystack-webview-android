package com.theelitedevelopers.paystackwebview.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers.All rights reserved.

public class PayStackData implements Parcelable {
    @SerializedName("authorization_url")
    String authorizationUrl;
    @SerializedName("access_code")
    String accessCode;
    @SerializedName("reference")
    String reference;

    public PayStackData(){}

    public PayStackData(String authorizationUrl, String accessCode, String reference) {
        this.authorizationUrl = authorizationUrl;
        this.accessCode = accessCode;
        this.reference = reference;
    }

    protected PayStackData(Parcel in) {
        authorizationUrl = in.readString();
        accessCode = in.readString();
        reference = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(authorizationUrl);
        dest.writeString(accessCode);
        dest.writeString(reference);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayStackData> CREATOR = new Creator<PayStackData>() {
        @Override
        public PayStackData createFromParcel(Parcel in) {
            return new PayStackData(in);
        }

        @Override
        public PayStackData[] newArray(int size) {
            return new PayStackData[size];
        }
    };

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
