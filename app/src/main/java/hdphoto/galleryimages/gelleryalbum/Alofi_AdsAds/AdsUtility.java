package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AdsUtility {

    public static SharedPreferences mysharedpreferences;

    public static boolean isNetworkConnected(Activity mActivity) {
        ConnectivityManager cm = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
