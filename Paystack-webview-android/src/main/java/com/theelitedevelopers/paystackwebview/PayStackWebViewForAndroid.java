package com.theelitedevelopers.paystackwebview;
import static com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants.PAY_STACK_WEB_VIEW;
import static com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants.REQUEST_CODE;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.theelitedevelopers.paystackwebview.data.models.PayStackInitializer;
import com.theelitedevelopers.paystackwebview.data.models.PayStackManager;
import com.theelitedevelopers.paystackwebview.data.constants.PayStackWebViewConstants;
import com.theelitedevelopers.paystackwebview.ui.PayStackActivity;
import com.theelitedevelopers.paystackwebview.utils.Utils;

public class PayStackWebViewForAndroid extends PayStackManager {

    private Activity activity;
    private Fragment supportFragment;
    private android.app.Fragment fragment;

    public PayStackWebViewForAndroid(Activity activity){
        super();
        this.activity = activity;
    }

    public PayStackWebViewForAndroid(Fragment fragment){
        super();
        this.supportFragment = fragment;
    }

    public PayStackWebViewForAndroid(android.app.Fragment fragment){
        super();
        this.fragment = fragment;
    }

    public PayStackWebViewForAndroid setSecretKey(String secretKey){
        this.secretKey = secretKey;
        return this;
    }

    public PayStackWebViewForAndroid setEmail(String email){
        this.email = email;
        return this;
    }

    public PayStackWebViewForAndroid setAmount(double amount){
        if(amount > 0){
            this.amount = amount;
        }
        return this;
    }

    public PayStackWebViewForAndroid setCallbackURL(String callbackURL){
        this.callback_url = callbackURL;
        return this;
    }

    /**
     * If you want to keep your user in the loop and aware that there's something
     * going on in the background, this method set to TRUE displays a progress bar
     * that shows that data is being loaded and even handles connection errors by
     * displaying a text and a button that allows the user to TRY AGAIN making the request.
     *
     * @param show -> Boolean that determines whether progress bar is shown while making request.
     * @return -> this.
     */
    public PayStackWebViewForAndroid showProgressBar(boolean show){
        this.show = show;
        return this;
    }

    /**
     * Paystack allows developers to send extra data that could be the goods or services users
     * are trying to pay for or any information that is related to the payment that will help us make
     * decisions once payment has been confirmed. Notice that we convert the data into a String data type -
     * This is to help us pass the data through intent from current activity to the WebView Activity
     * where payment will be made. Parcelable cannot be implemented for a raw object.
     *
     * @param metaData -> Can be data of any kind, most likely an instance of a class
     * @return -> this.
     */
    public PayStackWebViewForAndroid setMetaData(Object metaData){
        this.temporaryMetaData = Utils.convertToJsonToString(metaData);
        return this;
    }

    /**
     * This method will take the filled in object with details like Email, amount, etc already set by
     * the user. At this point, the metaData user entered exists in the form of a "String" data type.
     *
     * This was because we needed to pass it to the WebView activity. Now, to be able to pass it to the server,
     * we need to convert it to a JSON Object.
     * @param payStackInitializer - instance that contains data elements
     * @return - this.
     */
    public PayStackInitializer getDataWithJsonMetaData(PayStackInitializer payStackInitializer){
        //Check to see if user added the metadata. If so, we use the constructor
        //that includes the metadata, if not, we use a different constructor.
        if(payStackInitializer.getMetaData() != null){
            return new PayStackInitializer(
                    payStackInitializer.getSecretKey(),
                    payStackInitializer.getEmail(),
                    payStackInitializer.getAmount(),
                    payStackInitializer.getCallback_url(),
                    payStackInitializer.isShow(),
                    Utils.convertFromStringToJson(payStackInitializer.getTemporaryMetaData())
            );
        }else {
            return new PayStackInitializer(
                    payStackInitializer.getSecretKey(),
                    payStackInitializer.getEmail(),
                    payStackInitializer.getAmount(),
                    payStackInitializer.getCallback_url(),
                    payStackInitializer.isShow()
            );
        }
    }


    public PayStackWebViewForAndroid initialize(){
        if(amount <= 0){
            if(activity != null){
                Toast.makeText(activity, "Amount is zero. Kindly set an amount greater than zero to proceed.", Toast.LENGTH_SHORT).show();
            }else if(supportFragment != null){
                Toast.makeText(supportFragment.requireActivity(), "Amount is zero. Kindly set an amount greater than zero to proceed.", Toast.LENGTH_SHORT).show();
            }else if(fragment != null && fragment.getActivity() != null){
                Toast.makeText(fragment.getActivity(), "Amount is zero. Kindly set an amount greater than zero to proceed.", Toast.LENGTH_SHORT).show();
            }
            return this;
        }

        //check if Secret Key was set
        if(secretKey == null || secretKey.isEmpty()){
            if(activity != null){
                Toast.makeText(activity, "Secret key was not set. Kindly set secret key to continue.", Toast.LENGTH_SHORT).show();
            }else if(supportFragment != null){
                Toast.makeText(supportFragment.requireActivity(), "Secret key was not set. Kindly set secret key to continue.", Toast.LENGTH_SHORT).show();
            }else if(fragment != null && fragment.getActivity() != null){
                Toast.makeText(fragment.getActivity(), "Secret key was not set. Kindly set secret key to continue.", Toast.LENGTH_SHORT).show();
            }
            return this;
        }

        //check if Callback URL was set as well
        if(callback_url == null || callback_url.isEmpty()){
            if(activity != null){
                Toast.makeText(activity, "Callback URL was not set. Kindly set callback URL to continue.", Toast.LENGTH_SHORT).show();
            }else if(supportFragment != null){
                Toast.makeText(supportFragment.requireActivity(), "Callback URL was not set. Kindly set callback URL to continue.", Toast.LENGTH_SHORT).show();
            }else if(fragment != null && fragment.getActivity() != null){
                Toast.makeText(fragment.getActivity(), "Callback URL was not set. Kindly set callback URL to continue.", Toast.LENGTH_SHORT).show();
            }
            return this;
        }

        //check if email was set as well
        if(email == null || email.isEmpty()){
            if(activity != null){
                Toast.makeText(activity, "Email was not set. Kindly input email to continue.", Toast.LENGTH_SHORT).show();
            }else if(supportFragment != null){
                Toast.makeText(supportFragment.requireActivity(), "Email was not set. Kindly input email to continue.", Toast.LENGTH_SHORT).show();
            }else if(fragment != null && fragment.getActivity() != null){
                Toast.makeText(fragment.getActivity(), "Email was not set. Kindly input email to continue.", Toast.LENGTH_SHORT).show();
            }
            return this;
        }

        if(activity != null){
            Intent intent = new Intent(activity, PayStackActivity.class);
            intent.putExtra(PayStackWebViewConstants.DETAILS, createIntentData());
            activity.startActivityForResult(intent, REQUEST_CODE);
        }else if(supportFragment != null && supportFragment.getContext() != null){
            Intent intent = new Intent(supportFragment.getActivity(), PayStackActivity.class);
            intent.putExtra(PayStackWebViewConstants.DETAILS, createIntentData());
            supportFragment.requireActivity().startActivityForResult(intent, REQUEST_CODE);
        }else if(fragment != null && fragment.getActivity() != null){
            Intent intent = new Intent(fragment.getActivity(), PayStackActivity.class);
            intent.putExtra(PayStackWebViewConstants.DETAILS, createIntentData());
            fragment.startActivityForResult(intent, REQUEST_CODE);
        }else {
            Log.d(PAY_STACK_WEB_VIEW, "Context is required!");
        }

        return this;
    }

    /**
     * Creates data that's sent to the PayActivity
     * @return - PayStackInitializer instance.
     */
    private PayStackInitializer createIntentData(){
        return new PayStackInitializer(
                getSecretKey(),
                getEmail(),
                getAmount(),
                getCallback_url(),
                isShow(),
                getTemporaryMetaData()
        );
    }
}