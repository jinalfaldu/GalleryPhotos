package hdphoto.galleryimages.gelleryalbum.Images_android.Utils;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;


public class PrivateMasterPassword {
    ArrayList<String> msAArray;
    ArrayList<String> msBArray;
    ArrayList<String> msCArray;

    public ArrayList<String> GetMSArrayA() {
        ArrayList<String> arrayList = new ArrayList<>();
        this.msAArray = arrayList;
        arrayList.add("5");
        this.msAArray.add(ExifInterface.GPS_MEASUREMENT_2D);
        this.msAArray.add("6");
        this.msAArray.add("9");
        return this.msAArray;
    }

    public ArrayList<String> GetMSArrayB() {
        ArrayList<String> arrayList = new ArrayList<>();
        this.msBArray = arrayList;
        arrayList.add("4");
        this.msBArray.add("8");
        this.msBArray.add("6");
        this.msBArray.add("5");
        return this.msBArray;
    }

    public ArrayList<String> GetMSArrayC() {
        ArrayList<String> arrayList = new ArrayList<>();
        this.msCArray = arrayList;
        arrayList.add("7");
        this.msCArray.add("5");
        this.msCArray.add("9");
        this.msCArray.add(ExifInterface.GPS_MEASUREMENT_2D);
        return this.msCArray;
    }
}
