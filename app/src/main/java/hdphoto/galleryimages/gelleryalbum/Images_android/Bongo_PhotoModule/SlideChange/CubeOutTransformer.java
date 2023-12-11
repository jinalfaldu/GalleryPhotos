package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange;

import android.view.View;


public class CubeOutTransformer extends Sloane_BaseTransformer {
    @Override
    public boolean isPagingEnabled() {
        return true;
    }

    @Override
    public void onTransform(View view, float f) {
        view.setPivotX(f < 0.0f ? view.getWidth() : 0.0f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(f * 90.0f);
    }
}
