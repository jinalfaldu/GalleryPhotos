package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import hdphoto.galleryimages.gelleryalbum.R;
import java.util.ArrayList;
import java.util.List;


public class CtRainbowPickerAdapter extends BaseAdapter {
    int colorGridColumnWidth;
    List<Integer> colorList;
    Context context;

    @Override 
    public long getItemId(int i) {
        return 0L;
    }

    public CtRainbowPickerAdapter(Context context, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        this.colorList = arrayList;
        this.context = context;
        arrayList.add(Integer.valueOf(i));
        this.colorGridColumnWidth = (int) this.context.getResources().getDimension(R.dimen.colorGridColumnWidth);
        int i3 = 0;
        while (true) {
            if (i3 > 255) {
                break;
            }
            this.colorList.add(Integer.valueOf(Color.rgb(255, i3, 0)));
            i3 += 16;
        }
        for (int i4 = 255; i4 >= 0; i4 -= 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(i4, 255, 0)));
        }
        for (int i5 = 0; i5 <= 255; i5 += 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(0, 255, i5)));
        }
        for (int i6 = 255; i6 >= 0; i6 -= 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(0, i6, 255)));
        }
        for (int i7 = 0; i7 <= 255; i7 += 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(i7, 0, 255)));
        }
        for (int i8 = 255; i8 >= 0; i8 -= 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(255, 0, i8)));
        }
        for (i2 = 255; i2 >= 0; i2 -= 11) {
            this.colorList.add(Integer.valueOf(Color.rgb(i2, i2, i2)));
        }
    }

    @Override 
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            imageView = new ImageView(this.context);
            int i2 = this.colorGridColumnWidth;
            imageView.setLayoutParams(new AbsListView.LayoutParams(i2, i2));
        } else {
            imageView = (ImageView) view;
        }
        if (i == 0) {
            imageView.setImageResource(R.drawable.color_none);
        } else {
            imageView.setImageResource(17170445);
            imageView.setBackgroundColor(this.colorList.get(i).intValue());
        }
        imageView.setId(i);
        return imageView;
    }

    @Override 
    public int getCount() {
        return this.colorList.size();
    }

    @Override 
    public Integer getItem(int i) {
        return this.colorList.get(i);
    }
}
