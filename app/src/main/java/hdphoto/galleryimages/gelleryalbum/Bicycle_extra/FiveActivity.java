package hdphoto.galleryimages.gelleryalbum.Bicycle_extra;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.IntentPass;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.ADSharedPref;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.ImageAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.MomentData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.VideoAlbumData;

public class FiveActivity extends AppCompatActivity {
    LinearLayout tv_go_now;
    FiveActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        activity = this;
        new NativeHelper().shownativeads(this, findViewById(R.id.native_container));
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        tv_go_now = findViewById(R.id.tv_go_now);
        tv_go_now.setOnClickListener(v -> {
            String extraPage = ADSharedPref.getString(activity, ADSharedPref.EXTRA_PAGE, "0");
            if (extraPage.equals("4")) {
                IntentPass.intent(activity, Degu_MainActivity.class, true, new Bundle());
            } else {
                IntentPass.intent(activity, Degu_MainActivity.class, true, new Bundle());
            }
        });
    }

    @Override
    public void onBackPressed() {
        String extraPage = ADSharedPref.getString(activity, ADSharedPref.EXTRA_PAGE, "0");
        if (extraPage.equals("3")) {
            back();
        } else {
            IntentPass.intent(activity, FourActivity.class, true, new Bundle());
        }

    }

    private void back() {
        final Dialog dialog = new Dialog(this, 16973841);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.exitrateus_dialoag);
        dialog.findViewById(R.id.starbtn).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMMainActivity.15
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
                intent.addFlags(1208483840);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });
        dialog.findViewById(R.id.Canclebackbtn).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMMainActivity.16
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finishAffinity();

            }
        });
        dialog.show();
    }
}