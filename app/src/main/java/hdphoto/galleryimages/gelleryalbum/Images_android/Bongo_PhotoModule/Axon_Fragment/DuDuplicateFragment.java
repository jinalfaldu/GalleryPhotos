package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataActivity.DataScanActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.Utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class DuDuplicateFragment extends BaseFragment {
    public static String mType;
    Activity activity;
    private ArrayList<String> arrPermission;
    boolean checkProcessRunning = false;
    Context context;
    private HashMap<String, ArrayList<File>> fileTypeArray;
    ImageView rl_Audio;
    ImageView rl_Document;
    ImageView rl_Image;
    ImageView rl_Video;
    View view;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_duplicate, viewGroup, false);
        this.context = getContext();
        this.activity = getActivity();
        ID_Binding();
        Click_Event();
        CheckPermission();
        this.fileTypeArray = new HashMap<>();
        new NativeHelper80().shownativeads(getActivity(), view.findViewById(R.id.native_container));

        return this.view;
    }

    private void ID_Binding() {
        this.rl_Image = this.view.findViewById(R.id.rl_images);
        this.rl_Audio = this.view.findViewById(R.id.rl_audios);
        this.rl_Video = this.view.findViewById(R.id.rl_videos);
        this.rl_Document = this.view.findViewById(R.id.rl_documents);
        this.view.findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdInterGD.getInstance().showInter(getActivity(), new AdInterGD.MyCallback() {
                    @Override
                    public void callbackCall() {
                        activity.finish();
                    }
                });
            }
        });
    }

    public void Click_Event() {
        this.rl_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DuDuplicateFragment.mType = "IMAGE";
                checkProcessRunning = true;
                getActivity().startActivity(new Intent(getActivity(), DataScanActivity.class).putExtra("ScanType", 1).putExtra("ScanData", "Images").putExtra("CheckProcess", true));

            }
        });
        this.rl_Audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DuDuplicateFragment.mType = "AUDIO";
                checkProcessRunning = true;
                getActivity().startActivity(new Intent(getActivity(), DataScanActivity.class).putExtra("ScanType", 3).putExtra("ScanData", "Audios").putExtra("CheckProcess", true));
            }
        });
        this.rl_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DuDuplicateFragment.mType = "VIDEO";
                checkProcessRunning = true;
                getActivity().startActivity(new Intent(getActivity(), DataScanActivity.class).putExtra("ScanType", 2).putExtra("ScanData", "Videos").putExtra("CheckProcess", true));

            }
        });
        this.rl_Document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkProcessRunning = true;
                getActivity().startActivity(new Intent(getActivity(), DataScanActivity.class).putExtra("ScanType", 4).putExtra("ScanData", "Documents").putExtra("CheckProcess", true));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 100) {
            for (int i2 : iArr) {
                if (iArr.length <= 0 || i2 != 0) {
                    Toast.makeText(this.activity, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", 0).show();
                    this.activity.finish();
                } else {
                    File file = new File(Utils.IMAGE_RECOVER_DIRECTORY);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                }
            }
        }
    }

    private void CheckPermission() {
        this.arrPermission = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Utils.checkSelfPermission(this.activity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                this.arrPermission.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
            if (!Utils.checkSelfPermission(this.activity, "android.permission.READ_EXTERNAL_STORAGE")) {
                this.arrPermission.add("android.permission.READ_EXTERNAL_STORAGE");
            }
            if (this.arrPermission.isEmpty()) {
                return;
            }
            requestPermissions((String[]) this.arrPermission.toArray(new String[0]), 100);
        }
    }
}
