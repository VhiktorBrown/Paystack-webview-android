
package com.theelitedevelopers.paystackwebview.data.models;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers. All rights reserved.

public class PayStackInitializer implements Parcelable {

    private final String secretKey;
    private final String email;
    private final double amount;
    private final String callback_url;
    private boolean show = false;
    private String temporaryMetaData;
    @SerializedName("metadata")
    private JSONObject metaData;

    public PayStackInitializer(String secretKey, String email, double amount, String callback_url, boolean show, String temporaryMetaData) {
        this.secretKey = secretKey;
        this.email = email;
        this.amount = amount;
        this.callback_url = callback_url;
        this.show = show;
        this.temporaryMetaData = temporaryMetaData;
    }

    public PayStackInitializer(String secretKey, String email, double amount, String callback_url, boolean show, JSONObject metaData) {
        this.secretKey = secretKey;
        this.email = email;
        this.amount = amount;
        this.callback_url = callback_url;
        this.show = show;
        this.metaData = metaData;
    }

    public PayStackInitializer(String secretKey, String email, double amount, String callback_url, boolean show) {
        this.secretKey = secretKey;
        this.email = email;
        this.amount = amount;
        this.callback_url = callback_url;
        this.show = show;
    }

    protected PayStackInitializer(Parcel in) {
        secretKey = in.readString();
        email = in.readString();
        amount = in.readDouble();
        callback_url = in.readString();
        show = in.readByte() != 0;
        temporaryMetaData = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(secretKey);
        dest.writeString(email);
        dest.writeDouble(amount);
        dest.writeString(callback_url);
        dest.writeByte((byte) (show ? 1 : 0));
        dest.writeString(temporaryMetaData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayStackInitializer> CREATOR = new Creator<>() {
        @Override
        public PayStackInitializer createFromParcel(Parcel in) {
            return new PayStackInitializer(in);
        }

        @Override
        public PayStackInitializer[] newArray(int size) {
            return new PayStackInitializer[size];
        }
    };

    public String getSecretKey() {
        return secretKey;
    }

    public String getEmail() {
        return email;
    }


    public double getAmount() {
        return amount;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public boolean isShow() {
        return show;
    }

    public String getTemporaryMetaData() {
        return temporaryMetaData;
    }

    public JSONObject getMetaData() {
        return metaData;
    }

}
