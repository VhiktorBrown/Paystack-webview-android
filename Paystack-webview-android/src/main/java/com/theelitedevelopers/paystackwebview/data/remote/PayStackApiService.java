package com.theelitedevelopers.paystackwebview.data.remote;

import static com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants.PAY_STACK_BASE_URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers.All rights reserved.

public class PayStackApiService {

    private static PayStackApiService instance;
    private static Retrofit retrofit;

    public static synchronized PayStackApiService getInstance() {
        if (instance == null) {
            instance = new PayStackApiService();
        }
        return instance;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    Gson gson = new GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create();

    private PayStackApiService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(PAY_STACK_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public PayStackApiInterface getPayStackApi(){
        return retrofit.create(PayStackApiInterface.class);
    }
}
