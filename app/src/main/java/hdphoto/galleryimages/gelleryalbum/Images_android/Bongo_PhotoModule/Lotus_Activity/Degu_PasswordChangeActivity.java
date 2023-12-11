package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

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
import hdphoto.galleryimages.gelleryalbum.R;

import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;


public class Degu_PasswordChangeActivity extends BseActivity {
    static final String TAG = "PasswordChangeActivity";
    int enterPin = 0;
    ImageView hideBtnPin1;
    ImageView hideBtnPin2;
    ImageView hideBtnPin3;
    ImageView hideBtnPin4;
    ImageView imgBack;
    ImageView imgClearNumber;
    ImageView imgNumber0;
    ImageView num1;
    ImageView num2;
    ImageView num3;
    ImageView num4;
    ImageView num5;
    ImageView num6;
    ImageView num7;
    ImageView num8;
    ImageView num9;
    LinearLayout lyPinLockView;
    LinearLayout lyShakeAnimationgallery;
    ArrayList<String> origanalPin;
    ArrayList<String> pinArray;
    TextView txt_hint;
    TextView txt_toolbarTitle;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_password_change);
        this.pinArray = new ArrayList<>();
        this.preferenceClass = new PrefClass(getApplicationContext());
        binding();
    }

    private void binding() {
        this.imgBack = (ImageView) findViewById(R.id.btnBack);
        TextView textView = (TextView) findViewById(R.id.toolbarTitle);
        this.txt_toolbarTitle = textView;
        textView.setText("Change Password");
        this.lyShakeAnimationgallery =  findViewById(R.id.layoutShakeAnimation);
        this.lyPinLockView =  findViewById(R.id.pinLockViewLayout);
        this.txt_hint = (TextView) findViewById(R.id.txtHintTextPassword);
        this.hideBtnPin1 =  findViewById(R.id.hidePin1);
        this.hideBtnPin2 =  findViewById(R.id.hidePin2);
        this.hideBtnPin3 =  findViewById(R.id.hidePin3);
        this.hideBtnPin4 =  findViewById(R.id.hidePin4);
        this.num1 =  findViewById(R.id.btnNumber1);
        this.num2 =  findViewById(R.id.btnNumber2);
        this.num3 =  findViewById(R.id.btnNumber3);
        this.num4 =  findViewById(R.id.btnNumber4);
        this.num5 =  findViewById(R.id.btnNumber5);
        this.num6 =  findViewById(R.id.btnNumber6);
        this.num7 =  findViewById(R.id.btnNumber7);
        this.num8 =  findViewById(R.id.btnNumber8);
        this.num9 =  findViewById(R.id.btnNumber9);
        this.imgNumber0 =  findViewById(R.id.btnNumber0);
        this.imgClearNumber =  findViewById(R.id.btnClearNumber);
        this.txt_hint.setText("Enter Old Password.");
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
                Degu_PasswordChangeActivity.this.CheckSecurityPassword("0");
            }
        });
        this.num1.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            }
        });
        this.num2.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword(ExifInterface.GPS_MEASUREMENT_2D);
            }
        });
        this.num3.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword(ExifInterface.GPS_MEASUREMENT_3D);
            }
        });
        this.num4.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword("4");
            }
        });
        this.num5.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword("5");
            }
        });
        this.num6.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword("6");
            }
        });
        this.num7.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword("7");
            }
        });
        this.num8.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword("8");
            }
        });
        this.num9.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.CheckSecurityPassword("9");
            }
        });
        this.imgClearNumber.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_PasswordChangeActivity.this.ClearPassWord();
            }
        });
    }

    @Override 
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this,true);
    }

    public void ClearPassWord() {
        if (this.pinArray.size() == 0) {
            this.txt_hint.setText("Enter Password.");
            this.pinArray.clear();
        } else if (this.pinArray.size() == 1) {
            this.hideBtnPin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList = this.pinArray;
            arrayList.remove(arrayList.get(0));
        } else if (this.pinArray.size() == 2) {
            this.hideBtnPin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList2 = this.pinArray;
            arrayList2.remove(arrayList2.get(0));
        } else if (this.pinArray.size() == 3) {
            this.hideBtnPin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList3 = this.pinArray;
            arrayList3.remove(arrayList3.get(0));
        } else if (this.pinArray.size() == 4) {
            this.hideBtnPin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            ArrayList<String> arrayList4 = this.pinArray;
            arrayList4.remove(arrayList4.get(0));
        }
    }

    public void CheckSecurityPassword(String str) {
        this.pinArray.add(str);
        if (this.pinArray.size() == 1) {
            this.hideBtnPin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
        } else if (this.pinArray.size() == 2) {
            this.hideBtnPin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
        } else if (this.pinArray.size() == 3) {
            this.hideBtnPin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
        } else if (this.pinArray.size() == 4) {
            this.hideBtnPin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_fill));
            UnlockPassword();
        }
    }

    private void UnlockPassword() {
        new ArrayList();
        if (!this.pinArray.equals(this.preferenceClass.getListString(Common.gCurrentPassword))) {
            int i = this.enterPin;
            if (i == 1) {
                this.preferenceClass.putListString(Common.gTempPassword, this.pinArray);
                this.pinArray.clear();
                this.txt_hint.setText("Confirm Password.");
                this.hideBtnPin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.hideBtnPin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.hideBtnPin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.hideBtnPin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.enterPin = 2;
            } else if (i != 2) {
                Toast.makeText(getApplicationContext(), "Invalid Password.", 0).show();
                this.txt_hint.setText("Enter old Password.");
                this.pinArray.clear();
                this.lyShakeAnimationgallery.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_anim));
                ((Vibrator) getSystemService("vibrator")).vibrate(800L);
                this.hideBtnPin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.hideBtnPin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.hideBtnPin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
                this.hideBtnPin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            } else if (getStingListToString(this.preferenceClass.getListString(Common.gTempPassword)).equals(getStingListToString(this.pinArray))) {
                this.preferenceClass.putListString(Common.gCurrentPassword, this.pinArray);
                this.preferenceClass.putInt(Common.gIsLockStatus, 2);
                StoreData(this.pinArray);
                finish();
            } else {
                if (this.enterPin == 2) {
                    this.enterPin = 1;
                    Toast.makeText(getApplicationContext(), "Invalid Confirm Password.", 0).show();
                    this.txt_hint.setText("Enter New Password.");
                } else {
                    this.enterPin = 0;
                }
                ResetAll();
            }
        } else if (this.enterPin == 0) {
            ArrayList<String> arrayList = this.pinArray;
            this.origanalPin = arrayList;
            arrayList.clear();
            this.txt_hint.setText("Enter New Password.");
            this.hideBtnPin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.hideBtnPin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.hideBtnPin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.hideBtnPin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
            this.enterPin = 1;
        } else {
            this.enterPin = 1;
            Toast.makeText(this, "You can't use old Password as current Password.", 0).show();
            Toast.makeText(this, "Set Diffrent Password.", 0).show();
            ResetAll();
        }
    }

    private void ResetAll() {
        this.pinArray.clear();
        this.lyShakeAnimationgallery.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_anim));
        ((Vibrator) getSystemService("vibrator")).vibrate(800L);
        this.hideBtnPin1.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.hideBtnPin2.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.hideBtnPin3.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
        this.hideBtnPin4.setBackground(getResources().getDrawable(R.drawable.lock_ring_null));
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
            Log.e(TAG, "createFile: Sucsess " + fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
