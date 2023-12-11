package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;


public class Aspid_ShapeLayout {
    boolean isScalable = false;
    int porterDuffClearBorderIndex = -1;
    Grapes_Shape[] shapeArr;

    public Aspid_ShapeLayout() {
    }

    public Aspid_ShapeLayout(Grapes_Shape[] cMShapeArr) {
        this.shapeArr = cMShapeArr;
    }

    public void setClearIndex(int i) {
        if (i < 0 || i >= this.shapeArr.length) {
            return;
        }
        this.porterDuffClearBorderIndex = i;
    }

    public void setScalibility(boolean z) {
        this.isScalable = z;
    }

    public int getClearIndex() {
        return this.porterDuffClearBorderIndex;
    }

    public boolean getScalibility() {
        return this.isScalable;
    }
}
