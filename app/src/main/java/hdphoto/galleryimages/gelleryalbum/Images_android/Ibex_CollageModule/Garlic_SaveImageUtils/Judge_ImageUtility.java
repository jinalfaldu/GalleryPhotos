package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Garlic_SaveImageUtils;

import android.content.Context;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import hdphoto.galleryimages.gelleryalbum.R;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Judge_ImageUtility {
    static String TAG = "SaveImage Utility";

    public static String getPrefferredDirectoryPathEx(Context context) {
        String string = null;
        String str = String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + context.getResources().getString(R.string.directory);
        if (PreferenceManager.getDefaultSharedPreferences(context).getString("save_image_directory_custom", null) == null) {
            return str;
        }
        return String.valueOf(string) + "/";
    }

    public static String getPrefferredDirectoryPath(Context context, boolean z, boolean z2, boolean z3) {
        String str;
        String string = null;
        if (z3) {
            str = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()) + File.separator + context.getResources().getString(R.string.directory);
        } else {
            str = String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + context.getResources().getString(R.string.directory);
        }
        if (PreferenceManager.getDefaultSharedPreferences(context).getString("save_image_directory_custom", null) != null) {
            String str2 = String.valueOf(string) + File.separator;
            if (z2) {
                return str2;
            }
            File file = new File(str2);
            if (file.canRead() && file.canWrite() && checkIfEACCES(str2)) {
                str = str2;
            }
            Log.e(TAG, "prefDir " + str2);
        }
        return str;
    }

    public static boolean checkIfEACCES(String str) {
        String str2 = null;
        boolean z;
        try {
            File file = new File(str);
            file.mkdirs();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(String.valueOf(str) + "mpp"), "utf-8"));
            try {
                bufferedWriter.write("Something");
                z = true;
            } catch (Throwable unused) {
                z = false;
            }
            try {
                bufferedWriter.close();
                try {
                    bufferedWriter.close();
                } catch (Exception unused2) {
                }
                return z;
            } catch (IOException e) {
                Log.e(TAG, e.toString());
                try {
                    bufferedWriter.close();
                } catch (Exception unused3) {
                }
                return z;
            }
        } catch (IOException e2) {
            Log.e(TAG, e2.toString());
            return false;
        }
    }
}
