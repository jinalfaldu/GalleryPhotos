package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.HiPhi_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.Impala_MirrorActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtFontCache;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtGridViewAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtShadowData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText.CtTextData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Duff_CollageActivity;


public class RiverFontFragment extends Fragment {
    static final String TAG = "FontFragment";
    public static final String TEXT_DATA_BUNDLE_KEY = "text_data";
    public static OnFontSelectedListener onFontSelectedListener;
    CtGridViewAdapter customGridAdapter;
    EditText editText;
    String[] fontPathList;
    CtTextData textData;
    TextView textView;

    
    public interface OnFontSelectedListener {
        void onFontSelected(CtTextData cMTextData);

        void onFontSelectionCancelled();
    }

    public static void showBackgroundColorPicker(DialogInterface dialogInterface, int i) {
    }

    public static void showFontColorPicker(DialogInterface dialogInterface, int i) {
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Typeface typeFace;
        View inflate = layoutInflater.inflate(R.layout.fragment_font_edit, viewGroup, false);
        if (getArguments() != null) {
            this.textData = (CtTextData) getArguments().getSerializable(TEXT_DATA_BUNDLE_KEY);
        }
        this.fontPathList = new String[]{"fonts/Abang.ttf", "fonts/ahundredmiles.ttf", "fonts/Alaska.ttf", "fonts/Ambisi.ttf", "fonts/Axon-Bold.ttf", "fonts/Axon-Light.ttf", "fonts/Axon-Regular.ttf", "fonts/Axon-UltraLight.ttf", "fonts/Bezaen.ttf", "fonts/Binz.ttf", "fonts/Blunt.ttf", "fonts/Brush Action.ttf", "fonts/CaviarDreams.ttf", "fonts/digiclock.ttf", "fonts/Febiolla Script.ttf", "fonts/FreeUniversal-Bold.ttf", "fonts/GoodDog.otf", "fonts/gtw.ttf", "fonts/HandTest.ttf", "fonts/Hellofreeday.otf", "fonts/Hellofreeday Outline.otf", "fonts/Hibernate.ttf", "fonts/Hughoney.ttf", "fonts/Jester.ttf", "fonts/Junction 02.otf", "fonts/Katheryna Regular.ttf", "fonts/Kerape Font.ttf"};
        TextView textView = (TextView) inflate.findViewById(R.id.tv_font_preview);
        this.textView = textView;
        textView.setPaintFlags(textView.getPaintFlags() | 128);
        this.textView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.1
            @Override 
            public final void onClick(View view) {
                RiverFontFragment.this.FontPreviewClick(view);
            }
        });
        EditText editText = (EditText) inflate.findViewById(R.id.et_font_preview);
        this.editText = editText;
        editText.setInputType(editText.getInputType() | 524288 | 176);
        this.editText.addTextChangedListener(new TextWatcher() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.2
            @Override 
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override 
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().compareToIgnoreCase("") != 0) {
                    RiverFontFragment.this.textView.setText(charSequence.toString());
                } else {
                    RiverFontFragment.this.textView.setText(CtTextData.defaultMessage);
                }
                RiverFontFragment.this.editText.setSelection(RiverFontFragment.this.editText.getText().length());
            }

            @Override 
            public void afterTextChanged(Editable editable) {
                RiverFontFragment.this.editText.setSelection(RiverFontFragment.this.editText.getText().length());
            }
        });
        this.editText.setFocusableInTouchMode(true);
        CtTextData cMTextData = this.textData;
        if (cMTextData == null) {
            this.textData = new CtTextData(getResources().getDimension(R.dimen.myFontSize));
            Rect rect = new Rect();
            this.textData.textPaint.getTextBounds(CtTextData.defaultMessage, 0, 12, rect);
            this.textData.xPos = (getResources().getDisplayMetrics().widthPixels / 2.0f) - (rect.width() / 2);
            this.textData.yPos = getResources().getDisplayMetrics().heightPixels / 3.0f;
            Log.e(TAG, "textData==null");
            this.editText.setText("");
            this.textView.setText(getString(R.string.preview_text));
        } else {
            if (!cMTextData.message.equals(CtTextData.defaultMessage)) {
                this.editText.setText(this.textData.message, TextView.BufferType.EDITABLE);
            }
            Log.e(TAG, this.textData.message);
            this.textView.setTextColor(this.textData.textPaint.getColor());
            this.textView.setText(this.textData.message);
            if (this.textData.getFontPath() != null && (typeFace = CtFontCache.getTypeFace(getContext(), this.textData.getFontPath())) != null) {
                this.textView.setTypeface(typeFace);
            }
        }
        GridView gridView = (GridView) inflate.findViewById(R.id.gridview_font);
        CtGridViewAdapter cMGridViewAdapter = new CtGridViewAdapter(getContext(), R.layout.row_grid, this.fontPathList);
        this.customGridAdapter = cMGridViewAdapter;
        gridView.setAdapter((ListAdapter) cMGridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.3
            @Override
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                RiverFontFragment.this.onFontTextSet(adapterView, view, i, j);
            }
        });
        inflate.findViewById(R.id.iv_font_color).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.4
            @Override 
            public final void onClick(View view) {
                RiverFontFragment.this.onFontColor(view);
            }
        });
        inflate.findViewById(R.id.iv_background_color).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.5
            @Override 
            public final void onClick(View view) {
                RiverFontFragment.this.onBackgroundColor(view);
            }
        });
        inflate.findViewById(R.id.iv_text_shadow).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.6
            @Override 
            public final void onClick(View view) {
                RiverFontFragment.this.onTextShadow(view);
            }
        });
        inflate.findViewById(R.id.tv_done).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.7
            @Override 
            public final void onClick(View view) {
                RiverFontFragment.this.onSaveFont(view);
            }
        });
        inflate.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.8
            @Override 
            public final void onClick(View view) {
                RiverFontFragment.this.onCancelFont(view);
            }
        });
        return inflate;
    }

    public void FontPreviewClick(View view) {
        onFontPreviewClicked();
    }

    public void onFontTextSet(AdapterView adapterView, View view, int i, long j) {
        Typeface typeFace = CtFontCache.getTypeFace(getContext(), this.fontPathList[i]);
        if (typeFace != null) {
            this.textView.setTypeface(typeFace);
        }
        this.textData.setTextFont(this.fontPathList[i], getContext());
    }

    public void onFontColor(View view) {
        showFontColorPicker();
    }

    public void onBackgroundColor(View view) {
        showBackgroundColorPicker();
    }

    public void onTextShadow(View view) {
        if (this.textData.getShadowData().getRadius() != 0.0f) {
            this.textData.setShadowData(new CtShadowData(0.0f, 0.0f, 0.0f, -16777216));
        } else {
            this.textData.setShadowData(new CtShadowData(7.0f, 7.0f, 7.0f, -16777216));
        }
        this.textView.setShadowLayer(this.textData.getShadowData().getRadius(), this.textData.getShadowData().getDx(), this.textData.getShadowData().getDy(), this.textData.getShadowData().getColor());
    }

    @Override 
    public void onAttach(Context context) {
        super.onAttach(context);
        onFontSelectedListener = (OnFontSelectedListener) context;
    }

    private void onFontPreviewClicked() {
        this.editText.requestFocusFromTouch();
        ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this.editText, 0);
        String charSequence = this.textView.getText().toString();
        if (charSequence.compareToIgnoreCase(CtTextData.defaultMessage) != 0) {
            this.editText.setText(charSequence);
            EditText editText = this.editText;
            editText.setSelection(editText.getText().length());
        } else {
            this.editText.setText("");
        }
        new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.9
            @Override 
            public final void run() {
                RiverFontFragment.this.FontPreviewClick();
            }
        }, 200L);
    }

    public void FontPreviewClick() {
        this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
        this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
        EditText editText = this.editText;
        editText.setSelection(editText.getText().length());
    }

    private void showFontColorPicker() {
        if (getActivity() != null) {
            ColorPickerDialogBuilder.with(getActivity()).setTitle(getString(R.string.choose_font_color)).initialColor(this.textView.getCurrentTextColor()).wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setPositiveButton("Ok", new ColorPickerClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.10
                @Override // com.flask.colorpicker.builder.ColorPickerClickListener
                public final void onClick(DialogInterface dialogInterface, int i, Integer[] numArr) {
                    RiverFontFragment.this.showFontColorPicker(dialogInterface, i, numArr);
                }
            }).setNegativeButton(getString(R.string.cancel_btn), RiverFontFragmentFontColor.INSTANCE).build().show();
        }
    }

    public void showFontColorPicker(DialogInterface dialogInterface, int i, Integer[] numArr) {
        this.textView.setTextColor(i);
        this.textData.textPaint.setColor(i);
    }

    private void showBackgroundColorPicker() {
        if (getActivity() != null) {
            ColorPickerDialogBuilder.with(getActivity()).setTitle(getString(R.string.choose_background_color)).initialColor(R.color.black_tras).wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setPositiveButton("Ok", new ColorPickerClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMFragments.CMFontFragment.11
                @Override // com.flask.colorpicker.builder.ColorPickerClickListener
                public final void onClick(DialogInterface dialogInterface, int i, Integer[] numArr) {
                    RiverFontFragment.this.showBackgroundColorPicker(dialogInterface, i, numArr);
                }
            }).setNegativeButton(getString(R.string.cancel_btn), RiverFontFragmentBackgroundColor.INSTANCE).showColorEdit(true).setColorEditTextColor(ContextCompat.getColor(getActivity(), 17170459)).build().show();
        }
    }

    public void showBackgroundColorPicker(DialogInterface dialogInterface, int i, Integer[] numArr) {
        this.textView.setBackgroundColor(i);
        this.textData.setTextBackgroundColor(i);
    }

    public void onSaveFont(View view) {
        onDoneButtonClicked();
    }

    public void onCancelFont(View view) {
        Duff_CollageActivity.checkTextFragment = false;
        Duff_CollageActivity.checkTextApply = false;
        onFontSelectedListener.onFontSelectionCancelled();
        if (getActivity() != null) {
            if (!HiPhi_MainActivity.fromActivity.equals("")) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                    Duff_CollageActivity.rl_toolbar.setVisibility(0);
                    Duff_CollageActivity.rl_bottom.setVisibility(0);
                } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                    Impala_MirrorActivity.rl_toolbar.setVisibility(0);
                    Impala_MirrorActivity.rl_bottom.setVisibility(0);
                }
            }
            getActivity().onBackPressed();
        }
    }

    private void onDoneButtonClicked() {
        Log.e("===", "onDoneButtonClicked: ");
        Duff_CollageActivity.checkTextFragment = false;
        Duff_CollageActivity.checkTextApply = true;
        Log.d("TYTYTYTY", " --- " + Duff_CollageActivity.checkTextFragment);
        if (!HiPhi_MainActivity.fromActivity.equals("")) {
            if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                Duff_CollageActivity.rl_toolbar.setVisibility(8);
                Duff_CollageActivity.rl_bottom.setVisibility(0);
            } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                Impala_MirrorActivity.rl_toolbar.setVisibility(8);
                Impala_MirrorActivity.rl_bottom.setVisibility(0);
            }
        }
        String charSequence = this.textView.getText().toString();
        if (charSequence.compareToIgnoreCase(CtTextData.defaultMessage) == 0 || charSequence.length() == 0) {
            Toast makeText = Toast.makeText(getContext(), "Please Enter Text", 0);
            makeText.setGravity(17, makeText.getXOffset() / 2, makeText.getYOffset() / 2);
            makeText.show();
            return;
        }
        this.textData.message = charSequence;
        this.editText.setText("");
        this.textView.setText("");
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.editText.getWindowToken(), 0);
        OnFontSelectedListener onFontSelectedListener2 = onFontSelectedListener;
        if (onFontSelectedListener2 != null) {
            onFontSelectedListener2.onFontSelected(this.textData);
        }
    }

    @Override 
    public void onDestroy() {
        CtGridViewAdapter cMGridViewAdapter = this.customGridAdapter;
        if (cMGridViewAdapter != null) {
            if (cMGridViewAdapter.typeFaceArray != null) {
                int length = this.customGridAdapter.typeFaceArray.length;
                for (int i = 0; i < length; i++) {
                    this.customGridAdapter.typeFaceArray[i] = null;
                }
            }
            this.customGridAdapter.typeFaceArray = null;
        }
        super.onDestroy();
    }
}
