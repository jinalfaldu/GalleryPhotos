package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdAdapter.EdColorPickerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;



public class EdShapeBSFragment extends BottomSheetDialogFragment implements SeekBar.OnSeekBarChangeListener {
    Properties gProperties;


    public interface Properties {
        void onColorChanged(int i);

        void onOpacityChanged(int i);

//        void onShapePicked(ShapeType shapeType);

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
        return layoutInflater.inflate(R.layout.dg_fragment_shapes_bs, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.shapeColors);
        ((RadioGroup) view.findViewById(R.id.shapeRadioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EdShapeBSFragment.this.lambda$onViewCreated$0$EMShapeBSFragment(radioGroup, i);
            }
        });
        ((SeekBar) view.findViewById(R.id.shapeOpacity)).setOnSeekBarChangeListener(this);
        ((SeekBar) view.findViewById(R.id.shapeSize)).setOnSeekBarChangeListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        recyclerView.setHasFixedSize(true);
        EdColorPickerAdapter eMColorPickerAdapter = new EdColorPickerAdapter(getActivity());
        eMColorPickerAdapter.setOnColorPickerClickListener(new EdColorPickerAdapter.OnColorPickerClickListener() {
            @Override
            public final void onColorPickerClickListener(int i) {
                EdShapeBSFragment.this.lambda$onViewCreated$1$EMShapeBSFragment(i);
            }
        });
        recyclerView.setAdapter(eMColorPickerAdapter);
    }

    public void lambda$onViewCreated$0$EMShapeBSFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.lineRadioButton) {
//            this.gProperties.onShapePicked(ShapeType.LINE);
        } else if (i == R.id.ovalRadioButton) {
//            this.gProperties.onShapePicked(ShapeType.OVAL);
        } else if (i == R.id.rectRadioButton) {
//            this.gProperties.onShapePicked(ShapeType.RECTANGLE);
        } else {
//            this.gProperties.onShapePicked(ShapeType.BRUSH);
        }
    }

    public void lambda$onViewCreated$1$EMShapeBSFragment(int i) {
        if (this.gProperties != null) {
            dismiss();
            this.gProperties.onColorChanged(i);
        }
    }

    public void setPropertiesChangeListener(Properties properties) {
        this.gProperties = properties;
    }

    @Override 
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        Properties properties;
        int id = seekBar.getId();
        if (id == R.id.shapeOpacity) {
            Properties properties2 = this.gProperties;
            if (properties2 != null) {
                properties2.onOpacityChanged(i);
            }
        } else if (id != R.id.shapeSize || (properties = this.gProperties) == null) {
        } else {
            properties.onShapeSizeChanged(i);
        }
    }
}
