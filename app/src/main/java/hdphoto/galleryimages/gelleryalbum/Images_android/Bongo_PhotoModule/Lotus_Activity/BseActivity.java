package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;


public class BseActivity extends AppCompatActivity {
    public static Activity activity;
    PrefClass preferenceClass;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        activity = this;
        this.preferenceClass = new PrefClass(getApplicationContext());
    }

    public String getStingListToString(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }

    public void ShareMultipleVideo(ArrayList<DataFileModel> arrayList) {
        ArrayList<Uri> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(arrayList.get(i).path);
            arrayList2.add(FileProvider.getUriForFile(this, getPackageName() + ".provider", file));
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("video/*");
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList2);
            startActivity(Intent.createChooser(intent, "Share Video"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShareMultipleImage(ArrayList<DataFileModel> arrayList) {
        ArrayList<Uri> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(arrayList.get(i).path);
            arrayList2.add(FileProvider.getUriForFile(this, getPackageName() + ".provider", file));
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList2);
            startActivity(Intent.createChooser(intent, "Share Image"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
