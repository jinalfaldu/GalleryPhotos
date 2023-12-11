package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class BaseFragment extends Fragment {
    Activity activity;
    PrefClass preferenceClass;

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.activity = getActivity();
        this.preferenceClass = new PrefClass(getActivity());
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void ShareImages(ArrayList<DataFileModel> arrayList) {
        ArrayList<Uri> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(arrayList.get(i).path);
            FragmentActivity activity = getActivity();
            arrayList2.add(FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file));
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

    public void ShareVideos(ArrayList<Object> arrayList) {
        ArrayList<Uri> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(((DataFileModel) arrayList.get(i)).path);
            FragmentActivity activity = getActivity();
            arrayList2.add(FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file));
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

    public void ShareBoth(ArrayList<DataFileModel> arrayList) {
        ArrayList<Uri> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(arrayList.get(i).path);
            FragmentActivity activity = getActivity();
            arrayList2.add(FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file));
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
            intent.setType("*/*");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList2);
            intent.addFlags(1);
            intent.addFlags(268435456);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object> GetPrivateVideoFiles(FragmentActivity fragmentActivity) throws IOException {
        this.preferenceClass = new PrefClass(fragmentActivity);
        ArrayList<Object> arrayList = new ArrayList<>();
        File[] listFiles = new File(FolderPath.SDCARD_PATH_VIDEO).listFiles();
        String str = AppUtilsClass.rootMainDir + File.separator + ".PhotoGallery1/PrivateVideo";
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                DataFileModel gMDataFileModel = new DataFileModel();
                gMDataFileModel.id = 0L;
                gMDataFileModel.name = file.getName();
                gMDataFileModel.path = file.getPath();
                gMDataFileModel.duration = getDurationFile(file.getPath());
                gMDataFileModel.folderName = "PrivateVideo";
                gMDataFileModel.folderPath = str;
                gMDataFileModel.oldpath = "";
                gMDataFileModel.directory = "";
                gMDataFileModel.isDirectory = false;
                gMDataFileModel.isSelected = false;
                gMDataFileModel.mediaType = ExifInterface.GPS_MEASUREMENT_3D;
                arrayList.add(gMDataFileModel);
            }
        }
        return arrayList;
    }

    public ArrayList<Object> GetPrivateImageFiles(FragmentActivity fragmentActivity) {
        this.preferenceClass = new PrefClass(fragmentActivity);
        ArrayList<Object> arrayList = new ArrayList<>();
        File file = new File(FolderPath.SDCARD_PATH_IMAGE);
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                DataFileModel gMDataFileModel = new DataFileModel();
                gMDataFileModel.name = file2.getName();
                gMDataFileModel.path = file2.getAbsolutePath();
                gMDataFileModel.mediaType = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                arrayList.add(gMDataFileModel);
            }
        }
        return arrayList;
    }

    public ArrayList<Object> GetDeletedImageFiles(FragmentActivity fragmentActivity) {
        this.preferenceClass = new PrefClass(fragmentActivity);
        ArrayList<Object> arrayList = new ArrayList<>();
        File[] listFiles = new File(FolderPath.SDCARD_PATH_DELETE_IMAGE).listFiles();
        String str = AppUtilsClass.rootMainDir + File.separator + ".PhotoGallery1/TrashImage";
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                DataFileModel gMDataFileModel = new DataFileModel();
                gMDataFileModel.id = 0L;
                gMDataFileModel.name = file.getName();
                gMDataFileModel.path = file.getPath();
                gMDataFileModel.folderName = "TrashImage";
                gMDataFileModel.folderPath = str;
                gMDataFileModel.oldpath = "";
                gMDataFileModel.directory = "";
                gMDataFileModel.isDirectory = false;
                gMDataFileModel.isSelected = false;
                gMDataFileModel.mediaType = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                arrayList.add(gMDataFileModel);
            }
        }
        return arrayList;
    }

    public ArrayList<Object> GetDeletedVideoFiles(FragmentActivity fragmentActivity) throws IOException {
        this.preferenceClass = new PrefClass(fragmentActivity);
        ArrayList<Object> arrayList = new ArrayList<>();
        File[] listFiles = new File(FolderPath.SDCARD_PATH_DELETE_VIDEO).listFiles();
        String str = AppUtilsClass.rootMainDir + File.separator + ".PhotoGallery1/TrashVideo";
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                DataFileModel gMDataFileModel = new DataFileModel();
                gMDataFileModel.id = 0L;
                gMDataFileModel.name = file.getName();
                gMDataFileModel.path = file.getPath();
                gMDataFileModel.duration = getDurationFile(file.getPath());
                gMDataFileModel.folderName = "TrashVideo";
                gMDataFileModel.folderPath = str;
                gMDataFileModel.oldpath = "";
                gMDataFileModel.directory = "";
                gMDataFileModel.isDirectory = false;
                gMDataFileModel.isSelected = false;
                gMDataFileModel.mediaType = ExifInterface.GPS_MEASUREMENT_3D;
                arrayList.add(gMDataFileModel);
            }
        }
        return arrayList;
    }

    public static long getDurationFile(String str) throws IOException {
        long j;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(str);
            j = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
            mediaMetadataRetriever.release();
        } catch (Exception unused) {
            mediaMetadataRetriever.release();
            j = 0;
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
        if (j > 0) {
            return j;
        }
        return 0L;
    }
}
