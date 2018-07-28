package com.duxiwei.increupdateand;

/**
 * Created by duxiwei on 18-7-29.
 * Mail  duxiwei@aliyun.com
 */

import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.duxiwei.util.ApkUpdate;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void doBspatch() {
        final File destApk = new File(Environment.getExternalStorageDirectory(), "dest.apk");
        final File patch = new File(Environment.getExternalStorageDirectory(), "PATCH.patch");

        Log.e("hongyang", "patch = " + patch.exists() + " , " + patch.getAbsolutePath());

        ApkUpdate.update(ApkUpdate.extract(this),
                destApk.getAbsolutePath(),
                patch.getAbsolutePath());

        Log.e("hongyang", new File(Environment.getExternalStorageDirectory(), "old").getAbsolutePath() + " , " + destApk.exists());

        if (destApk.exists())
            ApkUpdate.install(this, destApk.getAbsolutePath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                doBspatch();
            }
        }
    }
}
