package hdphoto.galleryimages.gelleryalbum.Images_android.Sticker;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import java.io.IOException;
import java.util.ArrayList;


public class OverlayAdapter extends RecyclerView.Adapter<OverlayAdapter.MyViewHolder> {
    Activity activity;
    OverClick overClick;
    ArrayList<String> stickerlist;

    public OverlayAdapter(Activity activity, ArrayList<String> arrayList, OverClick overClick) {
        this.stickerlist = arrayList;
        this.overClick = overClick;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_over, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        try {
            myViewHolder.img_sticker.setImageBitmap(BitmapFactory.decodeStream(this.activity.getAssets().open(String.valueOf(this.stickerlist.get(i)))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myViewHolder.img_sticker.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                OverlayAdapter.this.overClick.overclick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.stickerlist.size();
    }

    
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_sticker;

        public MyViewHolder(View view) {
            super(view);
            this.img_sticker = (ImageView) view.findViewById(R.id.img_sticker);
        }
    }
}
