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


public class CtGridViewAdapter extends ArrayAdapter<ClipData.Item> {
    Context context;
    String[] fontPathList;
    int layoutResourceId;
    public Typeface[] typeFaceArray;

    public CtGridViewAdapter(Context context, int i, String[] strArr) {
        super(context, i);
        this.layoutResourceId = i;
        this.context = context;
        this.fontPathList = strArr;
        int length = strArr.length;
        this.typeFaceArray = new Typeface[length];
        for (int i2 = 0; i2 < length; i2++) {
            this.typeFaceArray[i2] = CtFontCache.getTypeFace(context, this.fontPathList[i2]);
        }
    }

    @Override 
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.row_grid, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.item_text)).setTypeface(this.typeFaceArray[i]);
        return view;
    }

    @Override 
    public int getCount() {
        return this.fontPathList.length;
    }
}
