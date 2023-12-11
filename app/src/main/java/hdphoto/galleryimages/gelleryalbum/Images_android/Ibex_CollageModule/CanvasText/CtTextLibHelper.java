package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.CanvasText;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import hdphoto.galleryimages.gelleryalbum.R;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Fragments.RiverFontFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerData;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_TextSticker;


public class CtTextLibHelper {
    private static final String TAG = "TextLibHelper";
    public static final String[] fontPathList = {"fonts/MfStillKindaRidiculous.otf", "fonts/Binz.otf", "fonts/Blunt.otf", "fonts/CaviarDreams.otf", "fonts/digiclock.otf", "fonts/FreeUniversal-Bold.otf", "fonts/GoodDog.otf", "fonts/gtw.otf", "fonts/HandTest.otf", "fonts/Jester.otf", "fonts/Junction 02.otf", "fonts/Laine.otf", "fonts/NotCourierSans.otf", "fonts/OldFolksShuffle.otf", "fonts/OSP-DIN.otf", "fonts/otfpoc.otf", "fonts/PressStart2P.otf"};
    public static final String textFragmentTag = "myTextLibFragmentTag";
    Bitmap btmpBlackBar;
    Bitmap btmpEditBitmap;
    Bitmap btmpRemoveBitmap;
    Bitmap btmpScaleBitmap;
    Bitmap btmpSwitchBitmap;
    CtTextLibFragment txtLibFragment;
    CtTextLibFragment.TextStyledListener txtStyledListener;

    SingleTap createSingleTapListener(final FragmentActivity fragmentActivity, final ViewGroup viewGroup, final int i) {
        if (fragmentActivity == null || viewGroup == null) {
            return null;
        }
        return new SingleTap() {
            @Override
            public void onSingleTap(CtTextData cMTextData) {
                CtTextLibHelper.this.txtLibFragment = new CtTextLibFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(RiverFontFragment.TEXT_DATA_BUNDLE_KEY, cMTextData);
                CtTextLibHelper.this.txtLibFragment.setArguments(bundle);
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(i, CtTextLibHelper.this.txtLibFragment, CtTextLibHelper.textFragmentTag).commitAllowingStateLoss();
                CtTextLibHelper.this.txtLibFragment.setTextStyledListener(CtTextLibHelper.this.createFragmentListener(fragmentActivity, viewGroup, i));
            }
        };
    }

    CtTextLibFragment.TextStyledListener createFragmentListener(final FragmentActivity fragmentActivity, final ViewGroup viewGroup, final int i) {
        if (fragmentActivity == null || viewGroup == null) {
            return null;
        }
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        if (this.txtLibFragment == null) {
            this.txtLibFragment = (CtTextLibFragment) supportFragmentManager.findFragmentByTag(textFragmentTag);
        }
        if (this.txtStyledListener == null) {
            this.txtStyledListener = new CtTextLibFragment.TextStyledListener() {
                @Override
                public void onOk(CtTextData cMTextData) {
                    String[] split;
                    if (CtTextLibHelper.this.btmpRemoveBitmap == null) {
                        CtTextLibHelper.this.btmpRemoveBitmap = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.remove_text);
                    }
                    if (CtTextLibHelper.this.btmpScaleBitmap == null) {
                        CtTextLibHelper.this.btmpScaleBitmap = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.scale_text);
                    }
                    if (CtTextLibHelper.this.btmpEditBitmap == null) {
                        CtTextLibHelper.this.btmpEditBitmap = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.ic_text_snap_edit2);
                    }
                    if (CtTextLibHelper.this.btmpSwitchBitmap == null) {
                        CtTextLibHelper.this.btmpSwitchBitmap = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.ic_text_snap_switch);
                    }
                    CtCustomTextView cMCustomTextView = null;
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (childAt instanceof CtCustomTextView) {
                            CtCustomTextView cMCustomTextView2 = (CtCustomTextView) childAt;
                            if (cMCustomTextView2.textData.ID.compareTo(cMTextData.ID) == 0) {
                                cMCustomTextView = cMCustomTextView2;
                            }
                        }
                    }
                    if (cMCustomTextView == null) {
                        Rect rect = new Rect();
                        int i3 = 0;
                        int i4 = 0;
                        for (String str : cMTextData.message.split("\n")) {
                            i3 = (int) (i3 - ((-cMTextData.textPaint.ascent()) + cMTextData.textPaint.descent()));
                            cMTextData.textPaint.getTextBounds(str, 0, str.length(), rect);
                            if (rect.width() > i4) {
                                i4 = rect.width();
                            }
                        }
                        int descent = (int) (i3 + (-cMTextData.textPaint.ascent()) + cMTextData.textPaint.descent());
                        Log.e(CtTextLibHelper.TAG, "height " + descent);
                        cMTextData.xPos = (((float) fragmentActivity.getResources().getDisplayMetrics().widthPixels) / 2.0f) - ((float) (i4 / 2));
                        cMTextData.yPos = (((float) fragmentActivity.getResources().getDisplayMetrics().heightPixels) / 3.5f) - ((float) descent);
                        cMCustomTextView = new CtCustomTextView(fragmentActivity, cMTextData, CtTextLibHelper.this.btmpRemoveBitmap, CtTextLibHelper.this.btmpScaleBitmap, CtTextLibHelper.this.btmpEditBitmap, CtTextLibHelper.this.btmpSwitchBitmap, CtTextLibHelper.this.btmpBlackBar);
                        cMCustomTextView.setTextAndStickerViewSelectedListener(CtTextLibHelper.this.createTextAndStickerViewSelectedListener(viewGroup));
                        cMCustomTextView.setSingleTapListener(CtTextLibHelper.this.createSingleTapListener(fragmentActivity, viewGroup, i));
                        viewGroup.addView(cMCustomTextView);
                    } else {
                        cMCustomTextView.textData.set(cMTextData);
                        if (cMTextData.getFontPath() != null) {
                            cMCustomTextView.textData.setTextFont(cMTextData.getFontPath(), fragmentActivity);
                        }
                    }
                    cMCustomTextView.invalidate();
                    supportFragmentManager.beginTransaction().hide(CtTextLibHelper.this.txtLibFragment).commitAllowingStateLoss();
                }

                @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMCanvasText.CMTextLibFragment.TextStyledListener
                public void onCancel() {
                    fragmentActivity.getSupportFragmentManager().beginTransaction().hide(CtTextLibHelper.this.txtLibFragment).commitAllowingStateLoss();
                }
            };
        }
        return this.txtStyledListener;
    }

    CtCustomTextView.TextAndStickerViewSelectedListener createTextAndStickerViewSelectedListener(final ViewGroup viewGroup) {
        if (viewGroup == null) {
            return null;
        }
        return new CtCustomTextView.TextAndStickerViewSelectedListener() {
            @Override
            public void onTouchUp(BaseData cMBaseData) {
            }

            @Override
            public void setSelectedView(CtDecorateView cMDecorateView) {
                cMDecorateView.bringToFront();
                if (Build.VERSION.SDK_INT < 19) {
                    viewGroup.requestLayout();
                }
            }
        };
    }

    public void saveTextAndStickerData(Bundle bundle, ViewGroup viewGroup, Matrix matrix) {
        if (viewGroup == null || bundle == null || viewGroup == null || bundle == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        if (viewGroup == null || childCount <= 0) {
            return;
        }
        Parcelable[] parcelableArr = new BaseData[childCount];
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof CtCustomTextView) {
                CtTextData cMTextData = ((CtCustomTextView) childAt).textData;
                if (matrix != null) {
                    cMTextData.setImageSaveMatrix(matrix);
                }
                parcelableArr[i] = cMTextData;
            }
            if (childAt instanceof Ledl_TextSticker) {
                Ledl_StickerData stickerData = ((Ledl_TextSticker) childAt).getStickerData();
                if (matrix != null) {
                    stickerData.setImageSaveMatrix(matrix);
                }
                parcelableArr[i] = stickerData;
            }
        }
        bundle.putParcelableArray("base_data_array", parcelableArr);
    }

    public void loadTextAndStickerData(FragmentActivity fragmentActivity, Bundle bundle, ViewGroup viewGroup, int i, Matrix matrix) {
        BaseData[] ToBaseData;
        BaseData[] cMBaseDataArr;
        int i2;
        int i3;
        Bitmap decodeResource;
        if (viewGroup == null || bundle == null || (ToBaseData = BaseData.ToBaseData(bundle.getParcelableArray("base_data_array"))) == null) {
            return;
        }
        Bitmap decodeResource2 = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.remove_text);
        Bitmap decodeResource3 = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.scale_text);
        Bitmap decodeResource4 = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.ic_text_snap_edit2);
        Bitmap decodeResource5 = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.ic_text_snap_switch);
        Bitmap decodeResource6 = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.ic_text_black_bar);
        Bitmap decodeResource7 = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.sticker_remove_text);
        Bitmap decodeResource8 = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.sticker_scale_text);
        int length = ToBaseData.length;
        int i4 = 0;
        while (i4 < length) {
            BaseData cMBaseData = ToBaseData[i4];
            if (cMBaseData instanceof CtTextData) {
                cMBaseDataArr = ToBaseData;
                i2 = i4;
                i3 = length;
                CtCustomTextView cMCustomTextView = new CtCustomTextView(fragmentActivity, (CtTextData) cMBaseData, decodeResource2, decodeResource3, decodeResource4, decodeResource5, decodeResource6);
                cMCustomTextView.setTextAndStickerViewSelectedListener(createTextAndStickerViewSelectedListener(viewGroup));
                cMCustomTextView.setSingleTapListener(createSingleTapListener(fragmentActivity, viewGroup, i));
                viewGroup.addView(cMCustomTextView);
            } else {
                cMBaseDataArr = ToBaseData;
                i2 = i4;
                i3 = length;
                if (cMBaseData instanceof Ledl_StickerData) {
                    Ledl_StickerData cMStickerData = (Ledl_StickerData) cMBaseData;
                    if (cMStickerData.getPath() != null) {
                        decodeResource = BitmapFactory.decodeFile(cMStickerData.getPath());
                    } else {
                        decodeResource = BitmapFactory.decodeResource(fragmentActivity.getResources(), cMStickerData.getResId());
                    }
                    viewGroup.addView(new Ledl_TextSticker(fragmentActivity, decodeResource, cMStickerData, decodeResource7, decodeResource8, cMStickerData.getResId(), cMStickerData.getPath()));
                }
            }
            i4 = i2 + 1;
            ToBaseData = cMBaseDataArr;
            length = i3;
        }
    }

    public void hideForOnCreate(FragmentActivity fragmentActivity, ViewGroup viewGroup, int i) {
        if (fragmentActivity == null || viewGroup == null) {
            return;
        }
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        CtTextLibFragment cMTextLibFragment = (CtTextLibFragment) supportFragmentManager.findFragmentByTag(textFragmentTag);
        this.txtLibFragment = cMTextLibFragment;
        if (cMTextLibFragment != null) {
            supportFragmentManager.beginTransaction().hide(this.txtLibFragment).commitAllowingStateLoss();
            this.txtLibFragment.setTextStyledListener(createFragmentListener(fragmentActivity, viewGroup, i));
        }
    }
}
