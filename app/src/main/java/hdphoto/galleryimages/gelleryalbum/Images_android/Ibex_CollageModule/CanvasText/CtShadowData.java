package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import java.io.Serializable;


public class CtShadowData implements Serializable {
    int color;
    float dx;
    float dy;
    float radius;

    public float getRadius() {
        return this.radius;
    }

    public float getDx() {
        return this.dx;
    }

    public float getDy() {
        return this.dy;
    }

    public int getColor() {
        return this.color;
    }

    public boolean isShadowEnabled() {
        return (this.radius == 0.0f || this.dx == 0.0f || this.dy == 0.0f || this.color == 0) ? false : true;
    }

    public CtShadowData(float f, float f2, float f3, int i) {
        this.radius = f;
        this.dx = f2;
        this.dy = f3;
        this.color = i;
    }

    public CtShadowData(CtShadowData cMShadowData) {
        this.radius = cMShadowData.radius;
        this.dx = cMShadowData.dx;
        this.dy = cMShadowData.dy;
        this.color = cMShadowData.color;
    }
}
