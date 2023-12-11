package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Booby_Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_ImageSelectActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import java.util.ArrayList;


public class ImageFolderAdapter extends BaseAdapter {
    ArrayList<Object> ImageAlbumArray;
    Activity activity;
    int size;

    @Override
    public long getItemId(int i) {
        return i;
    }

    public ImageFolderAdapter(Degu_ImageSelectActivity gMImageSelectActivity, ArrayList<Object> arrayList) {
        this.activity = gMImageSelectActivity;
        this.ImageAlbumArray = arrayList;
    }

    public ImageFolderAdapter(FragmentActivity fragmentActivity, ArrayList<Object> arrayList) {
        this.activity = fragmentActivity;
        this.ImageAlbumArray = arrayList;
    }

    @Override 
    public int getCount() {
        return this.ImageAlbumArray.size();
    }

    @Override 
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    
    public class ViewHolder {
        ImageView imgAlbumCover;

        public ViewHolder() {
        }
    }

    public void setLayoutParams(int i) {
        this.size = i;
    }

    @Override 
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.layout_image_folder_adapter, viewGroup, false);
        viewHolder.imgAlbumCover = (ImageView) inflate.findViewById(R.id.image_view_image_select);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        Glide.with(this.activity).load(((DataFileModel) this.ImageAlbumArray.get(i)).path).apply((BaseRequestOptions<?>) requestOptions).into(viewHolder.imgAlbumCover);
        viewHolder.imgAlbumCover.getLayoutParams().width = this.size;
        viewHolder.imgAlbumCover.getLayoutParams().height = this.size;
        return inflate;
    }
}
