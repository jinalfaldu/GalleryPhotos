package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass;

import android.os.Parcel;
import android.os.Parcelable;


public class CloudAlbumModel implements Parcelable {
    public static final Parcelable.Creator<CloudAlbumModel> CREATOR = new CloudCreator();
    protected String mId;
    protected String mThumbUrl;
    protected String mTitle;

    @Override 
    public int describeContents() {
        return 0;
    }


    static class CloudCreator implements Parcelable.Creator<CloudAlbumModel> {
        CloudCreator() {
        }

      
        @Override 
        public CloudAlbumModel createFromParcel(Parcel parcel) {
            return new CloudAlbumModel(parcel);
        }

      
        @Override 
        public CloudAlbumModel[] newArray(int i) {
            return new CloudAlbumModel[i];
        }
    }

    public String getID() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getThumbUrl() {
        return this.mThumbUrl;
    }

    @Override 
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mThumbUrl);
        parcel.writeString(this.mTitle);
    }

    private CloudAlbumModel(Parcel parcel) {
        this.mId = parcel.readString();
        this.mThumbUrl = parcel.readString();
        this.mTitle = parcel.readString();
    }
}
