package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData;

import android.app.Activity;
import android.os.AsyncTask;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import java.util.ArrayList;


public class VideoAlbumData {
    public static boolean gettingData = false;
    Activity context;
    public GetVideoAlbumAsync getVideoAlbumAsync = new GetVideoAlbumAsync();

    public VideoAlbumData(Activity activity) {
        this.context = activity;
    }


    public class GetVideoAlbumAsync extends AsyncTask<Void, Void, Void> {
        public GetVideoAlbumAsync() {
        }

        @Override 
        protected void onPreExecute() {
            VideoAlbumData.gettingData = true;
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            try {
                AppUtilsClass.ScanVideoAlbumListData(VideoAlbumData.this.context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ArrayList().addAll(ConstantArrayClass.videoAlbumsList);
            return null;
        }

        @Override 
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            VideoAlbumData.gettingData = false;
            if (ViVideoFragment.videoFolderAdapter != null) {
                ViVideoFragment.videoFolderAdapter.refreshData(ConstantArrayClass.videoAlbumsList);
            }
        }
    }
}
