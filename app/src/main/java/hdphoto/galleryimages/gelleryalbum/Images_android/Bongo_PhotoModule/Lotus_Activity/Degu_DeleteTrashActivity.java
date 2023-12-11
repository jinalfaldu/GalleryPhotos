package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImageDeleteFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoDeleteFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;


public class Degu_DeleteTrashActivity extends BseActivity {
    public static ImageView allDelete;
    public static RelativeLayout rl_dataLayout;
    Activity activity;
    ImageView btnBack;
    ImImageDeleteFragment imageDeleteFragment;
    View img_photo;
    View img_video;
    LinearLayout layPhotos;
    LinearLayout layVideos;
    PrefClass preferenceClass;
    TextView txtPhotos;
    TextView txtVideos;
    ViVideoDeleteFragment videoDeleteFragment;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_delete_trash);
        this.activity = this;
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        this.preferenceClass = new PrefClass(getApplicationContext());
        allDelete = (ImageView) findViewById(R.id.allDelete);
        rl_dataLayout = (RelativeLayout) findViewById(R.id.rl_dataLayout);
        ImageView imageView = (ImageView) findViewById(R.id.btnBack);
        this.btnBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Degu_DeleteTrashActivity.this.onBackPressed();
            }
        });
        allDelete.setVisibility(0);
        rl_dataLayout.setVisibility(0);
        ID_Binding();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override 
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this,true);


    }

    private void ID_Binding() {
        this.layPhotos = (LinearLayout) findViewById(R.id.layPhotos);
        this.layVideos = (LinearLayout) findViewById(R.id.layVideos);
        this.txtPhotos = (TextView) findViewById(R.id.txtPhotos);
        this.txtVideos = (TextView) findViewById(R.id.txtVideos);
        this.img_photo =  findViewById(R.id.img_photo);
        this.img_video =  findViewById(R.id.img_video);
        this.imageDeleteFragment = new ImImageDeleteFragment();
        this.videoDeleteFragment = new ViVideoDeleteFragment();
        changeFragment(new ImImageDeleteFragment());
        Degu_MainActivity.privateclick = 0;
        this.layPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPhotos.setTextColor(getResources().getColor(R.color.tab_txt));
                txtVideos.setTextColor(getResources().getColor(R.color.gray_73));
                img_photo.setVisibility(View.VISIBLE);
                img_video.setVisibility(View.INVISIBLE);
                Degu_MainActivity.privateclick = 0;
                Degu_DeleteTrashActivity.this.changeFragment(new ImImageDeleteFragment());
            }
        });
        this.layVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPhotos.setTextColor(getResources().getColor(R.color.gray_73));
                txtVideos.setTextColor(getResources().getColor(R.color.tab_txt));
                img_photo.setVisibility(View.INVISIBLE);
                img_video.setVisibility(View.VISIBLE);
                Degu_MainActivity.privateclick = 1;
                Degu_DeleteTrashActivity.this.changeFragment(new ViVideoDeleteFragment());
            }
        });
    }

    public void changeFragment(Fragment fragment) {
        Degu_MainActivity.ivSelectAll.setImageDrawable(getResources().getDrawable(R.drawable.action_select));
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.delete_frame_layout, fragment);
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        beginTransaction.commit();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
