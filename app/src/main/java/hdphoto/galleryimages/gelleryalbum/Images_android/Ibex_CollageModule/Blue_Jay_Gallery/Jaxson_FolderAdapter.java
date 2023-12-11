package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import java.util.Collections;
import java.util.List;


public class Jaxson_FolderAdapter extends RecyclerView.Adapter<Jaxson_FolderAdapter.AlbumViewHolder> {
    private List<Declan_Album> items;
    private OnAlbumSelectedListener onAlbumSelectedListener;
    private OnAlbumSelectedInternalListener onAlbumSelectedInternalListener = new OnAlbumSelectedInternalListener() {
        @Override
        public void onAlbumSelected(Declan_Album cMAlbum, int i) {
            Jaxson_FolderAdapter.this.selection = i;
            if (Jaxson_FolderAdapter.this.onAlbumSelectedListener != null) {
                Jaxson_FolderAdapter.this.onAlbumSelectedListener.onAlbumSelected(cMAlbum);
            }
            Jaxson_FolderAdapter.this.notifyDataSetChanged();
        }
    };
    private int selection = -1;

    
    public interface OnAlbumSelectedInternalListener {
        void onAlbumSelected(Declan_Album cMAlbum, int i);
    }

    
    public interface OnAlbumSelectedListener {
        void onAlbumSelected(Declan_Album cMAlbum);
    }

    public Jaxson_FolderAdapter(List<Declan_Album> list) {
        this.items = list;
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_item, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, int i) {
        albumViewHolder.bind(this.items.get(i), i, i == this.selection, this.onAlbumSelectedInternalListener);
        if (this.selection == -1) {
            albumViewHolder.itemView.post(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMFolderAdapter.2
                @Override 
                public void run() {
                    Jaxson_FolderAdapter.this.onAlbumSelectedInternalListener.onAlbumSelected((Declan_Album) Jaxson_FolderAdapter.this.items.get(0), 0);
                }
            });
        }
    }

    @Override 
    public int getItemCount() {
        return this.items.size();
    }

    public void setItems(List<Declan_Album> list) {
        this.items = list == null ? Collections.emptyList() : list;
        if (this.selection >= list.size()) {
            this.selection = -1;
        }
        notifyDataSetChanged();
    }

    public void setOnAlbumSelectedListener(OnAlbumSelectedListener onAlbumSelectedListener) {
        this.onAlbumSelectedListener = onAlbumSelectedListener;
    }

    
    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbImage;
        TextView txtAlbumName;
        View vvSelectionFrame;

        public AlbumViewHolder(View view) {
            super(view);
            this.imgThumbImage = (ImageView) view.findViewById(R.id.thumb_image);
            this.txtAlbumName = (TextView) view.findViewById(R.id.album_name);
            this.vvSelectionFrame = view.findViewById(R.id.selection_frame);
        }

        public void bind(final Declan_Album cMAlbum, final int i, boolean z, final OnAlbumSelectedInternalListener onAlbumSelectedInternalListener) {
            ImageView imageView = this.imgThumbImage;
            imageView.setImageBitmap(Jose_GalleryUtility.GetThumbBitmap(imageView.getContext(), cMAlbum.imageIdForThumb, 0));
            this.vvSelectionFrame.setVisibility(z ? View.VISIBLE : View.GONE);
            TextView textView = this.txtAlbumName;
            textView.setText(cMAlbum.name + String.format("(%s)", Integer.valueOf(cMAlbum.subImages.size())));
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMFolderAdapter.AlbumViewHolder.1
                @Override 
                public void onClick(View view) {
                    onAlbumSelectedInternalListener.onAlbumSelected(cMAlbum, i);
                }
            });
        }
    }
}
