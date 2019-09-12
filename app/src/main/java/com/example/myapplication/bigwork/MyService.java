package com.example.myapplication.bigwork;

import android.app.Service;
import android.content.Intent;

import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    private MyThread thread=null;
    public static int i=5;

    private int commodityId;
    private String tradeName;

    public MyService() {
        Intent intent = new Intent();
        commodityId = intent.getIntExtra("commodityId",0);
        tradeName = intent.getStringExtra("tradeName");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * ③重写onStartCommand方法
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //⑤在service中实例化并开启线程
        if(thread==null){
            thread=new MyThread();
            thread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * ④自定义线程类，设置倒计时的线程方法
     */
    class MyThread extends Thread{
        @Override
        public void run() {
            while (i>=0){
                Intent intent=new Intent();

                intent.putExtra("commodityId",0);
                intent.putExtra("tradeName", 0);
                sendBroadcast(intent);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
            super.run();
        }
    }

}
