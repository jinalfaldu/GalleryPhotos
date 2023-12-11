package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;

public class IntentPass {

    public static void intent(Activity activity, Class<? extends Activity> openActivity, boolean isAdsShow, Bundle bundle) {
        if (isAdsShow) {
            try {
                AdInterGD.getInstance().showInter(activity, () -> {
                    Intent intent = new Intent(activity, openActivity);
                    if (bundle != null) {
                        intent.putExtras(bundle);
                    }
                    activity.startActivity(intent);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent(activity, openActivity);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            activity.startActivity(intent);
        }
    }
}
