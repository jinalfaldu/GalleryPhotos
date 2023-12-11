package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import hdphoto.galleryimages.gelleryalbum.R;

public class CustomRangeSeekbar extends View {


    int INVALID_POINTER_ID;
    float NO_FIXED_GAP;
    float NO_STEP;
    Paint _paint;
    RectF _rect;
    float absoluteMaxStartValue;
    float absoluteMaxValue;
    float absoluteMinStartValue;
    float absoluteMinValue;
    int barColor;
    float barHeight;
    int barHighlightColor;
    float barPadding;
    Bitmap btmpLeftThumb;
    Bitmap btmpLeftThumbPressed;
    Bitmap btmpRightThumb;
    Bitmap btmpRightThumbPressed;
    float cornerRadius;
    int dataType;
    float fixGap;
    float gap;
    Drawable leftDrawable;
    Drawable leftDrawablePressed;
    int leftThumbColor;
    int leftThumbColorNormal;
    int leftThumbColorPressed;
    int mActivePointerId;
    boolean mIsDragging;
    float maxStartValue;
    float maxValue;
    float minStartValue;
    float minValue;
    double normalizedMaxValue;
    double normalizedMinValue;
    OnRangeSeekbarChangeListener onRangeSeekbarChangeListener;
    OnRangeSeekbarFinalValueListener onRangeSeekbarFinalValueListener;
    int pointerIndex;
    Thumb pressedThumb;
    RectF rectLeftThumb;
    RectF rectRightThumb;
    Drawable rightDrawable;
    Drawable rightDrawablePressed;
    int rightThumbColor;
    int rightThumbColorNormal;
    int rightThumbColorPressed;
    float steps;
    float thumbHeight;
    float thumbWidth;


    public enum Thumb {
        MIN,
        MAX
    }

    public void touchDown(float f, float f2) {
    }

    public void touchMove(float f, float f2) {
    }

    public void touchUp(float f, float f2) {
    }

    public CustomRangeSeekbar(Context context) {
        this(context, null);
    }

    public CustomRangeSeekbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Finally extract failed */
    public CustomRangeSeekbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.INVALID_POINTER_ID = 255;
        this.NO_STEP = -1.0f;
        this.NO_FIXED_GAP = -1.0f;
        this.mActivePointerId = 255;
        this.normalizedMinValue =  0.0d;
        this.normalizedMaxValue = 100.0d;
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.example.crystalrangeseekbar.R.styleable.CrystalRangeSeekbar);
        try {
            this.cornerRadius = getCornerRadius(obtainStyledAttributes);
            this.minValue = getMinValue(obtainStyledAttributes);
            this.maxValue = getMaxValue(obtainStyledAttributes);
            this.minStartValue = getMinStartValue(obtainStyledAttributes);
            this.maxStartValue = getMaxStartValue(obtainStyledAttributes);
            this.steps = getSteps(obtainStyledAttributes);
            this.gap = getGap(obtainStyledAttributes);
            this.fixGap = getFixedGap(obtainStyledAttributes);
            this.barColor = getBarColor(obtainStyledAttributes);
            this.barHighlightColor = getBarHighlightColor(obtainStyledAttributes);
            this.leftThumbColorNormal = getLeftThumbColor(obtainStyledAttributes);
            this.rightThumbColorNormal = getRightThumbColor(obtainStyledAttributes);
            this.leftThumbColorPressed = getLeftThumbColorPressed(obtainStyledAttributes);
            this.rightThumbColorPressed = getRightThumbColorPressed(obtainStyledAttributes);
            this.leftDrawable = getLeftDrawable(obtainStyledAttributes);
            this.rightDrawable = getRightDrawable(obtainStyledAttributes);
            this.leftDrawablePressed = getLeftDrawablePressed(obtainStyledAttributes);
            this.rightDrawablePressed = getRightDrawablePressed(obtainStyledAttributes);
            this.dataType = getDataType(obtainStyledAttributes);
            obtainStyledAttributes.recycle();
            Binding();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void Binding() {
        this.absoluteMinValue = this.minValue;
        this.absoluteMaxValue = this.maxValue;
        this.leftThumbColor = this.leftThumbColorNormal;
        this.rightThumbColor = this.rightThumbColorNormal;
        this.btmpLeftThumb = getBitmap(this.leftDrawable);
        this.btmpRightThumb = getBitmap(this.rightDrawable);
        this.btmpLeftThumbPressed = getBitmap(this.leftDrawablePressed);
        Bitmap bitmap = getBitmap(this.rightDrawablePressed);
        this.btmpRightThumbPressed = bitmap;
        Bitmap bitmap2 = this.btmpLeftThumbPressed;
        if (bitmap2 == null) {
            bitmap2 = this.btmpLeftThumb;
        }
        this.btmpLeftThumbPressed = bitmap2;
        if (bitmap == null) {
            bitmap = this.btmpRightThumb;
        }
        this.btmpRightThumbPressed = bitmap;
        float max = Math.max(0.0f, Math.min(this.gap, this.absoluteMaxValue - this.absoluteMinValue));
        this.gap = max;
        float f = this.absoluteMaxValue;
        this.gap = (max / (f - this.absoluteMinValue)) * 100.0f;
        float f2 = this.fixGap;
        if (f2 != -1.0f) {
            float min = Math.min(f2, f);
            this.fixGap = min;
            this.fixGap = (min / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            AddFixGap(true);
        }
        this.thumbWidth = getThumbWidth();
        this.thumbHeight = getThumbHeight();
        this.barHeight = getBarHeight();
        this.barPadding = getBarPadding();
        this._paint = new Paint(1);
        this._rect = new RectF();
        this.rectLeftThumb = new RectF();
        this.rectRightThumb = new RectF();
        this.pressedThumb = null;
        setMinStartValue();
        setMaxStartValue();
    }

    public CustomRangeSeekbar setCornerRadius(float f) {
        this.cornerRadius = f;
        return this;
    }

    public CustomRangeSeekbar setMinValue(float f) {
        this.minValue = f;
        this.absoluteMinValue = f;
        return this;
    }

    public CustomRangeSeekbar setMaxValue(float f) {
        this.maxValue = f;
        this.absoluteMaxValue = f;
        return this;
    }

    public CustomRangeSeekbar setMinStartValue(float f) {
        this.minStartValue = f;
        this.absoluteMinStartValue = f;
        return this;
    }

    public CustomRangeSeekbar setMaxStartValue(float f) {
        this.maxStartValue = f;
        this.absoluteMaxStartValue = f;
        return this;
    }

    public CustomRangeSeekbar setSteps(float f) {
        this.steps = f;
        return this;
    }

    public CustomRangeSeekbar setGap(float f) {
        this.gap = f;
        return this;
    }

    public CustomRangeSeekbar setFixGap(float f) {
        this.fixGap = f;
        return this;
    }

    public CustomRangeSeekbar setBarColor(int i) {
        this.barColor = i;
        return this;
    }

    public CustomRangeSeekbar setBarHighlightColor(int i) {
        this.barHighlightColor = i;
        return this;
    }

    public CustomRangeSeekbar setLeftThumbColor(int i) {
        this.leftThumbColorNormal = i;
        return this;
    }

    public CustomRangeSeekbar setLeftThumbHighlightColor(int i) {
        this.leftThumbColorPressed = i;
        return this;
    }

    public CustomRangeSeekbar setLeftThumbDrawable(int i) {
        setLeftThumbDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public CustomRangeSeekbar setLeftThumbDrawable(Drawable drawable) {
        setLeftThumbBitmap(getBitmap(drawable));
        return this;
    }

    public CustomRangeSeekbar setLeftThumbBitmap(Bitmap bitmap) {
        this.btmpLeftThumb = bitmap;
        return this;
    }

    public CustomRangeSeekbar setLeftThumbHighlightDrawable(int i) {
        setLeftThumbHighlightDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public CustomRangeSeekbar setLeftThumbHighlightDrawable(Drawable drawable) {
        setLeftThumbHighlightBitmap(getBitmap(drawable));
        return this;
    }

    public CustomRangeSeekbar setLeftThumbHighlightBitmap(Bitmap bitmap) {
        this.btmpLeftThumbPressed = bitmap;
        return this;
    }

    public CustomRangeSeekbar setRightThumbColor(int i) {
        this.rightThumbColorNormal = i;
        return this;
    }

    public CustomRangeSeekbar setRightThumbHighlightColor(int i) {
        this.rightThumbColorPressed = i;
        return this;
    }

    public CustomRangeSeekbar setRightThumbDrawable(int i) {
        setRightThumbDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public CustomRangeSeekbar setRightThumbDrawable(Drawable drawable) {
        setRightThumbBitmap(getBitmap(drawable));
        return this;
    }

    public CustomRangeSeekbar setRightThumbBitmap(Bitmap bitmap) {
        this.btmpRightThumb = bitmap;
        return this;
    }

    public CustomRangeSeekbar setRightThumbHighlightDrawable(int i) {
        setRightThumbHighlightDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public CustomRangeSeekbar setRightThumbHighlightDrawable(Drawable drawable) {
        setRightThumbHighlightBitmap(getBitmap(drawable));
        return this;
    }

    public CustomRangeSeekbar setRightThumbHighlightBitmap(Bitmap bitmap) {
        this.btmpRightThumbPressed = bitmap;
        return this;
    }

    public CustomRangeSeekbar setDataType(int i) {
        this.dataType = i;
        return this;
    }

    public void setOnRangeSeekBarChangeListener(OnRangeSeekbarChangeListener onRangeSeekbarChangeListener) {
        this.onRangeSeekbarChangeListener = onRangeSeekbarChangeListener;
        if (onRangeSeekbarChangeListener != null) {
            onRangeSeekbarChangeListener.valueChanged(getSelectedMinValue(), getSelectedMaxValue());
        }
    }

    public void setOnRangeSeekbarFinalValueListener(OnRangeSeekbarFinalValueListener onRangeSeekbarFinalValueListener) {
        this.onRangeSeekbarFinalValueListener = onRangeSeekbarFinalValueListener;
    }

    public Number getSelectedMinValue() {
        float f = 0;
        double d;
        double d2 = this.normalizedMinValue;
        float f2 = this.steps;
        if (f2 > 0.0f) {
            float f3 = this.absoluteMaxValue;
            if (f2 <= f3 / 2.0f) {
                double d3 = (f2 / (f3 - this.absoluteMinValue)) * 100.0f;
                Double.isNaN(d3);
                double d4 = d2 % d3;
                if (d4 > f / 2.0f) {
                    Double.isNaN(d3);
                    d = (d2 - d4) + d3;
                } else {
                    d = d2 - d4;
                }
                return formatValue(Double.valueOf(NormalizedToValue(d)));
            }
        }
        if (f2 == -1.0f) {
            return formatValue(Double.valueOf(NormalizedToValue(d2)));
        }
        throw new IllegalStateException("steps out of range " + this.steps);
    }

    public Number getSelectedMaxValue() {
        float f = 0;
        double d;
        double d2 = this.normalizedMaxValue;
        float f2 = this.steps;
        if (f2 > 0.0f) {
            float f3 = this.absoluteMaxValue;
            if (f2 <= f3 / 2.0f) {
                double d3 = (f2 / (f3 - this.absoluteMinValue)) * 100.0f;
                Double.isNaN(d3);
                double d4 = d2 % d3;
                if (d4 > f / 2.0f) {
                    Double.isNaN(d3);
                    d = (d2 - d4) + d3;
                } else {
                    d = d2 - d4;
                }
                return formatValue(Double.valueOf(NormalizedToValue(d)));
            }
        }
        if (f2 == -1.0f) {
            return formatValue(Double.valueOf(NormalizedToValue(d2)));
        }
        throw new IllegalStateException("steps out of range " + this.steps);
    }

    public void apply() {
        this.normalizedMinValue =  0.0d;
        this.normalizedMaxValue = 100.0d;
        float max = Math.max(0.0f, Math.min(this.gap, this.absoluteMaxValue - this.absoluteMinValue));
        this.gap = max;
        float f = this.absoluteMaxValue;
        this.gap = (max / (f - this.absoluteMinValue)) * 100.0f;
        float f2 = this.fixGap;
        if (f2 != -1.0f) {
            float min = Math.min(f2, f);
            this.fixGap = min;
            this.fixGap = (min / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            AddFixGap(true);
        }
        Bitmap bitmap = this.btmpLeftThumb;
        this.thumbWidth = bitmap != null ? bitmap.getWidth() : getResources().getDimension(R.dimen.thumb_width);
        Bitmap bitmap2 = this.btmpRightThumb;
        float height = bitmap2 != null ? bitmap2.getHeight() : getResources().getDimension(R.dimen.thumb_height);
        this.thumbHeight = height;
        this.barHeight = height * 0.5f * 0.3f;
        this.barPadding = this.thumbWidth * 0.5f;
        float f3 = this.minStartValue;
        if (f3 <= this.absoluteMinValue) {
            this.minStartValue = 0.0f;
            setNormalizedMinValue(0.0d);
        } else {
            float f4 = this.absoluteMaxValue;
            if (f3 >= f4) {
                this.minStartValue = f4;
                setMinStartValue();
            } else {
                setMinStartValue();
            }
        }
        float f5 = this.maxStartValue;
        if (f5 <= this.absoluteMinStartValue || f5 <= this.absoluteMinValue) {
            this.maxStartValue = 0.0f;
            setNormalizedMaxValue(0.0d);
        } else {
            float f6 = this.absoluteMaxValue;
            if (f5 >= f6) {
                this.maxStartValue = f6;
                setMaxStartValue();
            } else {
                setMaxStartValue();
            }
        }
        invalidate();
        OnRangeSeekbarChangeListener onRangeSeekbarChangeListener = this.onRangeSeekbarChangeListener;
        if (onRangeSeekbarChangeListener != null) {
            onRangeSeekbarChangeListener.valueChanged(getSelectedMinValue(), getSelectedMaxValue());
        }
    }

    public Thumb getPressedThumb() {
        return this.pressedThumb;
    }

    public float getThumbWidth() {
        Bitmap bitmap = this.btmpLeftThumb;
        return bitmap != null ? bitmap.getWidth() : getResources().getDimension(R.dimen.custom_range_seek_bar_thumb_width);
    }

    public float getThumbHeight() {
        Bitmap bitmap = this.btmpLeftThumb;
        return bitmap != null ? bitmap.getHeight() : getResources().getDimension(R.dimen.custom_range_seek_bar_thumb_height);
    }

    public float getBarHeight() {
        return this.thumbHeight * 0.5f * 0.3f;
    }

    public float getBarPadding() {
        return this.thumbWidth * 0.5f;
    }

    public Bitmap getBitmap(Drawable drawable) {
        if (drawable != null) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }

    public float getCornerRadius(TypedArray typedArray) {
        return typedArray.getFloat(2, 0.0f);
    }

    public float getMinValue(TypedArray typedArray) {
        return typedArray.getFloat(13, 0.0f);
    }

    public float getMaxValue(TypedArray typedArray) {
        return typedArray.getFloat(11, 100.0f);
    }

    public float getMinStartValue(TypedArray typedArray) {
        return typedArray.getFloat(12, this.minValue);
    }

    public float getMaxStartValue(TypedArray typedArray) {
        return typedArray.getFloat(10, this.maxValue);
    }

    public float getSteps(TypedArray typedArray) {
        return typedArray.getFloat(19, -1.0f);
    }

    public float getGap(TypedArray typedArray) {
        return typedArray.getFloat(5, 0.0f);
    }

    public float getFixedGap(TypedArray typedArray) {
        return typedArray.getFloat(4, -1.0f);
    }

    public int getBarColor(TypedArray typedArray) {
        return typedArray.getColor(0, -7829368);
    }

    public int getBarHighlightColor(TypedArray typedArray) {
        return typedArray.getColor(1, -16777216);
    }

    public int getLeftThumbColor(TypedArray typedArray) {
        return typedArray.getColor(6, -16777216);
    }

    @SuppressLint("ResourceType")
    public int getRightThumbColor(TypedArray typedArray) {
        return typedArray.getColor(15, -16777216);
    }

    public int getLeftThumbColorPressed(TypedArray typedArray) {
        return typedArray.getColor(7, -12303292);
    }

    public int getRightThumbColorPressed(TypedArray typedArray) {
        return typedArray.getColor(16, -12303292);
    }

    public Drawable getLeftDrawable(TypedArray typedArray) {
        return typedArray.getDrawable(8);
    }

    public Drawable getRightDrawable(TypedArray typedArray) {
        return typedArray.getDrawable(17);
    }

    public Drawable getLeftDrawablePressed(TypedArray typedArray) {
        return typedArray.getDrawable(9);
    }

    public Drawable getRightDrawablePressed(TypedArray typedArray) {
        return typedArray.getDrawable(18);
    }

    public int getDataType(TypedArray typedArray) {
        return typedArray.getInt(3, 2);
    }

    public RectF getLeftThumbRect() {
        return this.rectLeftThumb;
    }

    public RectF getRightThumbRect() {
        return this.rectRightThumb;
    }

    public void setupBar(Canvas canvas, Paint paint, RectF rectF) {
        rectF.left = this.barPadding;
        rectF.top = (getHeight() - this.barHeight) * 0.5f;
        rectF.right = getWidth() - this.barPadding;
        rectF.bottom = (getHeight() + this.barHeight) * 0.5f;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.barColor);
        paint.setAntiAlias(true);
        drawBar(canvas, paint, rectF);
    }

    public void drawBar(Canvas canvas, Paint paint, RectF rectF) {
        float f = this.cornerRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
    }

    public void setupHighlightBar(Canvas canvas, Paint paint, RectF rectF) {
        rectF.left = normalizedToScreen(this.normalizedMinValue) + (getThumbWidth() / 2.0f);
        rectF.right = normalizedToScreen(this.normalizedMaxValue) + (getThumbWidth() / 2.0f);
        paint.setColor(this.barHighlightColor);
        drawHighlightBar(canvas, paint, rectF);
    }

    public void drawHighlightBar(Canvas canvas, Paint paint, RectF rectF) {
        float f = this.cornerRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
    }

    public void setupLeftThumb(Canvas canvas, Paint paint, RectF rectF) {
        int i = Thumb.MIN.equals(this.pressedThumb) ? this.leftThumbColorPressed : this.leftThumbColorNormal;
        this.leftThumbColor = i;
        paint.setColor(i);
        this.rectLeftThumb.left = normalizedToScreen(this.normalizedMinValue);
        RectF rectF2 = this.rectLeftThumb;
        rectF2.right = Math.min(rectF2.left + (getThumbWidth() / 2.0f) + this.barPadding, getWidth());
        this.rectLeftThumb.top = 0.0f;
        this.rectLeftThumb.bottom = this.thumbHeight;
        if (this.btmpLeftThumb != null) {
            drawLeftThumbWithImage(canvas, paint, this.rectLeftThumb, Thumb.MIN.equals(this.pressedThumb) ? this.btmpLeftThumbPressed : this.btmpLeftThumb);
        } else {
            drawLeftThumbWithColor(canvas, paint, this.rectLeftThumb);
        }
    }

    public void drawLeftThumbWithColor(Canvas canvas, Paint paint, RectF rectF) {
        canvas.drawOval(rectF, paint);
    }

    public void drawLeftThumbWithImage(Canvas canvas, Paint paint, RectF rectF, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, paint);
    }

    public void setupRightThumb(Canvas canvas, Paint paint, RectF rectF) {
        int i = Thumb.MAX.equals(this.pressedThumb) ? this.rightThumbColorPressed : this.rightThumbColorNormal;
        this.rightThumbColor = i;
        paint.setColor(i);
        this.rectRightThumb.left = normalizedToScreen(this.normalizedMaxValue);
        RectF rectF2 = this.rectRightThumb;
        rectF2.right = Math.min(rectF2.left + (getThumbWidth() / 2.0f) + this.barPadding, getWidth());
        this.rectRightThumb.top = 0.0f;
        this.rectRightThumb.bottom = this.thumbHeight;
        if (this.btmpRightThumb != null) {
            drawRightThumbWithImage(canvas, paint, this.rectRightThumb, Thumb.MAX.equals(this.pressedThumb) ? this.btmpRightThumbPressed : this.btmpRightThumb);
        } else {
            drawRightThumbWithColor(canvas, paint, this.rectRightThumb);
        }
    }

    public void drawRightThumbWithColor(Canvas canvas, Paint paint, RectF rectF) {
        canvas.drawOval(rectF, paint);
    }

    public void drawRightThumbWithImage(Canvas canvas, Paint paint, RectF rectF, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, paint);
    }

    public void TrackTouchEvent(MotionEvent motionEvent) {
        try {
            float x = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
            if (Thumb.MIN.equals(this.pressedThumb)) {
                setNormalizedMinValue(screenToNormalized(x));
            } else if (Thumb.MAX.equals(this.pressedThumb)) {
                setNormalizedMaxValue(screenToNormalized(x));
            }
        } catch (Exception unused) {
        }
    }

    public int getMeasureSpecWith(int i) {
        if (View.MeasureSpec.getMode(i) != 0) {
            return View.MeasureSpec.getSize(i);
        }
        return 200;
    }

    public int getMeasureSpecHeight(int i) {
        int round = Math.round(this.thumbHeight);
        return MeasureSpec.getMode(i) != MeasureSpec.UNSPECIFIED ? Math.min(round, MeasureSpec.getSize(i)) : round;
    }

    public final void log(Object obj) {
        Log.d("CRS=>", String.valueOf(obj));
    }

    private void setMinStartValue() {
        float f = this.minStartValue;
        if (f <= this.minValue || f >= this.maxValue) {
            return;
        }
        float min = Math.min(f, this.absoluteMaxValue);
        this.minStartValue = min;
        float f2 = this.absoluteMinValue;
        float f3 = min - f2;
        this.minStartValue = f3;
        float f4 = (f3 / (this.absoluteMaxValue - f2)) * 100.0f;
        this.minStartValue = f4;
        setNormalizedMinValue(f4);
    }

    private void setMaxStartValue() {
        float f = this.maxStartValue;
        if (f < this.absoluteMaxValue) {
            float f2 = this.absoluteMinValue;
            if (f <= f2 || f <= this.absoluteMinStartValue) {
                return;
            }
            float max = Math.max(this.absoluteMaxStartValue, f2);
            this.maxStartValue = max;
            float f3 = this.absoluteMinValue;
            float f4 = max - f3;
            this.maxStartValue = f4;
            float f5 = (f4 / (this.absoluteMaxValue - f3)) * 100.0f;
            this.maxStartValue = f5;
            setNormalizedMaxValue(f5);
        }
    }

    private Thumb evalPressedThumb(float f) {
        boolean isInThumbRange = isInThumbRange(f, this.normalizedMinValue);
        boolean isInThumbRange2 = isInThumbRange(f, this.normalizedMaxValue);
        if (isInThumbRange && isInThumbRange2) {
            return f / ((float) getWidth()) > 0.5f ? Thumb.MIN : Thumb.MAX;
        } else if (isInThumbRange) {
            return Thumb.MIN;
        } else {
            if (isInThumbRange2) {
                return Thumb.MAX;
            }
            return null;
        }
    }

    private boolean isInThumbRange(float f, double d) {
        float normalizedToScreen = normalizedToScreen(d);
        float thumbWidth = normalizedToScreen - (getThumbWidth() / 2.0f);
        float thumbWidth2 = (getThumbWidth() / 2.0f) + normalizedToScreen;
        float thumbWidth3 = f - (getThumbWidth() / 2.0f);
        if (normalizedToScreen <= getWidth() - this.thumbWidth) {
            f = thumbWidth3;
        }
        return f >= thumbWidth && f <= thumbWidth2;
    }

    private void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    private void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    private float normalizedToScreen(double d) {
        return (((float) d) / 100.0f) * (getWidth() - (this.barPadding * 2.0f));
    }

    private double screenToNormalized(float f) {
        double width = getWidth();
        double d = this.barPadding * 2.0f;
        if (width <= d) {
            return 0.0d;
        }
        Double.isNaN(width);
        Double.isNaN(d);
        double d2 = width - d;
        double d3 = f;
        Double.isNaN(d3);
        double d4 = this.barPadding;
        Double.isNaN(d4);
        return Math.min(100.0d, Math.max((double) 0.0d, ((d3 / d2) * 100.0d) - ((d4 / d2) * 100.0d)));
    }

    private void setNormalizedMinValue(double d) {
        this.normalizedMinValue = Math.max((double) 0.0d, Math.min(100.0d, Math.min(d, this.normalizedMaxValue)));
        float f = this.fixGap;
        if (f == -1.0f || f <= 0.0f) {
            AddMinGap();
        } else {
            AddFixGap(true);
        }
        invalidate();
    }

    private void setNormalizedMaxValue(double d) {
        this.normalizedMaxValue = Math.max((double) 0.0d, Math.min(100.0d, Math.max(d, this.normalizedMinValue)));
        float f = this.fixGap;
        if (f == -1.0f || f <= 0.0f) {
            AddMaxGap();
        } else {
            AddFixGap(false);
        }
        invalidate();
    }

    private void AddFixGap(boolean z) {
        if (z) {
            Double.isNaN(this.fixGap);
            double d = this.normalizedMinValue;
            double d2 = this.fixGap;
            double d3 = d + d2;
            this.normalizedMaxValue = d3;
            if (d3 >= 100.0d) {
                this.normalizedMaxValue = 100.0d;
                Double.isNaN(d2);
                this.normalizedMinValue = this.normalizedMaxValue - this.fixGap;
                return;
            }
            return;
        }
        Double.isNaN(this.fixGap);
        double d4 = this.normalizedMaxValue;
        double d5 = this.fixGap;
        double d6 = d4 - d5;
        this.normalizedMinValue = d6;
        if (d6 <= 0.0d) {
            this.normalizedMinValue = 0.0d;
            Double.isNaN(d5);
            this.normalizedMaxValue = this.normalizedMinValue + this.fixGap;
        }
    }

    private void AddMinGap() {
        Double.isNaN(this.gap);
        double d = this.gap;
        if (this.normalizedMinValue + d > this.normalizedMaxValue) {
            Double.isNaN(d);
            double d2 = this.normalizedMinValue;
            double d3 = this.gap + d2;
            this.normalizedMaxValue = d3;
            this.normalizedMaxValue = Math.max((double) 0.0d, Math.min(100.0d, Math.max(d3, d2)));
            Double.isNaN(this.gap);
            double d4 = this.normalizedMinValue;
            double d5 = this.normalizedMaxValue;
            double d6 = this.gap;
            if (d4 >= d5 - d6) {
                Double.isNaN(d6);
                this.normalizedMinValue = this.normalizedMaxValue - this.gap;
            }
        }
    }

    private void AddMaxGap() {
        Double.isNaN(this.gap);
        double d = this.normalizedMaxValue;
        double d2 = this.gap;
        if (d - d2 < this.normalizedMinValue) {
            Double.isNaN(d2);
            double d3 = this.normalizedMaxValue;
            double d4 = d3 - this.gap;
            this.normalizedMinValue = d4;
            this.normalizedMinValue = Math.max((double) 0.0d, Math.min(100.0d, Math.min(d4, d3)));
            Double.isNaN(this.gap);
            double d5 = this.normalizedMaxValue;
            double d6 = this.gap;
            if (d5 <= this.normalizedMinValue + d6) {
                Double.isNaN(d6);
                this.normalizedMaxValue = this.normalizedMinValue + this.gap;
            }
        }
    }

    private double NormalizedToValue(double d) {
        double d2 = this.maxValue - this.minValue;
        Double.isNaN(d2);
        Double.isNaN(this.minValue);
        return ((d / 100.0d) * d2) + this.minValue;
    }

    private void AttemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private <T extends Number> Number formatValue(T t) throws IllegalArgumentException {
        Double d = (Double) t;
        int i = this.dataType;
        if (i == 0) {
            return Long.valueOf(d.longValue());
        }
        if (i == 1) {
            return d;
        }
        if (i == 2) {
            return Long.valueOf(Math.round(d.doubleValue()));
        }
        if (i == 3) {
            return Float.valueOf(d.floatValue());
        }
        if (i == 4) {
            return Short.valueOf(d.shortValue());
        }
        if (i == 5) {
            return Byte.valueOf(d.byteValue());
        }
        throw new IllegalArgumentException("Number class '" + t.getClass().getName() + "' is not supported");
    }

    @Override // android.view.View
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode()) {
            setupBar(canvas, this._paint, this._rect);
            setupHighlightBar(canvas, this._paint, this._rect);
            setupLeftThumb(canvas, this._paint, this._rect);
            setupRightThumb(canvas, this._paint, this._rect);
        }
    }

    @Override // android.view.View
    public synchronized void onMeasure(int i, int i2) {
        setMeasuredDimension(getMeasureSpecWith(i), getMeasureSpecHeight(i2));
    }

    @Override // android.view.View
    public synchronized boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                int pointerId = motionEvent.getPointerId(motionEvent.getPointerCount() - 1);
                this.mActivePointerId = pointerId;
                int findPointerIndex = motionEvent.findPointerIndex(pointerId);
                this.pointerIndex = findPointerIndex;
                Thumb evalPressedThumb = evalPressedThumb(motionEvent.getX(findPointerIndex));
                this.pressedThumb = evalPressedThumb;
                if (evalPressedThumb == null) {
                    return super.onTouchEvent(motionEvent);
                }
                touchDown(motionEvent.getX(this.pointerIndex), motionEvent.getY(this.pointerIndex));
                setPressed(true);
                invalidate();
                onStartTrackingTouch();
                TrackTouchEvent(motionEvent);
                AttemptClaimDrag();
            } else if (action == 1) {
                if (this.mIsDragging) {
                    TrackTouchEvent(motionEvent);
                    onStopTrackingTouch();
                    setPressed(false);
                    touchUp(motionEvent.getX(this.pointerIndex), motionEvent.getY(this.pointerIndex));
                    OnRangeSeekbarFinalValueListener onRangeSeekbarFinalValueListener = this.onRangeSeekbarFinalValueListener;
                    if (onRangeSeekbarFinalValueListener != null) {
                        onRangeSeekbarFinalValueListener.finalValue(getSelectedMinValue(), getSelectedMaxValue());
                    }
                } else {
                    onStartTrackingTouch();
                    TrackTouchEvent(motionEvent);
                    onStopTrackingTouch();
                }
                this.pressedThumb = null;
                invalidate();
                OnRangeSeekbarChangeListener onRangeSeekbarChangeListener = this.onRangeSeekbarChangeListener;
                if (onRangeSeekbarChangeListener != null) {
                    onRangeSeekbarChangeListener.valueChanged(getSelectedMinValue(), getSelectedMaxValue());
                }
            } else if (action != 2) {
                if (action == 3) {
                    if (this.mIsDragging) {
                        onStopTrackingTouch();
                        setPressed(false);
                        touchUp(motionEvent.getX(this.pointerIndex), motionEvent.getY(this.pointerIndex));
                    }
                    invalidate();
                } else if (action != 5 && action == 6) {
                    invalidate();
                }
            } else if (this.pressedThumb != null) {
                if (this.mIsDragging) {
                    touchMove(motionEvent.getX(this.pointerIndex), motionEvent.getY(this.pointerIndex));
                    TrackTouchEvent(motionEvent);
                }
                OnRangeSeekbarChangeListener onRangeSeekbarChangeListener2 = this.onRangeSeekbarChangeListener;
                if (onRangeSeekbarChangeListener2 != null) {
                    onRangeSeekbarChangeListener2.valueChanged(getSelectedMinValue(), getSelectedMaxValue());
                }
            }
            return true;
        }
        return false;
    }
}
