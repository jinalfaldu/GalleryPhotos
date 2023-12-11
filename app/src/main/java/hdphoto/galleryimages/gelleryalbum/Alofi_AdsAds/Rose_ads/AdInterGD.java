package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import hdphoto.galleryimages.gelleryalbum.R;


public class AdInterGD {
    public InterstitialAd interstitialOne;
    public InterstitialAd interstitialTwo;
    public InterstitialAd interstitial3;
    public InterstitialAd interstitial4;
    MyCallback myCallback;
    private static AdInterGD mInstance;
    static int gclick = 1;

    public static AdInterGD getInstance() {
        if (mInstance == null) {
            mInstance = new AdInterGD();
        }
        return mInstance;
    }

    public interface MyCallback {
        void callbackCall();
    }

    public void loadInterOne(Activity context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.ct_status_dialog_layout);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        MobileAds.initialize(context, initializationStatus -> {
        });

        String admobinter = ADSharedPref.getString(context, ADSharedPref.INTER, "ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, admobinter, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                interstitialOne = interstitialAd;
                System.out.println("onAdLoaded 1 ===> ");
                interstitialOne.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        dialog.show();
                        // Called when fullscreen content is dismissed.
                        interstitialOne = null;
                        System.out.println("The ad 1 was dismissed. ===> ");
                        loadInterOne(context);

                        if (myCallback != null) {
                            myCallback.callbackCall();
                            myCallback = null;
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        interstitialOne = null;
                        Log.d("TAG", "The ad 1 failed to show.");
                        loadInterOne(context);
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                System.out.println("The ad 1 Load Error. ===> ");
                interstitialOne = null;
                dialog.dismiss();
            }
        });
    }

    public void loadInterTwo(final Activity context) {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        String admobinter02 = ADSharedPref.getString(context, ADSharedPref.INTER1, "ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, admobinter02, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                interstitialTwo = interstitialAd;
                System.out.println("onAdLoaded 2 ===> ");
                interstitialTwo.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        interstitialTwo = null;
                        System.out.println("The ad 2 was dismissed. ===> ");

                        loadInterTwo(context);
                        if (myCallback != null) {
                            myCallback.callbackCall();
                            myCallback = null;
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {

                        interstitialTwo = null;
                        Log.d("TAG", "The ad 2 failed to show.");
                        loadInterTwo(context);
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                System.out.println("The ad 2 Load Error. ===> ");
                interstitialTwo = null;
            }
        });
    }

    public void loadInterThree(final Activity context) {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        String admobinter02 = ADSharedPref.getString(context, ADSharedPref.INTER2, "ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, admobinter02, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                interstitial3 = interstitialAd;
                System.out.println("onAdLoaded 3 ===> ");
                interstitial3.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        interstitial3 = null;
                        System.out.println("The ad 3 was dismissed. ===> ");

                        loadInterThree(context);
                        if (myCallback != null) {
                            myCallback.callbackCall();
                            myCallback = null;
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        interstitial3 = null;
                        Log.d("TAG", "The ad 3 failed to show.");
                        loadInterThree(context);
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                System.out.println("The ad 3 Load Error. ===> ");
                interstitial3 = null;
            }
        });
    }

    public void loadInterFour(final Activity context) {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        String admobinter02 = ADSharedPref.getString(context, ADSharedPref.INTER3, "ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, admobinter02, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                interstitial4 = interstitialAd;
                Log.i("TAG", "onAdLoaded 4");
                System.out.println("onAdLoaded 4 ===> ");
                interstitial4.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        interstitial4 = null;
                        System.out.println("The ad 4 was dismissed. ===> ");

                        loadInterFour(context);
                        if (myCallback != null) {
                            myCallback.callbackCall();
                            myCallback = null;
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {

                        interstitial4 = null;
                        System.out.println("The ad 4 failed to show. ===> ");
                        loadInterFour(context);
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.d("TAG", "The ad 4 Load Error.");
                interstitial4 = null;
            }
        });
    }


    public void showInter(Activity context, MyCallback _myCallback) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.ct_status_dialog_layout);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
        this.myCallback = _myCallback;
        int click = ADSharedPref.getInteger(context, ADSharedPref.CLICK, 1);
        if (gclick == click) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gclick = 1;
                    if (interstitialOne != null) {
                        interstitialOne.show(context);
                    } else if (interstitialTwo != null) {
                        interstitialTwo.show(context);
                    } else if (interstitial3 != null) {
                        interstitial3.show(context);
                    } else if (interstitial4 != null) {
                        interstitial4.show(context);
                    } else {
                        if (myCallback != null) {
                            myCallback.callbackCall();
                            myCallback = null;
                        }
                    }
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }, 1300);
        } else {
            gclick = gclick + 1;
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            dialog.dismiss();
        }
    }

}