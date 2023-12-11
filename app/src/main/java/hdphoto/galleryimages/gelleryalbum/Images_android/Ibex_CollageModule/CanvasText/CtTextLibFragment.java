package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments.RiverFontFragment;


public class CtTextLibFragment extends Fragment {
    Activity activity;
    View containerBgColor;
    CtFontAdapter customGridAdapter;
    CtMyEditText edtText;
    GridView gvBgColors;
    View gvColorContainer;
    GridView gvColors;
    GridView gvFont;
    ImageView imgAlign;
    ImageView imgBgColor;
    ImageView imgColor;
    ImageView imgFont;
    ImageView imgKeyboard;
    ImageView imgOk;
    TextStyledListener textStyledListener;
    CtTextData txtDataLocal;
    String TAG = "TextLibFragment";
    int TOOL_MODE_BG_COLOR = 3;
    int TOOL_MODE_COLOR = 2;
    int TOOL_MODE_FONT = 1;
    int TOOL_MODE_KEYBOARD = 0;
    int alignCount = 0;
    View.OnClickListener myOnClickListener = new EdtFunctionListener();
    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SBChangeListener();
    View[] toolBtnList = new View[4];

    
    public interface TextStyledListener {
        void onCancel();

        void onOk(CtTextData cMTextData);
    }

    
    class FontItemListener implements AdapterView.OnItemClickListener {
        FontItemListener() {
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Typeface typeFace = CtFontCache.getTypeFace(CtTextLibFragment.this.activity, CtTextLibHelper.fontPathList[i]);
            if (typeFace != null) {
                CtTextLibFragment.this.edtText.setTypeface(typeFace);
            }
            CtTextLibFragment.this.txtDataLocal.setTextFont(CtTextLibHelper.fontPathList[i], CtTextLibFragment.this.activity);
        }
    }


    class TextChangeListener implements TextWatcher {
        @Override 
        public void afterTextChanged(Editable editable) {
        }

        @Override 
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        TextChangeListener() {
        }

        @Override 
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            CtTextLibFragment.this.txtDataLocal.message = charSequence.toString().compareToIgnoreCase("") != 0 ? charSequence.toString() : CtTextData.defaultMessage;
        }
    }


    class TextTouchListener implements View.OnTouchListener {
        TextTouchListener() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (1 != motionEvent.getAction()) {
                return false;
            }
            CtTextLibFragment cMTextLibFragment = CtTextLibFragment.this;
            cMTextLibFragment.SetVisibilityMode(cMTextLibFragment.TOOL_MODE_KEYBOARD);
            return false;
        }
    }


    class BgColorListener implements AdapterView.OnItemClickListener {
        BgColorListener() {
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CtTextLibFragment.this.txtDataLocal.setBackgroundColor(((Integer) CtTextLibFragment.this.gvBgColors.getItemAtPosition(i)).intValue());
            CtTextLibFragment.this.edtText.setBackgroundColor(CtTextLibFragment.this.txtDataLocal.getBackgroundColorFinal());
        }
    }


    class ColorListener implements AdapterView.OnItemClickListener {
        ColorListener() {
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CtTextLibFragment.this.edtText.setTextColor(CtTextLibFragment.this.txtDataLocal.setTextColor(((Integer) CtTextLibFragment.this.gvColors.getItemAtPosition(i)).intValue()));
        }
    }

    
    class EdtFunctionListener implements View.OnClickListener {
        EdtFunctionListener() {
        }

        @Override 
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.text_lib_font) {
                ((InputMethodManager) CtTextLibFragment.this.activity.getSystemService("input_method")).hideSoftInputFromWindow(CtTextLibFragment.this.edtText.getWindowToken(), 0);
                CtTextLibFragment cMTextLibFragment = CtTextLibFragment.this;
                cMTextLibFragment.SetVisibilityMode(cMTextLibFragment.TOOL_MODE_FONT);
            } else if (id == R.id.text_lib_keyboard) {
                CtTextLibFragment cMTextLibFragment2 = CtTextLibFragment.this;
                cMTextLibFragment2.SetVisibilityMode(cMTextLibFragment2.TOOL_MODE_KEYBOARD);
                ((InputMethodManager) CtTextLibFragment.this.activity.getSystemService("input_method")).toggleSoftInput(2, 1);
            } else if (id == R.id.text_lib_color) {
                ((InputMethodManager) CtTextLibFragment.this.activity.getSystemService("input_method")).hideSoftInputFromWindow(CtTextLibFragment.this.edtText.getWindowToken(), 0);
                CtTextLibFragment cMTextLibFragment3 = CtTextLibFragment.this;
                cMTextLibFragment3.SetVisibilityMode(cMTextLibFragment3.TOOL_MODE_COLOR);
            } else if (id == R.id.text_lib_bg_color) {
                ((InputMethodManager) CtTextLibFragment.this.activity.getSystemService("input_method")).hideSoftInputFromWindow(CtTextLibFragment.this.edtText.getWindowToken(), 0);
                CtTextLibFragment cMTextLibFragment4 = CtTextLibFragment.this;
                cMTextLibFragment4.SetVisibilityMode(cMTextLibFragment4.TOOL_MODE_BG_COLOR);
            } else if (id == R.id.text_lib_align) {
                CtTextLibFragment.this.alignCount++;
                CtTextLibFragment cMTextLibFragment5 = CtTextLibFragment.this;
                cMTextLibFragment5.SetAlignmentState(cMTextLibFragment5.alignCount % 3);
            } else if (id == R.id.text_lib_ok) {
                String str = CtTextLibFragment.this.txtDataLocal.message;
                if (str.compareToIgnoreCase(CtTextData.defaultMessage) == 0 || str.length() == 0) {
                    if (CtTextLibFragment.this.activity == null) {
                        CtTextLibFragment cMTextLibFragment6 = CtTextLibFragment.this;
                        cMTextLibFragment6.activity = cMTextLibFragment6.getActivity();
                    }
                    Toast makeText = Toast.makeText(CtTextLibFragment.this.activity, CtTextLibFragment.this.getString(R.string.canvas_text_enter_text), 0);
                    makeText.setGravity(17, makeText.getXOffset() / 2, makeText.getYOffset() / 2);
                    makeText.show();
                    return;
                }
                if (str.length() == 0) {
                    CtTextLibFragment.this.txtDataLocal.message = CtTextData.defaultMessage;
                } else {
                    CtTextLibFragment.this.txtDataLocal.message = str;
                }
                if (CtTextLibFragment.this.textStyledListener != null) {
                    CtTextLibFragment.this.textStyledListener.onOk(CtTextLibFragment.this.txtDataLocal);
                }
                ((InputMethodManager) CtTextLibFragment.this.activity.getSystemService("input_method")).hideSoftInputFromWindow(CtTextLibFragment.this.edtText.getWindowToken(), 0);
            }
        }
    }

    
    class SBChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override 
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override 
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        SBChangeListener() {
        }

        @Override 
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int id = seekBar.getId();
            if (id == R.id.seekbar_text_color_opacity) {
                if (i < 0 || i > 255) {
                    return;
                }
                CtTextLibFragment.this.txtDataLocal.textPaint.setAlpha(i);
                CtTextLibFragment.this.edtText.setTextColor(CtTextLibFragment.this.txtDataLocal.textPaint.getColor());
            } else if (id != R.id.seekbar_text_background_color_opacity || i < 0 || i > 255) {
            } else {
                CtTextLibFragment.this.txtDataLocal.setBackgroundAlpha(i);
                CtTextLibFragment.this.edtText.setBackgroundColor(CtTextLibFragment.this.txtDataLocal.getBackgroundColorFinal());
            }
        }
    }

    public void setTextStyledListener(TextStyledListener textStyledListener) {
        this.textStyledListener = textStyledListener;
    }

    public static CtTextLibFragment newInstance(String str, String str2) {
        CtTextLibFragment cMTextLibFragment = new CtTextLibFragment();
        cMTextLibFragment.setArguments(new Bundle());
        return cMTextLibFragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Log.e(this.TAG, "text fragment onActivityCreated");
        if (isVisible()) {
            ((InputMethodManager) this.activity.getSystemService("input_method")).toggleSoftInput(2, 1);
            ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(this.edtText, 1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.edtText.requestFocus();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_canvas_text, viewGroup, false);
        this.activity = getActivity();
        SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.seekbar_text_color_opacity);
        seekBar.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
        SeekBar seekBar2 = (SeekBar) inflate.findViewById(R.id.seekbar_text_background_color_opacity);
        seekBar2.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
        this.imgKeyboard = (ImageView) inflate.findViewById(R.id.text_lib_keyboard);
        this.imgFont = (ImageView) inflate.findViewById(R.id.text_lib_font);
        this.imgColor = (ImageView) inflate.findViewById(R.id.text_lib_color);
        this.imgBgColor = (ImageView) inflate.findViewById(R.id.text_lib_bg_color);
        this.imgAlign = (ImageView) inflate.findViewById(R.id.text_lib_align);
        this.imgOk = (ImageView) inflate.findViewById(R.id.text_lib_ok);
        View[] viewArr = this.toolBtnList;
        ImageView imageView = this.imgKeyboard;
        viewArr[0] = imageView;
        viewArr[1] = this.imgFont;
        viewArr[2] = this.imgColor;
        viewArr[3] = this.imgBgColor;
        imageView.setOnClickListener(this.myOnClickListener);
        this.imgFont.setOnClickListener(this.myOnClickListener);
        this.imgColor.setOnClickListener(this.myOnClickListener);
        this.imgBgColor.setOnClickListener(this.myOnClickListener);
        this.imgAlign.setOnClickListener(this.myOnClickListener);
        this.imgOk.setOnClickListener(this.myOnClickListener);
        CtMyEditText cMMyEditText = (CtMyEditText) inflate.findViewById(R.id.text_lib_edittext);
        this.edtText = cMMyEditText;
        cMMyEditText.setInputType(cMMyEditText.getInputType() | 524288 | 176);
        Bundle arguments = getArguments();
        if (arguments != null) {
            CtTextData cMTextData = (CtTextData) arguments.getSerializable(RiverFontFragment.TEXT_DATA_BUNDLE_KEY);
            if (cMTextData == null) {
                CtTextData cMTextData2 = new CtTextData(this.activity.getResources().getDimension(R.dimen.myFontSize));
                Rect rect = new Rect();
                cMTextData2.textPaint.getTextBounds(CtTextData.defaultMessage, 0, 12, rect);
                cMTextData2.xPos = (getResources().getDisplayMetrics().widthPixels / 2.0f) - (rect.width() / 2);
                cMTextData2.yPos = getResources().getDisplayMetrics().heightPixels / 3.0f;
                this.edtText.setText("");
                CtTextData cMTextData3 = new CtTextData();
                this.txtDataLocal = cMTextData3;
                cMTextData3.set(cMTextData2);
            } else {
                CtTextData cMTextData4 = new CtTextData();
                this.txtDataLocal = cMTextData4;
                cMTextData4.set(cMTextData);
                if (!this.txtDataLocal.message.equals(CtTextData.defaultMessage)) {
                    this.edtText.setText(this.txtDataLocal.message, TextView.BufferType.EDITABLE);
                }
                SetAlignmentState(CtMyPaint.alignmentValue(this.txtDataLocal.textPaint));
                Log.e(this.TAG, this.txtDataLocal.message);
                this.edtText.setTextColor(this.txtDataLocal.textPaint.getColor());
                this.edtText.setText(this.txtDataLocal.message);
                if (this.txtDataLocal.getFontPath() != null) {
                    CtTextData cMTextData5 = this.txtDataLocal;
                    cMTextData5.setTextFont(cMTextData5.getFontPath(), this.activity);
                    Typeface typeFace = CtFontCache.getTypeFace(this.activity, this.txtDataLocal.getFontPath());
                    if (typeFace != null) {
                        this.edtText.setTypeface(typeFace);
                    }
                }
                seekBar2.setProgress(this.txtDataLocal.getBackgroundAlpha());
                seekBar.setProgress(this.txtDataLocal.textPaint.getAlpha());
                this.edtText.setBackgroundColor(this.txtDataLocal.getBackgroundColorFinal());
            }
        }
        this.gvColorContainer = inflate.findViewById(R.id.gridViewColorContainer);
        this.containerBgColor = inflate.findViewById(R.id.gridViewBackgroundColorContainer);
        this.gvFont = (GridView) inflate.findViewById(R.id.gridview_font);
        CtFontAdapter cMFontAdapter = new CtFontAdapter(this.activity, R.layout.row_grid, CtTextLibHelper.fontPathList);
        this.customGridAdapter = cMFontAdapter;
        this.gvFont.setAdapter((ListAdapter) cMFontAdapter);
        this.gvFont.setOnItemClickListener(new FontItemListener());
        final RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.text_lib_container);
        final RelativeLayout relativeLayout2 = (RelativeLayout) inflate.findViewById(R.id.text_lib_main_layout);
        relativeLayout2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMTextLibFragment.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int height = relativeLayout2.getHeight();
                Rect rect2 = new Rect();
                relativeLayout2.getWindowVisibleDisplayFrame(rect2);
                int i = height - (rect2.bottom - rect2.top);
                if (i <= 150 || relativeLayout.getLayoutParams().height == i) {
                    return;
                }
                relativeLayout.getLayoutParams().height = i;
                relativeLayout.requestLayout();
            }
        });
        this.edtText.requestFocus();
        this.edtText.addTextChangedListener(new TextChangeListener());
        this.edtText.setFocusableInTouchMode(true);
        this.edtText.setOnTouchListener(new TextTouchListener());
        ((InputMethodManager) this.activity.getSystemService("input_method")).showSoftInput(this.edtText, 0);
        GridView gridView = (GridView) inflate.findViewById(R.id.gridViewBackgroundColor);
        this.gvBgColors = gridView;
        gridView.setAdapter((ListAdapter) new CtRainbowPickerAdapter(this.activity, CtTextData.bgColorSentinel));
        this.gvBgColors.setOnItemClickListener(new BgColorListener());
        GridView gridView2 = (GridView) inflate.findViewById(R.id.gridViewColor);
        this.gvColors = gridView2;
        gridView2.setAdapter((ListAdapter) new CtRainbowPickerAdapter(this.activity, -1));
        this.gvColors.setOnItemClickListener(new ColorListener());
        return inflate;
    }

    void SetToolbarButtonBg(int i) {
        View[] viewArr = this.toolBtnList;
        if (viewArr != null) {
            for (View view : viewArr) {
                view.setBackgroundResource(R.drawable.text_lib_toolbar_button_selector);
            }
            this.toolBtnList[i].setBackgroundResource(R.color.text_lib_toolbar_button_bg_pressed);
        }
    }

    @Override 
    public void onDestroy() {
        CtFontAdapter cMFontAdapter = this.customGridAdapter;
        if (cMFontAdapter != null) {
            if (cMFontAdapter.typeFaceArray != null) {
                int size = this.customGridAdapter.typeFaceArray.size();
                for (int i = 0; i < size; i++) {
                    this.customGridAdapter.typeFaceArray.set(i, null);
                }
            }
            this.customGridAdapter.typeFaceArray = null;
        }
        super.onDestroy();
    }

    @Override 
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override 
    public void onDetach() {
        super.onDetach();
    }

    void SetVisibilityMode(int i) {
        if (i == this.TOOL_MODE_KEYBOARD) {
            this.gvFont.setVisibility(8);
            this.gvColorContainer.setVisibility(8);
            this.containerBgColor.setVisibility(8);
            SetToolbarButtonBg(this.TOOL_MODE_KEYBOARD);
        } else if (i == this.TOOL_MODE_FONT) {
            this.gvFont.setVisibility(0);
            this.gvColorContainer.setVisibility(8);
            this.containerBgColor.setVisibility(8);
            SetToolbarButtonBg(this.TOOL_MODE_FONT);
        } else if (i == this.TOOL_MODE_COLOR) {
            this.gvFont.setVisibility(8);
            this.gvColorContainer.setVisibility(0);
            this.containerBgColor.setVisibility(8);
            SetToolbarButtonBg(this.TOOL_MODE_COLOR);
        } else if (i == this.TOOL_MODE_BG_COLOR) {
            this.gvFont.setVisibility(8);
            this.gvColorContainer.setVisibility(8);
            this.containerBgColor.setVisibility(0);
            SetToolbarButtonBg(this.TOOL_MODE_BG_COLOR);
        }
    }

    void SetAlignmentState(int i) {
        int i2;
        int i3;
        this.alignCount = i;
        Paint.Align align = Paint.Align.LEFT;
        if (i == 1) {
            align = Paint.Align.CENTER;
            i2 = 17;
            i3 = R.drawable.ic_text_lib_align_center;
        } else {
            i2 = 3;
            i3 = R.drawable.ic_text_lib_align_left;
        }
        if (i == 2) {
            align = Paint.Align.RIGHT;
            i2 = 5;
            i3 = R.drawable.ic_text_lib_align_right;
        }
        this.edtText.setGravity(i2);
        this.txtDataLocal.textPaint.setTextAlign(align);
        this.imgAlign.setImageResource(i3);
    }
}
