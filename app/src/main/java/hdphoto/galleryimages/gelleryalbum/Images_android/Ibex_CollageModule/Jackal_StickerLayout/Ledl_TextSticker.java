package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import androidx.core.view.MotionEventCompat;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.BaseData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtCustomTextView;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtDecorateView;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtMyMatrix;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Anadol_RotationGestureDetector;
import unified.vpn.sdk.HydraVpnTransportException;


public class Ledl_TextSticker extends CtDecorateView {
    static float MIN_ZOOM = 0.2f;
    private static final String TAG = "TextSticker";
    float actualRadius;
    Paint bitmapPaint;
    float btmH;
    float btmW;
    PointF center;
    float circlePadding;
    Paint dashPaint;
    Path dashPathHorizontal;
    Path dashPathHorizontalTemp;
    Path dashPathVertical;
    Path dashPathVerticalTemp;
    boolean doubleTouchCall;
    final float epsilon;
    float[] f1374v;
    GestureDetector gestureDetector;
    Matrix inverse;
    boolean isInCircle;
    boolean isOnRect;
    boolean isOnTouch;
    boolean locked;
    int mActivePointerId;
    Anadol_RotationGestureDetector mRotationDetector;
    ScaleGestureDetector mScaleDetector;
    float mScaleFactor;
    float minDimen;
    boolean orthogonal;
    float paddingHeight;
    float paddingWidth;
    public Paint paint;
    PointF previousPos;
    float[] pts;
    Paint rectPaint;
    Paint rectPaintOnTouch;
    Paint redPaint;
    Bitmap removeBitmap;
    Matrix removeBitmapMatrix;
    float removeBitmapWidth;
    Anadol_RotationGestureDetector.OnRotationGestureListener rotateListener;
    boolean savedViewSelected;
    float scale;
    Bitmap scaleBitmap;
    Matrix scaleBitmapMatrix;
    SingleTap singleTapListener;
    float startAngle;
    float startAngleGesture;
    public Bitmap stickerBitmap;
    Ledl_StickerData stickerData;
    CtCustomTextView.TextAndStickerViewSelectedListener textAndStickerViewSelectedListener;
    Rect textBoundRect;
    RectF touchRect;
    float[] values;
    boolean viewSelected;
    StickerViewSelectedListener viewSelectedListenerEx;
    Paint whitePaint;
    PointF zoomStart;


    public interface SingleTap {
        void onSingleTap(Ledl_StickerData cMStickerData);
    }


    public interface StickerViewSelectedListener {
        void onTouchUp(Ledl_StickerData cMStickerData);

        void setSelectedView(Ledl_StickerView cMStickerView);
    }


    public interface StickerViewTouchUpListener {
        void onTouchUp();
    }

    public void setStickerViewSelectedListener(StickerViewSelectedListener stickerViewSelectedListener) {
    }

    void singleTapped() {
    }


    class DoubleTouchHandler implements Runnable {
        DoubleTouchHandler() {
        }

        @Override 
        public void run() {
            Ledl_TextSticker.this.doubleTouchCall = false;
        }
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            Log.e(Ledl_TextSticker.TAG, "onDown");
            if (Ledl_TextSticker.this.isInCircle || Ledl_TextSticker.this.isOnRect) {
                return true;
            }
            Ledl_TextSticker.this.viewSelected = false;
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.e(Ledl_TextSticker.TAG, "onSingleTapUp");
            if (Ledl_TextSticker.this.locked) {
                return false;
            }
            Ledl_TextSticker.this.pts[0] = motionEvent.getX();
            Ledl_TextSticker.this.pts[1] = motionEvent.getY();
            Ledl_TextSticker.this.stickerData.canvasMatrix.invert(Ledl_TextSticker.this.inverse);
            Ledl_TextSticker.this.inverse.mapPoints(Ledl_TextSticker.this.pts, Ledl_TextSticker.this.pts);
            Ledl_TextSticker cMTextSticker = Ledl_TextSticker.this;
            cMTextSticker.isOnRect = cMTextSticker.isOnRectCheck(cMTextSticker.pts[0], Ledl_TextSticker.this.pts[1]);
            Log.e(Ledl_TextSticker.TAG, "onSingleTapUp viewSelected " + Ledl_TextSticker.this.viewSelected);
            if (Ledl_TextSticker.this.isOnRect) {
                Log.e(Ledl_TextSticker.TAG, "onSingleTapUp doubleSavedViewSelected " + Ledl_TextSticker.this.doubleTouchCall);
                if (Ledl_TextSticker.this.doubleTouchCall) {
                    Ledl_TextSticker.this.viewSelected = true;
                } else {
                    Ledl_TextSticker cMTextSticker2 = Ledl_TextSticker.this;
                    cMTextSticker2.viewSelected = !cMTextSticker2.savedViewSelected;
                }
                Ledl_TextSticker.this.doubleTouchCall = false;
            } else {
                Ledl_TextSticker.this.viewSelected = false;
            }
            return Ledl_TextSticker.this.isInCircle || Ledl_TextSticker.this.isOnRect;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (Ledl_TextSticker.this.locked) {
                return false;
            }
            Ledl_TextSticker.this.pts[0] = motionEvent.getX();
            Ledl_TextSticker.this.pts[1] = motionEvent.getY();
            Ledl_TextSticker.this.stickerData.canvasMatrix.invert(Ledl_TextSticker.this.inverse);
            Ledl_TextSticker.this.inverse.mapPoints(Ledl_TextSticker.this.pts, Ledl_TextSticker.this.pts);
            Ledl_TextSticker cMTextSticker = Ledl_TextSticker.this;
            cMTextSticker.isOnRect = cMTextSticker.isOnRectCheck(cMTextSticker.pts[0], Ledl_TextSticker.this.pts[1]);
            if (Ledl_TextSticker.this.isOnRect) {
                Ledl_TextSticker.this.viewSelected = true;
            } else {
                Ledl_TextSticker.this.viewSelected = false;
            }
            return true;
        }
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            Ledl_TextSticker.this.mScaleFactor = scaleGestureDetector.getScaleFactor();
            if (scaleGestureDetector.isInProgress()) {
                Ledl_TextSticker.this.pts[0] = Ledl_TextSticker.this.touchRect.centerX();
                Ledl_TextSticker.this.pts[1] = Ledl_TextSticker.this.touchRect.centerY();
                Ledl_TextSticker.this.stickerData.canvasMatrix.mapPoints(Ledl_TextSticker.this.pts, Ledl_TextSticker.this.pts);
                Ledl_TextSticker.this.mScaleFactor = scaleGestureDetector.getScaleFactor();
                Ledl_TextSticker.this.mScaleFactor = Math.max(Ledl_TextSticker.MIN_ZOOM, Ledl_TextSticker.this.mScaleFactor);
                Ledl_TextSticker.this.stickerData.canvasMatrix.postScale(Ledl_TextSticker.this.mScaleFactor, Ledl_TextSticker.this.mScaleFactor, Ledl_TextSticker.this.pts[0], Ledl_TextSticker.this.pts[1]);
                Ledl_TextSticker cMTextSticker = Ledl_TextSticker.this;
                cMTextSticker.scale = cMTextSticker.getScale();
                Ledl_TextSticker.this.invalidate();
            } else {
                Ledl_TextSticker.this.pts[0] = Ledl_TextSticker.this.touchRect.centerX();
                Ledl_TextSticker.this.pts[1] = Ledl_TextSticker.this.touchRect.centerY();
                Ledl_TextSticker.this.stickerData.canvasMatrix.mapPoints(Ledl_TextSticker.this.pts, Ledl_TextSticker.this.pts);
                Ledl_TextSticker.this.mScaleFactor = scaleGestureDetector.getScaleFactor();
                Ledl_TextSticker.this.mScaleFactor = Math.max(Ledl_TextSticker.MIN_ZOOM, Ledl_TextSticker.this.mScaleFactor);
                Ledl_TextSticker.this.stickerData.canvasMatrix.postScale(Ledl_TextSticker.this.mScaleFactor, Ledl_TextSticker.this.mScaleFactor, Ledl_TextSticker.this.pts[0], Ledl_TextSticker.this.pts[1]);
                Ledl_TextSticker cMTextSticker2 = Ledl_TextSticker.this;
                cMTextSticker2.scale = cMTextSticker2.getScale();
                Ledl_TextSticker.this.invalidate();
            }
            return true;
        }
    }


    class RGListener implements Anadol_RotationGestureDetector.OnRotationGestureListener {
        RGListener() {
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMRotationGestureDetector.OnRotationGestureListener
        public void OnRotation(Anadol_RotationGestureDetector cMRotationGestureDetector) {
            float angle = cMRotationGestureDetector.getAngle();
            Ledl_TextSticker cMTextSticker = Ledl_TextSticker.this;
            float matrixRotation = cMTextSticker.getMatrixRotation(cMTextSticker.stickerData.canvasMatrix);
            if ((matrixRotation == 0.0f || matrixRotation == 90.0f || matrixRotation == 180.0f || matrixRotation == -180.0f || matrixRotation == -90.0f) && Math.abs(Ledl_TextSticker.this.startAngleGesture - angle) < 4.0f) {
                Ledl_TextSticker.this.orthogonal = true;
                return;
            }
            if (Math.abs((matrixRotation - Ledl_TextSticker.this.startAngleGesture) + angle) < 4.0f) {
                angle = Ledl_TextSticker.this.startAngleGesture - matrixRotation;
                Ledl_TextSticker.this.orthogonal = true;
            } else if (Math.abs(90.0f - ((matrixRotation - Ledl_TextSticker.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (Ledl_TextSticker.this.startAngleGesture + 90.0f) - matrixRotation;
                Ledl_TextSticker.this.orthogonal = true;
            } else if (Math.abs(180.0f - ((matrixRotation - Ledl_TextSticker.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (Ledl_TextSticker.this.startAngleGesture + 180.0f) - matrixRotation;
                Ledl_TextSticker.this.orthogonal = true;
            } else if (Math.abs((-180.0f) - ((matrixRotation - Ledl_TextSticker.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (Ledl_TextSticker.this.startAngleGesture - 0.024902344f) - matrixRotation;
                Ledl_TextSticker.this.orthogonal = true;
            } else if (Math.abs((-90.0f) - ((matrixRotation - Ledl_TextSticker.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (Ledl_TextSticker.this.startAngleGesture - 0.049804688f) - matrixRotation;
                Ledl_TextSticker.this.orthogonal = true;
            } else {
                Ledl_TextSticker.this.orthogonal = false;
            }
            Ledl_TextSticker.this.pts[0] = Ledl_TextSticker.this.touchRect.centerX();
            Ledl_TextSticker.this.pts[1] = Ledl_TextSticker.this.touchRect.centerY();
            Ledl_TextSticker.this.stickerData.canvasMatrix.mapPoints(Ledl_TextSticker.this.pts, Ledl_TextSticker.this.pts);
            Ledl_TextSticker.this.stickerData.canvasMatrix.postRotate(Ledl_TextSticker.this.startAngleGesture - angle, Ledl_TextSticker.this.pts[0], Ledl_TextSticker.this.pts[1]);
            Ledl_TextSticker.this.startAngleGesture = angle;
            Ledl_TextSticker.this.invalidate();
        }
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public BaseData getData() {
        return this.stickerData;
    }

    public void setSingleTapListener(SingleTap singleTap) {
        this.singleTapListener = singleTap;
    }

    public void setTextAndStickerSelectedListner(CtCustomTextView.TextAndStickerViewSelectedListener textAndStickerViewSelectedListener) {
        this.textAndStickerViewSelectedListener = textAndStickerViewSelectedListener;
    }

    public void setViewSelected(boolean z) {
        Log.e(TAG, "setViewSelected " + z);
        this.viewSelected = z;
        postInvalidate();
    }

    public Ledl_StickerData getStickerData() {
        return this.stickerData;
    }

    public void setStickerData(Ledl_StickerData cMStickerData) {
        this.stickerData.set(cMStickerData);
    }

    public boolean getViewSelected() {
        return this.viewSelected;
    }

    public void lockView(boolean z) {
        this.locked = z;
    }

    public Ledl_TextSticker(Context context, Bitmap bitmap, Ledl_StickerData cMStickerData, Bitmap bitmap2, Bitmap bitmap3, int i, String str) {
        super(context);
        this.bitmapPaint = new Paint(1);
        this.center = new PointF();
        this.dashPaint = new Paint();
        this.dashPathHorizontalTemp = new Path();
        this.dashPathVerticalTemp = new Path();
        this.doubleTouchCall = false;
        this.epsilon = 4.0f;
        this.inverse = new Matrix();
        this.isInCircle = false;
        this.isOnRect = false;
        this.isOnTouch = false;
        this.locked = false;
        this.mActivePointerId = -1;
        this.mScaleFactor = 1.0f;
        this.orthogonal = false;
        this.previousPos = new PointF();
        this.pts = new float[2];
        this.redPaint = new Paint(1);
        this.removeBitmapMatrix = new Matrix();
        this.rotateListener = new RGListener();
        this.savedViewSelected = false;
        this.scaleBitmapMatrix = new Matrix();
        this.startAngle = 0.0f;
        this.startAngleGesture = 0.0f;
        this.f1374v = new float[9];
        this.values = new float[9];
        this.viewSelected = false;
        this.whitePaint = new Paint(1);
        this.zoomStart = new PointF();
        this.mRotationDetector = new Anadol_RotationGestureDetector(this.rotateListener);
        this.actualRadius = this.paddingHeight;
        this.circlePadding = 5.0f;
        this.paddingHeight = 30.0f;
        this.paddingWidth = 10.0f;
        this.scale = 1.0f;
        this.stickerBitmap = bitmap;
        this.removeBitmap = bitmap2;
        this.scaleBitmap = bitmap3;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        float f = getResources().getDisplayMetrics().widthPixels;
        float f2 = getResources().getDisplayMetrics().heightPixels;
        this.minDimen = Math.min(f, f2);
        Paint paint = new Paint(1);
        this.rectPaint = paint;
        paint.setColor(2006555033);
        Paint paint2 = new Paint(1);
        this.rectPaintOnTouch = paint2;
        paint2.setColor(2011028957);
        this.textBoundRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (cMStickerData == null) {
            if (str != null) {
                this.stickerData = new Ledl_StickerData(str);
            } else {
                this.stickerData = new Ledl_StickerData(i);
            }
            float f3 = this.minDimen / 1080.0f;
            this.stickerData.canvasMatrix.postScale(f3, f3);
            this.stickerData.canvasMatrix.postTranslate(0.1f, 0.1f);
            this.stickerData.xPos = ((f / f3) - this.textBoundRect.width()) / 2.0f;
            this.stickerData.yPos = f2 / (f3 * 3.0f);
        } else {
            this.stickerData = cMStickerData;
        }
        this.paddingWidth = f / 15.0f;
        this.paddingHeight = f / 14.0f;
        this.touchRect = new RectF(this.stickerData.xPos - this.paddingWidth, this.stickerData.yPos - this.paddingHeight, this.stickerData.xPos + this.textBoundRect.width() + this.paddingWidth, this.stickerData.yPos + this.textBoundRect.height() + this.paddingHeight);
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        Paint paint3 = new Paint();
        this.paint = paint3;
        paint3.setAntiAlias(true);
        this.paint.setFilterBitmap(true);
        this.redPaint.setColor(-16485377);
        this.whitePaint.setColor(-1460137);
        this.bitmapPaint.setFilterBitmap(true);
        this.actualRadius = this.minDimen / 20.0f;
        float max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        float f4 = this.actualRadius;
        if (max > 3.0f * f4) {
            MIN_ZOOM = (f4 * 1.0f) / max;
        }
        this.circlePadding = f4 / 2.0f;
        if (f4 <= 5.0f) {
            this.actualRadius = this.paddingHeight;
        }
        this.removeBitmapWidth = this.removeBitmap.getWidth();
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        float f5 = (this.actualRadius * 2.0f) / this.removeBitmapWidth;
        this.removeBitmapMatrix.postScale(f5, f5);
        this.removeBitmapMatrix.postTranslate(this.touchRect.left - ((this.removeBitmapWidth * f5) / 2.0f), this.touchRect.top - ((this.removeBitmapWidth * f5) / 2.0f));
        this.scaleBitmapMatrix.postScale(f5, f5);
        this.scaleBitmapMatrix.postTranslate(this.touchRect.right - ((this.removeBitmapWidth * f5) / 2.0f), this.touchRect.bottom - ((this.removeBitmapWidth * f5) / 2.0f));
        float scale = getScale();
        this.scale = scale;
        float f6 = 1.0f / scale;
        this.scaleBitmapMatrix.postScale(f6, f6, this.touchRect.right, this.touchRect.bottom);
        Matrix matrix = this.removeBitmapMatrix;
        float f7 = 1.0f / this.scale;
        matrix.postScale(f7, f7, this.touchRect.left, this.touchRect.top);
        this.btmW = bitmap.getWidth();
        this.btmH = bitmap.getHeight();
        this.dashPaint.setColor(-7829368);
        this.dashPaint.setStyle(Paint.Style.STROKE);
        float f8 = f / 120.0f;
        f8 = f8 <= 0.0f ? 5.0f : f8;
        this.dashPaint.setStrokeWidth(f8);
        this.dashPaint.setPathEffect(new DashPathEffect(new float[]{f8, f8}, 0.0f));
        Path path = new Path();
        this.dashPathVertical = path;
        path.moveTo(this.btmW / 2.0f, (-this.btmH) / 5.0f);
        this.dashPathVertical.lineTo(this.btmW / 2.0f, (this.btmH * 6.0f) / 5.0f);
        Path path2 = new Path();
        this.dashPathHorizontal = path2;
        path2.moveTo((-this.btmW) / 5.0f, this.btmH / 2.0f);
        this.dashPathHorizontal.lineTo((this.btmW * 6.0f) / 5.0f, this.btmH / 2.0f);
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public boolean isDecorateViewSelected() {
        return this.viewSelected;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public void setDecorateViewSelected(boolean z) {
        this.viewSelected = z;
        invalidate();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public void setMatrix(CtMyMatrix cMMyMatrix) {
        this.stickerData.canvasMatrix.set(cMMyMatrix);
        this.scale = getScale();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public void onDestroy() {
        this.stickerBitmap.recycle();
        this.stickerBitmap = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.setMatrix(this.stickerData.canvasMatrix);
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        float scale = getScale();
        this.scale = scale;
        float f = this.minDimen / (scale * 18.0f);
        this.touchRect.set(this.stickerData.xPos - f, this.stickerData.yPos - f, this.stickerData.xPos + this.textBoundRect.width() + f, this.stickerData.yPos + this.textBoundRect.height() + f);
        float f2 = (this.actualRadius * 2.0f) / this.removeBitmapWidth;
        this.removeBitmapMatrix.postScale(f2, f2);
        this.removeBitmapMatrix.postTranslate(this.touchRect.left - ((this.removeBitmapWidth * f2) / 2.0f), this.touchRect.top - ((this.removeBitmapWidth * f2) / 2.0f));
        this.scaleBitmapMatrix.postScale(f2, f2);
        this.scaleBitmapMatrix.postTranslate(this.touchRect.right - ((this.removeBitmapWidth * f2) / 2.0f), this.touchRect.bottom - ((this.removeBitmapWidth * f2) / 2.0f));
        Matrix matrix = this.scaleBitmapMatrix;
        float f3 = 1.0f / this.scale;
        matrix.postScale(f3, f3, this.touchRect.right, this.touchRect.bottom);
        Matrix matrix2 = this.removeBitmapMatrix;
        float f4 = 1.0f / this.scale;
        matrix2.postScale(f4, f4, this.touchRect.left, this.touchRect.top);
        float f5 = this.actualRadius / this.scale;
        if (this.viewSelected) {
            if (this.isOnTouch) {
                canvas.drawRect(this.touchRect, this.rectPaintOnTouch);
            } else {
                canvas.drawRect(this.touchRect, this.rectPaint);
            }
            canvas.drawCircle(this.touchRect.right, this.touchRect.bottom, f5, this.whitePaint);
            canvas.drawCircle(this.touchRect.left, this.touchRect.top, f5, this.redPaint);
            canvas.drawBitmap(this.scaleBitmap, this.scaleBitmapMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.removeBitmap, this.removeBitmapMatrix, this.bitmapPaint);
        }
        Bitmap bitmap = this.stickerBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(this.stickerBitmap, this.stickerData.xPos, this.stickerData.yPos, this.paint);
        }
        if (this.orthogonal) {
            this.dashPathVertical.offset(this.stickerData.xPos, this.stickerData.yPos, this.dashPathVerticalTemp);
            this.dashPathHorizontal.offset(this.stickerData.xPos, this.stickerData.yPos, this.dashPathHorizontalTemp);
            canvas.drawPath(this.dashPathVerticalTemp, this.dashPaint);
            canvas.drawPath(this.dashPathHorizontalTemp, this.dashPaint);
        }
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public boolean isOnRectCheck(float f, float f2) {
        float width = this.touchRect.width() / 10.0f;
        float height = this.touchRect.height() / 10.0f;
        if (getScale() < MIN_ZOOM * 2.0f) {
            width = -width;
            height = -height;
        }
        if (f <= this.touchRect.left + width || f >= this.touchRect.right - width || f2 <= this.touchRect.top + height || f2 >= this.touchRect.bottom - height) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public float containsScore(float f, float f2) {
        float[] fArr = this.pts;
        fArr[0] = f;
        fArr[1] = f2;
        this.stickerData.canvasMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float[] fArr3 = this.pts;
        float f3 = fArr3[0];
        float f4 = fArr3[1];
        RectF rectF = this.touchRect;
        if (f3 >= rectF.left && f3 <= rectF.right && f4 >= rectF.top && f4 <= rectF.bottom) {
            float centerX = ((f3 - rectF.centerX()) * (f3 - rectF.centerX())) + ((f4 - rectF.centerY()) * (f4 - rectF.centerY()));
            float width = (rectF.width() * rectF.width()) + (rectF.height() * rectF.height());
            if (centerX > 0.0f) {
                return width / centerX;
            }
        }
        return -2.0f;
    }

    boolean isInCircle(float f, float f2) {
        float f3 = ((f - this.touchRect.right) * (f - this.touchRect.right)) + ((f2 - this.touchRect.bottom) * (f2 - this.touchRect.bottom));
        float f4 = this.actualRadius + this.circlePadding;
        float f5 = this.scale;
        if (f3 >= (f4 * f4) / (f5 * f5)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    boolean isOnCross(float f, float f2) {
        float f3 = ((f - this.touchRect.left) * (f - this.touchRect.left)) + ((f2 - this.touchRect.top) * (f2 - this.touchRect.top));
        float f4 = this.actualRadius + this.circlePadding;
        float f5 = this.scale;
        if (f3 >= (f4 * f4) / (f5 * f5)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    boolean isOnCross2(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        float f7 = (f5 * f5) + (f6 * f6);
        float f8 = this.actualRadius + this.circlePadding;
        float f9 = this.scale;
        if (f7 >= (f8 * f8) / (f9 * f9)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        if (this.locked) {
            return false;
        }
        this.mScaleDetector.onTouchEvent(motionEvent);
        this.mRotationDetector.onTouchEvent(motionEvent);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.isOnRect = false;
            this.isInCircle = false;
            this.savedViewSelected = this.viewSelected;
            this.isOnTouch = true;
            float[] fArr = this.pts;
            fArr[0] = x;
            fArr[1] = y;
            this.stickerData.canvasMatrix.invert(this.inverse);
            Matrix matrix = this.inverse;
            float[] fArr2 = this.pts;
            matrix.mapPoints(fArr2, fArr2);
            Log.e(TAG, "ACTION_DOWN savedViewSelected " + this.savedViewSelected);
            Log.e(TAG, "pointer count = " + motionEvent.getPointerCount());
            if (this.viewSelected) {
                float[] fArr3 = this.pts;
                if (isOnCross(fArr3[0], fArr3[1])) {
                    DeleteTextDialog(getContext(), this);
                    return true;
                }
            }
            float[] fArr4 = this.pts;
            this.isOnRect = isOnRectCheck(fArr4[0], fArr4[1]);
            Log.e(TAG, "ACTION_DOWN viewSelected " + this.viewSelected);
            float[] fArr5 = this.pts;
            this.isInCircle = isInCircle(fArr5[0], fArr5[1]);
            this.previousPos.set(x, y);
            this.zoomStart.set(x, y);
            this.pts[0] = this.touchRect.centerX();
            this.pts[1] = this.touchRect.centerY();
            CtMyMatrix cMMyMatrix = this.stickerData.canvasMatrix;
            float[] fArr6 = this.pts;
            cMMyMatrix.mapPoints(fArr6, fArr6);
            float[] fArr7 = this.pts;
            this.startAngle = -pointToAngle(x, y, fArr7[0], fArr7[1]);
            if (this.isInCircle || this.isOnRect) {
                this.textAndStickerViewSelectedListener.setSelectedView(this);
            }
            this.mActivePointerId = motionEvent.getPointerId(0);
            boolean z = this.savedViewSelected;
            if (!z) {
                this.doubleTouchCall = true;
                return z;
            }
        } else if (action == 1) {
            new Handler().postDelayed(new DoubleTouchHandler(), 100L);
            Log.e(TAG, "ACTION_UP");
            this.orthogonal = false;
            this.textAndStickerViewSelectedListener.onTouchUp(this.stickerData);
            if (this.onDecorateViewTouchUpListener != null) {
                this.onDecorateViewTouchUpListener.onTouchUp(this.stickerData);
            }
            this.isOnTouch = false;
            this.isOnRect = false;
            this.mActivePointerId = -1;
        } else if (action != 2) {
            if (action == 5) {
                Log.e(TAG, "ACTION_POINTER_DOWN pointer count = " + motionEvent.getPointerCount());
            } else if (action == 6) {
                this.startAngleGesture = 0.0f;
                int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                if (motionEvent.getPointerId(action2) == this.mActivePointerId) {
                    int i = action2 == 0 ? 1 : 0;
                    if (i >= 0 && i < motionEvent.getPointerCount()) {
                        this.previousPos.set(motionEvent.getX(i), motionEvent.getY(i));
                        this.mActivePointerId = motionEvent.getPointerId(i);
                    }
                }
            }
        } else if (this.isInCircle || !this.isOnRect || (findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId)) < 0 || findPointerIndex >= motionEvent.getPointerCount()) {
            float[] fArr8 = this.pts;
            float f = -pointToAngle(x, y, fArr8[0], fArr8[1]);
            float matrixRotation = getMatrixRotation(this.stickerData.canvasMatrix);
            if ((matrixRotation == 0.0f || matrixRotation == 90.0f || matrixRotation == 180.0f || matrixRotation == -180.0f || matrixRotation == -90.0f) && Math.abs(this.startAngle - f) < 4.0f) {
                this.orthogonal = true;
            } else {
                if (Math.abs((matrixRotation - this.startAngle) + f) < 4.0f) {
                    f = this.startAngle - matrixRotation;
                    this.orthogonal = true;
                } else if (Math.abs(90.0f - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                    f = (this.startAngle + 90.0f) - matrixRotation;
                    this.orthogonal = true;
                } else if (Math.abs(180.0f - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                    f = (this.startAngle + 180.0f) - matrixRotation;
                    this.orthogonal = true;
                } else if (Math.abs((-180.0f) - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                    f = (this.startAngle - 0.024902344f) - matrixRotation;
                    this.orthogonal = true;
                } else if (Math.abs((-90.0f) - ((matrixRotation - this.startAngle) + f)) < 4.0f) {
                    f = (this.startAngle - 0.049804688f) - matrixRotation;
                    this.orthogonal = true;
                } else {
                    this.orthogonal = false;
                }
                float[] fArr9 = this.pts;
                this.stickerData.canvasMatrix.postRotate(this.startAngle - f, fArr9[0], fArr9[1]);
                this.startAngle = f;
            }
            float[] fArr10 = this.pts;
            float sqrt = ((float) Math.sqrt(((x - fArr10[0]) * (x - fArr10[0])) + ((y - fArr10[1]) * (y - fArr10[1])))) / ((float) Math.sqrt(((this.zoomStart.x - this.pts[0]) * (this.zoomStart.x - this.pts[0])) + ((this.zoomStart.y - this.pts[1]) * (this.zoomStart.y - this.pts[1]))));
            float scale = getScale();
            this.scale = scale;
            float f2 = MIN_ZOOM;
            if (scale >= f2 || (scale < f2 && sqrt > 1.0f)) {
                CtMyMatrix cMMyMatrix2 = this.stickerData.canvasMatrix;
                float[] fArr11 = this.pts;
                cMMyMatrix2.postScale(sqrt, sqrt, fArr11[0], fArr11[1]);
                this.zoomStart.set(x, y);
                this.scale = getScale();
            }
        } else {
            float x2 = motionEvent.getX(findPointerIndex);
            float y2 = motionEvent.getY(findPointerIndex);
            this.stickerData.canvasMatrix.postTranslate(x2 - this.previousPos.x, y2 - this.previousPos.y);
            this.previousPos.set(x2, y2);
        }
        boolean onTouchEvent = this.gestureDetector.onTouchEvent(motionEvent);
        postInvalidate();
        return onTouchEvent;
    }

    float getScale() {
        this.stickerData.canvasMatrix.getValues(this.values);
        float[] fArr = this.values;
        float f = fArr[0];
        float f2 = fArr[3];
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }

    public static int pointToAngle(float f, float f2, float f3, float f4) {
        int i = (f > f3 ? 1 : (f == f3 ? 0 : -1));
        char c = i > 0 ? (char) 1 : i == 0 ? (char) 0 : (char) 65535;
        if (c < 0 || f2 >= f4) {
            if (c <= 0 || f2 < f4) {
                if (f > f3 || f2 <= f4) {
                    if (c >= 0 || f2 > f4) {
                        return 0;
                    }
                    return ((int) Math.toDegrees(Math.atan((f4 - f2) / (f3 - f)))) + HydraVpnTransportException.HYDRA_ERROR_CONFIGURATION;
                }
                return ((int) Math.toDegrees(Math.atan((f3 - f) / (f2 - f4)))) + 90;
            }
            return (int) Math.toDegrees(Math.atan((f2 - f4) / (f - f3)));
        }
        return ((int) Math.toDegrees(Math.atan((f - f3) / (f4 - f2)))) + 270;
    }

    float getMatrixRotation(Matrix matrix) {
        matrix.getValues(this.f1374v);
        float[] fArr = this.f1374v;
        return (float) Math.round(Math.atan2(fArr[1], fArr[0]) * 57.29577951308232d);
    }
}
