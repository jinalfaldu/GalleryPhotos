package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery.Peyton_GalleryDataFragment;


public class CoCollageFragment extends BaseFragment {
    Activity activity;
    Context context;
    Peyton_GalleryDataFragment galleryDataFragment;

    public View view;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_collage, viewGroup, false);
        this.context = getContext();
        this.activity = getActivity();
        this.galleryDataFragment = new Peyton_GalleryDataFragment();
        binding();
        return this.view;
    }

    private void binding() {
        changeFragment(this.galleryDataFragment);
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.gallery_container, fragment);
        beginTransaction.commit();
    }
}
