package hdphoto.galleryimages.gelleryalbum.Images_android.Sticker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


public class StickerImageView extends DemoStickerView {
    private ImageView iv_main;
    private String owner_id;

    public StickerImageView(Context context, DemoStickerView.OnTouchSticker onTouchSticker) {
        super(context, onTouchSticker);
    }

    public StickerImageView(Context context, AttributeSet attributeSet, DemoStickerView.OnTouchSticker onTouchSticker) {
        super(context, attributeSet, onTouchSticker);
    }

    public StickerImageView(Context context, AttributeSet attributeSet, int i, DemoStickerView.OnTouchSticker onTouchSticker) {
        super(context, attributeSet, i, onTouchSticker);
    }

    public String getOwnerId() {
        return this.owner_id;
    }

    public void setOwnerId(String str) {
        this.owner_id = str;
    }

    @Override
    public View getMainView() {
        if (this.iv_main == null) {
            this.iv_main = new ImageView(getContext());
        }
        return this.iv_main;
    }

    public void setImageResource(int i) {
        this.iv_main.setImageResource(i);
    }

    public void setImageDrawable(Drawable drawable) {
        this.iv_main.setImageDrawable(drawable);
    }

    public Bitmap getImageBitmap() {
        return ((BitmapDrawable) this.iv_main.getDrawable()).getBitmap();
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.iv_main.setImageBitmap(bitmap);
    }
}
