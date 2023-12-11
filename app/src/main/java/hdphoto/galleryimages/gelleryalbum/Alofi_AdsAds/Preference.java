package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    public SharedPreferences appSharedPrefs;
    public SharedPreferences.Editor prefsEditor;
    Context context;

    public Preference(Context context) {
        this.context = context;
        this.appSharedPrefs = context.getSharedPreferences("XproVPN_pref", Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public void setStringpreference(String key_value, String defult_value) {
        this.prefsEditor.putString(key_value, defult_value).commit();
    }
}
