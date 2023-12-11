package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.content.Context;
import android.graphics.Typeface;
import java.util.Hashtable;


public class CtFontCache {
    static Hashtable<String, Typeface> cacheFont = new Hashtable<>();

    public static Typeface getTypeFace(Context context, String str) {
        synchronized (cacheFont) {
            if (str != null) {
                if (str.length() != 0 && str.compareTo("") != 0) {
                    if (!cacheFont.containsKey(str)) {
                        cacheFont.put(str, Typeface.createFromAsset(context.getAssets(), str));
                    }
                    return cacheFont.get(str);
                }
            }
            return null;
        }
    }
}
