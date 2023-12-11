package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils;

import android.os.Debug;
import hdphoto.galleryimages.gelleryalbum.R;


public class Jumper_EffectUtility {
    public static int EFFECT_MODE_MULTIPLY = 1;
    public static int EFFECT_MODE_NONE = -1;
    public static int EFFECT_MODE_OVERLAY = 2;
    public static int EFFECT_MODE_SCREEN = 3;
    public static int[] framesArray = {-1, R.drawable.frame_0, R.drawable.frame_1, R.drawable.frame_2, R.drawable.frame_3, R.drawable.frame_4, R.drawable.frame_5, R.drawable.frame_6, R.drawable.frame_7, R.drawable.frame_8, R.drawable.frame_9, R.drawable.frame_10, R.drawable.frame_11, R.drawable.frame_12, R.drawable.frame_13, R.drawable.frame_14, R.drawable.frame_15, R.drawable.frame_16, R.drawable.frame_17, R.drawable.frame_18, R.drawable.frame_19, R.drawable.frame_20, R.drawable.frame_21, R.drawable.frame_22, R.drawable.frame_23, R.drawable.frame_24, R.drawable.frame_25, R.drawable.frame_26, R.drawable.frame_27, R.drawable.frame_28, R.drawable.frame_29, R.drawable.frame_30, R.drawable.frame_31, R.drawable.frame_32, R.drawable.frame_33, R.drawable.frame_34, R.drawable.frame_35, R.drawable.frame_36, R.drawable.frame_37, R.drawable.frame_38, R.drawable.frame_39, R.drawable.frame_40, R.drawable.frame_41, R.drawable.frame_42, R.drawable.frame_43, R.drawable.frame_44};
    public static int[] framesThumbArray = {R.drawable.no_pattern, R.drawable.frame_0_thumb, R.drawable.frame_1_thumb, R.drawable.frame_2_thumb, R.drawable.frame_3_thumb, R.drawable.frame_4_thumb, R.drawable.frame_5_thumb, R.drawable.frame_6_thumb, R.drawable.frame_7_thumb, R.drawable.frame_8_thumb, R.drawable.frame_9_thumb, R.drawable.frame_10_thumb, R.drawable.frame_11_thumb, R.drawable.frame_12_thumb, R.drawable.frame_13_thumb, R.drawable.frame_14_thumb, R.drawable.frame_15_thumb, R.drawable.frame_16_thumb, R.drawable.frame_17_thumb, R.drawable.frame_18_thumb, R.drawable.frame_19_thumb, R.drawable.frame_20_thumb, R.drawable.frame_21_thumb, R.drawable.frame_22_thumb, R.drawable.frame_23_thumb, R.drawable.frame_24_thumb, R.drawable.frame_25_thumb, R.drawable.frame_26_thumb, R.drawable.frame_27_thumb, R.drawable.frame_28_thumb, R.drawable.frame_29_thumb, R.drawable.frame_30_thumb, R.drawable.frame_31_thumb, R.drawable.frame_32_thumb, R.drawable.frame_33_thumb, R.drawable.frame_34_thumb, R.drawable.frame_35_thumb, R.drawable.frame_36_thumb, R.drawable.frame_37_thumb, R.drawable.frame_38_thumb, R.drawable.frame_39_thumb, R.drawable.frame_40_thumb, R.drawable.frame_41_thumb, R.drawable.frame_42_thumb, R.drawable.frame_43_thumb, R.drawable.frame_44_thumb};
    public static int[] filtersThumbArray = {R.drawable.no_pattern, R.drawable.filter_1_thumb, R.drawable.filter_2_thumb, R.drawable.filter_3_thumb, R.drawable.filter_4_thumb, R.drawable.filter_5_thumb, R.drawable.filter_6_thumb, R.drawable.filter_7_thumb, R.drawable.filter_8_thumb, R.drawable.filter_9_thumb, R.drawable.filter_10_thumb, R.drawable.filter_11_thumb, R.drawable.filter_12_thumb, R.drawable.filter_13_thumb, R.drawable.filter_14_thumb, R.drawable.filter_15_thumb, R.drawable.filter_16_thumb, R.drawable.filter_17_thumb, R.drawable.filter_18_thumb, R.drawable.filter_19_thumb, R.drawable.filter_20_thumb, R.drawable.filter_21_thumb, R.drawable.filter_22_thumb};
    public static int[] overlaysArray = {-1, R.drawable.lens_01, R.drawable.lens_02, R.drawable.lens_03, R.drawable.lens_04, R.drawable.lens_05, R.drawable.lens_06, R.drawable.lens_07, R.drawable.lens_08, R.drawable.lens_09, R.drawable.lens_10, R.drawable.lens_11, R.drawable.lens_12, R.drawable.lens_13, R.drawable.lens_14, R.drawable.lens_15, R.drawable.lens_16, R.drawable.lens_17, R.drawable.lens_18, R.drawable.lens_19, R.drawable.lens_20, R.drawable.lens_21, R.drawable.lens_22, R.drawable.lens_23, R.drawable.lens_24, R.drawable.lens_26, R.drawable.lens_27, R.drawable.lens_28};
    public static int[] overlaysThumbArray = {R.drawable.no_pattern, R.drawable.lens_0_thumb, R.drawable.lens_1_thumb, R.drawable.lens_2_thumb, R.drawable.lens_3_thumb, R.drawable.lens_4_thumb, R.drawable.lens_5_thumb, R.drawable.lens_6_thumb, R.drawable.lens_7_thumb, R.drawable.lens_8_thumb, R.drawable.lens_9_thumb, R.drawable.lens_10_thumb, R.drawable.lens_11_thumb, R.drawable.lens_12_thumb, R.drawable.lens_13_thumb, R.drawable.lens_14_thumb, R.drawable.lens_15_thumb, R.drawable.lens_16_thumb, R.drawable.lens_17_thumb, R.drawable.lens_18_thumb, R.drawable.lens_19_thumb, R.drawable.lens_20_thumb, R.drawable.lens_21_thumb, R.drawable.lens_22_thumb, R.drawable.lens_23_thumb, R.drawable.lens_24_thumb, R.drawable.lens_25_thumb, R.drawable.lens_26_thumb};
    public static int[] screensArray = {-1, R.drawable.screen_01, R.drawable.screen_03, R.drawable.screen_04, R.drawable.screen_22, R.drawable.screen_05, R.drawable.screen_06, R.drawable.screen_07, R.drawable.screen_08, R.drawable.screen_09, R.drawable.screen_10, R.drawable.screen_26, R.drawable.screen_11, R.drawable.screen_12, R.drawable.screen_13, R.drawable.screen_23, R.drawable.screen_14, R.drawable.screen_15, R.drawable.screen_16, R.drawable.screen_18, R.drawable.screen_19, R.drawable.screen_21, R.drawable.screen_02, R.drawable.screen_24};
    public static int[] screensThumbArray = {R.drawable.no_pattern, R.drawable.screen_0_thumb, R.drawable.screen_1_thumb, R.drawable.screen_2_thumb, R.drawable.screen_3_thumb, R.drawable.screen_4_thumb, R.drawable.screen_5_thumb, R.drawable.screen_6_thumb, R.drawable.screen_7_thumb, R.drawable.screen_8_thumb, R.drawable.screen_9_thumb, R.drawable.screen_10_thumb, R.drawable.screen_11_thumb, R.drawable.screen_12_thumb, R.drawable.screen_13_thumb, R.drawable.screen_14_thumb, R.drawable.screen_15_thumb, R.drawable.screen_16_thumb, R.drawable.screen_17_thumb, R.drawable.screen_18_thumb, R.drawable.screen_19_thumb, R.drawable.screen_20_thumb, R.drawable.screen_21_thumb, R.drawable.screen_22_thumb};
    public static int[] screenModesList = {-1, 2, 3, 2, 2, 3, 3, 2, 2, 2, 2, 2, 3, 3, 3, 2, 3, 3, 3, 2, 1, 1, 3, 2};

    
    public interface ExcludeTabListener {
        void exclude();
    }

    
    public interface FooterVisibilityListener {
        void setVisibility();
    }

    public static double GetFreeSpaceOfMemory() {
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
}
