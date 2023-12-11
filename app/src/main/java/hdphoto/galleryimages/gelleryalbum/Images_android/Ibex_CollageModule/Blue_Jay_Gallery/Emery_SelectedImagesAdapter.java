package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;

import java.util.Collections;
import java.util.List;


public class Emery_SelectedImagesAdapter extends RecyclerView.Adapter<Emery_SelectedImagesAdapter.AlbumViewHolder> {
    List<Declan_Album.SubImage> items;
    ImageRemovedListener onImageRemovedListener;

    
    public interface ImageRemovedListener {
        void onImageRemoved(Declan_Album.SubImage subImage, int i);
    }

    public Emery_SelectedImagesAdapter(List<Declan_Album.SubImage> list) {
        this.items = list;
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_selected_images_adapter, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, int i) {
        albumViewHolder.bind(this.items.get(i), i, this.onImageRemovedListener);
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

    public void setOnImageRemovedListener(ImageRemovedListener onImageRemovedListener) {
        this.onImageRemovedListener = onImageRemovedListener;
    }

    
    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRemoveView;
        ImageView imgThumbImage;

        public AlbumViewHolder(View view) {
            super(view);
            this.imgThumbImage = (ImageView) view.findViewById(R.id.thumb_image);
            this.imgRemoveView = (ImageView) view.findViewById(R.id.remove);
        }

        public void bind(final Declan_Album.SubImage subImage, final int i, final ImageRemovedListener onImageRemovedListener) {
            ImageView imageView = this.imgThumbImage;
            imageView.setImageBitmap(Jose_GalleryUtility.GetThumbBitmap(imageView.getContext(), subImage.id, 0));
            this.imgRemoveView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMSelectedImagesAdapter.AlbumViewHolder.1
                @Override 
                public void onClick(View view) {
                    onImageRemovedListener.onImageRemoved(subImage, i);
                }
            });
        }
    }
}
