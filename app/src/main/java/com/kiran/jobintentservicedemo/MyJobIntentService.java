package com.kiran.jobintentservicedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;


public class MyJobIntentService extends JobIntentService{

    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        int top = 5;  //default is 5.
        int num;

        for (int i = 0; i < top; i++) {
            num = mGenerator.nextInt(100);
            toast("Random number is " + num+" i: "+i);
            Log.wtf(TAG, "Random number is " + num+" i: "+i);
            try {
                Thread.sleep(1000);  // 1000 is one second, ten seconds would be 10000
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        toast("All work complete");
    }

    final Handler handler = new Handler();

    private void toast(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyJobIntentService.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    static final int JOB_ID = 1000;
    final String TAG = "MyJobIntenetService";
    // Random number generator
    private final Random mGenerator = new Random();
}
