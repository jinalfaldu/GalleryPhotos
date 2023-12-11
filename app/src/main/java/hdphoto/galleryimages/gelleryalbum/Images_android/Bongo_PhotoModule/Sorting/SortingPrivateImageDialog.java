package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.HideImageFolderDataSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortingPrivateImageDialog {
    public static String SortingName = "NameofSorting";
    public static String SortingType = "TypeofSorting";
    static final String TAG = "SortingGalleryHide";
    Activity activity;
    ArrayList<Object> imgPathlist;
    HideImageFolderDataSortingListener sortingHideImageCallBack;
    Typeface typeface;
    String NameStr = "Name";
    String LastModifyDateStr = "Last modifyDate";
    String AscendingStr = "Ascending";
    String DescendingStr = "Descending";


    public class DescendingSorting implements Comparator<Object> {
        DescendingSorting() {
        }

        @Override 
        public int compare(Object obj, Object obj2) {
            return new File(((DataFileModel) obj2).path).getName().compareToIgnoreCase(new File(((DataFileModel) obj).path).getName());
        }
    }


    public class AscendingSorting implements Comparator<Object> {
        AscendingSorting() {
        }

        @Override 
        public int compare(Object obj, Object obj2) {
            return new File(((DataFileModel) obj).path).getName().compareToIgnoreCase(new File(((DataFileModel) obj2).path).getName());
        }
    }


    public class AscendingDateSorting implements Comparator<Object> {
        AscendingDateSorting() {
        }

        @Override 
        public int compare(Object obj, Object obj2) {
            if (Build.VERSION.SDK_INT >= 30) {
                return Long.compare(Long.parseLong(((DataFileModel) obj).takenDate), Long.parseLong(((DataFileModel) obj2).takenDate));
            }
            return Long.compare(Long.parseLong(((DataFileModel) obj).modifiedDate), Long.parseLong(((DataFileModel) obj2).modifiedDate));
        }
    }


    public class DescendingDateSorting implements Comparator<Object> {
        DescendingDateSorting() {
        }

        @Override 
        public int compare(Object obj, Object obj2) {
            if (Build.VERSION.SDK_INT >= 30) {
                return Long.compare(Long.parseLong(((DataFileModel) obj2).takenDate), Long.parseLong(((DataFileModel) obj).takenDate));
            }
            return Long.compare(Long.parseLong(((DataFileModel) obj2).modifiedDate), Long.parseLong(((DataFileModel) obj).modifiedDate));
        }
    }

    public SortingPrivateImageDialog(Activity activity, ArrayList<Object> arrayList, HideImageFolderDataSortingListener hideImageFolderDataSortingListener) {
        this.imgPathlist = new ArrayList<>();
        Log.d("SSSSSS", " - SortingPrivateImageDialog ");
        this.activity = activity;
        this.imgPathlist = arrayList;
        this.sortingHideImageCallBack = hideImageFolderDataSortingListener;
        BindPreference();
    }

    public void BindPreference() {
        if (LoginPreferenceUtilsData.GetStringData(this.activity, SortingName) == null) {
            LoginPreferenceUtilsData.SaveStringData(this.activity, SortingName, this.LastModifyDateStr);
        }
        if (LoginPreferenceUtilsData.GetStringData(this.activity, SortingType) == null) {
            LoginPreferenceUtilsData.SaveStringData(this.activity, SortingType, this.DescendingStr);
        }
    }

    public void ShowSortingDialogue() {
        final Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_sorting);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
//        this.typeface = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Roboto-Medium.ttf");
        ((TextView) dialog.findViewById(R.id.txtTitle)).setTypeface(this.typeface);
        final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.myRadioGroup);
        RadioButton radioButton = (RadioButton) dialog.findViewById(R.id.lastmodified);
        RadioButton radioButton2 = (RadioButton) dialog.findViewById(R.id.name);
        if (LoginPreferenceUtilsData.GetStringData(this.activity, SortingName).equalsIgnoreCase(this.NameStr)) {
            radioButton2.setChecked(true);
        } else if (LoginPreferenceUtilsData.GetStringData(this.activity, SortingName).equalsIgnoreCase(this.LastModifyDateStr)) {
            radioButton.setChecked(true);
        }
        final RadioGroup radioGroup2 = (RadioGroup) dialog.findViewById(R.id.mySortGroup);
        RadioButton radioButton3 = (RadioButton) dialog.findViewById(R.id.ascending);
        RadioButton radioButton4 = (RadioButton) dialog.findViewById(R.id.descending);
        if (LoginPreferenceUtilsData.GetStringData(this.activity, SortingType).equalsIgnoreCase(this.AscendingStr)) {
            radioButton3.setChecked(true);
        } else if (LoginPreferenceUtilsData.GetStringData(this.activity, SortingType).equalsIgnoreCase(this.DescendingStr)) {
            radioButton4.setChecked(true);
        }
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMSorting.GMSortingPrivateImageDialog.1
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                RadioGroup radioGroup3 = radioGroup;
                String charSequence = ((RadioButton) radioGroup3.findViewById(radioGroup3.getCheckedRadioButtonId())).getText().toString();
                RadioGroup radioGroup4 = radioGroup2;
                SortingPrivateImageDialog.this.Sorting(charSequence, ((RadioButton) radioGroup4.findViewById(radioGroup4.getCheckedRadioButtonId())).getText().toString());
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMSorting.GMSortingPrivateImageDialog.2
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void Sorting(String str, String str2) {
        Log.d("SSSSSS", " - Sorting -  = nameStr = " + str + " = typeStr = " + str2);
        try {
            if (str.equals(this.LastModifyDateStr) && str2.equals(this.DescendingStr) && this.imgPathlist.size() > 0 && this.imgPathlist != null) {
                Log.d("SSSSSS", " - Last modifyDate - Descending ");
                Collections.sort(this.imgPathlist, new DescendingDateSorting());
                this.sortingHideImageCallBack.Sorting(this.imgPathlist);
            } else if (str.equals(this.LastModifyDateStr) && str2.equals(this.AscendingStr) && this.imgPathlist.size() > 0 && this.imgPathlist != null) {
                Log.d("SSSSSS", " - Last modifyDate - Ascending ");
                Collections.sort(this.imgPathlist, new AscendingDateSorting());
                this.sortingHideImageCallBack.Sorting(this.imgPathlist);
            } else if (str.equals(this.NameStr) && str2.equals(this.DescendingStr) && this.imgPathlist.size() > 0 && this.imgPathlist != null) {
                Log.d("SSSSSS", " - Name - Descending ");
                Collections.sort(this.imgPathlist, new DescendingSorting());
                this.sortingHideImageCallBack.Sorting(this.imgPathlist);
            } else if (str.equals(this.NameStr) && str2.equals(this.AscendingStr) && this.imgPathlist.size() > 0 && this.imgPathlist != null) {
                Log.d("SSSSSS", " - Name - Ascending ");
                Collections.sort(this.imgPathlist, new AscendingSorting());
                this.sortingHideImageCallBack.Sorting(this.imgPathlist);
            }
            LoginPreferenceUtilsData.SaveStringData(this.activity, SortingName, str);
            LoginPreferenceUtilsData.SaveStringData(this.activity, SortingType, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
