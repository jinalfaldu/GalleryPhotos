package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Heart_Adapter.Azure_FilterAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdBitmap.BitmapResizer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.ApplyTextInterface;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.BaseData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CustomRelativeLayout;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtDecorateView;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtTextData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtTextLibHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.SingleTap;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments.RiverFilterEffectFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments.RiverFontFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery.Peyton_GalleryDataFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Aspid_Utility;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.Jumper_EffectUtility;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.Jumper_MirrorMode;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_TextSticker;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Impala_MirrorActivity extends AppCompatActivity implements RiverFontFragment.OnFontSelectedListener {
    static final String TAG = "MirrorNewActivity";
    public static RelativeLayout rl_bottom;
    public static RelativeLayout rl_toolbar;
    FragmentActivity activityFragment;
    CustomRelativeLayout customRelativeLayout;
    ImageView[] d3ButtonArray;
    Bitmap filterBitmap;
    RiverFilterEffectFragment filterEffectFragment;
    RiverFontFragment fontFragment;
    int height;
    ImageView img_mirror;
    ImageView img_mirror_3d;
    ImageView img_mirror_adj;
    ImageView img_mirror_effect;
    ImageView img_mirror_frame;
    ImageView img_mirror_ratio;
    ImageView img_mirror_text;
    RelativeLayout mainLayout;
    ImageView[] mirrorEffectArray;
    TextView[] mirrorRatioArray;
    MirrorView mirrorView;
    Dialog saveImageDialog;
    int screenHeightPixels;
    int screenWidthPixels;
    Animation slideInLeft;
    Animation slideInRight;
    Animation slideOutLeft;
    Animation slideOutRight;
    Bitmap sourceBitmap;
    View[] tabButtonList;
    FrameLayout textAndStickerViewContainer;
    CtTextLibHelper textLibHelper;
    ViewFlipper viewFlipper;
    int width;
    int D3_BUTTON_SIZE = 24;
    int MIRROR_BUTTON_SIZE = 15;
    int RATIO_BUTTON_SIZE = 11;
    int currentSelectedTabIndex = -1;
    int[] Shape3DList = {R.drawable.mirror_3d_14, R.drawable.mirror_3d_14, R.drawable.mirror_3d_10, R.drawable.mirror_3d_10, R.drawable.mirror_3d_11, R.drawable.mirror_3d_11, R.drawable.mirror_3d_4, R.drawable.mirror_3d_4, R.drawable.mirror_3d_3, R.drawable.mirror_3d_3, R.drawable.mirror_3d_1, R.drawable.mirror_3d_1, R.drawable.mirror_3d_6, R.drawable.mirror_3d_6, R.drawable.mirror_3d_13, R.drawable.mirror_3d_13, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16};
    int initialYPos = 0;
    Matrix matrixMirror1 = new Matrix();
    Matrix matrixMirror2 = new Matrix();
    Matrix matrixMirror3 = new Matrix();
    Matrix matrixMirror4 = new Matrix();
    float mulX = 16.0f;
    float mulY = 16.0f;
    boolean showText = false;
    ArrayList<CtTextData> textDataList = new ArrayList<>();
    int textFragemntContinerId = R.id.text_view_fragment_container;
    Matrix matrix = new Matrix();
    final Handler collageVisibilityHandler = new Handler();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mirror);
        Bundle extras = getIntent().getExtras();
        Bitmap DecodeBitmapFromFile = BitmapResizer.DecodeBitmapFromFile(extras.getString("selectedImagePath"), extras.getInt("MAX_SIZE"));
        this.sourceBitmap = DecodeBitmapFromFile;
        if (DecodeBitmapFromFile == null) {
            Toast makeText = Toast.makeText(this, "Could not load the photo, please use another GALLERY app!", 0);
            makeText.setGravity(17, makeText.getXOffset() / 2, makeText.getYOffset() / 2);
            makeText.show();
            finish();
            return;
        }
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_scale_image);
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                relativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int measuredWidth = relativeLayout.getMeasuredWidth();
                int measuredHeight = relativeLayout.getMeasuredHeight();
                Impala_MirrorActivity.this.width = measuredWidth;
                Impala_MirrorActivity.this.height = measuredHeight - 100;
                Impala_MirrorActivity cMMirrorActivity = Impala_MirrorActivity.this;
                cMMirrorActivity.screenWidthPixels = cMMirrorActivity.width;
                Impala_MirrorActivity cMMirrorActivity2 = Impala_MirrorActivity.this;
                cMMirrorActivity2.screenHeightPixels = cMMirrorActivity2.height;
            }
        });
        rl_toolbar = (RelativeLayout) findViewById(R.id.rl_toolbar);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        this.img_mirror = (ImageView) findViewById(R.id.button_mirror);
        this.img_mirror_3d = (ImageView) findViewById(R.id.button_mirror_3d);
        this.img_mirror_ratio = (ImageView) findViewById(R.id.button_mirror_ratio);
        this.img_mirror_effect = (ImageView) findViewById(R.id.button_mirror_effect);
        this.img_mirror_frame = (ImageView) findViewById(R.id.button_mirror_frame);
        this.img_mirror_text = (ImageView) findViewById(R.id.button_mirror_text);
        this.img_mirror_adj = (ImageView) findViewById(R.id.button_mirror_adj);
        new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.2
            @Override 
            public void run() {
                Impala_MirrorActivity cMMirrorActivity = Impala_MirrorActivity.this;
                cMMirrorActivity.mirrorView = new MirrorView(cMMirrorActivity, cMMirrorActivity.screenWidthPixels, Impala_MirrorActivity.this.screenHeightPixels);
                Impala_MirrorActivity cMMirrorActivity2 = Impala_MirrorActivity.this;
                cMMirrorActivity2.mainLayout = (RelativeLayout) cMMirrorActivity2.findViewById(R.id.layout_mirror_activity);
                Impala_MirrorActivity.this.mainLayout.addView(Impala_MirrorActivity.this.mirrorView);
            }
        }, 500L);
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.mirror_view_flipper);
        this.viewFlipper = viewFlipper;
        viewFlipper.bringToFront();
        findViewById(R.id.mirror_footer).bringToFront();
        this.slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        this.slideOutLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        this.slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        this.slideOutRight = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        AddEffectFragment();
        SetSelectedTab(0);
        new BitmapWorkerTask().execute(extras, bundle);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.3
            @Override 
            public void onClick(View view) {
                Impala_MirrorActivity.this.SaveImageAlertDialog();
            }
        });
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.4
            @Override 
            public void onClick(View view) {
                new SaveImageTask().execute(new Object[0]);
            }
        });
    }


    private class BitmapWorkerTask extends AsyncTask<Bundle, Void, Void> {
        int arraySize;
        Bundle data;
        ProgressDialog progressDialog;
        Bundle savedInstanceState;

        BitmapWorkerTask() {
        }

        @Override 
        public void onPreExecute() {
            ProgressDialog progressDialog = new ProgressDialog(Impala_MirrorActivity.this);
            this.progressDialog = progressDialog;
            progressDialog.setCancelable(false);
            this.progressDialog.setMessage("loading images!");
            this.progressDialog.show();
        }

        @Override 
        public Void doInBackground(Bundle... bundleArr) {
            this.data = bundleArr[0];
            this.savedInstanceState = bundleArr[1];
            return null;
        }

        @Override 
        public void onPostExecute(Void r4) {
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
            Impala_MirrorActivity.this.textLibHelper = new CtTextLibHelper();
            if (this.savedInstanceState != null) {
                Impala_MirrorActivity.this.textLibHelper.hideForOnCreate(Impala_MirrorActivity.this.activityFragment, Impala_MirrorActivity.this.textAndStickerViewContainer, Impala_MirrorActivity.this.textFragemntContinerId);
            }
        }
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.OnFontSelectedListener
    public void onFontSelected(CtTextData cMTextData) {
        this.customRelativeLayout.addTextView(cMTextData);
        getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.OnFontSelectedListener
    public void onFontSelectionCancelled() {
        this.showText = true;
        this.mainLayout.removeView(this.customRelativeLayout);
        this.mirrorView.postInvalidate();
    }

    @Override

    public void onPause() {
        super.onPause();
    }

    public void AddEffectFragment() {
        if (this.filterEffectFragment == null) {
            RiverFilterEffectFragment cMFilterEffectFragment = (RiverFilterEffectFragment) getSupportFragmentManager().findFragmentByTag("MY_EFFECT_FRAGMENT");
            this.filterEffectFragment = cMFilterEffectFragment;
            if (cMFilterEffectFragment == null) {
                RiverFilterEffectFragment cMFilterEffectFragment2 = new RiverFilterEffectFragment();
                this.filterEffectFragment = cMFilterEffectFragment2;
                cMFilterEffectFragment2.setBitmap(this.sourceBitmap);
                this.filterEffectFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.mirror_effect_fragment_container, this.filterEffectFragment, "MY_EFFECT_FRAGMENT").commit();
            } else {
                cMFilterEffectFragment.setBitmap(this.sourceBitmap);
                this.filterEffectFragment.SetSelectedTabIndex(0);
            }
            this.filterEffectFragment.setBitmapReadyListener(new RiverFilterEffectFragment.BitmapReady() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.5
                @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFilterEffectFragment.BitmapReady
                public void onBitmapReady(Bitmap bitmap) {
                    Impala_MirrorActivity.this.filterBitmap = bitmap;
                    Impala_MirrorActivity.this.mirrorView.postInvalidate();
                }
            });
            this.filterEffectFragment.setBorderIndexChangedListener(new Azure_FilterAdapter.IndexChangedListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.6
                @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMAdapter.CMFilterAdapter.RecyclerAdapterIndexChangedListener
                public void onIndexChanged(int i) {
                    Impala_MirrorActivity.this.mirrorView.SetFrame(i);
                }
            });
        }
    }

    public void onMirrorLayout(View view) {
        SetSelectedTab(0);
        ChangeBtnSelection(0);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        rl_toolbar.setVisibility(0);
    }

    public void onShape(View view) {
        SetSelectedTab(1);
        ChangeBtnSelection(1);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        rl_toolbar.setVisibility(0);
    }

    public void onRatio(View view) {
        SetSelectedTab(2);
        ChangeBtnSelection(2);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        rl_toolbar.setVisibility(0);
    }

    public void onMirrorEffect(View view) {
        SetSelectedTab(3);
        ChangeBtnSelection(3);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        rl_toolbar.setVisibility(0);
    }

    public void onMirrorFrame(View view) {
        SetSelectedTab(4);
        ChangeBtnSelection(4);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        rl_toolbar.setVisibility(0);
    }

    public void onMirrorText(View view) {
        HiPhi_MainActivity.fromActivity = "MirrorActivity";
        AddCanvasTextView();
        ClearViewFlipper();
        ChangeBtnSelection(5);
        rl_toolbar.setVisibility(8);
    }

    public void onMirrorFilter(View view) {
        SetSelectedTab(5);
        ChangeBtnSelection(6);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        rl_toolbar.setVisibility(0);
    }

    public void onRatio11(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 1.0f;
        this.mulY = 1.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(0);
    }

    public void onRatio21(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 2.0f;
        this.mulY = 1.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(1);
    }

    public void onRatio12(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 1.0f;
        this.mulY = 2.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(2);
    }

    public void onRatio32(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 3.0f;
        this.mulY = 2.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(3);
    }

    public void onRatio23(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 2.0f;
        this.mulY = 3.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(4);
    }

    public void onRatio43(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 4.0f;
        this.mulY = 3.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(5);
    }

    public void onRatio34(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 3.0f;
        this.mulY = 4.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(6);
    }

    public void onRatio45(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 4.0f;
        this.mulY = 5.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(7);
    }

    public void onRatio57(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 5.0f;
        this.mulY = 7.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(8);
    }

    public void onRatio169(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 16.0f;
        this.mulY = 9.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(9);
    }

    public void onRatio916(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mulX = 9.0f;
        this.mulY = 16.0f;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetRatioButtonBg(10);
    }

    public void button_m1(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(0);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(0);
    }

    public void button_m2(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(1);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(1);
    }

    public void button_m3(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(2);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(2);
    }

    public void button_m4(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(3);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(3);
    }

    public void button_m5(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(4);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(4);
    }

    public void button_m6(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(5);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(5);
    }

    public void button_m7(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(6);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(6);
    }

    public void button_m8(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(7);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(7);
    }

    public void button_m9(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(8);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(8);
    }

    public void button_m10(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(9);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(9);
    }

    public void button_m11(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(10);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(10);
    }

    public void button_m12(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(11);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(11);
    }

    public void button_m13(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(12);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(12);
    }

    public void button_m14(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(13);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(13);
    }

    public void button_m15(View view) {
        this.mirrorView.drawSavedImage = false;
        this.mirrorView.setCurrentMode(14);
        this.mirrorView.d3Mode = false;
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
        SetMirrorButtonBg(14);
    }

    public void btn3D1(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(0);
    }

    public void btn3D2(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(1);
    }

    public void btn3D3(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(2);
    }

    public void btn3D4(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(3);
    }

    public void btn3D5(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(4);
    }

    public void btn3D6(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(5);
    }

    public void btn3D7(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(6);
    }

    public void btn3D8(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(7);
    }

    public void btn3D9(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(8);
    }

    public void btn3D10(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(9);
    }

    public void btn3D11(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(10);
    }

    public void btn3D12(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(11);
    }

    public void btn3D13(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(12);
    }

    public void btn3D14(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(13);
    }

    public void btn3D15(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(14);
    }

    public void btn3D16(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(15);
    }

    public void btn3D17(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(16);
    }

    public void btn3D18(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(17);
    }

    public void btn3D19(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(18);
    }

    public void btn3D20(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(19);
    }

    public void btn3D21(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(20);
    }

    public void btn3D22(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(21);
    }

    public void btn3D23(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(22);
    }

    public void btn3D24(View view) {
        this.mirrorView.drawSavedImage = false;
        Set3DMode(23);
    }


    private final class MyMediaScannerConnectionClient implements MediaScannerConnection.MediaScannerConnectionClient {
        private final MediaScannerConnection mConn;
        private final String mFilename;
        private final String mMimetype;

        private MyMediaScannerConnectionClient(Context context, File file, String str) {
            this.mFilename = file.getAbsolutePath();
            MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(context, this);
            this.mConn = mediaScannerConnection;
            mediaScannerConnection.connect();
            this.mMimetype = str;
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            this.mConn.scanFile(this.mFilename, this.mMimetype);
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            this.mConn.disconnect();
        }
    }


    private class SaveImageTask extends AsyncTask<Object, Object, Object> {
        ProgressDialog progressDialog;
        String resultPath;

        private SaveImageTask() {
            this.resultPath = null;
            this.progressDialog = new ProgressDialog(Impala_MirrorActivity.this);
        }

        @Override 
        public Object doInBackground(Object... objArr) {
            try {
                this.resultPath = Impala_MirrorActivity.this.mirrorView.SaveBitmap(true, Impala_MirrorActivity.this.screenWidthPixels, Impala_MirrorActivity.this.screenHeightPixels);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Saving image...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override 
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (Impala_MirrorActivity.this.saveImageDialog != null && Impala_MirrorActivity.this.saveImageDialog.isShowing()) {
                Impala_MirrorActivity.this.saveImageDialog.dismiss();
            }
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.cancel();
            }
            try {
                if (this.resultPath != null) {
                    HiPhi_MainActivity.CMMainActivity.finish();
                    Peyton_GalleryDataFragment.selectedImages.clear();
                    AppUtilsClass.RefreshImageAlbum(Impala_MirrorActivity.this);
                    AppUtilsClass.RefreshMoment(Impala_MirrorActivity.this);
                    Intent intent = new Intent(Impala_MirrorActivity.this, Inchworm_ShareActivity.class);
                    intent.putExtra("imagePath", this.resultPath);
                    Impala_MirrorActivity.this.startActivity(intent);
                    Impala_MirrorActivity.this.finish();
                }
                Impala_MirrorActivity cMMirrorActivity = Impala_MirrorActivity.this;
                new MyMediaScannerConnectionClient(cMMirrorActivity.getApplicationContext(), new File(this.resultPath), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override 
    public void onResume() {
        super.onResume();
    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
        Bitmap bitmap = this.sourceBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        Bitmap bitmap2 = this.filterBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("show_text", this.showText);
        bundle.putSerializable(RiverFontFragment.TEXT_DATA_BUNDLE_KEY, this.textDataList);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        CtTextLibHelper cMTextLibHelper = this.textLibHelper;
        if (cMTextLibHelper != null) {
            cMTextLibHelper.saveTextAndStickerData(bundle, this.textAndStickerViewContainer, null);
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.textLibHelper == null) {
            this.textLibHelper = new CtTextLibHelper();
        }
        this.showText = bundle.getBoolean("show_text");
        ArrayList<CtTextData> arrayList = (ArrayList) bundle.getSerializable(RiverFontFragment.TEXT_DATA_BUNDLE_KEY);
        this.textDataList = arrayList;
        if (arrayList == null) {
            this.textDataList = new ArrayList<>();
        }
        CtTextLibHelper cMTextLibHelper = this.textLibHelper;
        if (cMTextLibHelper != null) {
            cMTextLibHelper.loadTextAndStickerData(this.activityFragment, bundle, this.textAndStickerViewContainer, this.textFragemntContinerId, null);
        }
    }

    public void myClickHandler(View view) {
        int id = view.getId();
        this.mirrorView.drawSavedImage = false;
        if (id == R.id.button_mirror) {
            SetSelectedTab(0);
        } else if (id == R.id.button_mirror_frame) {
            SetSelectedTab(4);
        } else if (id == R.id.button_mirror_ratio) {
            SetSelectedTab(2);
        } else if (id == R.id.button_mirror_effect) {
            SetSelectedTab(3);
        } else if (id == R.id.button_mirror_adj) {
            SetSelectedTab(5);
        } else if (id == R.id.button_mirror_3d) {
            SetSelectedTab(1);
        } else if (id == R.id.button_3d_1) {
            Set3DMode(0);
        } else if (id == R.id.button_3d_2) {
            Set3DMode(1);
        } else if (id == R.id.button_3d_3) {
            Set3DMode(2);
        } else if (id == R.id.button_3d_4) {
            Set3DMode(3);
        } else if (id == R.id.button_3d_5) {
            Set3DMode(4);
        } else if (id == R.id.button_3d_6) {
            Set3DMode(5);
        } else if (id == R.id.button_3d_7) {
            Set3DMode(6);
        } else if (id == R.id.button_3d_8) {
            Set3DMode(7);
        } else if (id == R.id.button_3d_9) {
            Set3DMode(8);
        } else if (id == R.id.button_3d_10) {
            Set3DMode(9);
        } else if (id == R.id.button_3d_11) {
            Set3DMode(10);
        } else if (id == R.id.button_3d_12) {
            Set3DMode(11);
        } else if (id == R.id.button_3d_13) {
            Set3DMode(12);
        } else if (id == R.id.button_3d_14) {
            Set3DMode(13);
        } else if (id == R.id.button_3d_15) {
            Set3DMode(14);
        } else if (id == R.id.button_3d_16) {
            Set3DMode(15);
        } else if (id == R.id.button_3d_17) {
            Set3DMode(16);
        } else if (id == R.id.button_3d_18) {
            Set3DMode(17);
        } else if (id == R.id.button_3d_19) {
            Set3DMode(18);
        } else if (id == R.id.button_3d_20) {
            Set3DMode(19);
        } else if (id == R.id.button_3d_21) {
            Set3DMode(20);
        } else if (id == R.id.button_3d_22) {
            Set3DMode(21);
        } else if (id == R.id.button_3d_23) {
            Set3DMode(22);
        } else if (id == R.id.button_3d_24) {
            Set3DMode(23);
        } else if (id == R.id.button11) {
            this.mulX = 1.0f;
            this.mulY = 1.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(0);
        } else if (id == R.id.button21) {
            this.mulX = 2.0f;
            this.mulY = 1.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(1);
        } else if (id == R.id.button12) {
            this.mulX = 1.0f;
            this.mulY = 2.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(2);
        } else if (id == R.id.button32) {
            this.mulX = 3.0f;
            this.mulY = 2.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(3);
        } else if (id == R.id.button23) {
            this.mulX = 2.0f;
            this.mulY = 3.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(4);
        } else if (id == R.id.button43) {
            this.mulX = 4.0f;
            this.mulY = 3.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(5);
        } else if (id == R.id.button34) {
            this.mulX = 3.0f;
            this.mulY = 4.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(6);
        } else if (id == R.id.button45) {
            this.mulX = 4.0f;
            this.mulY = 5.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(7);
        } else if (id == R.id.button57) {
            this.mulX = 5.0f;
            this.mulY = 7.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(8);
        } else if (id == R.id.button169) {
            this.mulX = 16.0f;
            this.mulY = 9.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(9);
        } else if (id == R.id.button916) {
            this.mulX = 9.0f;
            this.mulY = 16.0f;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetRatioButtonBg(10);
        } else if (id == R.id.button_m1) {
            this.mirrorView.setCurrentMode(0);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(0);
        } else if (id == R.id.button_m2) {
            this.mirrorView.setCurrentMode(1);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(1);
        } else if (id == R.id.button_m3) {
            this.mirrorView.setCurrentMode(2);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(2);
        } else if (id == R.id.button_m4) {
            this.mirrorView.setCurrentMode(3);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(3);
        } else if (id == R.id.button_m5) {
            this.mirrorView.setCurrentMode(4);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(4);
        } else if (id == R.id.button_m6) {
            this.mirrorView.setCurrentMode(5);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(5);
        } else if (id == R.id.button_m7) {
            this.mirrorView.setCurrentMode(6);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(6);
        } else if (id == R.id.button_m8) {
            this.mirrorView.setCurrentMode(7);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(7);
        } else if (id == R.id.button_m9) {
            this.mirrorView.setCurrentMode(8);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(8);
        } else if (id == R.id.button_m10) {
            this.mirrorView.setCurrentMode(9);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(9);
        } else if (id == R.id.button_m11) {
            this.mirrorView.setCurrentMode(10);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(10);
        } else if (id == R.id.button_m12) {
            this.mirrorView.setCurrentMode(11);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(11);
        } else if (id == R.id.button_m13) {
            this.mirrorView.setCurrentMode(12);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(12);
        } else if (id == R.id.button_m14) {
            this.mirrorView.setCurrentMode(13);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(13);
        } else if (id == R.id.button_m15) {
            this.mirrorView.setCurrentMode(14);
            this.mirrorView.d3Mode = false;
            this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, true);
            SetMirrorButtonBg(14);
        } else if (id == R.id.button_mirror_text) {
            AddCanvasTextView();
            ClearViewFlipper();
        }
    }

    private void ClearFxAndFrame() {
        int GetSelectedTabIndex = this.filterEffectFragment.GetSelectedTabIndex();
        int i = this.currentSelectedTabIndex;
        if (i == 3 || i == 4) {
            if (GetSelectedTabIndex == 0 || GetSelectedTabIndex == 1) {
                ClearViewFlipper();
            }
        }
    }

    public void AddCanvasTextView() {
        CustomRelativeLayout cMCustomRelativeLayout = new CustomRelativeLayout(this, this.textDataList, this.mirrorView.mainMatrix, new SingleTap() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.7
            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.SingleTap
            public void onSingleTap(CtTextData cMTextData) {
                Impala_MirrorActivity.this.fontFragment = new RiverFontFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(RiverFontFragment.TEXT_DATA_BUNDLE_KEY, cMTextData);
                Impala_MirrorActivity.this.fontFragment.setArguments(bundle);
                Impala_MirrorActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.text_view_fragment_container, Impala_MirrorActivity.this.fontFragment, "FONT_FRAGMENT").commit();
            }
        });
        this.customRelativeLayout = cMCustomRelativeLayout;
        cMCustomRelativeLayout.setApplyTextListener(new ApplyTextInterface() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.8
            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.ApplyTextInterface
            public void onCancel() {
                Impala_MirrorActivity.this.showText = true;
                Impala_MirrorActivity.this.mainLayout.removeView(Impala_MirrorActivity.this.customRelativeLayout);
                Impala_MirrorActivity.this.mirrorView.postInvalidate();
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.ApplyTextInterface
            public void onOk(ArrayList<CtTextData> arrayList) {
                Iterator<CtTextData> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().setImageSaveMatrix(Impala_MirrorActivity.this.mirrorView.mainMatrix);
                }
                Impala_MirrorActivity.this.textDataList = arrayList;
                Impala_MirrorActivity.this.showText = true;
                if (Impala_MirrorActivity.this.mainLayout == null) {
                    Impala_MirrorActivity cMMirrorActivity = Impala_MirrorActivity.this;
                    cMMirrorActivity.mainLayout = (RelativeLayout) cMMirrorActivity.findViewById(R.id.layout_mirror_activity);
                }
                Impala_MirrorActivity.this.mainLayout.removeView(Impala_MirrorActivity.this.customRelativeLayout);
                Impala_MirrorActivity.this.mirrorView.postInvalidate();
            }
        });
        this.showText = false;
        this.mirrorView.invalidate();
        this.mainLayout.addView(this.customRelativeLayout);
        findViewById(R.id.text_view_fragment_container).bringToFront();
        RiverFontFragment cMFontFragment = new RiverFontFragment();
        this.fontFragment = cMFontFragment;
        cMFontFragment.setArguments(new Bundle());
        getSupportFragmentManager().beginTransaction().add(R.id.text_view_fragment_container, this.fontFragment, "FONT_FRAGMENT").commit();
    }


    public class C06036 implements ViewGroup.OnHierarchyChangeListener {
        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
        }


        class C10111 implements CtDecorateView.OnDecorateViewTouchUp {
            C10111() {
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView.OnDecorateViewTouchUp
            public void onTouchUp(BaseData cMBaseData) {
                Matrix currentMatrixForSticker = Impala_MirrorActivity.this.getCurrentMatrixForSticker();
                if (currentMatrixForSticker != null) {
                    cMBaseData.setImageSaveMatrix(currentMatrixForSticker);
                }
            }
        }

        C06036() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (view2 instanceof Ledl_TextSticker) {
                Impala_MirrorActivity.this.setVisibilityOfCollage(0);
            }
            ((CtDecorateView) view2).setOnDecorateViewTouchUp(new C10111());
        }
    }

    Matrix getCurrentMatrixForSticker() {
        this.matrix.postConcat(this.mirrorView.mainMatrix);
        return this.matrix;
    }

    void setVisibilityOfCollage(final int i) {
        if (this.mainLayout == null) {
            this.mainLayout = (RelativeLayout) findViewById(R.id.layout_mirror_activity);
        }
        if (this.mainLayout.getVisibility() != i) {
            if (i == 4) {
                this.collageVisibilityHandler.postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.9
                    @Override 
                    public void run() {
                        Impala_MirrorActivity.this.mainLayout.setVisibility(i);
                    }
                }, 100L);
            } else {
                this.mainLayout.setVisibility(i);
            }
        }
    }

    private void Set3DMode(int i) {
        this.mirrorView.d3Mode = true;
        if (i > 15 && i < 20) {
            this.mirrorView.setCurrentMode(i);
        } else if (i > 19) {
            this.mirrorView.setCurrentMode(i - 4);
        } else if (i % 2 == 0) {
            this.mirrorView.setCurrentMode(0);
        } else {
            this.mirrorView.setCurrentMode(1);
        }
        this.mirrorView.Reset(this.screenWidthPixels, this.screenHeightPixels, false);
        Load3DShapeBitmap(this.Shape3DList[i]);
        this.mirrorView.postInvalidate();
        SetD3ButtonBg(i);
    }

    private void Load3DShapeBitmap(int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (this.mirrorView.d3Bitmap == null || this.mirrorView.d3Bitmap.isRecycled()) {
            options.inJustDecodeBounds = true;
            options.inMutable = true;
            BitmapFactory.decodeResource(getResources(), i, options);
            this.mirrorView.d3Bitmap = Bitmap.createBitmap(options.outWidth, options.outHeight, Bitmap.Config.ARGB_8888);
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        options.inBitmap = this.mirrorView.d3Bitmap;
        try {
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), i, options);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            if (this.mirrorView.d3Bitmap != null && !this.mirrorView.d3Bitmap.isRecycled()) {
                this.mirrorView.d3Bitmap.recycle();
            }
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), i);
        }
    }

    private void SetD3ButtonBg(int i) {
        if (this.d3ButtonArray == null) {
            ImageView[] imageViewArr = new ImageView[this.D3_BUTTON_SIZE];
            this.d3ButtonArray = imageViewArr;
            imageViewArr[0] = (ImageView) findViewById(R.id.button_3d_1);
            this.d3ButtonArray[1] = (ImageView) findViewById(R.id.button_3d_2);
            this.d3ButtonArray[2] = (ImageView) findViewById(R.id.button_3d_3);
            this.d3ButtonArray[3] = (ImageView) findViewById(R.id.button_3d_4);
            this.d3ButtonArray[4] = (ImageView) findViewById(R.id.button_3d_5);
            this.d3ButtonArray[5] = (ImageView) findViewById(R.id.button_3d_6);
            this.d3ButtonArray[6] = (ImageView) findViewById(R.id.button_3d_7);
            this.d3ButtonArray[7] = (ImageView) findViewById(R.id.button_3d_8);
            this.d3ButtonArray[8] = (ImageView) findViewById(R.id.button_3d_9);
            this.d3ButtonArray[9] = (ImageView) findViewById(R.id.button_3d_10);
            this.d3ButtonArray[10] = (ImageView) findViewById(R.id.button_3d_11);
            this.d3ButtonArray[11] = (ImageView) findViewById(R.id.button_3d_12);
            this.d3ButtonArray[12] = (ImageView) findViewById(R.id.button_3d_13);
            this.d3ButtonArray[13] = (ImageView) findViewById(R.id.button_3d_14);
            this.d3ButtonArray[14] = (ImageView) findViewById(R.id.button_3d_15);
            this.d3ButtonArray[15] = (ImageView) findViewById(R.id.button_3d_16);
            this.d3ButtonArray[16] = (ImageView) findViewById(R.id.button_3d_17);
            this.d3ButtonArray[17] = (ImageView) findViewById(R.id.button_3d_18);
            this.d3ButtonArray[18] = (ImageView) findViewById(R.id.button_3d_19);
            this.d3ButtonArray[19] = (ImageView) findViewById(R.id.button_3d_20);
            this.d3ButtonArray[20] = (ImageView) findViewById(R.id.button_3d_21);
            this.d3ButtonArray[21] = (ImageView) findViewById(R.id.button_3d_22);
            this.d3ButtonArray[22] = (ImageView) findViewById(R.id.button_3d_23);
            this.d3ButtonArray[23] = (ImageView) findViewById(R.id.button_3d_24);
        }
        for (int i2 = 0; i2 < this.D3_BUTTON_SIZE; i2++) {
            this.d3ButtonArray[i2].setBackgroundResource(0);
        }
        this.d3ButtonArray[i].setBackgroundResource(R.drawable.selector_box);
    }

    private void SetMirrorButtonBg(int i) {
        if (this.mirrorEffectArray == null) {
            ImageView[] imageViewArr = new ImageView[this.MIRROR_BUTTON_SIZE];
            this.mirrorEffectArray = imageViewArr;
            imageViewArr[0] = (ImageView) findViewById(R.id.button_m1);
            this.mirrorEffectArray[1] = (ImageView) findViewById(R.id.button_m2);
            this.mirrorEffectArray[2] = (ImageView) findViewById(R.id.button_m3);
            this.mirrorEffectArray[3] = (ImageView) findViewById(R.id.button_m4);
            this.mirrorEffectArray[4] = (ImageView) findViewById(R.id.button_m5);
            this.mirrorEffectArray[5] = (ImageView) findViewById(R.id.button_m6);
            this.mirrorEffectArray[6] = (ImageView) findViewById(R.id.button_m7);
            this.mirrorEffectArray[7] = (ImageView) findViewById(R.id.button_m8);
            this.mirrorEffectArray[8] = (ImageView) findViewById(R.id.button_m9);
            this.mirrorEffectArray[9] = (ImageView) findViewById(R.id.button_m10);
            this.mirrorEffectArray[10] = (ImageView) findViewById(R.id.button_m11);
            this.mirrorEffectArray[11] = (ImageView) findViewById(R.id.button_m12);
            this.mirrorEffectArray[12] = (ImageView) findViewById(R.id.button_m13);
            this.mirrorEffectArray[13] = (ImageView) findViewById(R.id.button_m14);
            this.mirrorEffectArray[14] = (ImageView) findViewById(R.id.button_m15);
        }
        for (int i2 = 0; i2 < this.MIRROR_BUTTON_SIZE; i2++) {
            this.mirrorEffectArray[i2].setBackgroundResource(0);
        }
        this.mirrorEffectArray[i].setBackgroundResource(R.drawable.selector_box);
    }

    private void SetRatioButtonBg(int i) {
        if (this.mirrorRatioArray == null) {
            TextView[] textViewArr = new TextView[this.RATIO_BUTTON_SIZE];
            this.mirrorRatioArray = textViewArr;
            textViewArr[0] = (TextView) findViewById(R.id.button11);
            this.mirrorRatioArray[1] = (TextView) findViewById(R.id.button21);
            this.mirrorRatioArray[2] = (TextView) findViewById(R.id.button12);
            this.mirrorRatioArray[3] = (TextView) findViewById(R.id.button32);
            this.mirrorRatioArray[4] = (TextView) findViewById(R.id.button23);
            this.mirrorRatioArray[5] = (TextView) findViewById(R.id.button43);
            this.mirrorRatioArray[6] = (TextView) findViewById(R.id.button34);
            this.mirrorRatioArray[7] = (TextView) findViewById(R.id.button45);
            this.mirrorRatioArray[8] = (TextView) findViewById(R.id.button57);
            this.mirrorRatioArray[9] = (TextView) findViewById(R.id.button169);
            this.mirrorRatioArray[10] = (TextView) findViewById(R.id.button916);
        }
        for (int i2 = 0; i2 < this.RATIO_BUTTON_SIZE; i2++) {
            this.mirrorRatioArray[i2].setBackgroundResource(0);
            this.mirrorRatioArray[i2].setTextColor(getResources().getColor(R.color.gray));
        }
        this.mirrorRatioArray[i].setBackgroundResource(R.drawable.selector_box_ratio);
        this.mirrorRatioArray[i].setTextColor(-1);
    }

    public void SetSelectedTab(int i) {
        SetTabBg(0);
        int displayedChild = this.viewFlipper.getDisplayedChild();
        if (i == 0) {
            if (displayedChild == 0) {
                return;
            }
            this.viewFlipper.setInAnimation(this.slideInLeft);
            this.viewFlipper.setOutAnimation(this.slideOutRight);
            this.viewFlipper.setDisplayedChild(0);
        }
        if (i == 1) {
            SetTabBg(1);
            if (displayedChild == 1) {
                return;
            }
            if (displayedChild == 0) {
                this.viewFlipper.setInAnimation(this.slideInRight);
                this.viewFlipper.setOutAnimation(this.slideOutLeft);
            } else {
                this.viewFlipper.setInAnimation(this.slideInLeft);
                this.viewFlipper.setOutAnimation(this.slideOutRight);
            }
            this.viewFlipper.setDisplayedChild(1);
        }
        if (i == 2) {
            SetTabBg(2);
            if (displayedChild == 2) {
                return;
            }
            if (displayedChild == 0) {
                this.viewFlipper.setInAnimation(this.slideInRight);
                this.viewFlipper.setOutAnimation(this.slideOutLeft);
            } else {
                this.viewFlipper.setInAnimation(this.slideInLeft);
                this.viewFlipper.setOutAnimation(this.slideOutRight);
            }
            this.viewFlipper.setDisplayedChild(2);
        }
        if (i == 3) {
            SetTabBg(3);
            this.filterEffectFragment.SetSelectedTabIndex(0);
            if (displayedChild == 3) {
                return;
            }
            if (displayedChild == 0 || displayedChild == 2) {
                this.viewFlipper.setInAnimation(this.slideInRight);
                this.viewFlipper.setOutAnimation(this.slideOutLeft);
            } else {
                this.viewFlipper.setInAnimation(this.slideInLeft);
                this.viewFlipper.setOutAnimation(this.slideOutRight);
            }
            this.viewFlipper.setDisplayedChild(3);
        }
        if (i == 4) {
            SetTabBg(4);
            this.filterEffectFragment.SetSelectedTabIndex(1);
            if (displayedChild == 3) {
                return;
            }
            if (displayedChild == 5) {
                this.viewFlipper.setInAnimation(this.slideInLeft);
                this.viewFlipper.setOutAnimation(this.slideOutRight);
            } else {
                this.viewFlipper.setInAnimation(this.slideInRight);
                this.viewFlipper.setOutAnimation(this.slideOutLeft);
            }
            this.viewFlipper.setDisplayedChild(3);
        }
        if (i == 5) {
            SetTabBg(5);
            this.filterEffectFragment.ShowToolBar();
            if (displayedChild == 3) {
                return;
            }
            this.viewFlipper.setInAnimation(this.slideInRight);
            this.viewFlipper.setOutAnimation(this.slideOutLeft);
            this.viewFlipper.setDisplayedChild(3);
        }
        if (i == 7) {
            SetTabBg(-1);
            if (displayedChild != 4) {
                this.viewFlipper.setInAnimation(this.slideInRight);
                this.viewFlipper.setOutAnimation(this.slideOutLeft);
                this.viewFlipper.setDisplayedChild(4);
            }
        }
    }

    private void SetTabBg(int i) {
        this.currentSelectedTabIndex = i;
        if (this.tabButtonList == null) {
            View[] viewArr = new View[6];
            this.tabButtonList = viewArr;
            viewArr[0] = findViewById(R.id.button_mirror);
            this.tabButtonList[1] = findViewById(R.id.button_mirror_3d);
            this.tabButtonList[3] = findViewById(R.id.button_mirror_effect);
            this.tabButtonList[2] = findViewById(R.id.button_mirror_ratio);
            this.tabButtonList[4] = findViewById(R.id.button_mirror_frame);
            this.tabButtonList[5] = findViewById(R.id.button_mirror_adj);
        }
        for (int i2 = 0; i2 < this.tabButtonList.length; i2++) {
        }
    }

    public void ClearViewFlipper() {
        this.viewFlipper.setInAnimation(null);
        this.viewFlipper.setOutAnimation(null);
        this.viewFlipper.setDisplayedChild(4);
        SetTabBg(-1);
    }

    @Override 
    public void onBackPressed() {
        HiPhi_MainActivity.fromActivity = "";
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
            onFontSelectionCancelled();
            rl_toolbar.setVisibility(0);
            rl_bottom.setVisibility(0);
        } else if (this.viewFlipper.getDisplayedChild() == 3) {
            ClearFxAndFrame();
            ClearViewFlipper();
        } else if (!this.showText && this.customRelativeLayout != null) {
            onFontSelectionCancelled();
            rl_toolbar.setVisibility(0);
            rl_bottom.setVisibility(0);
            this.showText = true;
            this.mainLayout.removeView(this.customRelativeLayout);
            this.mirrorView.postInvalidate();
            this.customRelativeLayout = null;
        } else if (this.viewFlipper.getDisplayedChild() != 4) {
            ClearViewFlipper();
        } else {
            SaveImageAlertDialog();
        }
    }

    public void SaveImageAlertDialog() {
        Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        this.saveImageDialog = dialog;
        dialog.requestWindowFeature(1);
        this.saveImageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.saveImageDialog.setContentView(R.layout.dg_save_image);
        this.saveImageDialog.setTitle("Save Image");
        this.saveImageDialog.setCancelable(false);
        this.saveImageDialog.setCanceledOnTouchOutside(false);
        this.saveImageDialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.10
            @Override 
            public void onClick(View view) {
                new SaveImageTask().execute(new Object[0]);
            }
        });
        this.saveImageDialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.11
            @Override 
            public void onClick(View view) {
                Impala_MirrorActivity.this.finish();
                Impala_MirrorActivity.this.saveImageDialog.dismiss();
            }
        });
        this.saveImageDialog.findViewById(R.id.rl_cancel).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.CMMirrorActivity.12
            @Override 
            public void onClick(View view) {
                Impala_MirrorActivity.this.saveImageDialog.dismiss();
            }
        });
        this.saveImageDialog.show();
    }

    public void ApplyTextBackground(CtTextData cMTextData, Canvas canvas) {
        Rect rect = new Rect();
        cMTextData.textPaint.getTextBounds(cMTextData.message, 0, cMTextData.message.length(), rect);
        RectF rectF = new RectF(cMTextData.xPos - 20.0f, (cMTextData.yPos - rect.height()) - 40.0f, cMTextData.xPos + rect.width() + 20.0f, cMTextData.yPos + 20.0f);
        Paint paint = new Paint(1);
        int textBackgroundColor = cMTextData.getTextBackgroundColor();
        if (textBackgroundColor != -1) {
            paint.setColor(textBackgroundColor);
            canvas.drawRect(rectF, paint);
        }
    }


    public class MirrorView extends View {
        int currentModeIndex;
        Bitmap d3Bitmap;
        boolean d3Mode;
        int defaultColor;
        RectF destRect1;
        RectF destRect1X;
        RectF destRect1Y;
        RectF destRect2;
        RectF destRect2X;
        RectF destRect2Y;
        RectF destRect3;
        RectF destRect4;
        boolean drawSavedImage;
        RectF dstRectPaper1;
        RectF dstRectPaper2;
        RectF dstRectPaper3;
        RectF dstRectPaper4;
        Bitmap frameBitmap;
        Paint framePaint;
        int height;
        boolean isTouchStartedLeft;
        boolean isTouchStartedTop;
        boolean isVerticle;
        Matrix m1;
        Matrix m2;
        Matrix m3;
        Jumper_MirrorMode mModeX;
        Jumper_MirrorMode mModeX10;
        Jumper_MirrorMode mModeX11;
        Jumper_MirrorMode mModeX12;
        Jumper_MirrorMode mModeX13;
        Jumper_MirrorMode mModeX14;
        Jumper_MirrorMode mModeX15;
        Jumper_MirrorMode mModeX16;
        Jumper_MirrorMode mModeX17;
        Jumper_MirrorMode mModeX18;
        Jumper_MirrorMode mModeX19;
        Jumper_MirrorMode mModeX2;
        Jumper_MirrorMode mModeX20;
        Jumper_MirrorMode mModeX3;
        Jumper_MirrorMode mModeX4;
        Jumper_MirrorMode mModeX5;
        Jumper_MirrorMode mModeX6;
        Jumper_MirrorMode mModeX7;
        Jumper_MirrorMode mModeX8;
        Jumper_MirrorMode mModeX9;
        final Matrix mainMatrix;
        Jumper_MirrorMode[] mirrorModeList;
        float oldX;
        float oldY;
        RectF srcRect1;
        RectF srcRect2;
        RectF srcRect3;
        RectF srcRectPaper;
        int tMode1;
        int tMode2;
        int tMode3;
        Matrix textMatrix;
        Paint textRectPaint;
        RectF totalArea1;
        RectF totalArea2;
        RectF totalArea3;
        int width;

        public MirrorView(Context context, int i, int i2) {
            super(context);
            this.currentModeIndex = 0;
            this.d3Mode = false;
            this.defaultColor = R.color.transparent;
            this.drawSavedImage = false;
            this.mainMatrix = new Matrix();
            this.framePaint = new Paint();
            this.isVerticle = false;
            this.m1 = new Matrix();
            this.m2 = new Matrix();
            this.m3 = new Matrix();
            this.mirrorModeList = new Jumper_MirrorMode[20];
            this.textMatrix = new Matrix();
            this.textRectPaint = new Paint(1);
            this.width = Impala_MirrorActivity.this.sourceBitmap.getWidth();
            this.height = Impala_MirrorActivity.this.sourceBitmap.getHeight();
            CreateMatrix(i, i2);
            CreateRectX(i, i2);
            CreateRectY(i, i2);
            CreateRectXY(i, i2);
            CreateModes();
            this.framePaint.setAntiAlias(true);
            this.framePaint.setFilterBitmap(true);
            this.framePaint.setDither(true);
            this.textRectPaint.setColor(getResources().getColor(R.color.transparent));
        }

        public void Reset(int i, int i2, boolean z) {
            CreateMatrix(i, i2);
            CreateRectX(i, i2);
            CreateRectY(i, i2);
            CreateRectXY(i, i2);
            CreateModes();
            if (z) {
                postInvalidate();
            }
        }

        public String SaveBitmap(boolean z, int i, int i2) {
            Bitmap bitmap;
            float maxSizeForSave = Aspid_Utility.maxSizeForSave() / Math.min(i, i2);
            if (Impala_MirrorActivity.this.mulY > Impala_MirrorActivity.this.mulX) {
                float f = Impala_MirrorActivity.this.mulX;
                maxSizeForSave = (maxSizeForSave * 1.0f) / Impala_MirrorActivity.this.mulY;
            }
            float f2 = maxSizeForSave > 0.0f ? maxSizeForSave : 1.0f;
            int round = Math.round(i * f2);
            int round2 = Math.round(i2 * f2);
            RectF srcRect = this.mirrorModeList[this.currentModeIndex].getSrcRect();
            Reset(round, round2, false);
            int round3 = Math.round(Impala_MirrorActivity.this.mirrorView.getCurrentMirrorMode().rectTotalArea.width());
            int round4 = Math.round(Impala_MirrorActivity.this.mirrorView.getCurrentMirrorMode().rectTotalArea.height());
            if (round3 % 2 == 1) {
                round3--;
            }
            if (round4 % 2 == 1) {
                round4--;
            }
            Bitmap createBitmap = Bitmap.createBitmap(round3, round4, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Matrix matrix = new Matrix();
            matrix.reset();
            float f3 = (-(round - round3)) / 2.0f;
            float f4 = (-(round2 - round4)) / 2.0f;
            matrix.postTranslate(f3, f4);
            Jumper_MirrorMode cMMirrorMode = this.mirrorModeList[this.currentModeIndex];
            cMMirrorMode.setSrcRect(srcRect);
            if (Impala_MirrorActivity.this.filterBitmap == null) {
                DrawMode(canvas, Impala_MirrorActivity.this.sourceBitmap, cMMirrorMode, matrix);
            } else {
                DrawMode(canvas, Impala_MirrorActivity.this.filterBitmap, cMMirrorMode, matrix);
            }
            String str = null;
            if (this.d3Mode && (bitmap = this.d3Bitmap) != null && !bitmap.isRecycled()) {
                canvas.setMatrix(matrix);
                canvas.drawBitmap(this.d3Bitmap, (Rect) null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
            }
            if (Impala_MirrorActivity.this.textDataList != null) {
                for (int i3 = 0; i3 < Impala_MirrorActivity.this.textDataList.size(); i3++) {
                    CtTextData cMTextData = Impala_MirrorActivity.this.textDataList.get(i3);
                    Matrix matrix2 = new Matrix();
                    matrix2.set(cMTextData.imageSaveMatrix);
                    matrix2.postScale(f2, f2);
                    matrix2.postTranslate(f3, f4);
                    canvas.setMatrix(matrix2);
                    Impala_MirrorActivity.this.ApplyTextBackground(cMTextData, canvas);
                    canvas.drawText(cMTextData.message, cMTextData.xPos, Impala_MirrorActivity.this.textDataList.get(i3).yPos, cMTextData.textPaint);
                }
            }
            Bitmap bitmap2 = this.frameBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                canvas.setMatrix(matrix);
                canvas.drawBitmap(this.frameBitmap, (Rect) null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
            }
            if (z) {
                str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + getResources().getString(R.string.app_name) + File.separator + "CollagePhotos" + File.separator + "MirrorCollagePic" + System.currentTimeMillis() + ".jpg";
                new File(str).getParentFile().mkdirs();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(str);
                    createBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            createBitmap.recycle();
            Reset(i, i2, false);
            this.mirrorModeList[this.currentModeIndex].setSrcRect(srcRect);
            return str;
        }

        public void setCurrentMode(int i) {
            this.currentModeIndex = i;
        }

        public Jumper_MirrorMode getCurrentMirrorMode() {
            return this.mirrorModeList[this.currentModeIndex];
        }

        private void CreateModes() {
            RectF rectF = this.srcRect3;
            RectF rectF2 = this.destRect1;
            RectF rectF3 = this.destRect3;
            this.mModeX = new Jumper_MirrorMode(4, rectF, rectF2, rectF2, rectF3, rectF3, Impala_MirrorActivity.this.matrixMirror1, this.mainMatrix, Impala_MirrorActivity.this.matrixMirror1, this.tMode3, this.totalArea3);
            RectF rectF4 = this.srcRect3;
            RectF rectF5 = this.destRect1;
            RectF rectF6 = this.destRect4;
            this.mModeX2 = new Jumper_MirrorMode(4, rectF4, rectF5, rectF6, rectF5, rectF6, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror1, this.mainMatrix, this.tMode3, this.totalArea3);
            RectF rectF7 = this.srcRect3;
            RectF rectF8 = this.destRect3;
            RectF rectF9 = this.destRect2;
            this.mModeX3 = new Jumper_MirrorMode(4, rectF7, rectF8, rectF9, rectF8, rectF9, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror1, this.mainMatrix, this.tMode3, this.totalArea3);
            RectF rectF10 = this.srcRect3;
            RectF rectF11 = this.destRect1;
            this.mModeX8 = new Jumper_MirrorMode(4, rectF10, rectF11, rectF11, rectF11, rectF11, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror2, Impala_MirrorActivity.this.matrixMirror3, this.tMode3, this.totalArea3);
            int i = this.tMode3 == 0 ? 0 : 4;
            RectF rectF12 = this.srcRect3;
            RectF rectF13 = this.destRect2;
            this.mModeX9 = new Jumper_MirrorMode(4, rectF12, rectF13, rectF13, rectF13, rectF13, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror2, Impala_MirrorActivity.this.matrixMirror3, i, this.totalArea3);
            int i2 = this.tMode3 == 1 ? 1 : 3;
            RectF rectF14 = this.srcRect3;
            RectF rectF15 = this.destRect3;
            this.mModeX10 = new Jumper_MirrorMode(4, rectF14, rectF15, rectF15, rectF15, rectF15, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror2, Impala_MirrorActivity.this.matrixMirror3, i2, this.totalArea3);
            int i3 = this.tMode3 == 0 ? 3 : 4;
            RectF rectF16 = this.srcRect3;
            RectF rectF17 = this.destRect4;
            this.mModeX11 = new Jumper_MirrorMode(4, rectF16, rectF17, rectF17, rectF17, rectF17, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror2, Impala_MirrorActivity.this.matrixMirror3, i3, this.totalArea3);
            RectF rectF18 = this.srcRect1;
            RectF rectF19 = this.destRect1X;
            this.mModeX4 = new Jumper_MirrorMode(2, rectF18, rectF19, rectF19, Impala_MirrorActivity.this.matrixMirror1, this.tMode1, this.totalArea1);
            int i4 = this.tMode1;
            int i5 = i4 == 0 ? 0 : i4 == 5 ? 5 : 4;
            RectF rectF20 = this.srcRect1;
            RectF rectF21 = this.destRect2X;
            this.mModeX5 = new Jumper_MirrorMode(2, rectF20, rectF21, rectF21, Impala_MirrorActivity.this.matrixMirror1, i5, this.totalArea1);
            RectF rectF22 = this.srcRect2;
            RectF rectF23 = this.destRect1Y;
            this.mModeX6 = new Jumper_MirrorMode(2, rectF22, rectF23, rectF23, Impala_MirrorActivity.this.matrixMirror2, this.tMode2, this.totalArea2);
            int i6 = this.tMode2;
            int i7 = i6 == 1 ? 1 : i6 == 6 ? 6 : 3;
            RectF rectF24 = this.srcRect2;
            RectF rectF25 = this.destRect2Y;
            this.mModeX7 = new Jumper_MirrorMode(2, rectF24, rectF25, rectF25, Impala_MirrorActivity.this.matrixMirror2, i7, this.totalArea2);
            this.mModeX12 = new Jumper_MirrorMode(2, this.srcRect1, this.destRect1X, this.destRect2X, Impala_MirrorActivity.this.matrixMirror4, this.tMode1, this.totalArea1);
            this.mModeX13 = new Jumper_MirrorMode(2, this.srcRect2, this.destRect1Y, this.destRect2Y, Impala_MirrorActivity.this.matrixMirror4, this.tMode2, this.totalArea2);
            RectF rectF26 = this.srcRect1;
            RectF rectF27 = this.destRect1X;
            this.mModeX14 = new Jumper_MirrorMode(2, rectF26, rectF27, rectF27, Impala_MirrorActivity.this.matrixMirror3, this.tMode1, this.totalArea1);
            RectF rectF28 = this.srcRect2;
            RectF rectF29 = this.destRect1Y;
            this.mModeX15 = new Jumper_MirrorMode(2, rectF28, rectF29, rectF29, Impala_MirrorActivity.this.matrixMirror3, this.tMode2, this.totalArea2);
            this.mModeX16 = new Jumper_MirrorMode(4, this.srcRectPaper, this.dstRectPaper1, this.dstRectPaper2, this.dstRectPaper3, this.dstRectPaper4, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror1, this.mainMatrix, this.tMode1, this.totalArea1);
            RectF rectF30 = this.srcRectPaper;
            RectF rectF31 = this.dstRectPaper1;
            RectF rectF32 = this.dstRectPaper3;
            this.mModeX17 = new Jumper_MirrorMode(4, rectF30, rectF31, rectF32, rectF32, rectF31, this.mainMatrix, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror1, this.tMode1, this.totalArea1);
            RectF rectF33 = this.srcRectPaper;
            RectF rectF34 = this.dstRectPaper2;
            RectF rectF35 = this.dstRectPaper4;
            this.mModeX18 = new Jumper_MirrorMode(4, rectF33, rectF34, rectF35, rectF34, rectF35, this.mainMatrix, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror1, this.tMode1, this.totalArea1);
            RectF rectF36 = this.srcRectPaper;
            RectF rectF37 = this.dstRectPaper1;
            RectF rectF38 = this.dstRectPaper2;
            this.mModeX19 = new Jumper_MirrorMode(4, rectF36, rectF37, rectF38, rectF38, rectF37, this.mainMatrix, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror1, this.tMode1, this.totalArea1);
            RectF rectF39 = this.srcRectPaper;
            RectF rectF40 = this.dstRectPaper4;
            RectF rectF41 = this.dstRectPaper3;
            Jumper_MirrorMode cMMirrorMode = new Jumper_MirrorMode(4, rectF39, rectF40, rectF41, rectF41, rectF40, this.mainMatrix, Impala_MirrorActivity.this.matrixMirror1, Impala_MirrorActivity.this.matrixMirror1, this.tMode1, this.totalArea1);
            this.mModeX20 = cMMirrorMode;
            Jumper_MirrorMode[] cMMirrorModeArr = this.mirrorModeList;
            cMMirrorModeArr[0] = this.mModeX4;
            cMMirrorModeArr[1] = this.mModeX5;
            cMMirrorModeArr[2] = this.mModeX6;
            Jumper_MirrorMode cMMirrorMode2 = this.mModeX7;
            cMMirrorModeArr[3] = cMMirrorMode2;
            cMMirrorModeArr[4] = this.mModeX8;
            cMMirrorModeArr[5] = this.mModeX9;
            cMMirrorModeArr[6] = this.mModeX10;
            cMMirrorModeArr[7] = this.mModeX11;
            cMMirrorModeArr[8] = this.mModeX12;
            cMMirrorModeArr[9] = this.mModeX13;
            cMMirrorModeArr[10] = this.mModeX14;
            cMMirrorModeArr[11] = this.mModeX15;
            cMMirrorModeArr[12] = this.mModeX;
            cMMirrorModeArr[13] = this.mModeX2;
            cMMirrorModeArr[14] = this.mModeX3;
            cMMirrorModeArr[15] = cMMirrorMode2;
            cMMirrorModeArr[16] = this.mModeX17;
            cMMirrorModeArr[17] = this.mModeX18;
            cMMirrorModeArr[18] = this.mModeX19;
            cMMirrorModeArr[19] = cMMirrorMode;
        }

        public Bitmap GetBitmap() {
            setDrawingCacheEnabled(true);
            buildDrawingCache();
            Bitmap createBitmap = Bitmap.createBitmap(getDrawingCache());
            setDrawingCacheEnabled(false);
            return createBitmap;
        }

        public void SetFrame(int i) {
            Bitmap bitmap = this.frameBitmap;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.frameBitmap.recycle();
                this.frameBitmap = null;
            }
            if (i == 0) {
                postInvalidate();
                return;
            }
            this.frameBitmap = BitmapFactory.decodeResource(getResources(), Jumper_EffectUtility.framesArray[i]);
            postInvalidate();
        }

        private void CreateMatrix(int i, int i2) {
            this.mainMatrix.reset();
            Impala_MirrorActivity.this.matrixMirror1.reset();
            Impala_MirrorActivity.this.matrixMirror1.postScale(-1.0f, 1.0f);
            float f = i;
            Impala_MirrorActivity.this.matrixMirror1.postTranslate(f, 0.0f);
            Impala_MirrorActivity.this.matrixMirror2.reset();
            Impala_MirrorActivity.this.matrixMirror2.postScale(1.0f, -1.0f);
            float f2 = i2;
            Impala_MirrorActivity.this.matrixMirror2.postTranslate(0.0f, f2);
            Impala_MirrorActivity.this.matrixMirror3.reset();
            Impala_MirrorActivity.this.matrixMirror3.postScale(-1.0f, -1.0f);
            Impala_MirrorActivity.this.matrixMirror3.postTranslate(f, f2);
        }

        private void CreateRectX(int i, int i2) {
            float f;
            float f2;
            int i3 = 0;
            float f3;
            float f4;
            float f5 = i;
            float f6 = (Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX) * f5;
            float f7 = f5 / 2.0f;
            int i4 = Impala_MirrorActivity.this.initialYPos;
            float f8 = i2;
            float f9 = 0.0f;
            if (f6 > f8) {
                float f10 = ((Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY) * f8) / 2.0f;
                f = f8;
                f2 = f7 - f10;
                f7 = f10;
            } else {
                f = f6;
                f2 = 0.0f;
            }
            float f11 = Impala_MirrorActivity.this.initialYPos + ((f8 - f) / 2.0f);
            float f12 = this.width;
            float f13 = this.height;
            float f14 = f7 + f2;
            float f15 = f + f11;
            this.destRect1X = new RectF(f2, f11, f14, f15);
            float f16 = f7 + f14;
            this.destRect2X = new RectF(f14, f11, f16, f15);
            this.totalArea1 = new RectF(f2, f11, f16, f15);
            this.tMode1 = 1;
            float f17 = this.width;
            if (Impala_MirrorActivity.this.mulX * this.height <= Impala_MirrorActivity.this.mulY * 2.0f * f17) {
                float f18 = (f17 - (((Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY) * this.height) / 2.0f)) / 2.0f;
                f12 = (((Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY) * this.height) / 2.0f) + f18;
                f4 = f13;
                f9 = f18;
                f3 = 0.0f;
            } else {
                f3 = (this.height - ((i3 * 2) * (Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX))) / 2.0f;
                this.tMode1 = 5;
                f4 = (this.width * 2 * (Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX)) + f3;
            }
            this.srcRect1 = new RectF(f9, f3, f12, f4);
            this.srcRectPaper = new RectF(f9, f3, ((f12 - f9) / 2.0f) + f9, f4);
            float f19 = f7 / 2.0f;
            float f20 = f19 + f2;
            this.dstRectPaper1 = new RectF(f2, f11, f20, f15);
            float f21 = f19 + f20;
            this.dstRectPaper2 = new RectF(f20, f11, f21, f15);
            float f22 = f19 + f21;
            this.dstRectPaper3 = new RectF(f21, f11, f22, f15);
            this.dstRectPaper4 = new RectF(f22, f11, f19 + f22, f15);
        }

        private void CreateRectY(int i, int i2) {
            float f;
            float f2;
            float f3;
            float f4 = i;
            float f5 = ((Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX) * f4) / 2.0f;
            float f6 = i2;
            float f7 = 0.0f;
            if (f5 > f6) {
                float f8 = ((Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY) * f6) / 2.0f;
                f = f6;
                f2 = (f4 / 2.0f) - f8;
                f4 = f8;
            } else {
                f = f5;
                f2 = 0.0f;
            }
            float f9 = Impala_MirrorActivity.this.initialYPos + ((f6 - (f * 2.0f)) / 2.0f);
            float f10 = f4 + f2;
            float f11 = f + f9;
            this.destRect1Y = new RectF(f2, f9, f10, f11);
            float f12 = f + f11;
            this.destRect2Y = new RectF(f2, f11, f10, f12);
            this.totalArea2 = new RectF(f2, f9, f10, f12);
            float f13 = this.width;
            float f14 = this.height;
            this.tMode2 = 0;
            float f15 = Impala_MirrorActivity.this.mulX * 2.0f * this.height;
            float f16 = Impala_MirrorActivity.this.mulY;
            float f17 = this.width;
            if (f15 > f16 * f17) {
                float f18 = (this.height - (((Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX) * this.width) / 2.0f)) / 2.0f;
                f3 = f18;
                f14 = (((Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX) * this.width) / 2.0f) + f18;
            } else {
                float f19 = (f17 - ((this.height * 2) * (Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY))) / 2.0f;
                f13 = (this.height * 2 * (Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY)) + f19;
                this.tMode2 = 6;
                f7 = f19;
                f3 = 0.0f;
            }
            this.srcRect2 = new RectF(f7, f3, f13, f14);
        }

        private void CreateRectXY(int i, int i2) {
            float f;
            float f2;
            float f3;
            float f4 = i;
            float f5 = ((Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX) * f4) / 2.0f;
            float f6 = f4 / 2.0f;
            float f7 = i2;
            float f8 = 0.0f;
            if (f5 > f7) {
                float f9 = ((Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY) * f7) / 2.0f;
                f = f7;
                f2 = f6 - f9;
                f6 = f9;
            } else {
                f = f5;
                f2 = 0.0f;
            }
            float f10 = Impala_MirrorActivity.this.initialYPos + ((f7 - (f * 2.0f)) / 2.0f);
            float f11 = this.width;
            float f12 = this.height;
            float f13 = f6 + f2;
            float f14 = f + f10;
            this.destRect1 = new RectF(f2, f10, f13, f14);
            float f15 = f6 + f13;
            this.destRect2 = new RectF(f13, f10, f15, f14);
            float f16 = f + f14;
            this.destRect3 = new RectF(f2, f14, f13, f16);
            this.destRect4 = new RectF(f13, f14, f15, f16);
            this.totalArea3 = new RectF(f2, f10, f15, f16);
            float f17 = Impala_MirrorActivity.this.mulX * this.height;
            float f18 = Impala_MirrorActivity.this.mulY;
            float f19 = this.width;
            if (f17 <= f18 * f19) {
                float f20 = (f19 - ((Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY) * this.height)) / 2.0f;
                f11 = ((Impala_MirrorActivity.this.mulX / Impala_MirrorActivity.this.mulY) * this.height) + f20;
                this.tMode3 = 1;
                f8 = f20;
                f3 = 0.0f;
            } else {
                f3 = (this.height - (f19 * (Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX))) / 2.0f;
                f12 = f3 + (this.width * (Impala_MirrorActivity.this.mulY / Impala_MirrorActivity.this.mulX));
                this.tMode3 = 0;
            }
            this.srcRect3 = new RectF(f8, f3, f11, f12);
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            Bitmap bitmap;
            canvas.drawColor(getResources().getColor(R.color.transparent));
            if (Impala_MirrorActivity.this.filterBitmap == null) {
                DrawMode(canvas, Impala_MirrorActivity.this.sourceBitmap, this.mirrorModeList[this.currentModeIndex], this.mainMatrix);
            } else {
                DrawMode(canvas, Impala_MirrorActivity.this.filterBitmap, this.mirrorModeList[this.currentModeIndex], this.mainMatrix);
            }
            if (this.d3Mode && (bitmap = this.d3Bitmap) != null && !bitmap.isRecycled()) {
                canvas.setMatrix(this.mainMatrix);
                canvas.drawBitmap(this.d3Bitmap, (Rect) null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
            }
            if (Impala_MirrorActivity.this.showText) {
                for (int i = 0; i < Impala_MirrorActivity.this.textDataList.size(); i++) {
                    CtTextData cMTextData = Impala_MirrorActivity.this.textDataList.get(i);
                    this.textMatrix.set(cMTextData.imageSaveMatrix);
                    this.textMatrix.postConcat(this.mainMatrix);
                    canvas.setMatrix(this.textMatrix);
                    Impala_MirrorActivity.this.ApplyTextBackground(cMTextData, canvas);
                    canvas.drawText(Impala_MirrorActivity.this.textDataList.get(i).message, Impala_MirrorActivity.this.textDataList.get(i).xPos, Impala_MirrorActivity.this.textDataList.get(i).yPos, Impala_MirrorActivity.this.textDataList.get(i).textPaint);
                    canvas.setMatrix(this.mainMatrix);
                    canvas.drawRect(0.0f, 0.0f, this.mirrorModeList[this.currentModeIndex].rectTotalArea.left, Impala_MirrorActivity.this.screenHeightPixels, this.textRectPaint);
                    canvas.drawRect(0.0f, 0.0f, Impala_MirrorActivity.this.screenWidthPixels, this.mirrorModeList[this.currentModeIndex].rectTotalArea.top, this.textRectPaint);
                    canvas.drawRect(this.mirrorModeList[this.currentModeIndex].rectTotalArea.right, 0.0f, Impala_MirrorActivity.this.screenWidthPixels, Impala_MirrorActivity.this.screenHeightPixels, this.textRectPaint);
                    canvas.drawRect(0.0f, this.mirrorModeList[this.currentModeIndex].rectTotalArea.bottom, Impala_MirrorActivity.this.screenWidthPixels, Impala_MirrorActivity.this.screenHeightPixels, this.textRectPaint);
                }
            }
            Bitmap bitmap2 = this.frameBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                canvas.setMatrix(this.mainMatrix);
                canvas.drawBitmap(this.frameBitmap, (Rect) null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
            }
            super.onDraw(canvas);
        }

        private void DrawMode(Canvas canvas, Bitmap bitmap, Jumper_MirrorMode cMMirrorMode, Matrix matrix) {
            canvas.setMatrix(matrix);
            canvas.drawBitmap(bitmap, cMMirrorMode.getDrawBitmapSrc(), cMMirrorMode.rect1, this.framePaint);
            this.m1.set(cMMirrorMode.matrix1);
            this.m1.postConcat(matrix);
            canvas.setMatrix(this.m1);
            if (bitmap != null && !bitmap.isRecycled()) {
                canvas.drawBitmap(bitmap, cMMirrorMode.getDrawBitmapSrc(), cMMirrorMode.rect2, this.framePaint);
            }
            if (cMMirrorMode.count == 4) {
                this.m2.set(cMMirrorMode.matrix2);
                this.m2.postConcat(matrix);
                canvas.setMatrix(this.m2);
                if (bitmap != null && !bitmap.isRecycled()) {
                    canvas.drawBitmap(bitmap, cMMirrorMode.getDrawBitmapSrc(), cMMirrorMode.rect3, this.framePaint);
                }
                this.m3.set(cMMirrorMode.matrix3);
                this.m3.postConcat(matrix);
                canvas.setMatrix(this.m3);
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
                canvas.drawBitmap(bitmap, cMMirrorMode.getDrawBitmapSrc(), cMMirrorMode.rect4, this.framePaint);
            }
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.isTouchStartedLeft = x < ((float) (Impala_MirrorActivity.this.screenWidthPixels / 2));
                this.isTouchStartedTop = y < ((float) (Impala_MirrorActivity.this.screenHeightPixels / 2));
                this.oldX = x;
                this.oldY = y;
            } else if (action == 2) {
                MoveGrid(this.mirrorModeList[this.currentModeIndex].getSrcRect(), x - this.oldX, y - this.oldY);
                this.mirrorModeList[this.currentModeIndex].updateBitmapSrc();
                this.oldX = x;
                this.oldY = y;
            }
            postInvalidate();
            return true;
        }

        public void MoveGrid(RectF rectF, float f, float f2) {
            if (this.mirrorModeList[this.currentModeIndex].touchMode == 1 || this.mirrorModeList[this.currentModeIndex].touchMode == 4 || this.mirrorModeList[this.currentModeIndex].touchMode == 6) {
                if (this.mirrorModeList[this.currentModeIndex].touchMode == 4) {
                    f *= -1.0f;
                }
                if (this.isTouchStartedLeft && this.mirrorModeList[this.currentModeIndex].touchMode != 6) {
                    f *= -1.0f;
                }
                if (rectF.left + f < 0.0f) {
                    f = -rectF.left;
                }
                float f3 = this.width;
                if (rectF.right + f >= f3) {
                    f = f3 - rectF.right;
                }
                rectF.left += f;
                rectF.right += f;
            } else if (this.mirrorModeList[this.currentModeIndex].touchMode == 0 || this.mirrorModeList[this.currentModeIndex].touchMode == 3 || this.mirrorModeList[this.currentModeIndex].touchMode == 5) {
                if (this.mirrorModeList[this.currentModeIndex].touchMode == 3) {
                    f2 *= -1.0f;
                }
                if (this.isTouchStartedTop && this.mirrorModeList[this.currentModeIndex].touchMode != 5) {
                    f2 *= -1.0f;
                }
                if (rectF.top + f2 < 0.0f) {
                    f2 = -rectF.top;
                }
                float f4 = this.height;
                if (rectF.bottom + f2 >= f4) {
                    f2 = f4 - rectF.bottom;
                }
                rectF.top += f2;
                rectF.bottom += f2;
            }
        }
    }

    public void ChangeBtnSelection(int i) {
        switch (i) {
            case 0:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_select));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_unselect));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_unselect));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_unselect));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_unselect));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_unselect));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_unselect));
                return;
            case 1:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_unselect));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_select));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_unselect));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_unselect));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_unselect));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_unselect));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_unselect));
                return;
            case 2:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_unselect));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_unselect));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_select));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_unselect));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_unselect));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_unselect));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_unselect));
                return;
            case 3:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_unselect));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_unselect));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_unselect));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_select));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_unselect));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_unselect));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_unselect));
                return;
            case 4:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_unselect));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_unselect));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_unselect));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_unselect));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_select));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_unselect));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_unselect));
                return;
            case 5:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_unselect));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_unselect));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_unselect));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_unselect));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_unselect));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_select));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_unselect));
                return;
            case 6:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_unselect));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_unselect));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_unselect));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_unselect));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_unselect));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_unselect));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_select));
                return;
            case 7:
                this.img_mirror.setImageDrawable(getResources().getDrawable(R.drawable.m_mirror_unselect));
                this.img_mirror_3d.setImageDrawable(getResources().getDrawable(R.drawable.m_shap_unselect));
                this.img_mirror_ratio.setImageDrawable(getResources().getDrawable(R.drawable.m_ratio_unselect));
                this.img_mirror_effect.setImageDrawable(getResources().getDrawable(R.drawable.m_effect_unselect));
                this.img_mirror_frame.setImageDrawable(getResources().getDrawable(R.drawable.m_frame_unselect));
                this.img_mirror_text.setImageDrawable(getResources().getDrawable(R.drawable.m_text_unselect));
                this.img_mirror_adj.setImageDrawable(getResources().getDrawable(R.drawable.m_filter_unselect));
                return;
            default:
                return;
        }
    }
}
