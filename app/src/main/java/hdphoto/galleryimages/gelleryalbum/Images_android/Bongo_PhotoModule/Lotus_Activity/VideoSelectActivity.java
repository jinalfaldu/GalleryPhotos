package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.BothAlbumSelectAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.VideoDialogAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.VideoFolderAdapter;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;


public class VideoSelectActivity extends BseActivity {
    static String TAG = "VideoSelectActivity";
    public static GridView gridViewVideo;
    public static BothAlbumSelectAdapter innerBothAlbumSelectAdapter;
    public static ActionMode mActionMode;
    public static ArrayList<Object> myVideoList;
    public static ArrayList<Object> sendImgList;
    public static VideoSelectActivity videoSelectActivity;
    AccessFolder11 accessFolder11;
    String album;
    Dialog albumDialog;
    GridView albumVideoGridView;
    ImageView btnSearch;
    ImageView btnSearchClose;
    BottomSheetDialog dialog;
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
    int lastPosition;
    ProgressBar loader;
    private Menu mMenu;
    PrefClass preferenceClass;
    ProgressDialog progressDelete;
    ProgressDialog progressLock;
    ProgressDialog progressPermanentDelete;
    String progressTag;
    int pv;
    SearchView.OnQueryTextListener queryTextListener;
    RelativeLayout rl_NoDataLayout;
    RelativeLayout rl_Search;
    RelativeLayout rl_searchBox;
    SortingListener sortingListener;
    TextView txtErrorDisplay;
    TextView txtTitle;
    VideoFolderAdapter videoAlbumAdapter;
    VideoDialogAdapter videoDialogAdapter;
    GridView videoGridView;
    int ARRAY_REFRESH_CODE = 1101;
    int countSelected = 0;
    int dialog_count = 0;
    boolean isSelectedAll = false;
    boolean isSingleSelection = false;
    SearchView videoSearchView = null;
    int position = 0;
    boolean searchFlag = false;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override 
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            VideoSelectActivity.this.mMenu = menu;
            actionMode.getMenuInflater().inflate(R.menu.select_video_menu, menu);
            return true;
        }

        @Override 
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            if (Build.VERSION.SDK_INT < 21) {
                return true;
            }
            VideoSelectActivity.this.getWindow().clearFlags(67108864);
            return true;
        }

        @Override 
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.Copy:
                    if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                        VideoSelectActivity.this.VideoAlbumDialog(false);
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Video.", 0).show();
                    }
                    return true;
                case R.id.Delete:
                    if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                        VideoSelectActivity.this.DeleteDialog();
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Video.", 0).show();
                    }
                    return true;
                case R.id.Lock:
                    new ArrayList().clear();
                    ArrayList<DataFileModel> GetSelectedVideoList = VideoSelectActivity.this.GetSelectedVideoList();
                    if (VideoSelectActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                        VideoSelectActivity.this.SecurityDialog(GetSelectedVideoList);
                        actionMode.finish();
                    } else if (GetSelectedVideoList.size() >= 1) {
                        VideoSelectActivity.this.LockDialog();
                    } else {
                        Toast.makeText(BseActivity.activity, "Select at least one image.", 0).show();
                    }
                    return true;
                case R.id.Move:
                    if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                        VideoSelectActivity.this.VideoAlbumDialog(true);
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Video.", 0).show();
                    }
                    return true;
                case R.id.Rename:
                    if (VideoSelectActivity.this.GetSelectedVideoList().size() == 1) {
                        VideoSelectActivity gMVideoSelectActivity = VideoSelectActivity.this;
                        gMVideoSelectActivity.RenameDialog(gMVideoSelectActivity.GetSelectedVideoList().get(0).path);
                    } else {
                        Toast.makeText(BseActivity.activity, "Select at only video", 0).show();
                    }
                    return true;
                case R.id.Selector:
                    if (VideoSelectActivity.this.isSelectedAll) {
                        VideoSelectActivity.this.UnSelectAll();
                        VideoSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                        actionMode.finish();
                    } else {
                        VideoSelectActivity.this.SelectAll();
                        VideoSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_unselect));
                    }
                    return true;
                case R.id.Share:
                    if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                        VideoSelectActivity gMVideoSelectActivity2 = VideoSelectActivity.this;
                        gMVideoSelectActivity2.ShareMultipleVideo(gMVideoSelectActivity2.GetSelectedVideoList());
                    } else {
                        Toast.makeText(BseActivity.activity, "Select Video.", 0).show();
                    }
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            VideoSelectActivity.mActionMode = null;
            VideoSelectActivity.this.UnSelectAll();
            if (Build.VERSION.SDK_INT >= 21) {
                VideoSelectActivity.this.getWindow().clearFlags(67108864);
            }
        }
    };
    boolean renameTag = false;
    String toastTag = "";
    private String folder_path = "";

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
    public void onDestroy() {
        super.onDestroy();
        ConstantArrayClass.imageList = null;
        BothAlbumSelectAdapter gMInnerBothAlbumSelectAdapter = innerBothAlbumSelectAdapter;
        if (gMInnerBothAlbumSelectAdapter != null) {
            gMInnerBothAlbumSelectAdapter.releaseResources();
        }
        gridViewVideo.setOnItemClickListener(null);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DataOrientation(configuration.orientation);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_select);
        activity = this;
        videoSelectActivity = this;
        Common.strplay = "InnerVideoSelectActivity";
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        this.album = intent.getStringExtra(AppUtilsClass.INTENT_EXTRA_ALBUM);
        this.position = intent.getIntExtra("position", 0);
        String stringExtra = intent.getStringExtra(Common.gBucketID);
        Log.d("CATCH", " -- " + stringExtra);
        AppUtilsClass.bucketId = stringExtra;
        this.preferenceClass = new PrefClass(getApplicationContext());
        TextView textView = (TextView) findViewById(R.id.text_view_error);
        this.txtErrorDisplay = textView;
        textView.setVisibility(4);
        this.loader = (ProgressBar) findViewById(R.id.loader);
        gridViewVideo = (GridView) findViewById(R.id.grid_view_image_select);
        ID_Binding();
        SortingCallBack();
        new GetVideoAsync().execute(new Void[0]);
    }

    public static void slideDown(final View view) {
        view.animate().translationY(view.getHeight()).alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.2
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
                    VideoSelectActivity.slideUpNow(view);
                }
            });
        }
    }

    public static void slideUpNow(final View view) {
        view.setTranslationY(view.getHeight());
        view.animate().translationY(0.0f).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(0);
                view.setAlpha(1.0f);
            }
        });
    }

    private void ID_Binding() {
        this.rl_searchBox = findViewById(R.id.rl_searchBox);
        this.videoSearchView = findViewById(R.id.searchView);
        this.btnSearch = findViewById(R.id.btnSearch);
        this.btnSearchClose = findViewById(R.id.btnSearchClose);
        this.rl_Search = findViewById(R.id.rl_Search);
        this.imgBack = findViewById(R.id.btnBack);
        this.txtTitle = findViewById(R.id.toolbarTitle);
        this.imgSort = findViewById(R.id.btnSortImage);
        this.rl_NoDataLayout = findViewById(R.id.hintNoHideImageLayout);
        this.imgEdit = findViewById(R.id.edit_ic);
        this.imgDelete = findViewById(R.id.delete_ic);
        this.imgRename =  findViewById(R.id.rename_ic);
        this.imgShare =  findViewById(R.id.share_ic);
        this.imgMove =  findViewById(R.id.move_ic);
        this.imgCopy =  findViewById(R.id.copy_ic);
        Click_Event();
    }

    private void Click_Event() {
        this.videoSearchView.setFocusable(false);
        this.videoSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                VideoSelectActivity.this.filter(str);
                return false;
            }
        });
        this.rl_Search.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoSelectActivity.this.searchFlag) {
                    Log.d("CGCGCG", " - true if");
                    VideoSelectActivity.slideDown(VideoSelectActivity.this.rl_searchBox);
                    VideoSelectActivity.this.rl_searchBox.setVisibility(8);
                    VideoSelectActivity.this.btnSearch.setVisibility(0);
                    VideoSelectActivity.this.btnSearchClose.setVisibility(8);
                    VideoSelectActivity.this.searchFlag = false;
                    return;
                }
                Log.d("CGCGCG", " - false if");
                VideoSelectActivity.slideUp(VideoSelectActivity.this.rl_searchBox);
                VideoSelectActivity.this.rl_searchBox.setVisibility(0);
                VideoSelectActivity.this.btnSearch.setVisibility(8);
                VideoSelectActivity.this.btnSearchClose.setVisibility(0);
                VideoSelectActivity.this.searchFlag = true;
            }
        });
        this.txtTitle.setText(this.album);
        this.txtTitle.setVisibility(0);
        this.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.finish();
            }
        });
        this.imgSort.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ConstantArrayClass.videoList.size() >= 1) {
                    new SortingDataDialog(VideoSelectActivity.this, ConstantArrayClass.videoList, VideoSelectActivity.this.sortingListener).ShowSortingDialogue();
                }
            }
        });
        this.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoSelectActivity.this.GetSelectedVideoList().size() == 1) {
                    Log.e(VideoSelectActivity.TAG, "onClick: ");
                } else {
                    Toast.makeText(VideoSelectActivity.this, "Select single Image.", 0).show();
                }
            }
        });
        this.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                    VideoSelectActivity.this.DeleteDialog();
                } else {
                    Toast.makeText(VideoSelectActivity.this, "Select Video.", 0).show();
                }
            }
        });
        this.imgRename.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoSelectActivity.this.GetSelectedVideoList().size() == 1) {
                    VideoSelectActivity gMVideoSelectActivity = VideoSelectActivity.this;
                    gMVideoSelectActivity.RenameDialog(gMVideoSelectActivity.GetSelectedVideoList().get(0).path);
                    return;
                }
                Toast.makeText(VideoSelectActivity.this, "Select at only video", 0).show();
            }
        });
        this.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                    VideoSelectActivity gMVideoSelectActivity = VideoSelectActivity.this;
                    gMVideoSelectActivity.ShareMultipleVideo(gMVideoSelectActivity.GetSelectedVideoList());
                    return;
                }
                Toast.makeText(VideoSelectActivity.this, "Select Video.", 0).show();
            }
        });
        this.imgMove.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                    VideoSelectActivity.this.VideoAlbumDialog(true);
                } else {
                    Toast.makeText(VideoSelectActivity.this, "Select Video.", 0).show();
                }
            }
        });
        this.imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoSelectActivity.this.GetSelectedVideoList().size() >= 1) {
                    VideoSelectActivity.this.VideoAlbumDialog(false);
                } else {
                    Toast.makeText(VideoSelectActivity.this, "Select Video.", 0).show();
                }
            }
        });
    }

    public void filter(String str) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Object> it = ConstantArrayClass.videoList.iterator();
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
        this.isSingleSelection = true;
        ((DataFileModel) arrayList.get(i)).isSelected = !((DataFileModel) arrayList.get(i)).isSelected;
        if (((DataFileModel) arrayList.get(i)).isSelected) {
            this.countSelected++;
        } else {
            this.countSelected--;
        }
        if (this.countSelected <= 0) {
            this.isSingleSelection = false;
            this.isSelectedAll = false;
            ActionMode actionMode = mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
        }
        if (GetSelectedVideoList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedVideoList().size()));
        }
        innerBothAlbumSelectAdapter.notifyDataSetChanged();
    }

    public ArrayList<DataFileModel> GetSelectedVideoList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = ConstantArrayClass.videoList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) ConstantArrayClass.videoList.get(i)).isSelected) {
                arrayList.add((DataFileModel) ConstantArrayClass.videoList.get(i));
            }
        }
        return arrayList;
    }

    public void UnSelectAll() {
        int size = ConstantArrayClass.videoList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) ConstantArrayClass.videoList.get(i)).isSelected = false;
        }
        if (GetSelectedVideoList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedVideoList().size()));
        }
        this.countSelected = 0;
        this.isSelectedAll = false;
        this.isSingleSelection = false;
        innerBothAlbumSelectAdapter.notifyDataSetChanged();
    }

    public void SelectAll() {
        int size = ConstantArrayClass.videoList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) ConstantArrayClass.videoList.get(i)).isSelected = true;
        }
        if (GetSelectedVideoList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedVideoList().size()));
        }
        this.countSelected = ConstantArrayClass.videoList.size();
        this.isSelectedAll = true;
        this.isSingleSelection = true;
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
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                FolderPath.lock_screen = 1;
                VideoSelectActivity.this.startActivity(new Intent(VideoSelectActivity.this, DeguPrivateActivity.class));
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
        dialog.setContentView(R.layout.dialog_lock_video);
        dialog.setTitle("Lock Video");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.LockVideos();
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

    public void LockVideos() {
        new ArrayList().clear();
        ArrayList<DataFileModel> GetSelectedVideoList = GetSelectedVideoList();
        if (GetSelectedVideoList.size() > 0) {
            this.progressTag = "FromLock";
            new LockVideoExecute(GetSelectedVideoList).execute(new Void[0]);
            return;
        }
        Toast.makeText(this, "Select Video.", 0).show();
    }


    public class LockVideoExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public LockVideoExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            VideoSelectActivity.this.progressLock = new ProgressDialog(VideoSelectActivity.this);
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            VideoSelectActivity.this.progressLock.setMessage("Please wait a while...");
            VideoSelectActivity.this.progressLock.setProgressStyle(0);
            VideoSelectActivity.this.progressLock.setIndeterminate(false);
            VideoSelectActivity.this.progressLock.setCancelable(false);
            VideoSelectActivity.this.progressLock.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = VideoSelectActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_VIDEO).mkdirs();
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(this.pathList.get(i).path);
                File file2 = new File(FolderPath.SDCARD_PATH_VIDEO + File.separator + file.getName());
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
                            ContentResolver contentResolver = VideoSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                        }
                        VideoSelectActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            VideoSelectActivity.this.UnSelectAll();
            VideoSelectActivity.this.isSingleSelection = false;
            VideoSelectActivity.this.isSelectedAll = false;
            VideoSelectActivity.this.progressLock.dismiss();
            if (VideoSelectActivity.mActionMode != null) {
                VideoSelectActivity.mActionMode.finish();
            }
            AppUtilsClass.RefreshVideoAlbum(VideoSelectActivity.this);
            AppUtilsClass.RefreshMoment(VideoSelectActivity.this);
            new GetVideoAsync().execute(new Void[0]);
            Toast.makeText(VideoSelectActivity.this, "Files moved to vault successfully.", 0).show();
        }
    }

    public void VideoAlbumDialog(final boolean z) {
        Dialog dialog = new Dialog(this, R.style.MyDialog);
        this.albumDialog = dialog;
        dialog.requestWindowFeature(1);
        this.albumDialog.setCancelable(false);
        this.albumDialog.setContentView(R.layout.dg_folder);
        TextView textView = (TextView) this.albumDialog.findViewById(R.id.toolbarTitle);
        textView.setText("Select Video Folder");
        textView.setVisibility(0);
        this.videoGridView = (GridView) this.albumDialog.findViewById(R.id.albumGridView);
        ((ImageView) this.albumDialog.findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.UnSelectAll();
                VideoSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                if (VideoSelectActivity.mActionMode != null) {
                    VideoSelectActivity.mActionMode.finish();
                }
                VideoSelectActivity.this.albumDialog.dismiss();
            }
        });
        this.albumDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override 
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && VideoSelectActivity.this.dialog_count == 0) {
                    VideoSelectActivity.this.onBackPressed();
                    return true;
                }
                VideoSelectActivity.this.dialog_count = 0;
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
                    gMDialogAlbumModel.folderPath = GetFolderPath(query.getString(query.getColumnIndexOrThrow(SMUtilsWP.mediaPath)));
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
        this.videoGridView.setAdapter((ListAdapter) gMVideoDialogAdapter);
        FolderOrientation(getResources().getConfiguration().orientation);
        this.videoDialogAdapter.notifyDataSetChanged();
        this.videoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList arrayList3 = arrayList;
                if (arrayList3 == null || arrayList3.isEmpty()) {
                    return;
                }
                VideoSelectActivity.this.MoveFileInsideDialog(new File(((DialogAlbumModel) arrayList.get(i)).folderPath), z, ((DialogAlbumModel) arrayList.get(i)).bucket_id, ((DialogAlbumModel) arrayList.get(i)).foldername);
            }
        });
        this.albumDialog.show();
    }

    public String GetFolderPath(String str) {
        return new File(str).getAbsoluteFile().getParent();
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
        ImageView imageView = (ImageView) this.insideFileDialog.findViewById(R.id.btnBack);
        TextView textView = (TextView) this.insideFileDialog.findViewById(R.id.toolbarTitle);
        textView.setText(str2);
        textView.setVisibility(0);
        this.albumVideoGridView = (GridView) this.insideFileDialog.findViewById(R.id.albumGridView);
        String[] strArr = {"_id", "_display_name", SMUtilsWP.mediaPath, "date_added"};
        Cursor query = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id =?", new String[]{str}, null);
        ArrayList arrayList = new ArrayList(query.getCount());
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex(strArr[2]));
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
        VideoFolderAdapter gMVideoFolderAdapter = new VideoFolderAdapter(this, arrayList);
        this.videoAlbumAdapter = gMVideoFolderAdapter;
        this.albumVideoGridView.setAdapter((ListAdapter) gMVideoFolderAdapter);
        ChangeOrientation(getResources().getConfiguration().orientation);
        this.videoAlbumAdapter.notifyDataSetChanged();
        this.albumVideoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.22
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                VideoSelectActivity.sendImgList = new ArrayList<>();
                try {
                    VideoSelectActivity.sendImgList.addAll(VideoSelectActivity.myVideoList);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                VideoSelectActivity.this.lastPosition = i;
                Common.IdentifyActivity = VideoSelectActivity.TAG;
                Intent intent = new Intent(VideoSelectActivity.this, DataFileModel.class);
                intent.putExtra(Common.gImagePath, ((DataFileModel) VideoSelectActivity.sendImgList.get(i)).path);
                intent.putExtra(Common.gTotalimage, VideoSelectActivity.sendImgList.size());
                intent.putExtra(Common.gCurrenrtPosition, i);
                intent.putExtra(Common.gArrayType, "open");
                intent.putExtra(Common.gActivityname, "InnerVideoSelectActivity");
                intent.putExtra(Common.gBucketID, AppUtilsClass.bucketId);
                intent.putExtra(Common.gMediaType, ((DataFileModel) VideoSelectActivity.sendImgList.get(VideoSelectActivity.this.lastPosition)).mediaType);
                BseActivity.activity.startActivityForResult(intent, VideoSelectActivity.this.ARRAY_REFRESH_CODE);
            }
        });
        this.insideFileDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.23
            @Override 
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    VideoSelectActivity.this.dialog_count = 1;
                    VideoSelectActivity.this.onBackPressed();
                }
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.24
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.insideFileDialog.dismiss();
            }
        });
        final LinearLayout linearLayout = (LinearLayout) this.insideFileDialog.findViewById(R.id.btnPaste);
        if (linearLayout.getVisibility() == 0) {
            this.albumVideoGridView.setOnItemClickListener(null);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.25
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.MoveVideoonAlbum(file, z, str);
                linearLayout.setVisibility(8);
                VideoSelectActivity.this.UnSelectAll();
                if (VideoSelectActivity.mActionMode != null) {
                    VideoSelectActivity.mActionMode.finish();
                }
            }
        });
        this.insideFileDialog.show();
    }

    public void MoveVideoonAlbum(File file, boolean z, String str) {
        if (GetSelectedVideoList().size() > 0) {
            new MoveToAlbumExecute(GetSelectedVideoList(), file, z, str).execute(new Void[0]);
        } else {
            Toast.makeText(this, "Select Image.", 0).show();
        }
    }


    public class MoveToAlbumExecute extends AsyncTask<Void, Void, Void> {
        File albumDir;
        String bucketId;
        boolean operation;
        ArrayList<DataFileModel> pathaList;
        ProgressDialog progressDialog;
        int toast_cnt = 0;

        public MoveToAlbumExecute(ArrayList<DataFileModel> arrayList, File file, boolean z, String str) {
            this.pathaList = new ArrayList<>();
            this.progressDialog = new ProgressDialog(VideoSelectActivity.this);
            this.pathaList = arrayList;
            this.albumDir = file;
            this.operation = z;
            this.bucketId = str;
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
                for (int i = 0; i < this.pathaList.size(); i++) {
                    File file = new File(this.pathaList.get(i).path);
                    File file2 = new File(this.albumDir + File.separator + file.getName());
                    try {
                        FileUtils.CopyMoveFile(file, file2);
                        if (this.pathaList.size() - 1 == i) {
                            for (int i2 = 0; i2 < this.pathaList.size(); i2++) {
                                ContentResolver contentResolver = VideoSelectActivity.this.getContentResolver();
                                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                                contentResolver.delete(uri, "_data='" + this.pathaList.get(i2).path + "'", null);
                                StringBuilder sb = new StringBuilder();
                                sb.append("Remove MediaStore: Video ");
                                sb.append(i2);
                                Log.e(VideoSelectActivity.TAG, sb.toString());
                            }
                        }
                        file.delete();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("title", file2.getName());
                        contentValues.put("description", "Name");
                        contentValues.put(SMUtilsWP.mediaPath, file2.getPath());
                        contentValues.put("mime_type", "video/*");
                        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                        contentValues.put("bucket_id", Integer.valueOf(file2.getPath().toLowerCase(Locale.US).hashCode()));
                        contentValues.put("bucket_display_name", file2.getName().toLowerCase(Locale.US));
                        VideoSelectActivity.this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                this.toast_cnt = 1;
                for (int i3 = 0; i3 < this.pathaList.size(); i3++) {
                    File file3 = new File(this.pathaList.get(i3).path);
                    File file4 = new File(this.albumDir + File.separator + System.currentTimeMillis() + file3.getName());
                    try {
                        org.apache.commons.io.FileUtils.copyFile(file3, file4);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put(SMUtilsWP.mediaPath, file4.getPath());
                        VideoSelectActivity.this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues2);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override 
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            VideoSelectActivity.this.RefreshAdapter(this.bucketId);
            this.progressDialog.dismiss();
            AppUtilsClass.RefreshPhotoVideo(VideoSelectActivity.this);
            new GetVideoAsync().execute(new Void[0]);
            if (this.pathaList.size() == 1) {
                int i = this.toast_cnt;
                if (i == 0) {
                    Toast.makeText(VideoSelectActivity.this, "File moved successfully.", 0).show();
                } else if (i == 1) {
                    Toast.makeText(VideoSelectActivity.this, "File copied successfully.", 0).show();
                }
                VideoSelectActivity.this.insideFileDialog.dismiss();
                VideoSelectActivity.this.albumDialog.dismiss();
                return;
            }
            int i2 = this.toast_cnt;
            if (i2 == 0) {
                Toast.makeText(VideoSelectActivity.this, "Files moved successfully.", 0).show();
            } else if (i2 == 1) {
                Toast.makeText(VideoSelectActivity.this, "Files copied successfully.", 0).show();
            }
            VideoSelectActivity.this.insideFileDialog.dismiss();
            VideoSelectActivity.this.albumDialog.dismiss();
        }
    }

    public void RefreshAdapter(String str) {
        String[] strArr = {"_id", "_display_name", SMUtilsWP.mediaPath, "date_added"};
        Cursor query = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id =?", new String[]{str}, null);
        ArrayList arrayList = new ArrayList(query.getCount());
        if (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex(strArr[2]));
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
        myVideoList = arrayList2;
        arrayList2.addAll(arrayList);
        VideoFolderAdapter gMVideoFolderAdapter = new VideoFolderAdapter(this, myVideoList);
        this.videoAlbumAdapter = gMVideoFolderAdapter;
        this.albumVideoGridView.setAdapter((ListAdapter) gMVideoFolderAdapter);
        ChangeOrientation(getResources().getConfiguration().orientation);
        this.videoAlbumAdapter.notifyDataSetChanged();
    }

    public void DataOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
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
        GridView gridView = gridViewVideo;
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        gridView.setNumColumns(this.i2);
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
        VideoDialogAdapter gMVideoDialogAdapter = this.videoDialogAdapter;
        if (gMVideoDialogAdapter != null) {
            gMVideoDialogAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.albumDivider));
        }
        GridView gridView = this.videoGridView;
        if (i != 1) {
            this.i2 = Degu_MainActivity.albumDivider;
        }
        gridView.setNumColumns(this.i2);
    }

    private void ChangeOrientation(int i) {
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
        VideoFolderAdapter gMVideoFolderAdapter = this.videoAlbumAdapter;
        if (gMVideoFolderAdapter != null) {
            gMVideoFolderAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        GridView gridView = this.albumVideoGridView;
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        gridView.setNumColumns(this.i2);
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
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.26
            @Override 
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    VideoSelectActivity.this.renameTag = true;
                    File file = new File(str);
                    String parent = file.getParent();
                    String substring = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
                    File file2 = new File(parent + File.separator + editText.getText().toString().trim() + substring);
                    new MediaScanners(VideoSelectActivity.this, file2);
                    try {
                        if (FileUtils.CopyMoveFile(file, file2).exists()) {
                            AppUtilsClass.insertUri(VideoSelectActivity.this, file2);
                            file.delete();
                            ContentResolver contentResolver = VideoSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file + "'", null);
                            VideoSelectActivity.this.UnSelectAll();
                            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.26.1
                                @Override 
                                public void run() {
                                    new GetVideoAsync().execute(new Void[0]);
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
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.27
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DeleteDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_video);
        dialog.setTitle("Delete Video");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.28
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.progressTag = "FromDelete";
                new DeleteToTrashVideoExecute(VideoSelectActivity.this.GetSelectedVideoList()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.29
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.UnSelectAll();
                VideoSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                if (VideoSelectActivity.mActionMode != null) {
                    VideoSelectActivity.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class DeleteImageBGExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;
        ProgressDialog progressDialog;

        public DeleteImageBGExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            this.progressDialog = new ProgressDialog(VideoSelectActivity.this);
            this.pathList = arrayList;
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
            for (int i = 0; i < this.pathList.size(); i++) {
                try {
                    new File(this.pathList.get(i).path).delete();
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file = new File(this.pathList.get(i2).path);
                            ContentResolver contentResolver = VideoSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
                            StringBuilder sb = new StringBuilder();
                            sb.append("Delete Media Store Count: ");
                            sb.append(i2);
                            Log.e(VideoSelectActivity.TAG, sb.toString());
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
            VideoSelectActivity.this.UnSelectAll();
            this.progressDialog.dismiss();
            if (VideoSelectActivity.mActionMode != null) {
                VideoSelectActivity.mActionMode.finish();
            }
            new GetVideoAsync().execute(new Void[0]);
            Toast.makeText(VideoSelectActivity.this, "Files are deleted successfully.", 0).show();
        }
    }


    public class DeleteToTrashVideoExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteToTrashVideoExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            VideoSelectActivity.this.progressDelete = new ProgressDialog(VideoSelectActivity.this);
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            VideoSelectActivity.this.progressDelete.setMessage("Please wait a while...");
            VideoSelectActivity.this.progressDelete.setProgressStyle(0);
            VideoSelectActivity.this.progressDelete.setIndeterminate(false);
            VideoSelectActivity.this.progressDelete.setCancelable(false);
            VideoSelectActivity.this.progressDelete.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = VideoSelectActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_VIDEO).mkdirs();
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(this.pathList.get(i).path);
                File file2 = new File(FolderPath.SDCARD_PATH_DELETE_VIDEO + File.separator + file.getName());
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
                            ContentResolver contentResolver = VideoSelectActivity.this.getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                        }
                        VideoSelectActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            VideoSelectActivity.this.UnSelectAll();
            VideoSelectActivity.this.isSingleSelection = false;
            VideoSelectActivity.this.isSelectedAll = false;
            VideoSelectActivity.this.progressDelete.dismiss();
            if (VideoSelectActivity.mActionMode != null) {
                VideoSelectActivity.mActionMode.finish();
            }
            AppUtilsClass.RefreshVideoAlbum(VideoSelectActivity.this);
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.DeleteToTrashVideoExecute.1
                @Override 
                public void run() {
                    AppUtilsClass.RefreshMoment(VideoSelectActivity.this);
                    MoMomentFragment.momentAdapter.notifyDataSetChanged();
                }
            }, 1000L);
            new GetVideoAsync().execute(new Void[0]);
            Toast.makeText(VideoSelectActivity.this, "Files moved to trash successfully.", 0).show();
        }
    }

  
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.ARRAY_REFRESH_CODE && i2 == -1) {
            Log.d("FHFHFH", " - onActivityResult - " + ConstantArrayClass.videoList.size());
            if (ConstantArrayClass.videoList.size() > 0) {
                this.rl_NoDataLayout.setVisibility(8);
                gridViewVideo.setVisibility(0);
                return;
            }
            this.rl_NoDataLayout.setVisibility(0);
            gridViewVideo.setVisibility(8);
        }
    }


    public class GetVideoAsync extends AsyncTask<Void, Void, Void> {
        @Override 
        protected void onPreExecute() {
        }

        public GetVideoAsync() {
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            Log.d("CATCH", " bucketId = " + VideoSelectActivity.this.position);
            try {
                if (ConstantArrayClass.videoList != null) {
                    ConstantArrayClass.videoList.clear();
                }
                if (ConstantArrayClass.videoList == null) {
                    ConstantArrayClass.videoList = new ArrayList<>();
                }
                ConstantArrayClass.videoList.addAll(ConstantArrayClass.videoAlbumsList.get(VideoSelectActivity.this.position).pathlist);
                return null;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Log.d("CATCH", " e" + e.getMessage());
                return null;
            }
        }

        @Override 
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            VideoSelectActivity.this.SetVideoAdapter();
            try {
                new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.GetVideoAsync.1
                    @Override 
                    public void run() {
                        if (ConstantArrayClass.videoList == null) {
                            return;
                        }
                        if (ConstantArrayClass.videoList.size() > 0) {
                            VideoSelectActivity.this.rl_NoDataLayout.setVisibility(8);
                            VideoSelectActivity.gridViewVideo.setVisibility(0);
                            return;
                        }
                        VideoSelectActivity.this.rl_NoDataLayout.setVisibility(0);
                        VideoSelectActivity.gridViewVideo.setVisibility(8);
                    }
                }, 500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (VideoSelectActivity.this.renameTag) {
                AppUtilsClass.RefreshVideoAlbum(VideoSelectActivity.this);
                if (VideoSelectActivity.mActionMode != null) {
                    VideoSelectActivity.mActionMode.finish();
                }
                AppUtilsClass.ScanMomentAllDataNew(VideoSelectActivity.this);
                VideoSelectActivity.this.renameTag = false;
            }
        }
    }

    public void SetVideoAdapter() {
        new SortingDataDialog(this, ConstantArrayClass.videoList, this.sortingListener).Sorting(LoginPreferenceUtilsData.GetStringData(getApplicationContext(), SortingDataDialog.SortingName), LoginPreferenceUtilsData.GetStringData(getApplicationContext(), SortingDataDialog.SortingType));
        BothAlbumSelectAdapter gMInnerBothAlbumSelectAdapter = new BothAlbumSelectAdapter(this, getApplicationContext(), ConstantArrayClass.videoList);
        innerBothAlbumSelectAdapter = gMInnerBothAlbumSelectAdapter;
        gridViewVideo.setAdapter((ListAdapter) gMInnerBothAlbumSelectAdapter);
        this.loader.setVisibility(8);
        gridViewVideo.setVisibility(0);
        DataOrientation(getResources().getConfiguration().orientation);
        innerBothAlbumSelectAdapter.setItemClickCallback(new OnClickListener<ArrayList<Object>, Integer, View>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.30
            @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
            public void onClickMoreListener(ArrayList<Object> arrayList, Integer num, View view) {
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
            public void onClickListener(final ArrayList<Object> arrayList, final Integer num) {
                        String str = "";
                        VideoSelectActivity.sendImgList = new ArrayList<>();
                        VideoSelectActivity.sendImgList = arrayList;
                        if (VideoSelectActivity.this.isSingleSelection) {
                            VideoSelectActivity.this.SelectSingleImage(VideoSelectActivity.sendImgList, num.intValue());
                            return;
                        }
                        VideoSelectActivity.this.lastPosition = num.intValue();
                        Common.IdentifyActivity = VideoSelectActivity.TAG;
                        File file = new File(((DataFileModel) VideoSelectActivity.sendImgList.get(VideoSelectActivity.this.lastPosition)).path);
                        try {
                            if (file.exists()) {
                                String name = file.getName();
                                str = name.substring(name.lastIndexOf("."));
                            }
                        } catch (Exception unused) {
                        }
                        String str2 = (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : ExifInterface.GPS_MEASUREMENT_3D;
                        if (((DataFileModel) VideoSelectActivity.sendImgList.get(VideoSelectActivity.this.lastPosition)).mediaType != null) {
                            str2 = ((DataFileModel) VideoSelectActivity.sendImgList.get(VideoSelectActivity.this.lastPosition)).mediaType;
                        }
                        Intent intent = new Intent(VideoSelectActivity.this, PreviewActivity.class);
                        intent.putExtra(Common.gImagePath, ((DataFileModel) VideoSelectActivity.sendImgList.get(num.intValue())).path);
                        intent.putExtra(Common.gTotalimage, VideoSelectActivity.sendImgList.size());
                        intent.putExtra(Common.gCurrenrtPosition, num);
                        intent.putExtra(Common.gMediaType, str2);
                        intent.putExtra(Common.gArrayType, "open");
                        intent.putExtra(Common.gActivityname, "InnerVideoSelectActivity");
                        BseActivity.activity.startActivityForResult(intent, VideoSelectActivity.this.ARRAY_REFRESH_CODE);

            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
            public void onLongClickListener(ArrayList<Object> arrayList, Integer num) {
                VideoSelectActivity.sendImgList = new ArrayList<>();
                VideoSelectActivity.sendImgList = arrayList;
                if (VideoSelectActivity.mActionMode != null) {
                    VideoSelectActivity.mActionMode = null;
                }
                if (VideoSelectActivity.mActionMode == null) {
                    VideoSelectActivity gMVideoSelectActivity = VideoSelectActivity.this;
                    VideoSelectActivity.mActionMode = gMVideoSelectActivity.startSupportActionMode(gMVideoSelectActivity.mActionModeCallback);
                }
                VideoSelectActivity.this.SelectSingleImage(VideoSelectActivity.sendImgList, num.intValue());
            }
        });
    }

    public void SortingCallBack() {
        this.sortingListener = new VideoSorting();
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
                        VideoSelectActivity.this.finish();
                        try {
                            new SortingDataDialog(VideoSelectActivity.this, ConstantArrayClass.videoList, VideoSelectActivity.this.sortingListener).Sorting(AppUtilsClass.lastModifiedStr, AppUtilsClass.descendingStr);
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


    public class VideoSorting implements SortingListener {
        VideoSorting() {
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.SortingListener
        public void Sorting(ArrayList<Object> arrayList) {
            VideoSelectActivity.sendImgList = new ArrayList<>();
            VideoSelectActivity.sendImgList = arrayList;
            VideoSelectActivity gMVideoSelectActivity = VideoSelectActivity.this;
            VideoSelectActivity.innerBothAlbumSelectAdapter = new BothAlbumSelectAdapter(gMVideoSelectActivity, gMVideoSelectActivity.getApplicationContext(), VideoSelectActivity.sendImgList);
            VideoSelectActivity.gridViewVideo.setAdapter((ListAdapter) VideoSelectActivity.innerBothAlbumSelectAdapter);
            VideoSelectActivity.this.loader.setVisibility(8);
            VideoSelectActivity.gridViewVideo.setVisibility(0);
            VideoSelectActivity gMVideoSelectActivity2 = VideoSelectActivity.this;
            gMVideoSelectActivity2.DataOrientation(gMVideoSelectActivity2.getResources().getConfiguration().orientation);
            VideoSelectActivity.innerBothAlbumSelectAdapter.notifyDataSetChanged();
            VideoSelectActivity.innerBothAlbumSelectAdapter.setItemClickCallback(new OnClickListener<ArrayList<Object>, Integer, View>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.VideoSorting.1
                @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
                public void onClickMoreListener(ArrayList<Object> arrayList2, Integer num, View view) {
                }

                @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
                public void onClickListener(final ArrayList<Object> arrayList2, final Integer num) {
                            String str = "";
                            VideoSelectActivity.sendImgList = new ArrayList<>();
                            VideoSelectActivity.sendImgList = arrayList2;
                            if (VideoSelectActivity.this.isSingleSelection) {
                                VideoSelectActivity.this.SelectSingleImage(VideoSelectActivity.sendImgList, num.intValue());
                                return;
                            }
                            VideoSelectActivity.this.lastPosition = num.intValue();
                            Common.IdentifyActivity = VideoSelectActivity.TAG;
                            File file = new File(((DataFileModel) VideoSelectActivity.sendImgList.get(VideoSelectActivity.this.lastPosition)).path);
                            try {
                                if (file.exists()) {
                                    String name = file.getName();
                                    str = name.substring(name.lastIndexOf("."));
                                }
                            } catch (Exception unused) {
                            }
                            String str2 = (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : ExifInterface.GPS_MEASUREMENT_3D;
                            if (((DataFileModel) VideoSelectActivity.sendImgList.get(VideoSelectActivity.this.lastPosition)).mediaType != null) {
                                str2 = ((DataFileModel) VideoSelectActivity.sendImgList.get(VideoSelectActivity.this.lastPosition)).mediaType;
                            }
                            Intent intent = new Intent(VideoSelectActivity.this, PreviewActivity.class);
                            intent.putExtra(Common.gImagePath, ((DataFileModel) VideoSelectActivity.sendImgList.get(num.intValue())).path);
                            intent.putExtra(Common.gTotalimage, VideoSelectActivity.sendImgList.size());
                            intent.putExtra(Common.gCurrenrtPosition, num);
                            intent.putExtra(Common.gMediaType, str2);
                            intent.putExtra(Common.gArrayType, "open");
                            intent.putExtra(Common.gActivityname, "InnerVideoSelectActivity");
                            BseActivity.activity.startActivityForResult(intent, VideoSelectActivity.this.ARRAY_REFRESH_CODE);
                }

                @Override // hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMListeners.OnClickListener
                public void onLongClickListener(ArrayList<Object> arrayList2, Integer num) {
                    VideoSelectActivity.sendImgList = new ArrayList<>();
                    VideoSelectActivity.sendImgList = arrayList2;
                    if (VideoSelectActivity.mActionMode != null) {
                        VideoSelectActivity.mActionMode = null;
                    }
                    if (VideoSelectActivity.mActionMode == null) {
                        VideoSelectActivity.mActionMode = VideoSelectActivity.this.startSupportActionMode(VideoSelectActivity.this.mActionModeCallback);
                    }
                    VideoSelectActivity.this.SelectSingleImage(VideoSelectActivity.sendImgList, num.intValue());
                }
            });
        }
    }

    public void FolderDialog(ArrayList<FolderModel> arrayList, boolean z) {
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
        AccessFolder11 accessFolder11 = new AccessFolder11(this, arrayList, z);
        this.accessFolder11 = accessFolder11;
        recyclerView.setAdapter(accessFolder11);
        this.dialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.32
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.UnSelectAll();
                VideoSelectActivity.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(BseActivity.activity, R.drawable.action_select));
                if (VideoSelectActivity.mActionMode != null) {
                    VideoSelectActivity.mActionMode.finish();
                }
                VideoSelectActivity.this.dialog.dismiss();
            }
        });
        this.dialog.findViewById(R.id.txtAddNewFolder).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.33
            @Override 
            public void onClick(View view) {
                VideoSelectActivity.this.AddNewFolderDialog();
            }
        });
        this.dialog.show();
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
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.AccessFolder11.1
                @Override 
                public void onClick(View view) {
                    VideoSelectActivity.this.dialog.dismiss();
                    VideoSelectActivity.this.CopyMoveData(AccessFolder11.this.folderList.get(i).getFolderPath(), AccessFolder11.this.abool);
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
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.34
            @Override 
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    VideoSelectActivity gMVideoSelectActivity = VideoSelectActivity.this;
                    gMVideoSelectActivity.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    File file = new File(VideoSelectActivity.this.folder_path);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else {
                        Toast.makeText(BseActivity.activity, "Already Exists", 0).show();
                    }
                    AppUtilsClass.getAccessFolder11();
                    if (VideoSelectActivity.this.accessFolder11 != null) {
                        VideoSelectActivity.this.accessFolder11.refreshData(AppUtilsClass.accessfolderListFor11);
                    }
                    dialog.dismiss();
                    return;
                }
                editText.setError("Please Enter Folder Name");
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideoSelectActivity.35
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void CopyMoveData(String str, boolean z) {
        for (int i = 0; i < GetSelectedVideoList().size(); i++) {
            File file = new File(GetSelectedVideoList().get(i).path);
            File file2 = new File(str + File.separator + file.getName());
            new MediaScanners(GalleryAppClass.getInstance(), file2);
            try {
                FileUtils.CopyMoveFile(file, file2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!z) {
            Toast.makeText(this, "Video Copy Successfully", 0).show();
            UnSelectAll();
            this.isSingleSelection = false;
            this.isSelectedAll = false;
            ActionMode actionMode = mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            AppUtilsClass.ScanVideoAlbumListData(this);
            AppUtilsClass.RefreshMoment(this);
            MoMomentFragment.momentAdapter.notifyDataSetChanged();
        }
        if (z) {
            this.progressTag = "FromPermanentDelete";
            this.toastTag = "MoveData";
            new DeleteVideoExecute(GetSelectedVideoList()).execute(new Void[0]);
        }
    }


    public class DeleteVideoExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteVideoExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            VideoSelectActivity.this.progressPermanentDelete = new ProgressDialog(VideoSelectActivity.this);
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            VideoSelectActivity.this.progressPermanentDelete.setMessage("Please wait a while...");
            VideoSelectActivity.this.progressPermanentDelete.setProgressStyle(0);
            VideoSelectActivity.this.progressPermanentDelete.setIndeterminate(false);
            VideoSelectActivity.this.progressPermanentDelete.setCancelable(false);
            VideoSelectActivity.this.progressPermanentDelete.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            new ArrayList();
            new ArrayList();
            ArrayList<String> listString = VideoSelectActivity.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_IMAGE).mkdirs();
            try {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < this.pathList.size()) {
                    if (this.pathList.size() - 1 == i) {
                        while (i >= 0) {
                            if (Build.VERSION.SDK_INT >= 30) {
                                arrayList.add(ContentUris.withAppendedId(MediaStore.Video.Media.getContentUri("external"), AppUtilsClass.getFilePathToMediaID(this.pathList.get(i).path, VideoSelectActivity.this)));
                            }
                            this.pathList.remove(i);
                            i--;
                        }
                        VideoSelectActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                    i++;
                }
                if (Build.VERSION.SDK_INT < 30) {
                    return null;
                }
                AppUtilsClass.requestIntent(arrayList, VideoSelectActivity.this);
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
                VideoSelectActivity.this.UnSelectAll();
                VideoSelectActivity.this.isSingleSelection = false;
                VideoSelectActivity.this.isSelectedAll = false;
                VideoSelectActivity.this.progressPermanentDelete.dismiss();
                if (VideoSelectActivity.mActionMode != null) {
                    VideoSelectActivity.mActionMode.finish();
                }
                AppUtilsClass.ScanVideoAlbumListData(VideoSelectActivity.this);
                AppUtilsClass.RefreshMoment(VideoSelectActivity.this);
                MoMomentFragment.momentAdapter.notifyDataSetChanged();
                new GetVideoAsync().execute(new Void[0]);
                if (VideoSelectActivity.this.toastTag.equals("MoveData")) {
                    Toast.makeText(VideoSelectActivity.this, "Files Move Successfully.", 0).show();
                } else {
                    Toast.makeText(VideoSelectActivity.this, "Files are deleted successfully.", 0).show();
                }
            }
        }
    }
}
