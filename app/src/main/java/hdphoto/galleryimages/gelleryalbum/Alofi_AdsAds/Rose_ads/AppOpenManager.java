package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;

public class AppOpenManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private static final String LOG_TAG = "AppOpenManager";
    private static boolean isShowingAd = false;
    private final GalleryAppClass myApplication;
    private AppOpenAd appOpenAd = null;
    private Activity currentActivity;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    int checkAppOpen = 1;

    public AppOpenManager(GalleryAppClass myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    String AD_UNIT_ID;

    public void fetchAd() {
        // Have unused ad, no need to fetch another.
        if (isAdAvailable()) {
            return;
        }

        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            /**
             * Called when an app open ad has loaded.
             *
             * @param appOpenAd the loaded app open ad.
             */


            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                AppOpenManager.this.appOpenAd = appOpenAd;
            }

            /**
             * Called when an app open ad has failed to load.
             *
             * @param loadAdError the error.
             */
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                loadAdFailed2(myApplication);
            }
        };
        AdRequest request = getAdRequest();
        AppOpenAd.load(myApplication, ADSharedPref.getString(myApplication, ADSharedPref.AppOPEN, ""), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    public void loadAdFailed2(Context context) {
        SharedPreferences mysharedpreferences = myApplication.getSharedPreferences(myApplication.getPackageName(), Context.MODE_PRIVATE);
        AD_UNIT_ID = ADSharedPref.getString(myApplication, ADSharedPref.AppOPEN1, "");
        System.out.println("AD_UNIT_ID ====> " + AD_UNIT_ID);
        if (isShowingAd || isAdAvailable()) {
            return;
        }
        isShowingAd = true;
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(context, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(AppOpenAd ad) {
                appOpenAd = ad;
                isShowingAd = false;
                Log.d(LOG_TAG, "onAdLoaded.");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                isShowingAd = false;
                loadAdFailed3(myApplication);
                Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
            }
        });
    }

    public void loadAdFailed3(Context context) {
        SharedPreferences mysharedpreferences = myApplication.getSharedPreferences(myApplication.getPackageName(), Context.MODE_PRIVATE);
        AD_UNIT_ID = ADSharedPref.getString(myApplication, ADSharedPref.AppOPEN3, "");
        System.out.println("AD_UNIT_ID ====> " + AD_UNIT_ID);
        if (isShowingAd || isAdAvailable()) {
            return;
        }
        isShowingAd = true;
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(context, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(AppOpenAd ad) {
                appOpenAd = ad;
                isShowingAd = false;
                Log.d(LOG_TAG, "onAdLoaded.");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                isShowingAd = false;
                loadAdFailed4(myApplication);
                Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
            }
        });
    }

    public void loadAdFailed4(Context context) {
        SharedPreferences mysharedpreferences = myApplication.getSharedPreferences(myApplication.getPackageName(), Context.MODE_PRIVATE);
        AD_UNIT_ID = ADSharedPref.getString(myApplication, ADSharedPref.AppOPEN4, "");
        System.out.println("AD_UNIT_ID ====> " + AD_UNIT_ID);
        if (isShowingAd || isAdAvailable()) {
            return;
        }
        isShowingAd = true;
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(context, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(AppOpenAd ad) {
                appOpenAd = ad;
                isShowingAd = false;
                Log.d(LOG_TAG, "onAdLoaded.");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                isShowingAd = false;
                Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
            }
        });
    }


    /**
     * Creates and returns ad request.
     */
    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    /**
     * Utility method that checks if ad exists and can be shown.
     */
    public boolean isAdAvailable() {
        return appOpenAd != null;
    }


    @Override
    public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        currentActivity = null;
    }

    public void showAdIfAvailable() {
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        if (!isShowingAd && isAdAvailable()) {


            FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Set the reference to null so isAdAvailable() returns false.
                    AppOpenManager.this.appOpenAd = null;
                    isShowingAd = false;
                    fetchAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    isShowingAd = true;
                }
            };

            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);

            appOpenAd.show(currentActivity);

        } else {
            Log.d(LOG_TAG, "Can not show ad.");
            fetchAd();
        }

    }


    /**
     * LifecycleObserver methods
     */
    @OnLifecycleEvent(ON_START)
    public void onStart() {
        showAdIfAvailable();
        Log.d(LOG_TAG, "onStart");
    }
}