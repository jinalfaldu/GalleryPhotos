package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange;

import android.view.View;


public class Ak_CubeInTransformer extends Sloane_BaseTransformer {
    @Override
    public boolean isPagingEnabled() {
        return true;
    }

    @Override
    public void onTransform(View view, float f) {
        view.setPivotX(f > 0.0f ? 0.0f : view.getWidth());
        view.setPivotY(0.0f);
        view.setRotationY(f * (-90.0f));
    }
}
