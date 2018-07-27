package com.kewenc.kewencaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kewenc.kewencaidl.service.AIDLInterface;

public class MainActivity extends AppCompatActivity {
    private AIDLInterface aidlInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlInterface = AIDLInterface.Stub.asInterface(iBinder);
            try {
                aidlInterface.setData("Hello AIDL");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Log.e("TAGF",aidlInterface.getData());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this, AIDLService.class);
        startService(intent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }
}
