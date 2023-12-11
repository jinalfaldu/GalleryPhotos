package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class Musician_PicassoGenericTarget implements Target {
    @Override // com.squareup.picasso.Target
    public void onBitmapFailed(Exception exc, Drawable drawable) {
    }

    @Override // com.squareup.picasso.Target
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
    }

    @Override // com.squareup.picasso.Target
    public void onPrepareLoad(Drawable drawable) {
    }
}
