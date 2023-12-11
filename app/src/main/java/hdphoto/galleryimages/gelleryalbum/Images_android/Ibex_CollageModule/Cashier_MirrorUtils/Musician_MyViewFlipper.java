package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;


public class Musician_MyViewFlipper extends ViewFlipper {
    public Musician_MyViewFlipper(Context context) {
        super(context);
    }

    public Musician_MyViewFlipper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException unused) {
            stopFlipping();
        }
    }
}
