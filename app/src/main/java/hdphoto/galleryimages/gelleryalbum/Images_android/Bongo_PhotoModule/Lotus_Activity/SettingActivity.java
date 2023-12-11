package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;

public class SettingActivity extends BseActivity {
    Activity activity;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img_back;
    LinearLayout ll_albumList;
    LinearLayout ll_changePassword;
    LinearLayout ll_changeQuestion;
    LinearLayout ll_dataList;
    LinearLayout ll_themeChange;
    LinearLayout option_dialog;
    PrefClass preferenceClass;
    RelativeLayout rel_image_main;
    RelativeLayout rl_column2;
    RelativeLayout rl_column3;
    RelativeLayout rl_column4;
    RelativeLayout rl_column5;
    RelativeLayout rl_column6;
    RelativeLayout rl_column7;
    RelativeLayout rl_column8;
    String toastString;
    TextView txt_columnTitle;
    Typeface typeface;
    int value = 0;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
        this.activity = this;
        this.preferenceClass = new PrefClass(this);
        new NativeHelper().shownativeads(this, findViewById(R.id.native_container));
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

        ID_Binding();
    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
    }

    private void ID_Binding() {
        this.img_back = (ImageView) findViewById(R.id.img_back);
        this.ll_changePassword = (LinearLayout) findViewById(R.id.ll_changePassword);
        this.ll_albumList = (LinearLayout) findViewById(R.id.ll_albumList);
        this.ll_dataList = (LinearLayout) findViewById(R.id.ll_dataList);
        this.option_dialog = (LinearLayout) findViewById(R.id.option_dialog);
        this.ll_changeQuestion = (LinearLayout) findViewById(R.id.ll_changeQuestion);
        this.ll_themeChange = (LinearLayout) findViewById(R.id.ll_themeChange);
        this.rel_image_main = (RelativeLayout) findViewById(R.id.rel_image_main);
        this.img2 = (ImageView) findViewById(R.id.img2);
        this.img3 = (ImageView) findViewById(R.id.img3);
        this.img4 = (ImageView) findViewById(R.id.img4);
        this.rl_column2 = (RelativeLayout) findViewById(R.id.rl_column2);
        this.rl_column3 = (RelativeLayout) findViewById(R.id.rl_column3);
        this.rl_column4 = (RelativeLayout) findViewById(R.id.rl_column4);
        this.rl_column5 = (RelativeLayout) findViewById(R.id.rl_column5);
        this.rl_column6 = (RelativeLayout) findViewById(R.id.rl_column6);
        this.rl_column7 = (RelativeLayout) findViewById(R.id.rl_column7);
        this.rl_column8 = (RelativeLayout) findViewById(R.id.rl_column8);
        this.txt_columnTitle = (TextView) findViewById(R.id.txt_columnTitle);
        this.typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        Click_Event();
    }

    private void Click_Event() {
        this.img_back.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.1
            @Override
            public void onClick(View view) {
                SettingActivity.this.HideLayout();
                onBackPressed();
            }
        });
        this.ll_changePassword.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.2
            @Override
            public void onClick(View view) {
                SettingActivity.this.HideLayout();
                if (SettingActivity.this.preferenceClass.getInt(Common.gIsLockStatus, 0) == 0) {
                    SettingActivity.this.SecurityDialog();
                } else {
                    AdInterGD.getInstance().showInter(SettingActivity.this, new AdInterGD.MyCallback() {
                        @Override
                        public void callbackCall() {
                            Intent intent = new Intent(SettingActivity.this, Degu_PasswordChangeActivity.class);
                            intent.putExtra(Common.gActivityname, "MainActivity");
                            SettingActivity.this.startActivity(intent);
                        }
                    });
                }
            }
        });
        this.ll_changeQuestion.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.3
            @Override
            public void onClick(View view) {
                        SettingActivity.this.HideLayout();
                        SettingActivity.this.startActivity(new Intent(SettingActivity.this, SecurityQAActivity.class).putExtra("toActivity", "SettingActivity"));

            }
        });
        this.ll_themeChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.OpenThemeDialog();
            }
        });
        this.rel_image_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.HideLayout();
            }
        });
        this.ll_albumList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.toastString = "";
                SettingActivity.this.toastString = "Album";
                Degu_MainActivity.parameter = 0;
                SettingActivity.this.VisibleLayout();
                SettingActivity.this.img2.setImageDrawable(SettingActivity.this.getResources().getDrawable(R.drawable.fld_column_2));
                SettingActivity.this.img3.setImageDrawable(SettingActivity.this.getResources().getDrawable(R.drawable.fld_column_3));
                SettingActivity.this.img4.setImageDrawable(SettingActivity.this.getResources().getDrawable(R.drawable.fld_column_4));
                SettingActivity.this.txt_columnTitle.setText("Folder Column : ");
                SettingActivity.this.rl_column5.setVisibility(8);
                SettingActivity.this.rl_column6.setVisibility(8);
                SettingActivity.this.rl_column7.setVisibility(8);
                SettingActivity.this.rl_column8.setVisibility(8);
            }
        });
        this.ll_dataList.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.7
            @Override
            public void onClick(View view) {
                SettingActivity.this.toastString = "";
                SettingActivity.this.toastString = "Data";
                Degu_MainActivity.parameter = 1;
                SettingActivity.this.VisibleLayout();
                SettingActivity.this.img2.setImageDrawable(SettingActivity.this.getResources().getDrawable(R.drawable.img_column_2));
                SettingActivity.this.img3.setImageDrawable(SettingActivity.this.getResources().getDrawable(R.drawable.img_column_3));
                SettingActivity.this.img4.setImageDrawable(SettingActivity.this.getResources().getDrawable(R.drawable.img_column_4));
                SettingActivity.this.txt_columnTitle.setText("Data Column : ");
                SettingActivity.this.rl_column5.setVisibility(View.VISIBLE);
                SettingActivity.this.rl_column6.setVisibility(View.VISIBLE);
                SettingActivity.this.rl_column7.setVisibility(View.VISIBLE);
                SettingActivity.this.rl_column8.setVisibility(View.VISIBLE);
            }
        });
        this.rl_column2.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.8
            @Override
            public void onClick(View view) {
                SettingActivity.this.SetToast(2);
                if (Degu_MainActivity.parameter == 0) {
                    Degu_MainActivity.preferenceAlbumUtils.putInt(Common.gAlbumColumns, 2);
                    Degu_MainActivity.albumDivider = 4;
                    SettingActivity.this.PassTag();
                } else if (Degu_MainActivity.parameter == 1) {
                    Degu_MainActivity.preferenceDataUtils.putInt(Common.gDataColumns, 2);
                    Degu_MainActivity.dataDivider = 4;
                    SettingActivity.this.PassTag();
                }
                SettingActivity.this.HideLayout();
            }
        });
        this.rl_column3.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.9
            @Override
            public void onClick(View view) {
                SettingActivity.this.SetToast(3);
                if (Degu_MainActivity.parameter == 0) {
                    Degu_MainActivity.preferenceAlbumUtils.putInt(Common.gAlbumColumns, 3);
                    Degu_MainActivity.albumDivider = 6;
                    SettingActivity.this.PassTag();
                } else if (Degu_MainActivity.parameter == 1) {
                    Degu_MainActivity.preferenceDataUtils.putInt(Common.gDataColumns, 3);
                    Degu_MainActivity.dataDivider = 6;
                    SettingActivity.this.PassTag();
                }
                SettingActivity.this.HideLayout();
            }
        });
        this.rl_column4.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.10
            @Override
            public void onClick(View view) {
                SettingActivity.this.SetToast(4);
                if (Degu_MainActivity.parameter == 0) {
                    Degu_MainActivity.preferenceAlbumUtils.putInt(Common.gAlbumColumns, 4);
                    Degu_MainActivity.albumDivider = 8;
                    SettingActivity.this.PassTag();
                } else if (Degu_MainActivity.parameter == 1) {
                    Degu_MainActivity.preferenceDataUtils.putInt(Common.gDataColumns, 4);
                    Degu_MainActivity.dataDivider = 8;
                    SettingActivity.this.PassTag();
                }
                SettingActivity.this.HideLayout();
            }
        });
        this.rl_column5.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.11
            @Override
            public void onClick(View view) {
                SettingActivity.this.SetToast(5);
                if (Degu_MainActivity.parameter == 0) {
                    Degu_MainActivity.preferenceAlbumUtils.putInt(Common.gAlbumColumns, 5);
                    Degu_MainActivity.albumDivider = 10;
                    SettingActivity.this.PassTag();
                } else if (Degu_MainActivity.parameter == 1) {
                    Degu_MainActivity.preferenceDataUtils.putInt(Common.gDataColumns, 5);
                    Degu_MainActivity.dataDivider = 10;
                    SettingActivity.this.PassTag();
                }
                SettingActivity.this.HideLayout();
            }
        });
        this.rl_column6.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.12
            @Override
            public void onClick(View view) {
                SettingActivity.this.SetToast(6);
                if (Degu_MainActivity.parameter == 0) {
                    Degu_MainActivity.preferenceAlbumUtils.putInt(Common.gAlbumColumns, 6);
                    Degu_MainActivity.albumDivider = 12;
                    SettingActivity.this.PassTag();
                } else if (Degu_MainActivity.parameter == 1) {
                    Degu_MainActivity.preferenceDataUtils.putInt(Common.gDataColumns, 6);
                    Degu_MainActivity.dataDivider = 12;
                    SettingActivity.this.PassTag();
                }
                SettingActivity.this.HideLayout();
            }
        });
        this.rl_column7.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.13
            @Override
            public void onClick(View view) {
                SettingActivity.this.SetToast(7);
                if (Degu_MainActivity.parameter == 0) {
                    Degu_MainActivity.preferenceAlbumUtils.putInt(Common.gAlbumColumns, 7);
                    Degu_MainActivity.albumDivider = 14;
                    SettingActivity.this.PassTag();
                } else if (Degu_MainActivity.parameter == 1) {
                    Degu_MainActivity.preferenceDataUtils.putInt(Common.gDataColumns, 7);
                    Degu_MainActivity.dataDivider = 14;
                    SettingActivity.this.PassTag();
                }
                SettingActivity.this.HideLayout();
            }
        });
        this.rl_column8.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.14
            @Override
            public void onClick(View view) {
                SettingActivity.this.SetToast(8);
                if (Degu_MainActivity.parameter == 0) {
                    Degu_MainActivity.preferenceAlbumUtils.putInt(Common.gAlbumColumns, 8);
                    Degu_MainActivity.albumDivider = 16;
                    SettingActivity.this.PassTag();
                } else if (Degu_MainActivity.parameter == 1) {
                    Degu_MainActivity.preferenceDataUtils.putInt(Common.gDataColumns, 8);
                    Degu_MainActivity.dataDivider = 16;
                    SettingActivity.this.PassTag();
                }
                SettingActivity.this.HideLayout();
            }
        });
    }

    public void SetToast(int i) {
        String str;
        if (i == 0 || (str = this.toastString) == null) {
            return;
        }
        if (str.equals("Album")) {
            Activity activity = this.activity;
            Toast.makeText(activity, "Set Album Column " + i + " Successfully", 0).show();
        } else if (this.toastString.equals("Data")) {
            Activity activity2 = this.activity;
            Toast.makeText(activity2, "Set Data Column " + i + " Successfully", 0).show();
        }
    }

    public void HideLayout() {
        this.rel_image_main.setVisibility(8);
        this.option_dialog.setVisibility(8);
    }

    public void VisibleLayout() {
        this.rel_image_main.setVisibility(0);
        this.option_dialog.setVisibility(0);
    }

    public void PassTag() {
        Degu_MainActivity.oriTag = "SettingActivity";
    }

    public void SecurityDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_security);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.textDesc)).setText("Set your security lock for hide photos & videos!");
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.15
            @Override
            public void onClick(View view) {
                FolderPath.lock_screen = 1;
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.16
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void OpenThemeDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_theme);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.img_selectWhite);
        final ImageView imageView2 = (ImageView) dialog.findViewById(R.id.img_selectBlack);
        if (this.preferenceClass.getInt("ThemeMode", 0) == 0) {
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
        } else if (this.preferenceClass.getInt("ThemeMode", 1) == 1) {
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
        }
        ((LinearLayout) dialog.findViewById(R.id.ll_themeWhite)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.17
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this.activity, "White", 0).show();
                imageView.setVisibility(0);
                imageView2.setVisibility(8);
                SettingActivity.this.value = 0;
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.ll_themeBlack)).setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSettingActivity.18
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this.activity, "Black", 0).show();
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
                SettingActivity.this.value = 1;
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity gMSettingActivity = SettingActivity.this;
                gMSettingActivity.setPreference(gMSettingActivity.value);
                dialog.dismiss();
            }
        });
        ((RelativeLayout) dialog.findViewById(R.id.rl_no)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void setPreference(int i) {
        if (i == 0) {
            this.preferenceClass.putInt("ThemeMode", 0);
        } else if (i == 1) {
            this.preferenceClass.putInt("ThemeMode", 1);
        }
    }

    @Override
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this,true);
    }
}
