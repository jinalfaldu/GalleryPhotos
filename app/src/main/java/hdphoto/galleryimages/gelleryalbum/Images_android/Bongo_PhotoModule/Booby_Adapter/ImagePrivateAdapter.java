package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.OnClickHideListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import java.util.ArrayList;
import java.util.List;


public class ImagePrivateAdapter extends BaseAdapter {
    public static final List<String> PHOTO_PATTERN = GetImagePattern();
    static final String TAG = "ImagePrivateAdapter";
    Activity activity;
    ArrayList<Object> arrayList;
    Context context;
    OnClickHideListener<ArrayList<Object>, Integer, Boolean, View> onClickHideListener;
    int size;

    @Override
    public long getItemId(int i) {
        return i;
    }

    public ImagePrivateAdapter(Activity activity, Context context, ArrayList<Object> arrayList) {
        this.activity = activity;
        this.context = context;
        this.arrayList = arrayList;
    }

    public void refreshData(ArrayList<Object> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
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
            view = LayoutInflater.from(this.activity).inflate(R.layout.layout_image_private_delete_adapter, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.img_hide_photo = (ImageView) view.findViewById(R.id.hide_photo_row_image);
            viewHolder.rl_hide_photo_row_image_view = (RelativeLayout) view.findViewById(R.id.view_alpha);
            viewHolder.rl_albumViewLayout = (RelativeLayout) view.findViewById(R.id.albumViewLayout);
            viewHolder.rl_option = (RelativeLayout) view.findViewById(R.id.rel_option);
            viewHolder.rl_option.setVisibility(8);
            viewHolder.txt_view_album_name = (TextView) view.findViewById(R.id.text_view_album_name);
            viewHolder.txt_view_album_size = (TextView) view.findViewById(R.id.text_view_album_size);
            viewHolder.imgMoreDetail = (ImageView) view.findViewById(R.id.btnMoreDetails);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.img_hide_photo.getLayoutParams().width = this.size;
        viewHolder.img_hide_photo.getLayoutParams().height = this.size;
        viewHolder.rl_hide_photo_row_image_view.getLayoutParams().width = this.size;
        viewHolder.rl_hide_photo_row_image_view.getLayoutParams().height = this.size;
        if (((DataFileModel) this.arrayList.get(i)).isSelected) {
            viewHolder.rl_hide_photo_row_image_view.setVisibility(0);
        } else {
            viewHolder.rl_hide_photo_row_image_view.setVisibility(8);
        }
        try {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.centerCrop();
            GetFullPath(((DataFileModel) this.arrayList.get(i)).path);
            if (CheckHideFile(((DataFileModel) this.arrayList.get(i)).path)) {
                Glide.with(this.context).load(((DataFileModel) this.arrayList.get(i)).path).apply((BaseRequestOptions<?>) requestOptions).into(viewHolder.img_hide_photo);
            } else if (CheckPhoto(((DataFileModel) this.arrayList.get(i)).path)) {
                Glide.with(this.context).load(((DataFileModel) this.arrayList.get(i)).path).apply((BaseRequestOptions<?>) requestOptions).into(viewHolder.img_hide_photo);
            }
        } catch (Exception e) {
            Log.e(TAG, "getView Size: " + e);
        }
        viewHolder.rl_option.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                if (ImagePrivateAdapter.this.onClickHideListener != null) {
                    ImagePrivateAdapter.this.onClickHideListener.onClickMoreListener(ImagePrivateAdapter.this.arrayList, Integer.valueOf(i), view2, true);
                } else {
                    Log.e(ImagePrivateAdapter.TAG, "onClick: ");
                }
            }
        });
        viewHolder.img_hide_photo.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                AdInterGD.getInstance().showInter(activity, new AdInterGD.MyCallback() {
                    @Override
                    public void callbackCall() {
                        try {
                            if (((DataFileModel) ImagePrivateAdapter.this.arrayList.get(i)).isDirectory) {
                                ImagePrivateAdapter.this.onClickHideListener.onClickListener(((DataFileModel) ImagePrivateAdapter.this.arrayList.get(i)).getSubFile(), Integer.valueOf(i), true, view2);
                            } else {
                                ImagePrivateAdapter.this.onClickHideListener.onClickListener(ImagePrivateAdapter.this.arrayList, Integer.valueOf(i), false, view2);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });

            }
        });
        viewHolder.img_hide_photo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override 
            public boolean onLongClick(View view2) {
                try {
                    if (((DataFileModel) ImagePrivateAdapter.this.arrayList.get(i)).isDirectory) {
                        ImagePrivateAdapter.this.onClickHideListener.onLongClickListener(ImagePrivateAdapter.this.arrayList, Integer.valueOf(i), true, view2);
                    } else {
                        ImagePrivateAdapter.this.onClickHideListener.onLongClickListener(ImagePrivateAdapter.this.arrayList, Integer.valueOf(i), false, view2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return false;
            }
        });
        if (((DataFileModel) this.arrayList.get(i)).isDirectory) {
            viewHolder.rl_albumViewLayout.setVisibility(0);
            TextView textView = viewHolder.txt_view_album_size;
            textView.setText("(" + ((DataFileModel) this.arrayList.get(i)).getSubFile().size() + ")");
            viewHolder.txt_view_album_name.setText(((DataFileModel) this.arrayList.get(i)).name);
        } else {
            viewHolder.rl_albumViewLayout.setVisibility(8);
        }
        viewHolder.imgMoreDetail.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                ImagePrivateAdapter.this.onClickHideListener.onClickMoreListener(ImagePrivateAdapter.this.arrayList, Integer.valueOf(i), view2, false);
            }
        });
        return view;
    }

    public static boolean CheckHideFile(String str) {
        try {
            String substring = str.substring(str.lastIndexOf(".") + 1, str.length());
            if (substring != null) {
                if (substring.equalsIgnoreCase("hf")) {
                    return true;
                }
            }
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean CheckPhoto(String str) {
        try {
            String substring = str.substring(str.lastIndexOf(".") + 1, str.length());
            if (substring == null) {
                return false;
            }
            for (String str2 : PHOTO_PATTERN) {
                if (substring.equalsIgnoreCase(str2)) {
                    return true;
                }
            }
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static List<String> GetImagePattern() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("jpeg");
        arrayList.add("jpg");
        arrayList.add("png");
        arrayList.add("gif");
        arrayList.add("bmp");
        return arrayList;
    }

    public void setItemClickCallback(OnClickHideListener onClickHideListener) {
        this.onClickHideListener = onClickHideListener;
    }


    private static class ViewHolder {
        ImageView imgMoreDetail;
        ImageView img_hide_photo;
        RelativeLayout rl_albumViewLayout;
        RelativeLayout rl_hide_photo_row_image_view;
        RelativeLayout rl_option;
        TextView txt_view_album_name;
        TextView txt_view_album_size;

        private ViewHolder() {
        }
    }

    public static String GetFullPath(String str) {
        int i;
        String substring = str.substring(5);
        StringBuilder sb = new StringBuilder("/storage");
        int i2 = 0;
        while (true) {
            if (i2 >= substring.length()) {
                i = 0;
                break;
            } else if (substring.charAt(i2) == ':') {
                i = i2 + 1;
                sb.append('/');
                break;
            } else {
                sb.append(substring.charAt(i2));
                i2++;
            }
        }
        while (i < substring.length()) {
            sb.append(substring.charAt(i));
            i++;
        }
        if (!sb.substring(9, 16).equalsIgnoreCase("primary")) {
            return sb.toString();
        }
        return sb.substring(0, 8) + "/emulated/0/" + sb.substring(17);
    }
}
