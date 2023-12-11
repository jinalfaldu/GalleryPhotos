package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import ja.burhanrashid52.photoeditor.PhotoFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EdFilterViewAdapter extends RecyclerView.Adapter<EdFilterViewAdapter.ViewHolder> {
    EdFilterListener mFilterListener;
    List<Pair<String, PhotoFilter>> mFilterPairList = new ArrayList();

    public EdFilterViewAdapter(EdFilterListener eMFilterListener) {
        this.mFilterListener = eMFilterListener;
        SetFilters();
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_filter_view_adapter, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Pair<String, PhotoFilter> pair = this.mFilterPairList.get(i);
        viewHolder.imgFilterView.setImageBitmap(GetBitmapFromAsset(viewHolder.itemView.getContext(), (String) pair.first));
        viewHolder.txtFilterName.setText(((PhotoFilter) pair.second).name().replace("_", " "));
    }

    @Override 
    public int getItemCount() {
        return this.mFilterPairList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFilterView;
        TextView txtFilterName;

        ViewHolder(View view) {
            super(view);
            this.imgFilterView = (ImageView) view.findViewById(R.id.imgFilterView);
            this.txtFilterName = (TextView) view.findViewById(R.id.txtFilterName);
            view.setOnClickListener(new View.OnClickListener() {
                @Override 
                public void onClick(View view2) {
                    EdFilterViewAdapter.this.mFilterListener.onFilterSelected((PhotoFilter) EdFilterViewAdapter.this.mFilterPairList.get(ViewHolder.this.getLayoutPosition()).second);
                }
            });
        }
    }

    private Bitmap GetBitmapFromAsset(Context context, String str) {
        try {
            return BitmapFactory.decodeStream(context.getAssets().open(str));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void SetFilters() {
        this.mFilterPairList.add(new Pair<>("filters/original.jpg", PhotoFilter.NONE));
        this.mFilterPairList.add(new Pair<>("filters/auto_fix.png", PhotoFilter.AUTO_FIX));
        this.mFilterPairList.add(new Pair<>("filters/brightness.png", PhotoFilter.BRIGHTNESS));
        this.mFilterPairList.add(new Pair<>("filters/contrast.png", PhotoFilter.CONTRAST));
        this.mFilterPairList.add(new Pair<>("filters/documentary.png", PhotoFilter.DOCUMENTARY));
        this.mFilterPairList.add(new Pair<>("filters/dual_tone.png", PhotoFilter.DUE_TONE));
        this.mFilterPairList.add(new Pair<>("filters/fill_light.png", PhotoFilter.FILL_LIGHT));
        this.mFilterPairList.add(new Pair<>("filters/fish_eye.png", PhotoFilter.FISH_EYE));
        this.mFilterPairList.add(new Pair<>("filters/grain.png", PhotoFilter.GRAIN));
        this.mFilterPairList.add(new Pair<>("filters/gray_scale.png", PhotoFilter.GRAY_SCALE));
        this.mFilterPairList.add(new Pair<>("filters/lomish.png", PhotoFilter.LOMISH));
        this.mFilterPairList.add(new Pair<>("filters/negative.png", PhotoFilter.NEGATIVE));
        this.mFilterPairList.add(new Pair<>("filters/posterize.png", PhotoFilter.POSTERIZE));
        this.mFilterPairList.add(new Pair<>("filters/saturate.png", PhotoFilter.SATURATE));
        this.mFilterPairList.add(new Pair<>("filters/sepia.png", PhotoFilter.SEPIA));
        this.mFilterPairList.add(new Pair<>("filters/sharpen.png", PhotoFilter.SHARPEN));
        this.mFilterPairList.add(new Pair<>("filters/temprature.png", PhotoFilter.TEMPERATURE));
        this.mFilterPairList.add(new Pair<>("filters/tint.png", PhotoFilter.TINT));
        this.mFilterPairList.add(new Pair<>("filters/vignette.png", PhotoFilter.VIGNETTE));
        this.mFilterPairList.add(new Pair<>("filters/cross_process.png", PhotoFilter.CROSS_PROCESS));
        this.mFilterPairList.add(new Pair<>("filters/b_n_w.png", PhotoFilter.BLACK_WHITE));
        this.mFilterPairList.add(new Pair<>("filters/flip_horizental.png", PhotoFilter.FLIP_HORIZONTAL));
        this.mFilterPairList.add(new Pair<>("filters/flip_vertical.png", PhotoFilter.FLIP_VERTICAL));
        this.mFilterPairList.add(new Pair<>("filters/rotate.png", PhotoFilter.ROTATE));
    }
}
