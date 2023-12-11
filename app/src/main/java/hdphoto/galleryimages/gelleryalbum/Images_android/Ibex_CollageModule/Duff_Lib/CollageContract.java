package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.PatternsResponse;
import java.util.List;


public interface CollageContract {

    
    public interface Presenter {
        void loadPatterns();

        void onDestroy();
    }

    
    public interface View {
        void setPatterns(List<PatternsResponse.PatternsPack> list);
    }
}
