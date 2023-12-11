package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.provider.MediaStore;


public class Jose_GalleryUtility {
    public static Bitmap GetThumbBitmap(Context context, long j, int i) {
        Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), j, 1, null);
        if (thumbnail == null) {
            return null;
        }
        Bitmap RotateImage = RotateImage(thumbnail, i);
        if (RotateImage == null) {
            return thumbnail;
        }
        if (RotateImage == thumbnail) {
            return RotateImage;
        }
        thumbnail.recycle();
        return RotateImage;
    }

    private static Bitmap RotateImage(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        if (i == 90) {
            matrix.postRotate(90.0f);
        } else if (i == 180) {
            matrix.postRotate(180.0f);
        } else if (i == 270) {
            matrix.postRotate(270.0f);
        }
        if (i == 0) {
            return null;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
