package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdBitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class BitmapResizer {
    public static Point DecodeFileSize(File file, int i) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = 1;
            while (Math.max(i2, i3) / 2 > i) {
                i2 /= 2;
                i3 /= 2;
                i4 *= 2;
            }
            if (i4 == 1) {
                return new Point(-1, -1);
            }
            return new Point(i2, i3);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    public static Bitmap DecodeBitmapFromFile(String str, int i) {
        try {
            new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap DecodeFile = DecodeFile(str, i);
        if (DecodeFile == null) {
            return null;
        }
        if (DecodeFile == null || Build.VERSION.SDK_INT >= 13) {
            return DecodeFile;
        }
        Bitmap copy = DecodeFile.copy(Bitmap.Config.ARGB_8888, true);
        if (DecodeFile != copy) {
            DecodeFile.recycle();
        }
        return copy;
    }

    private static Bitmap DecodeFile(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i2 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        while (Math.max(i3, i4) / 2 > i) {
            i3 /= 2;
            i4 /= 2;
            i2 *= 2;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = i2;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options2);
        if (decodeFile != null) {
            Log.e("decoded file height", String.valueOf(decodeFile.getHeight()));
            Log.e("decoded file width", String.valueOf(decodeFile.getWidth()));
        }
        return decodeFile;
    }
}
