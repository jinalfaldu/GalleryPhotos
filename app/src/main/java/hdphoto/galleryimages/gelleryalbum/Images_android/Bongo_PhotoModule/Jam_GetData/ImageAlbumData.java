package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData;

import android.app.Activity;
import android.os.AsyncTask;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImageFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import java.util.ArrayList;


public class ImageAlbumData {
    public static boolean gettingData = false;
    Activity context;
    public GetImageAlbumAsync getImageAlbumAsync = new GetImageAlbumAsync();

    public ImageAlbumData(Activity activity) {
        this.context = activity;
    }


    public class GetImageAlbumAsync extends AsyncTask<Void, Void, Void> {
        public GetImageAlbumAsync() {
        }

        @Override 
        protected void onPreExecute() {
            ImageAlbumData.gettingData = true;
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            AppUtilsClass.ScanImageAlbumListData(ImageAlbumData.this.context);
            new ArrayList().addAll(ConstantArrayClass.imageAlbumsList);
            return null;
        }

        @Override 
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            ImageAlbumData.gettingData = false;
            if (ImImageFragment.imageFolderAdapter == null || ConstantArrayClass.imageAlbumsList.size() == 0) {
                return;
            }
            ImImageFragment.imageFolderAdapter.refreshData(ConstantArrayClass.imageAlbumsList);
        }
    }
}
