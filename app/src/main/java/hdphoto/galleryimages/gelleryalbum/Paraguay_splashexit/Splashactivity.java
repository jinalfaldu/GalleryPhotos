package hdphoto.galleryimages.gelleryalbum.Paraguay_splashexit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.ImageAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.MomentData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.VideoAlbumData;
import hdphoto.galleryimages.gelleryalbum.Bicycle_extra.FirstActivity;


public class Splashactivity extends AppCompatActivity {
    public static ImageAlbumData getImageAlbumData = null;
    public static MomentData getMomentData = null;
    public static VideoAlbumData getVideoAlbumData = null;
    public static boolean hasAndroidPermissions = false;
    boolean s1;
    SharedPreferences sh;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.iuc_splashactivity);
        getWindow().setFlags(1024, 1024);
        goto_act();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", 0);
        this.sh = sharedPreferences;
        this.s1 = sharedPreferences.getBoolean("name", false);
        hasAndroidPermissions = hasPermissions("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.CAMERA", "android.permission.ACCESS_COARSE_LOCATION");
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

    public boolean hasPermissions(String... strArr) {
        boolean z = true;
        for (String str : strArr) {
            if (!hasPermission(str)) {
                z = false;
            }
        }
        return z;
    }

    public boolean hasPermission(String str) {
        int checkCallingOrSelfPermission = checkCallingOrSelfPermission(str);
        StringBuilder sb = new StringBuilder();
        sb.append("permission: ");
        sb.append(str);
        sb.append(" = \t\t");
        sb.append(checkCallingOrSelfPermission == 0 ? "GRANTED" : "DENIED");
        Log.v("TAG", sb.toString());
        return checkCallingOrSelfPermission == 0;
    }

    private void goto_act() {
        if (!this.s1) {
            startActivity(new Intent(this, PermissionActivity.class));
            finish();
            return;
        }
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }
}
