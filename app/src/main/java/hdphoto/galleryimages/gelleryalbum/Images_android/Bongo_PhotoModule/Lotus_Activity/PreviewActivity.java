package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.ExternalStorageHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdActivity.EdEditImageActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.AlbumAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.ImageFolderAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.SlidShowEffectAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.VideoDialogAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.VideoFolderAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImageDeleteFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImagePrivateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.MoMomentFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoDeleteFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoPrivateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.SingleListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DialogAlbumModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.Julia_AccordionTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.BackgroundToForegroundTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.Ak_CubeInTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.CubeOutTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.DefaultTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.DepthPageTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.DrawerTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.Braxton_FlipHorizontalTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.FlipVerticalTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.Reese_ForegroundToBackgroundTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.RotateDownTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.RotateUpTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.ScaleInOutTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.StackTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.TabletTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.ZoomInTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.ZoomOutSlideTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange.ZoomOutTransformer;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.PeasWp.VideoPLayerActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("WrongConstant")

public class PreviewActivity extends BseActivity {
    public static String ImgPath = null;
    private static final String TAG = "PreviewActivity";
    private static final ArrayList<TransformerItem> TRANSFORM_CLASSES;
    public static ArrayList<Object> allImagesList;
    public static LinearLayout ll_bottomControl;
    public static RelativeLayout rl_videoEditing;
    public static ViewPager viewPager;

    Dialog imageAlbumDialog;
    ImageFolderAdapter imageFolderAdapter;
    ImageView imgBack;
    ImageView imgDeleteiv;
    ImageView imgEditImnageiv;
    ImageView imgImageDetailsiv;
    ImageView imgImageEffectiv;
    ImageView imgMoreOption;
    ImageView imgSetWallpaperiv;
    ImageView imgShareiv;
    ImageView imgSlideShowiv;
    ImageView img_brightness;
    ImageView img_brightness_select;
    Dialog insideFileDialog;
    AccessFolder11 accessFolder11;
    AlbumAdapter albumAdapter;
    Dialog albumDialog;
    GridView albumGridView;
    GridView albumImageGridView;
    ClipData clipData;
    ClipboardManager clipboardManager;
    Cursor cursor;
    BottomSheetDialog dialog;
    private Throwable e;
    Handler handler;
    LinearLayout ll_delete;
    LinearLayout ll_delete_trash;
    LinearLayout ll_details;
    LinearLayout ll_edit;
    LinearLayout ll_imgvdo_layout;
    LinearLayout ll_more;
    LinearLayout ll_restore;
    LinearLayout ll_secure;
    LinearLayout ll_trash_layout;
    LinearLayout ll_unsecure;
    String mediaFileType;
    int posForFileDetail11;
    ProgressDialog progressDeleteImage;
    ProgressDialog progressDeleteVideo;
    ProgressDialog progressLockImage;
    ProgressDialog progressLockVideo;
    ProgressDialog progressPermanentImageDelete;
    ProgressDialog progressPermanentVideoDelete;
    Cursor query;
    RadioGroup radioGroup;
    RelativeLayout rl_op_main;
    View rl_top;
    BottomSheetDialog setWPDialog;
    Timer swipeTimer;
    TextView txt_CopyPath;
    TextView txt_CopyTo;
    TextView txt_CurrentImageNo;
    TextView txt_Delete;
    TextView txt_Detail;
    TextView txt_Edit;
    TextView txt_ImageEffect;
    LinearLayout txt_MaxBrightness;
    TextView txt_MoveTo;
    TextView txt_Rename;
    TextView txt_Secure;
    TextView txt_SetAsWallpaper;
    TextView txt_Share;
    TextView txt_SlideShow;
    TextView txt_UnSecure;
    VideoFolderAdapter videoAlbumAdapter;
    VideoDialogAdapter videoDialogAdapter;
    VideoView videoView;
    ViewPagerAdapter viewPagerAdapter;
    public static ArrayList<Object> myImageList = new ArrayList<>();
    public static String FName = "";
    int REQUEST_ID_SET_AS_WALLPAPER = 111;
    String activityName = "null";
    AlertDialog alertDialog1 = null;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            PreviewActivity gMPreviewActivity = PreviewActivity.this;
            gMPreviewActivity.unregisterReceiver(gMPreviewActivity.broadcastReceiver);
            if (Common.IdentifyActivity.equals("EditImageActivity")) {
                Intent intent2 = new Intent(PreviewActivity.this, EdEditImageActivity.class);
                intent2.putExtra("imagepath", PreviewActivity.ImgPath + "");
                PreviewActivity.this.startActivity(intent2);
                Common.IdentifyActivity = "";
            }
        }
    };
    String bucketId = "null";
    int cur_pos = 0;
    int currentPage = 0;
    int currentPosition = 1;
    int delpos = 0;
    int dialog_count = 0;
    boolean isShowView = false;
    String[] dataParameter = {"_id", "_display_name", SMUtilsWP.mediaPath, "date_added"};
    int selectedPos = 0;
    public int slideShowDuration = 1000;
    boolean slideShowRunning = false;
    int totalImage = 0;
    String progressTag = "";
    public boolean gettingData = false;
    String toastTag = "";
    private String folder_path = "";
    ArrayList<Object> arrayList = null;

    static {
        ArrayList<TransformerItem> arrayList = new ArrayList<>();
        TRANSFORM_CLASSES = arrayList;
        arrayList.add(new TransformerItem(DefaultTransformer.class));
        arrayList.add(new TransformerItem(Julia_AccordionTransformer.class));
        arrayList.add(new TransformerItem(BackgroundToForegroundTransformer.class));
        arrayList.add(new TransformerItem(Ak_CubeInTransformer.class));
        arrayList.add(new TransformerItem(CubeOutTransformer.class));
        arrayList.add(new TransformerItem(DepthPageTransformer.class));
        arrayList.add(new TransformerItem(Braxton_FlipHorizontalTransformer.class));
        arrayList.add(new TransformerItem(FlipVerticalTransformer.class));
        arrayList.add(new TransformerItem(Reese_ForegroundToBackgroundTransformer.class));
        arrayList.add(new TransformerItem(RotateDownTransformer.class));
        arrayList.add(new TransformerItem(RotateUpTransformer.class));
        arrayList.add(new TransformerItem(ScaleInOutTransformer.class));
        arrayList.add(new TransformerItem(StackTransformer.class));
        arrayList.add(new TransformerItem(TabletTransformer.class));
        arrayList.add(new TransformerItem(ZoomInTransformer.class));
        arrayList.add(new TransformerItem(ZoomOutSlideTransformer.class));
        arrayList.add(new TransformerItem(ZoomOutTransformer.class));
        arrayList.add(new TransformerItem(DrawerTransformer.class));
    }

    @Override
    public void onCreate(Bundle bundle) {


        super.onCreate(bundle);
        setContentView(R.layout.activity_preview);
        arrayList = new ArrayList<>();
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        Degu_MainActivity.oriTag = "";
        activity = this;
        Window window = activity.getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        if (getIntent() == null) {
            finish();
        }
        this.clipboardManager = (ClipboardManager) getSystemService("clipboard");
        ImgPath = getIntent().getStringExtra(Common.gImagePath);
        this.totalImage = getIntent().getIntExtra(Common.gTotalimage, 0);
        this.currentPosition = getIntent().getIntExtra(Common.gCurrenrtPosition, 0);
        Log.println(Log.ASSERT, "#currentPosition", currentPosition + "");
        this.activityName = getIntent().getStringExtra(Common.gActivityname);
        this.mediaFileType = getIntent().getStringExtra(Common.gMediaType);

        this.bucketId = getIntent().getStringExtra(Common.gBucketID);
        this.posForFileDetail11 = this.currentPosition;
        ID_Binding();
        String str = ImgPath;
        String substring = str.substring(str.lastIndexOf("."));
        if (substring.endsWith(".jpg") || substring.endsWith(".JPG") || substring.endsWith(".jpeg") || substring.endsWith(".JPEG") || substring.endsWith(".png") || substring.endsWith(".PNG") || substring.endsWith(".gif") || substring.endsWith(".GIF")) {
            this.ll_edit.setVisibility(0);
            this.txt_SlideShow.setVisibility(0);
            this.txt_ImageEffect.setVisibility(0);
            if (this.activityName.equals("ImagePrivateFragment")) {
                this.txt_SetAsWallpaper.setVisibility(8);
            } else {
                this.txt_SetAsWallpaper.setVisibility(0);
            }
            if (this.activityName.equals("ImageDeleteFragment")) {
                this.ll_trash_layout.setVisibility(0);
                this.ll_imgvdo_layout.setVisibility(8);
            } else {
                this.ll_trash_layout.setVisibility(8);
                this.ll_imgvdo_layout.setVisibility(0);
            }
        } else {
            this.ll_edit.setVisibility(8);
            rl_videoEditing.setVisibility(0);
            this.txt_SlideShow.setVisibility(8);
            this.txt_ImageEffect.setVisibility(8);
            this.txt_SetAsWallpaper.setVisibility(8);
            if (this.activityName.equals("VideoDeleteFragment")) {
                this.ll_trash_layout.setVisibility(0);
                this.ll_imgvdo_layout.setVisibility(8);
            } else {
                this.ll_trash_layout.setVisibility(8);
                this.ll_imgvdo_layout.setVisibility(0);
            }
        }
        if (this.activityName.equals("InnerImageSelectActivity")) {
            this.ll_more.setVisibility(0);
            allImagesList = Degu_ImageSelectActivity.sendImgList;
        } else if (this.activityName.equals("MomentFragment")) {

            this.ll_more.setVisibility(0);
            allImagesList = new ArrayList<>();
//            arrayList = new ArrayList<>();
            Log.e(TAG, "onCreate: ==>" + allImagesList.size());
            Log.println(Log.ASSERT, "Array", arrayList + "");
            for (int i = 0; i < ConstantArrayClass.albumsList.size(); i++) {
                if (ConstantArrayClass.albumsList.get(i) instanceof DataFileModel) {
                    allImagesList.add((DataFileModel) ConstantArrayClass.albumsList.get(i));
                    arrayList.add((DataFileModel) ConstantArrayClass.albumsList.get(i));
//                    Log.println(Log.ASSERT,"Array2",arrayList + "");

                    if (((DataFileModel) allImagesList.get(arrayList.size() - 1)).path.equals(ImgPath)) {
                        this.currentPosition = allImagesList.size() - 1;
                        Log.println(Log.ASSERT, "currentPosition1", currentPosition + "");
                        Log.println(Log.ASSERT, "ImgPath", ImgPath + "");

                        Log.e(TAG, "onCreate1: ==>" + allImagesList.size());
                    }

                }
            }
        } else if (this.activityName.equals("InnerVideoSelectActivity")) {
            allImagesList = VideoSelectActivity.sendImgList;
            this.ll_more.setVisibility(0);
        } else if (this.activityName.equals("ImagePrivateFragment")) {
            this.ll_more.setVisibility(0);
            this.txt_CopyTo.setVisibility(8);
            this.txt_MoveTo.setVisibility(8);
            this.txt_Secure.setVisibility(8);
            this.ll_secure.setVisibility(8);
            this.txt_Rename.setVisibility(8);
            this.txt_UnSecure.setVisibility(8);
            this.ll_unsecure.setVisibility(0);
            allImagesList = ImImagePrivateFragment.sendPrivateImageList;
        } else if (this.activityName.equals("VideoPrivateFragment")) {
            this.ll_more.setVisibility(0);
            this.txt_CopyTo.setVisibility(8);
            this.txt_MoveTo.setVisibility(8);
            this.txt_Secure.setVisibility(8);
            this.ll_secure.setVisibility(8);
            this.txt_Rename.setVisibility(8);
            this.txt_UnSecure.setVisibility(8);
            this.ll_unsecure.setVisibility(0);
            allImagesList = ViVideoPrivateFragment.sendPrivateVideoList;
        } else if (this.activityName.equals("ImageDeleteFragment")) {
            this.ll_more.setVisibility(0);
            this.txt_CopyTo.setVisibility(8);
            this.txt_MoveTo.setVisibility(8);
            this.txt_Secure.setVisibility(8);
            this.ll_secure.setVisibility(8);
            this.txt_Rename.setVisibility(8);
            this.txt_UnSecure.setVisibility(8);
            this.ll_unsecure.setVisibility(0);
            allImagesList = ImImageDeleteFragment.sendDeleteImageList;
        } else if (this.activityName.equals("VideoDeleteFragment")) {
            this.ll_more.setVisibility(0);
            this.txt_CopyTo.setVisibility(8);
            this.txt_MoveTo.setVisibility(8);
            this.txt_Secure.setVisibility(8);
            this.ll_secure.setVisibility(8);
            this.txt_Rename.setVisibility(8);
            this.txt_UnSecure.setVisibility(8);
            this.ll_unsecure.setVisibility(0);
            allImagesList = ViVideoDeleteFragment.sendDeleteVideoList;
        }

        ArrayList<Object> arrayList2 = allImagesList;
        if (arrayList2 == null || arrayList2.isEmpty()) {
            return;
        }

        this.viewPagerAdapter = new ViewPagerAdapter(this, allImagesList);
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.container);
        viewPager = viewPager2;
        viewPager2.setAdapter(this.viewPagerAdapter);
        viewPager.setCurrentItem(currentPosition);
        this.currentPosition++;
        this.txt_CurrentImageNo.setText(this.currentPosition + "/" + this.totalImage);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i2) {
            }

            @Override
            public void onPageScrolled(int i2, float f, int i3) {
                String str2 = "";
                PreviewActivity.this.selectedPos = i2;
                PreviewActivity.this.currentPosition = i2;
                PreviewActivity.this.cur_pos = i2 + 1;
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.posForFileDetail11 = gMPreviewActivity.currentPosition;
                PreviewActivity.ImgPath = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                File file = new File(((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path);
                try {
                    if (file.exists()) {
                        String name = file.getName();
                        str2 = name.substring(name.lastIndexOf("."));
                    }
                } catch (Exception unused) {
                }
                String str3 = (str2.endsWith(".jpg") || str2.endsWith(".JPG") || str2.endsWith(".jpeg") || str2.endsWith(".JPEG") || str2.endsWith(".png") || str2.endsWith(".PNG") || str2.endsWith(".gif") || str2.endsWith(".GIF")) ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : ExifInterface.GPS_MEASUREMENT_3D;
                if (((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).mediaType != null) {
                    str3 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).getMediaType();
                }
                PreviewActivity.this.mediaFileType = str3;
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                String str2 = "";
                PreviewActivity.this.selectedPos = i2;
                int i3 = i2 + 1;
                PreviewActivity.this.currentPosition = i2;
                PreviewActivity.this.cur_pos = i3;
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.posForFileDetail11 = gMPreviewActivity.currentPosition;
                PreviewActivity.ImgPath = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                File file = new File(((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path);
                try {
                    if (file.exists()) {
                        String name = file.getName();
                        str2 = name.substring(name.lastIndexOf("."));
                    }
                } catch (Exception unused) {
                }
                String str3 = (str2.endsWith(".jpg") || str2.endsWith(".JPG") || str2.endsWith(".jpeg") || str2.endsWith(".JPEG") || str2.endsWith(".png") || str2.endsWith(".PNG") || str2.endsWith(".gif") || str2.endsWith(".GIF")) ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : ExifInterface.GPS_MEASUREMENT_3D;
                if (((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).mediaType != null) {
                    str3 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).getMediaType();
                }
                PreviewActivity.this.mediaFileType = str3;
                TextView textView = PreviewActivity.this.txt_CurrentImageNo;
                textView.setText(i3 + "/" + PreviewActivity.allImagesList.size());
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    PreviewActivity.this.ll_edit.setVisibility(0);
                    PreviewActivity.rl_videoEditing.setVisibility(8);
                    PreviewActivity.this.txt_SlideShow.setVisibility(0);
                    PreviewActivity.this.txt_ImageEffect.setVisibility(0);
                    if (PreviewActivity.this.activityName.equals("ImagePrivateFragment")) {
                        PreviewActivity.this.txt_SetAsWallpaper.setVisibility(8);
                    } else {
                        PreviewActivity.this.txt_SetAsWallpaper.setVisibility(0);
                    }
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    PreviewActivity.rl_videoEditing.setVisibility(0);
                    PreviewActivity.this.ll_edit.setVisibility(8);
                    PreviewActivity.this.txt_SlideShow.setVisibility(8);
                    PreviewActivity.this.txt_ImageEffect.setVisibility(8);
                    PreviewActivity.this.txt_SetAsWallpaper.setVisibility(8);
                }
            }
        });
        findViewById(R.id.rel_play).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.3
            @Override
            public void onClick(View view) {
                if (PreviewActivity.this.activityName.equals("VideoPrivateFragment")) {
                    Log.e("===", "rel_play activityName: " + PreviewActivity.this.activityName);
                    Intent intent = new Intent(PreviewActivity.this, VideoPLayerActivity.class);
                    intent.putExtra("medium", PreviewActivity.ImgPath);
                    intent.putExtra("videoPos", PreviewActivity.this.currentPosition);
                    intent.putExtra("fromActivity", "VideoPrivateFragment");
                    intent.addCategory("android.intent.category.DEFAULT");
                    PreviewActivity.this.startActivity(intent);
                    return;
                }
                Log.e("===", "rel_play activityName: " + PreviewActivity.this.activityName);
                Intent intent2 = new Intent(PreviewActivity.this, VideoPLayerActivity.class);
                intent2.putExtra("medium", PreviewActivity.ImgPath);
                intent2.putExtra("videoPos", PreviewActivity.this.currentPosition);
                intent2.putExtra("fromActivity", "null");
                intent2.addCategory("android.intent.category.DEFAULT");
                PreviewActivity.this.startActivity(intent2);
            }
        });
        this.viewPagerAdapter.setItemClickCallback(new SingleListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.4
            @Override
            // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.SingleListener
            public void onSingleCallback(Object obj, Object obj2, Object obj3, View view) {
                if (PreviewActivity.this.slideShowRunning) {
                    PreviewActivity.this.isShowView = false;
                    PreviewActivity.this.slideShowRunning = false;
                    PreviewActivity.this.ShowControl();
                    if (PreviewActivity.this.swipeTimer != null) {
                        PreviewActivity.this.swipeTimer.cancel();
                        PreviewActivity.this.swipeTimer.purge();
                        PreviewActivity.this.swipeTimer = null;
                        return;
                    }
                    return;
                }
                PreviewActivity.this.hideShowController();
            }
        });
    }

    public void hideShowController() {
        if (ll_bottomControl.getVisibility() == 0) {
            ll_bottomControl.setVisibility(4);
            this.rl_top.setVisibility(4);
            return;
        }
        ll_bottomControl.setVisibility(0);
        this.rl_top.setVisibility(0);
    }


    public static final class TransformerItem {
        final Class<? extends ViewPager.PageTransformer> clazz;
        final String title;

        public TransformerItem(Class<? extends ViewPager.PageTransformer> cls) {
            this.clazz = cls;
            this.title = cls.getSimpleName();
        }

        public String toString() {
            return this.title;
        }
    }

    private void ID_Binding() {
        this.imgBack =  findViewById(R.id.btnBack);
        this.ll_trash_layout =  findViewById(R.id.ll_trash_layout);
        this.ll_imgvdo_layout =  findViewById(R.id.ll_imgvdo_layout);
        this.ll_restore =  findViewById(R.id.ll_restore);
        this.ll_delete_trash =  findViewById(R.id.ll_delete_trash);
        this.txt_CurrentImageNo =  findViewById(R.id.txtCurrentImageNo);
        this.imgShareiv =  findViewById(R.id.btnShareImage);
        this.videoView =  findViewById(R.id.videoview);
        this.imgDeleteiv =  findViewById(R.id.btnDeleteImage);
        this.imgImageEffectiv =  findViewById(R.id.btnImageEffect);
        this.imgSetWallpaperiv =  findViewById(R.id.btnSetWallpaper);
        this.imgSlideShowiv =  findViewById(R.id.btnSlideShow);
        this.imgEditImnageiv =  findViewById(R.id.btnEditImnage);
        this.imgImageDetailsiv =  findViewById(R.id.btnImageDetails);
        this.imgMoreOption =  findViewById(R.id.btnmoreoption);
        ll_bottomControl =  findViewById(R.id.linearLayout2);
        this.rl_top = findViewById(R.id.rl_top);
        rl_videoEditing = (RelativeLayout) findViewById(R.id.rl_videoEditing);
        this.rl_op_main = (RelativeLayout) findViewById(R.id.rel_op_main);
        this.txt_SlideShow =  findViewById(R.id.txt_SlideShow);
        this.txt_ImageEffect =  findViewById(R.id.txt_ImageEffect);
        this.txt_Detail =  findViewById(R.id.txt_detail);
        this.txt_Delete =  findViewById(R.id.txt_del);
        this.txt_Edit =  findViewById(R.id.txt_edit);
        this.txt_Rename =  findViewById(R.id.txt_rename);
        this.txt_Share =  findViewById(R.id.txt_share);
        this.txt_CopyTo =  findViewById(R.id.txt_copy_to);
        this.txt_MoveTo =  findViewById(R.id.txt_move_to);
        this.txt_Secure =  findViewById(R.id.txt_secure);
        this.txt_UnSecure =  findViewById(R.id.txt_unsecure);
        this.txt_CopyPath =  findViewById(R.id.txt_Copy_Path);
        this.txt_SetAsWallpaper =  findViewById(R.id.txt_Set_as_wallpaper);
        this.txt_MaxBrightness =  findViewById(R.id.txt_max_brightness);
        this.img_brightness =  findViewById(R.id.img_brightness);
        this.img_brightness_select =  findViewById(R.id.img_brightness_select);
        this.ll_more =  findViewById(R.id.lin_more);
        this.ll_secure =  findViewById(R.id.ll_secure);
        this.ll_edit =  findViewById(R.id.ll_edit);
        this.ll_delete =  findViewById(R.id.ll_delete);
        this.ll_details =  findViewById(R.id.ll_details);
        this.ll_unsecure =  findViewById(R.id.ll_unsecure);
        Click_Event();
    }

    private void Click_Event() {
        this.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.ll_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.RestoreDialog();
            }
        });
        this.ll_delete_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.DeleteDialog(PreviewActivity.ImgPath);
            }
        });
        this.ll_secure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    if (PreviewActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                        Common.checkPreview = true;
                        ConstantArrayClass.firstTimeLockDataArray.add((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition));
                        PreviewActivity.this.SecurityDialog();
                        return;
                    }
                    PreviewActivity.this.LockImagesDialog(PreviewActivity.ImgPath);
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    if (PreviewActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                        Common.checkPreview = true;
                        ConstantArrayClass.firstTimeLockDataArray.add((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition));
                        PreviewActivity.this.SecurityDialog();
                        return;
                    }
                    PreviewActivity.this.LockVideosDialog(PreviewActivity.ImgPath);
                }
            }
        });
        this.ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreviewActivity.this.rl_op_main.setVisibility(8);
                Intent intent = new Intent(PreviewActivity.this, EdEditImageActivity.class);
                StringBuilder sb = new StringBuilder();
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                sb.append(PreviewActivity.ImgPath);
                sb.append("");
                intent.putExtra("imagepath", sb.toString());
                PreviewActivity.this.startActivity(intent);
                Common.IdentifyActivity = "";

            }
        });
        this.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.DeleteDialog(PreviewActivity.ImgPath);
            }
        });
        this.ll_details.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.11
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.DetailDialog(PreviewActivity.ImgPath);
            }
        });
        this.ll_unsecure.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.12
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.UnlockDialog();
            }
        });
        this.txt_SlideShow.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.13
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.SlidShowTimerDialog();
            }
        });
        this.txt_ImageEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.SlidEffectDialog();
            }
        });
        this.imgShareiv.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.15
            @Override
            public void onClick(View view) {
                PreviewActivity.this.SingleShare();
            }
        });
        this.imgDeleteiv.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.16
            @Override
            public void onClick(View view) {
                PreviewActivity.this.deleteImage(PreviewActivity.ImgPath);
            }
        });
        this.imgImageEffectiv.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.17
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.SlidEffectDialog();
            }
        });
        this.imgSetWallpaperiv.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.18
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.ATTACH_DATA");
                intent.addFlags(1);
                intent.addFlags(524288);
                intent.setDataAndType(FileProvider.getUriForFile(activity, "hdphoto.galleryimages.gelleryalbum.android.provider", new File(PreviewActivity.ImgPath)), PreviewActivity.getMimeType(PreviewActivity.ImgPath));
                intent.putExtra("SET_LOCKSCREEN_WALLPAPER", true);
                activity.startActivityForResult(Intent.createChooser(intent, PreviewActivity.this.getString(R.string.set_as)), PreviewActivity.this.REQUEST_ID_SET_AS_WALLPAPER);
            }
        });
        this.imgSlideShowiv.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.19
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.SlidShowTimerDialog();
            }
        });
        this.imgImageDetailsiv.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.20
            @Override
            public void onClick(View view) {
                PreviewActivity.this.DetailDialog(PreviewActivity.ImgPath);
            }
        });
        this.imgMoreOption.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.21
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(0);
            }
        });
        this.rl_op_main.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.22
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
            }
        });
        this.txt_Detail.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.23
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.DetailDialog(PreviewActivity.ImgPath);
            }
        });
        this.txt_Delete.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.24
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.DeleteDialog(PreviewActivity.ImgPath);
            }
        });
        this.txt_Edit.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.25
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                Intent intent = new Intent(PreviewActivity.this, EdEditImageActivity.class);
                intent.putExtra("imagepath", PreviewActivity.ImgPath + "");
                PreviewActivity.this.startActivity(intent);
                Common.IdentifyActivity = "";
            }
        });
        this.txt_Rename.setVisibility(0);
        this.txt_Rename.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.26
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                if (PreviewActivity.this.activityName.equals("ImagePrivateFragment")) {
                    return;
                }
                PreviewActivity.this.RenameFileDialog();
            }
        });
        this.txt_Share.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.27
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.SingleShare();
            }
        });
        this.txt_CopyTo.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.28
            @Override
            public void onClick(View view) {

                PreviewActivity.this.rl_op_main.setVisibility(8);
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    PreviewActivity.this.GetImageAlbumDialog(false);
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    PreviewActivity.this.GetVideoAlbumDialog(false);
                }

            }
        });
        this.txt_MoveTo.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.29
            @Override
            public void onClick(View view) {

                PreviewActivity.this.rl_op_main.setVisibility(8);
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    PreviewActivity.this.GetImageAlbumDialog(true);
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    PreviewActivity.this.GetVideoAlbumDialog(true);
                }

            }
        });
        this.txt_Secure.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.30
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    if (PreviewActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                        PreviewActivity.this.SecurityDialog();
                    } else {
                        PreviewActivity.this.LockImagesDialog(PreviewActivity.ImgPath);
                    }
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    if (PreviewActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                        PreviewActivity.this.SecurityDialog();
                    } else {
                        PreviewActivity.this.LockVideosDialog(PreviewActivity.ImgPath);
                    }
                }
            }
        });
        this.txt_UnSecure.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.31
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                PreviewActivity.this.UnlockDialog();
            }
        });
        this.txt_CopyPath.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.32
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                ClipData newPlainText = ClipData.newPlainText(MimeTypes.BASE_TYPE_TEXT, PreviewActivity.ImgPath);
                PreviewActivity.this.clipData = newPlainText;
                PreviewActivity.this.clipboardManager.setPrimaryClip(newPlainText);
                PreviewActivity.this.rl_op_main.setVisibility(8);
                Toast.makeText(PreviewActivity.this, "Path copied successfully.", 0).show();
            }
        });
        this.txt_SetAsWallpaper.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.33
            @Override
            public void onClick(View view) {
                PreviewActivity.this.rl_op_main.setVisibility(8);
                Intent intent = new Intent("android.intent.action.ATTACH_DATA");
                intent.addFlags(1);
                intent.addFlags(524288);
                intent.setDataAndType(FileProvider.getUriForFile(activity, "hdphoto.galleryimages.gelleryalbum.android.provider", new File(PreviewActivity.ImgPath)), PreviewActivity.getMimeType(PreviewActivity.ImgPath));
                intent.putExtra("SET_LOCKSCREEN_WALLPAPER", true);
                activity.startActivityForResult(Intent.createChooser(intent, PreviewActivity.this.getString(R.string.set_as)), PreviewActivity.this.REQUEST_ID_SET_AS_WALLPAPER);
            }
        });
        this.txt_MaxBrightness.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.34
            @Override
            public void onClick(View view) {
                if (PreviewActivity.this.img_brightness.getVisibility() == 0) {
                    PreviewActivity.this.getWindow().addFlags(128);
                    WindowManager.LayoutParams attributes = PreviewActivity.this.getWindow().getAttributes();
                    attributes.screenBrightness = 1.0f;
                    PreviewActivity.this.getWindow().setAttributes(attributes);
                    PreviewActivity.this.img_brightness.setVisibility(8);
                    PreviewActivity.this.img_brightness_select.setVisibility(0);
                    PreviewActivity.this.rl_op_main.setVisibility(8);
                    return;
                }
                PreviewActivity.this.getWindow().addFlags(128);
                WindowManager.LayoutParams attributes2 = PreviewActivity.this.getWindow().getAttributes();
                attributes2.screenBrightness = -1.0f;
                PreviewActivity.this.getWindow().setAttributes(attributes2);
                PreviewActivity.this.img_brightness_select.setVisibility(8);
                PreviewActivity.this.img_brightness.setVisibility(0);
                PreviewActivity.this.rl_op_main.setVisibility(8);
            }
        });
    }

    public static String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    private void SetWallpaper() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogStyle);
        this.setWPDialog = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        this.setWPDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.setWPDialog.setContentView(R.layout.dg_custom_wallpaper);
        this.setWPDialog.setCancelable(false);
        this.setWPDialog.setCanceledOnTouchOutside(false);
        this.setWPDialog.findViewById(R.id.rlSetHome).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.35
            @Override
            public void onClick(View view) {
                PreviewActivity.this.setWPDialog.dismiss();
                PreviewActivity.this.SetHomeWall();
            }
        });
        this.setWPDialog.findViewById(R.id.rlSetLock).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.36
            @Override
            public void onClick(View view) {
                PreviewActivity.this.setWPDialog.dismiss();
                PreviewActivity.this.SetLockWall();
            }
        });
        this.setWPDialog.findViewById(R.id.rlSetBoth).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.37
            @Override
            public void onClick(View view) {
                PreviewActivity.this.setWPDialog.dismiss();
                PreviewActivity.this.SetBothWall();
            }
        });
        this.setWPDialog.findViewById(R.id.rlCancel).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.38
            @Override
            public void onClick(View view) {
                PreviewActivity.this.setWPDialog.dismiss();
            }
        });
        this.setWPDialog.show();
    }

    public void SetHomeWall() {
        try {
            new HomeWallAsync().execute(new Void[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class HomeWallAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressHomeWallDialog;

        public HomeWallAsync() {
            this.progressHomeWallDialog = new ProgressDialog(PreviewActivity.this);
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressHomeWallDialog.setMessage("Please wait a while...");
            this.progressHomeWallDialog.setProgressStyle(0);
            this.progressHomeWallDialog.setIndeterminate(false);
            this.progressHomeWallDialog.setCancelable(false);
            this.progressHomeWallDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            Glide.with((FragmentActivity) PreviewActivity.this).asBitmap().load(PreviewActivity.ImgPath).into(new SimpleTarget<Bitmap>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.HomeWallAsync.1
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
//                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//                }

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    try {
                        if (Build.VERSION.SDK_INT >= 24) {
                            WallpaperManager.getInstance(PreviewActivity.this).setBitmap(bitmap, null, true, 1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }

        @Override
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            this.progressHomeWallDialog.dismiss();
        }
    }

    public void SetLockWall() {
        try {
            new LockWallAsync().execute(new Void[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class LockWallAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressLockWallDialog;

        public LockWallAsync() {
            this.progressLockWallDialog = new ProgressDialog(PreviewActivity.this);
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressLockWallDialog.setMessage("Please wait a while...");
            this.progressLockWallDialog.setProgressStyle(0);
            this.progressLockWallDialog.setIndeterminate(false);
            this.progressLockWallDialog.setCancelable(false);
            this.progressLockWallDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            Glide.with((FragmentActivity) PreviewActivity.this).asBitmap().load(PreviewActivity.ImgPath).into(new SimpleTarget<Bitmap>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.LockWallAsync.1
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
//                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//                }

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    try {
                        if (Build.VERSION.SDK_INT >= 24) {
                            WallpaperManager.getInstance(PreviewActivity.this).setBitmap(bitmap, null, true, 2);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }

        @Override
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            this.progressLockWallDialog.dismiss();
        }
    }

    public void SetBothWall() {
        try {
            new BothWallAsync().execute(new Void[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class BothWallAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressBothWallDialog;

        public BothWallAsync() {
            this.progressBothWallDialog = new ProgressDialog(PreviewActivity.this);
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressBothWallDialog.setMessage("Please wait a while...");
            this.progressBothWallDialog.setProgressStyle(0);
            this.progressBothWallDialog.setIndeterminate(false);
            this.progressBothWallDialog.setCancelable(false);
            this.progressBothWallDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            Glide.with((FragmentActivity) PreviewActivity.this).asBitmap().load(PreviewActivity.ImgPath).into(new SimpleTarget<Bitmap>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.BothWallAsync.1
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
//                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//                }

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    try {
                        if (Build.VERSION.SDK_INT >= 24) {
                            WallpaperManager.getInstance(PreviewActivity.this).setBitmap(bitmap, null, true, 2);
                            WallpaperManager.getInstance(PreviewActivity.this).setBitmap(bitmap, null, true, 1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }

        @Override
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            this.progressBothWallDialog.dismiss();
        }
    }

    private void AlbumOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (this.albumAdapter != null) {
            int i2 = displayMetrics.widthPixels;
            this.albumAdapter.setLayoutParams(i == 1 ? i2 / 3 : i2 / 9);
        }
        if (this.videoDialogAdapter != null) {
            int i3 = displayMetrics.widthPixels;
            this.videoDialogAdapter.setLayoutParams(i == 1 ? i3 / 3 : i3 / 9);
        }
        this.albumGridView.setNumColumns(i != 1 ? 9 : 3);
    }

    private void ImageOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (this.imageFolderAdapter != null) {
            int i2 = displayMetrics.widthPixels;
            this.imageFolderAdapter.setLayoutParams(i == 1 ? i2 / 4 : i2 / 16);
        }
        if (this.videoAlbumAdapter != null) {
            int i3 = displayMetrics.widthPixels;
            this.videoAlbumAdapter.setLayoutParams(i == 1 ? i3 / 4 : i3 / 16);
        }
        this.albumImageGridView.setNumColumns(i != 1 ? 16 : 4);
    }

    public void UnlockDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_unlock_image);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Unlock Image");
            ( (TextView)dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to unlock image?");
        } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            ( (TextView)dialog.findViewById(R.id.txtTitle)).setText("Unlock Video");
            ((TextView) dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to unlock video?");
        }
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.39
            @Override
            public void onClick(View view) {
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    new UnLockImageExecute(PreviewActivity.ImgPath).execute(new Void[0]);
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    new UnLockVideoExecute(PreviewActivity.ImgPath).execute(new Void[0]);
                }
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.40
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class UnLockImageExecute extends AsyncTask<Void, Void, Void> {
        String imagePath;
        ProgressDialog progressDialog;
        boolean isRecover = true;
        ArrayList<Object> imageArray = new ArrayList<>();

        public UnLockImageExecute(String str) {
            this.progressDialog = new ProgressDialog(PreviewActivity.this);
            this.imagePath = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Please wait a while...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            File file = new File(this.imagePath);
            String name = file.getName();
            File file2 = new File(FolderPath.SDCARD_PATH_IMAGE_LOCK_BACKUP);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2 + File.separator + name);
            if (file3.exists()) {
                Log.e(PreviewActivity.TAG, "doInBackground: ");
            }
            String parent = file3.getParent();
            new MediaScanners(GalleryAppClass.getInstance(), file3);
            try {
                FileUtils.CopyMoveFile(file, file3);
                AppUtilsClass.insertUri(PreviewActivity.this, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                String str2 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                listString.add(str2);
                arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                File file4 = new File(this.imagePath);
                file4.delete();
                ContentResolver contentResolver = PreviewActivity.this.getContentResolver();
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("OOOOO", " 000 - " + e.getMessage());
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r6) {
            super.onPostExecute(r6);
            int i = PreviewActivity.this.currentPosition;
            this.progressDialog.dismiss();
            if (i == PreviewActivity.allImagesList.size()) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.currentPosition--;
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                i = 0;
            } else {
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
            }
            PreviewActivity.this.viewPagerAdapter = new ViewPagerAdapter(activity, PreviewActivity.allImagesList);
            PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
            PreviewActivity.viewPager.setCurrentItem(i);
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.UnLockImageExecute.1
                @Override
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo(PreviewActivity.this);
                }
            }, 500L);
            if ((PreviewActivity.this.activityName.equals("ImagePrivateFragment") || PreviewActivity.this.activityName.equals("VideoPrivateFragment") || PreviewActivity.this.activityName.equals("ImageDeleteFragment") || PreviewActivity.this.activityName.equals("VideoDeleteFragment")) && PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                gMPreviewActivity2.setResult(-1, gMPreviewActivity2.getIntent());
                PreviewActivity.this.finish();
            }
            Toast.makeText(PreviewActivity.this, "Files are unlocked successfully.", 0).show();
        }
    }


    public class UnLockVideoExecute extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;
        String videoPath;
        boolean isRecover = true;
        ArrayList<Object> imageArray = new ArrayList<>();

        public UnLockVideoExecute(String str) {
            this.progressDialog = new ProgressDialog(PreviewActivity.this);
            this.videoPath = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Please wait a while...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            File file = new File(this.videoPath);
            String name = file.getName();
            File file2 = new File(FolderPath.SDCARD_PATH_VIDEO_LOCK_BACKUP);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2 + File.separator + name);
            if (file3.exists()) {
                Log.e(PreviewActivity.TAG, "doInBackground: ");
            }
            String parent = file3.getParent();
            new MediaScanners(GalleryAppClass.getInstance(), file3);
            try {
                FileUtils.CopyMoveFile(file, file3);
                AppUtilsClass.insertUri(PreviewActivity.this, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                String str2 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                listString.add(str2);
                arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                File file4 = new File(this.videoPath);
                file4.delete();
                ContentResolver contentResolver = PreviewActivity.this.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("OOOOO", " 000 - " + e.getMessage());
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r6) {
            super.onPostExecute(r6);
            this.progressDialog.dismiss();
            int i = PreviewActivity.this.currentPosition;
            if (i == PreviewActivity.allImagesList.size()) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.currentPosition--;
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                i = 0;
            } else {
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
            }
            PreviewActivity.this.viewPagerAdapter = new ViewPagerAdapter(activity, PreviewActivity.allImagesList);
            PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
            PreviewActivity.viewPager.setCurrentItem(i);
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.UnLockVideoExecute.1
                @Override
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo(PreviewActivity.this);
                }
            }, 500L);
            if ((PreviewActivity.this.activityName.equals("ImagePrivateFragment") || PreviewActivity.this.activityName.equals("VideoPrivateFragment") || PreviewActivity.this.activityName.equals("ImageDeleteFragment") || PreviewActivity.this.activityName.equals("VideoDeleteFragment")) && PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                gMPreviewActivity2.setResult(-1, gMPreviewActivity2.getIntent());
                PreviewActivity.this.finish();
            }
            Toast.makeText(PreviewActivity.this, "Files are restored successfully.", 0).show();
        }
    }

    public void DeleteDialog(final String str) {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_file);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Delete Image");
            ( (TextView)dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to delete image?");
        } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            ( (TextView)dialog.findViewById(R.id.txtTitle)).setText("Delete Video");
            ( (TextView)dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to delete video?");
        }
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.41
            @Override
            public void onClick(View view) {
                if (PreviewActivity.this.activityName.equals("ImageDeleteFragment") || PreviewActivity.this.activityName.equals("VideoDeleteFragment") || PreviewActivity.this.activityName.equals("ImagePrivateFragment") || PreviewActivity.this.activityName.equals("VideoPrivateFragment")) {
                    new DeleteBGExecute(str).execute(new Void[0]);
                } else if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    PreviewActivity.this.progressTag = "FromDeleteImage";
                    new DeleteTrashImageExecute(str).execute(new Void[0]);
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    PreviewActivity.this.progressTag = "FromDeleteVideo";
                    new DeleteTrashVideoExecute(str).execute(new Void[0]);
                }
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.42
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class DeleteBGExecute extends AsyncTask<Void, Void, Void> {
        String delImagePath;
        ProgressDialog progressDialog;

        public DeleteBGExecute(String str) {
            this.delImagePath = "";
            this.progressDialog = new ProgressDialog(PreviewActivity.this);
            this.delImagePath = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Please wait a while...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = new File(this.delImagePath);
            try {
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                ContentResolver contentResolver = PreviewActivity.this.getContentResolver();
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                try {
                    contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                ContentResolver contentResolver2 = PreviewActivity.this.getContentResolver();
                Uri uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                try {
                    contentResolver2.delete(uri2, "_data='" + file.getPath() + "'", null);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r6) {
            super.onPostExecute(r6);
            int i = PreviewActivity.this.currentPosition;
            if (i == PreviewActivity.allImagesList.size()) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.currentPosition--;
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                i = 0;
            } else {
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
            }
            PreviewActivity.this.viewPagerAdapter = new ViewPagerAdapter(activity, PreviewActivity.allImagesList);
            PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
            PreviewActivity.viewPager.setCurrentItem(i);
            this.progressDialog.dismiss();
            if ((PreviewActivity.this.activityName.equals("ImagePrivateFragment") || PreviewActivity.this.activityName.equals("VideoPrivateFragment") || PreviewActivity.this.activityName.equals("ImageDeleteFragment") || PreviewActivity.this.activityName.equals("VideoDeleteFragment")) && PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                gMPreviewActivity2.setResult(-1, gMPreviewActivity2.getIntent());
                PreviewActivity.this.finish();
            }
            Toast.makeText(PreviewActivity.this, "Files are deleted successfully.", 0).show();
        }
    }


    public class DeleteTrashImageExecute extends AsyncTask<Void, Void, Void> {
        String pathList;

        public DeleteTrashImageExecute(String str) {
            PreviewActivity.this.progressDeleteImage = new ProgressDialog(PreviewActivity.this);
            this.pathList = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            PreviewActivity.this.progressDeleteImage.setMessage("Please wait a while...");
            PreviewActivity.this.progressDeleteImage.setProgressStyle(0);
            PreviewActivity.this.progressDeleteImage.setIndeterminate(false);
            PreviewActivity.this.progressDeleteImage.setCancelable(false);
            PreviewActivity.this.progressDeleteImage.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = null;
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_IMAGE).mkdirs();
            File file2 = new File(this.pathList);
            File file3 = new File(FolderPath.SDCARD_PATH_DELETE_IMAGE + File.separator + file2.getName());
            String parent = file3.getParent();
            if (PreviewActivity.allImagesList.size() == 1) {
                PreviewActivity.this.currentPosition = 0;
            } else if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                PreviewActivity.this.currentPosition--;
            }
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                MoMomentFragment.momentAdapter.RemoveItemSingle(this.pathList);
                PreviewActivity.allImagesList.remove(PreviewActivity.this.selectedPos);
            }
            try {
                FileUtils.CopyMoveFile(file2, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                String str2 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                listString.add(this.pathList);
                arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                new File(this.pathList).delete();
                try {
                    PreviewActivity.this.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data='" + file.getPath() + "'", null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            PreviewActivity gMPreviewActivity = PreviewActivity.this;
            gMPreviewActivity.delpos = gMPreviewActivity.currentPosition;
            if (PreviewActivity.this.activityName.equals("InnerImageSelectActivity")) {
                if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                    PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                    gMPreviewActivity2.currentPosition--;
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                } else {
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                }
            }
            if (PreviewActivity.this.viewPagerAdapter != null) {
                PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
            }
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.DeleteTrashImageExecute.1
                @Override
                public void run() {
                    AppUtilsClass.RefreshImageAlbum(PreviewActivity.this);
                }
            }, 500L);
            AppUtilsClass.RefreshMoment(PreviewActivity.this);
            PreviewActivity.this.progressDeleteImage.dismiss();
            Toast.makeText(PreviewActivity.this, "Files are deleted successfully.", 0).show();
            if (PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity3 = PreviewActivity.this;
                gMPreviewActivity3.setResult(-1, gMPreviewActivity3.getIntent());
                PreviewActivity.this.finish();
            }
        }
    }


    public class DeleteTrashVideoExecute extends AsyncTask<Void, Void, Void> {
        String pathList;

        public DeleteTrashVideoExecute(String str) {
            PreviewActivity.this.progressDeleteVideo = new ProgressDialog(PreviewActivity.this);
            this.pathList = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            PreviewActivity.this.progressDeleteVideo.setMessage("Please wait a while...");
            PreviewActivity.this.progressDeleteVideo.setProgressStyle(0);
            PreviewActivity.this.progressDeleteVideo.setIndeterminate(false);
            PreviewActivity.this.progressDeleteVideo.setCancelable(false);
            PreviewActivity.this.progressDeleteVideo.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = null;
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_VIDEO).mkdirs();
            File file2 = new File(this.pathList);
            File file3 = new File(FolderPath.SDCARD_PATH_DELETE_VIDEO + File.separator + file2.getName());
            String parent = file3.getParent();
            if (PreviewActivity.allImagesList.size() == 1) {
                PreviewActivity.this.currentPosition = 0;
            } else if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                PreviewActivity.this.currentPosition--;
            }
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                MoMomentFragment.momentAdapter.RemoveItemSingle(this.pathList);
                PreviewActivity.allImagesList.remove(PreviewActivity.this.selectedPos);
            }
            try {
                FileUtils.CopyMoveFile(file2, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                String str2 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                listString.add(str2);
                arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                new File(str2).delete();
                try {
                    PreviewActivity.this.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data='" + file.getPath() + "'", null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            PreviewActivity gMPreviewActivity = PreviewActivity.this;
            gMPreviewActivity.delpos = gMPreviewActivity.currentPosition;
            if (PreviewActivity.this.activityName.equals("InnerVideoSelectActivity")) {
                if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                    PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                    gMPreviewActivity2.currentPosition--;
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                } else {
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                }
            }
            if (PreviewActivity.this.viewPagerAdapter != null) {
                PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
            }
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.DeleteTrashVideoExecute.1
                @Override
                public void run() {
                    AppUtilsClass.RefreshVideoAlbum(PreviewActivity.this);
                }
            }, 500L);
            AppUtilsClass.RefreshMoment(PreviewActivity.this);
            PreviewActivity.this.progressDeleteVideo.dismiss();
            Toast.makeText(PreviewActivity.this, "Files are deleted successfully.", 0).show();
            if (PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity3 = PreviewActivity.this;
                gMPreviewActivity3.setResult(-1, gMPreviewActivity3.getIntent());
                PreviewActivity.this.finish();
            }
        }
    }

    public void RenameFileDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_rename_file);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Rename File");
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        editText.setText(FilenameUtils.getBaseName(ImgPath));
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.43
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.clear();
                    arrayList.add(PreviewActivity.ImgPath);
                    File file = new File(PreviewActivity.ImgPath);
                    String parent = file.getParent();
                    String substring = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
                    File file2 = new File(parent + File.separator + editText.getText().toString().trim() + substring);
                    if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                        try {
                            String obj = editText.getText().toString();
                            if (!obj.isEmpty()) {
                                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                                String renameFile = PreviewActivity.renameFile(gMPreviewActivity, file, obj + file2.getName().substring(file2.getName().lastIndexOf("."), file2.getName().length()));
                                if (renameFile != null) {
                                    ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.selectedPos)).setPath(renameFile);
                                } else {
                                    Toast.makeText(PreviewActivity.this, "Try different name", 0).show();
                                }
                            }
                            editText.requestFocus();
                            editText.setError("Image name can not be empty!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                        gMPreviewActivity2.viewPagerAdapter = new ViewPagerAdapter(gMPreviewActivity2, PreviewActivity.allImagesList);
                        PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
                        PreviewActivity.viewPager.setCurrentItem(PreviewActivity.this.selectedPos);
                        dialog.dismiss();
                        Log.d("REFREEEE", " - Rename ");
                        PreviewActivity gMPreviewActivity3 = PreviewActivity.this;
                        gMPreviewActivity3.viewPagerAdapter = new ViewPagerAdapter(gMPreviewActivity3, PreviewActivity.allImagesList);
                        PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
                        PreviewActivity.viewPager.setCurrentItem(PreviewActivity.this.selectedPos);
                        return;
                    }
                    if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                        try {
                            String obj2 = editText.getText().toString();
                            if (!obj2.isEmpty()) {
                                PreviewActivity gMPreviewActivity4 = PreviewActivity.this;
                                String renameFile2 = PreviewActivity.renameFile(gMPreviewActivity4, file, obj2 + file2.getName().substring(file2.getName().lastIndexOf("."), file2.getName().length()));
                                if (renameFile2 != null) {
                                    ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.selectedPos)).setPath(renameFile2);
                                } else {
                                    Toast.makeText(PreviewActivity.this, "Try different name", 0).show();
                                }
                            }
                            editText.requestFocus();
                            editText.setError("Video name can not be empty!");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        PreviewActivity gMPreviewActivity5 = PreviewActivity.this;
                        gMPreviewActivity5.viewPagerAdapter = new ViewPagerAdapter(gMPreviewActivity5, PreviewActivity.allImagesList);
                        PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
                        PreviewActivity.viewPager.setCurrentItem(PreviewActivity.this.selectedPos);
                    }
                    dialog.dismiss();
                    Log.d("REFREEEE", " - Rename ");
                    PreviewActivity gMPreviewActivity6 = PreviewActivity.this;
                    gMPreviewActivity6.viewPagerAdapter = new ViewPagerAdapter(gMPreviewActivity6, PreviewActivity.allImagesList);
                    PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
                    PreviewActivity.viewPager.setCurrentItem(PreviewActivity.this.selectedPos);
                    return;
                }
                editText.setError("Please Enter File Name");
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.44
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static String renameFile(Context context, File file, String str) {
        File file2 = new File(file.getAbsolutePath());
        File file3 = new File(file2.getParent() + File.separator + str);
        if (file2.exists() && !file3.exists() && file2.renameTo(file3) && file3.exists()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SMUtilsWP.mediaPath, file3.getAbsolutePath());
            ContentResolver contentResolver = context.getContentResolver();
            Uri contentUri = MediaStore.Files.getContentUri("external");
            contentResolver.update(contentUri, contentValues, "_data='" + file3 + "'", null);
            Toast.makeText(context, "Rename successfully", 0).show();
            return file3.getAbsolutePath();
        }
        return null;
    }

    public void RestoreDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_restore_image);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Restore Image");
            ((TextView) dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to restore image?");
        } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            ( (TextView)dialog.findViewById(R.id.txtTitle)).setText("Restore Video");
            ((TextView) dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to restore video?");
        }
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.45
            @Override
            public void onClick(View view) {
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    new RestoreImageExecute(PreviewActivity.ImgPath).execute(new Void[0]);
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    new RestoreVideoExecute(PreviewActivity.ImgPath).execute(new Void[0]);
                }
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.46
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class RestoreImageExecute extends AsyncTask<Void, Void, Void> {
        String imagePath;
        ProgressDialog progressDialog;
        boolean isRecover = true;
        ArrayList<Object> imageArray = new ArrayList<>();

        public RestoreImageExecute(String str) {
            this.progressDialog = new ProgressDialog(PreviewActivity.this);
            this.imagePath = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Please wait a while...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            File file = new File(this.imagePath);
            String name = file.getName();
            File file2 = new File(FolderPath.SDCARD_PATH_IMAGE_TRASH_BACKUP);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2 + File.separator + name);
            if (file3.exists()) {
                Log.e(PreviewActivity.TAG, "doInBackground: ");
            }
            String parent = file3.getParent();
            new MediaScanners(GalleryAppClass.getInstance(), file3);
            try {
                FileUtils.CopyMoveFile(file, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                String str2 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                listString.add(str2);
                arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                File file4 = new File(this.imagePath);
                file4.delete();
                ContentResolver contentResolver = PreviewActivity.this.getContentResolver();
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("OOOOO", " 000 - " + e.getMessage());
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r6) {
            super.onPostExecute(r6);
            int i = PreviewActivity.this.currentPosition;
            this.progressDialog.dismiss();
            if (i == PreviewActivity.allImagesList.size()) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.currentPosition--;
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                i = 0;
            } else {
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
            }
            PreviewActivity.this.viewPagerAdapter = new ViewPagerAdapter(activity, PreviewActivity.allImagesList);
            PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
            PreviewActivity.viewPager.setCurrentItem(i);
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.RestoreImageExecute.1
                @Override
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo(PreviewActivity.this);
                }
            }, 500L);
            if ((PreviewActivity.this.activityName.equals("ImagePrivateFragment") || PreviewActivity.this.activityName.equals("VideoPrivateFragment") || PreviewActivity.this.activityName.equals("ImageDeleteFragment") || PreviewActivity.this.activityName.equals("VideoDeleteFragment")) && PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                gMPreviewActivity2.setResult(-1, gMPreviewActivity2.getIntent());
                PreviewActivity.this.finish();
            }
            Toast.makeText(PreviewActivity.this, "Files are unlocked successfully.", 0).show();
        }
    }


    public class RestoreVideoExecute extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;
        String videoPath;
        boolean isRecover = true;
        ArrayList<Object> imageArray = new ArrayList<>();

        public RestoreVideoExecute(String str) {
            this.progressDialog = new ProgressDialog(PreviewActivity.this);
            this.videoPath = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Please wait a while...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            File file = new File(this.videoPath);
            String name = file.getName();
            File file2 = new File(FolderPath.SDCARD_PATH_VIDEO_TRASH_BACKUP);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2 + File.separator + name);
            if (file3.exists()) {
                Log.e(PreviewActivity.TAG, "doInBackground: ");
            }
            String parent = file3.getParent();
            new MediaScanners(GalleryAppClass.getInstance(), file3);
            try {
                FileUtils.CopyMoveFile(file, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                String str2 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                listString.add(str2);
                arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                File file4 = new File(this.videoPath);
                file4.delete();
                ContentResolver contentResolver = PreviewActivity.this.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("OOOOO", " 000 - " + e.getMessage());
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r6) {
            super.onPostExecute(r6);
            this.progressDialog.dismiss();
            int i = PreviewActivity.this.currentPosition;
            if (i == PreviewActivity.allImagesList.size()) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.currentPosition--;
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                i = 0;
            } else {
                PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
            }
            PreviewActivity.this.viewPagerAdapter = new ViewPagerAdapter(activity, PreviewActivity.allImagesList);
            PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
            PreviewActivity.viewPager.setCurrentItem(i);
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.RestoreVideoExecute.1
                @Override
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo(PreviewActivity.this);
                }
            }, 500L);
            if ((PreviewActivity.this.activityName.equals("ImagePrivateFragment") || PreviewActivity.this.activityName.equals("VideoPrivateFragment") || PreviewActivity.this.activityName.equals("ImageDeleteFragment") || PreviewActivity.this.activityName.equals("VideoDeleteFragment")) && PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                gMPreviewActivity2.setResult(-1, gMPreviewActivity2.getIntent());
                PreviewActivity.this.finish();
            }
            Toast.makeText(PreviewActivity.this, "Files are restored successfully.", 0).show();
        }
    }

    public void SecurityDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_security);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView textView =  dialog.findViewById(R.id.textDesc);
        if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            textView.setText("Set your security lock for hide photos!");
        } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            textView.setText("Set your security lock for hide videos!");
        }
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.47
            @Override
            public void onClick(View view) {

                FolderPath.lock_screen = 1;
                PreviewActivity.this.startActivity(new Intent(PreviewActivity.this, DeguPrivateActivity.class));
                dialog.dismiss();

            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.48
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void LockImagesDialog(final String str) {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_lock_image);
        dialog.setTitle("Lock Image");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.49
            @Override
            public void onClick(View view) {
                PreviewActivity.this.LockImages(str);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.50
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void LockImages(String str) {
        if (ExternalStorageHelper.isExternalStorageReadableAndWritable()) {
            try {
                this.progressTag = "FromLockImage";
                new LockImageExecute(str).execute(new Void[0]);
                return;
            } catch (Exception e) {
                e.getMessage();
                this.viewPagerAdapter.notifyDataSetChanged();
                return;
            }
        }
        Toast.makeText(this, "not Read Write", 0).show();
    }


    public class LockImageExecute extends AsyncTask<Void, Void, Void> {
        String pathList;

        public LockImageExecute(String str) {
            PreviewActivity.this.progressLockImage = new ProgressDialog(PreviewActivity.this);
            this.pathList = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            PreviewActivity.this.progressLockImage.setMessage("Please wait a while...");
            PreviewActivity.this.progressLockImage.setProgressStyle(0);
            PreviewActivity.this.progressLockImage.setIndeterminate(false);
            PreviewActivity.this.progressLockImage.setCancelable(false);
            PreviewActivity.this.progressLockImage.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = null;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_IMAGE).mkdirs();
            File file2 = new File(this.pathList);
            File file3 = new File(FolderPath.SDCARD_PATH_IMAGE + File.separator + file2.getName());
            String parent = file3.getParent();
            if (PreviewActivity.allImagesList.size() == 1) {
                PreviewActivity.this.currentPosition = 0;
            } else if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                PreviewActivity.this.currentPosition--;
            }
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                MoMomentFragment.momentAdapter.RemoveItemSingle(this.pathList);
                PreviewActivity.allImagesList.remove(PreviewActivity.this.selectedPos);
            }
            try {
                FileUtils.CopyMoveFile(file2, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                String str2 = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path;
                listString.add(str2);
                arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                new File(this.pathList).delete();
                PreviewActivity.this.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data='" + file.getPath() + "'", null);
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r6) {
            super.onPostExecute(r6);
            PreviewActivity gMPreviewActivity = PreviewActivity.this;
            gMPreviewActivity.delpos = gMPreviewActivity.currentPosition;
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                gMPreviewActivity2.delpos = gMPreviewActivity2.currentPosition;
                if (Degu_ImageSelectActivity.innerBothAlbumSelectAdapter != null) {
                    Degu_ImageSelectActivity.innerBothAlbumSelectAdapter.notifyDataSetChanged();
                }
                if (PreviewActivity.this.viewPagerAdapter != null) {
                    PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
                }
            } else {
                int i = PreviewActivity.this.currentPosition;
                if (i == PreviewActivity.allImagesList.size()) {
                    PreviewActivity gMPreviewActivity3 = PreviewActivity.this;
                    gMPreviewActivity3.currentPosition--;
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                    i = 0;
                } else {
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                }
                PreviewActivity.this.viewPagerAdapter = new ViewPagerAdapter(activity, PreviewActivity.allImagesList);
                PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
                PreviewActivity.viewPager.setCurrentItem(i);
                AppUtilsClass.RefreshMoment(PreviewActivity.this);
            }
            PreviewActivity.this.progressLockImage.dismiss();
            Toast.makeText(PreviewActivity.this, "Files moved to vault successfully.", 0).show();
            if (PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity4 = PreviewActivity.this;
                gMPreviewActivity4.setResult(-1, gMPreviewActivity4.getIntent());
                PreviewActivity.this.finish();
            }
        }
    }

    public void LockVideosDialog(final String str) {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_lock_video);
        dialog.setTitle("Lock Video");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.51
            @Override
            public void onClick(View view) {
                PreviewActivity.this.LockVideos(str);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.52
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void LockVideos(String str) {
        this.progressTag = "FromLockVideo";
        new LockVideoExecute(str).execute(new Void[0]);
    }


    public class LockVideoExecute extends AsyncTask<Void, Void, Void> {
        String pathList;

        public LockVideoExecute(String str) {
            PreviewActivity.this.progressLockVideo = new ProgressDialog(PreviewActivity.this);
            this.pathList = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            PreviewActivity.this.progressLockVideo.setMessage("Please wait a while...");
            PreviewActivity.this.progressLockVideo.setProgressStyle(0);
            PreviewActivity.this.progressLockVideo.setIndeterminate(false);
            PreviewActivity.this.progressLockVideo.setCancelable(false);
            PreviewActivity.this.progressLockVideo.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = null;
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = PreviewActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_VIDEO).mkdirs();
            File file2 = new File(this.pathList);
            File file3 = new File(FolderPath.SDCARD_PATH_VIDEO + File.separator + file2.getName());
            String parent = file3.getParent();
            if (PreviewActivity.allImagesList.size() == 1) {
                PreviewActivity.this.currentPosition = 0;
            } else if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                PreviewActivity.this.currentPosition--;
            }
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                MoMomentFragment.momentAdapter.RemoveItemSingle(this.pathList);
                PreviewActivity.allImagesList.remove(PreviewActivity.this.selectedPos);
            }
            try {
                FileUtils.CopyMoveFile(file2, file3);
                long j = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).id;
                String str = ((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).name;
                listString.add(((DataFileModel) PreviewActivity.allImagesList.get(PreviewActivity.this.currentPosition)).path);
                arrayList.add(new DataFileModel(j, str, this.pathList, file3.getPath(), parent, false));
                new File(this.pathList).delete();
                PreviewActivity.this.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data='" + file.getPath() + "'", null);
                PreviewActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r6) {
            super.onPostExecute(r6);
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.delpos = gMPreviewActivity.currentPosition;
                if (VideoSelectActivity.innerBothAlbumSelectAdapter != null) {
                    VideoSelectActivity.innerBothAlbumSelectAdapter.notifyDataSetChanged();
                }
                if (PreviewActivity.this.viewPagerAdapter != null) {
                    PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
                }
            } else {
                int i = PreviewActivity.this.currentPosition;
                if (i == PreviewActivity.allImagesList.size()) {
                    PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                    gMPreviewActivity2.currentPosition--;
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                    i = 0;
                } else {
                    PreviewActivity.allImagesList.remove(PreviewActivity.this.currentPosition);
                }
                PreviewActivity.this.viewPagerAdapter = new ViewPagerAdapter(activity, PreviewActivity.allImagesList);
                PreviewActivity.viewPager.setAdapter(PreviewActivity.this.viewPagerAdapter);
                PreviewActivity.viewPager.setCurrentItem(i);
                AppUtilsClass.RefreshMoment(PreviewActivity.this);
            }
            PreviewActivity.this.progressLockVideo.dismiss();
            Toast.makeText(PreviewActivity.this, "Files moved to vault successfully.", 0).show();
            if (PreviewActivity.allImagesList.size() == 0) {
                PreviewActivity gMPreviewActivity3 = PreviewActivity.this;
                gMPreviewActivity3.setResult(-1, gMPreviewActivity3.getIntent());
                PreviewActivity.this.finish();
            }
        }
    }

    public void GetImageAlbumDialog(final boolean z) {
        Dialog dialog = new Dialog(this, R.style.MyDialog);
        this.albumDialog = dialog;
        dialog.requestWindowFeature(1);
        this.albumDialog.setCancelable(false);
        this.albumDialog.setContentView(R.layout.dg_folder);
        ImageView imageView =  this.albumDialog.findViewById(R.id.btnBack);
        TextView textView =  this.albumDialog.findViewById(R.id.toolbarTitle);
        textView.setText("Select Image Folder");
        textView.setVisibility(0);
        this.albumGridView = (GridView) this.albumDialog.findViewById(R.id.albumGridView);
        this.query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "bucket_id", "bucket_display_name", SMUtilsWP.mediaPath}, null, null, null);
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (this.query != null) {
            while (this.query.moveToNext()) {
                DialogAlbumModel gMDialogAlbumModel = new DialogAlbumModel();
                Cursor cursor = this.query;
                gMDialogAlbumModel.bucket_id = cursor.getString(cursor.getColumnIndex("bucket_id"));
                if (!arrayList2.contains(gMDialogAlbumModel.bucket_id)) {
                    Cursor cursor2 = this.query;
                    gMDialogAlbumModel.foldername = cursor2.getString(cursor2.getColumnIndex("bucket_display_name"));
                    Cursor cursor3 = this.query;
                    gMDialogAlbumModel.coverThumb = cursor3.getString(cursor3.getColumnIndexOrThrow(SMUtilsWP.mediaPath));
                    Cursor cursor4 = this.query;
                    gMDialogAlbumModel.folderPath = GetParentPath(cursor4.getString(cursor4.getColumnIndexOrThrow(SMUtilsWP.mediaPath)));
                    Cursor cursor5 = this.query;
                    gMDialogAlbumModel.id = cursor5.getString(cursor5.getColumnIndex("_id"));
                    gMDialogAlbumModel.pathlist = GetImagePathList("" + gMDialogAlbumModel.bucket_id);
                    arrayList.add(gMDialogAlbumModel);
                    arrayList2.add(gMDialogAlbumModel.bucket_id);
                }
            }
            this.query.close();
        }
        AlbumAdapter gMAlbumAdapter = new AlbumAdapter(this, arrayList);
        this.albumAdapter = gMAlbumAdapter;
        this.albumGridView.setAdapter((ListAdapter) gMAlbumAdapter);
        AlbumOrientation(getResources().getConfiguration().orientation);
        this.albumGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList arrayList3 = arrayList;
                if (arrayList3 == null || arrayList3.isEmpty()) {
                    return;
                }
                PreviewActivity.this.MoveFileInsideDialog(new File(((DialogAlbumModel) arrayList.get(i)).folderPath), z, ((DialogAlbumModel) arrayList.get(i)).bucket_id, ((DialogAlbumModel) arrayList.get(i)).foldername);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.this.albumDialog.dismiss();
            }
        });
        this.albumDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && PreviewActivity.this.dialog_count == 0) {
                    PreviewActivity.this.onBackPressed();
                    return true;
                }
                PreviewActivity.this.dialog_count = 0;
                return false;
            }
        });
        this.albumDialog.show();
    }

    public String GetParentPath(String str) {
        return new File(str).getAbsoluteFile().getParent();
    }

    public ArrayList<String> GetImagePathList(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{SMUtilsWP.mediaPath, "bucket_display_name"}, "bucket_id = ?", new String[]{str}, null);
        if (query.moveToFirst()) {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(SMUtilsWP.mediaPath);
            query.getColumnIndexOrThrow("bucket_display_name");
            do {
                arrayList.add(query.getString(columnIndexOrThrow));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }

    public void GetVideoAlbumDialog(final boolean z) {
        Dialog dialog = new Dialog(this, R.style.MyDialog);
        this.albumDialog = dialog;
        dialog.requestWindowFeature(1);
        this.albumDialog.setCancelable(false);
        this.albumDialog.setContentView(R.layout.dg_folder);
        TextView textView =  this.albumDialog.findViewById(R.id.toolbarTitle);
        textView.setText("Select Video Folder");
        textView.setVisibility(0);
        this.albumGridView = (GridView) this.albumDialog.findViewById(R.id.albumGridView);
        ( this.albumDialog.findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.this.albumDialog.dismiss();
            }
        });
        this.albumDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && PreviewActivity.this.dialog_count == 0) {
                    PreviewActivity.this.onBackPressed();
                    return true;
                }
                PreviewActivity.this.dialog_count = 0;
                return false;
            }
        });
        Cursor query = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "bucket_id", "bucket_display_name", SMUtilsWP.mediaPath}, null, null, null);
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (query != null) {
            while (query.moveToNext()) {
                DialogAlbumModel gMDialogAlbumModel = new DialogAlbumModel();
                gMDialogAlbumModel.bucket_id = query.getString(query.getColumnIndex("bucket_id"));
                if (!arrayList2.contains(gMDialogAlbumModel.bucket_id)) {
                    gMDialogAlbumModel.foldername = query.getString(query.getColumnIndex("bucket_display_name"));
                    gMDialogAlbumModel.coverThumb = query.getString(query.getColumnIndexOrThrow(SMUtilsWP.mediaPath));
                    gMDialogAlbumModel.folderPath = GetParentPath(query.getString(query.getColumnIndexOrThrow(SMUtilsWP.mediaPath)));
                    gMDialogAlbumModel.id = query.getString(query.getColumnIndex("_id"));
                    gMDialogAlbumModel.pathlist = GetVideoPathList("" + gMDialogAlbumModel.bucket_id);
                    arrayList.add(gMDialogAlbumModel);
                    arrayList2.add(gMDialogAlbumModel.bucket_id);
                }
            }
            query.close();
        }
        VideoDialogAdapter gMVideoDialogAdapter = new VideoDialogAdapter(this, arrayList);
        this.videoDialogAdapter = gMVideoDialogAdapter;
        this.albumGridView.setAdapter((ListAdapter) gMVideoDialogAdapter);
        AlbumOrientation(getResources().getConfiguration().orientation);
        this.videoDialogAdapter.notifyDataSetChanged();
        this.albumGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.58
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList arrayList3 = arrayList;
                if (arrayList3 == null || arrayList3.isEmpty()) {
                    return;
                }
                PreviewActivity.this.MoveFileInsideDialog(new File(((DialogAlbumModel) arrayList.get(i)).folderPath), z, ((DialogAlbumModel) arrayList.get(i)).bucket_id, ((DialogAlbumModel) arrayList.get(i)).foldername);
            }
        });
        this.albumDialog.show();
    }

    public ArrayList<String> GetVideoPathList(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{SMUtilsWP.mediaPath, "bucket_display_name"}, "bucket_id = ?", new String[]{str}, null);
        if (query.moveToFirst()) {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(SMUtilsWP.mediaPath);
            do {
                arrayList.add(query.getString(columnIndexOrThrow));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }

    public void MoveFileInsideDialog(final File file, final boolean z, final String str, String str2) {
        Dialog dialog = new Dialog(this, R.style.MyDialog);
        this.insideFileDialog = dialog;
        dialog.requestWindowFeature(1);
        this.insideFileDialog.setCancelable(false);
        this.insideFileDialog.setContentView(R.layout.dialog_gm_folder_image);
        ImageView imageView =  this.insideFileDialog.findViewById(R.id.btnBack);
        TextView textView =  this.insideFileDialog.findViewById(R.id.toolbarTitle);
        textView.setText(str2);
        textView.setVisibility(0);
        this.albumImageGridView = (GridView) this.insideFileDialog.findViewById(R.id.albumGridView);
        String[] strArr = {"_id", "_display_name", SMUtilsWP.mediaPath, "date_added"};
        if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            this.cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id =?", new String[]{str}, null);
        } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            this.cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id =?", new String[]{str}, null);
        }
        ArrayList arrayList = new ArrayList(this.cursor.getCount());
        while (this.cursor.moveToNext()) {
            Cursor cursor = this.cursor;
            String string = cursor.getString(cursor.getColumnIndex(strArr[2]));
            try {
                if (new File(string).exists()) {
                    DataFileModel gMDataFileModel = new DataFileModel();
                    gMDataFileModel.path = string;
                    arrayList.add(gMDataFileModel);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.cursor.close();
        if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            ImageFolderAdapter gMImageFolderAdapter = new ImageFolderAdapter(this, arrayList);
            this.imageFolderAdapter = gMImageFolderAdapter;
            this.albumImageGridView.setAdapter((ListAdapter) gMImageFolderAdapter);
            ImageOrientation(getResources().getConfiguration().orientation);
            this.imageFolderAdapter.notifyDataSetChanged();
        } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            VideoFolderAdapter gMVideoFolderAdapter = new VideoFolderAdapter(this, arrayList);
            this.videoAlbumAdapter = gMVideoFolderAdapter;
            this.albumImageGridView.setAdapter((ListAdapter) gMVideoFolderAdapter);
            ImageOrientation(getResources().getConfiguration().orientation);
            this.videoAlbumAdapter.notifyDataSetChanged();
        }
        this.insideFileDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.59
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    PreviewActivity.this.dialog_count = 1;
                    PreviewActivity.this.onBackPressed();
                }
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.60
            @Override
            public void onClick(View view) {
                PreviewActivity.this.insideFileDialog.dismiss();
            }
        });
        final LinearLayout linearLayout =  this.insideFileDialog.findViewById(R.id.btnPaste);
        if (linearLayout.getVisibility() == 0) {
            this.albumImageGridView.setOnItemClickListener(null);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.61
            @Override
            public void onClick(View view) {
                PreviewActivity.this.MoveImageonAlbum(file, z, str);
                linearLayout.setVisibility(8);
            }
        });
        this.insideFileDialog.show();
    }

    public void MoveImageonAlbum(File file, boolean z, String str) {
        new MoveToAlbumExecute(ImgPath, file, z, str).execute(new Void[0]);
    }


    public class MoveToAlbumExecute extends AsyncTask<Void, Void, Void> {
        File albumDir;
        String bucketId;
        String imagepath;
        boolean operation;
        ProgressDialog progressDialog;
        int toast_cnt = 0;

        public MoveToAlbumExecute(String str, File file, boolean z, String str2) {
            this.progressDialog = new ProgressDialog(PreviewActivity.this);
            this.imagepath = str;
            this.albumDir = file;
            this.operation = z;
            this.bucketId = str2;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Please wait a while...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            if (this.operation) {
                this.toast_cnt = 0;
                File file = new File(this.imagepath);
                File file2 = new File(this.albumDir + File.separator + file.getName());
                if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    try {
                        FileUtils.CopyMoveFile(file, file2);
                        file.delete();
                        ContentResolver contentResolver = PreviewActivity.this.getContentResolver();
                        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        contentResolver.delete(uri, "_data='" + this.imagepath + "'", null);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(SMUtilsWP.mediaPath, file2.getPath());
                        contentValues.put("mime_type", "image/*");
                        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                        PreviewActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    try {
                        FileUtils.CopyMoveFile(file, file2);
                        file.delete();
                        ContentResolver contentResolver2 = PreviewActivity.this.getContentResolver();
                        Uri uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        contentResolver2.delete(uri2, "_data='" + this.imagepath + "'", null);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("title", file2.getName());
                        contentValues2.put("description", "Name");
                        contentValues2.put(SMUtilsWP.mediaPath, file2.getPath());
                        contentValues2.put("mime_type", "video/*");
                        contentValues2.put("date_added", Long.valueOf(System.currentTimeMillis()));
                        contentValues2.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                        contentValues2.put("bucket_id", Integer.valueOf(file2.getPath().toLowerCase(Locale.US).hashCode()));
                        contentValues2.put("bucket_display_name", file2.getName().toLowerCase(Locale.US));
                        PreviewActivity.this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues2);
                        return null;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return null;
                    }
                } else {
                    return null;
                }
            }
            this.toast_cnt = 1;
            File file3 = new File(PreviewActivity.ImgPath);
            File file4 = new File(this.albumDir + File.separator + System.currentTimeMillis() + file3.getName());
            this.albumDir.mkdirs();
            if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                try {
                    org.apache.commons.io.FileUtils.copyFile(file3, file4);
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put("title", file4.getName());
                    contentValues3.put("description", "Gallery");
                    contentValues3.put(SMUtilsWP.mediaPath, file4.getPath());
                    contentValues3.put("mime_type", "image/*");
                    contentValues3.put("date_added", Long.valueOf(System.currentTimeMillis()));
                    contentValues3.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                    contentValues3.put("bucket_id", Integer.valueOf(file4.getPath().toLowerCase(Locale.US).hashCode()));
                    contentValues3.put("bucket_display_name", file4.getName().toLowerCase(Locale.US));
                    PreviewActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues3);
                    return null;
                } catch (IOException e3) {
                    Log.e(PreviewActivity.TAG, "Copy Image IOException: " + e3);
                    e3.printStackTrace();
                    return null;
                }
            } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                try {
                    org.apache.commons.io.FileUtils.copyFile(file3, file4);
                    ContentValues contentValues4 = new ContentValues();
                    contentValues4.put(SMUtilsWP.mediaPath, file4.getPath());
                    PreviewActivity.this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues4);
                    return null;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            AppUtilsClass.RefreshPhotoVideo(PreviewActivity.this);
            if (PreviewActivity.this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                PreviewActivity.this.RefreshImageAdapter(this.bucketId);
            } else if (PreviewActivity.this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                PreviewActivity.this.RefreshVideoAdapter(this.bucketId);
            }
            int i = this.toast_cnt;
            if (i == 0) {
                Toast.makeText(PreviewActivity.this, "File moved successfully.", 0).show();
            } else if (i == 1) {
                Toast.makeText(PreviewActivity.this, "File copied successfully.", 0).show();
            }
            this.progressDialog.dismiss();
            PreviewActivity.this.insideFileDialog.dismiss();
            PreviewActivity.this.albumDialog.dismiss();
        }
    }

    public void RefreshImageAdapter(String str) {
        Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.dataParameter, "bucket_id =?", new String[]{str}, "date_added");
        ArrayList arrayList = new ArrayList(query.getCount());
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex(this.dataParameter[2]));
            try {
                if (new File(string).exists()) {
                    DataFileModel gMDataFileModel = new DataFileModel();
                    gMDataFileModel.path = string;
                    arrayList.add(gMDataFileModel);
                }
            } catch (Exception e) {
                Log.e("Exception : ", e.toString());
            }
        }
        query.close();
        ArrayList<Object> arrayList2 = new ArrayList<>();
        myImageList = arrayList2;
        arrayList2.addAll(arrayList);
        ImageFolderAdapter gMImageFolderAdapter = new ImageFolderAdapter(this, myImageList);
        this.imageFolderAdapter = gMImageFolderAdapter;
        this.albumImageGridView.setAdapter((ListAdapter) gMImageFolderAdapter);
        ImageOrientation(getResources().getConfiguration().orientation);
        this.imageFolderAdapter.notifyDataSetChanged();
    }

    public void RefreshVideoAdapter(String str) {
        Cursor query = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, this.dataParameter, "bucket_id =?", new String[]{str}, "date_added");
        ArrayList arrayList = new ArrayList(query.getCount());
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex(this.dataParameter[2]));
            try {
                if (new File(string).exists()) {
                    DataFileModel gMDataFileModel = new DataFileModel();
                    gMDataFileModel.path = string;
                    arrayList.add(gMDataFileModel);
                }
            } catch (Exception e) {
                Log.e("Exception : ", e.toString());
            }
        }
        query.close();
        ArrayList<Object> arrayList2 = new ArrayList<>();
        myImageList = arrayList2;
        arrayList2.addAll(arrayList);
        VideoFolderAdapter gMVideoFolderAdapter = new VideoFolderAdapter(this, myImageList);
        this.videoAlbumAdapter = gMVideoFolderAdapter;
        this.albumImageGridView.setAdapter((ListAdapter) gMVideoFolderAdapter);
        ImageOrientation(getResources().getConfiguration().orientation);
        this.videoAlbumAdapter.notifyDataSetChanged();
    }

    public void SlidShowTimerDialog() {
        final Dialog dialog = new Dialog(this, R.style.alert_dialog);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_slide_show);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        this.radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.62
            @Override
            public void onClick(View view) {
                RadioButton radioButton = (RadioButton) PreviewActivity.this.radioGroup.findViewById(PreviewActivity.this.radioGroup.getCheckedRadioButtonId());
                if (radioButton.getText().equals("1 Sec.")) {
                    PreviewActivity.this.slideShowDuration = 1000;
                } else if (radioButton.getText().equals("2 Sec.")) {
                    PreviewActivity.this.slideShowDuration = 2000;
                } else if (radioButton.getText().equals("3 Sec.")) {
                    PreviewActivity.this.slideShowDuration = 3000;
                } else if (radioButton.getText().equals("4 Sec.")) {
                    PreviewActivity.this.slideShowDuration = 4000;
                } else if (radioButton.getText().equals("5 Sec.")) {
                    PreviewActivity.this.slideShowDuration = 5000;
                }
                dialog.dismiss();
                PreviewActivity.this.TimerStart();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.63
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void TimerStart() {
        try {
            this.slideShowRunning = true;
            if (this.viewPagerAdapter != null) {
                this.currentPage = viewPager.getCurrentItem() + 1;
            }
            HideControl();
            this.handler = new Handler();
            final SlideTimer slideTimer = new SlideTimer();
            Timer timer = new Timer();
            this.swipeTimer = timer;
            timer.schedule(new TimerTask() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.64
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    PreviewActivity.this.handler.post(slideTimer);
                }
            }, 0L, this.slideShowDuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class SlideTimer implements Runnable {
        SlideTimer() {
        }

        @Override
        public void run() {
            if (PreviewActivity.this.currentPage == PreviewActivity.allImagesList.size()) {
                PreviewActivity.this.currentPage = 0;
            } else {
                PreviewActivity.this.currentPage++;
            }
            PreviewActivity.viewPager.setCurrentItem(PreviewActivity.this.currentPage, true);
        }
    }

    public void SlidEffectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.alert_dialog);
        View inflate = getLayoutInflater().inflate(R.layout.dg_effect_list, (ViewGroup) null);
        builder.setView(inflate);
        ( inflate.findViewById(R.id.lin_main)).setBackgroundColor(0);
        ListView listView = (ListView) inflate.findViewById(R.id.listView1);
        final SlidShowEffectAdapter gMSlidShowEffectAdapter = new SlidShowEffectAdapter(this, new String[]{"Default", "Accordion", "BackgroundToForeground", "CubeIn", "CubeOut", "DepthPage", "FlipHorizontal", "FlipVertical", "ForegroundToBackground", "RotateDown", "RotateUp", "ScaleInOut", "Stack", "Tablet", "ZoomIn", "ZoomOutSlide", "ZoomOut", "Drawer"});
        listView.setAdapter((ListAdapter) gMSlidShowEffectAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.65
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Common.gEff_Pos = i;
                PreviewActivity.this.SetEffect(i);
                gMSlidShowEffectAdapter.notifyDataSetChanged();
            }
        });
        ((RelativeLayout) inflate.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.66
            @Override
            public void onClick(View view) {
                PreviewActivity.this.alertDialog1.dismiss();
            }
        });
        ((RelativeLayout) inflate.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.67
            @Override
            public void onClick(View view) {
                PreviewActivity.this.alertDialog1.dismiss();
            }
        });
        this.alertDialog1 = builder.show();
    }

    public void SetEffect(int i) {
        try {
            viewPager.setPageTransformer(true, TRANSFORM_CLASSES.get(i).clazz.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        }
    }

    public static String getFilePathFromStringPath(String str) {
        return str.substring(str.lastIndexOf("/") + 1, str.length());
    }

    public void DetailDialog(String str) {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_data_detail);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView textView =  dialog.findViewById(R.id.txtImageName);
        TextView textView2 =  dialog.findViewById(R.id.txtImagePath);
        TextView textView3 =  dialog.findViewById(R.id.txtImageSize);
        TextView textView4 =  dialog.findViewById(R.id.txtImageDate);
        try {
            File file = new File(str);
            if (file.exists()) {
                Date date = new Date(file.lastModified());
                Log.d("JDJDJD", " - Info - " + file.lastModified() + " - Dateee - " + date.toString());
                textView.setText(getFilePathFromStringPath(str));
                textView2.setText(file.getParent());
                textView3.setText(GetFileSize((int) (file.length() / 1024)) + "");
                if (this.activityName.equals("MomentFragment")) {
                    Log.d("JDJDJD", " AD " + ((DataFileModel) allImagesList.get(this.currentPosition)).takenDate);
                    String GetDateFromTimeStampBase = AppUtilsClass.GetDateFromTimeStampBase(Long.parseLong(((DataFileModel) allImagesList.get(this.currentPosition)).takenDate) * 1000);
                    Log.d("JDJDJD", " date1 " + GetDateFromTimeStampBase);
                    textView4.setText(GetDateFromTimeStampBase);
                } else {
                    String GetDateFromTimeStampBase2 = AppUtilsClass.GetDateFromTimeStampBase(file.lastModified());
                    Log.d("JDJDJD", " FD " + file.lastModified() + " -> " + GetDateFromTimeStampBase2);
                    textView4.setText(GetDateFromTimeStampBase2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("JDJDJD", " - Exe - " + e.getMessage());
        }
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.68
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public String GetFileSize(int i) {
        double d = i;
        Double.isNaN(d);
        double d2 = d / 1024.0d;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (d2 > 1.0d) {
            return decimalFormat.format(d2).concat(" MB");
        }
        return decimalFormat.format(i).concat(" KB");
    }

    public void SingleShare() {
        try {
            Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(ImgPath));
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            startActivity(Intent.createChooser(intent, "Share Image"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShareFile(Context context, boolean z, String str) {
        Uri uriForFile;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        if (z) {
            intent.setType("Video/*");
        } else {
            intent.setType("image/*");
        }
        if (str.startsWith("content")) {
            uriForFile = Uri.parse(str);
        } else {
            uriForFile = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(str));
        }
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        context.startActivity(intent);
    }


    public class GetImageListAsync extends AsyncTask<Void, Void, Void> {
        @Override
        public Void doInBackground(Void... voidArr) {
            return null;
        }

        public GetImageListAsync() {
        }

        @Override
        protected void onPreExecute() {
            PreviewActivity.this.gettingData = true;
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            PreviewActivity.this.gettingData = false;
            if (PreviewActivity.this.viewPagerAdapter != null) {
                PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
            }
            AppUtilsClass.ScanImageAlbumListData(PreviewActivity.this);
        }
    }


    public class GetVideoListAsync extends AsyncTask<Void, Void, Void> {
        @Override
        public Void doInBackground(Void... voidArr) {
            return null;
        }

        public GetVideoListAsync() {
        }

        @Override
        protected void onPreExecute() {
            PreviewActivity.this.gettingData = true;
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            PreviewActivity.this.gettingData = false;
            if (PreviewActivity.this.viewPagerAdapter != null) {
                PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
            }
            AppUtilsClass.ScanVideoAlbumListData(PreviewActivity.this);
        }
    }

    public void deleteImage(String str) {
        File file = new File(str);
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
        this.currentPosition = this.currentPosition + (-1);
        this.viewPagerAdapter.notifyDataSetChanged();
    }

    public void ShowControl() {
        ll_bottomControl.setVisibility(0);
        this.rl_top.setVisibility(0);
        if (this.rl_op_main.getVisibility() == 0) {
            this.rl_op_main.setVisibility(8);
        }
    }

    public void HideControl() {
        ll_bottomControl.setVisibility(4);
        this.rl_top.setVisibility(4);
        this.rl_op_main.setVisibility(8);
    }

    @Override

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == this.REQUEST_ID_SET_AS_WALLPAPER && i2 == 1) {
            Toast.makeText(this, "Wallpaper set Successfully !!", 0).show();
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override 
    public void onBackPressed() {
        AdInterGD.getInstance().showInter(this, new AdInterGD.MyCallback() {
            @Override
            public void callbackCall() {
                Timer timer = PreviewActivity.this.swipeTimer;
                if (timer != null) {
                    timer.cancel();
                    PreviewActivity.this.swipeTimer.purge();
                    PreviewActivity.this.swipeTimer = null;
                }
                Dialog dialog = PreviewActivity.this.insideFileDialog;
                if (dialog == null || !dialog.isShowing()) {
                    Dialog dialog2 = PreviewActivity.this.albumDialog;
                    if (dialog2 == null || !dialog2.isShowing()) {
                        PreviewActivity.this.finish();
                        return;
                    } else {
                        PreviewActivity.this.albumDialog.dismiss();
                        return;
                    }
                }
                PreviewActivity.this.insideFileDialog.dismiss();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.pause();
        }
    }

    @Override
    
    public void onResume() {
        super.onResume();
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.start();
        }
        ViewPagerAdapter gMViewPagerAdapter = this.viewPagerAdapter;
        if (gMViewPagerAdapter != null) {
            gMViewPagerAdapter.notifyDataSetChanged();
        }
    }

    public void FolderDialog(ArrayList<FolderModel> arrayList, boolean z, boolean z2) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogStyle);
        this.dialog = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setContentView(R.layout.dg_custom_folder_list);
        this.dialog.setCancelable(false);
        this.dialog.setCanceledOnTouchOutside(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        RecyclerView recyclerView = (RecyclerView) this.dialog.findViewById(R.id.rcv_folderlist);
        recyclerView.setLayoutManager(linearLayoutManager);
        AccessFolder11 accessFolder11 = new AccessFolder11(this, arrayList, z, z2);
        this.accessFolder11 = accessFolder11;
        recyclerView.setAdapter(accessFolder11);
        this.dialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.70
            @Override
            public void onClick(View view) {
                PreviewActivity.this.dialog.dismiss();
            }
        });
        this.dialog.findViewById(R.id.txtAddNewFolder).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.71
            @Override
            public void onClick(View view) {
                PreviewActivity.this.AddNewFolderDialog();
            }
        });
        this.dialog.show();
    }


    public class AccessFolder11 extends RecyclerView.Adapter<AccessFolder11.MyViewHolder> {
        boolean abool;
        Context context;
        ArrayList<FolderModel> folderList;
        boolean frombool;

        public AccessFolder11(Context context, ArrayList<FolderModel> arrayList, boolean z, boolean z2) {
            this.folderList = new ArrayList<>();
            this.context = context;
            this.folderList = arrayList;
            this.abool = z;
            this.frombool = z2;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_select_folder, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
            myViewHolder.fileName.setText(this.folderList.get(i).foldername);
            myViewHolder.fullPath.setText(this.folderList.get(i).folderPath);
            myViewHolder.fullPath.setSelected(true);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.AccessFolder11.1
                @Override
                public void onClick(View view) {
                    PreviewActivity.this.dialog.dismiss();
                    if (AccessFolder11.this.frombool) {
                        String folderPath = AccessFolder11.this.folderList.get(i).getFolderPath();
                        folderPath.substring(folderPath.lastIndexOf("/") + 1);
                        PreviewActivity.FName = folderPath;
                        PreviewActivity.this.UnLockImage();
                        return;
                    }
                    PreviewActivity.this.CopyMoveData(AccessFolder11.this.folderList.get(i).getFolderPath(), AccessFolder11.this.abool);
                }
            });
        }

        @Override
        public int getItemCount() {
            ArrayList<FolderModel> arrayList = this.folderList;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        public void refreshData(ArrayList<FolderModel> arrayList) {
            this.folderList = arrayList;
            notifyDataSetChanged();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView fileName;
            TextView fullPath;

            public MyViewHolder(View view) {
                super(view);
                this.fileName =  view.findViewById(R.id.fileName);
                this.fullPath =  view.findViewById(R.id.fullPath);
            }
        }
    }

    public void AddNewFolderDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_add_folder);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ( (TextView)dialog.findViewById(R.id.txtTitle)).setText("Add New Folder");
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.72
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    PreviewActivity gMPreviewActivity = PreviewActivity.this;
                    gMPreviewActivity.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    File file = new File(PreviewActivity.this.folder_path);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else {
                        Toast.makeText(activity, "Already Exists", 0).show();
                    }
                    AppUtilsClass.getAccessFolder11();
                    if (PreviewActivity.this.accessFolder11 != null) {
                        PreviewActivity.this.accessFolder11.refreshData(AppUtilsClass.accessfolderListFor11);
                    }
                    dialog.dismiss();
                    return;
                }
                editText.setError("Please Enter Folder Name");
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.73
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void CopyMoveData(String str, boolean z) {
        File file = new File(ImgPath);
        File file2 = new File(str + File.separator + file.getName());
        new MediaScanners(GalleryAppClass.getInstance(), file2);
        try {
            FileUtils.CopyMoveFile(file, file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!z) {
            Toast.makeText(this, "Files Copy Successfully", 0).show();
            if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                AppUtilsClass.ScanImageAlbumListData(this);
            } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                AppUtilsClass.ScanVideoAlbumListData(this);
            }
            AppUtilsClass.RefreshMoment(this);
        }
        if (z) {
            if (this.mediaFileType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                this.progressTag = "FromPermanentImageDelete";
                this.toastTag = "MoveImageData";
                new DeleteImageExecute(ImgPath).execute(new Void[0]);
            } else if (this.mediaFileType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                this.progressTag = "FromPermanentVideoDelete";
                this.toastTag = "MoveVideoData";
                new DeleteVideoExecute(ImgPath).execute(new Void[0]);
            }
        }
    }


    public class DeleteImageExecute extends AsyncTask<Void, Void, Void> {
        String pathList;

        public DeleteImageExecute(String str) {
            PreviewActivity.this.progressPermanentImageDelete = new ProgressDialog(PreviewActivity.this);
            this.pathList = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            PreviewActivity.this.progressPermanentImageDelete.setMessage("Please wait a while...");
            PreviewActivity.this.progressPermanentImageDelete.setProgressStyle(0);
            PreviewActivity.this.progressPermanentImageDelete.setIndeterminate(false);
            PreviewActivity.this.progressPermanentImageDelete.setCancelable(false);
            PreviewActivity.this.progressPermanentImageDelete.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            if (PreviewActivity.allImagesList.size() == 1) {
                PreviewActivity.this.currentPosition = 0;
            } else if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                PreviewActivity.this.currentPosition--;
            }
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                MoMomentFragment.momentAdapter.RemoveItemSingle(this.pathList);
                PreviewActivity.allImagesList.remove(PreviewActivity.this.selectedPos);
            }
            if (Build.VERSION.SDK_INT < 30) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (Build.VERSION.SDK_INT < 30) {
                return null;
            }
            arrayList.add(ContentUris.withAppendedId(MediaStore.Images.Media.getContentUri("external"), AppUtilsClass.getFilePathToMediaID(this.pathList, PreviewActivity.this)));
            AppUtilsClass.requestIntent(arrayList, PreviewActivity.this);
            return null;
        }

        @Override
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            if (Build.VERSION.SDK_INT != 30) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.delpos = gMPreviewActivity.currentPosition;
                if (PreviewActivity.this.viewPagerAdapter != null) {
                    PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
                }
                new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.DeleteImageExecute.1
                    @Override
                    public void run() {
                        AppUtilsClass.RefreshImageAlbum(PreviewActivity.this);
                    }
                }, 500L);
                AppUtilsClass.RefreshMoment(PreviewActivity.this);
                PreviewActivity.this.progressPermanentImageDelete.dismiss();
                if (PreviewActivity.this.toastTag.equals("MoveImageData")) {
                    Toast.makeText(PreviewActivity.this, "Files Move Successfully.", 0).show();
                } else {
                    Toast.makeText(PreviewActivity.this, "Files are deleted successfully.", 0).show();
                }
                if (PreviewActivity.allImagesList.size() == 0) {
                    PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                    gMPreviewActivity2.setResult(-1, gMPreviewActivity2.getIntent());
                    PreviewActivity.this.finish();
                }
            }
        }
    }


    public class DeleteVideoExecute extends AsyncTask<Void, Void, Void> {
        String pathList;

        public DeleteVideoExecute(String str) {
            PreviewActivity.this.progressPermanentVideoDelete = new ProgressDialog(PreviewActivity.this);
            this.pathList = str;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            PreviewActivity.this.progressPermanentVideoDelete.setMessage("Please wait a while...");
            PreviewActivity.this.progressPermanentVideoDelete.setProgressStyle(0);
            PreviewActivity.this.progressPermanentVideoDelete.setIndeterminate(false);
            PreviewActivity.this.progressPermanentVideoDelete.setCancelable(false);
            PreviewActivity.this.progressPermanentVideoDelete.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            if (PreviewActivity.allImagesList.size() == 1) {
                PreviewActivity.this.currentPosition = 0;
            } else if (PreviewActivity.this.currentPosition == PreviewActivity.allImagesList.size()) {
                PreviewActivity.this.currentPosition--;
            }
            if (PreviewActivity.this.activityName.equals("MomentFragment")) {
                MoMomentFragment.momentAdapter.RemoveItemSingle(this.pathList);
                PreviewActivity.allImagesList.remove(PreviewActivity.this.selectedPos);
            }
            if (Build.VERSION.SDK_INT < 30) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (Build.VERSION.SDK_INT < 30) {
                return null;
            }
            arrayList.add(ContentUris.withAppendedId(MediaStore.Video.Media.getContentUri("external"), AppUtilsClass.getFilePathToMediaID(this.pathList, PreviewActivity.this)));
            AppUtilsClass.requestIntent(arrayList, PreviewActivity.this);
            return null;
        }

        @Override
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            if (Build.VERSION.SDK_INT != 30) {
                PreviewActivity gMPreviewActivity = PreviewActivity.this;
                gMPreviewActivity.delpos = gMPreviewActivity.currentPosition;
                if (PreviewActivity.this.viewPagerAdapter != null) {
                    PreviewActivity.this.viewPagerAdapter.notifyDataSetChanged();
                }
                new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.DeleteVideoExecute.1
                    @Override
                    public void run() {
                        AppUtilsClass.RefreshVideoAlbum(PreviewActivity.this);
                    }
                }, 500L);
                AppUtilsClass.RefreshMoment(PreviewActivity.this);
                PreviewActivity.this.progressPermanentVideoDelete.dismiss();
                if (PreviewActivity.this.toastTag.equals("MoveVideoData")) {
                    Toast.makeText(PreviewActivity.this, "Files Move Successfully.", 0).show();
                } else {
                    Toast.makeText(PreviewActivity.this, "Files are deleted successfully.", 0).show();
                }
                if (PreviewActivity.allImagesList.size() == 0) {
                    PreviewActivity gMPreviewActivity2 = PreviewActivity.this;
                    gMPreviewActivity2.setResult(-1, gMPreviewActivity2.getIntent());
                    PreviewActivity.this.finish();
                }
            }
        }
    }

    public void DeleteImageExecute() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        DocumentFile fromSingleUri = DocumentFile.fromSingleUri(this, Uri.parse(ImgPath));
        if (fromSingleUri.exists() && fromSingleUri.delete()) {
            arrayList.add(ImgPath);
            z = true;
        } else {
            z = false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            allImagesList.remove(it.next());
        }
        int i = this.currentPosition;
        if (i == allImagesList.size()) {
            int i2 = this.currentPosition - 1;
            this.currentPosition = i2;
            allImagesList.remove(i2);
            i = 0;
        } else {
            allImagesList.remove(this.currentPosition);
        }
        ViewPagerAdapter gMViewPagerAdapter = new ViewPagerAdapter(activity, allImagesList);
        this.viewPagerAdapter = gMViewPagerAdapter;
        viewPager.setAdapter(gMViewPagerAdapter);
        viewPager.setCurrentItem(i);
        if (!z) {
            Toast.makeText(this, "Couldn't delete some files.", 0).show();
        } else if (z) {
            Toast.makeText(this, "Files are deleted successfully.", 0).show();
        }
    }

    public void UnLockImage() {
        if (ImgPath.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (DocumentFile.fromSingleUri(this, Uri.parse(ImgPath)).exists() && DownloadForUnlock(this, ImgPath)) {
            arrayList.add(ImgPath);
        }
        runOnUiThread(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMPreviewActivity.74
            @Override
            public void run() {
                try {
                    PreviewActivity.this.TempDeleteImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.viewPagerAdapter.notifyDataSetChanged();
    }

    public boolean DownloadForUnlock(Context context, String str) {
        return copyFileInSavedDirForUnlock(context, str);
    }

    static boolean copyFileInSavedDirForUnlock(Context context, String str) {
        Uri fromFile = Uri.fromFile(new File(FName + File.separator + new File(str).getName()));
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
            OutputStream openOutputStream = context.getContentResolver().openOutputStream(fromFile, "w");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read > 0) {
                    openOutputStream.write(bArr, 0, read);
                } else {
                    openInputStream.close();
                    openOutputStream.flush();
                    openOutputStream.close();
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent.setData(fromFile);
                    context.sendBroadcast(intent);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void TempDeleteImage() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        DocumentFile fromSingleUri = DocumentFile.fromSingleUri(this, Uri.parse(ImgPath));
        if (fromSingleUri.exists() && fromSingleUri.delete()) {
            arrayList.add(ImgPath);
            z = true;
        } else {
            z = false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            allImagesList.remove(it.next());
        }
        int i = this.currentPosition;
        if (i == allImagesList.size()) {
            int i2 = this.currentPosition - 1;
            this.currentPosition = i2;
            allImagesList.remove(i2);
            i = 0;
        } else {
            allImagesList.remove(this.currentPosition);
        }
        ViewPagerAdapter gMViewPagerAdapter = new ViewPagerAdapter(activity, allImagesList);
        this.viewPagerAdapter = gMViewPagerAdapter;
        viewPager.setAdapter(gMViewPagerAdapter);
        viewPager.setCurrentItem(i);
        if (!z) {
            Toast.makeText(this, "Couldn't delete some files.", 0).show();
        } else if (z) {
            Toast.makeText(this, "Files are deleted successfully.", 0).show();
        }
        if (Build.VERSION.SDK_INT >= 30) {
            AppUtilsClass.RefreshMoment(this);
        }
    }
}
