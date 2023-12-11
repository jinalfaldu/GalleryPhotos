package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.ExternalStorageHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdActivity.EdEditImageActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.AlbumAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.ImageFolderAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.BothAlbumSelectAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.MoMomentFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.SortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DialogAlbumModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.LoginPreferenceUtilsData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingDataDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;


public class Degu_ImageSelectActivity extends BseActivity {
    private static String TAG = "ImageSelectActivity";
    public static Degu_ImageSelectActivity imageSelectActivity;
    public static BothAlbumSelectAdapter innerBothAlbumSelectAdapter;
    public static ActionMode mActionMode;
    public static ArrayList<Object> myImageinsideList = new ArrayList<>();
    public static ArrayList<Object> sendImgList;
    boolean Lock_AllowMultiple;
    AccessFolder11 accessFolder11;
    String album;
    Dialog albumDialog;
    GridView albumGridView;
    GridView albumImageGridView;
    BottomSheetDialog btmsDialog;
    Button btnDone;
    ImageView btnSearch;
    ImageView btnSearchClose;
    AlbumAdapter dialogAlbumAdapter;
    ImageFolderAdapter dialogAlbumImageAdapter;
    GridView gridViewImage;
    int i2;
    ImageView imgBack;
    ImageView imgCopy;
    ImageView imgDelete;
    ImageView imgEdit;
    ImageView imgMove;
    ImageView imgRename;
    ImageView imgShare;
    ImageView imgSort;
    Dialog insideFileDialog;
    ProgressBar loader;
    private Menu mMenu;
    PrefClass preferenceClass;
    ProgressDialog progressDelete;
    ProgressDialog progressLock;
    ProgressDialog progressPermanentDelete;
    String progressTag;
    int pv;
    RelativeLayout rl_NoDataLayout;
    RelativeLayout rl_Search;
    RelativeLayout rl_searchBox;
    SortingListener sortingListener;
    TextView txtTitle;
    int ARRAY_REFRESH_CODE = 1101;
    int countSelected = 0;
    int dialog_count = 0;
    boolean isSelectAll = false;
    boolean isSingleSelect = false;
    int lastPosition = 0;
    String[] dataParameter = {"_id", "_display_name", SMUtilsWP.mediaPath, "date_added"};
    SearchView searchView = null;
    int position = 0;
    boolean searchFlag = false;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override 
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            Degu_ImageSelectActivity.this.mMenu = menu;
            actionMode.getMenuInflater().inflate(R.menu.select_image_menu, menu);
            return true;
        }

        @Override 
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            if (Build.VERSION.SDK_INT < 21) {
                return true;
            }
            Degu_ImageSelectActivity.this.getWindow().clearFlags(67108864);
            return true;
        }

        @Override 
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.Copy:
                    if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                        Degu_ImageSelectActivity.this.ImageAlbumDialog(false);
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Image.", 0).show();
                    }
                    return true;
                case R.id.Delete:
                    if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                        Degu_ImageSelectActivity.this.DeleteDialog();
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Image.", 0).show();
                    }
                    return true;
                case R.id.Edit:
                    if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() == 1) {
                        Intent intent = new Intent(Degu_ImageSelectActivity.this, EdEditImageActivity.class);
                        intent.putExtra("imagepath", Degu_ImageSelectActivity.this.GetSelectedImageList().get(0).path + "");
                        Degu_ImageSelectActivity.this.startActivity(intent);
                        Common.IdentifyActivity = "";
                    } else {
                        Toast.makeText(BseActivity.activity, "Select single image.", 0).show();
                    }
                    actionMode.finish();
                    return true;
                case R.id.Lock:
                    new ArrayList().clear();
                    ArrayList<DataFileModel> GetSelectedImageList = Degu_ImageSelectActivity.this.GetSelectedImageList();
                    if (Degu_ImageSelectActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                        Degu_ImageSelectActivity.this.SecurityDialog(GetSelectedImageList);
                        actionMode.finish();
                    } else if (GetSelectedImageList.size() >= 1) {
                        Degu_ImageSelectActivity.this.LockDialog();
                    } else {
                        Toast.makeText(BseActivity.activity, "Select at least one image.", 0).show();
                    }
                    return true;
                case R.id.Move:
                    if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                        Degu_ImageSelectActivity.this.ImageAlbumDialog(true);
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Image.", 0).show();
                    }
                    return true;
                case R.id.Rename:
                    if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() == 1) {
                        Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                        gMImageSelectActivity.RenameDialog(gMImageSelectActivity.GetSelectedImageList().get(0).path);
                    } else {
                        Toast.makeText(BseActivity.activity, "Select only one image", 0).show();
                    }
                    return true;
                case R.id.Selector:
                    if (Degu_ImageSelectActivity.this.isSelectAll) {
                        Degu_ImageSelectActivity.this.UnSelectAll();
                        Degu_ImageSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                        actionMode.finish();
                    } else {
                        Degu_ImageSelectActivity.this.SelectAll();
                        Degu_ImageSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_unselect));
                    }
                    return true;
                case R.id.Share:
                    if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                        Degu_ImageSelectActivity gMImageSelectActivity2 = Degu_ImageSelectActivity.this;
                        gMImageSelectActivity2.ShareMultipleImage(gMImageSelectActivity2.GetSelectedImageList());
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Image.", 0).show();
                    }
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override 
        public void onDestroyActionMode(ActionMode actionMode) {
            Degu_ImageSelectActivity.mActionMode = null;
            Degu_ImageSelectActivity.this.UnSelectAll();
            if (Build.VERSION.SDK_INT >= 21) {
                Degu_ImageSelectActivity.this.getWindow().clearFlags(67108864);
            }
        }
    };
    boolean renameTag = false;
    String toastTag = "";
    private String folder_path = "";

    @Override
    public void onDestroy() {
        super.onDestroy();
        ConstantArrayClass.imageList = null;
        BothAlbumSelectAdapter gMInnerBothAlbumSelectAdapter = innerBothAlbumSelectAdapter;
        if (gMInnerBothAlbumSelectAdapter != null) {
            gMInnerBothAlbumSelectAdapter.releaseResources();
        }
        this.gridViewImage.setOnItemClickListener(null);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DataOrientation(configuration.orientation);
    }

  
    @Override
    public void onResume() {
        DataOrientation(getResources().getConfiguration().orientation);
        BothAlbumSelectAdapter gMInnerBothAlbumSelectAdapter = innerBothAlbumSelectAdapter;
        if (gMInnerBothAlbumSelectAdapter != null) {
            gMInnerBothAlbumSelectAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_select);
        activity = this;
        imageSelectActivity = this;
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        this.Lock_AllowMultiple = getIntent().getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false);
        this.album = intent.getStringExtra(AppUtilsClass.INTENT_EXTRA_ALBUM);
        this.position = intent.getIntExtra("position", 0);
        String stringExtra = intent.getStringExtra(Common.gBucketID);
        AppUtilsClass.bucketId = stringExtra;
        this.preferenceClass = new PrefClass(getApplicationContext());
        this.loader = (ProgressBar) findViewById(R.id.loader);
        this.gridViewImage = (GridView) findViewById(R.id.grid_view_image_select);
        ID_Binding();
        SortingCallBack();
        new GetImageAsync().execute(new Void[0]);

        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

    }

    private void ID_Binding() {
        this.rl_searchBox = (RelativeLayout) findViewById(R.id.rl_searchBox);
        this.searchView = (SearchView) findViewById(R.id.searchView);
        this.btnSearch = (ImageView) findViewById(R.id.btnSearch);
        this.btnSearchClose = (ImageView) findViewById(R.id.btnSearchClose);
        this.rl_Search = (RelativeLayout) findViewById(R.id.rl_Search);
        this.imgBack = (ImageView) findViewById(R.id.btnBack);
        this.txtTitle = (TextView) findViewById(R.id.toolbarTitle);
        this.imgSort = (ImageView) findViewById(R.id.btnSortImage);
        this.imgEdit = (ImageView) findViewById(R.id.edit_ic);
        this.imgCopy = (ImageView) findViewById(R.id.copy_ic);
        this.imgMove = (ImageView) findViewById(R.id.move_ic);
        this.imgRename = (ImageView) findViewById(R.id.rename_ic);
        this.imgDelete = (ImageView) findViewById(R.id.delete_ic);
        this.imgShare = (ImageView) findViewById(R.id.share_ic);
        this.btnDone = (Button) findViewById(R.id.btn_done);
        this.rl_NoDataLayout = (RelativeLayout) findViewById(R.id.hintNoHideImageLayout);
        Click_Event();
    }

    public static void slideDown(final View view) {
        view.animate().translationY(view.getHeight()).alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMImageSelectActivity.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
                view.setAlpha(1.0f);
                view.setTranslationY(0.0f);
            }
        });
    }

    public static void slideUp(final View view) {
        view.setVisibility(0);
        view.setAlpha(0.0f);
        if (view.getHeight() > 0) {
            slideUpNow(view);
        } else {
            view.post(new Runnable() {
                @Override
                public void run() {
                    Degu_ImageSelectActivity.slideUpNow(view);
                }
            });
        }
    }

    public static void slideUpNow(final View view) {
        view.setTranslationY(view.getHeight());
        view.animate().translationY(0.0f).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMImageSelectActivity.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(0);
                view.setAlpha(1.0f);
            }
        });
    }

    private void Click_Event() {
        this.searchView.setFocusable(false);
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                Degu_ImageSelectActivity.this.filter(str);
                return false;
            }
        });
        this.rl_Search.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (Degu_ImageSelectActivity.this.searchFlag) {
                    Degu_ImageSelectActivity.slideDown(Degu_ImageSelectActivity.this.rl_searchBox);
                    Degu_ImageSelectActivity.this.rl_searchBox.setVisibility(8);
                    Degu_ImageSelectActivity.this.btnSearch.setVisibility(0);
                    Degu_ImageSelectActivity.this.btnSearchClose.setVisibility(8);
                    Degu_ImageSelectActivity.this.searchFlag = false;
                    return;
                }
                Degu_ImageSelectActivity.slideUp(Degu_ImageSelectActivity.this.rl_searchBox);
                Degu_ImageSelectActivity.this.rl_searchBox.setVisibility(0);
                Degu_ImageSelectActivity.this.btnSearch.setVisibility(8);
                Degu_ImageSelectActivity.this.btnSearchClose.setVisibility(0);
                Degu_ImageSelectActivity.this.searchFlag = true;
            }
        });
        this.txtTitle.setText(this.album);
        this.txtTitle.setVisibility(0);
        this.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.finish();
            }
        });
        this.imgSort.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ConstantArrayClass.imageList.size() >= 1) {
                    new SortingDataDialog(Degu_ImageSelectActivity.this, ConstantArrayClass.imageList, Degu_ImageSelectActivity.this.sortingListener).ShowSortingDialogue();
                } else {
                    Toast.makeText(Degu_ImageSelectActivity.this, "No Image.", 0).show();
                }
            }
        });
        this.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() == 1) {
                    Intent intent = new Intent(Degu_ImageSelectActivity.this, EdEditImageActivity.class);
                    intent.putExtra("imagepath", Degu_ImageSelectActivity.this.GetSelectedImageList().get(0).path + "");
                    Degu_ImageSelectActivity.this.startActivity(intent);
                    Common.IdentifyActivity = "";
                    return;
                }
                Toast.makeText(Degu_ImageSelectActivity.this, "Select single image.", 0).show();
            }
        });
        this.imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                    Degu_ImageSelectActivity.this.ImageAlbumDialog(false);
                } else {
                    Toast.makeText(Degu_ImageSelectActivity.this, "Select Image.", 0).show();
                }
            }
        });
        this.imgMove.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                    Degu_ImageSelectActivity.this.ImageAlbumDialog(true);
                } else {
                    Toast.makeText(Degu_ImageSelectActivity.this, "Select Image.", 0).show();
                }
            }
        });
        this.imgRename.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() == 1) {
                    Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                    gMImageSelectActivity.RenameDialog(gMImageSelectActivity.GetSelectedImageList().get(0).path);
                    return;
                }
                Toast.makeText(Degu_ImageSelectActivity.this, "Select only one image", 0).show();
            }
        });
        this.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                    Degu_ImageSelectActivity.this.DeleteDialog();
                } else {
                    Toast.makeText(Degu_ImageSelectActivity.this, "Select Image.", 0).show();
                }
            }
        });
        this.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (Degu_ImageSelectActivity.this.GetSelectedImageList().size() >= 1) {
                    Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                    gMImageSelectActivity.ShareMultipleImage(gMImageSelectActivity.GetSelectedImageList());
                    return;
                }
                Toast.makeText(Degu_ImageSelectActivity.this, "Select Image.", 0).show();
            }
        });
        this.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Intent intent = Degu_ImageSelectActivity.this.getIntent();
                Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                intent.setClipData(gMImageSelectActivity.CreateClipData(gMImageSelectActivity.GetSelectedImageList()));
                intent.addFlags(1);
                Degu_ImageSelectActivity.this.setResult(-1, intent);
                Degu_ImageSelectActivity.this.finish();
            }
        });
    }

    public void filter(String str) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Object> it = ConstantArrayClass.imageList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            DataFileModel gMDataFileModel = (DataFileModel) next;
            if (gMDataFileModel.name != null && gMDataFileModel.name.toLowerCase().contains(str.toLowerCase())) {
                arrayList.add(next);
            }
        }
        if (arrayList.isEmpty()) {
            Toast.makeText(this, "No Data Found!", 0).show();
        } else {
            innerBothAlbumSelectAdapter.refreshData(arrayList);
        }
    }

    public void SelectSingleImage(ArrayList<Object> arrayList, int i) {
        this.isSingleSelect = true;
        ((DataFileModel) arrayList.get(i)).isSelected = !((DataFileModel) arrayList.get(i)).isSelected;
        if (((DataFileModel) arrayList.get(i)).isSelected) {
            this.countSelected++;
        } else {
            this.countSelected--;
        }
        if (this.countSelected <= 0) {
            this.isSingleSelect = false;
            this.isSelectAll = false;
            ActionMode actionMode = mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
        }
        if (GetSelectedImageList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedImageList().size()));
        }
        innerBothAlbumSelectAdapter.notifyDataSetChanged();
    }

    public ArrayList<DataFileModel> GetSelectedImageList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = ConstantArrayClass.imageList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) ConstantArrayClass.imageList.get(i)).isSelected) {
                arrayList.add((DataFileModel) ConstantArrayClass.imageList.get(i));
            }
        }
        return arrayList;
    }

    public void UnSelectAll() {
        int size = ConstantArrayClass.imageList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) ConstantArrayClass.imageList.get(i)).isSelected = false;
        }
        if (GetSelectedImageList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedImageList().size()));
        }
        this.countSelected = 0;
        this.isSelectAll = false;
        this.isSingleSelect = false;
        innerBothAlbumSelectAdapter.notifyDataSetChanged();
    }

    public void SelectAll() {
        int size = ConstantArrayClass.imageList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) ConstantArrayClass.imageList.get(i)).isSelected = true;
        }
        if (GetSelectedImageList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedImageList().size()));
        }
        this.countSelected = ConstantArrayClass.imageList.size();
        this.isSelectAll = true;
        this.isSingleSelect = true;
        innerBothAlbumSelectAdapter.notifyDataSetChanged();
    }

    public void SecurityDialog(ArrayList<DataFileModel> arrayList) {
        ConstantArrayClass.firstTimeLockDataArray = arrayList;
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_security);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.textDesc)).setText("Set your security lock for hide image!");
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                FolderPath.lock_screen = 1;
                Degu_ImageSelectActivity.this.startActivity(new Intent(Degu_ImageSelectActivity.this, DeguPrivateActivity.class));
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void LockDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_lock_image);
        dialog.setTitle("Lock Image");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.LockImages();
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void LockImages() {
        new ArrayList().clear();
        ArrayList<DataFileModel> GetSelectedImageList = GetSelectedImageList();
        if (GetSelectedImageList.size() <= 0) {
            Toast.makeText(this, "Select Image.", 0).show();
        } else if (ExternalStorageHelper.isExternalStorageReadableAndWritable()) {
            this.progressTag = "FromLock";
            new LockImageExecute(GetSelectedImageList).execute(new Void[0]);
        } else {
            Toast.makeText(this, "not Read Write", 0).show();
        }
    }


    public class LockImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public LockImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            Degu_ImageSelectActivity.this.progressLock = new ProgressDialog(Degu_ImageSelectActivity.this);
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            Degu_ImageSelectActivity.this.progressLock.setMessage("Please wait a while...");
            Degu_ImageSelectActivity.this.progressLock.setProgressStyle(0);
            Degu_ImageSelectActivity.this.progressLock.setIndeterminate(false);
            Degu_ImageSelectActivity.this.progressLock.setCancelable(false);
            Degu_ImageSelectActivity.this.progressLock.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = Degu_ImageSelectActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_IMAGE).mkdirs();
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(this.pathList.get(i).path);
                File file2 = new File(FolderPath.SDCARD_PATH_IMAGE + File.separator + file.getName());
                String parent = file2.getParent();
                try {
                    FileUtils.CopyMoveFile(file, file2);
                    long j = this.pathList.get(i).id;
                    String str = this.pathList.get(i).name;
                    String str2 = this.pathList.get(i).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file2.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file3 = new File(this.pathList.get(i2).path);
                            file3.delete();
                            ContentResolver contentResolver = Degu_ImageSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                        }
                        Degu_ImageSelectActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            Degu_ImageSelectActivity.this.UnSelectAll();
            Degu_ImageSelectActivity.this.isSingleSelect = false;
            Degu_ImageSelectActivity.this.isSelectAll = false;
            Degu_ImageSelectActivity.this.progressLock.dismiss();
            if (Degu_ImageSelectActivity.mActionMode != null) {
                Degu_ImageSelectActivity.mActionMode.finish();
            }
            AppUtilsClass.RefreshImageAlbum(Degu_ImageSelectActivity.this);
            AppUtilsClass.RefreshMoment(Degu_ImageSelectActivity.this);
            new GetImageAsync().execute(new Void[0]);
            Toast.makeText(Degu_ImageSelectActivity.this, "Files moved to vault successfully.", 0).show();
        }
    }

    public void ImageAlbumDialog(final boolean z) {
        Dialog dialog = new Dialog(this, R.style.MyDialog);
        this.albumDialog = dialog;
        dialog.requestWindowFeature(1);
        this.albumDialog.setCancelable(false);
        this.albumDialog.setContentView(R.layout.dg_folder);
        ImageView imageView = (ImageView) this.albumDialog.findViewById(R.id.btnBack);
        TextView textView = (TextView) this.albumDialog.findViewById(R.id.toolbarTitle);
        textView.setText("Select Image Album");
        textView.setVisibility(0);
        this.albumGridView = (GridView) this.albumDialog.findViewById(R.id.albumGridView);
        Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "bucket_id", "bucket_display_name", SMUtilsWP.mediaPath}, null, null, null);
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
                    gMDialogAlbumModel.pathlist = GetPathList("" + gMDialogAlbumModel.bucket_id);
                    arrayList.add(gMDialogAlbumModel);
                    arrayList2.add(gMDialogAlbumModel.bucket_id);
                }
            }
            query.close();
        }
        AlbumAdapter gMAlbumAdapter = new AlbumAdapter(this, (ArrayList<DialogAlbumModel>) arrayList);
        this.dialogAlbumAdapter = gMAlbumAdapter;
        this.albumGridView.setAdapter((ListAdapter) gMAlbumAdapter);
        FolderOrientation(getResources().getConfiguration().orientation);
        this.albumGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList arrayList3 = arrayList;
                if (arrayList3 == null || arrayList3.isEmpty()) {
                    return;
                }
                Degu_ImageSelectActivity.this.MoveImageInsideDialog(new File(((DialogAlbumModel) arrayList.get(i)).folderPath), z, ((DialogAlbumModel) arrayList.get(i)).bucket_id, ((DialogAlbumModel) arrayList.get(i)).foldername);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.UnSelectAll();
                Degu_ImageSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                if (Degu_ImageSelectActivity.mActionMode != null) {
                    Degu_ImageSelectActivity.mActionMode.finish();
                }
                Degu_ImageSelectActivity.this.albumDialog.dismiss();
            }
        });
        this.albumDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override 
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && Degu_ImageSelectActivity.this.dialog_count == 0) {
                    onBackPressed();
                    return true;
                }
                Degu_ImageSelectActivity.this.dialog_count = 0;
                return false;
            }
        });
        this.albumDialog.show();
    }

    public String GetParentPath(String str) {
        return new File(str).getAbsoluteFile().getParent();
    }

    public ArrayList<String> GetPathList(String str) {
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

    public void MoveImageInsideDialog(final File file, final boolean z, final String str, String str2) {
        Dialog dialog = new Dialog(this, R.style.MyDialog);
        this.insideFileDialog = dialog;
        dialog.requestWindowFeature(1);
        this.insideFileDialog.setCancelable(false);
        this.insideFileDialog.setContentView(R.layout.dialog_gm_folder_image);
        ImageView imageView = (ImageView) this.insideFileDialog.findViewById(R.id.btnBack);
        TextView textView = (TextView) this.insideFileDialog.findViewById(R.id.toolbarTitle);
        textView.setText(str2);
        textView.setVisibility(0);
        this.albumImageGridView = (GridView) this.insideFileDialog.findViewById(R.id.albumGridView);
        Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.dataParameter, "bucket_id =?", new String[]{str}, null);
        ArrayList arrayList = new ArrayList(query.getCount());
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex(SMUtilsWP.mediaPath));
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
        query.close();
        ImageFolderAdapter gMImageFolderAdapter = new ImageFolderAdapter(this, (ArrayList<Object>) arrayList);
        this.dialogAlbumImageAdapter = gMImageFolderAdapter;
        this.albumImageGridView.setAdapter((ListAdapter) gMImageFolderAdapter);
        orientationBasedUIAlbumImage(getResources().getConfiguration().orientation);
        this.dialogAlbumImageAdapter.notifyDataSetChanged();
        this.albumImageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Degu_ImageSelectActivity.sendImgList.clear();
                Degu_ImageSelectActivity.sendImgList = new ArrayList<>();
                Degu_ImageSelectActivity.sendImgList.addAll(Degu_ImageSelectActivity.myImageinsideList);
                Degu_ImageSelectActivity.this.lastPosition = i;
                Common.IdentifyActivity = Degu_ImageSelectActivity.TAG;
                Intent intent = new Intent(Degu_ImageSelectActivity.this, PreviewActivity.class);
                intent.putExtra(Common.gImagePath, ((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).path);
                intent.putExtra(Common.gTotalimage, Degu_ImageSelectActivity.sendImgList.size());
                intent.putExtra(Common.gCurrenrtPosition, Degu_ImageSelectActivity.this.lastPosition);
                intent.putExtra(Common.gArrayType, "open");
                intent.putExtra(Common.gActivityname, "InnerImageSelectActivity");
                intent.putExtra(Common.gBucketID, AppUtilsClass.bucketId);
                intent.putExtra(Common.gMediaType, ((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).mediaType);
                Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                gMImageSelectActivity.startActivityForResult(intent, gMImageSelectActivity.ARRAY_REFRESH_CODE);
            }
        });
        this.insideFileDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    Degu_ImageSelectActivity.this.dialog_count = 1;
                    Degu_ImageSelectActivity.this.onBackPressed();
                }
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.insideFileDialog.dismiss();
            }
        });
        final LinearLayout linearLayout = (LinearLayout) this.insideFileDialog.findViewById(R.id.btnPaste);
        if (linearLayout.getVisibility() == 0) {
            this.albumImageGridView.setOnItemClickListener(null);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.MoveImageonAlbum(file, z, str);
                linearLayout.setVisibility(8);
                Degu_ImageSelectActivity.this.UnSelectAll();
                if (Degu_ImageSelectActivity.mActionMode != null) {
                    Degu_ImageSelectActivity.mActionMode.finish();
                }
            }
        });
        this.insideFileDialog.show();
    }

    public void MoveImageonAlbum(File file, boolean z, String str) {
        if (GetSelectedImageList().size() > 0) {
            new MoveToAlbum(GetSelectedImageList(), file, z, str).execute(new Void[0]);
        } else {
            Toast.makeText(this, "Select Image.", 0).show();
        }
    }


    public class MoveToAlbum extends AsyncTask<Void, Void, Void> {
        File albumDir;
        String bucketId;
        boolean operation;
        ArrayList<DataFileModel> patharraylist;
        ProgressDialog progress;
        int toast_cnt = 0;

        public MoveToAlbum(ArrayList<DataFileModel> arrayList, File file, boolean z, String str) {
            this.patharraylist = new ArrayList<>();
            this.progress = new ProgressDialog(Degu_ImageSelectActivity.this);
            this.patharraylist = arrayList;
            this.albumDir = file;
            this.operation = z;
            this.bucketId = str;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            this.progress.setMessage("Please wait a while...");
            this.progress.setProgressStyle(0);
            this.progress.setIndeterminate(false);
            this.progress.setCancelable(false);
            this.progress.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            if (this.operation) {
                this.toast_cnt = 0;
                for (int i = 0; i < this.patharraylist.size(); i++) {
                    File file = new File(this.patharraylist.get(i).path);
                    File file2 = new File(this.albumDir + File.separator + file.getName());
                    try {
                        org.apache.commons.io.FileUtils.copyFile(file, file2);
                        file.delete();
                        if (this.patharraylist.size() - 1 == i) {
                            for (int i2 = 0; i2 < this.patharraylist.size(); i2++) {
                                ContentResolver contentResolver = Degu_ImageSelectActivity.this.getContentResolver();
                                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                                contentResolver.delete(uri, "_data='" + this.patharraylist.get(i2).path + "'", null);
                            }
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(SMUtilsWP.mediaPath, file2.getPath());
                        contentValues.put("mime_type", "image/*");
                        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                        contentValues.put("date_modified", Long.valueOf(file2.lastModified()));
                        Degu_ImageSelectActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        String str = Degu_ImageSelectActivity.TAG;
                        Log.e(str, "Move Image Count: " + i);
                    } catch (IOException e) {
                        String str2 = Degu_ImageSelectActivity.TAG;
                        Log.e(str2, "Move Image IOException: " + e);
                        e.printStackTrace();
                    }
                }
            } else {
                this.toast_cnt = 1;
                for (int i3 = 0; i3 < this.patharraylist.size(); i3++) {
                    File file3 = new File(this.patharraylist.get(i3).path);
                    File file4 = new File(this.albumDir + File.separator + System.currentTimeMillis() + file3.getName());
                    this.albumDir.mkdirs();
                    try {
                        org.apache.commons.io.FileUtils.copyFile(file3, file4);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("title", file4.getName());
                        contentValues2.put("description", "Gallery");
                        contentValues2.put(SMUtilsWP.mediaPath, file4.getPath());
                        contentValues2.put("mime_type", "image/*");
                        contentValues2.put("date_added", Long.valueOf(System.currentTimeMillis()));
                        contentValues2.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                        contentValues2.put("bucket_id", Integer.valueOf(file4.getPath().toLowerCase(Locale.US).hashCode()));
                        contentValues2.put("bucket_display_name", file4.getName().toLowerCase(Locale.US));
                        contentValues2.put(SMUtilsWP.mediaPath, file4.getPath());
                        Degu_ImageSelectActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues2);
                        String str3 = Degu_ImageSelectActivity.TAG;
                        Log.e(str3, "Copy Image Count: " + i3);
                    } catch (IOException e2) {
                        String str4 = Degu_ImageSelectActivity.TAG;
                        Log.e(str4, "Copy Image IOException: " + e2);
                        e2.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override 
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            Degu_ImageSelectActivity.this.RefreshAdapter(this.bucketId);
            this.progress.dismiss();
            AppUtilsClass.RefreshImageAlbum(Degu_ImageSelectActivity.this);
            AppUtilsClass.RefreshMoment(Degu_ImageSelectActivity.this);
            new GetImageAsync().execute(new Void[0]);
            if (this.patharraylist.size() == 1) {
                int i = this.toast_cnt;
                if (i == 0) {
                    Toast.makeText(Degu_ImageSelectActivity.this, "File moved successfully.", 0).show();
                } else if (i == 1) {
                    Toast.makeText(Degu_ImageSelectActivity.this, "File copied successfully.", 0).show();
                }
                Degu_ImageSelectActivity.this.insideFileDialog.dismiss();
                Degu_ImageSelectActivity.this.albumDialog.dismiss();
                return;
            }
            int i2 = this.toast_cnt;
            if (i2 == 0) {
                Toast.makeText(Degu_ImageSelectActivity.this, "Files moved successfully.", 0).show();
            } else if (i2 == 1) {
                Toast.makeText(Degu_ImageSelectActivity.this, "Files copied successfully.", 0).show();
            }
            Degu_ImageSelectActivity.this.insideFileDialog.dismiss();
            Degu_ImageSelectActivity.this.albumDialog.dismiss();
        }
    }

    public void RefreshAdapter(String str) {
        Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.dataParameter, "bucket_id =?", new String[]{str}, null);
        ArrayList arrayList = new ArrayList(query.getCount());
        if (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex(SMUtilsWP.mediaPath));
            try {
                if (new File(string).exists()) {
                    DataFileModel gMDataFileModel = new DataFileModel();
                    gMDataFileModel.path = string;
                    arrayList.add(gMDataFileModel);
                }
            } catch (Exception e) {
                Log.e("Exception : ", e.toString());
            }
            query.moveToPrevious();
            return;
        }
        query.close();
        ArrayList<Object> arrayList2 = new ArrayList<>();
        myImageinsideList = arrayList2;
        arrayList2.addAll(arrayList);
        ImageFolderAdapter gMImageFolderAdapter = new ImageFolderAdapter(this, myImageinsideList);
        this.dialogAlbumImageAdapter = gMImageFolderAdapter;
        this.albumImageGridView.setAdapter((ListAdapter) gMImageFolderAdapter);
        orientationBasedUIAlbumImage(getResources().getConfiguration().orientation);
        this.dialogAlbumImageAdapter.notifyDataSetChanged();
    }

    public void DataOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (Degu_MainActivity.preferenceDataUtils.getInt(Common.gDataColumns, 0) != 0) {
            int i2 = Degu_MainActivity.preferenceDataUtils.getInt(Common.gDataColumns, 0);
            this.pv = i2;
            if (i2 != 0) {
                this.i2 = i2;
            } else {
                this.i2 = 3;
                Degu_MainActivity.dataDivider = 9;
            }
        } else {
            this.i2 = 3;
            Degu_MainActivity.dataDivider = 9;
        }
        BothAlbumSelectAdapter gMInnerBothAlbumSelectAdapter = innerBothAlbumSelectAdapter;
        if (gMInnerBothAlbumSelectAdapter != null) {
            gMInnerBothAlbumSelectAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        this.gridViewImage.setNumColumns(this.i2);
    }

    private void FolderOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (Degu_MainActivity.preferenceAlbumUtils.getInt(Common.gAlbumColumns, 0) != 0) {
            int i2 = Degu_MainActivity.preferenceAlbumUtils.getInt(Common.gAlbumColumns, 0);
            this.pv = i2;
            if (i2 != 0) {
                this.i2 = i2;
            } else {
                this.i2 = 2;
                Degu_MainActivity.albumDivider = 4;
            }
        } else {
            this.i2 = 2;
            Degu_MainActivity.albumDivider = 4;
        }
        AlbumAdapter gMAlbumAdapter = this.dialogAlbumAdapter;
        if (gMAlbumAdapter != null) {
            gMAlbumAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.albumDivider));
        }
        if (i != 1) {
            this.i2 = Degu_MainActivity.albumDivider;
        }
        this.albumGridView.setNumColumns(this.i2);
    }

    private void orientationBasedUIAlbumImage(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (Degu_MainActivity.preferenceDataUtils.getInt(Common.gDataColumns, 0) != 0) {
            int i2 = Degu_MainActivity.preferenceDataUtils.getInt(Common.gDataColumns, 0);
            this.pv = i2;
            if (i2 != 0) {
                this.i2 = i2;
            } else {
                this.i2 = 3;
                Degu_MainActivity.dataDivider = 9;
            }
        } else {
            this.i2 = 3;
            Degu_MainActivity.dataDivider = 9;
        }
        ImageFolderAdapter gMImageFolderAdapter = this.dialogAlbumImageAdapter;
        if (gMImageFolderAdapter != null) {
            gMImageFolderAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        this.albumImageGridView.setNumColumns(this.i2);
    }

    public void RenameDialog(final String str) {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_rename_file);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Rename File");
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        editText.setText(FilenameUtils.getBaseName(str));
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMImageSelectActivity.27
            @Override 
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    Degu_ImageSelectActivity.this.renameTag = true;
                    File file = new File(str);
                    String parent = file.getParent();
                    String substring = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
                    File file2 = new File(parent + File.separator + editText.getText().toString().trim() + substring);
                    try {
                        if (FileUtils.CopyMoveFile(file, file2).exists()) {
                            AppUtilsClass.insertUri(Degu_ImageSelectActivity.this, file2);
                            file.delete();
                            ContentResolver contentResolver = Degu_ImageSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file + "'", null);
                            Degu_ImageSelectActivity.this.UnSelectAll();
                            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMImageSelectActivity.27.1
                                @Override 
                                public void run() {
                                    new GetImageAsync().execute(new Void[0]);
                                }
                            }, 500L);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                    return;
                }
                editText.setError("Please Enter File Name");
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMImageSelectActivity.28
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

    public void DeleteDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_file);
        dialog.setTitle("Delete Image");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.progressTag = "FromDelete";
                new DeleteToTrashImageExecute(Degu_ImageSelectActivity.this.GetSelectedImageList()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.UnSelectAll();
                Degu_ImageSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                if (Degu_ImageSelectActivity.mActionMode != null) {
                    Degu_ImageSelectActivity.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class DeleteImageBGExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;
        ProgressDialog progress;

        public DeleteImageBGExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            this.progress = new ProgressDialog(Degu_ImageSelectActivity.this);
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            this.progress.setMessage("Please wait a while...");
            this.progress.setProgressStyle(0);
            this.progress.setIndeterminate(false);
            this.progress.setCancelable(false);
            this.progress.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            for (int i = 0; i < this.pathList.size(); i++) {
                try {
                    new File(this.pathList.get(i).path).delete();
                    String str = Degu_ImageSelectActivity.TAG;
                    Log.e(str, "Delete File Count: " + i);
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file = new File(this.pathList.get(i2).path);
                            ContentResolver contentResolver = Degu_ImageSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
                            StringBuilder sb = new StringBuilder();
                            sb.append("Delete Media Store Count: ");
                            sb.append(i2);
                            Log.e(Degu_ImageSelectActivity.TAG, sb.toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override 
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            Degu_ImageSelectActivity.this.UnSelectAll();
            this.progress.dismiss();
            if (Degu_ImageSelectActivity.mActionMode != null) {
                Degu_ImageSelectActivity.mActionMode.finish();
            }
            AppUtilsClass.RefreshImageAlbum(Degu_ImageSelectActivity.this);
            new GetImageAsync().execute(new Void[0]);
            Toast.makeText(Degu_ImageSelectActivity.this, "Files are deleted successfully.", 0).show();
        }
    }


    public class DeleteToTrashImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteToTrashImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            Degu_ImageSelectActivity.this.progressDelete = new ProgressDialog(Degu_ImageSelectActivity.this);
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            Degu_ImageSelectActivity.this.progressDelete.setMessage("Please wait a while...");
            Degu_ImageSelectActivity.this.progressDelete.setProgressStyle(0);
            Degu_ImageSelectActivity.this.progressDelete.setIndeterminate(false);
            Degu_ImageSelectActivity.this.progressDelete.setCancelable(false);
            Degu_ImageSelectActivity.this.progressDelete.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = Degu_ImageSelectActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_IMAGE).mkdirs();
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(this.pathList.get(i).path);
                File file2 = new File(FolderPath.SDCARD_PATH_DELETE_IMAGE + File.separator + file.getName());
                String parent = file2.getParent();
                try {
                    FileUtils.CopyMoveFile(file, file2);
                    long j = this.pathList.get(i).id;
                    String str = this.pathList.get(i).name;
                    String str2 = this.pathList.get(i).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file2.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file3 = new File(this.pathList.get(i2).path);
                            file3.delete();
                            ContentResolver contentResolver = Degu_ImageSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                        }
                        Degu_ImageSelectActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
                        String str3 = Degu_ImageSelectActivity.TAG;
                        Log.e(str3, "OldPath Size: " + listString.size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override 
        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            Degu_ImageSelectActivity.this.UnSelectAll();
            Degu_ImageSelectActivity.this.isSingleSelect = false;
            Degu_ImageSelectActivity.this.isSelectAll = false;
            Degu_ImageSelectActivity.this.progressDelete.dismiss();
            if (Degu_ImageSelectActivity.mActionMode != null) {
                Degu_ImageSelectActivity.mActionMode.finish();
            }
            AppUtilsClass.RefreshImageAlbum(Degu_ImageSelectActivity.this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtilsClass.RefreshMoment(Degu_ImageSelectActivity.this);
                    MoMomentFragment.momentAdapter.notifyDataSetChanged();
                }
            }, 1000L);
            new GetImageAsync().execute(new Void[0]);
            Toast.makeText(Degu_ImageSelectActivity.this, "Files moved to trash successfully.", 0).show();
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.ARRAY_REFRESH_CODE && i2 == -1) {
            if (ConstantArrayClass.imageList.size() > 0) {
                this.rl_NoDataLayout.setVisibility(8);
                this.gridViewImage.setVisibility(0);
                return;
            }
            this.rl_NoDataLayout.setVisibility(0);
            this.gridViewImage.setVisibility(8);
        }
    }


    public class GetImageAsync extends AsyncTask<Void, Void, Void> {
        @Override 
        protected void onPreExecute() {
        }

        public GetImageAsync() {
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            try {
                if (ConstantArrayClass.imageList != null) {
                    ConstantArrayClass.imageList.clear();
                }
                if (ConstantArrayClass.imageList == null) {
                    ConstantArrayClass.imageList = new ArrayList<>();
                }
                ConstantArrayClass.imageList.addAll(ConstantArrayClass.imageAlbumsList.get(Degu_ImageSelectActivity.this.position).pathlist);
                return null;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override 
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            Degu_ImageSelectActivity.this.SetImageAdapter();
            try {
                new Handler().postDelayed(new Runnable() {
                    @Override 
                    public void run() {
                        if (ConstantArrayClass.imageList == null) {
                            return;
                        }
                        if (ConstantArrayClass.imageList.size() > 0) {
                            Degu_ImageSelectActivity.this.rl_NoDataLayout.setVisibility(8);
                            Degu_ImageSelectActivity.this.gridViewImage.setVisibility(0);
                            return;
                        }
                        Degu_ImageSelectActivity.this.rl_NoDataLayout.setVisibility(0);
                        Degu_ImageSelectActivity.this.gridViewImage.setVisibility(8);
                    }
                }, 500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Degu_ImageSelectActivity.this.renameTag) {
                AppUtilsClass.RefreshImageAlbum(Degu_ImageSelectActivity.this);
                if (Degu_ImageSelectActivity.mActionMode != null) {
                    Degu_ImageSelectActivity.mActionMode.finish();
                }
                AppUtilsClass.ScanMomentAllDataNew(Degu_ImageSelectActivity.this);
                Degu_ImageSelectActivity.this.renameTag = false;
            }
        }
    }

    public void SetImageAdapter() {
        new SortingDataDialog(this, ConstantArrayClass.imageList, null).Sorting(LoginPreferenceUtilsData.GetStringData(this, SortingDataDialog.SortingName), LoginPreferenceUtilsData.GetStringData(this, SortingDataDialog.SortingType));
        BothAlbumSelectAdapter gMInnerBothAlbumSelectAdapter = new BothAlbumSelectAdapter(this, getApplicationContext(), ConstantArrayClass.imageList);
        innerBothAlbumSelectAdapter = gMInnerBothAlbumSelectAdapter;
        this.gridViewImage.setAdapter((ListAdapter) gMInnerBothAlbumSelectAdapter);
        this.loader.setVisibility(8);
        this.gridViewImage.setVisibility(0);
        DataOrientation(getResources().getConfiguration().orientation);
        innerBothAlbumSelectAdapter.setItemClickCallback(new OnClickListener<ArrayList<Object>, Integer, View>() {
            @Override
            public void onClickMoreListener(ArrayList<Object> arrayList, Integer num, View view) {
            }

            @Override
            public void onClickListener(final ArrayList<Object> arrayList, final Integer num) {

                        String str = "";
                        Degu_ImageSelectActivity.sendImgList = new ArrayList<>();
                        Degu_ImageSelectActivity.sendImgList = arrayList;
                        if (Degu_ImageSelectActivity.this.isSingleSelect) {
                            Degu_ImageSelectActivity.this.SelectSingleImage(Degu_ImageSelectActivity.sendImgList, num.intValue());
                            return;
                        }
                        Degu_ImageSelectActivity.this.lastPosition = num.intValue();
                        Common.IdentifyActivity = Degu_ImageSelectActivity.TAG;
                        File file = new File(((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).path);
                        try {
                            if (file.exists()) {
                                String name = file.getName();
                                str = name.substring(name.lastIndexOf("."));
                            }
                        } catch (Exception unused) {
                        }
                        String str2 = (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : ExifInterface.GPS_MEASUREMENT_3D;
                        if (((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).mediaType != null) {
                            str2 = ((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).mediaType;
                        }
                        Intent intent = new Intent(BseActivity.activity, PreviewActivity.class);
                        intent.putExtra(Common.gImagePath, ((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).path);
                        intent.putExtra(Common.gTotalimage, Degu_ImageSelectActivity.sendImgList.size());
                        intent.putExtra(Common.gCurrenrtPosition, Degu_ImageSelectActivity.this.lastPosition);
                        intent.putExtra(Common.gArrayType, "open");
                        intent.putExtra(Common.gActivityname, "InnerImageSelectActivity");
                        intent.putExtra(Common.gMediaType, str2);
                        Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                        gMImageSelectActivity.startActivityForResult(intent, gMImageSelectActivity.ARRAY_REFRESH_CODE);

            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
            public void onLongClickListener(ArrayList<Object> arrayList, Integer num) {
                Degu_ImageSelectActivity.sendImgList = new ArrayList<>();
                Degu_ImageSelectActivity.sendImgList = arrayList;
                if (Degu_ImageSelectActivity.mActionMode != null) {
                    Degu_ImageSelectActivity.mActionMode = null;
                }
                if (Degu_ImageSelectActivity.mActionMode == null) {
                    Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                    Degu_ImageSelectActivity.mActionMode = gMImageSelectActivity.startSupportActionMode(gMImageSelectActivity.mActionModeCallback);
                }
                Degu_ImageSelectActivity.this.SelectSingleImage(Degu_ImageSelectActivity.sendImgList, num.intValue());
            }
        });
    }

    private void SortingCallBack() {
        this.sortingListener = new ImageSorting();
    }


    public class ImageSorting implements SortingListener {
        ImageSorting() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            Degu_ImageSelectActivity.sendImgList = new ArrayList<>();
            Degu_ImageSelectActivity.sendImgList = arrayList;
            Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
            Degu_ImageSelectActivity.innerBothAlbumSelectAdapter = new BothAlbumSelectAdapter(gMImageSelectActivity, gMImageSelectActivity.getApplicationContext(), Degu_ImageSelectActivity.sendImgList);
            Degu_ImageSelectActivity.this.gridViewImage.setAdapter((ListAdapter) Degu_ImageSelectActivity.innerBothAlbumSelectAdapter);
            Degu_ImageSelectActivity.this.loader.setVisibility(8);
            Degu_ImageSelectActivity.this.gridViewImage.setVisibility(0);
            Degu_ImageSelectActivity gMImageSelectActivity2 = Degu_ImageSelectActivity.this;
            gMImageSelectActivity2.DataOrientation(gMImageSelectActivity2.getResources().getConfiguration().orientation);
            Degu_ImageSelectActivity.innerBothAlbumSelectAdapter.notifyDataSetChanged();
            Degu_ImageSelectActivity.innerBothAlbumSelectAdapter.setItemClickCallback(new OnClickListener<ArrayList<Object>, Integer, View>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMImageSelectActivity.ImageSorting.1
                @Override
                public void onClickMoreListener(ArrayList<Object> arrayList2, Integer num, View view) {
                }

                @Override
                public void onClickListener(ArrayList<Object> arrayList2, Integer num) {
                    String str = "";
                    Degu_ImageSelectActivity.sendImgList = new ArrayList<>();
                    Degu_ImageSelectActivity.sendImgList = arrayList2;
                    if (Degu_ImageSelectActivity.this.isSingleSelect) {
                        Degu_ImageSelectActivity.this.SelectSingleImage(Degu_ImageSelectActivity.sendImgList, num.intValue());
                        return;
                    }
                    Degu_ImageSelectActivity.this.lastPosition = num.intValue();
                    Common.IdentifyActivity = Degu_ImageSelectActivity.TAG;
                    File file = new File(((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).path);
                    try {
                        if (file.exists()) {
                            String name = file.getName();
                            str = name.substring(name.lastIndexOf("."));
                        }
                    } catch (Exception unused) {
                    }
                    String str2 = (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : ExifInterface.GPS_MEASUREMENT_3D;
                    if (((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).mediaType != null) {
                        str2 = ((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).mediaType;
                    }
                    Intent intent = new Intent(Degu_ImageSelectActivity.this, PreviewActivity.class);
                    intent.putExtra(Common.gImagePath, ((DataFileModel) Degu_ImageSelectActivity.sendImgList.get(Degu_ImageSelectActivity.this.lastPosition)).path);
                    intent.putExtra(Common.gTotalimage, Degu_ImageSelectActivity.sendImgList.size());
                    intent.putExtra(Common.gCurrenrtPosition, Degu_ImageSelectActivity.this.lastPosition);
                    intent.putExtra(Common.gArrayType, "open");
                    intent.putExtra(Common.gActivityname, "InnerImageSelectActivity");
                    intent.putExtra(Common.gMediaType, str2);
                    Degu_ImageSelectActivity.this.startActivityForResult(intent, Degu_ImageSelectActivity.this.ARRAY_REFRESH_CODE);
                }

                @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
                public void onLongClickListener(ArrayList<Object> arrayList2, Integer num) {
                    Degu_ImageSelectActivity.sendImgList = new ArrayList<>();
                    Degu_ImageSelectActivity.sendImgList = arrayList2;
                    if (Degu_ImageSelectActivity.mActionMode != null) {
                        Degu_ImageSelectActivity.mActionMode = null;
                    }
                    if (Degu_ImageSelectActivity.mActionMode == null) {
                        Degu_ImageSelectActivity.mActionMode = Degu_ImageSelectActivity.this.startSupportActionMode(Degu_ImageSelectActivity.this.mActionModeCallback);
                    }
                    Degu_ImageSelectActivity.this.SelectSingleImage(Degu_ImageSelectActivity.sendImgList, num.intValue());
                }
            });
        }
    }

    public ClipData CreateClipData(ArrayList<DataFileModel> arrayList) {
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = getMimeType(this, FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(arrayList.get(i).path)));
        }
        ClipData clipData = new ClipData("Images", strArr, new ClipData.Item(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(arrayList.get(0).path))));
        for (int i2 = 1; i2 < arrayList.size(); i2++) {
            clipData.addItem(new ClipData.Item(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(arrayList.get(i2).path))));
        }
        return clipData;
    }

    public static String getMimeType(Context context, Uri uri) {
        return context.getContentResolver().getType(uri);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Dialog dialog = this.insideFileDialog;
        if (dialog == null || !dialog.isShowing()) {
            Dialog dialog2 = this.albumDialog;
            if (dialog2 == null || !dialog2.isShowing()) {
                        Degu_ImageSelectActivity.this.finish();
                        try {
                            new SortingDataDialog(Degu_ImageSelectActivity.this, ConstantArrayClass.imageList, Degu_ImageSelectActivity.this.sortingListener).Sorting(AppUtilsClass.lastModifiedStr, AppUtilsClass.descendingStr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                return;
            } else {
                this.albumDialog.dismiss();
                return;
            }
        }
        this.insideFileDialog.dismiss();
    }

    public void FolderDialog(ArrayList<FolderModel> arrayList, boolean z) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogStyle);
        this.btmsDialog = bottomSheetDialog;
        bottomSheetDialog.setContentView(R.layout.dg_custom_folder_list);
        this.btmsDialog.setCancelable(false);
        this.btmsDialog.setCanceledOnTouchOutside(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        RecyclerView recyclerView = (RecyclerView) this.btmsDialog.findViewById(R.id.rcv_folderlist);
        recyclerView.setLayoutManager(linearLayoutManager);
        AccessFolder11 accessFolder11 = new AccessFolder11(this, arrayList, z);
        this.accessFolder11 = accessFolder11;
        recyclerView.setAdapter(accessFolder11);
        this.btmsDialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.UnSelectAll();
                Degu_ImageSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                if (Degu_ImageSelectActivity.mActionMode != null) {
                    Degu_ImageSelectActivity.mActionMode.finish();
                }
                Degu_ImageSelectActivity.this.btmsDialog.dismiss();
            }
        });
        this.btmsDialog.findViewById(R.id.txtAddNewFolder).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ImageSelectActivity.this.AddNewFolderDialog();
            }
        });
        this.btmsDialog.show();
    }


    public class AccessFolder11 extends RecyclerView.Adapter<AccessFolder11.MyViewHolder> {
        boolean abool;
        Context context;
        ArrayList<FolderModel> folderList;

        public AccessFolder11(Context context, ArrayList<FolderModel> arrayList, boolean z) {
            this.folderList = new ArrayList<>();
            this.context = context;
            this.folderList = arrayList;
            this.abool = z;
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
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override 
                public void onClick(View view) {
                    Degu_ImageSelectActivity.this.btmsDialog.dismiss();
                    Degu_ImageSelectActivity.this.CopyMoveData(AccessFolder11.this.folderList.get(i).getFolderPath(), AccessFolder11.this.abool);
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
                this.fileName = (TextView) view.findViewById(R.id.fileName);
                this.fullPath = (TextView) view.findViewById(R.id.fullPath);
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
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Add New Folder");
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    Degu_ImageSelectActivity gMImageSelectActivity = Degu_ImageSelectActivity.this;
                    gMImageSelectActivity.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    File file = new File(Degu_ImageSelectActivity.this.folder_path);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else {
                        Toast.makeText(BseActivity.activity, "Already Exists", 0).show();
                    }
                    AppUtilsClass.getAccessFolder11();
                    if (Degu_ImageSelectActivity.this.accessFolder11 != null) {
                        Degu_ImageSelectActivity.this.accessFolder11.refreshData(AppUtilsClass.accessfolderListFor11);
                    }
                    dialog.dismiss();
                    return;
                }
                editText.setError("Please Enter Folder Name");
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void CopyMoveData(String str, boolean z) {
        for (int i = 0; i < GetSelectedImageList().size(); i++) {
            File file = new File(GetSelectedImageList().get(i).path);
            File file2 = new File(str + File.separator + file.getName());
            new MediaScanners(GalleryAppClass.getInstance(), file2);
            try {
                FileUtils.CopyMoveFile(file, file2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!z) {
            Toast.makeText(this, "Image Copy Successfully", 0).show();
            UnSelectAll();
            this.isSingleSelect = false;
            this.isSelectAll = false;
            ActionMode actionMode = mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            AppUtilsClass.RefreshImageAlbum(this);
            AppUtilsClass.RefreshMoment(this);
            MoMomentFragment.momentAdapter.notifyDataSetChanged();
        }
        if (z) {
            this.progressTag = "FromPermanentDelete";
            this.toastTag = "MoveData";
            new DeleteImageExecute(GetSelectedImageList()).execute(new Void[0]);
        }
    }


    public class DeleteImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            Degu_ImageSelectActivity.this.progressPermanentDelete = new ProgressDialog(Degu_ImageSelectActivity.this);
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            Degu_ImageSelectActivity.this.progressPermanentDelete.setMessage("Please wait a while...");
            Degu_ImageSelectActivity.this.progressPermanentDelete.setProgressStyle(0);
            Degu_ImageSelectActivity.this.progressPermanentDelete.setIndeterminate(false);
            Degu_ImageSelectActivity.this.progressPermanentDelete.setCancelable(false);
            Degu_ImageSelectActivity.this.progressPermanentDelete.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            new ArrayList();
            new ArrayList();
            ArrayList<String> listString = Degu_ImageSelectActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_IMAGE).mkdirs();
            try {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < this.pathList.size()) {
                    if (this.pathList.size() - 1 == i) {
                        while (i >= 0) {
                            if (Build.VERSION.SDK_INT >= 30) {
                                arrayList.add(ContentUris.withAppendedId(MediaStore.Images.Media.getContentUri("external"), AppUtilsClass.getFilePathToMediaID(this.pathList.get(i).path, Degu_ImageSelectActivity.this)));
                            }
                            this.pathList.remove(i);
                            i--;
                        }
                        Degu_ImageSelectActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                    i++;
                }
                if (Build.VERSION.SDK_INT < 30) {
                    return null;
                }
                AppUtilsClass.requestIntent(arrayList, Degu_ImageSelectActivity.this);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override 
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            if (Build.VERSION.SDK_INT != 30) {
                Degu_ImageSelectActivity.this.UnSelectAll();
                Degu_ImageSelectActivity.this.isSingleSelect = false;
                Degu_ImageSelectActivity.this.isSelectAll = false;
                Degu_ImageSelectActivity.this.progressPermanentDelete.dismiss();
                if (Degu_ImageSelectActivity.mActionMode != null) {
                    Degu_ImageSelectActivity.mActionMode.finish();
                }
                AppUtilsClass.RefreshImageAlbum(Degu_ImageSelectActivity.this);
                AppUtilsClass.RefreshMoment(Degu_ImageSelectActivity.this);
                MoMomentFragment.momentAdapter.notifyDataSetChanged();
                new GetImageAsync().execute(new Void[0]);
                if (Degu_ImageSelectActivity.this.toastTag.equals("MoveData")) {
                    Toast.makeText(Degu_ImageSelectActivity.this, "Files Move Successfully.", 0).show();
                } else {
                    Toast.makeText(Degu_ImageSelectActivity.this, "Files are deleted successfully.", 0).show();
                }
            }
        }
    }

    public File CopyFile(File file, File file2) throws Exception {
        try {
            if (!file.isDirectory()) {
                file2.createNewFile();
                FileChannel channel = new FileInputStream(file).getChannel();
                channel.transferTo(0L, channel.size(), new FileOutputStream(file2).getChannel());
                Toast.makeText(this, "Image Copy Successfully", 0).show();
                new MediaScanners(this, file2);
                return file2;
            } else if (!file.getPath().equals(file2.getPath())) {
                File CreateDirectory = CreateDirectory(file2, file.getName());
                for (File file3 : file.listFiles()) {
                    CopyFile(file3, CreateDirectory);
                }
                return CreateDirectory;
            } else {
                throw new Exception();
            }
        } catch (Exception unused) {
            Toast.makeText(this, "Error in Image Copy", 0).show();
            throw new Exception(String.format("Error copying %s", file.getName()));
        }
    }

    public File CreateDirectory(File file, String str) throws Exception {
        File file2 = new File(file, str);
        if (file2.mkdirs()) {
            return file2;
        }
        if (file2.exists()) {
            throw new Exception(String.format("%s already exists", str));
        }
        throw new Exception(String.format("Error creating %s", str));
    }
}
