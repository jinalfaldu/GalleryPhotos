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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_DeleteTrashActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.PreviewActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.IImageDeleteAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideImageFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickHideListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.RefreshAdapterListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.LoginPreferenceUtilsFolder;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingPrivateImageDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ImImageDeleteFragment extends BaseFragment {
    static String TAG = "ImageDeleteFragment";
    public static ArrayList<Object> deleteImageList;
    public static int pos1;
    public static IImageDeleteAdapter imageDeleteAdapter;
    public static ActionMode mActionMode;
    public static ArrayList<Object> sendDeleteImageList;
    Activity activity;
    int bpos;
    Context context;
    GridView deletePhotoGridView;
    String folderPath;
    String fromBtn;
    HideImageFolderDataSortingListener hideImageFolderDataSortingListener;
    int i2;
    ImageView imgDelete;
    ImageView imgEdit;
    ImageView imgSetWallpaper;
    ImageView imgShare;
    ImageView imgUnlockButton;
    LinearLayout ll_bottomControl;
    private Menu mMenu;
    PrefClass preferenceClass;
    int pv;
    RefreshAdapterListener refreshAdapterListener;
    RelativeLayout rlDeleteNotice;
    RelativeLayout rl_NoDataLayout;
    int GRANT_PERMISSION = 333;
    int PRIVATE_ALBUM_RESULT = 444;
    int REFRESH_RESULT = 555;
    int REQUEST_ID_SET_AS_WALLPAPER = 111;
    int countSelected = 0;
    boolean isSelectAll = false;
    boolean isSingleSelection = false;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override 
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            ImImageDeleteFragment.this.mMenu = menu;
            actionMode.getMenuInflater().inflate(R.menu.delete_data_menu, menu);
            return true;
        }

        @Override 
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            if (Build.VERSION.SDK_INT < 21) {
                return true;
            }
            ImImageDeleteFragment.this.getActivity().getWindow().clearFlags(67108864);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.Delete :
                    if (ImImageDeleteFragment.this.GetSelectedList().size() >= 1) {
                        ImImageDeleteFragment.this.DeleteDialog();
                    } else {
                        Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
                    }
                    return true;
                case R.id.Selector:
                    if (ImImageDeleteFragment.this.isSelectAll) {
                        ImImageDeleteFragment.this.UnSelectAll();
                        ImImageDeleteFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ImImageDeleteFragment.this.activity, R.drawable.action_select));
                        actionMode.finish();
                    } else if (ImImageDeleteFragment.deleteImageList.size() >= 1) {
                        ImImageDeleteFragment.this.fromBtn = "BoxBtn";
                        ImImageDeleteFragment.this.SelectAll();
                        ImImageDeleteFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ImImageDeleteFragment.this.activity, R.drawable.action_unselect));
                    } else {
                        Toast.makeText(ImImageDeleteFragment.this.context, "No Photos Found!!!", 0).show();
                    }
                    return true;
                case R.id.Share:
                    if (ImImageDeleteFragment.this.GetSelectedList().size() >= 1) {
                        ImImageDeleteFragment gMImageDeleteFragment = ImImageDeleteFragment.this;
                        gMImageDeleteFragment.ShareImages(gMImageDeleteFragment.GetSelectedList());
                    } else {
                        Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
                    }
                    actionMode.finish();
                    return true;
                case R.id.Unlock:
                    if (ImImageDeleteFragment.this.GetSelectedList().size() > 0) {
                        ImImageDeleteFragment.this.RestoreDialog();
                    } else {
                        Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override 
        public void onDestroyActionMode(ActionMode actionMode) {
            ImImageDeleteFragment.mActionMode = null;
            ImImageDeleteFragment.this.UnSelectAll();
            if (Build.VERSION.SDK_INT >= 21) {
                ImImageDeleteFragment.this.getActivity().getWindow().clearFlags(67108864);
            }
        }
    };

    @Override 
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override 
    public void onResume() {
        super.onResume();
        if (deleteImageList.size() > 0) {
            this.rl_NoDataLayout.setVisibility(8);
            this.deletePhotoGridView.setVisibility(0);
            this.rlDeleteNotice.setVisibility(0);
        } else {
            this.deletePhotoGridView.setVisibility(8);
            this.rl_NoDataLayout.setVisibility(0);
            this.rlDeleteNotice.setVisibility(8);
        }
        DataOrientation(getResources().getConfiguration().orientation);
        IImageDeleteAdapter gMImageDeleteAdapter = imageDeleteAdapter;
        if (gMImageDeleteAdapter != null) {
            gMImageDeleteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DataOrientation(configuration.orientation);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_delete_image, viewGroup, false);
        this.activity = getActivity();
        this.preferenceClass = new PrefClass(getActivity());
        ID_Binding(inflate);
        deleteImageList = new ArrayList<>();
        try {
            ArrayList<Object> GetDeletedImageFiles = GetDeletedImageFiles(getActivity());
            deleteImageList = GetDeletedImageFiles;
            Collections.reverse(GetDeletedImageFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SetAdapter();
        Degu_MainActivity.ivUnLock.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.GetSelectedList().size() > 0) {
                    ImImageDeleteFragment.this.RestoreDialog();
                } else {
                    Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
                }
            }
        });
        Degu_MainActivity.ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.isSelectAll) {
                    ImImageDeleteFragment.this.UnSelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ImImageDeleteFragment.this.getResources().getDrawable(R.drawable.action_select));
                } else if (ImImageDeleteFragment.deleteImageList.size() >= 1) {
                    ImImageDeleteFragment.this.fromBtn = "TopBtn";
                    ImImageDeleteFragment.this.SelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ImImageDeleteFragment.this.getResources().getDrawable(R.drawable.action_unselect));
                } else {
                    Toast.makeText(ImImageDeleteFragment.this.context, "No Photos Found!!", 0).show();
                }
            }
        });
        Degu_MainActivity.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.GetSelectedList().size() >= 1) {
                    ImImageDeleteFragment.this.DeleteDialog();
                } else {
                    Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
                }
            }
        });
        Degu_MainActivity.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.GetSelectedList().size() >= 1) {
                    ImImageDeleteFragment gMImageDeleteFragment = ImImageDeleteFragment.this;
                    gMImageDeleteFragment.ShareImages(gMImageDeleteFragment.GetSelectedList());
                    return;
                }
                Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
            }
        });
        if (deleteImageList.size() > 0) {
            Degu_DeleteTrashActivity.allDelete.setVisibility(0);
        } else {
            Degu_DeleteTrashActivity.allDelete.setVisibility(8);
        }
        Degu_DeleteTrashActivity.allDelete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageDeleteFragment.this.fromBtn = "TopBtn";
                ImImageDeleteFragment.this.SelectAll();
                ImImageDeleteFragment.this.DeleteDialog();
            }
        });
        SetUpRefreshAdapter();
        return inflate;
    }

    private void ID_Binding(View view) {
        this.deletePhotoGridView = (GridView) view.findViewById(R.id.grid_view_private_photo_album);
        this.rlDeleteNotice =  view.findViewById(R.id.rlDeleteNotice);
        this.rl_NoDataLayout =  view.findViewById(R.id.hintNoHideImageLayout);
        this.ll_bottomControl = (LinearLayout) view.findViewById(R.id.bottomControlLayout);
        this.imgUnlockButton =  view.findViewById(R.id.btnImageUnloackButton);
        this.imgEdit =  view.findViewById(R.id.edit_ic);
        this.imgDelete =  view.findViewById(R.id.delete_ic);
        this.imgSetWallpaper =  view.findViewById(R.id.setWallpaper_ic);
        this.imgShare =  view.findViewById(R.id.share_ic);
        Click_Event();
    }

    private void Click_Event() {
        this.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.GetSelectedList().size() >= 1) {
                    ImImageDeleteFragment.this.DeleteDialog();
                } else {
                    Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
                }
            }
        });
        this.imgSetWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.GetSelectedList().size() == 1) {
                    Intent intent = new Intent("android.intent.action.ATTACH_DATA");
                    intent.addFlags(1);
                    intent.addFlags(524288);
                    intent.setDataAndType(FileProvider.getUriForFile(ImImageDeleteFragment.this.activity, "hdphoto.galleryimages.gelleryalbum.android.provider", new File(ImImageDeleteFragment.this.GetSelectedList().get(0).path)), "image/*");
                    intent.putExtra("SET_LOCKSCREEN_WALLPAPER", true);
                    ImImageDeleteFragment.this.activity.startActivityForResult(Intent.createChooser(intent, ImImageDeleteFragment.this.getString(R.string.set_as)), ImImageDeleteFragment.this.REQUEST_ID_SET_AS_WALLPAPER);
                    return;
                }
                Toast.makeText(ImImageDeleteFragment.this.context, "Select only one image.", 0).show();
            }
        });
        this.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.GetSelectedList().size() >= 1) {
                    ImImageDeleteFragment gMImageDeleteFragment = ImImageDeleteFragment.this;
                    gMImageDeleteFragment.ShareImages(gMImageDeleteFragment.GetSelectedList());
                    return;
                }
                Toast.makeText(ImImageDeleteFragment.this.context, "Select Image.", 0).show();
            }
        });
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
            HiddenView();
            ActionMode actionMode = mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
        }
        if (mActionMode != null && GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        imageDeleteAdapter.notifyDataSetChanged();
    }

    public ArrayList<DataFileModel> GetSelectedList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = deleteImageList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) deleteImageList.get(i)).isSelected) {
                arrayList.add((DataFileModel) deleteImageList.get(i));
            }
        }
        return arrayList;
    }

    public void UnSelectAll() {
        int size = deleteImageList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) deleteImageList.get(i)).isSelected = false;
        }
        if (mActionMode != null && GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = 0;
        this.isSingleSelection = false;
        this.isSelectAll = false;
        HiddenView();
        imageDeleteAdapter.notifyDataSetChanged();
    }

    public void SelectAll() {
        int size = deleteImageList.size();
        for (int i = 0; i < size; i++) {
            if (!((DataFileModel) deleteImageList.get(i)).isDirectory) {
                ((DataFileModel) deleteImageList.get(i)).isSelected = true;
            }
        }
        if (mActionMode != null && GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = deleteImageList.size();
        this.isSingleSelection = true;
        this.isSelectAll = true;
        VisibleView();
        imageDeleteAdapter.notifyDataSetChanged();
    }

    public void RestoreDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_restore_image);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Restore Image");
        ((TextView) dialog.findViewById(R.id.textDesc)).setText("Are you sure you want to restore image?");
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageDeleteFragment.this.isSelectAll = false;
                ImImageDeleteFragment.this.isSingleSelection = false;
                new RestoreImageExecute(ImImageDeleteFragment.this.GetSelectedList()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageDeleteFragment.this.UnSelectAll();
                if (ImImageDeleteFragment.mActionMode != null) {
                    ImImageDeleteFragment.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class RestoreImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;
        ProgressDialog progressRestore;

        public RestoreImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            this.progressRestore = new ProgressDialog(ImImageDeleteFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            this.progressRestore.setMessage("Please wait a while...");
            this.progressRestore.setProgressStyle(0);
            this.progressRestore.setIndeterminate(false);
            this.progressRestore.setCancelable(false);
            this.progressRestore.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = ImImageDeleteFragment.this.preferenceClass.getListString(Common.gOldPath);
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(this.pathList.get(i).path);
                String name = file.getName();
                File file2 = new File(FolderPath.SDCARD_PATH_IMAGE_TRASH_BACKUP);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                File file3 = new File(file2 + File.separator + name);
                if (file3.exists()) {
                    Log.e(ImImageDeleteFragment.TAG, "doInBackground: ");
                }
                String parent = file3.getParent();
                new MediaScanners(GalleryAppClass.getInstance(), file3);
                try {
                    FileUtils.CopyMoveFile(file, file3);
                    AppUtilsClass.insertUri(ImImageDeleteFragment.this.getActivity(), file3);
                    long j = this.pathList.get(i).id;
                    String str = this.pathList.get(i).name;
                    String str2 = this.pathList.get(i).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file4 = new File(this.pathList.get(i2).path);
                            file4.delete();
                            ContentResolver contentResolver = ImImageDeleteFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                        }
                        ImImageDeleteFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("OOOOO", " 000 - " + e.getMessage());
                }
            }
            return null;
        }

        @Override 
        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            ImImageDeleteFragment.this.isSingleSelection = false;
            ImImageDeleteFragment.this.isSelectAll = false;
            this.progressRestore.dismiss();
            if (ImImageDeleteFragment.mActionMode != null) {
                ImImageDeleteFragment.mActionMode.finish();
            }
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo((Activity) ImImageDeleteFragment.this.context);
                }
            }, 500L);
            if (ImImageDeleteFragment.deleteImageList.size() > 0) {
                ImImageDeleteFragment.this.rl_NoDataLayout.setVisibility(8);
                ImImageDeleteFragment.this.deletePhotoGridView.setVisibility(0);
            } else {
                ImImageDeleteFragment.this.rl_NoDataLayout.setVisibility(0);
                ImImageDeleteFragment.this.deletePhotoGridView.setVisibility(8);
            }
            ImImageDeleteFragment.this.RefreshAdapter();
            ImImageDeleteFragment.this.HiddenView();
            Toast.makeText(ImImageDeleteFragment.this.getActivity(), "Files are restored successfully.", 0).show();
        }
    }

    public void DeleteDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_image);
        dialog.setTitle("Delete Image");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageDeleteFragment.this.DeleteSelectedList();
                ImImageDeleteFragment.this.HiddenView();
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageDeleteFragment.this.fromBtn != null) {
                    if (ImImageDeleteFragment.this.fromBtn.equals("TopBtn")) {
                        ImImageDeleteFragment.this.UnSelectAll();
                    } else {
                        ImImageDeleteFragment.this.fromBtn.equals("BoxBtn");
                    }
                }
                if (ImImageDeleteFragment.mActionMode != null) {
                    ImImageDeleteFragment.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DeleteSelectedList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(GetSelectedList());
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                new File(((DataFileModel) arrayList.get(i)).path).delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            File file = new File(((DataFileModel) arrayList.get(i2)).path);
            file.delete();
            ContentResolver contentResolver = this.activity.getContentResolver();
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
        }
        ActionMode actionMode = mActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.delete_frame_layout, new ImImageDeleteFragment());
        beginTransaction.commit();
    }

    public void SetAdapter() {
        new SortingPrivateImageDialog(getActivity(), deleteImageList, this.hideImageFolderDataSortingListener).Sorting(LoginPreferenceUtilsFolder.GetStringData(getActivity(), SortingPrivateImageDialog.SortingName), LoginPreferenceUtilsFolder.GetStringData(getActivity(), SortingPrivateImageDialog.SortingType));
        sortingCallBack();
        GetFirstHiddenAlbum();
        IImageDeleteAdapter gMImageDeleteAdapter = new IImageDeleteAdapter(getActivity(), getContext(), deleteImageList);
        imageDeleteAdapter = gMImageDeleteAdapter;
        this.deletePhotoGridView.setAdapter((ListAdapter) gMImageDeleteAdapter);
        DataOrientation(getResources().getConfiguration().orientation);
        imageDeleteAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() {
            @Override
            public void onClickMoreListener(ArrayList<Object> arrayList, Integer num, View view, Boolean bool) {
            }

            @Override
            public void onClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
                ImImageDeleteFragment.sendDeleteImageList = new ArrayList<>();
                ImImageDeleteFragment.sendDeleteImageList = arrayList;
                ImImageDeleteFragment.this.bpos = num.intValue();
                if (!ImImageDeleteFragment.this.isSingleSelection) {
                    String str = ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(num.intValue())).path;
                    str.substring(str.lastIndexOf("."));
                    if (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) {
                        Common.IdentifyActivity = "ImageDeleteFragment";
                        Intent intent = new Intent(ImImageDeleteFragment.this.activity, PreviewActivity.class);
                        intent.putExtra(Common.gImagePath, ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(ImImageDeleteFragment.this.bpos)).path);
                        intent.putExtra(Common.gTotalimage, ImImageDeleteFragment.sendDeleteImageList.size());
                        intent.putExtra(Common.gCurrenrtPosition, ImImageDeleteFragment.this.bpos);
                        intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                        intent.putExtra(Common.gActivityname, "ImageDeleteFragment");
                        intent.putExtra(Common.gMediaType, ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(ImImageDeleteFragment.this.bpos)).mediaType);
                        ImImageDeleteFragment.this.activity.startActivity(intent);
                    }
                } else if (bool.booleanValue()) {
                    Toast.makeText(ImImageDeleteFragment.this.context, "isPrivateAlbum", 0).show();
                } else {
                    ImImageDeleteFragment.this.SelectSingleImage(ImImageDeleteFragment.sendDeleteImageList, num.intValue());
                }
            }

            @Override
            public void onLongClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
                ImImageDeleteFragment.sendDeleteImageList = new ArrayList<>();
                ImImageDeleteFragment.sendDeleteImageList = arrayList;
                if (bool.booleanValue()) {
                    Log.e(ImImageDeleteFragment.TAG, "onLongClickListener: ");
                    return;
                }
                ImImageDeleteFragment.this.folderPath = ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(num.intValue())).folderPath;
                ImImageDeleteFragment.this.VisibleView();
                if (ImImageDeleteFragment.mActionMode == null) {
                    ImImageDeleteFragment.mActionMode = ((AppCompatActivity) ImImageDeleteFragment.this.getActivity()).startSupportActionMode(ImImageDeleteFragment.this.mActionModeCallback);
                }
                ImImageDeleteFragment.this.SelectSingleImage(ImImageDeleteFragment.sendDeleteImageList, num.intValue());
            }
        });
    }

    public void GetFirstHiddenAlbum() {
        Collections.sort(deleteImageList, new Comparator<Object>() {
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
        IImageDeleteAdapter gMImageDeleteAdapter = imageDeleteAdapter;
        if (gMImageDeleteAdapter != null) {
            gMImageDeleteAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        GridView gridView = this.deletePhotoGridView;
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        gridView.setNumColumns(this.i2);
    }

    public void RefreshAdapter() {
        getActivity().runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                FragmentTransaction beginTransaction = ImImageDeleteFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.delete_frame_layout, new ImImageDeleteFragment());
                beginTransaction.commit();
                ImImageDeleteFragment.deleteImageList = new ArrayList<>();
                ImImageDeleteFragment gMImageDeleteFragment = ImImageDeleteFragment.this;
                ImImageDeleteFragment.deleteImageList = gMImageDeleteFragment.GetDeletedImageFiles(gMImageDeleteFragment.getActivity());
                ImImageDeleteFragment.this.GetFirstHiddenAlbum();
                ImImageDeleteFragment.imageDeleteAdapter = new IImageDeleteAdapter(ImImageDeleteFragment.this.getActivity(), ImImageDeleteFragment.this.getContext(), ImImageDeleteFragment.deleteImageList);
                ImImageDeleteFragment.this.deletePhotoGridView.setAdapter((ListAdapter) ImImageDeleteFragment.imageDeleteAdapter);
                ImImageDeleteFragment gMImageDeleteFragment2 = ImImageDeleteFragment.this;
                gMImageDeleteFragment2.DataOrientation(gMImageDeleteFragment2.activity.getResources().getConfiguration().orientation);
                Degu_MainActivity.ivSelectAll.setImageDrawable(ImImageDeleteFragment.this.activity.getResources().getDrawable(R.drawable.action_select));
            }
        });
    }

    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == this.GRANT_PERMISSION) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.activity.getContentResolver().takePersistableUriPermission(intent.getData(), intent.getFlags());
            }
            if (GetSelectedList().size() > 0) {
                RestoreDialog();
            } else {
                Toast.makeText(this.context, "Select Image.", 0).show();
            }
        } else if (i2 == -1 && i == this.PRIVATE_ALBUM_RESULT) {
            RefreshAdapter();
        } else if (i == this.REFRESH_RESULT) {
            RefreshAdapter();
        }
    }

    public void VisibleView() {
        this.ll_bottomControl.setVisibility(8);
        Degu_MainActivity.ivAddAlbum.setVisibility(8);
    }

    public void HiddenView() {
        this.ll_bottomControl.setVisibility(8);
    }

    private void SetUpRefreshAdapter() {
        this.refreshAdapterListener = new RefreshDataExecute();
    }


    public class RefreshDataExecute implements RefreshAdapterListener {
        RefreshDataExecute() {
        }

        @Override
        public void Refresh(Boolean bool) {
            ImImageDeleteFragment.imageDeleteAdapter.notifyDataSetChanged();
        }
    }

    private void sortingCallBack() {
        this.hideImageFolderDataSortingListener = new SortListenerHideImageFolderData();
    }


    public class SortListenerHideImageFolderData implements HideImageFolderDataSortingListener {
        SortListenerHideImageFolderData() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ImImageDeleteFragment.deleteImageList = new ArrayList<>();
            ImImageDeleteFragment.deleteImageList.addAll(arrayList);
            ImImageDeleteFragment.this.GetFirstHiddenAlbum();
            ImImageDeleteFragment.imageDeleteAdapter = new IImageDeleteAdapter(ImImageDeleteFragment.this.getActivity(), ImImageDeleteFragment.this.getContext(), ImImageDeleteFragment.deleteImageList);
            ImImageDeleteFragment.this.deletePhotoGridView.setAdapter((ListAdapter) ImImageDeleteFragment.imageDeleteAdapter);
            ImImageDeleteFragment gMImageDeleteFragment = ImImageDeleteFragment.this;
            gMImageDeleteFragment.DataOrientation(gMImageDeleteFragment.getResources().getConfiguration().orientation);
            ImImageDeleteFragment.imageDeleteAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMImageDeleteFragment.SortListenerHideImageFolderData.1
                @Override
                public void onClickMoreListener(ArrayList<Object> arrayList2, Integer num, View view, Boolean bool) {
                }

                @Override
                public void onClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ImImageDeleteFragment.sendDeleteImageList = new ArrayList<>();
                    ImImageDeleteFragment.sendDeleteImageList = arrayList2;
                    ImImageDeleteFragment.this.bpos = num.intValue();
                    if (!ImImageDeleteFragment.this.isSingleSelection) {
                        String str = ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(num.intValue())).path;
                        str.substring(str.lastIndexOf("."));
                        if (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) {
                            Common.IdentifyActivity = "ImageDeleteFragment";
                            ImImageDeleteFragment.pos1 = num.intValue();
                            Intent intent = new Intent(ImImageDeleteFragment.this.activity, PreviewActivity.class);
                            intent.putExtra(Common.gImagePath, ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(ImImageDeleteFragment.this.bpos)).path);
                            intent.putExtra(Common.gTotalimage, ImImageDeleteFragment.sendDeleteImageList.size());
                            intent.putExtra(Common.gCurrenrtPosition, ImImageDeleteFragment.this.bpos);
                            intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                            intent.putExtra(Common.gActivityname, "ImageDeleteFragment");
                            intent.putExtra(Common.gMediaType, ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(ImImageDeleteFragment.this.bpos)).mediaType);
                            ImImageDeleteFragment.this.activity.startActivity(intent);
                        }
                    } else if (bool.booleanValue()) {
                        Toast.makeText(ImImageDeleteFragment.this.context, "isPrivateAlbum", 0).show();
                    } else {
                        ImImageDeleteFragment.this.SelectSingleImage(ImImageDeleteFragment.sendDeleteImageList, num.intValue());
                    }
                }

                @Override
                public void onLongClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ImImageDeleteFragment.sendDeleteImageList = new ArrayList<>();
                    ImImageDeleteFragment.sendDeleteImageList = arrayList2;
                    if (bool.booleanValue()) {
                        return;
                    }
                    ImImageDeleteFragment.this.folderPath = ((DataFileModel) ImImageDeleteFragment.sendDeleteImageList.get(num.intValue())).folderPath;
                    ImImageDeleteFragment.this.VisibleView();
                    if (ImImageDeleteFragment.mActionMode == null) {
                        ImImageDeleteFragment.mActionMode = ((AppCompatActivity) ImImageDeleteFragment.this.getActivity()).startSupportActionMode(ImImageDeleteFragment.this.mActionModeCallback);
                    }
                    ImImageDeleteFragment.this.SelectSingleImage(ImImageDeleteFragment.sendDeleteImageList, num.intValue());
                }
            });
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
}
