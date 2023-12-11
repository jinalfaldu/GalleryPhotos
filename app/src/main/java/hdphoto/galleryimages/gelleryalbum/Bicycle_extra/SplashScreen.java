package hdphoto.galleryimages.gelleryalbum.Bicycle_extra;


import static unified.vpn.sdk.OpenVpnTransport.TRANSPORT_ID_TCP;
import static unified.vpn.sdk.OpenVpnTransport.TRANSPORT_ID_UDP;
import static hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.AdsUtility.mysharedpreferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unified.vpn.sdk.AuthMethod;
import unified.vpn.sdk.ClientInfo;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.Country;
import unified.vpn.sdk.HydraTransport;
import unified.vpn.sdk.HydraTransportConfig;
import unified.vpn.sdk.HydraVpnTransportException;
import unified.vpn.sdk.NetworkRelatedException;
import unified.vpn.sdk.OpenVpnTransportConfig;
import unified.vpn.sdk.PartnerApiException;
import unified.vpn.sdk.SdkNotificationConfig;
import unified.vpn.sdk.SessionConfig;
import unified.vpn.sdk.SessionInfo;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.TrafficListener;
import unified.vpn.sdk.TrafficRule;
import unified.vpn.sdk.TransportConfig;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.User;
import unified.vpn.sdk.VpnException;
import unified.vpn.sdk.VpnPermissionDeniedException;
import unified.vpn.sdk.VpnPermissionRevokedException;
import unified.vpn.sdk.VpnState;
import unified.vpn.sdk.VpnStateListener;
import unified.vpn.sdk.VpnTransportException;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.AdsUtility;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.ApiClient;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.ApiInterface;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.CountryData;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.IntentPass;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.LoginDialog;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Preference;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.ADSharedPref;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NewAppOpenAdManager;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.ImageAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.MomentData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.VideoAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Paraguay_splashexit.PermissionActivity;

public class SplashScreen extends Activity implements TrafficListener, VpnStateListener, LoginDialog.LoginConfirmationInterface {

    public static SplashScreen splashScreen;
    UnifiedSdk unifiedSDK;
    public static Activity activity;
    public static NewAppOpenAdManager appOpenAdManager;
    String morepages;
    private String selectedCountry = "";

    Preference preference;
    private String ServerIPaddress = "00.000.000.00";
    public static ImageAlbumData getImageAlbumData = null;
    public static MomentData getMomentData = null;
    boolean connected = false;
    public static VideoAlbumData getVideoAlbumData = null;

    int flag;
    public static int click;
    String v_country = "";
    String vpn_baseurl = "";
    String carrier_id = "";
    String vpn_status = "";


    @SuppressLint("WrongConstant")
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.iuc_splashactivity);
        activity = this;
        splashScreen = this;
        mysharedpreferences = getApplicationContext().getSharedPreferences(getApplicationContext().getPackageName(), MODE_PRIVATE);

        if (AdsUtility.isNetworkConnected(this)) {
            new getdata().execute();
        } else {
            Dialog nointer = new Dialog(SplashScreen.this);
            nointer.setContentView(R.layout.status_dialog_internet);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            nointer.setCancelable(false);
            TextView btn_no = nointer.findViewById(R.id.btn_no);

            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    finishAffinity();
                }
            });
            nointer.show();
        }
    }

    public class getdata extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String kk = "";
            try {

                ArrayList<String> urls = new ArrayList<String>();
                URL url = new URL("https://myexternalip.com/raw");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String str;

                while ((str = in.readLine()) == null) {
                    urls.add(str);
                }
                in.close();
                kk = str;
                ArrayList<String> urls1 = new ArrayList<String>();
                String u = "http://ip-api.com/php/" + kk;
                URL url1 = new URL(u);
                HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
                BufferedReader in1 = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
                String str1;
                while ((str1 = in1.readLine()) == null) {
                    urls1.add(str1);
                }
                in1.close();
                kk = str1;
            } catch (java.io.IOException e) {
                e.printStackTrace();

                try {
                    java.util.Scanner s = new java.util.Scanner(new URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A");
                    kk = s.next();

                } catch (Exception ee) {
                }
            }
            return kk;
        }

        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        @Override
        protected void onPostExecute(String kk1) {
            super.onPostExecute(kk1);
            getId(kk1);
        }
    }


    public void getId(String s) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call call = apiService.getid(s);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                int statusCode = response.code();

                if (response.isSuccessful()) {
                    try {
                        if (statusCode == 200) {
                            String data = new Gson().toJson(response.body());
                            JSONObject jso = new JSONObject(data);

                            click = jso.getInt("inter_count");

                            String adBanner = jso.getString("banner");
                            String adBanner1 = jso.getString("banner1");
                            String adBanner2 = jso.getString("banner2");
                            String adBanner3 = jso.getString("banner3");
                            String adInter = jso.getString("interstitial");
                            String adInter1 = jso.getString("interstitial1");
                            String adInter2 = jso.getString("interstitial2");
                            String adInter3 = jso.getString("interstitial3");
                            String adNative = jso.getString("native");
                            String adNative1 = jso.getString("native1");
                            String adNative2 = jso.getString("native2");
                            String adNative3 = jso.getString("native3");
                            String appopen = jso.getString("appopen");
                            String appopen1 = jso.getString("appopen1");
                            String appopen2 = jso.getString("appopen2");
                            String appopen3 = jso.getString("appopen3");


                            vpn_status = jso.getString("vpn_status");
                            vpn_baseurl = jso.getString("vpn_baseurl");
                            carrier_id = jso.getString("carrier_id");
                            v_country = jso.getString("v_country");

                            morepages = jso.getString("morepages");
                            String is_back = jso.getString("back_status");

                            String Privacy_police = jso.getString("Privacy_police");

                            String adsurl = jso.getString("adsurl");

                            String app_status = jso.getString("applive_status");
                            String app_link = jso.getString("redirect_app_link");

                            flag = jso.getInt("open_inter_status");


                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.BACK, is_back);
                            ADSharedPref.setInteger(SplashScreen.this, ADSharedPref.CLICK, click);

                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.BANNER, adBanner);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.BANNER1, adBanner1);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.BANNER1, adBanner2);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.BANNER1, adBanner3);

                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.INTER, adInter);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.INTER1, adInter1);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.INTER2, adInter2);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.INTER3, adInter3);

                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.NATIVE, adNative);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.NATIVE1, adNative1);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.NATIVE2, adNative2);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.NATIVE3, adNative3);

                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.AppOPEN, appopen);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.AppOPEN1, appopen1);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.AppOPEN3, appopen2);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.AppOPEN4, appopen3);

                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.V_STATUS, vpn_status);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.V_LINK, vpn_baseurl);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.V_ID, carrier_id);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.VCountry, v_country);

                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.Privacy_police, Privacy_police);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.adsurl, adsurl);

                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.APP_STATUS, app_status);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.APP_LINK, app_link);
                            ADSharedPref.setString(SplashScreen.this, ADSharedPref.EXTRA_PAGE, morepages);

                            String stats = ADSharedPref.getString(activity, ADSharedPref.APP_STATUS, "0");
                            if (stats.equals("0")) {
                                next1();
                            } else {
                                dialog();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String stats = ADSharedPref.getString(activity, ADSharedPref.APP_STATUS, "0");
                        if (stats.equals("0")) {
                            next1();
                        } else {
                            dialog();
                        }
                    }
                } else {
                    String stats = ADSharedPref.getString(activity, ADSharedPref.APP_STATUS, "0");
                    if (stats.equals("0")) {
                        next1();
                    } else {
                        dialog();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(activity, "" + t, Toast.LENGTH_LONG).show();
                String stats = ADSharedPref.getString(activity, ADSharedPref.APP_STATUS, "0");
                if (stats.equals("0")) {
                    next1();
                } else {
                    dialog();
                }
            }
        });
    }

    public void next1() {
        if (vpn_status.equals("0")) {
            if (vpn()) {
                Screen();
            } else {
                vpnLoad();
            }
        } else {
            Screen();
        }
    }

    private void vpnLoad() {
        vpn_baseurl = ADSharedPref.getString(this, ADSharedPref.V_LINK, "0");
        carrier_id = ADSharedPref.getString(this, ADSharedPref.V_ID, "0");


        initHydraSdk();
        loginToVpn();
        new Handler().postDelayed(() -> {
            connectToVpn();
        }, 3000);
    }

    public boolean vpn() {
        String iface = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();
                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    public void initHydraSdk() {
        String vpn_baseurl = ADSharedPref.getString(getApplicationContext(), ADSharedPref.V_LINK, "0");
        String carrier_id = ADSharedPref.getString(getApplicationContext(), ADSharedPref.V_ID, "0");

        ((GalleryAppClass) getApplication()).createNotificationChannel();
        ClientInfo clientInfo = ClientInfo.newBuilder()
                .addUrl(vpn_baseurl)
                .carrierId(carrier_id)
                .build();
        UnifiedSdk.clearInstances();
        unifiedSDK = UnifiedSdk.getInstance(clientInfo);
        SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder()
                .title(getResources().getString(R.string.app_name))
                .channelId(getPackageName())
                .build();
        UnifiedSdk.update(notificationConfig);


        List<TransportConfig> transportConfigList = new ArrayList<>();
        transportConfigList.add(HydraTransportConfig.create());
        transportConfigList.add(OpenVpnTransportConfig.tcp());
        transportConfigList.add(OpenVpnTransportConfig.udp());
        UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);

        UnifiedSdk.setLoggingLevel(Log.VERBOSE);
    }

    public void Screen() {

        AdInterGD.getInstance().loadInterOne(SplashScreen.this);
        AdInterGD.getInstance().loadInterTwo(SplashScreen.this);
        AdInterGD.getInstance().loadInterThree(SplashScreen.this);
        AdInterGD.getInstance().loadInterFour(SplashScreen.this);

        new NativeHelper().firstLoadNative(SplashScreen.this, ADSharedPref.getString(SplashScreen.this, ADSharedPref.NATIVE, "no id"));
        new NativeHelper().firstLoadNative1(SplashScreen.this, ADSharedPref.getString(SplashScreen.this, ADSharedPref.NATIVE1, "no id"));
        new NativeHelper().firstLoadNative2(SplashScreen.this, ADSharedPref.getString(SplashScreen.this, ADSharedPref.NATIVE2, "no id"));
        new NativeHelper().firstLoadNative3(SplashScreen.this, ADSharedPref.getString(SplashScreen.this, ADSharedPref.NATIVE3, "no id"));

        appOpenAdManager = new NewAppOpenAdManager(activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (flag == 0) {
                            appOpenAdManager.showAdIfAvailable(SplashScreen.this, SplashScreen.this::loadIntent);
                        } else {
                            loadIntent();
                        }
                    }
                }, 5500);

            }
        }, 1500);
    }

    public boolean checkPermission() {
        return Build.VERSION.SDK_INT >= 33 ? checkSelfPermission("android.permission.READ_MEDIA_IMAGES") == PackageManager.PERMISSION_GRANTED && checkSelfPermission("android.permission.READ_MEDIA_VIDEO") ==
                PackageManager.PERMISSION_GRANTED && checkSelfPermission("android.permission.READ_MEDIA_AUDIO") == PackageManager.PERMISSION_GRANTED : checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED && checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
    }

    private void loadIntent() {
        if (checkPermission()) {
            startActivity(new Intent(activity, FirstActivity.class));
        } else {
            IntentPass.intent(SplashScreen.this, PermissionActivity.class, false, new Bundle());
        }
    }

    protected void connectToVpn() {
        isLoggedIn(new unified.vpn.sdk.Callback<Boolean>() {
            @Override
            public void success(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    List<String> fallbackOrder = new ArrayList<>();
                    v_country = ADSharedPref.getString(activity, ADSharedPref.VCountry, "0");

                    fallbackOrder.add(TRANSPORT_ID_TCP);
                    fallbackOrder.add(TRANSPORT_ID_UDP);
                    showConnectProgress();
                    List<String> bypassDomains = new LinkedList<>();
                    bypassDomains.add("*facebook.com");
                    bypassDomains.add("*wtfismyip.com");
                    UnifiedSdk.getInstance().getVpn().start(new SessionConfig.Builder()
                            .withReason(TrackingConstants.GprReasons.M_UI)
                            .withTransportFallback(fallbackOrder)
                            .withVirtualLocation(v_country)
                            .withTransport(HydraTransport.TRANSPORT_ID)
                            .addDnsRule(TrafficRule.Builder.bypass().fromDomains(bypassDomains))
                            .build(), new CompletableCallback() {
                        @Override
                        public void complete() {
                            hideConnectProgress();
                            startUIUpdateTask();
                            Screen();
                        }

                        @Override
                        public void error(@NonNull VpnException e) {
                            // OpenAppNext();
                            hideConnectProgress();
                            updateUI();
                            handleError(e);

                        }
                    });
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                //  OpenAppNext();
            }
        });
    }


    protected void isConnected(unified.vpn.sdk.Callback<Boolean> callback) {
        UnifiedSdk.getVpnState(new unified.vpn.sdk.Callback<VpnState>() {
            @Override
            public void success(@NonNull VpnState vpnState) {
                callback.success(vpnState == VpnState.CONNECTED);
            }

            @Override
            public void failure(@NonNull VpnException e) {
                callback.success(false);
                //OpenAppNext();
            }
        });
    }


    protected void isLoggedIn(unified.vpn.sdk.Callback<Boolean> callback) {
        UnifiedSdk.getInstance().getBackend().isLoggedIn(callback);
    }

    protected void showConnectProgress() {

    }

    protected void hideConnectProgress() {

    }


    protected void startUIUpdateTask() {
        stopUIUpdateTask();
    }

    protected void stopUIUpdateTask() {
        updateUI();
    }

    protected void updateUI() {
        UnifiedSdk.getVpnState(new unified.vpn.sdk.Callback<VpnState>() {
            @Override
            public void success(@NonNull VpnState vpnState) {
                switch (vpnState) {
                    case IDLE: {

                        if (connected) {
                            connected = false;
                        }
                        ChangeBlockVisibility();
                        hideConnectProgress();
                        break;
                    }
                    case CONNECTED: {
                        if (!connected) {
                            connected = true;
                            String stats = ADSharedPref.getString(SplashScreen.this, ADSharedPref.APP_STATUS, "0");

                            if (stats.equals("0")) {
                                Screen();
                            } else {
                                dialog();
                            }
                        }
                        hideConnectProgress();
                        break;
                    }
                    case CONNECTING_VPN:
                    case CONNECTING_CREDENTIALS:
                    case CONNECTING_PERMISSIONS: {
                        ChangeBlockVisibility();
                        showConnectProgress();
                        break;
                    }
                    case PAUSED: {
                        ChangeBlockVisibility();
                        break;
                    }
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                // OpenAppNext();
            }
        });
        getCurrentServer(new unified.vpn.sdk.Callback<String>() {
            @Override
            public void success(@NonNull final String currentServer_demo) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        String currentServer = selectedCountry;
                        if (!currentServer.equals("")) {
                            Locale locale = new Locale("", currentServer);
                            Log.e("VPN>>>", locale.getDisplayCountry());
                        }
                    }
                });
            }

            @Override
            public void failure(@NonNull VpnException e) {
                new getdata().execute();
            }
        });
    }

    private void ChangeBlockVisibility() {

    }

    protected void getCurrentServer(final unified.vpn.sdk.Callback<String> callback) {
        UnifiedSdk.getVpnState(new unified.vpn.sdk.Callback<VpnState>() {
            @Override
            public void success(@NonNull VpnState state) {
                if (state == VpnState.CONNECTED) {
                    UnifiedSdk.getStatus(new unified.vpn.sdk.Callback<SessionInfo>() {
                        @Override
                        public void success(@NonNull SessionInfo sessionInfo) {
                            ServerIPaddress = sessionInfo.getCredentials().getServers().get(0).getAddress();
                            ShowIPaddera(ServerIPaddress);
                            Log.e("ServerIPaddress", ServerIPaddress);
                            callback.success(sessionInfo.getCredentials().getServers().get(0).getCountry());
                        }

                        @Override
                        public void failure(@NonNull VpnException e) {
                            callback.success(selectedCountry);
                        }
                    });
                } else {
                    callback.success(selectedCountry);
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                callback.failure(e);
            }
        });
    }

    protected void ShowIPaddera(String ipaddress) {
    }


    public void handleError(Throwable e) {
        if (e instanceof NetworkRelatedException) {
            showMessage("Check internet connection");
        } else if (e instanceof VpnException) {
            if (e instanceof VpnPermissionRevokedException) {
                showMessage("User revoked vpn permissions");
            } else if (e instanceof VpnPermissionDeniedException) {
                showMessage("User canceled to grant vpn permissions");
            } else if (e instanceof VpnTransportException) {
                HydraVpnTransportException hydraVpnTransportException = (HydraVpnTransportException) e;
                if (hydraVpnTransportException.getCode() == HydraVpnTransportException.HYDRA_ERROR_BROKEN) {
                    showMessage("Connection with vpn server was lost");
                } else if (hydraVpnTransportException.getCode() == HydraVpnTransportException.HYDRA_DCN_BLOCKED_BW) {
                    showMessage("Client traffic exceeded");
                } else {
                    showMessage("Error in VPN transport");
                }
            } else {
            }
        } else if (e instanceof PartnerApiException) {
            switch (((PartnerApiException) e).getContent()) {
                case PartnerApiException.CODE_NOT_AUTHORIZED:
                    showMessage("User unauthorized");
                    break;
                case PartnerApiException.CODE_TRAFFIC_EXCEED:
                    showMessage("Server unavailable");
                    break;
                default:
                    showMessage("Other error. Check PartnerApiException constants");
                    break;
            }
        }
    }

    protected void showMessage(String msg) {
    }

    public void disconnectFromVnp() {
        showConnectProgress();
        try {
            UnifiedSdk.getInstance().getVpn().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
                @Override
                public void complete() {
                    hideConnectProgress();
                    stopUIUpdateTask();
                }

                @Override
                public void error(@NonNull VpnException e) {
                    hideConnectProgress();
                    updateUI();
                    handleError(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        UnifiedSdk.addTrafficListener(this);
        UnifiedSdk.addVpnStateListener(this);
    }

    @Override
    public void setLoginParams(String hostUrl, String carrierId) {
        ((GalleryAppClass) getApplication()).setNewHostAndCarrier(hostUrl, carrierId);
    }

    @Override
    public void loginUser() {
        loginToVpn();
    }

    protected void loginToVpn() {
        AuthMethod authMethod = AuthMethod.anonymous();
        UnifiedSdk.getInstance().getBackend().login(authMethod, new unified.vpn.sdk.Callback<User>() {
            @Override
            public void success(@NonNull User user) {
                updateUI();
                isConnected(new unified.vpn.sdk.Callback<Boolean>() {
                    @Override
                    public void success(@NonNull Boolean aBoolean) {
                        if (aBoolean) {
                            disconnectFromVnp();
                        } else {
                            connectToVpn();
                        }
                    }

                    @Override
                    public void failure(@NonNull VpnException e) {
                        //  OpenAppNext();
                    }
                });

            }

            @Override
            public void failure(@NonNull VpnException e) {
                updateUI();
                handleError(e);
                // OpenAppNext();
            }
        });
    }


    @Override
    public void onTrafficUpdate(long bytesTx, long bytesRx) {
        updateUI();
        updateTrafficStats(bytesTx, bytesRx);
    }

    @Override
    public void vpnStateChanged(@NonNull VpnState vpnState) {
        updateUI();
    }

    @Override
    public void vpnError(@NonNull VpnException e) {
        updateUI();
        handleError(e);
    }

    protected void updateTrafficStats(long outBytes, long inBytes) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUIUpdateTask();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3000) {
            if (resultCode == RESULT_OK) {
                Gson gson = new Gson();
                Bundle args = data.getBundleExtra("Bundle");
                CountryData item = gson.fromJson(args.getString("Country_data"), CountryData.class);
                onRegionSelected(item);
            }
        }
    }

    public void onRegionSelected(CountryData item) {

        final Country new_countryValue = item.getCountryvalue();
        selectedCountry = new_countryValue.getCountry();
        preference.setStringpreference(selectedCountry, selectedCountry);
        Toast.makeText(this, "Click to Connect VPN", Toast.LENGTH_SHORT).show();
        updateUI();
        UnifiedSdk.getVpnState(new unified.vpn.sdk.Callback<VpnState>() {
            @Override
            public void success(@NonNull VpnState state) {
                if (state == VpnState.CONNECTED) {
                    showMessage("Reconnecting to VPN with " + selectedCountry);
                    UnifiedSdk.getInstance().getVpn().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
                        @Override
                        public void complete() {
                            connectToVpn();
                        }

                        @Override
                        public void error(@NonNull VpnException e) {
                            selectedCountry = "";
                            preference.setStringpreference(selectedCountry, selectedCountry);
                            connectToVpn();
                        }
                    });
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectFromVnp();
    }

    public void dialog() {
        String applink = ADSharedPref.getString(getApplicationContext(), ADSharedPref.APP_LINK, "0");
        try {
            Dialog redirect;
            redirect = new Dialog(SplashScreen.this);
            redirect.requestWindowFeature(Window.FEATURE_NO_TITLE);
            redirect.setContentView(R.layout.dic_dictionary_update_app_dialog);
            redirect.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = redirect.getWindow();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (window != null) {
                window.setGravity(Gravity.CENTER);
                window.setLayout((int) (0.9 * Resources.getSystem().getDisplayMetrics().widthPixels), android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            }
            redirect.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            redirect.show();
            AppCompatButton btn = redirect.findViewById(R.id.btn_ok);
            btn.setOnClickListener(v -> {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(applink));
                startActivity(viewIntent);
                finishAffinity();
                redirect.dismiss();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
