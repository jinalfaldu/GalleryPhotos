package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;


public class BaseData implements Serializable, Parcelable {
    public static final Parcelable.Creator<BaseData> CREATOR = new BaseDataCreator();

    @Override 
    public int describeContents() {
        return 0;
    }

    public CtMyMatrix getCanvasMatrix() {
        return null;
    }

    public CtMyMatrix getImageSaveMatrix() {
        return null;
    }

    public void setImageSaveMatrix(Matrix matrix) {
    }

    public void setMatrix(Matrix matrix) {
    }

    @Override 
    public void writeToParcel(Parcel parcel, int i) {
    }

    
    static class BaseDataCreator implements Parcelable.Creator<BaseData> {
        BaseDataCreator() {
        }

      
        @Override 
        public BaseData createFromParcel(Parcel parcel) {
            return new BaseData(parcel);
        }

      
        @Override 
        public BaseData[] newArray(int i) {
            return new BaseData[i];
        }
    }

    protected BaseData(Parcel parcel) {
    }

    public BaseData() {
    }

    public static BaseData[] ToBaseData(Parcelable[] parcelableArr) {
        if (parcelableArr == null) {
            return null;
        }
        BaseData[] cMBaseDataArr = new BaseData[parcelableArr.length];
        System.arraycopy(parcelableArr, 0, cMBaseDataArr, 0, parcelableArr.length);
        return cMBaseDataArr;
    }
}
