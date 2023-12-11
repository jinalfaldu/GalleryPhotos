package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments;

import android.content.DialogInterface;


public final class RiverFontFragmentBackgroundColor implements DialogInterface.OnClickListener {
    public static final RiverFontFragmentBackgroundColor INSTANCE = new RiverFontFragmentBackgroundColor();

    private RiverFontFragmentBackgroundColor() {
    }

    @Override
    public final void onClick(DialogInterface dialogInterface, int i) {
        RiverFontFragment.showBackgroundColorPicker(dialogInterface, i);
    }
}
