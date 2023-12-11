package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
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
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.ExternalStorageHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.WrapperGridlayoutManager;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_ImageSelectActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_ImagesActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.DeguPrivateActivity;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;
import java.util.ArrayList;


public class ImImageFragment extends BaseFragment {
    static String TAG = "ImageFragment";
    public static ImageFolderAdapter imageFolderAdapter;
    Activity activity;
    AlbumSortingListener albumSortingListener;
    int bpos;
    ArrayList<FolderModel> callBackAlbumsList;
    ContentObserver contentObserver;
    Context context;
    Dialog deletAlbumdg;
    Dialog detailAlbumdg;
    RecyclerView fragmnetimagegridView;
    WrapperGridlayoutManager gridLayoutManager;
    Handler handler;
    int i2;
    Uri imageToUploadUri;
    ProgressBar loader;
    ProgressDialog progressDelete;
    ProgressDialog progressLock;
    String progressTag;
    int pv;
    RelativeLayout rlPhotoLayout;
    Thread thread;
    TextView tv_ErrorDisplay;
    View view1;
    int countSelected = 0;
    String folder_path = "";
    boolean isSelectAll = false;
    boolean isSingleSelection = false;
    boolean isSelectedAll = false;

    @Override 
    public void onResume() {
        FolderOrientation(this.activity.getResources().getConfiguration().orientation);
        ImageFolderAdapter imageFolderAdapter2 = imageFolderAdapter;
        if (imageFolderAdapter2 != null) {
            imageFolderAdapter2.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image, viewGroup, false);
        this.activity = getActivity();
        this.preferenceClass = new PrefClass(getActivity());
        this.view1 = inflate.findViewById(R.id.layout_album_select);
        Degu_MainActivity.ivSelectAll.setImageDrawable(this.activity.getResources().getDrawable(R.drawable.action_select));
        this.rlPhotoLayout = (RelativeLayout) inflate.findViewById(R.id.hintMainPhotoLayout);
        this.tv_ErrorDisplay = (TextView) inflate.findViewById(R.id.text_view_error);
        this.loader = (ProgressBar) inflate.findViewById(R.id.loader);
        this.fragmnetimagegridView = (RecyclerView) inflate.findViewById(R.id.grid_view_album_select);
        this.tv_ErrorDisplay.setVisibility(4);
        HiddenView();
        SortingCallBack();
        Degu_MainActivity.ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageFragment.this.isSelectAll) {
                    ImImageFragment.this.UnSelectAll();
                    Degu_MainActivity.ivSelectAll.setImageDrawable(ImImageFragment.this.activity.getResources().getDrawable(R.drawable.action_select));
                    return;
                }
                ImImageFragment.this.SelectAll();
                Degu_MainActivity.ivSelectAll.setImageDrawable(ImImageFragment.this.activity.getResources().getDrawable(R.drawable.action_unselect));
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override 
            public void run() {
                ImImageFragment.this.CheckImageFound(ConstantArrayClass.imageAlbumsList);
            }
        }, 1000L);
        SetAdapter();
        Degu_MainActivity.rl_imageBtn.setVisibility(View.VISIBLE);
        Degu_MainActivity.rl_videoBtn.setVisibility(8);
        Degu_MainActivity.addMomentAlbum.setVisibility(8);
        Degu_MainActivity.addImageAlbum.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageFragment.this.AddAlbumDialog();
            }
        });
        Degu_MainActivity.sortingImageList.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                new SortingFolderDialog(ImImageFragment.this.activity, ConstantArrayClass.imageAlbumsList, ImImageFragment.this.albumSortingListener).ShowSortingDialogue();
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
                    ImImageFragment gMImageFragment = ImImageFragment.this;
                    gMImageFragment.folder_path = AppUtilsClass.rootMainDCIMDir + editText.getText().toString() + File.separator;
                    Intent intent = new Intent(ImImageFragment.this.activity, Degu_ImagesActivity.class);
                    intent.putExtra(Common.gAlbumPath, ImImageFragment.this.folder_path);
                    ImImageFragment.this.activity.startActivityForResult(intent, 100);
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
        final Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
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
                    new RenameAlbumExecute(arrayList, num, trim).execute(new Void[0]);
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


    public class RenameAlbumExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<FolderModel> arrayList;
        String newAlbumFolder;
        int pos;
        ProgressDialog progressDialog;

        public RenameAlbumExecute(ArrayList<FolderModel> arrayList, Integer num, String str) {
            this.arrayList = new ArrayList<>();
            this.progressDialog = new ProgressDialog(ImImageFragment.this.context);
            this.arrayList = arrayList;
            this.pos = num.intValue();
            this.newAlbumFolder = str;
        }

        @Override 
        public void onPreExecute() {
            ImImageFragment.this.activity.runOnUiThread(new Runnable() {
                @Override 
                public void run() {
                    RenameAlbumExecute.this.progressDialog.setMessage("Please wait a while...");
                    RenameAlbumExecute.this.progressDialog.setProgressStyle(0);
                    RenameAlbumExecute.this.progressDialog.setIndeterminate(false);
                    RenameAlbumExecute.this.progressDialog.setCancelable(false);
                    RenameAlbumExecute.this.progressDialog.show();
                }
            });
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
                        String substring = file2.getPath().substring(file2.getPath().lastIndexOf("/") + 1);
                        if (substring.endsWith(".jpg") || substring.endsWith(".JPG") || substring.endsWith(".jpeg") || substring.endsWith(".JPEG") || substring.endsWith(".png") || substring.endsWith(".PNG") || substring.endsWith(".gif") || substring.endsWith(".GIF")) {
                            file.mkdirs();
                            if (file2.renameTo(file3)) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                                contentValues.put("mime_type", "image/*");
                                contentValues.put(SMUtilsWP.mediaPath, file3.getPath());
                                ImImageFragment.this.activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                                ImImageFragment.this.activity.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data='" + file2.getPath() + "'", null);
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
            AppUtilsClass.RefreshImageAlbum(ImImageFragment.this.getActivity());
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshMoment(ImImageFragment.this.getActivity());
                }
            }, 500L);
            this.progressDialog.dismiss();
        }
    }

    public void DetailDialog(ArrayList<FolderModel> arrayList, Integer num) {
        File file;
        Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        this.detailAlbumdg = dialog;
        dialog.requestWindowFeature(1);
        int i = 0;
        this.detailAlbumdg.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.detailAlbumdg.setContentView(R.layout.dialog_detail_folder);
        this.detailAlbumdg.setTitle("Details");
        this.detailAlbumdg.setCancelable(false);
        this.detailAlbumdg.setCanceledOnTouchOutside(false);
        TextView textView = (TextView) this.detailAlbumdg.findViewById(R.id.txtAlbumName);
        TextView textView2 = (TextView) this.detailAlbumdg.findViewById(R.id.txtAlbumPath);
        TextView textView3 = (TextView) this.detailAlbumdg.findViewById(R.id.txtAlbumSize);
        TextView textView4 = (TextView) this.detailAlbumdg.findViewById(R.id.txtAlbumItem);
        TextView textView5 = (TextView) this.detailAlbumdg.findViewById(R.id.txtAlbumCreatedOn);
        RelativeLayout relativeLayout = (RelativeLayout) this.detailAlbumdg.findViewById(R.id.rl_no);
        File parentFile = new File(arrayList.get(num.intValue()).getCoverThumb()).getParentFile();
        String[] list = parentFile.list();
        long j = 0;
        if (list != null) {
            int length = list.length;
            while (i < length) {
                File file2 = new File(parentFile + File.separator + list[i]);
                if (file2.exists()) {
                    file = parentFile;
                    String substring = file2.getPath().substring(file2.getPath().lastIndexOf("/") + 1);
                    if (substring.endsWith(".jpg") || substring.endsWith(".JPG") || substring.endsWith(".jpeg") || substring.endsWith(".JPEG") || substring.endsWith(".png") || substring.endsWith(".PNG") || substring.endsWith(".gif") || substring.endsWith(".GIF")) {
                        j += file2.length();
                    }
                } else {
                    file = parentFile;
                }
                i++;
                parentFile = file;
            }
        }
        textView.setText(arrayList.get(num.intValue()).foldername + "");
        textView2.setText(new File(arrayList.get(num.intValue()).getCoverThumb()).getParent());
        textView3.setText(FileUtils.convertToHumanReadableSize(this.activity, j));
        textView4.setText(arrayList.get(num.intValue()).getPathlist().size() + " Files.");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageFragment.this.detailAlbumdg.dismiss();
            }
        });
        this.detailAlbumdg.show();
    }

    public void DeleteAlbumDialog(ArrayList<FolderModel> arrayList, Integer num) {
        Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        this.deletAlbumdg = dialog;
        dialog.requestWindowFeature(1);
        this.deletAlbumdg.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.deletAlbumdg.setContentView(R.layout.dg_delete_image_folder);
        this.deletAlbumdg.setTitle("Delete Image");
        this.deletAlbumdg.setCancelable(false);
        this.deletAlbumdg.setCanceledOnTouchOutside(false);
        this.deletAlbumdg.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (ImImageFragment.this.GetSelectedList().size() <= 0) {
                    Toast.makeText(ImImageFragment.this.getActivity(), "Select Image.", 0).show();
                } else if (ExternalStorageHelper.isExternalStorageReadableAndWritable()) {
                    ImImageFragment.this.progressTag = "FromDelete";
                    new DeleteTrashImageExecute(ImImageFragment.this.GetSelectedList()).execute(new Void[0]);
                } else {
                    Toast.makeText(ImImageFragment.this.getActivity(), "not Read Write", 0).show();
                }
                ImImageFragment.this.deletAlbumdg.dismiss();
            }
        });
        this.deletAlbumdg.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageFragment.this.deletAlbumdg.dismiss();
            }
        });
        this.deletAlbumdg.show();
    }


    public class DeleteTrashImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public DeleteTrashImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            ImImageFragment.this.progressDelete = new ProgressDialog(ImImageFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            ImImageFragment.this.progressDelete.setMessage("Please wait a while...");
            ImImageFragment.this.progressDelete.setProgressStyle(0);
            ImImageFragment.this.progressDelete.setIndeterminate(false);
            ImImageFragment.this.progressDelete.setCancelable(false);
            ImImageFragment.this.progressDelete.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = ImImageFragment.this.preferenceClass.getListString(Common.gOldPath);
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
                            ContentResolver contentResolver = ImImageFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                            StringBuilder sb = new StringBuilder();
                            sb.append("Remove TO MediaStore Count: ");
                            sb.append(i2);
                            Log.e(ImImageFragment.TAG, sb.toString());
                        }
                        ImImageFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            ImImageFragment.this.isSingleSelection = false;
            ImImageFragment.this.isSelectedAll = false;
            ImImageFragment.this.progressDelete.dismiss();
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshImageAlbum(ImImageFragment.this.getActivity());
                }
            }, 200L);
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshMoment(ImImageFragment.this.getActivity());
                }
            }, 500L);
            Toast.makeText(ImImageFragment.this.getActivity(), "files moved to trash successfully.", 0).show();
        }
    }

    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 111 && this.i2 == -1) {
            Uri uri = this.imageToUploadUri;
            if (uri != null) {
                AddImageToGallery(uri.getPath());
            } else {
                Toast.makeText(this.activity, "Error while capturing Image", 0).show();
            }
        }
    }

    public void selectSingleImage(ArrayList<FolderModel> arrayList, int i) {
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
        imageFolderAdapter.notifyDataSetChanged();
    }

    public ArrayList<DataFileModel> GetSelectedList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = ConstantArrayClass.folderAllImageList.size();
        for (int i = 0; i < size; i++) {
            if (((DataFileModel) ConstantArrayClass.folderAllImageList.get(i)).isSelected) {
                arrayList.add((DataFileModel) ConstantArrayClass.folderAllImageList.get(i));
            }
        }
        return arrayList;
    }

    public void SelectAll() {
        int size = ConstantArrayClass.imageAlbumsList.size();
        for (int i = 0; i < size; i++) {
            ConstantArrayClass.imageAlbumsList.get(i).isSelected = true;
        }
        this.countSelected = ConstantArrayClass.imageAlbumsList.size();
        this.isSingleSelection = true;
        this.isSelectAll = true;
        imageFolderAdapter.notifyDataSetChanged();
    }

    public void UnSelectAll() {
        int size = ConstantArrayClass.imageAlbumsList.size();
        for (int i = 0; i < size; i++) {
            ConstantArrayClass.imageAlbumsList.get(i).isSelected = false;
        }
        this.countSelected = 0;
        this.isSingleSelection = false;
        this.isSelectAll = false;
        imageFolderAdapter.notifyDataSetChanged();
        HiddenView();
    }

    public void SelectFolderImage() {
        int size = ConstantArrayClass.folderAllImageList.size();
        for (int i = 0; i < size; i++) {
            ((DataFileModel) ConstantArrayClass.folderAllImageList.get(i)).isSelected = true;
        }
        this.countSelected = ConstantArrayClass.folderAllImageList.size();
        this.isSelectedAll = true;
        this.isSingleSelection = true;
        imageFolderAdapter.notifyDataSetChanged();
    }


    public class GetFolderAllImageSelectedAsync extends AsyncTask<Void, Void, Void> {
        String BucketID;

        @Override 
        protected void onPreExecute() {
        }

        public GetFolderAllImageSelectedAsync(String str) {
            this.BucketID = str;
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            AppUtilsClass.GetFolderAllImageSelectedList(ImImageFragment.this.getActivity(), this.BucketID);
            return null;
        }

        @Override 
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            ImImageFragment.this.SelectFolderImage();
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
                ImImageFragment.this.startActivity(new Intent(ImImageFragment.this.getActivity(), DeguPrivateActivity.class));
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
        dialog.setContentView(R.layout.dialog_lock_image);
        dialog.setTitle("Lock Image");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageFragment.this.LockImages();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ImImageFragment.this.UnSelectAll();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void LockImages() {
        new ArrayList().clear();
        ArrayList<DataFileModel> GetSelectedList = GetSelectedList();
        if (GetSelectedList.size() <= 0) {
            Toast.makeText(getActivity(), "Select Image.", 0).show();
        } else if (ExternalStorageHelper.isExternalStorageReadableAndWritable()) {
            this.progressTag = "FromLock";
            new LockGalleryImageExecute(GetSelectedList).execute(new Void[0]);
        } else {
            Toast.makeText(getActivity(), "not Read Write", 0).show();
        }
    }


    public class LockGalleryImageExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public LockGalleryImageExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            ImImageFragment.this.progressLock = new ProgressDialog(ImImageFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            ImImageFragment.this.progressLock.setMessage("Please wait a while...");
            ImImageFragment.this.progressLock.setProgressStyle(0);
            ImImageFragment.this.progressLock.setIndeterminate(false);
            ImImageFragment.this.progressLock.setCancelable(false);
            ImImageFragment.this.progressLock.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            ArrayList<String> listString = ImImageFragment.this.preferenceClass.getListString(Common.gOldPath);
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
                            ContentResolver contentResolver = ImImageFragment.this.getActivity().getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file3.getPath() + "'", null);
                        }
                        ImImageFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            ImImageFragment.this.isSingleSelection = false;
            ImImageFragment.this.isSelectedAll = false;
            ImImageFragment.this.progressLock.dismiss();
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshImageAlbum(ImImageFragment.this.getActivity());
                    ImImageFragment.imageFolderAdapter.notifyDataSetChanged();
                }
            }, 200L);
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshMoment(ImImageFragment.this.getActivity());
                    MoMomentFragment.momentAdapter.notifyDataSetChanged();
                }
            }, 500L);
            Toast.makeText(ImImageFragment.this.getActivity(), "Files moved to vault successfully.", 0).show();
        }
    }

    private void SetAdapter() {
        new SortingFolderDialog(this.activity, ConstantArrayClass.imageAlbumsList, this.albumSortingListener).Sorting(LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingDataDialog.SortingName), LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingDataDialog.SortingType));
        ImageFolderAdapter imageFolderAdapter2 = new ImageFolderAdapter(this.activity, getContext(), ConstantArrayClass.imageAlbumsList);
        imageFolderAdapter = imageFolderAdapter2;
        this.fragmnetimagegridView.setAdapter(imageFolderAdapter2);
        this.loader.setVisibility(8);
        FolderOrientation(this.activity.getResources().getConfiguration().orientation);
        imageFolderAdapter.setItemClickCallback(new OnClickListener<ArrayList<FolderModel>, Integer, View>() {
            @Override
            public void onLongClickListener(ArrayList<FolderModel> arrayList, Integer num) {
            }

            @Override
            public void onClickListener(final ArrayList<FolderModel> arrayList, final Integer num) {
                        ImImageFragment.this.callBackAlbumsList = new ArrayList<>();
                        ImImageFragment.this.callBackAlbumsList = arrayList;
                        ImImageFragment.this.bpos = num.intValue();
                        Common.IdentifyActivity = "ImageFragment";
                        Common.strplay = "Photofrag";
                        Intent intent = new Intent(ImImageFragment.this.getActivity(), Degu_ImageSelectActivity.class);
                        intent.putExtra(Common.gBucketID, num.intValue());
                        intent.putExtra("position", num.intValue());
                        intent.putExtra(AppUtilsClass.INTENT_EXTRA_ALBUM, ImImageFragment.this.callBackAlbumsList.get(num.intValue()).foldername);
                        ImImageFragment.this.activity.startActivityForResult(intent, 2000);
            }

            @Override
            public void onClickMoreListener(ArrayList<FolderModel> arrayList, Integer num, View view) {
                ImImageFragment gMImageFragment = ImImageFragment.this;
                gMImageFragment.MoreOptionDialog(arrayList, num, view, gMImageFragment.getActivity());
                new GetFolderAllImageSelectedAsync(arrayList.get(num.intValue()).getBucket_id()).execute(new Void[0]);
            }
        });
    }


    public class ImageFolderAdapter extends RecyclerView.Adapter<ImageFolderAdapter.MyViewHolder> {
        Activity activity;
        ArrayList<FolderModel> arrayList;
        Context context;
        LayoutInflater layoutInflater;
        ArrayList<FolderModel> mStringFilterList;
        private OnClickListener<ArrayList<FolderModel>, Integer, View> onClickListener;
        int size;

        @Override
        public long getItemId(int i) {
            return i;
        }

        public ImageFolderAdapter(Activity activity, Context context, ArrayList<FolderModel> arrayList) {
            this.arrayList = arrayList;
            this.mStringFilterList = arrayList;
            this.context = context;
            this.activity = activity;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override 
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_album_adapter, viewGroup, false));
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
                new File(this.arrayList.get(i).getCoverThumb());
                myViewHolder.textView.setText(this.arrayList.get(i).getFoldername());
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
            try {
                if (this.arrayList.get(i).getFoldername().equals("Take Photo")) {
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.centerCrop();
                    Glide.with(this.context).load((Object) this.arrayList.get(i).getPathlist().get(0)).apply((BaseRequestOptions<?>) requestOptions).into(myViewHolder.imageView);
                } else {
                    Uri fromFile = Uri.fromFile(new File(this.arrayList.get(i).getPathlist().get(0).path));
                    RequestOptions requestOptions2 = new RequestOptions();
                    requestOptions2.centerCrop();
                    Glide.with(this.context).load(fromFile).apply((BaseRequestOptions<?>) requestOptions2).into(myViewHolder.imageView);
                }
            } catch (Exception e3) {
                System.out.println(e3.getMessage());
            }
            myViewHolder.ivMoreDetail.setOnClickListener(new View.OnClickListener() {
                @Override 
                public void onClick(View view) {
                    if (ImageFolderAdapter.this.onClickListener != null) {
                        ImageFolderAdapter.this.onClickListener.onClickMoreListener(ImageFolderAdapter.this.arrayList, Integer.valueOf(i), view);
                    }
                }
            });
            myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override 
                public void onClick(View view) {
                    AdInterGD.getInstance().showInter(getActivity(), new AdInterGD.MyCallback() {
                        @Override
                        public void callbackCall() {
                            if (ImageFolderAdapter.this.onClickListener != null) {
                                ImageFolderAdapter.this.onClickListener.onClickListener(ImageFolderAdapter.this.arrayList, Integer.valueOf(i));
                            }
                        }
                    });
                }
            });
            myViewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override 
                public boolean onLongClick(View view) {
                    if (ImageFolderAdapter.this.onClickListener != null) {
                        ImageFolderAdapter.this.onClickListener.onLongClickListener(ImageFolderAdapter.this.arrayList, Integer.valueOf(i));
                        return false;
                    }
                    return false;
                }
            });
            if (ImImagePrivateFragment.privateimage == 1) {
                ImImagePrivateFragment.privateimage = 0;
                for (int i2 = 0; i2 < this.arrayList.size(); i2++) {
                    if (this.arrayList.get(i2).getFoldername().equals("RestoreLockImage")) {
                        myViewHolder.txttag.setText("New");
                        Toast.makeText(this.activity, "New", 0).show();
                    } else {
                        myViewHolder.txttag.setText("");
                    }
                }
            }
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
            ImImageFragment.this.CheckImageFound(ConstantArrayClass.imageAlbumsList);
            this.arrayList = arrayList;
            Activity activity = this.activity;
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override 
                    public void run() {
                        ImageFolderAdapter.this.notifyDataSetChanged();
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
            TextView textView;
            TextView tvCount;
            TextView txttag;

            public MyViewHolder(View view) {
                super(view);
                this.imageView = (ImageView) view.findViewById(R.id.image_view_album_image);
                this.textView = (TextView) view.findViewById(R.id.text_view_album_name);
                this.tvCount = (TextView) view.findViewById(R.id.text_view_album_size);
                this.ivMoreDetail = (ImageView) view.findViewById(R.id.btnMoreDetails);
                this.txttag = (TextView) view.findViewById(R.id.txttag);
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
                ImImageFragment.this.RenameDialog(arrayList, num);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.rl_info).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                ImImageFragment.this.DetailDialog(arrayList, num);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.rl_delete).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                ImImageFragment.this.DeleteAlbumDialog(arrayList, num);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.rl_lock).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                new ArrayList().clear();
                ArrayList<DataFileModel> GetSelectedList = ImImageFragment.this.GetSelectedList();
                if (ImImageFragment.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                    ImImageFragment.this.SecurityDialog(GetSelectedList);
                } else if (GetSelectedList.size() >= 1) {
                    ImImageFragment.this.LockDialog();
                } else {
                    Toast.makeText(ImImageFragment.this.activity, "Select at least one image.", 0).show();
                }
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.findViewById(R.id.txtCancel).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                ImImageFragment.this.UnSelectAll();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    public void FolderOrientation(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.activity.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
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
        ImageFolderAdapter imageFolderAdapter2 = imageFolderAdapter;
        if (imageFolderAdapter2 != null) {
            imageFolderAdapter2.setLayoutParams(displayMetrics.widthPixels / (i == 1 ? this.i2 : Degu_MainActivity.albumDivider));
        }
        if (i != 1) {
            this.i2 = Degu_MainActivity.albumDivider;
        }
        WrapperGridlayoutManager wrapperGridlayoutManager = new WrapperGridlayoutManager(this.activity, this.i2);
        this.gridLayoutManager = wrapperGridlayoutManager;
        this.fragmnetimagegridView.setLayoutManager(wrapperGridlayoutManager);
    }

    public void AddImageToGallery(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("mime_type", "image/jpg");
        contentValues.put(SMUtilsWP.mediaPath, str);
        this.activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public void CheckImageFound(ArrayList<FolderModel> arrayList) {
        if (arrayList.size() >= 1) {
            this.rlPhotoLayout.setVisibility(8);
            this.fragmnetimagegridView.setVisibility(View.VISIBLE);
            return;
        }
        this.fragmnetimagegridView.setVisibility(View.GONE);
        this.rlPhotoLayout.setVisibility(View.VISIBLE);
    }

    private void SortingCallBack() {
        this.albumSortingListener = new onCallbackReceive();
    }

    @Override 
    public void onStop() {
        super.onStop();
        if (this.contentObserver != null) {
            stopThread();
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
        ConstantArrayClass.imageAlbumsList = null;
        ImageFolderAdapter imageFolderAdapter2 = imageFolderAdapter;
        if (imageFolderAdapter2 != null) {
            imageFolderAdapter2.releaseResources();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FolderOrientation(configuration.orientation);
    }

    private void stopThread() {
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
        Degu_MainActivity.ivSelectAll.setVisibility(8);
        Degu_MainActivity.ivAddAlbum.setVisibility(View.GONE);
        Degu_MainActivity.ivSort.setVisibility(View.GONE);
    }

    @Override 
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
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


    public class onCallbackReceive implements AlbumSortingListener {
        onCallbackReceive() {
        }

        @Override
        public void Sorting(ArrayList<FolderModel> arrayList) {
            ImImageFragment.this.CheckImageFound(arrayList);
            ImImageFragment.imageFolderAdapter = new ImageFolderAdapter(ImImageFragment.this.activity, ImImageFragment.this.getContext(), arrayList);
            ImImageFragment.this.fragmnetimagegridView.setAdapter(ImImageFragment.imageFolderAdapter);
            ImImageFragment.this.loader.setVisibility(View.GONE);
            ImImageFragment.this.fragmnetimagegridView.setVisibility(0);
            ImImageFragment gMImageFragment = ImImageFragment.this;
            gMImageFragment.FolderOrientation(gMImageFragment.activity.getResources().getConfiguration().orientation);
            ImImageFragment.imageFolderAdapter.setItemClickCallback(new OnClickListener<ArrayList<FolderModel>, Integer, View>() {
                @Override
                public void onLongClickListener(ArrayList<FolderModel> arrayList2, Integer num) {
                }

                @Override
                public void onClickListener(final ArrayList<FolderModel> arrayList2, final Integer num) {
                            ImImageFragment.this.callBackAlbumsList = new ArrayList<>();
                            Common.IdentifyActivity = "ImageFragment";
                            ImImageFragment.this.callBackAlbumsList = arrayList2;
                            ImImageFragment.this.bpos = num.intValue();
                            Intent intent = new Intent(ImImageFragment.this.getActivity(), Degu_ImageSelectActivity.class);
                            intent.putExtra(Common.gBucketID, num.intValue());
                            intent.putExtra("position", num.intValue());
                            intent.putExtra(AppUtilsClass.INTENT_EXTRA_ALBUM, ImImageFragment.this.callBackAlbumsList.get(num.intValue()).foldername);
                            ImImageFragment.this.activity.startActivityForResult(intent, 2000);

                }

                @Override
                public void onClickMoreListener(ArrayList<FolderModel> arrayList2, Integer num, View view) {
                    ImImageFragment.this.MoreOptionDialog(arrayList2, num, view, ImImageFragment.this.getActivity());
                    new GetFolderAllImageSelectedAsync(arrayList2.get(num.intValue()).getBucket_id()).execute(new Void[0]);
                }
            });
        }
    }
}
