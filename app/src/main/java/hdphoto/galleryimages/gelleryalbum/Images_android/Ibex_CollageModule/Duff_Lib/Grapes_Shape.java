package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Luxgen_ShapeLayout.Spyker_CollageShapeUtils;


public class Grapes_Shape {
    static final int[] scrapBookRotation = {13, -13, -7, -12, 11, 8, -9, 10, 9};
    public final int SHAPE_MODE_MASK;
    public final int SHAPE_MODE_POINT;
    public final int SHAPE_MODE_RECT;
    String TAG;
    Bitmap bitmap;
    int bitmapHeight;
    Matrix bitmapMatrix;
    RectF bitmapRect;
    int bitmapWidth;
    Paint borderPaint;
    int borderStrokeWidth;
    RectF bounds;
    Bitmap btmDelete;
    Bitmap btmScale;
    PointF centerOriginal;
    Paint dashPaint;
    Path dashPathHorizontal;
    Path dashPathVertical;
    int delW;
    float deleteWidthHalf;
    RectF drawShapeRect;
    float dx;
    float dy;
    int[] exceptionIndex;
    float[] f506f;
    float[] f507p;
    Matrix inverse;
    boolean isScrapBook;
    Bitmap maskBitmap;
    Matrix maskMatrix;
    Paint maskPaint;
    float maxScale;
    float minScale;
    NinePatchDrawable npd;
    int npdPadding;
    int offsetX;
    int offsetY;
    RectF originalBounds;
    Path originalPath;
    private Paint paintPath;
    Paint paintScrap;
    private Paint paintTransparent;
    Paint paintXFerMode;
    Path path;
    Matrix pathMatrix;
    PointF[] points;
    float[] pts;
    private String r0;
    Region region;
    Matrix removeBitmapMatrix;
    Matrix scaleBitmapMatrix;
    float scaleDown;
    float scaleUp;
    float scrapBookPadding;
    int screenWidth;
    int shapeMode;
    RectF sourceRect;
    final float tempRadius;
    RectF tempRect;
    final float tempScrapBookPadding;
    float tempTouchStrokeWidth;
    Paint touchPaint;
    RectF touchRect;
    float touchStrokeWidth;
    Matrix transparentMaskMatrix;
    float[] values;

    public float checkRange(float f, float f2, float f3) {
        return f > f3 ? f - f2 : f < f3 ? f + f2 : f;
    }

    public float sqr(float f) {
        return f * f;
    }

    public Grapes_Shape(PointF[] pointFArr, Bitmap bitmap, int[] iArr, int i, int i2, boolean z, int i3, boolean z2, Bitmap bitmap2, Bitmap bitmap3, int i4) {
        this.TAG = "Shape";
        this.offsetY = 0;
        this.offsetX = 0;
        this.SHAPE_MODE_POINT = 1;
        this.SHAPE_MODE_RECT = 2;
        this.SHAPE_MODE_MASK = 3;
        this.maskBitmap = null;
        this.maskMatrix = new Matrix();
        this.transparentMaskMatrix = new Matrix();
        this.tempRect = new RectF();
        this.drawShapeRect = new RectF();
        this.minScale = 1.0f;
        this.maxScale = 1.0f;
        this.bitmapRect = new RectF();
        this.f507p = new float[2];
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.scaleDown = 0.95f;
        this.scaleUp = 1.05f;
        this.f506f = new float[2];
        this.centerOriginal = new PointF();
        this.touchPaint = new Paint(1);
        this.borderPaint = new Paint(1);
        this.paintScrap = new Paint(2);
        this.pts = new float[2];
        this.inverse = new Matrix();
        this.tempScrapBookPadding = 25.0f;
        this.scrapBookPadding = 25.0f;
        this.tempTouchStrokeWidth = 8.0f;
        this.touchStrokeWidth = 8.0f;
        this.values = new float[9];
        this.tempRadius = 60.0f;
        this.borderStrokeWidth = 6;
        this.dashPaint = new Paint();
        this.delW = 0;
        this.deleteWidthHalf = 0.0f;
        this.npdPadding = 16;
        this.points = pointFArr;
        this.offsetX = i;
        this.offsetY = i2;
        this.btmDelete = bitmap2;
        this.btmScale = bitmap3;
        this.screenWidth = i4;
        this.isScrapBook = z;
        createPathFromPoints();
        this.path.offset(i, i2);
        this.exceptionIndex = iArr;
        this.bitmap = bitmap;
        this.bitmapWidth = bitmap.getWidth();
        this.bitmapHeight = this.bitmap.getHeight();
        this.shapeMode = 1;
        ShapeBind(z, i3, false, 0, 0);
    }

    public Grapes_Shape(PointF[] pointFArr, Bitmap bitmap, int[] iArr, int i, int i2, Bitmap bitmap2, boolean z, int i3, boolean z2, Bitmap bitmap3, Bitmap bitmap4, int i4) {
        this.TAG = "Shape";
        this.offsetY = 0;
        this.offsetX = 0;
        this.SHAPE_MODE_POINT = 1;
        this.SHAPE_MODE_RECT = 2;
        this.SHAPE_MODE_MASK = 3;
        this.maskBitmap = null;
        this.maskMatrix = new Matrix();
        this.transparentMaskMatrix = new Matrix();
        this.tempRect = new RectF();
        this.drawShapeRect = new RectF();
        this.minScale = 1.0f;
        this.maxScale = 1.0f;
        this.bitmapRect = new RectF();
        this.f507p = new float[2];
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.scaleDown = 0.95f;
        this.scaleUp = 1.05f;
        this.f506f = new float[2];
        this.centerOriginal = new PointF();
        this.touchPaint = new Paint(1);
        this.borderPaint = new Paint(1);
        this.paintScrap = new Paint(2);
        this.pts = new float[2];
        this.inverse = new Matrix();
        this.tempScrapBookPadding = 25.0f;
        this.scrapBookPadding = 25.0f;
        this.tempTouchStrokeWidth = 8.0f;
        this.touchStrokeWidth = 8.0f;
        this.values = new float[9];
        this.tempRadius = 60.0f;
        this.borderStrokeWidth = 6;
        this.dashPaint = new Paint();
        this.delW = 0;
        this.deleteWidthHalf = 0.0f;
        this.npdPadding = 16;
        this.maskBitmap = bitmap2;
        this.points = pointFArr;
        this.offsetX = i;
        this.offsetY = i2;
        this.btmDelete = bitmap3;
        this.btmScale = bitmap4;
        this.screenWidth = i4;
        this.isScrapBook = z;
        createPathFromPoints();
        this.path.offset(i, i2);
        this.exceptionIndex = iArr;
        this.bitmap = bitmap;
        this.bitmapWidth = bitmap.getWidth();
        this.bitmapHeight = this.bitmap.getHeight();
        this.shapeMode = 3;
        ShapeBind(z, i3, false, 0, 0);
    }

    public void changeRatio(PointF[] pointFArr, int[] iArr, int i, int i2, boolean z, int i3, int i4, int i5) {
        this.points = pointFArr;
        this.offsetX = i;
        this.offsetY = i2;
        createPathFromPoints();
        this.path.offset(i, i2);
        this.exceptionIndex = iArr;
        ShapeBind(z, i3, true, i4, i5);
    }

    public void freeBitmaps() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        Bitmap bitmap2 = this.maskBitmap;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            return;
        }
        this.maskBitmap = null;
    }

    public void setRadius(CornerPathEffect cornerPathEffect) {
        this.paintPath.setPathEffect(cornerPathEffect);
        this.paintTransparent.setPathEffect(cornerPathEffect);
    }

    public float smallestDistance() {
        float f = 1500.0f;
        for (int i = 0; i < this.points.length; i++) {
            int i2 = 0;
            while (true) {
                PointF[] pointFArr = this.points;
                if (i2 >= pointFArr.length) {
                    break;
                }
                if (i != i2) {
                    float abs = Math.abs(pointFArr[i].x - this.points[i2].x) + Math.abs(this.points[i].y - this.points[i2].y);
                    if (abs < f) {
                        f = abs;
                    }
                }
                i2++;
            }
        }
        return f;
    }

    public void ShapeBind(boolean z, int i, boolean z2, int i2, int i3) {
        this.bounds = new RectF();
        this.originalPath = new Path(this.path);
        this.path.computeBounds(this.bounds, true);
        this.originalBounds = new RectF(this.bounds);
        Paint paint = new Paint(1);
        this.paintXFerMode = paint;
        paint.setFilterBitmap(true);
        this.paintXFerMode.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Paint paint2 = new Paint(1);
        this.paintPath = paint2;
        paint2.setFilterBitmap(true);
        Paint paint3 = new Paint(1);
        this.maskPaint = paint3;
        paint3.setFilterBitmap(true);
        Paint paint4 = new Paint(1);
        this.paintTransparent = paint4;
        paint4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.paintTransparent.setFilterBitmap(true);
        if (z) {
            setScrapBookBitmapPosition(i, z2, i2, i3);
        } else {
            setBitmapPosition();
        }
        this.paintPath.setPathEffect(new CornerPathEffect(3.0f));
        this.pathMatrix = new Matrix();
        Region region = new Region();
        this.region = region;
        region.setPath(this.path, new Region((int) this.bounds.left, (int) this.bounds.top, (int) this.bounds.right, (int) this.bounds.bottom));
        if (z) {
            this.dashPaint.setColor(7829368);
            this.dashPaint.setStyle(Paint.Style.STROKE);
            float f = this.screenWidth / 120.0f;
            if (f <= 0.0f) {
                f = 5.0f;
            }
            this.dashPaint.setStrokeWidth(f);
            this.dashPaint.setPathEffect(new DashPathEffect(new float[]{f, f}, 0.0f));
            Path path = new Path();
            this.dashPathVertical = path;
            path.moveTo(this.bitmapWidth / 2, (-this.bitmapHeight) / 5);
            this.dashPathVertical.lineTo(this.bitmapWidth / 2, (this.bitmapHeight * 6) / 5);
            Path path2 = new Path();
            this.dashPathHorizontal = path2;
            path2.moveTo((-this.bitmapWidth) / 5, this.bitmapHeight / 2);
            this.dashPathHorizontal.lineTo((this.bitmapWidth * 6) / 5, this.bitmapHeight / 2);
        }
    }

    public void setBitmap(Bitmap bitmap, boolean z) {
        this.bitmap = bitmap;
        this.bitmapWidth = bitmap.getWidth();
        this.bitmapHeight = bitmap.getHeight();
        if (z) {
            return;
        }
        setBitmapPosition();
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Bitmap getMaskBitmap() {
        return this.maskBitmap;
    }

    private void setBitmapPosition() {
        float bitmapScale = getBitmapScale();
        float height = this.bounds.top - (((this.bitmapHeight * bitmapScale) - this.bounds.height()) / 2.0f);
        Matrix matrix = new Matrix();
        this.bitmapMatrix = matrix;
        matrix.reset();
        this.bitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.bitmapMatrix.postTranslate(this.bounds.left - (((this.bitmapWidth * bitmapScale) - this.bounds.width()) / 2.0f), height);
        if (this.shapeMode == 3) {
            setMaskBitmapPositions();
        }
        setMaxMinScales(bitmapScale);
    }

    private float getBitmapScale() {
        float width = this.bounds.width() / this.bitmapWidth;
        float height = this.bounds.height() / this.bitmapHeight;
        return width < height ? height : width;
    }

    public void setMaxMinScales(float f) {
        boolean z = this.isScrapBook;
        if (z) {
            this.minScale = f / 2.0f;
        } else {
            this.minScale = f;
        }
        if (z) {
            this.maxScale = f * 2.0f;
        } else {
            this.maxScale = f * 4.0f;
        }
    }

    public void setMinScales(float f) {
        if (this.isScrapBook) {
            this.minScale = f / 2.0f;
        } else {
            this.minScale = f;
        }
    }

    private void setMaskBitmapPositions() {
        Bitmap bitmap = this.maskBitmap;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = this.maskBitmap.getHeight();
            float f = width;
            float width2 = this.bounds.width() / f;
            float f2 = height;
            float height2 = this.bounds.height() / f2;
            if (width2 > height2) {
                width2 = height2;
            }
            float height3 = this.bounds.top - (((f2 * width2) - this.bounds.height()) / 2.0f);
            float width3 = this.bounds.left - (((f * width2) - this.bounds.width()) / 2.0f);
            Matrix matrix = new Matrix();
            this.maskMatrix = matrix;
            matrix.reset();
            this.maskMatrix.postScale(width2, width2);
            this.maskMatrix.postTranslate(width3, height3);
            float width4 = this.originalBounds.width() / f;
            float height4 = this.originalBounds.height() / f2;
            if (width4 > height4) {
                width4 = height4;
            }
            float height5 = this.originalBounds.top - (((f2 * width4) - this.originalBounds.height()) / 2.0f);
            float width5 = this.originalBounds.left - (((f * width4) - this.originalBounds.width()) / 2.0f);
            Matrix matrix2 = new Matrix();
            this.transparentMaskMatrix = matrix2;
            matrix2.reset();
            this.transparentMaskMatrix.postScale(width4, width4);
            this.transparentMaskMatrix.postTranslate(width5, height5);
        }
    }

    public void scalePath(float f, float f2, float f3) {
        int i = this.shapeMode;
        if (i == 1) {
            pathTransform(this.points, this.path, f, this.originalBounds.centerX(), this.originalBounds.centerY());
        } else if (i == 2) {
            pathTransformFromRect(f);
        } else {
            float f4 = f * 2.0f;
            this.pathMatrix.reset();
            this.pathMatrix.setScale((f2 - f4) / f2, (f3 - f4) / f3, this.originalBounds.centerX(), this.originalBounds.centerY());
            this.originalPath.transform(this.pathMatrix, this.path);
        }
        this.path.computeBounds(this.bounds, true);
        if (this.shapeMode == 3) {
            setMaskBitmapPositions();
        }
    }

    public void createPathFromPoints() {
        Path path = new Path();
        this.path = path;
        path.setFillType(Path.FillType.EVEN_ODD);
        this.path.moveTo(this.points[0].x, this.points[0].y);
        int i = 1;
        while (true) {
            PointF[] pointFArr = this.points;
            if (i < pointFArr.length) {
                this.path.lineTo(pointFArr[i].x, this.points[i].y);
                i++;
            } else {
                this.path.lineTo(pointFArr[0].x, this.points[0].y);
                this.path.close();
                return;
            }
        }
    }

    public void pathTransform(PointF[] pointFArr, Path path, float f, float f2, float f3) {
        float f4 = f2 - this.offsetX;
        float f5 = f3 - this.offsetY;
        path.rewind();
        path.setFillType(Path.FillType.EVEN_ODD);
        int length = pointFArr.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = f;
        }
        if (this.exceptionIndex != null) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.exceptionIndex;
                if (i2 >= iArr.length) {
                    break;
                }
                fArr[iArr[i2]] = 2.0f * f;
                i2++;
            }
        }
        path.moveTo(checkRange(pointFArr[0].x, fArr[0], f4), checkRange(pointFArr[0].y, f, f5));
        for (int i3 = 1; i3 < length; i3++) {
            path.lineTo(checkRange(pointFArr[i3].x, fArr[i3], f4), checkRange(pointFArr[i3].y, f, f5));
        }
        path.lineTo(checkRange(pointFArr[0].x, fArr[0], f4), checkRange(pointFArr[0].y, f, f5));
        path.close();
        path.offset(this.offsetX, this.offsetY);
    }

    public void pathTransformFromRect(float f) {
        this.tempRect.set(this.sourceRect.left + f, this.sourceRect.top + f, this.sourceRect.right - f, this.sourceRect.bottom - f);
        this.path.rewind();
        this.path.addRect(this.tempRect, Path.Direction.CCW);
    }

    public void drawShape(Canvas canvas, int i, int i2, int i3, boolean z) {
        if (z) {
            if (this.shapeMode != 3) {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            } else {
                Bitmap bitmap = this.maskBitmap;
                if (bitmap != null && !bitmap.isRecycled()) {
                    canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
                }
            }
            canvas.restoreToCount(i3);
        }
        this.drawShapeRect.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
        this.bitmapMatrix.mapRect(this.drawShapeRect);
        int saveLayer = canvas.saveLayer(this.drawShapeRect, null, 31);
        if (this.shapeMode != 3) {
            canvas.drawPath(this.path, this.paintPath);
        } else {
            Bitmap bitmap2 = this.maskBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.maskPaint);
            }
        }
        canvas.drawBitmap(this.bitmap, this.bitmapMatrix, this.paintXFerMode);
        canvas.restoreToCount(saveLayer);
    }

    public void drawShapeForSave(Canvas canvas, int i, int i2, int i3, boolean z) {
        if (z) {
            if (this.shapeMode != 3) {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            } else {
                Bitmap bitmap = this.maskBitmap;
                if (bitmap != null && !bitmap.isRecycled()) {
                    canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
                }
            }
            canvas.restoreToCount(i3);
        }
        RectF rectF = new RectF(0.0f, 0.0f, this.bitmapWidth + 0, this.bitmapHeight + 0);
        this.bitmapMatrix.mapRect(rectF);
        int saveLayer = canvas.saveLayer(rectF, null, 31);
        if (this.shapeMode != 3) {
            canvas.drawPath(this.path, this.paintPath);
        } else {
            Bitmap bitmap2 = this.maskBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.maskPaint);
            }
        }
        canvas.drawBitmap(this.bitmap, this.bitmapMatrix, this.paintXFerMode);
        canvas.restoreToCount(saveLayer);
    }

    public void bitmapMatrixScale(float f, float f2, float f3, float f4) {
        this.bitmapMatrix.postScale(f, f2, f3, f4);
        checkScaleBoundries();
    }

    public void bitmapMatrixScaleScrapBook(float f, float f2) {
        float[] fArr = this.f507p;
        fArr[0] = this.bitmapWidth / 2;
        fArr[1] = this.bitmapHeight / 2;
        this.bitmapMatrix.mapPoints(fArr);
        Matrix matrix = this.bitmapMatrix;
        float[] fArr2 = this.f507p;
        matrix.postScale(f, f2, fArr2[0], fArr2[1]);
        checkScaleBoundries();
    }

    public void checkScaleBoundries() {
        float scale = getScale();
        float f = this.minScale;
        if (scale < f) {
            Matrix matrix = this.bitmapMatrix;
            float scale2 = f / getScale();
            float scale3 = this.minScale / getScale();
            float[] fArr = this.f507p;
            matrix.postScale(scale2, scale3, fArr[0], fArr[1]);
        }
        float scale4 = getScale();
        float f2 = this.maxScale;
        if (scale4 > f2) {
            float scale5 = f2 / getScale();
            float scale6 = this.maxScale / getScale();
            Matrix matrix2 = this.bitmapMatrix;
            float[] fArr2 = this.f507p;
            matrix2.postScale(scale5, scale6, fArr2[0], fArr2[1]);
        }
    }

    public void bitmapMatrixTranslate(float f, float f2) {
        this.bitmapMatrix.postTranslate(f, f2);
        if (this.isScrapBook) {
            return;
        }
        checkBoundaries();
    }

    public void checkBoundaries() {
        this.bitmapRect.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
        this.bitmapMatrix.mapRect(this.bitmapRect);
        float f = this.bitmapRect.left > this.bounds.left ? this.bounds.left - this.bitmapRect.left : 0.0f;
        float f2 = this.bitmapRect.top > this.bounds.top ? this.bounds.top - this.bitmapRect.top : 0.0f;
        if (this.bitmapRect.right < this.bounds.right) {
            f = this.bounds.right - this.bitmapRect.right;
        }
        if (this.bitmapRect.bottom < this.bounds.bottom) {
            f2 = this.bounds.bottom - this.bitmapRect.bottom;
        }
        this.bitmapMatrix.postTranslate(f, f2);
    }

    public void checkScaleBounds() {
        setMinScales(getBitmapScale());
        checkScaleBoundries();
    }

    public void bitmapMatrixgGetValues(float[] fArr) {
        this.bitmapMatrix.getValues(fArr);
    }

    public void bitmapMatrixRotate(float f) {
        float[] fArr = this.f507p;
        fArr[0] = this.bitmapWidth / 2;
        fArr[1] = this.bitmapHeight / 2;
        this.bitmapMatrix.mapPoints(fArr);
        Matrix matrix = this.bitmapMatrix;
        float[] fArr2 = this.f507p;
        matrix.postRotate(f, fArr2[0], fArr2[1]);
    }

    public int setScaleMatrix(int i) {
        if (this.dx <= 0.5f) {
            this.dx = this.bitmapWidth / 100.0f;
        }
        if (this.dy <= 0.5f) {
            this.dy = this.bitmapHeight / 100.0f;
        }
        PointF centerOfImage = getCenterOfImage();
        if (i == 0) {
            setMatrixFit();
        } else if (i == 1) {
            setBitmapPosition();
        } else if (i == 3) {
            this.bitmapMatrix.postRotate(-90.0f, centerOfImage.x, centerOfImage.y);
        } else if (i == 2) {
            this.bitmapMatrix.postRotate(90.0f, centerOfImage.x, centerOfImage.y);
        } else if (i == 4) {
            this.bitmapMatrix.postScale(-1.0f, 1.0f, centerOfImage.x, centerOfImage.y);
        } else if (i == 5) {
            this.bitmapMatrix.postScale(1.0f, -1.0f, centerOfImage.x, centerOfImage.y);
        } else if (i == 6) {
            this.bitmapMatrix.postRotate(-10.0f, centerOfImage.x, centerOfImage.y);
        } else if (i == 7) {
            this.bitmapMatrix.postRotate(10.0f, centerOfImage.x, centerOfImage.y);
        } else if (i == 8) {
            if (getScale() >= this.maxScale) {
                return 1;
            }
            Matrix matrix = this.bitmapMatrix;
            float f = this.scaleUp;
            matrix.postScale(f, f, centerOfImage.x, centerOfImage.y);
        } else if (i == 9) {
            if (getScale() <= this.minScale) {
                return 2;
            }
            Matrix matrix2 = this.bitmapMatrix;
            float f2 = this.scaleDown;
            matrix2.postScale(f2, f2, centerOfImage.x, centerOfImage.y);
        } else if (i == 10) {
            this.bitmapRect.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.right <= this.bounds.right && !this.isScrapBook) {
                return 3;
            }
            this.bitmapMatrix.postTranslate(-this.dx, 0.0f);
        } else if (i == 11) {
            this.bitmapRect.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.left >= this.bounds.left && !this.isScrapBook) {
                return 4;
            }
            this.bitmapMatrix.postTranslate(this.dx, 0.0f);
        } else if (i == 12) {
            this.bitmapRect.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.bottom <= this.bounds.bottom && !this.isScrapBook) {
                return 5;
            }
            this.bitmapMatrix.postTranslate(0.0f, -this.dy);
        } else if (i == 13) {
            this.bitmapRect.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.top >= this.bounds.top && !this.isScrapBook) {
                return 6;
            }
            this.bitmapMatrix.postTranslate(0.0f, this.dy);
        }
        checkScaleBoundries();
        if (this.isScrapBook) {
            return 0;
        }
        checkBoundaries();
        return 0;
    }

    public PointF getCenterOfImage() {
        if (this.centerOriginal == null) {
            this.centerOriginal = new PointF();
        }
        if (this.f506f == null) {
            this.f506f = new float[2];
        }
        float[] fArr = this.f506f;
        fArr[0] = this.bitmapWidth / 2.0f;
        fArr[1] = this.bitmapHeight / 2.0f;
        this.bitmapMatrix.mapPoints(fArr);
        PointF pointF = this.centerOriginal;
        float[] fArr2 = this.f506f;
        pointF.set(fArr2[0], fArr2[1]);
        return this.centerOriginal;
    }

    public void setMatrixFit() {
        float min = Math.min(this.bounds.width() / this.bitmapWidth, this.bounds.height() / this.bitmapHeight);
        if (this.isScrapBook) {
            min *= Spyker_CollageShapeUtils.scrapBookShapeScale;
        }
        String str = this.TAG;
        Log.e(str, "Collage.scrapBookShapeScale " + Spyker_CollageShapeUtils.scrapBookShapeScale);
        float height = this.bounds.top + ((this.bounds.height() - (((float) this.bitmapHeight) * min)) / 2.0f);
        this.bitmapMatrix.reset();
        this.bitmapMatrix.postScale(min, min);
        this.bitmapMatrix.postTranslate(this.bounds.left + ((this.bounds.width() - (this.bitmapWidth * min)) / 2.0f), height);
    }

    private void setScrapBookBitmapPosition(int i, boolean z, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        if (z) {
            float f = this.bitmapWidth;
            float f2 = this.bitmapHeight;
            int i7 = 0;
            float[] fArr = {0.0f, 0.0f, f, 0.0f, f, f2, 0.0f, f2};
            this.bitmapMatrix.mapPoints(fArr);
            RectF rectF = new RectF(this.offsetX, this.offsetY, i4 + i2, i5 + i3);
            if (rectF.contains(fArr[0], fArr[1]) || rectF.contains(fArr[2], fArr[3]) || rectF.contains(fArr[4], fArr[5]) || rectF.contains(fArr[6], fArr[7])) {
                return;
            }
            PointF pointF = new PointF(this.offsetX, this.offsetY);
            PointF pointF2 = new PointF(this.offsetX + i2, this.offsetY);
            PointF pointF3 = new PointF();
            if (fArr[1] < this.offsetY) {
                pointF3.set(fArr[0], fArr[1]);
                Log.e(this.TAG, "0  " + DistToSegment(pointF3, pointF, pointF2));
                pointF3.set(fArr[2], fArr[3]);
                Log.e(this.TAG, "1  " + DistToSegment(pointF3, pointF, pointF2));
                pointF3.set(fArr[4], fArr[5]);
                Log.e(this.TAG, "2  " + DistToSegment(pointF3, pointF, pointF2));
                pointF3.set(fArr[6], fArr[7]);
                float[] fArr2 = {PointToLineDistance(pointF, pointF2, pointF3), PointToLineDistance(pointF, pointF2, pointF3), PointToLineDistance(pointF, pointF2, pointF3), PointToLineDistance(pointF, pointF2, pointF3)};
                Log.e(this.TAG, "3  " + DistToSegment(pointF3, pointF, pointF2));
                float f3 = fArr2[0];
                for (int i8 = 1; i8 < 4; i8++) {
                    if (fArr2[i8] < f3) {
                        f3 = fArr2[i8];
                        i7 = i8;
                    }
                    Log.e(this.TAG, "fi  " + fArr2[i8]);
                }
                this.bitmapMatrix.postTranslate(0.0f, (this.offsetY + 120) - fArr[(i7 * 2) + 1]);
                return;
            }
            PointF pointF4 = new PointF(this.offsetX, i6 + i3);
            PointF pointF5 = new PointF(this.offsetX + i2, this.offsetY + i3);
            pointF3.set(fArr[0], fArr[1]);
            float f4 = pointF4.x;
            Log.e(this.TAG, "A  x " + this.r0 + " y " + pointF4.y);
            float f5 = pointF5.x;
            Log.e(this.TAG, "B  x " + this.r0 + " y " + pointF5.y);
            StringBuilder sb = new StringBuilder();
            sb.append("0  ");
            sb.append(DistToSegment(pointF3, pointF4, pointF5));
            Log.e(this.TAG, sb.toString());
            float f6 = pointF3.x;
            Log.e(this.TAG, "0  x " + this.r0 + " y " + pointF3.y);
            pointF3.set(fArr[2], fArr[3]);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("1  ");
            sb2.append(DistToSegment(pointF3, pointF4, pointF5));
            Log.e(this.TAG, sb2.toString());
            float f7 = pointF3.x;
            Log.e(this.TAG, "1  x " + this.r0 + " y " + pointF3.y);
            pointF3.set(fArr[4], fArr[5]);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("2  ");
            sb3.append(DistToSegment(pointF3, pointF4, pointF5));
            Log.e(this.TAG, sb3.toString());
            float f8 = pointF3.x;
            Log.e(this.TAG, "2  x " + this.r0 + " y " + pointF3.y);
            pointF3.set(fArr[6], fArr[7]);
            float[] fArr3 = {PointToLineDistance(pointF4, pointF5, pointF3), PointToLineDistance(pointF4, pointF5, pointF3), PointToLineDistance(pointF4, pointF5, pointF3), PointToLineDistance(pointF4, pointF5, pointF3)};
            Log.e(this.TAG, "3  " + DistToSegment(pointF3, pointF4, pointF5));
            float f9 = pointF3.x;
            Log.e(this.TAG, "3  x " + this.r0 + " y " + pointF3.y);
            float f10 = fArr3[0];
            StringBuilder sb4 = new StringBuilder();
            sb4.append("bi  ");
            sb4.append(fArr3[0]);
            Log.e(this.TAG, sb4.toString());
            for (int i9 = 1; i9 < 4; i9++) {
                if (fArr3[i9] < f10) {
                    f10 = fArr3[i9];
                    i7 = i9;
                }
                Log.e(this.TAG, "bi  " + fArr3[i9]);
            }
            Log.e(this.TAG, "minIndex  " + i7);
            StringBuilder sb5 = new StringBuilder();
            sb5.append(" points[minIndex*2+1] ");
            int i10 = (i7 * 2) + 1;
            sb5.append(fArr[i10]);
            Log.e(this.TAG, sb5.toString());
            Log.e(this.TAG, "translate " + (((this.offsetY + i3) - 120) - fArr[i10]));
            this.bitmapMatrix.postTranslate(0.0f, ((float) ((this.offsetY + i3) + (-120))) - fArr[i10]);
            return;
        }
        this.bitmapMatrix = new Matrix();
        setMatrixFit();
        float scale = getScale();
        setMaxMinScales(scale);
        float f11 = 1.0f / scale;
        this.touchStrokeWidth = this.tempTouchStrokeWidth * f11;
        this.scrapBookPadding = f11 * 25.0f;
        this.bitmapMatrix.postRotate(scrapBookRotation[i], this.bounds.left + (this.bounds.width() / 2.0f), this.bounds.top + (this.bounds.height() / 2.0f));
        float f12 = this.scrapBookPadding;
        float f13 = -f12;
        this.touchRect = new RectF(f13, f13, this.bitmapWidth + f12, this.bitmapHeight + f12);
        this.touchPaint.setColor(1290417);
        this.touchPaint.setFilterBitmap(true);
        this.touchPaint.setStyle(Paint.Style.STROKE);
        this.touchPaint.setStrokeWidth(this.touchStrokeWidth);
        this.borderPaint.setColor(-1);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(this.borderStrokeWidth);
        this.borderPaint.setAntiAlias(true);
    }

    public float PointToLineDistance(PointF pointF, PointF pointF2, PointF pointF3) {
        return Math.abs(((pointF3.x - pointF.x) * (pointF2.y - pointF.y)) - ((pointF3.y - pointF.y) * (pointF2.x - pointF.x))) / ((float) Math.sqrt(((pointF2.x - pointF.x) * (pointF2.x - pointF.x)) + ((pointF2.y - pointF.y) * (pointF2.y - pointF.y))));
    }

    public float dist2(PointF pointF, PointF pointF2) {
        return sqr(pointF.x - pointF2.x) + sqr(pointF.y - pointF2.y);
    }

    public float DistToSegmentSquared(PointF pointF, PointF pointF2, PointF pointF3) {
        float dist2 = dist2(pointF2, pointF3);
        if (dist2 == 0.0f) {
            return dist2(pointF, pointF2);
        }
        float f = (((pointF.x - pointF2.x) * (pointF3.x - pointF2.x)) + ((pointF.y - pointF2.y) * (pointF3.y - pointF2.y))) / dist2;
        if (f < 0.0f) {
            return dist2(pointF, pointF2);
        }
        if (f > 1.0f) {
            return dist2(pointF, pointF3);
        }
        return dist2(pointF, new PointF(pointF2.x + ((pointF3.x - pointF2.x) * f), pointF2.y + ((pointF3.y - pointF2.y) * f)));
    }

    public float DistToSegment(PointF pointF, PointF pointF2, PointF pointF3) {
        return (float) Math.sqrt(DistToSegmentSquared(pointF, pointF2, pointF3));
    }

    public float[] GetMappedCenter() {
        float[] fArr = this.pts;
        fArr[0] = this.bitmapWidth / 2;
        fArr[1] = this.bitmapHeight / 2;
        this.bitmapMatrix.mapPoints(fArr, fArr);
        return this.pts;
    }

    public boolean isScrapBookSelected(float f, float f2) {
        float[] fArr = this.pts;
        fArr[0] = f;
        fArr[1] = f2;
        this.inverse.reset();
        this.bitmapMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float[] fArr3 = this.pts;
        return fArr3[0] >= 0.0f && fArr3[0] <= ((float) this.bitmapWidth) && fArr3[1] >= 0.0f && fArr3[1] <= ((float) this.bitmapHeight);
    }

    public void DrawShapeForScrapBook(Canvas canvas, int i, int i2, boolean z, boolean z2) {
        canvas.save();
        canvas.concat(this.bitmapMatrix);
        NinePatchDrawable ninePatchDrawable = this.npd;
        int i3 = this.npdPadding;
        int i4 = this.borderStrokeWidth;
        int i5 = (-i3) - i4;
        ninePatchDrawable.setBounds(i5, i5, this.bitmapWidth + i3 + i4, this.bitmapHeight + i3 + i4);
        this.npd.draw(canvas);
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paintScrap);
        if (z) {
            float scale = this.tempTouchStrokeWidth * (1.0f / getScale());
            this.touchStrokeWidth = scale;
            this.touchPaint.setStrokeWidth(scale);
            canvas.drawRect(this.touchRect, this.touchPaint);
            SetDelAndScaleBitmapMatrix();
            Bitmap bitmap = this.btmDelete;
            if (bitmap != null && !bitmap.isRecycled()) {
                canvas.drawBitmap(this.btmDelete, this.removeBitmapMatrix, this.touchPaint);
            }
            Bitmap bitmap2 = this.btmScale;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                canvas.drawBitmap(this.btmScale, this.scaleBitmapMatrix, this.touchPaint);
            }
            if (z2) {
                canvas.drawPath(this.dashPathVertical, this.dashPaint);
                canvas.drawPath(this.dashPathHorizontal, this.dashPaint);
            }
        }
        int i6 = this.borderStrokeWidth;
        float f = (-i6) / 2;
        int i7 = i6 / 2;
        canvas.drawRect(f, f, this.bitmapWidth + i7, this.bitmapHeight + i7, this.borderPaint);
        canvas.restore();
    }

    public void SetDelAndScaleBitmapMatrix() {
        Bitmap bitmap;
        if (this.removeBitmapMatrix == null) {
            this.removeBitmapMatrix = new Matrix();
        }
        if (this.scaleBitmapMatrix == null) {
            this.scaleBitmapMatrix = new Matrix();
        }
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        if (this.delW == 0 && (bitmap = this.btmDelete) != null) {
            this.delW = bitmap.getWidth();
        }
        if (this.screenWidth <= 0) {
            this.screenWidth = 720;
        }
        float f = this.delW;
        float f2 = ((this.screenWidth / 20.0f) * 2.0f) / f;
        this.deleteWidthHalf = (f * f2) / 1.4f;
        this.removeBitmapMatrix.postScale(f2, f2);
        Matrix matrix = this.removeBitmapMatrix;
        float f3 = (-this.scrapBookPadding) - ((this.delW * f2) / 2.0f);
        matrix.postTranslate(f3, f3);
        this.scaleBitmapMatrix.postScale(f2, f2);
        Matrix matrix2 = this.scaleBitmapMatrix;
        float f4 = this.scrapBookPadding;
        float f5 = (this.delW * f2) / 2.0f;
        matrix2.postTranslate((this.bitmapWidth + f4) - f5, (this.bitmapHeight + f4) - f5);
        float scale = 1.0f / getScale();
        this.scaleBitmapMatrix.postScale(scale, scale, this.touchRect.right, this.touchRect.bottom);
        this.removeBitmapMatrix.postScale(scale, scale, this.touchRect.left, this.touchRect.top);
        int i = this.screenWidth;
        if (i > 0) {
            this.tempTouchStrokeWidth = i / 120.0f;
        }
    }

    public void BindScrapBook(NinePatchDrawable ninePatchDrawable) {
        setNinePatch(ninePatchDrawable);
    }

    public void setNinePatch(NinePatchDrawable ninePatchDrawable) {
        this.npd = ninePatchDrawable;
        this.touchRect.round(new Rect());
    }

    public float getScale() {
        this.bitmapMatrix.getValues(this.values);
        float[] fArr = this.values;
        float sqrt = (float) Math.sqrt((fArr[0] * fArr[0]) + (fArr[3] * fArr[3]));
        if (sqrt <= 0.0f) {
            return 1.0f;
        }
        return sqrt;
    }

    public boolean isInCircle(float f, float f2) {
        float[] fArr = this.pts;
        fArr[0] = f;
        fArr[1] = f2;
        this.bitmapMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float f3 = ((this.pts[0] - this.touchRect.right) * (this.pts[0] - this.touchRect.right)) + ((this.pts[1] - this.touchRect.bottom) * (this.pts[1] - this.touchRect.bottom));
        float f4 = this.deleteWidthHalf;
        return f3 < (f4 * f4) / (getScale() * getScale());
    }

    public boolean isOnCross(float f, float f2) {
        float[] fArr = this.pts;
        fArr[0] = f;
        fArr[1] = f2;
        this.bitmapMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float f3 = ((this.pts[0] - this.touchRect.left) * (this.pts[0] - this.touchRect.left)) + ((this.pts[1] - this.touchRect.top) * (this.pts[1] - this.touchRect.top));
        float f4 = this.deleteWidthHalf;
        return f3 < (f4 * f4) / (getScale() * getScale());
    }
}
