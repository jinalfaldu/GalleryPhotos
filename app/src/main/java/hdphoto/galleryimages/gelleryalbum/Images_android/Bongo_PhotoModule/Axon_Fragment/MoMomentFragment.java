package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Typeface;
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
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.StandaloneActionMode;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.ExternalStorageHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.WrapperGridlayoutManager;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_BothDataActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.PreviewActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.DeguPrivateActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.AlbumAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.MomentAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.MomentSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DialogAlbumModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;


public class MoMomentFragment extends BaseFragment {
    static int CAMERA_PHOTO = 111;
    static int STORAGE_PERMISSION = 222;
    static String TAG = "MomentFragment";
    public static RecyclerView fragmnetimagegridView;
    public static MomentAdapter momentAdapter;
    public static RelativeLayout rlPhotoLayout;
    AccessFolder11 accessFolder11;
    Activity activity;
    private Dialog albumDialog;
    private GridView albumGridView;
    int bpos;
    ArrayList<Object> callBackAlbumsList;
    ContentObserver contentObserver;
    Context context;
    int currentFirstVisibleItem;
    Dialog deletAlbumDialog;
    Dialog detailAlbumDialog;
    BottomSheetDialog dialog;
    private AlbumAdapter dialogAlbumAdapter;
    WrapperGridlayoutManager gridLayoutManager;
    Handler handler;
    int i2;
    Uri imageToUploadUri;
    ImageView img_return_top;
    ProgressBar loader;
    ActionMode mActionMode;
    private Menu mMenu;
    MomentSortingListener momentSortingListener;
    ProgressDialog progressDelete;
    ProgressDialog progressLock;
    ProgressDialog progressPermanentDelete;
    String progressTag;
    int pv;
    Thread thread;
    TextView tv_ErrorDisplay;
    Typeface typeface;
    View view1;
    int countSelected = 0;
    boolean isSelectedAll = false;
    boolean isSingleSelection = false;
    public ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MoMomentFragment.this.mMenu = menu;
            actionMode.getMenuInflater().inflate(R.menu.moment_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            int i = Build.VERSION.SDK_INT;
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.Copy:
                    if (MoMomentFragment.this.GetSelectedList().size() >= 1) {
                        MoMomentFragment.this.GetAlbumDialog(false);
                    } else {
                        Toast.makeText(MoMomentFragment.this.activity, "Select File.", 0).show();
                    }
                    return true;
                case R.id.Delete:
                    if (MoMomentFragment.this.GetSelectedList().size() >= 1) {
                        MoMomentFragment.this.DeleteDialog();
                    } else {
                        Toast.makeText(MoMomentFragment.this.activity, "Select File.", 0).show();
                    }
                    return true;
                case R.id.Lock:
                    if (MoMomentFragment.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                        MoMomentFragment gMMomentFragment = MoMomentFragment.this;
                        gMMomentFragment.SecurityDialog(gMMomentFragment.GetSelectedList());
                        actionMode.finish();
                    } else if (MoMomentFragment.this.GetSelectedList().size() >= 1) {
                        MoMomentFragment.this.LockDialog();
                    } else {
                        Toast.makeText(MoMomentFragment.this.activity, "Select at least one file.", 0).show();
                    }
                    return true;
                case R.id.Move:
                    if (MoMomentFragment.this.GetSelectedList().size() >= 1) {
                        MoMomentFragment.this.GetAlbumDialog(true);
                    } else {
                        Toast.makeText(MoMomentFragment.this.activity, "Select File.", 0).show();
                    }
                    return true;
                case R.id.Selector:
                    if (MoMomentFragment.this.isSelectedAll) {
                        MoMomentFragment.this.UnSelectAll();
                        MoMomentFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(MoMomentFragment.this.activity, R.drawable.action_select));
                        actionMode.finish();
                    } else {
                        MoMomentFragment.this.SelectAll();
                        MoMomentFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(MoMomentFragment.this.activity, R.drawable.action_unselect));
                    }
                    return true;
                case R.id.Share:
                    if (MoMomentFragment.this.GetSelectedList().size() >= 1) {
                        MoMomentFragment gMMomentFragment2 = MoMomentFragment.this;
                        gMMomentFragment2.ShareBoth(gMMomentFragment2.GetSelectedList());
                    } else {
                        Toast.makeText(MoMomentFragment.this.getActivity(), "Select File.", 0).show();
                    }
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            MoMomentFragment.this.mActionMode = null;
            MoMomentFragment.this.UnSelectAll();
            int i = Build.VERSION.SDK_INT;
        }
    };
    int dialog_count = 0;
    String toastTag = "";
    private String folder_path = "";

    @Override
    public void onResume() {
        if (Degu_MainActivity.oriTag.equals("SettingActivity")) {
            DataOrientation(this.activity.getResources().getConfiguration().orientation);
        }
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DataOrientation(configuration.orientation);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_moment, viewGroup, false);
        this.activity = getActivity();
        this.context = getContext();
        this.preferenceClass = new PrefClass(getActivity());
        this.view1 = inflate.findViewById(R.id.layout_album_select);
//        this.typeface = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Roboto-Medium.ttf");
        Degu_MainActivity.ivSelectAll.setImageDrawable(getResources().getDrawable(R.drawable.action_select));
        rlPhotoLayout = (RelativeLayout) inflate.findViewById(R.id.hintMainPhotoLayout);
        this.tv_ErrorDisplay = (TextView) inflate.findViewById(R.id.text_view_error);
        this.loader = (ProgressBar) inflate.findViewById(R.id.loader);
        this.img_return_top = (ImageView) inflate.findViewById(R.id.img_return_top);
        fragmnetimagegridView = (RecyclerView) inflate.findViewById(R.id.grid_view_album_select);
        this.tv_ErrorDisplay.setVisibility(4);
        HiddenView();
        SortingCallBack();
        this.img_return_top.setVisibility(8);
        fragmnetimagegridView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMMomentFragment.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                MoMomentFragment gMMomentFragment = MoMomentFragment.this;
                gMMomentFragment.currentFirstVisibleItem = gMMomentFragment.gridLayoutManager.findFirstVisibleItemPosition();
                if (MoMomentFragment.this.currentFirstVisibleItem == 0) {
                    MoMomentFragment.this.img_return_top.setVisibility(8);
                } else if (i2 > 0) {
                    MoMomentFragment.this.img_return_top.setVisibility(8);
                } else if (i2 < 0) {
                    MoMomentFragment.this.img_return_top.setVisibility(0);
                }
            }
        });
        this.img_return_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.fragmnetimagegridView.smoothScrollToPosition(0);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MoMomentFragment.this.isCheckImage(ConstantArrayClass.albumsList);
            }
        }, 1000L);
        SetAdapterData();
        Degu_MainActivity.addMomentAlbum.setVisibility(0);
        Degu_MainActivity.rl_imageBtn.setVisibility(8);
        Degu_MainActivity.rl_videoBtn.setVisibility(8);
        Degu_MainActivity.addMomentAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.AddFolderDialog();
            }
        });
        return inflate;
    }

    public void AddFolderDialog() {
        final Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_add_folder);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView textView = (TextView) dialog.findViewById(R.id.txtTitle);
        textView.setText("Add New Folder");
        textView.setTypeface(this.typeface);
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    MoMomentFragment gMMomentFragment = MoMomentFragment.this;
                    gMMomentFragment.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    Intent intent = new Intent(MoMomentFragment.this.activity, Degu_BothDataActivity.class);
                    intent.putExtra(Common.gAlbumPath, MoMomentFragment.this.folder_path);
                    MoMomentFragment.this.activity.startActivityForResult(intent, 100);
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

    public void DeleteDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_file);
        dialog.setTitle("Delete File");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.progressTag = "FromDelete";
                new DeleteToTrashMomentExecute(MoMomentFragment.this.GetSelectedList()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.UnSelectAll();
                MoMomentFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(MoMomentFragment.this.activity, R.drawable.action_select));
                if (MoMomentFragment.this.mActionMode != null) {
                    MoMomentFragment.this.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class DeleteToTrashMomentExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteToTrashMomentExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            MoMomentFragment.this.progressDelete = new ProgressDialog(MoMomentFragment.this.activity);
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            MoMomentFragment.this.progressDelete.setMessage("Please wait a while...");
            MoMomentFragment.this.progressDelete.setProgressStyle(0);
            MoMomentFragment.this.progressDelete.setIndeterminate(false);
            MoMomentFragment.this.progressDelete.setCancelable(false);
            MoMomentFragment.this.progressDelete.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = null;
            Uri uri;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> listString = MoMomentFragment.this.preferenceClass.getListString(Common.gOldPath);
            String str = null;
            int i = 0;
            while (i < this.pathList.size()) {
                File file2 = new File(this.pathList.get(i).path);
                String mediaType = this.pathList.get(i).getMediaType();
                if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    str = FolderPath.SDCARD_PATH_DELETE_IMAGE;
                } else if (mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    str = FolderPath.SDCARD_PATH_DELETE_VIDEO;
                }
                String str2 = str;
                File file3 = new File(str2 + File.separator + file2.getName());
                String parent = file3.getParent();
                try {
                    FileUtils.CopyMoveFile(file2, file3);
                    long j = this.pathList.get(i).id;
                    String str3 = this.pathList.get(i).name;
                    String str4 = this.pathList.get(i).path;
                    listString.add(str4);
                    arrayList.add(new DataFileModel(j, str3, str4, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            new File(this.pathList.get(i2).path).delete();
                            ContentResolver contentResolver = MoMomentFragment.this.getActivity().getContentResolver();
                            if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            } else {
                                uri = mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D) ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : null;
                            }
                            contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
                            StringBuilder sb = new StringBuilder();
                            sb.append("Remove TO MediaStore Count: ");
                            sb.append(i2);
                            Log.e(MoMomentFragment.TAG, sb.toString());
                        }
                        MoMomentFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
                str = str2;
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            MoMomentFragment.this.UnSelectAll();
            MoMomentFragment.this.isSingleSelection = false;
            MoMomentFragment.this.isSelectedAll = false;
            MoMomentFragment.this.progressDelete.dismiss();
            if (MoMomentFragment.this.mActionMode != null) {
                MoMomentFragment.this.mActionMode.finish();
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo(MoMomentFragment.this.getActivity());
                }
            }, 500L);
            Toast.makeText(MoMomentFragment.this.getActivity(), "Files are deleted successfully.", 0).show();
        }
    }

    public void SecurityDialog(ArrayList<DataFileModel> arrayList) {
        ConstantArrayClass.firstTimeLockDataArray = arrayList;
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_security);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.textDesc)).setText("Set your security lock for hide photos & videos!");
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FolderPath.lock_screen = 1;
                MoMomentFragment.this.startActivity(new Intent(MoMomentFragment.this.getActivity(), DeguPrivateActivity.class));
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
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_gm_lock_files);
        dialog.setTitle("Lock Files");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.LockFiles();
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

    public void LockFiles() {
        new ArrayList().clear();
        ArrayList<DataFileModel> GetSelectedList = GetSelectedList();
        if (GetSelectedList.size() <= 0) {
            Toast.makeText(getActivity(), "Select File.", 0).show();
        } else if (ExternalStorageHelper.isExternalStorageReadableAndWritable()) {
            this.progressTag = "FromLock";
            new LockFilesExecute(GetSelectedList).execute(new Void[0]);
        } else {
            Toast.makeText(getActivity(), "not Read Write", 0).show();
        }
    }


    public class LockFilesExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public LockFilesExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            MoMomentFragment.this.progressLock = new ProgressDialog(MoMomentFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            MoMomentFragment.this.progressLock.setMessage("Please wait a while...");
            MoMomentFragment.this.progressLock.setProgressStyle(0);
            MoMomentFragment.this.progressLock.setIndeterminate(false);
            MoMomentFragment.this.progressLock.setCancelable(false);
            MoMomentFragment.this.progressLock.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = null;
            Uri uri;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> listString = MoMomentFragment.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_IMAGE).mkdirs();
            new File(FolderPath.SDCARD_PATH_VIDEO).mkdirs();
            new File(FolderPath.SDCARD_PATH_FOR11).mkdirs();
            new ArrayList();
            String str = null;
            int i = 0;
            while (i < this.pathList.size()) {
                File file2 = new File(this.pathList.get(i).path);
                String mediaType = this.pathList.get(i).getMediaType();
                if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    str = FolderPath.SDCARD_PATH_IMAGE;
                } else if (mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    str = FolderPath.SDCARD_PATH_VIDEO;
                }
                String str2 = str;
                File file3 = new File(str2 + File.separator + file2.getName());
                String parent = file3.getParent();
                try {
                    FileUtils.CopyMoveFile(file2, file3);
                    long j = this.pathList.get(i).id;
                    String str3 = this.pathList.get(i).name;
                    String str4 = this.pathList.get(i).path;
                    listString.add(str4);
                    arrayList.add(new DataFileModel(j, str3, str4, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            new File(this.pathList.get(i2).path).delete();
                            ContentResolver contentResolver = MoMomentFragment.this.getActivity().getContentResolver();
                            if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            } else {
                                uri = mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D) ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : null;
                            }
                            try {
                                contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        MoMomentFragment.momentAdapter.RemoveItem(this.pathList);
                        MoMomentFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                i++;
                str = str2;
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            MoMomentFragment.this.isSingleSelection = false;
            MoMomentFragment.this.isSelectedAll = false;
            MoMomentFragment.this.progressLock.dismiss();
            if (MoMomentFragment.this.mActionMode != null) {
                MoMomentFragment.this.mActionMode.finish();
            }
            AppUtilsClass.RefreshImageAlbum(MoMomentFragment.this.getActivity());
            AppUtilsClass.RefreshVideoAlbum(MoMomentFragment.this.getActivity());
            Toast.makeText(MoMomentFragment.this.getActivity(), "Files moved to vault successfully.", 0).show();
        }
    }

    public static File CopyMoveFile(File file, File file2) throws Exception {
        try {
            if (!file.isDirectory()) {
                file2.createNewFile();
                FileChannel channel = new FileInputStream(file).getChannel();
                channel.transferTo(0L, channel.size(), new FileOutputStream(file2).getChannel());
                Toast.makeText(GalleryAppClass.getInstance(), "Image Copy Successfully", 0).show();
                new MediaScanners(GalleryAppClass.getInstance(), file2);
                return file2;
            } else if (!file.getPath().equals(file2.getPath())) {
                File CopyMoveCreateDirectory = CopyMoveCreateDirectory(file2, file.getName());
                for (File file3 : file.listFiles()) {
                    CopyMoveFile(file3, CopyMoveCreateDirectory);
                }
                return CopyMoveCreateDirectory;
            } else {
                throw new Exception();
            }
        } catch (Exception unused) {
            Toast.makeText(GalleryAppClass.getInstance(), "Sorry we can't Copy file. try with other file.", 0).show();
            throw new Exception(String.format("Error copying %s", file.getName()));
        }
    }

    public static File CopyMoveCreateDirectory(File file, String str) throws Exception {
        File file2 = new File(file, str);
        if (file2.mkdirs()) {
            return file2;
        }
        if (file2.exists()) {
            throw new Exception(String.format("%s already exists", str));
        }
        throw new Exception(String.format("Error creating %s", str));
    }

    public void GetAlbumDialog(final boolean z) {
        Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
        this.albumDialog = dialog;
        dialog.requestWindowFeature(1);
        this.albumDialog.setCancelable(false);
        this.albumDialog.setContentView(R.layout.dg_folder);
        ImageView imageView = (ImageView) this.albumDialog.findViewById(R.id.btnBack);
        TextView textView = (TextView) this.albumDialog.findViewById(R.id.toolbarTitle);
        textView.setText("Select File Folder");
        textView.setVisibility(0);
        this.albumGridView = (GridView) this.albumDialog.findViewById(R.id.albumGridView);
        Cursor query = getActivity().getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id", "bucket_id", "bucket_display_name", SMUtilsWP.mediaPath}, "media_type=1 OR media_type=3", null, null);
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
                    gMDialogAlbumModel.pathlist = GetBothDataPathList("" + gMDialogAlbumModel.bucket_id);
                    arrayList.add(gMDialogAlbumModel);
                    arrayList2.add(gMDialogAlbumModel.bucket_id);
                }
            }
            query.close();
        }
        AlbumAdapter gMAlbumAdapter = new AlbumAdapter(getActivity(), arrayList);
        this.dialogAlbumAdapter = gMAlbumAdapter;
        this.albumGridView.setAdapter((ListAdapter) gMAlbumAdapter);
        orientationBasedUIAlbum(getResources().getConfiguration().orientation);
        this.albumGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList arrayList3 = arrayList;
                if (arrayList3 == null || arrayList3.isEmpty()) {
                    return;
                }
                File file = new File(((DialogAlbumModel) arrayList.get(i)).folderPath);
                String str = ((DialogAlbumModel) arrayList.get(i)).bucket_id;
                String str2 = ((DialogAlbumModel) arrayList.get(i)).foldername;
                MoMomentFragment.this.CopyMoveData(file.getAbsolutePath(), z);
                if (MoMomentFragment.this.albumDialog != null || MoMomentFragment.this.albumDialog.isShowing()) {
                    MoMomentFragment.this.albumDialog.dismiss();
                }
                if (MoMomentFragment.this.mActionMode != null) {
                    MoMomentFragment.this.mActionMode.finish();
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.UnSelectAll();
                MoMomentFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(MoMomentFragment.this.activity, R.drawable.action_select));
                if (MoMomentFragment.this.mActionMode != null) {
                    MoMomentFragment.this.mActionMode.finish();
                }
                MoMomentFragment.this.albumDialog.dismiss();
            }
        });
        this.albumDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && MoMomentFragment.this.dialog_count == 0) {
                    MoMomentFragment.this.getActivity().onBackPressed();
                    return true;
                }
                MoMomentFragment.this.dialog_count = 0;
                return false;
            }
        });
        this.albumDialog.show();
    }

    public String GetParentPath(String str) {
        return new File(str).getAbsoluteFile().getParent();
    }

    public ArrayList<String> GetBothDataPathList(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = this.activity.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{SMUtilsWP.mediaPath, "bucket_display_name"}, "bucket_id = ?", new String[]{str}, null);
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

    public void CopyMoveData(String str, boolean z) {
        for (int i = 0; i < GetSelectedList().size(); i++) {
            File file = new File(GetSelectedList().get(i).path);
            File file2 = new File(str + File.separator + file.getName());
            try {
                FileUtils.CopyMoveFile(file, file2);
                AppUtilsClass.insertUri(getActivity(), file2);
                if (z) {
                    file.delete();
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    Uri contentUri = MediaStore.Files.getContentUri("external");
                    contentResolver.delete(contentUri, "_data='" + file.getPath() + "'", null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!z) {
            Toast.makeText(GalleryAppClass.getInstance(), "Files Copy Successfully", 0).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppUtilsClass.RefreshPhotoVideo(MoMomentFragment.this.getActivity());
            }
        }, 500L);
        if (z) {
            Toast.makeText(GalleryAppClass.getInstance(), "Files Move Successfully", 0).show();
        }
    }

    private void orientationBasedUIAlbum(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (this.dialogAlbumAdapter != null) {
            int i2 = displayMetrics.widthPixels;
            this.dialogAlbumAdapter.setLayoutParams(i == 1 ? i2 / 2 : i2 / 4);
        }
        this.albumGridView.setNumColumns(i != 1 ? 4 : 2);
    }

    public void DataOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
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
        MomentAdapter gMMomentAdapter = momentAdapter;
        if (gMMomentAdapter != null) {
            gMMomentAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        WrapperGridlayoutManager wrapperGridlayoutManager = new WrapperGridlayoutManager(this.activity, this.i2);
        this.gridLayoutManager = wrapperGridlayoutManager;
        wrapperGridlayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMMomentFragment.18
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i3) {
                if (MoMomentFragment.momentAdapter == null || MoMomentFragment.momentAdapter.getItemViewType(i3) != 0) {
                    return 1;
                }
                return MoMomentFragment.this.i2;
            }
        });
        fragmnetimagegridView.setLayoutManager(this.gridLayoutManager);
    }

    private void RenameDialog(final ArrayList<Object> arrayList, final Integer num) {
        final Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_rename_folder);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        editText.setText(((DataFileModel) arrayList.get(num.intValue())).folderName);
        final String obj = editText.getText().toString();
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                if (!trim.equals(obj)) {
                    new RenameExecute(arrayList, num, trim).execute(new Void[0]);
                    dialog.dismiss();
                    return;
                }
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


    public class RenameExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<Object> arrayList;
        String newAlbumFolder;
        int pos;
        ProgressDialog progressDialog;

        public RenameExecute(ArrayList<Object> arrayList, Integer num, String str) {
            this.arrayList = new ArrayList<>();
            this.progressDialog = new ProgressDialog(MoMomentFragment.this.context);
            this.arrayList = arrayList;
            this.pos = num.intValue();
            this.newAlbumFolder = str;
        }

        @Override
        public void onPreExecute() {
            MoMomentFragment.this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RenameExecute.this.progressDialog.setMessage("Please wait a while...");
                    RenameExecute.this.progressDialog.setProgressStyle(0);
                    RenameExecute.this.progressDialog.setIndeterminate(false);
                    RenameExecute.this.progressDialog.setCancelable(false);
                    RenameExecute.this.progressDialog.show();
                }
            });
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = new File(((DataFileModel) this.arrayList.get(this.pos)).folderPath);
            File file2 = new File(file.getParent() + File.separator + this.newAlbumFolder);
            String[] list = file.list();
            if (list != null) {
                for (String str : list) {
                    File file3 = new File(file + File.separator + str);
                    File file4 = new File(file2 + File.separator + str);
                    if (file3.exists()) {
                        String substring = file3.getPath().substring(file3.getPath().lastIndexOf("/") + 1);
                        if (substring.endsWith(".jpg") || substring.endsWith(".JPG") || substring.endsWith(".jpeg") || substring.endsWith(".JPEG") || substring.endsWith(".png") || substring.endsWith(".PNG") || substring.endsWith(".gif") || substring.endsWith(".GIF")) {
                            file2.mkdirs();
                            if (file3.renameTo(file4)) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                                contentValues.put("mime_type", "image/*");
                                contentValues.put(SMUtilsWP.mediaPath, file4.getPath());
                                MoMomentFragment.this.activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                                MoMomentFragment.this.activity.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data='" + file3.getPath() + "'", null);
                            }
                        }
                    }
                }
            }
            if (file.list() == null) {
                file.delete();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            MoMomentFragment.momentAdapter.notifyDataSetChanged();
            this.progressDialog.dismiss();
        }
    }

    public void SelectSingleFile(ArrayList<Object> arrayList, int i) {
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
            ActionMode actionMode = this.mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
        }
        if (GetSelectedList().size() != 0) {
            this.mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        momentAdapter.notifyDataSetChanged();
    }

    public ArrayList<DataFileModel> GetSelectedList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = ConstantArrayClass.albumsList.size();
        for (int i = 0; i < size; i++) {
            if ((ConstantArrayClass.albumsList.get(i) instanceof DataFileModel) && ((DataFileModel) ConstantArrayClass.albumsList.get(i)).isSelected) {
                arrayList.add((DataFileModel) ConstantArrayClass.albumsList.get(i));
            }
        }
        return arrayList;
    }

    public void UnSelectAll() {
        int size = ConstantArrayClass.albumsList.size();
        for (int i = 0; i < size; i++) {
            if (ConstantArrayClass.albumsList.get(i) instanceof DataFileModel) {
                ((DataFileModel) ConstantArrayClass.albumsList.get(i)).isSelected = false;
            }
        }
        if (GetSelectedList().size() != 0) {
            this.mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = 0;
        this.isSelectedAll = false;
        this.isSingleSelection = false;
        momentAdapter.notifyDataSetChanged();
    }

    public void SelectAll() {
        int size = ConstantArrayClass.albumsList.size();
        for (int i = 0; i < size; i++) {
            if (ConstantArrayClass.albumsList.get(i) instanceof DataFileModel) {
                ((DataFileModel) ConstantArrayClass.albumsList.get(i)).isSelected = true;
            }
        }
        if (GetSelectedList().size() != 0) {
            this.mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = ConstantArrayClass.albumsList.size();
        this.isSelectedAll = true;
        this.isSingleSelection = true;
        momentAdapter.notifyDataSetChanged();
    }

    private void DetailDialog(ArrayList<Object> arrayList, Integer num) {
        String[] strArr;
        Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        this.detailAlbumDialog = dialog;
        dialog.requestWindowFeature(1);
        int i = 0;
        this.detailAlbumDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.detailAlbumDialog.setContentView(R.layout.dialog_detail_folder);
        this.detailAlbumDialog.setTitle("Details");
        this.detailAlbumDialog.setCancelable(false);
        this.detailAlbumDialog.setCanceledOnTouchOutside(false);
        TextView textView = (TextView) this.detailAlbumDialog.findViewById(R.id.txtAlbumName);
        TextView textView2 = (TextView) this.detailAlbumDialog.findViewById(R.id.txtAlbumPath);
        TextView textView3 = (TextView) this.detailAlbumDialog.findViewById(R.id.txtAlbumSize);
        TextView textView4 = (TextView) this.detailAlbumDialog.findViewById(R.id.txtAlbumItem);
        TextView textView5 = (TextView) this.detailAlbumDialog.findViewById(R.id.txtAlbumCreatedOn);
        RelativeLayout relativeLayout = (RelativeLayout) this.detailAlbumDialog.findViewById(R.id.rl_no);
        File file = new File(((DataFileModel) arrayList.get(num.intValue())).folderPath);
        String[] list = file.list();
        long j = 0;
        if (list != null) {
            int length = list.length;
            long j2 = 0;
            int i2 = 0;
            while (i < length) {
                StringBuilder sb = new StringBuilder();
                sb.append(file);
                File file2 = file;
                sb.append(File.separator);
                sb.append(list[i]);
                File file3 = new File(sb.toString());
                if (file3.exists()) {
                    strArr = list;
                    String substring = file3.getPath().substring(file3.getPath().lastIndexOf("/") + 1);
                    if (substring.endsWith(".jpg") || substring.endsWith(".JPG") || substring.endsWith(".jpeg") || substring.endsWith(".JPEG") || substring.endsWith(".png") || substring.endsWith(".PNG") || substring.endsWith(".gif") || substring.endsWith(".GIF")) {
                        j2 += file3.length();
                        i2++;
                    }
                } else {
                    strArr = list;
                }
                i++;
                file = file2;
                list = strArr;
            }
            i = i2;
            j = j2;
        }
        Date date = new Date(new File(((DataFileModel) arrayList.get(num.intValue())).folderPath).lastModified());
        textView.setText(((DataFileModel) arrayList.get(num.intValue())).folderName + "");
        textView2.setText(((DataFileModel) arrayList.get(num.intValue())).folderPath);
        textView3.setText(FileUtils.convertToHumanReadableSize(this.activity, j));
        textView4.setText(i + " Files.");
        textView5.setText(date + "");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.detailAlbumDialog.dismiss();
            }
        });
        this.detailAlbumDialog.show();
    }

    private void deleteImageAlbum(final ArrayList<Object> arrayList, final Integer num) {
        Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        this.deletAlbumDialog = dialog;
        dialog.requestWindowFeature(1);
        this.deletAlbumDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.deletAlbumDialog.setContentView(R.layout.dg_delete_image_folder);
        this.deletAlbumDialog.setTitle("Delete Image");
        this.deletAlbumDialog.setCancelable(false);
        this.deletAlbumDialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) this.deletAlbumDialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteAlbumAndImageExecute(arrayList, num).execute(new Void[0]);
                MoMomentFragment.this.deletAlbumDialog.dismiss();
            }
        });
        ((RelativeLayout) this.deletAlbumDialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.deletAlbumDialog.dismiss();
            }
        });
        this.deletAlbumDialog.show();
    }


    public class DeleteAlbumAndImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<Object> arrayList;
        int pos;
        ProgressDialog progressDialog;

        public DeleteAlbumAndImageExecute(ArrayList<Object> arrayList, Integer num) {
            this.arrayList = new ArrayList<>();
            this.progressDialog = new ProgressDialog(MoMomentFragment.this.context);
            this.arrayList = arrayList;
            this.pos = num.intValue();
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            MoMomentFragment.this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DeleteAlbumAndImageExecute.this.progressDialog.setMessage("Please wait a while...");
                    DeleteAlbumAndImageExecute.this.progressDialog.setProgressStyle(0);
                    DeleteAlbumAndImageExecute.this.progressDialog.setIndeterminate(false);
                    DeleteAlbumAndImageExecute.this.progressDialog.setCancelable(false);
                    DeleteAlbumAndImageExecute.this.progressDialog.show();
                }
            });
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = new File(((DataFileModel) this.arrayList.get(this.pos)).folderPath);
            try {
                String[] list = file.list();
                if (list != null) {
                    for (String str : list) {
                        String str2 = file.getPath() + File.separator + str;
                        File file2 = new File(file.getPath() + File.separator + str);
                        if (file2.exists()) {
                            file2.delete();
                            MoMomentFragment.this.activity.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data='" + str2 + "'", null);
                        }
                    }
                    if (file.list().length <= 0) {
                        file.delete();
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            MoMomentFragment.this.countSelected = 0;
            MoMomentFragment.this.isSingleSelection = false;
            MoMomentFragment.this.isSelectedAll = false;
            return null;
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            MoMomentFragment.this.HiddenView();
            MoMomentFragment.momentAdapter.notifyDataSetChanged();
            this.progressDialog.dismiss();
            MoMomentFragment.this.deletAlbumDialog.dismiss();
            Toast.makeText(MoMomentFragment.this.getActivity(), "Image Album deleted successfully.", 0).show();
        }
    }

    public void checkPermission() {
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA"}, STORAGE_PERMISSION);
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, "android.permission.CAMERA")) {
            showRequestPermissionRationale();
        } else {
            showAppPermissionSettings();
        }
    }

    private void showRequestPermissionRationale() {
        Snackbar.make(this.view1, getString(R.string.permission_info), BaseTransientBottomBar.LENGTH_INDEFINITE).setAction(getString(R.string.permission_ok), new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMMomentFragment.24
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.checkPermission();
            }
        }).show();
    }

    private void showAppPermissionSettings() {
        Snackbar.make(this.view1, getString(R.string.permission_force), BaseTransientBottomBar.LENGTH_INDEFINITE).setAction(getString(R.string.permission_settings), new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMMomentFragment.25
            @Override
            public void onClick(View view) {
                Uri fromParts = Uri.fromParts(MoMomentFragment.this.getString(R.string.permission_package), MoMomentFragment.this.activity.getPackageName(), null);
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setData(fromParts);
                MoMomentFragment.this.startActivityForResult(intent, 1000);
            }
        }).show();
    }

    private void SetAdapterData() {
        MomentAdapter gMMomentAdapter = new MomentAdapter(this.activity, getContext(), ConstantArrayClass.albumsList);
        momentAdapter = gMMomentAdapter;
        fragmnetimagegridView.setAdapter(gMMomentAdapter);
        this.loader.setVisibility(View.GONE);
        DataOrientation(this.activity.getResources().getConfiguration().orientation);
        int i = Build.VERSION.SDK_INT;
        momentAdapter.setItemClickCallback(new OnClickListener<ArrayList<Object>, Integer, View>() {
            @Override
            public void onClickMoreListener(ArrayList<Object> arrayList, Integer num, View view) {
            }

            @Override
            public void onLongClickListener(ArrayList<Object> arrayList, Integer num) {
                MoMomentFragment.this.callBackAlbumsList = new ArrayList<>();
                MoMomentFragment.this.callBackAlbumsList = arrayList;
                if (MoMomentFragment.this.mActionMode == null) {
                    MoMomentFragment gMMomentFragment = MoMomentFragment.this;
                    gMMomentFragment.mActionMode = ((AppCompatActivity) gMMomentFragment.activity).startSupportActionMode(MoMomentFragment.this.mActionModeCallback);
                    StandaloneActionMode standaloneActionMode = (StandaloneActionMode) MoMomentFragment.this.mActionMode;
                    try {
                        Field declaredField = StandaloneActionMode.class.getDeclaredField("mContextView");
                        declaredField.setAccessible(true);
                        Object obj = declaredField.get(standaloneActionMode);
                        if (MoMomentFragment.this.preferenceClass.getInt("ThemeMode", 0) == 0) {
                            ((View) obj).setBackground(new ColorDrawable(MoMomentFragment.this.getContext().getResources().getColor(R.color.white)));
                            MoMomentFragment.this.getActivity().getWindow().setStatusBarColor(MoMomentFragment.this.getResources().getColor(R.color.white));
                        } else if (MoMomentFragment.this.preferenceClass.getInt("ThemeMode", 1) == 1) {
                            ((View) obj).setBackground(new ColorDrawable(MoMomentFragment.this.getContext().getResources().getColor(R.color.black)));
                            MoMomentFragment.this.getActivity().getWindow().setStatusBarColor(MoMomentFragment.this.getResources().getColor(R.color.black));
                        }
                        MoMomentFragment gMMomentFragment2 = MoMomentFragment.this;
                        gMMomentFragment2.SelectSingleFile(gMMomentFragment2.callBackAlbumsList, num.intValue());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }

            @Override
            public void onClickListener(ArrayList<Object> arrayList, Integer num) {
                MoMomentFragment.this.callBackAlbumsList = new ArrayList<>();
                MoMomentFragment.this.callBackAlbumsList = arrayList;
                MoMomentFragment.this.bpos = num.intValue();
                if (MoMomentFragment.this.isSingleSelection) {
                    MoMomentFragment gMMomentFragment = MoMomentFragment.this;
                    gMMomentFragment.SelectSingleFile(gMMomentFragment.callBackAlbumsList, num.intValue());
                    return;
                }
                Common.IdentifyActivity = "MomentFragment";
                Common.strplay = "Photofrag";
                Intent intent = new Intent(MoMomentFragment.this.context, PreviewActivity.class);
                intent.putExtra(Common.gImagePath, ((DataFileModel) MoMomentFragment.this.callBackAlbumsList.get(MoMomentFragment.this.bpos)).path);
                intent.putExtra(Common.gTotalimage, MoMomentFragment.this.callBackAlbumsList.size());
                intent.putExtra(Common.gCurrenrtPosition, MoMomentFragment.this.bpos);
                intent.putExtra(Common.gArrayType, "open");
                intent.putExtra(Common.gActivityname, "MomentFragment");
                intent.putExtra(Common.gMediaType, ((DataFileModel) MoMomentFragment.this.callBackAlbumsList.get(MoMomentFragment.this.bpos)).getMediaType());
                MoMomentFragment.this.context.startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        int i3 = AppUtilsClass.REQUEST_PERM_DELETE;
        if (i == CAMERA_PHOTO && i2 == -1) {
            Uri uri = this.imageToUploadUri;
            if (uri != null) {
                AddImageToGallery(uri.getPath());
            } else {
                Toast.makeText(this.activity, "Error while capturing Image", 0).show();
            }
        }
    }

    public void AddImageToGallery(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("mime_type", "image/jpg");
        contentValues.put(SMUtilsWP.mediaPath, str);
        this.activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public void isCheckImage(ArrayList<Object> arrayList) {
        if (arrayList.size() >= 1) {
            rlPhotoLayout.setVisibility(8);
            fragmnetimagegridView.setVisibility(0);
            return;
        }
        fragmnetimagegridView.setVisibility(8);
        rlPhotoLayout.setVisibility(0);
    }

    private void SortingCallBack() {
        this.momentSortingListener = new MomentSorting();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.contentObserver != null) {
            this.activity.getContentResolver().unregisterContentObserver(this.contentObserver);
            this.contentObserver = null;
            Handler handler = this.handler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                this.handler = null;
            }
        }
    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
        MomentAdapter gMMomentAdapter = momentAdapter;
        if (gMMomentAdapter != null) {
            gMMomentAdapter.releaseResources();
        }
    }

    public void HiddenView() {
        Degu_MainActivity.rl_imageBtn.setVisibility(8);
        Degu_MainActivity.rl_videoBtn.setVisibility(8);
        Degu_MainActivity.ivSelectAll.setVisibility(8);
        Degu_MainActivity.ivAddAlbum.setVisibility(8);
        Degu_MainActivity.ivSort.setVisibility(8);
    }


    public class MomentSorting implements MomentSortingListener {
        MomentSorting() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            MoMomentFragment.this.isCheckImage(arrayList);
            MoMomentFragment.momentAdapter = new MomentAdapter(MoMomentFragment.this.activity, MoMomentFragment.this.getContext(), arrayList);
            MoMomentFragment.fragmnetimagegridView.setAdapter(MoMomentFragment.momentAdapter);
            MoMomentFragment.this.loader.setVisibility(8);
            MoMomentFragment.fragmnetimagegridView.setVisibility(0);
            MoMomentFragment gMMomentFragment = MoMomentFragment.this;
            gMMomentFragment.DataOrientation(gMMomentFragment.activity.getResources().getConfiguration().orientation);
            MoMomentFragment.momentAdapter.setItemClickCallback(new OnClickListener<ArrayList<Object>, Integer, View>() {
                @Override
                public void onClickMoreListener(ArrayList<Object> arrayList2, Integer num, View view) {
                }

                @Override
                public void onLongClickListener(ArrayList<Object> arrayList2, Integer num) {
                    MoMomentFragment.this.callBackAlbumsList = new ArrayList<>();
                    MoMomentFragment.this.callBackAlbumsList = arrayList2;
                    if (MoMomentFragment.this.mActionMode == null) {
                        MoMomentFragment.this.mActionMode = ((AppCompatActivity) MoMomentFragment.this.activity).startSupportActionMode(MoMomentFragment.this.mActionModeCallback);
                        StandaloneActionMode standaloneActionMode = (StandaloneActionMode) MoMomentFragment.this.mActionMode;
                        try {
                            Field declaredField = StandaloneActionMode.class.getDeclaredField("mContextView");
                            declaredField.setAccessible(true);
                            Object obj = declaredField.get(standaloneActionMode);
                            if (MoMomentFragment.this.preferenceClass.getInt("ThemeMode", 0) == 0) {
                                ((View) obj).setBackground(new ColorDrawable(MoMomentFragment.this.getContext().getResources().getColor(R.color.white)));
                                MoMomentFragment.this.getActivity().getWindow().setStatusBarColor(MoMomentFragment.this.getResources().getColor(R.color.white));
                            } else if (MoMomentFragment.this.preferenceClass.getInt("ThemeMode", 1) == 1) {
                                ((View) obj).setBackground(new ColorDrawable(MoMomentFragment.this.getContext().getResources().getColor(R.color.black)));
                                MoMomentFragment.this.getActivity().getWindow().setStatusBarColor(MoMomentFragment.this.getResources().getColor(R.color.black));
                            }
                            MoMomentFragment.this.SelectSingleFile(MoMomentFragment.this.callBackAlbumsList, num.intValue());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }

                @Override
                public void onClickListener(ArrayList<Object> arrayList2, Integer num) {
                    MoMomentFragment.this.callBackAlbumsList = new ArrayList<>();
                    MoMomentFragment.this.callBackAlbumsList = arrayList2;
                    MoMomentFragment.this.bpos = num.intValue();
                    if (MoMomentFragment.this.isSingleSelection) {
                        MoMomentFragment.this.SelectSingleFile(MoMomentFragment.this.callBackAlbumsList, num.intValue());
                        return;
                    }
                    Common.IdentifyActivity = "MomentFragment";
                    Common.strplay = "Photofrag";
                    Intent intent = new Intent(MoMomentFragment.this.context, PreviewActivity.class);
                    intent.putExtra(Common.gImagePath, ((DataFileModel) MoMomentFragment.this.callBackAlbumsList.get(MoMomentFragment.this.bpos)).path);
                    intent.putExtra(Common.gTotalimage, MoMomentFragment.this.callBackAlbumsList.size());
                    intent.putExtra(Common.gCurrenrtPosition, MoMomentFragment.this.bpos);
                    intent.putExtra(Common.gArrayType, "open");
                    intent.putExtra(Common.gActivityname, "MomentFragment");
                    intent.putExtra(Common.gMediaType, ((DataFileModel) MoMomentFragment.this.callBackAlbumsList.get(MoMomentFragment.this.bpos)).getMediaType());
                    MoMomentFragment.this.context.startActivity(intent);

                }
            });
        }
    }

    public void FolderDialog(ArrayList<FolderModel> arrayList, boolean z) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.activity, R.style.BottomSheetDialogStyle);
        this.dialog = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setContentView(R.layout.dg_custom_folder_list);
        this.dialog.setCancelable(false);
        this.dialog.setCanceledOnTouchOutside(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        RecyclerView recyclerView = (RecyclerView) this.dialog.findViewById(R.id.rcv_folderlist);
        recyclerView.setLayoutManager(linearLayoutManager);
        AccessFolder11 accessFolder11 = new AccessFolder11(getContext(), arrayList, z);
        this.accessFolder11 = accessFolder11;
        recyclerView.setAdapter(accessFolder11);
        this.dialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.UnSelectAll();
                MoMomentFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(MoMomentFragment.this.activity, R.drawable.action_select));
                if (MoMomentFragment.this.mActionMode != null) {
                    MoMomentFragment.this.mActionMode.finish();
                }
                MoMomentFragment.this.dialog.dismiss();
            }
        });
        this.dialog.findViewById(R.id.txtAddNewFolder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoMomentFragment.this.AddNewFolderDialog();
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
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MoMomentFragment.this.dialog.dismiss();
                    MoMomentFragment.this.CopyMoveData(AccessFolder11.this.folderList.get(i).getFolderPath(), AccessFolder11.this.abool);
                    if (MoMomentFragment.this.mActionMode != null) {
                        MoMomentFragment.this.mActionMode.finish();
                    }
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
        final Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
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
                    MoMomentFragment gMMomentFragment = MoMomentFragment.this;
                    gMMomentFragment.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    File file = new File(MoMomentFragment.this.folder_path);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else {
                        Toast.makeText(MoMomentFragment.this.activity, "Already Exists", 0).show();
                    }
                    AppUtilsClass.getAccessFolder11();
                    if (MoMomentFragment.this.accessFolder11 != null) {
                        MoMomentFragment.this.accessFolder11.refreshData(AppUtilsClass.accessfolderListFor11);
                    }
                    AppUtilsClass.RefreshImageAlbum(MoMomentFragment.this.getActivity());
                    AppUtilsClass.RefreshVideoAlbum(MoMomentFragment.this.getActivity());
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


    public class DeleteImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            MoMomentFragment.this.progressPermanentDelete = new ProgressDialog(MoMomentFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            MoMomentFragment.this.progressPermanentDelete.setMessage("Please wait a while...");
            MoMomentFragment.this.progressPermanentDelete.setProgressStyle(0);
            MoMomentFragment.this.progressPermanentDelete.setIndeterminate(false);
            MoMomentFragment.this.progressPermanentDelete.setCancelable(false);
            MoMomentFragment.this.progressPermanentDelete.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            Uri withAppendedId;
            new ArrayList();
            new ArrayList();
            ArrayList<String> listString = MoMomentFragment.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_IMAGE).mkdirs();
            try {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < this.pathList.size()) {
                    String mediaType = this.pathList.get(i).getMediaType();
                    if (this.pathList.size() - 1 == i) {
                        while (i >= 0) {
                            if (Build.VERSION.SDK_INT >= 30) {
                                if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                    withAppendedId = ContentUris.withAppendedId(MediaStore.Images.Media.getContentUri("external"), AppUtilsClass.getFilePathToMediaID(this.pathList.get(i).path, MoMomentFragment.this.getActivity()));
                                } else {
                                    withAppendedId = mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D) ? ContentUris.withAppendedId(MediaStore.Video.Media.getContentUri("external"), AppUtilsClass.getFilePathToMediaID(this.pathList.get(i).path, MoMomentFragment.this.getActivity())) : null;
                                }
                                arrayList.add(withAppendedId);
                            }
                            this.pathList.remove(i);
                            i--;
                        }
                        MoMomentFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                    i++;
                }
                if (Build.VERSION.SDK_INT >= 30) {
                    AppUtilsClass.requestIntent(arrayList, MoMomentFragment.this.getActivity());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            if (Build.VERSION.SDK_INT != 30) {
                MoMomentFragment.this.UnSelectAll();
                MoMomentFragment.this.isSingleSelection = false;
                MoMomentFragment.this.isSelectedAll = false;
                MoMomentFragment.this.progressPermanentDelete.dismiss();
                if (MoMomentFragment.this.mActionMode != null) {
                    MoMomentFragment.this.mActionMode.finish();
                }
                AppUtilsClass.RefreshPhotoVideo(MoMomentFragment.this.getActivity());
                if (MoMomentFragment.this.toastTag.equals("MoveData")) {
                    Toast.makeText(MoMomentFragment.this.getActivity(), "Files Move successfully.", 0).show();
                } else {
                    Toast.makeText(MoMomentFragment.this.getActivity(), "Files are deleted successfully.", 0).show();
                }
            }
        }
    }
}
