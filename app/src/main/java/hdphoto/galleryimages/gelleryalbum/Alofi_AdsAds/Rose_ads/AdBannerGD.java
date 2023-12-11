package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class AdBannerGD {
    private static AdBannerGD mInstance;

    public static AdBannerGD getInstance() {
        if (mInstance == null) {
            mInstance = new AdBannerGD();
        }
        return mInstance;
    }


    public void showBanner(Activity context, RelativeLayout linearLayout) {

        String admobbanner = ADSharedPref.getString(context, ADSharedPref.BANNER, "ca-app-pub-3940256099942544/6300978111");
        AdView adView = new AdView(context);
        adView.setAdSize(getAdSize(context));
        adView.setAdUnitId(admobbanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {


            }

            @Override
            public void onAdOpened() {

            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onAdClosed() {

            }
        });
        linearLayout.addView(adView);
    }

    public void showBanner1(Activity context, RelativeLayout linearLayout) {


        String admobbanner = ADSharedPref.getString(context, ADSharedPref.BANNER1, "ca-app-pub-3940256099942544/6300978111");

        AdView adView = new AdView(context);
        adView.setAdSize(getAdSize(context));
        adView.setAdUnitId(admobbanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {



            }

            @Override
            public void onAdOpened() {

            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onAdClosed() {

            }
        });
        linearLayout.addView(adView);

    }


    private AdSize getAdSize(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }

}
