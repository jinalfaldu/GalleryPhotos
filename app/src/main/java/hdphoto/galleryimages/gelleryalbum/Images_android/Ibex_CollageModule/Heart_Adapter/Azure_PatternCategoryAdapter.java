package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Heart_Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.PatternsResponse;
import java.util.List;


public class Azure_PatternCategoryAdapter extends RecyclerView.Adapter<Azure_PatternCategoryAdapter.ViewHolder> {
    List<PatternsResponse.PatternsPack> categories;
    OnItemClickListener<PatternsResponse.Pattern> onItemClickListener;
    int[] patternBg = {R.drawable.ptrn_n_bg, R.drawable.ptrn_f_bg, R.drawable.ptrn_h_bg, R.drawable.ptrn_a_bg, R.drawable.ptrn_t_bg, R.drawable.ptrn_b_bg, R.drawable.ptrn_w_bg};
    int[] patternColor = {R.color.white, R.color.LightYellow, R.color.Yellow, R.color.Blue, R.color.Pink, R.color.Pitch, R.color.Violet, R.color.Gray};
    int[] patternCatIcon = {R.drawable.ptrn_n, R.drawable.ptrn_f, R.drawable.ptrn_h, R.drawable.ptrn_a, R.drawable.ptrn_t, R.drawable.ptrn_b, R.drawable.ptrn_w};

    public Azure_PatternCategoryAdapter(List<PatternsResponse.PatternsPack> list, OnItemClickListener<PatternsResponse.Pattern> onItemClickListener) {
        this.categories = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pattern_category_adapter, (ViewGroup) null), this.onItemClickListener);
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(this.categories.get(i));
        RelativeLayout relativeLayout = viewHolder.rl_bg;
        int[] iArr = this.patternBg;
        relativeLayout.setBackgroundResource(iArr[i % iArr.length]);
        ImageView imageView = viewHolder.imgIcon;
        int[] iArr2 = this.patternCatIcon;
        imageView.setImageResource(iArr2[i % iArr2.length]);
    }

    @Override 
    public int getItemCount() {
        return this.categories.size();
    }

    public void setItems(List<PatternsResponse.PatternsPack> list) {
        this.categories = list;
        notifyDataSetChanged();
    }

    public List<PatternsResponse.PatternsPack> getItems() {
        return this.categories;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        Azure_PatternsAdapter cmPatternsAdapter;
        ImageView imgIcon;
        OnItemClickListener<PatternsResponse.Pattern> onItemClickListener;
        RecyclerView rcvPatterns;
        RelativeLayout rl_bg;
        RelativeLayout rl_main;
        TextView txtTitle;

        public ViewHolder(View view, OnItemClickListener<PatternsResponse.Pattern> onItemClickListener) {
            super(view);
            this.onItemClickListener = onItemClickListener;
            this.imgIcon = (ImageView) view.findViewById(R.id.iv_category_icon);
            this.txtTitle = (TextView) view.findViewById(R.id.tv_category_name);
            this.rcvPatterns = (RecyclerView) view.findViewById(R.id.rv_patterns);
            this.rl_bg = (RelativeLayout) view.findViewById(R.id.rl_bg);
        }

        public void PatternsCategories(View view) {
            if (this.rcvPatterns.getVisibility() == View.VISIBLE) {
                this.rcvPatterns.setVisibility(View.GONE);
            } else {
                this.rcvPatterns.setVisibility(View.VISIBLE);
            }
        }

        public void bind(PatternsResponse.PatternsPack patternsPack) {
            this.txtTitle.setText(patternsPack.getName());
            Azure_PatternsAdapter cMPatternsAdapter = new Azure_PatternsAdapter(patternsPack.getPatterns(), this.onItemClickListener);
            this.cmPatternsAdapter = cMPatternsAdapter;
            this.rcvPatterns.setAdapter(cMPatternsAdapter);
        }
    }
}
