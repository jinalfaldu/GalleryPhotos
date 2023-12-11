package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds;

import android.app.Activity;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.ADSharedPref;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;


public class OnBackPressed {
    public static void onBackPressed(Activity activity, boolean isAdsShow) {
        String backAds = ADSharedPref.getString(activity, ADSharedPref.BACK, "0");
        if (isAdsShow) {
            if (backAds.equals("1")) {
                try {
                    AdInterGD.getInstance().showInter(activity, activity::finish);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                activity.finish();
            }
        } else {
            activity.finish();
        }
    }
}

