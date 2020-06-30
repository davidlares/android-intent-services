package com.davidlares.longtasksapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "ServiceTag";
    private final Binder mBinder = new ServiceBinder();

    public MyService() {
        Log.d(TAG, "MyService: Service created");
    }

    @Override
    public IBinder onBind(Intent intent) {
       // should return the binder object here
       return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "On Create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "On Destroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "OnUnBind Service");
        return super.onUnbind(intent);
    }


    public String getAValue() {
        return "from the service";
    }

    // nested class
    public class ServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
