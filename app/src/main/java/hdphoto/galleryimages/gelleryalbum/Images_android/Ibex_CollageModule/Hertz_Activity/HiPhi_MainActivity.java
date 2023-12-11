package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdBitmap.BitmapResizer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery.Peyton_GalleryDataFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Duff_CollageActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Clover_FragmentHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.MusicianUtility;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Garlic_SaveImageUtils.Onion_ImageLoader;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class HiPhi_MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static HiPhi_MainActivity CMMainActivity = null;
    public static Activity context = null;
    public static String fromActivity = "";
    int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    String IMAGE_DIRECTORY_NAME = "Photo Collage";
    int MEDIA_TYPE_IMAGE = 1;
    int PRMN_CAMERA_EDITOR = 44;
    int PRMN_COLLAGE_EDITOR = 11;
    int PRMN_MIRROR_EDITOR = 55;
    int PRMN_SCRAPBOOK_EDITOR = 33;
    int PRMN_SINGLE_EDITOR = 22;
    int REQUEST_MIRROR = 3;
    Uri fileUri;
    Peyton_GalleryDataFragment galleryDataFragment;
    Onion_ImageLoader imageLoader;
    ImageView img_Camera;
    ImageView img_Collage;
    ImageView img_Mirror;
    ImageView img_ScrapBook;
    ImageView img_Single_Editor;
    RelativeLayout mMainLayout;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_collage_main);
        CMMainActivity = this;
        context = this;
        ID_Binding();
        Onion_ImageLoader cMImageLoader = new Onion_ImageLoader(this);
        this.imageLoader = cMImageLoader;
        cMImageLoader.setListener(new Onion_ImageLoader.ImageLoaded() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMainActivity.1
            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMSaveImageUtils.CMImageLoader.ImageLoaded
            public void callFileSizeDialog() {
                HiPhi_MainActivity.this.FileSizeAlertDialog();
            }
        });
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Peyton_GalleryDataFragment galleryFragment = Clover_FragmentHelper.getGalleryFragment(this);
        if (galleryFragment == null || !galleryFragment.isVisible()) {
            return;
        }
        galleryFragment.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void ID_Binding() {
        this.mMainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        this.img_Single_Editor = (ImageView) findViewById(R.id.single_editor_btn);
        this.img_Camera = (ImageView) findViewById(R.id.camera_btn);
        this.img_Collage = (ImageView) findViewById(R.id.collage_btn);
        this.img_Mirror = (ImageView) findViewById(R.id.mirror_btn);
        this.img_ScrapBook = (ImageView) findViewById(R.id.scrapbok_btn);
        this.img_Single_Editor.setOnClickListener(this);
        this.img_Camera.setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.collage_btn).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                CMMainActivity.fromActivity = "CollageActivity";
                HiPhi_MainActivity.this.OpenCollageScreen(false, false, false);
            }
        });
        findViewById(R.id.mirror_btn).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                CMMainActivity.fromActivity = "MirrorActivity";
                HiPhi_MainActivity.this.OpenMirrorGallery(false);
            }
        });
        findViewById(R.id.scrapbok_btn).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMainActivity.5
            @Override 
            public void onClick(View view) {
                CMMainActivity.fromActivity = "ScrapBookActivity";
                HiPhi_MainActivity.this.OpenCollageScreen(false, true, false);
            }
        });
    }

    public void FileSizeAlertDialog() {
        Point DecodeFileSize = BitmapResizer.DecodeFileSize(new File(this.imageLoader.selectedImagePath), MusicianUtility.GetMaxSizeForDimension(this, 1, 1500.0f));
        if (DecodeFileSize == null || DecodeFileSize.x != -1) {
            IntentShaderActivity();
        } else {
            IntentShaderActivity();
        }
    }

    private void IntentShaderActivity() {
        int GetMaxSizeForDimension = MusicianUtility.GetMaxSizeForDimension(this, 1, 1500.0f);
        Intent intent = new Intent(getApplicationContext(), Impala_MirrorActivity.class);
        intent.putExtra("selectedImagePath", this.imageLoader.selectedImagePath);
        intent.putExtra("isSession", false);
        intent.putExtra("MAX_SIZE", GetMaxSizeForDimension);
        MusicianUtility.GetFreeMemory(this);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == this.PRMN_COLLAGE_EDITOR) {
            if (ActivityCompat.checkSelfPermission(this, strArr[0]) == 0) {
                OpenCollageScreen(false, false, false);
                Toast.makeText(this, "Permission granted", 0).show();
                return;
            }
            Toast.makeText(this, "Permission denied", 0).show();
        } else if (i == this.PRMN_SINGLE_EDITOR) {
            if (ActivityCompat.checkSelfPermission(this, strArr[0]) == 0) {
                OpenCollageScreen(true, false, false);
                Toast.makeText(this, "Permission granted", 0).show();
                return;
            }
            Toast.makeText(this, "Permission denied", 0).show();
        } else if (i == this.PRMN_SCRAPBOOK_EDITOR) {
            if (ActivityCompat.checkSelfPermission(this, strArr[0]) == 0) {
                OpenCollageScreen(false, true, false);
                Toast.makeText(this, "Permission granted", 0).show();
                return;
            }
            Toast.makeText(this, "Permission denied", 0).show();
        } else if (i == this.PRMN_CAMERA_EDITOR) {
            if (ActivityCompat.checkSelfPermission(this, strArr[0]) == 0) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Uri GetOutputMediaFileUri = GetOutputMediaFileUri(this.MEDIA_TYPE_IMAGE);
                this.fileUri = GetOutputMediaFileUri;
                intent.putExtra("output", GetOutputMediaFileUri);
                startActivityForResult(intent, this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                Toast.makeText(this, "Permission granted", 0).show();
                return;
            }
            Toast.makeText(this, "Permission denied", 0).show();
        } else if (i == this.PRMN_MIRROR_EDITOR) {
            if (ActivityCompat.checkSelfPermission(this, strArr[0]) == 0) {
                Intent intent2 = new Intent();
                intent2.setType("image/*");
                intent2.setAction("android.intent.action.PICK");
                startActivityForResult(Intent.createChooser(intent2, "Select Picture"), this.REQUEST_MIRROR);
                Toast.makeText(this, "Permission granted", 0).show();
                return;
            }
            Toast.makeText(this, "Permission denied", 0).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("file_uri", this.fileUri);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.fileUri = (Uri) bundle.getParcelable("file_uri");
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            if (i == this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                if (i2 == -1) {
                    Intent intent2 = new Intent(this, Duff_CollageActivity.class);
                    PrintStream printStream = System.out;
                    printStream.println("CAMERA IMAGE PATH" + this.fileUri.getPath());
                    intent2.putExtra("selected_image_path", this.fileUri.getPath());
                    startActivity(intent2);
                    finish();
                } else if (i2 == 0) {
                    Toast.makeText(getApplicationContext(), "User cancelled image capture", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", 0).show();
                }
            } else if (i2 == -1 && i == this.REQUEST_MIRROR) {
                try {
                    this.imageLoader.getImageFromIntent(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "" + getString(R.string.error_img_not_found), 0).show();
                }
            } else {
                super.onActivityResult(i, i2, intent);
            }
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }

    @Override 
    public void onClick(View view) {
        if (view.getId() == R.id.single_editor_btn) {
            if (Build.VERSION.SDK_INT < 23) {
                OpenCollageScreen(true, false, false);
            } else if (checkAndRequestSinglePermissions()) {
                OpenCollageScreen(true, false, false);
            }
        } else if (view.getId() != R.id.camera_btn) {
        } else {
            if (Build.VERSION.SDK_INT < 23) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Uri GetOutputMediaFileUri = GetOutputMediaFileUri(this.MEDIA_TYPE_IMAGE);
                this.fileUri = GetOutputMediaFileUri;
                intent.putExtra("output", GetOutputMediaFileUri);
                startActivityForResult(intent, this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            } else if (checkAndRequestCameraPermissions()) {
                Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
                Uri GetOutputMediaFileUri2 = GetOutputMediaFileUri(this.MEDIA_TYPE_IMAGE);
                this.fileUri = GetOutputMediaFileUri2;
                intent2.putExtra("output", GetOutputMediaFileUri2);
                startActivityForResult(intent2, this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            }
        }
    }

    public boolean isAvailable(Intent intent) {
        return getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public boolean isSamSungApps() {
        for (ApplicationInfo applicationInfo : getPackageManager().getInstalledApplications(128)) {
            if (applicationInfo.packageName.equalsIgnoreCase("com.sec.android.app.samsungapps")) {
                return true;
            }
        }
        return false;
    }

    public void OpenCollageScreen(final boolean z, final boolean z2, final boolean z3) {
        getWindow().getDecorView().post(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMainActivity.6
            @Override 
            public final void run() {
                HiPhi_MainActivity.this.OpenCollageGallery(z, z2, z3);
            }
        });
    }

    public void OpenCollageGallery(boolean z, boolean z2, boolean z3) {
        Peyton_GalleryDataFragment addCollageGalleryFragment = Clover_FragmentHelper.addCollageGalleryFragment(this, R.id.gallery_fragment_container, true, null);
        this.galleryDataFragment = addCollageGalleryFragment;
        addCollageGalleryFragment.setCollageSingleMode(z);
        this.galleryDataFragment.isScrapbookCheck(z2);
        this.galleryDataFragment.setIsShape(z3);
        if (z2) {
            return;
        }
        this.galleryDataFragment.SetLimitMax(Peyton_GalleryDataFragment.MAX_COLLAGE);
    }

    public void OpenMirrorGallery(boolean z) {
        Peyton_GalleryDataFragment addMirrorGalleryFragment = Clover_FragmentHelper.addMirrorGalleryFragment(this, R.id.gallery_fragment_container, true, null);
        this.galleryDataFragment = addMirrorGalleryFragment;
        addMirrorGalleryFragment.setCollageSingleMode(z);
        this.galleryDataFragment.isMirrorCheck();
        this.galleryDataFragment.SetLimitMaxMirror(Peyton_GalleryDataFragment.MAX_MIRROR);
    }

    @Override 
    public void onBackPressed() {
        Peyton_GalleryDataFragment galleryFragment = Clover_FragmentHelper.getGalleryFragment(this);
        if (galleryFragment == null || !galleryFragment.isVisible()) {
            finish();
        } else {
            OnBackPressed.onBackPressed(this,true);

        }
    }

    public Uri GetOutputMediaFileUri(int i) {
        return Uri.fromFile(GetOutputMediaFile(i));
    }

    public File GetOutputMediaFile(int i) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), this.IMAGE_DIRECTORY_NAME);
        if (file.exists() || file.mkdirs()) {
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            if (i != this.MEDIA_TYPE_IMAGE) {
                return null;
            }
            return new File(file.getPath() + "/IMG_" + format + ".jpg");
        }
        String str = this.IMAGE_DIRECTORY_NAME;
        Log.d(str, "Oops! Failed create " + this.IMAGE_DIRECTORY_NAME + " directory");
        return null;
    }

    private boolean checkAndRequestSinglePermissions() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, "android.permission.CAMERA");
        int checkSelfPermission2 = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission2 != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.CAMERA");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), this.PRMN_SINGLE_EDITOR);
        return false;
    }

    private boolean checkAndRequestCameraPermissions() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, "android.permission.CAMERA");
        int checkSelfPermission2 = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission2 != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.CAMERA");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), this.PRMN_CAMERA_EDITOR);
        return false;
    }
}
