package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import com.makeramen.roundedimageview.RoundedImageView;


public class Aspid_ShapeAdapter extends AMG_MyRecyclerAdapterBase<Aspid_ShapeAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "Adapter";
    int colorDefault;
    int colorSelected;
    CurrentCollageIndexChangedListener currentIndexListener;
    public int[] iconList;
    Context mContext;
    int selectedPosition;
    boolean setSelectedView;
    RecyclerView shapeRCV;
    View vvSelectedListItem;

    
    public interface CurrentCollageIndexChangedListener {
        void onIndexChanged(int i);
    }

    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgCollageIcon;
        int item;

        public void setItem(int i) {
            this.item = i;
            this.imgCollageIcon.setImageResource(i);
        }

        public ViewHolder(View view) {
            super(view);
            this.imgCollageIcon = (RoundedImageView) view.findViewById(R.id.image_view_collage_icon);
        }
    }

    public Aspid_ShapeAdapter(Context context, int[] iArr, CurrentCollageIndexChangedListener currentCollageIndexChangedListener, int i, int i2, boolean z) {
        this.setSelectedView = true;
        this.mContext = context;
        this.iconList = iArr;
        this.currentIndexListener = currentCollageIndexChangedListener;
        this.colorDefault = i;
        this.colorSelected = i2;
        this.setSelectedView = z;
    }

    public void setData(int[] iArr) {
        this.iconList = iArr;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMMyRecyclerAdapterBase, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_shape_adapter, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        inflate.setOnClickListener(this);
        return viewHolder;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMMyRecyclerAdapterBase, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setItem(this.iconList[i]);
        if (this.selectedPosition == i) {
            viewHolder.itemView.setBackgroundResource(R.drawable.selector_box);
        } else {
            viewHolder.itemView.setBackgroundResource(0);
        }
    }

    @Override 
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.shapeRCV = recyclerView;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMMyRecyclerAdapterBase
    public void setSelectedPositinVoid() {
        this.vvSelectedListItem = null;
        this.selectedPosition = -1;
    }

    @Override 
    public void onClick(View view) {
        View view2;
        int childPosition = this.shapeRCV.getChildPosition(view);
        RecyclerView.ViewHolder findViewHolderForPosition = this.shapeRCV.findViewHolderForPosition(this.selectedPosition);
        if (findViewHolderForPosition != null && (view2 = findViewHolderForPosition.itemView) != null) {
            view2.setBackgroundResource(0);
        }
        if (this.vvSelectedListItem != null) {
            Log.d(TAG, "selectedListItem " + childPosition);
        }
        this.currentIndexListener.onIndexChanged(childPosition);
        if (this.setSelectedView) {
            this.selectedPosition = childPosition;
            view.setBackgroundResource(R.drawable.selector_box);
            this.vvSelectedListItem = view;
        }
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMMyRecyclerAdapterBase, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.iconList.length;
    }
}
