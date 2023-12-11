package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout;

import java.io.Serializable;
import java.util.ArrayList;


public class Ledl_StickerPack implements Serializable {
    String iconUrl;
    String packName;
    ArrayList<Ledl_StickerItemModel> stickerItemModelArrayList;

    public String getPackName() {
        return this.packName;
    }

    public void setPackName(String str) {
        this.packName = str;
    }

    public ArrayList<Ledl_StickerItemModel> getStickerItemModelArrayList() {
        return this.stickerItemModelArrayList;
    }

    public void setStickerItemModelArrayList(ArrayList<Ledl_StickerItemModel> arrayList) {
        this.stickerItemModelArrayList = arrayList;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }
}
