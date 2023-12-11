package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;


public class Degu_PasswordLockActivity extends BseActivity {
    private static final String TAG = "PasswordLockActivity";
    String activityName;
    ImageView imgBack;
    ImageView imgClearNumber;
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
    LinearLayout llShakeAnimation;
    ArrayList<String> pinArray;
    boolean pinConfirm = false;
    ImageView pinLockBtn1;
    ImageView pinLockBtn2;
    ImageView pinLockBtn3;
    ImageView pinLockBtn4;
    TextView txtHintTextPassword;
    TextView txtToolbarTitle;
    TextView txt_confirm;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_password_lock);
        this.pinArray = new ArrayList<>();
        ID_Binding();
        this.txtHintTextPassword.setText("Add new Password.");
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        this.activityName = intent.getStringExtra(Common.gActivityname);
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

    }

    private void ID_Binding() {
        this.imgBack = findViewById(R.id.btnBack);
        this.txt_confirm =  findViewById(R.id.txt_confirm);
        TextView textView =  findViewById(R.id.toolbarTitle);
        this.txtToolbarTitle = textView;
        textView.setText("Set Password");
        this.llShakeAnimation = findViewById(R.id.layoutShakeAnimation);
        this.txtHintTextPassword = findViewById(R.id.txtHintTextPassword);
        this.pinLockBtn1 =  findViewById(R.id.hidePin1);
        this.pinLockBtn2 =  findViewById(R.id.hidePin2);
        this.pinLockBtn3 =  findViewById(R.id.hidePin3);
        this.pinLockBtn4 =  findViewById(R.id.hidePin4);
        this.imgNumber0 =  findViewById(R.id.btnNumber0);
        this.imgNumber1 =  findViewById(R.id.btnNumber1);
        this.imgNumber2 =  findViewById(R.id.btnNumber2);
        this.imgNumber3 =  findViewById(R.id.btnNumber3);
        this.imgNumber4 =  findViewById(R.id.btnNumber4);
        this.imgNumber5 =  findViewById(R.id.btnNumber5);
        this.imgNumber6 =  findViewById(R.id.btnNumber6);
        this.imgNumber7 =  findViewById(R.id.btnNumber7);
        this.imgNumber8 =  findViewById(R.id.btnNumber8);
        this.imgNumber9 =  findViewById(R.id.btnNumber9);
        this.imgClearNumber =  findViewById(R.id.btnClearNumber);
        Click_Event();
    }

    private void Click_Event() {
        this.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.imgNumber0.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword("0");
            }
        });
        this.imgNumber1.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            }
        });
        this.imgNumber2.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword(ExifInterface.GPS_MEASUREMENT_2D);
            }
        });
        this.imgNumber3.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword(ExifInterface.GPS_MEASUREMENT_3D);
            }
        });
        this.imgNumber4.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword("4");
            }
        });
        this.imgNumber5.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword("5");
            }
        });
        this.imgNumber6.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword("6");
            }
        });
        this.imgNumber7.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword("7");
            }
        });
        this.imgNumber8.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword("8");
            }
        });
        this.imgNumber9.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.CheckPassword("9");
            }
        });
        this.imgClearNumber.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordLockActivity.this.ClearPassword();
            }
        });
    }

    @Override 
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this,true);

    }

    public void ClearPassword() {
        if (this.pinArray.size() == 0) {
            this.txtHintTextPassword.setText("Add New Password.");
            this.pinArray.clear();
            this.pinConfirm = false;
        } else if (this.pinArray.size() == 1) {
            this.pinLockBtn1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList = this.pinArray;
            arrayList.remove(arrayList.get(0));
        } else if (this.pinArray.size() == 2) {
            this.pinLockBtn2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList2 = this.pinArray;
            arrayList2.remove(arrayList2.get(0));
        } else if (this.pinArray.size() == 3) {
            this.pinLockBtn3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList3 = this.pinArray;
            arrayList3.remove(arrayList3.get(0));
        } else if (this.pinArray.size() == 4) {
            this.pinLockBtn4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList4 = this.pinArray;
            arrayList4.remove(arrayList4.get(0));
        }
        this.preferenceClass.putListString(Common.gCurrentPassword, this.pinArray);
    }

    public void CheckPassword(String str) {
        this.pinArray.add(str);
        if (this.pinArray.size() == 1) {
            this.pinLockBtn1.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
        } else if (this.pinArray.size() == 2) {
            this.pinLockBtn2.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
        } else if (this.pinArray.size() == 3) {
            this.pinLockBtn3.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
        } else if (this.pinArray.size() == 4) {
            this.pinLockBtn4.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
            if (this.pinConfirm) {
                Log.d("PINNNN", " - CheckPassword - " + this.pinConfirm);
                new ArrayList();
                if (this.pinArray.equals(this.preferenceClass.getListString(Common.gCurrentPassword))) {
                    Degu_MainActivity.checkLockForFirstFile = true;
                    Toast.makeText(this, "Password Set Successfully", 0).show();
                    this.preferenceClass.putInt(Common.gIsLockStatus, 1);
                    if (this.activityName.equals("SecurityQAActivity")) {
                        SecurityQAActivity.securityQuetionScreen.finish();
                    } else if (this.activityName.equals("ForgotPasswordActivity")) {
                        Degu_ForgotPasswordActivity.forgotPasswordActivity.finish();
                    }
                    StoreData(this.pinArray);
                    finish();
                    return;
                }
                Toast.makeText(this, "No match Password.", 0).show();
                this.txtHintTextPassword.setText("Add New Password.");
                this.pinArray.clear();
                this.llShakeAnimation.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_anim));
                ((Vibrator) getSystemService("vibrator")).vibrate(800L);
                this.pinLockBtn1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.pinLockBtn2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.pinLockBtn3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.pinLockBtn4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.pinConfirm = false;
                return;
            }
            Log.d("PINNNN", " - CheckPassword - " + this.pinArray.toString());
            this.preferenceClass.putListString(Common.gCurrentPassword, this.pinArray);
            Log.e(TAG, "save pin: " + this.pinArray.size());
            this.pinArray.clear();
            this.txtHintTextPassword.setText("Confirm Password.");
            ConfirmPassword();
        }
    }

    private void StoreData(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        CreateFile(sb.toString());
    }

    public void CreateFile(String str) {
        try {
            FileWriter fileWriter = new FileWriter(AppUtilsClass.rootMainDir + "/.password.txt");
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ConfirmPassword() {
        Log.d("PINNNN", " - ConfirmPassword - " + this.pinConfirm);
        this.pinLockBtn1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.pinLockBtn2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.pinLockBtn3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.pinLockBtn4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.pinConfirm = true;
    }
}
