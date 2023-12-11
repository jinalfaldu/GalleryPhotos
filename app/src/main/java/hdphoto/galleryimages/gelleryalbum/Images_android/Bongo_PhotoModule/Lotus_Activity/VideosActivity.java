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

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter.AllDataAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import java.io.File;
import java.util.ArrayList;


public class VideosActivity extends Degu_PermissionHelperActivity {
    String album;
    AllDataAdapter allGalleryVideoAdapter;
    GridView gridViewVideo;
    ImageView imgBack;
    ProgressBar progressBar;
    ProgressDialog progressFolder;
    TextView txtErrorDisplay;
    TextView txtMoveToAlbum;
    TextView txtToolbarTitle;
    Handler videosHandler;
    ArrayList<DataFileModel> videosList;
    ContentObserver videosObserver;
    Thread videosThread;
    int albumType = 0;
    int countSelected = 0;
    boolean isSingleSelection = false;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_videos);
        setView(findViewById(R.id.layout_image_select));
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        this.album = intent.getStringExtra(Common.gAlbumPath);
        this.albumType = intent.getIntExtra(Common.gAlbumType, 0);
        ImageView imageView = (ImageView) findViewById(R.id.btnBack);
        this.imgBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                VideosActivity.this.onBackPressed();
            }
        });
        TextView textView = (TextView) findViewById(R.id.toolbarTitle);
        this.txtToolbarTitle = textView;
        textView.setText("Select Video");
        TextView textView2 = (TextView) findViewById(R.id.btnMoveToAlbum);
        this.txtMoveToAlbum = textView2;
        textView2.setVisibility(0);
        this.txtMoveToAlbum.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                VideosActivity.this.MoveToAlbum();
            }
        });
        TextView textView3 = (TextView) findViewById(R.id.text_view_error);
        this.txtErrorDisplay = textView3;
        textView3.setVisibility(4);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar_image_select);
        GridView gridView = (GridView) findViewById(R.id.grid_view_image_select);
        this.gridViewVideo = gridView;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                VideosActivity.this.SelectSingleVideo(i);
            }
        });
        this.gridViewVideo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                VideosActivity.this.SelectSingleVideo(i);
                return true;
            }
        });
        new GetAllVideoExecute().execute(new Void[0]);
    }

    private ArrayList<DataFileModel> GetSelectedVideoList() {
        ArrayList<DataFileModel> arrayList = new ArrayList<>();
        int size = this.videosList.size();
        for (int i = 0; i < size; i++) {
            if (this.videosList.get(i).isSelected) {
                arrayList.add(this.videosList.get(i));
            }
        }
        return arrayList;
    }

    public void MoveToAlbum() {
        if (GetSelectedVideoList().size() > 0) {
            new VideoMoveToFolderExecute(GetSelectedVideoList(), this.album).execute(new Void[0]);
        } else {
            Toast.makeText(this, "Select Video.", 0).show();
        }
    }

    public void SelectSingleVideo(int i) {
        this.isSingleSelection = true;
        this.videosList.get(i).isSelected = !this.videosList.get(i).isSelected;
        if (this.videosList.get(i).isSelected) {
            this.countSelected++;
        } else {
            this.countSelected--;
        }
        if (this.countSelected <= 0) {
            this.isSingleSelection = false;
        }
        this.allGalleryVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        orientationBasedUI(configuration.orientation);
    }

    public void orientationBasedUI(int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        if (this.allGalleryVideoAdapter != null) {
            int i2 = displayMetrics.widthPixels;
            this.allGalleryVideoAdapter.setLayoutParams(i == 1 ? i2 / 4 : i2 / 8);
        }
        this.gridViewVideo.setNumColumns(i != 1 ? 8 : 4);
    }

    @Override
    public void hideViews() {
        this.progressBar.setVisibility(4);
        this.gridViewVideo.setVisibility(4);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.videosList = null;
        AllDataAdapter gMAllDataAdapter = this.allGalleryVideoAdapter;
        if (gMAllDataAdapter != null) {
            gMAllDataAdapter.releaseResources();
        }
        this.gridViewVideo.setOnItemClickListener(null);
    }


    public class GetAllVideoExecute extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressPermanentDelete;

        public GetAllVideoExecute() {
            this.progressPermanentDelete = new ProgressDialog(VideosActivity.this);
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
            VideosActivity.this.ScanVideoFiles();
            return null;
        }

        @Override 
        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            this.progressPermanentDelete.dismiss();
            VideosActivity gMVideosActivity = VideosActivity.this;
            gMVideosActivity.allGalleryVideoAdapter = new AllDataAdapter(gMVideosActivity, gMVideosActivity.getApplicationContext(), VideosActivity.this.videosList);
            VideosActivity.this.gridViewVideo.setAdapter((ListAdapter) VideosActivity.this.allGalleryVideoAdapter);
            VideosActivity.this.progressBar.setVisibility(4);
            VideosActivity.this.gridViewVideo.setVisibility(0);
            VideosActivity gMVideosActivity2 = VideosActivity.this;
            gMVideosActivity2.orientationBasedUI(gMVideosActivity2.getResources().getConfiguration().orientation);
        }
    }

    public void ScanVideoFiles() {
        try {
            ArrayList arrayList = new ArrayList();
            Cursor query = getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id", SMUtilsWP.mediaPath, "media_type", "datetaken", "bucket_id", "bucket_display_name"}, "media_type=3", null, "datetaken DESC");
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
                Log.d("IMAGESELECT", "ee" + e.getMessage());
            }
            query.close();
            if (this.videosList == null) {
                this.videosList = new ArrayList<>();
            }
            ArrayList<DataFileModel> arrayList2 = this.videosList;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
            this.videosList.addAll(arrayList);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }


    public class VideoMoveToFolderExecute extends AsyncTask<Void, Void, Void> {
        String albumName;
        ArrayList<DataFileModel> pathList;

        public VideoMoveToFolderExecute(ArrayList<DataFileModel> arrayList, String str) {
            this.pathList = new ArrayList<>();
            VideosActivity.this.progressFolder = new ProgressDialog(VideosActivity.this);
            this.pathList = arrayList;
            this.albumName = str;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            VideosActivity.this.progressFolder.setMessage("Please wait a while...");
            VideosActivity.this.progressFolder.setProgressStyle(0);
            VideosActivity.this.progressFolder.setIndeterminate(false);
            VideosActivity.this.progressFolder.setCancelable(false);
            VideosActivity.this.progressFolder.show();
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> listString = VideosActivity.this.preferenceClass.getListString(Common.gOldPath);
            File file = new File(this.albumName);
            if (!file.exists()) {
                file.mkdirs();
            }
            for (int i = 0; i < this.pathList.size(); i++) {
                File file2 = new File(this.pathList.get(i).path);
                File file3 = new File(this.albumName + file2.getName());
                String parent = file3.getParent();
                Log.d("PARENT", " - file - " + file2.getAbsolutePath() + " - parent - " + file2.getParent());
                Log.d("PARENT", " - file2 - " + file3.getAbsolutePath() + " - parent2 - " + file3.getParent());
                try {
                    FileUtils.CopyMoveFile(file2, file3);
                    AppUtilsClass.insertUri(VideosActivity.this, file3);
                    long j = this.pathList.get(i).id;
                    String str = this.pathList.get(i).name;
                    String str2 = this.pathList.get(i).path;
                    listString.add(str2);
                    arrayList.add(new DataFileModel(j, str, str2, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            File file4 = new File(this.pathList.get(i2).path);
                            file4.delete();
                            ContentResolver contentResolver = VideosActivity.this.getContentResolver();
                            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            contentResolver.delete(uri, "_data='" + file4.getPath() + "'", null);
                        }
                        VideosActivity.this.preferenceClass.putListString(Common.gOldPath, listString);
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
            if (VideosActivity.this.progressFolder.isShowing() || VideosActivity.this.progressFolder != null) {
                VideosActivity.this.progressFolder.dismiss();
            }
            VideosActivity.this.allGalleryVideoAdapter.notifyDataSetChanged();
            VideosActivity.this.setResult(-1, new Intent());
            AppUtilsClass.RefreshVideoAlbum(VideosActivity.this);
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideosActivity.VideoMoveToFolderExecute.1
                @Override 
                public void run() {
                    AppUtilsClass.RefreshMoment(VideosActivity.this);
                }
            }, 500L);
            Toast.makeText(VideosActivity.this, "Folder Created Successfully.", 0).show();
            new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMVideosActivity.VideoMoveToFolderExecute.2
                @Override 
                public void run() {
                    VideosActivity.this.finish();
                }
            }, 1000L);
        }
    }

  
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
