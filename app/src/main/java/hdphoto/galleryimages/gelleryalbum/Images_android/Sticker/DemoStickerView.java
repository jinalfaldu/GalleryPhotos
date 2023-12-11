package hdphoto.galleryimages.gelleryalbum.Images_android.Sticker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import hdphoto.galleryimages.gelleryalbum.R;


public abstract class DemoStickerView extends FrameLayout implements View.OnClickListener {
    private static final int BUTTON_SIZE_DP = 30;
    private static final int SELF_SIZE_DP = 100;
    public static final String TAG = "com.stickerView";
    public double centerX;
    public double centerY;
    private BorderView iv_border;
    private ImageView iv_delete;
    private ImageView iv_flip;
    private ImageView iv_scale;
    private View.OnTouchListener mTouchListener;
    public float move_orgX;
    public float move_orgY;
    OnTouchSticker onTouchSticker;
    public float rotate_newX;
    public float rotate_newY;
    public float rotate_orgX;
    public float rotate_orgY;
    public double scale_orgHeight;
    public double scale_orgWidth;
    public float scale_orgX;
    public float scale_orgY;
    public float this_orgX;
    public float this_orgY;


    public interface OnTouchSticker {
        void onTouchedSticker();
    }

    public abstract View getMainView();

    public void onRotating() {
    }

    public void onScaling(boolean z) {
    }

    public DemoStickerView(Context context, OnTouchSticker onTouchSticker) {
        super(context);
        this.mTouchListener = new View.OnTouchListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.Sticker.DemoStickerView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int i;
                DemoStickerView demoStickerView = null;
                DemoStickerView demoStickerView2 = null;
                DemoStickerView demoStickerView3 = null;
                if (view.getTag().equals("DraggableViewGroup")) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        DemoStickerView.this.setControlItemsHidden(true);
                        DemoStickerView.this.onTouchSticker.onTouchedSticker();
                        Log.v(DemoStickerView.TAG, "sticker view action down");
                        DemoStickerView.this.move_orgX = motionEvent.getRawX();
                        DemoStickerView.this.move_orgY = motionEvent.getRawY();
                        return true;
                    } else if (action == 1) {
                        Log.v(DemoStickerView.TAG, "sticker view action up");
                        DemoStickerView.this.setControlItemsHidden(false);
                        return true;
                    } else if (action != 2) {
                        return true;
                    } else {
                        Log.v(DemoStickerView.TAG, "sticker view action move_right");
                        float rawX = motionEvent.getRawX() - DemoStickerView.this.move_orgX;
                        float rawY = motionEvent.getRawY() - DemoStickerView.this.move_orgY;
                        DemoStickerView demoStickerView4 = DemoStickerView.this;
                        demoStickerView4.setX(demoStickerView4.getX() + rawX);
                        DemoStickerView demoStickerView5 = DemoStickerView.this;
                        demoStickerView5.setY(demoStickerView5.getY() + rawY);
                        DemoStickerView.this.move_orgX = motionEvent.getRawX();
                        DemoStickerView.this.move_orgY = motionEvent.getRawY();
                        return true;
                    }
                } else if (view.getTag().equals("iv_scale")) {
                    int action2 = motionEvent.getAction();
                    if (action2 == 0) {
                        Log.v(DemoStickerView.TAG, "iv_scale action down");
                        DemoStickerView demoStickerView6 = DemoStickerView.this;
                        demoStickerView6.this_orgX = demoStickerView6.getX();
                        DemoStickerView demoStickerView7 = DemoStickerView.this;
                        demoStickerView7.this_orgY = demoStickerView7.getY();
                        DemoStickerView.this.scale_orgX = motionEvent.getRawX();
                        DemoStickerView.this.scale_orgY = motionEvent.getRawY();
                        DemoStickerView.this.scale_orgWidth = demoStickerView.getLayoutParams().width;
                        DemoStickerView.this.scale_orgHeight = demoStickerView2.getLayoutParams().height;
                        DemoStickerView.this.rotate_orgX = motionEvent.getRawX();
                        DemoStickerView.this.rotate_orgY = motionEvent.getRawY();
                        DemoStickerView.this.centerX = demoStickerView3.getX() + ((View) DemoStickerView.this.getParent()).getX() + (DemoStickerView.this.getWidth() / 2.0f);
                        int identifier = DemoStickerView.this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                        double dimensionPixelSize = identifier > 0 ? DemoStickerView.this.getResources().getDimensionPixelSize(identifier) : 0;
                        DemoStickerView demoStickerView8 = DemoStickerView.this;
                        double y = demoStickerView8.getY() + ((View) DemoStickerView.this.getParent()).getY();
                        Double.isNaN(y);
                        Double.isNaN(dimensionPixelSize);
                        double d = y + dimensionPixelSize;
                        double height = DemoStickerView.this.getHeight() / 2.0f;
                        Double.isNaN(height);
                        demoStickerView8.centerY = d + height;
                        return true;
                    } else if (action2 == 1) {
                        Log.v(DemoStickerView.TAG, "iv_scale action up");
                        return true;
                    } else if (action2 != 2) {
                        return true;
                    } else {
                        Log.v(DemoStickerView.TAG, "iv_scale action move_right");
                        DemoStickerView.this.rotate_newX = motionEvent.getRawX();
                        DemoStickerView.this.rotate_newY = motionEvent.getRawY();
                        double atan2 = Math.atan2(motionEvent.getRawY() - DemoStickerView.this.scale_orgY, motionEvent.getRawX() - DemoStickerView.this.scale_orgX);
                        double d2 = DemoStickerView.this.scale_orgY;
                        double d3 = DemoStickerView.this.centerY;
                        Double.isNaN(d2);
                        double d4 = d2 - d3;
                        double d5 = DemoStickerView.this.scale_orgX;
                        double d6 = DemoStickerView.this.centerX;
                        Double.isNaN(d5);
                        double abs = (Math.abs(atan2 - Math.atan2(d4, d5 - d6)) * 180.0d) / 3.141592653589793d;
                        Log.v(DemoStickerView.TAG, "angle_diff: " + abs);
                        DemoStickerView demoStickerView9 = DemoStickerView.this;
                        double length = demoStickerView9.getLength(demoStickerView9.centerX, DemoStickerView.this.centerY, (double) DemoStickerView.this.scale_orgX, (double) DemoStickerView.this.scale_orgY);
                        DemoStickerView demoStickerView10 = DemoStickerView.this;
                        double length2 = demoStickerView10.getLength(demoStickerView10.centerX, DemoStickerView.this.centerY, motionEvent.getRawX(), motionEvent.getRawY());
                        int convertDpToPixel = DemoStickerView.convertDpToPixel(100.0f, DemoStickerView.this.getContext());
                        if (length2 > length && (abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d)) {
                            double round = Math.round(Math.max(Math.abs(motionEvent.getRawX() - DemoStickerView.this.scale_orgX), Math.abs(motionEvent.getRawY() - DemoStickerView.this.scale_orgY)));
                            ViewGroup.LayoutParams layoutParams = DemoStickerView.this.getLayoutParams();
                            double d7 = layoutParams.width;
                            Double.isNaN(d7);
                            Double.isNaN(round);
                            layoutParams.width = (int) (d7 + round);
                            ViewGroup.LayoutParams layoutParams2 = DemoStickerView.this.getLayoutParams();
                            double d8 = layoutParams2.height;
                            Double.isNaN(d8);
                            Double.isNaN(round);
                            layoutParams2.height = (int) (d8 + round);
                            DemoStickerView.this.onScaling(true);
                        } else if (length2 < length && ((abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d) && DemoStickerView.this.getLayoutParams().width > (i = convertDpToPixel / 2) && DemoStickerView.this.getLayoutParams().height > i)) {
                            double round2 = Math.round(Math.max(Math.abs(motionEvent.getRawX() - DemoStickerView.this.scale_orgX), Math.abs(motionEvent.getRawY() - DemoStickerView.this.scale_orgY)));
                            ViewGroup.LayoutParams layoutParams3 = DemoStickerView.this.getLayoutParams();
                            double d9 = layoutParams3.width;
                            Double.isNaN(d9);
                            Double.isNaN(round2);
                            layoutParams3.width = (int) (d9 - round2);
                            ViewGroup.LayoutParams layoutParams4 = DemoStickerView.this.getLayoutParams();
                            double d10 = layoutParams4.height;
                            Double.isNaN(d10);
                            Double.isNaN(round2);
                            layoutParams4.height = (int) (d10 - round2);
                            DemoStickerView.this.onScaling(false);
                        }
                        double rawY2 = motionEvent.getRawY();
                        double d11 = DemoStickerView.this.centerY;
                        Double.isNaN(rawY2);
                        double d12 = rawY2 - d11;
                        double rawX2 = motionEvent.getRawX();
                        double d13 = DemoStickerView.this.centerX;
                        Double.isNaN(rawX2);
                        double atan22 = (Math.atan2(d12, rawX2 - d13) * 180.0d) / 3.141592653589793d;
                        Log.v(DemoStickerView.TAG, "log angle: " + atan22);
                        DemoStickerView.this.setRotation(((float) atan22) - 70.0f);
                        Log.v(DemoStickerView.TAG, "getRotation(): " + DemoStickerView.this.getRotation());
                        DemoStickerView.this.onRotating();
                        DemoStickerView demoStickerView11 = DemoStickerView.this;
                        demoStickerView11.rotate_orgX = demoStickerView11.rotate_newX;
                        DemoStickerView demoStickerView12 = DemoStickerView.this;
                        demoStickerView12.rotate_orgY = demoStickerView12.rotate_newY;
                        DemoStickerView.this.scale_orgX = motionEvent.getRawX();
                        DemoStickerView.this.scale_orgY = motionEvent.getRawY();
                        DemoStickerView.this.postInvalidate();
                        DemoStickerView.this.requestLayout();
                        return true;
                    }
                } else {
                    return true;
                }
            }
        };
        this.move_orgX = -1.0f;
        this.move_orgY = -1.0f;
        this.rotate_newX = -1.0f;
        this.rotate_newY = -1.0f;
        this.rotate_orgX = -1.0f;
        this.rotate_orgY = -1.0f;
        this.scale_orgHeight = -1.0d;
        this.scale_orgWidth = -1.0d;
        this.scale_orgX = -1.0f;
        this.scale_orgY = -1.0f;
        this.this_orgX = -1.0f;
        this.this_orgY = -1.0f;
        init(context);
        this.onTouchSticker = onTouchSticker;
    }

    public DemoStickerView(Context context, AttributeSet attributeSet, OnTouchSticker onTouchSticker) {
        super(context, attributeSet);
        this.mTouchListener = new View.OnTouchListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.Sticker.DemoStickerView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int i;
                DemoStickerView demoStickerView = null;
                DemoStickerView demoStickerView2 = null;
                DemoStickerView demoStickerView3 = null;
                if (view.getTag().equals("DraggableViewGroup")) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        DemoStickerView.this.setControlItemsHidden(true);
                        DemoStickerView.this.onTouchSticker.onTouchedSticker();
                        Log.v(DemoStickerView.TAG, "sticker view action down");
                        DemoStickerView.this.move_orgX = motionEvent.getRawX();
                        DemoStickerView.this.move_orgY = motionEvent.getRawY();
                        return true;
                    } else if (action == 1) {
                        Log.v(DemoStickerView.TAG, "sticker view action up");
                        DemoStickerView.this.setControlItemsHidden(false);
                        return true;
                    } else if (action != 2) {
                        return true;
                    } else {
                        Log.v(DemoStickerView.TAG, "sticker view action move_right");
                        float rawX = motionEvent.getRawX() - DemoStickerView.this.move_orgX;
                        float rawY = motionEvent.getRawY() - DemoStickerView.this.move_orgY;
                        DemoStickerView demoStickerView4 = DemoStickerView.this;
                        demoStickerView4.setX(demoStickerView4.getX() + rawX);
                        DemoStickerView demoStickerView5 = DemoStickerView.this;
                        demoStickerView5.setY(demoStickerView5.getY() + rawY);
                        DemoStickerView.this.move_orgX = motionEvent.getRawX();
                        DemoStickerView.this.move_orgY = motionEvent.getRawY();
                        return true;
                    }
                } else if (view.getTag().equals("iv_scale")) {
                    int action2 = motionEvent.getAction();
                    if (action2 == 0) {
                        Log.v(DemoStickerView.TAG, "iv_scale action down");
                        DemoStickerView demoStickerView6 = DemoStickerView.this;
                        demoStickerView6.this_orgX = demoStickerView6.getX();
                        DemoStickerView demoStickerView7 = DemoStickerView.this;
                        demoStickerView7.this_orgY = demoStickerView7.getY();
                        DemoStickerView.this.scale_orgX = motionEvent.getRawX();
                        DemoStickerView.this.scale_orgY = motionEvent.getRawY();
                        DemoStickerView.this.scale_orgWidth = demoStickerView.getLayoutParams().width;
                        DemoStickerView.this.scale_orgHeight = demoStickerView2.getLayoutParams().height;
                        DemoStickerView.this.rotate_orgX = motionEvent.getRawX();
                        DemoStickerView.this.rotate_orgY = motionEvent.getRawY();
                        DemoStickerView.this.centerX = demoStickerView3.getX() + ((View) DemoStickerView.this.getParent()).getX() + (DemoStickerView.this.getWidth() / 2.0f);
                        int identifier = DemoStickerView.this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                        double dimensionPixelSize = identifier > 0 ? DemoStickerView.this.getResources().getDimensionPixelSize(identifier) : 0;
                        DemoStickerView demoStickerView8 = DemoStickerView.this;
                        double y = demoStickerView8.getY() + ((View) DemoStickerView.this.getParent()).getY();
                        Double.isNaN(y);
                        Double.isNaN(dimensionPixelSize);
                        double d = y + dimensionPixelSize;
                        double height = DemoStickerView.this.getHeight() / 2.0f;
                        Double.isNaN(height);
                        demoStickerView8.centerY = d + height;
                        return true;
                    } else if (action2 == 1) {
                        Log.v(DemoStickerView.TAG, "iv_scale action up");
                        return true;
                    } else if (action2 != 2) {
                        return true;
                    } else {
                        Log.v(DemoStickerView.TAG, "iv_scale action move_right");
                        DemoStickerView.this.rotate_newX = motionEvent.getRawX();
                        DemoStickerView.this.rotate_newY = motionEvent.getRawY();
                        double atan2 = Math.atan2(motionEvent.getRawY() - DemoStickerView.this.scale_orgY, motionEvent.getRawX() - DemoStickerView.this.scale_orgX);
                        double d2 = DemoStickerView.this.scale_orgY;
                        double d3 = DemoStickerView.this.centerY;
                        Double.isNaN(d2);
                        double d4 = d2 - d3;
                        double d5 = DemoStickerView.this.scale_orgX;
                        double d6 = DemoStickerView.this.centerX;
                        Double.isNaN(d5);
                        double abs = (Math.abs(atan2 - Math.atan2(d4, d5 - d6)) * 180.0d) / 3.141592653589793d;
                        Log.v(DemoStickerView.TAG, "angle_diff: " + abs);
                        DemoStickerView demoStickerView9 = DemoStickerView.this;
                        double length = demoStickerView9.getLength(demoStickerView9.centerX, DemoStickerView.this.centerY, (double) DemoStickerView.this.scale_orgX, (double) DemoStickerView.this.scale_orgY);
                        DemoStickerView demoStickerView10 = DemoStickerView.this;
                        double length2 = demoStickerView10.getLength(demoStickerView10.centerX, DemoStickerView.this.centerY, motionEvent.getRawX(), motionEvent.getRawY());
                        int convertDpToPixel = DemoStickerView.convertDpToPixel(100.0f, DemoStickerView.this.getContext());
                        if (length2 > length && (abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d)) {
                            double round = Math.round(Math.max(Math.abs(motionEvent.getRawX() - DemoStickerView.this.scale_orgX), Math.abs(motionEvent.getRawY() - DemoStickerView.this.scale_orgY)));
                            ViewGroup.LayoutParams layoutParams = DemoStickerView.this.getLayoutParams();
                            double d7 = layoutParams.width;
                            Double.isNaN(d7);
                            Double.isNaN(round);
                            layoutParams.width = (int) (d7 + round);
                            ViewGroup.LayoutParams layoutParams2 = DemoStickerView.this.getLayoutParams();
                            double d8 = layoutParams2.height;
                            Double.isNaN(d8);
                            Double.isNaN(round);
                            layoutParams2.height = (int) (d8 + round);
                            DemoStickerView.this.onScaling(true);
                        } else if (length2 < length && ((abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d) && DemoStickerView.this.getLayoutParams().width > (i = convertDpToPixel / 2) && DemoStickerView.this.getLayoutParams().height > i)) {
                            double round2 = Math.round(Math.max(Math.abs(motionEvent.getRawX() - DemoStickerView.this.scale_orgX), Math.abs(motionEvent.getRawY() - DemoStickerView.this.scale_orgY)));
                            ViewGroup.LayoutParams layoutParams3 = DemoStickerView.this.getLayoutParams();
                            double d9 = layoutParams3.width;
                            Double.isNaN(d9);
                            Double.isNaN(round2);
                            layoutParams3.width = (int) (d9 - round2);
                            ViewGroup.LayoutParams layoutParams4 = DemoStickerView.this.getLayoutParams();
                            double d10 = layoutParams4.height;
                            Double.isNaN(d10);
                            Double.isNaN(round2);
                            layoutParams4.height = (int) (d10 - round2);
                            DemoStickerView.this.onScaling(false);
                        }
                        double rawY2 = motionEvent.getRawY();
                        double d11 = DemoStickerView.this.centerY;
                        Double.isNaN(rawY2);
                        double d12 = rawY2 - d11;
                        double rawX2 = motionEvent.getRawX();
                        double d13 = DemoStickerView.this.centerX;
                        Double.isNaN(rawX2);
                        double atan22 = (Math.atan2(d12, rawX2 - d13) * 180.0d) / 3.141592653589793d;
                        Log.v(DemoStickerView.TAG, "log angle: " + atan22);
                        DemoStickerView.this.setRotation(((float) atan22) - 70.0f);
                        Log.v(DemoStickerView.TAG, "getRotation(): " + DemoStickerView.this.getRotation());
                        DemoStickerView.this.onRotating();
                        DemoStickerView demoStickerView11 = DemoStickerView.this;
                        demoStickerView11.rotate_orgX = demoStickerView11.rotate_newX;
                        DemoStickerView demoStickerView12 = DemoStickerView.this;
                        demoStickerView12.rotate_orgY = demoStickerView12.rotate_newY;
                        DemoStickerView.this.scale_orgX = motionEvent.getRawX();
                        DemoStickerView.this.scale_orgY = motionEvent.getRawY();
                        DemoStickerView.this.postInvalidate();
                        DemoStickerView.this.requestLayout();
                        return true;
                    }
                } else {
                    return true;
                }
            }
        };
        this.move_orgX = -1.0f;
        this.move_orgY = -1.0f;
        this.rotate_newX = -1.0f;
        this.rotate_newY = -1.0f;
        this.rotate_orgX = -1.0f;
        this.rotate_orgY = -1.0f;
        this.scale_orgHeight = -1.0d;
        this.scale_orgWidth = -1.0d;
        this.scale_orgX = -1.0f;
        this.scale_orgY = -1.0f;
        this.this_orgX = -1.0f;
        this.this_orgY = -1.0f;
        init(context);
        this.onTouchSticker = onTouchSticker;
    }

    public DemoStickerView(Context context, AttributeSet attributeSet, int i, OnTouchSticker onTouchSticker) {
        super(context, attributeSet, i);
        this.mTouchListener = new View.OnTouchListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.Sticker.DemoStickerView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int i2;
                DemoStickerView demoStickerView = null;
                DemoStickerView demoStickerView2 = null;
                DemoStickerView demoStickerView3 = null;
                if (view.getTag().equals("DraggableViewGroup")) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        DemoStickerView.this.setControlItemsHidden(true);
                        DemoStickerView.this.onTouchSticker.onTouchedSticker();
                        Log.v(DemoStickerView.TAG, "sticker view action down");
                        DemoStickerView.this.move_orgX = motionEvent.getRawX();
                        DemoStickerView.this.move_orgY = motionEvent.getRawY();
                        return true;
                    } else if (action == 1) {
                        Log.v(DemoStickerView.TAG, "sticker view action up");
                        DemoStickerView.this.setControlItemsHidden(false);
                        return true;
                    } else if (action != 2) {
                        return true;
                    } else {
                        Log.v(DemoStickerView.TAG, "sticker view action move_right");
                        float rawX = motionEvent.getRawX() - DemoStickerView.this.move_orgX;
                        float rawY = motionEvent.getRawY() - DemoStickerView.this.move_orgY;
                        DemoStickerView demoStickerView4 = DemoStickerView.this;
                        demoStickerView4.setX(demoStickerView4.getX() + rawX);
                        DemoStickerView demoStickerView5 = DemoStickerView.this;
                        demoStickerView5.setY(demoStickerView5.getY() + rawY);
                        DemoStickerView.this.move_orgX = motionEvent.getRawX();
                        DemoStickerView.this.move_orgY = motionEvent.getRawY();
                        return true;
                    }
                } else if (view.getTag().equals("iv_scale")) {
                    int action2 = motionEvent.getAction();
                    if (action2 == 0) {
                        Log.v(DemoStickerView.TAG, "iv_scale action down");
                        DemoStickerView demoStickerView6 = DemoStickerView.this;
                        demoStickerView6.this_orgX = demoStickerView6.getX();
                        DemoStickerView demoStickerView7 = DemoStickerView.this;
                        demoStickerView7.this_orgY = demoStickerView7.getY();
                        DemoStickerView.this.scale_orgX = motionEvent.getRawX();
                        DemoStickerView.this.scale_orgY = motionEvent.getRawY();
                        DemoStickerView.this.scale_orgWidth = demoStickerView.getLayoutParams().width;
                        DemoStickerView.this.scale_orgHeight = demoStickerView2.getLayoutParams().height;
                        DemoStickerView.this.rotate_orgX = motionEvent.getRawX();
                        DemoStickerView.this.rotate_orgY = motionEvent.getRawY();
                        DemoStickerView.this.centerX = demoStickerView3.getX() + ((View) DemoStickerView.this.getParent()).getX() + (DemoStickerView.this.getWidth() / 2.0f);
                        int identifier = DemoStickerView.this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                        double dimensionPixelSize = identifier > 0 ? DemoStickerView.this.getResources().getDimensionPixelSize(identifier) : 0;
                        DemoStickerView demoStickerView8 = DemoStickerView.this;
                        double y = demoStickerView8.getY() + ((View) DemoStickerView.this.getParent()).getY();
                        Double.isNaN(y);
                        Double.isNaN(dimensionPixelSize);
                        double d = y + dimensionPixelSize;
                        double height = DemoStickerView.this.getHeight() / 2.0f;
                        Double.isNaN(height);
                        demoStickerView8.centerY = d + height;
                        return true;
                    } else if (action2 == 1) {
                        Log.v(DemoStickerView.TAG, "iv_scale action up");
                        return true;
                    } else if (action2 != 2) {
                        return true;
                    } else {
                        Log.v(DemoStickerView.TAG, "iv_scale action move_right");
                        DemoStickerView.this.rotate_newX = motionEvent.getRawX();
                        DemoStickerView.this.rotate_newY = motionEvent.getRawY();
                        double atan2 = Math.atan2(motionEvent.getRawY() - DemoStickerView.this.scale_orgY, motionEvent.getRawX() - DemoStickerView.this.scale_orgX);
                        double d2 = DemoStickerView.this.scale_orgY;
                        double d3 = DemoStickerView.this.centerY;
                        Double.isNaN(d2);
                        double d4 = d2 - d3;
                        double d5 = DemoStickerView.this.scale_orgX;
                        double d6 = DemoStickerView.this.centerX;
                        Double.isNaN(d5);
                        double abs = (Math.abs(atan2 - Math.atan2(d4, d5 - d6)) * 180.0d) / 3.141592653589793d;
                        Log.v(DemoStickerView.TAG, "angle_diff: " + abs);
                        DemoStickerView demoStickerView9 = DemoStickerView.this;
                        double length = demoStickerView9.getLength(demoStickerView9.centerX, DemoStickerView.this.centerY, (double) DemoStickerView.this.scale_orgX, (double) DemoStickerView.this.scale_orgY);
                        DemoStickerView demoStickerView10 = DemoStickerView.this;
                        double length2 = demoStickerView10.getLength(demoStickerView10.centerX, DemoStickerView.this.centerY, motionEvent.getRawX(), motionEvent.getRawY());
                        int convertDpToPixel = DemoStickerView.convertDpToPixel(100.0f, DemoStickerView.this.getContext());
                        if (length2 > length && (abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d)) {
                            double round = Math.round(Math.max(Math.abs(motionEvent.getRawX() - DemoStickerView.this.scale_orgX), Math.abs(motionEvent.getRawY() - DemoStickerView.this.scale_orgY)));
                            ViewGroup.LayoutParams layoutParams = DemoStickerView.this.getLayoutParams();
                            double d7 = layoutParams.width;
                            Double.isNaN(d7);
                            Double.isNaN(round);
                            layoutParams.width = (int) (d7 + round);
                            ViewGroup.LayoutParams layoutParams2 = DemoStickerView.this.getLayoutParams();
                            double d8 = layoutParams2.height;
                            Double.isNaN(d8);
                            Double.isNaN(round);
                            layoutParams2.height = (int) (d8 + round);
                            DemoStickerView.this.onScaling(true);
                        } else if (length2 < length && ((abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d) && DemoStickerView.this.getLayoutParams().width > (i2 = convertDpToPixel / 2) && DemoStickerView.this.getLayoutParams().height > i2)) {
                            double round2 = Math.round(Math.max(Math.abs(motionEvent.getRawX() - DemoStickerView.this.scale_orgX), Math.abs(motionEvent.getRawY() - DemoStickerView.this.scale_orgY)));
                            ViewGroup.LayoutParams layoutParams3 = DemoStickerView.this.getLayoutParams();
                            double d9 = layoutParams3.width;
                            Double.isNaN(d9);
                            Double.isNaN(round2);
                            layoutParams3.width = (int) (d9 - round2);
                            ViewGroup.LayoutParams layoutParams4 = DemoStickerView.this.getLayoutParams();
                            double d10 = layoutParams4.height;
                            Double.isNaN(d10);
                            Double.isNaN(round2);
                            layoutParams4.height = (int) (d10 - round2);
                            DemoStickerView.this.onScaling(false);
                        }
                        double rawY2 = motionEvent.getRawY();
                        double d11 = DemoStickerView.this.centerY;
                        Double.isNaN(rawY2);
                        double d12 = rawY2 - d11;
                        double rawX2 = motionEvent.getRawX();
                        double d13 = DemoStickerView.this.centerX;
                        Double.isNaN(rawX2);
                        double atan22 = (Math.atan2(d12, rawX2 - d13) * 180.0d) / 3.141592653589793d;
                        Log.v(DemoStickerView.TAG, "log angle: " + atan22);
                        DemoStickerView.this.setRotation(((float) atan22) - 70.0f);
                        Log.v(DemoStickerView.TAG, "getRotation(): " + DemoStickerView.this.getRotation());
                        DemoStickerView.this.onRotating();
                        DemoStickerView demoStickerView11 = DemoStickerView.this;
                        demoStickerView11.rotate_orgX = demoStickerView11.rotate_newX;
                        DemoStickerView demoStickerView12 = DemoStickerView.this;
                        demoStickerView12.rotate_orgY = demoStickerView12.rotate_newY;
                        DemoStickerView.this.scale_orgX = motionEvent.getRawX();
                        DemoStickerView.this.scale_orgY = motionEvent.getRawY();
                        DemoStickerView.this.postInvalidate();
                        DemoStickerView.this.requestLayout();
                        return true;
                    }
                } else {
                    return true;
                }
            }
        };
        this.move_orgX = -1.0f;
        this.move_orgY = -1.0f;
        this.rotate_newX = -1.0f;
        this.rotate_newY = -1.0f;
        this.rotate_orgX = -1.0f;
        this.rotate_orgY = -1.0f;
        this.scale_orgHeight = -1.0d;
        this.scale_orgWidth = -1.0d;
        this.scale_orgX = -1.0f;
        this.scale_orgY = -1.0f;
        this.this_orgX = -1.0f;
        this.this_orgY = -1.0f;
        init(context);
        this.onTouchSticker = onTouchSticker;
    }

    public static int convertDpToPixel(float f, Context context) {
        return (int) (f * (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    private void init(Context context) {
        this.iv_border = new BorderView(context);
        this.iv_scale = new ImageView(context);
        this.iv_delete = new ImageView(context);
        this.iv_flip = new ImageView(context);
        this.iv_scale.setImageResource(R.drawable.icon_resize);
        this.iv_delete.setImageResource(R.drawable.icon_delete);
        this.iv_flip.setImageResource(R.drawable.icon_flip);
        setTag("DraggableViewGroup");
        this.iv_border.setTag("iv_border");
        this.iv_scale.setTag("iv_scale");
        this.iv_delete.setTag("iv_delete");
        this.iv_flip.setTag("iv_flip");
        int convertDpToPixel = convertDpToPixel(30.0f, getContext()) / 2;
        int convertDpToPixel2 = convertDpToPixel(100.0f, getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(convertDpToPixel2, convertDpToPixel2);
        layoutParams.gravity = 17;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.setMargins(40, 40, 40, 40);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(convertDpToPixel(30.0f, getContext()), convertDpToPixel(30.0f, getContext()));
        layoutParams4.gravity = 85;
        FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(convertDpToPixel(30.0f, getContext()), convertDpToPixel(30.0f, getContext()));
        layoutParams5.gravity = 53;
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(convertDpToPixel(30.0f, getContext()), convertDpToPixel(30.0f, getContext()));
        layoutParams6.gravity = 51;
        setLayoutParams(layoutParams);
        addView(getMainView(), layoutParams2);
        addView(this.iv_border, layoutParams3);
        addView(this.iv_scale, layoutParams4);
        addView(this.iv_delete, layoutParams5);
        addView(this.iv_flip, layoutParams6);
        setOnTouchListener(this.mTouchListener);
        setOnClickListener(this);
        this.iv_scale.setOnTouchListener(this.mTouchListener);
        this.iv_delete.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.Sticker.DemoStickerView.2
            @Override 
            public void onClick(View view) {
                if (DemoStickerView.this.getParent() != null) {
                    ((ViewGroup) DemoStickerView.this.getParent()).removeView(DemoStickerView.this);
                }
            }
        });
        this.iv_flip.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.Sticker.DemoStickerView.3
            @Override 
            public void onClick(View view) {
                Log.v(DemoStickerView.TAG, "flip the view");
                View mainView = DemoStickerView.this.getMainView();
                mainView.setRotationY(mainView.getRotationY() == -180.0f ? 0.0f : -180.0f);
                mainView.invalidate();
                DemoStickerView.this.requestLayout();
            }
        });
    }

    public boolean isFlip() {
        return getMainView().getRotationY() == -180.0f;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public double getLength(double d, double d2, double d3, double d4) {
        return Math.sqrt(Math.pow(d4 - d2, 2.0d) + Math.pow(d3 - d, 2.0d));
    }

    private float[] getRelativePos(float f, float f2) {
        Log.v("ken", "getRelativePos getX:" + ((View) getParent()).getX());
        Log.v("ken", "getRelativePos getY:" + ((View) getParent()).getY());
        float[] fArr = {f - ((View) getParent()).getX(), f2 - ((View) getParent()).getY()};
        Log.v(TAG, "getRelativePos absY:" + f2);
        Log.v(TAG, "getRelativePos relativeY:" + fArr[1]);
        return fArr;
    }

    public void setControlItemsHidden(boolean z) {
        if (z) {
            this.iv_border.setVisibility(4);
            this.iv_scale.setVisibility(4);
            this.iv_delete.setVisibility(4);
            this.iv_flip.setVisibility(4);
            return;
        }
        this.iv_border.setVisibility(0);
        this.iv_scale.setVisibility(0);
        this.iv_delete.setVisibility(0);
        this.iv_flip.setVisibility(0);
    }

    public View getImageViewFlip() {
        return this.iv_flip;
    }

    @Override 
    public void onClick(View view) {
        setControlItemsHidden(false);
    }

    public void setControlsVisibility(boolean z) {
        if (!z) {
            this.iv_border.setVisibility(8);
            this.iv_delete.setVisibility(8);
            this.iv_flip.setVisibility(8);
            this.iv_scale.setVisibility(8);
            return;
        }
        this.iv_border.setVisibility(0);
        this.iv_delete.setVisibility(0);
        this.iv_flip.setVisibility(0);
        this.iv_scale.setVisibility(0);
    }

    

    public class BorderView extends View {
        public BorderView(Context context) {
            super(context);
        }

        public BorderView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public BorderView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            Log.v(DemoStickerView.TAG, "params.leftMargin: " + layoutParams.leftMargin);
            Rect rect = new Rect();
            rect.left = getLeft() - layoutParams.leftMargin;
            rect.top = getTop() - layoutParams.topMargin;
            rect.right = getRight() - layoutParams.rightMargin;
            rect.bottom = getBottom() - layoutParams.bottomMargin;
            Paint paint = new Paint();
            paint.setStrokeWidth(8.0f);
            paint.setColor(getResources().getColor(R.color.white));
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(rect, paint);
        }
    }
}
