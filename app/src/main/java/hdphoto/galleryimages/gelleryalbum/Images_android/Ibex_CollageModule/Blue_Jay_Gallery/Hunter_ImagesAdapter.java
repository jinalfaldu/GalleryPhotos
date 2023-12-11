package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import hdphoto.galleryimages.gelleryalbum.R;

import java.util.Collections;
import java.util.List;


public class Hunter_ImagesAdapter extends RecyclerView.Adapter<Hunter_ImagesAdapter.AlbumViewHolder> {
    List<Declan_Album.SubImage> items;
    OnImageSelectedListener onImageSelectedListener;


    public interface OnImageSelectedListener {
        void onImageSelected(Declan_Album.SubImage subImage);
    }

    public Hunter_ImagesAdapter(List<Declan_Album.SubImage> list) {
        this.items = list;
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_images_adapter, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, int i) {
        albumViewHolder.bind(this.items.get(i), this.onImageSelectedListener);
    }

    @Override 
    public int getItemCount() {
        return this.items.size();
    }

    public void setItems(List<Declan_Album.SubImage> list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        this.items = list;
        notifyDataSetChanged();
    }

    public void setOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
        this.onImageSelectedListener = onImageSelectedListener;
    }


    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbImage;

        public AlbumViewHolder(View view) {
            super(view);
            this.imgThumbImage = (ImageView) view.findViewById(R.id.thumb_image);
        }

        public void bind(final Declan_Album.SubImage subImage, final OnImageSelectedListener onImageSelectedListener) {
            ImageView imageView = this.imgThumbImage;
            imageView.setImageBitmap(Jose_GalleryUtility.GetThumbBitmap(imageView.getContext(), subImage.id, 0));
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override 
                public void onClick(View view) {
                    onImageSelectedListener.onImageSelected(subImage);
                }
            });
        }
    }
}
