package com.example.harsh.talos;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SimpleTimeZone;
import com.example.harsh.talos.adapter.listAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private RecyclerView recyclerView;
    private listAdapter listadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        final PackageManager pm = getPackageManager();
        //List<ApplicationInfo> installedApps = pm.getInstalledApplications(0);
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);
        ArrayList<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();

        for(ApplicationInfo app : apps) {
            //checks for flags; if flagged, check if updated system app
            if((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                installedApps.add(app);
                //it's a system app, not interested
            } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                //Discard this one
                //in this case, it should be a user-installed app
            } else {
                installedApps.add(app);
            }
        }

        Collections.sort(installedApps, new Comparator<ApplicationInfo>() {
            @Override
            public int compare(ApplicationInfo applicationInfo, ApplicationInfo t1) {
                return (applicationInfo.loadLabel(pm).toString()).compareToIgnoreCase(t1.loadLabel(pm).toString());
            }
        });

        listadapter = new listAdapter(installedApps, this, pm);

        recyclerView.setAdapter(listadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*List<String> App_names = new ArrayList<>();
       // List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (int i = 0; i< installedApps.size(); ++i){
            App_names.add(i,installedApps.get(i).loadLabel(pm).toString());
        }*/

       /* for (ApplicationInfo applicationInfo : packages) {
            Log.d("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);

            try {
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

                //Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;

                if(requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        Log.d("test", requestedPermissions[i]);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }*/

    }
}
