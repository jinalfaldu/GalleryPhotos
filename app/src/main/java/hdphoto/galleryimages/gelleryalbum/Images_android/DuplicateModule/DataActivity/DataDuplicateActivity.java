package hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataAdapter.DataRecyclerViewType;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataAdapter.DataSectionRecyclerViewAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataDeleteTask.DataDeleteFilesAsyncTask;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataModel.Duplicate;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.BseActivity;
import java.io.File;
import java.security.AccessController;
import java.util.ArrayList;


public class DataDuplicateActivity extends BseActivity {
    DataSectionRecyclerViewAdapter adapter;
    RecyclerView dataRCV;
    Dialog deleteImageDialog;
    ArrayList<File> listFile = new ArrayList<>();
    DataDeleteFilesAsyncTask mDeleteFilesAsyncTask;
    DataRecyclerViewType recyclerViewType;
    SharedPreferences sharedPreferences;
    String stringExtra;
    TextView txtToolBarTitle;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_duplicate);
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        this.stringExtra = getIntent().getStringExtra("title_tool_bar");
        TextView textView = (TextView) findViewById(R.id.tootbatTitle);
        this.txtToolBarTitle = textView;
        textView.setText(this.stringExtra);
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.recyclerViewType = DataRecyclerViewType.LINEAR_VERTICAL;
        SetRecyclerView();
        PopulateRecyclerView();
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                AdInterGD.getInstance().showInter(DataDuplicateActivity.this, new AdInterGD.MyCallback() {
                    @Override
                    public void callbackCall() {
                        DataDuplicateActivity.this.DeleteSelectedItem();
                        if (DataDuplicateActivity.this.listFile.size() == 0) {
                            Toast.makeText(DataDuplicateActivity.this, "Cannot delete, all items are unchecked!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        DataDuplicateActivity dMDuplicateActivity = DataDuplicateActivity.this;
                        dMDuplicateActivity.createDeleteDialog(dMDuplicateActivity);
                    }
                });
            }
        });
    }

    public void DeleteFiles() {
        DataDeleteFilesAsyncTask dMDeleteFilesAsyncTask = new DataDeleteFilesAsyncTask(this, this.listFile, new DataDeleteFilesAsyncTask.OnRestoreListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.DuplicateModule.DMActivity.DMDuplicateActivity.3
            @Override
            public void onComplete() {
                DataDuplicateActivity.this.finish();
            }
        });
        this.mDeleteFilesAsyncTask = dMDeleteFilesAsyncTask;
        dMDeleteFilesAsyncTask.execute(new String[0]);
    }

    public boolean SDCardCheck() {
        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(this, null);
        return (externalFilesDirs.length <= 1 || externalFilesDirs[0] == null || externalFilesDirs[1] == null) ? false : true;
    }

    public void createDeleteDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.ThemeWithCorners1);
        this.deleteImageDialog = dialog;
        dialog.requestWindowFeature(1);
        this.deleteImageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.deleteImageDialog.setContentView(R.layout.dg_collage_delete_img);
        this.deleteImageDialog.setTitle("Confirm Delete");
        this.deleteImageDialog.setCancelable(false);
        this.deleteImageDialog.setCanceledOnTouchOutside(false);
        ((TextView) this.deleteImageDialog.findViewById(R.id.txtTitle)).setText("Confirm Delete");
        ((TextView) this.deleteImageDialog.findViewById(R.id.textDesc)).setText("Are you sure , You want to delete the files?");
        ((RelativeLayout) this.deleteImageDialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (!DataDuplicateActivity.this.sharedPreferences.getString("sdCardUri", "").equals("")) {
                    DataDuplicateActivity.this.DeleteFiles();
                } else if (DataDuplicateActivity.this.SDCardCheck()) {
                    DataDuplicateActivity.this.ExternalStorageFilesDialog();
                } else {
                    DataDuplicateActivity.this.DeleteFiles();
                }
                DataDuplicateActivity.this.deleteImageDialog.dismiss();
            }
        });
        ((RelativeLayout) this.deleteImageDialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                DataDuplicateActivity.this.deleteImageDialog.dismiss();
            }
        });
        this.deleteImageDialog.show();
    }

    public void ExternalStorageFilesDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.requestWindowFeature(1);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.dg_sdcard);
        ((Button) progressDialog.findViewById(R.id.ok_sd)).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null) {
                    progressDialog2.dismiss();
                }
                DataDuplicateActivity.this.FileSearch();
            }
        });
    }

    public void FileSearch() {
        startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 100);
    }

    public void DeleteSelectedItem() {
        if (DataScanActivity.mListData != null) {
            for (int i = 0; i < DataScanActivity.mListData.size(); i++) {
                ArrayList<Duplicate> listDuplicate = DataScanActivity.mListData.get(i).getListDuplicate();
                for (int size = listDuplicate.size() - 1; size >= 0; size--) {
                    if (listDuplicate.get(size).isChecked()) {
                        this.listFile.add(listDuplicate.get(size).getFile());
                        listDuplicate.remove(listDuplicate.get(size));
                    }
                }
            }
        }
    }

    private void SetRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        this.dataRCV = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.dataRCV.setLayoutManager(new LinearLayoutManager(this));
    }

    private void PopulateRecyclerView() {
        DataSectionRecyclerViewAdapter dMSectionRecyclerViewAdapter = new DataSectionRecyclerViewAdapter(this, this.recyclerViewType, DataScanActivity.mListData);
        this.adapter = dMSectionRecyclerViewAdapter;
        this.dataRCV.setAdapter(dMSectionRecyclerViewAdapter);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1) {
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            boolean z = false;
            if (intent != null) {
                Uri data = intent.getData();
                if (Build.VERSION.SDK_INT >= 19 && AccessController.getContext() != null) {
                    getContentResolver().takePersistableUriPermission(data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                if (checkIfSDCardRoot(data)) {
                    edit.putString("sdCardUri", data.toString());
                    edit.putBoolean("storagePermission", true);
                    z = true;
                } else {
                    Toast.makeText(this, "Please Select Right SD Card.", Toast.LENGTH_SHORT).show();
                    edit.putBoolean("storagePermission", false);
                    edit.putString("sdCardUri", "");
                }
            } else {
                Toast.makeText(this, "Please Select Right SD Card.", Toast.LENGTH_SHORT).show();
                edit.putString("sdCardUri", "");
            }
            if (edit.commit()) {
                edit.apply();
                if (z) {
                    DeleteFiles();
                }
            }
        }
        if (i == 200 && i2 == -1) {
            finish();
        }
    }

    private static boolean checkIfSDCardRoot(Uri uri) {
        return isExternalStorageDocument(uri) && isRootUri(uri) && !isInternalStorage(uri);
    }

    private static boolean isRootUri(Uri uri) {
        return DocumentsContract.getTreeDocumentId(uri).endsWith(" ");
    }

    public static boolean isInternalStorage(Uri uri) {
        return isExternalStorageDocument(uri) && DocumentsContract.getTreeDocumentId(uri).contains("primary");
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean isNetworkConnected(Activity activity) {
        return ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    @Override 
    public void onBackPressed() {

        OnBackPressed.onBackPressed(this,true);


    }
}
