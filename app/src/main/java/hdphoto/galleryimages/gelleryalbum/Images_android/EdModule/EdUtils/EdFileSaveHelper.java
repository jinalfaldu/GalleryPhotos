package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdUtils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EdFileSaveHelper implements LifecycleObserver {
    ExecutorService executor;
    MutableLiveData<FileMeta> fileCreatedResult;
    ContentResolver gContentResolver;
    Observer<FileMeta> observer;
    OnFileCreateResult resultListener;


    public interface OnFileCreateResult {
        void onFileCreateResult(boolean z, String str, String str2, Uri uri);
    }

    public void lambda$new$0$EMFileSaveHelper(FileMeta fileMeta) {
        OnFileCreateResult onFileCreateResult = this.resultListener;
        if (onFileCreateResult != null) {
            onFileCreateResult.onFileCreateResult(fileMeta.isCreated, fileMeta.filePath, fileMeta.error, fileMeta.uri);
        }
    }

    public EdFileSaveHelper(ContentResolver contentResolver) {
        this.observer = new Observer() {
            @Override
            public final void onChanged(Object obj) {
                EdFileSaveHelper.this.lambda$new$0$EMFileSaveHelper((FileMeta) obj);
            }
        };
        this.gContentResolver = contentResolver;
        this.executor = Executors.newSingleThreadExecutor();
        this.fileCreatedResult = new MutableLiveData<>();
    }

    public EdFileSaveHelper(AppCompatActivity appCompatActivity) {
        this(appCompatActivity.getContentResolver());
        addObserver(appCompatActivity);
    }

    private void addObserver(LifecycleOwner lifecycleOwner) {
        this.fileCreatedResult.observe(lifecycleOwner, this.observer);
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void release() {
        ExecutorService executorService = this.executor;
        if (executorService != null) {
            executorService.shutdownNow();
        }
    }

    public static boolean isSdkHigherThan28() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public void createFile(final String str, OnFileCreateResult onFileCreateResult) {
        this.resultListener = onFileCreateResult;
        this.executor.submit(new Runnable() {
            @Override 
            public final void run() {
                EdFileSaveHelper.this.lambda$createFile$1$EMFileSaveHelper(str);
            }
        });
    }

    public void lambda$createFile$1$EMFileSaveHelper(String str) {
        try {
            ContentValues contentValues = new ContentValues();
            Uri GetEditedImageUri = GetEditedImageUri(str, contentValues, buildUriCollection(contentValues));
            UpdateResult(true, GetFilePath(null, GetEditedImageUri), null, GetEditedImageUri, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            UpdateResult(false, null, e.getMessage(), null, null);
        }
    }

    private String GetFilePath(Cursor cursor, Uri uri) {
        Cursor query = this.gContentResolver.query(uri, new String[]{SMUtilsWP.mediaPath}, null, null, null);
        int columnIndexOrThrow = query.getColumnIndexOrThrow(SMUtilsWP.mediaPath);
        query.moveToFirst();
        return query.getString(columnIndexOrThrow);
    }

    private Uri GetEditedImageUri(String str, ContentValues contentValues, Uri uri) throws IOException {
        contentValues.put("_display_name", str);
        Uri insert = this.gContentResolver.insert(uri, contentValues);
        this.gContentResolver.openOutputStream(insert).close();
        return insert;
    }

    private Uri buildUriCollection(ContentValues contentValues) {
        if (!isSdkHigherThan28()) {
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        Uri contentUri = MediaStore.Images.Media.getContentUri("external_primary");
        contentValues.put("is_pending", (Integer) 1);
        return contentUri;
    }

    public void lambda$notifyThatFileIsNowPubliclyAvailable$2$EMFileSaveHelper(ContentResolver contentResolver) {
        FileMeta value = this.fileCreatedResult.getValue();
        if (value != null) {
            value.cvDetails.clear();
            value.cvDetails.put("is_pending", (Integer) 0);
            contentResolver.update(value.uri, value.cvDetails, null, null);
        }
    }


    public static class FileMeta {
        ContentValues cvDetails;
        String error;
        String filePath;
        boolean isCreated;
        Uri uri;

        public FileMeta(boolean z, String str, Uri uri, String str2, ContentValues contentValues) {
            this.isCreated = z;
            this.filePath = str;
            this.uri = uri;
            this.error = str2;
            this.cvDetails = contentValues;
        }
    }

    private void UpdateResult(boolean z, String str, String str2, Uri uri, ContentValues contentValues) {
        this.fileCreatedResult.postValue(new FileMeta(z, str, uri, str2, contentValues));
    }
}
