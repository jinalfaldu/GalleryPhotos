package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class NewAppOpenAdManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    MyApplicationAppOpen myApplication;
    private static final String LOG_TAG = "AppOpenAdManager";

    private String AD_UNIT_ID;
    int checkAppOpen = 1;
    private AppOpenAd appOpenAd = null;
    private boolean isLoadingAd = false;
    public boolean isShowingAd = false;

    /**
     * Keep track of the time an app open ad is loaded to ensure you don't show an expired ad.
     */
    private long loadTime = 0;

    /**
     * Constructor.
     */
    public NewAppOpenAdManager(Context activity) {
        AD_UNIT_ID = ADSharedPref.getString(activity, ADSharedPref.AppOPEN, "");

        ADSharedPref.openads = AD_UNIT_ID;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        loadAd(activity);
    }

    public NewAppOpenAdManager(MyApplicationAppOpen new_my_application, Context activity) {
        AD_UNIT_ID = ADSharedPref.getString(activity, ADSharedPref.AppOPEN, "");
        this.myApplication = new_my_application;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        loadAd(activity);
    }

    public void loadAd(Context context) {
        if (isLoadingAd || isAdAvailable()) {
            return;
        }

        isLoadingAd = true;
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(context, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                appOpenAd = ad;
                isLoadingAd = false;
                loadTime = (new Date()).getTime();
                if (checkAppOpen == 1) {
                    System.out.println("AppOpen:  ===> " + checkAppOpen);
                } else if (checkAppOpen == 2) {
                    System.out.println("AppOpen:  ===> " + checkAppOpen);
                } else if (checkAppOpen == 3) {
                    System.out.println("AppOpen:  ===> " + checkAppOpen);
                }
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isLoadingAd = false;
                if (checkAppOpen == 1) {
                    AD_UNIT_ID = ADSharedPref.getString(context, ADSharedPref.AppOPEN1, "");
                    System.out.println("failedAppOpen:  ===> " + ADSharedPref.AppOPEN1);
                    checkAppOpen = 2;
                    loadAd(context);
                } else if (checkAppOpen == 2) {
                    AD_UNIT_ID = ADSharedPref.getString(context, ADSharedPref.AppOPEN3, "");
                    System.out.println("failedAppOpen:  ===> " + ADSharedPref.AppOPEN3);
                    checkAppOpen = 3;
                    loadAd(context);
                } else if (checkAppOpen == 3) {
                    AD_UNIT_ID = ADSharedPref.getString(context, ADSharedPref.AppOPEN4, "");
                    System.out.println("failedAppOpen:  ===> " + ADSharedPref.AppOPEN4);
                    checkAppOpen = 4;
                    loadAd(context);
                }
            }
        });
    }

    /**
     * Check if ad was loaded more than n hours ago.
     */
    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }

    /**
     * Check if ad exists and can be shown.
     */
    private boolean isAdAvailable() {
        // Ad references in the app open beta will time out after four hours, but this time limit
        // may change in future beta versions. For details, see:
        // https://support.google.com/admob/answer/9341964?hl=en
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    /**
     * Show the ad if one isn't already showing.
     *
     * @param activity the DD_activity that shows the app open ad
     */
    private void showAdIfAvailable(@NonNull final Activity activity) {
        showAdIfAvailable(activity, new MyApplicationAppOpen.OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
                // Empty because the user will go back to the DD_activity that shows the ad.
            }
        });
    }

    /**
     * Show the ad if one isn't already showing.
     *
     * @param activity                 the DD_activity that shows the app open ad
     * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
     */
    public void showAdIfAvailable(@NonNull final Activity activity, @NonNull MyApplicationAppOpen.OnShowAdCompleteListener onShowAdCompleteListener) {
        // If the app open ad is already showing, do not show the ad again.
        if (isShowingAd) {
            Log.d(LOG_TAG, "The app open ad is already showing.");
            return;
        }

        // If the app open ad is not available yet, invoke the callback then load the ad.
        if (!isAdAvailable()) {
            Log.d(LOG_TAG, "The app open ad is not ready yet.");
            onShowAdCompleteListener.onShowAdComplete();
            loadAd(activity);
            return;
        }

        Log.d(LOG_TAG, "Will show ad.");

        appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            /** Called when full screen content is dismissed. */
            @Override
            public void onAdDismissedFullScreenContent() {
                // Set the reference to null so isAdAvailable() returns false.
                appOpenAd = null;
                isShowingAd = false;

                Log.d(LOG_TAG, "onAdDismissedFullScreenContent.");
                //Toast.makeText(DD_activity, "onAdDismissedFullScreenContent", Toast.LENGTH_SHORT).show();

                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
            }

            /** Called when fullscreen content failed to show. */
            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                appOpenAd = null;
                isShowingAd = false;

                Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                //Toast.makeText(DD_activity, "onAdFailedToShowFullScreenContent", Toast.LENGTH_SHORT).show();

                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
            }

            /** Called when fullscreen content is shown. */
            @Override
            public void onAdShowedFullScreenContent() {
                Log.d(LOG_TAG, "onAdShowedFullScreenContent.");
                //Toast.makeText(DD_activity, "onAdShowedFullScreenContent", Toast.LENGTH_SHORT).show();
            }
        });

        isShowingAd = true;
        appOpenAd.show(activity);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
