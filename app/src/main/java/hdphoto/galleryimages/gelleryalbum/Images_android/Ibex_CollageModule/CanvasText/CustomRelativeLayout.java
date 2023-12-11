package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.HiPhi_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.Impala_MirrorActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Duff_CollageActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon.OnItemSelected;
import java.util.ArrayList;


public class CustomRelativeLayout extends RelativeLayout implements OnItemSelected, View.OnClickListener {
    static final String TAG = "CustomRelativeLayout";
    public static ApplyTextInterface applyListener;
    public static RelativeLayout rl_FontApply;
    Context context;
    int currentCanvasTextIndex;
    ArrayList<CtCustomTextView> customTextViewList;
    RelativeLayout.LayoutParams levelParams;
    RelativeLayout mainLayout;
    public View.OnClickListener onClickListener;
    Bitmap removeBitmap;
    RemoveTextListener removeTextListener;
    Bitmap scaleBitmap;
    SingleTap singleTapListener;
    Bitmap textBlackBar;
    ArrayList<CtTextData> textDataList;
    ArrayList<CtTextData> textDataListOriginal;
    Bitmap textEditBitmap;
    Bitmap textSwitchBitmap;
    ViewSelectedListener viewSelectedListner;


    public interface RemoveTextListener {
        void onRemove();
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLibCommon.OnItemSelected
    public void itemSelected(int i) {
        if (this.customTextViewList.isEmpty()) {
            return;
        }
        this.customTextViewList.get(this.currentCanvasTextIndex).SetTextColor(i);
    }

    @Override 
    public void onClick(View view) {
        HideKeyboard((Activity) this.context);
        int id = view.getId();
        int i = 0;
        if (id == R.id.button_apply_action) {
            Duff_CollageActivity.checkTextApply = false;
            if (!HiPhi_MainActivity.fromActivity.equals("")) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                    Duff_CollageActivity.rl_toolbar.setVisibility(0);
                } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                    Impala_MirrorActivity.rl_toolbar.setVisibility(0);
                }
            }
            while (i < this.textDataList.size()) {
                if (this.textDataList.get(i).message.compareTo(CtTextData.defaultMessage) == 0) {
                    this.textDataList.remove(i);
                    i--;
                }
                i++;
            }
            applyListener.onOk(this.textDataList);
        } else if (id == R.id.button_cancel_action) {
            Duff_CollageActivity.checkTextApply = false;
            if (!HiPhi_MainActivity.fromActivity.equals("")) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                    Duff_CollageActivity.rl_toolbar.setVisibility(0);
                } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                    Impala_MirrorActivity.rl_toolbar.setVisibility(0);
                }
            }
            this.textDataList.clear();
            this.textDataList.addAll(this.textDataListOriginal);
            applyListener.onCancel();
        } else if (id != R.id.txt_lib_ok) {
            if (id == R.id.txt_lib_cancel) {
                Duff_CollageActivity.checkTextApply = false;
                if (!HiPhi_MainActivity.fromActivity.equals("")) {
                    if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                        Duff_CollageActivity.rl_toolbar.setVisibility(0);
                    } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                        Impala_MirrorActivity.rl_toolbar.setVisibility(0);
                    }
                }
                this.textDataList.clear();
                this.textDataList.addAll(this.textDataListOriginal);
                applyListener.onCancel();
            }
        } else {
            Duff_CollageActivity.checkTextApply = false;
            if (!HiPhi_MainActivity.fromActivity.equals("")) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                    Duff_CollageActivity.rl_toolbar.setVisibility(0);
                } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                    Impala_MirrorActivity.rl_toolbar.setVisibility(0);
                }
            }
            while (i < this.textDataList.size()) {
                if (this.textDataList.get(i).message.compareTo(CtTextData.defaultMessage) == 0) {
                    this.textDataList.remove(i);
                    i--;
                }
                i++;
            }
            applyListener.onOk(this.textDataList);
        }
    }


    public class RemoveText implements RemoveTextListener {
        RemoveText() {
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMCustomRelativeLayout.RemoveTextListener
        public void onRemove() {
            if (CustomRelativeLayout.this.customTextViewList.isEmpty()) {
                return;
            }
            CtCustomTextView cMCustomTextView = CustomRelativeLayout.this.customTextViewList.get(CustomRelativeLayout.this.currentCanvasTextIndex);
            Duff_CollageActivity.mainLayout.removeView(cMCustomTextView);
            CustomRelativeLayout.this.customTextViewList.remove(cMCustomTextView);
            CustomRelativeLayout.this.textDataList.remove(cMCustomTextView.textData);
            CustomRelativeLayout cMCustomRelativeLayout = CustomRelativeLayout.this;
            cMCustomRelativeLayout.currentCanvasTextIndex = cMCustomRelativeLayout.customTextViewList.size() - 1;
            if (CustomRelativeLayout.this.customTextViewList.isEmpty()) {
                return;
            }
            CustomRelativeLayout.this.customTextViewList.get(CustomRelativeLayout.this.currentCanvasTextIndex).setTextSelected(true);
        }
    }


    public class ViewSelector implements ViewSelectedListener {
        ViewSelector() {
        }

        @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.ViewSelectedListener
        public void setSelectedView(CtCustomTextView cMCustomTextView) {
            CustomRelativeLayout cMCustomRelativeLayout = CustomRelativeLayout.this;
            cMCustomRelativeLayout.currentCanvasTextIndex = cMCustomRelativeLayout.customTextViewList.indexOf(cMCustomTextView);
            for (int i = 0; i < CustomRelativeLayout.this.customTextViewList.size(); i++) {
                if (CustomRelativeLayout.this.currentCanvasTextIndex != i) {
                    CustomRelativeLayout.this.customTextViewList.get(i).setTextSelected(false);
                }
            }
        }
    }

    public void LoadBitmaps() {
        Bitmap bitmap = this.removeBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            this.removeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_delete);
        }
        Bitmap bitmap2 = this.scaleBitmap;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            this.scaleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_resize);
        }
    }

    public CustomRelativeLayout(Context context, ArrayList<CtTextData> arrayList, Matrix matrix, SingleTap singleTap) {
        super(context);
        this.removeTextListener = new RemoveText();
        this.viewSelectedListner = new ViewSelector();
        this.textEditBitmap = null;
        this.textSwitchBitmap = null;
        this.textBlackBar = null;
        this.mainLayout = (RelativeLayout) findViewById(R.id.canvas_relative_layout);
        this.customTextViewList = new ArrayList<>();
        this.textDataList = new ArrayList<>();
        this.textDataListOriginal = new ArrayList<>();
        this.currentCanvasTextIndex = 0;
        this.context = context;
        this.singleTapListener = singleTap;
        LoadBitmaps();
        ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.activity_custom_layout, this);
        rl_FontApply = (RelativeLayout) findViewById(R.id.rl_FontApply);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.levelParams = layoutParams;
        layoutParams.addRule(15, -1);
        this.levelParams.addRule(14, -1);
        for (int i = 0; i < arrayList.size(); i++) {
            this.textDataListOriginal.add(new CtTextData(arrayList.get(i)));
            this.textDataList.add(new CtTextData(arrayList.get(i)));
        }
        for (int i2 = 0; i2 < this.textDataList.size(); i2++) {
            CtCustomTextView cMCustomTextView = new CtCustomTextView(this.context, this.textDataList.get(i2), this.removeBitmap, this.scaleBitmap, this.textEditBitmap, this.textSwitchBitmap, this.textBlackBar);
            cMCustomTextView.setSingleTapListener(this.singleTapListener);
            cMCustomTextView.setViewSelectedListener(this.viewSelectedListner);
            cMCustomTextView.setRemoveTextListener(new RemoveText());
            Duff_CollageActivity.mainLayout.addView(cMCustomTextView, this.levelParams);
            this.customTextViewList.add(cMCustomTextView);
            CtMyMatrix cMMyMatrix = new CtMyMatrix();
            cMMyMatrix.set(this.textDataList.get(i2).imageSaveMatrix);
            cMMyMatrix.postConcat(matrix);
            cMCustomTextView.setMatrix(cMMyMatrix);
        }
        if (!this.customTextViewList.isEmpty()) {
            ArrayList<CtCustomTextView> arrayList2 = this.customTextViewList;
            arrayList2.get(arrayList2.size() - 1).setTextSelected(true);
            this.currentCanvasTextIndex = this.customTextViewList.size() - 1;
        }
        ((ImageView) findViewById(R.id.button_apply_action)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.button_cancel_action)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.txt_lib_ok)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.txt_lib_cancel)).setOnClickListener(this);
    }

    public void setSingleTapListener(SingleTap singleTap) {
        this.singleTapListener = singleTap;
    }

    public void setApplyTextListener(ApplyTextInterface applyTextInterface) {
        applyListener = applyTextInterface;
    }

    public void addTextView(CtTextData cMTextData) {
        if (this.textDataList.contains(cMTextData)) {
            Log.e(TAG, "textDataList.contains(textData)");
            this.customTextViewList.get(this.currentCanvasTextIndex).SetNewTextData(cMTextData);
            return;
        }
        for (int i = 0; i < this.customTextViewList.size(); i++) {
            this.customTextViewList.get(i).setTextSelected(false);
        }
        this.currentCanvasTextIndex = this.customTextViewList.size();
        LoadBitmaps();
        CtCustomTextView cMCustomTextView = new CtCustomTextView(this.context, cMTextData, this.removeBitmap, this.scaleBitmap, this.textEditBitmap, this.textSwitchBitmap, this.textBlackBar);
        Log.e("===", "addTextView: " + cMCustomTextView);
        cMCustomTextView.setSingleTapListener(this.singleTapListener);
        cMCustomTextView.setViewSelectedListener(this.viewSelectedListner);
        cMCustomTextView.setRemoveTextListener(new RemoveText());
        this.customTextViewList.add(cMCustomTextView);
        Duff_CollageActivity.mainLayout.addView(cMCustomTextView);
        this.textDataList.add(cMCustomTextView.textData);
        cMCustomTextView.setTextSelected(true);
        cMCustomTextView.SingleTapped();
    }

    public void addTextDataEx(CtTextData cMTextData) {
        if (this.textDataList.contains(cMTextData)) {
            Log.e(TAG, "textDataList.contains(textData)");
            this.customTextViewList.get(this.currentCanvasTextIndex).SetNewTextData(cMTextData);
            return;
        }
        for (int i = 0; i < this.customTextViewList.size(); i++) {
            this.customTextViewList.get(i).setTextSelected(false);
        }
        this.currentCanvasTextIndex = this.customTextViewList.size();
        CtCustomTextView cMCustomTextView = new CtCustomTextView(this.context, cMTextData, this.removeBitmap, this.scaleBitmap, this.textEditBitmap, this.textSwitchBitmap, this.textBlackBar);
        cMCustomTextView.setSingleTapListener(this.singleTapListener);
        cMCustomTextView.setViewSelectedListener(this.viewSelectedListner);
        cMCustomTextView.setRemoveTextListener(new RemoveText());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        HideKeyboard((Activity) this.context);
        return true;
    }

    public void HideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager == null || activity.getCurrentFocus() == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
