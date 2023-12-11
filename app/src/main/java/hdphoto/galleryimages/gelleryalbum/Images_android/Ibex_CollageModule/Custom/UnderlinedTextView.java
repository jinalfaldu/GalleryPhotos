package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;


public class UnderlinedTextView extends AppCompatTextView {
    public UnderlinedTextView(Context context) {
        this(context, null);
    }

    public UnderlinedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public UnderlinedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPaintFlags(getPaintFlags() | 8);
    }
}
