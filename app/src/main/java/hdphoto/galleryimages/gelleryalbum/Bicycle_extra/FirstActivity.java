package hdphoto.galleryimages.gelleryalbum.Bicycle_extra;

import android.annotation.SuppressLint;
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


public class FirstActivity extends AppCompatActivity {
    LinearLayout tv_let_go;
    public static ImageAlbumData getImageAlbumData;
    public static MomentData getMomentData;
    public static VideoAlbumData getVideoAlbumData;
    FirstActivity activity;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        getWindow().setFlags(1024, 1024);
        activity = this;

        new NativeHelper().shownativeads(this, findViewById(R.id.native_container));
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        tv_let_go = findViewById(R.id.tv_let_go);
        tv_let_go.setOnClickListener(v -> {
            FirstActivity.ID_Binding(FirstActivity.this);
            String extraPage = ADSharedPref.getString(activity, ADSharedPref.EXTRA_PAGE, "0");
            if (extraPage.equals("0")) {
                IntentPass.intent(activity, Degu_MainActivity.class, true, new Bundle());
            } else {
                IntentPass.intent(activity, SecondActivity.class, true, new Bundle());
            }
        });
    }

    public static void ID_Binding(Activity activity) {
        MomentData gMGetMomentData = new MomentData(activity);
        getMomentData = gMGetMomentData;
        gMGetMomentData.getMomentAllAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        ImageAlbumData gMGetImageAlbumData = new ImageAlbumData(activity);
        getImageAlbumData = gMGetImageAlbumData;
        gMGetImageAlbumData.getImageAlbumAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        VideoAlbumData gMGetVideoAlbumData = new VideoAlbumData(activity);
        getVideoAlbumData = gMGetVideoAlbumData;
        gMGetVideoAlbumData.getVideoAlbumAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        final Dialog dialog = new Dialog(this, 16973841);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.exitrateus_dialoag);
        dialog.findViewById(R.id.starbtn).setOnClickListener(new View.OnClickListener() {
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
        dialog.findViewById(R.id.Canclebackbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finishAffinity();
            }
        });
        dialog.show();
    }

}