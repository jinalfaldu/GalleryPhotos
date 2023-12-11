package hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataModel.DataModel;
import java.util.ArrayList;


public class DataSectionRecyclerViewAdapter extends RecyclerView.Adapter<DataSectionRecyclerViewAdapter.SectionViewHolder> {
    private Context context;
    private DataRecyclerViewType recyclerViewType;
    private ArrayList<DataModel> sectionModelArrayList;


    public class SectionViewHolder extends RecyclerView.ViewHolder {
        RecyclerView itemRecyclerView;
        TextView sectionLabel;

        public SectionViewHolder(View view) {
            super(view);
            this.sectionLabel = (TextView) view.findViewById(R.id.section_label);
            this.itemRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        }
    }

    public DataSectionRecyclerViewAdapter(Context context, DataRecyclerViewType dMRecyclerViewType, ArrayList<DataModel> arrayList) {
        this.context = context;
        this.recyclerViewType = dMRecyclerViewType;
        this.sectionModelArrayList = arrayList;
    }

    @Override 
    public SectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_section, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(SectionViewHolder sectionViewHolder, int i) {
        DataModel dMDataModel = this.sectionModelArrayList.get(i);
        sectionViewHolder.sectionLabel.setText(dMDataModel.getTitleGroup());
        sectionViewHolder.itemRecyclerView.setHasFixedSize(true);
        sectionViewHolder.itemRecyclerView.setNestedScrollingEnabled(false);
        int i2 = AllViewClass.ViewList[this.recyclerViewType.ordinal()];
        if (i2 == 1) {
            sectionViewHolder.itemRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, 1, false));
        } else if (i2 == 2) {
            sectionViewHolder.itemRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, 0, false));
        } else if (i2 == 3) {
            sectionViewHolder.itemRecyclerView.setLayoutManager(new GridLayoutManager(this.context, 3));
        }
        sectionViewHolder.itemRecyclerView.setAdapter(new DataItemRecyclerViewAdapter(this.context, dMDataModel.getListDuplicate()));
    }


    public static class AllViewClass {
        static final int[] ViewList;

        static {
            int[] iArr = new int[DataRecyclerViewType.values().length];
            ViewList = iArr;
            iArr[DataRecyclerViewType.LINEAR_VERTICAL.ordinal()] = 1;
            iArr[DataRecyclerViewType.LINEAR_HORIZONTAL.ordinal()] = 2;
            try {
                iArr[DataRecyclerViewType.GRID.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override 
    public int getItemCount() {
        return this.sectionModelArrayList.size();
    }
}
