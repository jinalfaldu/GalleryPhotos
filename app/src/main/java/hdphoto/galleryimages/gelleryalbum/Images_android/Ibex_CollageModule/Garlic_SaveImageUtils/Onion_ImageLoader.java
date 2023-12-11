package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Garlic_SaveImageUtils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import cz.msebera.android.httpclient.HttpHost;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Onion_ImageLoader {
    Context context;
    Cursor cursorBackup;
    public String fileManagerString;
    ImageLoaded imageLoadedListener;
    public String selectedImagePath;
    String TAG = "ImageLoader";
    int count = 0;
    String loadImageMessage = "Loading image!";


    public interface ImageLoaded {
        void callFileSizeDialog();
    }


    public class LoadImage19Task extends AsyncTask<Uri, Void, Void> {
        String path;
        ProgressDialog saveImageDialog;
        Uri uri;

        private LoadImage19Task() {
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
            Onion_ImageLoader cMImageLoader = Onion_ImageLoader.this;
            cMImageLoader.loadImageMessage = cMImageLoader.context.getString(R.string.loading_image);
            try {
                ProgressDialog progressDialog = new ProgressDialog(Onion_ImageLoader.this.context);
                this.saveImageDialog = progressDialog;
                progressDialog.setMessage(Onion_ImageLoader.this.loadImageMessage);
                this.saveImageDialog.show();
            } catch (Exception unused) {
            }
        }

        @Override 
        public void onPostExecute(Void r2) {
            super.onPostExecute((Void) r2);
            Onion_ImageLoader.this.selectedImagePath = this.path;
            Onion_ImageLoader.this.startActivityFromUri(this.uri);
            try {
                this.saveImageDialog.dismiss();
            } catch (Exception unused) {
            }
        }

        @Override 
        public Void doInBackground(Uri... uriArr) {
            if (uriArr == null) {
                return null;
            }
            try {
                Uri uri = uriArr[0];
                this.uri = uri;
                if (uri == null) {
                    return null;
                }
                this.path = Onion_ImageLoader.this.getRealPathFromURI19(uri);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void setListener(ImageLoaded imageLoaded) {
        this.imageLoadedListener = imageLoaded;
    }

    public Onion_ImageLoader(Context context) {
        this.context = context;
    }

    public void getImageFromIntent(Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            data = (Uri) intent.getExtras().get("android.intent.extra.STREAM");
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.selectedImagePath = getPathForKitKat(data);
            String str = this.TAG;
            Log.w(str, "getPathForKitKat " + this.selectedImagePath);
            if (this.selectedImagePath == null) {
                new LoadImage19Task().execute(data);
                return;
            } else {
                startActivityFromUri(data);
                return;
            }
        }
        this.selectedImagePath = getRealPathFromURI(data);
        String str2 = this.TAG;
        Log.e(str2, "getImageFromIntent selectedImagePath  " + this.selectedImagePath);
        startActivityFromUri(data);
    }

    public void startActivityFromUri(Uri uri) {
        this.fileManagerString = uri.getPath();
        Log.w(this.TAG, "startActivityFromUri selectedImagePath" + this.selectedImagePath);
        if (this.selectedImagePath == null) {
            this.selectedImagePath = this.fileManagerString;
            Log.w(this.TAG, "null selectedImagePath " + this.selectedImagePath);
        }
        String str = this.selectedImagePath;
        if (str == null || str.length() == 0 || this.selectedImagePath.toLowerCase().contains(HttpHost.DEFAULT_SCHEME_NAME)) {
            new LoadImage19Task().execute(uri);
            this.count++;
        } else if (checkFileExtension(this.selectedImagePath)) {
            this.imageLoadedListener.callFileSizeDialog();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setMessage("Image Format Error").setCancelable(false).setNegativeButton("Ok", new DialogInterface.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMSaveImageUtils.CMImageLoader.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    }

    public String getRealPathFromURI(Uri uri) {
        String[] strArr = {SMUtilsWP.mediaPath};
        Cursor query = this.context.getContentResolver().query(uri, strArr, null, null, null);
        String r2 = null;
        try {
            r2 = query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow(SMUtilsWP.mediaPath)) : null;
            query.close();
        } catch (Exception unused) {
        }
        String str = r2;
        if (query == null || str == null) {
            Cursor query2 = this.context.getContentResolver().query(uri, strArr, null, null, null);
            this.cursorBackup = query2;
            if (query2 == null) {
                return str;
            }
            try {
                int columnIndexOrThrow = query2.getColumnIndexOrThrow(SMUtilsWP.mediaPath);
                this.cursorBackup.moveToFirst();
                return this.cursorBackup.getString(columnIndexOrThrow);
            } catch (Exception unused2) {
                return str;
            }
        }
        return str;
    }

    public String getRealPathFromURI19(Uri uri) throws IOException {
        return saveImageToTemp(getBitmapFromUri(uri));
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor openFileDescriptor = this.context.getContentResolver().openFileDescriptor(uri, "r");
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
        openFileDescriptor.close();
        return decodeFileDescriptor;
    }

    private String saveImageToTemp(Bitmap bitmap) throws FileNotFoundException {
        File file;
        String str = String.valueOf(Judge_ImageUtility.getPrefferredDirectoryPath(this.context, false, true, false)) + "temp/dump.dump";
        new File(str).getParentFile().mkdirs();
        Log.i(this.TAG, "resultPath " + str);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, new FileOutputStream(str));
        bitmap.recycle();
        return str;
    }

    private String getFileExtension(String str) {
        if (str == null) {
            str = "";
        }
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf > 0 ? str.substring(lastIndexOf) : "";
    }

    private boolean checkFileExtension(String str) {
        String lowerCase = getFileExtension(str).toLowerCase();
        return lowerCase.contains("jpg") || lowerCase.contains("png") || lowerCase.contains("jpeg") || lowerCase.contains("gif") || lowerCase.contains("bmp") || lowerCase.contains("webp") || lowerCase.contains("dump");
    }

    public String getPathForKitKat(Uri uri) {
        Uri uri2 = null;
        if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(this.context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(this.context, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(" ");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(this.context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else {
            if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(" ");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(this.context, uri2, "_id=?", new String[]{split2[1]});
            }
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{SMUtilsWP.mediaPath}, str, strArr, null);
            if (query == null || !query.moveToFirst()) {
                if (query == null) {
                    return null;
                }
                query.close();
                return null;
            }
            DatabaseUtils.dumpCursor(query);
            String string = query.getString(query.getColumnIndexOrThrow(SMUtilsWP.mediaPath));
            if (query == null) {
                return string;
            }
            query.close();
            return string;
        } catch (Throwable unused) {
            return SMUtilsWP.mediaPath;
        }
    }
}
