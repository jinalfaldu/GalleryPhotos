package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.Tool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hdphoto.galleryimages.gelleryalbum.R;

import java.util.ArrayList;
import java.util.List;


public class EMEditingToolsAdapter extends RecyclerView.Adapter<EMEditingToolsAdapter.ViewHolder> {
    OnItemSelected mOnItemSelected;
    List<ToolModel> mToolList;


    public interface OnItemSelected {
        void onToolSelected(EMToolType eMToolType);
    }

    public EMEditingToolsAdapter(OnItemSelected onItemSelected) {
        ArrayList arrayList = new ArrayList();
        this.mToolList = arrayList;
        this.mOnItemSelected = onItemSelected;
        arrayList.add(new ToolModel("Shape", R.drawable.ic_oval, EMToolType.SHAPE));
        this.mToolList.add(new ToolModel("Text", R.drawable.ic_text, EMToolType.TEXT));
        this.mToolList.add(new ToolModel("Eraser", R.drawable.ic_eraser, EMToolType.ERASER));
        this.mToolList.add(new ToolModel("Filter", R.drawable.ic_photo_filter, EMToolType.FILTER));
        this.mToolList.add(new ToolModel("Emoji", R.drawable.ic_insert_emoticon, EMToolType.EMOJI));
        this.mToolList.add(new ToolModel("Sticker", R.drawable.ic_sticker, EMToolType.STICKER));
    }

    public class ToolModel {
        private int mToolIcon;
        private String mToolName;
        private EMToolType mToolType;

        ToolModel(String str, int i, EMToolType eMToolType) {
            this.mToolName = str;
            this.mToolIcon = i;
            this.mToolType = eMToolType;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_em_editing_tools_adapter, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ToolModel toolModel = this.mToolList.get(i);
        viewHolder.txtTool.setText(toolModel.mToolName);
        viewHolder.imgToolIcon.setImageResource(toolModel.mToolIcon);
    }

    @Override
    public int getItemCount() {
        return this.mToolList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgToolIcon;
        TextView txtTool;

        ViewHolder(View view) {
            super(view);
            this.imgToolIcon = (ImageView) view.findViewById(R.id.imgToolIcon);
            this.txtTool = (TextView) view.findViewById(R.id.txtTool);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    EMEditingToolsAdapter.this.mOnItemSelected.onToolSelected(EMEditingToolsAdapter.this.mToolList.get(ViewHolder.this.getLayoutPosition()).mToolType);
                }
            });
        }
    }
}
