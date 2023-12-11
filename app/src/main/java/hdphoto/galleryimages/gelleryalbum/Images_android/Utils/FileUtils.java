package hdphoto.galleryimages.gelleryalbum.Images_android.Utils;

import android.content.Context;
import android.util.Log;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.MediaScanners;
import hdphoto.galleryimages.gelleryalbum.Images_android.MyAppClass.GalleryAppClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;


public class FileUtils {
    public static int GIGA = 1073741824;
    public static int KILO = 1024;
    public static int MEGA = 1048576;

    public static boolean GalleryContains(File[] fileArr, File file) {
        for (File file2 : fileArr) {
            if (GalleryContains(file2, file)) {
                return true;
            }
        }
        return false;
    }

    public static String getFileExtension(String str) {
        return str.lastIndexOf(".") == -1 ? "" : str.substring(str.lastIndexOf(".") + 1, str.length());
    }

    public static boolean GalleryContains(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        String absolutePath = file.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        if (absolutePath.equals(absolutePath2)) {
            return true;
        }
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        return absolutePath2.startsWith(absolutePath);
    }

    public static String getFileName(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    public static String convertToHumanReadableSize(Context context, long j) {
        if (j == 0) {
            Log.d("lplplp", "convertToHumanReadableSize====" + j);
            return "";
        }
        long j2 = KILO;
        if (j < j2) {
            return context.getString(R.string.bytes, String.valueOf(j));
        }
        long j3 = MEGA;
        return j < j3 ? context.getString(R.string.kilobytes, String.valueOf(j / j2)) : j < org.apache.commons.io.FileUtils.ONE_GB ? context.getString(R.string.megabytes, String.valueOf(j / j3)) : context.getString(R.string.gigabytes, new DecimalFormat("0.#").format(((float) j) / 1.07374182E9f));
    }

    /* JADX WARN: Finally extract failed */
    public static boolean copy(InputStream inputStream, OutputStream outputStream) {
        try {
            IoUtils.copy(inputStream, outputStream);
            outputStream.flush();
            IoUtils.closeQuietly(inputStream);
            IoUtils.closeQuietly(outputStream);
            return true;
        } catch (IOException unused) {
            Log.e("TransferThread", "writing failed");
            IoUtils.closeQuietly(inputStream);
            IoUtils.closeQuietly(outputStream);
            return false;
        } catch (Throwable th) {
            IoUtils.closeQuietly(inputStream);
            IoUtils.closeQuietly(outputStream);
            throw th;
        }
    }

    public static File copyFile(File file, File file2) throws Exception {
        try {
            if (!file.isDirectory()) {
                file2.createNewFile();
                FileChannel channel = new FileInputStream(file).getChannel();
                channel.transferTo(0L, channel.size(), new FileOutputStream(file2).getChannel());
                return file2;
            } else if (!file.getPath().equals(file2.getPath())) {
                File createDirectory = createDirectory(file2, file.getName());
                for (File file3 : file.listFiles()) {
                    copyFile(file3, createDirectory);
                }
                return createDirectory;
            } else {
                throw new Exception();
            }
        } catch (Exception unused) {
            throw new Exception(String.format("Error copying %s", file.getName()));
        }
    }

    public static File createDirectory(File file, String str) throws Exception {
        File file2 = new File(file, str);
        if (file2.mkdirs()) {
            return file2;
        }
        if (file2.exists()) {
            throw new Exception(String.format("%s already exists", str));
        }
        throw new Exception(String.format("Error creating %s", str));
    }

    public static File CopyMoveFile(File file, File file2) throws Exception {
        try {
            if (!file.isDirectory()) {
                file2.createNewFile();
                FileChannel channel = new FileInputStream(file).getChannel();
                channel.transferTo(0L, channel.size(), new FileOutputStream(file2).getChannel());
                new MediaScanners(GalleryAppClass.getInstance(), file2);
                return file2;
            } else if (!file.getPath().equals(file2.getPath())) {
                File CopyMoveCreateDirectory = CopyMoveCreateDirectory(file2, file.getName());
                for (File file3 : file.listFiles()) {
                    CopyMoveFile(file3, CopyMoveCreateDirectory);
                }
                return CopyMoveCreateDirectory;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log.d("COPPYYY", " unused " + e.getMessage());
            throw new Exception(String.format("Error copying %s", file.getName()));
        }
    }

    public static File CopyMoveCreateDirectory(File file, String str) throws Exception {
        File file2 = new File(file, str);
        if (file2.mkdirs()) {
            return file2;
        }
        if (file2.exists()) {
            throw new Exception(String.format("%s already exists", str));
        }
        throw new Exception(String.format("Error creating %s", str));
    }
}
