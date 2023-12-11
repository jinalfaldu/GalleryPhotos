package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.Musician_UriToUrl;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.PreviewActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.ImagePrivateAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideImageFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickHideListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.RefreshAdapterListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.LoginPreferenceUtilsData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingPrivateImageDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import com.google.android.exoplayer2.util.MimeTypes;
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



public class ImImagePrivateFragment extends BaseFragment implements View.OnClickListener {
    public static String FName = "";
    static String TAG = "ImagePrivateFragment";
    public static ActionMode mActionMode;
    public static ImagePrivateAdapter privateImageAdapter;
    public static ArrayList<Object> privateImageList;
    public static int privateimage;
    public static ArrayList<Object> sendPrivateImageList;
    AccessFolder11 accessFolder11;
    Activity activity;
    int bpos;
    Context context;
    BottomSheetDialog dialog;
    String folderPath;
    HideImageFolderDataSortingListener hideImageFolderDataSortingListener;
    int i2;
    ImageView imgDelete;
    ImageView imgEdit;
    ImageView imgSetWallpaper;
    ImageView imgShare;
    ImageView imgUnlokButton;
    LinearLayout ll_bottomControl;
    private Menu mMenu;
    PrefClass preferenceClass;
    GridView privateImageGridView;
    int pv;
    RefreshAdapterListener refreshAdapterListener;
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
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return true;
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            ImImagePrivateFragment.this.mMenu = menu;
            actionMode.getMenuInflater().inflate(R.menu.private_data_menu, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.Delete:
                    if (ImImagePrivateFragment.this.GetSelectedList().size() >= 1) {
                        ImImagePrivateFragment.this.DeleteDialog();
                    } else {
                        Toast.makeText(ImImagePrivateFragment.this.context, "Select Image.", 0).show();
                    }
                    return true;
                case R.id.Selector:
                    int i = ImImagePrivateFragment.this.preferenceClass.getInt(Common.gIsLockStatus, 0);
                    if (i == 0) {
                        Toast.makeText(ImImagePrivateFragment.this.context, "Please Set Security!!", 0).show();
                    } else if (i == 1) {
                        Toast.makeText(ImImagePrivateFragment.this.context, "Please Enter Passcode!!", 0).show();
                    } else if (ImImagePrivateFragment.this.isSelectAll) {
                        ImImagePrivateFragment.this.UnSelectAll();
                        ImImagePrivateFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ImImagePrivateFragment.this.activity, R.drawable.action_select));
                        actionMode.finish();
                    } else if (ImImagePrivateFragment.privateImageList.size() >= 1) {
                        ImImagePrivateFragment.this.SelectAll();
                        ImImagePrivateFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ImImagePrivateFragment.this.activity, R.drawable.action_unselect));
                    } else {
                        Toast.makeText(ImImagePrivateFragment.this.context, "No Photos Found!!", 0).show();
                    }
                    return true;
                case R.id.Share:
                    if (ImImagePrivateFragment.this.GetSelectedList().size() >= 1) {
                        ImImagePrivateFragment gMImagePrivateFragment = ImImagePrivateFragment.this;
                        gMImagePrivateFragment.ShareImages(gMImagePrivateFragment.GetSelectedList());
                    } else {
                        Toast.makeText(ImImagePrivateFragment.this.context, "Select Image.", 0).show();
                    }
                    actionMode.finish();
                    return true;
                case R.id.Unlock:
                    if (ImImagePrivateFragment.this.GetSelectedList().size() > 0) {
                        ImImagePrivateFragment.this.UnlockDialog();
                    } else {
                        Toast.makeText(ImImagePrivateFragment.this.context, "Select Image.", 0).show();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            ImImagePrivateFragment.mActionMode = null;
            ImImagePrivateFragment.this.UnSelectAll();
        }
    };
    private String folder_path = "";

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (privateImageList.size() > 0) {
            this.rl_NoDataLayout.setVisibility(8);
            this.privateImageGridView.setVisibility(0);
        } else {
            this.rl_NoDataLayout.setVisibility(0);
            this.privateImageGridView.setVisibility(8);
        }
        DataOrientation(getResources().getConfiguration().orientation);
        ImagePrivateAdapter gMImagePrivateAdapter = privateImageAdapter;
        if (gMImagePrivateAdapter != null) {
            gMImagePrivateAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DataOrientation(configuration.orientation);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image_private, viewGroup, false);
        this.activity = getActivity();
        this.preferenceClass = new PrefClass(getActivity());
        ID_Binding(inflate);
        privateImageList = new ArrayList<>();
        try {
            ArrayList<Object> GetPrivateImageFiles = GetPrivateImageFiles(getActivity());
            privateImageList = GetPrivateImageFiles;
            Collections.reverse(GetPrivateImageFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SetAdapter();
        Degu_MainActivity.ivUnLock.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImagePrivateFragment.this.GetSelectedList().size() > 0) {
                    ImImagePrivateFragment.this.UnlockDialog();
                } else {
                    Toast.makeText(ImImagePrivateFragment.this.context, "Select photo", 0).show();
                }
            }
        });
        Degu_MainActivity.ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                int i = ImImagePrivateFragment.this.preferenceClass.getInt(Common.gIsLockStatus, 0);
                if (i == 0) {
                    Toast.makeText(ImImagePrivateFragment.this.context, "Please Set Security!!", 0).show();
                } else if (i == 1) {
                    Toast.makeText(ImImagePrivateFragment.this.context, "Please Enter Passcode!!", 0).show();
                } else if (ImImagePrivateFragment.this.isSelectAll) {
                    ImImagePrivateFragment.this.UnSelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ImImagePrivateFragment.this.getResources().getDrawable(R.drawable.action_select));
                } else if (ImImagePrivateFragment.privateImageList.size() >= 1) {
                    ImImagePrivateFragment.this.SelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ImImagePrivateFragment.this.getResources().getDrawable(R.drawable.action_unselect));
                } else {
                    Toast.makeText(ImImagePrivateFragment.this.context, "No Photos Found!!", 0).show();
                }
            }
        });
        Degu_MainActivity.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImagePrivateFragment.this.GetSelectedList().size() >= 1) {
                    ImImagePrivateFragment.this.DeleteDialog();
                } else {
                    Toast.makeText(ImImagePrivateFragment.this.context, "Select Image.", 0).show();
                }
            }
        });
        Degu_MainActivity.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImagePrivateFragment.this.GetSelectedList().size() >= 1) {
                    ImImagePrivateFragment gMImagePrivateFragment = ImImagePrivateFragment.this;
                    gMImagePrivateFragment.ShareImages(gMImagePrivateFragment.GetSelectedList());
                    return;
                }
                Toast.makeText(ImImagePrivateFragment.this.context, "Select Image.", 0).show();
            }
        });
        RefreshPrivateAdapter();
        return inflate;
    }

    private void ID_Binding(View view) {
        this.privateImageGridView = (GridView) view.findViewById(R.id.grid_view_private_photo_album);
        this.rl_NoDataLayout = (RelativeLayout) view.findViewById(R.id.hintNoHideImageLayout);
        this.ll_bottomControl = (LinearLayout) view.findViewById(R.id.bottomControlLayout);
        this.imgUnlokButton = (ImageView) view.findViewById(R.id.btnImageUnloackButton);
        this.imgEdit = (ImageView) view.findViewById(R.id.edit_ic);
        this.imgDelete = (ImageView) view.findViewById(R.id.delete_ic);
        this.imgSetWallpaper = (ImageView) view.findViewById(R.id.setWallpaper_ic);
        this.imgShare = (ImageView) view.findViewById(R.id.share_ic);
        this.imgEdit.setOnClickListener(this);
        this.imgDelete.setOnClickListener(this);
        this.imgSetWallpaper.setOnClickListener(this);
        this.imgShare.setOnClickListener(this);
    }

    public void ShareFiles(Context context, ArrayList<DataFileModel> arrayList) {
        ArrayList<Uri> arrayList2 = new ArrayList<>();
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            for (int i = 0; i < arrayList.size(); i++) {
                String str = arrayList.get(i).path;
                arrayList2.add(str.startsWith("content") ? Uri.parse(str) : FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(str)));
            }
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList2);
            context.startActivity(Intent.createChooser(intent, "Share Image"));
        } catch (Exception e) {
            e.printStackTrace();
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
            this.isSelectAll = false;
            HiddenView();
            ActionMode actionMode = mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
        }
        if (GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        privateImageAdapter.notifyDataSetChanged();
    }

    public ArrayList<DataFileModel> GetSelectedList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = privateImageList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) privateImageList.get(i)).isSelected) {
                arrayList.add((DataFileModel) privateImageList.get(i));
            }
        }
        return arrayList;
    }

    public void UnSelectAll() {
        int size = privateImageList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) privateImageList.get(i)).isSelected = false;
        }
        if (GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = 0;
        this.isSingleSelection = false;
        this.isSelectAll = false;
        HiddenView();
        privateImageAdapter.notifyDataSetChanged();
    }

    public void SelectAll() {
        int size = privateImageList.size();
        for (int i = 0; i < size; i++) {
            if (!((DataFileModel) privateImageList.get(i)).isDirectory) {
                ((DataFileModel) privateImageList.get(i)).isSelected = true;
            }
        }
        if (GetSelectedList().size() != 0) {
            mActionMode.setTitle(String.valueOf(GetSelectedList().size()));
        }
        this.countSelected = privateImageList.size();
        this.isSingleSelection = true;
        this.isSelectAll = true;
        VisibleView();
        privateImageAdapter.notifyDataSetChanged();
    }

    public void UnlockDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_unlock_image);
        dialog.setTitle("Unlock Image");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImagePrivateFragment.this.isSelectAll = false;
                ImImagePrivateFragment.this.isSingleSelection = false;
                new UnLockImageExecute(ImImagePrivateFragment.this.GetSelectedList()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImagePrivateFragment.this.UnSelectAll();
                if (ImImagePrivateFragment.mActionMode != null) {
                    ImImagePrivateFragment.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class UnLockImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;
        ProgressDialog progressLock;

        public UnLockImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            this.progressLock = new ProgressDialog(ImImagePrivateFragment.this.getActivity());
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
            ArrayList<String> listString = ImImagePrivateFragment.this.preferenceClass.getListString(Common.gOldPath);
            for (int i = 0; i < this.pathList.size(); i++) {
                File file = new File(this.pathList.get(i).path);
                String name = file.getName();
                File file2 = new File(FolderPath.SDCARD_PATH_IMAGE_LOCK_BACKUP);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                File file3 = new File(file2 + File.separator + name);
                if (file3.exists()) {
                    Log.e(ImImagePrivateFragment.TAG, "doInBackground: ");
                }
                String parent = file3.getParent();
                new MediaScanners(GalleryAppClass.getInstance(), file3);
                try {
                    FileUtils.CopyMoveFile(file, file3);
                    AppUtilsClass.insertUri(ImImagePrivateFragment.this.getActivity(), file3);
                    long j = this.pathList.get(i).id;
                    String str = this.pathList.get(i).name;
                    String str2 = this.pathList.get(i).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file4 = new File(this.pathList.get(i2).path);
                            file4.delete();
                            ContentResolver contentResolver = ImImagePrivateFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                        }
                        ImImagePrivateFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            ImImagePrivateFragment.privateimage = 1;
            ImImagePrivateFragment.this.isSingleSelection = false;
            ImImagePrivateFragment.this.isSelectAll = false;
            this.progressLock.dismiss();
            if (ImImagePrivateFragment.mActionMode != null) {
                ImImagePrivateFragment.mActionMode.finish();
            }
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo(ImImagePrivateFragment.this.getActivity());
                }
            }, 500L);
            if (ImImagePrivateFragment.privateImageList.size() > 0) {
                ImImagePrivateFragment.this.rl_NoDataLayout.setVisibility(8);
                ImImagePrivateFragment.this.privateImageGridView.setVisibility(0);
            } else {
                ImImagePrivateFragment.this.rl_NoDataLayout.setVisibility(0);
                ImImagePrivateFragment.this.privateImageGridView.setVisibility(8);
            }
            ImImagePrivateFragment.this.RefreshAdapter();
            ImImagePrivateFragment.this.HiddenView();
            Toast.makeText(ImImagePrivateFragment.this.getActivity(), "Files are unlocked successfully.", 0).show();
        }
    }

    public void DeleteDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dg_delete_file);
        dialog.setTitle("Delete Image");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImagePrivateFragment.this.DeleteSelectedItem();
                ImImagePrivateFragment.this.HiddenView();
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImagePrivateFragment.this.UnSelectAll();
                if (ImImagePrivateFragment.mActionMode != null) {
                    ImImagePrivateFragment.mActionMode.finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DeleteSelectedItem() {
        ArrayList arrayList = new ArrayList(GetSelectedList());
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                new File(((DataFileModel) arrayList.get(i)).path).delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            File file = new File(((DataFileModel) arrayList.get(i2)).path);
            ContentResolver contentResolver = this.activity.getContentResolver();
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
        }
        ActionMode actionMode = mActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.private_frame_layout, new ImImagePrivateFragment());
        beginTransaction.commit();
    }

    public void SetAdapter() {
        new SortingPrivateImageDialog(getActivity(), privateImageList, this.hideImageFolderDataSortingListener).Sorting(LoginPreferenceUtilsData.GetStringData(getActivity(), SortingPrivateImageDialog.SortingName), LoginPreferenceUtilsData.GetStringData(getActivity(), SortingPrivateImageDialog.SortingType));
        SortingCallBack();
        getFirstHiddenAlbum();
        ImagePrivateAdapter gMImagePrivateAdapter = new ImagePrivateAdapter(getActivity(), getContext(), privateImageList);
        privateImageAdapter = gMImagePrivateAdapter;
        this.privateImageGridView.setAdapter((ListAdapter) gMImagePrivateAdapter);
        DataOrientation(getResources().getConfiguration().orientation);
        privateImageAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() {
            @Override
            public void onClickMoreListener(ArrayList<Object> arrayList, Integer num, View view, Boolean bool) {
            }

            @Override
            public void onLongClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
            }

            @Override
            public void onClickListener(ArrayList<Object> arrayList, Integer num, Boolean bool, View view) {
                ImImagePrivateFragment.sendPrivateImageList = new ArrayList<>();
                ImImagePrivateFragment.sendPrivateImageList = arrayList;
                ImImagePrivateFragment.this.bpos = num.intValue();
                if (!ImImagePrivateFragment.this.isSingleSelection) {
                    String str = ((DataFileModel) ImImagePrivateFragment.sendPrivateImageList.get(num.intValue())).path;
                    str.substring(str.lastIndexOf("."));
                    if (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) {
                        Common.IdentifyActivity = "ImagePrivateFragment";
                        Intent intent = new Intent(ImImagePrivateFragment.this.activity, PreviewActivity.class);
                        intent.putExtra(Common.gImagePath, ((DataFileModel) ImImagePrivateFragment.sendPrivateImageList.get(ImImagePrivateFragment.this.bpos)).path);
                        intent.putExtra(Common.gTotalimage, ImImagePrivateFragment.sendPrivateImageList.size());
                        intent.putExtra(Common.gCurrenrtPosition, ImImagePrivateFragment.this.bpos);
                        intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                        intent.putExtra(Common.gActivityname, "ImagePrivateFragment");
                        intent.putExtra(Common.gMediaType, ((DataFileModel) ImImagePrivateFragment.sendPrivateImageList.get(ImImagePrivateFragment.this.bpos)).mediaType);
                        ImImagePrivateFragment.this.activity.startActivity(intent);
                    }
                } else if (bool.booleanValue()) {
                    Toast.makeText(ImImagePrivateFragment.this.context, "isPrivateAlbum", 0).show();
                } else {
                    ImImagePrivateFragment.this.SelectSingleImage(ImImagePrivateFragment.sendPrivateImageList, num.intValue());
                }
            }
        });
    }

    public void getFirstHiddenAlbum() {
        Collections.sort(privateImageList, new Comparator<Object>() {
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
        ImagePrivateAdapter gMImagePrivateAdapter = privateImageAdapter;
        if (gMImagePrivateAdapter != null) {
            gMImagePrivateAdapter.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.dataDivider));
        }
        GridView gridView = this.privateImageGridView;
        if (i != 1) {
            this.i2 = Degu_MainActivity.dataDivider;
        }
        gridView.setNumColumns(this.i2);
    }

    public void RefreshAdapter() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction beginTransaction = ImImagePrivateFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.private_frame_layout, new ImImagePrivateFragment());
                beginTransaction.commit();
                try {
                    ImImagePrivateFragment.privateImageList = new ArrayList<>();
                    ImImagePrivateFragment gMImagePrivateFragment = ImImagePrivateFragment.this;
                    ImImagePrivateFragment.privateImageList = gMImagePrivateFragment.GetPrivateImageFiles(gMImagePrivateFragment.getActivity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ImImagePrivateFragment.this.getFirstHiddenAlbum();
                ImImagePrivateFragment.privateImageAdapter = new ImagePrivateAdapter(ImImagePrivateFragment.this.getActivity(), ImImagePrivateFragment.this.getContext(), ImImagePrivateFragment.privateImageList);
                ImImagePrivateFragment.this.privateImageGridView.setAdapter((ListAdapter) ImImagePrivateFragment.privateImageAdapter);
                ImImagePrivateFragment gMImagePrivateFragment2 = ImImagePrivateFragment.this;
                gMImagePrivateFragment2.DataOrientation(gMImagePrivateFragment2.activity.getResources().getConfiguration().orientation);
                Degu_MainActivity.ivSelectAll.setImageDrawable(ImImagePrivateFragment.this.activity.getResources().getDrawable(R.drawable.action_select));
            }
        });
    }

    @Override 
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.delete_ic) {
            if (GetSelectedList().size() >= 1) {
                DeleteDialog();
            } else {
                Toast.makeText(this.context, "Select Image.", 0).show();
            }
        } else if (id != R.id.setWallpaper_ic) {
            if (id == R.id.share_ic) {
                if (GetSelectedList().size() >= 1) {
                    ShareImages(GetSelectedList());
                } else {
                    Toast.makeText(this.context, "Select Image.", 0).show();
                }
            }
        } else if (GetSelectedList().size() == 1) {
            Intent intent = new Intent("android.intent.action.ATTACH_DATA");
            intent.addFlags(1);
            intent.addFlags(524288);
            intent.setDataAndType(FileProvider.getUriForFile(this.activity, "hdphoto.galleryimages.gelleryalbum.android.provider", new File(GetSelectedList().get(0).path)), "image/*");
            intent.putExtra("SET_LOCKSCREEN_WALLPAPER", true);
            this.activity.startActivityForResult(Intent.createChooser(intent, getString(R.string.set_as)), this.REQUEST_ID_SET_AS_WALLPAPER);
        } else {
            Toast.makeText(this.context, "Select only one image.", 0).show();
        }
    }

    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == this.GRANT_PERMISSION) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.activity.getContentResolver().takePersistableUriPermission(intent.getData(), intent.getFlags());
            }
            if (GetSelectedList().size() > 0) {
                UnlockDialog();
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

    private void RefreshPrivateAdapter() {
        this.refreshAdapterListener = new RefreshDataExecute();
    }


    public class RefreshDataExecute implements RefreshAdapterListener {
        RefreshDataExecute() {
        }

        @Override
        public void Refresh(Boolean bool) {
            ImImagePrivateFragment.privateImageAdapter.notifyDataSetChanged();
        }
    }

    private void SortingCallBack() {
        this.hideImageFolderDataSortingListener = new sortListenerHideImageFolderData();
    }


    public class sortListenerHideImageFolderData implements HideImageFolderDataSortingListener {
        sortListenerHideImageFolderData() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ImImagePrivateFragment.privateImageList = new ArrayList<>();
            ImImagePrivateFragment.privateImageList.addAll(arrayList);
            ImImagePrivateFragment.this.getFirstHiddenAlbum();
            ImImagePrivateFragment.privateImageAdapter = new ImagePrivateAdapter(ImImagePrivateFragment.this.getActivity(), ImImagePrivateFragment.this.getContext(), ImImagePrivateFragment.privateImageList);
            ImImagePrivateFragment.this.privateImageGridView.setAdapter((ListAdapter) ImImagePrivateFragment.privateImageAdapter);
            ImImagePrivateFragment gMImagePrivateFragment = ImImagePrivateFragment.this;
            gMImagePrivateFragment.DataOrientation(gMImagePrivateFragment.getResources().getConfiguration().orientation);
            ImImagePrivateFragment.privateImageAdapter.setItemClickCallback(new OnClickHideListener<ArrayList<Object>, Integer, Boolean, View>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMImagePrivateFragment.sortListenerHideImageFolderData.1
                @Override
                public void onClickMoreListener(ArrayList<Object> arrayList2, Integer num, View view, Boolean bool) {
                }

                @Override
                public void onClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ImImagePrivateFragment.sendPrivateImageList = new ArrayList<>();
                    ImImagePrivateFragment.sendPrivateImageList = arrayList2;
                    ImImagePrivateFragment.this.bpos = num.intValue();
                    if (!ImImagePrivateFragment.this.isSingleSelection) {
                        String str = ((DataFileModel) ImImagePrivateFragment.sendPrivateImageList.get(num.intValue())).path;
                        str.substring(str.lastIndexOf("."));
                        if (str.endsWith(".jpg") || str.endsWith(".JPG") || str.endsWith(".jpeg") || str.endsWith(".JPEG") || str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".gif") || str.endsWith(".GIF")) {
                            Common.IdentifyActivity = "ImagePrivateFragment";
                            Intent intent = new Intent(ImImagePrivateFragment.this.activity, PreviewActivity.class);
                            intent.putExtra(Common.gImagePath, ((DataFileModel) ImImagePrivateFragment.sendPrivateImageList.get(ImImagePrivateFragment.this.bpos)).path);
                            intent.putExtra(Common.gTotalimage, ImImagePrivateFragment.sendPrivateImageList.size());
                            intent.putExtra(Common.gCurrenrtPosition, ImImagePrivateFragment.this.bpos);
                            intent.putExtra(Common.gArrayType, HeaderConstants.PRIVATE);
                            intent.putExtra(Common.gActivityname, "ImagePrivateFragment");
                            intent.putExtra(Common.gMediaType, ((DataFileModel) ImImagePrivateFragment.sendPrivateImageList.get(ImImagePrivateFragment.this.bpos)).mediaType);
                            ImImagePrivateFragment.this.activity.startActivity(intent);
                        }
                    } else if (bool.booleanValue()) {
                        Toast.makeText(ImImagePrivateFragment.this.context, "isPrivateAlbum", 0).show();
                    } else {
                        ImImagePrivateFragment.this.SelectSingleImage(ImImagePrivateFragment.sendPrivateImageList, num.intValue());
                    }
                }

                @Override
                public void onLongClickListener(ArrayList<Object> arrayList2, Integer num, Boolean bool, View view) {
                    ImImagePrivateFragment.sendPrivateImageList = new ArrayList<>();
                    ImImagePrivateFragment.sendPrivateImageList = arrayList2;
                    if (bool.booleanValue()) {
                        return;
                    }
                    ImImagePrivateFragment.this.folderPath = ((DataFileModel) ImImagePrivateFragment.sendPrivateImageList.get(num.intValue())).folderPath;
                    ImImagePrivateFragment.this.VisibleView();
                    if (ImImagePrivateFragment.mActionMode != null) {
                        ImImagePrivateFragment.mActionMode = null;
                    }
                    if (ImImagePrivateFragment.mActionMode == null) {
                        ImImagePrivateFragment.mActionMode = ((AppCompatActivity) ImImagePrivateFragment.this.getActivity()).startSupportActionMode(ImImagePrivateFragment.this.mActionModeCallback);
                    }
                    ImImagePrivateFragment.this.SelectSingleImage(ImImagePrivateFragment.sendPrivateImageList, num.intValue());
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

    public void Delete11Execute() {
        new Delete11Execute(GetSelectedList()).execute(new Void[0]);
    }


    public class Delete11Execute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> arrayDataList;
        ArrayList arrayList = new ArrayList();
        char c = CharCompanionObject.MAX_VALUE;
        ProgressDialog progressDialog;

        public Delete11Execute(ArrayList<DataFileModel> arrayList) {
            this.arrayDataList = new ArrayList<>();
            this.arrayDataList = arrayList;
            this.progressDialog = new ProgressDialog(ImImagePrivateFragment.this.context);
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
            Iterator<DataFileModel> it = this.arrayDataList.iterator();
            while (it.hasNext()) {
                DataFileModel next = it.next();
                DocumentFile fromSingleUri = DocumentFile.fromSingleUri(ImImagePrivateFragment.this.requireActivity(), Uri.parse(next.path));
                if (!fromSingleUri.exists() || !fromSingleUri.delete()) {
                    this.c = (char) 0;
                } else {
                    this.arrayList.add(next);
                    if (this.c == 0) {
                        return null;
                    }
                    this.c = (char) 1;
                }
            }
            this.arrayDataList.clear();
            Iterator it2 = this.arrayList.iterator();
            while (it2.hasNext()) {
                ImImagePrivateFragment.privateImageList.remove(it2.next());
            }
            return null;
        }

        @Override 
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            this.progressDialog.dismiss();
            ImImagePrivateFragment.privateImageAdapter.notifyDataSetChanged();
            ImImagePrivateFragment.this.isSingleSelection = false;
            ImImagePrivateFragment.this.UnSelectAll();
            if (ImImagePrivateFragment.mActionMode != null) {
                ImImagePrivateFragment.mActionMode.finish();
            }
            char c = this.c;
            if (c == 0) {
                Toast.makeText(ImImagePrivateFragment.this.context, "Couldn't Delete Image", 0).show();
            } else if (c == 1) {
                Toast.makeText(ImImagePrivateFragment.this.context, "Files are deleted successfully.", 0).show();
            }
            AppUtilsClass.RefreshImageAlbum(ImImagePrivateFragment.this.getActivity());
            AppUtilsClass.RefreshMoment(ImImagePrivateFragment.this.getActivity());
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        RecyclerView recyclerView = (RecyclerView) this.dialog.findViewById(R.id.rcv_folderlist);
        recyclerView.setLayoutManager(linearLayoutManager);
        AccessFolder11 accessFolder11 = new AccessFolder11(getActivity(), arrayList, z);
        this.accessFolder11 = accessFolder11;
        recyclerView.setAdapter(accessFolder11);
        this.dialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImagePrivateFragment.this.UnSelectAll();
                ImImagePrivateFragment.this.mMenu.getItem(0).setIcon(ContextCompat.getDrawable(ImImagePrivateFragment.this.activity, R.drawable.action_select));
                if (ImImagePrivateFragment.mActionMode != null) {
                    ImImagePrivateFragment.mActionMode.finish();
                }
                ImImagePrivateFragment.this.dialog.dismiss();
            }
        });
        this.dialog.findViewById(R.id.txtAddNewFolder).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImagePrivateFragment.this.AddNewFolderDialog();
            }
        });
        this.dialog.show();
    }


    public class AccessFolder11 extends RecyclerView.Adapter<AccessFolder11.MyViewHolder> {
        boolean abool;
        Context context;
        ArrayList<FolderModel> folderList;
        ArrayList<DataFileModel> unlockArray = new ArrayList<>();

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
                    ImImagePrivateFragment.this.dialog.dismiss();
                    String folderPath = AccessFolder11.this.folderList.get(i).getFolderPath();
                    folderPath.substring(folderPath.lastIndexOf("/") + 1);
                    ImImagePrivateFragment.FName = folderPath;
                    AccessFolder11.this.unlockArray.clear();
                    AccessFolder11 accessFolder11 = AccessFolder11.this;
                    accessFolder11.unlockArray = ImImagePrivateFragment.this.GetSelectedList();
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
                    ImImagePrivateFragment gMImagePrivateFragment = ImImagePrivateFragment.this;
                    gMImagePrivateFragment.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    File file = new File(ImImagePrivateFragment.this.folder_path);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else {
                        Toast.makeText(ImImagePrivateFragment.this.context, "Already Exists", 0).show();
                    }
                    AppUtilsClass.getAccessFolder11();
                    if (ImImagePrivateFragment.this.accessFolder11 != null) {
                        ImImagePrivateFragment.this.accessFolder11.refreshData(AppUtilsClass.accessfolderListFor11);
                    }
                    dialog.dismiss();
                }
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

    public void UnLockImage(ArrayList<DataFileModel> arrayList) {
        Log.d("PrivateData", " -start ");
        if (arrayList.isEmpty()) {
            return;
        }
        char c = CharCompanionObject.MAX_VALUE;
        ArrayList arrayList2 = new ArrayList();
        try {
            Iterator<DataFileModel> it = arrayList.iterator();
            while (it.hasNext()) {
                DataFileModel next = it.next();
                if (!DocumentFile.fromSingleUri(requireActivity(), Uri.parse(next.path)).exists()) {
                    c = 0;
                } else if (DownloadForUnlock(getActivity(), next.path)) {
                    Log.d("PrivateData", " - next.path - " + next.path);
                    arrayList2.add(next);
                    if (c == 0) {
                        return;
                    }
                    c = 1;
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("PrivateData", " Exc 00 - " + e.getMessage());
        }
        TempDeleteImage(arrayList);
        try {
            arrayList.clear();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                ArrayList<Object> arrayList3 = privateImageList;
                ((DataFileModel) it2.next()).isSelected = false;
                arrayList3.contains(false);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        privateImageAdapter.notifyDataSetChanged();
        if (c == 0) {
            Toast.makeText(this.context, "Couldn't Unlock Image", 0).show();
        } else if (c == 1) {
            Toast.makeText(this.context, "Files are unlocked successfully.", 0).show();
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

    public void TempDeleteImage(ArrayList<DataFileModel> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<DataFileModel> it = arrayList.iterator();
        char c = CharCompanionObject.MAX_VALUE;
        while (it.hasNext()) {
            DataFileModel next = it.next();
            DocumentFile fromSingleUri = DocumentFile.fromSingleUri(requireActivity(), Uri.parse(next.path));
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
            privateImageList.remove(it2.next());
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                ImImagePrivateFragment.privateImageAdapter.notifyDataSetChanged();
            }
        });
    }

    public static String getRealPathFromURI_API19(Context context, Uri uri) {
        Uri contentUri;
        if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (Musician_UriToUrl.isGooglePhotosUri(uri)) {
                    return null;
                }
                return Musician_UriToUrl.getDataColumn(context, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            } else {
                return null;
            }
        } else if (Musician_UriToUrl.isExternalStorageDocument(uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            String[] split = documentId.split(" ");
            if (!"primary".equalsIgnoreCase(split[0])) {
                return "storage/" + documentId.replace(" ", "/");
            } else if (split.length > 1) {
                return Environment.getExternalStorageDirectory() + File.separator + split[1];
            } else {
                return Environment.getExternalStorageDirectory() + File.separator;
            }
        } else if (Musician_UriToUrl.isDownloadsDocument(uri)) {
            String filePath = getFilePath(context, uri);
            if (filePath != null) {
                return Environment.getExternalStorageDirectory().toString() + "/Download/" + filePath;
            }
            String documentId2 = DocumentsContract.getDocumentId(uri);
            if (documentId2.startsWith("raw:")) {
                documentId2 = documentId2.replaceFirst("raw:", "");
                if (new File(documentId2).exists()) {
                    return documentId2;
                }
            }
            return Musician_UriToUrl.getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId2).longValue()), null, null);
        } else if (Musician_UriToUrl.isMediaDocument(uri)) {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(" ");
            String str = split2[0];
            if ("image".equals(str)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if (MimeTypes.BASE_TYPE_VIDEO.equals(str)) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if (MimeTypes.BASE_TYPE_AUDIO.equals(str)) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            } else {
                contentUri = MediaStore.Files.getContentUri("external");
            }
            return Musician_UriToUrl.getDataColumn(context, contentUri, "_id=?", new String[]{split2[1]});
        } else if (!"content".equalsIgnoreCase(uri.getScheme()) || Musician_UriToUrl.isGooglePhotosUri(uri)) {
            return null;
        } else {
            return Musician_UriToUrl.getDataColumn(context, uri, null, null);
        }
    }

    public static String getFilePath(Context context, Uri uri) {
        Cursor query = null;
        try {
            query = context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        } catch (Throwable unused) {
        }
        if (query == null || !query.moveToFirst()) {
            if (query != null) {
                query.close();
            }
            return null;
        }
        String string = query.getString(query.getColumnIndexOrThrow("_display_name"));
        if (query != null) {
            query.close();
        }
        return string;
    }


    public class Unlock11Execute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> arrayDataList;
        ArrayList arrayList = new ArrayList();
        char c = CharCompanionObject.MAX_VALUE;
        ProgressDialog progressDialog;

        public Unlock11Execute(ArrayList<DataFileModel> arrayList) {
            this.arrayDataList = new ArrayList<>();
            this.arrayDataList = arrayList;
            this.progressDialog = new ProgressDialog(ImImagePrivateFragment.this.context);
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
                try {
                    Iterator<DataFileModel> it = this.arrayDataList.iterator();
                    while (it.hasNext()) {
                        DataFileModel next = it.next();
                        if (DocumentFile.fromSingleUri(ImImagePrivateFragment.this.requireActivity(), Uri.parse(next.path)).exists()) {
                            ImImagePrivateFragment gMImagePrivateFragment = ImImagePrivateFragment.this;
                            if (gMImagePrivateFragment.DownloadForUnlock(gMImagePrivateFragment.getActivity(), next.path)) {
                                Log.d("PrivateData", " - next.path - " + next.path);
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
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("PrivateData", " Exc 00 - " + e.getMessage());
                }
                ImImagePrivateFragment.this.TempDeleteImage(this.arrayDataList);
                try {
                    this.arrayDataList.clear();
                    Iterator it2 = this.arrayList.iterator();
                    while (it2.hasNext()) {
                        ArrayList<Object> arrayList = ImImagePrivateFragment.privateImageList;
                        ((DataFileModel) it2.next()).isSelected = false;
                        arrayList.contains(false);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            this.progressDialog.dismiss();
            ImImagePrivateFragment.this.isSingleSelection = false;
            ImImagePrivateFragment.this.UnSelectAll();
            if (ImImagePrivateFragment.mActionMode != null) {
                ImImagePrivateFragment.mActionMode.finish();
            }
            AppUtilsClass.RefreshImageAlbum(ImImagePrivateFragment.this.getActivity());
            AppUtilsClass.RefreshMoment(ImImagePrivateFragment.this.getActivity());
            MoMomentFragment.momentAdapter.notifyDataSetChanged();
            ImImagePrivateFragment.privateImageAdapter.notifyDataSetChanged();
            char c = this.c;
            if (c == 0) {
                Toast.makeText(ImImagePrivateFragment.this.context, "Couldn't Unlock Image", 0).show();
            } else if (c == 1) {
                Toast.makeText(ImImagePrivateFragment.this.context, "Files are unlocked successfully.", 0).show();
            }
        }
    }
}
