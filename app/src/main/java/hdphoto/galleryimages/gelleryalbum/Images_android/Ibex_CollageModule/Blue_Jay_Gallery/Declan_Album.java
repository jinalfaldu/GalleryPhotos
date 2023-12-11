package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import java.util.ArrayList;
import java.util.List;


public class Declan_Album {
    int ID;
    long imageIdForThumb;
    String name;
    @Deprecated
    List<Cora_GridViewItem> gridItems = new ArrayList();
    List<Long> imageIdList = new ArrayList();
    List<Integer> orientationList = new ArrayList();
    List<SubImage> subImages = new ArrayList();

    
    public static class SubImage {
        public final long id;
        public final String imagePath;
        public final int orientation;

        public SubImage(long j, int i, String str) {
            this.id = j;
            this.orientation = i;
            this.imagePath = str;
        }
    }
}
