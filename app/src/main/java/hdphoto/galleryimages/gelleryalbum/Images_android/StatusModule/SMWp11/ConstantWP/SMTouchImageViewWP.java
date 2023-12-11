package hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import androidx.appcompat.widget.AppCompatImageView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;

public class SMTouchImageViewWP extends AppCompatImageView {
    public static String DEBUG = "DEBUG";
    public static float SUPER_MAX_MULTIPLIER = 1.25f;
    ZoomVariables delayedZoomVariables;
    GestureDetector.OnDoubleTapListener doubleTapListener;
    float doubleTapScale;
    Fling fling;
    boolean imageRenderedAtLeastOnce;
    boolean isRotateImageToFitScreen;
    GestureDetector mGestureDetector;
    ScaleGestureDetector mScaleDetector;
    ImageView.ScaleType mScaleType;
    float matchViewHeight;
    float matchViewWidth;
    Matrix matrix;
    float[] matrixArray;
    float maxScale;
    boolean maxScaleIsSetByMultiplier;
    float maxScaleMultiplier;
    float minScale;
    float normalizedScale;
    boolean onDrawReady;
    int orientation;
    FixedPixel orientationChangeFixedPixel;
    boolean orientationJustChanged;
    float prevMatchViewHeight;
    float prevMatchViewWidth;
    Matrix prevMatrix;
    int prevViewHeight;
    int prevViewWidth;
    State state;
    float superMaxScale;
    float superMinScale;
    OnTouchImageViewListener touchImageViewListener;
    float userSpecifiedMinScale;
    View.OnTouchListener userTouchListener;
    int viewHeight;
    FixedPixel viewSizeChangeFixedPixel;
    int viewWidth;
    boolean zoomEnabled;


    public enum FixedPixel {
        CENTER,
        TOP_LEFT,
        BOTTOM_RIGHT
    }


    public interface OnTouchImageViewListener {
        void onMove();
    }


    public interface OnZoomFinishedListener {
        void onZoomFinished();
    }


    public enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    public float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    public float getFixTrans(float f, float f2, float f3, float f4) {
        float f5;
        if (f3 <= f2) {
            f5 = (f2 + f4) - f3;
        } else {
            f4 = (f2 + f4) - f3;
            f5 = f4;
        }
        if (f < f4) {
            return (-f) + f4;
        }
        if (f > f5) {
            return (-f) + f5;
        }
        return 0.0f;
    }

    public SMTouchImageViewWP(Context context) {
        this(context, null);
    }

    public SMTouchImageViewWP(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SMTouchImageViewWP(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.orientationChangeFixedPixel = FixedPixel.CENTER;
        this.viewSizeChangeFixedPixel = FixedPixel.CENTER;
        this.orientationJustChanged = false;
        this.maxScaleIsSetByMultiplier = false;
        this.doubleTapListener = null;
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        configureImageView(context, attributeSet, i);
    }

    public void configureImageView(Context context, AttributeSet attributeSet, int i) {
        super.setClickable(true);
        this.orientation = getResources().getConfiguration().orientation;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener(this, this, this, null));
        this.mGestureDetector = new GestureDetector(context, new GestureListener(this, this, this, null));
        this.matrix = new Matrix();
        this.prevMatrix = new Matrix();
        this.matrixArray = new float[9];
        this.normalizedScale = 1.0f;
        if (this.mScaleType == null) {
            this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        }
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.superMinScale = 0.75f;
        this.superMaxScale = SUPER_MAX_MULTIPLIER * 3.0f;
        setImageMatrix(this.matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        setState(State.NONE);
        this.onDrawReady = false;
        super.setOnTouchListener(new publicOnTouchListener());
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TouchImageView, i, 0);
        try {
            if (!isInEditMode()) {
                setZoomEnabled(obtainStyledAttributes.getBoolean(0, true));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.userTouchListener = onTouchListener;
    }

    public boolean isZoomEnabled() {
        return this.zoomEnabled;
    }

    public void setZoomEnabled(boolean z) {
        this.zoomEnabled = z;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        this.imageRenderedAtLeastOnce = false;
        super.setImageResource(i);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.imageRenderedAtLeastOnce = false;
        super.setImageBitmap(bitmap);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.imageRenderedAtLeastOnce = false;
        super.setImageDrawable(drawable);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        this.imageRenderedAtLeastOnce = false;
        super.setImageURI(uri);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.MATRIX) {
            super.setScaleType(ImageView.ScaleType.MATRIX);
            return;
        }
        this.mScaleType = scaleType;
        if (this.onDrawReady) {
            setZoom(this);
        }
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public boolean isZoomed() {
        return this.normalizedScale != 1.0f;
    }

    public void savePreviousImageValues() {
        Matrix matrix = this.matrix;
        if (matrix == null || this.viewHeight == 0 || this.viewWidth == 0) {
            return;
        }
        matrix.getValues(this.matrixArray);
        this.prevMatrix.setValues(this.matrixArray);
        this.prevMatchViewHeight = this.matchViewHeight;
        this.prevMatchViewWidth = this.matchViewWidth;
        this.prevViewHeight = this.viewHeight;
        this.prevViewWidth = this.viewWidth;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("orientation", this.orientation);
        bundle.putFloat("saveScale", this.normalizedScale);
        bundle.putFloat("matchViewHeight", this.matchViewHeight);
        bundle.putFloat("matchViewWidth", this.matchViewWidth);
        bundle.putInt("viewWidth", this.viewWidth);
        bundle.putInt("viewHeight", this.viewHeight);
        this.matrix.getValues(this.matrixArray);
        bundle.putFloatArray("matrix", this.matrixArray);
        bundle.putBoolean("imageRendered", this.imageRenderedAtLeastOnce);
        bundle.putSerializable("viewSizeChangeFixedPixel", this.viewSizeChangeFixedPixel);
        bundle.putSerializable("orientationChangeFixedPixel", this.orientationChangeFixedPixel);
        return bundle;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.normalizedScale = bundle.getFloat("saveScale");
            float[] floatArray = bundle.getFloatArray("matrix");
            this.matrixArray = floatArray;
            this.prevMatrix.setValues(floatArray);
            this.prevMatchViewHeight = bundle.getFloat("matchViewHeight");
            this.prevMatchViewWidth = bundle.getFloat("matchViewWidth");
            this.prevViewHeight = bundle.getInt("viewHeight");
            this.prevViewWidth = bundle.getInt("viewWidth");
            this.imageRenderedAtLeastOnce = bundle.getBoolean("imageRendered");
            this.viewSizeChangeFixedPixel = (FixedPixel) bundle.getSerializable("viewSizeChangeFixedPixel");
            this.orientationChangeFixedPixel = (FixedPixel) bundle.getSerializable("orientationChangeFixedPixel");
            if (this.orientation != bundle.getInt("orientation")) {
                this.orientationJustChanged = true;
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        this.onDrawReady = true;
        this.imageRenderedAtLeastOnce = true;
        ZoomVariables zoomVariables = this.delayedZoomVariables;
        if (zoomVariables != null) {
            setZoom(zoomVariables.scale, this.delayedZoomVariables.focusX, this.delayedZoomVariables.focusY, this.delayedZoomVariables.scaleType);
            this.delayedZoomVariables = null;
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = getResources().getConfiguration().orientation;
        if (i != this.orientation) {
            this.orientationJustChanged = true;
            this.orientation = i;
        }
        savePreviousImageValues();
    }

    public void setMaxZoomRatio(float f) {
        this.maxScaleMultiplier = f;
        float f2 = this.minScale * f;
        this.maxScale = f2;
        this.superMaxScale = f2 * SUPER_MAX_MULTIPLIER;
        this.maxScaleIsSetByMultiplier = true;
    }

    public float getCurrentZoom() {
        return this.normalizedScale;
    }

    public void setMinZoom(float f) {
        this.userSpecifiedMinScale = f;
        if (f != -1.0f) {
            this.minScale = f;
        } else if (this.mScaleType == ImageView.ScaleType.CENTER || this.mScaleType == ImageView.ScaleType.CENTER_CROP) {
            Drawable drawable = getDrawable();
            int drawableWidth = getDrawableWidth(drawable);
            int drawableHeight = getDrawableHeight(drawable);
            if (drawable != null && drawableWidth > 0 && drawableHeight > 0) {
                float f2 = this.viewWidth / drawableWidth;
                float f3 = this.viewHeight / drawableHeight;
                if (this.mScaleType == ImageView.ScaleType.CENTER) {
                    this.minScale = Math.min(f2, f3);
                } else {
                    this.minScale = Math.min(f2, f3) / Math.max(f2, f3);
                }
            }
        } else {
            this.minScale = 1.0f;
        }
        if (this.maxScaleIsSetByMultiplier) {
            setMaxZoomRatio(this.maxScaleMultiplier);
        }
        this.superMinScale = this.minScale * 0.75f;
    }

    public void resetZoom() {
        this.normalizedScale = 1.0f;
        fitImageToView();
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.mScaleType);
    }

    public void setZoom(float f, float f2, float f3, ImageView.ScaleType scaleType) {
        if (!this.onDrawReady) {
            this.delayedZoomVariables = new ZoomVariables(f, f2, f3, scaleType);
            return;
        }
        if (this.userSpecifiedMinScale == -1.0f) {
            setMinZoom(-1.0f);
            float f4 = this.normalizedScale;
            float f5 = this.minScale;
            if (f4 < f5) {
                this.normalizedScale = f5;
            }
        }
        if (scaleType != this.mScaleType) {
            setScaleType(scaleType);
        }
        resetZoom();
        scaleImage(f, this.viewWidth / 2, this.viewHeight / 2, true);
        this.matrix.getValues(this.matrixArray);
        this.matrixArray[2] = -((getImageWidth() * f2) - (this.viewWidth * 0.5f));
        this.matrixArray[5] = -((getImageHeight() * f3) - (this.viewHeight * 0.5f));
        this.matrix.setValues(this.matrixArray);
        fixTrans();
        savePreviousImageValues();
        setImageMatrix(this.matrix);
    }

    public void setZoom(SMTouchImageViewWP sMTouchImageViewWP) {
        PointF scrollPosition = sMTouchImageViewWP.getScrollPosition();
        setZoom(sMTouchImageViewWP.getCurrentZoom(), scrollPosition.x, scrollPosition.y, sMTouchImageViewWP.getScaleType());
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return new PointF(0.5f, 0.5f);
        }
        int drawableWidth = getDrawableWidth(drawable);
        int drawableHeight = getDrawableHeight(drawable);
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap(this.viewWidth / 2, this.viewHeight / 2, true);
        transformCoordTouchToBitmap.x /= drawableWidth;
        transformCoordTouchToBitmap.y /= drawableHeight;
        return transformCoordTouchToBitmap;
    }

    public boolean orientationMismatch(Drawable drawable) {
        return (this.viewWidth > this.viewHeight) != (drawable.getIntrinsicWidth() > drawable.getIntrinsicHeight());
    }

    public int getDrawableWidth(Drawable drawable) {
        if (!orientationMismatch(drawable) || !this.isRotateImageToFitScreen) {
            return drawable.getIntrinsicWidth();
        }
        return drawable.getIntrinsicHeight();
    }

    public int getDrawableHeight(Drawable drawable) {
        if (!orientationMismatch(drawable) || !this.isRotateImageToFitScreen) {
            return drawable.getIntrinsicHeight();
        }
        return drawable.getIntrinsicWidth();
    }

    public void fixTrans() {
        this.matrix.getValues(this.matrixArray);
        float[] fArr = this.matrixArray;
        this.matrix.postTranslate(getFixTrans(fArr[2], this.viewWidth, getImageWidth(), (this.isRotateImageToFitScreen && orientationMismatch(getDrawable())) ? getImageWidth() : 0.0f), getFixTrans(fArr[5], this.viewHeight, getImageHeight(), 0.0f));
    }

    public void fixScaleTrans() {
        fixTrans();
        this.matrix.getValues(this.matrixArray);
        float imageWidth = getImageWidth();
        float f = this.viewWidth;
        if (imageWidth < f) {
            float imageWidth2 = (f - getImageWidth()) / 2.0f;
            if (this.isRotateImageToFitScreen && orientationMismatch(getDrawable())) {
                imageWidth2 += getImageWidth();
            }
            this.matrixArray[2] = imageWidth2;
        }
        float imageHeight = getImageHeight();
        float f2 = this.viewHeight;
        if (imageHeight < f2) {
            this.matrixArray[5] = (f2 - getImageHeight()) / 2.0f;
        }
        this.matrix.setValues(this.matrixArray);
    }

    public float getImageWidth() {
        return this.matchViewWidth * this.normalizedScale;
    }

    public float getImageHeight() {
        return this.matchViewHeight * this.normalizedScale;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int drawableWidth = getDrawableWidth(drawable);
        int drawableHeight = getDrawableHeight(drawable);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        int viewSize = setViewSize(mode, size, drawableWidth);
        int viewSize2 = setViewSize(mode2, size2, drawableHeight);
        if (!this.orientationJustChanged) {
            savePreviousImageValues();
        }
        setMeasuredDimension((viewSize - getPaddingLeft()) - getPaddingRight(), (viewSize2 - getPaddingTop()) - getPaddingBottom());
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.viewWidth = i;
        this.viewHeight = i2;
        fitImageToView();
    }

    public void fitImageToView() {
        FixedPixel fixedPixel = this.orientationJustChanged ? this.orientationChangeFixedPixel : this.viewSizeChangeFixedPixel;
        this.orientationJustChanged = false;
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0 || this.matrix == null || this.prevMatrix == null) {
            return;
        }
        if (this.userSpecifiedMinScale == -1.0f) {
            setMinZoom(-1.0f);
            float f = this.normalizedScale;
            float f2 = this.minScale;
            if (f < f2) {
                this.normalizedScale = f2;
            }
        }
        int drawableWidth = getDrawableWidth(drawable);
        int drawableHeight = getDrawableHeight(drawable);
        float f3 = drawableWidth;
        float f4 = this.viewWidth / f3;
        float f5 = drawableHeight;
        float f6 = this.viewHeight / f5;
        int i = AnonymousClass1.INTS[this.mScaleType.ordinal()];
        if (i == 2) {
            Math.max(f4, f6);
        } else if (i == 3) {
            f4 = Math.min(1.0f, Math.min(f4, f6));
            f6 = f4;
        }
        float min = Math.min(f4, f6);
        float f7 = this.viewWidth;
        float f8 = f7 - (f3 * min);
        float f9 = this.viewHeight;
        float f10 = f9 - (f5 * min);
        this.matchViewWidth = f7 - f8;
        this.matchViewHeight = f9 - f10;
        if (isZoomed() || this.imageRenderedAtLeastOnce) {
            if (this.prevMatchViewWidth == 0.0f || this.prevMatchViewHeight == 0.0f) {
                savePreviousImageValues();
            }
            this.prevMatrix.getValues(this.matrixArray);
            float[] fArr = this.matrixArray;
            float f11 = this.normalizedScale;
            fArr[0] = (this.matchViewWidth / f3) * f11;
            fArr[4] = (this.matchViewHeight / f5) * f11;
            float f12 = fArr[2];
            float f13 = fArr[5];
            FixedPixel fixedPixel2 = fixedPixel;
            fArr[2] = newTranslationAfterChange(f12, f11 * this.prevMatchViewWidth, getImageWidth(), this.prevViewWidth, this.viewWidth, drawableWidth, fixedPixel2);
            this.matrixArray[5] = newTranslationAfterChange(f13, this.prevMatchViewHeight * this.normalizedScale, getImageHeight(), this.prevViewHeight, this.viewHeight, drawableHeight, fixedPixel2);
            this.matrix.setValues(this.matrixArray);
        } else {
            if (!this.isRotateImageToFitScreen || !orientationMismatch(drawable)) {
                this.matrix.setScale(min, min);
            } else {
                this.matrix.setRotate(90.0f);
                this.matrix.postTranslate(f3, 0.0f);
                this.matrix.postScale(min, min);
            }
            int i2 = AnonymousClass1.INTS[this.mScaleType.ordinal()];
            if (i2 == 5) {
                this.matrix.postTranslate(0.0f, 0.0f);
            } else if (i2 != 6) {
                this.matrix.postTranslate(f8 / 2.0f, f10 / 2.0f);
            } else {
                this.matrix.postTranslate(f8, f10);
            }
            this.normalizedScale = 1.0f;
        }
        fixTrans();
        setImageMatrix(this.matrix);
    }


    public static class AnonymousClass1 {
        static int[] INTS;

        AnonymousClass1() {
        }

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            INTS = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                INTS[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                INTS[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                INTS[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                INTS[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                INTS[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                INTS[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public int setViewSize(int i, int i2, int i3) {
        if (i == Integer.MIN_VALUE) {
            return Math.min(i3, i2);
        }
        if (i == 0) {
            return i3;
        }
        int i4 = FileUtils.GIGA;
        return i2;
    }

    public float newTranslationAfterChange(float f, float f2, float f3, int i, int i2, int i3, FixedPixel fixedPixel) {
        float f4 = i2;
        float f5 = 0.5f;
        if (f3 < f4) {
            return (f4 - (i3 * this.matrixArray[0])) * 0.5f;
        }
        if (f > 0.0f) {
            return -((f3 - f4) * 0.5f);
        }
        if (fixedPixel == FixedPixel.BOTTOM_RIGHT) {
            f5 = 1.0f;
        } else if (fixedPixel == FixedPixel.TOP_LEFT) {
            f5 = 0.0f;
        }
        return -(((((-f) + (i * f5)) / f2) * f3) - (f4 * f5));
    }

    public void setState(State state) {
        this.state = state;
    }

    @Deprecated
    public boolean canScrollHorizontallyFroyo(int i) {
        return canScrollHorizontally(i);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        this.matrix.getValues(this.matrixArray);
        float f = this.matrixArray[2];
        if (getImageWidth() < this.viewWidth) {
            return false;
        }
        if (f < -1.0f || i >= 0) {
            return (Math.abs(f) + ((float) this.viewWidth)) + 1.0f < getImageWidth() || i <= 0;
        }
        return false;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        this.matrix.getValues(this.matrixArray);
        float f = this.matrixArray[5];
        if (getImageHeight() < this.viewHeight) {
            return false;
        }
        if (f < -1.0f || i >= 0) {
            return (Math.abs(f) + ((float) this.viewHeight)) + 1.0f < getImageHeight() || i <= 0;
        }
        return false;
    }


    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        public GestureListener() {
        }

        GestureListener(SMTouchImageViewWP sMTouchImageViewWP, SMTouchImageViewWP sMTouchImageViewWP2, SMTouchImageViewWP sMTouchImageViewWP3, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (SMTouchImageViewWP.this.doubleTapListener != null) {
                return SMTouchImageViewWP.this.doubleTapListener.onSingleTapConfirmed(motionEvent);
            }
            return SMTouchImageViewWP.this.performClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            SMTouchImageViewWP.this.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (SMTouchImageViewWP.this.fling != null) {
                SMTouchImageViewWP.this.fling.cancelFling();
            }
            SMTouchImageViewWP sMTouchImageViewWP = SMTouchImageViewWP.this;
            sMTouchImageViewWP.fling = new Fling(sMTouchImageViewWP, (int) f, (int) f2);
            SMTouchImageViewWP sMTouchImageViewWP2 = SMTouchImageViewWP.this;
            sMTouchImageViewWP2.compatPostOnAnimation(sMTouchImageViewWP2.fling);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (SMTouchImageViewWP.this.isZoomEnabled()) {
                boolean onDoubleTap = SMTouchImageViewWP.this.doubleTapListener != null ? SMTouchImageViewWP.this.doubleTapListener.onDoubleTap(motionEvent) : false;
                if (SMTouchImageViewWP.this.state != State.NONE) {
                    return onDoubleTap;
                }
                SMTouchImageViewWP sMTouchImageViewWP = SMTouchImageViewWP.this;
                sMTouchImageViewWP.compatPostOnAnimation(new DoubleTapZoom(sMTouchImageViewWP.normalizedScale == SMTouchImageViewWP.this.minScale ? SMTouchImageViewWP.this.doubleTapScale == 0.0f ? SMTouchImageViewWP.this.maxScale : SMTouchImageViewWP.this.doubleTapScale : SMTouchImageViewWP.this.minScale, motionEvent.getX(), motionEvent.getY(), false));
                return true;
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (SMTouchImageViewWP.this.doubleTapListener != null) {
                return SMTouchImageViewWP.this.doubleTapListener.onDoubleTapEvent(motionEvent);
            }
            return false;
        }
    }


    public class publicOnTouchListener implements View.OnTouchListener {
        public PointF last = new PointF();

        public publicOnTouchListener() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (SMTouchImageViewWP.this.userTouchListener != null) {
                SMTouchImageViewWP.this.userTouchListener.onTouch(view, motionEvent);
            }
            SMTouchImageViewWP.this.mScaleDetector.onTouchEvent(motionEvent);
            SMTouchImageViewWP.this.mGestureDetector.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (SMTouchImageViewWP.this.state == State.NONE || SMTouchImageViewWP.this.state == State.DRAG || SMTouchImageViewWP.this.state == State.FLING) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action != 1 && action == 2 && SMTouchImageViewWP.this.state == State.DRAG) {
                        float f = pointF.x - this.last.x;
                        float f2 = pointF.y - this.last.y;
                        SMTouchImageViewWP sMTouchImageViewWP = SMTouchImageViewWP.this;
                        float fixDragTrans = sMTouchImageViewWP.getFixDragTrans(f, sMTouchImageViewWP.viewWidth, SMTouchImageViewWP.this.getImageWidth());
                        SMTouchImageViewWP sMTouchImageViewWP2 = SMTouchImageViewWP.this;
                        sMTouchImageViewWP2.matrix.postTranslate(fixDragTrans, sMTouchImageViewWP2.getFixDragTrans(f2, sMTouchImageViewWP2.viewHeight, SMTouchImageViewWP.this.getImageHeight()));
                        SMTouchImageViewWP.this.fixTrans();
                        this.last.set(pointF.x, pointF.y);
                    }
                    SMTouchImageViewWP.this.setState(State.NONE);
                } else {
                    this.last.set(pointF);
                    if (SMTouchImageViewWP.this.fling != null) {
                        SMTouchImageViewWP.this.fling.cancelFling();
                    }
                    SMTouchImageViewWP.this.setState(State.DRAG);
                }
            }
            SMTouchImageViewWP sMTouchImageViewWP3 = SMTouchImageViewWP.this;
            sMTouchImageViewWP3.setImageMatrix(sMTouchImageViewWP3.matrix);
            return true;
        }
    }


    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public ScaleListener() {
        }

        ScaleListener(SMTouchImageViewWP sMTouchImageViewWP, SMTouchImageViewWP sMTouchImageViewWP2, SMTouchImageViewWP sMTouchImageViewWP3, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            SMTouchImageViewWP.this.setState(State.ZOOM);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            SMTouchImageViewWP.this.scaleImage(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (SMTouchImageViewWP.this.touchImageViewListener == null) {
                return true;
            }
            SMTouchImageViewWP.this.touchImageViewListener.onMove();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            float f;
            super.onScaleEnd(scaleGestureDetector);
            SMTouchImageViewWP.this.setState(State.NONE);
            boolean z = true;
            if (SMTouchImageViewWP.this.normalizedScale > SMTouchImageViewWP.this.maxScale) {
                f = SMTouchImageViewWP.this.maxScale;
            } else if (SMTouchImageViewWP.this.normalizedScale < SMTouchImageViewWP.this.minScale) {
                f = SMTouchImageViewWP.this.minScale;
            } else {
                f = SMTouchImageViewWP.this.normalizedScale;
                z = false;
            }
            float f2 = f;
            if (z) {
                SMTouchImageViewWP sMTouchImageViewWP = SMTouchImageViewWP.this;
                sMTouchImageViewWP.compatPostOnAnimation(new DoubleTapZoom(f2, sMTouchImageViewWP.viewWidth / 2, SMTouchImageViewWP.this.viewHeight / 2, true));
            }
        }
    }

    public void scaleImage(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.superMinScale;
            f4 = this.superMaxScale;
        } else {
            f3 = this.minScale;
            f4 = this.maxScale;
        }
        float f5 = this.normalizedScale;
        Double.isNaN(f5);
        float f6 = (float) (this.normalizedScale * d);
        this.normalizedScale = f6;
        if (f6 > f4) {
            this.normalizedScale = f4;
            d = f4 / f5;
        } else if (f6 < f3) {
            this.normalizedScale = f3;
            d = f3 / f5;
        }
        float f7 = (float) d;
        this.matrix.postScale(f7, f7, f, f2);
        fixScaleTrans();
    }


    public class DoubleTapZoom implements Runnable {
        public float bitmapX;
        public float bitmapY;
        public PointF endTouch;
        public AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        public long startTime = System.currentTimeMillis();
        public PointF startTouch;
        public float startZoom;
        public boolean stretchImageToSuper;
        public float targetZoom;

        DoubleTapZoom(float f, float f2, float f3, boolean z) {
            SMTouchImageViewWP.this.setState(State.ANIMATE_ZOOM);
            this.startZoom = SMTouchImageViewWP.this.normalizedScale;
            this.targetZoom = f;
            this.stretchImageToSuper = z;
            PointF transformCoordTouchToBitmap = SMTouchImageViewWP.this.transformCoordTouchToBitmap(f2, f3, false);
            this.bitmapX = transformCoordTouchToBitmap.x;
            float f4 = transformCoordTouchToBitmap.y;
            this.bitmapY = f4;
            this.startTouch = SMTouchImageViewWP.this.transformCoordBitmapToTouch(this.bitmapX, f4);
            this.endTouch = new PointF(SMTouchImageViewWP.this.viewWidth / 2, SMTouchImageViewWP.this.viewHeight / 2);
        }

        @Override 
        public void run() {
            if (SMTouchImageViewWP.this.getDrawable() == null) {
                SMTouchImageViewWP.this.setState(State.NONE);
                return;
            }
            float interpolate = interpolate();
            SMTouchImageViewWP.this.scaleImage(calculateDeltaScale(interpolate), this.bitmapX, this.bitmapY, this.stretchImageToSuper);
            translateImageToCenterTouchPosition(interpolate);
            SMTouchImageViewWP.this.fixScaleTrans();
            SMTouchImageViewWP sMTouchImageViewWP = SMTouchImageViewWP.this;
            sMTouchImageViewWP.setImageMatrix(sMTouchImageViewWP.matrix);
            if (SMTouchImageViewWP.this.touchImageViewListener != null) {
                SMTouchImageViewWP.this.touchImageViewListener.onMove();
            }
            if (interpolate < 1.0f) {
                SMTouchImageViewWP.this.compatPostOnAnimation(this);
            } else {
                SMTouchImageViewWP.this.setState(State.NONE);
            }
        }

        public void translateImageToCenterTouchPosition(float f) {
            float f2 = this.startTouch.x + ((this.endTouch.x - this.startTouch.x) * f);
            float f3 = this.startTouch.y + ((this.endTouch.y - this.startTouch.y) * f);
            PointF transformCoordBitmapToTouch = SMTouchImageViewWP.this.transformCoordBitmapToTouch(this.bitmapX, this.bitmapY);
            SMTouchImageViewWP.this.matrix.postTranslate(f2 - transformCoordBitmapToTouch.x, f3 - transformCoordBitmapToTouch.y);
        }

        public float interpolate() {
            return this.interpolator.getInterpolation(Math.min(1.0f, (float) ((System.currentTimeMillis() - this.startTime) / 500)));
        }

        public double calculateDeltaScale(float f) {
            float f2 = this.startZoom;
            double d = f2 + ((this.targetZoom - f2) * f);
            double d2 = SMTouchImageViewWP.this.normalizedScale;
            Double.isNaN(d);
            Double.isNaN(d2);
            return d / d2;
        }
    }

    public PointF transformCoordTouchToBitmap(float f, float f2, boolean z) {
        this.matrix.getValues(this.matrixArray);
        float intrinsicWidth = getDrawable().getIntrinsicWidth();
        float intrinsicHeight = getDrawable().getIntrinsicHeight();
        float[] fArr = this.matrixArray;
        float f3 = fArr[2];
        float f4 = fArr[5];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f2 - f4) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    public PointF transformCoordBitmapToTouch(float f, float f2) {
        this.matrix.getValues(this.matrixArray);
        return new PointF(this.matrixArray[2] + (getImageWidth() * (f / getDrawable().getIntrinsicWidth())), this.matrixArray[5] + (getImageHeight() * (f2 / getDrawable().getIntrinsicHeight())));
    }


    public class Fling implements Runnable {
        int currX;
        int currY;
        CompatScroller scroller;
        SMTouchImageViewWP touchImageView;

        Fling(SMTouchImageViewWP sMTouchImageViewWP, int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            this.touchImageView = sMTouchImageViewWP;
            sMTouchImageViewWP.setState(State.FLING);
            this.scroller = new CompatScroller(sMTouchImageViewWP.getContext());
            sMTouchImageViewWP.matrix.getValues(sMTouchImageViewWP.matrixArray);
            int i7 = (int) sMTouchImageViewWP.matrixArray[2];
            int i8 = (int) sMTouchImageViewWP.matrixArray[5];
            if (sMTouchImageViewWP.isRotateImageToFitScreen && sMTouchImageViewWP.orientationMismatch(sMTouchImageViewWP.getDrawable())) {
                i7 = (int) (i7 - sMTouchImageViewWP.getImageWidth());
            }
            if (sMTouchImageViewWP.getImageWidth() > sMTouchImageViewWP.viewWidth) {
                i3 = sMTouchImageViewWP.viewWidth - ((int) sMTouchImageViewWP.getImageWidth());
                i4 = 0;
            } else {
                i3 = i7;
                i4 = i3;
            }
            if (sMTouchImageViewWP.getImageHeight() > sMTouchImageViewWP.viewHeight) {
                i5 = sMTouchImageViewWP.viewHeight - ((int) sMTouchImageViewWP.getImageHeight());
                i6 = 0;
            } else {
                i5 = i8;
                i6 = i5;
            }
            this.scroller.fling(i7, i8, i, i2, i3, i4, i5, i6);
            this.currX = i7;
            this.currY = i8;
        }

        public void cancelFling() {
            if (this.scroller != null) {
                this.touchImageView.setState(State.NONE);
                this.scroller.forceFinished(true);
            }
        }

        @Override 
        public void run() {
            if (this.touchImageView.touchImageViewListener != null) {
                this.touchImageView.touchImageViewListener.onMove();
            }
            if (this.scroller.isFinished()) {
                this.scroller = null;
            } else if (this.scroller.computeScrollOffset()) {
                int currX = this.scroller.getCurrX();
                int currY = this.scroller.getCurrY();
                this.currX = currX;
                this.currY = currY;
                this.touchImageView.matrix.postTranslate(currX - this.currX, currY - this.currY);
                this.touchImageView.fixTrans();
                SMTouchImageViewWP sMTouchImageViewWP = this.touchImageView;
                sMTouchImageViewWP.setImageMatrix(sMTouchImageViewWP.matrix);
                this.touchImageView.compatPostOnAnimation(this);
            }
        }
    }


    public class CompatScroller {
        OverScroller overScroller;

        CompatScroller(Context context) {
            this.overScroller = new OverScroller(context);
        }

        public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.overScroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void forceFinished(boolean z) {
            this.overScroller.forceFinished(z);
        }

        public boolean isFinished() {
            return this.overScroller.isFinished();
        }

        public boolean computeScrollOffset() {
            this.overScroller.computeScrollOffset();
            return this.overScroller.computeScrollOffset();
        }

        public int getCurrX() {
            return this.overScroller.getCurrX();
        }

        public int getCurrY() {
            return this.overScroller.getCurrY();
        }
    }

    public void compatPostOnAnimation(Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16L);
        }
    }


    public class ZoomVariables {
        float focusX;
        float focusY;
        float scale;
        ImageView.ScaleType scaleType;

        ZoomVariables(float f, float f2, float f3, ImageView.ScaleType scaleType) {
            this.scale = f;
            this.focusX = f2;
            this.focusY = f3;
            this.scaleType = scaleType;
        }
    }

    public void setZoomAnimated(float f, float f2, float f3, int i) {
        compatPostOnAnimation(new AnimatedZoom(f, new PointF(f2, f3), i));
    }


    public class AnimatedZoom implements Runnable {
        public OnZoomFinishedListener listener;
        public PointF startFocus;
        public float startZoom;
        public PointF targetFocus;
        public float targetZoom;
        public final int zoomTimeMillis;
        public LinearInterpolator interpolator = new LinearInterpolator();
        public long startTime = System.currentTimeMillis();

        AnimatedZoom(float f, PointF pointF, int i) {
            SMTouchImageViewWP.this.setState(State.ANIMATE_ZOOM);
            this.startZoom = SMTouchImageViewWP.this.normalizedScale;
            this.targetZoom = f;
            this.zoomTimeMillis = i;
            this.startFocus = SMTouchImageViewWP.this.getScrollPosition();
            this.targetFocus = pointF;
        }

        @Override 
        public void run() {
            float interpolate = interpolate();
            float f = this.startZoom;
            SMTouchImageViewWP.this.setZoom(f + ((this.targetZoom - f) * interpolate), this.startFocus.x + ((this.targetFocus.x - this.startFocus.x) * interpolate), this.startFocus.y + ((this.targetFocus.y - this.startFocus.y) * interpolate));
            if (interpolate < 1.0f) {
                SMTouchImageViewWP.this.compatPostOnAnimation(this);
                return;
            }
            SMTouchImageViewWP.this.setState(State.NONE);
            OnZoomFinishedListener onZoomFinishedListener = this.listener;
            if (onZoomFinishedListener != null) {
                onZoomFinishedListener.onZoomFinished();
            }
        }

        public float interpolate() {
            return this.interpolator.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.startTime)) / this.zoomTimeMillis));
        }

        public void setListener(OnZoomFinishedListener onZoomFinishedListener) {
            this.listener = onZoomFinishedListener;
        }
    }
}
