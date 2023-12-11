package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.MotionEventCompat;
import hdphoto.galleryimages.gelleryalbum.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Ledl_StickerView extends AppCompatImageView {
    static final float BITMAP_SCALE = 0.7f;
    float MAX_SCALE;
    float MIN_SCALE;
    private Sticker currentSticker;
    Bitmap deleteBitmap;
    int deleteBitmapHeight;
    int deleteBitmapWidth;
    DisplayMetrics displayMatrix;
    Rect dst_deleteRect;
    Rect dst_flipRect;
    Rect dst_resizeRect;
    Rect dst_topRect;
    Bitmap flipVBitmap;
    int flipVBitmapHeight;
    int flipVBitmapWidth;
    double halfDiagonalLength;
    boolean isHorizonMirror;
    boolean isInEdit;
    boolean isInResize;
    boolean isInSide;
    boolean isPointerDown;
    float lastLength;
    float lastRotateDegree;
    float lastX;
    float lastY;
    Paint localPaint;
    Bitmap mBitmap;
    int mScreenHeight;
    int mScreenWidth;
    private ArrayList<Sticker> mStickers;
    Matrix matrix;
    PointF mid;
    float oldDis;
    OperationListener operationListener;
    float originWidth;
    final float pointerLimitDis;
    final float pointerZoomCoeff;
    Bitmap resizeBitmap;
    int resizeBitmapHeight;
    int resizeBitmapWidth;
    long stickerId;
    Bitmap topBitmap;
    int topBitmapHeight;
    int topBitmapWidth;


    public interface OperationListener {
        void onDeleteClick();

        void onEdit(Ledl_StickerView cMStickerView);

        void onTop(Ledl_StickerView cMStickerView);
    }

    public Ledl_StickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mid = new PointF();
        this.isPointerDown = false;
        this.pointerLimitDis = 20.0f;
        this.pointerZoomCoeff = 0.09f;
        this.isInResize = false;
        this.matrix = new Matrix();
        this.isInEdit = true;
        this.MIN_SCALE = 0.5f;
        this.MAX_SCALE = 1.2f;
        this.originWidth = 0.0f;
        this.isHorizonMirror = false;
        this.stickerId = 0L;
        Bind();
    }

    public Ledl_StickerView(Context context) {
        super(context);
        this.mid = new PointF();
        this.isPointerDown = false;
        this.pointerLimitDis = 20.0f;
        this.pointerZoomCoeff = 0.09f;
        this.isInResize = false;
        this.matrix = new Matrix();
        this.isInEdit = true;
        this.MIN_SCALE = 0.5f;
        this.MAX_SCALE = 1.2f;
        this.originWidth = 0.0f;
        this.isHorizonMirror = false;
        this.stickerId = 0L;
        Bind();
    }

    public Ledl_StickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mid = new PointF();
        this.isPointerDown = false;
        this.pointerLimitDis = 20.0f;
        this.pointerZoomCoeff = 0.09f;
        this.isInResize = false;
        this.matrix = new Matrix();
        this.isInEdit = true;
        this.MIN_SCALE = 0.5f;
        this.MAX_SCALE = 1.2f;
        this.originWidth = 0.0f;
        this.isHorizonMirror = false;
        this.stickerId = 0L;
        Bind();
    }

    private void Bind() {
        this.dst_deleteRect = new Rect();
        this.dst_resizeRect = new Rect();
        this.dst_flipRect = new Rect();
        this.dst_topRect = new Rect();
        Paint paint = new Paint();
        this.localPaint = paint;
        paint.setColor(getResources().getColor(R.color.red_e73a3d));
        this.localPaint.setAntiAlias(true);
        this.localPaint.setDither(true);
        this.localPaint.setStyle(Paint.Style.STROKE);
        this.localPaint.setStrokeWidth(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.displayMatrix = displayMetrics;
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = this.displayMatrix.heightPixels;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mBitmap != null) {
            float[] fArr = new float[9];
            this.matrix.getValues(fArr);
            float f = (fArr[1] * 0.0f) + fArr[2] + (fArr[0] * 0.0f);
            float f2 = (fArr[3] * 0.0f) + (fArr[4] * 0.0f) + fArr[5];
            float width = (fArr[0] * this.mBitmap.getWidth()) + (fArr[1] * 0.0f) + fArr[2];
            float width2 = (fArr[3] * this.mBitmap.getWidth()) + (fArr[4] * 0.0f) + fArr[5];
            float height = (fArr[0] * 0.0f) + (fArr[1] * this.mBitmap.getHeight()) + fArr[2];
            float height2 = (fArr[3] * 0.0f) + (fArr[4] * this.mBitmap.getHeight()) + fArr[5];
            float width3 = (fArr[0] * this.mBitmap.getWidth()) + (fArr[1] * this.mBitmap.getHeight()) + fArr[2];
            float width4 = (fArr[3] * this.mBitmap.getWidth()) + (fArr[4] * this.mBitmap.getHeight()) + fArr[5];
            canvas.save();
            canvas.drawBitmap(this.mBitmap, this.matrix, null);
            this.dst_deleteRect.left = (int) (width - (this.deleteBitmapWidth / 2));
            this.dst_deleteRect.right = (int) ((this.deleteBitmapWidth / 2) + width);
            this.dst_deleteRect.top = (int) (width2 - (this.deleteBitmapHeight / 2));
            this.dst_deleteRect.bottom = (int) ((this.deleteBitmapHeight / 2) + width2);
            this.dst_resizeRect.left = (int) (width3 - (this.resizeBitmapWidth / 2));
            this.dst_resizeRect.right = (int) (width3 + (this.resizeBitmapWidth / 2));
            this.dst_resizeRect.top = (int) (width4 - (this.resizeBitmapHeight / 2));
            this.dst_resizeRect.bottom = (int) ((this.resizeBitmapHeight / 2) + width4);
            this.dst_topRect.left = (int) (f - (this.flipVBitmapWidth / 2));
            this.dst_topRect.right = (int) ((this.flipVBitmapWidth / 2) + f);
            this.dst_topRect.top = (int) (f2 - (this.flipVBitmapHeight / 2));
            this.dst_topRect.bottom = (int) ((this.flipVBitmapHeight / 2) + f2);
            this.dst_flipRect.left = (int) (height - (this.topBitmapWidth / 2));
            this.dst_flipRect.right = (int) ((this.topBitmapWidth / 2) + height);
            this.dst_flipRect.top = (int) (height2 - (this.topBitmapHeight / 2));
            this.dst_flipRect.bottom = (int) ((this.topBitmapHeight / 2) + height2);
            if (this.isInEdit) {
                canvas.drawLine(f, f2, width, width2, this.localPaint);
                canvas.drawLine(width, width2, width3, width4, this.localPaint);
                canvas.drawLine(height, height2, width3, width4, this.localPaint);
                canvas.drawLine(height, height2, f, f2, this.localPaint);
                canvas.drawBitmap(this.deleteBitmap, (Rect) null, this.dst_deleteRect, (Paint) null);
                canvas.drawBitmap(this.resizeBitmap, (Rect) null, this.dst_resizeRect, (Paint) null);
                canvas.drawBitmap(this.flipVBitmap, (Rect) null, this.dst_flipRect, (Paint) null);
                canvas.drawBitmap(this.topBitmap, (Rect) null, this.dst_topRect, (Paint) null);
            }
            canvas.restore();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        setBitmap(BitmapFactory.decodeResource(getResources(), i));
    }

    public void setBitmap(Bitmap bitmap) {
        this.matrix.reset();
        this.mBitmap = bitmap;
        setDiagonalLength();
        BindBitmaps();
        int width = this.mBitmap.getWidth();
        int height = this.mBitmap.getHeight();
        this.originWidth = width;
        float f = (this.MIN_SCALE + this.MAX_SCALE) / 2.0f;
        int i = width / 2;
        int i2 = height / 2;
        this.matrix.postScale(f, f, i, i2);
        Matrix matrix = this.matrix;
        int i3 = this.mScreenWidth / 2;
        matrix.postTranslate(i3 - i, i3 - i2);
        invalidate();
    }

    private void setDiagonalLength() {
        this.halfDiagonalLength = Math.hypot(this.mBitmap.getWidth(), this.mBitmap.getHeight()) / 2.0d;
    }

    private void BindBitmaps() {
        if (this.mBitmap.getWidth() >= this.mBitmap.getHeight()) {
            float f = this.mScreenWidth / 8;
            if (this.mBitmap.getWidth() < f) {
                this.MIN_SCALE = 1.0f;
            } else {
                this.MIN_SCALE = (f * 1.0f) / this.mBitmap.getWidth();
            }
            int width = this.mBitmap.getWidth();
            int i = this.mScreenWidth;
            if (width > i) {
                this.MAX_SCALE = 1.0f;
            } else {
                this.MAX_SCALE = (i * 1.0f) / this.mBitmap.getWidth();
            }
        } else {
            float f2 = this.mScreenWidth / 8;
            if (this.mBitmap.getHeight() < f2) {
                this.MIN_SCALE = 1.0f;
            } else {
                this.MIN_SCALE = (f2 * 1.0f) / this.mBitmap.getHeight();
            }
            int height = this.mBitmap.getHeight();
            int i2 = this.mScreenWidth;
            if (height > i2) {
                this.MAX_SCALE = 1.0f;
            } else {
                this.MAX_SCALE = (i2 * 1.0f) / this.mBitmap.getHeight();
            }
        }
        this.topBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_top_enable);
        this.deleteBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_delete);
        this.flipVBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_flip);
        this.resizeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_resize);
        this.deleteBitmapWidth = (int) (this.deleteBitmap.getWidth() * 0.7f);
        this.deleteBitmapHeight = (int) (this.deleteBitmap.getHeight() * 0.7f);
        this.resizeBitmapWidth = (int) (this.resizeBitmap.getWidth() * 0.7f);
        this.resizeBitmapHeight = (int) (this.resizeBitmap.getHeight() * 0.7f);
        this.flipVBitmapWidth = (int) (this.flipVBitmap.getWidth() * 0.7f);
        this.flipVBitmapHeight = (int) (this.flipVBitmap.getHeight() * 0.7f);
        this.topBitmapWidth = (int) (this.topBitmap.getWidth() * 0.7f);
        this.topBitmapHeight = (int) (this.topBitmap.getHeight() * 0.7f);
    }

    protected PointF calculateMidPoint(MotionEvent motionEvent) {
        if (motionEvent == null || motionEvent.getPointerCount() < 2) {
            this.mid.set(0.0f, 0.0f);
            return this.mid;
        }
        try {
            this.mid.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this.mid;
    }

    private boolean touchInsideSticker(float f, float f2) {
        Iterator<Sticker> it = this.mStickers.iterator();
        while (it.hasNext()) {
            Sticker next = it.next();
            Region region = new Region();
            region.setPath(next.getBoundPath(), new Region(0, 0, getMeasuredWidth(), getMeasuredHeight()));
            if (region.contains((int) f, (int) f2)) {
                this.currentSticker = next;
                int indexOf = this.mStickers.indexOf(next);
                ArrayList<Sticker> arrayList = this.mStickers;
                Collections.swap(arrayList, indexOf, arrayList.size() - 1);
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        OperationListener operationListener;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        boolean z = true;
        float f = 1.0f;
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3 && actionMasked == 5) {
                        if (spacing(motionEvent) > 20.0f) {
                            this.oldDis = spacing(motionEvent);
                            this.isPointerDown = true;
                            midPointToStartPoint(motionEvent);
                        } else {
                            this.isPointerDown = false;
                        }
                        this.isInSide = false;
                        this.isInResize = false;
                    }
                } else if (this.isPointerDown) {
                    float spacing = spacing(motionEvent);
                    float f2 = (spacing == 0.0f || spacing < 20.0f) ? 1.0f : (((spacing / this.oldDis) - 1.0f) * 0.09f) + 1.0f;
                    float abs = (Math.abs(this.dst_flipRect.left - this.dst_resizeRect.left) * f2) / this.originWidth;
                    if ((abs > this.MIN_SCALE || f2 >= 1.0f) && (abs < this.MAX_SCALE || f2 <= 1.0f)) {
                        this.lastLength = diagonalLength(motionEvent);
                        f = f2;
                    }
                    this.matrix.postScale(f, f, this.mid.x, this.mid.y);
                    invalidate();
                } else if (this.isInResize) {
                    this.matrix.postRotate((rotationToStartPoint(motionEvent) - this.lastRotateDegree) * 2.0f, this.mid.x, this.mid.y);
                    this.lastRotateDegree = rotationToStartPoint(motionEvent);
                    float diagonalLength = diagonalLength(motionEvent) / this.lastLength;
                    if ((diagonalLength(motionEvent) / this.halfDiagonalLength > this.MIN_SCALE || diagonalLength >= 1.0f) && (diagonalLength(motionEvent) / this.halfDiagonalLength < this.MAX_SCALE || diagonalLength <= 1.0f)) {
                        this.lastLength = diagonalLength(motionEvent);
                        f = diagonalLength;
                    } else if (!isInResize(motionEvent)) {
                        this.isInResize = false;
                    }
                    this.matrix.postScale(f, f, this.mid.x, this.mid.y);
                    invalidate();
                } else if (this.isInSide) {
                    float x = motionEvent.getX(0);
                    float y = motionEvent.getY(0);
                    this.matrix.postTranslate(x - this.lastX, y - this.lastY);
                    this.lastX = x;
                    this.lastY = y;
                    invalidate();
                }
            }
            this.isInResize = false;
            this.isInSide = false;
            this.isPointerDown = false;
        } else if (isInButton(motionEvent, this.dst_deleteRect)) {
            OperationListener operationListener2 = this.operationListener;
            if (operationListener2 != null) {
                operationListener2.onDeleteClick();
            }
        } else if (isInResize(motionEvent)) {
            this.isInResize = true;
            this.lastRotateDegree = rotationToStartPoint(motionEvent);
            midPointToStartPoint(motionEvent);
            this.lastLength = diagonalLength(motionEvent);
        } else if (isInButton(motionEvent, this.dst_flipRect)) {
            PointF pointF = new PointF();
            midDiagonalPoint(pointF);
            this.matrix.postScale(-1.0f, 1.0f, pointF.x, pointF.y);
            this.isHorizonMirror = !this.isHorizonMirror;
            invalidate();
        } else if (isInButton(motionEvent, this.dst_topRect)) {
            bringToFront();
            OperationListener operationListener3 = this.operationListener;
            if (operationListener3 != null) {
                operationListener3.onTop(this);
            }
        } else if (isInBitmap(motionEvent)) {
            this.isInSide = true;
            this.lastX = motionEvent.getX(0);
            this.lastY = motionEvent.getY(0);
        } else {
            z = false;
        }
        if (z && (operationListener = this.operationListener) != null) {
            operationListener.onEdit(this);
        }
        return z;
    }

    private boolean isInBitmap(MotionEvent motionEvent) {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        float f = (fArr[3] * 0.0f) + (fArr[4] * 0.0f) + fArr[5];
        float width = (fArr[0] * this.mBitmap.getWidth()) + (fArr[1] * 0.0f) + fArr[2];
        float width2 = (fArr[3] * this.mBitmap.getWidth()) + (fArr[4] * 0.0f) + fArr[5];
        return pointInRect(new float[]{(fArr[0] * 0.0f) + (fArr[1] * 0.0f) + fArr[2], width, (fArr[0] * this.mBitmap.getWidth()) + (fArr[1] * this.mBitmap.getHeight()) + fArr[2], (fArr[0] * 0.0f) + (fArr[1] * this.mBitmap.getHeight()) + fArr[2]}, new float[]{f, width2, (fArr[3] * this.mBitmap.getWidth()) + (fArr[4] * this.mBitmap.getHeight()) + fArr[5], (fArr[3] * 0.0f) + (fArr[4] * this.mBitmap.getHeight()) + fArr[5]}, motionEvent.getX(0), motionEvent.getY(0));
    }

    private boolean pointInRect(float[] fArr, float[] fArr2, float f, float f2) {
        double hypot = Math.hypot(fArr[0] - fArr[1], fArr2[0] - fArr2[1]);
        double hypot2 = Math.hypot(fArr[1] - fArr[2], fArr2[1] - fArr2[2]);
        double hypot3 = Math.hypot(fArr[3] - fArr[2], fArr2[3] - fArr2[2]);
        double hypot4 = Math.hypot(fArr[0] - fArr[3], fArr2[0] - fArr2[3]);
        double hypot5 = Math.hypot(f - fArr[0], f2 - fArr2[0]);
        double hypot6 = Math.hypot(f - fArr[1], f2 - fArr2[1]);
        double hypot7 = Math.hypot(f - fArr[2], f2 - fArr2[2]);
        double hypot8 = Math.hypot(f - fArr[3], f2 - fArr2[3]);
        double d = ((hypot + hypot5) + hypot6) / 2.0d;
        double d2 = ((hypot2 + hypot6) + hypot7) / 2.0d;
        double d3 = ((hypot3 + hypot7) + hypot8) / 2.0d;
        double d4 = ((hypot4 + hypot8) + hypot5) / 2.0d;
        return Math.abs((hypot * hypot2) - (((Math.sqrt((((d - hypot) * d) * (d - hypot5)) * (d - hypot6)) + Math.sqrt((((d2 - hypot2) * d2) * (d2 - hypot6)) * (d2 - hypot7))) + Math.sqrt((((d3 - hypot3) * d3) * (d3 - hypot7)) * (d3 - hypot8))) + Math.sqrt((((d4 - hypot4) * d4) * (d4 - hypot8)) * (d4 - hypot5)))) < 0.5d;
    }

    private boolean isInButton(MotionEvent motionEvent, Rect rect) {
        return motionEvent.getX(0) >= ((float) rect.left) && motionEvent.getX(0) <= ((float) rect.right) && motionEvent.getY(0) >= ((float) rect.top) && motionEvent.getY(0) <= ((float) rect.bottom);
    }

    private boolean isInResize(MotionEvent motionEvent) {
        return motionEvent.getX(0) >= ((float) (this.dst_resizeRect.left + (-20))) && motionEvent.getX(0) <= ((float) (this.dst_resizeRect.right + 20)) && motionEvent.getY(0) >= ((float) (this.dst_resizeRect.top + (-20))) && motionEvent.getY(0) <= ((float) (this.dst_resizeRect.bottom + 20));
    }

    private void midPointToStartPoint(MotionEvent motionEvent) {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        this.mid.set(((((fArr[0] * 0.0f) + (fArr[1] * 0.0f)) + fArr[2]) + motionEvent.getX(0)) / 2.0f, ((((fArr[3] * 0.0f) + (fArr[4] * 0.0f)) + fArr[5]) + motionEvent.getY(0)) / 2.0f);
    }

    private void midDiagonalPoint(PointF pointF) {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        pointF.set(((((fArr[0] * 0.0f) + (fArr[1] * 0.0f)) + fArr[2]) + (((fArr[0] * this.mBitmap.getWidth()) + (fArr[1] * this.mBitmap.getHeight())) + fArr[2])) / 2.0f, ((((fArr[3] * 0.0f) + (fArr[4] * 0.0f)) + fArr[5]) + (((fArr[3] * this.mBitmap.getWidth()) + (fArr[4] * this.mBitmap.getHeight())) + fArr[5])) / 2.0f);
    }

    private float rotationToStartPoint(MotionEvent motionEvent) {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        return (float) Math.toDegrees(Math.atan2(motionEvent.getY(0) - (((fArr[3] * 0.0f) + (fArr[4] * 0.0f)) + fArr[5]), motionEvent.getX(0) - (((fArr[0] * 0.0f) + (fArr[1] * 0.0f)) + fArr[2])));
    }

    private float diagonalLength(MotionEvent motionEvent) {
        return (float) Math.hypot(motionEvent.getX(0) - this.mid.x, motionEvent.getY(0) - this.mid.y);
    }

    private float spacing(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != 2) {
            return 0.0f;
        }
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public void setOperationListener(OperationListener operationListener) {
        this.operationListener = operationListener;
    }

    public void setInEdit(boolean z) {
        this.isInEdit = z;
        invalidate();
    }

    public Matrix getBitmapMatrix() {
        return this.matrix;
    }
}
