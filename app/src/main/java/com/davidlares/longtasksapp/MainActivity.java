package com.davidlares.longtasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.davidlares.longtasksapp.services.MyIntentService;
import com.davidlares.longtasksapp.services.MyService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActTag";
    private MyService mService;
    private TextView mTextView;
    private Button mButton;

    private final ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.ServiceBinder serviceBinder = (MyService.ServiceBinder) iBinder;
            mService = serviceBinder.getService();
            Log.d(TAG, "OnService Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            if(mService != null) {
                mService = null;
            }
            Log.d(TAG, "OnServiceDisconnected");
        }
    };

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MyIntentService.MESSAGE_KEY);
            Log.d(TAG, message);
            mTextView.append(message + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customThread();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // bind to a service
        Intent serviceIntent = new Intent(this, MyService.class);
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "OnBind - Service bound");

        // using Localbroadcast
        // LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
    }

    public void customThread() {
        Log.d(TAG, "Starting custom thread");
        Log.d(TAG, mService.getAValue());
        mTextView.append(mService.getAValue() + "\n");
        // MyIntentService.startActionFoo(this,"Value1", "Value2");
    }
}
