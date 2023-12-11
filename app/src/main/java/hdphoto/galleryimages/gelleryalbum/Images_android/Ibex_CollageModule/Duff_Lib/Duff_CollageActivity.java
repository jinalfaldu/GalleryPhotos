package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.commit451.nativestackblur.NativeStackBlur;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.HiPhi_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.Inchworm_ShareActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.stickers.StickerDataActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Heart_Adapter.Azure_PatternCategoryAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Heart_Adapter.OnItemClickListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.PatternsResponse;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApiService.Injection;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.ApplyTextInterface;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CustomRelativeLayout;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtTextData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtTextLibHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.SingleTap;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom.CustomColorPicker;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom.CustomDrawerToggle;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments.RiverFontFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments.RiverFullEffectFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery.Peyton_GalleryDataFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon.Collards_Parameter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.Cashier_BlurBuilderNormal;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.Musician_PicassoGenericTarget;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Luxgen_ShapeLayout.Rabbit_CollageLayout;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Luxgen_ShapeLayout.Spyker_CollageShapeUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Luxgen_ShapeLayout.Spyker_MaskPair;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerItemModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerView;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.Bubbles;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.Butterfly;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.CustomViewPagerAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.CustomViewPagerAdapter1;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.Heart;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.Light;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.Sparkles;
import hdphoto.galleryimages.gelleryalbum.Images_android.Sticker.StickerImageView;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Duff_CollageActivity extends AppCompatActivity implements RiverFontFragment.OnFontSelectedListener, CollageContract.View, CustomColorPicker.OnBackgroundSelectedListener {
    public static boolean checkTextApply = false;
    public static boolean checkTextFragment = false;
    public static CustomRelativeLayout customRelativeLayout;
    public static int height;
    public static RelativeLayout mainLayout;
    public static RelativeLayout rl_bottom;
    public static FrameLayout rl_mainFull;
    public static RelativeLayout rl_toolbar;
    public static StickerImageView stickerImageView;
    public static ArrayList<Integer> stickerviewId = new ArrayList<>();
    public static int view_id = 1;
    public static int width;
    FragmentActivity activityFragment;
    int backgroundMode;
    Bitmap btmpDelete;
    Bitmap[] btmpList;
    Bitmap btmpScale;
    ArrayList<Bitmap> btmpStickers;
    CustomDrawerToggle btnBackground;
    Azure_PatternCategoryAdapter cmPatternCategoryAdapter;
    Aspid_ShapeAdapter collageAdapter;
    RecyclerView collageRCV;
    CollageView collageView;
    RelativeLayout collage_text_view_fragment_container;
    ViewGroup contextFooter;
    Dialog deleteImageDialog;
    Dialog dialogPattern;
    FrameLayout effect_fragment_container;
    RiverFontFragment fontFragment;
    FrameLayout frame_text;
    RiverFullEffectFragment fullEffectFragment;
    ImageView img_cllg_adj;
    ImageView img_cllg_background;
    ImageView img_cllg_blur;
    ImageView img_cllg_layout;
    ImageView img_cllg_ratio;
    ImageView img_cllg_space;
    ImageView img_cllg_sticker;
    ImageView img_text;
    private int index;
    private int index2;
    LinearLayout ll_blur_container;
    LinearLayout ll_space_layout;
    Ledl_StickerView mCurrentView;
    Anadol_RotationGestureDetector mRotationDetector;
    ArrayList<View> mViewsArray;
    private NestedScrollView nestedScrollView;
    LinearLayout overlay2;
    Collards_Parameter[] parameterList;
    NinePatchDrawable patchDrawable;
    CollageContract.Presenter presenter;
    HorizontalScrollView ratio_horizontalScrollView;
    RelativeLayout rl_mainLayout;
    Dialog saveImageDialog;
    SeekBar seekBarBlur;
    SeekBar seekBarPadding;
    SeekBar seekBarRound;
    SeekBar seekBarSize;
    Animation slideLeftIn;
    Animation slideLeftOut;
    Animation slideRightIn;
    Animation slideRightOut;
    LinearLayout stickerlay;
    View[] tabButtonList;
    TabLayout tabLayout;
    private TabLayout tabLayout1;
    FrameLayout textAndStickerViewContainer;
    CtTextLibHelper textLibHelper;
    TextView[] txtRatioButtonArray;
    ViewFlipper viewFlipper;
    private CustomViewPagerAdapter viewPagerAdapter;
    private CustomViewPagerAdapter1 viewPagerAdapter1;
    private ViewPager view_pager;
    private ViewPager view_pager1;
    View vvSelectFilterTextView;
    View vvSelectSwapTextView;
    private final ArrayList<Fragment> listOfFragments = new ArrayList<>();
    private final ArrayList<Fragment> listOfFragments1 = new ArrayList<>();
    private final ArrayList<String> listOfTabTitles = new ArrayList<>();
    private final ArrayList<String> listOfTabTitles1 = new ArrayList<>();
    String TAG = "CollageView";
    float UPPER_SIZE_FOR_LOAD = 1500.0f;
    int RATIO_BUTTON_SIZE = 11;
    boolean isScrapBook = false;
    SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.1
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int id = seekBar.getId();
            if (id == R.id.seekbar_round) {
                if (Duff_CollageActivity.this.collageView != null) {
                    Duff_CollageActivity.this.collageView.setCornerRadius(i);
                }
            } else if (id == R.id.seekbar_padding) {
                if (Duff_CollageActivity.this.collageView != null) {
                    Duff_CollageActivity.this.collageView.setPathPadding(Duff_CollageActivity.this.collageView.currentCollageIndex, i);
                }
            } else if (id != R.id.seekbar_size || Duff_CollageActivity.this.collageView == null) {
            } else {
                Duff_CollageActivity.this.collageView.setCollageSize(Duff_CollageActivity.this.collageView.sizeMatrix, i);
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (seekBar.getId() == R.id.seekbar_collage_blur) {
                float progress = seekBar.getProgress() / 4.0f;
                if (progress > 25.0f) {
                    progress = 25.0f;
                }
                if (progress < 0.0f) {
                    progress = 0.0f;
                }
                String str = Duff_CollageActivity.this.TAG;
                Log.e(str, "blur radius " + progress);
                Duff_CollageActivity.this.collageView.setBlurBitmap((int) progress, false);
            }
        }
    };
    float mulX = 1.0f;
    float mulY = 1.0f;
    boolean selectImageForAdj = false;
    boolean showText = false;
    boolean swapMode = false;
    ArrayList<CtTextData> textDataList = new ArrayList<>();
    int textFragemntContinerId = R.id.collage_text_view_fragment_container;
    Paint patternPaint = new Paint(1);

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_collage);
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        this.mViewsArray = new ArrayList<>();
        this.btmpStickers = new ArrayList<>();
        this.rl_mainLayout = (RelativeLayout) findViewById(R.id.rl_mainLayout);
        this.activityFragment = this;
        findViewById(this.textFragemntContinerId).bringToFront();
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_scale_image);
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                relativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int measuredWidth = relativeLayout.getMeasuredWidth();
                int measuredHeight = relativeLayout.getMeasuredHeight();
                Duff_CollageActivity.width = measuredWidth;
                Duff_CollageActivity.height = measuredHeight - 30;
            }
        });
        this.collage_text_view_fragment_container = (RelativeLayout) findViewById(R.id.collage_text_view_fragment_container);
        Bundle extras = getIntent().getExtras();
        int collageSize = getCollageSize(extras);
        rl_toolbar = (RelativeLayout) findViewById(R.id.rl_toolbar);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);

        this.seekBarRound = (SeekBar) findViewById(R.id.seekbar_round);
        this.seekBarPadding = (SeekBar) findViewById(R.id.seekbar_padding);
        this.seekBarSize = (SeekBar) findViewById(R.id.seekbar_size);
        this.seekBarBlur = (SeekBar) findViewById(R.id.seekbar_collage_blur);
        this.seekBarRound.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekBarPadding.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekBarSize.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekBarBlur.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.collageRCV = (RecyclerView) findViewById(R.id.recyclerView_grid);
        this.ll_blur_container = (LinearLayout) findViewById(R.id.seekbar_blur_container);
        this.ll_space_layout = (LinearLayout) findViewById(R.id.ll_space_layout);
        this.frame_text = (FrameLayout) findViewById(R.id.frame_text);
        this.effect_fragment_container = (FrameLayout) findViewById(R.id.collage_effect_fragment_container);
        this.ratio_horizontalScrollView = (HorizontalScrollView) findViewById(R.id.collage_ratio_horizontalScrollView);
        this.stickerlay = (LinearLayout) findViewById(R.id.stickerlay);
        this.tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        this.view_pager = (ViewPager) findViewById(R.id.view_pager);
        this.nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        this.view_pager1 = (ViewPager) findViewById(R.id.view_pager2);
        this.tabLayout1 = (TabLayout) findViewById(R.id.tab_layout2);
        this.overlay2 = (LinearLayout) findViewById(R.id.overlay2);
        rl_mainFull = (FrameLayout) findViewById(R.id.rl_mainFull);
        this.stickerlay.setVisibility(8);
        this.overlay2.setVisibility(8);
        this.index = getIntent().getIntExtra("index", 0);
        this.listOfFragments.add(new Bubbles());
        this.listOfFragments.add(new Butterfly());
        this.listOfFragments.add(new Heart());
        this.listOfFragments.add(new Light());
        this.listOfTabTitles.add("Bubbles");
        this.listOfTabTitles.add("Butterfly");
        this.listOfTabTitles.add("Love");
        this.listOfTabTitles.add("Texts");
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager(), this.listOfFragments, this.listOfTabTitles);
        this.viewPagerAdapter = customViewPagerAdapter;
        this.view_pager.setAdapter(customViewPagerAdapter);
        this.tabLayout.setupWithViewPager(this.view_pager);
        this.view_pager.setCurrentItem(this.index);
        this.listOfFragments1.add(new Bubbles());
        this.listOfFragments1.add(new Butterfly());
        this.listOfFragments1.add(new Heart());
        this.listOfFragments1.add(new Light());
        this.listOfFragments1.add(new Sparkles());
        this.listOfTabTitles1.add("Bubbles");
        this.listOfTabTitles1.add("Butterfly");
        this.listOfTabTitles1.add("Heart");
        this.listOfTabTitles1.add("Light");
        this.listOfTabTitles1.add("Sparkles");
        CustomViewPagerAdapter1 customViewPagerAdapter1 = new CustomViewPagerAdapter1(getSupportFragmentManager(), this.listOfFragments1, this.listOfTabTitles1);
        this.viewPagerAdapter1 = customViewPagerAdapter1;
        this.view_pager1.setAdapter(customViewPagerAdapter1);
        this.tabLayout1.setupWithViewPager(this.view_pager1);
        this.view_pager1.setCurrentItem(this.index);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.collage_main_layout);
        mainLayout = relativeLayout2;
        relativeLayout2.setBackgroundColor(getResources().getColor(R.color.white));
        this.view_pager.setOnTouchListener(new View.OnTouchListener() { 
            int downX;
            int downY;
            final int dragthreshold = 30;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.downX = (int) motionEvent.getRawX();
                    this.downY = (int) motionEvent.getRawY();
                } else if (action == 1) {
                    Duff_CollageActivity.this.nestedScrollView.getParent().requestDisallowInterceptTouchEvent(false);
                    Duff_CollageActivity.this.view_pager.getParent().requestDisallowInterceptTouchEvent(false);
                } else if (action == 2) {
                    int abs = Math.abs(((int) motionEvent.getRawX()) - this.downX);
                    int abs2 = Math.abs(((int) motionEvent.getRawY()) - this.downY);
                    if (abs2 > abs && abs2 > 30) {
                        Duff_CollageActivity.this.view_pager.getParent().requestDisallowInterceptTouchEvent(false);
                        Duff_CollageActivity.this.nestedScrollView.getParent().requestDisallowInterceptTouchEvent(true);
                    } else if (abs > abs2 && abs > 30) {
                        Duff_CollageActivity.this.view_pager.getParent().requestDisallowInterceptTouchEvent(true);
                        Duff_CollageActivity.this.nestedScrollView.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        int color = getResources().getColor(R.color.transparent);
        int color2 = getResources().getColor(R.color.selectedTool);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.collageRCV.setLayoutManager(linearLayoutManager);
        Aspid_ShapeAdapter cMShapeAdapter = new Aspid_ShapeAdapter(this, Spyker_CollageShapeUtils.collageShapeIconsArray[collageSize - 1], new Aspid_ShapeAdapter.CurrentCollageIndexChangedListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.4
            @Override 
            public void onIndexChanged(int i) {
                Duff_CollageActivity.this.collageView.setCurrentCollageIndex(i);
            }
        }, color, color2, true);
        this.collageAdapter = cMShapeAdapter;
        this.collageRCV.setAdapter(cMShapeAdapter);
        this.collageRCV.setItemAnimator(new DefaultItemAnimator());
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.collage_view_flipper);
        this.viewFlipper = viewFlipper;
        viewFlipper.setDisplayedChild(5);
        final HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.collage_footer);
        horizontalScrollView.bringToFront();
        horizontalScrollView.postDelayed(new Runnable() { 
            @Override 
            public void run() {
                HorizontalScrollView horizontalScrollView2 = horizontalScrollView;
                horizontalScrollView2.scrollTo(horizontalScrollView2.getChildAt(0).getMeasuredWidth(), 0);
            }
        }, 50L);
        horizontalScrollView.postDelayed(new Runnable() { 
            @Override 
            public void run() {
                horizontalScrollView.fullScroll(17);
            }
        }, 600L);
        new BitmapWorkerTask().execute(extras, bundle);
        this.img_cllg_layout = (ImageView) findViewById(R.id.button_collage_layout);
        this.img_cllg_sticker = (ImageView) findViewById(R.id.button_collage_sticker);
        this.img_cllg_blur = (ImageView) findViewById(R.id.button_collage_blur);
        this.img_cllg_space = (ImageView) findViewById(R.id.button_collage_space);
        this.img_cllg_ratio = (ImageView) findViewById(R.id.button_collage_ratio);
        this.img_text = (ImageView) findViewById(R.id.button_text);
        this.img_cllg_adj = (ImageView) findViewById(R.id.button_collage_adj);
        this.img_cllg_background = (ImageView) findViewById(R.id.button_collage_background);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.SaveImageAlertDialog();
            }
        });
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.SetSelectedTab(5);
                new SaveImageAsync().execute(new Object[0]);
            }
        });
        this.rl_mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Duff_CollageActivity.this.removeBorder();
                return false;
            }
        });
    }


    public Bitmap getbitmap(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    
    public void removeBorder() {
        for (int i = 0; i < stickerviewId.size(); i++) {
            View findViewById = this.rl_mainLayout.findViewById(stickerviewId.get(i).intValue());
            if (findViewById instanceof StickerImageView) {
                ((StickerImageView) findViewById).setControlItemsHidden(true);
            }
        }
    }

    
    public void saveImage(Bitmap bitmap) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + Glob.Edit_Folder_name);
        file.mkdirs();
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpeg";
        File file2 = new File(file, str);
        file2.renameTo(file2);
        String str2 = "file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + Glob.Edit_Folder_name + "/" + str;
        Glob._url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + Glob.Edit_Folder_name + "/" + str;
        Log.d("cache uri=", str2);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str2))));
            MediaScannerConnection.scanFile(this, new String[]{Glob._url}, null, new MediaScannerConnection.MediaScannerConnectionClient() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.10
                @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
                public void onMediaScannerConnected() {
                }

                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str3, Uri uri) {
                    Glob._url = str3;
                    Glob.testuri = uri;
                }
            });
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str2))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadPattern(PatternsResponse.Pattern pattern, int i) {
        Picasso.get().load(pattern.getUri()).into(new Musician_PicassoGenericTarget() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.11
            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMMirrorUtils.CMPicassoGenericTarget, com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                Duff_CollageActivity.this.collageView.setPatternPaint(bitmap);
                Duff_CollageActivity.this.ChangeBtnSelection(8);
                Duff_CollageActivity.this.dialogPattern.dismiss();
            }
        });
    }

    private void PatternDialog() {
        Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        this.dialogPattern = dialog;
        dialog.requestWindowFeature(1);
        this.dialogPattern.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialogPattern.setContentView(R.layout.dg_pattern_collage);
        this.dialogPattern.setCancelable(false);
        this.dialogPattern.setCanceledOnTouchOutside(false);
        this.cmPatternCategoryAdapter = new Azure_PatternCategoryAdapter(new ArrayList(), new OnItemClickListener() {
            @Override
            public final void onItemClicked(Object obj, int i) {
                Duff_CollageActivity.this.LoadPattern((PatternsResponse.Pattern) obj, i);
            }
        });
        RecyclerView recyclerView = (RecyclerView) this.dialogPattern.findViewById(R.id.rcv_patterns_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        recyclerView.setAdapter(this.cmPatternCategoryAdapter);
        Eel_CollagePresenter cMCollagePresenter = new Eel_CollagePresenter(this, Injection.provideApiService());
        this.presenter = cMCollagePresenter;
        cMCollagePresenter.loadPatterns();
        this.dialogPattern.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.ChangeBtnSelection(8);
                Duff_CollageActivity.this.dialogPattern.dismiss();
            }
        });
        this.dialogPattern.findViewById(R.id.rl_color).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                CustomColorPicker.show(Duff_CollageActivity.this.getSupportFragmentManager());
                Duff_CollageActivity.this.dialogPattern.dismiss();
            }
        });
        this.dialogPattern.show();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            SaveImageAlertDialog();
            return false;
        } else if (itemId != R.id.mi_save) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            SetSelectedTab(5);
            new SaveImageAsync().execute(new Object[0]);
            return true;
        }
    }

    @Override
    public void onFontSelected(CtTextData cMTextData) {
        if (customRelativeLayout == null) {
            AddCanvasTextView(cMTextData);
        }
        customRelativeLayout.addTextView(cMTextData);
        getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
    }

    @Override
    public void onFontSelectionCancelled() {
        this.showText = true;
        mainLayout.removeView(customRelativeLayout);
        this.collageView.postInvalidate();
    }

    public int getCollageSize(Bundle bundle) {
        long[] longArray = bundle.getLongArray("photo_id_list");
        if (longArray == null) {
            return 1;
        }
        return longArray.length;
    }

    public void AddCanvasTextView(CtTextData cMTextData) {
        CustomRelativeLayout cMCustomRelativeLayout = new CustomRelativeLayout(this, this.textDataList, this.collageView.identityMatrix, new SingleTap() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.15
            @Override
            public void onSingleTap(CtTextData cMTextData2) {
                Duff_CollageActivity.this.addCTVonSingleTap(cMTextData2);
            }
        });
        customRelativeLayout = cMCustomRelativeLayout;
        cMCustomRelativeLayout.setApplyTextListener(new ApplyTextInterface() {
            @Override
            public void onOk(ArrayList<CtTextData> arrayList) {
                Iterator<CtTextData> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().setImageSaveMatrix(Duff_CollageActivity.this.collageView.identityMatrix);
                }
                Duff_CollageActivity.this.textDataList = arrayList;
                Duff_CollageActivity.this.showText = true;
                if (Duff_CollageActivity.mainLayout == null) {
                    Duff_CollageActivity.mainLayout = (RelativeLayout) Duff_CollageActivity.this.findViewById(R.id.collage_main_layout);
                }
                Duff_CollageActivity.mainLayout.removeView(Duff_CollageActivity.customRelativeLayout);
                Duff_CollageActivity.this.collageView.postInvalidate();
            }

            @Override
            public void onCancel() {
                Duff_CollageActivity.this.showText = true;
                Duff_CollageActivity.mainLayout.removeView(Duff_CollageActivity.customRelativeLayout);
                Duff_CollageActivity.this.collageView.postInvalidate();
            }
        });
        this.showText = false;
        this.collageView.invalidate();
        customRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        mainLayout.addView(customRelativeLayout);
        findViewById(R.id.collage_text_view_fragment_container).bringToFront();
        RiverFontFragment cMFontFragment = new RiverFontFragment();
        this.fontFragment = cMFontFragment;
        cMFontFragment.setArguments(new Bundle());
        getSupportFragmentManager().beginTransaction().add(R.id.collage_text_view_fragment_container, this.fontFragment, "FONT_FRAGMENT").commit();
    }

    public void addCTVonSingleTap(CtTextData cMTextData) {
        this.fontFragment = new RiverFontFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RiverFontFragment.TEXT_DATA_BUNDLE_KEY, cMTextData);
        this.fontFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.collage_text_view_fragment_container, this.fontFragment, "FONT_FRAGMENT").commit();
    }

    public void applyTextBackground(CtTextData cMTextData, Canvas canvas) {
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

    @Override
    public void onSolidBackgroundSelected(int i) {
        this.collageView.setPatternPaintColor(i);
    }

    @Override
    public void onGradientBackgroundSelected(int i, int i2) {
        this.collageView.setPatternPaintGradient(i, i2);
    }

    public void onSwap(View view) {
        if (this.collageView.shapeLayoutList.get(this.collageView.currentCollageIndex).shapeArr.length == 2) {
            this.collageView.SwapBitmaps(0, 1);
            return;
        }
        this.vvSelectSwapTextView.setVisibility(0);
        this.swapMode = true;
    }

    public void onDelete(View view) {
        createDeleteDialog();
    }

    public void onFill(View view) {
        this.collageView.setShapeScaleMatrix(1);
    }

    public void onInside(View view) {
        this.collageView.setShapeScaleMatrix(0);
    }

    public void onFilter(View view) {
        ChangeBtnSelection(7);
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        this.collageRCV.setVisibility(8);
        this.ll_blur_container.setVisibility(8);
        this.ll_space_layout.setVisibility(8);
        this.ratio_horizontalScrollView.setVisibility(8);
        this.frame_text.setVisibility(8);
        this.effect_fragment_container.setVisibility(0);
        if (this.collageView.shapeLayoutList.get(0).shapeArr.length == 1) {
            this.collageView.shapeIndex = 0;
            this.collageView.openFilterFragment();
        } else if (this.collageView.shapeIndex >= 0) {
            this.collageView.openFilterFragment();
        } else {
            SetSelectedTab(5);
            this.vvSelectFilterTextView.setVisibility(0);
            this.selectImageForAdj = true;
        }
        rl_toolbar.setVisibility(0);
    }

    public void onLeftRotate(View view) {
        this.collageView.setShapeScaleMatrix(3);
    }

    public void onRightRotate(View view) {
        this.collageView.setShapeScaleMatrix(2);
    }

    public void onFlipHorizontal(View view) {
        this.collageView.setShapeScaleMatrix(4);
    }

    public void onFlipVertical(View view) {
        this.collageView.setShapeScaleMatrix(5);
    }

    public void onZoomIn(View view) {
        ToastMatrixMessage(this.collageView.setShapeScaleMatrix(8));
    }

    public void onZoomOut(View view) {
        ToastMatrixMessage(this.collageView.setShapeScaleMatrix(9));
    }

    public void onLeftMove(View view) {
        ToastMatrixMessage(this.collageView.setShapeScaleMatrix(10));
    }

    public void onRightMove(View view) {
        ToastMatrixMessage(this.collageView.setShapeScaleMatrix(11));
    }

    public void onUpMove(View view) {
        ToastMatrixMessage(this.collageView.setShapeScaleMatrix(12));
    }

    public void onDownMove(View view) {
        ToastMatrixMessage(this.collageView.setShapeScaleMatrix(13));
    }

    public void onRotatePositive(View view) {
        this.collageView.setShapeScaleMatrix(7);
    }

    public void onRotateNegative(View view) {
        this.collageView.setShapeScaleMatrix(7);
    }

    public void onSelectWarningLayoutFilter(View view) {
        this.vvSelectFilterTextView.setVisibility(4);
        this.selectImageForAdj = false;
    }

    public void onSelectWarningLayout(View view) {
        this.stickerlay.setVisibility(8);
        this.vvSelectSwapTextView.setVisibility(4);
        this.swapMode = false;
    }

    public void onCollageLayout(View view) {
        this.stickerlay.setVisibility(8);
        if (!checkTextApply) {
            ChangeBtnSelection(0);
            SetSelectedTab(0);
            this.collageRCV.setVisibility(0);
            this.ll_blur_container.setVisibility(8);
            this.ll_space_layout.setVisibility(8);
            this.ratio_horizontalScrollView.setVisibility(8);
            this.frame_text.setVisibility(8);
            this.effect_fragment_container.setVisibility(8);
            rl_toolbar.setVisibility(0);
            return;
        }
        Toast.makeText(this, "First complete text setup.", 0).show();
    }

    public void onStickers(View view) {
        if (!checkTextApply) {
            ChangeBtnSelection(1);
            this.collageRCV.setVisibility(8);
            this.ll_blur_container.setVisibility(8);
            this.ll_space_layout.setVisibility(8);
            this.ratio_horizontalScrollView.setVisibility(8);
            this.frame_text.setVisibility(8);
            this.effect_fragment_container.setVisibility(8);
            rl_toolbar.setVisibility(0);
            this.stickerlay.setVisibility(0);
            return;
        }
        Toast.makeText(this, "First complete text setup.", 0).show();
    }

    public void onBlur(View view) {
        this.stickerlay.setVisibility(8);
        if (!checkTextApply) {
            ChangeBtnSelection(2);
            CollageView collageView = this.collageView;
            collageView.setBlurBitmap(collageView.blurRadius, false);
            SetSelectedTab(4);
            this.collageView.startAnimator();
            this.collageRCV.setVisibility(8);
            this.ll_blur_container.setVisibility(0);
            this.ll_space_layout.setVisibility(8);
            this.ratio_horizontalScrollView.setVisibility(8);
            this.frame_text.setVisibility(8);
            this.effect_fragment_container.setVisibility(8);
            rl_toolbar.setVisibility(0);
            return;
        }
        Toast.makeText(this, "First complete text setup.", 0).show();
    }

    public void onBackground(View view) {
        this.stickerlay.setVisibility(8);
        if (!checkTextApply) {
            ChangeBtnSelection(3);
            PatternDialog();
            RiverFontFragment cMFontFragment = this.fontFragment;
            if (cMFontFragment != null && cMFontFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
                checkTextFragment = false;
            }
            this.collageRCV.setVisibility(8);
            this.ll_blur_container.setVisibility(8);
            this.ll_space_layout.setVisibility(8);
            this.ratio_horizontalScrollView.setVisibility(8);
            this.frame_text.setVisibility(8);
            this.effect_fragment_container.setVisibility(8);
            rl_toolbar.setVisibility(0);
            return;
        }
        Toast.makeText(this, "First complete text setup.", 0).show();
    }

    public void onSpace(View view) {
        this.stickerlay.setVisibility(View.GONE);
        if (!checkTextApply) {
            ChangeBtnSelection(4);
            SetSelectedTab(2);
            RiverFontFragment cMFontFragment = this.fontFragment;
            if (cMFontFragment != null && cMFontFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
                checkTextFragment = false;
            }
            this.collageRCV.setVisibility(View.GONE);
            this.ll_blur_container.setVisibility(View.GONE);
            this.ll_space_layout.setVisibility(View.VISIBLE);
            this.ratio_horizontalScrollView.setVisibility(View.GONE);
            this.frame_text.setVisibility(View.GONE);
            this.effect_fragment_container.setVisibility(View.GONE);
            rl_toolbar.setVisibility(View.VISIBLE);
            return;
        }
        Toast.makeText(this, "First complete text setup.", 0).show();
    }

    public void onRatio(View view) {
        this.stickerlay.setVisibility(8);
        if (!checkTextApply) {
            ChangeBtnSelection(5);
            SetSelectedTab(3);
            RiverFontFragment cMFontFragment = this.fontFragment;
            if (cMFontFragment != null && cMFontFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
                checkTextFragment = false;
            }
            this.collageRCV.setVisibility(8);
            this.ll_blur_container.setVisibility(8);
            this.ll_space_layout.setVisibility(8);
            this.ratio_horizontalScrollView.setVisibility(0);
            this.frame_text.setVisibility(8);
            this.effect_fragment_container.setVisibility(8);
            rl_toolbar.setVisibility(0);
            return;
        }
        Toast.makeText(this, "First complete text setup.", 0).show();
    }

    public void onText(View view) {
                Duff_CollageActivity.this.stickerlay.setVisibility(8);
                if (!Duff_CollageActivity.checkTextApply) {
                    HiPhi_MainActivity.fromActivity = "CollageActivity";
                    Duff_CollageActivity.this.ChangeBtnSelection(6);
                    if (Duff_CollageActivity.checkTextFragment) {
                        RiverFontFragment cMFontFragment = Duff_CollageActivity.this.fontFragment;
                        if (cMFontFragment != null && cMFontFragment.isVisible()) {
                            Duff_CollageActivity.this.getSupportFragmentManager().beginTransaction().remove(Duff_CollageActivity.this.fontFragment).commit();
                        }
                        Duff_CollageActivity.checkTextFragment = false;
                    } else {
                        Duff_CollageActivity.this.AddCanvasTextView(null);
                        Duff_CollageActivity.checkTextFragment = true;
                    }
                    Duff_CollageActivity.this.collageRCV.setVisibility(8);
                    Duff_CollageActivity.this.ll_blur_container.setVisibility(8);
                    Duff_CollageActivity.this.ll_space_layout.setVisibility(8);
                    Duff_CollageActivity.this.ratio_horizontalScrollView.setVisibility(8);
                    Duff_CollageActivity.this.frame_text.setVisibility(0);
                    Duff_CollageActivity.this.effect_fragment_container.setVisibility(8);
                    Duff_CollageActivity.rl_toolbar.setVisibility(8);
                    Duff_CollageActivity.this.clearViewFlipper();
                    Duff_CollageActivity.checkTextApply = true;
                    return;
                }
                Toast.makeText(Duff_CollageActivity.this, "First complete text setup.", 0).show();

    }

    public void onFilterMain(View view) {
        this.stickerlay.setVisibility(8);
        if (!checkTextApply) {
            ChangeBtnSelection(7);
            RiverFontFragment cMFontFragment = this.fontFragment;
            if (cMFontFragment != null && cMFontFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
                checkTextFragment = false;
            }
            this.collageRCV.setVisibility(8);
            this.ll_blur_container.setVisibility(8);
            this.ll_space_layout.setVisibility(8);
            this.ratio_horizontalScrollView.setVisibility(8);
            this.frame_text.setVisibility(8);
            this.effect_fragment_container.setVisibility(0);
            if (this.collageView.shapeLayoutList.get(0).shapeArr.length == 1) {
                this.collageView.shapeIndex = 0;
                this.collageView.openFilterFragment();
            } else if (this.collageView.shapeIndex >= 0) {
                this.collageView.openFilterFragment();
                Log.e(this.TAG, "collageView.shapeIndex>=0 openFilterFragment");
            } else {
                SetSelectedTab(5);
                this.vvSelectFilterTextView.setVisibility(0);
                this.selectImageForAdj = true;
            }
            rl_toolbar.setVisibility(0);
            return;
        }
        Toast.makeText(this, "First complete text setup.", 0).show();
    }

    public void onRatio11(View view) {
        this.stickerlay.setVisibility(8);
        Log.d("RatioClick", "w--" + width + "--h--" + height);
        this.mulX = 1.0f;
        this.mulY = 1.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(0);
    }

    public void onRatio21(View view) {
        this.mulX = 2.0f;
        this.mulY = 1.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(1);
    }

    public void onRatio12(View view) {
        this.mulX = 1.0f;
        this.mulY = 2.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(2);
    }

    public void onRatio32(View view) {
        this.mulX = 3.0f;
        this.mulY = 2.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(3);
    }

    public void onRatio23(View view) {
        this.mulX = 2.0f;
        this.mulY = 3.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(4);
    }

    public void onRatio43(View view) {
        this.mulX = 4.0f;
        this.mulY = 3.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(5);
    }

    public void onRatio34(View view) {
        this.mulX = 3.0f;
        this.mulY = 4.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(6);
    }

    public void onRatio45(View view) {
        this.mulX = 4.0f;
        this.mulY = 5.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(7);
    }

    public void onRatio57(View view) {
        this.mulX = 5.0f;
        this.mulY = 7.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(8);
    }

    public void onRatio169(View view) {
        this.mulX = 16.0f;
        this.mulY = 9.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(9);
    }

    public void onRatio916(View view) {
        this.mulX = 9.0f;
        this.mulY = 16.0f;
        this.collageView.UpdateShapeListForRatio(width, height);
        setRatioButtonBg(10);
    }

    
    private class BitmapWorkerTask extends AsyncTask<Bundle, Void, Void> {
        int arraySize;
        Bundle data;
        ProgressDialog progressDialog;
        Bundle savedInstanceState;

        BitmapWorkerTask() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            ProgressDialog progressDialog = new ProgressDialog(Duff_CollageActivity.this);
            this.progressDialog = progressDialog;
            progressDialog.setCancelable(false);
            this.progressDialog.setMessage("loading images!");
            this.progressDialog.show();
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(Bundle... bundleArr) {
            int i;
            Bundle bundle = bundleArr[0];
            this.data = bundle;
            this.savedInstanceState = bundleArr[1];
            Duff_CollageActivity.this.isScrapBook = bundle.getBoolean("is_scrap_book", false);
            long[] longArray = this.data.getLongArray("photo_id_list");
            int[] intArray = this.data.getIntArray("photo_orientation_list");
            this.arraySize = 0;
            if (longArray == null) {
                String string = this.data.getString("selected_image_path");
                if (string != null) {
                    this.arraySize = 1;
                    Duff_CollageActivity.this.btmpList = new Bitmap[1];
                    Bitmap[] bitmapArr = Duff_CollageActivity.this.btmpList;
                    Duff_CollageActivity cMCollageActivity = Duff_CollageActivity.this;
                    bitmapArr[0] = Aspid_Utility.decodeFile(string, Aspid_Utility.maxSizeForDimension(cMCollageActivity, 3, cMCollageActivity.UPPER_SIZE_FOR_LOAD), Duff_CollageActivity.this.isScrapBook);
                }
            } else {
                int length = longArray.length;
                this.arraySize = length;
                Duff_CollageActivity.this.btmpList = new Bitmap[length];
                int i2 = length >= 3 ? length : 3;
                Duff_CollageActivity cMCollageActivity2 = Duff_CollageActivity.this;
                int maxSizeForDimension = Aspid_Utility.maxSizeForDimension(cMCollageActivity2, i2, cMCollageActivity2.UPPER_SIZE_FOR_LOAD);
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    i = this.arraySize;
                    if (i3 >= i) {
                        break;
                    }
                    Duff_CollageActivity cMCollageActivity3 = Duff_CollageActivity.this;
                    Bitmap scaledBitmapFromId = Aspid_Utility.getScaledBitmapFromId(cMCollageActivity3, longArray[i3], intArray[i3], maxSizeForDimension, cMCollageActivity3.isScrapBook);
                    if (scaledBitmapFromId != null) {
                        Duff_CollageActivity.this.btmpList[i3] = scaledBitmapFromId;
                    } else {
                        i4++;
                    }
                    i3++;
                }
                if (i4 > 0) {
                    int i5 = i - i4;
                    Bitmap[] bitmapArr2 = new Bitmap[i5];
                    int i6 = 0;
                    for (int i7 = 0; i7 < this.arraySize; i7++) {
                        if (Duff_CollageActivity.this.btmpList[i7] != null) {
                            bitmapArr2[i6] = Duff_CollageActivity.this.btmpList[i7];
                            i6++;
                        }
                    }
                    this.arraySize = i5;
                    Duff_CollageActivity.this.btmpList = bitmapArr2;
                }
            }
            Duff_CollageActivity.this.parameterList = new Collards_Parameter[this.arraySize];
            for (int i8 = 0; i8 < Duff_CollageActivity.this.parameterList.length; i8++) {
                Duff_CollageActivity.this.parameterList[i8] = new Collards_Parameter();
            }
            return null;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Void r6) {
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
            if (this.arraySize <= 0) {
                Toast makeText = Toast.makeText(Duff_CollageActivity.this, "Couldn't load images!", 0);
                makeText.setGravity(17, makeText.getXOffset() / 2, makeText.getYOffset() / 2);
                makeText.show();
                Duff_CollageActivity.this.finish();
                return;
            }
            if (Spyker_CollageShapeUtils.collageShapeIconsArray[Duff_CollageActivity.this.btmpList.length - 1] != Duff_CollageActivity.this.collageAdapter.iconList) {
                Duff_CollageActivity.this.collageAdapter.setData(Spyker_CollageShapeUtils.collageShapeIconsArray[Duff_CollageActivity.this.btmpList.length - 1]);
                Duff_CollageActivity.this.collageAdapter.notifyDataSetChanged();
                Log.e(Duff_CollageActivity.this.TAG, "change collage icons");
            }
            if (Duff_CollageActivity.this.isScrapBook) {
                Duff_CollageActivity cMCollageActivity = Duff_CollageActivity.this;
                cMCollageActivity.btmpDelete = BitmapFactory.decodeResource(cMCollageActivity.getResources(), R.drawable.scrapbook_remove);
                Duff_CollageActivity cMCollageActivity2 = Duff_CollageActivity.this;
                cMCollageActivity2.btmpScale = BitmapFactory.decodeResource(cMCollageActivity2.getResources(), R.drawable.scrapbook_scale);
            }
            if (Duff_CollageActivity.this.isScrapBook) {
                Duff_CollageActivity cMCollageActivity3 = Duff_CollageActivity.this;
                cMCollageActivity3.patchDrawable = (NinePatchDrawable) ContextCompat.getDrawable(cMCollageActivity3, R.drawable.shadow_7);
                String str = Duff_CollageActivity.this.TAG;
                Log.e(str, "ndp width " + Duff_CollageActivity.this.patchDrawable.getMinimumHeight());
            }
            Duff_CollageActivity cMCollageActivity4 = Duff_CollageActivity.this;
            cMCollageActivity4.collageView = new CollageView(cMCollageActivity4, Duff_CollageActivity.width, Duff_CollageActivity.height);
            Duff_CollageActivity.mainLayout.addView(Duff_CollageActivity.this.collageView);
            Duff_CollageActivity.this.viewFlipper.bringToFront();
            Duff_CollageActivity cMCollageActivity5 = Duff_CollageActivity.this;
            cMCollageActivity5.slideLeftIn = AnimationUtils.loadAnimation(cMCollageActivity5, R.anim.slide_in_left);
            Duff_CollageActivity cMCollageActivity6 = Duff_CollageActivity.this;
            cMCollageActivity6.slideLeftOut = AnimationUtils.loadAnimation(cMCollageActivity6, R.anim.slide_out_left);
            Duff_CollageActivity cMCollageActivity7 = Duff_CollageActivity.this;
            cMCollageActivity7.slideRightIn = AnimationUtils.loadAnimation(cMCollageActivity7, R.anim.slide_in_right);
            Duff_CollageActivity cMCollageActivity8 = Duff_CollageActivity.this;
            cMCollageActivity8.slideRightOut = AnimationUtils.loadAnimation(cMCollageActivity8, R.anim.slide_out_right);
            Duff_CollageActivity.this.AddEffectFragment();
            if (this.arraySize == 1) {
                Duff_CollageActivity.this.SingleImageVisibility();
            }
            if (Duff_CollageActivity.this.isScrapBook) {
                Duff_CollageActivity.this.ScrapbookVisibility();
            }
            Duff_CollageActivity cMCollageActivity9 = Duff_CollageActivity.this;
            cMCollageActivity9.viewFlipper = (ViewFlipper) cMCollageActivity9.findViewById(R.id.collage_view_flipper);
            Duff_CollageActivity.this.viewFlipper.bringToFront();
            Duff_CollageActivity.this.findViewById(R.id.collage_footer).bringToFront();
            Duff_CollageActivity cMCollageActivity10 = Duff_CollageActivity.this;
            cMCollageActivity10.contextFooter = (ViewGroup) cMCollageActivity10.findViewById(R.id.collage_context_menu);
            Duff_CollageActivity.this.contextFooter.bringToFront();
            Duff_CollageActivity cMCollageActivity11 = Duff_CollageActivity.this;
            cMCollageActivity11.vvSelectSwapTextView = cMCollageActivity11.findViewById(R.id.select_image_swap);
            Duff_CollageActivity.this.vvSelectSwapTextView.bringToFront();
            Duff_CollageActivity.this.vvSelectSwapTextView.setVisibility(4);
            Duff_CollageActivity cMCollageActivity12 = Duff_CollageActivity.this;
            cMCollageActivity12.vvSelectFilterTextView = cMCollageActivity12.findViewById(R.id.select_image_filter);
            Duff_CollageActivity.this.vvSelectFilterTextView.bringToFront();
            Duff_CollageActivity.this.vvSelectFilterTextView.setVisibility(4);
            Duff_CollageActivity.this.textLibHelper = new CtTextLibHelper();
            if (this.savedInstanceState != null) {
                Duff_CollageActivity.this.textLibHelper.hideForOnCreate(Duff_CollageActivity.this.activityFragment, Duff_CollageActivity.this.textAndStickerViewContainer, Duff_CollageActivity.this.textFragemntContinerId);
            }
        }
    }

    public void ScrapbookVisibility() {
        findViewById(R.id.button_collage_layout).setVisibility(8);
        findViewById(R.id.button_collage_space).setVisibility(8);
        findViewById(R.id.button_collage_context_swap).setVisibility(8);
        findViewById(R.id.button_collage_context_fit).setVisibility(8);
        findViewById(R.id.button_collage_context_center).setVisibility(8);
        findViewById(R.id.button_collage_context_delete).setVisibility(0);
    }

    public void AddEffectFragment() {
        if (this.fullEffectFragment == null) {
            RiverFullEffectFragment cMFullEffectFragment = (RiverFullEffectFragment) getSupportFragmentManager().findFragmentByTag("FULL_FRAGMENT");
            this.fullEffectFragment = cMFullEffectFragment;
            if (cMFullEffectFragment == null) {
                RiverFullEffectFragment cMFullEffectFragment2 = new RiverFullEffectFragment();
                this.fullEffectFragment = cMFullEffectFragment2;
                cMFullEffectFragment2.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.collage_effect_fragment_container, this.fullEffectFragment, "FULL_FRAGMENT").commitAllowingStateLoss();
            } else if (this.collageView.shapeIndex >= 0) {
                this.fullEffectFragment.setBitmapWithParameter(this.btmpList[this.collageView.shapeIndex], this.parameterList[this.collageView.shapeIndex]);
            }
            getSupportFragmentManager().beginTransaction().hide(this.fullEffectFragment).commitAllowingStateLoss();
            this.fullEffectFragment.setFullBitmapReadyListener(new RiverFullEffectFragment.FullBitmapReady() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.18
                @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFullEffectFragment.FullBitmapReady
                public void onBitmapReady(Bitmap bitmap, Collards_Parameter cMParameter) {
                    Duff_CollageActivity.this.collageView.UpdateShapeListForFilterBitmap(bitmap);
                    Duff_CollageActivity.this.collageView.UpdateParamList(cMParameter);
                    Duff_CollageActivity.this.collageView.postInvalidate();
                    Duff_CollageActivity.this.getSupportFragmentManager().beginTransaction().hide(Duff_CollageActivity.this.fullEffectFragment).commit();
                    Duff_CollageActivity.this.collageView.postInvalidate();
                }

                @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFullEffectFragment.FullBitmapReady
                public void onCancel() {
                    Duff_CollageActivity.this.setVisibilityFilterListView(false);
                    Duff_CollageActivity.this.collageView.postInvalidate();
                }
            });
            findViewById(R.id.collage_effect_fragment_container).bringToFront();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override 
    public void onDestroy() {
        if (this.btmpList != null) {
            int i = 0;
            while (true) {
                Bitmap[] bitmapArr = this.btmpList;
                if (i >= bitmapArr.length) {
                    break;
                }
                if (bitmapArr[i] != null) {
                    bitmapArr[i].recycle();
                }
                i++;
            }
        }
        CollageView collageView = this.collageView;
        if (collageView != null) {
            if (collageView.shapeLayoutList != null) {
                for (int i2 = 0; i2 < this.collageView.shapeLayoutList.size(); i2++) {
                    for (int i3 = 0; i3 < this.collageView.shapeLayoutList.get(i2).shapeArr.length; i3++) {
                        if (this.collageView.shapeLayoutList.get(i2).shapeArr[i3] != null) {
                            this.collageView.shapeLayoutList.get(i2).shapeArr[i3].freeBitmaps();
                        }
                    }
                }
            }
            if (this.collageView.maskBitmapArray != null) {
                for (int i4 = 0; i4 < this.collageView.maskBitmapArray.length; i4++) {
                    if (this.collageView.maskBitmapArray[i4] != null) {
                        if (!this.collageView.maskBitmapArray[i4].isRecycled()) {
                            this.collageView.maskBitmapArray[i4].recycle();
                        }
                        this.collageView.maskBitmapArray[i4] = null;
                    }
                }
            }
        }
        super.onDestroy();
    }

    @Override

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            RiverFontFragment cMFontFragment = this.fontFragment;
            if (cMFontFragment != null && cMFontFragment.isVisible()) {
                bundle.putBoolean("show_text", this.showText);
                bundle.putSerializable(RiverFontFragment.TEXT_DATA_BUNDLE_KEY, this.textDataList);
                getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            CtTextLibHelper cMTextLibHelper = this.textLibHelper;
            if (cMTextLibHelper != null) {
                cMTextLibHelper.saveTextAndStickerData(bundle, this.textAndStickerViewContainer, null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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
        if (this.contextFooter == null) {
            this.contextFooter = (ViewGroup) findViewById(R.id.collage_context_menu);
        }
        ViewGroup viewGroup = this.contextFooter;
        if (viewGroup != null) {
            viewGroup.bringToFront();
        }
        CtTextLibHelper cMTextLibHelper = this.textLibHelper;
        if (cMTextLibHelper != null) {
            cMTextLibHelper.loadTextAndStickerData(this.activityFragment, bundle, this.textAndStickerViewContainer, this.textFragemntContinerId, null);
        }
    }

    @Override 
    public void onBackPressed() {
        RiverFontFragment cMFontFragment = this.fontFragment;
        if (cMFontFragment != null && cMFontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
            onFontSelectionCancelled();
            rl_toolbar.setVisibility(0);
            rl_bottom.setVisibility(0);
            checkTextApply = false;
            checkTextFragment = false;
        } else if (this.showText || customRelativeLayout == null) {
            RiverFullEffectFragment cMFullEffectFragment = this.fullEffectFragment;
            if (cMFullEffectFragment == null || !cMFullEffectFragment.isVisible()) {
                if (this.swapMode) {
                    this.vvSelectSwapTextView.setVisibility(4);
                    this.swapMode = false;
                    return;
                }
                CollageView collageView = this.collageView;
                if (collageView != null && collageView.shapeIndex >= 0) {
                    this.collageView.UnSelectShapes();
                } else if (this.selectImageForAdj) {
                    this.vvSelectFilterTextView.setVisibility(4);
                    this.selectImageForAdj = false;
                } else {
                    ViewFlipper viewFlipper = this.viewFlipper;
                    if (viewFlipper == null || viewFlipper.getDisplayedChild() == 5) {
                        SaveImageAlertDialog();
                    } else {
                        SetSelectedTab(5);
                    }
                }
            }
        } else {
            onFontSelectionCancelled();
            rl_toolbar.setVisibility(0);
            rl_bottom.setVisibility(0);
            this.showText = true;
            mainLayout.removeView(customRelativeLayout);
            this.collageView.postInvalidate();
            customRelativeLayout = null;
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
        this.saveImageDialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.19
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.removeBorder();
                Duff_CollageActivity cMCollageActivity = Duff_CollageActivity.this;
                Glob.finalBitmap = cMCollageActivity.getbitmap(cMCollageActivity.rl_mainLayout);
                Duff_CollageActivity.this.saveImage(Glob.finalBitmap);
                Duff_CollageActivity.this.startActivity(new Intent(Duff_CollageActivity.this, Inchworm_ShareActivity.class));
                Duff_CollageActivity.this.finish();
            }
        });
        this.saveImageDialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.20
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.saveImageDialog.dismiss();
                Duff_CollageActivity.this.finish();
            }
        });
        this.saveImageDialog.findViewById(R.id.rl_cancel).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.21
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.saveImageDialog.dismiss();
            }
        });
        this.saveImageDialog.show();
    }

    
    private class SaveImageAsync extends AsyncTask<Object, Object, Object> {
        Bitmap bitmap;
        ProgressDialog progressDialog;
        String resultPath;

        private SaveImageAsync() {
            this.resultPath = null;
            this.progressDialog = new ProgressDialog(Duff_CollageActivity.this);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Saving image...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override // android.os.AsyncTask
        public Object doInBackground(Object... objArr) {
            try {
                this.resultPath = Duff_CollageActivity.this.collageView.SaveBitmap(Duff_CollageActivity.width, Duff_CollageActivity.height);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (Duff_CollageActivity.this.saveImageDialog != null && Duff_CollageActivity.this.saveImageDialog.isShowing()) {
                Duff_CollageActivity.this.saveImageDialog.dismiss();
            }
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
            if (this.resultPath != null) {

                        AppUtilsClass.RefreshImageAlbum(Duff_CollageActivity.this);
                        AppUtilsClass.RefreshMoment(Duff_CollageActivity.this);
                        Peyton_GalleryDataFragment.selectedImages.clear();
                        Intent intent = new Intent(Duff_CollageActivity.this, Inchworm_ShareActivity.class);
                        intent.putExtra("imagePath", SaveImageAsync.this.resultPath);
                        Duff_CollageActivity.this.startActivity(intent);
                        Duff_CollageActivity.this.finish();

            }
            Duff_CollageActivity cMCollageActivity = Duff_CollageActivity.this;
            new MyMediaScannerConnectionClient(cMCollageActivity.getApplicationContext(), new File(this.resultPath), null);
        }
    }

    
    private final class MyMediaScannerConnectionClient implements MediaScannerConnection.MediaScannerConnectionClient {
        private final MediaScannerConnection mConn;
        private final String mFilename;
        private final String mMimetype;

        MyMediaScannerConnectionClient(Context context, File file, String str) {
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

    private void setRatioButtonBg(int i) {
        if (this.txtRatioButtonArray == null) {
            TextView[] textViewArr = new TextView[this.RATIO_BUTTON_SIZE];
            this.txtRatioButtonArray = textViewArr;
            textViewArr[0] = (TextView) findViewById(R.id.button11);
            this.txtRatioButtonArray[1] = (TextView) findViewById(R.id.button21);
            this.txtRatioButtonArray[2] = (TextView) findViewById(R.id.button12);
            this.txtRatioButtonArray[3] = (TextView) findViewById(R.id.button32);
            this.txtRatioButtonArray[4] = (TextView) findViewById(R.id.button23);
            this.txtRatioButtonArray[5] = (TextView) findViewById(R.id.button43);
            this.txtRatioButtonArray[6] = (TextView) findViewById(R.id.button34);
            this.txtRatioButtonArray[7] = (TextView) findViewById(R.id.button45);
            this.txtRatioButtonArray[8] = (TextView) findViewById(R.id.button57);
            this.txtRatioButtonArray[9] = (TextView) findViewById(R.id.button169);
            this.txtRatioButtonArray[10] = (TextView) findViewById(R.id.button916);
        }
        for (int i2 = 0; i2 < this.RATIO_BUTTON_SIZE; i2++) {
            this.txtRatioButtonArray[i2].setBackgroundResource(0);
            this.txtRatioButtonArray[i2].setTextColor(getResources().getColor(R.color.txt_color));
        }
        this.txtRatioButtonArray[i].setBackgroundResource(R.drawable.selector_box_ratio);
        this.txtRatioButtonArray[i].setTextColor(-1);
    }

    public void ToastMatrixMessage(int i) {
        String str = i == 1 ? "You reached maximum zoom!" : i == 2 ? "You reached minimum zoom!" : i == 7 ? "You reached max bottom!" : i == 5 ? "You reached max top!" : i == 4 ? "You reached max right!" : i == 3 ? "You reached max left!" : null;
        if (str != null) {
            Toast makeText = Toast.makeText(this, str, 0);
            makeText.setGravity(17, makeText.getXOffset() / 2, makeText.getYOffset() / 2);
            makeText.show();
        }
    }

    public void clearViewFlipper() {
        this.viewFlipper.setDisplayedChild(5);
    }

    
    public class CollageView extends View {
        static final float RATIO_CONSTANT = 1.25f;
        float MIN_ZOOM;
        int[] MaskList;
        RectF above;
        int animHalfTime;
        int animSizeSeekBarProgress;
        boolean animate;
        int animationCount;
        int animationDurationLimit;
        int animationLimit;
        private final Runnable animator;
        Bitmap blurBitmap;
        Cashier_BlurBuilderNormal blurBuilderNormal;
        int blurRadius;
        RectF blurRectDst;
        Rect blurRectSrc;
        Paint borderPaint;
        RectF bottom;
        RectF bottomLeftRect;
        RectF bottomRightRect;
        Paint circlePaint;
        float cornerRadius;
        int currentCollageIndex;
        RectF drawingAreaRect;
        final float epsilon;
        float finalAngle;
        Bitmap frameBitmap;
        int frameDuration;
        RectF frameRect;
        Matrix identityMatrix;
        boolean isInCircle;
        boolean isOnCross;
        RectF left;
        int mActivePointerId;
        float mLastTouchX;
        float mLastTouchY;
        ScaleGestureDetector mScaleDetector;
        float mScaleFactor;
        GestureDetectorCompat mTouchDetector;
        Bitmap[] maskBitmapArray;
        float[] matrixValues;
        boolean moveBoolean;
        int offsetX;
        int offsetY;
        boolean orthogonal;
        float paddingDistance;
        Paint paint;
        Paint paintGray;
        Bitmap patternBitmap;
        Paint patternPaint;
        int previousIndex;
        float[] pts;
        Rect rectAnim;
        RectF right;
        Anadol_RotationGestureDetector.OnRotationGestureListener rotateListener;
        Grapes_Shape scaleShape;
        float scaleX;
        float scaleY;
        int screenHeight;
        int screenWidth;
        int shapeIndex;
        List<Aspid_ShapeLayout> shapeLayoutList;
        Matrix sizeMatrix;
        Matrix sizeMatrixSaved;
        float sizeScale;
        ArrayList<Float> smallestDistanceList;
        float startAngle;
        Matrix startMatrix;
        long startTime;
        Matrix textMatrix;
        RectF topLeftRect;
        RectF topRightRect;
        float[] values;
        PointF zoomStartPaint;

        public float CalculateScaleSize(float f) {
            return 1.0f - (f / 200.0f);
        }

        
        public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
            private static final String DEBUG_TAG = "Gestures";

            MyGestureListener() {
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                Log.d(DEBUG_TAG, "onSingleTapConfirmed: ");
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Log.d(DEBUG_TAG, "onSingleTapUp: ");
                if (!CollageView.this.isOnCross) {
                    Duff_CollageActivity.this.collageView.selectCurrentShape(motionEvent.getX(), motionEvent.getY(), true);
                }
                return true;
            }
        }

        
        public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            private ScaleListener() {
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                if (CollageView.this.shapeIndex < 0) {
                    return true;
                }
                CollageView.this.mScaleFactor = scaleGestureDetector.getScaleFactor();
                scaleGestureDetector.isInProgress();
                CollageView collageView = CollageView.this;
                collageView.mScaleFactor = Math.max(0.1f, Math.min(collageView.mScaleFactor, 5.0f));
                CollageView collageView2 = CollageView.this;
                collageView2.scaleShape = collageView2.shapeLayoutList.get(CollageView.this.currentCollageIndex).shapeArr[CollageView.this.shapeIndex];
                if (Duff_CollageActivity.this.isScrapBook) {
                    CollageView.this.scaleShape.bitmapMatrixScaleScrapBook(CollageView.this.mScaleFactor, CollageView.this.mScaleFactor);
                } else {
                    CollageView.this.scaleShape.bitmapMatrixScale(CollageView.this.mScaleFactor, CollageView.this.mScaleFactor, CollageView.this.scaleShape.bounds.centerX(), CollageView.this.scaleShape.bounds.centerY());
                }
                CollageView.this.invalidate();
                CollageView.this.requestLayout();
                return true;
            }
        }

        public CollageView(Context context, int i, int i2) {
            super(context);
            this.animationLimit = 31;
            this.animHalfTime = (31 / 2) + 1;
            this.animSizeSeekBarProgress = 0;
            this.animate = false;
            this.animationCount = 0;
            this.animationDurationLimit = 50;
            this.blurRadius = 14;
            this.cornerRadius = 0.0f;
            this.currentCollageIndex = 0;
            this.frameDuration = 10;
            this.identityMatrix = new Matrix();
            this.MaskList = new int[]{R.drawable.mask_butterfly, R.drawable.mask_cloud, R.drawable.mask_clover, R.drawable.mask_leaf, R.drawable.mask_left_foot, R.drawable.mask_diamond, R.drawable.mask_santa, R.drawable.mask_snowman, R.drawable.mask_paw, R.drawable.mask_egg, R.drawable.mask_twitter, R.drawable.mask_circle, R.drawable.mask_hexagon, R.drawable.mask_heart};
            this.paddingDistance = 0.0f;
            this.paint = new Paint();
            this.shapeIndex = -1;
            this.shapeLayoutList = new ArrayList();
            this.sizeMatrix = new Matrix();
            this.sizeScale = 1.0f;
            this.smallestDistanceList = new ArrayList<>();
            this.startTime = System.nanoTime();
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.rectAnim = new Rect();
            this.textMatrix = new Matrix();
            this.blurRectDst = new RectF();
            this.drawingAreaRect = new RectF();
            this.above = new RectF();
            this.left = new RectF();
            this.right = new RectF();
            this.bottom = new RectF();
            this.moveBoolean = false;
            this.paintGray = new Paint(1);
            this.mActivePointerId = 1;
            this.zoomStartPaint = new PointF();
            this.startMatrix = new Matrix();
            this.startAngle = 0.0f;
            this.MIN_ZOOM = 0.1f;
            this.isInCircle = false;
            this.isOnCross = false;
            this.orthogonal = false;
            this.mScaleFactor = 1.0f;
            this.matrixValues = new float[9];
            this.finalAngle = 0.0f;
            this.epsilon = 4.0f;
            this.values = new float[9];
            this.blurRectSrc = new Rect();
            this.maskBitmapArray = new Bitmap[this.MaskList.length];
            this.patternPaint = new Paint(1);
            this.animator = new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.CollageView.1
                @Override 
                public void run() {
                    CollageView collageView = null;
                    int nanoTime = ((int) (((float) (System.nanoTime() - CollageView.this.startTime)) / 1000000.0f)) / CollageView.this.animationDurationLimit;
                    boolean z = true;
                    if (nanoTime <= 0) {
                        nanoTime = 1;
                    }
                    if (CollageView.this.animationCount == 0) {
                        Duff_CollageActivity.this.collageView.animationCount++;
                    } else {
                        Duff_CollageActivity.this.collageView.animationCount += nanoTime;
                    }
                    CollageView collageView2 = CollageView.this;
                    Matrix matrix = collageView2.sizeMatrix;
                    CollageView collageView3 = CollageView.this;
                    collageView2.setCollageSize(matrix, collageView3.animSize(collageView3.animationCount));
                    if (CollageView.this.animationCount >= CollageView.this.animationLimit) {
                        CollageView.this.animate = false;
                        z = false;
                    }
                    if (z) {
                        CollageView.this.postDelayed(this, collageView.frameDuration);
                    } else {
                        CollageView.this.sizeMatrix.set(CollageView.this.sizeMatrixSaved);
                    }
                    CollageView.this.shapeLayoutList.get(CollageView.this.currentCollageIndex).shapeArr[0].drawShapeRect.roundOut(CollageView.this.rectAnim);
                    CollageView collageView4 = CollageView.this;
                    collageView4.invalidate(collageView4.rectAnim);
                    CollageView.this.startTime = System.nanoTime();
                }
            };
            this.rotateListener = new Anadol_RotationGestureDetector.OnRotationGestureListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.CollageView.2
                @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMRotationGestureDetector.OnRotationGestureListener
                public void OnRotation(Anadol_RotationGestureDetector cMRotationGestureDetector) {
                    if (CollageView.this.shapeIndex >= 0) {
                        float angle = cMRotationGestureDetector.getAngle();
                        CollageView collageView = CollageView.this;
                        collageView.scaleShape = collageView.shapeLayoutList.get(CollageView.this.currentCollageIndex).shapeArr[CollageView.this.shapeIndex];
                        CollageView collageView2 = CollageView.this;
                        float matrixRotation = collageView2.getMatrixRotation(collageView2.scaleShape.bitmapMatrix);
                        if ((matrixRotation == 0.0f || matrixRotation == 90.0f || matrixRotation == 180.0f || matrixRotation == -180.0f || matrixRotation == -90.0f) && Math.abs(CollageView.this.finalAngle - angle) < 4.0f) {
                            CollageView.this.orthogonal = true;
                            return;
                        }
                        if (Math.abs((matrixRotation - CollageView.this.finalAngle) + angle) < 4.0f) {
                            angle = CollageView.this.finalAngle - matrixRotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(90.0f - ((matrixRotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle + 90.0f) - matrixRotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(180.0f - ((matrixRotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle + 180.0f) - matrixRotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs((-180.0f) - ((matrixRotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle - 0.024902344f) - matrixRotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs((-90.0f) - ((matrixRotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle - 0.049804688f) - matrixRotation;
                            CollageView.this.orthogonal = true;
                        } else {
                            CollageView.this.orthogonal = false;
                        }
                        CollageView.this.scaleShape.bitmapMatrixRotate(CollageView.this.finalAngle - angle);
                        CollageView.this.finalAngle = angle;
                        CollageView.this.invalidate();
                        CollageView.this.requestLayout();
                    }
                }
            };
            Paint paint = new Paint(1);
            this.borderPaint = paint;
            paint.setColor(getResources().getColor(R.color.pink));
            this.borderPaint.setStyle(Paint.Style.STROKE);
            this.borderPaint.setStrokeWidth(5.0f);
            this.screenWidth = i;
            this.screenHeight = i2;
            Paint paint2 = new Paint();
            this.circlePaint = paint2;
            paint2.setColor(SupportMenu.CATEGORY_MASK);
            this.identityMatrix.reset();
            float f = i * 0;
            float f2 = i;
            float f3 = f2 * 0.5f;
            float f4 = i2;
            float f5 = 0.5f * f4;
            this.topLeftRect = new RectF(f, i2 * 0, f3, f5);
            float f6 = f2 * 1.0f;
            this.topRightRect = new RectF(f3, 0.0f * f4, f6, f5);
            float f7 = f4 * 1.0f;
            this.bottomLeftRect = new RectF(f, f5, f3, f7);
            this.bottomRightRect = new RectF(f3, f5, f6, f7);
            Path path = new Path();
            Path path2 = new Path();
            Path path3 = new Path();
            Path path4 = new Path();
            path.addRect(this.topLeftRect, Path.Direction.CCW);
            path2.addRect(this.topRightRect, Path.Direction.CCW);
            path3.addRect(this.bottomLeftRect, Path.Direction.CCW);
            path4.addRect(this.bottomRightRect, Path.Direction.CCW);
            this.mTouchDetector = new GestureDetectorCompat(context, new MyGestureListener());
            this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
            Duff_CollageActivity.this.mRotationDetector = new Anadol_RotationGestureDetector(this.rotateListener);
            CalculateOffset();
            Paint paint3 = new Paint(1);
            this.patternPaint = paint3;
            paint3.setColor(-1);
            CreateShapeList(Duff_CollageActivity.this.btmpList.length, i);
            this.paintGray.setColor(-12303292);
        }

        private void CalculateOffset() {
            PointF ratio = getRatio();
            this.offsetX = (int) ((Duff_CollageActivity.width - (ratio.x * Duff_CollageActivity.width)) / 2.0f);
            this.offsetY = (int) ((Duff_CollageActivity.height - (ratio.y * Duff_CollageActivity.width)) / 2.0f);
        }

        public String SaveBitmap(int i, int i2) {
            float f;
            Matrix matrix;
            Aspid_ShapeLayout cMShapeLayout;
            Canvas canvas;
            Bitmap bitmap;
            Log.d("SSSSSSS", " - 000 ");
            float f2 = i;
            int i3 = (int) (Duff_CollageActivity.this.collageView.scaleX * f2);
            int i4 = (int) (Duff_CollageActivity.this.collageView.scaleY * f2);
            float maxSizeForSave = Aspid_Utility.maxSizeForSave(Duff_CollageActivity.this, 2048.0f) / Math.max(i3, i4);
            float f3 = i3;
            int i5 = (int) (f3 * maxSizeForSave);
            float f4 = i4;
            int i6 = (int) (f4 * maxSizeForSave);
            if (i5 <= 0) {
                Log.e(Duff_CollageActivity.this.TAG, "newBtmWidth");
            } else {
                i3 = i5;
            }
            if (i6 <= 0) {
                Log.e(Duff_CollageActivity.this.TAG, "newBtmHeight");
            } else {
                i4 = i6;
            }
            Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            Aspid_ShapeLayout cMShapeLayout2 = this.shapeLayoutList.get(this.currentCollageIndex);
            Matrix matrix2 = new Matrix();
            matrix2.reset();
            matrix2.preScale(maxSizeForSave, maxSizeForSave);
            canvas2.setMatrix(matrix2);
            if (Duff_CollageActivity.this.backgroundMode == 0) {
                matrix = matrix2;
                cMShapeLayout = cMShapeLayout2;
                f = maxSizeForSave;
                canvas = canvas2;
                canvas2.drawRect(0.0f, 0.0f, f3, f4, this.patternPaint);
            } else {
                f = maxSizeForSave;
                matrix = matrix2;
                cMShapeLayout = cMShapeLayout2;
                canvas = canvas2;
            }
            Bitmap bitmap2 = this.blurBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled() && Duff_CollageActivity.this.backgroundMode == 1) {
                canvas.drawBitmap(this.blurBitmap, this.blurRectSrc, new RectF(0.0f, 0.0f, f3, f4), this.paint);
            }
            float f5 = this.sizeScale;
            Matrix matrix3 = matrix;
            matrix3.postScale(f5, f5, i3 / 2.0f, i4 / 2.0f);
            matrix3.preTranslate(-this.offsetX, -this.offsetY);
            canvas.setMatrix(matrix3);
            float f6 = this.sizeScale;
            int saveLayer = canvas.saveLayer((-i) / f6, (-i2) / f6, this.offsetX + (f2 / f6), this.offsetY + (i2 / f6), null, 31);
            Aspid_ShapeLayout cMShapeLayout3 = cMShapeLayout;
            int i7 = 0;
            while (i7 < cMShapeLayout3.shapeArr.length) {
                boolean z = i7 == cMShapeLayout3.getClearIndex();
                Log.e(Duff_CollageActivity.this.TAG, "drawPorterClear " + z);
                if (Duff_CollageActivity.this.isScrapBook) {
                    bitmap = createBitmap;
                    cMShapeLayout3.shapeArr[i7].DrawShapeForScrapBook(canvas, i3, i4, false, false);
                } else {
                    bitmap = createBitmap;
                    cMShapeLayout3.shapeArr[i7].drawShapeForSave(canvas, i3, i4, saveLayer, z);
                }
                i7++;
                createBitmap = bitmap;
            }
            Bitmap bitmap3 = createBitmap;
            if (Duff_CollageActivity.this.mViewsArray != null && Duff_CollageActivity.this.mViewsArray.size() > 0) {
                for (int i8 = 0; i8 < Duff_CollageActivity.this.mViewsArray.size(); i8++) {
                    canvas.drawBitmap(Duff_CollageActivity.this.btmpStickers.get(i8), ((Ledl_StickerView) Duff_CollageActivity.this.mViewsArray.get(i8)).getBitmapMatrix(), null);
                }
            }
            if (Duff_CollageActivity.this.textDataList != null) {
                for (int i9 = 0; i9 < Duff_CollageActivity.this.textDataList.size(); i9++) {
                    Matrix matrix4 = new Matrix();
                    matrix4.set(Duff_CollageActivity.this.textDataList.get(i9).imageSaveMatrix);
                    matrix4.postTranslate(-this.offsetX, -this.offsetY);
                    float f7 = f;
                    matrix4.postScale(f7, f7);
                    canvas.setMatrix(matrix4);
                    Duff_CollageActivity cMCollageActivity = Duff_CollageActivity.this;
                    cMCollageActivity.applyTextBackground(cMCollageActivity.textDataList.get(i9), canvas);
                    canvas.drawText(Duff_CollageActivity.this.textDataList.get(i9).message, Duff_CollageActivity.this.textDataList.get(i9).xPos, Duff_CollageActivity.this.textDataList.get(i9).yPos, Duff_CollageActivity.this.textDataList.get(i9).textPaint);
                }
            }
            canvas.restoreToCount(saveLayer);
            String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + getResources().getString(R.string.app_name) + File.separator + "CollagePhotos" + File.separator + "CollagePic" + System.currentTimeMillis() + ".jpg";
            new File(str).getParentFile().mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                try {
                    bitmap3.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                bitmap3.recycle();
                return str;
            } catch (IOException e) {
                e.printStackTrace();
                bitmap3.recycle();
                return str;
            }
        }

        public int getMaskIndex(int i) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.MaskList;
                if (i2 >= iArr.length) {
                    return -1;
                }
                if (i == iArr[i2]) {
                    return i2;
                }
                i2++;
            }
        }

        private void CreateShapeList(int i, int i2) {
            boolean z;
            int i3;
            int i4;
            int i5;
            String str;
            this.shapeLayoutList.clear();
            this.smallestDistanceList.clear();
            Spyker_CollageShapeUtils CreateCollage = Spyker_CollageShapeUtils.CreateCollage(i, i2, i2, Duff_CollageActivity.this.isScrapBook);
            int size = ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(0)).shapeList.size();
            String str2 = Duff_CollageActivity.this.TAG;
            int i6 = 0;
            while (i6 < CreateCollage.collageLayoutList.size()) {
                Grapes_Shape[] cMShapeArr = new Grapes_Shape[size];
                int i7 = 0;
                while (i7 < i) {
                    if (((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i6)).maskPairList == null || ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i6)).maskPairList.isEmpty()) {
                        z = false;
                        i3 = 0;
                    } else {
                        z = false;
                        i3 = 0;
                        for (Spyker_MaskPair cMMaskPair : ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i6)).maskPairList) {
                            if (i7 == cMMaskPair.index) {
                                i3 = cMMaskPair.id;
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        Bitmap bitmap = null;
                        int maskIndex = getMaskIndex(i3);
                        if (maskIndex >= 0) {
                            if (this.maskBitmapArray == null) {
                                this.maskBitmapArray = new Bitmap[this.MaskList.length];
                            }
                            Bitmap[] bitmapArr = this.maskBitmapArray;
                            if (bitmapArr[maskIndex] == null) {
                                bitmapArr[maskIndex] = LoadMaskBitmap2(i3);
                                Log.e(str2, "load mask bitmap from factory");
                            } else {
                                Log.e(str2, "load mask bitmap from pool");
                            }
                            bitmap = this.maskBitmapArray[maskIndex];
                        }
                        int i8 = i7;
                        i4 = i8;
                        cMShapeArr[i4] = new Grapes_Shape((PointF[]) ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i6)).shapeList.get(i7), Duff_CollageActivity.this.btmpList[i7], null, this.offsetX, this.offsetY, bitmap, Duff_CollageActivity.this.isScrapBook, i8, false, Duff_CollageActivity.this.btmpDelete, Duff_CollageActivity.this.btmpScale, this.screenWidth);
                        if (Duff_CollageActivity.this.isScrapBook) {
                            cMShapeArr[i4].BindScrapBook(Duff_CollageActivity.this.patchDrawable);
                        }
                        i5 = size;
                        str = str2;
                    } else {
                        i4 = i7;
                        i5 = size;
                        str = str2;
                        cMShapeArr[i4] = new Grapes_Shape((PointF[]) ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i6)).shapeList.get(i4), Duff_CollageActivity.this.btmpList[i4], ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i6)).getexceptionIndex(i4), this.offsetX, this.offsetY, Duff_CollageActivity.this.isScrapBook, i4, false, Duff_CollageActivity.this.btmpDelete, Duff_CollageActivity.this.btmpScale, this.screenWidth);
                        if (Duff_CollageActivity.this.isScrapBook) {
                            cMShapeArr[i4].BindScrapBook(Duff_CollageActivity.this.patchDrawable);
                        }
                    }
                    i7 = i4 + 1;
                    size = i5;
                    str2 = str;
                }
                int i9 = size;
                this.smallestDistanceList.add(Float.valueOf(SmallestDistance(cMShapeArr)));
                Aspid_ShapeLayout cMShapeLayout = new Aspid_ShapeLayout(cMShapeArr);
                cMShapeLayout.setClearIndex(((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i6)).getClearIndex());
                this.shapeLayoutList.add(cMShapeLayout);
                i6++;
                size = i9;
            }
            if (Duff_CollageActivity.this.isScrapBook) {
                return;
            }
            if (i != 1) {
                for (int i10 = 0; i10 < this.shapeLayoutList.size(); i10++) {
                    setPathPadding(i10, 3.0f);
                    for (int i11 = 0; i11 < this.shapeLayoutList.get(i10).shapeArr.length; i11++) {
                        this.shapeLayoutList.get(i10).shapeArr[i11].setScaleMatrix(1);
                    }
                }
                setCollageSize(this.sizeMatrix, 3);
            } else if (Duff_CollageActivity.this.btmpList.length == 1) {
                setCollageSize(this.sizeMatrix, 3);
            }
        }

        public int setShapeScaleMatrix(int i) {
            if (this.shapeIndex < 0) {
                return -1;
            }
            int scaleMatrix = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].setScaleMatrix(i);
            invalidate();
            return scaleMatrix;
        }

        public void DeleteBitmap(int i, int i2, int i3) {
            int i4;
            boolean z;
            String str;
            int i5;
            int i6;
            Bitmap[] bitmapArr;
            Bitmap bitmap;
            String str2 = Duff_CollageActivity.this.TAG;
            Grapes_Shape[] cMShapeArr = this.shapeLayoutList.get(0).shapeArr;
            if (i < 0 || i >= this.shapeLayoutList.get(0).shapeArr.length) {
                return;
            }
            int length = this.shapeLayoutList.get(0).shapeArr.length - 1;
            Bitmap[] bitmapArr2 = new Bitmap[length];
            Bitmap[] bitmapArr3 = new Bitmap[length];
            int i7 = 0;
            for (int i8 = 0; i8 < length + 1; i8++) {
                if (i8 != i) {
                    bitmapArr2[i7] = this.shapeLayoutList.get(0).shapeArr[i8].getBitmap();
                    bitmapArr3[i7] = Duff_CollageActivity.this.btmpList[i8];
                    i7++;
                }
            }
            Duff_CollageActivity.this.btmpList[i].recycle();
            this.shapeLayoutList.get(0).shapeArr[i].getBitmap().recycle();
            this.shapeLayoutList.clear();
            this.smallestDistanceList.clear();
            Spyker_CollageShapeUtils CreateCollage = Spyker_CollageShapeUtils.CreateCollage(length, i2, i2, Duff_CollageActivity.this.isScrapBook);
            int size = ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(0)).shapeList.size();
            Duff_CollageActivity.this.btmpList = bitmapArr3;
            int i9 = 0;
            while (i9 < CreateCollage.collageLayoutList.size()) {
                Grapes_Shape[] cMShapeArr2 = new Grapes_Shape[size];
                int i10 = 0;
                while (i10 < length) {
                    if (((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i9)).maskPairList == null || ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i9)).maskPairList.isEmpty()) {
                        i4 = 0;
                        z = false;
                    } else {
                        z = false;
                        int i11 = 0;
                        for (Spyker_MaskPair cMMaskPair : ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i9)).maskPairList) {
                            if (i10 == cMMaskPair.index) {
                                i11 = cMMaskPair.id;
                                z = true;
                            }
                        }
                        i4 = i11;
                    }
                    if (z) {
                        int maskIndex = getMaskIndex(i4);
                        if (maskIndex >= 0) {
                            if (this.maskBitmapArray == null) {
                                this.maskBitmapArray = new Bitmap[this.MaskList.length];
                            }
                            Bitmap[] bitmapArr4 = this.maskBitmapArray;
                            if (bitmapArr4[maskIndex] == null) {
                                bitmapArr4[maskIndex] = LoadMaskBitmap2(i4);
                                Log.e(str2, "load mask bitmap from factory");
                            } else {
                                Log.e(str2, "load mask bitmap from pool");
                            }
                            bitmap = this.maskBitmapArray[maskIndex];
                        } else {
                            bitmap = null;
                        }
                        i5 = size;
                        str = str2;
                        int i12 = i10;
                        i6 = i12;
                        cMShapeArr2[i6] = new Grapes_Shape((PointF[]) ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i9)).shapeList.get(i10), bitmapArr2[i10], null, this.offsetX, this.offsetY, bitmap, Duff_CollageActivity.this.isScrapBook, i12, true, Duff_CollageActivity.this.btmpDelete, Duff_CollageActivity.this.btmpScale, this.screenWidth);
                        if (Duff_CollageActivity.this.isScrapBook) {
                            cMShapeArr2[i6].BindScrapBook(Duff_CollageActivity.this.patchDrawable);
                        }
                        bitmapArr = bitmapArr2;
                    } else {
                        str = str2;
                        i5 = size;
                        i6 = i10;
                        bitmapArr = bitmapArr2;
                        cMShapeArr2[i6] = new Grapes_Shape((PointF[]) ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i9)).shapeList.get(i6), bitmapArr2[i6], ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i9)).getexceptionIndex(i6), this.offsetX, this.offsetY, Duff_CollageActivity.this.isScrapBook, i6, true, Duff_CollageActivity.this.btmpDelete, Duff_CollageActivity.this.btmpScale, this.screenWidth);
                        if (Duff_CollageActivity.this.isScrapBook) {
                            cMShapeArr2[i6].BindScrapBook(Duff_CollageActivity.this.patchDrawable);
                        }
                    }
                    i10 = i6 + 1;
                    bitmapArr2 = bitmapArr;
                    size = i5;
                    str2 = str;
                }
                String str3 = str2;
                Bitmap[] bitmapArr5 = bitmapArr2;
                int i13 = size;
                if (Duff_CollageActivity.this.isScrapBook) {
                    for (int i14 = 0; i14 < cMShapeArr.length; i14++) {
                        if (i14 < i) {
                            cMShapeArr2[i14].bitmapMatrix.set(cMShapeArr[i14].bitmapMatrix);
                        }
                        if (i14 > i) {
                            cMShapeArr2[i14 - 1].bitmapMatrix.set(cMShapeArr[i14].bitmapMatrix);
                        }
                    }
                }
                Aspid_ShapeLayout cMShapeLayout = new Aspid_ShapeLayout(cMShapeArr2);
                cMShapeLayout.setClearIndex(((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i9)).getClearIndex());
                this.shapeLayoutList.add(cMShapeLayout);
                this.smallestDistanceList.add(Float.valueOf(SmallestDistance(cMShapeArr2)));
                i9++;
                bitmapArr2 = bitmapArr5;
                size = i13;
                str2 = str3;
            }
            String str4 = str2;
            this.currentCollageIndex = 0;
            Duff_CollageActivity.this.collageAdapter.selectedPosition = 0;
            Duff_CollageActivity.this.collageAdapter.setData(Spyker_CollageShapeUtils.collageShapeIconsArray[length - 1]);
            Duff_CollageActivity.this.collageAdapter.notifyDataSetChanged();
            if (!Duff_CollageActivity.this.isScrapBook) {
                UpdateShapeListForRatio(i2, i3);
            }
            UnSelectShapes();
            for (int i15 = 0; i15 < this.shapeLayoutList.get(0).shapeArr.length; i15++) {
                Log.e(str4, "i " + i15 + "is recylced " + this.shapeLayoutList.get(0).shapeArr[i15].getBitmap().isRecycled());
            }
            invalidate();
            if (length == 1) {
                Duff_CollageActivity.this.SingleImageVisibility();
            }
            if (length == 1) {
                setPathPadding(0, 0.0f);
                if (this.sizeScale != 1.0f || Duff_CollageActivity.this.isScrapBook) {
                    return;
                }
                setCollageSize(this.sizeMatrix, 3);
            }
        }

        public Bitmap LoadMaskBitmap2(int i) {
            return ConvertToAlphaMask(BitmapFactory.decodeResource(getResources(), i));
        }

        private Bitmap ConvertToAlphaMask(Bitmap bitmap) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ALPHA_8);
            new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            bitmap.recycle();
            return createBitmap;
        }

        public float SmallestDistance(Grapes_Shape[] cMShapeArr) {
            float smallestDistance = cMShapeArr[0].smallestDistance();
            for (Grapes_Shape cMShape : cMShapeArr) {
                float smallestDistance2 = cMShape.smallestDistance();
                if (smallestDistance2 < smallestDistance) {
                    smallestDistance = smallestDistance2;
                }
            }
            return smallestDistance;
        }

        public void UpdateShapeListForRatio(int i, int i2) {
            Bitmap bitmap = null;
            int i3 = 0;
            int length = this.shapeLayoutList.get(0).shapeArr.length;
            PointF ratio = getRatio();
            CalculateOffset();
            float f = i;
            Spyker_CollageShapeUtils CreateCollage = Spyker_CollageShapeUtils.CreateCollage(length, (int) (ratio.x * f), (int) (ratio.y * f), Duff_CollageActivity.this.isScrapBook);
            this.smallestDistanceList.clear();
            int i4 = 0;
            while (i4 < this.shapeLayoutList.size()) {
                if (length == 1) {
                    this.shapeLayoutList.get(i4).shapeArr[i3].changeRatio((PointF[]) ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i4)).shapeList.get(i3), null, this.offsetX, this.offsetY, Duff_CollageActivity.this.isScrapBook, 0, (int) (ratio.x * f), (int) (ratio.y * f));
                } else {
                    for (int i5 = 0; i5 < length; i5++) {
                        this.shapeLayoutList.get(i4).shapeArr[i5].changeRatio((PointF[]) ((Rabbit_CollageLayout) CreateCollage.collageLayoutList.get(i4)).shapeList.get(i5), null, this.offsetX, this.offsetY, Duff_CollageActivity.this.isScrapBook, i5, (int) (ratio.x * f), (int) (ratio.y * f));
                    }
                }
                this.smallestDistanceList.add(Float.valueOf(SmallestDistance(this.shapeLayoutList.get(i4).shapeArr)));
                setPathPadding(i4, this.paddingDistance);
                if (!Duff_CollageActivity.this.isScrapBook) {
                    for (int i6 = 0; i6 < this.shapeLayoutList.get(i4).shapeArr.length; i6++) {
                        this.shapeLayoutList.get(i4).shapeArr[i6].setScaleMatrix(1);
                    }
                }
                i4++;
                i3 = 0;
            }
            setCornerRadius(this.cornerRadius);
            if (this.blurBitmap != null) {
                setBlurRect2(bitmap.getWidth(), this.blurBitmap.getHeight());
            }
            postInvalidate();
        }

        public PointF getRatio() {
            this.scaleY = 1.0f;
            this.scaleX = 1.0f;
            this.scaleY = Duff_CollageActivity.this.mulY / Duff_CollageActivity.this.mulX;
            if (!Duff_CollageActivity.this.isScrapBook) {
                float f = this.scaleY;
                if (f > RATIO_CONSTANT) {
                    this.scaleX = RATIO_CONSTANT / f;
                    this.scaleY = RATIO_CONSTANT;
                }
            }
            return new PointF(this.scaleX, this.scaleY);
        }

        public void UpdateShapeListForFilterBitmap(Bitmap bitmap) {
            if (this.shapeIndex >= 0) {
                for (int i = 0; i < this.shapeLayoutList.size(); i++) {
                    this.shapeLayoutList.get(i).shapeArr[this.shapeIndex].setBitmap(bitmap, true);
                }
            }
        }

        public void UpdateParamList(Collards_Parameter cMParameter) {
            if (this.shapeIndex >= 0) {
                Duff_CollageActivity.this.parameterList[this.shapeIndex] = new Collards_Parameter(cMParameter);
            }
        }

        public void SwapBitmaps(int i, int i2) {
            Bitmap bitmap = this.shapeLayoutList.get(0).shapeArr[i].getBitmap();
            Bitmap bitmap2 = this.shapeLayoutList.get(0).shapeArr[i2].getBitmap();
            for (int i3 = 0; i3 < this.shapeLayoutList.size(); i3++) {
                this.shapeLayoutList.get(i3).shapeArr[i].setBitmap(bitmap2, false);
                this.shapeLayoutList.get(i3).shapeArr[i2].setBitmap(bitmap, false);
            }
            Bitmap bitmap3 = Duff_CollageActivity.this.btmpList[i];
            Duff_CollageActivity.this.btmpList[i] = Duff_CollageActivity.this.btmpList[i2];
            Duff_CollageActivity.this.btmpList[i2] = bitmap3;
            Collards_Parameter cMParameter = Duff_CollageActivity.this.parameterList[i];
            Duff_CollageActivity.this.parameterList[i] = Duff_CollageActivity.this.parameterList[i2];
            Duff_CollageActivity.this.parameterList[i2] = cMParameter;
            float floatValue = this.smallestDistanceList.get(i).floatValue();
            ArrayList<Float> arrayList = this.smallestDistanceList;
            arrayList.set(i, arrayList.get(i2));
            this.smallestDistanceList.set(i2, Float.valueOf(floatValue));
            Duff_CollageActivity.this.vvSelectSwapTextView.setVisibility(4);
            UnSelectShapes();
        }

        public void setCurrentCollageIndex(int i) {
            this.currentCollageIndex = i;
            if (i >= this.shapeLayoutList.size()) {
                this.currentCollageIndex = 0;
            }
            if (this.currentCollageIndex < 0) {
                this.currentCollageIndex = this.shapeLayoutList.size() - 1;
            }
            setCornerRadius(this.cornerRadius);
            setPathPadding(this.currentCollageIndex, this.paddingDistance);
        }

        public void setCornerRadius(float f) {
            this.cornerRadius = f;
            CornerPathEffect cornerPathEffect = new CornerPathEffect(f);
            for (int i = 0; i < this.shapeLayoutList.get(this.currentCollageIndex).shapeArr.length; i++) {
                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i].setRadius(cornerPathEffect);
            }
            postInvalidate();
        }

        public void setPathPadding(int i, float f) {
            this.paddingDistance = f;
            for (int i2 = 0; i2 < this.shapeLayoutList.get(i).shapeArr.length; i2++) {
                float f2 = this.screenWidth;
                this.shapeLayoutList.get(i).shapeArr[i2].scalePath((this.smallestDistanceList.get(i).floatValue() / 250.0f) * f, f2, f2);
                if (!Duff_CollageActivity.this.isScrapBook) {
                    this.shapeLayoutList.get(i).shapeArr[i2].checkScaleBounds();
                    this.shapeLayoutList.get(i).shapeArr[i2].checkBoundaries();
                }
            }
            postInvalidate();
        }

        public void setCollageSize(Matrix matrix, int i) {
            matrix.reset();
            float CalculateScaleSize = CalculateScaleSize(i);
            this.sizeScale = CalculateScaleSize;
            int i2 = this.offsetX;
            int i3 = this.offsetY;
            matrix.postScale(CalculateScaleSize, CalculateScaleSize, ((i2 + i2) + (Duff_CollageActivity.width * this.scaleX)) / 2.0f, ((i3 + i3) + (Duff_CollageActivity.width * this.scaleY)) / 2.0f);
            invalidate();
        }

        public void setPatternPaint(Bitmap bitmap) {
            if (this.patternPaint == null) {
                Paint paint = new Paint(1);
                this.patternPaint = paint;
                paint.setColor(-1);
            }
            this.patternBitmap = bitmap;
            this.patternPaint.setShader(new BitmapShader(this.patternBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            postInvalidate();
        }

        public void setPatternPaintColor(int i) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(1);
            }
            this.patternPaint.setShader(null);
            this.patternPaint.setColor(i);
            postInvalidate();
        }

        public void setPatternPaintGradient(int i, int i2) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(1);
            }
            this.patternPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), i, i2, Shader.TileMode.MIRROR));
            postInvalidate();
        }

        public void startAnimator() {
            if (Duff_CollageActivity.this.seekBarSize != null) {
                this.animSizeSeekBarProgress = Duff_CollageActivity.this.seekBarSize.getProgress();
            } else {
                this.animSizeSeekBarProgress = 0;
            }
            this.sizeMatrixSaved = new Matrix(this.sizeMatrix);
            this.animationCount = 0;
            this.animate = true;
            removeCallbacks(this.animator);
            postDelayed(this.animator, 150L);
        }

        public int animSize(int i) {
            if (i >= this.animHalfTime) {
                i = this.animationLimit - i;
            }
            return this.animSizeSeekBarProgress + Math.round(i * 2);
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            int saveLayer;
            int width = getWidth();
            int height = getHeight();
            canvas.save();
            RectF rectF = this.drawingAreaRect;
            float f = width;
            float f2 = this.offsetX;
            float f3 = this.offsetY;
            rectF.set(f2, f3, (this.scaleX * f) + f2, (this.scaleY * f) + f3);
            canvas.drawPaint(this.paintGray);
            if (Duff_CollageActivity.this.backgroundMode == 0) {
                canvas.drawRect(this.drawingAreaRect, this.patternPaint);
            }
            Bitmap bitmap = this.blurBitmap;
            if (bitmap != null && !bitmap.isRecycled() && Duff_CollageActivity.this.backgroundMode == 1) {
                this.blurRectDst.set(this.drawingAreaRect);
                canvas.drawBitmap(this.blurBitmap, this.blurRectSrc, this.blurRectDst, this.paint);
            }
            if (!Duff_CollageActivity.this.isScrapBook) {
                canvas.setMatrix(this.sizeMatrix);
            }
            if (!Duff_CollageActivity.this.isScrapBook || Duff_CollageActivity.this.showText) {
                float f4 = this.sizeScale;
                saveLayer = canvas.saveLayer(0.0f, 0.0f, f / f4, height / f4, null, 31);
            } else {
                saveLayer = 0;
            }
            int i = 0;
            while (i < this.shapeLayoutList.get(this.currentCollageIndex).shapeArr.length) {
                boolean z = i == this.shapeLayoutList.get(this.currentCollageIndex).getClearIndex();
                if (Duff_CollageActivity.this.isScrapBook) {
                    this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i].DrawShapeForScrapBook(canvas, width, height, i == this.shapeIndex, this.orthogonal);
                } else {
                    this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i].drawShape(canvas, width, height, saveLayer, z);
                }
                i++;
            }
            if (!Duff_CollageActivity.this.isScrapBook && this.shapeIndex >= 0 && this.shapeLayoutList.get(0).shapeArr.length > 1) {
                canvas.drawRect(this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bounds, this.borderPaint);
            }
            if (Duff_CollageActivity.this.showText) {
                canvas.restoreToCount(saveLayer);
                for (int i2 = 0; i2 < Duff_CollageActivity.this.textDataList.size(); i2++) {
                    this.textMatrix.set(Duff_CollageActivity.this.textDataList.get(i2).imageSaveMatrix);
                    canvas.setMatrix(this.textMatrix);
                    Duff_CollageActivity cMCollageActivity = Duff_CollageActivity.this;
                    cMCollageActivity.applyTextBackground(cMCollageActivity.textDataList.get(i2), canvas);
                    canvas.drawText(Duff_CollageActivity.this.textDataList.get(i2).message, Duff_CollageActivity.this.textDataList.get(i2).xPos, Duff_CollageActivity.this.textDataList.get(i2).yPos, Duff_CollageActivity.this.textDataList.get(i2).textPaint);
                    canvas.setMatrix(this.identityMatrix);
                }
            }
            Bitmap bitmap2 = this.frameBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                canvas.drawBitmap(this.frameBitmap, (Rect) null, this.frameRect, this.paint);
            }
            if (Duff_CollageActivity.this.isScrapBook) {
                canvas.restore();
                this.above.set(0.0f, 0.0f, canvas.getWidth(), this.drawingAreaRect.top);
                this.left.set(0.0f, this.drawingAreaRect.top, this.drawingAreaRect.left, this.drawingAreaRect.bottom);
                this.right.set(this.drawingAreaRect.right, this.drawingAreaRect.top, canvas.getWidth(), this.drawingAreaRect.bottom);
                this.bottom.set(0.0f, this.drawingAreaRect.bottom, canvas.getWidth(), canvas.getHeight());
                canvas.drawRect(this.above, this.paintGray);
                canvas.drawRect(this.left, this.paintGray);
                canvas.drawRect(this.right, this.paintGray);
                canvas.drawRect(this.bottom, this.paintGray);
            }
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (Duff_CollageActivity.this.mCurrentView != null) {
                Duff_CollageActivity.this.mCurrentView.setInEdit(false);
            }
            this.mScaleDetector.onTouchEvent(motionEvent);
            this.mTouchDetector.onTouchEvent(motionEvent);
            if (Duff_CollageActivity.this.isScrapBook) {
                Duff_CollageActivity.this.mRotationDetector.onTouchEvent(motionEvent);
            }
            int action = motionEvent.getAction();
            int i = action & 255;
            if (i == 0) {
                this.previousIndex = this.shapeIndex;
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.mLastTouchX = x;
                this.mLastTouchY = y;
                this.orthogonal = false;
                this.mActivePointerId = motionEvent.getPointerId(0);
                if (!Duff_CollageActivity.this.isScrapBook || this.shapeIndex < 0) {
                    selectCurrentShape(x, y, false);
                } else {
                    this.zoomStartPaint.set(x, y);
                    float[] GetMappedCenter = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].GetMappedCenter();
                    this.pts = GetMappedCenter;
                    if (GetMappedCenter != null) {
                        this.startAngle = -Aspid_Utility.pointToAngle(x, y, GetMappedCenter[0], GetMappedCenter[1]);
                    }
                    this.isInCircle = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].isInCircle(x, y);
                    this.isOnCross = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].isOnCross(x, y);
                }
            } else if (i == 1) {
                this.orthogonal = false;
                this.mActivePointerId = 1;
                if (this.isOnCross) {
                    Duff_CollageActivity.this.createDeleteDialog();
                }
                this.isInCircle = false;
                this.isOnCross = false;
                invalidate();
            } else if (i != 2) {
                if (i == 3) {
                    this.mActivePointerId = 1;
                    this.isInCircle = false;
                    this.isOnCross = false;
                } else if (i == 7) {
                    this.finalAngle = 0.0f;
                    int i2 = (action & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                    if (motionEvent.getPointerId(i2) == this.mActivePointerId) {
                        int i3 = i2 == 0 ? 1 : 0;
                        this.mLastTouchX = motionEvent.getX(i3);
                        this.mLastTouchY = motionEvent.getY(i3);
                        this.mActivePointerId = motionEvent.getPointerId(i3);
                    }
                }
            } else if (!this.isOnCross) {
                int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex == -1) {
                    return false;
                }
                float x2 = motionEvent.getX(findPointerIndex);
                float y2 = motionEvent.getY(findPointerIndex);
                if (this.shapeIndex < 0) {
                    selectCurrentShape(x2, y2, false);
                }
                if (this.shapeIndex >= 0) {
                    if (!Duff_CollageActivity.this.isScrapBook || !this.isInCircle) {
                        this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrixTranslate(x2 - this.mLastTouchX, y2 - this.mLastTouchY);
                        this.mLastTouchX = x2;
                        this.mLastTouchY = y2;
                        invalidate();
                    } else {
                        float[] GetMappedCenter2 = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].GetMappedCenter();
                        this.pts = GetMappedCenter2;
                        float f = -Aspid_Utility.pointToAngle(x2, y2, GetMappedCenter2[0], GetMappedCenter2[1]);
                        String str = Duff_CollageActivity.this.TAG;
                        Log.d(str, "currentAngle " + f);
                        float matrixRotation = getMatrixRotation(this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrix);
                        if ((matrixRotation == 0.0f || matrixRotation == 90.0f || matrixRotation == 180.0f || matrixRotation == -180.0f || matrixRotation == -90.0f) && Math.abs(this.startAngle - f) < 4.0f) {
                            this.orthogonal = true;
                        } else {
                            if (Math.abs((matrixRotation - this.startAngle) + f) < 4.0f) {
                                f = this.startAngle - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math.abs(90.0f - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                                f = (this.startAngle + 90.0f) - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math.abs(180.0f - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                                f = (this.startAngle + 180.0f) - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math.abs((-180.0f) - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                                f = (this.startAngle - 0.024902344f) - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math.abs((-90.0f) - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                                f = (this.startAngle - 0.049804688f) - matrixRotation;
                                this.orthogonal = true;
                            } else {
                                this.orthogonal = false;
                            }
                            this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrixRotate(this.startAngle - f);
                            this.startAngle = f;
                        }
                        float[] fArr = this.pts;
                        float sqrt = ((float) Math.sqrt(((x2 - fArr[0]) * (x2 - fArr[0])) + ((y2 - fArr[1]) * (y2 - fArr[1])))) / ((float) Math.sqrt(((this.zoomStartPaint.x - this.pts[0]) * (this.zoomStartPaint.x - this.pts[0])) + ((this.zoomStartPaint.y - this.pts[1]) * (this.zoomStartPaint.y - this.pts[1]))));
                        float scale = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].getScale();
                        float f2 = this.MIN_ZOOM;
                        if (scale >= f2 || (scale < f2 && sqrt > 1.0f)) {
                            this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrixScaleScrapBook(sqrt, sqrt);
                            this.zoomStartPaint.set(x2, y2);
                        }
                        invalidate();
                        return true;
                    }
                }
            }
            return true;
        }

        private void selectCurrentShapeScrapBook(float f, float f2, boolean z) {
            boolean z2;
            int length = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr.length;
            int i = length - 1;
            int i2 = i;
            while (true) {
                if (i2 < 0) {
                    z2 = false;
                    break;
                } else if (this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i2].isScrapBookSelected(f, f2)) {
                    this.shapeIndex = i2;
                    z2 = true;
                    break;
                } else {
                    i2--;
                }
            }
            if (this.previousIndex == this.shapeIndex && z) {
                UnSelectShapes();
            } else if (!z2) {
                UnSelectShapes();
            } else if (Duff_CollageActivity.this.selectImageForAdj) {
                openFilterFragment();
            } else {
                int i3 = this.shapeIndex;
                if (i3 >= 0 && i3 < length) {
                    Grapes_Shape cMShape = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex];
                    Bitmap bitmap = Duff_CollageActivity.this.btmpList[this.shapeIndex];
                    Collards_Parameter cMParameter = Duff_CollageActivity.this.parameterList[this.shapeIndex];
                    for (int i4 = 0; i4 < length; i4++) {
                        if (i4 >= this.shapeIndex) {
                            if (i4 < i) {
                                int i5 = i4 + 1;
                                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i4] = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i5];
                                Duff_CollageActivity.this.btmpList[i4] = Duff_CollageActivity.this.btmpList[i5];
                                Duff_CollageActivity.this.parameterList[i4] = Duff_CollageActivity.this.parameterList[i5];
                            } else {
                                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i4] = cMShape;
                                Duff_CollageActivity.this.btmpList[i4] = bitmap;
                                Duff_CollageActivity.this.parameterList[i4] = cMParameter;
                            }
                        }
                    }
                    int i6 = this.previousIndex;
                    int i7 = this.shapeIndex;
                    if (i6 == i7) {
                        this.previousIndex = i;
                    } else if (i6 > i7) {
                        this.previousIndex = i6 - 1;
                    }
                    this.shapeIndex = i;
                    if (this.shapeLayoutList.get(0).shapeArr.length > 0) {
                        Duff_CollageActivity.this.contextFooter.setVisibility(0);
                        Duff_CollageActivity.this.stickerlay.setVisibility(8);
                        Duff_CollageActivity.this.SetSelectedTab(5);
                    }
                }
            }
            if (this.shapeIndex >= 0) {
                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[0];
            }
            postInvalidate();
        }

        public void selectCurrentShape(float f, float f2, boolean z) {
            if (Duff_CollageActivity.this.isScrapBook) {
                selectCurrentShapeScrapBook(f, f2, z);
            } else {
                selectCurrentShapeCollage(f, f2, z);
            }
        }

        private void selectCurrentShapeCollage(float f, float f2, boolean z) {
            int i = this.shapeIndex;
            for (int i2 = 0; i2 < this.shapeLayoutList.get(this.currentCollageIndex).shapeArr.length; i2++) {
                if (this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i2].region.contains((int) f, (int) f2)) {
                    this.shapeIndex = i2;
                }
            }
            if (Duff_CollageActivity.this.selectImageForAdj) {
                openFilterFragment();
            } else if (Duff_CollageActivity.this.swapMode) {
                Log.e(Duff_CollageActivity.this.TAG, "PRE SWAP");
                int i3 = this.shapeIndex;
                if (i != i3 && i > -1 && i3 > -1) {
                    Log.e(Duff_CollageActivity.this.TAG, "SWAP");
                    SwapBitmaps(this.shapeIndex, i);
                    Duff_CollageActivity.this.swapMode = false;
                }
            } else if (this.previousIndex == this.shapeIndex && z) {
                UnSelectShapes();
            } else if (this.shapeLayoutList.get(0).shapeArr.length > 0) {
                Duff_CollageActivity.this.contextFooter.setVisibility(0);
                Duff_CollageActivity.this.stickerlay.setVisibility(8);
                Duff_CollageActivity.this.SetSelectedTab(5);
                Log.e(Duff_CollageActivity.this.TAG, "VISIBLE");
            }
            if (this.shapeIndex >= 0) {
                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[0];
            }
            postInvalidate();
        }

        public void UnSelectShapes() {
            Duff_CollageActivity.this.contextFooter.setVisibility(4);
            this.shapeIndex = -1;
            Log.e(Duff_CollageActivity.this.TAG, "unselectShapes");
            postInvalidate();
        }

        public void openFilterFragment() {
            Duff_CollageActivity.this.contextFooter.setVisibility(4);
            Duff_CollageActivity.this.vvSelectFilterTextView.setVisibility(4);
            Duff_CollageActivity.this.selectImageForAdj = false;
            if (this.shapeIndex >= 0) {
                Duff_CollageActivity.this.fullEffectFragment.setBitmapWithParameter(Duff_CollageActivity.this.btmpList[this.shapeIndex], Duff_CollageActivity.this.parameterList[this.shapeIndex]);
                Duff_CollageActivity.this.setVisibilityFilterListView(true);
            }
        }

        public float getMatrixRotation(Matrix matrix) {
            matrix.getValues(this.values);
            float[] fArr = this.values;
            return (float) Math.round(Math.atan2(fArr[1], fArr[0]) * 57.29577951308232d);
        }

        public void setBlurBitmap(final int i, boolean z) {
            if (this.blurBuilderNormal == null) {
                this.blurBuilderNormal = new Cashier_BlurBuilderNormal();
            }
            if (z) {
                Duff_CollageActivity.this.backgroundMode = 2;
                if (!Duff_CollageActivity.this.isScrapBook) {
                    Duff_CollageActivity.this.seekBarSize.setProgress(Duff_CollageActivity.this.seekBarSize.getMax());
                }
            } else {
                Duff_CollageActivity.this.backgroundMode = 1;
            }
            try {
                Duff_CollageActivity.this.runOnUiThread(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.CollageView.3
                    @Override 
                    public void run() {
                        CollageView collageView = CollageView.this;
                        collageView.blurBitmap = NativeStackBlur.process(Duff_CollageActivity.this.btmpList[0].copy(Duff_CollageActivity.this.btmpList[0].getConfig(), true), i);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            Bitmap bitmap = this.blurBitmap;
            if (bitmap != null) {
                setBlurRect2(bitmap.getWidth(), this.blurBitmap.getHeight());
            }
            postInvalidate();
        }

        public void setBlurRect2(float f, float f2) {
            float f3;
            float f4;
            if ((Duff_CollageActivity.this.mulY * f) / Duff_CollageActivity.this.mulX < f2) {
                f3 = (int) f;
                f4 = (Duff_CollageActivity.this.mulY * f) / Duff_CollageActivity.this.mulX;
            } else {
                f3 = (((int) Duff_CollageActivity.this.mulX) * f2) / Duff_CollageActivity.this.mulY;
                f4 = (int) f2;
            }
            int i = (int) ((f - f3) / 2.0f);
            int i2 = (int) ((f2 - f4) / 2.0f);
            this.blurRectSrc.set(i, i2, (int) (i + f3), (int) (i2 + f4));
        }
    }

    public void SingleImageVisibility() {
        findViewById(R.id.seekbar_corner_container).setVisibility(8);
        findViewById(R.id.seekbar_space_container).setVisibility(8);
        findViewById(R.id.button_collage_blur).setVisibility(8);
        findViewById(R.id.button_collage_context_delete).setVisibility(8);
        findViewById(R.id.button_collage_context_swap).setVisibility(8);
        if (!this.isScrapBook) {
            CollageView collageView = this.collageView;
            collageView.setCollageSize(collageView.sizeMatrix, 45);
            SeekBar seekBar = this.seekBarSize;
            if (seekBar != null) {
                seekBar.setProgress(45);
            }
        }
        if (this.isScrapBook) {
            return;
        }
        SetSelectedTab(2);
    }

    public void SetSelectedTab(int i) {
        ViewFlipper viewFlipper = this.viewFlipper;
        if (viewFlipper != null) {
            int displayedChild = viewFlipper.getDisplayedChild();
            if (i == 0) {
                if (displayedChild == 0) {
                    return;
                }
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
                this.viewFlipper.setDisplayedChild(0);
            }
            if (i == 6) {
                startActivityForResult(new Intent(this, StickerDataActivity.class), 89);
            }
            if (i == 4) {
                if (displayedChild == 4) {
                    return;
                }
                if (displayedChild == 0) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(4);
            }
            if (i == 2) {
                if (displayedChild == 2) {
                    return;
                }
                if (displayedChild == 0 || displayedChild == 1) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(2);
            }
            if (i == 3) {
                if (displayedChild == 3) {
                    return;
                }
                if (displayedChild == 5) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                }
                this.viewFlipper.setDisplayedChild(3);
            }
            if (i != 5 || displayedChild == 5) {
                return;
            }
            this.viewFlipper.setInAnimation(this.slideRightIn);
            this.viewFlipper.setOutAnimation(this.slideLeftOut);
            this.viewFlipper.setDisplayedChild(5);
        }
    }

    public void setVisibilityFilterListView(boolean z) {
        if (z && this.fullEffectFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().show(this.fullEffectFragment).commit();
        }
        if (!z && this.fullEffectFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(this.fullEffectFragment).commit();
        }
        findViewById(R.id.collage_effect_fragment_container).bringToFront();
    }

    public void createDeleteDialog() {
        if (this.collageView.shapeLayoutList.get(0).shapeArr.length == 1) {
            Toast makeText = Toast.makeText(this, "You can't delete last image!", 0);
            makeText.setGravity(17, makeText.getXOffset() / 2, makeText.getYOffset() / 2);
            makeText.show();
            return;
        }
        createDeleteDialog(this, this.collageView.shapeIndex, width, height);
    }

    public void createDeleteDialog(Context context, final int i, final int i2, final int i3) {
        Dialog dialog = new Dialog(context, R.style.ThemeWithCorners1);
        this.deleteImageDialog = dialog;
        dialog.requestWindowFeature(1);
        this.deleteImageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.deleteImageDialog.setContentView(R.layout.dg_collage_delete_img);
        this.deleteImageDialog.setTitle("Delete Image");
        this.deleteImageDialog.setCancelable(false);
        this.deleteImageDialog.setCanceledOnTouchOutside(false);
        this.deleteImageDialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.22
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.collageView.DeleteBitmap(i, i2, i3);
                Duff_CollageActivity.this.deleteImageDialog.dismiss();
            }
        });
        this.deleteImageDialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.23
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.this.deleteImageDialog.dismiss();
            }
        });
        this.deleteImageDialog.show();
    }

    public void addStickerView(final Bitmap bitmap) {
        final Ledl_StickerView cMStickerView = new Ledl_StickerView(this);
        cMStickerView.setBitmap(bitmap);
        cMStickerView.setOperationListener(new Ledl_StickerView.OperationListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.24
            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMStickerLayout.CMStickerView.OperationListener
            public void onDeleteClick() {
                Duff_CollageActivity.this.mViewsArray.remove(cMStickerView);
                Duff_CollageActivity.this.btmpStickers.remove(bitmap);
                Duff_CollageActivity.mainLayout.removeView(cMStickerView);
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMStickerLayout.CMStickerView.OperationListener
            public void onEdit(Ledl_StickerView cMStickerView2) {
                Duff_CollageActivity.this.mCurrentView.setInEdit(false);
                Duff_CollageActivity.this.mCurrentView = cMStickerView2;
                Duff_CollageActivity.this.mCurrentView.setInEdit(true);
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMStickerLayout.CMStickerView.OperationListener
            public void onTop(Ledl_StickerView cMStickerView2) {
                int indexOf = Duff_CollageActivity.this.mViewsArray.indexOf(cMStickerView2);
                if (indexOf != Duff_CollageActivity.this.mViewsArray.size() - 1) {
                    Ledl_StickerView cMStickerView3 = (Ledl_StickerView) Duff_CollageActivity.this.mViewsArray.remove(indexOf);
                    cMStickerView3.setTag(Integer.valueOf(Duff_CollageActivity.this.mViewsArray.size()));
                    Duff_CollageActivity.this.mViewsArray.add(Duff_CollageActivity.this.mViewsArray.size(), cMStickerView3);
                    Duff_CollageActivity.this.btmpStickers.add(bitmap);
                }
            }
        });
        mainLayout.addView(cMStickerView, new RelativeLayout.LayoutParams(-1, -1));
        this.mViewsArray.add(cMStickerView);
        this.btmpStickers.add(bitmap);
        setCurrentEdit(cMStickerView);
    }

    private void setCurrentEdit(Ledl_StickerView cMStickerView) {
        Ledl_StickerView cMStickerView2 = this.mCurrentView;
        if (cMStickerView2 != null) {
            cMStickerView2.setInEdit(false);
        }
        this.mCurrentView = cMStickerView;
        cMStickerView.setInEdit(true);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 89 && i2 == -1) {
            ArrayList arrayList = intent != null ? (ArrayList) intent.getSerializableExtra(StickerDataActivity.SELECTED_STICKERS_EXTRA_KEY) : null;
            if (arrayList != null && arrayList.size() > 0) {
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    Picasso.get().load(((Ledl_StickerItemModel) arrayList.get(i3)).getStickerUrl()).into(new Musician_PicassoGenericTarget() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollageActivity.25
                        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMMirrorUtils.CMPicassoGenericTarget, com.squareup.picasso.Target
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                            Duff_CollageActivity.this.addStickerView(bitmap);
                        }
                    });
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CollageContract.View
    public void setPatterns(List<PatternsResponse.PatternsPack> list) {
        this.cmPatternCategoryAdapter.setItems(list);
    }

    public void ChangeBtnSelection(int i) {
        switch (i) {
            case 0:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.green));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            case 1:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.green));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            case 2:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.green));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            case 3:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.green));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            case 4:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.green));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            case 5:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.green));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            case 6:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.green));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            case 7:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.green));
                return;
            case 8:
//                this.img_cllg_layout.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_sticker.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_blur.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_background.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_space.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_ratio.setColorFilter(getResources().getColor(R.color.white));
//                this.img_text.setColorFilter(getResources().getColor(R.color.white));
//                this.img_cllg_adj.setColorFilter(getResources().getColor(R.color.white));
                return;
            default:
                return;
        }
    }
}
