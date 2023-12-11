package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtDecorateView;


public class Ledl_StickerFrameLayout extends FrameLayout {
    static final String TAG = "StickerFrameLayout";
    CtDecorateView decorateView;
    int index;

    public Ledl_StickerFrameLayout(Context context) {
        super(context);
        this.decorateView = null;
        this.index = -1;
    }

    public Ledl_StickerFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.decorateView = null;
        this.index = -1;
    }

    public Ledl_StickerFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.decorateView = null;
        this.index = -1;
    }

    public Ledl_StickerFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.decorateView = null;
        this.index = -1;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        CtDecorateView cMDecorateView;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.index = SelectedChild(x, y);
            Log.e(TAG, "selectedChild " + this.index);
            if (this.index >= 0) {
                if (getChildCount() > 0) {
                    for (int i = 0; i < getChildCount(); i++) {
                        if (i != this.index) {
                            ((CtDecorateView) getChildAt(i)).setDecorateViewSelected(false);
                        }
                    }
                }
                CtDecorateView cMDecorateView2 = (CtDecorateView) getChildAt(this.index);
                this.decorateView = cMDecorateView2;
                cMDecorateView2.isOnRectCheck(x, y);
                this.decorateView.dispatchTouchEvent(motionEvent);
            }
            Log.e(TAG, "pointer count = " + motionEvent.getPointerCount());
        } else if (action == 1) {
            CtDecorateView cMDecorateView3 = this.decorateView;
            if (cMDecorateView3 != null) {
                cMDecorateView3.dispatchTouchEvent(motionEvent);
            }
            this.decorateView = null;
            this.index = -1;
        } else if (action == 2) {
            CtDecorateView cMDecorateView4 = this.decorateView;
            if (cMDecorateView4 != null) {
                cMDecorateView4.dispatchTouchEvent(motionEvent);
            }
        } else if (action == 6 && (cMDecorateView = this.decorateView) != null) {
            cMDecorateView.dispatchTouchEvent(motionEvent);
        }
        return this.index >= 0;
    }

    private int SelectedChild(float f, float f2) {
        int i = -1;
        if (getChildCount() > 0) {
            float f3 = -1.0f;
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                float containsScore = ((CtDecorateView) getChildAt(i2)).containsScore(f, f2);
                if (containsScore > f3) {
                    i = i2;
                    f3 = containsScore;
                }
            }
        }
        return i;
    }
}
