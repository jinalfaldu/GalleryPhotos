package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads;

import android.content.Context;
import android.content.SharedPreferences;

public class ADSharedPref {
    public static String openads;
    public static final String PREF_NAME = "PREF_PROFILE";
    public static final int MODE = Context.MODE_PRIVATE;
    public static final String CLICK = "CLICK";
    public static final String Privacy_police = "Privacy_police";
    public static final String adsurl = "adsurl";
    public static final String AppOPEN = "AD_OPEN";
    public static final String AppOPEN1 = "AD_OPEN1";
    public static final String AppOPEN3 = "AD_OPEN3";
    public static final String AppOPEN4 = "AD_OPEN4";
    public static final String BANNER = "AD_BANNER";
    public static final String BANNER1 = "AD_BANNER1";
    public static final String INTER = "AD_INTER";
    public static final String INTER1 = "AD_INTER1";
    public static final String INTER2 = "AD_INTER2";
    public static final String INTER3 = "AD_INTER3";
    public static final String NATIVE = "AD_NATIVE";
    public static final String NATIVE1 = "AD_NATIVE1";
    public static final String NATIVE2 = "AD_NATIVE2";
    public static final String NATIVE3 = "AD_NATIVE3";

    public static final String V_STATUS = "VPAGE";
    public static final String V_LINK = "VLINK";
    public static final String V_ID = "VID";
    public static final String VCountry = "VC";

    public static final String EXTRA_PAGE = "EXTRAPAGE";

    public static final String BACK = "AD_BACK";

    public static final String BLINK = "BLINK";
    public static final String BLINK_TIME = "BLINK_TIME";

    public static final String APP_STATUS = "APP_STATUS";
    public static final String APP_LINK = "APP_LINK";


    public static void setBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static void setInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public static int getInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void setString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String getString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void setFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public static float getFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static void setLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public static long getLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void clearlogin(Context context) {
        getPreferences(context).edit().clear().commit();
    }

}

