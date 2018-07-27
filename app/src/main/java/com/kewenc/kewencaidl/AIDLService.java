package com.kewenc.kewencaidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.kewenc.kewencaidl.service.AIDLInterface;
import com.kewenc.kewencaidl.service.PushInterface;

public class AIDLService extends Service {
    public AIDLService() {
    }
private PushInterface pushInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            pushInterface = PushInterface.Stub.asInterface(iBinder);
            try {
                pushInterface.setData("Hello Push");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Log.e("TAGF",pushInterface.getData());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    public void onCreate() {
        Intent intent = new Intent(this, PushService.class);
        super.onCreate();
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    String data = "AIDLService";
    private Binder binder = new AIDLInterface.Stub(){

        @Override
        public String getData() throws RemoteException {
            return data;
        }

        @Override
        public void setData(String str) throws RemoteException {
            data = str;
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
