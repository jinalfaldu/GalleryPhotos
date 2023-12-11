package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import hdphoto.galleryimages.gelleryalbum.R;


public class GGradientColorRangeBar extends LinearLayout {
    GradientColorView gradientColorView;
    CustomRangeSeekbar rangeSeekBar;

    public GGradientColorRangeBar(Context context) {
        this(context, null);
    }

    public GGradientColorRangeBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GGradientColorRangeBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutBind();
    }

    private void LayoutBind() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_gradient_color_range_bar, (ViewGroup) this, true);
        this.rangeSeekBar = (CustomRangeSeekbar) findViewById(R.id.range_seek_bar);
        this.gradientColorView = (GradientColorView) findViewById(R.id.gradient_color_view);
    }

    public void SetGradient(int i, int i2, int i3) {
        this.gradientColorView.setGradient(i, i2, i3);
        this.rangeSeekBar.setMinValue(0.0f);
        this.rangeSeekBar.setMaxValue(this.gradientColorView.GetColorsCount() - 1);
        this.rangeSeekBar.setSteps(1.0f);
        this.rangeSeekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekbarChangeListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCustom.CMGradientColorRangeBar.1
            @Override // com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener
            public final void valueChanged(Number number, Number number2) {
                GGradientColorRangeBar.this.GradientColorRangeBar(number, number2);
            }
        });
        this.rangeSeekBar.invalidate();
    }

    public void GradientColorRangeBar(Number number, Number number2) {
        int GetColor = this.gradientColorView.GetColor(number.intValue());
        this.rangeSeekBar.setLeftThumbColor(GetColor);
        this.rangeSeekBar.setLeftThumbHighlightColor(GetColor);
        int GetColor2 = this.gradientColorView.GetColor(number2.intValue());
        this.rangeSeekBar.setRightThumbColor(GetColor2);
        this.rangeSeekBar.setRightThumbHighlightColor(GetColor2);
    }

    public int GetColorFrom() {
        return this.gradientColorView.GetColor(this.rangeSeekBar.getSelectedMinValue().intValue());
    }

    public int GetColorTo() {
        return this.gradientColorView.GetColor(this.rangeSeekBar.getSelectedMaxValue().intValue());
    }
}
