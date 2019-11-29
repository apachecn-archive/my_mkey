package com.example.my_mkey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.netease.mkey.core.OtpLib;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView mainTv;
    TextView my_key;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTv = findViewById(R.id.main_tv);
        my_key = findViewById(R.id.my_mkey);
        progressbar = findViewById(R.id.progress_bar);
        new TimeThread().start();
    }
    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);

        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    mainTv.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
                    long ct=(System.currentTimeMillis()/1000)%30;
                    long e= System.currentTimeMillis() / 1000;
                    if(ct==0){   String  otp=OtpLib.my_otp(e,"8888888888","11111111111111111111111111111111");my_key.setText(otp);}
                    //str是你的序列号，str2需要hook拿到，然后只要不重新激活，就可以永久用这个app生成6位otp了

                        int pgs=Integer.parseInt(String.valueOf(ct));

                    progressbar.setProgress(pgs);
                    break;
            }
            return false;
        }
    });






}
