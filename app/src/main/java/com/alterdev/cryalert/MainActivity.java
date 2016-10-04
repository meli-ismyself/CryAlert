package com.alterdev.cryalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by meli.oktavia on 27/08/2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoyangAyunan = (Button)findViewById(R.id.btn_goyang_ayunan);
        btnGoyangAyunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoyangAyunan();
            }
        });


    }

    private void GoyangAyunan(){
        GetImeiDevice getimei = new GetImeiDevice(this);
        String imei = getimei.getDeviceID();
        String data = "http://dokterbrankas.com/scifi/command.php?action=goyang&imei=" +imei ;
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

    @Override
    protected void onPause() {
        System.out.println("onPause ++++++++++++++++++++++++++++++ ");
        super.onPause();
    }
}
