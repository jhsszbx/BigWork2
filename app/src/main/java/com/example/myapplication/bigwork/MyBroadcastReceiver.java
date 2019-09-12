package com.example.myapplication.bigwork;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.bigwork.activity.CommodityDetailsActivity;
import com.example.myapplication.bigwork.adapter.HomeTuijianRecyclerviewAdapter;
import com.example.myapplication.bigwork.table.Goods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.constraint.Constraints.TAG;

/**
 * Created by 28675 on 2019/3/25.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    private int commodityId;
    private String tradeName;


    // 复写onReceive()方法
    // 接收到广播后，则自动调用该方法
    @Override
    public void onReceive(Context context, Intent intent) {

        //写入接收广播后的操作
        commodityId = intent.getIntExtra("commodityId",0);
        Log.d("onReceive.commodityId", "" +commodityId);
        tradeName = intent.getStringExtra("tradeName");
        Log.d("onReceivetradeName", "" +tradeName);

        // 接收数据
        String action = intent.getAction(); //获取收到广播的名称
        Log.e("MyBroadcastReceiver", "onReceive被使用了，现在接收到的是来自习近平的问候："+ action );

        // 发生跳转传输数据设置
        // 获取NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan1 = new NotificationChannel("1",
                    "Primary Channel", NotificationManager.IMPORTANCE_DEFAULT);
            chan1.setLightColor(Color.GREEN);
            chan1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mNotificationManager.createNotificationChannel(chan1);
//            mBuilder = new NotificationCompat.Builder(Utils.getContext(), PRIMARY_CHANNEL);
        }

        // 创建NotiftionCompat.Builder
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "1");

        // 对Builder设置一些Notification相关属性：
        mBuilder.setContentTitle("商品又有折扣了！！！") //设置通知栏标题
                .setContentText(tradeName + "内容") //设置通知栏显示内容.setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
                //.setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
                //  .setNumber(number) //设置通知集合的数量
                .setTicker("通知到来") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                //  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                 //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                 .setSmallIcon(R.drawable.ic_launcher_background);//设置通知小ICON

        // 使用Builder创建通知
        Notification notification = mBuilder.build();

        // 使用NotificationManager将通知推送出去
        int id = 199;
        Log.d("mNotificationManager", "创建通知");
        mNotificationManager.notify(id, notification);

    }
}
