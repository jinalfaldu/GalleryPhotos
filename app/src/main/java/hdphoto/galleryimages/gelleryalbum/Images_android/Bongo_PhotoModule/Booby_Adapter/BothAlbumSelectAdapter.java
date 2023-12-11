package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;

import java.util.ArrayList;


public class BothAlbumSelectAdapter extends BaseAdapter implements Filterable {
    Activity activity;
    ArrayList<Object> arrayList;
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Object> mStringFilterList;
    OnClickListener<ArrayList<Object>, Integer, View> onClickListener;
    int size;
    Typeface typeface;
    ValueFilter valueFilter;

    @Override 
    public long getItemId(int i) {
        return i;
    }

    public BothAlbumSelectAdapter(Activity activity, Context context, ArrayList<Object> arrayList) {
        this.arrayList = arrayList;
        this.mStringFilterList = arrayList;
        this.context = context;
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override 
    public int getCount() {
        ArrayList<Object> arrayList = this.arrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override 
    public Object getItem(int i) {
        return this.arrayList.get(i);
    }

    @Override 
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.activity).inflate(R.layout.layout_inner_both_album_select_adapter, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.image_view_image_select);
            viewHolder.cardimg =  view.findViewById(R.id.cardimg);
            viewHolder.rl_view = (RelativeLayout) view.findViewById(R.id.view_alpha);
            viewHolder.rl_play = (RelativeLayout) view.findViewById(R.id.rel_play);
            viewHolder.txt_size = (TextView) view.findViewById(R.id.txt_size);
            viewHolder.ll_size = (LinearLayout) view.findViewById(R.id.lin_size);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.getLayoutParams().width = this.size;
        viewHolder.imageView.getLayoutParams().height = this.size;
        viewHolder.rl_view.getLayoutParams().width = this.size;
        viewHolder.rl_view.getLayoutParams().height = this.size;
        if (Common.strplay.equals("InnerVideoSelectActivity")) {
            viewHolder.rl_play.setVisibility(0);
            viewHolder.ll_size.setVisibility(8);
        }
        try {
            if (((DataFileModel) this.arrayList.get(i)).isSelected) {
                viewHolder.rl_view.setVisibility(0);
            } else {
                viewHolder.rl_view.setVisibility(8);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.centerCrop();
            Glide.with(this.context).load(((DataFileModel) this.arrayList.get(i)).path).apply((BaseRequestOptions<?>) requestOptions).into(viewHolder.imageView);
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
        if (((this.arrayList.size() <= 0 || ((DataFileModel) this.arrayList.get(i)).duration == 0) ? 0L : ((DataFileModel) this.arrayList.get(i)).duration) != 0) {
            String milliSecToTimer = milliSecToTimer(((DataFileModel) this.arrayList.get(i)).duration);
            TextView textView = viewHolder.txt_size;
            textView.setText(milliSecToTimer + "");
        }
        viewHolder.rl_play.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                AdInterGD.getInstance().showInter(activity, new AdInterGD.MyCallback() {
                    @Override
                    public void callbackCall() {
                        try {
                            BothAlbumSelectAdapter.this.onClickListener.onClickListener(BothAlbumSelectAdapter.this.arrayList, Integer.valueOf(i));
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                });
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                AdInterGD.getInstance().showInter(activity, new AdInterGD.MyCallback() {
                    @Override
                    public void callbackCall() {
                        try {
                            BothAlbumSelectAdapter.this.onClickListener.onClickListener(BothAlbumSelectAdapter.this.arrayList, Integer.valueOf(i));
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                });
            }
        });
        viewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view2) {
                return false;
            }
        });
        return view;
    }

    public static String milliSecToTimer(long j) {
        String str;
        String str2;
        String str3;
        int i = (int) (j / 3600000);
        long j2 = j % 3600000;
        int i2 = ((int) j2) / 60000;
        int i3 = (int) ((j2 % DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS) / 1000);
        if (i != 0) {
            str = i + " ";
        } else {
            str = "";
        }
        if (i2 < 10) {
            str2 = "0" + i2;
        } else {
            str2 = "" + i2;
        }
        if (i3 < 10) {
            str3 = "0" + i3;
        } else {
            str3 = "" + i3;
        }
        return str + str2 + " " + str3;
    }

    public void setItemClickCallback(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.valueFilter == null) {
            this.valueFilter = new ValueFilter();
        }
        return this.valueFilter;
    }

    
    private static class ViewHolder {
        public ImageView imageView;
        public CardView cardimg;
        public LinearLayout ll_size;
        public RelativeLayout rl_play;
        public RelativeLayout rl_view;
        public TextView txt_size;

        private ViewHolder() {
        }
    }

    public void setLayoutParams(int i) {
        this.size = i;
    }

    public void releaseResources() {
        this.arrayList = null;
        this.context = null;
        this.activity = null;
    }

    
    public class ValueFilter extends Filter {
        private ValueFilter() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (charSequence == null || charSequence.length() <= 0) {
                filterResults.count = BothAlbumSelectAdapter.this.mStringFilterList.size();
                filterResults.values = BothAlbumSelectAdapter.this.mStringFilterList;
            } else {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < BothAlbumSelectAdapter.this.mStringFilterList.size(); i++) {
                    if (((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).name != null && ((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).name.toUpperCase().contains(charSequence.toString().toUpperCase())) {
                        arrayList.add(new DataFileModel(((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).id, ((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).name, ((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).path, ((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).oldpath, ((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).directory, ((DataFileModel) BothAlbumSelectAdapter.this.mStringFilterList.get(i)).isSelected));
                    }
                }
                filterResults.count = arrayList.size();
                filterResults.values = arrayList;
            }
            return filterResults;
        }

        @Override
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            BothAlbumSelectAdapter.this.arrayList = (ArrayList) filterResults.values;
            BothAlbumSelectAdapter.this.notifyDataSetChanged();
        }
    }

    public void removeitem(ArrayList<DataFileModel> arrayList) {
        this.arrayList.removeAll(arrayList);
        this.activity.runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                BothAlbumSelectAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void removeitemsingle(String str) {
        for (int i = 0; i < this.arrayList.size(); i++) {
            if ((this.arrayList.get(i) instanceof DataFileModel) && ((DataFileModel) this.arrayList.get(i)).path.contains(str)) {
                this.arrayList.remove(i);
            }
        }
        this.activity.runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                BothAlbumSelectAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void refreshData(ArrayList<Object> arrayList) {
        this.arrayList = arrayList;
        this.activity.runOnUiThread(new Runnable() {
            @Override 
            public void run() {
                BothAlbumSelectAdapter.this.notifyDataSetChanged();
            }
        });
    }
}
