package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class CtMyMatrix extends Matrix implements Serializable, Parcelable {
    public static final Parcelable.Creator<CtMyMatrix> CREATOR = new MyMatrixCreator();
    private static final long serialVersionUID = 6346371585195628612L;

    @Override 
    public int describeContents() {
        return 0;
    }

    public CtMyMatrix(Matrix matrix) {
        super(matrix);
    }

    public CtMyMatrix() {
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        float[] fArr = new float[9];
        super.getValues(fArr);
        objectOutputStream.writeObject(fArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws Exception, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        super.setValues((float[]) objectInputStream.readObject());
    }


    static class MyMatrixCreator implements Parcelable.Creator<CtMyMatrix> {
        MyMatrixCreator() {
        }

      
        @Override 
        public CtMyMatrix createFromParcel(Parcel parcel) {
            return new CtMyMatrix(parcel);
        }

      
        @Override 
        public CtMyMatrix[] newArray(int i) {
            return new CtMyMatrix[i];
        }
    }

    @Override 
    public void writeToParcel(Parcel parcel, int i) {
        float[] fArr = new float[9];
        super.getValues(fArr);
        parcel.writeFloatArray(fArr);
    }

    public CtMyMatrix(Parcel parcel) {
        float[] fArr = new float[9];
        parcel.readFloatArray(fArr);
        super.setValues(fArr);
    }
}
