package com.test.mytest.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.blankj.utilcode.utils.LogUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * Created by yyt19 on 2017/11/26.
 */

public class CustomTakePhotoHelper {
    static File file = new File(Environment.getExternalStorageDirectory(),
            "/temp/" + System.currentTimeMillis() + ".jpg");

    /**
     * 从文件中选择照片
     */
    public static void pickByAlbum(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromGalleryWithCrop(getImageUri(), getCropOptions());
    }


    /**
     * 拍照
     *
     * @param takePhoto
     */
    public static void pickByCamera(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromCaptureWithCrop(getImageUri(), getCropOptions());
    }

    private static void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);//takephoto自带相册
        builder.setCorrectImage(true);//纠正拍照的照片旋转角度
        takePhoto.setTakePhotoOptions(builder.create());
    }

    //压缩
    private static void configCompress(TakePhoto takePhoto) {
        CompressConfig config;
        LubanOptions options = new LubanOptions.Builder()
                .setMaxSize(102400)
                .create();
        config = CompressConfig.ofLuban(options);
        config.enableReserveRaw(true);//是否保留原文件
        takePhoto.onEnableCompress(config, true);
    }

    private static Uri getImageUri() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file=new File(Environment.getExternalStorageDirectory(),
                "/temp/" + System.currentTimeMillis() + ".jpg");
        LogUtils.e("filepath",file.getPath());
        return Uri.fromFile(file);
    }

    //裁剪
    private static CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(800).setOutputY(800);//宽x高
        builder.setWithOwnCrop(true);
        return builder.create();
    }

    //获取宽高
    public static int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        /**
         *options.outHeight为原始图片的高
         */
        return new int[]{options.outWidth, options.outHeight};
    }
}
