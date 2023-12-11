package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import android.content.Intent;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.Impala_MirrorActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery.Peyton_GalleryDataFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.MusicianUtility;


public class Clover_FragmentHelper {
    protected static final String TAG = "CollageHelper";

    public static Peyton_GalleryDataFragment getGalleryFragment(FragmentActivity fragmentActivity) {
        return (Peyton_GalleryDataFragment) fragmentActivity.getSupportFragmentManager().findFragmentByTag("myFragmentTag");
    }

    public static Peyton_GalleryDataFragment addCollageGalleryFragment(FragmentActivity fragmentActivity, int i, boolean z, View view) {
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        Peyton_GalleryDataFragment cMGalleryDataFragment = (Peyton_GalleryDataFragment) supportFragmentManager.findFragmentByTag("myFragmentTag");
        if (cMGalleryDataFragment == null) {
            Peyton_GalleryDataFragment cMGalleryDataFragment2 = new Peyton_GalleryDataFragment();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.add(i, cMGalleryDataFragment2, "myFragmentTag");
            beginTransaction.commitAllowingStateLoss();
            cMGalleryDataFragment2.setCollageGalleryListener(createCollageGalleryListener(fragmentActivity, cMGalleryDataFragment2, z, view));
            fragmentActivity.findViewById(i).bringToFront();
            return cMGalleryDataFragment2;
        }
        fragmentActivity.getSupportFragmentManager().beginTransaction().show(cMGalleryDataFragment).commitAllowingStateLoss();
        return cMGalleryDataFragment;
    }

    public static Peyton_GalleryDataFragment.CollageGalleryListener createCollageGalleryListener(final FragmentActivity fragmentActivity, final Peyton_GalleryDataFragment cMGalleryDataFragment, boolean z, final View view) {
        return new Peyton_GalleryDataFragment.CollageGalleryListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMFragmentHelper.1
            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.CollageGalleryListener
            public void onGalleryOkImageArrayRemoveFragment(long[] jArr, int[] iArr, boolean z2, boolean z3) {
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.CollageGalleryListener
            public void onGalleryOkSingleImage(long j, int i, boolean z2, boolean z3) {
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.CollageGalleryListener
            public void onGalleryCancel() {
                View view2 = view;
                if (view2 != null && view2.getVisibility() != 0) {
                    view.setVisibility(0);
                }
                fragmentActivity.getSupportFragmentManager().beginTransaction().remove(cMGalleryDataFragment).commitAllowingStateLoss();
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.CollageGalleryListener
            public void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean z2, boolean z3) {
                View view2 = view;
                if (view2 != null && view2.getVisibility() != 0) {
                    view.setVisibility(0);
                }
                Intent intent = new Intent(fragmentActivity, Duff_CollageActivity.class);
                intent.putExtra("photo_id_list", jArr);
                intent.putExtra("photo_orientation_list", iArr);
                intent.putExtra("is_scrap_book", z2);
                intent.putExtra("is_shape", z3);
                fragmentActivity.startActivity(intent);
            }
        };
    }

    public static Peyton_GalleryDataFragment addMirrorGalleryFragment(FragmentActivity fragmentActivity, int i, boolean z, View view) {
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        Peyton_GalleryDataFragment cMGalleryDataFragment = (Peyton_GalleryDataFragment) supportFragmentManager.findFragmentByTag("myFragmentTag");
        if (cMGalleryDataFragment == null) {
            Peyton_GalleryDataFragment cMGalleryDataFragment2 = new Peyton_GalleryDataFragment();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.add(i, cMGalleryDataFragment2, "myFragmentTag");
            beginTransaction.commitAllowingStateLoss();
            cMGalleryDataFragment2.setMirrorGalleryListener(createMirrorGalleryListener(fragmentActivity, cMGalleryDataFragment2, z, view));
            fragmentActivity.findViewById(i).bringToFront();
            return cMGalleryDataFragment2;
        }
        fragmentActivity.getSupportFragmentManager().beginTransaction().show(cMGalleryDataFragment).commitAllowingStateLoss();
        return cMGalleryDataFragment;
    }

    public static Peyton_GalleryDataFragment.MirrorGalleryListener createMirrorGalleryListener(final FragmentActivity fragmentActivity, final Peyton_GalleryDataFragment cMGalleryDataFragment, boolean z, final View view) {
        return new Peyton_GalleryDataFragment.MirrorGalleryListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMFragmentHelper.2
            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.MirrorGalleryListener
            public void onGalleryOkImageArrayRemoveFragment(long[] jArr, int[] iArr, boolean z2, boolean z3) {
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.MirrorGalleryListener
            public void onGalleryOkSingleImage(long j, int i, boolean z2, boolean z3) {
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.MirrorGalleryListener
            public void onGalleryCancel() {
                View view2 = view;
                if (view2 != null && view2.getVisibility() != 0) {
                    view.setVisibility(0);
                }
                fragmentActivity.getSupportFragmentManager().beginTransaction().remove(cMGalleryDataFragment).commitAllowingStateLoss();
            }

            @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.MirrorGalleryListener
            public void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean z2, boolean z3, String[] strArr) {
                View view2 = view;
                if (view2 != null && view2.getVisibility() != 0) {
                    view.setVisibility(0);
                }
                int GetMaxSizeForDimension = MusicianUtility.GetMaxSizeForDimension(fragmentActivity, 1, 1500.0f);
                Intent intent = new Intent(fragmentActivity, Impala_MirrorActivity.class);
                intent.putExtra("selectedImagePath", strArr[0]);
                intent.putExtra("isSession", false);
                intent.putExtra("MAX_SIZE", GetMaxSizeForDimension);
                fragmentActivity.startActivity(intent);
            }
        };
    }
}
