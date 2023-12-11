package hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.os.EnvironmentCompat;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.NativeHelper80;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataModel.DataModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataModel.Duplicate;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataModel.TypeFile;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.Utils.Utils;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.BseActivity;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class DataScanActivity extends BseActivity {
    public static ArrayList<DataModel> mListData = new ArrayList<>();
    Dialog analyzeDialog;
    ImageView btnBack;
    HashMap<String, ArrayList<File>> fileTypeArray;
    boolean getProcess;
    LinearLayout ll_scanner;
    ScanImagesAsyncTask mScanImagesAsyncTask;
    RelativeLayout rl_noData;
    TextView textDesc;
    TextView textProgress;
    TextView txtTitle;
    int getScanType = 0;
    String getScanData = null;
    HashMap<String, ArrayList<File>> mListAudio = new HashMap<>();
    HashMap<String, ArrayList<File>> mListDoc = new HashMap<>();
    HashMap<String, ArrayList<File>> mListImage = new HashMap<>();
    HashMap<String, ArrayList<File>> mListOtherFile = new HashMap<>();
    HashMap<String, ArrayList<File>> mListVideo = new HashMap<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dm_scan);
        getIntentData();
        ID_Binding();

        this.fileTypeArray = new HashMap<>();
        new NativeHelper80().shownativeads(this, findViewById(R.id.banner_container_80));
    }

    private void ID_Binding() {
        this.textProgress = (TextView) findViewById(R.id.textProgress);
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.txtTitle = (TextView) findViewById(R.id.txtTitle);
        this.textDesc = (TextView) findViewById(R.id.textDesc);
        this.ll_scanner = (LinearLayout) findViewById(R.id.ll_scanner);
        this.rl_noData = (RelativeLayout) findViewById(R.id.rl_noData);
        Click_Event();
    }

    private void Click_Event() {
        TextView textView = this.txtTitle;
        textView.setText(this.getScanData + " Analyzing Data");
        TextView textView2 = this.textDesc;
        textView2.setText("Please wait " + this.getScanData + " " + getString(R.string.analyzing));
        this.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                DataScanActivity.this.onBackPressed();
            }
        });
        ScanFile(this.getScanType, this.getScanData);
    }

    private void getIntentData() {
        this.getScanType = getIntent().getIntExtra("ScanType", 0);
        this.getScanData = getIntent().getStringExtra("ScanData");
        this.getProcess = getIntent().getBooleanExtra("CheckProcess", false);
    }

    @Override 
    public void onBackPressed() {
       
                if (DataScanActivity.this.getProcess) {
                    DataScanActivity.this.mScanImagesAsyncTask.cancel(true);
                }
        OnBackPressed.onBackPressed(this,true);
           
    }

    public void CollectRequiredFilesContent() {
        this.fileTypeArray.put(getString(R.string.apk), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.zip), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.vcf), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.mp3), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.aac), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.amr), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.m4a), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.ogg), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.wav), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.flac), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string._3gp), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.mp4), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.mkv), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.webm), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.jpg), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.jpeg), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.png), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.bmp), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.gif), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.doc), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.docx), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.html), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.pdf), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.txt), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.xml), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.xlsx), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.js), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.css), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.dat), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.cache), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.nomedia), new ArrayList<>());
        this.fileTypeArray.put(getString(R.string.emptyshow), new ArrayList<>());
    }

    public void ScanFile(int i, String str) {
        ScanImagesAsyncTask scanImagesAsyncTask = this.mScanImagesAsyncTask;
        if (scanImagesAsyncTask == null || scanImagesAsyncTask.getStatus() != AsyncTask.Status.RUNNING) {
            mListData.clear();
            this.ll_scanner.setVisibility(0);
            this.rl_noData.setVisibility(8);
            ScanImagesAsyncTask scanImagesAsyncTask2 = new ScanImagesAsyncTask(i);
            this.mScanImagesAsyncTask = scanImagesAsyncTask2;
            scanImagesAsyncTask2.execute(new String[0]);
            return;
        }
        Toast.makeText(this, getString(R.string.scan_wait), 0).show();
    }

    private void AnalyzeDialog(String str) {
        Dialog dialog = new Dialog(this, R.style.ThemeWithCorners1);
        this.analyzeDialog = dialog;
        dialog.requestWindowFeature(1);
        this.analyzeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.analyzeDialog.setContentView(R.layout.dg_file_scane);
        this.analyzeDialog.setTitle("Scan File");
        this.analyzeDialog.setCancelable(false);
        this.analyzeDialog.setCanceledOnTouchOutside(false);
        ((TextView) this.analyzeDialog.findViewById(R.id.textDesc)).setText("Please wait " + str + " " + getString(R.string.analyzing));
    }


    public class ScanImagesAsyncTask extends AsyncTask<String, Integer, ArrayList<DataModel>> {
        int typeScan;

        public ScanImagesAsyncTask(int i) {
            this.typeScan = 0;
            this.typeScan = i;
        }

        @Override 
        public void onPreExecute() {
            super.onPreExecute();
        }

        @Override 
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate((Integer[]) numArr);
            Log.d("fdgfdg", " - " + numArr[0]);
            TextView textView = DataScanActivity.this.textProgress;
            textView.setText("" + numArr[0]);
        }

        @Override 
        public ArrayList<DataModel> doInBackground(String... strArr) {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            DataScanActivity.this.fileTypeArray.clear();
            DataScanActivity.this.CollectRequiredFilesContent();
            DataScanActivity.this.CheckFileOfDirectory(Utils.getFileList(absolutePath));
            DataScanActivity.this.GetSdCard();
            HashMap<String, ArrayList<File>> GetMediaGroup = DataScanActivity.this.GetMediaGroup(this.typeScan);
            ArrayList arrayList = new ArrayList(GetMediaGroup.keySet());
            int i = 1;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                HashMap<Long, ArrayList<File>> FindExactDuplicates = DataScanActivity.this.FindExactDuplicates(GetMediaGroup.get(arrayList.get(i2)));
                if (FindExactDuplicates != null && FindExactDuplicates.size() > 0) {
                    Iterator it = new ArrayList(FindExactDuplicates.keySet()).iterator();
                    while (it.hasNext()) {
                        DataModel dMDataModel = new DataModel();
                        ArrayList<File> arrayList2 = FindExactDuplicates.get((Long) it.next());
                        if (arrayList2 != null && arrayList2.size() > 0) {
                            dMDataModel.setTitleGroup("Group: " + Integer.valueOf(i));
                            ArrayList<Duplicate> arrayList3 = new ArrayList<>();
                            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                                Duplicate dMDuplicate = new Duplicate();
                                dMDuplicate.setFile(arrayList2.get(i3));
                                dMDuplicate.setTypeFile(TypeFile.getFileType(arrayList2.get(i3).getPath()));
                                if (i3 == 0) {
                                    dMDuplicate.setChecked(false);
                                }
                                arrayList3.add(dMDuplicate);
                            }
                            dMDataModel.setListDuplicate(arrayList3);
                            try {
                                Thread.sleep(130L);
                            } catch (Exception e) {
                                Log.d("Error: ", e.toString());
                            }
                            publishProgress(Integer.valueOf(i));
                            i++;
                        }
                        DataScanActivity.mListData.add(dMDataModel);
                    }
                }
            }
            return null;
        }

        @Override 
        public void onPostExecute(ArrayList<DataModel> arrayList) {
            super.onPostExecute(arrayList);
            new Handler().postDelayed(new Runnable() {
                @Override 
                public void run() {
                    if (DataScanActivity.mListData.size() == 0) {
                        Toast.makeText(DataScanActivity.this, DataScanActivity.this.getString(R.string.no_file_found), 0).show();
                        DataScanActivity.this.ll_scanner.setVisibility(8);
                        DataScanActivity.this.rl_noData.setVisibility(0);
                        return;
                    }
                  
                            Intent intent = new Intent(DataScanActivity.this, DataDuplicateActivity.class);
                            intent.putExtra("title_tool_bar", DataScanActivity.this.GetTitle(ScanImagesAsyncTask.this.typeScan));
                            DataScanActivity.this.startActivity(intent);
                            DataScanActivity.this.finish();
                        
                }
            }, 1000L);
        }
    }

    public void GetSdCard() {
        String[] GetExternalStorageDirectories = GetExternalStorageDirectories();
        if (GetExternalStorageDirectories == null || GetExternalStorageDirectories.length <= 0) {
            return;
        }
        for (String str : GetExternalStorageDirectories) {
            File file = new File(str);
            if (file.exists()) {
                CheckFileOfDirectory(file.listFiles());
            }
        }
    }

    public String[] GetExternalStorageDirectories() {
        byte[] bArr = new byte[0];
        File[] externalFilesDirs;
        String[] split;
        boolean equals;
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19 && (externalFilesDirs = getExternalFilesDirs(null)) != null && externalFilesDirs.length > 0) {
            for (File file : externalFilesDirs) {
                if (file != null && (split = file.getPath().split("/Android")) != null && split.length > 0) {
                    String str = split[0];
                    if (Build.VERSION.SDK_INT >= 21) {
                        equals = Environment.isExternalStorageRemovable(file);
                    } else {
                        equals = "mounted".equals(EnvironmentCompat.getStorageState(file));
                    }
                    if (equals) {
                        arrayList.add(str);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            String str2 = "";
            try {
                Process start = new ProcessBuilder(new String[0]).command("mount | grep /dev/block/vold").redirectErrorStream(true).start();
                start.waitFor();
                InputStream inputStream = start.getInputStream();
                while (inputStream.read(new byte[1024]) != -1) {
                    str2 = str2 + new String(bArr);
                }
                inputStream.close();
            } catch (Exception unused) {
            }
            if (!str2.trim().isEmpty()) {
                String[] split2 = str2.split("\n");
                if (split2.length > 0) {
                    for (String str3 : split2) {
                        arrayList.add(str3.split(" ")[2]);
                    }
                }
            }
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }

    public void CheckFileOfDirectory(File[] fileArr) {
        if (fileArr != null) {
            for (int i = 0; i < fileArr.length; i++) {
                if (fileArr[i].isDirectory()) {
                    CheckFileOfDirectory(Utils.getFileList(fileArr[i].getPath()));
                } else {
                    DetectFileTypeAndAddInCategory(fileArr[i]);
                }
            }
        }
    }

    private void DetectFileTypeAndAddInCategory(File file) {
        ArrayList<File> arrayList;
        String name = file.getName();
        if (name.endsWith(".apk")) {
            ArrayList<File> arrayList2 = this.fileTypeArray.get(getString(R.string.apk));
            if (arrayList2 != null) {
                arrayList2.add(file);
            }
        } else if (name.endsWith(".zip")) {
            ArrayList<File> arrayList3 = this.fileTypeArray.get(getString(R.string.zip));
            if (arrayList3 != null) {
                arrayList3.add(file);
            }
        } else if (name.endsWith(".vcf")) {
            ArrayList<File> arrayList4 = this.fileTypeArray.get(getString(R.string.vcf));
            if (arrayList4 != null) {
                arrayList4.add(file);
            }
        } else if (name.endsWith(".mp3")) {
            ArrayList<File> arrayList5 = this.fileTypeArray.get(getString(R.string.mp3));
            if (arrayList5 != null) {
                arrayList5.add(file);
            }
        } else if (name.endsWith(".aac")) {
            ArrayList<File> arrayList6 = this.fileTypeArray.get(getString(R.string.aac));
            if (arrayList6 != null) {
                arrayList6.add(file);
            }
        } else if (name.endsWith(".amr")) {
            ArrayList<File> arrayList7 = this.fileTypeArray.get(getString(R.string.amr));
            if (arrayList7 != null) {
                arrayList7.add(file);
            }
        } else if (name.endsWith(".m4a")) {
            ArrayList<File> arrayList8 = this.fileTypeArray.get(getString(R.string.m4a));
            if (arrayList8 != null) {
                arrayList8.add(file);
            }
        } else if (name.endsWith(".ogg")) {
            ArrayList<File> arrayList9 = this.fileTypeArray.get(getString(R.string.ogg));
            if (arrayList9 != null) {
                arrayList9.add(file);
            }
        } else if (name.endsWith(".wav")) {
            ArrayList<File> arrayList10 = this.fileTypeArray.get(getString(R.string.wav));
            if (arrayList10 != null) {
                arrayList10.add(file);
            }
        } else if (name.endsWith(".flac")) {
            ArrayList<File> arrayList11 = this.fileTypeArray.get(getString(R.string.flac));
            if (arrayList11 != null) {
                arrayList11.add(file);
            }
        } else if (name.endsWith(".3gp")) {
            ArrayList<File> arrayList12 = this.fileTypeArray.get(getString(R.string._3gp));
            if (arrayList12 != null) {
                arrayList12.add(file);
            }
        } else if (name.endsWith(".mp4")) {
            ArrayList<File> arrayList13 = this.fileTypeArray.get(getString(R.string.mp4));
            if (arrayList13 != null) {
                arrayList13.add(file);
            }
        } else if (name.endsWith(".mkv")) {
            ArrayList<File> arrayList14 = this.fileTypeArray.get(getString(R.string.mkv));
            if (arrayList14 != null) {
                arrayList14.add(file);
            }
        } else if (name.endsWith(".webm")) {
            ArrayList<File> arrayList15 = this.fileTypeArray.get(getString(R.string.webm));
            if (arrayList15 != null) {
                arrayList15.add(file);
            }
        } else if (name.endsWith(".jpg")) {
            ArrayList<File> arrayList16 = this.fileTypeArray.get(getString(R.string.jpg));
            if (arrayList16 != null) {
                arrayList16.add(file);
            }
        } else if (name.endsWith(".jpeg")) {
            ArrayList<File> arrayList17 = this.fileTypeArray.get(getString(R.string.jpeg));
            if (arrayList17 != null) {
                arrayList17.add(file);
            }
        } else if (name.endsWith(".png")) {
            ArrayList<File> arrayList18 = this.fileTypeArray.get(getString(R.string.png));
            if (arrayList18 != null) {
                arrayList18.add(file);
            }
        } else if (name.endsWith(".bmp")) {
            ArrayList<File> arrayList19 = this.fileTypeArray.get(getString(R.string.bmp));
            if (arrayList19 != null) {
                arrayList19.add(file);
            }
        } else if (name.endsWith(".gif")) {
            ArrayList<File> arrayList20 = this.fileTypeArray.get(getString(R.string.gif));
            if (arrayList20 != null) {
                arrayList20.add(file);
            }
        } else if (name.endsWith(".doc")) {
            ArrayList<File> arrayList21 = this.fileTypeArray.get(getString(R.string.doc));
            if (arrayList21 != null) {
                arrayList21.add(file);
            }
        } else if (name.endsWith(".docx")) {
            ArrayList<File> arrayList22 = this.fileTypeArray.get(getString(R.string.docx));
            if (arrayList22 != null) {
                arrayList22.add(file);
            }
        } else if (name.endsWith(".html")) {
            ArrayList<File> arrayList23 = this.fileTypeArray.get(getString(R.string.html));
            if (arrayList23 != null) {
                arrayList23.add(file);
            }
        } else if (name.endsWith(".pdf")) {
            ArrayList<File> arrayList24 = this.fileTypeArray.get(getString(R.string.pdf));
            if (arrayList24 != null) {
                arrayList24.add(file);
            }
        } else if (name.endsWith(".txt")) {
            ArrayList<File> arrayList25 = this.fileTypeArray.get(getString(R.string.txt));
            if (arrayList25 != null) {
                arrayList25.add(file);
            }
        } else if (name.endsWith(".xml")) {
            ArrayList<File> arrayList26 = this.fileTypeArray.get(getString(R.string.xml));
            if (arrayList26 != null) {
                arrayList26.add(file);
            }
        } else if (name.endsWith(".xlsx")) {
            ArrayList<File> arrayList27 = this.fileTypeArray.get(getString(R.string.xlsx));
            if (arrayList27 != null) {
                arrayList27.add(file);
            }
        } else if (name.endsWith(".js")) {
            ArrayList<File> arrayList28 = this.fileTypeArray.get(getString(R.string.js));
            if (arrayList28 != null) {
                arrayList28.add(file);
            }
        } else if (name.endsWith(".css")) {
            ArrayList<File> arrayList29 = this.fileTypeArray.get(getString(R.string.css));
            if (arrayList29 != null) {
                arrayList29.add(file);
            }
        } else if (name.endsWith(".dat")) {
            ArrayList<File> arrayList30 = this.fileTypeArray.get(getString(R.string.dat));
            if (arrayList30 != null) {
                arrayList30.add(file);
            }
        } else if (name.endsWith(".cache")) {
            ArrayList<File> arrayList31 = this.fileTypeArray.get(getString(R.string.cache));
            if (arrayList31 != null) {
                arrayList31.add(file);
            }
        } else if (name.endsWith(".nomedia")) {
            ArrayList<File> arrayList32 = this.fileTypeArray.get(getString(R.string.nomedia));
            if (arrayList32 != null) {
                arrayList32.add(file);
            }
        } else if (!name.endsWith(".emptyshow") || (arrayList = this.fileTypeArray.get(getString(R.string.emptyshow))) == null) {
        } else {
            arrayList.add(file);
        }
    }

    public HashMap<Long, ArrayList<File>> FindExactDuplicates(ArrayList<File> arrayList) {
        HashMap<Long, ArrayList<File>> hashMap = new HashMap<>();
        if (arrayList != null) {
            HashMap<Long, ArrayList<File>> FindDuplicatesBySize = FindDuplicatesBySize(arrayList);
            ArrayList arrayList2 = new ArrayList(FindDuplicatesBySize.keySet());
            for (int i = 0; i < arrayList2.size(); i++) {
                ArrayList<File> arrayList3 = FindDuplicatesBySize.get(arrayList2.get(i));
                int size = arrayList3.size();
                for (int i2 = 0; i2 < size; i2++) {
                    for (int i3 = 0; i3 < size; i3++) {
                        if (i2 != i3 && i2 < size && i3 < size) {
                            try {
                                if (contentEquals(arrayList3.get(i2), arrayList3.get(i3))) {
                                    File file = arrayList3.get(i2);
                                    if (hashMap.containsKey(Long.valueOf(file.length()))) {
                                        ArrayList<File> arrayList4 = hashMap.get(Long.valueOf(file.length()));
                                        if (!arrayList4.contains(file)) {
                                            arrayList4.add(file);
                                        }
                                    } else {
                                        ArrayList<File> arrayList5 = new ArrayList<>();
                                        arrayList5.add(file);
                                        hashMap.put(Long.valueOf(file.length()), arrayList5);
                                    }
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private HashMap<Long, ArrayList<File>> FindDuplicatesBySize(ArrayList<File> arrayList) {
        HashMap<Long, ArrayList<File>> hashMap = new HashMap<>();
        Iterator<File> it = arrayList.iterator();
        while (it.hasNext()) {
            File next = it.next();
            long length = next.length();
            if (hashMap.containsKey(Long.valueOf(length))) {
                hashMap.get(Long.valueOf(length)).add(next);
            } else {
                ArrayList<File> arrayList2 = new ArrayList<>();
                arrayList2.add(next);
                hashMap.put(Long.valueOf(length), arrayList2);
            }
        }
        ArrayList arrayList3 = new ArrayList(hashMap.keySet());
        for (int i = 0; i < arrayList3.size(); i++) {
            try {
                if (hashMap.get(arrayList3.get(i)).size() == 1) {
                    hashMap.remove(arrayList3.get(i));
                }
            } catch (Exception unused) {
            }
        }
        return hashMap;
    }

    private boolean contentEquals(File file, File file2) throws IOException {
        if (file.exists() && file2.exists() && file.length() == file2.length()) {
            if (file.length() <= 3000) {
                try {
                    return FileUtils.contentEquals(file, file2);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            try {
                if (file.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        byte[] bArr = new byte[512];
                        IOUtils.read(fileInputStream, bArr, 0, 512);
                        new String(bArr);
                        IOUtils.skip(fileInputStream, (file.length() / 2) - 256);
                        byte[] bArr2 = new byte[512];
                        IOUtils.read(fileInputStream, bArr2, 0, 512);
                        String str = new String(bArr2);
                        IOUtils.skip(fileInputStream, file.length() - 512);
                        byte[] bArr3 = new byte[512];
                        IOUtils.read(fileInputStream, bArr3, 0, 512);
                        String str2 = new String(bArr3);
                        byte[] bArr4 = new byte[512];
                        IOUtils.read(fileInputStream2, bArr4, 0, 512);
                        new String(bArr4);
                        IOUtils.skip(fileInputStream2, (file2.length() / 2) - 256);
                        byte[] bArr5 = new byte[512];
                        IOUtils.read(fileInputStream2, bArr5, 0, 512);
                        String str3 = new String(bArr5);
                        IOUtils.skip(fileInputStream2, file2.length() - 512);
                        byte[] bArr6 = new byte[512];
                        IOUtils.read(fileInputStream2, bArr6, 0, 512);
                        String str4 = new String(bArr6);
                        if (str3.equals(str3) && str.equals(str3) && str2.equals(str4)) {
                            return true;
                        }
                        fileInputStream.close();
                        fileInputStream2.close();
                        fileInputStream.close();
                        fileInputStream2.close();
                        fileInputStream.close();
                        fileInputStream2.close();
                        return false;
                    } catch (IOException unused) {
                    } finally {
                        fileInputStream.close();
                        fileInputStream2.close();
                    }
                }
            } catch (FileNotFoundException unused2) {
            }
        }
        return false;
    }

    public String GetTitle(int i) {
        if (i == 1) {
            return getString(R.string.scan_image_file);
        }
        if (i == 2) {
            return getString(R.string.scan_video_file);
        }
        if (i == 3) {
            return getString(R.string.scan_audio_file);
        }
        if (i == 4) {
            return getString(R.string.scan_doc_file);
        }
        if (i != 5) {
            return getString(R.string.app_name);
        }
        return getString(R.string.scan_all_file);
    }

    public HashMap<String, ArrayList<File>> GetMediaGroup(int i) {
        if (i == 1) {
            this.mListImage.put(getString(R.string.jpg), this.fileTypeArray.get(getString(R.string.jpg)));
            this.mListImage.put(getString(R.string.jpeg), this.fileTypeArray.get(getString(R.string.jpeg)));
            this.mListImage.put(getString(R.string.png), this.fileTypeArray.get(getString(R.string.png)));
            this.mListImage.put(getString(R.string.bmp), this.fileTypeArray.get(getString(R.string.bmp)));
            this.mListImage.put(getString(R.string.gif), this.fileTypeArray.get(getString(R.string.gif)));
            return this.mListImage;
        } else if (i == 2) {
            this.mListVideo.put(getString(R.string._3gp), this.fileTypeArray.get(getString(R.string._3gp)));
            this.mListVideo.put(getString(R.string.mp4), this.fileTypeArray.get(getString(R.string.mp4)));
            this.mListVideo.put(getString(R.string.mkv), this.fileTypeArray.get(getString(R.string.mkv)));
            this.mListVideo.put(getString(R.string.webm), this.fileTypeArray.get(getString(R.string.webm)));
            return this.mListVideo;
        } else if (i == 3) {
            this.mListAudio.put(getString(R.string.mp3), this.fileTypeArray.get(getString(R.string.mp3)));
            this.mListAudio.put(getString(R.string.aac), this.fileTypeArray.get(getString(R.string.aac)));
            this.mListAudio.put(getString(R.string.amr), this.fileTypeArray.get(getString(R.string.amr)));
            this.mListAudio.put(getString(R.string.m4a), this.fileTypeArray.get(getString(R.string.m4a)));
            this.mListAudio.put(getString(R.string.ogg), this.fileTypeArray.get(getString(R.string.ogg)));
            this.mListAudio.put(getString(R.string.wav), this.fileTypeArray.get(getString(R.string.wav)));
            this.mListAudio.put(getString(R.string.flac), this.fileTypeArray.get(getString(R.string.flac)));
            return this.mListAudio;
        } else if (i == 4) {
            this.mListDoc.put(getString(R.string.doc), this.fileTypeArray.get(getString(R.string.doc)));
            this.mListDoc.put(getString(R.string.docx), this.fileTypeArray.get(getString(R.string.docx)));
            this.mListDoc.put(getString(R.string.html), this.fileTypeArray.get(getString(R.string.html)));
            this.mListDoc.put(getString(R.string.pdf), this.fileTypeArray.get(getString(R.string.pdf)));
            this.mListDoc.put(getString(R.string.txt), this.fileTypeArray.get(getString(R.string.txt)));
            this.mListDoc.put(getString(R.string.xml), this.fileTypeArray.get(getString(R.string.xml)));
            this.mListDoc.put(getString(R.string.xlsx), this.fileTypeArray.get(getString(R.string.xlsx)));
            return this.mListDoc;
        } else if (i != 5) {
            return this.fileTypeArray;
        } else {
            this.mListOtherFile.put(getString(R.string.zip), this.fileTypeArray.get(getString(R.string.zip)));
            this.mListOtherFile.put(getString(R.string.apk), this.fileTypeArray.get(getString(R.string.apk)));
            this.mListOtherFile.put(getString(R.string.vcf), this.fileTypeArray.get(getString(R.string.vcf)));
            this.mListOtherFile.put(getString(R.string.js), this.fileTypeArray.get(getString(R.string.js)));
            this.mListOtherFile.put(getString(R.string.css), this.fileTypeArray.get(getString(R.string.css)));
            this.mListOtherFile.put(getString(R.string.dat), this.fileTypeArray.get(getString(R.string.dat)));
            this.mListOtherFile.put(getString(R.string.cache), this.fileTypeArray.get(getString(R.string.cache)));
            return this.mListOtherFile;
        }
    }
}
