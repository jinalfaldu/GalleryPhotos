package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImagePrivateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.PrPrivateMainFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoPrivateFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideImageFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideVideoFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingPrivateImageDialog;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting.SortingVideoPrivateDialog;
import java.util.ArrayList;


public class DeguPrivateActivity extends BseActivity {
    private HideImageFolderDataSortingListener hideImageFolderDataSortingListener;
    HideVideoFolderDataSortingListener hideVideoFolderDataSortingListener;
    ImageView imgBack;
    ImageView imgSortImage;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gm_private);
        if (this.preferenceClass.getInt(Common.gIsLockStatus, 0) != 0) {
            this.preferenceClass.putInt(Common.gIsLockStatus, 1);
        }
        changeFragment(new PrPrivateMainFragment());
        ID_Binding();
        sortingPrivateImageCallBack();
        sortingPrivateVideoCallBack();
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

    }

    private void ID_Binding() {
        this.imgBack = (ImageView) findViewById(R.id.btnBack);
        this.imgSortImage = (ImageView) findViewById(R.id.btnSortImage);
        Click_Event();
    }

    private void Click_Event() {
        this.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                DeguPrivateActivity.this.onBackPressed();
            }
        });
        this.imgSortImage.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                DeguPrivateActivity.this.SortingData();
            }
        });
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fram_container, fragment);
        beginTransaction.commit();
    }

    public void SortingData() {
        if (Degu_MainActivity.privateclick == 0) {
            int i = this.preferenceClass.getInt(Common.gIsLockStatus, 0);
            if (i == 0) {
                Toast.makeText(this, "Please Set Security!", 0).show();
            } else if (i == 1) {
                Toast.makeText(this, "Please Enter Passcode!", 0).show();
            } else if (ImImagePrivateFragment.privateImageList.size() >= 1) {
                new SortingPrivateImageDialog(this, ImImagePrivateFragment.privateImageList, this.hideImageFolderDataSortingListener).ShowSortingDialogue();
            } else {
                Toast.makeText(this, "No Photos Found!", 0).show();
            }
        } else if (Degu_MainActivity.privateclick == 1) {
            int i2 = this.preferenceClass.getInt(Common.gIsLockStatus, 0);
            if (i2 == 0) {
                Toast.makeText(this, "Please Set Security!", 0).show();
            } else if (i2 == 1) {
                Toast.makeText(this, "Please Enter Passcode!", 0).show();
            } else if (ViVideoPrivateFragment.privateVideoList.size() >= 1) {
                new SortingVideoPrivateDialog(this, ViVideoPrivateFragment.privateVideoList, this.hideVideoFolderDataSortingListener).ShowSortingDialogue();
            } else {
                Toast.makeText(this, "No Videos Found!", 0).show();
            }
        }
    }

    private void sortingPrivateImageCallBack() {
        this.hideImageFolderDataSortingListener = new sortListenerHideImageFolderData();
    }


    public class sortListenerHideImageFolderData implements HideImageFolderDataSortingListener {
        sortListenerHideImageFolderData() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ImImagePrivateFragment.privateImageAdapter.notifyDataSetChanged();
            Toast.makeText(DeguPrivateActivity.this, "Sorting Successfully", 0).show();
        }
    }

    private void sortingPrivateVideoCallBack() {
        this.hideVideoFolderDataSortingListener = new sortCallBack();
    }


    public class sortCallBack implements HideVideoFolderDataSortingListener {
        sortCallBack() {
        }

        @Override
        public void Sorting(ArrayList<Object> arrayList) {
            ViVideoPrivateFragment.privateVideoAdapter.notifyDataSetChanged();
            Toast.makeText(DeguPrivateActivity.this, "Sorting Successfully", 0).show();
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(i, i2, intent);
        }
    }

    @Override
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this,true);

    }
}
