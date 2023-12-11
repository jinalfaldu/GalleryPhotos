package hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class SMUtilsWP {
    public static byte[] byteArray = null;
    public static int byteSize = 0;
    public static int bytesAvailable = 0;
    public static int bytesRead = 0;
    public static ContentValues contentValues = null;
    public static FileInputStream inputStream = null;
    public static int maxByteSize = 1048576;
    public static final String mediaPath = "_data";

    public static boolean copyFilesToOther(String str, String str2) {
        try {
            File file = new File(str);
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(str2)));
            FileInputStream fileInputStream = new FileInputStream(file);
            inputStream = fileInputStream;
            int available = fileInputStream.available();
            bytesAvailable = available;
            int min = Math.min(available, maxByteSize);
            byteSize = min;
            byte[] bArr = new byte[min];
            byteArray = bArr;
            bytesRead = inputStream.read(bArr, 0, min);
            while (bytesRead > 0) {
                dataOutputStream.write(byteArray, 0, byteSize);
                int available2 = inputStream.available();
                bytesAvailable = available2;
                int min2 = Math.min(available2, maxByteSize);
                byteSize = min2;
                bytesRead = inputStream.read(byteArray, 0, min2);
            }
            inputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void refreshMediaStore(Context context, File file) {
        if (Build.VERSION.SDK_INT < 19) {
            MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.StatusModule.SMWp11.ConstantWP.SMUtilsWP.1
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str, Uri uri) {
                }
            });
            return;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

    public static void broadcastUris(Context context, File file, String str) {
        String[] location = getLocation(context, file.getAbsolutePath(), str);
        try {
            contentValues = new ContentValues();
            if (str.equals("img")) {
                if (location[0].isEmpty() && location[1].isEmpty()) {
                    contentValues.put(mediaPath, file.getAbsolutePath());
                    context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                }
                contentValues.put(mediaPath, file.getAbsolutePath());
                contentValues.put("longitude", location[0]);
                contentValues.put("latitude", location[1]);
                context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            } else if (str.equals("vid")) {
                contentValues.put(mediaPath, file.getAbsolutePath());
                context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
            }
        } catch (Exception unused) {
        }
    }

    public static String[] getLocation(Context context, String str, String str2) {
        Uri uri;
        String[] strArr;
        String[] strArr2 = {"", ""};
        try {
            if (str2.equals("img")) {
                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                strArr = new String[]{"longitude", "latitude"};
            } else {
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                strArr = new String[]{"longitude", "latitude"};
            }
            Cursor query = context.getContentResolver().query(uri, strArr, "_data =?", new String[]{str}, null);
            if (query.getCount() > 0) {
                query.moveToFirst();
                strArr2 = new String[]{query.getString(query.getColumnIndex("longitude")), query.getString(query.getColumnIndex("latitude"))};
            }
            query.close();
        } catch (Exception unused) {
        }
        return strArr2;
    }
}
