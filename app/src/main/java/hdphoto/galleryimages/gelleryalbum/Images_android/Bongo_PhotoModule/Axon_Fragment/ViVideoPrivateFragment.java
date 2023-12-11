package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.PreviewActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.VideoPrivateAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideVideoFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickHideListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.RefreshAdapterListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.LoginPreferenceUtilsData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingVideoPrivateDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import kotlin.jvm.internal.CharCompanionObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class ViVideoPrivateFragment extends BaseFragment {
    public static String FName = "";
    static String TAG = "VideoPrivateFragment";
    public static ActionMode mActionMode;
    public static VideoPrivateAdapter privateVideoAdapter;
    public static ArrayList<Object> privateVideoList;
    public static ArrayList<Object> sendPrivateVideoList;
    AccessFolder11 accessFolder11;
    public Activity activity;
    int bpos;
    Context context;
    BottomSheetDialog dialog;
    String folderPath;
    int i2;
    ImageView ivUnlokButton;
    Menu mMenu;
    PrefClass preferenceClass;
    GridView privateVideoGridView;
    int pv;
    RefreshAdapterListener refreshAdapterCallBack;
    RelativeLayout rl_NoDataLayout;
    HideVideoFolderDataSortingListener sortingHideVideoCallBack;
    int PRIVATE_ALBUM_RESULT = 444;
    int REFRESH_RESULT = 555;
    int countSelected = 0;
    boolean isSelectAll = false;
    boolean isSingleSelection = false;
    ActionMode.Callback actionModeCallback = new ActionMode.Callback() { 
        @Override 
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return true;
        }

        @Override 
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            ViVideoPrivateFragment.this.mMenu = menu;
            actionMode.getMenuInflater().inflate(R.menu.private_data_menu, menu);
            return true;
        }

        @Override 
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.Delete:
                    if (ViVideoPrivateFragment.this.GetSelectedList().size() >= 1) {
                        ViVideoPrivateFragment gMVideoPrivateFragment = ViVideoPrivateFragment.this;
                        gMVideoPrivateFragment.DeleteDialog(gMVideoPrivateFragment.GetSelectedList());
                    } else {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "Select Video.", 0).show();
                    }
                    return true;
                case R.id.Selector:
                    int i = ViVideoPrivateFragment.this.preferenceClass.getInt(Common.gIsLockStatus, 0);
                    if (i == 0) {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "Please Set Security!!", 0).show();
                    } else if (i == 1) {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "Please Enter Passcode!!", 0).show();
                    } else if (ViVideoPrivateFragment.this.isSelectAll) {
                        ViVideoPrivateFragment.this.UnSelectAll();
                        ViVideoPrivateFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ViVideoPrivateFragment.this.activity, R.drawable.action_select));
                        actionMode.finish();
                    } else if (ViVideoPrivateFragment.privateVideoList.size() >= 1) {
                        ViVideoPrivateFragment.this.SelectAll();
                        ViVideoPrivateFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ViVideoPrivateFragment.this.activity, R.drawable.action_unselect));
                    } else {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "No Videos Found!!", 0).show();
                    }
                    return true;
                case R.id.Share:
                    if (ViVideoPrivateFragment.this.GetSelectedList().size() >= 1) {
                        ViVideoPrivateFragment gMVideoPrivateFragment2 = ViVideoPrivateFragment.this;
                        gMVideoPrivateFragment2.ShareVideos(gMVideoPrivateFragment2.GetSelectedList());
                    } else {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "Select Video.", 0).show();
                    }
                    actionMode.finish();
                    return true;
                case R.id.Unlock:
                    if (ViVideoPrivateFragment.this.GetSelectedList().size() > 0) {
                        ViVideoPrivateFragment.this.UnlockDialog();
                    } else {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "Select Video.", 0).show();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override 
        public void onDestroyActionMode(ActionMode actionMode) {
            ViVideoPrivateFragment.mActionMode = null;
            ViVideoPrivateFragment.this.UnSelectAll();
        }
    };
    private String folder_path = "";

    @Override 
    public void onDestroy() {
        super.onDestroy();
    }

    @Override 
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DataOrientation(configuration.orientation);
    }

    @Override 
    public void onResume() {
        super.onResume();
        if (privateVideoList.size() > 0) {
            this.rl_NoDataLayout.setVisibility(8);
            this.privateVideoGridView.setVisibility(0);
        } else {
            this.privateVideoGridView.setVisibility(8);
            this.ivUnlokButton.setVisibility(8);
            this.rl_NoDataLayout.setVisibility(0);
            Degu_MainActivity.ivSelectAll.setVisibility(8);
            Degu_MainActivity.ivUnLock.setVisibility(8);
        }
        DataOrientation(getResources().getConfiguration().orientation);
        VideoPrivateAdapter gMVideoPrivateAdapter = privateVideoAdapter;
        if (gMVideoPrivateAdapter != null) {
            gMVideoPrivateAdapter.notifyDataSetChanged();
        }
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_private_video, viewGroup, false);
        this.activity = getActivity();
        this.preferenceClass = new PrefClass(getActivity());
        this.privateVideoGridView = (GridView) inflate.findViewById(R.id.grid_view_private_video_album);
        this.rl_NoDataLayout = (RelativeLayout) inflate.findViewById(R.id.hintNoHideVideoLayout);
        this.ivUnlokButton = (ImageView) inflate.findViewById(R.id.btnVideoUnloackButton);
        privateVideoList = new ArrayList<>();
        try {
            ArrayList<Object> GetPrivateVideoFiles = GetPrivateVideoFiles(getActivity());
            privateVideoList = GetPrivateVideoFiles;
            Collections.reverse(GetPrivateVideoFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SetAdapter();
        Degu_MainActivity.ivUnLock.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ViVideoPrivateFragment.this.GetSelectedList().size() > 0) {
                    ViVideoPrivateFragment.this.UnlockDialog();
                } else {
                    Toast.makeText(ViVideoPrivateFragment.this.context, "Select Video.", 0).show();
                }
            }
        });
        Degu_MainActivity.ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                int i = ViVideoPrivateFragment.this.preferenceClass.getInt(Common.gIsLockStatus, 0);
                if (i == 0) {
                    Toast.makeText(ViVideoPrivateFragment.this.context, "Please Set Security!!", 0).show();
                } else if (i == 1) {
                    Toast.makeText(ViVideoPrivateFragment.this.context, "Please Enter Passcode!!", 0).show();
                } else if (ViVideoPrivateFragment.this.isSelectAll) {
                    ViVideoPrivateFragment.this.UnSelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoPrivateFragment.this.getResources().getDrawable(R.drawable.action_select));
                } else if (ViVideoPrivateFragment.privateVideoList.size() >= 1) {
                    ViVideoPrivateFragment.this.SelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoPrivateFragment.this.getResources().getDrawable(R.drawable.action_unselect));
                } else {
                    Toast.makeText(ViVideoPrivateFragment.this.context, "No Videos Found!!", 0).show();
                }
            }
        });
        SortingCallBack();
        return inflate;
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
            this.isSelectAll = false;
            ActionMode actionMode = mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
        }
        if (GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        privateVideoAdapter.notifyDataSetChanged();
    }

    public ArrayList<Object> GetSelectedList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        int size = privateVideoList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) privateVideoList.get(i)).isSelected) {
                arrayList.add((DataFileModel) privateVideoList.get(i));
            }
        }
        return arrayList;
    }

    public void UnSelectAll() {
        int size = privateVideoList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) privateVideoList.get(i)).isSelected = false;
        }
        if (GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = 0;
        this.isSingleSelection = false;
        this.isSelectAll = false;
        privateVideoAdapter.notifyDataSetChanged();
    }

    public void SelectAll() {
        int size = privateVideoList.size();
        for (int i = 0; i < size; i++) {
            if (!((DataFileModel) privateVideoList.get(i)).isDirectory) {
                ((DataFileModel) privateVideoList.get(i)).isSelected = true;
            }
        }
        if (GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = privateVideoList.size();
        this.isSingleSelection = true;
        this.isSelectAll = true;
        privateVideoAdapter.notifyDataSetChanged();
    }

    public void UnlockDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_unlock_video);
        dialog.setTitle("Unlock Video");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                ViVideoPrivateFragment.this.isSelectAll = false;
                ViVideoPrivateFragment.this.isSingleSelection = false;
                new UnLockVideoExecute(ViVideoPrivateFragment.this.GetSelectedList()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                ViVideoPrivateFragment.this.UnSelectAll();
                if (ViVideoPrivateFragment.mActionMode != null) {
                    ViVideoPrivateFragment.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    
    public class UnLockVideoExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<Object> pathList;
        ProgressDialog progressLock;

        public UnLockVideoExecute(ArrayList<Object> arrayList) {
            this.pathList = new ArrayList<>();
            this.progressLock = new ProgressDialog(ViVideoPrivateFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            this.progressLock.setMessage("Please wait a while...");
            this.progressLock.setProgressStyle(0);
            this.progressLock.setIndeterminate(false);
            this.progressLock.setCancelable(false);
            this.progressLock.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = ViVideoPrivateFragment.this.preferenceClass.getListString(Common.gOldPath);
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(((DataFileModel) this.pathList.get(i)).path);
                String name = file.getName();
                File file2 = new File(FolderPath.SDCARD_PATH_VIDEO_LOCK_BACKUP);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                File file3 = new File(file2 + File.separator + name);
                if (file3.exists()) {
                    Log.e(ViVideoPrivateFragment.TAG, "doInBackground: ");
                }
                String parent = file3.getParent();
                new MediaScanners(GalleryAppClass.getInstance(), file3);
                try {
                    FileUtils.CopyMoveFile(file, file3);
                    AppUtilsClass.insertUri(ViVideoPrivateFragment.this.getActivity(), file3);
                    long j = ((DataFileModel) this.pathList.get(i)).id;
                    String str = ((DataFileModel) this.pathList.get(i)).name;
                    String str2 = ((DataFileModel) this.pathList.get(i)).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file4 = new File(((DataFileModel) this.pathList.get(i2)).path);
                            file4.delete();
                            ContentResolver contentResolver = ViVideoPrivateFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                        }
                        ViVideoPrivateFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            ViVideoPrivateFragment.this.isSingleSelection = false;
            ViVideoPrivateFragment.this.isSelectAll = false;
            this.progressLock.dismiss();
            if (ViVideoPrivateFragment.mActionMode != null) {
                ViVideoPrivateFragment.mActionMode.finish();
            }
            new Handler().postDelayed(new Runnable() { 
                @Override 
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo((Activity) ViVideoPrivateFragment.this.context);
                }
            }, 500L);
            if (ViVideoPrivateFragment.privateVideoList.size() > 0) {
                ViVideoPrivateFragment.this.rl_NoDataLayout.setVisibility(8);
                ViVideoPrivateFragment.this.privateVideoGridView.setVisibility(0);
            } else {
                ViVideoPrivateFragment.this.rl_NoDataLayout.setVisibility(0);
                ViVideoPrivateFragment.this.privateVideoGridView.setVisibility(8);
            }
            ViVideoPrivateFragment.this.RefreshAdapter();
            Toast.makeText(ViVideoPrivateFragment.this.getActivity(), "Files are unlocked successfully.", 0).show();
        }
    }

    public void DeleteDialog(final ArrayList<Object> arrayList) {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_file);
        dialog.setTitle("Delete Video");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Delete Video");
        ((TextView) dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to delete video?");
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                ViVideoPrivateFragment.this.DeleteSelected(arrayList);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                ViVideoPrivateFragment.this.UnSelectAll();
                if (ViVideoPrivateFragment.mActionMode != null) {
                    ViVideoPrivateFragment.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DeleteSelected(ArrayList<Object> arrayList) {
        ArrayList arrayList2 = new ArrayList(arrayList);
        for (int i = 0; i < arrayList2.size(); i++) {
            try {
                new File(((DataFileModel) arrayList2.get(i)).path).delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            File file = new File(((DataFileModel) arrayList2.get(i2)).path);
            ContentResolver contentResolver = this.activity.getContentResolver();
            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
        }
        ActionMode actionMode = mActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.private_frame_layout, new ViVideoPrivateFragment());
        beginTransaction.commit();
    }

    private void SetAdapter() {
        new SortingVideoPrivateDialog(getActivity(), privateVideoList, this.sortingHideVideoCallBack).Sorting(LoginPreferenceUtilsData.GetStringData(getActivity(), SortingVideoPrivateDialog.SortingName), LoginPreferenceUtilsData.GetStringData(getActivity(), SortingVideoPrivateDialog.SortingType));
        sortingCallBack();
        getFirstHiddenAlbum();
        VideoPrivateAdapter gMVideoPrivateAdapter = new VideoPrivateAdapter(getActivity(), getContext(), privateVideoList);
        privateVideoAdapter = gMVideoPrivateAdapter;
        this.privateVideoGridView.setAdapter((ListAdapter) gMVideoPrivateAdapter);
        DataOrientation(getResources().getConfiguration().orientation);
        privateVideoAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() { 
            @Override 
            public void onClickMoreListener(ArrayList<Object> arrayList, Integer num, View view, Boolean bool) {
            }

            @Override 
            public void onLongClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
            }

            @Override 
            public void onClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
                ViVideoPrivateFragment.sendPrivateVideoList = new ArrayList<>();
                ViVideoPrivateFragment.sendPrivateVideoList = arrayList;
                ViVideoPrivateFragment.this.bpos = num.intValue();
                if (!ViVideoPrivateFragment.this.isSingleSelection) {
                    String str = ((DataFileModel) ViVideoPrivateFragment.sendPrivateVideoList.get(num.intValue())).path;
                    str.substring(str.lastIndexOf("."));
                    if (str.endsWith(".mp4") || str.endsWith(".MP4") || str.endsWith(".3gp") || str.endsWith(".3GP") || str.endsWith(".mkv") || str.endsWith(".MKV")) {
                        Common.IdentifyActivity = "VideoPrivateFragment";
                        Intent intent = new Intent(ViVideoPrivateFragment.this.activity, PreviewActivity.class);
                        intent.putExtra(Common.gImagePath, ((DataFileModel) ViVideoPrivateFragment.sendPrivateVideoList.get(ViVideoPrivateFragment.this.bpos)).path);
                        intent.putExtra(Common.gTotalimage, ViVideoPrivateFragment.sendPrivateVideoList.size());
                        intent.putExtra(Common.gCurrenrtPosition, ViVideoPrivateFragment.this.bpos);
                        intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                        intent.putExtra(Common.gActivityname, "VideoPrivateFragment");
                        intent.putExtra(Common.gMediaType, ExifInterface.GPS_MEASUREMENT_3D);
                        ViVideoPrivateFragment.this.activity.startActivity(intent);
                    }
                } else if (bool.booleanValue()) {
                    Toast.makeText(ViVideoPrivateFragment.this.context, "isPrivateAlbum", 0).show();
                } else {
                    ViVideoPrivateFragment.this.SelectSingleImage(ViVideoPrivateFragment.sendPrivateVideoList, num.intValue());
                }
            }
        });
    }

    public void getFirstHiddenAlbum() {
        Collections.sort(privateVideoList, new Comparator<Object>() { 
            @Override 
            public int compare(Object obj, Object obj2) {
                return Boolean.compare(((DataFileModel) obj2).isDirectory, ((DataFileModel) obj).isDirectory);
            }
        });
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
        VideoPrivateAdapter gMVideoPrivateAdapter = privateVideoAdapter;
        if (gMVideoPrivateAdapter != null) {
            gMVideoPrivateAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        GridView gridView = this.privateVideoGridView;
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        gridView.setNumColumns(this.i2);
    }

    public void RefreshAdapter() {
        getActivity().runOnUiThread(new Runnable() { 
            @Override 
            public void run() {
                FragmentTransaction beginTransaction = ViVideoPrivateFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.private_frame_layout, new ViVideoPrivateFragment());
                beginTransaction.commit();
                ViVideoPrivateFragment.privateVideoList = new ArrayList<>();
                ViVideoPrivateFragment gMVideoPrivateFragment = ViVideoPrivateFragment.this;
                try {
                    ViVideoPrivateFragment.privateVideoList = gMVideoPrivateFragment.GetPrivateVideoFiles(gMVideoPrivateFragment.getActivity());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ViVideoPrivateFragment.this.getFirstHiddenAlbum();
                ViVideoPrivateFragment.privateVideoAdapter = new VideoPrivateAdapter(ViVideoPrivateFragment.this.getActivity(), ViVideoPrivateFragment.this.getContext(), ViVideoPrivateFragment.privateVideoList);
                ViVideoPrivateFragment.this.privateVideoGridView.setAdapter((ListAdapter) ViVideoPrivateFragment.privateVideoAdapter);
                ViVideoPrivateFragment gMVideoPrivateFragment2 = ViVideoPrivateFragment.this;
                gMVideoPrivateFragment2.DataOrientation(gMVideoPrivateFragment2.activity.getResources().getConfiguration().orientation);
                ViVideoPrivateFragment.this.SortingCallBack();
                Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoPrivateFragment.this.activity.getResources().getDrawable(R.drawable.action_select));
            }
        });
    }

    private void sortingCallBack() {
        this.sortingHideVideoCallBack = new sortCallBack();
    }

    public void SortingCallBack() {
        this.refreshAdapterCallBack = new RefreshData();
    }

    
    public class RefreshData implements RefreshAdapterListener {
        RefreshData() {
        }

        @Override 
        public void Refresh(Boolean bool) {
            ViVideoPrivateFragment.privateVideoAdapter.notifyDataSetChanged();
        }
    }

    @Override 
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override 
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override 
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    
    public class sortCallBack implements HideVideoFolderDataSortingListener {
        sortCallBack() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ViVideoPrivateFragment.privateVideoList = new ArrayList<>();
            ViVideoPrivateFragment.privateVideoList.addAll(arrayList);
            ViVideoPrivateFragment.this.getFirstHiddenAlbum();
            ViVideoPrivateFragment.privateVideoAdapter = new VideoPrivateAdapter(ViVideoPrivateFragment.this.getActivity(), ViVideoPrivateFragment.this.getContext(), ViVideoPrivateFragment.privateVideoList);
            ViVideoPrivateFragment.this.privateVideoGridView.setAdapter((ListAdapter) ViVideoPrivateFragment.privateVideoAdapter);
            ViVideoPrivateFragment gMVideoPrivateFragment = ViVideoPrivateFragment.this;
            gMVideoPrivateFragment.DataOrientation(gMVideoPrivateFragment.getResources().getConfiguration().orientation);
            ViVideoPrivateFragment.privateVideoAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMVideoPrivateFragment.sortCallBack.1
                @Override 
                public void onClickMoreListener(ArrayList<Object> arrayList2, Integer num, View view, Boolean bool) {
                }

                @Override 
                public void onClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ViVideoPrivateFragment.sendPrivateVideoList = new ArrayList<>();
                    ViVideoPrivateFragment.sendPrivateVideoList = arrayList2;
                    ViVideoPrivateFragment.this.bpos = num.intValue();
                    if (!ViVideoPrivateFragment.this.isSingleSelection) {
                        String str = ((DataFileModel) ViVideoPrivateFragment.sendPrivateVideoList.get(num.intValue())).path;
                        str.substring(str.lastIndexOf("."));
                        if (str.endsWith(".mp4") || str.endsWith(".MP4") || str.endsWith(".3gp") || str.endsWith(".3GP") || str.endsWith(".mkv") || str.endsWith(".MKV")) {
                            Common.IdentifyActivity = "VideoPrivateFragment";
                            Intent intent = new Intent(ViVideoPrivateFragment.this.activity, PreviewActivity.class);
                            intent.putExtra(Common.gImagePath, ((DataFileModel) ViVideoPrivateFragment.sendPrivateVideoList.get(ViVideoPrivateFragment.this.bpos)).path);
                            intent.putExtra(Common.gTotalimage, ViVideoPrivateFragment.sendPrivateVideoList.size());
                            intent.putExtra(Common.gCurrenrtPosition, ViVideoPrivateFragment.this.bpos);
                            intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                            intent.putExtra(Common.gActivityname, "VideoPrivateFragment");
                            intent.putExtra(Common.gMediaType, ExifInterface.GPS_MEASUREMENT_3D);
                            ViVideoPrivateFragment.this.activity.startActivity(intent);
                        }
                    } else if (bool.booleanValue()) {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "isPrivateAlbum", 0).show();
                    } else {
                        ViVideoPrivateFragment.this.SelectSingleImage(ViVideoPrivateFragment.sendPrivateVideoList, num.intValue());
                    }
                }

                @Override 
                public void onLongClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ViVideoPrivateFragment.sendPrivateVideoList = new ArrayList<>();
                    ViVideoPrivateFragment.sendPrivateVideoList = arrayList2;
                    if (bool.booleanValue()) {
                        Toast.makeText(ViVideoPrivateFragment.this.context, "isPrivateAlbum", 0).show();
                        return;
                    }
                    ViVideoPrivateFragment.this.folderPath = ((DataFileModel) ViVideoPrivateFragment.sendPrivateVideoList.get(num.intValue())).folderPath;
                    if (ViVideoPrivateFragment.mActionMode != null) {
                        ViVideoPrivateFragment.mActionMode = null;
                    }
                    if (ViVideoPrivateFragment.mActionMode == null) {
                        ViVideoPrivateFragment.mActionMode = ((AppCompatActivity) ViVideoPrivateFragment.this.getActivity()).startSupportActionMode(ViVideoPrivateFragment.this.actionModeCallback);
                    }
                    ViVideoPrivateFragment.this.SelectSingleImage(ViVideoPrivateFragment.sendPrivateVideoList, num.intValue());
                }
            });
        }
    }

    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == this.PRIVATE_ALBUM_RESULT) {
            RefreshAdapter();
        } else if (i == this.REFRESH_RESULT) {
            RefreshAdapter();
        }
    }


    public class AccessFolder11 extends RecyclerView.Adapter<AccessFolder11.MyViewHolder> {
        boolean abool;
        Context context;
        ArrayList<FolderModel> folderList;
        ArrayList<Object> unlockArray = new ArrayList<>();

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
                    ViVideoPrivateFragment.this.dialog.dismiss();
                    String folderPath = AccessFolder11.this.folderList.get(i).getFolderPath();
                    folderPath.substring(folderPath.lastIndexOf("/") + 1);
                    ViVideoPrivateFragment.FName = folderPath;
                    AccessFolder11.this.unlockArray.clear();
                    AccessFolder11 accessFolder11 = AccessFolder11.this;
                    accessFolder11.unlockArray = ViVideoPrivateFragment.this.GetSelectedList();
                    new Unlock11Execute(AccessFolder11.this.unlockArray).execute(new Void[0]);
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

    public void TempDeleteVideo(ArrayList<Object> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<Object> it = arrayList.iterator();
        char c = CharCompanionObject.MAX_VALUE;
        while (it.hasNext()) {
            Object next = it.next();
            DocumentFile fromSingleUri = DocumentFile.fromSingleUri(requireActivity(), Uri.parse(((DataFileModel) next).path));
            if (fromSingleUri.exists() && fromSingleUri.delete()) {
                arrayList2.add(next);
                if (c == 0) {
                    return;
                }
                c = 1;
            } else {
                c = 0;
            }
        }
        arrayList.clear();
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            privateVideoList.remove(it2.next());
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                ViVideoPrivateFragment.privateVideoAdapter.notifyDataSetChanged();
            }
        });
    }

    
    public class Unlock11Execute extends AsyncTask<Void, Void, Void> {
        ArrayList<Object> arrayDataList;
        ArrayList arrayList = new ArrayList();
        char c = CharCompanionObject.MAX_VALUE;
        ProgressDialog progressDialog;

        public Unlock11Execute(ArrayList<Object> arrayList) {
            this.arrayDataList = new ArrayList<>();
            this.arrayDataList = arrayList;
            this.progressDialog = new ProgressDialog(ViVideoPrivateFragment.this.context);
        }

        @Override 
        public void onPreExecute() {
            this.progressDialog.setMessage("Please wait a while...");
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setIndeterminate(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            if (!this.arrayDataList.isEmpty()) {
                Iterator<Object> it = this.arrayDataList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    DataFileModel gMDataFileModel = (DataFileModel) next;
                    if (DocumentFile.fromSingleUri(ViVideoPrivateFragment.this.requireActivity(), Uri.parse(gMDataFileModel.path)).exists()) {
                        ViVideoPrivateFragment gMVideoPrivateFragment = ViVideoPrivateFragment.this;
                        if (gMVideoPrivateFragment.DownloadForUnlock(gMVideoPrivateFragment.getActivity(), gMDataFileModel.path)) {
                            this.arrayList.add(next);
                            if (this.c == 0) {
                                return null;
                            }
                            this.c = (char) 1;
                        } else {
                            continue;
                        }
                    } else {
                        this.c = (char) 0;
                    }
                }
                ViVideoPrivateFragment.this.TempDeleteVideo(this.arrayDataList);
                try {
                    this.arrayDataList.clear();
                    Iterator it2 = this.arrayList.iterator();
                    while (it2.hasNext()) {
                        ArrayList<Object> arrayList = ViVideoPrivateFragment.privateVideoList;
                        ((DataFileModel) it2.next()).isSelected = false;
                        arrayList.contains(false);
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
            this.progressDialog.dismiss();
            ViVideoPrivateFragment.this.isSingleSelection = false;
            ViVideoPrivateFragment.this.UnSelectAll();
            if (ViVideoPrivateFragment.mActionMode != null) {
                ViVideoPrivateFragment.mActionMode.finish();
            }
            AppUtilsClass.RefreshVideoAlbum(ViVideoPrivateFragment.this.getActivity());
            AppUtilsClass.RefreshMoment(ViVideoPrivateFragment.this.getActivity());
            MoMomentFragment.momentAdapter.notifyDataSetChanged();
            ViVideoPrivateFragment.privateVideoAdapter.notifyDataSetChanged();
            char c = this.c;
            if (c == 0) {
                Toast.makeText(ViVideoPrivateFragment.this.context, "Couldn't Unlock Video", 0).show();
            } else if (c == 1) {
                Toast.makeText(ViVideoPrivateFragment.this.context, "Files are unlocked successfully.", 0).show();
            }
        }
    }
}
