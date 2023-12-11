package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import android.view.MotionEvent;


public class Anadol_RotationGestureDetector {
    float fX;
    float fY;
    float mAngle;
    OnRotationGestureListener mListener;
    int ptrID1 = -1;
    int ptrID2 = -1;
    float sX;
    float sY;


    public interface OnRotationGestureListener {
        void OnRotation(Anadol_RotationGestureDetector cMRotationGestureDetector);
    }

    public float getAngle() {
        return this.mAngle;
    }

    public Anadol_RotationGestureDetector(OnRotationGestureListener onRotationGestureListener) {
        this.mListener = onRotationGestureListener;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.ptrID1 = motionEvent.getPointerId(motionEvent.getActionIndex());
        } else if (actionMasked == 1) {
            this.ptrID1 = -1;
        } else if (actionMasked == 2) {
            int i = this.ptrID1;
            if (i != -1 && this.ptrID2 != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(i);
                int findPointerIndex2 = motionEvent.findPointerIndex(this.ptrID2);
                int pointerCount = motionEvent.getPointerCount();
                if (findPointerIndex >= 0 && findPointerIndex < pointerCount && findPointerIndex2 >= 0 && findPointerIndex2 < pointerCount) {
                    this.mAngle = angleBetweenLines(this.fX, this.fY, this.sX, this.sY, motionEvent.getX(motionEvent.findPointerIndex(this.ptrID2)), motionEvent.getY(motionEvent.findPointerIndex(this.ptrID2)), motionEvent.getX(motionEvent.findPointerIndex(this.ptrID1)), motionEvent.getY(motionEvent.findPointerIndex(this.ptrID1)));
                    OnRotationGestureListener onRotationGestureListener = this.mListener;
                    if (onRotationGestureListener != null) {
                        onRotationGestureListener.OnRotation(this);
                    }
                }
            }
        } else if (actionMasked == 3) {
            this.ptrID1 = -1;
            this.ptrID2 = -1;
        } else if (actionMasked == 5) {
            this.ptrID2 = motionEvent.getPointerId(motionEvent.getActionIndex());
            int findPointerIndex3 = motionEvent.findPointerIndex(this.ptrID1);
            int findPointerIndex4 = motionEvent.findPointerIndex(this.ptrID2);
            int pointerCount2 = motionEvent.getPointerCount();
            if (findPointerIndex3 >= 0 && findPointerIndex3 < pointerCount2 && findPointerIndex4 >= 0 && findPointerIndex4 < pointerCount2) {
                this.sX = motionEvent.getX(findPointerIndex3);
                this.sY = motionEvent.getY(findPointerIndex3);
                this.fX = motionEvent.getX(findPointerIndex4);
                this.fY = motionEvent.getY(findPointerIndex4);
            }
            this.ptrID2 = -1;
        }
        return true;
    }

    private float angleBetweenLines(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float degrees = ((float) Math.toDegrees(((float) Math.atan2(f2 - f4, f - f3)) - ((float) Math.atan2(f6 - f8, f5 - f7)))) % 360.0f;
        if (degrees < -180.0f) {
            degrees += 360.0f;
        }
        return degrees > 180.0f ? degrees - 360.0f : degrees;
    }
}
