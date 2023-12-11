package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import hdphoto.galleryimages.gelleryalbum.R;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon.Collards_Parameter;


public class RiverFullEffectFragment extends Fragment {
    private static final String TAG = "FullEffectFragment";
    public static View header;
    Activity activity;
    FullBitmapReady bitmapReadyListener;
    Context context;
    Bitmap currentBitmap;
    RiverFilterEffectFragment filterEffectFragment;
    ImageView imageView;
    Bitmap sourceBitmap;
    ImageView txt_apply_filter;
    ImageView txt_cancel_filter;


    public interface FullBitmapReady {
        void onBitmapReady(Bitmap bitmap, Collards_Parameter cMParameter);

        void onCancel();
    }


    public interface HideHeaderListener {
        void hide(boolean z);
    }

    @Override 
    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_full_effect, viewGroup, false);
        this.imageView = (ImageView) inflate.findViewById(R.id.imageView1);
        header = inflate.findViewById(R.id.full_fragment_apply_filter_header);
        StringBuilder sb = new StringBuilder("imageView is null ");
        sb.append(this.imageView == null);
        Log.e(TAG, sb.toString());
        AddFragment();
        this.txt_cancel_filter = (ImageView) inflate.findViewById(R.id.button_cancel_filter);
        this.txt_apply_filter = (ImageView) inflate.findViewById(R.id.button_apply_filter);
        this.txt_cancel_filter.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                filterEffectFragment.ResetParametersWithoutChange();
                bitmapReadyListener.onCancel();
            }
        });
        this.txt_apply_filter.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFullEffectFragment.2
            @Override 
            public void onClick(View view) {
                if (currentBitmap == null) {
                    filterEffectFragment.ResetParametersWithoutChange();
                    bitmapReadyListener.onCancel();
                    return;
                }
                Collards_Parameter cMParameter = new Collards_Parameter(filterEffectFragment.parameterGlobal);
                filterEffectFragment.ResetParametersWithoutChange();
                bitmapReadyListener.onBitmapReady(currentBitmap, cMParameter);
            }
        });
        return inflate;
    }

    @Override 
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = getActivity();
        this.activity = getActivity();
    }

    @Override 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.imageView.setImageBitmap(this.sourceBitmap);
    }

    public void setBitmapWithParameter(Bitmap bitmap, Collards_Parameter cMParameter) {
        this.sourceBitmap = bitmap;
        ImageView imageView = this.imageView;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        RiverFilterEffectFragment cMFilterEffectFragment = this.filterEffectFragment;
        if (cMFilterEffectFragment != null) {
            if (cMParameter == null || cMFilterEffectFragment.parameterGlobal == null || cMParameter.getId() != this.filterEffectFragment.parameterGlobal.id) {
                this.filterEffectFragment.setBitmapAndResetBlur(this.sourceBitmap);
            } else {
                this.filterEffectFragment.setBitmap(this.sourceBitmap);
            }
            if (cMParameter != null) {
                this.filterEffectFragment.setParameters(cMParameter);
            }
        }
    }

    public void AddFragment() {
        if (this.filterEffectFragment == null) {
            RiverFilterEffectFragment cMFilterEffectFragment = (RiverFilterEffectFragment) getChildFragmentManager().findFragmentByTag("MY_FRAGMENT");
            this.filterEffectFragment = cMFilterEffectFragment;
            if (cMFilterEffectFragment == null) {
                RiverFilterEffectFragment cMFilterEffectFragment2 = new RiverFilterEffectFragment();
                this.filterEffectFragment = cMFilterEffectFragment2;
                cMFilterEffectFragment2.setArguments(getArguments());
                getChildFragmentManager().beginTransaction().add(R.id.fragment_container, this.filterEffectFragment, "MY_FRAGMENT").commit();
            }
            getChildFragmentManager().beginTransaction().show(this.filterEffectFragment).commit();
            this.filterEffectFragment.setBitmapReadyListener(new RiverFilterEffectFragment.BitmapReady() {
                @Override
                public void onBitmapReady(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                    currentBitmap = bitmap;
                }
            });
            this.filterEffectFragment.setHideHeaderListener(new HideHeaderListener() {
                @Override
                public void hide(boolean z) {
                    if (z) {
                        RiverFullEffectFragment.header.setVisibility(0);
                    } else {
                        RiverFullEffectFragment.header.setVisibility(4);
                    }
                }
            });
        }
    }

    public void setFullBitmapReadyListener(FullBitmapReady fullBitmapReady) {
        this.bitmapReadyListener = fullBitmapReady;
    }
}
