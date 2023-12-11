package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;


public class HoHomeFragment extends BaseFragment {
    Fragment activeFragment;
    Activity activity;
    Context context;
    FragmentManager fragmentManager;
    ImImageFragment imageFragment;
    View view1, view2, view3;
    TextView txt_Home, txt_Status, txt_Collage;
    MoMomentFragment momentFragment;
    RelativeLayout rl_Image;
    RelativeLayout rl_Moment;
    RelativeLayout rl_Video;
    ViVideoFragment videoFragment;
    View view;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_gm_home, viewGroup, false);
        this.context = getContext();
        this.activity = getActivity();
        ID_Binding(this.view);
        return this.view;
    }

    private void ID_Binding(View view) {
        this.rl_Moment = view.findViewById(R.id.rl_Moment);
        this.rl_Image = view.findViewById(R.id.rl_Image);
        this.rl_Video = view.findViewById(R.id.rl_Video);
        this.view1 = view.findViewById(R.id.view1);
        this.view2 = view.findViewById(R.id.view2);
        this.view3 = view.findViewById(R.id.view3);
        this.txt_Home = view.findViewById(R.id.txt_Home);
        this.txt_Status = view.findViewById(R.id.txt_Status);
        this.txt_Collage = view.findViewById(R.id.txt_Collage);
        this.momentFragment = new MoMomentFragment();
        this.imageFragment = new ImImageFragment();
        this.videoFragment = new ViVideoFragment();
        FragmentManager childFragmentManager = getChildFragmentManager();
        this.fragmentManager = childFragmentManager;
        childFragmentManager.beginTransaction().add(R.id.flContent, this.videoFragment, ExifInterface.GPS_MEASUREMENT_3D).hide(this.videoFragment).commitAllowingStateLoss();
        this.fragmentManager.beginTransaction().add(R.id.flContent, this.imageFragment, ExifInterface.GPS_MEASUREMENT_2D).hide(this.imageFragment).commitAllowingStateLoss();
        this.fragmentManager.beginTransaction().add(R.id.flContent, this.momentFragment, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).commitAllowingStateLoss();
        this.activeFragment = this.momentFragment;
        this.fragmentManager.beginTransaction().hide(this.activeFragment).show(this.momentFragment).commitAllowingStateLoss();
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager != null && !fragmentManager.isStateSaved()) {
            this.fragmentManager.popBackStackImmediate();
        }
        this.rl_Moment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Degu_MainActivity.DeleteCache(HoHomeFragment.this.context);
                Degu_MainActivity.addMomentAlbum.setVisibility(0);
                Degu_MainActivity.rl_imageBtn.setVisibility(8);
                Degu_MainActivity.rl_videoBtn.setVisibility(8);
                HoHomeFragment.this.view1.setVisibility(View.VISIBLE);
                HoHomeFragment.this.view2.setVisibility(View.GONE);
                HoHomeFragment.this.view3.setVisibility(View.GONE);
                txt_Home.setTextColor(getResources().getColor(R.color.tab_txt));
                txt_Status.setTextColor(getResources().getColor(R.color.gray_73));
                txt_Collage.setTextColor(getResources().getColor(R.color.gray_73));
                HoHomeFragment.this.fragmentManager.beginTransaction().hide(HoHomeFragment.this.activeFragment).show(HoHomeFragment.this.momentFragment).commitAllowingStateLoss();
                HoHomeFragment.this.fragmentManager.popBackStackImmediate();
                HoHomeFragment gMHomeFragment = HoHomeFragment.this;
                gMHomeFragment.activeFragment = gMHomeFragment.momentFragment;
            }
        });
        this.rl_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Degu_MainActivity.DeleteCache(HoHomeFragment.this.context);
                Degu_MainActivity.addMomentAlbum.setVisibility(8);
                Degu_MainActivity.rl_imageBtn.setVisibility(0);
                Degu_MainActivity.rl_videoBtn.setVisibility(8);
                txt_Home.setTextColor(getResources().getColor(R.color.gray_73));
                txt_Status.setTextColor(getResources().getColor(R.color.tab_txt));
                txt_Collage.setTextColor(getResources().getColor(R.color.gray_73));
                HoHomeFragment.this.view1.setVisibility(View.GONE);
                HoHomeFragment.this.view2.setVisibility(View.VISIBLE);
                HoHomeFragment.this.view3.setVisibility(View.GONE);
                Degu_MainActivity.fragclick = 0;
                HoHomeFragment.this.fragmentManager.beginTransaction().hide(HoHomeFragment.this.activeFragment).show(HoHomeFragment.this.imageFragment).commitAllowingStateLoss();
                HoHomeFragment.this.fragmentManager.popBackStackImmediate();
                HoHomeFragment gMHomeFragment = HoHomeFragment.this;
                gMHomeFragment.activeFragment = gMHomeFragment.imageFragment;
            }
        });
        this.rl_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Degu_MainActivity.DeleteCache(HoHomeFragment.this.context);
                Degu_MainActivity.addMomentAlbum.setVisibility(8);
                Degu_MainActivity.rl_imageBtn.setVisibility(8);
                Degu_MainActivity.rl_videoBtn.setVisibility(0);
                txt_Home.setTextColor(getResources().getColor(R.color.gray_73));
                txt_Status.setTextColor(getResources().getColor(R.color.gray_73));
                txt_Collage.setTextColor(getResources().getColor(R.color.tab_txt));
                HoHomeFragment.this.view1.setVisibility(View.GONE);
                HoHomeFragment.this.view2.setVisibility(View.GONE);
                HoHomeFragment.this.view3.setVisibility(View.VISIBLE);
                Degu_MainActivity.fragclick = 1;
                HoHomeFragment.this.fragmentManager.beginTransaction().hide(HoHomeFragment.this.activeFragment).show(HoHomeFragment.this.videoFragment).commitAllowingStateLoss();
                HoHomeFragment.this.fragmentManager.popBackStackImmediate();
                HoHomeFragment gMHomeFragment = HoHomeFragment.this;
                gMHomeFragment.activeFragment = gMHomeFragment.videoFragment;
            }
        });
    }
}