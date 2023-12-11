package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


public class CtTextData extends BaseData implements Serializable, Parcelable {
    public static final int defBgAlpha = 255;
    public static final String defaultMessage = "Preview Text";
    private static final long serialVersionUID = 3789254181897332363L;
    String ID;
    int backgroundAlpha;
    int backgroundColor;
    public CtMyMatrix canvasMatrix;
    String fontPath;
    public CtMyMatrix imageSaveMatrix;
    boolean isSnapMode;
    public boolean isTypeFaceSet;
    public String message;
    private CtShadowData shadowData;
    private int textBackgroundColor;
    public CtMyPaint textPaint;
    public float textSize;
    public float xPos;
    public float yPos;
    public float yPosSnap;
    public static final Parcelable.Creator<CtTextData> CREATOR = new TextDataCreator();
    public static int bgColorSentinel = ViewCompat.MEASURED_SIZE_MASK;
    static final AtomicInteger counter = new AtomicInteger();
    public static final int defBgColor = bgColorSentinel;

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }


    static class TextDataCreator implements Parcelable.Creator<CtTextData> {
        TextDataCreator() {
        }

      
        @Override 
        public CtTextData createFromParcel(Parcel parcel) {
            return new CtTextData(parcel);
        }

      
        @Override 
        public CtTextData[] newArray(int i) {
            return new CtTextData[i];
        }
    }

    public static String nextIdValue() {
        return UUID.randomUUID().toString();
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public int setTextColor(int i) {
        int color = getColor(this.textPaint.getAlpha(), i);
        this.textPaint.setColor(color);
        return color;
    }

    public int getBackgroundColorValue() {
        return this.backgroundColor;
    }

    public int getBackgroundColorFinal() {
        int i = this.backgroundColor;
        if (i == bgColorSentinel) {
            return 0;
        }
        return getColor(this.backgroundAlpha, i);
    }

    int getColor(int i, int i2) {
        return Color.argb(i, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    public void setBackgroundAlpha(int i) {
        this.backgroundAlpha = i;
    }

    public int getBackgroundAlpha() {
        return this.backgroundAlpha;
    }

    public boolean getSnapMode() {
        return this.isSnapMode;
    }

    public void setSnapMode(boolean z) {
        this.isSnapMode = z;
        float textSize = this.textPaint.getTextSize();
        if (this.isSnapMode) {
            this.textPaint.setTextSize(textSize * 0.8f);
        } else {
            this.textPaint.setTextSize(textSize * 1.25f);
        }
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData
    public CtMyMatrix getCanvasMatrix() {
        return this.canvasMatrix;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData
    public CtMyMatrix getImageSaveMatrix() {
        return this.imageSaveMatrix;
    }

    public String getId() {
        return this.ID;
    }

    public void setTextFont(String str, Context context) {
        this.fontPath = str;
        if (str != null) {
            Typeface typeFace = CtFontCache.getTypeFace(context, str);
            if (typeFace != null) {
                this.textPaint.setTypeface(typeFace);
            }
            this.isTypeFaceSet = true;
        }
    }

    public String getFontPath() {
        return this.fontPath;
    }

    public CtTextData(float f) {
        this.message = defaultMessage;
        this.isTypeFaceSet = false;
        this.yPosSnap = 0.0f;
        this.isSnapMode = false;
        int i = defBgColor;
        this.backgroundColor = i;
        this.backgroundAlpha = 255;
        this.canvasMatrix = new CtMyMatrix();
        CtMyPaint cMMyPaint = new CtMyPaint();
        this.textPaint = cMMyPaint;
        cMMyPaint.setAntiAlias(true);
        this.textPaint.setColor(-1);
        this.textPaint.setTextSize(f);
        this.fontPath = null;
        this.ID = nextIdValue();
        this.isSnapMode = false;
        this.yPosSnap = 0.0f;
        this.backgroundColor = i;
        this.backgroundAlpha = 255;
        this.shadowData = new CtShadowData(0.0f, 0.0f, 0.0f, -16777216);
    }

    public CtTextData() {
        this.message = defaultMessage;
        this.isTypeFaceSet = false;
        this.yPosSnap = 0.0f;
        this.isSnapMode = false;
        int i = defBgColor;
        this.backgroundColor = i;
        this.backgroundAlpha = 255;
        this.canvasMatrix = new CtMyMatrix();
        CtMyPaint cMMyPaint = new CtMyPaint();
        this.textPaint = cMMyPaint;
        cMMyPaint.setAntiAlias(true);
        this.textPaint.setColor(-1);
        this.textSize = 60.0f;
        this.textPaint.setTextSize(60.0f);
        this.fontPath = null;
        this.ID = nextIdValue();
        this.yPosSnap = 0.0f;
        this.isSnapMode = false;
        this.backgroundColor = i;
        this.backgroundAlpha = 255;
        this.shadowData = new CtShadowData(0.0f, 0.0f, 0.0f, -16777216);
    }

    public CtTextData(CtTextData cMTextData) {
        this.message = defaultMessage;
        this.isTypeFaceSet = false;
        this.yPosSnap = 0.0f;
        this.isSnapMode = false;
        this.backgroundColor = defBgColor;
        this.backgroundAlpha = 255;
        this.shadowData = new CtShadowData(cMTextData.getShadowData());
        this.textBackgroundColor = cMTextData.getTextBackgroundColor();
        set(cMTextData);
    }

    public void set(CtTextData cMTextData) {
        this.canvasMatrix = new CtMyMatrix(cMTextData.canvasMatrix);
        this.textPaint = new CtMyPaint(cMTextData.textPaint);
        if (cMTextData.imageSaveMatrix != null) {
            this.imageSaveMatrix = new CtMyMatrix(cMTextData.imageSaveMatrix);
        }
        this.textPaint.setAntiAlias(true);
        this.message = new String(cMTextData.message);
        this.textSize = cMTextData.textSize;
        this.xPos = cMTextData.xPos;
        this.yPos = cMTextData.yPos;
        String str = cMTextData.fontPath;
        if (str != null) {
            this.fontPath = str;
        }
        String str2 = cMTextData.ID;
        this.ID = str2;
        if (str2 == null) {
            this.ID = nextIdValue();
        }
        this.yPosSnap = cMTextData.yPosSnap;
        this.isSnapMode = cMTextData.isSnapMode;
        this.backgroundColor = cMTextData.backgroundColor;
        this.backgroundAlpha = cMTextData.backgroundAlpha;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData
    public void setImageSaveMatrix(Matrix matrix) {
        if (matrix != null) {
            CtMyMatrix cMMyMatrix = new CtMyMatrix(matrix);
            matrix.invert(cMMyMatrix);
            CtMyMatrix cMMyMatrix2 = new CtMyMatrix(matrix);
            cMMyMatrix2.set(this.canvasMatrix);
            cMMyMatrix.preConcat(cMMyMatrix2);
            this.imageSaveMatrix = cMMyMatrix;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeFloat(this.xPos);
        objectOutputStream.writeFloat(this.yPos);
        objectOutputStream.writeFloat(this.textSize);
        objectOutputStream.writeObject(this.textPaint);
        objectOutputStream.writeObject(this.message);
        objectOutputStream.writeObject(this.canvasMatrix);
        objectOutputStream.writeObject(this.imageSaveMatrix);
        objectOutputStream.writeObject(this.fontPath);
        objectOutputStream.writeObject(this.ID);
        objectOutputStream.writeFloat(this.yPosSnap);
        objectOutputStream.writeBoolean(this.isSnapMode);
        objectOutputStream.writeInt(this.backgroundColor);
        objectOutputStream.writeInt(this.backgroundAlpha);
        objectOutputStream.writeObject(this.shadowData);
        objectOutputStream.writeInt(this.textBackgroundColor);
    }

    private void readObject(ObjectInputStream objectInputStream) throws Exception, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.xPos = objectInputStream.readFloat();
        this.yPos = objectInputStream.readFloat();
        this.textSize = objectInputStream.readFloat();
        this.textPaint = (CtMyPaint) objectInputStream.readObject();
        this.message = (String) objectInputStream.readObject();
        this.canvasMatrix = (CtMyMatrix) objectInputStream.readObject();
        this.imageSaveMatrix = (CtMyMatrix) objectInputStream.readObject();
        try {
            this.fontPath = (String) objectInputStream.readObject();
        } catch (Exception unused) {
            this.fontPath = null;
        }
        try {
            this.ID = (String) objectInputStream.readObject();
        } catch (Exception unused2) {
            this.ID = nextIdValue();
        }
        try {
            this.yPosSnap = objectInputStream.readFloat();
        } catch (Exception unused3) {
            this.yPosSnap = 0.0f;
        }
        try {
            this.isSnapMode = objectInputStream.readBoolean();
        } catch (Exception unused4) {
            this.isSnapMode = false;
        }
        try {
            this.backgroundColor = objectInputStream.readInt();
        } catch (Exception unused5) {
            this.backgroundColor = defBgColor;
        }
        try {
            this.backgroundAlpha = objectInputStream.readInt();
        } catch (Exception unused6) {
            this.backgroundAlpha = 255;
        }
        this.textPaint.setAntiAlias(true);
        this.isTypeFaceSet = false;
        this.shadowData = (CtShadowData) objectInputStream.readObject();
        this.textBackgroundColor = objectInputStream.readInt();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.xPos);
        parcel.writeFloat(this.yPos);
        parcel.writeFloat(this.textSize);
        parcel.writeParcelable(this.textPaint, i);
        parcel.writeString(this.message);
        parcel.writeParcelable(this.canvasMatrix, i);
        parcel.writeParcelable(this.imageSaveMatrix, i);
        parcel.writeString(this.fontPath);
        parcel.writeString(this.ID);
        parcel.writeFloat(this.yPosSnap);
        parcel.writeByte(this.isSnapMode ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.backgroundColor);
        parcel.writeInt(this.backgroundAlpha);
    }

    public CtTextData(Parcel parcel) {
        this.message = defaultMessage;
        this.isTypeFaceSet = false;
        this.yPosSnap = 0.0f;
        this.isSnapMode = false;
        this.backgroundColor = defBgColor;
        this.backgroundAlpha = 255;
        this.xPos = parcel.readFloat();
        this.yPos = parcel.readFloat();
        this.textSize = parcel.readFloat();
        this.textPaint = (CtMyPaint) parcel.readParcelable(CtMyPaint.class.getClassLoader());
        this.message = parcel.readString();
        this.canvasMatrix = (CtMyMatrix) parcel.readParcelable(CtMyMatrix.class.getClassLoader());
        this.imageSaveMatrix = (CtMyMatrix) parcel.readParcelable(CtMyMatrix.class.getClassLoader());
        try {
            this.fontPath = parcel.readString();
        } catch (Exception unused) {
            this.fontPath = null;
        }
        try {
            this.ID = parcel.readString();
        } catch (Exception unused2) {
            this.ID = nextIdValue();
        }
        try {
            this.yPosSnap = parcel.readFloat();
        } catch (Exception unused3) {
            this.yPosSnap = 0.0f;
        }
        try {
            this.isSnapMode = parcel.readByte() != 0;
        } catch (Exception unused4) {
            this.isSnapMode = false;
        }
        try {
            this.backgroundColor = parcel.readInt();
        } catch (Exception unused5) {
            this.backgroundColor = defBgColor;
        }
        try {
            this.backgroundAlpha = parcel.readInt();
        } catch (Exception unused6) {
            this.backgroundAlpha = 255;
        }
        this.textPaint.setAntiAlias(true);
        this.isTypeFaceSet = false;
        this.textBackgroundColor = parcel.readInt();
    }

    public static CtTextData[] toTextData(Parcelable[] parcelableArr) {
        if (parcelableArr == null) {
            return null;
        }
        CtTextData[] cMTextDataArr = new CtTextData[parcelableArr.length];
        System.arraycopy(parcelableArr, 0, cMTextDataArr, 0, parcelableArr.length);
        return cMTextDataArr;
    }

    public int getTextBackgroundColor() {
        return this.textBackgroundColor;
    }

    public CtShadowData getShadowData() {
        return this.shadowData;
    }

    public void setShadowData(CtShadowData cMShadowData) {
        this.shadowData = cMShadowData;
    }

    public void setTextBackgroundColor(int i) {
        this.textBackgroundColor = i;
    }
}
