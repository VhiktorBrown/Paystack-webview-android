# Paystack Webview Android
The Android library that helps developers integrate Paystack's payment gateway with just few lines of code.

## Screenshots

Paystack Quick test  | Pay With Card  
 :-------------------------:|:-------------------------:
<img src="screenshots/paystack-webview-1.jpg" height="400" width="200"/>  |  <img src="paystack-webview-2.jpg" height="400" width="200"/>  |


## How to Use
- Add Jitpack to your gradle file:
    - For older projects, open up the app level build.gradle file and add jitpack as shown
      ``` java
      allprojects {
      repositories {
      ......
      maven { url 'https://jitpack.io' }
          }
       }
      ```
  
    - For more recent versions of Android studio, open up settings.gradle file in your project and add Jitpack as shown
      ``` java
      dependencyResolutionManagement {
      repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
      repositories {
      .......
      maven { url 'https://jitpack.io' }
          }
       }
      ```


- Add Dependency to your app level build.gradle file
  
  ``` java
  dependencies {
	        implementation 'com.github.VhiktorBrown:Paystack-webview-android:1.0.5'
  }
  ```

- After adding these, sync your project With Gradle files.


## Code Implementation
- In your Activity, copy and paste this code and fill in your own values.
  
  ``` java
    new PayStackWebViewForAndroid(this)
                    .setAmount(600000)
                    .setEmail("customer@gmail.com")
                    .setSecretKey("your_paystack_secret_key")
                    .setCallbackURL("https://transaction_callback_url")
                    .showProgressBar(true)
                    .setMetaData(metaData)
                    .initialize();
  ```


- Add this line of code in the same Activity(outside the onCreate() method) to know if payment was successful or not.

``` java
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
                //Get reference and send to your backend for confirmation before you provide goods or services
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
```