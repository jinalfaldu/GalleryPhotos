package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments;

import android.content.DialogInterface;


public final class RiverFontFragmentFontColor implements DialogInterface.OnClickListener {
    public static final RiverFontFragmentFontColor INSTANCE = new RiverFontFragmentFontColor();

    private RiverFontFragmentFontColor() {
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        RiverFontFragment.showFontColorPicker(dialogInterface, i);
    }
}
