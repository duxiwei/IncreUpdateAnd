package com.duxiwei.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;

import java.io.File;

/**
 * Created by duxiwei on 18-7-29.
 * Mail  duxiwei@aliyun.com
 */
public class ApkUpdate {

    static {
        System.loadLibrary("update");
    }

    public static native int update(String oldApk, String newApk, String patch);

    @TargetApi(Build.VERSION_CODES.DONUT)
    public static String extract(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String apkPath = applicationInfo.sourceDir;
        return apkPath;
    }

    public static void install(Context context, String apkPath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.fromFile(new File(apkPath)),
                "application/vnd.android.package-archive");
//        context.startActivity(i);
        context.startActivity(Intent.createChooser(i, "新版本"));
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
