package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import java.io.File;


public class Inchworm_ShareActivity extends AppCompatActivity {
    Bundle bundle;
    String imagePath;
    ImageView imgShare;

    @Override
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this,true);

    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_save_image);
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        Bundle extras = getIntent().getExtras();
        this.bundle = extras;
        if (extras != null) {
            this.imagePath = extras.getString("imagePath");
        }
        ImageView imageView = (ImageView) findViewById(R.id.share_imageView);
        this.imgShare = imageView;
        imageView.setImageURI(Uri.parse(this.imagePath));
        findViewById(R.id.btnHome).setOnClickListener(new AnonymousClass1());
        findViewById(R.id.action_share).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Inchworm_ShareActivity cMShareActivity = Inchworm_ShareActivity.this;
                Inchworm_ShareActivity.ShareFile(cMShareActivity, cMShareActivity.imagePath);
            }
        });
    }


    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override 
        public void onClick(View view) {
                    Inchworm_ShareActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppUtilsClass.RefreshImageAlbum(Inchworm_ShareActivity.this);
                            AppUtilsClass.RefreshMoment(Inchworm_ShareActivity.this);
                        }
                    });
                    HiPhi_MainActivity.fromActivity = "";
                    Inchworm_ShareActivity.this.startActivity(new Intent(Inchworm_ShareActivity.this, Degu_MainActivity.class));
                    Inchworm_ShareActivity.this.finish();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static void ShareFile(Context context, String str) {
        Uri uriForFile;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/*");
        if (str.startsWith("content")) {
            uriForFile = Uri.parse(str);
        } else {
            uriForFile = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(str));
        }
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        context.startActivity(intent);
    }
}
