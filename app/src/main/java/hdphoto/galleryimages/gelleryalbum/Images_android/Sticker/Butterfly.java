package hdphoto.galleryimages.gelleryalbum.Images_android.Sticker;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Duff_CollageActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Butterfly extends Fragment implements OverClick {
    private DemoStickerView.OnTouchSticker onTouchSticker = new DemoStickerView.OnTouchSticker() {
        @Override
        public void onTouchedSticker() {
            Butterfly.this.removeBorder();
        }
    };
    ArrayList<String> stickerlist;
    RecyclerView stickerlistview;

    @Override 
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        fillstickerdata("butterfly");
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().getApplicationContext();
        View inflate = layoutInflater.inflate(R.layout.frag_main, viewGroup, false);
        this.stickerlistview = (RecyclerView) inflate.findViewById(R.id.stickerlistview);
        return inflate;
    }

    private void fillstickerdata(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.stickerlist = arrayList;
        arrayList.clear();
        try {
            String[] list = getResources().getAssets().list(str);
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    ArrayList<String> arrayList2 = this.stickerlist;
                    arrayList2.add(str + "/" + list[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stickerlistview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), 1, false));
        this.stickerlistview.setLayoutManager(new GridLayoutManager(getContext(), 6));
        this.stickerlistview.setItemAnimator(new DefaultItemAnimator());
        this.stickerlistview.setAdapter(new OverlayAdapter(getActivity(), this.stickerlist, this));
    }

    private void addsticker(int i) {
        Duff_CollageActivity.stickerImageView = new StickerImageView(getActivity(), this.onTouchSticker);
        try {
            Duff_CollageActivity.stickerImageView.setImageBitmap(BitmapFactory.decodeStream(getActivity().getAssets().open(String.valueOf(this.stickerlist.get(i)))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Duff_CollageActivity.view_id = new Random().nextInt();
        if (Duff_CollageActivity.view_id < 0) {
            Duff_CollageActivity.view_id -= Duff_CollageActivity.view_id * 2;
        }
        Duff_CollageActivity.stickerImageView.setId(Duff_CollageActivity.view_id);
        Duff_CollageActivity.stickerviewId.add(Integer.valueOf(Duff_CollageActivity.view_id));
        Duff_CollageActivity.stickerImageView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Duff_CollageActivity.stickerImageView.setControlItemsHidden(false);
            }
        });
        Duff_CollageActivity.rl_mainFull.addView(Duff_CollageActivity.stickerImageView);
    }

    public void removeBorder() {
        for (int i = 0; i < Duff_CollageActivity.stickerviewId.size(); i++) {
            View findViewById = Duff_CollageActivity.rl_mainFull.findViewById(Duff_CollageActivity.stickerviewId.get(i).intValue());
            if (findViewById instanceof StickerImageView) {
                ((StickerImageView) findViewById).setControlItemsHidden(true);
            }
        }
    }

    @Override
    public void overclick(int i) {
        addsticker(i);
    }
}
