package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;


public class SlidShowEffectAdapter extends ArrayAdapter<String> {
    final Activity activity;
    final String[] maintitle;

    public SlidShowEffectAdapter(Activity activity, String[] strArr) {
        super(activity, (int) R.layout.layout_slide_show_effect, strArr);
        this.activity = activity;
        this.maintitle = strArr;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.layout_slide_show_effect, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon);
        ((TextView) inflate.findViewById(R.id.text1)).setText(this.maintitle[i]);
        if (Common.gEff_Pos == i) {
            imageView.setImageResource(R.drawable.slide_select);
        } else {
            imageView.setImageResource(R.drawable.slide_unselect);
        }
        return inflate;
    }
}
