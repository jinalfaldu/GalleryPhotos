package hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataDeleteTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataDeleteFilesAsyncTask extends AsyncTask<String, Integer, String> {
    public static int REQUEST_PERM_DELETE = 786;
    int count = 0;
    ArrayList<File> listFile;
    Activity mContext;
    ProgressDialog mProgressDialog;
    OnRestoreListener onRestoreListener;

    
    public interface OnRestoreListener {
        void onComplete();
    }

    public DataDeleteFilesAsyncTask(Activity activity, ArrayList<File> arrayList, OnRestoreListener onRestoreListener) {
        this.mContext = activity;
        this.listFile = arrayList;
        this.onRestoreListener = onRestoreListener;
        this.mProgressDialog = new ProgressDialog(activity);
    }

    @Override 
    public void onPreExecute() {
        super.onPreExecute();
        this.mProgressDialog.setMessage("Please wait a while...");
        this.mProgressDialog.setIndeterminate(false);
        this.mProgressDialog.setCancelable(true);
        this.mProgressDialog.show();
    }

    @Override 
    public String doInBackground(String... strArr) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (i < this.listFile.size()) {
            if (this.listFile.get(i).exists()) {
                try {
                    isOnExtSdCard(this.listFile.get(i), this.mContext);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if (!isOnExtSdCard(this.listFile.get(i), this.mContext)) {
                        try {
                            this.listFile.get(i).delete();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        try {
                            GetDocumentFile(this.listFile.get(i), false, this.mContext).delete();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            i++;
            this.count = i;
            publishProgress(Integer.valueOf(i));
        }
        try {
            Thread.sleep(2000);
            return null;
        } catch (InterruptedException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    @Override 
    public void onPostExecute(String str) {
        super.onPostExecute((String) str);
        try {
            ProgressDialog progressDialog = this.mProgressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.mProgressDialog.cancel();
                this.mProgressDialog = null;
            }
        } catch (Exception unused) {
        }
        OnRestoreListener onRestoreListener = this.onRestoreListener;
        if (onRestoreListener != null) {
            onRestoreListener.onComplete();
        }
    }

    @Override 
    public void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate((Integer[]) numArr);
    }

    private DocumentFile GetDocumentFile(File file, boolean z, Context context) throws IOException {
        String str;
        Uri parse;
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("sdCardUri", "");
        String GetExtSdCardFolder = GetExtSdCardFolder(file, context);
        if (GetExtSdCardFolder == null) {
            return null;
        }
        try {
            str = file.getCanonicalPath();
        } catch (Exception unused) {
            str = null;
        }
        if (GetExtSdCardFolder.equals(str)) {
            if (string != null || string.equals("") || (parse = Uri.parse(string)) == null) {
                return null;
            }
            return DocumentFile.fromTreeUri(context, parse);
        }
        str.substring(GetExtSdCardFolder.length() + 1);
        return null;
    }

    private static String GetExtSdCardFolder(File file, Context context) throws IOException {
        String[] GetExtSdCardPaths = GetExtSdCardPaths(context);
        for (int i = 0; i < GetExtSdCardPaths.length; i++) {
            if (file.getCanonicalPath().startsWith(GetExtSdCardPaths[i])) {
                return GetExtSdCardPaths[i];
            }
            continue;
        }
        return null;
    }

    private static String[] GetExtSdCardPaths(Context context) {
        File[] externalFilesDirs;
        ArrayList arrayList = new ArrayList();
        for (File file : context.getExternalFilesDirs("external")) {
            if (file != null && !file.equals(context.getExternalFilesDir("external"))) {
                int lastIndexOf = file.getAbsolutePath().lastIndexOf("/Android/data");
                if (lastIndexOf < 0) {
                    Log.w("log", "Unexpected external file dir: " + file.getAbsolutePath());
                } else {
                    String substring = file.getAbsolutePath().substring(0, lastIndexOf);
                    try {
                        substring = new File(substring).getCanonicalPath();
                    } catch (IOException unused) {
                    }
                    arrayList.add(substring);
                }
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add("/storage/sdcard1");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static boolean isOnExtSdCard(File file, Context context) throws IOException {
        return GetExtSdCardFolder(file, context) != null;
    }

    public String getType(Context context, File file) {
        return context.getContentResolver().getType(Uri.fromFile(file));
    }

    @SuppressLint("Range")
    public static long GetFilePathToMediaID(String str, Context context) {
        Cursor query = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id"}, "_data=?", new String[]{str}, null);
        long j = 0;
        if (query != null) {
            while (query.moveToNext()) {
                j = Long.parseLong(query.getString(query.getColumnIndex("_id")));
            }
        }
        return j;
    }

    public static void requestDeletePermission(List<Uri> list, Activity activity) {
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                activity.startIntentSenderForResult(MediaStore.createDeleteRequest(activity.getContentResolver(), list).getIntentSender(), REQUEST_PERM_DELETE, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void requestIntent(List<Uri> list, Activity activity) {
        if (Build.VERSION.SDK_INT < 30 || list.size() <= 0) {
            return;
        }
        requestDeletePermission(list, activity);
    }
}
