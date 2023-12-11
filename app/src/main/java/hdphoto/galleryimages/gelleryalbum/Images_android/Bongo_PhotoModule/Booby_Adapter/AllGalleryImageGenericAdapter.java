package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import java.util.ArrayList;


public abstract class AllGalleryImageGenericAdapter<T> extends BaseAdapter {
    Activity activity;
    ArrayList<T> arrayList;
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<T> mStringFilterList;
    int size;
    Typeface typeface;

    @Override 
    public long getItemId(int i) {
        return i;
    }

    public AllGalleryImageGenericAdapter(Activity activity, Context context, ArrayList<T> arrayList) {
        this.arrayList = arrayList;
        this.mStringFilterList = arrayList;
        this.context = context;
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override 
    public int getCount() {
        return this.arrayList.size();
    }

    @Override 
    public T getItem(int i) {
        return this.arrayList.get(i);
    }

    public void setLayoutParams(int i) {
        this.size = i;
    }

    public void releaseResources() {
        this.arrayList = null;
        this.context = null;
        this.activity = null;
    }
}
