package hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.anchorfree.hdr.AFHydra;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Locale;
import org.xbill.DNS.SimpleResolver;


public class Utils {

    public static final String IMAGE_RECOVER_DIRECTORY = Environment.getExternalStorageDirectory() + File.separator + "RestoredPhotos";

    public static String formatFileSize(long j) {
        double d = 0;
        if (j <= 0) {
            return "";
        }
        int log10 = (int) (Math.log10(j) / Math.log10(1024.0d));
        return new DecimalFormat("#,##0.#").format(d / Math.pow(1024.0d, log10)) + " " + new String[]{AFHydra.EV_BYTECOUNT, "KB", "MB", "GB", "TB"}[log10];
    }

    public static String getFileName(String str) {
        return str.substring(str.lastIndexOf("/") + 1);
    }

    public static File[] getFileList(String str) {
        File file = new File(str);
        return !file.isDirectory() ? new File[0] : file.listFiles();
    }

    public static boolean checkSelfPermission(Activity activity, String str) {
        return !isAndroid23() || ContextCompat.checkSelfPermission(activity, str) == 0;
    }

    public static boolean isAndroid23() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static void setLocale(Context context) {
        String str;
        int languageIndex = SharePreferenceUtils.getInstance(context).getLanguageIndex();
        if (languageIndex == 0) {
            str = "cs";
        } else if (languageIndex == 1) {
            str = "de";
        } else {
            if (languageIndex == 2 || languageIndex == 3 || languageIndex == 4 || languageIndex == 5 || languageIndex == 6 || languageIndex == 7 || languageIndex == 8 || languageIndex == 9 || languageIndex == 10 || languageIndex == 11 || languageIndex != 12) {
            }
            str = "en";
        }
        if (SharePreferenceUtils.getInstance(context).getFirstRun()) {
            str = Locale.getDefault().getLanguage();
            if (str.equalsIgnoreCase("cs")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(0);
            }
            if (str.equalsIgnoreCase("de")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(1);
            }
            if (str.equalsIgnoreCase("en")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(2);
            }
            if (str.equalsIgnoreCase("es")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(3);
            }
            if (str.equalsIgnoreCase("fr")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(4);
            }
            if (str.equalsIgnoreCase("in")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(5);
            }
            if (str.equalsIgnoreCase("it")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(6);
            }
            if (str.equalsIgnoreCase("pl")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(7);
            }
            if (str.equalsIgnoreCase("pt")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(8);
            }
            if (str.equalsIgnoreCase("ru")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(9);
            }
            if (str.equalsIgnoreCase("tr")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(10);
            }
            if (str.equalsIgnoreCase("vi")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(11);
            }
            if (str.equalsIgnoreCase("ar")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(12);
            }
            if (str.equalsIgnoreCase("th")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(13);
            }
            if (str.equalsIgnoreCase("bn")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(14);
            }
            if (str.equalsIgnoreCase("hi")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(15);
            }
            if (str.equalsIgnoreCase("ta")) {
                SharePreferenceUtils.getInstance(context).saveLanguageIndex(16);
            }
        }
        Locale locale = new Locale(str);
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static int getHeightStatusBar(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void setStatusBarHomeTransparent(FragmentActivity fragmentActivity) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = fragmentActivity.getWindow();
            window.setNavigationBarColor(0);
            window.getDecorView().setSystemUiVisibility(SimpleResolver.DEFAULT_EDNS_PAYLOADSIZE);
            setWindowFlag((AppCompatActivity) fragmentActivity, 67108864, false);
            window.setStatusBarColor(0);
        }
    }

    public static void setWindowFlag(AppCompatActivity appCompatActivity, int i, boolean z) {
        Window window = appCompatActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags = i | attributes.flags;
        } else {
            attributes.flags = (~i) & attributes.flags;
        }
        window.setAttributes(attributes);
    }
}
