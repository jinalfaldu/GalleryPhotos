package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.Common;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.PrefClass;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Degu_ForgotPasswordActivity extends BseActivity implements View.OnClickListener {
    private static final String TAG = "ForgotPasswordActivity";
    public static Degu_ForgotPasswordActivity forgotPasswordActivity;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: hdphoto.galleryimages.gelleryalbum.android.GalleryModule.GMActivity.GMForgotPasswordActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Degu_ForgotPasswordActivity gMForgotPasswordActivity = Degu_ForgotPasswordActivity.this;
            gMForgotPasswordActivity.unregisterReceiver(gMForgotPasswordActivity.broadcastReceiver);
            if (Common.IdentifyActivity.equals("PasswordLockActivity")) {
                        Intent intent2 = new Intent(Degu_ForgotPasswordActivity.this, Degu_PasswordLockActivity.class);
                        intent2.putExtra(Common.gActivityname, Degu_ForgotPasswordActivity.TAG);
                        Degu_ForgotPasswordActivity.this.startActivity(intent2);
                        Common.IdentifyActivity = "";

            }
        }
    };
    EditText edtEmailbackup;
    EditText edtSecurityAnswer;
    EditText edtSecurityQuestion;
    ImageView imgBack;
    ImageView img_Snap;
    PrefClass preferenceClass;
    RelativeLayout rl_screen;
    TextView txtSubmit;
    TextView txtToolbarTitle;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_forget_password);
        forgotPasswordActivity = this;
        this.preferenceClass = new PrefClass(getApplicationContext());
        ID_Binding();
        this.edtSecurityQuestion.setText(this.preferenceClass.getString(Common.gQuestion));
    }

    private void ID_Binding() {
        this.img_Snap = (ImageView) findViewById(R.id.img_Snap);
        this.rl_screen = (RelativeLayout) findViewById(R.id.rl_screen);
        this.imgBack = (ImageView) findViewById(R.id.btnBack);
        TextView textView = (TextView) findViewById(R.id.toolbarTitle);
        this.txtToolbarTitle = textView;
        textView.setText("Forgot Password");
        this.edtSecurityQuestion = (EditText) findViewById(R.id.edit_security_question);
        this.edtSecurityAnswer = (EditText) findViewById(R.id.edit_security_answer);
        this.edtEmailbackup = (EditText) findViewById(R.id.editEmail);
        TextView textView2 = (TextView) findViewById(R.id.btnSubmitForgetPassword);
        this.txtSubmit = textView2;
        textView2.setOnClickListener(this);
        this.edtSecurityAnswer.addTextChangedListener(new TextWatcher() {
            @Override 
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override 
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override 
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    Degu_ForgotPasswordActivity.this.edtEmailbackup.setText("");
                    Degu_ForgotPasswordActivity.this.edtEmailbackup.setEnabled(true);
                    return;
                }
                Degu_ForgotPasswordActivity.this.edtEmailbackup.setEnabled(true);
            }
        });
        this.edtEmailbackup.addTextChangedListener(new TextWatcher() {
            @Override 
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override 
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    Degu_ForgotPasswordActivity.this.edtSecurityAnswer.setText("");
                    Degu_ForgotPasswordActivity.this.edtSecurityAnswer.setEnabled(false);
                    return;
                }
                Degu_ForgotPasswordActivity.this.edtSecurityAnswer.setEnabled(true);
            }
        });
        this.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Degu_ForgotPasswordActivity.this.onBackPressed();
            }
        });
        this.img_Snap.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                String string = Degu_ForgotPasswordActivity.this.preferenceClass.getString(Common.gQuestionAnswer);
                if (Degu_ForgotPasswordActivity.this.edtSecurityAnswer.getText() == null) {
                    Toast.makeText(Degu_ForgotPasswordActivity.this, "Please enter answer.", 0).show();
                } else if (Degu_ForgotPasswordActivity.this.edtSecurityAnswer.getText().toString().trim().equals(string)) {
                    Degu_ForgotPasswordActivity.this.edtSecurityAnswer.setFocusable(false);
                    Degu_ForgotPasswordActivity gMForgotPasswordActivity = Degu_ForgotPasswordActivity.this;
                    gMForgotPasswordActivity.TakeSnap(gMForgotPasswordActivity.rl_screen);
                } else {
                    Toast.makeText(Degu_ForgotPasswordActivity.this, "Invalid Answer. Please Enter Correct Answer", 0).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        OnBackPressed.onBackPressed(this,true);


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

    @Override 
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnBack) {
            onBackPressed();
        } else if (id == R.id.btnSubmitForgetPassword) {
            RecoverSecurityPassword();
        }
    }

    private void RecoverSecurityPassword() {
        if (this.edtSecurityAnswer.isEnabled()) {
            if (this.edtSecurityAnswer.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter Answer.", 0).show();
            } else if (this.edtSecurityAnswer.getText().toString().trim().equals(this.preferenceClass.getString(Common.gQuestionAnswer))) {
                new ArrayList();
                ArrayList<String> listString = this.preferenceClass.getListString(Common.gCurrentPassword);
                StringBuilder sb = new StringBuilder();
                Iterator<String> it = listString.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                }
                String sb2 = sb.toString();
                registerReceiver(this.broadcastReceiver, new IntentFilter("PasswordLockActivity"));
                Common.IdentifyActivity = "PasswordLockActivity";
                activity.sendBroadcast(new Intent(Common.IdentifyActivity));
            } else {
                Toast.makeText(this, "Invalid Answer. Please Enter Correct Answer", 0).show();
            }
        }
    }
}
