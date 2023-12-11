package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.MoMomentFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.MyViewHolder> {
    public static final int viewType_header = 0;
    public static final int viewType_level = 1;
    Activity activity;
    Context context;
    ArrayList<Object> momentArray;
    private OnClickListener<ArrayList<Object>, Integer, View> onClickListener;
    int size;

    public MomentAdapter(Activity activity, Context context, ArrayList<Object> arrayList) {
        this.activity = activity;
        this.context = context;
        this.momentArray = arrayList;
    }

    public void RefreshData(ArrayList<Object> arrayList) {
        CheckImageFound(ConstantArrayClass.albumsList);
        this.momentArray = arrayList;
        notifyDataSetChanged();
    }

    private void CheckImageFound(ArrayList<Object> arrayList) {
        if (arrayList.size() >= 1) {
            MoMomentFragment.rlPhotoLayout.setVisibility(8);
            MoMomentFragment.fragmnetimagegridView.setVisibility(0);
            return;
        }
        MoMomentFragment.fragmnetimagegridView.setVisibility(8);
        MoMomentFragment.rlPhotoLayout.setVisibility(0);
    }

    public void releaseResources() {
        this.momentArray = null;
        this.context = null;
        this.activity = null;
    }

    @Override 
    public int getItemViewType(int i) {
        try {
            if (this.momentArray.get(i) instanceof DataFileModel) {
                return 1;
            }
            return this.momentArray.get(i) instanceof String ? 0 : -1;
        } catch (IndexOutOfBoundsException unused) {
            return 1;
        }
    }

    public void FilterNewData(ArrayList<Object> arrayList) {
        this.momentArray.clear();
        this.momentArray.addAll(arrayList);
        this.activity.runOnUiThread(new Runnable() { 
            @Override 
            public void run() {
                MomentAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void RemoveItem(ArrayList<DataFileModel> arrayList) {
        this.momentArray.removeAll(arrayList);
        this.activity.runOnUiThread(new Runnable() { 
            @Override 
            public void run() {
                MomentAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void RemoveItemSingle(String str) {
        for (int i = 0; i < this.momentArray.size(); i++) {
            if ((this.momentArray.get(i) instanceof DataFileModel) && ((DataFileModel) this.momentArray.get(i)).path.contains(str)) {
                this.momentArray.remove(i);
            }
        }
        this.activity.runOnUiThread(new Runnable() { 
            @Override 
            public void run() {
                MomentAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override 
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_date_header_moment_adapter, viewGroup, false));
        }
        if (i != 1) {
            return null;
        }
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_data_moment_adapter, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        int itemViewType = myViewHolder.getItemViewType();
        if (itemViewType == 0) {
            try {
                myViewHolder.txt_date.setText((String) this.momentArray.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (itemViewType == 1) {
            myViewHolder.image_view_album_image.getLayoutParams().width = this.size;
            myViewHolder.image_view_album_image.getLayoutParams().height = this.size;
            try {
                if (((DataFileModel) this.momentArray.get(i)).isSelected) {
                    myViewHolder.view_alpha.setVisibility(0);
                } else {
                    myViewHolder.view_alpha.setVisibility(8);
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
            try {
                if (((DataFileModel) this.momentArray.get(i)).getMediaType().equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    myViewHolder.rel_play.setVisibility(0);
                } else if (((DataFileModel) this.momentArray.get(i)).getMediaType().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    myViewHolder.rel_play.setVisibility(8);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            try {
                myViewHolder.text_view_album_name.setText(String.valueOf(new File(((DataFileModel) this.momentArray.get(i)).path).getName()));
            } catch (Exception e4) {
                System.out.println(e4.getMessage());
            }
            try {
                TextView textView = myViewHolder.text_view_album_size;
                textView.setText("(" + ((DataFileModel) this.momentArray.get(i)).pathlist.size() + ")");
            } catch (Exception e5) {
                System.out.println(e5.getMessage());
            }
            try {
                if (((DataFileModel) this.momentArray.get(i)).getFolderName().equals("Take Photo")) {
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.centerCrop();
                    Glide.with(this.context).load(((DataFileModel) this.momentArray.get(i)).path).apply((BaseRequestOptions<?>) requestOptions).into(myViewHolder.image_view_album_image);
                } else {
                    Uri fromFile = Uri.fromFile(new File(((DataFileModel) this.momentArray.get(i)).path));
                    RequestOptions requestOptions2 = new RequestOptions();
                    requestOptions2.centerCrop();
                    Glide.with(this.context).load(fromFile).apply((BaseRequestOptions<?>) requestOptions2).into(myViewHolder.image_view_album_image);
                }
            } catch (Exception e6) {
                System.out.println(e6.getMessage());
            }
            myViewHolder.card_click.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View view) {
                    if (MomentAdapter.this.onClickListener != null) {
                        MomentAdapter.this.onClickListener.onClickListener(MomentAdapter.this.momentArray, Integer.valueOf(i));
                    }
                }
            });
            myViewHolder.card_click.setOnLongClickListener(new View.OnLongClickListener() { 
                @Override 
                public boolean onLongClick(View view) {
                    if (MomentAdapter.this.onClickListener != null) {
                        MomentAdapter.this.onClickListener.onLongClickListener(MomentAdapter.this.momentArray, Integer.valueOf(i));
                        return false;
                    }
                    return false;
                }
            });
        }
    }

    @Override 
    public int getItemCount() {
        ArrayList<Object> arrayList = this.momentArray;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void setItemClickCallback(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView btnMoreDetails;
        CardView card_click;
        ImageView image_view_album_image;
        ImageView img_calender;
        ImageView rel_play;
        RelativeLayout rl_date;
        TextView text_view_album_name;
        TextView text_view_album_size;
        TextView txt_date;
        RelativeLayout view_alpha;

        public MyViewHolder(View view) {
            super(view);
            this.image_view_album_image = (ImageView) view.findViewById(R.id.image_view_album_image);
            this.text_view_album_name = (TextView) view.findViewById(R.id.text_view_album_name);
            this.text_view_album_size = (TextView) view.findViewById(R.id.text_view_album_size);
            this.view_alpha = (RelativeLayout) view.findViewById(R.id.view_alpha);
            this.btnMoreDetails = (ImageView) view.findViewById(R.id.btnMoreDetails);
            this.rel_play = (ImageView) view.findViewById(R.id.rel_play);
            this.txt_date = (TextView) view.findViewById(R.id.txt_date);
            this.card_click = (CardView) view.findViewById(R.id.card_click);
            this.rl_date = (RelativeLayout) view.findViewById(R.id.rl_date);
            this.img_calender = (ImageView) view.findViewById(R.id.img_calender);
        }
    }

    public void setLayoutParams(int i) {
        this.size = i;
    }
}
