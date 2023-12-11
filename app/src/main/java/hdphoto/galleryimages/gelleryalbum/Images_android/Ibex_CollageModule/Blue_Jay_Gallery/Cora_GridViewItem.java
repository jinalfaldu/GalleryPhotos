package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import android.app.Activity;
import android.graphics.Bitmap;


public class Cora_GridViewItem {
    Activity context;
    String count;
    String folderName;
    long imageIdForThumb;
    private boolean isDirectory;
    int orientation;

    public Cora_GridViewItem(Activity activity, String str, String str2, boolean z, long j, int i) {
        this.folderName = str;
        this.isDirectory = z;
        this.count = str2;
        this.context = activity;
        this.imageIdForThumb = j;
        this.orientation = i;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public boolean isDirectory() {
        return this.isDirectory;
    }

    public Bitmap getImage() {
        return Jose_GalleryUtility.GetThumbBitmap(this.context, this.imageIdForThumb, this.orientation);
    }
}
