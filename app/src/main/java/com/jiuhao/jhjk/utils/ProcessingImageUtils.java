package com.jiuhao.jhjk.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.blankj.utilcode.util.FileUtils;
import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片处理
 */

public class ProcessingImageUtils {
    /**
     * 获取uri真实路径
     */
    public static String getRealFilePath(final Uri uri) {
        String path = uri.getPath();
        String data = path.replace("external_files", Environment.getExternalStorageDirectory().getPath());
        Logger.d("获取uri真实路径: \nuri:" + path + "\npath:" + data);
        return data;
    }

    /**
     * 根据Uri获取文件真实地址
     */
    public String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String realPath = null;
        if (scheme == null)
            realPath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            realPath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        realPath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        if (TextUtils.isEmpty(realPath)) {
            if (uri != null) {
                String uriString = uri.toString();
                int index = uriString.lastIndexOf("/");
                String imageName = uriString.substring(index);
                File storageDir;

                storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File file = new File(storageDir, imageName);
                if (file.exists()) {
                    realPath = file.getAbsolutePath();
                } else {
                    storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file1 = new File(storageDir, imageName);
                    realPath = file1.getAbsolutePath();
                }
            }
        }
        return realPath;
    }


    /**
     * 采样率压缩
     *
     * @param filePath 压缩图
     * @param file     压缩的图片保存地址
     */
    public static void pixeCompressBitmap(String filePath, File file) {
        //采样率，数值越高，图片像素越低
        //压缩    1/3
        int inSampleSize = 2;
        BitmapFactory.Options osts = new BitmapFactory.Options();
        osts.inSampleSize = inSampleSize;
        //inJustDecodeBounds设为True时，不会真正加载图片，而是得到图片的宽高信息。
        osts.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(filePath, osts);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        try {
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(stream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();

            FileUtils.deleteFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 尺寸压缩
     * 通过缩放图片的像素，减小图片占用内存大小,这个比如用于缩略图
     *
     * @param bitmap 要压缩的图片
     * @param ratio  压缩比例，值越大，图片的尺寸就越小
     * @param file   压缩的图片保存地址
     */
    public static void sizeCompressBitmap(Bitmap bitmap, int ratio, File file) {
        if (ratio <= 0) {
            return;
        }
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / ratio, bitmap.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        Rect rect = new Rect(0, 0, bitmap.getWidth() / ratio, bitmap.getHeight() / ratio);
        canvas.drawBitmap(bitmap, null, rect, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 质量压缩
     * 这个只是降低了图片的质量，但是像素是不会减小的
     *
     * @param bitmap 要压缩的图片
     * @param file   //压缩的图片保存地址
     *               quality  (0-100)  100是不压缩，值越小，压缩得越厉害
     */
    public static void qualityCompressBitmap(Bitmap bitmap, File file) {
        //字节数组输出流
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int quality = 90;
        //图片压缩后把数据放在stream中
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //不断把stream的数据写文件输出流中去
            fileOutputStream.write(stream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
