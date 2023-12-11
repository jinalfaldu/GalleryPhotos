package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi;

import java.io.Serializable;
import java.util.List;


public class BaseResponse<T> implements Serializable {
    List<T> data;
    String message;
    String result;
    String status;

    public String getResult() {
        return this.result;
    }

    public boolean getResultAsBoolean() {
        try {
            return Boolean.parseBoolean(this.result);
        } catch (Exception unused) {
            return false;
        }
    }

    public List<T> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }
}
