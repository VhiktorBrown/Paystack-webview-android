package com.theelitedevelopers.paystackwebview.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class PayStackBody implements Parcelable {

    private String email;
    private double amount;
    private String callback_url;
    @SerializedName("metadata")
    private JSONObject metaData;

    public PayStackBody(String email, double amount, String callback_url) {
        this.email = email;
        this.amount = amount;
        this.callback_url = callback_url;
    }

    public PayStackBody(String email, double amount, String callback_url, JSONObject metaData) {
        this.email = email;
        this.amount = amount;
        this.callback_url = callback_url;
        this.metaData = metaData;
    }

    protected PayStackBody(Parcel in) {
        email = in.readString();
        amount = in.readDouble();
        callback_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeDouble(amount);
        dest.writeString(callback_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayStackInitializer> CREATOR = new Creator<PayStackInitializer>() {
        @Override
        public PayStackInitializer createFromParcel(Parcel in) {
            return new PayStackInitializer(in);
        }

        @Override
        public PayStackInitializer[] newArray(int size) {
            return new PayStackInitializer[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public JSONObject getMetaData() {
        return metaData;
    }

    public void setMetaData(JSONObject metaData) {
        this.metaData = metaData;
    }
}

