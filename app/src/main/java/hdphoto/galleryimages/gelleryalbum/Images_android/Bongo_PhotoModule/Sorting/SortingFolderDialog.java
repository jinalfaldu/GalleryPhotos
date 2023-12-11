package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Sorting;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ImImageFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment.ViVideoFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners.AlbumSortingListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.FolderModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortingFolderDialog {
    public static String SortingName = "NameofSorting";
    public static String SortingType = "TypeofSorting";
    Activity activity;
    ArrayList<FolderModel> albumPathList;
    AlbumSortingListener albumSortingCallBack;
    Typeface typeface;
    String NameStr = "Name";
    String LastModifyDateStr = "Size";
    String AscendingStr = "Ascending";
    String DescendingStr = "Descending";


    public class DescendingSorting implements Comparator<FolderModel> {
        DescendingSorting() {
        }

        @Override 
        public int compare(FolderModel gMFolderModel, FolderModel gMFolderModel2) {
            return new File(gMFolderModel2.getFoldername()).getName().compareToIgnoreCase(new File(gMFolderModel.getFoldername()).getName());
        }
    }


    public class AscendingSorting implements Comparator<FolderModel> {
        AscendingSorting() {
        }

        @Override 
        public int compare(FolderModel gMFolderModel, FolderModel gMFolderModel2) {
            return new File(gMFolderModel.getFoldername()).getName().compareToIgnoreCase(new File(gMFolderModel2.getFoldername()).getName());
        }
    }


    public class AscendingDateSorting implements Comparator<FolderModel> {
        AscendingDateSorting() {
        }

        @Override 
        public int compare(FolderModel gMFolderModel, FolderModel gMFolderModel2) {
            return Long.compare(gMFolderModel.getPathlist().size(), gMFolderModel2.getPathlist().size());
        }
    }


    public class DescendingDateSorting implements Comparator<FolderModel> {
        DescendingDateSorting() {
        }

        @Override 
        public int compare(FolderModel gMFolderModel, FolderModel gMFolderModel2) {
            Log.d("jkjkjk", "DescendingDateSorting-----" + gMFolderModel2.getLastModified());
            return Long.compare(gMFolderModel2.getPathlist().size(), gMFolderModel.getPathlist().size());
        }
    }

    public SortingFolderDialog(Activity activity, ArrayList<FolderModel> arrayList, AlbumSortingListener albumSortingListener) {
        this.albumPathList = new ArrayList<>();
        this.activity = activity;
        this.albumPathList = arrayList;
        this.albumSortingCallBack = albumSortingListener;
        BindPreference();
    }

    public void BindPreference() {
        if (LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingName) == null) {
            LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingName, this.NameStr);
        }
        if (LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingType) == null) {
            LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingType, this.AscendingStr);
        }
    }

    public void ShowSortingDialogue() {
        final Dialog dialog = new Dialog(this.activity, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialogfolder_gm_sorting);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
//        this.typeface = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Roboto-Medium.ttf");
        final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.myRadioGroup);
        RadioButton radioButton = (RadioButton) dialog.findViewById(R.id.lastmodified);
        RadioButton radioButton2 = (RadioButton) dialog.findViewById(R.id.name);
        if (LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingName).equalsIgnoreCase(this.NameStr)) {
            radioButton2.setChecked(true);
        } else if (LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingName).equalsIgnoreCase(this.LastModifyDateStr)) {
            radioButton.setChecked(true);
        }
        final RadioGroup radioGroup2 = (RadioGroup) dialog.findViewById(R.id.mySortGroup);
        RadioButton radioButton3 = (RadioButton) dialog.findViewById(R.id.ascending);
        RadioButton radioButton4 = (RadioButton) dialog.findViewById(R.id.descending);
        if (LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingType).equalsIgnoreCase(this.AscendingStr)) {
            radioButton3.setChecked(true);
        } else if (LoginPreferenceUtilsFolder.GetStringData(this.activity, SortingType).equalsIgnoreCase(this.DescendingStr)) {
            radioButton4.setChecked(true);
        }
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMSorting.GMSortingFolderDialog.1
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                RadioGroup radioGroup3 = radioGroup;
                String charSequence = ((RadioButton) radioGroup3.findViewById(radioGroup3.getCheckedRadioButtonId())).getText().toString();
                SortingFolderDialog gMSortingFolderDialog = SortingFolderDialog.this;
                RadioGroup radioGroup4 = radioGroup2;
                gMSortingFolderDialog.Sorting(charSequence, ((RadioButton) radioGroup4.findViewById(radioGroup4.getCheckedRadioButtonId())).getText().toString());
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMSorting.GMSortingFolderDialog.2
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void Sorting(String str, String str2) {
        ArrayList<FolderModel> arrayList;
        ArrayList<FolderModel> arrayList2;
        ArrayList<FolderModel> arrayList3;
        ArrayList<FolderModel> arrayList4;
        try {
            if (str.equalsIgnoreCase(this.NameStr) && str2.equalsIgnoreCase(this.AscendingStr)) {
                if (this.albumPathList.size() > 0 && (arrayList4 = this.albumPathList) != null) {
                    try {
                        Collections.sort(arrayList4, new AscendingSorting());
                        this.albumSortingCallBack.Sorting(this.albumPathList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingName, str);
                LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingType, str2);
                if (ImImageFragment.imageFolderAdapter != null) {
                    ImImageFragment.imageFolderAdapter.notifyDataSetChanged();
                }
                if (ViVideoFragment.videoFolderAdapter == null) {
                    ViVideoFragment.videoFolderAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            }
            if (str.equalsIgnoreCase(this.NameStr) && str2.equalsIgnoreCase(this.DescendingStr)) {
                if (this.albumPathList.size() > 0 && (arrayList3 = this.albumPathList) != null) {
                    Collections.sort(arrayList3, new DescendingSorting());
                    this.albumSortingCallBack.Sorting(this.albumPathList);
                }
                LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingName, str);
                LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingType, str2);
                ImImageFragment.ImageFolderAdapter imageFolderAdapter = ImImageFragment.imageFolderAdapter;
                ViVideoFragment.VideoFolderAdapter videoFolderAdapter = ViVideoFragment.videoFolderAdapter;
            }
            if (str.equalsIgnoreCase(this.LastModifyDateStr) && str2.equalsIgnoreCase(this.AscendingStr)) {
                if (this.albumPathList.size() > 0 && (arrayList2 = this.albumPathList) != null) {
                    Collections.sort(arrayList2, new AscendingDateSorting());
                    this.albumSortingCallBack.Sorting(this.albumPathList);
                }
                LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingName, str);
                LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingType, str2);
                ImImageFragment.ImageFolderAdapter imageFolderAdapter2 = ImImageFragment.imageFolderAdapter;
                ViVideoFragment.VideoFolderAdapter videoFolderAdapter2 = ViVideoFragment.videoFolderAdapter;
            }
            if (str.equalsIgnoreCase(this.LastModifyDateStr) && str2.equalsIgnoreCase(this.DescendingStr) && this.albumPathList.size() > 0 && (arrayList = this.albumPathList) != null) {
                Collections.sort(arrayList, new DescendingDateSorting());
                this.albumSortingCallBack.Sorting(this.albumPathList);
            }
            LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingName, str);
            LoginPreferenceUtilsFolder.SaveStringData(this.activity, SortingType, str2);
            ImImageFragment.ImageFolderAdapter imageFolderAdapter3 = ImImageFragment.imageFolderAdapter;
            ViVideoFragment.VideoFolderAdapter videoFolderAdapter3 = ViVideoFragment.videoFolderAdapter;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
