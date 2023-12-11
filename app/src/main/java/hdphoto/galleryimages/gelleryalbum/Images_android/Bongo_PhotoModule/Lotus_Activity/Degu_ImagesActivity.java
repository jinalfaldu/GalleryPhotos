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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.AllDataAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import java.io.File;
import java.util.ArrayList;


public class Degu_ImagesActivity extends Degu_PermissionHelperActivity {
    String album;
    AllDataAdapter allDataAdapter;
    ContentObserver galleryObserver;
    GridView imagesGridView;
    Handler imagesHandler;
    ArrayList<DataFileModel> imagesList;
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
        setContentView(R.layout.activity_gm_images);
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
                Degu_ImagesActivity.this.onBackPressed();
            }
        });
        TextView textView = (TextView) findViewById(R.id.toolbarTitle);
        this.txtToolbarTitle = textView;
        textView.setText("Select Image");
        TextView textView2 = (TextView) findViewById(R.id.btnMoveToAlbum);
        this.txtMoveToAlbum = textView2;
        textView2.setVisibility(0);
        this.txtMoveToAlbum.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Degu_ImagesActivity.this.MoveToAlbum();
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
                Degu_ImagesActivity.this.SelectSingleImage(i);
            }
        });
        this.imagesGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { 
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                Degu_ImagesActivity.this.SelectSingleImage(i);
                return true;
            }
        });
        new GetAllImageExecute().execute(new Void[0]);
    }

    private ArrayList<DataFileModel> GetSelectedImageList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = this.imagesList.size();
        for (int i = 0; i < size; i++) {
            if (this.imagesList.get(i).isSelected) {
                arrayList.add(this.imagesList.get(i));
            }
        }
        return arrayList;
    }

    public void MoveToAlbum() {
        new ArrayList().clear();
        ArrayList<DataFileModel> GetSelectedImageList = GetSelectedImageList();
        if (GetSelectedImageList.size() > 0) {
            new ImageMoveToFolderExecute(GetSelectedImageList, this.album).execute(new Void[0]);
        } else {
            Toast.makeText(this, "Select Image.", 0).show();
        }
    }

    public void SelectSingleImage(int i) {
        this.isSingleSelection = true;
        this.imagesList.get(i).isSelected = !this.imagesList.get(i).isSelected;
        if (this.imagesList.get(i).isSelected) {
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
        this.imagesList = null;
        AllDataAdapter gMAllDataAdapter = this.allDataAdapter;
        if (gMAllDataAdapter != null) {
            gMAllDataAdapter.releaseResources();
        }
        this.imagesGridView.setOnItemClickListener(null);
    }

    
    public class GetAllImageExecute extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressPermanentDelete;

        public GetAllImageExecute() {
            this.progressPermanentDelete = new ProgressDialog(Degu_ImagesActivity.this);
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
            Degu_ImagesActivity.this.ScanImageFiles();
            return null;
        }

        @Override 
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            this.progressPermanentDelete.dismiss();
            Degu_ImagesActivity gMImagesActivity = Degu_ImagesActivity.this;
            gMImagesActivity.allDataAdapter = new AllDataAdapter(gMImagesActivity, gMImagesActivity.getApplicationContext(), Degu_ImagesActivity.this.imagesList);
            Degu_ImagesActivity.this.imagesGridView.setAdapter((ListAdapter) Degu_ImagesActivity.this.allDataAdapter);
            Degu_ImagesActivity.this.progressBar.setVisibility(4);
            Degu_ImagesActivity.this.imagesGridView.setVisibility(0);
            Degu_ImagesActivity gMImagesActivity2 = Degu_ImagesActivity.this;
            gMImagesActivity2.orientationBasedUI(gMImagesActivity2.getResources().getConfiguration().orientation);
        }
    }

    public void ScanImageFiles() {
        try {
            ArrayList arrayList = new ArrayList();
            Cursor query = getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id", SMUtilsWP.mediaPath, "media_type", "datetaken", "bucket_id", "bucket_display_name"}, "media_type=1", null, "datetaken DESC");
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
            if (this.imagesList == null) {
                this.imagesList = new ArrayList<>();
            }
            ArrayList<DataFileModel> arrayList2 = this.imagesList;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
            this.imagesList.addAll(arrayList);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    
    public class ImageMoveToFolderExecute extends AsyncTask<Void, Void, Void> {
        String albumName;
        ArrayList<DataFileModel> pathList;

        public ImageMoveToFolderExecute(ArrayList<DataFileModel> arrayList, String str) {
            this.pathList = new ArrayList<>();
            Degu_ImagesActivity.this.progressFolder = new ProgressDialog(Degu_ImagesActivity.this);
            this.pathList = arrayList;
            this.albumName = str;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            Degu_ImagesActivity.this.progressFolder.setMessage("Please wait a while...");
            Degu_ImagesActivity.this.progressFolder.setProgressStyle(0);
            Degu_ImagesActivity.this.progressFolder.setIndeterminate(false);
            Degu_ImagesActivity.this.progressFolder.setCancelable(false);
            Degu_ImagesActivity.this.progressFolder.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> listString = Degu_ImagesActivity.this.preferenceClass.getListString(Common.gOldPath);
            File file = new File(this.albumName);
            if (!file.exists()) {
                file.mkdirs();
            }
            for (int i = 0; i < this.pathList.size(); i++) {
                File file2 = new File(this.pathList.get(i).path);
                File file3 = new File(this.albumName + file2.getName());
                String parent = file3.getParent();
                try {
                    FileUtils.CopyMoveFile(file2, file3);
                    AppUtilsClass.insertUri(Degu_ImagesActivity.this, file3);
                    long j = this.pathList.get(i).id;
                    String str = this.pathList.get(i).name;
                    String str2 = this.pathList.get(i).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file4 = new File(this.pathList.get(i2).path);
                            file4.delete();
                            ContentResolver contentResolver = Degu_ImagesActivity.this.getContentResolver();
                            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                        }
                        Degu_ImagesActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            if (Degu_ImagesActivity.this.progressFolder.isShowing() || Degu_ImagesActivity.this.progressFolder != null) {
                Degu_ImagesActivity.this.progressFolder.dismiss();
            }
            Degu_ImagesActivity.this.allDataAdapter.notifyDataSetChanged();
            Degu_ImagesActivity.this.setResult(-1, new Intent());
            AppUtilsClass.RefreshImageAlbum(Degu_ImagesActivity.this);
            new Handler().postDelayed(new Runnable() { 
                @Override 
                public void run() {
                    AppUtilsClass.RefreshMoment(Degu_ImagesActivity.this);
                }
            }, 500L);
            Toast.makeText(Degu_ImagesActivity.this, "Folder Created Successfully.", 0).show();
            new Handler().postDelayed(new Runnable() { 
                @Override 
                public void run() {
                    Degu_ImagesActivity.this.finish();
                }
            }, 1000L);
        }
    }

  
    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
