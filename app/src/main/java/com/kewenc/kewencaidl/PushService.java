package com.kewenc.kewencaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.kewenc.kewencaidl.service.AIDLInterface;
import com.kewenc.kewencaidl.service.PushInterface;

public class PushService extends Service {
    public PushService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    String data = "PushService";
    private Binder binder = new PushInterface.Stub(){

        @Override
        public String getData() throws RemoteException {
            return data;
        }

        @Override
        public void setData(String str) throws RemoteException {
            data = str;
        }
    };
}
