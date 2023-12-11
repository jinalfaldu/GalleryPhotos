package hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataModel;

import java.io.File;


public class Duplicate {
    File file;
    boolean isChecked = true;
    int typeFile = 8;

    public int getTypeFile() {
        return this.typeFile;
    }

    public int setTypeFile(int i) {
        this.typeFile = i;
        return i;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }
}
