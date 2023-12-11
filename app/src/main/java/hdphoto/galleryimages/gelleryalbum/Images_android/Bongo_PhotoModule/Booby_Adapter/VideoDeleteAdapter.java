package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickHideListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import java.util.ArrayList;


public class VideoDeleteAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Object> arrayList;
    Context context;
    LayoutInflater layoutInflater;
    OnClickHideListener<ArrayList<Object>, Integer, Boolean, View> onClickHideListener;
    int size;

    @Override 
    public long getItemId(int i) {
        return i;
    }

    public VideoDeleteAdapter(Activity activity, Context context, ArrayList<Object> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override 
    public int getCount() {
        return this.arrayList.size();
    }

    @Override 
    public Object getItem(int i) {
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

    @Override 
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.layoutInflater.inflate(R.layout.layout_image_private_delete_adapter, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.img_hide_photo_row = (ImageView) view.findViewById(R.id.hide_photo_row_image);
            viewHolder.rl_hide_photo_row_image_view = (RelativeLayout) view.findViewById(R.id.view_alpha);
            viewHolder.rl_albumView = (RelativeLayout) view.findViewById(R.id.albumViewLayout);
            viewHolder.txt_view_album_name = (TextView) view.findViewById(R.id.text_view_album_name);
            viewHolder.txt_view_album_size = (TextView) view.findViewById(R.id.text_view_album_size);
            viewHolder.imgMoreDetail = (ImageView) view.findViewById(R.id.btnMoreDetails);
            viewHolder.rl_play = (RelativeLayout) view.findViewById(R.id.rel_play);
            viewHolder.tv_size = (TextView) view.findViewById(R.id.txt_size);
            viewHolder.ll_size = (LinearLayout) view.findViewById(R.id.lin_size);
            viewHolder.txt_size = (TextView) view.findViewById(R.id.txt_size);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.rl_play.setVisibility(0);
        viewHolder.ll_size.setVisibility(0);
        viewHolder.img_hide_photo_row.getLayoutParams().width = this.size;
        viewHolder.img_hide_photo_row.getLayoutParams().height = this.size;
        viewHolder.rl_hide_photo_row_image_view.getLayoutParams().width = this.size;
        viewHolder.rl_hide_photo_row_image_view.getLayoutParams().height = this.size;
        if (((DataFileModel) this.arrayList.get(i)).isSelected) {
            viewHolder.rl_hide_photo_row_image_view.setVisibility(0);
        } else {
            viewHolder.rl_hide_photo_row_image_view.setVisibility(8);
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        Glide.with(this.context).load(((DataFileModel) this.arrayList.get(i)).path).apply((BaseRequestOptions<?>) requestOptions).into(viewHolder.img_hide_photo_row);
        if (((DataFileModel) this.arrayList.get(i)).isDirectory) {
            viewHolder.rl_albumView.setVisibility(0);
            TextView textView = viewHolder.txt_view_album_size;
            textView.setText("(" + ((DataFileModel) this.arrayList.get(i)).getSubFile().size() + ")");
            viewHolder.txt_view_album_name.setText(((DataFileModel) this.arrayList.get(i)).name);
        } else {
            viewHolder.rl_albumView.setVisibility(8);
        }
        viewHolder.img_hide_photo_row.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                try {
                    if (((DataFileModel) VideoDeleteAdapter.this.arrayList.get(i)).isDirectory) {
                        VideoDeleteAdapter.this.onClickHideListener.onClickListener(((DataFileModel) VideoDeleteAdapter.this.arrayList.get(i)).getSubFile(), Integer.valueOf(i), true, view2);
                    } else {
                        VideoDeleteAdapter.this.onClickHideListener.onClickListener(VideoDeleteAdapter.this.arrayList, Integer.valueOf(i), false, view2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        viewHolder.img_hide_photo_row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override 
            public boolean onLongClick(View view2) {
                try {
                    if (((DataFileModel) VideoDeleteAdapter.this.arrayList.get(i)).isDirectory) {
                        VideoDeleteAdapter.this.onClickHideListener.onLongClickListener(VideoDeleteAdapter.this.arrayList, Integer.valueOf(i), true, view2);
                    } else {
                        VideoDeleteAdapter.this.onClickHideListener.onLongClickListener(VideoDeleteAdapter.this.arrayList, Integer.valueOf(i), false, view2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        viewHolder.imgMoreDetail.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                VideoDeleteAdapter.this.onClickHideListener.onClickMoreListener(VideoDeleteAdapter.this.arrayList, Integer.valueOf(i), view2, false);
            }
        });
        return view;
    }

    private static String getTime(int i) {
        int i2 = i / 3600;
        int i3 = i % 3600;
        return String.format("%02d", Integer.valueOf(i2)) + " " + String.format("%02d", Integer.valueOf(i3 / 60)) + " " + String.format("%02d", Integer.valueOf(i3 % 60));
    }

    public void setItemClickCallback(OnClickHideListener onClickHideListener) {
        this.onClickHideListener = onClickHideListener;
    }


    private static class ViewHolder {
        ImageView imgMoreDetail;
        ImageView img_hide_photo_row;
        LinearLayout ll_size;
        RelativeLayout rl_albumView;
        RelativeLayout rl_hide_photo_row_image_view;
        RelativeLayout rl_play;
        TextView tv_size;
        TextView txt_size;
        TextView txt_view_album_name;
        TextView txt_view_album_size;

        private ViewHolder() {
        }
    }
}
