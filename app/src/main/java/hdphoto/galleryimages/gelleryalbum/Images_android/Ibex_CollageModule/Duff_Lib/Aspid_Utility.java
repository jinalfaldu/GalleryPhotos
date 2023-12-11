package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import unified.vpn.sdk.HydraVpnTransportException;


public class Aspid_Utility {
    static final String TAG = "Utility";
    static final float limitDivider = 30.0f;
    static final float limitDividerGinger = 160.0f;

    public static Bitmap getScaledBitmapFromId(Context context, long j, int i, int i2, boolean z) {
        AssetFileDescriptor assetFileDescriptor;
        Uri withAppendedPath = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Long.toString(j));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            assetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(withAppendedPath, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assetFileDescriptor = null;
        }
        if (assetFileDescriptor == null) {
            return null;
        }
        BitmapFactory.decodeFileDescriptor(assetFileDescriptor.getFileDescriptor(), null, options);
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = calculateInSampleSize(options, i2, i2);
        if (Build.VERSION.SDK_INT >= 11 && z) {
            options2.inMutable = true;
        }
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(assetFileDescriptor.getFileDescriptor(), null, options2);
        if (decodeFileDescriptor == null) {
            return null;
        }
        Bitmap rotateImage = rotateImage(decodeFileDescriptor, i);
        if (rotateImage != null && decodeFileDescriptor != rotateImage) {
            decodeFileDescriptor.recycle();
        }
        if (rotateImage.isMutable() || !z) {
            return rotateImage;
        }
        Log.e(TAG, "bitmap is not mutable");
        Bitmap copy = rotateImage.copy(Bitmap.Config.ARGB_8888, true);
        if (copy != rotateImage) {
            rotateImage.recycle();
        }
        return copy;
    }

    public static Bitmap decodeFile(String str, int i, boolean z) {
        ExifInterface exifInterface;
        try {
            File file = new File(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            if (Build.VERSION.SDK_INT >= 11 && z) {
                options2.inMutable = true;
            }
            options2.inSampleSize = calculateInSampleSize(options, i, i);
            Bitmap decodeStream = BitmapFactory.decodeStream(new FileInputStream(file), null, options2);
            try {
                exifInterface = new ExifInterface(str);
            } catch (IOException e) {
                e.printStackTrace();
                exifInterface = null;
            }
            Bitmap rotateImage = rotateImage(decodeStream, exifInterface.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0));
            if (rotateImage.isMutable()) {
                return rotateImage;
            }
            Bitmap copy = rotateImage.copy(Bitmap.Config.ARGB_8888, true);
            if (copy != rotateImage) {
                rotateImage.recycle();
            }
            return copy;
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    private static Bitmap rotateImage(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        if (i == 90) {
            matrix.postRotate(90.0f);
        } else if (i == 180) {
            matrix.postRotate(180.0f);
        } else if (i == 270) {
            matrix.postRotate(270.0f);
        }
        return i == 0 ? bitmap : Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (true) {
                if (i6 / i5 <= i2 && i7 / i5 <= i) {
                    break;
                }
                i5 *= 2;
            }
        }
        return i5;
    }

    public static double getLeftSizeOfMemory() {
        return (Double.valueOf(Runtime.getRuntime().maxMemory()).doubleValue() - (Double.valueOf(Runtime.getRuntime().totalMemory()).doubleValue() - Double.valueOf(Runtime.getRuntime().freeMemory()).doubleValue())) - Double.valueOf(Debug.getNativeHeapAllocatedSize()).doubleValue();
    }

    public static int maxSizeForDimension(Context context, int i, float f) {
        float f2 = Build.VERSION.SDK_INT <= 11 ? limitDividerGinger : limitDivider;
        Log.e(TAG, "divider = " + f2);
        double leftSizeOfMemory = getLeftSizeOfMemory();
        double d = (double) (((float) i) * f2);
        Double.isNaN(d);
        int sqrt = (int) Math.sqrt(leftSizeOfMemory / d);
        if (sqrt <= 0) {
            sqrt = getDefaultLimit(i, f);
        }
        return Math.min(sqrt, getDefaultLimit(i, f));
    }

    public static int maxSizeForSave(Context context, float f) {
        float f2 = Build.VERSION.SDK_INT <= 11 ? limitDividerGinger : limitDivider;
        Log.e(TAG, "divider = " + f2);
        double leftSizeOfMemory = getLeftSizeOfMemory();
        double d = (double) f2;
        Double.isNaN(d);
        int sqrt = (int) Math.sqrt(leftSizeOfMemory / d);
        return sqrt > 0 ? (int) Math.min(sqrt, f) : (int) f;
    }

    private static int getDefaultLimit(int i, float f) {
        double d = f;
        double sqrt = Math.sqrt(i);
        Double.isNaN(d);
        int i2 = (int) (d / sqrt);
        Log.e(TAG, "limit = " + i2);
        return i2;
    }

    public static float pointToAngle(float f, float f2, float f3, float f4) {
        float f5;
        double d;
        double degrees;
        double d2;
        int i = (f > f3 ? 1 : (f == f3 ? 0 : -1));
        char c = i > 0 ? (char) 1 : i == 0 ? (char) 0 : (char) 65535;
        if (c >= 0 && f2 < f4) {
            d = 270.0d;
            double d3 = f - f3;
            double d4 = f4 - f2;
            Double.isNaN(d3);
            Double.isNaN(d4);
            degrees = Math.toDegrees(Math.atan(d3 / d4));
        } else if (c > 0 && f2 >= f4) {
            double d5 = f2 - f4;
            double d6 = f - f3;
            Double.isNaN(d5);
            Double.isNaN(d6);
            Math.toDegrees(Math.atan(d5 / d6));
            d2 = 0.0d;
            return (float) d2;
        } else if (c > 0 || f2 <= f4) {
            if (c >= 0 || f2 > f4) {
                f5 = 0.0f;
            } else {
                double d7 = f4 - f2;
                double d8 = f3 - f;
                Double.isNaN(d7);
                Double.isNaN(d8);
                f5 = ((int) Math.toDegrees(Math.atan(d7 / d8))) + HydraVpnTransportException.HYDRA_ERROR_CONFIGURATION;
            }
            if (f5 < -180.0f) {
                f5 += 360.0f;
            }
            return f5 > 180.0f ? f5 - 360.0f : f5;
        } else {
            d = 90.0d;
            double d9 = f3 - f;
            double d10 = f2 - f4;
            Double.isNaN(d9);
            Double.isNaN(d10);
            degrees = Math.toDegrees(Math.atan(d9 / d10));
        }
        d2 = degrees + d;
        return (float) d2;
    }

    public static int maxSizeForSave() {
        int sqrt = (int) Math.sqrt(getLeftSizeOfMemory() / 40.0d);
        if (sqrt > 1080) {
            return 1080;
        }
        return sqrt;
    }
}
