package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.WrapperGridlayoutManager;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.DeguPrivateActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.VideoSelectActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.VideosActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.AlbumSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.LoginPreferenceUtilsFolder;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingDataDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingFolderDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.ArrayList;


public class ViVideoFragment extends BaseFragment {
    static String TAG = "VideoFragment";
    public static VideoFolderAdapter videoFolderAdapter;
    Activity activity;
    AlbumSortingListener albumSortingListener;
    int bpos;
    ArrayList<FolderModel> callBackAlbumsList;
    ContentObserver contentObserver;
    Dialog detailAlbumDialog;
    WrapperGridlayoutManager gridLayoutManager;
    RecyclerView gridView;
    Handler handler;
    int i2;
    Uri imageToUploadUri;
    ProgressBar loader;
    ProgressDialog progressDelete, progressLock, progressPermanentDelete;
    String progressTag;
    int pv;
    RelativeLayout rl_NoDataLayout;
    Thread thread;
    TextView txtErrorDisplay;
    View view1;
    int countSelected = 0;
    String folder_path = "";
    boolean isSelectAll = false;
    boolean isSingleSelection = false;
    boolean isSelectedAll = false;

    @Override
    public void onResume() {
        FolderOrientation(getResources().getConfiguration().orientation);
        VideoFolderAdapter videoFolderAdapter2 = videoFolderAdapter;
        if (videoFolderAdapter2 != null) {
            videoFolderAdapter2.notifyDataSetChanged();
        }
        super.onResume();
        Common.strplay = "VideoFragment";
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FolderOrientation(configuration.orientation);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_video, viewGroup, false);
        this.preferenceClass = new PrefClass(getActivity());
        this.view1 = inflate.findViewById(R.id.layout_album_select);
        this.txtErrorDisplay = (TextView) inflate.findViewById(R.id.text_view_error);
        this.activity = getActivity();
        this.txtErrorDisplay.setVisibility(4);
        this.loader = (ProgressBar) inflate.findViewById(R.id.loader);
        this.gridView = (RecyclerView) inflate.findViewById(R.id.grid_view_album_select);
        this.rl_NoDataLayout = (RelativeLayout) inflate.findViewById(R.id.hintMainVideoListLayout);
        HiddenView();
        SortingCallBack();
        Degu_MainActivity.ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ViVideoFragment.this.isSelectAll) {
                    ViVideoFragment.this.UnSelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoFragment.this.activity.getResources().getDrawable(R.drawable.action_select));
                    return;
                }
                ViVideoFragment.this.SelectAll();
                Degu_MainActivity.ivSelectAll.setImageDrawable(ViVideoFragment.this.activity.getResources().getDrawable(R.drawable.action_unselect));
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViVideoFragment.this.CheckVideoFound(ConstantArrayClass.videoAlbumsList);
            }
        }, 1000L);
        SetVideoAdapterData();
        Degu_MainActivity.rl_imageBtn.setVisibility(8);
        Degu_MainActivity.rl_videoBtn.setVisibility(0);
        Degu_MainActivity.addMomentAlbum.setVisibility(8);
        Degu_MainActivity.addVideoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViVideoFragment.this.AddAlbumDialog();
            }
        });
        Degu_MainActivity.sortingVideoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SortingFolderDialog(ViVideoFragment.this.activity, ConstantArrayClass.videoAlbumsList, ViVideoFragment.this.albumSortingListener).ShowSortingDialogue();
            }
        });
        return inflate;
    }

    public void AddAlbumDialog() {
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
                    ViVideoFragment gMVideoFragment = ViVideoFragment.this;
                    gMVideoFragment.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    Intent intent = new Intent(ViVideoFragment.this.getActivity(), VideosActivity.class);
                    intent.putExtra(Common.gAlbumPath, ViVideoFragment.this.folder_path);
                    ViVideoFragment.this.activity.startActivityForResult(intent, 100);
                    dialog.dismiss();
                    return;
                }
                editText.setError("Please Enter Folder Name");
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void RenameDialog(final ArrayList<FolderModel> arrayList, final Integer num) {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_rename_folder);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.txtTitle)).setText("Rename Folder");
        final EditText editText = (EditText) dialog.findViewById(R.id.edit);
        editText.setText(arrayList.get(num.intValue()).foldername);
        final String obj = editText.getText().toString();
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                if (!trim.equals(obj)) {
                    new RenameVideoAlbumExecute(arrayList, num, trim).execute(new Void[0]);
                    dialog.dismiss();
                    return;
                }
                editText.setError("Please Enter Folder Name");
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class RenameVideoAlbumExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<FolderModel> arrayList;
        String newAlbumFolder;
        int pos;
        ProgressDialog progressDialog;

        public RenameVideoAlbumExecute(ArrayList<FolderModel> arrayList, Integer num, String str) {
            this.arrayList = new ArrayList<>();
            this.progressDialog = new ProgressDialog(ViVideoFragment.this.getActivity());
            this.arrayList = arrayList;
            this.pos = num.intValue();
            this.newAlbumFolder = str;
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
            File parentFile = new File(this.arrayList.get(this.pos).getCoverThumb()).getParentFile();
            File file = new File(parentFile.getParent() + File.separator + this.newAlbumFolder);
            String[] list = parentFile.list();
            if (list != null) {
                for (String str : list) {
                    File file2 = new File(parentFile + File.separator + str);
                    File file3 = new File(file + File.separator + str);
                    if (file2.exists()) {
                        String fileExtension = FileUtils.getFileExtension(file2.getPath());
                        if (fileExtension.endsWith("mp4") || fileExtension.endsWith("MP4") || fileExtension.endsWith("3gp") || fileExtension.endsWith("3GP") || fileExtension.endsWith("mkv") || fileExtension.endsWith("MKV")) {
                            file.mkdirs();
                            if (file2.renameTo(file3)) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                                contentValues.put("mime_type", MimeTypes.BASE_TYPE_VIDEO);
                                contentValues.put(SMUtilsWP.mediaPath, file3.getPath());
                                ViVideoFragment.this.activity.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                                ViVideoFragment.this.activity.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data='" + file2.getPath() + "'", null);
                            }
                        }
                    }
                }
            }
            if (parentFile.list() == null) {
                parentFile.delete();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            AppUtilsClass.RefreshVideoAlbum(ViVideoFragment.this.getActivity());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtilsClass.RefreshMoment(ViVideoFragment.this.getActivity());
                }
            }, 500L);
            this.progressDialog.dismiss();
        }
    }

    public void DetailDialog(ArrayList<FolderModel> arrayList, Integer num) {
        Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        this.detailAlbumDialog = dialog;
        dialog.requestWindowFeature(1);
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
        File parentFile = new File(arrayList.get(num.intValue()).getCoverThumb()).getParentFile();
        String[] list = parentFile.list();
        long j = 0;
        if (list != null) {
            int length = list.length;
            for (int i = 0; i < length; i++) {
                File file = new File(parentFile + File.separator + list[i]);
                if (file.exists()) {
                    String fileExtension = FileUtils.getFileExtension(file.getPath());
                    if (fileExtension.endsWith("mp4") || fileExtension.endsWith("MP4") || fileExtension.endsWith("3gp") || fileExtension.endsWith("3GP") || fileExtension.endsWith("mkv") || fileExtension.endsWith("MKV")) {
                        j += file.length();
                    }
                }
            }
        }
        textView.setText(arrayList.get(num.intValue()).foldername + "");
        textView2.setText(new File(arrayList.get(num.intValue()).getCoverThumb()).getParent());
        textView3.setText(FileUtils.convertToHumanReadableSize(getActivity(), j));
        textView4.setText(arrayList.get(num.intValue()).getPathlist().size() + " Files.");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViVideoFragment.this.detailAlbumDialog.dismiss();
            }
        });
        this.detailAlbumDialog.show();
    }

    public void DeleteVideoAlbumDialog(ArrayList<FolderModel> arrayList, Integer num) {
        final Dialog dialog = new Dialog(getActivity(), R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_gm_delete_video_folder);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ViVideoFragment.this.GetSelectedList().size() > 0) {
                    ViVideoFragment.this.progressTag = "FromDelete";
                    new DeleteTrashVideoExecute(ViVideoFragment.this.GetSelectedList()).execute(new Void[0]);
                } else {
                    Toast.makeText(ViVideoFragment.this.getActivity(), "Select Video.", 0).show();
                }
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public class DeleteTrashVideoExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteTrashVideoExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            ViVideoFragment.this.progressDelete = new ProgressDialog(ViVideoFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            ViVideoFragment.this.progressDelete.setMessage("Please wait a while...");
            ViVideoFragment.this.progressDelete.setProgressStyle(0);
            ViVideoFragment.this.progressDelete.setIndeterminate(false);
            ViVideoFragment.this.progressDelete.setCancelable(false);
            ViVideoFragment.this.progressDelete.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = ViVideoFragment.this.preferenceClass.getListString(Common.gOldPath);
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
                            ContentResolver contentResolver = ViVideoFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                            StringBuilder sb = new StringBuilder();
                            sb.append("Remove TO MediaStore Count: ");
                            sb.append(i2);
                            Log.e(ViVideoFragment.TAG, sb.toString());
                        }
                        ViVideoFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            ViVideoFragment.this.isSingleSelection = false;
            ViVideoFragment.this.isSelectedAll = false;
            ViVideoFragment.this.progressDelete.dismiss();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtilsClass.RefreshVideoAlbum(ViVideoFragment.this.getActivity());
                }
            }, 300L);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtilsClass.RefreshMoment(ViVideoFragment.this.getActivity());
                }
            }, 500L);
            Toast.makeText(ViVideoFragment.this.getActivity(), "Files moved to trash successfully.", 0).show();
        }
    }

    public void DeleteRecursive(File file) {
        if (file.isDirectory()) {
            file.delete();
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 111 && i2 == -1) {
            Uri uri = this.imageToUploadUri;
            if (uri != null) {
                AddImageToGallery(uri.getPath());
            } else {
                Toast.makeText(getActivity(), "Error while capturing Image", 0).show();
            }
        }
    }

    public void SelectSingleImage(ArrayList<FolderModel> arrayList, int i) {
        this.isSingleSelection = true;
        arrayList.get(i).isSelected = !arrayList.get(i).isSelected;
        if (arrayList.get(i).isSelected) {
            this.countSelected++;
        } else {
            this.countSelected--;
        }
        if (this.countSelected <= 0) {
            this.isSingleSelection = false;
            this.isSelectAll = false;
            HiddenView();
        }
        videoFolderAdapter.notifyDataSetChanged();
    }

    public ArrayList<DataFileModel> GetSelectedList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = ConstantArrayClass.folderAllVideoList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) ConstantArrayClass.folderAllVideoList.get(i)).isSelected) {
                arrayList.add((DataFileModel) ConstantArrayClass.folderAllVideoList.get(i));
            }
        }
        return arrayList;
    }

    public void SelectAll() {
        int size = ConstantArrayClass.videoAlbumsList.size();
        for (int i = 0; i < size; i++) {
            ConstantArrayClass.videoAlbumsList.get(i).isSelected = true;
        }
        this.countSelected = ConstantArrayClass.videoAlbumsList.size();
        this.isSingleSelection = true;
        this.isSelectAll = true;
        VisibleView();
        videoFolderAdapter.notifyDataSetChanged();
    }

    public void UnSelectAll() {
        int size = ConstantArrayClass.videoAlbumsList.size();
        for (int i = 0; i < size; i++) {
            ConstantArrayClass.videoAlbumsList.get(i).isSelected = false;
        }
        this.countSelected = 0;
        this.isSingleSelection = false;
        this.isSelectAll = false;
        videoFolderAdapter.notifyDataSetChanged();
        HiddenView();
    }

    public void SelectAllVideo() {
        int size = ConstantArrayClass.folderAllVideoList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) ConstantArrayClass.folderAllVideoList.get(i)).isSelected = true;
        }
        this.countSelected = ConstantArrayClass.folderAllVideoList.size();
        this.isSelectedAll = true;
        this.isSingleSelection = true;
        videoFolderAdapter.notifyDataSetChanged();
    }


    public class GetFolderAllVideoSelectedAsync extends AsyncTask<Void, Void, Void> {
        String bbid;

        @Override
        protected void onPreExecute() {
        }

        public GetFolderAllVideoSelectedAsync(String str) {
            this.bbid = str;
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            AppUtilsClass.GetFolderAllVideoSelectedList(ViVideoFragment.this.getActivity(), this.bbid);
            return null;
        }

        @Override
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            ViVideoFragment.this.SelectAllVideo();
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
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FolderPath.lock_screen = 1;
                ViVideoFragment.this.startActivity(new Intent(ViVideoFragment.this.getActivity(), DeguPrivateActivity.class));
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
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
        dialog.setContentView(R.layout.dialog_lock_video);
        dialog.setTitle("Lock Video");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViVideoFragment.this.LockVideos();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViVideoFragment.this.UnSelectAll();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void LockVideos() {
        new ArrayList().clear();
        ArrayList<DataFileModel> GetSelectedList = GetSelectedList();
        if (GetSelectedList.size() > 0) {
            this.progressTag = "FromLock";
            new LockVideoExecute(GetSelectedList).execute(new Void[0]);
            return;
        }
        Toast.makeText(getActivity(), "Select Video.", 0).show();
    }


    public class LockVideoExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public LockVideoExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            ViVideoFragment.this.progressLock = new ProgressDialog(ViVideoFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            ViVideoFragment.this.progressLock.setMessage("Please wait a while...");
            ViVideoFragment.this.progressLock.setProgressStyle(0);
            ViVideoFragment.this.progressLock.setIndeterminate(false);
            ViVideoFragment.this.progressLock.setCancelable(false);
            ViVideoFragment.this.progressLock.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = ViVideoFragment.this.preferenceClass.getListString(Common.gOldPath);
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
                            ContentResolver contentResolver = ViVideoFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                        }
                        ViVideoFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            ViVideoFragment.this.isSingleSelection = false;
            ViVideoFragment.this.isSelectedAll = false;
            ViVideoFragment.this.progressLock.dismiss();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtilsClass.RefreshVideoAlbum(ViVideoFragment.this.getActivity());
                }
            }, 300L);
            ViVideoFragment.videoFolderAdapter.notifyDataSetChanged();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtilsClass.RefreshMoment(ViVideoFragment.this.getActivity());
                }
            }, 500L);
            MoMomentFragment.momentAdapter.notifyDataSetChanged();
            Toast.makeText(ViVideoFragment.this.getActivity(), "Files moved to vault successfully.", 0).show();
        }
    }

    private void SetVideoAdapterData() {
        new SortingFolderDialog(getActivity(), ConstantArrayClass.videoAlbumsList, this.albumSortingListener).Sorting(LoginPreferenceUtilsFolder.GetStringData(getActivity(), SortingDataDialog.SortingName), LoginPreferenceUtilsFolder.GetStringData(getActivity(), SortingDataDialog.SortingType));
        VideoFolderAdapter videoFolderAdapter2 = new VideoFolderAdapter(getActivity(), getContext(), ConstantArrayClass.videoAlbumsList);
        videoFolderAdapter = videoFolderAdapter2;
        this.gridView.setAdapter(videoFolderAdapter2);
        this.loader.setVisibility(8);
        this.gridView.setVisibility(0);
        FolderOrientation(getResources().getConfiguration().orientation);
        videoFolderAdapter.setItemClickCallback(new OnClickListener<ArrayList<FolderModel>, Integer, View>() {
            @Override
            public void onLongClickListener(ArrayList<FolderModel> arrayList, Integer num) {
            }

            @Override
            public void onClickListener(final ArrayList<FolderModel> arrayList, final Integer num) {
                ViVideoFragment.this.callBackAlbumsList = new ArrayList<>();
                ViVideoFragment.this.callBackAlbumsList = arrayList;
                ViVideoFragment.this.bpos = num.intValue();
                Common.strplay = "VideoFragment";
                Common.IdentifyActivity = ViVideoFragment.TAG;
                Intent intent = new Intent(ViVideoFragment.this.getActivity(), VideoSelectActivity.class);
                intent.putExtra(Common.gBucketID, num.intValue());
                intent.putExtra("position", num.intValue());
                intent.putExtra(AppUtilsClass.INTENT_EXTRA_ALBUM, ViVideoFragment.this.callBackAlbumsList.get(num.intValue()).foldername);
                ViVideoFragment.this.activity.startActivityForResult(intent, 2000);

            }

            @Override
            public void onClickMoreListener(ArrayList<FolderModel> arrayList, Integer num, View view) {
                ViVideoFragment gMVideoFragment = ViVideoFragment.this;
                gMVideoFragment.MoreOptionDialog(arrayList, num, view, gMVideoFragment.getActivity());
                new GetFolderAllVideoSelectedAsync(arrayList.get(num.intValue()).getBucket_id()).execute(new Void[0]);
            }
        });
    }


    public class VideoFolderAdapter extends RecyclerView.Adapter<VideoFolderAdapter.MyViewHolder> {
        Activity activity;
        ArrayList<FolderModel> arrayList;
        Context context;
        LayoutInflater layoutInflater;
        ArrayList<FolderModel> mStringFilterList;
        OnClickListener<ArrayList<FolderModel>, Integer, View> onClickListener;
        int size;

        @Override
        public long getItemId(int i) {
            return i;
        }

        public VideoFolderAdapter(Activity activity, Context context, ArrayList<FolderModel> arrayList) {
            this.arrayList = arrayList;
            this.mStringFilterList = arrayList;
            this.context = context;
            this.activity = activity;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(this.layoutInflater.inflate(R.layout.layout_album_adapter, (ViewGroup) null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
            myViewHolder.imageView.getLayoutParams().width = this.size;
            myViewHolder.imageView.getLayoutParams().height = this.size;
            try {
                TextView textView = myViewHolder.tvCount;
                textView.setText("(" + this.arrayList.get(i).pathlist.size() + ")");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                myViewHolder.textView.setText(this.arrayList.get(i).getFoldername());
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
            try {
                Uri fromFile = Uri.fromFile(new File(this.arrayList.get(i).getPathlist().get(0).path));
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.override(200, 200);
                requestOptions.centerCrop();
                Glide.with(this.context).load(fromFile).apply((BaseRequestOptions<?>) requestOptions).into(myViewHolder.imageView);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (Common.strplay.equals("VideoFragment")) {
                myViewHolder.rl_play.setVisibility(0);
            }
            myViewHolder.ivMoreDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (VideoFolderAdapter.this.onClickListener != null) {
                        VideoFolderAdapter.this.onClickListener.onClickMoreListener(VideoFolderAdapter.this.arrayList, Integer.valueOf(i), view);
                    }
                }
            });
            myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdInterGD.getInstance().showInter(activity, new AdInterGD.MyCallback() {
                        @Override
                        public void callbackCall() {
                            if (VideoFolderAdapter.this.onClickListener != null) {
                                VideoFolderAdapter.this.onClickListener.onClickListener(VideoFolderAdapter.this.arrayList, Integer.valueOf(i));
                            }
                        }
                    });

                }
            });
            myViewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (VideoFolderAdapter.this.onClickListener != null) {
                        VideoFolderAdapter.this.onClickListener.onLongClickListener(VideoFolderAdapter.this.arrayList, Integer.valueOf(i));
                        return false;
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            if (this.arrayList.size() != 0) {
                return this.arrayList.size();
            }
            return 0;
        }

        public FolderModel getItem(int i) {
            return this.arrayList.get(i);
        }

        public void setLayoutParams(int i) {
            this.size = i;
        }

        public void releaseResources() {
            this.arrayList = null;
            this.context = null;
            this.activity = null;
        }

        public void refreshData(ArrayList<FolderModel> arrayList) {
            Log.d("VDVDVD", " - Refresh Data--" + arrayList.size());
            ViVideoFragment.this.CheckVideoFound(ConstantArrayClass.videoAlbumsList);
            this.arrayList = arrayList;
            Activity activity = this.activity;
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        VideoFolderAdapter.this.notifyDataSetChanged();
                    }
                });
            }
        }

        public void setItemClickCallback(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            ImageView ivMoreDetail;
            RelativeLayout rl_play;
            TextView textView;
            TextView tvCount;

            public MyViewHolder(View view) {
                super(view);
                this.imageView = (ImageView) view.findViewById(R.id.image_view_album_image);
                this.textView = (TextView) view.findViewById(R.id.text_view_album_name);
                this.tvCount = (TextView) view.findViewById(R.id.text_view_album_size);
                this.ivMoreDetail = (ImageView) view.findViewById(R.id.btnMoreDetails);
                this.rl_play = (RelativeLayout) view.findViewById(R.id.rel_play);
            }
        }
    }

    public void MoreOptionDialog(final ArrayList<FolderModel> arrayList, final Integer num, View view, FragmentActivity fragmentActivity) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(fragmentActivity, R.style.BottomSheetDialogStyle);
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        bottomSheetDialog.setContentView(R.layout.dg_folder_more_option);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.findViewById(R.id.rl_rename).setVisibility(0);
        bottomSheetDialog.findViewById(R.id.rl_rename).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ViVideoFragment.this.RenameDialog(arrayList, num);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.rl_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ViVideoFragment.this.DetailDialog(arrayList, num);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.rl_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ViVideoFragment.this.DeleteVideoAlbumDialog(arrayList, num);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.rl_lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                new ArrayList().clear();
                ArrayList<DataFileModel> GetSelectedList = ViVideoFragment.this.GetSelectedList();
                if (ViVideoFragment.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                    ViVideoFragment.this.SecurityDialog(GetSelectedList);
                } else if (GetSelectedList.size() >= 1) {
                    ViVideoFragment.this.LockDialog();
                } else {
                    Toast.makeText(ViVideoFragment.this.activity, "Select at least one video.", 0).show();
                }
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ViVideoFragment.this.UnSelectAll();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    public void FolderOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
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
        VideoFolderAdapter videoFolderAdapter2 = videoFolderAdapter;
        if (videoFolderAdapter2 != null) {
            videoFolderAdapter2.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.albumDivider));
        }
        if (i != 1) {
            this.i2 = Degu_MainActivity.albumDivider;
        }
        WrapperGridlayoutManager wrapperGridlayoutManager = new WrapperGridlayoutManager(this.activity, this.i2);
        this.gridLayoutManager = wrapperGridlayoutManager;
        this.gridView.setLayoutManager(wrapperGridlayoutManager);
    }

    public void AddImageToGallery(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("mime_type", "video/*");
        contentValues.put(SMUtilsWP.mediaPath, str);
        this.activity.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public void CheckVideoFound(ArrayList<FolderModel> arrayList) {
        if (arrayList.size() >= 1) {
            this.rl_NoDataLayout.setVisibility(8);
            this.gridView.setVisibility(0);
            return;
        }
        this.gridView.setVisibility(8);
        this.rl_NoDataLayout.setVisibility(0);
    }

    private void SortingCallBack() {
        this.albumSortingListener = new videoAlbumSorting();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.contentObserver != null) {
            StopThread();
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
        ConstantArrayClass.videoAlbumsList = null;
        VideoFolderAdapter videoFolderAdapter2 = videoFolderAdapter;
        if (videoFolderAdapter2 != null) {
            videoFolderAdapter2.releaseResources();
        }
    }

    private void StopThread() {
        Thread thread = this.thread;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        this.thread.interrupt();
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void HiddenView() {
        Degu_MainActivity.ivSort.setVisibility(8);
        Degu_MainActivity.ivSelectAll.setVisibility(8);
        Degu_MainActivity.ivAddAlbum.setVisibility(8);
    }

    private void VisibleView() {
        Degu_MainActivity.ivSelectAll.setVisibility(8);
        Degu_MainActivity.ivAddAlbum.setVisibility(8);
        Degu_MainActivity.ivSort.setVisibility(8);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public class videoAlbumSorting implements AlbumSortingListener {
        videoAlbumSorting() {
        }

        @Override
        public void Sorting(ArrayList<FolderModel> arrayList) {
            ViVideoFragment.this.CheckVideoFound(arrayList);
            ViVideoFragment.videoFolderAdapter = new VideoFolderAdapter(ViVideoFragment.this.getActivity(), ViVideoFragment.this.getContext(), arrayList);
            ViVideoFragment.this.gridView.setAdapter(ViVideoFragment.videoFolderAdapter);
            ViVideoFragment.this.loader.setVisibility(8);
            ViVideoFragment.this.gridView.setVisibility(0);
            ViVideoFragment gMVideoFragment = ViVideoFragment.this;
            gMVideoFragment.FolderOrientation(gMVideoFragment.activity.getResources().getConfiguration().orientation);
            ViVideoFragment.videoFolderAdapter.setItemClickCallback(new OnClickListener<ArrayList<FolderModel>, Integer, View>() {
                @Override
                public void onLongClickListener(ArrayList<FolderModel> arrayList2, Integer num) {
                }

                @Override
                public void onClickListener(ArrayList<FolderModel> arrayList2, Integer num) {
                    ViVideoFragment.this.callBackAlbumsList = new ArrayList<>();
                    Common.IdentifyActivity = ViVideoFragment.TAG;
                    ViVideoFragment.this.callBackAlbumsList = arrayList2;
                    ViVideoFragment.this.bpos = num.intValue();
                    if (ViVideoFragment.this.isSingleSelection) {
                        ViVideoFragment.this.SelectSingleImage(ViVideoFragment.this.callBackAlbumsList, num.intValue());
                        return;
                    }
                    Intent intent = new Intent(ViVideoFragment.this.getActivity(), VideoSelectActivity.class);
                    intent.putExtra(Common.gBucketID, num.intValue());
                    intent.putExtra("position", num.intValue());
                    intent.putExtra(AppUtilsClass.INTENT_EXTRA_ALBUM, ViVideoFragment.this.callBackAlbumsList.get(num.intValue()).foldername);
                    ViVideoFragment.this.activity.startActivityForResult(intent, 2000);

                }

                @Override
                public void onClickMoreListener(ArrayList<FolderModel> arrayList2, Integer num, View view) {
                    ViVideoFragment.this.MoreOptionDialog(arrayList2, num, view, ViVideoFragment.this.getActivity());
                    new GetFolderAllVideoSelectedAsync(arrayList2.get(num.intValue()).getBucket_id()).execute(new Void[0]);
                }
            });
        }
    }


    public class DeleteExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            ViVideoFragment.this.progressPermanentDelete = new ProgressDialog(ViVideoFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            ViVideoFragment.this.progressPermanentDelete.setMessage("Please wait a while...");
            ViVideoFragment.this.progressPermanentDelete.setProgressStyle(0);
            ViVideoFragment.this.progressPermanentDelete.setIndeterminate(false);
            ViVideoFragment.this.progressPermanentDelete.setCancelable(false);
            ViVideoFragment.this.progressPermanentDelete.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            new ArrayList();
            new ArrayList();
            ArrayList<String> listString = ViVideoFragment.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_DELETE_IMAGE).mkdirs();
            try {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < this.pathList.size()) {
                    if (this.pathList.size() - 1 == i) {
                        while (i >= 0) {
                            if (Build.VERSION.SDK_INT >= 30) {
                                arrayList.add(ContentUris.withAppendedId(MediaStore.Video.Media.getContentUri("external"), AppUtilsClass.getFilePathToMediaID(this.pathList.get(i).path, ViVideoFragment.this.getActivity())));
                            }
                            this.pathList.remove(i);
                            i--;
                        }
                        ViVideoFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                    i++;
                }
                if (Build.VERSION.SDK_INT < 30) {
                    return null;
                }
                AppUtilsClass.requestIntent(arrayList, ViVideoFragment.this.getActivity());
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
                ViVideoFragment.this.isSingleSelection = false;
                ViVideoFragment.this.isSelectedAll = false;
                ViVideoFragment.this.progressPermanentDelete.dismiss();
                AppUtilsClass.RefreshVideoAlbum(ViVideoFragment.this.getActivity());
                AppUtilsClass.RefreshMoment(ViVideoFragment.this.getActivity());
                Toast.makeText(ViVideoFragment.this.getActivity(), "Files are deleted successfully.", 0).show();
            }
        }
    }
}
