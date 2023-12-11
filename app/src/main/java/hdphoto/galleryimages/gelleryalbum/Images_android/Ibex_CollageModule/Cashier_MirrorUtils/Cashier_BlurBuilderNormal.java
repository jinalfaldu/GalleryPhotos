package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;


public class Cashier_BlurBuilderNormal {
    Bitmap inputBitmap;
    Bitmap outputBitmap;
    float BITMAP_SCALE = 0.4f;
    String TAG = "com.dev.com.gallery.utils.BlurBuilderNormal";
    int lastBlurRadius = -1;
    Paint paintBlur = new Paint(2);

    public static Bitmap createCroppedBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z) {
        Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(z);
        canvas.drawBitmap(bitmap, -i, -i2, paint);
        return createBitmap;
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i, int i2, boolean z) {
        Matrix matrix = new Matrix();
        matrix.setScale(i / bitmap.getWidth(), i2 / bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(z);
        canvas.drawBitmap(bitmap, matrix, paint);
        return createBitmap;
    }

    public Bitmap createBlurBitmapNDK(Bitmap bitmap, int i) {
        Bitmap bitmap2;
        if (i <= 2) {
            i = 2;
        }
        if (this.lastBlurRadius != i || (bitmap2 = this.outputBitmap) == null) {
            if (this.inputBitmap == null) {
                int round = Math.round(bitmap.getWidth() * this.BITMAP_SCALE);
                int round2 = Math.round(bitmap.getHeight() * this.BITMAP_SCALE);
                if (round % 2 == 1) {
                    round++;
                }
                if (round2 % 2 == 1) {
                    round2++;
                }
                if (Build.VERSION.SDK_INT < 12) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inDither = true;
                    options.inScaled = false;
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options.inPurgeable = true;
                    this.inputBitmap = createScaledBitmap(bitmap, round, round2, false);
                } else {
                    this.inputBitmap = Bitmap.createScaledBitmap(bitmap, round, round2, false);
                }
            }
            if (this.outputBitmap == null) {
                this.outputBitmap = this.inputBitmap.copy(Bitmap.Config.ARGB_8888, true);
            } else {
                new Canvas(this.outputBitmap).drawBitmap(this.inputBitmap, 0.0f, 0.0f, this.paintBlur);
            }
            this.lastBlurRadius = i;
            return this.outputBitmap;
        }
        return bitmap2;
    }

    public void destroy() {
        this.outputBitmap.recycle();
        this.outputBitmap = null;
        this.inputBitmap.recycle();
        this.inputBitmap = null;
    }

    public int getBlur() {
        return this.lastBlurRadius;
    }
}
