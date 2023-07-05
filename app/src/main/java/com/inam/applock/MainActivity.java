package com.inam.applock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Design dependency

    RecyclerView appList;
    FloatingActionButton okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appList = findViewById(R.id.app_list);
        okBtn = findViewById(R.id.ok_btn);


        appList.setLayoutManager(new LinearLayoutManager(this));
        loadAppList();


    }

    //Loading the list of all the apps from the android phone
    private void loadAppList() {
        List<AppInfo> apps = new ArrayList<>();

        //Getting the list of all the apps info
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> homeInfo = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo info : homeInfo) {
            AppInfo appInfo = new AppInfo();
            appInfo.setAppLogo(info.activityInfo.loadIcon(packageManager));
            appInfo.setPackageName(info.activityInfo.packageName);
            appInfo.setAppName((String) info.activityInfo.loadLabel(packageManager));

            //Adding app info to our list
            apps.add(appInfo);
        }

        //Making an adapter
        AppListAdapter adapter = new AppListAdapter(apps);
        appList.setAdapter(adapter);
    }
}