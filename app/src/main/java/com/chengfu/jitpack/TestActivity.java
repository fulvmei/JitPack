package com.chengfu.jitpack;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.xml.sax.XMLReader;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.xml.XMLConstants;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toast.makeText(this, "第一次启动", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("eee", "onNewIntent");
    }

    @Override
    public void finish() {
        navToLauncherTask(this);
//        super.finish();
    }

    public static void navToLauncherTask(@NonNull Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        assert activityManager != null;
        final List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
        boolean hasPlayTask = false;
        ActivityManager.AppTask launcherTask = null;
        for (ActivityManager.AppTask task : appTasks) {
            final Intent baseIntent = task.getTaskInfo().baseIntent;
            if (Objects.equals(baseIntent.getComponent(), activity.getComponentName())) {
                hasPlayTask = true;
            }
            final Set<String> categories = baseIntent.getCategories();
            if (categories != null && categories.contains(Intent.CATEGORY_LAUNCHER)) {
                launcherTask = task;

            }
        }
        if (hasPlayTask && launcherTask != null) {
            launcherTask.moveToFront();
        }
    }
}