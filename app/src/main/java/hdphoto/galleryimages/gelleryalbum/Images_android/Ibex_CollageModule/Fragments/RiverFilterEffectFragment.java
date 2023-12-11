package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.commit451.nativestackblur.NativeStackBlur;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.HiPhi_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Heart_Adapter.Azure_FilterAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon.Collards_Parameter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon.Collards_SeekBarHint;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon.MyAsyncTask;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.Jumper_EffectUtility;


public class RiverFilterEffectFragment extends Fragment {
    static final String TAG = "EffectFragment";
    Azure_FilterAdapter CMFilterAdapter;
    Activity activity;
    int bitmapHeight;
    BitmapReady bitmapReadyListener;
    Bitmap bitmapTiltBlur;
    int bitmapWidth;
    Azure_FilterAdapter borderAdapter;
    Bitmap btmpFilter;
    Paint bwPaint;
    Context context;
    Jumper_EffectUtility.ExcludeTabListener excludeTabListener;
    Jumper_EffectUtility.FooterVisibilityListener footerListener;
    FilterAndAdjustmentTask ft;
    RiverFullEffectFragment.HideHeaderListener hideHeaderListener;
    ImageView img_blur;
    ImageView img_filter_reset;
    ImageView img_frame;
    ImageView img_fx;
    ImageView img_light;
    ImageView img_texture;
    Paint oldTimesPaint;
    Azure_FilterAdapter overlayAdapter;
    Paint paintABC;
    Paint paintABC1;
    Paint paintABC2;
    Paint paintABC3;
    Paint paintABC4;
    Paint paintColdlife;
    Paint paintCyan;
    Paint paintDarken;
    Paint paintGray;
    Paint paintInvert;
    Paint paintLighten;
    Paint paintLime;
    Paint paintMilk;
    Paint paintPeachy;
    Paint paintPolaroid;
    Paint paintPurple;
    Paint paintScrim;
    public Collards_Parameter parameterGlobal;
    SeekBar seekBarAdjustment;
    LinearLayout.LayoutParams seekbarHintTextLayoutParams;
    Paint sepiaPaint;
    Paint sepiumPaint;
    Animation slideLeftIn;
    Animation slideLeftOut;
    Animation slideRightIn;
    Animation slideRightOut;
    Bitmap sourceBitmap;
    TextView textHint;
    Azure_FilterAdapter textureAdapter;
    int thumbSize;
    ImageView txt_lib_cancel;
    ImageView txt_lib_ok;
    View view;
    ViewFlipper viewFlipper;
    private ViewSwitcher viewSwitcher;
    public Paint yellowPaint;
    String[] filterBitmapTitle = {"None", "Gray", "Sepia", "Joey", "Sancia", "Blair", "Sura", "Tara", "Summer", "Penny", "Cuddy", "Cameron", "Lemon", "Tanya", "Lorelai", "Quinn", "Izabella", "Amber", "Cersei", "Debra", "Ellen", "Gabrielle", "Arya"};
    Azure_FilterAdapter.IndexChangedListener borderIndexChangedListener = null;
    boolean inFilterAndAdjustment = false;
    SeekBar.OnSeekBarChangeListener mySeekBarListener = new SeekBar.OnSeekBarChangeListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFilterEffectFragment.1
        @Override 
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (RiverFilterEffectFragment.this.textHint == null) {
                RiverFilterEffectFragment cMFilterEffectFragment = RiverFilterEffectFragment.this;
                cMFilterEffectFragment.textHint = (TextView) cMFilterEffectFragment.getView().findViewById(R.id.seekbar_hint);
            }
            if (RiverFilterEffectFragment.this.seekbarHintTextLayoutParams == null) {
                RiverFilterEffectFragment cMFilterEffectFragment2 = RiverFilterEffectFragment.this;
                cMFilterEffectFragment2.seekbarHintTextLayoutParams = (LinearLayout.LayoutParams) cMFilterEffectFragment2.textHint.getLayoutParams();
            }
            Rect bounds = ((Collards_SeekBarHint) seekBar).getSeekBarThumb().getBounds();
            RiverFilterEffectFragment.this.textHint.setText(String.valueOf(i));
            RiverFilterEffectFragment.this.textHint.getPaint().getTextBounds(RiverFilterEffectFragment.this.textHint.getText().toString(), 0, RiverFilterEffectFragment.this.textHint.getText().length(), RiverFilterEffectFragment.this.seekbarHintTextBounds);
            RiverFilterEffectFragment.this.seekbarHintTextLayoutParams.setMargins(bounds.centerX() - (RiverFilterEffectFragment.this.seekbarHintTextBounds.width() / 2), 0, 0, 0);
            RiverFilterEffectFragment.this.textHint.setLayoutParams(RiverFilterEffectFragment.this.seekbarHintTextLayoutParams);
            if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 0) {
                RiverFilterEffectFragment.this.parameterGlobal.setBrightness(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 1) {
                RiverFilterEffectFragment.this.parameterGlobal.setContrast(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 2) {
                RiverFilterEffectFragment.this.parameterGlobal.setTemperature(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 3) {
                RiverFilterEffectFragment.this.parameterGlobal.setSaturation(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 4) {
                RiverFilterEffectFragment.this.parameterGlobal.setTint(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 5) {
                RiverFilterEffectFragment.this.parameterGlobal.setSharpen(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 6) {
                RiverFilterEffectFragment.this.parameterGlobal.setBlur(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 7) {
                RiverFilterEffectFragment.this.parameterGlobal.setHighlight(i);
            } else if (RiverFilterEffectFragment.this.parameterGlobal.seekBarMode == 8) {
                RiverFilterEffectFragment.this.parameterGlobal.setShadow(i);
            }
        }

        @Override 
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (RiverFilterEffectFragment.this.textHint == null) {
                RiverFilterEffectFragment cMFilterEffectFragment = RiverFilterEffectFragment.this;
                cMFilterEffectFragment.textHint = (TextView) cMFilterEffectFragment.getView().findViewById(R.id.seekbar_hint);
            }
            RiverFilterEffectFragment.this.textHint.setVisibility(View.VISIBLE);
        }

        @Override 
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (RiverFilterEffectFragment.this.textHint == null) {
                RiverFilterEffectFragment cMFilterEffectFragment = RiverFilterEffectFragment.this;
                cMFilterEffectFragment.textHint = (TextView) cMFilterEffectFragment.getView().findViewById(R.id.seekbar_hint);
            }
            RiverFilterEffectFragment.this.textHint.setVisibility(View.VISIBLE);
            RiverFilterEffectFragment.this.callBack();
        }
    };
    Collards_Parameter parameterBackUp = new Collards_Parameter();
    int parameterSize = 4;
    Rect seekbarHintTextBounds = new Rect();
    int selectedTab = 0;


    public interface BitmapReady {
        void onBitmapReady(Bitmap bitmap);
    }

    static int getBorderMode(int i) {
        return 0;
    }

    static int isOverlayScreenMode(int i) {
        return i == 2 ? 2 : 1;
    }

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.e(TAG, "onCreate");
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = LayoutInflater.from(this.context).inflate(R.layout.fragment_filter_effect, viewGroup, false);
        this.thumbSize = (int) getResources().getDimension(R.dimen.lib_thumb_save_size);
        this.img_fx = (ImageView) this.view.findViewById(R.id.button_fx);
        this.img_frame = (ImageView) this.view.findViewById(R.id.button_frame);
        this.img_light = (ImageView) this.view.findViewById(R.id.button_light);
        this.img_texture = (ImageView) this.view.findViewById(R.id.button_texture);
        this.img_blur = (ImageView) this.view.findViewById(R.id.button_blur);
        this.img_filter_reset = (ImageView) this.view.findViewById(R.id.button_filter_reset);
        this.txt_lib_cancel = (ImageView) this.view.findViewById(R.id.button_lib_cancel);
        this.txt_lib_ok = (ImageView) this.view.findViewById(R.id.button_lib_ok);
        this.img_fx.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFullEffectFragment.header.setVisibility(View.GONE);
                    RiverFilterEffectFragment.this.SetTabSelected(0);
                    return;
                }
                RiverFilterEffectFragment.this.SetTabSelected(0);
            }
        });
        this.img_frame.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFullEffectFragment.header.setVisibility(View.GONE);
                    RiverFilterEffectFragment.this.SetTabSelected(1);
                    return;
                }
                RiverFilterEffectFragment.this.SetTabSelected(1);
            }
        });
        this.img_light.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFullEffectFragment.header.setVisibility(View.GONE);
                    RiverFilterEffectFragment.this.SetTabSelected(2);
                    return;
                }
                RiverFilterEffectFragment.this.SetTabSelected(2);
            }
        });
        this.img_texture.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFullEffectFragment.header.setVisibility(View.GONE);
                    RiverFilterEffectFragment.this.SetTabSelected(3);
                    return;
                }
                RiverFilterEffectFragment.this.SetTabSelected(3);
            }
        });
        this.img_blur.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFullEffectFragment.header.setVisibility(View.GONE);
                    RiverFilterEffectFragment.this.SetTabSelected(10);
                    RiverFilterEffectFragment.this.parameterGlobal.seekBarMode = 6;
                    RiverFilterEffectFragment.this.SetSeekBarProgress();
                    return;
                }
                RiverFilterEffectFragment.this.SetTabSelected(10);
                RiverFilterEffectFragment.this.parameterGlobal.seekBarMode = 6;
                RiverFilterEffectFragment.this.SetSeekBarProgress();
            }
        });
        this.img_filter_reset.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFullEffectFragment.header.setVisibility(View.VISIBLE);
                    RiverFilterEffectFragment.this.ResetParameters();
                    return;
                }
                RiverFilterEffectFragment.this.ResetParameters();
            }
        });
        this.txt_lib_cancel.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFullEffectFragment.header.setVisibility(View.VISIBLE);
                    RiverFilterEffectFragment.this.CancelViewSwitcher();
                    RiverFilterEffectFragment.this.viewSwitcher.setDisplayedChild(1);
                    return;
                }
                RiverFilterEffectFragment.this.CancelViewSwitcher();
                RiverFilterEffectFragment.this.viewSwitcher.setDisplayedChild(1);
            }
        });
        this.txt_lib_ok.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View view) {
                if (!HiPhi_MainActivity.fromActivity.equals("CollageActivity")) {
                    RiverFilterEffectFragment.this.viewSwitcher.setDisplayedChild(1);
                    return;
                }
                RiverFullEffectFragment.header.setVisibility(View.VISIBLE);
                RiverFilterEffectFragment.this.viewSwitcher.setDisplayedChild(1);
            }
        });
        return this.view;
    }

    @Override 
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(getString(R.string.effect_parameter_bundle_name), this.parameterGlobal);
        super.onSaveInstanceState(bundle);
    }

    @Override 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.parameterGlobal = (Collards_Parameter) bundle.getParcelable(getString(R.string.effect_parameter_bundle_name));
        } else if (getArguments() != null) {
            this.parameterGlobal = (Collards_Parameter) getArguments().getParcelable(getString(R.string.effect_parameter_bundle_name));
        }
        if (this.parameterGlobal == null) {
            this.parameterGlobal = new Collards_Parameter();
        }
        this.context = getActivity();
        this.activity = getActivity();
        SetPaints();
        SetAdapters();
        this.viewSwitcher = (ViewSwitcher) getView().findViewById(R.id.viewswitcher);
        this.viewFlipper = (ViewFlipper) getView().findViewById(R.id.control_container);
        this.slideRightIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_right);
        this.slideRightOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_right);
        this.slideLeftIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_left);
        this.slideLeftOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_left);
        SetTabSelected(this.selectedTab);
        this.viewSwitcher.setDisplayedChild(1);
        Jumper_EffectUtility.ExcludeTabListener excludeTabListener = this.excludeTabListener;
        if (excludeTabListener != null) {
            excludeTabListener.exclude();
        }
        Jumper_EffectUtility.FooterVisibilityListener footerVisibilityListener = this.footerListener;
        if (footerVisibilityListener != null) {
            footerVisibilityListener.setVisibility();
        }
        SeekBar seekBar = (SeekBar) getView().findViewById(R.id.seek_bar_adjustment);
        this.seekBarAdjustment = seekBar;
        seekBar.setOnSeekBarChangeListener(this.mySeekBarListener);
    }

    public void SetPaints() {
        this.sepiaPaint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{0.393f, 0.769f, 0.189f, 0.0f, 0.0f, 0.349f, 0.686f, 0.168f, 0.0f, 0.0f, 0.272f, 0.534f, 0.131f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        this.sepiaPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        this.paintGray = new Paint();
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix2.setSaturation(0.0f);
        this.paintGray.setColorFilter(new ColorMatrixColorFilter(colorMatrix2));
        this.paintInvert = new Paint();
        ColorMatrix colorMatrix3 = new ColorMatrix();
        colorMatrix3.set(new ColorMatrix(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintInvert.setColorFilter(new ColorMatrixColorFilter(colorMatrix3));
        this.paintPolaroid = new Paint();
        ColorMatrix colorMatrix4 = new ColorMatrix();
        colorMatrix4.set(new ColorMatrix(new float[]{2.0f, 0.0f, 0.0f, 0.0f, -130.0f, 0.0f, 2.0f, 0.0f, 0.0f, -130.0f, 0.0f, 0.0f, 2.0f, 0.0f, -130.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintPolaroid.setColorFilter(new ColorMatrixColorFilter(colorMatrix4));
        this.paintScrim = new Paint();
        ColorMatrix colorMatrix5 = new ColorMatrix();
        colorMatrix5.set(new ColorMatrix(new float[]{5.0f, 0.0f, 0.0f, 0.0f, -254.0f, 0.0f, 5.0f, 0.0f, 0.0f, -254.0f, 0.0f, 0.0f, 5.0f, 0.0f, -254.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintScrim.setColorFilter(new ColorMatrixColorFilter(colorMatrix5));
        this.paintABC = new Paint();
        ColorMatrix colorMatrix6 = new ColorMatrix();
        colorMatrix6.set(new ColorMatrix(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintABC.setColorFilter(new ColorMatrixColorFilter(colorMatrix6));
        this.paintABC1 = new Paint();
        ColorMatrix colorMatrix7 = new ColorMatrix();
        colorMatrix7.set(new ColorMatrix(new float[]{-0.36f, 1.691f, -0.32f, 0.0f, 0.0f, 0.325f, 0.398f, 0.275f, 0.0f, 0.0f, 0.79f, 0.796f, -0.76f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintABC1.setColorFilter(new ColorMatrixColorFilter(colorMatrix7));
        this.paintABC2 = new Paint();
        ColorMatrix colorMatrix8 = new ColorMatrix();
        colorMatrix8.set(new ColorMatrix(new float[]{-0.41f, 0.539f, -0.873f, 0.0f, 0.0f, 0.452f, 0.666f, -0.11f, 0.0f, 0.0f, -0.3f, 1.71f, -0.4f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintABC2.setColorFilter(new ColorMatrixColorFilter(colorMatrix8));
        this.paintABC3 = new Paint();
        ColorMatrix colorMatrix9 = new ColorMatrix();
        colorMatrix9.set(new ColorMatrix(new float[]{3.074f, -1.82f, -0.24f, 0.0f, 50.8f, -0.92f, 2.171f, -0.24f, 0.0f, 50.8f, -0.92f, -1.82f, 3.754f, 0.0f, 50.8f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintABC3.setColorFilter(new ColorMatrixColorFilter(colorMatrix9));
        this.paintABC4 = new Paint();
        ColorMatrix colorMatrix10 = new ColorMatrix();
        colorMatrix10.set(new ColorMatrix(new float[]{0.14f, 0.45f, 0.05f, 0.0f, 0.0f, 0.12f, 0.39f, 0.04f, 0.0f, 0.0f, 0.08f, 0.28f, 0.03f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintABC4.setColorFilter(new ColorMatrixColorFilter(colorMatrix10));
        this.paintPurple = new Paint();
        ColorMatrix colorMatrix11 = new ColorMatrix();
        colorMatrix11.set(new ColorMatrix(new float[]{1.0f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -0.1f, 0.0f, 0.0f, 1.2f, 1.0f, 0.1f, 0.0f, 0.0f, 0.0f, 1.7f, 1.0f, 0.0f}));
        this.paintPurple.setColorFilter(new ColorMatrixColorFilter(colorMatrix11));
        this.yellowPaint = new Paint();
        ColorMatrix colorMatrix12 = new ColorMatrix();
        colorMatrix12.set(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.2f, 1.0f, 0.3f, 0.1f, 0.0f, -3.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.yellowPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix12));
        this.paintCyan = new Paint();
        ColorMatrix colorMatrix13 = new ColorMatrix();
        colorMatrix13.set(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 1.9f, -2.2f, 0.0f, 1.0f, 0.0f, 0.0f, 0.3f, 3.0f, 0.0f, 1.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.2f}));
        this.paintCyan.setColorFilter(new ColorMatrixColorFilter(colorMatrix13));
        this.bwPaint = new Paint();
        ColorMatrix colorMatrix14 = new ColorMatrix();
        colorMatrix14.set(new ColorMatrix(new float[]{0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f}));
        this.bwPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix14));
        this.oldTimesPaint = new Paint();
        ColorMatrix colorMatrix15 = new ColorMatrix();
        colorMatrix15.set(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.4f, 1.3f, -0.4f, 0.2f, -0.1f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.oldTimesPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix15));
        this.paintColdlife = new Paint();
        ColorMatrix colorMatrix16 = new ColorMatrix();
        colorMatrix16.set(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, -0.2f, 0.2f, 0.1f, 0.4f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintColdlife.setColorFilter(new ColorMatrixColorFilter(colorMatrix16));
        this.sepiumPaint = new Paint();
        ColorMatrix colorMatrix17 = new ColorMatrix();
        colorMatrix17.set(new ColorMatrix(new float[]{1.3f, -0.3f, 1.1f, 0.0f, 0.0f, 0.0f, 1.3f, 0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.8f, 0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.sepiumPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix17));
        this.paintMilk = new Paint();
        ColorMatrix colorMatrix18 = new ColorMatrix();
        colorMatrix18.set(new ColorMatrix(new float[]{0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintMilk.setColorFilter(new ColorMatrixColorFilter(colorMatrix18));
        this.paintLime = new Paint();
        ColorMatrix colorMatrix19 = new ColorMatrix();
        colorMatrix19.set(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintLime.setColorFilter(new ColorMatrixColorFilter(colorMatrix19));
        this.paintPeachy = new Paint();
        ColorMatrix colorMatrix20 = new ColorMatrix();
        colorMatrix20.set(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintPeachy.setColorFilter(new ColorMatrixColorFilter(colorMatrix20));
        this.paintLighten = new Paint();
        ColorMatrix colorMatrix21 = new ColorMatrix();
        colorMatrix21.set(new ColorMatrix(new float[]{1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintLighten.setColorFilter(new ColorMatrixColorFilter(colorMatrix21));
        this.paintDarken = new Paint();
        ColorMatrix colorMatrix22 = new ColorMatrix();
        colorMatrix22.set(new ColorMatrix(new float[]{0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        this.paintDarken.setColorFilter(new ColorMatrixColorFilter(colorMatrix22));
    }

    private void SetAdapters() {
        Azure_FilterAdapter.IndexChangedListener recyclerAdapterIndexChangedListener = new Azure_FilterAdapter.IndexChangedListener() {
            @Override 
            public void onIndexChanged(int i) {
                RiverFilterEffectFragment.this.ApplyChangesOnBitmap();
            }
        };
        int[] iArr = Jumper_EffectUtility.framesThumbArray;
        Azure_FilterAdapter.IndexChangedListener recyclerAdapterIndexChangedListener2 = this.borderIndexChangedListener;
        Azure_FilterAdapter cMFilterAdapter = new Azure_FilterAdapter(iArr, recyclerAdapterIndexChangedListener2 != null ? recyclerAdapterIndexChangedListener2 : recyclerAdapterIndexChangedListener, R.color.transparent, R.color.pink, 100);
        this.borderAdapter = cMFilterAdapter;
        cMFilterAdapter.setSelectedIndexChangedListener(new Azure_FilterAdapter.SelectedIndexChangedListener() {
            @Override 
            public void selectedIndexChanged(int i) {
                Log.e(RiverFilterEffectFragment.TAG, "selectedIndexChanged " + i);
                RiverFilterEffectFragment.this.parameterGlobal.slctedBorderIndex = i;
            }
        });
        Azure_FilterAdapter cMFilterAdapter2 = new Azure_FilterAdapter(Jumper_EffectUtility.screensThumbArray, new Azure_FilterAdapter.IndexChangedListener() {
            @Override 
            public void onIndexChanged(int i) {
                RiverFilterEffectFragment.this.ApplyChangesOnBitmap();
            }
        }, R.color.transparent, R.color.pink, 100);
        this.textureAdapter = cMFilterAdapter2;
        cMFilterAdapter2.setSelectedIndexChangedListener(new Azure_FilterAdapter.SelectedIndexChangedListener() {
            @Override 
            public void selectedIndexChanged(int i) {
                RiverFilterEffectFragment.this.parameterGlobal.selectedTextureIndex = i;
            }
        });
        Azure_FilterAdapter cMFilterAdapter3 = new Azure_FilterAdapter(Jumper_EffectUtility.overlaysThumbArray, new Azure_FilterAdapter.IndexChangedListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFilterEffectFragment.14
            @Override 
            public void onIndexChanged(int i) {
                RiverFilterEffectFragment.this.ApplyChangesOnBitmap();
            }
        }, R.color.transparent, R.color.pink, 100);
        this.overlayAdapter = cMFilterAdapter3;
        cMFilterAdapter3.setSelectedIndexChangedListener(new Azure_FilterAdapter.SelectedIndexChangedListener() {
            @Override 
            public void selectedIndexChanged(int i) {
                RiverFilterEffectFragment.this.parameterGlobal.slctedOverlayIndex = i;
            }
        });
        Azure_FilterAdapter cMFilterAdapter4 = new Azure_FilterAdapter(Jumper_EffectUtility.filtersThumbArray, new Azure_FilterAdapter.IndexChangedListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFilterEffectFragment.16
            @Override 
            public void onIndexChanged(int i) {
                RiverFilterEffectFragment.this.ApplyChangesOnBitmap();
            }
        }, R.color.transparent, R.color.pink, 100);
        this.CMFilterAdapter = cMFilterAdapter4;
        cMFilterAdapter4.setSelectedIndexChangedListener(new Azure_FilterAdapter.SelectedIndexChangedListener() {
            @Override 
            public void selectedIndexChanged(int i) {
                RiverFilterEffectFragment.this.parameterGlobal.slctedFilterIndex = i;
            }
        });
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.border_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(this.borderAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView recyclerView2 = (RecyclerView) getView().findViewById(R.id.texture_RecyclerView);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false));
        recyclerView2.setAdapter(this.textureAdapter);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        RecyclerView recyclerView3 = (RecyclerView) getView().findViewById(R.id.overlay_RecyclerView);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false));
        recyclerView3.setAdapter(this.overlayAdapter);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        RecyclerView recyclerView4 = (RecyclerView) getView().findViewById(R.id.filter_RecyclerView);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false));
        recyclerView4.setAdapter(this.CMFilterAdapter);
        recyclerView4.setItemAnimator(new DefaultItemAnimator());
        this.textureAdapter.SetSelectedView(this.parameterGlobal.selectedTextureIndex);
        this.borderAdapter.SetSelectedView(this.parameterGlobal.slctedBorderIndex);
        this.overlayAdapter.SetSelectedView(this.parameterGlobal.slctedOverlayIndex);
        if (this.parameterGlobal.slctedFilterIndex >= this.CMFilterAdapter.getItemCount()) {
            this.parameterGlobal.slctedFilterIndex = 0;
        }
        this.CMFilterAdapter.SetSelectedView(this.parameterGlobal.slctedFilterIndex);
    }

    public void SetTabSelected(int i) {
        this.viewSwitcher.setDisplayedChild(0);
        int displayedChild = this.viewFlipper.getDisplayedChild();
        if (i == 0) {
            if (displayedChild == 0) {
                return;
            }
            this.viewFlipper.setInAnimation(this.slideLeftIn);
            this.viewFlipper.setOutAnimation(this.slideRightOut);
            this.viewFlipper.setDisplayedChild(0);
        }
        if (i == 1) {
            if (displayedChild == 1) {
                return;
            }
            if (displayedChild == 0) {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
            } else {
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
            }
            this.viewFlipper.setDisplayedChild(1);
        }
        if (i == 2) {
            if (displayedChild == 2) {
                return;
            }
            if (displayedChild == 3 || displayedChild == 4) {
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
            } else {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
            }
            this.viewFlipper.setDisplayedChild(2);
        }
        if (i == 3) {
            if (displayedChild == 3) {
                return;
            }
            if (displayedChild == 4) {
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
            } else {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
            }
            this.viewFlipper.setDisplayedChild(3);
        }
        if ((i == 4 || i == 6 || i == 7 || i == 5 || i == 8 || i == 9 || i == 10) && displayedChild != 4) {
            this.viewFlipper.setInAnimation(this.slideRightIn);
            this.viewFlipper.setOutAnimation(this.slideLeftOut);
            this.viewFlipper.setDisplayedChild(4);
        }
    }


    public class FilterAndAdjustmentTask extends MyAsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        FilterAndAdjustmentTask() {
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLibCommon.MyAsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            RiverFilterEffectFragment.this.inFilterAndAdjustment = true;
            try {
                ProgressDialog progressDialog = new ProgressDialog(RiverFilterEffectFragment.this.context);
                this.progressDialog = progressDialog;
                progressDialog.setMessage("Please Wait...");
                this.progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLibCommon.MyAsyncTask
        public Void doInBackground(Void... voidArr) {
            if (RiverFilterEffectFragment.this.isAdded()) {
                if (RiverFilterEffectFragment.this.btmpFilter == null) {
                    RiverFilterEffectFragment cMFilterEffectFragment = RiverFilterEffectFragment.this;
                    cMFilterEffectFragment.btmpFilter = cMFilterEffectFragment.sourceBitmap.copy(Bitmap.Config.ARGB_8888, true);
                } else {
                    new Canvas(RiverFilterEffectFragment.this.btmpFilter).drawBitmap(RiverFilterEffectFragment.this.sourceBitmap, 0.0f, 0.0f, new Paint());
                }
                new Canvas(RiverFilterEffectFragment.this.btmpFilter).drawBitmap(RiverFilterEffectFragment.this.sourceBitmap, 0.0f, 0.0f, new Paint());
                if (RiverFilterEffectFragment.this.parameterGlobal.blur > 0 && Build.VERSION.SDK_INT > 17) {
                    final Bitmap copy = RiverFilterEffectFragment.this.sourceBitmap.copy(RiverFilterEffectFragment.this.sourceBitmap.getConfig(), true);
                    try {
                        RiverFilterEffectFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFilterEffectFragment.FilterAndAdjustmentTask.1
                            @Override 
                            public void run() {
                                RiverFilterEffectFragment.this.btmpFilter = NativeStackBlur.process(copy, RiverFilterEffectFragment.this.parameterGlobal.blur);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (RiverFilterEffectFragment.this.isAdded()) {
                    PipeLine(RiverFilterEffectFragment.this.btmpFilter);
                    return null;
                }
                cancel(true);
                RiverFilterEffectFragment.this.inFilterAndAdjustment = false;
                return null;
            }
            RiverFilterEffectFragment.this.inFilterAndAdjustment = false;
            return null;
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLibCommon.MyAsyncTask
        public void onPostExecute(Void r2) {
            super.onPostExecute((Void) r2);
            RiverFilterEffectFragment.this.inFilterAndAdjustment = false;
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (RiverFilterEffectFragment.this.isAdded()) {
                RiverFilterEffectFragment.this.bitmapReadyListener.onBitmapReady(RiverFilterEffectFragment.this.btmpFilter);
            }
        }

        public void PipeLine(Bitmap bitmap) {
            if (RiverFilterEffectFragment.this.parameterGlobal.slctedFilterIndex <= 22) {
                RiverFilterEffectFragment cMFilterEffectFragment = RiverFilterEffectFragment.this;
                cMFilterEffectFragment.SetFilter(cMFilterEffectFragment.parameterGlobal.slctedFilterIndex, bitmap);
            }
            RiverFilterEffectFragment cMFilterEffectFragment2 = RiverFilterEffectFragment.this;
            Bitmap overlayBitmap = cMFilterEffectFragment2.getOverlayBitmap(cMFilterEffectFragment2.parameterGlobal.slctedOverlayIndex);
            if (overlayBitmap != null && !overlayBitmap.isRecycled()) {
                if (Build.VERSION.SDK_INT > 10) {
                    RiverFilterEffectFragment cMFilterEffectFragment3 = RiverFilterEffectFragment.this;
                    cMFilterEffectFragment3.applyOverlay11(overlayBitmap, bitmap, RiverFilterEffectFragment.isOverlayScreenMode(cMFilterEffectFragment3.parameterGlobal.slctedOverlayIndex));
                } else if (RiverFilterEffectFragment.isOverlayScreenMode(RiverFilterEffectFragment.this.overlayAdapter.getSelectedIndex()) != 0) {
                    RiverFilterEffectFragment cMFilterEffectFragment4 = RiverFilterEffectFragment.this;
                    cMFilterEffectFragment4.applyOverlay11(overlayBitmap, bitmap, RiverFilterEffectFragment.isOverlayScreenMode(cMFilterEffectFragment4.parameterGlobal.slctedOverlayIndex));
                }
            }
            RiverFilterEffectFragment cMFilterEffectFragment5 = RiverFilterEffectFragment.this;
            cMFilterEffectFragment5.filterMultiply(bitmap, cMFilterEffectFragment5.parameterGlobal.selectedTextureIndex, false);
            if (RiverFilterEffectFragment.this.borderIndexChangedListener == null) {
                RiverFilterEffectFragment cMFilterEffectFragment6 = RiverFilterEffectFragment.this;
                cMFilterEffectFragment6.setBorder(bitmap, cMFilterEffectFragment6.parameterGlobal.slctedBorderIndex, false);
            }
            Canvas canvas = new Canvas(bitmap);
            if (RiverFilterEffectFragment.this.parameterGlobal.slctedFilterIndex < 22) {
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint());
            }
        }
    }

    public void setBitmapReadyListener(BitmapReady bitmapReady) {
        this.bitmapReadyListener = bitmapReady;
    }

    public void setHideHeaderListener(RiverFullEffectFragment.HideHeaderListener hideHeaderListener) {
        this.hideHeaderListener = hideHeaderListener;
    }

    public boolean ShowToolBar() {
        if (this.viewSwitcher.getDisplayedChild() != 0) {
            return false;
        }
        CancelViewSwitcher();
        this.viewSwitcher.setDisplayedChild(1);
        return true;
    }

    @Override 
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void setBorderIndexChangedListener(Azure_FilterAdapter.IndexChangedListener recyclerAdapterIndexChangedListener) {
        this.borderIndexChangedListener = recyclerAdapterIndexChangedListener;
    }

    public void SetSelectedTabIndex(int i) {
        if (i < 0 || i >= 14) {
            return;
        }
        this.selectedTab = i;
        if (getView() != null) {
            SetTabSelected(i);
        }
    }

    public int GetSelectedTabIndex() {
        ViewFlipper viewFlipper = this.viewFlipper;
        if (viewFlipper != null) {
            return viewFlipper.getDisplayedChild();
        }
        return -1;
    }

    public void SetSeekBarProgress() {
        int shadowValue;
        if (this.parameterGlobal.seekBarMode == 0) {
            shadowValue = this.parameterGlobal.getBrightProgress();
        } else if (this.parameterGlobal.seekBarMode == 1) {
            shadowValue = this.parameterGlobal.getContrastProgress();
        } else if (this.parameterGlobal.seekBarMode == 2) {
            shadowValue = this.parameterGlobal.getTemperatureProgress();
        } else if (this.parameterGlobal.seekBarMode == 3) {
            shadowValue = this.parameterGlobal.saturation;
        } else if (this.parameterGlobal.seekBarMode == 4) {
            shadowValue = this.parameterGlobal.getTintProgressValue();
        } else if (this.parameterGlobal.seekBarMode == 5) {
            shadowValue = this.parameterGlobal.getSharpenValue();
        } else if (this.parameterGlobal.seekBarMode == 6) {
            shadowValue = this.parameterGlobal.getBlurValue();
        } else if (this.parameterGlobal.seekBarMode == 7) {
            shadowValue = this.parameterGlobal.getHighlightValue();
        } else {
            shadowValue = this.parameterGlobal.seekBarMode == 8 ? this.parameterGlobal.getShadowValue() : 50;
        }
        this.seekBarAdjustment.setProgress(shadowValue);
    }

    public void callBack() {
        ExecQueue();
    }

    public void setBitmap(Bitmap bitmap) {
        this.sourceBitmap = bitmap;
        this.bitmapWidth = bitmap.getWidth();
        this.bitmapHeight = this.sourceBitmap.getHeight();
        this.btmpFilter = null;
    }

    public void setBitmapAndResetBlur(Bitmap bitmap) {
        setBitmap(bitmap);
        Log.e(TAG, "setBitmapAndResetBlur setBitmapAndResetBlur");
        Bitmap bitmap2 = this.bitmapTiltBlur;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.bitmapTiltBlur.recycle();
        }
        this.bitmapTiltBlur = null;
    }

    @Override 
    public void onDestroyView() {
        super.onDestroyView();
    }

    public synchronized void setBorder(Bitmap bitmap, int i, boolean z) {
        Bitmap decodeResource;
        if (isAdded() && i != 0 && Jumper_EffectUtility.framesArray.length > i) {
            Paint paint = new Paint(1);
            if (getBorderMode(i) == 1) {
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
            }
            Matrix matrix = new Matrix();
            if (z) {
                decodeResource = BitmapFactory.decodeResource(getResources(), Jumper_EffectUtility.framesThumbArray[i]);
            } else {
                decodeResource = BitmapFactory.decodeResource(getResources(), Jumper_EffectUtility.framesArray[i]);
            }
            float width = bitmap.getWidth() / decodeResource.getWidth();
            float height = bitmap.getHeight() / decodeResource.getHeight();
            matrix.reset();
            Canvas canvas = new Canvas(bitmap);
            matrix.postScale(width, height);
            canvas.drawBitmap(decodeResource, matrix, paint);
            if (decodeResource != null && bitmap != decodeResource) {
                decodeResource.recycle();
            }
        }
    }

    public void filterMultiply(Bitmap bitmap, int i, boolean z) {
        Bitmap decodeResource;
        if (i == 0 || !isAdded()) {
            return;
        }
        Paint paint = new Paint(1);
        PorterDuff.Mode mode = PorterDuff.Mode.SCREEN;
        if (Jumper_EffectUtility.screenModesList[i] == Jumper_EffectUtility.EFFECT_MODE_MULTIPLY) {
            mode = PorterDuff.Mode.MULTIPLY;
        } else if (Jumper_EffectUtility.screenModesList[i] == Jumper_EffectUtility.EFFECT_MODE_OVERLAY && Build.VERSION.SDK_INT > 10) {
            mode = PorterDuff.Mode.OVERLAY;
        } else if (Jumper_EffectUtility.screenModesList[i] == Jumper_EffectUtility.EFFECT_MODE_OVERLAY && Build.VERSION.SDK_INT <= 10) {
            mode = PorterDuff.Mode.MULTIPLY;
        }
        paint.setXfermode(new PorterDuffXfermode(mode));
        Matrix matrix = new Matrix();
        if (z) {
            decodeResource = BitmapFactory.decodeResource(getResources(), Jumper_EffectUtility.screensThumbArray[i]);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (Jumper_EffectUtility.GetFreeSpaceOfMemory() > 1.024E7d) {
                options.inSampleSize = 1;
            } else {
                options.inSampleSize = 2;
            }
            decodeResource = BitmapFactory.decodeResource(getResources(), Jumper_EffectUtility.screensArray[i], options);
        }
        float width = bitmap.getWidth() / decodeResource.getWidth();
        float height = bitmap.getHeight() / decodeResource.getHeight();
        matrix.reset();
        Canvas canvas = new Canvas(bitmap);
        matrix.postScale(width, height);
        canvas.drawBitmap(decodeResource, matrix, paint);
        if (decodeResource == null || bitmap == decodeResource) {
            return;
        }
        decodeResource.recycle();
    }

    public Bitmap getOverlayBitmap(int i) {
        if (isAdded()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            if (Jumper_EffectUtility.GetFreeSpaceOfMemory() > 1.024E7d) {
                options.inSampleSize = 1;
            } else {
                options.inSampleSize = 2;
            }
            if (i <= 0 || i >= Jumper_EffectUtility.overlaysArray.length) {
                return null;
            }
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), Jumper_EffectUtility.overlaysArray[i], options);
            if (decodeResource.getConfig() != Bitmap.Config.ARGB_8888) {
                Bitmap copy = decodeResource.copy(Bitmap.Config.ARGB_8888, false);
                if (copy != decodeResource) {
                    decodeResource.recycle();
                }
                decodeResource = copy;
            }
            int width = decodeResource.getWidth();
            int height = decodeResource.getHeight();
            int i2 = this.bitmapHeight;
            int i3 = this.bitmapWidth;
            if ((i2 <= i3 || height >= width) && (i2 >= i3 || height <= width)) {
                return decodeResource;
            }
            Matrix matrix = new Matrix();
            matrix.postRotate(90.0f);
            Bitmap createBitmap = Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix, true);
            if (createBitmap != decodeResource) {
                decodeResource.recycle();
            }
            return createBitmap;
        }
        return null;
    }

    public void applyOverlay11(Bitmap bitmap, Bitmap bitmap2, int i) {
        Paint paint = new Paint(1);
        paint.setFilterBitmap(true);
        PorterDuff.Mode mode = PorterDuff.Mode.SCREEN;
        if (i == 0) {
            mode = PorterDuff.Mode.OVERLAY;
        }
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(mode);
        if (i == 2) {
            porterDuffXfermode = null;
        }
        paint.setXfermode(porterDuffXfermode);
        Matrix matrix = new Matrix();
        float width = bitmap2.getWidth() / bitmap.getWidth();
        float height = bitmap2.getHeight() / bitmap.getHeight();
        matrix.reset();
        Canvas canvas = new Canvas(bitmap2);
        matrix.postScale(width, height);
        canvas.drawBitmap(bitmap, matrix, paint);
    }

    public void CancelViewSwitcher() {
        Log.e(TAG, "parameterGlobal borderAdapter index " + this.parameterGlobal.slctedBorderIndex);
        Log.e(TAG, "parameterBackUp index " + this.parameterBackUp.slctedBorderIndex);
        Log.e(TAG, "borderAdapter index " + this.borderAdapter.getSelectedIndex());
        if (this.parameterGlobal.isParameterReallyChanged(this.parameterBackUp)) {
            this.parameterGlobal.set(this.parameterBackUp);
            this.textureAdapter.SetSelectedView(this.parameterGlobal.selectedTextureIndex);
            this.borderAdapter.SetSelectedView(this.parameterGlobal.slctedBorderIndex);
            Azure_FilterAdapter.IndexChangedListener recyclerAdapterIndexChangedListener = this.borderIndexChangedListener;
            if (recyclerAdapterIndexChangedListener != null) {
                recyclerAdapterIndexChangedListener.onIndexChanged(this.parameterGlobal.slctedBorderIndex);
            }
            Log.e(TAG, "borderAdapter index " + this.borderAdapter.getSelectedIndex());
            this.overlayAdapter.SetSelectedView(this.parameterGlobal.slctedOverlayIndex);
            if (this.parameterGlobal.slctedFilterIndex >= this.CMFilterAdapter.getItemCount()) {
                this.parameterGlobal.slctedFilterIndex = 0;
            }
            this.CMFilterAdapter.SetSelectedView(this.parameterGlobal.slctedFilterIndex);
            ExecQueue();
        }
    }

    public void ResetParameters() {
        this.parameterGlobal.reset();
        SetAdjustmentSeekbarProgress();
    }

    public void setParameters(Collards_Parameter cMParameter) {
        if (cMParameter != null) {
            this.parameterGlobal.set(cMParameter);
            SetAdjustmentSeekbarProgress();
        }
    }

    public void ResetParametersWithoutChange() {
        this.parameterGlobal.reset();
        SetSelectedIndexes();
        SetSeekBarProgress();
    }

    public void SetAdjustmentSeekbarProgress() {
        SetSeekBarProgress();
        SetSelectedIndexes();
        ExecQueue();
    }

    public void SetSelectedIndexes() {
        this.textureAdapter.SetSelectedView(this.parameterGlobal.selectedTextureIndex);
        this.borderAdapter.SetSelectedView(this.parameterGlobal.slctedBorderIndex);
        this.overlayAdapter.SetSelectedView(this.parameterGlobal.slctedOverlayIndex);
        this.CMFilterAdapter.SetSelectedView(this.parameterGlobal.slctedFilterIndex);
    }

    public void ApplyChangesOnBitmap() {
        this.parameterGlobal.slctedFilterIndex = this.CMFilterAdapter.getSelectedIndex();
        this.parameterGlobal.slctedBorderIndex = this.borderAdapter.getSelectedIndex();
        this.parameterGlobal.selectedTextureIndex = this.textureAdapter.getSelectedIndex();
        this.parameterGlobal.slctedOverlayIndex = this.overlayAdapter.getSelectedIndex();
        ExecQueue();
    }

    public void ExecQueue() {
        FilterAndAdjustmentTask filterAndAdjustmentTask = this.ft;
        if (filterAndAdjustmentTask == null || filterAndAdjustmentTask.getStatus() != MyAsyncTask.Status.RUNNING) {
            FilterAndAdjustmentTask filterAndAdjustmentTask2 = new FilterAndAdjustmentTask();
            this.ft = filterAndAdjustmentTask2;
            try {
                filterAndAdjustmentTask2.execute(new Void[0]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void SetFilter(int i, Bitmap bitmap) {
        if (i >= this.filterBitmapTitle.length) {
            i = 0;
        }
        int i2 = i - 1;
        if (Build.VERSION.SDK_INT == 7 || i2 == -1) {
            return;
        }
        if (i2 == 0) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintGray);
        } else if (i2 == 1) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.sepiaPaint);
        } else if (i2 == 2) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintPurple);
        } else if (i2 == 3) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.yellowPaint);
        } else if (i2 == 4) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintMilk);
        } else if (i2 == 5) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintColdlife);
        } else if (i2 == 6) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.bwPaint);
        } else if (i2 == 7) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintLime);
        } else if (i2 == 8) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.sepiumPaint);
        } else if (i2 == 9) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.oldTimesPaint);
        } else if (i2 == 10) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintCyan);
        } else if (i2 == 11) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintPolaroid);
        } else if (i2 == 12) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintInvert);
        } else if (i2 == 13) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintABC1);
        } else if (i2 == 14) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintABC4);
        } else if (i2 == 15) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintLighten);
        } else if (i2 == 16) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintABC3);
        } else if (i2 == 17) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintScrim);
        } else if (i2 == 18) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintABC2);
        } else if (i2 == 19) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintDarken);
        } else if (i2 == 20) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintABC);
        } else if (i2 == 21) {
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, this.paintPeachy);
        }
    }
}
