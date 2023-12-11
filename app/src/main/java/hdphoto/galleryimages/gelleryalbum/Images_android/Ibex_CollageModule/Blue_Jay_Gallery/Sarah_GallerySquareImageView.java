package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;


public class Sarah_GallerySquareImageView extends AppCompatImageView {
    public Sarah_GallerySquareImageView(Context context) {
        super(context);
    }

    public Sarah_GallerySquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Sarah_GallerySquareImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth);
    }
}
