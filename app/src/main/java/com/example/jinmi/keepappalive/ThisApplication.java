package com.example.jinmi.keepappalive;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jinmi on 2017/12/15 0015.
 */

public class ThisApplication extends Application {
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.i("Application", activity.getLocalClassName() + " onCreate");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                count++;
                Log.i("Application", activity.getLocalClassName() + " onStart");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.i("Application", activity.getLocalClassName() + " onResumed");

            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.i("Application", activity.getLocalClassName() + " onPaused");

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                Log.i("Application", activity.getLocalClassName() + " onStop");
                if (count == 0) {
                    Toast.makeText(getApplicationContext(), "app在后台", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ForeService.class);
                    startService(intent);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.i("Application", activity.getLocalClassName() + " onSave");

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.i("Application", activity.getLocalClassName() + " onDestroy");

            }
        });
    }
}
