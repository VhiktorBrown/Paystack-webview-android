package com.theelitedevelopers.paystackwebview.ui;
import static com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants.PAY_STACK_3D_URL;
import static com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants.RESULT_CANCELLED;
import static com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants.RESULT_SUCCESS;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.theelitedevelopers.paystackwebview.PayStackWebViewForAndroid;
import com.theelitedevelopers.paystackwebview.data.models.PayStackBody;
import com.theelitedevelopers.paystackwebview.data.models.PayStackInitializer;
import com.theelitedevelopers.paystackwebview.data.remote.PayStackApiService;
import com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants;
import com.theelitedevelopers.paystackwebview.data.dto.PayStackAuthorizationDto;
import com.theelitedevelopers.paystackwebview.databinding.ActivityPayStackBinding;

import java.util.HashMap;
import java.util.Map;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers. All rights reserved.

public class PayStackActivity extends AppCompatActivity {

    ActivityPayStackBinding binding;
    PayStackInitializer initializer;
    PayStackAuthorizationDto payStackAuthorizationDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayStackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializer = getIntent().getParcelableExtra(PayStackWebViewConstants.DETAILS);

        initializer = new PayStackWebViewForAndroid(this).getDataWithJsonMetaData(initializer);

        reloadURL();

        //make request
        requestAuthorizationURL();

        initViews();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initViews() {
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webView.getSettings().setLoadWithOverviewMode(true);
        binding.webView.getSettings().setLoadWithOverviewMode(true);
        binding.webView.getSettings().setDomStorageEnabled(true);

        binding.reloadButton.setOnClickListener(v -> {
            reloadURL();

            //make request
            requestAuthorizationURL();
        });

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();

                if (url.toString().startsWith(initializer.getCallback_url())) {
                    paymentCompleted(true);
                    return true;
                } else if (url.toString().equals(PAY_STACK_3D_URL)) {
                    paymentCompleted(true);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request.getUrl().toString());
            }
        });

    }

    @SuppressLint("SetJavaScriptEnabled")
    public void loadUrl(PayStackAuthorizationDto body) {
        binding.webView.loadUrl(body.getPayStackData().getAuthorizationUrl());
    }

    private void requestAuthorizationURL() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + initializer.getSecretKey());

        Single<Response<PayStackAuthorizationDto>> fetchAuthorizationResponse = PayStackApiService.getInstance()
                .getPayStackApi().fetchAuthorizationUrl(headers, getRequestBody());
        fetchAuthorizationResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<PayStackAuthorizationDto>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<PayStackAuthorizationDto> fetchAuthorizationResponse) {
                        hideAllViews();

                        if (fetchAuthorizationResponse.isSuccessful() && fetchAuthorizationResponse.body() != null) {
                            if (fetchAuthorizationResponse.body().getPayStackData() != null) {
                                payStackAuthorizationDto = fetchAuthorizationResponse.body();
                                //load the Authorization URL if gotten successfully.
                                loadUrl(fetchAuthorizationResponse.body());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showErrorConnMessage();
                        Toast.makeText(PayStackActivity.this, PayStackWebViewConstants.CHECK_CONNECTION, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private PayStackBody getRequestBody(){
        return new PayStackBody(initializer.getEmail(),
                initializer.getAmount(),
                initializer.getCallback_url(),
                initializer.getMetaData()
        );
    }

    private void showErrorConnMessage(){
        if(initializer.isShow()){
            binding.progressBar.setVisibility(View.GONE);
            binding.connErrorText.setVisibility(View.VISIBLE);
            binding.reloadButton.setVisibility(View.VISIBLE);
        }
    }

    private void hideAllViews(){
        binding.progressBar.setVisibility(View.GONE);
        binding.connErrorText.setVisibility(View.GONE);
        binding.reloadButton.setVisibility(View.GONE);
    }

    private void reloadURL(){
        if(initializer.isShow()){
            binding.reloadButton.setVisibility(View.GONE);
            binding.connErrorText.setVisibility(View.GONE);

            binding.progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        } else {
            super.onBackPressed();
            paymentCompleted(false);
        }
    }

    private void paymentCompleted(boolean paymentCompleted){
        Intent intent = new Intent();
        if(paymentCompleted){
            if(payStackAuthorizationDto != null){
                intent.putExtra(PayStackWebViewConstants.REFERENCE, payStackAuthorizationDto.getPayStackData().getReference());
                intent.putExtra(PayStackWebViewConstants.ACCESS_CODE, payStackAuthorizationDto.getPayStackData().getAccessCode());
            }
            setResult(RESULT_SUCCESS, intent);
        }else {
            if(payStackAuthorizationDto != null){
                intent.putExtra(PayStackWebViewConstants.REFERENCE, payStackAuthorizationDto.getPayStackData().getReference());
                intent.putExtra(PayStackWebViewConstants.ACCESS_CODE, payStackAuthorizationDto.getPayStackData().getAccessCode());
            }
            setResult(RESULT_CANCELLED, intent);
        }
        finish();
    }
}