package com.theelitedevelopers.paystackwebview.data.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.theelitedevelopers.paystackwebview.data.models.PayStackData;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers.All rights reserved.

public class PayStackAuthorizationDto implements Parcelable {
    public Boolean status;
    public String message;
    @SerializedName("data")
    public PayStackData payStackData;

    public PayStackAuthorizationDto(Boolean status, String message, PayStackData payStackData) {
        this.status = status;
        this.message = message;
        this.payStackData = payStackData;
    }

    protected PayStackAuthorizationDto(Parcel in) {
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayStackAuthorizationDto> CREATOR = new Creator<PayStackAuthorizationDto>() {
        @Override
        public PayStackAuthorizationDto createFromParcel(Parcel in) {
            return new PayStackAuthorizationDto(in);
        }

        @Override
        public PayStackAuthorizationDto[] newArray(int size) {
            return new PayStackAuthorizationDto[size];
        }
    };

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PayStackData getPayStackData() {
        return payStackData;
    }

    public void setPayStackData(PayStackData payStackData) {
        this.payStackData = payStackData;
    }
}
