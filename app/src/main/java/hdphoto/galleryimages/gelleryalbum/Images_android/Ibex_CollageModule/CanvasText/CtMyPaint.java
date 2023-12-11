package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class CtMyPaint extends Paint implements Serializable, Parcelable {
    public static final Parcelable.Creator<CtMyPaint> CREATOR = new MyPaintCreator();
    private static final long serialVersionUID = -2455397208601380474L;
    int alignment;
    int alpha;
    int color;
    float textSize;

    @Override 
    public int describeContents() {
        return 0;
    }

    public CtMyPaint() {
        super.setAntiAlias(true);
    }

    public CtMyPaint(CtMyPaint cMMyPaint) {
        super(cMMyPaint);
        this.color = cMMyPaint.color;
        this.textSize = cMMyPaint.textSize;
        int alignmentValue = alignmentValue(cMMyPaint);
        this.alignment = alignmentValue;
        setTextAlign(AlignEnumValue(alignmentValue));
        super.setAntiAlias(true);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        this.color = super.getColor();
        this.textSize = super.getTextSize();
        this.alignment = alignmentValue(this);
        objectOutputStream.writeInt(this.color);
        objectOutputStream.writeFloat(this.textSize);
        objectOutputStream.writeInt(this.alignment);
        objectOutputStream.writeInt(super.getAlpha());
    }

    private void readObject(ObjectInputStream objectInputStream) throws Exception, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.color = objectInputStream.readInt();
        this.textSize = objectInputStream.readFloat();
        super.setColor(this.color);
        super.setTextSize(this.textSize);
        super.setAntiAlias(true);
        try {
            int readInt = objectInputStream.readInt();
            this.alignment = readInt;
            super.setTextAlign(AlignEnumValue(readInt));
        } catch (Exception unused) {
        }
        try {
            int readInt2 = objectInputStream.readInt();
            this.alignment = readInt2;
            super.setTextAlign(AlignEnumValue(readInt2));
        } catch (Exception unused2) {
        }
    }


    static class MyPaintCreator implements Parcelable.Creator<CtMyPaint> {
        MyPaintCreator() {
        }

      
        @Override 
        public CtMyPaint createFromParcel(Parcel parcel) {
            return new CtMyPaint(parcel);
        }

      
        @Override 
        public CtMyPaint[] newArray(int i) {
            return new CtMyPaint[i];
        }
    }

    public static int alignmentValue(Paint paint) {
        Paint.Align textAlign = paint.getTextAlign();
        if (textAlign == Paint.Align.RIGHT) {
            return 2;
        }
        if (textAlign == Paint.Align.CENTER) {
            return 1;
        }
        Paint.Align align = Paint.Align.LEFT;
        return 0;
    }

    public static Paint.Align AlignEnumValue(int i) {
        Paint.Align align = Paint.Align.LEFT;
        if (i == 2) {
            return Paint.Align.RIGHT;
        }
        if (i == 1) {
            return Paint.Align.CENTER;
        }
        return i == 0 ? Paint.Align.LEFT : align;
    }

    @Override 
    public void writeToParcel(Parcel parcel, int i) {
        this.color = super.getColor();
        this.textSize = super.getTextSize();
        this.alignment = alignmentValue(this);
        this.alpha = getAlpha();
        parcel.writeInt(this.color);
        parcel.writeFloat(this.textSize);
        parcel.writeInt(this.alignment);
        parcel.writeInt(this.alpha);
    }

    public CtMyPaint(Parcel parcel) {
        this.color = parcel.readInt();
        this.textSize = parcel.readFloat();
        super.setColor(this.color);
        super.setTextSize(this.textSize);
        super.setAntiAlias(true);
        try {
            int readInt = parcel.readInt();
            this.alignment = readInt;
            super.setTextAlign(AlignEnumValue(readInt));
        } catch (Exception unused) {
        }
        try {
            int readInt2 = parcel.readInt();
            this.alpha = readInt2;
            super.setAlpha(readInt2);
        } catch (Exception unused2) {
        }
    }
}
