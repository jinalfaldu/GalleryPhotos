package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange;

import android.view.View;


public class ZoomInTransformer extends Sloane_BaseTransformer {
    @Override
    public void onTransform(View view, float f) {
        float f2 = 0.0f;
        float abs = f < 0.0f ? f + 1.0f : Math.abs(1.0f - f);
        view.setScaleX(abs);
        view.setScaleY(abs);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        if (f >= -1.0f && f <= 1.0f) {
            f2 = 1.0f - (abs - 1.0f);
        }
        view.setAlpha(f2);
    }
}
