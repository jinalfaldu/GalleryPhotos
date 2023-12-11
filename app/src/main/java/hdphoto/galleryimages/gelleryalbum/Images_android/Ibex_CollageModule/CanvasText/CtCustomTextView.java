package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

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
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MotionEventCompat;
import hdphoto.galleryimages.gelleryalbum.R;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Anadol_RotationGestureDetector;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_TextSticker;


public class CtCustomTextView extends CtDecorateView {
    static final float MIN_ZOOM = 0.8f;
    static int SNAP_CIRCLE_0 = 0;
    static int SNAP_CIRCLE_1 = 1;
    static int SNAP_CIRCLE_2 = 2;
    static int SNAP_CIRCLE_NONE = -1;
    static final String TAG = "CanvasTextView";
    public static Paint paintSnap = new Paint(1);
    public static boolean viewSelected = false;
    float actualRadius;
    Paint bitmapPaint;
    float bitmapWidth;
    Bitmap blackBarBitmap;
    float circlePadding;
    Paint dashPaint;
    Path dashPathHorizontal;
    Path dashPathVertical;
    boolean doubleTouchCall;
    boolean downModeSnap;
    Bitmap editBitmap;
    float[] f1373v;
    GestureDetector gestureDetector;
    Matrix inverse;
    boolean isInCircle;
    boolean isOnRect;
    boolean isOnTouch;
    Rect lineBound;
    boolean locked;
    int mActivePointerId;
    Anadol_RotationGestureDetector mRotationDetector;
    ScaleGestureDetector mScaleDetector;
    float mScaleFactor;
    boolean onRectSnap;
    boolean orthogonal;
    float paddingHeight;
    float paddingWidth;
    Paint paintBg;
    Paint paintGreen;
    PointF previousPos;
    float previousY;
    float previousYSnap;
    float[] pts;
    private RectF rectBackground;
    RectF rectBg;
    Paint rectPaint;
    Paint rectPaintBackground;
    Paint rectPaintOnTouch;
    RectF rectSnap;
    RectF rectText;
    Paint redPaint;
    Bitmap removeBitmap;
    Matrix removeBitmapMatrix;
    CustomRelativeLayout.RemoveTextListener removeTextListener;
    PointF res;
    Anadol_RotationGestureDetector.OnRotationGestureListener rotateListener;
    boolean savedViewSelected;
    float scale;
    Bitmap scaleBitmap;
    Matrix scaleBitmapMatrix;
    Matrix scaleBitmapSwitch;
    float screenHeight;
    float screenWidth;
    SingleTap singleTapListener;
    float startAngle;
    float startAngleGesture;
    Bitmap switchBitmap;
    TextAndStickerViewSelectedListener textAndStickerViewSelectedListener;
    Rect textBoundRect;
    CtTextData textData;
    float[] values;
    ViewSelectedListener viewSelectedListener;
    ViewSelectedListener viewSelectedListenerEx;
    Paint whitePaint;
    PointF zoomStart;

    
    public interface TextAndStickerViewSelectedListener {
        void onTouchUp(BaseData cMBaseData);

        void setSelectedView(CtDecorateView cMDecorateView);
    }

    
    class DoubleTouchHandler implements Runnable {
        DoubleTouchHandler() {
        }

        @Override 
        public void run() {
            CtCustomTextView.this.doubleTouchCall = false;
        }
    }

    
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            if (CtCustomTextView.this.textData.getSnapMode() && CtCustomTextView.this.onRectSnap) {
                return true;
            }
            if (CtCustomTextView.this.textData.getSnapMode() && !CtCustomTextView.this.onRectSnap) {
                CtCustomTextView.viewSelected = false;
                return false;
            } else if (CtCustomTextView.this.isInCircle || CtCustomTextView.this.isOnRect) {
                return true;
            } else {
                CtCustomTextView.viewSelected = false;
                return false;
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (CtCustomTextView.this.locked) {
                return false;
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (!CtCustomTextView.this.textData.getSnapMode()) {
                Log.d("Single Tap", "Tapped at");
                CtCustomTextView.this.pts[0] = motionEvent.getX();
                CtCustomTextView.this.pts[1] = motionEvent.getY();
                CtCustomTextView.this.textData.canvasMatrix.invert(CtCustomTextView.this.inverse);
                CtCustomTextView.this.inverse.mapPoints(CtCustomTextView.this.pts, CtCustomTextView.this.pts);
                CtCustomTextView cMCustomTextView = CtCustomTextView.this;
                cMCustomTextView.isOnRect = cMCustomTextView.isOnRectCheck(cMCustomTextView.pts[0], CtCustomTextView.this.pts[1]);
                if (CtCustomTextView.this.isOnRect) {
                    Log.d(CtCustomTextView.TAG, "onSingleTapUp doubleSavedViewSelected " + CtCustomTextView.this.doubleTouchCall);
                    if (CtCustomTextView.this.doubleTouchCall) {
                        CtCustomTextView.viewSelected = true;
                    } else {
                        CtCustomTextView.viewSelected = !CtCustomTextView.this.savedViewSelected;
                    }
                    CtCustomTextView.this.doubleTouchCall = false;
                } else {
                    CtCustomTextView.viewSelected = false;
                }
                return CtCustomTextView.this.isInCircle || CtCustomTextView.this.isOnRect;
            } else if (x <= CtCustomTextView.this.rectSnap.left || x >= CtCustomTextView.this.rectSnap.right || y <= CtCustomTextView.this.rectSnap.top || y >= CtCustomTextView.this.rectSnap.bottom) {
                CtCustomTextView.viewSelected = false;
                CtCustomTextView.this.onRectSnap = false;
                return false;
            } else {
                CtCustomTextView.this.onRectSnap = true;
                CtCustomTextView.viewSelected = true;
                return true;
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (CtCustomTextView.this.locked) {
                return false;
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (!CtCustomTextView.this.textData.getSnapMode()) {
                CtCustomTextView.this.pts[0] = motionEvent.getX();
                CtCustomTextView.this.pts[1] = motionEvent.getY();
                CtCustomTextView.this.textData.canvasMatrix.invert(CtCustomTextView.this.inverse);
                CtCustomTextView.this.inverse.mapPoints(CtCustomTextView.this.pts, CtCustomTextView.this.pts);
                CtCustomTextView cMCustomTextView = CtCustomTextView.this;
                cMCustomTextView.isOnRect = cMCustomTextView.isOnRectCheck(cMCustomTextView.pts[0], CtCustomTextView.this.pts[1]);
                if (CtCustomTextView.this.isOnRect) {
                    CtCustomTextView.viewSelected = true;
                    CtCustomTextView.this.SingleTapped();
                } else {
                    CtCustomTextView.viewSelected = false;
                }
                return true;
            } else if (x <= CtCustomTextView.this.rectSnap.left || x >= CtCustomTextView.this.rectSnap.right || y <= CtCustomTextView.this.rectSnap.top || y >= CtCustomTextView.this.rectSnap.bottom) {
                CtCustomTextView.viewSelected = false;
                CtCustomTextView.this.onRectSnap = false;
                return false;
            } else {
                CtCustomTextView.this.SingleTapped();
                CtCustomTextView.this.onRectSnap = true;
                CtCustomTextView.viewSelected = true;
                return true;
            }
        }
    }

    
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            CtCustomTextView.this.mScaleFactor = scaleGestureDetector.getScaleFactor();
            if (scaleGestureDetector.isInProgress()) {
                CtCustomTextView.this.pts[0] = CtCustomTextView.this.rectText.centerX();
                CtCustomTextView.this.pts[1] = CtCustomTextView.this.rectText.centerY();
                CtCustomTextView.this.textData.canvasMatrix.mapPoints(CtCustomTextView.this.pts, CtCustomTextView.this.pts);
                CtCustomTextView.this.mScaleFactor = scaleGestureDetector.getScaleFactor();
                CtCustomTextView cMCustomTextView = CtCustomTextView.this;
                cMCustomTextView.mScaleFactor = Math.max((float) CtCustomTextView.MIN_ZOOM, cMCustomTextView.mScaleFactor);
                CtCustomTextView.this.textData.canvasMatrix.postScale(CtCustomTextView.this.mScaleFactor, CtCustomTextView.this.mScaleFactor, CtCustomTextView.this.pts[0], CtCustomTextView.this.pts[1]);
                CtCustomTextView cMCustomTextView2 = CtCustomTextView.this;
                cMCustomTextView2.scale = cMCustomTextView2.GetScale();
                CtCustomTextView.this.invalidate();
            } else {
                CtCustomTextView.this.pts[0] = CtCustomTextView.this.rectText.centerX();
                CtCustomTextView.this.pts[1] = CtCustomTextView.this.rectText.centerY();
                CtCustomTextView.this.textData.canvasMatrix.mapPoints(CtCustomTextView.this.pts, CtCustomTextView.this.pts);
                CtCustomTextView.this.mScaleFactor = scaleGestureDetector.getScaleFactor();
                CtCustomTextView cMCustomTextView3 = CtCustomTextView.this;
                cMCustomTextView3.mScaleFactor = Math.max((float) CtCustomTextView.MIN_ZOOM, cMCustomTextView3.mScaleFactor);
                CtCustomTextView.this.textData.canvasMatrix.postScale(CtCustomTextView.this.mScaleFactor, CtCustomTextView.this.mScaleFactor, CtCustomTextView.this.pts[0], CtCustomTextView.this.pts[1]);
                CtCustomTextView cMCustomTextView4 = CtCustomTextView.this;
                cMCustomTextView4.scale = cMCustomTextView4.GetScale();
                CtCustomTextView.this.invalidate();
            }
            return true;
        }
    }

    
    public class RGListener implements Anadol_RotationGestureDetector.OnRotationGestureListener {
        RGListener() {
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMRotationGestureDetector.OnRotationGestureListener
        public void OnRotation(Anadol_RotationGestureDetector cMRotationGestureDetector) {
            float angle = cMRotationGestureDetector.getAngle();
            CtCustomTextView cMCustomTextView = CtCustomTextView.this;
            float GetMatrixRotation = cMCustomTextView.GetMatrixRotation(cMCustomTextView.textData.canvasMatrix);
            if ((GetMatrixRotation == 0.0f || GetMatrixRotation == 90.0f || GetMatrixRotation == 180.0f || GetMatrixRotation == -180.0f || GetMatrixRotation == -90.0f) && Math.abs(CtCustomTextView.this.startAngleGesture - angle) < 4.0f) {
                CtCustomTextView.this.orthogonal = true;
                return;
            }
            if (Math.abs((GetMatrixRotation - CtCustomTextView.this.startAngleGesture) + angle) < 4.0f) {
                angle = CtCustomTextView.this.startAngleGesture - GetMatrixRotation;
                CtCustomTextView.this.orthogonal = true;
            } else if (Math.abs(90.0f - ((GetMatrixRotation - CtCustomTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (CtCustomTextView.this.startAngleGesture + 90.0f) - GetMatrixRotation;
                CtCustomTextView.this.orthogonal = true;
            } else if (Math.abs(180.0f - ((GetMatrixRotation - CtCustomTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (CtCustomTextView.this.startAngleGesture + 180.0f) - GetMatrixRotation;
                CtCustomTextView.this.orthogonal = true;
            } else if (Math.abs((-180.0f) - ((GetMatrixRotation - CtCustomTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (CtCustomTextView.this.startAngleGesture - 0.024902344f) - GetMatrixRotation;
                CtCustomTextView.this.orthogonal = true;
            } else if (Math.abs((-90.0f) - ((GetMatrixRotation - CtCustomTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (CtCustomTextView.this.startAngleGesture - 0.049804688f) - GetMatrixRotation;
                CtCustomTextView.this.orthogonal = true;
            } else {
                CtCustomTextView.this.orthogonal = false;
            }
            CtCustomTextView.this.pts[0] = CtCustomTextView.this.rectText.centerX();
            CtCustomTextView.this.pts[1] = CtCustomTextView.this.rectText.centerY();
            CtCustomTextView.this.textData.canvasMatrix.mapPoints(CtCustomTextView.this.pts, CtCustomTextView.this.pts);
            CtCustomTextView.this.textData.canvasMatrix.postRotate(CtCustomTextView.this.startAngleGesture - angle, CtCustomTextView.this.pts[0], CtCustomTextView.this.pts[1]);
            CtCustomTextView.this.startAngleGesture = angle;
            CtCustomTextView.this.invalidate();
        }
    }

    public void setSingleTapListener(SingleTap singleTap) {
        this.singleTapListener = singleTap;
    }

    public void setViewSelectedListener(ViewSelectedListener viewSelectedListener) {
        this.viewSelectedListenerEx = viewSelectedListener;
    }

    public void setTextAndStickerViewSelectedListener(TextAndStickerViewSelectedListener textAndStickerViewSelectedListener) {
        this.textAndStickerViewSelectedListener = textAndStickerViewSelectedListener;
    }

    public void setTextSelected(boolean z) {
        viewSelected = z;
        postInvalidate();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public BaseData getData() {
        return this.textData;
    }

    public CtCustomTextView(Context context, CtTextData cMTextData, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5) {
        super(context);
        Typeface typeFace;
        this.paddingHeight = 30.0f;
        this.paddingWidth = 10.0f;
        this.bitmapPaint = new Paint(1);
        this.dashPaint = new Paint();
        this.doubleTouchCall = false;
        this.downModeSnap = false;
        this.inverse = new Matrix();
        this.isInCircle = false;
        this.isOnRect = false;
        this.isOnTouch = false;
        this.lineBound = new Rect();
        this.locked = false;
        this.mActivePointerId = -1;
        this.mScaleFactor = 1.0f;
        this.onRectSnap = false;
        this.orthogonal = false;
        this.paintBg = new Paint(1);
        this.paintGreen = new Paint(1);
        this.previousPos = new PointF();
        this.pts = new float[2];
        this.rectBg = new RectF();
        this.rectSnap = new RectF();
        this.redPaint = new Paint(1);
        this.removeBitmapMatrix = new Matrix();
        this.res = new PointF();
        this.rotateListener = new RGListener();
        this.savedViewSelected = false;
        this.scaleBitmapMatrix = new Matrix();
        this.scaleBitmapSwitch = new Matrix();
        this.startAngle = 0.0f;
        this.startAngleGesture = 0.0f;
        this.f1373v = new float[9];
        this.values = new float[9];
        this.whitePaint = new Paint(1);
        this.zoomStart = new PointF();
        this.mRotationDetector = new Anadol_RotationGestureDetector(this.rotateListener);
        this.screenWidth = getResources().getDisplayMetrics().widthPixels;
        this.screenHeight = getResources().getDisplayMetrics().heightPixels;
        this.rectPaintBackground = new Paint(1);
        this.textBoundRect = new Rect();
        this.actualRadius = 30.0f;
        this.circlePadding = 5.0f;
        this.scale = 1.0f;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        float dimension = context.getResources().getDimension(R.dimen.myFontSize);
        Paint paint = new Paint(1);
        this.rectPaint = paint;
        paint.setColor(2006555033);
        this.redPaint.setColor(-2140327);
        this.paintGreen.setColor(-1722294439);
        this.whitePaint.setColor(-1460137);
        this.bitmapPaint.setFilterBitmap(true);
        Paint paint2 = new Paint(1);
        this.rectPaintOnTouch = paint2;
        paint2.setColor(-16777216);
        if (cMTextData == null) {
            CtTextData cMTextData2 = new CtTextData(dimension);
            this.textData = cMTextData2;
            cMTextData2.textPaint.getTextBounds(CtTextData.defaultMessage, 0, 12, this.textBoundRect);
            this.textData.xPos = (this.screenWidth / 2.0f) - (this.textBoundRect.width() / 2);
            this.textData.yPos = this.screenHeight / 3.0f;
        } else {
            this.textData = cMTextData;
            if (cMTextData.getFontPath() != null && (typeFace = CtFontCache.getTypeFace(context, this.textData.getFontPath())) != null) {
                this.textData.textPaint.setTypeface(typeFace);
            }
            this.textData.textPaint.getTextBounds(this.textData.message, 0, this.textData.message.length(), this.textBoundRect);
            if (this.textData.getTextBackgroundColor() != -1) {
                this.rectPaintBackground.setColor(this.textData.getTextBackgroundColor());
            }
            if (this.textData.getShadowData() != null) {
                this.textData.textPaint.setShadowLayer(this.textData.getShadowData().getRadius(), this.textData.getShadowData().getDx(), this.textData.getShadowData().getDy(), this.textData.getShadowData().getColor());
            }
        }
        this.rectText = new RectF(this.textData.xPos - this.paddingWidth, (this.textData.yPos - this.textBoundRect.height()) - this.paddingHeight, this.textData.xPos + this.textBoundRect.width() + (this.paddingWidth * 2.0f), this.textData.yPos + this.paddingHeight);
        this.rectBackground = new RectF(this.textData.xPos - this.paddingWidth, (this.textData.yPos - this.textBoundRect.height()) - (this.paddingWidth * 2.0f), this.textData.xPos + this.textBoundRect.width() + (this.paddingWidth * 2.0f), this.textData.yPos + this.paddingWidth);
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        float min = Math.min(this.screenWidth, this.screenHeight);
        float f = this.actualRadius;
        this.circlePadding = f / 2.0f;
        if (f <= 5.0f) {
            this.actualRadius = this.paddingHeight;
        }
        this.removeBitmap = bitmap;
        this.scaleBitmap = bitmap2;
        this.editBitmap = bitmap3;
        this.switchBitmap = bitmap4;
        this.blackBarBitmap = bitmap5;
        this.bitmapWidth = bitmap.getWidth();
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        this.scaleBitmapSwitch.reset();
        float f2 = (this.actualRadius * 2.0f) / this.bitmapWidth;
        this.removeBitmapMatrix.postScale(f2, f2);
        this.removeBitmapMatrix.postTranslate(this.rectText.left - ((this.bitmapWidth * f2) / 2.0f), this.rectText.top - ((this.bitmapWidth * f2) / 2.0f));
        this.scaleBitmapMatrix.postScale(f2, f2);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - ((this.bitmapWidth * f2) / 2.0f), this.rectText.bottom - ((this.bitmapWidth * f2) / 2.0f));
        this.scaleBitmapSwitch.postScale(f2, f2);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - ((this.bitmapWidth * f2) / 2.0f), this.rectText.top - ((this.bitmapWidth * f2) / 2.0f));
        float GetScale = GetScale();
        this.scale = GetScale;
        float f3 = 1.0f / GetScale;
        this.scaleBitmapMatrix.postScale(f3, f3, this.rectText.right, this.rectText.bottom);
        Matrix matrix = this.removeBitmapMatrix;
        float f4 = 1.0f / this.scale;
        matrix.postScale(f4, f4, this.rectText.left, this.rectText.top);
        Matrix matrix2 = this.scaleBitmapSwitch;
        float f5 = 1.0f / this.scale;
        matrix2.postScale(f5, f5, this.rectText.right, this.rectText.top);
        float width = this.rectText.width();
        float height = this.rectText.height();
        this.dashPaint.setColor(-7829368);
        this.dashPaint.setStyle(Paint.Style.STROKE);
        float f6 = min / 125.0f;
        float f7 = f6 > 0.0f ? f6 : 5.0f;
        this.dashPaint.setStrokeWidth(f7);
        this.dashPaint.setPathEffect(new DashPathEffect(new float[]{f7, f7}, 0.0f));
        this.dashPathVertical = new Path();
        DotLinePathReset(width, height);
        SetSnapText();
    }

    void DotLinePathReset(float f, float f2) {
        this.dashPathVertical.reset();
        float f3 = f / 2.0f;
        this.dashPathVertical.moveTo(this.rectText.left + f3, this.rectText.top - (f2 / 5.0f));
        this.dashPathVertical.lineTo(this.rectText.left + f3, this.rectText.top + ((f2 * 6.0f) / 5.0f));
        Path path = new Path();
        this.dashPathHorizontal = path;
        float f4 = f2 / 2.0f;
        path.moveTo(this.rectText.left + ((-f) / 5.0f), this.rectText.top + f4);
        this.dashPathHorizontal.lineTo(this.rectText.left + ((f * 6.0f) / 5.0f), this.rectText.top + f4);
    }

    public void setAlignment(int i) {
        Paint.Align align = Paint.Align.LEFT;
        if (i == 1) {
            align = Paint.Align.CENTER;
        }
        if (i == 2) {
            align = Paint.Align.RIGHT;
        }
        this.textData.textPaint.setTextAlign(align);
        invalidate();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public boolean isDecorateViewSelected() {
        return viewSelected;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public void setDecorateViewSelected(boolean z) {
        viewSelected = z;
        invalidate();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public void setMatrix(CtMyMatrix cMMyMatrix) {
        this.textData.canvasMatrix = cMMyMatrix;
        this.scale = GetScale();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        ResetRectPosition();
        float f = (this.actualRadius * 2.0f) / this.bitmapWidth;
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        this.scaleBitmapSwitch.reset();
        this.removeBitmapMatrix.postScale(f, f);
        this.removeBitmapMatrix.postTranslate(this.rectText.left - ((this.bitmapWidth * f) / 2.0f), this.rectText.top - ((this.bitmapWidth * f) / 2.0f));
        this.scaleBitmapMatrix.postScale(f, f);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - ((this.bitmapWidth * f) / 2.0f), this.rectText.bottom - ((this.bitmapWidth * f) / 2.0f));
        this.scaleBitmapSwitch.postScale(f, f);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - ((this.bitmapWidth * f) / 2.0f), this.rectText.top - ((this.bitmapWidth * f) / 2.0f));
        float GetScale = GetScale();
        this.scale = GetScale;
        float f2 = 1.0f / GetScale;
        this.scaleBitmapMatrix.postScale(f2, f2, this.rectText.right, this.rectText.bottom);
        Matrix matrix = this.removeBitmapMatrix;
        float f3 = 1.0f / this.scale;
        matrix.postScale(f3, f3, this.rectText.left, this.rectText.top);
        Matrix matrix2 = this.scaleBitmapSwitch;
        float f4 = 1.0f / this.scale;
        matrix2.postScale(f4, f4, this.rectText.right, this.rectText.top);
        canvas.setMatrix(this.textData.canvasMatrix);
        if (viewSelected) {
            if (this.isOnTouch) {
                canvas.drawRect(this.rectText, this.rectPaintOnTouch);
            } else {
                canvas.drawRect(this.rectText, this.rectPaint);
            }
            float f5 = this.actualRadius / this.scale;
            canvas.drawCircle(this.rectText.right, this.rectText.bottom, f5, this.whitePaint);
            canvas.drawCircle(this.rectText.left, this.rectText.top, f5, this.redPaint);
            canvas.drawBitmap(this.scaleBitmap, this.scaleBitmapMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.removeBitmap, this.removeBitmapMatrix, this.bitmapPaint);
        }
        if (this.textData.getBackgroundColorValue() != -1) {
            canvas.drawRect(this.rectBackground, this.rectPaintBackground);
        }
        DrawMultiline(canvas, this.textData.message, this.textData.xPos, this.textData.yPos, this.textData.textPaint, this.textData, this.lineBound, this.rectBg, this.paintBg);
        if (this.orthogonal) {
            canvas.drawPath(this.dashPathVertical, this.dashPaint);
            canvas.drawPath(this.dashPathHorizontal, this.dashPaint);
        }
    }

    void SetSnapText() {
        SetRectSnap(this.textData, this.rectSnap, this.screenWidth);
        paintSnap.setColor(-2030043136);
        if (this.textData.yPosSnap == 0.0f) {
            this.textData.yPosSnap = this.screenHeight / 2.0f;
        }
    }

    public static void SetRectSnap(CtTextData cMTextData, RectF rectF, float f) {
        int GetTextHeight = GetTextHeight(cMTextData);
        float GetSnapRectPadding = GetSnapRectPadding(cMTextData);
        float f2 = cMTextData.yPosSnap + (GetTextHeight / 2);
        rectF.set(0.0f, (f2 - GetTextHeight) - GetSnapRectPadding, f, f2 + GetSnapRectPadding);
    }

    int isInCircleSnap(float f, float f2) {
        float f3 = this.actualRadius;
        float f4 = this.circlePadding + f3;
        float width = this.rectSnap.left + (this.rectSnap.width() / 2.0f);
        float f5 = this.rectSnap.bottom + f3;
        float f6 = 3.0f * f3;
        float f7 = f3 * (-3.0f);
        float f8 = f - (width - f6);
        float f9 = f2 - f5;
        float f10 = f9 * f9;
        float f11 = f4 * f4;
        if ((f8 * f8) + f10 < f11) {
            viewSelected = true;
            return SNAP_CIRCLE_0;
        }
        float f12 = f - (width - 0.0f);
        if ((f12 * f12) + f10 < f11) {
            viewSelected = true;
            return SNAP_CIRCLE_1;
        }
        float f13 = f - (width - f7);
        if ((f13 * f13) + f10 >= f11) {
            return SNAP_CIRCLE_NONE;
        }
        viewSelected = true;
        return SNAP_CIRCLE_2;
    }

    public static float GetSnapRectPadding(CtTextData cMTextData) {
        return ((-cMTextData.textPaint.ascent()) + cMTextData.textPaint.descent()) / 4.7f;
    }

    public static int GetTextHeight(CtTextData cMTextData) {
        String[] split;
        int i = 0;
        for (String str : cMTextData.message.split("\n")) {
            i = (int) (i + (-cMTextData.textPaint.ascent()) + cMTextData.textPaint.descent());
        }
        return i;
    }

    float ResetRectPosition() {
        this.paintBg.setColor(this.textData.getTextBackgroundColor());
        GetHeightAndMaxLength(this.textData, this.lineBound, this.res);
        float f = this.res.x;
        float f2 = this.res.y;
        float f3 = this.screenWidth;
        this.paddingWidth = f3 / 15.0f;
        this.paddingHeight = f3 / 14.0f;
        this.rectText.set(this.textData.xPos - this.paddingWidth, ((this.textData.yPos - this.textBoundRect.height()) - this.paddingHeight) + f2, this.textData.xPos + f + this.paddingWidth, this.textData.yPos + this.paddingHeight);
        float f4 = this.screenWidth / 30.0f;
        this.paddingWidth = f4;
        this.paddingHeight = f4;
        this.rectBg.set(this.textData.xPos - this.paddingWidth, ((this.textData.yPos - this.textBoundRect.height()) - this.paddingHeight) + f2, this.textData.xPos + f + this.paddingWidth, this.textData.yPos + this.paddingHeight + this.textData.textPaint.descent());
        DotLinePathReset(this.rectText.width(), this.rectText.height());
        return -f2;
    }

    static void GetHeightAndMaxLength(CtTextData cMTextData, Rect rect, PointF pointF) {
        String[] split;
        float f = 0.0f;
        float f2 = 0.0f;
        for (String str : cMTextData.message.split("\n")) {
            f2 -= (-cMTextData.textPaint.ascent()) + cMTextData.textPaint.descent();
            cMTextData.textPaint.getTextBounds(str, 0, str.length(), rect);
            if (rect.width() > f) {
                f = rect.width() + (rect.left * 2);
            }
        }
        pointF.set(f, f2 + (-cMTextData.textPaint.ascent()) + cMTextData.textPaint.descent());
    }

    public static void DrawMultiline(Canvas canvas, String str, float f, float f2, Paint paint, CtTextData cMTextData, Rect rect, RectF rectF, Paint paint2) {
        String[] split;
        float f3 = f2;
        int i = 0;
        for (String str2 : str.split("\n")) {
            f3 -= (-paint.ascent()) + paint.descent();
            cMTextData.textPaint.getTextBounds(str2, 0, str2.length(), rect);
            if (rect.width() > i) {
                i = rect.width();
            }
        }
        float descent = f3 + (-paint.ascent()) + paint.descent();
        Paint.Align textAlign = cMTextData.textPaint.getTextAlign();
        float f4 = textAlign == Paint.Align.RIGHT ? f + i : f;
        if (textAlign == Paint.Align.CENTER) {
            f4 += i / 2;
        }
        if (rectF != null && paint2 != null) {
            canvas.drawRect(rectF, paint2);
        }
        for (String str3 : str.split("\n")) {
            canvas.drawText(str3, f4, descent, paint);
            descent += (-paint.ascent()) + paint.descent();
        }
    }

    public void SingleTapped() {
        SingleTap singleTap = this.singleTapListener;
        if (singleTap != null) {
            singleTap.onSingleTap(this.textData);
        }
    }

    public void SetTextColor(int i) {
        this.textData.textPaint.setColor(i);
        postInvalidate();
    }

    public void setMessage(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            this.textData.message = CtTextData.defaultMessage;
        } else {
            this.textData.message = charSequence.toString();
        }
        float f = this.rectText.right;
        RectF rectF = this.rectText;
        rectF.right = rectF.left + this.textData.textPaint.measureText(this.textData.message) + (this.paddingWidth * 2.0f);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - f, 0.0f);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - f, 0.0f);
        postInvalidate();
    }

    public void SetNewTextData(CtTextData cMTextData) {
        float f = this.rectText.right;
        RectF rectF = this.rectText;
        rectF.right = rectF.left + cMTextData.textPaint.measureText(cMTextData.message) + (this.paddingWidth * 2.0f);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - f, 0.0f);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - f, 0.0f);
        postInvalidate();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public boolean isOnRectCheck(float f, float f2) {
        if (f <= this.rectText.left || f >= this.rectText.right || f2 <= this.rectText.top || f2 >= this.rectText.bottom) {
            return false;
        }
        viewSelected = true;
        return true;
    }

    boolean isInCircleCheck(float f, float f2) {
        float f3 = ((f - this.rectText.right) * (f - this.rectText.right)) + ((f2 - this.rectText.bottom) * (f2 - this.rectText.bottom));
        float f4 = this.actualRadius + this.circlePadding;
        float f5 = this.scale;
        if (f3 >= (f4 * f4) / (f5 * f5)) {
            return false;
        }
        viewSelected = true;
        return true;
    }

    boolean isInSecondCircleCheck(float f, float f2) {
        float f3 = ((f - this.rectText.right) * (f - this.rectText.right)) + ((f2 - this.rectText.top) * (f2 - this.rectText.top));
        float f4 = this.actualRadius + this.circlePadding;
        float f5 = this.scale;
        if (f3 >= (f4 * f4) / (f5 * f5)) {
            return false;
        }
        viewSelected = true;
        return true;
    }

    boolean isOnCrossCheck(float f, float f2) {
        float f3 = ((f - this.rectText.left) * (f - this.rectText.left)) + ((f2 - this.rectText.top) * (f2 - this.rectText.top));
        float f4 = this.actualRadius + this.circlePadding;
        float f5 = this.scale;
        if (f3 >= (f4 * f4) / (f5 * f5)) {
            return false;
        }
        viewSelected = true;
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        TextAndStickerViewSelectedListener textAndStickerViewSelectedListener;
        TextAndStickerViewSelectedListener textAndStickerViewSelectedListener2;
        if (this.locked) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int r1 = 0;
        if (this.textData.getSnapMode()) {
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                this.downModeSnap = true;
                int i = SNAP_CIRCLE_NONE;
                if (viewSelected) {
                    i = isInCircleSnap(x, y);
                    if (i == SNAP_CIRCLE_0) {
                        DeleteTextDialog(getContext(), this);
                        return true;
                    } else if (i == SNAP_CIRCLE_1) {
                        SingleTapped();
                        return true;
                    } else if (i == SNAP_CIRCLE_2) {
                        this.textData.setSnapMode(false);
                        invalidate();
                        return true;
                    }
                }
                if (x > this.rectSnap.left && x < this.rectSnap.right && y > this.rectSnap.top && y < this.rectSnap.bottom) {
                    r1 = 1;
                }
                if (r1 != 0) {
                    this.onRectSnap = true;
                    viewSelected = true;
                }
                this.previousY = y;
                this.previousYSnap = this.textData.yPosSnap;
                if ((r1 != 0 || i != SNAP_CIRCLE_NONE) && (textAndStickerViewSelectedListener2 = this.textAndStickerViewSelectedListener) != null) {
                    textAndStickerViewSelectedListener2.setSelectedView(this);
                } else if (this.isInCircle || this.isOnRect) {
                    this.viewSelectedListener.setSelectedView(this);
                }
            } else if (action == 1) {
                this.onRectSnap = false;
                if (this.onDecorateViewTouchUpListener != null) {
                    this.onDecorateViewTouchUpListener.onTouchUp(this.textData);
                }
            } else if (action == 2 && this.downModeSnap && this.onRectSnap) {
                this.textData.yPosSnap = (this.previousYSnap + y) - this.previousY;
            }
            invalidate();
            return this.gestureDetector.onTouchEvent(motionEvent);
        }
        this.mScaleDetector.onTouchEvent(motionEvent);
        this.mRotationDetector.onTouchEvent(motionEvent);
        int action2 = motionEvent.getAction() & 255;
        if (action2 == 0) {
            this.isOnRect = false;
            this.isInCircle = false;
            this.downModeSnap = false;
            this.isOnTouch = true;
            this.savedViewSelected = viewSelected;
            float[] fArr = this.pts;
            fArr[0] = x;
            fArr[1] = y;
            this.textData.canvasMatrix.invert(this.inverse);
            Matrix matrix = this.inverse;
            float[] fArr2 = this.pts;
            matrix.mapPoints(fArr2, fArr2);
            if (viewSelected) {
                float[] fArr3 = this.pts;
                if (isOnCrossCheck(fArr3[0], fArr3[1])) {
                    DeleteTextDialog(getContext(), this);
                    return true;
                }
                float[] fArr4 = this.pts;
                if (isInSecondCircleCheck(fArr4[0], fArr4[1])) {
                    this.textData.setSnapMode(true);
                    invalidate();
                    return true;
                }
            }
            float[] fArr5 = this.pts;
            this.isOnRect = isOnRectCheck(fArr5[0], fArr5[1]);
            float[] fArr6 = this.pts;
            this.isInCircle = isInCircleCheck(fArr6[0], fArr6[1]);
            this.previousPos.set(x, y);
            this.zoomStart.set(x, y);
            this.pts[0] = this.rectText.centerX();
            this.pts[1] = this.rectText.centerY();
            CtMyMatrix cMMyMatrix = this.textData.canvasMatrix;
            float[] fArr7 = this.pts;
            cMMyMatrix.mapPoints(fArr7, fArr7);
            float[] fArr8 = this.pts;
            this.startAngle = -Ledl_TextSticker.pointToAngle(x, y, fArr8[0], fArr8[1]);
            if ((this.isInCircle || this.isOnRect) && (textAndStickerViewSelectedListener = this.textAndStickerViewSelectedListener) != null) {
                textAndStickerViewSelectedListener.setSelectedView(this);
            }
            this.mActivePointerId = motionEvent.getPointerId(0);
            boolean z = this.savedViewSelected;
            if (!z) {
                this.doubleTouchCall = true;
                return z;
            }
        } else if (action2 == 1) {
            new Handler().postDelayed(new DoubleTouchHandler(), 100L);
            this.startAngleGesture = 0.0f;
            this.orthogonal = false;
            this.isOnTouch = false;
            this.isOnRect = false;
            if (this.onDecorateViewTouchUpListener != null) {
                this.onDecorateViewTouchUpListener.onTouchUp(this.textData);
            }
            this.mActivePointerId = -1;
        } else if (action2 != 2) {
            if (action2 == 6) {
                this.startAngleGesture = 0.0f;
                int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                if (motionEvent.getPointerId(action3) == this.mActivePointerId) {
                    r1 = action3 == 0 ? 1 : 0;
                    if (r1 >= 0 && r1 < motionEvent.getPointerCount()) {
                        this.previousPos.set(motionEvent.getX(r1), motionEvent.getY(r1));
                        this.mActivePointerId = motionEvent.getPointerId(r1);
                    }
                }
            }
        } else if (!this.downModeSnap) {
            if (this.isInCircle || !this.isOnRect || (findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId)) < 0 || findPointerIndex >= motionEvent.getPointerCount()) {
                float[] fArr9 = this.pts;
                float f = -Ledl_TextSticker.pointToAngle(x, y, fArr9[0], fArr9[1]);
                float GetMatrixRotation = GetMatrixRotation(this.textData.canvasMatrix);
                if ((GetMatrixRotation == 0.0f || GetMatrixRotation == 90.0f || GetMatrixRotation == 180.0f || GetMatrixRotation == -180.0f || GetMatrixRotation == -90.0f) && Math.abs(this.startAngle - f) < 4.0f) {
                    this.orthogonal = true;
                } else {
                    if (Math.abs((GetMatrixRotation - this.startAngle) + f) < 4.0f) {
                        f = this.startAngle - GetMatrixRotation;
                        this.orthogonal = true;
                    } else if (Math.abs(90.0f - ((GetMatrixRotation - this.startAngle) + f)) < 4.0f) {
                        f = (this.startAngle + 90.0f) - GetMatrixRotation;
                        this.orthogonal = true;
                    } else if (Math.abs(180.0f - ((GetMatrixRotation - this.startAngle) + f)) < 4.0f) {
                        f = (this.startAngle + 180.0f) - GetMatrixRotation;
                        this.orthogonal = true;
                    } else if (Math.abs((-180.0f) - ((GetMatrixRotation - this.startAngle) + f)) < 4.0f) {
                        f = (this.startAngle - 0.024902344f) - GetMatrixRotation;
                        this.orthogonal = true;
                    } else if (Math.abs((-90.0f) - ((GetMatrixRotation - this.startAngle) + f)) < 4.0f) {
                        f = (this.startAngle - 0.049804688f) - GetMatrixRotation;
                        this.orthogonal = true;
                    } else {
                        this.orthogonal = false;
                    }
                    float[] fArr10 = this.pts;
                    this.textData.canvasMatrix.postRotate(this.startAngle - f, fArr10[0], fArr10[1]);
                    this.startAngle = f;
                }
                float[] fArr11 = this.pts;
                float sqrt = ((float) Math.sqrt(((x - fArr11[0]) * (x - fArr11[0])) + ((y - fArr11[1]) * (y - fArr11[1])))) / ((float) Math.sqrt(((this.zoomStart.x - this.pts[0]) * (this.zoomStart.x - this.pts[0])) + ((this.zoomStart.y - this.pts[1]) * (this.zoomStart.y - this.pts[1]))));
                float GetScale = GetScale();
                this.scale = GetScale;
                if (GetScale >= MIN_ZOOM || (GetScale < MIN_ZOOM && sqrt > 1.0f)) {
                    CtMyMatrix cMMyMatrix2 = this.textData.canvasMatrix;
                    float[] fArr12 = this.pts;
                    cMMyMatrix2.postScale(sqrt, sqrt, fArr12[0], fArr12[1]);
                    this.zoomStart.set(x, y);
                    this.scale = GetScale();
                    float f2 = 1.0f / sqrt;
                    this.scaleBitmapMatrix.postScale(f2, f2, this.rectText.right, this.rectText.bottom);
                    this.removeBitmapMatrix.postScale(f2, f2, this.rectText.left, this.rectText.top);
                    this.scaleBitmapSwitch.postScale(f2, f2, this.rectText.right, this.rectText.top);
                }
            } else {
                float x2 = motionEvent.getX(findPointerIndex);
                float y2 = motionEvent.getY(findPointerIndex);
                this.textData.canvasMatrix.postTranslate(x2 - this.previousPos.x, y2 - this.previousPos.y);
                this.previousPos.set(x2, y2);
            }
        }
        postInvalidate();
        boolean onTouchEvent = this.gestureDetector.onTouchEvent(motionEvent);
        postInvalidate();
        return onTouchEvent;
    }

    float GetScale() {
        this.textData.canvasMatrix.getValues(this.values);
        float[] fArr = this.values;
        float f = fArr[0];
        float f2 = fArr[3];
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }

    float GetMatrixRotation(Matrix matrix) {
        matrix.getValues(this.f1373v);
        float[] fArr = this.f1373v;
        return (float) Math.round(Math.atan2(fArr[1], fArr[0]) * 57.29577951308232d);
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public float containsScore(float f, float f2) {
        if (this.textData.getSnapMode()) {
            return -2.0f;
        }
        float[] fArr = this.pts;
        fArr[0] = f;
        fArr[1] = f2;
        this.textData.canvasMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float[] fArr3 = this.pts;
        float f3 = fArr3[0];
        float f4 = fArr3[1];
        RectF rectF = this.rectText;
        if (f3 >= rectF.left && f3 <= rectF.right && f4 >= rectF.top && f4 <= rectF.bottom) {
            float centerX = ((f3 - rectF.centerX()) * (f3 - rectF.centerX())) + ((f4 - rectF.centerY()) * (f4 - rectF.centerY()));
            float width = (rectF.width() * rectF.width()) + (rectF.height() * rectF.height());
            if (centerX > 0.0f) {
                return width / centerX;
            }
        }
        return -2.0f;
    }

    public void setRemoveTextListener(CustomRelativeLayout.RemoveTextListener removeTextListener) {
        this.removeTextListener = removeTextListener;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView
    public void DeleteView(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
            this.removeTextListener.onRemove();
        }
    }
}
