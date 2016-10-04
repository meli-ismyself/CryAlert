package com.alterdev.cryalert;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Created by meli.oktavia on 27/08/2016.
 */
public class GetImeiDevice {

    Context context;

    public GetImeiDevice(Context context){
        this.context = context;
    }

    public String getDeviceID(){
        String result = "";

        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        result = deviceId;
        return result;
    }

    public String getImeiNumber(){
        TelephonyManager tManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceIMEI = tManager.getDeviceId();

        return  deviceIMEI;
    }
}