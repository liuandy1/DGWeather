package com.fgwx.dgweather.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by M on 2016/1/23.
 */
public class ScreenShoot {
    private static Bitmap takeScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // 去掉标题栏
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    public static void savePic(Bitmap b, File filePath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    public static void shoot(Activity a, File filePath) {
        if (filePath == null) {
            return;
        }
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        ScreenShoot.savePic(ScreenShoot.takeScreenShot(a), filePath);
    }

    public static void deleteScreenShootImage() {
        File file = new File(Constant.IMAGE_PATH);
        deleteFile(file);
    }

    private static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    deleteFile(f);
                }
                file.delete();
            }
        }
    }

    public static void dd(View v) {
        LogUtil.e("DDDDDDD");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
        String fname = "/sdcard/" + sdf.format(new Date()) + ".png";
        View view = v.getRootView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap != null) {
            LogUtil.e("bitmap is not NULL !");
            try {
                FileOutputStream out = new FileOutputStream(fname);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                LogUtil.e("file " + fname + "outputdone.");
                System.out.println("file " + fname + "outputdone.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.e("bitmap is NULL !");
            System.out.println("bitmap is NULL !");
        }
    }
}
