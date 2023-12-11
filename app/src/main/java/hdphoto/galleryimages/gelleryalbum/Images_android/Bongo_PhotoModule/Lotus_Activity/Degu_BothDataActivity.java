package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.AllDataAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.io.File;
import java.util.ArrayList;


public class Degu_BothDataActivity extends Degu_PermissionHelperActivity {
    String album;
    AllDataAdapter allDataAdapter;
    ArrayList<DataFileModel> bothDataArrayList;
    ContentObserver galleryObserver;
    GridView imagesGridView;
    Handler imagesHandler;
    Thread imagesThread;
    ImageView imgBack;
    ProgressBar progressBar;
    ProgressDialog progressFolder;
    TextView txtErrorDisplaygallery;
    TextView txtMoveToAlbum;
    TextView txtToolbarTitle;
    int countSelected = 0;
    int galleryalbumType = 0;
    boolean isSingleSelection = false;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_both_data);
        setView(findViewById(R.id.layout_image_select));
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        this.album = intent.getStringExtra(Common.gAlbumPath);
        this.galleryalbumType = intent.getIntExtra(Common.gAlbumType, 0);
        ImageView imageView = (ImageView) findViewById(R.id.btnBack);
        this.imgBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_BothDataActivity.this.onBackPressed();
            }
        });
        TextView textView = (TextView) findViewById(R.id.toolbarTitle);
        this.txtToolbarTitle = textView;
        textView.setText("Select Files");
        TextView textView2 = (TextView) findViewById(R.id.btnMoveToAlbum);
        this.txtMoveToAlbum = textView2;
        textView2.setVisibility(0);
        this.txtMoveToAlbum.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_BothDataActivity.this.MoveToAlbum();
            }
        });
        TextView textView3 = (TextView) findViewById(R.id.text_view_error);
        this.txtErrorDisplaygallery = textView3;
        textView3.setVisibility(4);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar_image_select);
        GridView gridView = (GridView) findViewById(R.id.grid_view_image_select);
        this.imagesGridView = gridView;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Degu_BothDataActivity.this.SelectSingleImage(i);
            }
        });
        this.imagesGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                Degu_BothDataActivity.this.SelectSingleImage(i);
                return true;
            }
        });
        new GetBothDataExecute().execute(new Void[0]);
    }

    private ArrayList<DataFileModel> GetSelectedImageList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = this.bothDataArrayList.size();
        for (int i = 0; i < size; i++) {
            if (this.bothDataArrayList.get(i).isSelected) {
                arrayList.add(this.bothDataArrayList.get(i));
            }
        }
        return arrayList;
    }

    public void MoveToAlbum() {
        if (GetSelectedImageList().size() > 0) {
            new FilesMoveToFolderExecute(GetSelectedImageList(), this.album).execute(new Void[0]);
        } else {
            Toast.makeText(this, "Select Files.", 0).show();
        }
    }

    public void SelectSingleImage(int i) {
        this.isSingleSelection = true;
        this.bothDataArrayList.get(i).isSelected = !this.bothDataArrayList.get(i).isSelected;
        if (this.bothDataArrayList.get(i).isSelected) {
            this.countSelected++;
        } else {
            this.countSelected--;
        }
        if (this.countSelected <= 0) {
            this.isSingleSelection = false;
        }
        this.allDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        orientationBasedUI(configuration.orientation);
    }

    public void orientationBasedUI(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (this.allDataAdapter != null) {
            int i2 = displayMetrics.widthPixels;
            this.allDataAdapter.setLayoutParams(i == 1 ? i2 / 4 : i2 / 8);
        }
        this.imagesGridView.setNumColumns(i != 1 ? 8 : 4);
    }

    @Override
    public void hideViews() {
        this.progressBar.setVisibility(4);
        this.imagesGridView.setVisibility(4);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.bothDataArrayList = null;
        AllDataAdapter gMAllDataAdapter = this.allDataAdapter;
        if (gMAllDataAdapter != null) {
            gMAllDataAdapter.releaseResources();
        }
        this.imagesGridView.setOnItemClickListener(null);
    }


    public class GetBothDataExecute extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressPermanentDelete;

        public GetBothDataExecute() {
            this.progressPermanentDelete = new ProgressDialog(Degu_BothDataActivity.this);
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            this.progressPermanentDelete.setMessage("Please wait a while...");
            this.progressPermanentDelete.setProgressStyle(0);
            this.progressPermanentDelete.setIndeterminate(false);
            this.progressPermanentDelete.setCancelable(false);
            this.progressPermanentDelete.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            Degu_BothDataActivity.this.ScanImageFiles();
            return null;
        }

        @Override
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            this.progressPermanentDelete.dismiss();
            Degu_BothDataActivity gMBothDataActivity = Degu_BothDataActivity.this;
            gMBothDataActivity.allDataAdapter = new AllDataAdapter(gMBothDataActivity, gMBothDataActivity.getApplicationContext(), Degu_BothDataActivity.this.bothDataArrayList);
            Degu_BothDataActivity.this.imagesGridView.setAdapter((ListAdapter) Degu_BothDataActivity.this.allDataAdapter);
            Degu_BothDataActivity.this.progressBar.setVisibility(4);
            Degu_BothDataActivity.this.imagesGridView.setVisibility(0);
            Degu_BothDataActivity gMBothDataActivity2 = Degu_BothDataActivity.this;
            gMBothDataActivity2.orientationBasedUI(gMBothDataActivity2.getResources().getConfiguration().orientation);
        }
    }

    public void ScanImageFiles() {
        try {
            ArrayList arrayList = new ArrayList();
            Cursor query = getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id", SMUtilsWP.mediaPath, "media_type", "datetaken", "bucket_id", "bucket_display_name"}, "media_type=1 OR media_type=3", null, "datetaken DESC");
            try {
                if (query.moveToFirst()) {
                    while (!query.isAfterLast()) {
                        if (new File(query.getString(query.getColumnIndex(SMUtilsWP.mediaPath))).exists()) {
                            query.getLong(query.getColumnIndex("datetaken"));
                            DataFileModel gMDataFileModel = new DataFileModel();
                            gMDataFileModel.id1 = query.getString(query.getColumnIndex("_id"));
                            gMDataFileModel.bucket_id = query.getString(query.getColumnIndex("bucket_id"));
                            gMDataFileModel.path = query.getString(query.getColumnIndexOrThrow(SMUtilsWP.mediaPath));
                            gMDataFileModel.folderName = query.getString(query.getColumnIndex("bucket_display_name"));
                            gMDataFileModel.takenDate = query.getString(query.getColumnIndexOrThrow("datetaken"));
                            gMDataFileModel.mediaType = query.getString(query.getColumnIndexOrThrow("media_type"));
                            arrayList.add(gMDataFileModel);
                        }
                        query.moveToNext();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            query.close();
            if (this.bothDataArrayList == null) {
                this.bothDataArrayList = new ArrayList<>();
            }
            ArrayList<DataFileModel> arrayList2 = this.bothDataArrayList;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
            this.bothDataArrayList.addAll(arrayList);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }


    public class FilesMoveToFolderExecute extends AsyncTask<Void, Void, Void> {
        String albumName;
        ArrayList<DataFileModel> pathList;

        public FilesMoveToFolderExecute(ArrayList<DataFileModel> arrayList, String str) {
            this.pathList = new ArrayList<>();
            Degu_BothDataActivity.this.progressFolder = new ProgressDialog(Degu_BothDataActivity.this);
            this.pathList = arrayList;
            this.albumName = str;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            Degu_BothDataActivity.this.progressFolder.setMessage("Please wait a while...");
            Degu_BothDataActivity.this.progressFolder.setProgressStyle(0);
            Degu_BothDataActivity.this.progressFolder.setIndeterminate(false);
            Degu_BothDataActivity.this.progressFolder.setCancelable(false);
            Degu_BothDataActivity.this.progressFolder.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            Uri uri;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> listString = Degu_BothDataActivity.this.preferenceClass.getListString(Common.gOldPath);
            File file = new File(this.albumName);
            if (!file.exists()) {
                file.mkdirs();
            }
            for (int i = 0; i < this.pathList.size(); i++) {
                File file2 = new File(this.pathList.get(i).path);
                File file3 = new File(this.albumName + file2.getName());
                String parent = file3.getParent();
                String mediaType = this.pathList.get(i).getMediaType();
                try {
                    FileUtils.CopyMoveFile(file2, file3);
                    AppUtilsClass.insertUri(Degu_BothDataActivity.this, file3);
                    long j = this.pathList.get(i).id;
                    String str = this.pathList.get(i).name;
                    String str2 = this.pathList.get(i).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file4 = new File(this.pathList.get(i2).path);
                            file4.delete();
                            if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            } else {
                                uri = mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D) ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : null;
                            }
                            ContentResolver contentResolver = Degu_BothDataActivity.this.getContentResolver();
                            contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                        }
                        Degu_BothDataActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override 
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            if (Degu_BothDataActivity.this.progressFolder.isShowing() || Degu_BothDataActivity.this.progressFolder != null) {
                Degu_BothDataActivity.this.progressFolder.dismiss();
            }
            Degu_BothDataActivity.this.allDataAdapter.notifyDataSetChanged();
            Degu_BothDataActivity.this.setResult(-1, new Intent());
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    AppUtilsClass.RefreshPhotoVideo(Degu_BothDataActivity.this);
                }
            }, 500L);
            Toast.makeText(Degu_BothDataActivity.this, "Folder Created Successfully.", 0).show();
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    Degu_BothDataActivity.this.finish();
                }
            }, 1000L);
        }
    }
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
