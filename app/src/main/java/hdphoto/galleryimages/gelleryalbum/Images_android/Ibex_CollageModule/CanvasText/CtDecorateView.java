package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.HiPhi_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.Impala_MirrorActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Duff_CollageActivity;


public class CtDecorateView extends View {
    public static final String TAG = "DecorateView";
    Dialog deleteTextDialog;
    public OnDecorateViewTouchUp onDecorateViewTouchUpListener;


    public interface OnDecorateViewTouchUp {
        void onTouchUp(BaseData cMBaseData);
    }

    public float containsScore(float f, float f2) {
        return -2.0f;
    }

    public BaseData getData() {
        return null;
    }

    public boolean isDecorateViewSelected() {
        return false;
    }

    public boolean isOnRectCheck(float f, float f2) {
        return false;
    }

    public void onDestroy() {
    }

    public void setDecorateViewSelected(boolean z) {
    }

    public void setMatrix(CtMyMatrix cMMyMatrix) {
    }

    public CtDecorateView(Context context) {
        super(context);
    }

    public CtDecorateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CtDecorateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnDecorateViewTouchUp(OnDecorateViewTouchUp onDecorateViewTouchUp) {
        this.onDecorateViewTouchUpListener = onDecorateViewTouchUp;
    }

    public void DeleteTextDialog(Context context, final View view) {
        Dialog dialog = new Dialog(context, R.style.ThemeWithCorners1);
        this.deleteTextDialog = dialog;
        dialog.requestWindowFeature(1);
        this.deleteTextDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.deleteTextDialog.setContentView(R.layout.dg_delete_text);
        this.deleteTextDialog.setTitle("Delete Text");
        this.deleteTextDialog.setCancelable(false);
        this.deleteTextDialog.setCanceledOnTouchOutside(false);
        ((RelativeLayout) this.deleteTextDialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView.1
            @Override 
            public void onClick(View view2) {
                CtDecorateView.this.DeleteView(view);
                Duff_CollageActivity.checkTextApply = false;
                if (!HiPhi_MainActivity.fromActivity.equals("")) {
                    if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                        Duff_CollageActivity.rl_toolbar.setVisibility(0);
                        CustomRelativeLayout.rl_FontApply.setVisibility(8);
                    } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                        Impala_MirrorActivity.rl_toolbar.setVisibility(0);
                        CustomRelativeLayout.rl_FontApply.setVisibility(8);
                    }
                }
                CtDecorateView.this.deleteTextDialog.dismiss();
            }
        });
        ((RelativeLayout) this.deleteTextDialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMDecorateView.2
            @Override 
            public void onClick(View view2) {
                CtDecorateView.this.deleteTextDialog.dismiss();
            }
        });
        this.deleteTextDialog.show();
    }

    public void DeleteView(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
            onDestroy();
        }
    }
}
