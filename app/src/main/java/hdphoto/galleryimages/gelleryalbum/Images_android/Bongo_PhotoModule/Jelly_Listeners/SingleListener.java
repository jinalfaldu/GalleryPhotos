package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners;

import android.view.View;


public interface SingleListener<T, V, P> {
    void onSingleCallback(T t, V v, P p, View view);
}
