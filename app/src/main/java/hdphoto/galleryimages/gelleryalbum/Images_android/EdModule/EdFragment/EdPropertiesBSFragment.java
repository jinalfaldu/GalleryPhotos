package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdAdapter.EdColorPickerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class EdPropertiesBSFragment extends BottomSheetDialogFragment implements SeekBar.OnSeekBarChangeListener {
    private Properties gProperties;


    public interface Properties {
        void onColorChanged(int i);

        void onOpacityChanged(int i);

        void onShapeSizeChanged(int i);
    }

    @Override 
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override 
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.dialog_em_fragment_brush_opacity_bs, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvColors);
        ((SeekBar) view.findViewById(R.id.sbOpacity)).setOnSeekBarChangeListener(this);
        ((SeekBar) view.findViewById(R.id.sbSize)).setOnSeekBarChangeListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        recyclerView.setHasFixedSize(true);
        EdColorPickerAdapter eMColorPickerAdapter = new EdColorPickerAdapter(getActivity());
        eMColorPickerAdapter.setOnColorPickerClickListener(new EdColorPickerAdapter.OnColorPickerClickListener() {
            @Override
            public void onColorPickerClickListener(int i) {
                if (EdPropertiesBSFragment.this.gProperties != null) {
                    EdPropertiesBSFragment.this.dismiss();
                    EdPropertiesBSFragment.this.gProperties.onColorChanged(i);
                }
            }
        });
        recyclerView.setAdapter(eMColorPickerAdapter);
    }

    public void setPropertiesChangeListener(Properties properties) {
        this.gProperties = properties;
    }

    @Override 
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        switch (seekBar.getId()) {
            case R.id.sbOpacity:
                Properties properties = this.gProperties;
                if (properties != null) {
                    properties.onOpacityChanged(i);
                    return;
                }
                return;
            case R.id.sbSize:
                Properties properties2 = this.gProperties;
                if (properties2 != null) {
                    properties2.onShapeSizeChanged(i);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
