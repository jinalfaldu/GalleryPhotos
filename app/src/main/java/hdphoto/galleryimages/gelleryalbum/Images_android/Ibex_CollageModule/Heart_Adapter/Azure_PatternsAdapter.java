package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Heart_Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.PatternsResponse;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.List;


public class Azure_PatternsAdapter extends RecyclerView.Adapter<Azure_PatternsAdapter.ViewHolder> {
    OnItemClickListener<PatternsResponse.Pattern> onItemClickListener;
    List<PatternsResponse.Pattern> patterns;

    public Azure_PatternsAdapter(List<PatternsResponse.Pattern> list, OnItemClickListener<PatternsResponse.Pattern> onItemClickListener) {
        this.patterns = list;
        this.onItemClickListener = onItemClickListener;
        notifyDataSetChanged();
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pattern_adapter, (ViewGroup) null), this.onItemClickListener);
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(this.patterns.get(i));
    }

    @Override 
    public int getItemCount() {
        return this.patterns.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgIcon;
        ProgressBar loader;
        PatternsResponse.Pattern pattern;

        public ViewHolder(View view, final OnItemClickListener<PatternsResponse.Pattern> onItemClickListener) {
            super(view);
            this.imgIcon = (RoundedImageView) view.findViewById(R.id.iv_pattern);
            this.loader = (ProgressBar) view.findViewById(R.id.loader);
            view.setOnClickListener(new View.OnClickListener() {
                @Override 
                public void onClick(View view2) {
                    ViewHolder.this.PatternClick(onItemClickListener, view2);
                }
            });
        }

        public void PatternClick(OnItemClickListener onItemClickListener, View view) {
            onItemClickListener.onItemClicked(this.pattern, getAdapterPosition());
        }

        public void bind(PatternsResponse.Pattern pattern) {
            this.pattern = pattern;
            this.loader.setVisibility(View.VISIBLE);
            Glide.with(GalleryAppClass.getInstance()).load(pattern.getUri()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(R.color.gray).dontAnimate().skipMemoryCache(true)).listener(new RequestListener<Drawable>() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMAdapter.CMPatternsAdapter.ViewHolder.2
                @Override
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    ViewHolder.this.loader.setVisibility(View.VISIBLE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    ViewHolder.this.loader.setVisibility(View.GONE);
                    return false;
                }
            }).into(this.imgIcon);
        }
    }
}
