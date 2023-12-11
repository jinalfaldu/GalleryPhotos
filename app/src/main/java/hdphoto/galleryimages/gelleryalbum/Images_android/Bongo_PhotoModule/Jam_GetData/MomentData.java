package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jam_GetData;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.MoMomentFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import java.util.ArrayList;


public class MomentData {
    public static boolean gettingData = false;
    Activity context;
    public GetMomentAllAsync getMomentAllAsync = new GetMomentAllAsync();

    public MomentData(Activity activity) {
        this.context = activity;
    }


    public class GetMomentAllAsync extends AsyncTask<Void, Void, Void> {
        public GetMomentAllAsync() {
        }

        @Override 
        protected void onPreExecute() {
            MomentData.gettingData = true;
        }

        @Override 
        public Void doInBackground(Void... voidArr) {
            AppUtilsClass.ScanMomentAllDataNew(MomentData.this.context);
            try {
                if (MoMomentFragment.momentAdapter == null) {
                    return null;
                }
                ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.addAll(ConstantArrayClass.albumsList);
                MoMomentFragment.momentAdapter.FilterNewData(arrayList);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("OOOOOO", " EXC --- " + e.getMessage());
                return null;
            }
        }

        @Override 
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            if (MoMomentFragment.momentAdapter != null && ConstantArrayClass.albumsList.size() != 0) {
                MoMomentFragment.momentAdapter.RefreshData(ConstantArrayClass.albumsList);
            }
            MomentData.gettingData = false;
        }
    }
}
