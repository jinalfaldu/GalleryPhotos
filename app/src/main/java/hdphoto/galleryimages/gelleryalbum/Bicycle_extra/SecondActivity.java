package hdphoto.galleryimages.gelleryalbum.Bicycle_extra;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.ADSharedPref;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.ImageAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.MomentData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.VideoAlbumData;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv_start_now;
    LinearLayout ll_rate, ll_share, ll_pp;
    SecondActivity activity;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        getWindow().setFlags(1024, 1024);
        activity = this;
        new NativeHelper().shownativeads(this, findViewById(R.id.native_container));
        tv_start_now = findViewById(R.id.tv_start_now);
        ll_rate = findViewById(R.id.rate);
        ll_share = findViewById(R.id.share);
        ll_pp = findViewById(R.id.pp);

        tv_start_now.setOnClickListener(v -> {
            String extraPage = ADSharedPref.getString(activity, ADSharedPref.EXTRA_PAGE, "0");
            if (extraPage.equals("1")) {
                IntentPass.intent(activity, Degu_MainActivity.class, true, new Bundle());
            } else {
                IntentPass.intent(activity, ThreeActivity.class, true, new Bundle());
            }
        });
    }

    public static void binding(Activity activity) {
        MomentData gMGetMomentData = new MomentData(activity);
        gMGetMomentData.getMomentAllAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        ImageAlbumData gMGetImageAlbumData = new ImageAlbumData(activity);
        gMGetImageAlbumData.getImageAlbumAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        VideoAlbumData gMGetVideoAlbumData = new VideoAlbumData(activity);
        gMGetVideoAlbumData.getVideoAlbumAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Override
    public void onBackPressed() {
        IntentPass.intent(activity, FirstActivity.class, true, new Bundle());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case R.id.rate:
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName()));
                try {
                    startActivity(intent2);
                } catch (ActivityNotFoundException unused) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;

            case R.id.pp:
                    String policy = ADSharedPref.getString(this, ADSharedPref.Privacy_police, "0");
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(policy)));
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(policy)));
                    }

                break;
        }
    }
}