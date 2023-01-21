# Paystack Webview Android
The Android library that helps developers integrate Paystack's payment gateway with just few lines of code.

## ScreenShots

Paystack Quick test           | Pay With Card  
 :-------------------------:|:-------------------------:
<img src="screenshots/paystack-webview-1.jpg"/>            |  <img src="paystack-webview-2.jpg"/>  |

## How to Use
- Add Jitpack to your gradle file:
    - For older projects, open up the app level build.gradle file and add jitpack as shown
      `allprojects {
      repositories {
      google()
      jcenter()
      maven { url 'https://jitpack.io' }
      }
      }`
    - For more recent versions of Android studio, open up settings.gradle file in your project and add Jitpack as shown
      `dependencyResolutionManagement {
      repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
      repositories {
      google()
      mavenCentral()
      jcenter() // Warning: this repository is going to shut down soon
      maven { url 'https://jitpack.io' }
      }
      }`