package com.alterdev.cryalert;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by meli.oktavia on 27/08/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";
    public static String refreshedToken;
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        Log.d(TAG, "Trying to get FCM Token ID");
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        GetData(refreshedToken);
    }
    private void GetData(String token){
        GetImeiDevice getimei = new GetImeiDevice(this);
        String imei = getimei.getDeviceID();
        String data = "http://dokterbrankas.com/scifi/register.php?token=" + token + "&imei=" +imei ;
        OkHttpHandler handler = new OkHttpHandler(data);

        byte[] dataByte = new byte[0];

        try {
            dataByte = handler.execute().get();
            if (dataByte != null && dataByte.length > 0) {
                String contentStr = new String(dataByte, "UTF-8");
                System.out.println("jsonStr 1++++++++++++++++++++++++++++++++++++++++>> " + contentStr);
            }
        }catch (Exception e){

        }
    }
    // [END refresh_token]

}