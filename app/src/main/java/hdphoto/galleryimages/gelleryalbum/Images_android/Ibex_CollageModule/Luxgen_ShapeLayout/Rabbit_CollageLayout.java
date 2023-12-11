package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Luxgen_ShapeLayout;

import java.util.ArrayList;
import java.util.List;


public class Rabbit_CollageLayout {
    public List shapeList;
    int[][] exceptionIndexForShapes = null;
    public List<Spyker_MaskPair> maskPairList = new ArrayList();
    List<Spyker_MaskPairSvg> maskPairListSvg = new ArrayList();
    int porterDuffClearBorderIntex = -1;
    boolean useLine = true;

    public Rabbit_CollageLayout() {
    }

    public Rabbit_CollageLayout(List list) {
        this.shapeList = list;
    }

    public int getClearIndex() {
        return this.porterDuffClearBorderIntex;
    }

    public int[] getexceptionIndex(int i) {
        int[][] iArr = this.exceptionIndexForShapes;
        if (iArr == null || i >= iArr.length || i < 0) {
            return null;
        }
        return iArr[i];
    }

    public void setClearIndex(int i) {
        if (i < 0 || i >= this.shapeList.size()) {
            return;
        }
        this.porterDuffClearBorderIntex = i;
    }
}
