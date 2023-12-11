package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdActivity;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;


public class EdBaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;

    public void isPermissionGranted(boolean z, String str) {
    }

    public boolean requestPermission(String str) {
        boolean z = ContextCompat.checkSelfPermission(this, str) == 0;
        if (!z) {
            ActivityCompat.requestPermissions(this, new String[]{str}, 52);
        }
        return z;
    }

    public void CreateFullScreen() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 52) {
            isPermissionGranted(iArr[0] == 0, strArr[0]);
        }
    }

  
    public void showLoading(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.mProgressDialog = progressDialog;
        progressDialog.setMessage(str);
        this.mProgressDialog.setProgressStyle(0);
        this.mProgressDialog.setCancelable(false);
        this.mProgressDialog.show();
    }

  
    public void HideLoading() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

  
    public void ShowSnackBar(String str) {
        View findViewById = findViewById(16908290);
        if (findViewById != null) {
            Snackbar.make(findViewById, str, -1).show();
        } else {
            Toast.makeText(this, str, 0).show();
        }
    }
}
