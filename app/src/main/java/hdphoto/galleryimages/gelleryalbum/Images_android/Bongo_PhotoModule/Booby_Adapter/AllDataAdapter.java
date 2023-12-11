package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.exifinterface.media.ExifInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.util.ArrayList;


public class AllDataAdapter extends AllGalleryImageGenericAdapter<DataFileModel> {
    public AllDataAdapter(Activity activity, Context context, ArrayList<DataFileModel> arrayList) {
        super(activity, context, arrayList);
    }

    @Override 
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.activity).inflate(R.layout.layout_all_data_adapter, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.image_view_image_select);
            viewHolder.rl_bg = (RelativeLayout) view.findViewById(R.id.relativelayout_bg);
            viewHolder.rel_play = (RelativeLayout) view.findViewById(R.id.rel_play);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        try {
            if (((DataFileModel) this.arrayList.get(i)).isSelected) {
                viewHolder.rl_bg.setVisibility(0);
            } else {
                viewHolder.rl_bg.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (((DataFileModel) this.arrayList.get(i)).getMediaType().equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                viewHolder.rel_play.setVisibility(0);
            } else if (((DataFileModel) this.arrayList.get(i)).getMediaType().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                viewHolder.rel_play.setVisibility(8);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.centerCrop();
            Glide.with(this.activity).load(((DataFileModel) this.arrayList.get(i)).path).apply((BaseRequestOptions<?>) requestOptions).into(viewHolder.imageView);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        viewHolder.imageView.getLayoutParams().width = this.size;
        viewHolder.imageView.getLayoutParams().height = this.size;
        viewHolder.rl_bg.getLayoutParams().width = this.size;
        viewHolder.rl_bg.getLayoutParams().height = this.size;
        return view;
    }


    private static class ViewHolder {
        public ImageView imageView;
        public RelativeLayout rel_play;
        public RelativeLayout rl_bg;

        private ViewHolder() {
        }
    }
}
