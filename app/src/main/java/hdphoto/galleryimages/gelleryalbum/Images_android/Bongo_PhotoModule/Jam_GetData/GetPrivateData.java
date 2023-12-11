package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.MimeTypeMap;
import androidx.documentfile.provider.DocumentFile;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImagePrivateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class GetPrivateData {
    public static boolean gettingData = false;
    public Activity context;
    Context mContext;

    public GetPrivateData(Activity activity) {
        this.context = activity;
        new GetHideDataAsync(activity).execute(new Void[0]);
    }

    
    public class GetHideDataAsync extends AsyncTask<Void, Void, Void> {
        DocumentFile[] allFiles;
        String extension;

        public GetHideDataAsync(Context context) {
            GetPrivateData.this.mContext = context;
        }

        @Override 
        protected void onPreExecute() {
            GetPrivateData.gettingData = true;
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            this.allFiles = null;
            Degu_MainActivity.ArrayImage11 = new ArrayList<>();
            Degu_MainActivity.ArrayVideo11 = new ArrayList<>();
            GetPrivateData gMGetPrivateData = GetPrivateData.this;
            this.allFiles = gMGetPrivateData.getFromSdcard(gMGetPrivateData.mContext);
            int i = 0;
            while (true) {
                DocumentFile[] documentFileArr = this.allFiles;
                if (documentFileArr != null) {
                    if (i >= documentFileArr.length) {
                        return null;
                    }
                    if (!documentFileArr[i].getUri().toString().contains(".nomedia")) {
                        if (this.allFiles[i].getUri().getScheme().equals("content")) {
                            this.extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(GetPrivateData.this.context.getContentResolver().getType(this.allFiles[i].getUri()));
                        } else {
                            this.extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(this.allFiles[i].getUri().getPath())).toString());
                        }
                        if (this.extension.equals("jpg") || this.extension.equals("JPG") || this.extension.equals("png") || this.extension.equals("PNG") || this.extension.equals("jpeg") || this.extension.equals("JPEG") || this.extension.equals("gif") || this.extension.equals("GIF")) {
                            DataFileModel gMDataFileModel = new DataFileModel();
                            gMDataFileModel.path = this.allFiles[i].getUri().toString();
                            gMDataFileModel.name = this.allFiles[i].getName();
                            gMDataFileModel.filedate = GetPrivateData.this.GetDateFromTimeStamp(this.allFiles[i].lastModified());
                            gMDataFileModel.filesize = GetPrivateData.this.GetFileSize(this.allFiles[i].length());
                            gMDataFileModel.mediaType = this.extension;
                            Degu_MainActivity.ArrayImage11.add(gMDataFileModel);
                        }
                        if (this.extension.equals("mp4") || this.extension.equals("MP4") || this.extension.equals("3gp") || this.extension.equals("3GP") || this.extension.equals("mkv") || this.extension.equals("MKV")) {
                            DataFileModel gMDataFileModel2 = new DataFileModel();
                            gMDataFileModel2.path = this.allFiles[i].getUri().toString();
                            gMDataFileModel2.name = this.allFiles[i].getName();
                            gMDataFileModel2.filedate = GetPrivateData.this.GetDateFromTimeStamp(this.allFiles[i].lastModified());
                            gMDataFileModel2.filesize = GetPrivateData.this.GetFileSize(this.allFiles[i].length());
                            gMDataFileModel2.mediaType = this.extension;
                            Degu_MainActivity.ArrayVideo11.add(gMDataFileModel2);
                        }
                    }
                    i++;
                }
            }
        }

        @Override 
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            GetPrivateData.gettingData = false;
            if (ImImagePrivateFragment.privateImageAdapter != null) {
                ImImagePrivateFragment.privateImageAdapter.refreshData(Degu_MainActivity.ArrayImage11);
            }
        }
    }

    private static String getTime(int i) {
        int i2 = i / 3600;
        int i3 = i % 3600;
        return String.format("%02d", Integer.valueOf(i2)) + " " + String.format("%02d", Integer.valueOf(i3 / 60)) + " " + String.format("%02d", Integer.valueOf(i3 % 60));
    }

    public static long getDuration(Context context, String str, String str2) {
        MediaPlayer mediaPlayer = null;
        try {
            mediaPlayer = new MediaPlayer();
        } catch (Throwable unused) {
        }
        try {
            mediaPlayer.setDataSource(context, Uri.parse(str2));
            mediaPlayer.prepare();
            long duration = mediaPlayer.getDuration();
            mediaPlayer.reset();
            mediaPlayer.release();
            return duration;
        } catch (Exception unused2) {
            return 0L;
        }
    }

    public DocumentFile[] getFromSdcard(Context context) {
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(context, Uri.parse(Degu_MainActivity.getWATree(context)));
        if (fromTreeUri != null && fromTreeUri.exists() && fromTreeUri.isDirectory() && fromTreeUri.canRead() && fromTreeUri.canWrite()) {
            return fromTreeUri.listFiles();
        }
        return null;
    }

    public String GetDateFromTimeStamp(long j) {
        return new SimpleDateFormat("EEE dd MMM yyyy hh:mm:ss z").format(new Date(j));
    }

    public String GetFileSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float f = (float) j;
        if (f < 1048576.0f) {
            return decimalFormat.format(f / 1024.0f) + " Kb";
        } else if (f < 1.07374182E9f) {
            return decimalFormat.format(f / 1048576.0f) + " Mb";
        } else if (f >= 1.09951163E12f) {
            return "";
        } else {
            return decimalFormat.format(f / 1.07374182E9f) + " Gb";
        }
    }
}
