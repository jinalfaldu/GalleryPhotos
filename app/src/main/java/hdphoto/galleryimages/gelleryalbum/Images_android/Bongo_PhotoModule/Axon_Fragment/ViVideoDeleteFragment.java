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
import android.os.Build;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_DeleteTrashActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.PreviewActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.VideoDeleteAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideVideoFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickHideListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.RefreshAdapterListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.LoginPreferenceUtilsFolder;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingVideoPrivateDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ViVideoDeleteFragment extends BaseFragment {
    public static String TAG = "VideoDeleteFragment";
    public static ActionMode actionMode;
    public static VideoDeleteAdapter deleteVideoAdapter;
    public static ArrayList<Object> deleteVideoList;
    public static ArrayList<Object> sendDeleteVideoList;
    Activity activity;
    int bpos;
    Context context;
    GridView deleteVideoGridView;
    String folderPath;
    String fromBtn;
    int i2;
    ImageView imgUnlockButton;
    private Menu mMenu;
    PrefClass preferenceClass;
    ProgressDialog progressDialog;
    int pv;
    RefreshAdapterListener refreshAdapterCallBack;
    RelativeLayout rlDeleteNotice;
    RelativeLayout rl_NoDataLayout;
    HideVideoFolderDataSortingListener sortingHideVideoCallBack;
    int PRIVATE_ALBUM_RESULT = 444;
    int REFRESH_RESULT = 555;
    int countSelected = 0;
    boolean isSelectAll = false;
    boolean isSingleSelection = false;
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode2, Menu menu) {
            ViVideoDeleteFragment.this.mMenu = menu;
            actionMode2.getMenuInflater().inflate(R.menu.delete_data_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode2, Menu menu) {
            if (Build.VERSION.SDK_INT < 21) {
                return true;
            }
            ViVideoDeleteFragment.this.getActivity().getWindow().clearFlags(67108864);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode2, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.Delete :
                    if (ViVideoDeleteFragment.this.GetSelectedList().size() >= 1) {
                        ViVideoDeleteFragment gMVideoDeleteFragment = ViVideoDeleteFragment.this;
                        gMVideoDeleteFragment.DeleteDialog(gMVideoDeleteFragment.GetSelectedList());
                    } else {
                        Toast.makeText(ViVideoDeleteFragment.this.context, "Select Video.", 0).show();
                    }
                    return true;
                case R.id.Selector:
                    if (ViVideoDeleteFragment.this.isSelectAll) {
                        ViVideoDeleteFragment.this.UnSelectAll();
                        ViVideoDeleteFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ViVideoDeleteFragment.this.activity, R.drawable.action_select));
                        actionMode2.finish();
                    } else if (ViVideoDeleteFragment.deleteVideoList.size() >= 1) {
                        ViVideoDeleteFragment.this.fromBtn = "BoxBtn";
                        ViVideoDeleteFragment.this.SelectAll();
                        ViVideoDeleteFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ViVideoDeleteFragment.this.activity, R.drawable.action_unselect));
                    } else {
                        Toast.makeText(ViVideoDeleteFragment.this.context, "No Videos Found!!", 0).show();
                    }
                    return true;
                case R.id.Share:
                    if (ViVideoDeleteFragment.this.GetSelectedList().size() >= 1) {
                        ViVideoDeleteFragment gMVideoDeleteFragment2 = ViVideoDeleteFragment.this;
                        gMVideoDeleteFragment2.ShareVideos(gMVideoDeleteFragment2.GetSelectedList());
                    } else {
                        Toast.makeText(ViVideoDeleteFragment.this.context, "Select Video.", 0).show();
                    }
                    actionMode2.finish();
                    return true;
                case R.id.Unlock:
                    if (ViVideoDeleteFragment.this.GetSelectedList().size() > 0) {
                        ViVideoDeleteFragment.this.RestoreDialog();
                    } else {
                        Toast.makeText(ViVideoDeleteFragment.this.context, "Select Video.", 0).show();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode2) {
            ViVideoDeleteFragment.actionMode = null;
            ViVideoDeleteFragment.this.UnSelectAll();
            if (Build.VERSION.SDK_INT >= 21) {
                ViVideoDeleteFragment.this.getActivity().getWindow().clearFlags(67108864);
            }
        }
    };

    @Override 
    public void onResume() {
        super.onResume();
        if (deleteVideoList.size() > 0) {
            this.rl_NoDataLayout.setVisibility(8);
            this.deleteVideoGridView.setVisibility(0);
            this.rlDeleteNotice.setVisibility(0);
        } else {
            this.deleteVideoGridView.setVisibility(8);
            this.rlDeleteNotice.setVisibility(8);
            this.imgUnlockButton.setVisibility(8);
            this.rl_NoDataLayout.setVisibility(0);
            Degu_MainActivity.ivSelectAll.setVisibility(8);
            Degu_MainActivity.ivUnLock.setVisibility(8);
        }
        DataOrientation(getResources().getConfiguration().orientation);
        VideoDeleteAdapter gMVideoDeleteAdapter = deleteVideoAdapter;
        if (gMVideoDeleteAdapter != null) {
            gMVideoDeleteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DataOrientation(configuration.orientation);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_delete_video, viewGroup, false);
        this.activity = getActivity();
        this.preferenceClass = new PrefClass(getActivity());
        this.deleteVideoGridView = (GridView) inflate.findViewById(R.id.grid_view_private_video_album);
        this.rlDeleteNotice = (RelativeLayout) inflate.findViewById(R.id.rlDeleteNotice);
        this.rl_NoDataLayout = (RelativeLayout) inflate.findViewById(R.id.hintNoHideVideoLayout);
        this.imgUnlockButton = (ImageView) inflate.findViewById(R.id.btnVideoUnloackButton);
        deleteVideoList = new ArrayList<>();
        try {
            ArrayList<Object> GetDeletedVideoFiles = GetDeletedVideoFiles(getActivity());
            deleteVideoList = GetDeletedVideoFiles;
            Collections.reverse(GetDeletedVideoFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SetAdapter();
        Degu_MainActivity.ivUnLock.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ViVideoDeleteFragment.this.GetSelectedList().size() > 0) {
                    ViVideoDeleteFragment.this.RestoreDialog();
                } else {
                    Toast.makeText(ViVideoDeleteFragment.this.context, "Select Video.", 0).show();
                }
            }
        });
        Degu_MainActivity.ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ViVideoDeleteFragment.this.isSelectAll) {
                    ViVideoDeleteFragment.this.UnSelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoDeleteFragment.this.getResources().getDrawable(R.drawable.action_select));
                } else if (ViVideoDeleteFragment.deleteVideoList.size() >= 1) {
                    ViVideoDeleteFragment.this.fromBtn = "TopBtn";
                    ViVideoDeleteFragment.this.SelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoDeleteFragment.this.getResources().getDrawable(R.drawable.action_unselect));
                } else {
                    Toast.makeText(ViVideoDeleteFragment.this.context, "No Videos Found!!", 0).show();
                }
            }
        });
        if (deleteVideoList.size() > 0) {
            Degu_DeleteTrashActivity.allDelete.setVisibility(0);
        } else {
            Degu_DeleteTrashActivity.allDelete.setVisibility(8);
        }
        Degu_DeleteTrashActivity.allDelete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ViVideoDeleteFragment.this.fromBtn = "TopBtn";
                ViVideoDeleteFragment.this.SelectAll();
                ViVideoDeleteFragment gMVideoDeleteFragment = ViVideoDeleteFragment.this;
                gMVideoDeleteFragment.DeleteDialog(gMVideoDeleteFragment.GetSelectedList());
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
            ActionMode actionMode2 = actionMode;
            if (actionMode2 != null) {
                actionMode2.finish();
            }
        }
        if (actionMode != null && GetSelectedList().size() != 0) {
            actionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        deleteVideoAdapter.notifyDataSetChanged();
    }

    public ArrayList<Object> GetSelectedList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        int size = deleteVideoList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) deleteVideoList.get(i)).isSelected) {
                arrayList.add((DataFileModel) deleteVideoList.get(i));
            }
        }
        return arrayList;
    }

    public void UnSelectAll() {
        int size = deleteVideoList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) deleteVideoList.get(i)).isSelected = false;
        }
        if (actionMode != null && GetSelectedList().size() != 0) {
            actionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = 0;
        this.isSingleSelection = false;
        this.isSelectAll = false;
        deleteVideoAdapter.notifyDataSetChanged();
    }

    public void SelectAll() {
        int size = deleteVideoList.size();
        for (int i = 0; i < size; i++) {
            if (!((DataFileModel) deleteVideoList.get(i)).isDirectory) {
                ((DataFileModel) deleteVideoList.get(i)).isSelected = true;
            }
        }
        if (actionMode != null && GetSelectedList().size() != 0) {
            actionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = deleteVideoList.size();
        this.isSingleSelection = true;
        this.isSelectAll = true;
        deleteVideoAdapter.notifyDataSetChanged();
    }

    public void RestoreDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_restore_video);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Restore Video");
        ((TextView) dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to restore video?");
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViVideoDeleteFragment.this.isSelectAll = false;
                ViVideoDeleteFragment.this.isSingleSelection = false;
                new RestoreVideoExecute(ViVideoDeleteFragment.this.GetSelectedList()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ViVideoDeleteFragment.this.UnSelectAll();
                if (ViVideoDeleteFragment.actionMode != null) {
                    ViVideoDeleteFragment.actionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class RestoreVideoExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<Object> pathList;
        ProgressDialog progressLock;

        public RestoreVideoExecute(ArrayList<Object> arrayList) {
            this.pathList = new ArrayList<>();
            this.progressLock = new ProgressDialog(ViVideoDeleteFragment.this.getActivity());
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
            ArrayList<String> listString = ViVideoDeleteFragment.this.preferenceClass.getListString(Common.gOldPath);
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(((DataFileModel) this.pathList.get(i)).path);
                String name = file.getName();
                File file2 = new File(FolderPath.SDCARD_PATH_VIDEO_TRASH_BACKUP);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                File file3 = new File(file2 + File.separator + name);
                if (file3.exists()) {
                    Log.e(ViVideoDeleteFragment.TAG, "doInBackground: ");
                }
                String parent = file3.getParent();
                new MediaScanners(GalleryAppClass.getInstance(), file3);
                try {
                    FileUtils.CopyMoveFile(file, file3);
                    AppUtilsClass.insertUri(ViVideoDeleteFragment.this.getActivity(), file3);
                    long j = ((DataFileModel) this.pathList.get(i)).id;
                    String str = ((DataFileModel) this.pathList.get(i)).name;
                    String str2 = ((DataFileModel) this.pathList.get(i)).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file4 = new File(((DataFileModel) this.pathList.get(i2)).path);
                            file4.delete();
                            ContentResolver contentResolver = ViVideoDeleteFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                        }
                        ViVideoDeleteFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            ViVideoDeleteFragment.this.isSingleSelection = false;
            ViVideoDeleteFragment.this.isSelectAll = false;
            this.progressLock.dismiss();
            if (ViVideoDeleteFragment.actionMode != null) {
                ViVideoDeleteFragment.actionMode.finish();
            }
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo((Activity) ViVideoDeleteFragment.this.context);
                }
            }, 500L);
            if (ViVideoDeleteFragment.deleteVideoList.size() > 0) {
                ViVideoDeleteFragment.this.rl_NoDataLayout.setVisibility(8);
                ViVideoDeleteFragment.this.deleteVideoGridView.setVisibility(0);
            } else {
                ViVideoDeleteFragment.this.rl_NoDataLayout.setVisibility(0);
                ViVideoDeleteFragment.this.deleteVideoGridView.setVisibility(8);
            }
            ViVideoDeleteFragment.this.RefreshAdapter();
            Toast.makeText(ViVideoDeleteFragment.this.getActivity(), "Files are restored successfully.", 0).show();
        }
    }

    public void DeleteDialog(final ArrayList<Object> arrayList) {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_video);
        dialog.setTitle("Delete Video");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Delete Video");
        ((TextView) dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to delete video?");
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ViVideoDeleteFragment.this.DeleteSelected(arrayList);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ViVideoDeleteFragment.this.fromBtn != null) {
                    if (ViVideoDeleteFragment.this.fromBtn.equals("TopBtn")) {
                        ViVideoDeleteFragment.this.UnSelectAll();
                    } else {
                        ViVideoDeleteFragment.this.fromBtn.equals("BoxBtn");
                    }
                }
                if (ViVideoDeleteFragment.actionMode != null) {
                    ViVideoDeleteFragment.actionMode.finish();
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
            file.delete();
            ContentResolver contentResolver = this.activity.getContentResolver();
            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
        }
        ActionMode actionMode2 = actionMode;
        if (actionMode2 != null) {
            actionMode2.finish();
        }
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.delete_frame_layout, new ViVideoDeleteFragment());
        beginTransaction.commit();
    }

    private void SetAdapter() {
        new SortingVideoPrivateDialog(getActivity(), deleteVideoList, this.sortingHideVideoCallBack).Sorting(LoginPreferenceUtilsFolder.GetStringData(getActivity(), SortingVideoPrivateDialog.SortingName), LoginPreferenceUtilsFolder.GetStringData(getActivity(), SortingVideoPrivateDialog.SortingType));
        sortingCallBack();
        GetFirstHiddenAlbum();
        VideoDeleteAdapter gMVideoDeleteAdapter = new VideoDeleteAdapter(getActivity(), getContext(), deleteVideoList);
        deleteVideoAdapter = gMVideoDeleteAdapter;
        this.deleteVideoGridView.setAdapter((ListAdapter) gMVideoDeleteAdapter);
        DataOrientation(getResources().getConfiguration().orientation);
        deleteVideoAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() {
            @Override
            public void onClickMoreListener(ArrayList<Object> arrayList, Integer num, View view, Boolean bool) {
            }

            @Override
            public void onClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
                ViVideoDeleteFragment.sendDeleteVideoList = new ArrayList<>();
                ViVideoDeleteFragment.sendDeleteVideoList = arrayList;
                ViVideoDeleteFragment.this.bpos = num.intValue();
                if (!ViVideoDeleteFragment.this.isSingleSelection) {
                    String str = ((DataFileModel) ViVideoDeleteFragment.sendDeleteVideoList.get(num.intValue())).path;
                    str.substring(str.lastIndexOf("."));
                    if (str.endsWith(".mp4") || str.endsWith(".MP4") || str.endsWith(".3gp") || str.endsWith(".3GP") || str.endsWith(".mkv") || str.endsWith(".MKV")) {
                        Common.IdentifyActivity = "VideoDeleteFragment";
                        Intent intent = new Intent(ViVideoDeleteFragment.this.activity, PreviewActivity.class);
                        intent.putExtra(Common.gImagePath, ((DataFileModel) ViVideoDeleteFragment.sendDeleteVideoList.get(ViVideoDeleteFragment.this.bpos)).path);
                        intent.putExtra(Common.gTotalimage, ViVideoDeleteFragment.sendDeleteVideoList.size());
                        intent.putExtra(Common.gCurrenrtPosition, ViVideoDeleteFragment.this.bpos);
                        intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                        intent.putExtra(Common.gActivityname, "VideoDeleteFragment");
                        intent.putExtra(Common.gMediaType, ExifInterface.GPS_MEASUREMENT_3D);
                        ViVideoDeleteFragment.this.activity.startActivity(intent);
                    }
                } else if (bool.booleanValue()) {
                    Toast.makeText(ViVideoDeleteFragment.this.context, "isPrivateAlbum", 0).show();
                } else {
                    ViVideoDeleteFragment.this.SelectSingleImage(ViVideoDeleteFragment.sendDeleteVideoList, num.intValue());
                }
            }

            @Override
            public void onLongClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
                ViVideoDeleteFragment.sendDeleteVideoList = new ArrayList<>();
                ViVideoDeleteFragment.sendDeleteVideoList = arrayList;
                if (bool.booleanValue()) {
                    Toast.makeText(ViVideoDeleteFragment.this.context, "isPrivateAlbum", 0).show();
                    return;
                }
                ViVideoDeleteFragment.this.folderPath = ((DataFileModel) ViVideoDeleteFragment.sendDeleteVideoList.get(num.intValue())).folderPath;
                if (ViVideoDeleteFragment.actionMode == null) {
                    ViVideoDeleteFragment.actionMode = ((AppCompatActivity) ViVideoDeleteFragment.this.getActivity()).startSupportActionMode(ViVideoDeleteFragment.this.actionModeCallback);
                }
                ViVideoDeleteFragment.this.SelectSingleImage(ViVideoDeleteFragment.sendDeleteVideoList, num.intValue());
            }
        });
    }

    public void GetFirstHiddenAlbum() {
        Collections.sort(deleteVideoList, new Comparator<Object>() {
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
        VideoDeleteAdapter gMVideoDeleteAdapter = deleteVideoAdapter;
        if (gMVideoDeleteAdapter != null) {
            gMVideoDeleteAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        GridView gridView = this.deleteVideoGridView;
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        gridView.setNumColumns(this.i2);
    }

    public void RefreshAdapter() {
        getActivity().runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                FragmentTransaction beginTransaction = ViVideoDeleteFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.delete_frame_layout, new ViVideoDeleteFragment());
                beginTransaction.commit();
                ViVideoDeleteFragment.deleteVideoList = new ArrayList<>();
                ViVideoDeleteFragment gMVideoDeleteFragment = ViVideoDeleteFragment.this;
                try {
                    ViVideoDeleteFragment.deleteVideoList = gMVideoDeleteFragment.GetDeletedVideoFiles(gMVideoDeleteFragment.getActivity());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ViVideoDeleteFragment.this.GetFirstHiddenAlbum();
                ViVideoDeleteFragment.deleteVideoAdapter = new VideoDeleteAdapter(ViVideoDeleteFragment.this.getActivity(), ViVideoDeleteFragment.this.getContext(), ViVideoDeleteFragment.deleteVideoList);
                ViVideoDeleteFragment.this.deleteVideoGridView.setAdapter((ListAdapter) ViVideoDeleteFragment.deleteVideoAdapter);
                ViVideoDeleteFragment gMVideoDeleteFragment2 = ViVideoDeleteFragment.this;
                gMVideoDeleteFragment2.DataOrientation(gMVideoDeleteFragment2.activity.getResources().getConfiguration().orientation);
                ViVideoDeleteFragment.this.SortingCallBack();
                Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoDeleteFragment.this.activity.getResources().getDrawable(R.drawable.action_select));
            }
        });
    }

    private void sortingCallBack() {
        this.sortingHideVideoCallBack = new sortCallBack();
    }


    public class sortCallBack implements HideVideoFolderDataSortingListener {
        sortCallBack() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ViVideoDeleteFragment.deleteVideoList = new ArrayList<>();
            ViVideoDeleteFragment.deleteVideoList.addAll(arrayList);
            ViVideoDeleteFragment.this.GetFirstHiddenAlbum();
            ViVideoDeleteFragment.deleteVideoAdapter = new VideoDeleteAdapter(ViVideoDeleteFragment.this.getActivity(), ViVideoDeleteFragment.this.getContext(), ViVideoDeleteFragment.deleteVideoList);
            ViVideoDeleteFragment.this.deleteVideoGridView.setAdapter((ListAdapter) ViVideoDeleteFragment.deleteVideoAdapter);
            ViVideoDeleteFragment gMVideoDeleteFragment = ViVideoDeleteFragment.this;
            gMVideoDeleteFragment.DataOrientation(gMVideoDeleteFragment.getResources().getConfiguration().orientation);
            ViVideoDeleteFragment.deleteVideoAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMVideoDeleteFragment.sortCallBack.1
                @Override
                public void onClickMoreListener(ArrayList<Object> arrayList2, Integer num, View view, Boolean bool) {
                }

                @Override
                public void onClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ViVideoDeleteFragment.sendDeleteVideoList = new ArrayList<>();
                    ViVideoDeleteFragment.sendDeleteVideoList = arrayList2;
                    ViVideoDeleteFragment.this.bpos = num.intValue();
                    if (!ViVideoDeleteFragment.this.isSingleSelection) {
                        String str = ((DataFileModel) ViVideoDeleteFragment.sendDeleteVideoList.get(num.intValue())).path;
                        str.substring(str.lastIndexOf("."));
                        if (str.endsWith(".mp4") || str.endsWith(".MP4") || str.endsWith(".3gp") || str.endsWith(".3GP") || str.endsWith(".mkv") || str.endsWith(".MKV")) {
                            Common.IdentifyActivity = "VideoDeleteFragment";
                            Intent intent = new Intent(ViVideoDeleteFragment.this.activity, PreviewActivity.class);
                            intent.putExtra(Common.gImagePath, ((DataFileModel) ViVideoDeleteFragment.sendDeleteVideoList.get(ViVideoDeleteFragment.this.bpos)).path);
                            intent.putExtra(Common.gTotalimage, ViVideoDeleteFragment.sendDeleteVideoList.size());
                            intent.putExtra(Common.gCurrenrtPosition, ViVideoDeleteFragment.this.bpos);
                            intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                            intent.putExtra(Common.gActivityname, "VideoDeleteFragment");
                            intent.putExtra(Common.gMediaType, ExifInterface.GPS_MEASUREMENT_3D);
                            ViVideoDeleteFragment.this.activity.startActivity(intent);
                        }
                    } else if (bool.booleanValue()) {
                        Toast.makeText(ViVideoDeleteFragment.this.context, "isPrivateAlbum", 0).show();
                    } else {
                        ViVideoDeleteFragment.this.SelectSingleImage(ViVideoDeleteFragment.sendDeleteVideoList, num.intValue());
                    }
                }

                @Override
                public void onLongClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ViVideoDeleteFragment.sendDeleteVideoList = new ArrayList<>();
                    ViVideoDeleteFragment.sendDeleteVideoList = arrayList2;
                    if (bool.booleanValue()) {
                        Toast.makeText(ViVideoDeleteFragment.this.context, "isPrivateAlbum", 0).show();
                        return;
                    }
                    ViVideoDeleteFragment.this.folderPath = ((DataFileModel) ViVideoDeleteFragment.sendDeleteVideoList.get(num.intValue())).folderPath;
                    if (ViVideoDeleteFragment.actionMode == null) {
                        ViVideoDeleteFragment.actionMode = ((AppCompatActivity) ViVideoDeleteFragment.this.getActivity()).startSupportActionMode(ViVideoDeleteFragment.this.actionModeCallback);
                    }
                    ViVideoDeleteFragment.this.SelectSingleImage(ViVideoDeleteFragment.sendDeleteVideoList, num.intValue());
                }
            });
        }
    }

    public void SortingCallBack() {
        this.refreshAdapterCallBack = new RefreshData();
    }


    public class RefreshData implements RefreshAdapterListener {
        RefreshData() {
        }

        @Override
        public void Refresh(Boolean bool) {
            ViVideoDeleteFragment.deleteVideoAdapter.notifyDataSetChanged();
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
    }

    @Override 
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
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
}
