package hdphoto.galleryimages.gelleryalbum.Images_android.Constant;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import java.io.File;


public class MediaScanners implements MediaScannerConnection.MediaScannerConnectionClient {
    File destFile;
    MediaScannerConnection mScannerConnection;

    public MediaScanners(Context context, File file) {
        this.destFile = file;
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(context, this);
        this.mScannerConnection = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        this.mScannerConnection.scanFile(this.destFile.getAbsolutePath(), null);
    }

    @Override
    public void onScanCompleted(String str, Uri uri) {
        this.mScannerConnection.disconnect();
    }
}
