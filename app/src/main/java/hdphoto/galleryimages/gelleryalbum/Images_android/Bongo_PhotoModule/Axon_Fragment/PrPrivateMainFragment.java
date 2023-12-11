package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Axon_Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_ForgotPasswordActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.Degu_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.PreviewActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.SecurityQAActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.odelClass.DataFileModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.ConstantArrayClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FileUtils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrivateMasterPassword;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;


public class PrPrivateMainFragment extends BaseFragment {
    static final String TAG = "PrivateMainFragment";
    Activity activity;
    ImImagePrivateFragment imagePrivateFragment;
    ImageView imgClearNumber;
    ImageView imgHidePin1;
    ImageView imgHidePin2;
    ImageView imgHidePin3;
    ImageView imgHidePin4;
    ImageView imgNumber0;
    ImageView imgNumber1;
    ImageView imgNumber2;
    ImageView imgNumber3;
    ImageView imgNumber4;
    ImageView imgNumber5;
    ImageView imgNumber6;
    ImageView imgNumber7;
    ImageView imgNumber8;
    ImageView imgNumber9;
    View img_photo;
    View img_video;
    LinearLayout llPhotos;
    LinearLayout llVideos;
    LinearLayout ll_HintLockView;
    LinearLayout ll_ShakeAnimation;
    LinearLayout ll_pinLockView;
    PrivateMasterPassword masterPin;
    ArrayList<String> originalPin;
    ArrayList<String> passwordArray;
    PrefClass preferenceClass;
    ProgressDialog progressLock;
    String progressTag;
    RelativeLayout rl_tabLayout;
    TabLayout tabs;
    TextView txtHintTextPassword;
    TextView txtPhotos;
    TextView txtSetSecurity;
    TextView txtVideos;
    TextView txt_confirm;
    TextView txt_forgotPin;
    Typeface typeface;
    ViVideoPrivateFragment videoPrivateFragment;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMFragment.GMPrivateMainFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            PrPrivateMainFragment.this.activity.unregisterReceiver(PrPrivateMainFragment.this.broadcastReceiver);
            if (Common.IdentifyActivity.equals("SecurityQuestionActivity")) {
                PrPrivateMainFragment.this.activity.startActivity(new Intent(PrPrivateMainFragment.this.getActivity(), SecurityQAActivity.class));
            } else if (Common.IdentifyActivity.equals("ForgotPasswordActivity")) {
                PrPrivateMainFragment.this.activity.startActivity(new Intent(PrPrivateMainFragment.this.getActivity(), Degu_ForgotPasswordActivity.class));
            }
        }
    };
    int enterPin = 0;
    int isLockStatus = 0;
    boolean checkPass = false;
    int digit = 0;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_private_main, viewGroup, false);
        this.activity = getActivity();
        this.preferenceClass = new PrefClass(getActivity());
//        this.typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        this.isLockStatus = this.preferenceClass.getInt(Common.gIsLockStatus, 0);
        this.originalPin = new ArrayList<>();
        this.masterPin = new PrivateMasterPassword();
        this.llPhotos = (LinearLayout) inflate.findViewById(R.id.layPhotos);
        this.llVideos = (LinearLayout) inflate.findViewById(R.id.layVideos);
        this.txtPhotos = (TextView) inflate.findViewById(R.id.txtPhotos);
        this.txtVideos = (TextView) inflate.findViewById(R.id.txtVideos);
        this.img_photo = (View) inflate.findViewById(R.id.img_photo);
        this.img_video = (View) inflate.findViewById(R.id.img_video);
        ID_Binding(inflate);
        this.passwordArray = new ArrayList<>();
        HiddenView();
        LayoutView();
        return inflate;
    }

    public void SetFragment() {
        this.imagePrivateFragment = new ImImagePrivateFragment();
        this.videoPrivateFragment = new ViVideoPrivateFragment();
        Degu_MainActivity.privateclick = 0;
        changeFragment(new ImImagePrivateFragment());
        this.llPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPhotos.setTextColor(getResources().getColor(R.color.tab_txt));
                txtVideos.setTextColor(getResources().getColor(R.color.gray_73));
                img_photo.setVisibility(View.VISIBLE);
                img_video.setVisibility(View.INVISIBLE);
                Degu_MainActivity.privateclick = 0;
                PrPrivateMainFragment.this.changeFragment(new ImImagePrivateFragment());
            }
        });
        this.llVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPhotos.setTextColor(getResources().getColor(R.color.gray_73));
                txtVideos.setTextColor(getResources().getColor(R.color.gray_73));
                img_photo.setVisibility(View.INVISIBLE);
                img_video.setVisibility(View.VISIBLE);
                Degu_MainActivity.privateclick = 1;
                PrPrivateMainFragment.this.changeFragment(new ViVideoPrivateFragment());
            }
        });
    }

    @Override 
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            changeFragment(new ImImagePrivateFragment());
            txtPhotos.setTextColor(getResources().getColor(R.color.tab_txt));
            txtVideos.setTextColor(getResources().getColor(R.color.gray_73));
            img_photo.setVisibility(View.VISIBLE);
            img_video.setVisibility(View.INVISIBLE);
            Degu_MainActivity.privateclick = 0;
            return;
        }
        changeFragment(new ViVideoPrivateFragment());
    }

    public void changeFragment(Fragment fragment) {
        Degu_MainActivity.ivSelectAll.setImageDrawable(getResources().getDrawable(R.drawable.action_select));
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.private_frame_layout, fragment);
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        beginTransaction.commit();
    }

    public void LayoutView() {
        int i = this.preferenceClass.getInt(Common.gIsLockStatus, 0);
        this.isLockStatus = i;
        if (i == 0) {
            try {
                this.rl_tabLayout.setVisibility(8);
                this.ll_pinLockView.setVisibility(8);
                this.ll_HintLockView.setVisibility(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i == 1) {
            this.rl_tabLayout.setVisibility(8);
            this.ll_HintLockView.setVisibility(8);
            this.ll_pinLockView.setVisibility(0);
        } else if (i == 2) {
            this.ll_HintLockView.setVisibility(8);
            this.ll_pinLockView.setVisibility(8);
            this.rl_tabLayout.setVisibility(0);
        }
    }

    private void ID_Binding(View view) {
        this.ll_ShakeAnimation = view.findViewById(R.id.layoutShakeAnimation);
        this.ll_HintLockView = view.findViewById(R.id.layoutHintLockView);
        this.ll_pinLockView = view.findViewById(R.id.pinLockViewLayout);
        this.rl_tabLayout = view.findViewById(R.id.tabLayout);
        this.tabs = view.findViewById(R.id.tab);
        this.ll_pinLockView = view.findViewById(R.id.pinLockViewLayout);
        this.txt_confirm = view.findViewById(R.id.txt_confirm);
        this.txtHintTextPassword = view.findViewById(R.id.txtHintTextPassword);
        this.txtSetSecurity = view.findViewById(R.id.btnSetSecurity);
        this.imgHidePin1 = view.findViewById(R.id.hidePin1);
        this.imgHidePin2 = view.findViewById(R.id.hidePin2);
        this.imgHidePin3 = view.findViewById(R.id.hidePin3);
        this.imgHidePin4 = view.findViewById(R.id.hidePin4);
        this.imgNumber1 = view.findViewById(R.id.btnNumber1);
        this.imgNumber2 = view.findViewById(R.id.btnNumber2);
        this.imgNumber3 = view.findViewById(R.id.btnNumber3);
        this.imgNumber4 = view.findViewById(R.id.btnNumber4);
        this.imgNumber5 = view.findViewById(R.id.btnNumber5);
        this.imgNumber6 = view.findViewById(R.id.btnNumber6);
        this.imgNumber7 = view.findViewById(R.id.btnNumber7);
        this.imgNumber8 = view.findViewById(R.id.btnNumber8);
        this.imgNumber9 = view.findViewById(R.id.btnNumber9);
        this.imgNumber0 = view.findViewById(R.id.btnNumber0);
        this.imgClearNumber = view.findViewById(R.id.btnClearNumber);
        this.txt_forgotPin = (TextView) view.findViewById(R.id.btnForgetPassword);
        Click_Event();
    }

    private void Click_Event() {
        this.txtSetSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdInterGD.getInstance().showInter(getActivity(), new AdInterGD.MyCallback() {
                    @Override
                    public void callbackCall() {
                        PrPrivateMainFragment.this.activity.registerReceiver(PrPrivateMainFragment.this.broadcastReceiver, new IntentFilter("SecurityQuestionActivity"));
                        Common.IdentifyActivity = "SecurityQuestionActivity";
                        PrPrivateMainFragment.this.activity.sendBroadcast(new Intent(Common.IdentifyActivity));
                    }
                });
            }
        });
        this.imgNumber0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword("0");
                }
            }
        });
        this.imgNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                }
            }
        });
        this.imgNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword(ExifInterface.GPS_MEASUREMENT_2D);
                }
            }
        });
        this.imgNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword(ExifInterface.GPS_MEASUREMENT_3D);
                }
            }
        });
        this.imgNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword("4");
                }
            }
        });
        this.imgNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword("5");
                }
            }
        });
        this.imgNumber6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword("6");
                }
            }
        });
        this.imgNumber7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword("7");
                }
            }
        });
        this.imgNumber8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword("8");
                }
            }
        });
        this.imgNumber9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PrPrivateMainFragment.this.digit < 4) {
                    PrPrivateMainFragment.this.CheckPassword("9");
                }
            }
        });
        this.imgClearNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrPrivateMainFragment.this.ClearPassword();
            }
        });
        this.txt_forgotPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrPrivateMainFragment.this.ForgetPassword();
            }
        });
    }

    private void HiddenView() {
        Degu_MainActivity.ivSelectAll.setVisibility(8);
        Degu_MainActivity.ivAddAlbum.setVisibility(8);
        Degu_MainActivity.ivUnLock.setVisibility(8);
        Degu_MainActivity.ivShare.setVisibility(8);
        Degu_MainActivity.ivDelete.setVisibility(8);
        Degu_MainActivity.ivSort.setVisibility(8);
    }

    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void ClearPassword() {
        if (this.passwordArray.size() == 0) {
            this.txtHintTextPassword.setText("Enter Password.");
            this.passwordArray.clear();
        } else if (this.passwordArray.size() == 1) {
            this.imgHidePin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList = this.passwordArray;
            arrayList.remove(arrayList.get(0));
            this.digit--;
        } else if (this.passwordArray.size() == 2) {
            this.imgHidePin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList2 = this.passwordArray;
            arrayList2.remove(arrayList2.get(0));
            this.digit--;
        } else if (this.passwordArray.size() == 3) {
            this.imgHidePin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList3 = this.passwordArray;
            arrayList3.remove(arrayList3.get(0));
            this.digit--;
        } else if (this.passwordArray.size() == 4) {
            this.imgHidePin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList4 = this.passwordArray;
            arrayList4.remove(arrayList4.get(0));
            this.digit--;
        }
    }

    public void ForgetPassword() {
        this.activity.registerReceiver(this.broadcastReceiver, new IntentFilter("ForgotPasswordActivity"));
        Common.IdentifyActivity = "ForgotPasswordActivity";
        this.activity.sendBroadcast(new Intent(Common.IdentifyActivity));
    }

    public void CheckPassword(String str) {
        if (this.digit <= 4) {
            this.passwordArray.add(str);
            if (this.passwordArray.size() == 1) {
                this.digit++;
                this.imgHidePin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
            } else if (this.passwordArray.size() == 2) {
                this.digit++;
                this.imgHidePin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
            } else if (this.passwordArray.size() == 3) {
                this.digit++;
                this.imgHidePin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
            } else if (this.passwordArray.size() == 4) {
                this.digit++;
                this.imgHidePin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
                Log.d("PASSSSSS", " - click 00 " + this.digit);
                UnlockPassword();
            }
        }
    }

    private void UnlockPassword() {
        new ArrayList();
        final ArrayList<String> listString = this.preferenceClass.getListString(Common.gCurrentPassword);
        if (this.preferenceClass.getInt(Common.gUseMasterPassword, 0) == 0) {
            this.txt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PrPrivateMainFragment.this.passwordArray.equals(listString)) {
                        PrPrivateMainFragment.this.preferenceClass.putInt(Common.gIsLockStatus, 2);
                        PrPrivateMainFragment.this.SetFragment();
                        PrPrivateMainFragment.this.LayoutView();
                        return;
                    }
                    Toast.makeText(PrPrivateMainFragment.this.getActivity(), "Invalid Password.", 0).show();
                    PrPrivateMainFragment.this.digit = 0;
                    PrPrivateMainFragment.this.txtHintTextPassword.setText("Enter Password.");
                    PrPrivateMainFragment.this.ResetAll();
                }
            });
        } else if (this.preferenceClass.getInt(Common.gUseMasterPassword, 0) == 1) {
            if (this.passwordArray.equals(this.masterPin.GetMSArrayA())) {
                EnterNewPassword();
            } else if (this.passwordArray.equals(this.masterPin.GetMSArrayB())) {
                EnterNewPassword();
            } else if (this.passwordArray.equals(this.masterPin.GetMSArrayC())) {
                EnterNewPassword();
            } else {
                ConfirmPassword();
            }
        }
    }

    private void ConfirmPassword() {
        int i = this.enterPin;
        if (i == 1) {
            this.preferenceClass.putListString(Common.gTempPassword, this.passwordArray);
            this.passwordArray.clear();
            this.txtHintTextPassword.setText("Confirm Password.");
            this.imgHidePin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.imgHidePin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.imgHidePin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.imgHidePin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.enterPin = 2;
        } else if (i != 2) {
            new ArrayList();
            if (this.passwordArray.equals(this.preferenceClass.getListString(Common.gCurrentPassword))) {
                this.preferenceClass.putInt(Common.gIsLockStatus, 2);
                LayoutView();
                return;
            }
            Toast.makeText(getActivity(), "Invalid Password.", 0).show();
            this.txtHintTextPassword.setText("Enter Password.");
            ResetAll();
        } else if (GetlistStingToString(this.preferenceClass.getListString(Common.gTempPassword)).equals(GetlistStingToString(this.passwordArray))) {
            this.preferenceClass.putListString(Common.gCurrentPassword, this.passwordArray);
            this.preferenceClass.putInt(Common.gIsLockStatus, 2);
            this.preferenceClass.putInt(Common.gUseMasterPassword, 0);
            LayoutView();
        } else {
            if (this.enterPin == 2) {
                this.enterPin = 1;
                Toast.makeText(getActivity(), "Invalid Confirm Password.", 0).show();
                this.txtHintTextPassword.setText("Enter New Password.");
            } else {
                this.enterPin = 0;
            }
            ResetAll();
        }
    }

    public void EnterNewPassword() {
        if (this.enterPin == 0) {
            ArrayList<String> arrayList = this.passwordArray;
            this.originalPin = arrayList;
            arrayList.clear();
            this.txtHintTextPassword.setText("Enter New Password.");
            this.imgHidePin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.imgHidePin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.imgHidePin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.imgHidePin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.enterPin = 1;
        } else if (this.passwordArray.equals(this.masterPin.GetMSArrayA())) {
            Toast.makeText(getActivity(), "You can't use this pin, Please try another pin.", 0).show();
            UseDifferentPassword();
        } else if (this.passwordArray.equals(this.masterPin.GetMSArrayB())) {
            Toast.makeText(getActivity(), "You can't use this pin, Please try another pin.", 0).show();
            UseDifferentPassword();
        } else if (this.passwordArray.equals(this.masterPin.GetMSArrayC())) {
            Toast.makeText(getActivity(), "You can't use this pin, Please try another pin.", 0).show();
            UseDifferentPassword();
        }
    }

    private void UseDifferentPassword() {
        this.txtHintTextPassword.setText("Enter New Password.");
        this.ll_ShakeAnimation.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake_anim));
        ((Vibrator) getActivity().getSystemService("vibrator")).vibrate(800L);
        this.passwordArray.clear();
        this.imgHidePin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.imgHidePin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.imgHidePin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.imgHidePin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
    }

    public void ResetAll() {
        this.ll_ShakeAnimation.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake_anim));
        ((Vibrator) getActivity().getSystemService("vibrator")).vibrate(800L);
        this.passwordArray.clear();
        this.imgHidePin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.imgHidePin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.imgHidePin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.imgHidePin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
    }

    public String GetlistStingToString(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }

    @Override 
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        LayoutView();
        if (!Degu_MainActivity.checkLockForFirstFile || ConstantArrayClass.firstTimeLockDataArray.size() == 0) {
            return;
        }
        this.progressTag = "FromLock";
        new LockFilesExecute(ConstantArrayClass.firstTimeLockDataArray).execute(new Void[0]);
    }

    @Override 
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override 
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override 
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public class LockFilesExecute extends AsyncTask<Void, Void, Void> {
        ArrayList<DataFileModel> pathList;

        public LockFilesExecute(ArrayList<DataFileModel> arrayList) {
            this.pathList = new ArrayList<>();
            PrPrivateMainFragment.this.progressLock = new ProgressDialog(PrPrivateMainFragment.this.getActivity());
            this.pathList = arrayList;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            PrPrivateMainFragment.this.progressLock.setMessage("Please wait a while...");
            PrPrivateMainFragment.this.progressLock.setProgressStyle(0);
            PrPrivateMainFragment.this.progressLock.setIndeterminate(false);
            PrPrivateMainFragment.this.progressLock.setCancelable(false);
            PrPrivateMainFragment.this.progressLock.show();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            File file = null;
            Uri uri;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> listString = PrPrivateMainFragment.this.preferenceClass.getListString(Common.gOldPath);
            new File(FolderPath.SDCARD_PATH_IMAGE).mkdirs();
            new File(FolderPath.SDCARD_PATH_VIDEO).mkdirs();
            new File(FolderPath.SDCARD_PATH_FOR11).mkdirs();
            new ArrayList();
            String str = null;
            int i = 0;
            while (i < this.pathList.size()) {
                File file2 = new File(this.pathList.get(i).path);
                String mediaType = this.pathList.get(i).getMediaType();
                if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    str = FolderPath.SDCARD_PATH_IMAGE;
                } else if (mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    str = FolderPath.SDCARD_PATH_VIDEO;
                }
                String str2 = str;
                File file3 = new File(str2 + File.separator + file2.getName());
                String parent = file3.getParent();
                try {
                    FileUtils.CopyMoveFile(file2, file3);
                    long j = this.pathList.get(i).id;
                    String str3 = this.pathList.get(i).name;
                    String str4 = this.pathList.get(i).path;
                    listString.add(str4);
                    arrayList.add(new DataFileModel(j, str3, str4, file3.getPath(), parent, false));
                    if (this.pathList.size() - 1 == i) {
                        for (int i2 = 0; i2 < this.pathList.size(); i2++) {
                            new File(this.pathList.get(i2).path).delete();
                            ContentResolver contentResolver = PrPrivateMainFragment.this.getActivity().getContentResolver();
                            if (mediaType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            } else {
                                uri = mediaType.equals(ExifInterface.GPS_MEASUREMENT_3D) ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : null;
                            }
                            try {
                                contentResolver.delete(uri, "_data='" + file.getPath() + "'", null);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        MoMomentFragment.momentAdapter.RemoveItem(this.pathList);
                        PrPrivateMainFragment.this.preferenceClass.putListString(Common.gOldPath, listString);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                i++;
                str = str2;
            }
            return null;
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            PrPrivateMainFragment.this.progressLock.dismiss();
            Degu_MainActivity.checkLockForFirstFile = false;
            if (Common.checkPreview) {
                PreviewActivity.allImagesList.remove(PreviewActivity.viewPager.getCurrentItem());
                AppUtilsClass.RefreshPhotoVideo(PrPrivateMainFragment.this.getActivity());
            } else {
                AppUtilsClass.RefreshImageAlbum(PrPrivateMainFragment.this.getActivity());
                AppUtilsClass.RefreshVideoAlbum(PrPrivateMainFragment.this.getActivity());
            }
            Toast.makeText(PrPrivateMainFragment.this.getActivity(), "Files moved to vault successfully.", 0).show();
        }
    }
}
