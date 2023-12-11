package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;


public class CtMyEditText extends AppCompatEditText {
    Context context;

    public CtMyEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    @Override
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            ((InputMethodManager) this.context.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        }
        return false;
    }
}
