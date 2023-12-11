package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import hdphoto.galleryimages.gelleryalbum.R;


public class GradientColorView extends View {
    int[] colorArray;
    Rect[] colorFullRects;
    Rect[] colorRects;
    Paint paint;

    public GradientColorView(Context context) {
        this(context, null);
    }

    public GradientColorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GradientColorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.colorArray = new int[0];
        this.colorRects = new Rect[0];
        this.colorFullRects = new Rect[0];
        Bind(context, attributeSet);
    }

    public void setHexColors(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        ConvertToColors(strArr);
        CalculateRectangles();
        invalidate();
    }

    public void setColors(int[] iArr) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        this.colorArray = iArr;
        CalculateRectangles();
        invalidate();
    }

    public void setGradient(int i, int i2, int i3) {
        if (i == 0 || i2 == 0 || i3 == 0) {
            return;
        }
        CalculateColors(i, i2, i3);
        CalculateRectangles();
        invalidate();
    }

    public void setGradient(int[] iArr, int i) {
        if (iArr == null || iArr.length < 2) {
            throw new IllegalArgumentException("Colors array must contain 2 or more color.");
        }
        if (iArr.length == 2) {
            setGradient(iArr[0], iArr[1], i);
            return;
        }
        CalculateColors(iArr, i);
        CalculateRectangles();
        invalidate();
    }

    private void Bind(Context context, AttributeSet attributeSet) {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ColorSlider, 0, 0);
            try {
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                int resourceId2 = obtainStyledAttributes.getResourceId(2, 0);
                if (resourceId != 0) {
                    int[] intArray = getResources().getIntArray(resourceId);
                    if (intArray.length > 0) {
                        int[] iArr = new int[intArray.length];
                        this.colorArray = iArr;
                        System.arraycopy(intArray, 0, iArr, 0, intArray.length);
                    }
                } else if (resourceId2 != 0) {
                    String[] stringArray = getResources().getStringArray(resourceId2);
                    if (stringArray.length > 0) {
                        ConvertToColors(stringArray);
                    }
                }
                if (this.colorArray.length == 0) {
                    int color = obtainStyledAttributes.getColor(1, 0);
                    int color2 = obtainStyledAttributes.getColor(5, 0);
                    int i = obtainStyledAttributes.getInt(4, 21);
                    if (color != 0 && color2 != 0 && i != 0) {
                        CalculateColors(color, color2, i);
                    }
                }
            } catch (Exception e) {
                Log.d("ColorSlider", "init: " + e.getLocalizedMessage());
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
            obtainStyledAttributes.recycle();
        }
        if (this.colorArray.length == 0) {
            BindDefaultColors();
        }
        int[] iArr2 = this.colorArray;
        this.colorRects = new Rect[iArr2.length];
        this.colorFullRects = new Rect[iArr2.length];
    }

    private void BindDefaultColors() {
        this.colorArray = new int[]{Color.parseColor("#F44336"), Color.parseColor("#E91E63"), Color.parseColor("#9C27B0"), Color.parseColor("#673AB7"), Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"), Color.parseColor("#03A9F4"), Color.parseColor("#00BCD4"), Color.parseColor("#009688"), Color.parseColor("#4CAF50"), Color.parseColor("#8BC34A"), Color.parseColor("#CDDC39"), Color.parseColor("#FFEB3B"), Color.parseColor("#FFC107"), Color.parseColor("#FF9800"), Color.parseColor("#FF5722"), Color.parseColor("#795548"), Color.parseColor("#9E9E9E"), Color.parseColor("#607D8B"), Color.parseColor("#FFFFFF")};
    }

    private void ConvertToColors(String[] strArr) {
        this.colorArray = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this.colorArray[i] = Color.parseColor(strArr[i]);
        }
    }

    private void CalculateColors(int[] iArr, int i) {
        GradientColorView cMGradientColorView = this;
        int[] iArr2 = iArr;
        int length = iArr2.length;
        int i2 = length - 1;
        int i3 = i / i2;
        int i4 = i % i3;
        if (i4 == 0) {
            i4 = 0;
        }
        cMGradientColorView.colorArray = new int[i];
        int i5 = 1;
        while (i5 < length) {
            int i6 = i5 - 1;
            int i7 = i6 * i3;
            int i8 = i5 * i3;
            if (i5 == i2) {
                i8 += i4;
            }
            float alpha = Color.alpha(iArr2[i6]);
            float red = Color.red(iArr2[i6]);
            float green = Color.green(iArr2[i6]);
            float blue = Color.blue(iArr2[i6]);
            float f = i8 - i7;
            float alpha2 = (Color.alpha(iArr2[i5]) - alpha) / f;
            float red2 = (Color.red(iArr2[i5]) - red) / f;
            int i9 = length;
            float green2 = (Color.green(iArr2[i5]) - green) / f;
            float blue2 = (Color.blue(iArr2[i5]) - blue) / f;
            int i10 = 0;
            while (i7 < i8) {
                int i11 = i2;
                int[] iArr3 = cMGradientColorView.colorArray;
                float f2 = i10;
                iArr3[i7] = Color.argb((int) (alpha + (alpha2 * f2)), (int) (red + (red2 * f2)), (int) (green + (green2 * f2)), (int) ((f2 * blue2) + blue));
                i10++;
                i7++;
                cMGradientColorView = this;
                i2 = i11;
                i3 = i3;
                red2 = red2;
                green2 = green2;
            }
            i5++;
            cMGradientColorView = this;
            iArr2 = iArr;
            length = i9;
        }
    }

    private void CalculateColors(int i, int i2, int i3) {
        float alpha = Color.alpha(i);
        float red = Color.red(i);
        float green = Color.green(i);
        float blue = Color.blue(i);
        float f = i3;
        float alpha2 = (Color.alpha(i2) - alpha) / f;
        float red2 = (Color.red(i2) - red) / f;
        float green2 = (Color.green(i2) - green) / f;
        float blue2 = (Color.blue(i2) - blue) / f;
        this.colorArray = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            float f2 = i4;
            this.colorArray[i4] = Color.argb((int) ((alpha2 * f2) + alpha), (int) ((red2 * f2) + red), (int) ((green2 * f2) + green), (int) ((f2 * blue2) + blue));
        }
    }

    public int GetColor(int i) {
        return this.colorArray[i];
    }

    public int GetColorsCount() {
        return this.colorArray.length;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.colorRects.length > 0) {
            DrawSlider(canvas);
        }
    }

    private void DrawSlider(Canvas canvas) {
        if (this.paint != null) {
            for (int i = 0; i < this.colorRects.length; i++) {
                this.paint.setColor(this.colorArray[i]);
                canvas.drawRect(this.colorRects[i], this.paint);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        CalculateRectangles();
    }

    private void CalculateRectangles() {
        float measuredHeight = getMeasuredHeight();
        int[] iArr = this.colorArray;
        float measuredWidth = getMeasuredWidth() / iArr.length;
        this.colorRects = new Rect[iArr.length];
        this.colorFullRects = new Rect[iArr.length];
        float f = 0.1f * measuredHeight;
        int i = 0;
        while (i < this.colorArray.length) {
            int i2 = (int) (i * measuredWidth);
            int i3 = i + 1;
            int i4 = (int) (i3 * measuredWidth);
            this.colorRects[i] = new Rect(i2, (int) f, i4, (int) (measuredHeight - f));
            this.colorFullRects[i] = new Rect(i2, 0, i4, (int) measuredHeight);
            i = i3;
        }
    }
}
