package com.theelitedevelopers.paystackwebview.data.models;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers. All rights reserved.

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

abstract public class PayStackManager {

    protected String secretKey;
    protected String email;
    protected double amount;
    protected String callback_url;
    protected String temporaryMetaData;
    protected boolean show;
    @SerializedName("metadata")
    protected JSONObject metaData;

    public abstract PayStackManager initialize();

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
