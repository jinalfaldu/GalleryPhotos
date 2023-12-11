package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;


public abstract class Sticker {
    private Path boundPath;
    private float[] dst;
    private boolean init;
    private Matrix matrix;
    private float minStickerSize;
    private float[] rotateSrcPts;
    private float[] srcPts;

    public abstract void draw(Canvas canvas);

    public abstract int getHeight();

    public abstract int getWidth();

    public void init(int i, int i2) {
        this.matrix = new Matrix();
        float f = i;
        float f2 = i2;
        this.srcPts = new float[]{0.0f, 0.0f, f, 0.0f, f, f2, 0.0f, f2};
        this.rotateSrcPts = new float[]{i / 2, i2 / 2, f, f2};
        this.dst = new float[8];
        this.boundPath = new Path();
    }

    public float getMinStickerSize() {
        return this.minStickerSize;
    }

    public void setMinStickerSize(float f) {
        this.minStickerSize = f;
    }

    boolean isInit() {
        return this.init;
    }

    void setInit(boolean z) {
        this.init = z;
    }

    Matrix getMatrix() {
        return this.matrix;
    }

    float getBitmapScale() {
        return getWidth() / getHeight();
    }

    float[] getDst() {
        return this.dst;
    }

    float[] getRotateSrcPts() {
        return this.rotateSrcPts;
    }

    void converse() {
        this.matrix.mapPoints(this.dst, this.srcPts);
    }

   
    public Path getBoundPath() {
        this.boundPath.reset();
        Path path = this.boundPath;
        float[] fArr = this.dst;
        path.moveTo(fArr[0], fArr[1]);
        Path path2 = this.boundPath;
        float[] fArr2 = this.dst;
        path2.lineTo(fArr2[2], fArr2[3]);
        Path path3 = this.boundPath;
        float[] fArr3 = this.dst;
        path3.lineTo(fArr3[4], fArr3[5]);
        Path path4 = this.boundPath;
        float[] fArr4 = this.dst;
        path4.lineTo(fArr4[6], fArr4[7]);
        Path path5 = this.boundPath;
        float[] fArr5 = this.dst;
        path5.lineTo(fArr5[0], fArr5[1]);
        return this.boundPath;
    }
}
