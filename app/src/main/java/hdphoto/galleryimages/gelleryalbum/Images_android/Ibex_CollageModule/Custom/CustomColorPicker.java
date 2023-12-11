package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import hdphoto.galleryimages.gelleryalbum.R;


public class CustomColorPicker extends DialogFragment {
    ColorPickerView colorPickerView;
    GGradientColorRangeBar gradientColorRangeBar;
    OnBackgroundSelectedListener onBackgroundSelectedListener;
    RelativeLayout rlBackgroundColorTab;
    RelativeLayout rlGradientBackgroundTab;
    BackgroundType selectedBackgroundType = BackgroundType.SOLID;


    public enum BackgroundType {
        SOLID,
        GRADIENT
    }


    public interface OnBackgroundSelectedListener {
        void onGradientBackgroundSelected(int i, int i2);

        void onSolidBackgroundSelected(int i);
    }

    public static void show(FragmentManager fragmentManager) {
        newInstance().show(fragmentManager, "");
    }

    public static CustomColorPicker newInstance() {
        return new CustomColorPicker();
    }

    @Override 
    public void onAttach(Context context) {
        super.onAttach(context);
        this.onBackgroundSelectedListener = (OnBackgroundSelectedListener) context;
    }

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(2, R.style.ThemeWithCorners1);
    }

    @Override 
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(-1, -2);
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        View inflate = layoutInflater.inflate(R.layout.dg_fragment_color_picker, viewGroup, false);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.tv_background_color_tab);
        this.rlBackgroundColorTab = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCustom.CMCustomColorPicker.1
            @Override 
            public final void onClick(View view) {
                CustomColorPicker.this.BackgroundColor();
            }
        });
        RelativeLayout relativeLayout2 = (RelativeLayout) inflate.findViewById(R.id.tv_gradient_background_tab);
        this.rlGradientBackgroundTab = relativeLayout2;
        relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCustom.CMCustomColorPicker.2
            @Override 
            public final void onClick(View view) {
                CustomColorPicker.this.GradientBackgroundColor();
            }
        });
        ColorPickerView colorPickerView = (ColorPickerView) inflate.findViewById(R.id.color_picker_view);
        this.colorPickerView = colorPickerView;
        colorPickerView.addOnColorChangedListener(new OnColorChangedListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCustom.CMCustomColorPicker.3
            @Override // com.flask.colorpicker.OnColorChangedListener
            public final void onColorChanged(int i) {
                CustomColorPicker.this.ColorPickerView(i);
            }
        });
        GGradientColorRangeBar cMGradientColorRangeBar = (GGradientColorRangeBar) inflate.findViewById(R.id.v_gradient_range_bar);
        this.gradientColorRangeBar = cMGradientColorRangeBar;
        cMGradientColorRangeBar.SetGradient(this.colorPickerView.getSelectedColor(), ColorUtils.blendARGB(this.colorPickerView.getSelectedColor(), -1, 0.8f), 500);
        inflate.findViewById(R.id.rl_no).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCustom.CMCustomColorPicker.4
            @Override 
            public final void onClick(View view) {
                CustomColorPicker.this.ColorPickerCancel(view);
            }
        });
        inflate.findViewById(R.id.rl_yes).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCustom.CMCustomColorPicker.5
            @Override 
            public final void onClick(View view) {
                CustomColorPicker.this.ColorPickerDone(view);
            }
        });
        onBackgroundTypeSelected();
        return inflate;
    }

    public void BackgroundColor() {
        if (this.selectedBackgroundType != BackgroundType.SOLID) {
            this.selectedBackgroundType = BackgroundType.SOLID;
            onBackgroundTypeSelected();
        }
    }

    public void GradientBackgroundColor() {
        if (this.selectedBackgroundType != BackgroundType.GRADIENT) {
            this.selectedBackgroundType = BackgroundType.GRADIENT;
            onBackgroundTypeSelected();
        }
    }

    public void ColorPickerView(int i) {
        this.gradientColorRangeBar.SetGradient(i, ColorUtils.blendARGB(this.colorPickerView.getSelectedColor(), -1, 0.8f), 500);
    }

    public void ColorPickerCancel(View view) {
        dismiss();
    }

    public void ColorPickerDone(View view) {
        if (this.onBackgroundSelectedListener != null) {
            int i = BackgroundColorType.SelectedTypeOfColor[this.selectedBackgroundType.ordinal()];
            if (i == 1) {
                this.onBackgroundSelectedListener.onSolidBackgroundSelected(this.colorPickerView.getSelectedColor());
            } else if (i == 2) {
                this.onBackgroundSelectedListener.onGradientBackgroundSelected(this.gradientColorRangeBar.GetColorFrom(), this.gradientColorRangeBar.GetColorTo());
            }
            dismiss();
        }
    }

    @Override 
    public void onDetach() {
        this.onBackgroundSelectedListener = null;
        super.onDetach();
    }


    public static class BackgroundColorType {
        static final int[] SelectedTypeOfColor;

        static {
            int[] iArr = new int[BackgroundType.values().length];
            SelectedTypeOfColor = iArr;
            iArr[BackgroundType.SOLID.ordinal()] = 1;
            iArr[BackgroundType.GRADIENT.ordinal()] = 2;
        }
    }

    private void onBackgroundTypeSelected() {
        int i = BackgroundColorType.SelectedTypeOfColor[this.selectedBackgroundType.ordinal()];
        if (i == 1) {
            this.rlBackgroundColorTab.setBackgroundResource(R.drawable.gradientbtn);
            this.rlGradientBackgroundTab.setBackgroundResource(R.drawable.gradientbtn1);
            this.gradientColorRangeBar.setVisibility(4);
        } else if (i == 2) {
            this.rlBackgroundColorTab.setBackgroundResource(R.drawable.gradientbtn1);
            this.rlGradientBackgroundTab.setBackgroundResource(R.drawable.gradientbtn);
            this.gradientColorRangeBar.setVisibility(0);
        }
    }

    private void underlineTextView(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | 8);
    }

    private void removeUnderlineFromTextView(TextView textView) {
        textView.setPaintFlags(0);
    }
}
