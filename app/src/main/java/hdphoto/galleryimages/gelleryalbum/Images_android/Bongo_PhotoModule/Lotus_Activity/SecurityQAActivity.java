package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class SecurityQAActivity extends BseActivity {
    public static SecurityQAActivity securityQuetionScreen;
    EditText edtAnswer;
    EditText edtEmail;
    String fromActivity;
    ImageView imgBack;
    ImageView img_Snap;
    PrefClass preferenceClass;
    RelativeLayout rl_screen;
    Toolbar toolbar;
    TextView txtSubmit;
    TextView txtToolbarTitle;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMSecurityQAActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SecurityQAActivity gMSecurityQAActivity = SecurityQAActivity.this;
            gMSecurityQAActivity.unregisterReceiver(gMSecurityQAActivity.broadcastReceiver);
            if (Common.IdentifyActivity.equals("PasswordLockActivity")) {
                Intent intent2 = new Intent(SecurityQAActivity.this, Degu_PasswordLockActivity.class);
                intent2.putExtra(Common.gActivityname, "SecurityQAActivity");
                SecurityQAActivity.this.startActivity(intent2);
                Common.IdentifyActivity = "";
                SecurityQAActivity.this.finish();

            }
        }
    };
    String question = "null";

    @Override
    public void onPointerCaptureChanged(boolean z) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_security_question);
        securityQuetionScreen = this;
        this.imgBack = (ImageView) findViewById(R.id.btnBack);
        TextView textView = (TextView) findViewById(R.id.toolbarTitle);
        this.txtToolbarTitle = textView;
        textView.setText("Security Question");
        this.txtSubmit = (TextView) findViewById(R.id.btnSubmit);
        this.edtAnswer = (EditText) findViewById(R.id.editAnswer);
        this.edtEmail = (EditText) findViewById(R.id.editEmail);
        this.img_Snap = (ImageView) findViewById(R.id.img_Snap);
        this.rl_screen = (RelativeLayout) findViewById(R.id.rl_screen);
        this.preferenceClass = new PrefClass(getApplicationContext());
        this.fromActivity = getIntent().getStringExtra("toActivity");
        SetSpinner();
        Click_Event();
        new NativeHelper().shownativeads(this, findViewById(R.id.native_container));
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));

    }

    private void Click_Event() {
        this.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SecurityQAActivity.this.question.equals("Select Question")) {
                    Toast.makeText(SecurityQAActivity.this, "Please select question.", 0).show();
                } else if (SecurityQAActivity.this.edtAnswer.getText() == null) {
                    Toast.makeText(SecurityQAActivity.this, "Please enter answer.", 0).show();
                } else if (SecurityQAActivity.this.edtAnswer.getText().length() >= 4) {
                    SecurityQAActivity.this.preferenceClass.putString(Common.gQuestion, SecurityQAActivity.this.question);
                    SecurityQAActivity.this.preferenceClass.putString(Common.gQuestionAnswer, SecurityQAActivity.this.edtAnswer.getText().toString().trim());
                    SecurityQAActivity.this.preferenceClass.putString("email", SecurityQAActivity.this.edtEmail.getText().toString().trim());
                    if (SecurityQAActivity.this.fromActivity == null) {
                        SecurityQAActivity gMSecurityQAActivity = SecurityQAActivity.this;
                        gMSecurityQAActivity.registerReceiver(gMSecurityQAActivity.broadcastReceiver, new IntentFilter("PasswordLockActivity"));
                        Common.IdentifyActivity = "PasswordLockActivity";
                        BseActivity.activity.sendBroadcast(new Intent(Common.IdentifyActivity));
                    } else if (SecurityQAActivity.this.fromActivity.equals("SettingActivity")) {
                        AdInterGD.getInstance().showInter(SecurityQAActivity.this, new AdInterGD.MyCallback() {
                            @Override
                            public void callbackCall() {
                                SecurityQAActivity.this.finish();
                            }
                        });
                    } else {
                        SecurityQAActivity gMSecurityQAActivity2 = SecurityQAActivity.this;
                        gMSecurityQAActivity2.registerReceiver(gMSecurityQAActivity2.broadcastReceiver, new IntentFilter("PasswordLockActivity"));
                        Common.IdentifyActivity = "PasswordLockActivity";
                        BseActivity.activity.sendBroadcast(new Intent(Common.IdentifyActivity));
                    }
                } else {
                    Toast.makeText(SecurityQAActivity.this, "atleast enter 4 character in security answer", 0).show();
                }
            }
        });
        this.img_Snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SecurityQAActivity.this.question.equals("Select Question")) {
                    Toast.makeText(SecurityQAActivity.this, "Please select question.", 0).show();
                } else if (SecurityQAActivity.this.edtAnswer.getText() == null) {
                    Toast.makeText(SecurityQAActivity.this, "Please enter answer.", 0).show();
                } else if (SecurityQAActivity.this.edtAnswer.getText().length() >= 4) {
                    SecurityQAActivity.this.edtAnswer.setFocusable(false);
                    SecurityQAActivity gMSecurityQAActivity = SecurityQAActivity.this;
                    gMSecurityQAActivity.TakeSnap(gMSecurityQAActivity.rl_screen);
                } else {
                    Toast.makeText(SecurityQAActivity.this, "At least enter 4 character in security answer.", 0).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this, true);

    }

    public void TakeSnap(RelativeLayout relativeLayout) {
        relativeLayout.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(relativeLayout.getDrawingCache());
        relativeLayout.setDrawingCacheEnabled(false);
        File file = new File(FolderPath.SAVE_SECURITY_QA_IMAGE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(FolderPath.SAVE_SECURITY_QA_IMAGE_PATH + File.separator + "SecurityFile.png");
        StringBuilder sb = new StringBuilder();
        sb.append(" --- ");
        sb.append(file2.getAbsolutePath());
        Log.d("REFRESH", sb.toString());
        if (file2.exists()) {
            file2.delete();
        } else {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            createBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".provider", file2);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            startActivity(Intent.createChooser(intent, "Share IMAGE"));
        } catch (Exception e3) {
            e3.printStackTrace();
            Toast.makeText(this, "Something Went Wrong.", 0).show();
        }
    }

    public void SetSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCustom);
        ArrayList arrayList = new ArrayList();
        arrayList.add("Select Question");
        arrayList.add("What was your childhood nickname?");
        arrayList.add("In what city or town did your parent meet?");
        arrayList.add("What is your favorite movie?");
        arrayList.add("What was your favorite food as a child?");
        arrayList.add("Who is your childhood sports hero?");
        arrayList.add("In what town was your first job?");
        spinner.setAdapter((SpinnerAdapter) new CustomSpinnerAdapter(this, arrayList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                SecurityQAActivity.this.question = adapterView.getItemAtPosition(i).toString();
            }
        });
    }


    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
        protected ArrayList<String> arrayList;

        @Override
        public long getItemId(int i) {
            return i;
        }

        public CustomSpinnerAdapter(Context context, ArrayList<String> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return this.arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return this.arrayList.get(i);
        }

        @Override
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(SecurityQAActivity.this);
            textView.setPadding(18, 18, 18, 18);
            textView.setTextSize(15.0f);
            textView.setGravity(16);
            textView.setText(this.arrayList.get(i));
            textView.setTextColor(SecurityQAActivity.this.getResources().getColor(R.color.black));
            textView.setBackgroundColor(SecurityQAActivity.this.getResources().getColor(R.color.white));
            return textView;
        }

        @Override 
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(SecurityQAActivity.this);
            textView.setGravity(16);
            textView.setPadding(35, 18, 35, 18);
            textView.setTextSize(15.0f);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdownarrow, 0);
            textView.setText(this.arrayList.get(i));
            return textView;
        }
    }
}
