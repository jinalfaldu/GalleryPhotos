package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.BaseData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtMyMatrix;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Ledl_StickerData extends BaseData implements Serializable, Parcelable {
    public static Parcelable.Creator<Ledl_StickerData> CREATOR = new StickerDataCreator();
    static String TAG = "CMStickerData";
    static long serialVersionUID = 3789254141896332763L;
    CtMyMatrix canvasMatrix;
    CtMyMatrix imageSaveMatrix;
    String path;
    int resId;
    public float xPos;
    public float yPos;

    @Override
    public int describeContents() {
        return 0;
    }


    static class StickerDataCreator implements Parcelable.Creator<Ledl_StickerData> {
        StickerDataCreator() {
        }

      
        @Override 
        public Ledl_StickerData createFromParcel(Parcel parcel) {
            return new Ledl_StickerData(parcel);
        }

      
        @Override 
        public Ledl_StickerData[] newArray(int i) {
            return new Ledl_StickerData[i];
        }
    }

    public int getResId() {
        return this.resId;
    }

    public String getPath() {
        return this.path;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData
    public CtMyMatrix getCanvasMatrix() {
        return this.canvasMatrix;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData
    public CtMyMatrix getImageSaveMatrix() {
        return this.imageSaveMatrix;
    }

    public Ledl_StickerData(int i) {
        CtMyMatrix cMMyMatrix = new CtMyMatrix();
        this.canvasMatrix = cMMyMatrix;
        cMMyMatrix.reset();
        this.resId = i;
    }

    public Ledl_StickerData(String str) {
        CtMyMatrix cMMyMatrix = new CtMyMatrix();
        this.canvasMatrix = cMMyMatrix;
        cMMyMatrix.reset();
        this.path = str;
    }

    public void set(Ledl_StickerData cMStickerData) {
        if (cMStickerData.canvasMatrix != null) {
            this.canvasMatrix = new CtMyMatrix(cMStickerData.canvasMatrix);
        }
        if (cMStickerData.imageSaveMatrix != null) {
            this.imageSaveMatrix = new CtMyMatrix(cMStickerData.imageSaveMatrix);
        }
        this.xPos = cMStickerData.xPos;
        this.yPos = cMStickerData.yPos;
        this.resId = cMStickerData.resId;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData
    public void setImageSaveMatrix(Matrix matrix) {
        if (matrix != null) {
            CtMyMatrix cMMyMatrix = new CtMyMatrix();
            matrix.invert(cMMyMatrix);
            CtMyMatrix cMMyMatrix2 = new CtMyMatrix();
            cMMyMatrix2.set(this.canvasMatrix);
            cMMyMatrix.preConcat(cMMyMatrix2);
            this.imageSaveMatrix = cMMyMatrix;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeFloat(this.xPos);
        objectOutputStream.writeFloat(this.yPos);
        objectOutputStream.writeObject(this.canvasMatrix);
        objectOutputStream.writeObject(this.imageSaveMatrix);
        objectOutputStream.writeInt(this.resId);
        objectOutputStream.writeObject(this.path);
    }

    private void readObject(ObjectInputStream objectInputStream) throws Exception, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.xPos = objectInputStream.readFloat();
        this.yPos = objectInputStream.readFloat();
        this.canvasMatrix = (CtMyMatrix) objectInputStream.readObject();
        this.imageSaveMatrix = (CtMyMatrix) objectInputStream.readObject();
        this.resId = objectInputStream.readInt();
        this.path = (String) objectInputStream.readObject();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMBaseData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.xPos);
        parcel.writeFloat(this.yPos);
        parcel.writeParcelable(this.canvasMatrix, i);
        parcel.writeParcelable(this.imageSaveMatrix, i);
        parcel.writeInt(this.resId);
        parcel.writeString(this.path);
    }

    public Ledl_StickerData(Parcel parcel) {
        this.xPos = parcel.readFloat();
        this.yPos = parcel.readFloat();
        this.canvasMatrix = (CtMyMatrix) parcel.readParcelable(CtMyMatrix.class.getClassLoader());
        this.imageSaveMatrix = (CtMyMatrix) parcel.readParcelable(CtMyMatrix.class.getClassLoader());
        this.resId = parcel.readInt();
        this.path = parcel.readString();
    }
}
