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