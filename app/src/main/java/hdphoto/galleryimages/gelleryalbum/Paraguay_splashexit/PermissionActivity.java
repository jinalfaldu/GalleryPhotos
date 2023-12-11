package hdphoto.galleryimages.gelleryalbum.Paraguay_splashexit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.ImageAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.MomentData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.VideoAlbumData;

import java.util.ArrayList;
import java.util.HashMap;

import unified.vpn.sdk.ResponseResultCodes;
import hdphoto.galleryimages.gelleryalbum.Bicycle_extra.FirstActivity;


public class PermissionActivity extends AppCompatActivity {
    public static ImageAlbumData getImageAlbumData;
    public static MomentData getMomentData;
    public static VideoAlbumData getVideoAlbumData;
    ImageView allowPermission;
    private boolean blnStart = false;
    boolean s1;
    SharedPreferences sh;

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_permission);
        RelativeLayout rlAllow = findViewById(R.id.rlAllow);

        rlAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Splashactivity.hasAndroidPermissions = true;
                SharedPreferences.Editor edit = PermissionActivity.this.getSharedPreferences("MySharedPref", 0).edit();
                edit.putBoolean("name", true);
                edit.commit();
                PermissionActivity.this.blnStart = true;
                if (!Settings.System.canWrite(PermissionActivity.this)) {
                    Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                    intent.setData(Uri.parse("package:" + PermissionActivity.this.getPackageName()));
                    intent.addFlags(268435456);
                    PermissionActivity.this.startActivity(intent);
                } else if (Build.VERSION.SDK_INT < 23) {
                    PermissionActivity.this.callnext();
                } else if (PermissionActivity.this.checkAndRequestPermissions()) {
                    PermissionActivity.this.callnext();
                }
            }
        });
    }

    public void callnext() {
        startActivity(new Intent(PermissionActivity.this, FirstActivity.class));

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

    public boolean checkAndRequestPermissions() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        int checkSelfPermission2 = ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
        int checkSelfPermission3 = ContextCompat.checkSelfPermission(this, "android.permission.CAMERA");
        int checkSelfPermission4 = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission2 != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission3 != 0) {
            arrayList.add("android.permission.CAMERA");
        }
        if (checkSelfPermission4 != 0) {
            arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        return false;
    }

    @Override

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            HashMap hashMap = new HashMap();
            hashMap.put("android.permission.WRITE_EXTERNAL_STORAGE", 0);
            hashMap.put("android.permission.READ_EXTERNAL_STORAGE", 0);
            hashMap.put("android.permission.CAMERA", 0);
            hashMap.put("android.permission.ACCESS_COARSE_LOCATION", 0);
            if (iArr.length > 0) {
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    hashMap.put(strArr[i2], Integer.valueOf(iArr[i2]));
                }
                if (((Integer) hashMap.get("android.permission.WRITE_EXTERNAL_STORAGE")).intValue() == 0 && ((Integer) hashMap.get("android.permission.READ_EXTERNAL_STORAGE")).intValue() == 0 && ((Integer) hashMap.get("android.permission.ACCESS_COARSE_LOCATION")).intValue() == 0 && ((Integer) hashMap.get("android.permission.CAMERA")).intValue() == 0) {
                    if (this.blnStart) {
                        callnext();
                    }
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_COARSE_LOCATION")) {
                    showDialogOK("Permission required for this app", new DialogInterface.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.splashexit.PermissionActivity.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i3) {
                            if (i3 == -1) {
                                PermissionActivity.this.checkAndRequestPermissions();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "Go to settings and enable permissions", 0).show();
                }
            }
        }
    }

    private void showDialogOK(String str, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(this).setMessage(str).setPositiveButton(ResponseResultCodes.OK, onClickListener).setNegativeButton("Cancel", onClickListener).create().show();
    }

    @Override 
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this, true);

    }
}
