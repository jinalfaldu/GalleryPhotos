package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.stickers;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerPack;
import java.util.List;


public interface StickersDataContract {


    public interface Presenter {
        void loadStickers();

        void onDestroy();
    }


    public interface View {
        void hideProgress();

        void showError(String str);

        void showProgress();

        void showStickers(List<Ledl_StickerPack> list);
    }
}
