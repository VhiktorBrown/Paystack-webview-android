package com.theelitedevelopers.paystackwebview.data.remote;

import com.theelitedevelopers.paystackwebview.data.models.PayStackInitializer;
import com.theelitedevelopers.paystackwebview.data.dto.PayStackAuthorizationDto;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers.All rights reserved.


public interface PayStackApiInterface {

    //Request for authorization URL from PayStack - Here, we also send the product details if any
    // so after payment, the backend fetches and verifies them.
    @POST("transaction/initialize")
    @Headers("Content-Type: application/json; charset=utf-8")
    Single<Response<PayStackAuthorizationDto>> fetchAuthorizationUrl(@HeaderMap Map<String, String> headers, @Body PayStackInitializer body);
}
