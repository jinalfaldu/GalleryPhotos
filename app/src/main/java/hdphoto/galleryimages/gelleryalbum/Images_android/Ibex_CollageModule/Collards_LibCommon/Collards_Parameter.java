package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;


public class Collards_Parameter implements Serializable, Parcelable {
    private static final long serialVersionUID = -3588782317514910667L;
    public int blur;
    public int brightness;
    public int contrast;
    public float highlight;
    public int id;
    public int saturation;
    public int seekBarMode;
    public int slctedBorderIndex;
    public int slctedFilterIndex;
    public int slctedOverlayIndex;
    public int selectedTextureIndex;
    public float shadow;
    public float sharpen;
    public int temperature;
    public int tint;
    public static final Parcelable.Creator<Collards_Parameter> CREATOR = new Parcelable.Creator<Collards_Parameter>() {
      
        @Override 
        public Collards_Parameter createFromParcel(Parcel parcel) {
            return new Collards_Parameter(parcel);
        }

      
        @Override 
        public Collards_Parameter[] newArray(int i) {
            return new Collards_Parameter[i];
        }
    };
    public static AtomicInteger uniqueId = new AtomicInteger();

    @Override 
    public int describeContents() {
        return 0;
    }

    public void setSelectedTextureIndex(int i) {
        this.selectedTextureIndex = i;
    }

    public void setSlctedBorderIndex(int i) {
        this.slctedBorderIndex = i;
    }

    public void setSlctedOverlayIndex(int i) {
        this.slctedOverlayIndex = i;
    }

    public void setSlctedFilterIndex(int i) {
        this.slctedFilterIndex = i;
    }

    public int getSelectedTextureIndex() {
        return this.selectedTextureIndex;
    }

    public int getSlctedBorderIndex() {
        return this.slctedBorderIndex;
    }

    public int getSlctedOverlayIndex() {
        return this.slctedOverlayIndex;
    }

    public int getSlctedFilterIndex() {
        return this.slctedFilterIndex;
    }

    public Collards_Parameter copy() {
        return new Collards_Parameter(this);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public boolean isParameterChanged() {
        new Collards_Parameter();
        return true;
    }

    public boolean isParameterChanged(Collards_Parameter cMParameter) {
        if (cMParameter == null) {
            return isParameterChanged();
        }
        return !(this.contrast == cMParameter.contrast && this.brightness == cMParameter.brightness && this.saturation == cMParameter.saturation && this.temperature == cMParameter.temperature && this.tint == cMParameter.tint && this.sharpen == cMParameter.sharpen && this.blur == cMParameter.blur && this.highlight == cMParameter.highlight && this.shadow == cMParameter.shadow && this.slctedBorderIndex == cMParameter.slctedBorderIndex && this.slctedFilterIndex == cMParameter.slctedFilterIndex && this.slctedOverlayIndex == cMParameter.slctedOverlayIndex && this.selectedTextureIndex == cMParameter.selectedTextureIndex) && isParameterChanged();
    }

    public boolean isParameterReallyChanged(Collards_Parameter cMParameter) {
        if (this.contrast == cMParameter.contrast && this.brightness == cMParameter.brightness && this.saturation == cMParameter.saturation && this.temperature == cMParameter.temperature && this.tint == cMParameter.tint && this.sharpen == cMParameter.sharpen && this.blur == cMParameter.blur && this.highlight == cMParameter.highlight && this.shadow == cMParameter.shadow && this.slctedBorderIndex == cMParameter.slctedBorderIndex && this.slctedFilterIndex == cMParameter.slctedFilterIndex && this.slctedOverlayIndex == cMParameter.slctedOverlayIndex) {
            int i = cMParameter.selectedTextureIndex;
            return true;
        }
        return true;
    }

    public Collards_Parameter(Collards_Parameter cMParameter) {
        this.blur = 0;
        this.highlight = 0.0f;
        this.seekBarMode = 0;
        this.shadow = 0.0f;
        this.sharpen = 0.0f;
        set(cMParameter);
    }

    public Collards_Parameter() {
        this.blur = 0;
        this.highlight = 0.0f;
        this.seekBarMode = 0;
        this.shadow = 0.0f;
        this.sharpen = 0.0f;
        reset();
        this.id = uniqueId.getAndIncrement();
        this.seekBarMode = 0;
    }

    public void set(Collards_Parameter cMParameter) {
        this.brightness = cMParameter.brightness;
        this.temperature = cMParameter.temperature;
        this.contrast = cMParameter.contrast;
        this.saturation = cMParameter.saturation;
        this.tint = cMParameter.tint;
        this.selectedTextureIndex = cMParameter.selectedTextureIndex;
        this.slctedBorderIndex = cMParameter.slctedBorderIndex;
        this.slctedOverlayIndex = cMParameter.slctedOverlayIndex;
        this.slctedFilterIndex = cMParameter.slctedFilterIndex;
        this.sharpen = cMParameter.sharpen;
        this.blur = cMParameter.blur;
        this.highlight = cMParameter.highlight;
        this.shadow = cMParameter.shadow;
        this.seekBarMode = cMParameter.seekBarMode;
        this.id = cMParameter.id;
    }

    public void reset() {
        this.brightness = 0;
        this.contrast = 0;
        this.temperature = 0;
        this.saturation = 50;
        this.tint = 0;
        this.selectedTextureIndex = 0;
        this.slctedBorderIndex = 0;
        this.slctedOverlayIndex = 0;
        this.slctedFilterIndex = 0;
        this.sharpen = 0.0f;
        this.blur = 0;
        this.highlight = 0.0f;
        this.shadow = 0.0f;
    }

    public int getTemperature() {
        return -this.temperature;
    }

    public void setTemperature(int i) {
        this.temperature = (i - 50) * 2;
    }

    public void setTemperatureValue(int i) {
        this.temperature = i;
    }

    public void setContrast(int i) {
        this.contrast = (i - 50) * 2;
    }

    public void setContrastValue(int i) {
        this.contrast = i;
    }

    public void setBrightness(int i) {
        int i2 = i - 50;
        if (i2 < 0) {
            this.brightness = i2 * 3;
        } else {
            this.brightness = i2 * 5;
        }
    }

    public void setBrightnessValue(int i) {
        this.brightness = i;
    }

    public int getContrastProgress() {
        return (this.contrast / 2) + 50;
    }

    public int getBrightProgress() {
        int i;
        int i2 = this.brightness;
        if (i2 < 0) {
            i = i2 / 3;
        } else {
            i = i2 / 5;
        }
        return i + 50;
    }

    public int getTemperatureProgress() {
        return (this.temperature / 2) + 50;
    }

    public void setSaturation(int i) {
        this.saturation = i;
    }

    public float getSaturation() {
        return this.saturation / 50.0f;
    }

    public int getTint() {
        return this.tint;
    }

    public void setTint(int i) {
        this.tint = i - 50;
    }

    public void setTintValue(int i) {
        this.tint = i;
    }

    public int getTintProgressValue() {
        return this.tint + 50;
    }

    public int getSaturationProgressValue() {
        return this.saturation;
    }

    public void setSharpen(int i) {
        this.sharpen = i / 100.0f;
    }

    public int getSharpenValue() {
        return (int) (this.sharpen * 100.0f);
    }

    public void setBlur(int i) {
        float f = i / 4.0f;
        if (f > 25.0f) {
            f = 25.0f;
        }
        this.blur = (int) f;
    }

    public int getBlurValue() {
        return this.blur * 4;
    }

    public void setHighlight(int i) {
        this.highlight = (i - 50) / 255.0f;
    }

    public int getHighlightValue() {
        return (int) ((this.highlight * 255.0f) + 50.0f);
    }

    public void setShadow(int i) {
        this.shadow = (i - 50) / 255.0f;
    }

    public int getShadowValue() {
        return (int) ((this.shadow * 255.0f) + 50.0f);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.brightness);
        objectOutputStream.writeInt(this.contrast);
        objectOutputStream.writeInt(this.temperature);
        objectOutputStream.writeInt(this.saturation);
        objectOutputStream.writeInt(this.tint);
        objectOutputStream.writeInt(this.selectedTextureIndex);
        objectOutputStream.writeInt(this.slctedBorderIndex);
        objectOutputStream.writeInt(this.slctedOverlayIndex);
        objectOutputStream.writeInt(this.slctedFilterIndex);
        objectOutputStream.writeInt(this.seekBarMode);
        objectOutputStream.writeFloat(this.sharpen);
        objectOutputStream.writeInt(this.blur);
        objectOutputStream.writeFloat(this.highlight);
        objectOutputStream.writeFloat(this.shadow);
        objectOutputStream.writeInt(this.id);
    }

    private void readObject(ObjectInputStream objectInputStream) throws Exception, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.brightness = objectInputStream.readInt();
        this.contrast = objectInputStream.readInt();
        this.temperature = objectInputStream.readInt();
        this.saturation = objectInputStream.readInt();
        this.tint = objectInputStream.readInt();
        this.selectedTextureIndex = objectInputStream.readInt();
        this.slctedBorderIndex = objectInputStream.readInt();
        this.slctedOverlayIndex = objectInputStream.readInt();
        this.slctedFilterIndex = objectInputStream.readInt();
        this.seekBarMode = objectInputStream.readInt();
        try {
            this.sharpen = objectInputStream.readFloat();
            this.blur = objectInputStream.readInt();
            this.highlight = objectInputStream.readFloat();
            this.shadow = objectInputStream.readFloat();
            this.id = objectInputStream.readInt();
        } catch (Exception unused) {
        }
    }

    public Collards_Parameter(Parcel parcel) {
        this.blur = 0;
        this.highlight = 0.0f;
        this.seekBarMode = 0;
        this.shadow = 0.0f;
        this.sharpen = 0.0f;
        this.brightness = parcel.readInt();
        this.contrast = parcel.readInt();
        this.temperature = parcel.readInt();
        this.saturation = parcel.readInt();
        this.tint = parcel.readInt();
        this.selectedTextureIndex = parcel.readInt();
        this.slctedBorderIndex = parcel.readInt();
        this.slctedOverlayIndex = parcel.readInt();
        this.slctedFilterIndex = parcel.readInt();
        this.seekBarMode = parcel.readInt();
        this.sharpen = parcel.readFloat();
        this.blur = parcel.readInt();
        this.highlight = parcel.readFloat();
        this.shadow = parcel.readFloat();
        this.id = parcel.readInt();
    }

    @Override 
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.brightness);
        parcel.writeInt(this.contrast);
        parcel.writeInt(this.temperature);
        parcel.writeInt(this.saturation);
        parcel.writeInt(this.tint);
        parcel.writeInt(this.selectedTextureIndex);
        parcel.writeInt(this.slctedBorderIndex);
        parcel.writeInt(this.slctedOverlayIndex);
        parcel.writeInt(this.slctedFilterIndex);
        parcel.writeInt(this.seekBarMode);
        parcel.writeFloat(this.sharpen);
        parcel.writeInt(this.blur);
        parcel.writeFloat(this.highlight);
        parcel.writeFloat(this.shadow);
        parcel.writeInt(this.id);
    }
}
