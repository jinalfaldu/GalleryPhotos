package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.SlideChange;

import android.view.View;


public class StackTransformer extends Sloane_BaseTransformer {
    @Override
    public void onTransform(View view, float f) {
        view.setTranslationX(f >= 0.0f ? (-view.getWidth()) * f : 0.0f);
    }
}
