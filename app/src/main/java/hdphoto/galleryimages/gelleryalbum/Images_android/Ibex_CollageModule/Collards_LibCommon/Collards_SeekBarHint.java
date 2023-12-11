package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;


public class Collards_SeekBarHint extends AppCompatSeekBar {
    Drawable mThumb;

    public Collards_SeekBarHint(Context context) {
        super(context);
    }

    public Collards_SeekBarHint(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public Collards_SeekBarHint(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable drawable) {
        super.setThumb(drawable);
        this.mThumb = drawable;
    }

    public Drawable getSeekBarThumb() {
        return this.mThumb;
    }
}
