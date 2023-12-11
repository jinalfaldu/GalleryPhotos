package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.MoMomentFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.PrPrivateMainFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Garlic_SaveImageUtils.Onion_ImageLoader;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.CoCollageFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.DuDuplicateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.HoHomeFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImageFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImagePrivateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoPrivateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.ImageAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.MomentData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData.VideoAlbumData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.AlbumSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideImageFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideVideoFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingFolderDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingPrivateImageDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingVideoPrivateDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;

import unified.vpn.sdk.ResponseResultCodes;
import vidgallery.infideap.drawerbehavior.AdvanceDrawerLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;


public class Degu_MainActivity extends BseActivity {
    public static ArrayList<Object> ArrayImage11 = null;
    public static ArrayList<Object> ArrayVideo11 = null;
    public static final String WA_TREE_URI = "wa_tree_uri";
    public static Activity activity = null;
    public static ImageView addImageAlbum = null;
    public static ImageView addMomentAlbum = null;
    public static ImageView addVideoAlbum = null;
    public static int albumDivider = 0;
    public static File androidpath = null;
    public static boolean checkLockForFirstFile = false;
    public static Context context = null;
    public static int dataDivider = 0;
    public static int fragclick = 0;
    public static ImageAlbumData getImageAlbumData = null;
    public static MomentData getMomentData = null;
    public static VideoAlbumData getVideoAlbumData = null;
    public static ImageView ivAddAlbum = null;
    public static ImageView ivDelete = null;
    public static ImageView ivSelectAll = null;
    public static ImageView ivShare = null;
    public static ImageView ivSort = null;
    public static ImageView ivUnLock = null;
    private static SharedPreferences mPreferences = null;
    public static String oriTag = "";
    public static int parameter;
    public static PrefClass preferenceAlbumUtils;
    public static PrefClass preferenceDataUtils;
    public static PrefClass preferenceForTrash11;
    public static int privateclick;
    public static RelativeLayout rl_imageBtn;
    public static RelativeLayout rl_videoBtn;
    public static ImageView sortingImageList;
    public static ImageView sortingVideoList;
    public static Toolbar toolbar;
    public static TextView txtTitle;
    Fragment activeFragment;
    private AlbumSortingListener albumSortingListener;
    CoCollageFragment collageFragment;
    ArrayList<Object> deleteImageList;
    ArrayList<Object> deleteVideoList;
    Dialog dialogExit;
    AdvanceDrawerLayout drawerlayout;
    DuDuplicateFragment duplicateFragment;
    FragmentManager fragmentManager;
    private HideImageFolderDataSortingListener hideImageFolderDataSortingListener;
    HoHomeFragment homeFragment;
    ImImageFragment imageFragment;
    ImageView imgOpenDrawer;
    LinearLayout llMoments;
    LinearLayout llOther;
    LinearLayout llPhotos;
    LinearLayout llVideos;
    MoMomentFragment momentFragment;
    PrPrivateMainFragment privateMainFragment;
    ProgressDialog progressDialog;
    RelativeLayout rl_Collage;
    RelativeLayout rl_Duplicate;
    RelativeLayout rl_Home;
    RelativeLayout rl_dataLayout;
    public static RelativeLayout rl_mainToolbar;
    RelativeLayout rl_tab_btn;
    private HideVideoFolderDataSortingListener sortingHideVideoCallBack;
    TextView txtMoments;
    TextView txtOther;
    TextView txtPhotos;
    TextView txtVideos;
    TextView txt_Collage;
    TextView txt_Duplicate;
    TextView txt_Home;
    TextView txt_Status;
    TextView txt_image_size;
    TextView txt_video_size;
    Typeface typeface;
    ViVideoFragment videoFragment;
    View view;
    ImageView imgHome, imgCollage, imgDuplicate;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context2, Intent intent) {
            Degu_MainActivity gMMainActivity = Degu_MainActivity.this;
            gMMainActivity.unregisterReceiver(gMMainActivity.broadcastReceiver);
            if (Common.IdentifyActivity.equals("PasswordChangeActivity")) {
                if (Degu_MainActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                    Degu_MainActivity.this.startActivity(new Intent(Degu_MainActivity.this, SecurityQAActivity.class));
                } else {
                    Intent intent2 = new Intent(Degu_MainActivity.this, Degu_PasswordChangeActivity.class);
                    intent2.putExtra(Common.gActivityname, "MainActivity");
                    Degu_MainActivity.this.startActivity(intent2);
                }
                Common.IdentifyActivity = "";
            }
        }
    };
    private String folder_path = "";
    int REQUEST_ACTION_OPEN_DOCUMENT_TREE = 101;
    LinkedHashMap<String, ArrayList<DataFileModel>> photosDataHashMap = new LinkedHashMap<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gm_main);
        context = getApplicationContext();
        activity = this;
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        this.view = findViewById(R.id.layout_album_select);
        ExitDialog();
        this.rl_tab_btn = (RelativeLayout) findViewById(R.id.rl_tab_btn);
        this.rl_dataLayout = (RelativeLayout) findViewById(R.id.rl_dataLayout);
        File file = new File(getExternalFilesDir(getString(R.string.app_name)).getAbsolutePath());
        androidpath = file;
        if (!file.exists()) {
            androidpath.mkdirs();
        }
        preferenceAlbumUtils = new PrefClass(this);
        preferenceDataUtils = new PrefClass(this);
        preferenceForTrash11 = new PrefClass(this);
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.progressDialog = progressDialog;
        progressDialog.setMessage("Recover File..");
        this.progressDialog.setCancelable(false);
        this.typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        if (this.preferenceClass.getInt(Common.gIsLockStatus, 0) != 0) {
            this.preferenceClass.putInt(Common.gIsLockStatus, 1);
        }
        DrawerListener();
        SetToolBar();
        this.rl_tab_btn.setVisibility(0);
        this.rl_dataLayout.setVisibility(0);
        ID_Binding();
        sortingBothDataCallBack();
        sortingPrivateImageCallBack();
        sortingPrivateVideoCallBack();
        GetAutoDeleteData();
    }

    public static void ID_Binding(Activity activity2) {
        MomentData gMGetMomentData = new MomentData(activity2);
        getMomentData = gMGetMomentData;
        gMGetMomentData.getMomentAllAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        ImageAlbumData gMGetImageAlbumData = new ImageAlbumData(activity2);
        getImageAlbumData = gMGetImageAlbumData;
        gMGetImageAlbumData.getImageAlbumAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        VideoAlbumData gMGetVideoAlbumData = new VideoAlbumData(activity2);
        getVideoAlbumData = gMGetVideoAlbumData;
        gMGetVideoAlbumData.getVideoAlbumAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void SetBothDataSize() {
        txt_image_size = (TextView) findViewById(R.id.txt_image_size);
        this.txt_video_size = (TextView) findViewById(R.id.txt_video_size);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Degu_MainActivity.this.txt_image_size.setText(String.valueOf(ConstantArrayClass.arrayImageSize.size()));
                Degu_MainActivity.this.txt_video_size.setText(String.valueOf(ConstantArrayClass.arrayVideoSize.size()));
            }
        }, 500L);
    }

    private void SetToolBar() {
//        this.rl_mainToolbar = (RelativeLayout) findViewById(R.id.rl_mainToolbar);
        TextView textView = (TextView) findViewById(R.id.tootbatTitle);
        txtTitle = textView;
        textView.setText("Gallery");
        ivSelectAll = (ImageView) findViewById(R.id.btnSelectAll);
        ivUnLock = (ImageView) findViewById(R.id.btnUnLock);
        ivDelete = (ImageView) findViewById(R.id.btnDelete);
        ivShare = (ImageView) findViewById(R.id.btnShare);
        ImageView imageView = (ImageView) findViewById(R.id.opendrawer);
        this.imgOpenDrawer = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Degu_MainActivity.this.drawerlayout.openDrawer(GravityCompat.START);
            }
        });
        ImageView imageView2 = (ImageView) findViewById(R.id.btnAddAlbum);
        ivAddAlbum = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Degu_MainActivity.fragclick == 0) {
                    Degu_MainActivity.this.AddImageFolderDialog();
                } else if (Degu_MainActivity.fragclick == 1) {
                    Degu_MainActivity.this.AddVideoFolderDialog();
                }
            }
        });
        ImageView imageView3 = (ImageView) findViewById(R.id.btnSortAlbum);
        ivSort = imageView3;
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Degu_MainActivity.this.SortingData();
            }
        });
    }

    private void DrawerListener() {
        AdvanceDrawerLayout advanceDrawerLayout = (AdvanceDrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerlayout = advanceDrawerLayout;
        advanceDrawerLayout.setViewScale(GravityCompat.START, 0.9f);
        this.drawerlayout.setViewElevation(GravityCompat.START, 20.0f);
        this.drawerlayout.setViewScrimColor(GravityCompat.START, 0);
        this.drawerlayout.setDrawerElevation(GravityCompat.START, 0.0f);
        this.drawerlayout.setRadius(GravityCompat.START, 20.0f);
        this.drawerlayout.setScrimColor(getResources().getColor(17170445));
        this.drawerlayout.setDrawerLockMode(1);
        findViewById(R.id.ll_Delete).setVisibility(0);
        findViewById(R.id.ll_Home).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Degu_MainActivity.this.drawerlayout.closeDrawer(GravityCompat.START);
            }
        });
        findViewById(R.id.ll_Setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Degu_MainActivity.this.drawerlayout.closeDrawer(GravityCompat.START);
                Degu_MainActivity.this.startActivity(new Intent(Degu_MainActivity.this, SettingActivity.class));
            }
        });
        findViewById(R.id.ll_Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Degu_MainActivity.this.drawerlayout.closeDrawer(GravityCompat.START);
                Degu_MainActivity.this.startActivity(new Intent(Degu_MainActivity.this, Degu_DeleteTrashActivity.class));
                Degu_MainActivity.preferenceForTrash11.putInt("getTrashDataFor11", 786);
            }
        });
        findViewById(R.id.ll_Private).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Degu_MainActivity.this.drawerlayout.closeDrawer(GravityCompat.START);
                Degu_MainActivity.this.startActivity(new Intent(Degu_MainActivity.this, DeguPrivateActivity.class));

            }
        });
//        findViewById(R.id.ll_SSaver).setOnClickListener(new View.OnClickListener() {
//            @Override // android.view.View.OnClickListener
//            public void onClick(View view) {
//                Degu_MainActivity.this.drawerlayout.closeDrawer(GravityCompat.START);
//                Degu_MainActivity.this.startActivity(new Intent(Degu_MainActivity.this, SMMainWPActivity.class));
//            }
//        });
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.flContent, fragment);
        beginTransaction.commit();
    }

    private void ID_Binding() {
        rl_imageBtn = (RelativeLayout) findViewById(R.id.rl_imageBtn);
        rl_videoBtn = (RelativeLayout) findViewById(R.id.rl_videoBtn);
        addMomentAlbum = (ImageView) findViewById(R.id.addMomentAlbum);
        addImageAlbum = (ImageView) findViewById(R.id.addImageAlbum);
        sortingImageList = (ImageView) findViewById(R.id.sortingImageList);
        addVideoAlbum = (ImageView) findViewById(R.id.addVideoAlbum);
        sortingVideoList = (ImageView) findViewById(R.id.sortingVideoList);
        this.llPhotos = (LinearLayout) findViewById(R.id.layPhotos);
        this.llVideos = (LinearLayout) findViewById(R.id.layVideos);
        this.llMoments = (LinearLayout) findViewById(R.id.layMoments);
        this.llOther = (LinearLayout) findViewById(R.id.layOther);
        this.txtPhotos = (TextView) findViewById(R.id.txtPhotos);
        this.txtVideos = (TextView) findViewById(R.id.txtVideos);
        this.txtMoments = (TextView) findViewById(R.id.txtMoments);
        this.txtOther = (TextView) findViewById(R.id.txtOther);
        this.rl_Home = (RelativeLayout) findViewById(R.id.rl_Home);
        this.rl_Collage = (RelativeLayout) findViewById(R.id.rl_Collage);
        this.rl_Duplicate = (RelativeLayout) findViewById(R.id.rl_Duplicate);
        this.txt_Home = (TextView) findViewById(R.id.txt_Home);
        this.txt_Status = (TextView) findViewById(R.id.txt_Status);
        this.txt_Collage = (TextView) findViewById(R.id.txt_Collage);
        this.imgHome = findViewById(R.id.imgHome);
        this.imgCollage = findViewById(R.id.imgCollage);
        this.imgDuplicate = findViewById(R.id.imgDuplicate);
        this.homeFragment = new HoHomeFragment();
//        this.wpMainFragment = new GMWPMainFragment();
        this.collageFragment = new CoCollageFragment();
        this.duplicateFragment = new DuDuplicateFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.fragmentManager = supportFragmentManager;
        supportFragmentManager.beginTransaction().add(R.id.flContent, this.duplicateFragment, "4").hide(this.duplicateFragment).commitAllowingStateLoss();
        this.fragmentManager.beginTransaction().add(R.id.flContent, this.collageFragment, ExifInterface.GPS_MEASUREMENT_3D).hide(this.collageFragment).commitAllowingStateLoss();
//        this.fragmentManager.beginTransaction().add(R.id.flContent, this.wpMainFragment, ExifInterface.GPS_MEASUREMENT_2D).hide(this.wpMainFragment).commitAllowingStateLoss();
        this.fragmentManager.beginTransaction().add(R.id.flContent, this.homeFragment, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).commitAllowingStateLoss();
        this.activeFragment = this.homeFragment;
        this.fragmentManager.beginTransaction().hide(this.activeFragment).show(this.homeFragment).commitAllowingStateLoss();
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager != null && !fragmentManager.isStateSaved()) {
            this.fragmentManager.popBackStackImmediate();
        }
        this.rl_Home.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Degu_MainActivity.DeleteCache(Degu_MainActivity.this);
//                GMMainActivity.this.rl_mainToolbar.setVisibility(View.VISIBLE);
                txtTitle.setText("Gallery");

                imgHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_select));
                imgCollage.setImageDrawable(getResources().getDrawable(R.drawable.ic_collage_unselected));
                imgDuplicate.setImageDrawable(getResources().getDrawable(R.drawable.ic_duplicate_unselected));
//                GMMainActivity.this.txt_Home.setVisibility(8);
//                GMMainActivity.this.txt_Status.setVisibility(0);
//                GMMainActivity.this.txt_Collage.setVisibility(0);
//                GMMainActivity.this.txt_Duplicate.setVisibility(0);
                Degu_MainActivity.this.fragmentManager.beginTransaction().hide(Degu_MainActivity.this.activeFragment).show(Degu_MainActivity.this.homeFragment).commitAllowingStateLoss();
                Degu_MainActivity.this.fragmentManager.popBackStackImmediate();
                Degu_MainActivity gMMainActivity = Degu_MainActivity.this;
                gMMainActivity.activeFragment = gMMainActivity.homeFragment;

            }
        });

        this.rl_Collage.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Degu_MainActivity.DeleteCache(Degu_MainActivity.this);
//                GMMainActivity.this.rl_mainToolbar.setVisibility(View.GONE);
                txtTitle.setText("Collage Maker");
                addMomentAlbum.setVisibility(View.GONE);
                imgHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_unselected));
                imgCollage.setImageDrawable(getResources().getDrawable(R.drawable.ic_collage_select));
                imgDuplicate.setImageDrawable(getResources().getDrawable(R.drawable.ic_duplicate_unselected));
//                GMMainActivity.this.txt_Home.setVisibility(0);
//                GMMainActivity.this.txt_Status.setVisibility(0);
//                GMMainActivity.this.txt_Collage.setVisibility(8);
//                GMMainActivity.this.txt_Duplicate.setVisibility(0);
                Degu_MainActivity.this.fragmentManager.beginTransaction().hide(Degu_MainActivity.this.activeFragment).show(Degu_MainActivity.this.collageFragment).commitAllowingStateLoss();
                Degu_MainActivity.this.fragmentManager.popBackStackImmediate();
                Degu_MainActivity gMMainActivity = Degu_MainActivity.this;
                gMMainActivity.activeFragment = gMMainActivity.collageFragment;
            }
        });
        this.rl_Duplicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Degu_MainActivity.DeleteCache(Degu_MainActivity.this);
//                GMMainActivity.this.rl_mainToolbar.setVisibility(View.GONE);
                txtTitle.setText("Duplicate Data");
                addMomentAlbum.setVisibility(View.GONE);
                imgHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_unselected));
                imgCollage.setImageDrawable(getResources().getDrawable(R.drawable.ic_collage_unselected));
                imgDuplicate.setImageDrawable(getResources().getDrawable(R.drawable.ic_duplicate_select));
//                GMMainActivity.this.txt_Home.setVisibility(0);
//                GMMainActivity.this.txt_Status.setVisibility(0);
//                GMMainActivity.this.txt_Collage.setVisibility(0);
//                GMMainActivity.this.txt_Duplicate.setVisibility(8);
                Degu_MainActivity.this.fragmentManager.beginTransaction().hide(Degu_MainActivity.this.activeFragment).show(Degu_MainActivity.this.duplicateFragment).commitAllowingStateLoss();
                Degu_MainActivity.this.fragmentManager.popBackStackImmediate();
                Degu_MainActivity gMMainActivity = Degu_MainActivity.this;
                gMMainActivity.activeFragment = gMMainActivity.duplicateFragment;

            }
        });
    }

    @Override // androidx.activity.Component
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            back();
        }
    }

    private void back() {
        final Dialog dialog = new Dialog(this, 16973841);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.exitrateus_dialoag);
        dialog.findViewById(R.id.starbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + Degu_MainActivity.this.getPackageName()));
                intent.addFlags(1208483840);
                try {
                    Degu_MainActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Degu_MainActivity gMMainActivity = Degu_MainActivity.this;
                    gMMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + Degu_MainActivity.this.getPackageName())));
                }
            }
        });
        dialog.findViewById(R.id.Canclebackbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finishAffinity();

            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_sort) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void AddImageFolderDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_add_folder);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Add Image Album");
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    Degu_MainActivity gMMainActivity = Degu_MainActivity.this;
                    gMMainActivity.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    Intent intent = new Intent(Degu_MainActivity.activity, Degu_ImagesActivity.class);
                    intent.putExtra(Common.gAlbumPath, Degu_MainActivity.this.folder_path);
                    Degu_MainActivity.activity.startActivityForResult(intent, 100);
                    dialog.dismiss();
                }
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.REQUEST_ACTION_OPEN_DOCUMENT_TREE && i2 == -1) {
            Uri data = intent.getData();
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    getContentResolver().takePersistableUriPermission(data, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(i, i2, intent);
        }
    }

    public void AddVideoFolderDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_add_folder);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Add Video Album");
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    Degu_MainActivity gMMainActivity = Degu_MainActivity.this;
                    gMMainActivity.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    Intent intent = new Intent(Degu_MainActivity.this, VideosActivity.class);
                    intent.putExtra(Common.gAlbumPath, Degu_MainActivity.this.folder_path);
                    Degu_MainActivity.this.startActivityForResult(intent, 100);
                    dialog.dismiss();
                }
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void sortingBothDataCallBack() {
        this.albumSortingListener = new onCallbackReceive();
    }

    public void SortingData() {
        int i = fragclick;
        if (i == 0) {
            new SortingFolderDialog(activity, ConstantArrayClass.imageAlbumsList, this.albumSortingListener).ShowSortingDialogue();
        } else if (i == 1) {
            new SortingFolderDialog(activity, ConstantArrayClass.videoAlbumsList, this.albumSortingListener).ShowSortingDialogue();
        } else if (i == 3) {
            int i2 = privateclick;
            if (i2 == 0) {
                int i3 = this.preferenceClass.getInt(Common.gIsLockStatus, 0);
                if (i3 == 0) {
                    Toast.makeText(context, "Please Set Security!!", 0).show();
                } else if (i3 == 1) {
                    Toast.makeText(context, "Please Enter Passcode!!", 0).show();
                } else if (ImImagePrivateFragment.privateImageList.size() >= 1) {
                    new SortingPrivateImageDialog(activity, ImImagePrivateFragment.privateImageList, this.hideImageFolderDataSortingListener).ShowSortingDialogue();
                } else {
                    Toast.makeText(context, "No Photos Found!!", Toast.LENGTH_SHORT).show();
                }
            } else if (i2 == 1) {
                int i4 = this.preferenceClass.getInt(Common.gIsLockStatus, 0);
                if (i4 == 0) {
                    Toast.makeText(context, "Please Set Security!!", Toast.LENGTH_SHORT).show();
                } else if (i4 == 1) {
                    Toast.makeText(context, "Please Enter Passcode!!", Toast.LENGTH_SHORT).show();
                } else if (ViVideoPrivateFragment.privateVideoList.size() >= 1) {
                    new SortingVideoPrivateDialog(activity, ViVideoPrivateFragment.privateVideoList, this.sortingHideVideoCallBack).ShowSortingDialogue();
                } else {
                    Toast.makeText(context, "No Videos Found!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public class onCallbackReceive implements AlbumSortingListener {
        onCallbackReceive() {
        }

        @Override
        public void Sorting(ArrayList<FolderModel> arrayList) {
            if (Degu_MainActivity.fragclick == 0) {
                ImImageFragment.imageFolderAdapter.notifyDataSetChanged();
                Toast.makeText(Degu_MainActivity.this, "Image Sorting Done", 0).show();
            } else if (Degu_MainActivity.fragclick == 1) {
                ViVideoFragment.videoFolderAdapter.notifyDataSetChanged();
                Toast.makeText(Degu_MainActivity.this, "Video Sorting Done", 0).show();
            }
        }
    }

    private void sortingPrivateImageCallBack() {
        this.hideImageFolderDataSortingListener = new sortListenerHideImageFolderData();
    }

    public class sortListenerHideImageFolderData implements HideImageFolderDataSortingListener {
        sortListenerHideImageFolderData() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ImImagePrivateFragment.privateImageAdapter.notifyDataSetChanged();
            Toast.makeText(Degu_MainActivity.this, "Hide Image Sorting Done", 0).show();
        }
    }

    private void sortingPrivateVideoCallBack() {
        this.sortingHideVideoCallBack = new sortCallBack();
    }

    public class sortCallBack implements HideVideoFolderDataSortingListener {
        sortCallBack() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ViVideoPrivateFragment.privateVideoAdapter.notifyDataSetChanged();
            Toast.makeText(Degu_MainActivity.this, "Private Video Sorting Done", 0).show();
        }
    }

    public void ElevenPermission() {
        Intent intent;
        StorageManager storageManager = (StorageManager) getSystemService("storage");
        String GetPrivateFolder = GetPrivateFolder();
        if (Build.VERSION.SDK_INT >= 29) {
            intent = storageManager.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
            String replace = intent.getParcelableExtra("android.provider.extra.INITIAL_URI").toString().replace("/root/", "/document/");
            intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse(replace + "%3ADCIM%2FGalleries%2F.PhotoGallery1%2FPrivateImage"));
        } else {
            intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse("content://com.android.externalstorage.documents/document/primary%3A" + GetPrivateFolder));
        }
        intent.addFlags(2);
        intent.addFlags(1);
        intent.addFlags(128);
        intent.addFlags(64);
        activity.startActivityForResult(intent, this.REQUEST_ACTION_OPEN_DOCUMENT_TREE);
    }

    public static boolean CheckAppInstall(Context context2, String str) {
        try {
            context2.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public String GetPrivateFolder() {
        new File(Environment.DIRECTORY_DCIM + File.separator + "Galleries" + File.separator + ".PhotoGallery1" + File.separator + "PrivateImage").isDirectory();
        return "DCIM%2FGalleries%2F.PhotoGallery1%2FPrivateImage";
    }

    private void showMessageOKCancel(String str, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(this).setMessage(str).setPositiveButton(ResponseResultCodes.OK, onClickListener).create().show();
    }

    public void PermissionDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_permission);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.textDesc)).setText(Html.fromHtml("Please choose the directory <b><font color='#FF0000'>(DCIM/Galleries/.PhotoGallery1/PrivateImage)</font></b> to grant write access to operate"));
        dialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.txtOpen).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
                Degu_MainActivity.this.ElevenPermission();
            }
        });
        dialog.show();
    }

    public static void setWATree(Context context2, String str) {
        getInstance(context2).edit().putString(WA_TREE_URI, str).apply();
    }

    public static String getWATree(Context context2) {
        return getInstance(context2).getString(WA_TREE_URI, "");
    }

    private static SharedPreferences getInstance(Context context2) {
        if (mPreferences == null) {
            mPreferences = context2.getApplicationContext().getSharedPreferences("wa_data", 0);
        }
        return mPreferences;
    }

    private void ExitDialog() {
        Dialog dialog = new Dialog(this);
        this.dialogExit = dialog;
        dialog.setContentView(getLayoutInflater().inflate(R.layout.dialog_exit, (ViewGroup) null));
        this.dialogExit.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialogExit.setCancelable(false);
        this.dialogExit.setCanceledOnTouchOutside(false);
        this.dialogExit.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Degu_MainActivity.this.dialogExit.dismiss();
            }
        });
        this.dialogExit.findViewById(R.id.txtExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Degu_MainActivity.this.dialogExit.dismiss();
                Degu_MainActivity.this.finishAffinity();
            }
        });
    }

    private void ShowExitDialog() {
        if (this.dialogExit.isShowing()) {
            return;
        }
        this.dialogExit.show();
    }

    public static void DeleteCache(Context context2) {
        try {
            DeleteDir(context2.getCacheDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean DeleteDir(File file) {
        if (file != null && file.isDirectory()) {
            for (String str : file.list()) {
                if (!DeleteDir(new File(file, str))) {
                    return false;
                }
            }
            return file.delete();
        } else if (file == null || !file.isFile()) {
            return false;
        } else {
            return file.delete();
        }
    }

    public String getPathForKitKat(Uri uri) {
        Uri uri2 = null;
        if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(this, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (Onion_ImageLoader.isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(this, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (Onion_ImageLoader.isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + File.separator + split[1];
            }
        } else if (Onion_ImageLoader.isDownloadsDocument(uri)) {
            return getDataColumn(this, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else {
            if (Onion_ImageLoader.isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if (MimeTypes.BASE_TYPE_VIDEO.equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (MimeTypes.BASE_TYPE_AUDIO.equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(this, uri2, "_id=?", new String[]{split2[1]});
            }
        }
        return null;
    }

    public String getDataColumn(Context context2, Uri uri, String str, String[] strArr) {
        try {
            Cursor query = context2.getContentResolver().query(uri, new String[]{SMUtilsWP.mediaPath}, str, strArr, null);
            if (query == null || !query.moveToFirst()) {
                if (query == null) {
                    return null;
                }
                query.close();
                return null;
            }
            DatabaseUtils.dumpCursor(query);
            String string = query.getString(query.getColumnIndexOrThrow(SMUtilsWP.mediaPath));
            if (query == null) {
                return string;
            }
            query.close();
            return string;
        } catch (Throwable unused) {
            return SMUtilsWP.mediaPath;
        }
    }

    public void GetAutoDeleteData() {
        this.deleteImageList = new ArrayList<>();
        this.deleteVideoList = new ArrayList<>();
        try {
            this.deleteImageList = AppUtilsClass.GetDeletedImageFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.deleteVideoList = AppUtilsClass.GetDeletedVideoFiles();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        ArrayList<Object> arrayList = this.deleteImageList;
        if (arrayList != null && arrayList.size() != 0) {
            OldImageDeleteAfter60Days();
        }
        ArrayList<Object> arrayList2 = this.deleteVideoList;
        if (arrayList2 == null || arrayList2.size() == 0) {
            return;
        }
        OldVideoDeleteAfter60Days();
    }

    private void OldImageDeleteAfter60Days() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < this.deleteImageList.size(); i++) {
            File file = new File(((DataFileModel) this.deleteImageList.get(i)).path);
            if (timeInMillis - file.lastModified() > 5184000000L) {
                file.delete();
            }
        }
    }

    private void OldVideoDeleteAfter60Days() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < this.deleteVideoList.size(); i++) {
            File file = new File(((DataFileModel) this.deleteVideoList.get(i)).path);
            if (timeInMillis - file.lastModified() > 5184000000L) {
                file.delete();
            }
        }
    }

    public void ScanBothDataSize(Activity activity2) {
        this.photosDataHashMap = new LinkedHashMap<>();
        if (ConstantArrayClass.arrayImageSize != null) {
            ConstantArrayClass.arrayImageSize.clear();
        }
        if (ConstantArrayClass.arrayVideoSize != null) {
            ConstantArrayClass.arrayVideoSize.clear();
        }
        Uri contentUri = MediaStore.Files.getContentUri("external");
        String[] strArr = {"_id", SMUtilsWP.mediaPath, "media_type"};
        Cursor query = activity2.getContentResolver().query(contentUri, strArr, "media_type=1 OR media_type=3", null, null);
        if (query != null) {
            query.getCount();
            while (query.moveToNext()) {
                query.getString(query.getColumnIndexOrThrow(strArr[0]));
                String string = query.getString(query.getColumnIndexOrThrow(strArr[1]));
                if (string == null) {
                    string = "";
                }
                String string2 = query.getString(query.getColumnIndexOrThrow(strArr[2]));
                if (string2.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    ConstantArrayClass.arrayImageSize.add(string);
                } else if (string2.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    ConstantArrayClass.arrayVideoSize.add(string);
                }
            }
            query.close();
        }
        SetBothDataSize();
    }
}
