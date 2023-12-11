package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.nativead.NativeAdView;

import hdphoto.galleryimages.gelleryalbum.R;

public class InflatAds {
    Context context;


    public InflatAds(Context context) {
        this.context = context;
    }

    public void inflat_admobnativemedium(com.google.android.gms.ads.nativead.NativeAd unifiedNativeAd, ViewGroup viewGroup) {
        int blink = ADSharedPref.getInteger(context, ADSharedPref.BLINK, 0);
        viewGroup.setVisibility(View.VISIBLE);
        NativeAdView adView;

        adView = (NativeAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.mediaview_native_small_new, null);

        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        TextView button;
        button = adView.findViewById(R.id.ad_call_to_action);

        if (blink == 1) {
            YoYo.with(Techniques.FadeIn)
                    .duration(ADSharedPref.getInteger(context, ADSharedPref.BLINK_TIME, 1000))
                    .repeat(10000)
                    .playOn(button);
        }

        adView.setCallToActionView(button);
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        if (unifiedNativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(unifiedNativeAd.getBody());
        }

        if (unifiedNativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
            button.setVisibility(View.VISIBLE);
        }

        if (unifiedNativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    unifiedNativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (unifiedNativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(unifiedNativeAd.getPrice());
        }

        if (unifiedNativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(unifiedNativeAd.getStore());
        }

        if (unifiedNativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(unifiedNativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (unifiedNativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(unifiedNativeAd);

        viewGroup.removeAllViews();
        viewGroup.addView(adView);
    }
}
