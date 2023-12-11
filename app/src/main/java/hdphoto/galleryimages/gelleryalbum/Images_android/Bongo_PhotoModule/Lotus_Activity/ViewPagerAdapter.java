package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.SingleListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import cz.msebera.android.httpclient.HttpStatus;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;


public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<Object> allDataList;
    Context context;
    private SingleListener<Boolean, Integer, Integer> singleListener;

    @Override
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ViewPagerAdapter(Context context, ArrayList<Object> arrayList) {
        this.context = context;
        this.allDataList = arrayList;
    }

    @Override
    public int getCount() {
        return this.allDataList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.layout_viewpager_adapter, viewGroup, false);
        final GestureImageView gestureImageView = (GestureImageView) inflate.findViewById(R.id.imageView);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.dummyImg);
        String extension = FilenameUtils.getExtension(new File(((DataFileModel) this.allDataList.get(i)).path).getName());
        if (!extension.equals("gif") && !extension.equals("GIF")) {
            new RequestOptions().override(HttpStatus.SC_MULTIPLE_CHOICES, HttpStatus.SC_MULTIPLE_CHOICES);
            Glide.with(this.context).asBitmap().load(((DataFileModel) this.allDataList.get(i)).path).into(new SimpleTarget<Bitmap>() {

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    gestureImageView.setImageBitmap(bitmap);
                    imageView.setVisibility(8);
                }
            });
            gestureImageView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMViewPagerAdapter.2
                @Override
                public void onClick(View view) {
                    try {
                        ViewPagerAdapter.this.singleListener.onSingleCallback(true, Integer.valueOf(i), Integer.valueOf(i), view);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            });
            viewGroup.addView(inflate);
            return inflate;
        }
        new RequestOptions().override(HttpStatus.SC_MULTIPLE_CHOICES, HttpStatus.SC_MULTIPLE_CHOICES);
        Glide.with(this.context).asBitmap().load(((DataFileModel) this.allDataList.get(i)).path).into(new SimpleTarget<Bitmap>() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMViewPagerAdapter.3
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                gestureImageView.setImageBitmap(bitmap);
                imageView.setVisibility(8);
            }
        });

        gestureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ViewPagerAdapter.this.singleListener.onSingleCallback(true, Integer.valueOf(i), Integer.valueOf(i), view);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
        viewGroup.addView(inflate);
        return inflate;
    }

    public void setItemClickCallback(SingleListener singleListener) {
        this.singleListener = singleListener;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((LinearLayout) obj);
    }
}
