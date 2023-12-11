package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Heart_Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import com.makeramen.roundedimageview.RoundedImageView;


public class Azure_FilterAdapter extends RecyclerView.Adapter<Azure_FilterAdapter.ViewHolder> implements View.OnClickListener {
    int colorDefault;
    int colorSelected;
    public int[] iconList;
    IndexChangedListener listener;
    int proIndex;
    RecyclerView recyclerView;
    int selectedIndex;
    SelectedIndexChangedListener selectedIndexChangedListener;
    View selectedListItem;

    
    public interface IndexChangedListener {
        void onIndexChanged(int i);
    }

    
    public interface SelectedIndexChangedListener {
        void selectedIndexChanged(int i);
    }

    public Azure_FilterAdapter(int[] iArr, IndexChangedListener recyclerAdapterIndexChangedListener, int i, int i2, int i3) {
        this.proIndex = 100;
        this.iconList = iArr;
        this.listener = recyclerAdapterIndexChangedListener;
        this.colorDefault = i;
        this.colorSelected = i2;
        this.proIndex = i3;
    }

    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView imageView;
        IndexChangedListener viewHolderListener;

        public void setRecyclerAdapterIndexChangedListener(IndexChangedListener recyclerAdapterIndexChangedListener) {
            this.viewHolderListener = recyclerAdapterIndexChangedListener;
        }

        public void setItem(int i) {
            this.imageView.setImageResource(i);
        }

        public ViewHolder(View view) {
            super(view);
            this.imageView = (RoundedImageView) view.findViewById(R.id.filter_image);
        }
    }

    public void setSelectedIndexChangedListener(SelectedIndexChangedListener selectedIndexChangedListener) {
        this.selectedIndexChangedListener = selectedIndexChangedListener;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void SetSelectedIndex(int i) {
        this.selectedIndex = i;
        this.selectedIndexChangedListener.selectedIndexChanged(i);
    }

    public void setData(int[] iArr) {
        this.iconList = iArr;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_filter_adapter, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        viewHolder.setRecyclerAdapterIndexChangedListener(this.listener);
        inflate.setOnClickListener(this);
        return viewHolder;
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setItem(this.iconList[i]);
        if (this.selectedIndex == i) {
            viewHolder.itemView.setBackgroundResource(R.drawable.selector_box);
        } else {
            viewHolder.itemView.setBackgroundResource(0);
        }
    }

    @Override 
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void SetSelectedView(int i) {
        View view = this.selectedListItem;
        if (view != null) {
            view.setBackgroundResource(0);
        }
        View childAt = this.recyclerView.getChildAt(i);
        this.selectedListItem = childAt;
        if (childAt != null) {
            childAt.setBackgroundResource(R.drawable.selector_box);
        }
        SetSelectedIndex(i);
    }

    @Override 
    public void onClick(View view) {
        View view2;
        int childPosition = this.recyclerView.getChildPosition(view);
        RecyclerView.ViewHolder findViewHolderForPosition = this.recyclerView.findViewHolderForPosition(this.selectedIndex);
        if (findViewHolderForPosition != null && (view2 = findViewHolderForPosition.itemView) != null) {
            view2.setBackgroundResource(0);
        }
        this.selectedIndex = childPosition;
        this.selectedIndexChangedListener.selectedIndexChanged(childPosition);
        view.setBackgroundResource(R.drawable.selector_box);
        this.selectedListItem = view;
        this.listener.onIndexChanged(childPosition);
    }

    @Override 
    public int getItemCount() {
        return this.iconList.length;
    }
}
