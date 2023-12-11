package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils;

import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;


public class MusicianUtility {
    private static String TAG = "com.dev.com.gallery.utils.Utility";
    private static final float limitDivider = 60.0f;
    private static final float limitDividerGinger = 160.0f;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static final int upperLimitGinger = 800;

    public static int generateViewId() {
        AtomicInteger atomicInteger;
        int i;
        int i2;
        do {
            atomicInteger = sNextGeneratedId;
            i = atomicInteger.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!atomicInteger.compareAndSet(i, i2));
        return i;
    }

    public static int GetMaxSizeForDimension(Context context, int i, float f) {
        float f2;
        if (Build.VERSION.SDK_INT <= 11) {
            f = 800.0f;
            f2 = limitDividerGinger;
        } else {
            f2 = limitDivider;
        }
        String str = TAG;
        Log.e(str, "divider = " + f2);
        double GetLeftSizeOfMemory = GetLeftSizeOfMemory();
        double d = (double) (((float) i) * f2);
        Double.isNaN(d);
        int sqrt = (int) Math.sqrt(GetLeftSizeOfMemory / d);
        if (sqrt <= 0) {
            sqrt = GetDefaultLimit(i, f);
        }
        return Math.min(sqrt, GetDefaultLimit(i, f));
    }

    public static int maxSizeForSave(Context context, float f) {
        float f2 = Build.VERSION.SDK_INT <= 11 ? limitDividerGinger : limitDivider;
        String str = TAG;
        Log.e(str, "divider = " + f2);
        double GetLeftSizeOfMemory = GetLeftSizeOfMemory();
        double d = (double) f2;
        Double.isNaN(d);
        int sqrt = (int) Math.sqrt(GetLeftSizeOfMemory / d);
        return sqrt > 0 ? (int) Math.min(sqrt, f) : (int) f;
    }

    public static double getLeftSizeOfMemoryMb() {
        double maxMemory = Runtime.getRuntime().maxMemory() / 1048576;
        double d = Runtime.getRuntime().totalMemory() / 1048576;
        Double.isNaN(maxMemory);
        Double.isNaN(d);
        return (maxMemory - d) - (Double.valueOf(Debug.getNativeHeapAllocatedSize()).doubleValue() / Double.valueOf(1048576.0d).doubleValue());
    }

    public static double getLeftSizeOfMemoryEx(Context context) {
        double maxMemory = Runtime.getRuntime().maxMemory();
        double d = Runtime.getRuntime().totalMemory();
        Double.isNaN(maxMemory);
        Double.isNaN(d);
        double d2 = maxMemory - d;
        double nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
        Double.isNaN(nativeHeapAllocatedSize);
        return d2 - nativeHeapAllocatedSize;
    }

    public static double GetLeftSizeOfMemory() {
        double maxMemory = Runtime.getRuntime().maxMemory();
        double d = Runtime.getRuntime().totalMemory();
        double freeMemory = Runtime.getRuntime().freeMemory();
        Double.isNaN(d);
        Double.isNaN(freeMemory);
        Double.isNaN(maxMemory);
        double d2 = maxMemory - (d - freeMemory);
        double nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
        Double.isNaN(nativeHeapAllocatedSize);
        return d2 - nativeHeapAllocatedSize;
    }

    public static void GetFreeMemory(Context context) {
        String str = TAG;
        Log.e(str, "free memory own method = " + (GetLeftSizeOfMemory() / 1048576.0d));
    }

    private static int GetDefaultLimit(int i, float f) {
        double d = f;
        double sqrt = Math.sqrt(i);
        Double.isNaN(d);
        int i2 = (int) (d / sqrt);
        Log.e(TAG, "limit = " + i2);
        return i2;
    }
}
