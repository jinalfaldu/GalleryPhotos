package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.formats.NativeAdOptions;


public class NativeHelper {
    String TAG = "TAG";

    public static com.google.android.gms.ads.nativead.NativeAd native_gads;
    public static com.google.android.gms.ads.nativead.NativeAd native_gads1;
    public static com.google.android.gms.ads.nativead.NativeAd native_gads2;
    public static com.google.android.gms.ads.nativead.NativeAd native_gads3;

    public static Activity mactivity;
    public void shownativeads(Activity activity, final ViewGroup viewGroup) {
        mactivity = activity;
        String admobnative = ADSharedPref.getString(activity, ADSharedPref.NATIVE, "no id");
        Log.println(Log.ASSERT, "ads1", admobnative);


        if (native_gads == null) {
            try {
                loadPreLoadNative(activity, admobnative, viewGroup, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                try {
                    new InflatAds(activity).inflat_admobnativemedium(native_gads, viewGroup);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.println(Log.ASSERT, "ads1", e.getMessage());

                }
                try {
                    loadPreLoadNative(activity, admobnative, viewGroup, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void shownativeads1(Activity activity, final ViewGroup viewGroup) {
        String admobnative = ADSharedPref.getString(activity, ADSharedPref.NATIVE1, "no id");
        if (native_gads1 == null) {
            try {
                loadPreLoadNative(activity, admobnative, viewGroup, 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                try {
                    new InflatAds(activity).inflat_admobnativemedium(native_gads1, viewGroup);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.println(Log.ASSERT, "ads2", e.getMessage());

                }
                try {
                    loadPreLoadNative(activity, admobnative, viewGroup, 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void shownativeads2(Activity activity, final ViewGroup viewGroup) {
        String admobnative = ADSharedPref.getString(activity, ADSharedPref.NATIVE2, "no id");
        if (native_gads2 == null) {
            try {
                loadPreLoadNative(activity, admobnative, viewGroup, 3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                try {
                    new InflatAds(activity).inflat_admobnativemedium(native_gads2, viewGroup);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.println(Log.ASSERT, "ads3", e.getMessage());

                }
                try {
                    loadPreLoadNative(activity, admobnative, viewGroup, 3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void shownativeads3(Activity activity, final ViewGroup viewGroup) {

        String admobnative = ADSharedPref.getString(activity, ADSharedPref.NATIVE3, "no id");

        if (native_gads3 == null) {
            try {
                loadPreLoadNative(activity, admobnative, viewGroup, 4);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                try {
                    new InflatAds(activity).inflat_admobnativemedium(native_gads3, viewGroup);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.println(Log.ASSERT, "ads4", e.getMessage());

                }
                try {
                    loadPreLoadNative(activity, admobnative, viewGroup, 4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void loadPreLoadNative(Activity activity, String admob_n, final ViewGroup viewGroup, int id) {
        try {
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(nativeAd -> {
                try {
                    if (id == 1) {
                        if (native_gads == null && viewGroup != null) {
                            new InflatAds(activity).inflat_admobnativemedium(nativeAd, viewGroup);
                        }
                        native_gads = nativeAd;
                    } else if (id == 2) {
                        if (native_gads1 == null && viewGroup != null) {
                            new InflatAds(activity).inflat_admobnativemedium(nativeAd, viewGroup);
                        }
                        native_gads1 = nativeAd;
                    } else if (id == 3) {
                        if (native_gads2 == null && viewGroup != null) {
                            new InflatAds(activity).inflat_admobnativemedium(nativeAd, viewGroup);
                        }
                        native_gads2 = nativeAd;
                    } else if (id == 4) {
                        if (native_gads3 == null && viewGroup != null) {
                            new InflatAds(activity).inflat_admobnativemedium(nativeAd, viewGroup);
                        }
                        native_gads3 = nativeAd;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                    if (id == 1) {
                        shownativeads1(activity, viewGroup);
                    } else if (id == 2) {
                        shownativeads2(activity, viewGroup);
                    } else if (id == 3) {
                        shownativeads3(activity, viewGroup);
                    }
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder().build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstLoadNative(Activity activity, String admob_n) {
        try {
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(nativeAd -> {
                native_gads = nativeAd;
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder().build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstLoadNative1(Activity activity, String admob_n) {
        try {
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(nativeAd -> {
                native_gads1 = nativeAd;
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder().build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstLoadNative2(Activity activity, String admob_n) {
        try {
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(nativeAd -> {
                native_gads2 = nativeAd;
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder().build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstLoadNative3(Activity activity, String admob_n) {
        try {
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(nativeAd -> {
                native_gads3 = nativeAd;
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder().build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

