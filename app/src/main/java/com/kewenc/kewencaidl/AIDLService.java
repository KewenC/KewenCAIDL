package com.kewenc.kewencaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.kewenc.kewencaidl.service.AIDLInterface;

public class AIDLService extends Service {
    public AIDLService() {
    }
    String data = "";
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
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }
}
