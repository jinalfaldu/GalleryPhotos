package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import hdphoto.galleryimages.gelleryalbum.R;
import java.util.ArrayList;


public class CtFontAdapter extends ArrayAdapter<ClipData.Item> {
    Context context;
    int layoutResourceId;
    ArrayList<Typeface> typeFaceArray;

    public CtFontAdapter(Context context, int i, String[] strArr) {
        super(context, i);
        this.typeFaceArray = new ArrayList<>();
        this.layoutResourceId = i;
        this.context = context;
        for (String str : strArr) {
            Typeface typeFace = CtFontCache.getTypeFace(context, str);
            if (typeFace != null) {
                this.typeFaceArray.add(typeFace);
            }
        }
    }

    @Override 
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.row_grid, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.item_text)).setTypeface(this.typeFaceArray.get(i));
        return view;
    }

    @Override 
    public int getCount() {
        return this.typeFaceArray.size();
    }
}
