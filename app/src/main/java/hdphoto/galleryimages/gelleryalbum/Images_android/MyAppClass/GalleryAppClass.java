package hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.List;

import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransportConfig;
import unified.vpn.sdk.OpenVpnTransportConfig;
import unified.vpn.sdk.TransportConfig;
import unified.vpn.sdk.UnifiedSdk;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AppOpenManager;
import hdphoto.galleryimages.gelleryalbum.R;


public class GalleryAppClass extends Application{
    private GalleryAppClass galleryAppClass;
    private GalleryAppClass myApplication;
    private static GalleryAppClass mInstance;

    public static final String SHARED_PREFS = "NORTHGHOST_SHAREDPREFS";

    AppOpenManager appOpenManager;
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        galleryAppClass = this;
        myApplication = this;

        appOpenManager = new AppOpenManager(this);
    }

    public static synchronized GalleryAppClass getInstance() {
        GalleryAppClass myApplication;
        synchronized (GalleryAppClass.class) {
            myApplication = mInstance;
        }
        return myApplication;
    }


    public void initHydraSdk() {
        List<TransportConfig> transportConfigList = new ArrayList<>();
        transportConfigList.add(HydraTransportConfig.create());
        transportConfigList.add(OpenVpnTransportConfig.tcp());
        transportConfigList.add(OpenVpnTransportConfig.udp());
        UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);

        UnifiedSdk.setLoggingLevel(Log.VERBOSE);
    }
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getResources().getString(R.string.app_name) + "";
            String description = getResources().getString(R.string.app_name) + "" + getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getPackageName(), name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public SharedPreferences getPrefs() {
        return getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public void setNewHostAndCarrier(String hostUrl, String carrierId) {
        SharedPreferences prefs = getPrefs();
        if (TextUtils.isEmpty(hostUrl)) {
            prefs.edit().remove("com.northghost.afvclient.STORED_HOST_KEY").apply();
        } else {
            prefs.edit().putString("com.northghost.afvclient.STORED_HOST_KEY", hostUrl).apply();
        }
        if (TextUtils.isEmpty(carrierId)) {
            prefs.edit().remove("com.northghost.afvclient.CARRIER_ID_KEY").apply();
        } else {
            prefs.edit().putString("com.northghost.afvclient.CARRIER_ID_KEY", carrierId).apply();
        }
        initHydraSdk();
    }
}
