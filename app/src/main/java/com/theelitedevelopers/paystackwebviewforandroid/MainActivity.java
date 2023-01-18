package com.theelitedevelopers.paystackwebviewforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.theelitedevelopers.paystackwebview.PayStackWebViewForAndroid;
import com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants;
import com.theelitedevelopers.paystackwebview.data.models.PayStackData;
import com.theelitedevelopers.paystackwebviewforandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PayStackData payStackData = new PayStackData();
        payStackData.setAccessCode("access_code");
        payStackData.setReference("reference");
        payStackData.setAuthorizationUrl("authorization_url");


        binding.makePaymentButton.setOnClickListener(v -> {
            new PayStackWebViewForAndroid(this)
                    .setAmount(600000)
                    .setEmail("bukkychukwujekwu@gmail.com")
                    .setSecretKey("secret_key")
                    .setCallbackURL("https://transaction_callback_url")
                    .showProgressBar(true)
                    .setMetaData(payStackData)
                    .initialize();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


         /* After payment if successful, you'll get the Reference and Access code
         * that was used to make payment. You should send this reference code to
         * your backend/server to confirm payment and get the goods or service
         * that you sent before user made payment.



         * Paystack allows you to send the goods or service that your user
         * is attempting to make payment for. So that after payment has been
         * successful, all you need to do is send the reference that the user
         * made payment with to your backend, and then your backend with that
         * reference can get back those goods and services from Paystack.
          */


        if(requestCode == PayStackWebViewConstants.REQUEST_CODE && data != null){
            if(resultCode == PayStackWebViewConstants.RESULT_SUCCESS){
                //Get reference and send to your backend for confirmation.
                String accessCode = data.getStringExtra(PayStackWebViewConstants.ACCESS_CODE);
                String reference = data.getStringExtra(PayStackWebViewConstants.REFERENCE);

                Toast.makeText(this, "Access code: "+accessCode+"\nReference code: "+reference, Toast.LENGTH_SHORT).show();
            }else if(resultCode == PayStackWebViewConstants.RESULT_CANCELLED){
                //do something/take action if payment was cancelled by user.
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}