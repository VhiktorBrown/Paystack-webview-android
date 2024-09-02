package com.theelitedevelopers.paystackwebview.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class PayStackBody implements Parcelable {

    private final String email;
    private final double amount;
    private final String callback_url;
    @SerializedName("metadata")
    private final JSONObject metaData;

    public PayStackBody(String email, double amount, String callback_url, JSONObject metaData) {
        this.email = email;
        this.amount = amount;
        this.callback_url = callback_url;
        this.metaData = metaData;
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

}

