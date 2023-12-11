package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.core.app.ActivityCompat;

import hdphoto.galleryimages.gelleryalbum.R;
import com.google.android.material.snackbar.Snackbar;


public class Degu_PermissionHelperActivity extends BseActivity {
    private final String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    protected View view;

    public void hideViews() {
    }

    public void permissionGranted() {
    }


    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            showRequestPermissionRationale();
        } else {
            showAppPermissionSettings();
        }
    }

    private void showRequestPermissionRationale() {
        Snackbar.make(this.view, getString(R.string.permission_info), -2).setAction(getString(R.string.permission_ok), new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PermissionHelperActivity gMPermissionHelperActivity = Degu_PermissionHelperActivity.this;
                ActivityCompat.requestPermissions(gMPermissionHelperActivity, gMPermissionHelperActivity.permissions, 1000);
            }
        }).show();
    }

    private void showAppPermissionSettings() {
        Snackbar.make(this.view, getString(R.string.permission_force), -2).setAction(getString(R.string.permission_settings), new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Uri fromParts = Uri.fromParts(Degu_PermissionHelperActivity.this.getString(R.string.permission_package), Degu_PermissionHelperActivity.this.getPackageName(), null);
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addFlags(1073741824);
                intent.setData(fromParts);
                Degu_PermissionHelperActivity.this.startActivityForResult(intent, 1000);
            }
        }).show();
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 1000 || iArr.length == 0 || iArr[0] == -1) {
            permissionDenied();
        } else {
            permissionGranted();
        }
    }

    private void permissionDenied() {
        hideViews();
        requestPermission();
    }

    public void setView(View view) {
        this.view = view;
    }
}
